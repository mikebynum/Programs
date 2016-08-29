/*----------------------------------------------------------------------*/
/* dprog_temp.c - CSCI 8530, Fall 2014									*/
/* Worked with Tavis Whitesell on part of this                          */
/* Usage:								                                */
/*	dprog_temp memname msgname					                        */
/*									                                    */
/* Although this program is complete (it compiles and runs), it is not  */
/* a satisfactory solution for the POSIX programming project.           */
/* In particular, it doesn't clear the screen and display only the text */
/* in the shared memory region with the <b> tags, and it doesn't do the */
/* blinking of the text.                                                */
/*									                                    */
/* This program is valuable, since it demonstrates whether the iprog    */
/* generator program is working correctly.                              */
/*----------------------------------------------------------------------*/
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <signal.h>
#include <unistd.h>
#include <time.h>
#include <mqueue.h>
#include <limits.h>
#include <errno.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <sys/mman.h>
#include <semaphore.h>

struct MessageStruct
{
	char* text;
	char* textWithBlinkedContent;
};



/*--------------*/
/* Local limits */
/*--------------*/
#define MAXMEMNAME 20		/* max length of memory region's name */
#define MAXMSGNAME 20		/* max length of message queue's name */
#define MAXTXT 3000		/* max length of text to display */

/*----------------------------------------------------------------*/
/* The blink rate is determined by the following two definitions. */
/* Obviously ON_TIME indicates how long the word "blinking" will  */
/* be displayed (in milliseconds), and OFF_TIME indicates how     */
/* long blanks will be displayed instead of "blinking".           */
/* It is assumed that these times will be between 1 and 999 msec. */
/* If not, then the code that sets the timer will need changes.   */
/*----------------------------------------------------------------*/
#define ON_TIME 250			/* time on, in milliseconds */
#define OFF_TIME 250			/* time blanked, in milliseconds */

#ifndef __QNX__
#define SIGEV_SIGNAL_INIT(__e,__s)	(\
	(__e)->sigev_notify = SIGEV_SIGNAL, \
	(__e)->sigev_signo = (__s))
#endif


static char memname[MAXMEMNAME+2];	/* name of shared memory object */
static char msgname[MAXMSGNAME+2];	/* name of message queue */


static char* nonblinkingtext;
static char* blinkingtext;

static int debug = 0; /* set this to 1 to turn on debug messages */

static struct sm {		/* shared memory layout */
    sem_t sem;			    /* the semaphore */
    char txt[MAXTXT+1];		    /* the text to display */
} *smp;

static char lcltxt[MAXTXT+1];	/* our copy of the text from shared memory */

static int shmfd;		/* "file" descriptor for the shmem object */
static int mqfd;		/* message queue file descriptor */
static int blinkCycle;

static timer_t tid;			/* timer ID */
static sigset_t ss;			/* signal set */
static struct sigevent ev;			/* timer expiration event */
static struct itimerspec ts;		/* time specification */
static struct sigaction new_action;
static struct MessageStruct *msp;

void usage(void)
{
    fprintf(stderr,"Usage: dprog_temp memname msgname\n");
    fprintf(stderr,"\n");
    fprintf(stderr,"  memname is the name of the shared memory region.\n");
    fprintf(stderr,"    Do not include the initial /.\n");
    fprintf(stderr,"    (maximum length is %d characters)\n", MAXMEMNAME);
    fprintf(stderr,"  msgname is the name of the message queue.\n");
    fprintf(stderr,"    Do not include the initial /.\n");
    fprintf(stderr,"    (maximum length is %d characters)\n", MAXMSGNAME);
    fprintf(stderr,"\nCorrect the command line and try again!\n");
    exit(1);
}

/*------------------------------*/
/*moves to 1,1 and clears screen*/
/*------------------------------*/
void moveAndClearScreen()
{
	/*move to 1,1*/
	write(1, "\33[1;1H", 6);
	/*clear cursor position and all following*/
	//write(1, "\33[J", 3);
	//write(1,"\33[H\33[J",6);
}


/*---------------------------------------------------------------*/
/* Signal handler (for SIGUSR1). If the text "blinking" was      */
/* displayed, then clear it. Otherwise (if it wasn't displayed), */
/* then display it.                                              */
/*---------------------------------------------------------------*/
void handler(int signum)
{
	moveAndClearScreen();
	/*blink cycle*/
	if (blinkCycle == 1)
	{
		/*------------------------------------------------------------------*/
		/*turn off the timer by setting the interval and value times to zero*/
		/*you'll want the interval time to always be zero, single on/off blink cycle times may not be the same*/
		/*------------------------------------------------------------------*/
		/*printf("DISPLAY PROGRAM: setting timer interval to %d.\n", ON_TIME);*/
		ts.it_value.tv_sec = 0;
		ts.it_value.tv_nsec = ON_TIME * 1000000;
		if (timer_settime(tid, 0, &ts, NULL) < 0)
		{
			printf("DISPLAY PROGRAM: timer_settime FAILED!\n");		/* display error text */
		}
		printf("%s\n", blinkingtext);
		blinkCycle = 0;
	}
	/*non-blink cycle*/
	else
	{
		/*------------------------------------------------------------------*/
		/*turn off the timer by setting the interval and value times to zero*/
		/*you'll want the interval time to always be zero, single on/off blink cycle times may not be the same*/
		/*------------------------------------------------------------------*/
		/*printf("DISPLAY PROGRAM: setting timer interval to %d.\n", OFF_TIME);*/
		ts.it_value.tv_sec = 0;
		ts.it_value.tv_nsec = OFF_TIME * 1000000;
		if (timer_settime(tid, 0, &ts, NULL) < 0)
		{
			printf("DISPLAY PROGRAM: timer_settime FAILED!\n");		/* display error text */
		}
		printf("%s\n", nonblinkingtext);
		blinkCycle = 1;
	}
	//sigsuspend(&ss);		/* wait for timer expiration */
	
}


/*---------------------------------*/
/* instantiates or otherwise obtains the blinking and nonblinking text regions to display in a time-interleaved manner*/
/*---------------------------------*/
void instantiateMessageStruct(char* message)
{
	char* localmessage;
	char* firstB;
	char* secondB;
	char* texttocopy;
	/*char* nonblinkingtext;
	char* blinkingtext;*/
	int originallength, messagelength, currentlength, endlength, diff;

	/*determine original messagelength*/
	if (debug) printf("The message is:\n%s\n", message);
	messagelength = strlen(message);
	if (debug) printf("The length of the message is %d\n", messagelength);
	originallength = messagelength;
	localmessage = (char*)malloc(messagelength);
	texttocopy = (char*)malloc(messagelength);
	nonblinkingtext = (char*)malloc(messagelength);
	blinkingtext = (char*)malloc(messagelength);
	firstB = (char*)malloc(messagelength);
	secondB = (char*)malloc(messagelength);
	strcpy(localmessage, message);	

	/* find the location of the first blinking indicator */
	firstB = strstr(localmessage, "<b>");
	
	int pass = 0; /* variable for debugging passes */
	
	/*---------------------------------------*/
	/*get message without blinked areas*/
	/*---------------------------------------*/
	while (firstB != NULL)
	{	
		if(debug) printf("Pass: %d\n", pass);
		if (debug) printf("The firstB is %s\n", firstB);
		
		/*get length of message from initial <b> to end*/
		currentlength = strlen(firstB);
		if (debug) printf("the length of the message from the initial <b> to the end is %d\n", currentlength);
		

		/*get difference between the current length of message and message beginning with first <b>*/
		diff = messagelength - currentlength;
		if (debug) printf("the difference between the length of the message and the message beginning with first <b> %d\n", diff);

		/*get initial substring of length diff from localmessage*/ 
		//texttocopy = (char*)realloc(texttocopy,diff);
		//texttocopy = calloc(diff,sizeof(int));
		texttocopy = (char*)calloc(originallength, sizeof(char));
		
		strncpy(texttocopy, localmessage, diff);
		
		if (debug) printf("The text copied is %s\n" ,texttocopy);
		
	
		/*catenate with that text*/
		strcat(nonblinkingtext, texttocopy);
	
		/*skip the <b>*/
		firstB += 3;	

		/*update current length to not include <b>*/
		currentlength = strlen(firstB);
		
		/*begin with next <b>*/
		secondB = strstr(firstB, "<b>");
	
		/*get the length of the remaining message beginning with <b>*/
		endlength = strlen(secondB);

		/*get the length of the blinked text between the enclosing <b>'s*/
		diff = currentlength - endlength;
		if (debug) printf("The nonblinking text is now:\n %s \nAnd a length of %d\n\n", nonblinkingtext, (int)strlen(nonblinkingtext));
		if (debug) printf("The length of the blinking word is:\n %d\n\n", diff);
		

		/*copy that length of spaces to struct 'blink-free' text*/
		for (; diff > 0; diff--)
		{
			strcat(nonblinkingtext," ");
		}	
		/*skip the closing <b>*/
		secondB += 3;
		if (debug) printf("The nonblinking text is now:\n %s\nAnd a length of %d\n\n", nonblinkingtext, (int)strlen(nonblinkingtext));
		
		/*copy to firstB to localmessage, which is where the next pass will begin*/
		messagelength = strlen(secondB);
		//localmessage = (char*)malloc(messagelength);
		localmessage = (char*)calloc(originallength, sizeof(char));
		strcpy(localmessage, secondB);
		
		if (debug) printf("The local message is now %s\n\n", localmessage);
		
		firstB = strstr(secondB, "<b>");

		/*initialize secondB for next pass*/
		secondB = (char*)calloc(originallength, sizeof(char));
		
		if (debug) printf("\n\n\n\n\n");
	}
	strcat(nonblinkingtext, localmessage);
	/*printf("\nblinked text NOT visible:\n");
	printf("%s\n", nonblinkingtext);*/

	
	
	
	/*------------------------------------------------------------*/
	/*copy all text minus <b> to struct->textincludingblinkedareas*/
	/*------------------------------------------------------------*/
	localmessage = (char*)calloc(originallength,sizeof(char));
	texttocopy = (char*)calloc(originallength, sizeof(char));
	firstB = (char*)calloc(originallength, sizeof(char));
	secondB = (char*)calloc(originallength, sizeof(char));
	strcpy(localmessage, message);
	messagelength = originallength;
	firstB = strstr(localmessage, "<b>");
	
	/*start where firstB = pointing at the initial <b>*/
	while (firstB != NULL)
	{

		/*get length of message from initial <b> to end*/
		currentlength = strlen(firstB);

		/*get difference betweenthe current length of message and message beginning with first <b>*/
		diff = messagelength - currentlength;

		/*get initial substring of length diff from localmessage*/
		//texttocopy = (char*)realloc(texttocopy, originallength);
		texttocopy = (char*)calloc(originallength, sizeof(char));
		strncpy(texttocopy, localmessage, diff);

		/*catenate with that text*/
		strcat(blinkingtext, texttocopy);

		/*skip the <b>*/
		firstB += 3;

		/*update current length to not include <b> just skipped*/
		currentlength = strlen(firstB);

		/*find next <b>*/
		secondB = strstr(firstB, "<b>");

		/*get the length of the remaining message beginning with <b>*/
		endlength = strlen(secondB);

		/*get the length of the blinked text between the enclosing <b>'s*/
		diff = currentlength - endlength;

		/*copy the enclosed text 'blinking' text*/
		texttocopy = (char*)calloc(originallength,sizeof(char));
		strncpy(texttocopy, firstB, diff);

		strcat(blinkingtext, texttocopy);

		/*skip the closing <b>*/
		secondB += 3;

		/*copy secondB to localmessage, which is where the next pass will begin*/
		messagelength = strlen(secondB);
		localmessage = (char*)malloc(originallength);
		strcpy(localmessage, secondB);

		/*initialize firstB for next pass*/
		/*start/end where firstB = pointing at the initial <b>*/
		firstB = strstr(localmessage, "<b>");

		/*initialize secondB for next pass*/
		secondB = (char*)calloc(originallength,sizeof(char));
	}
	
	

	strcat(blinkingtext, localmessage);
	/*printf("blinked text visible: \n");
	printf("%s\n", blinkingtext);*/
	
	if (debug) printf("The blinking text is %s\nthe nonblinking text is %s\n", blinkingtext, nonblinkingtext);
	
}


/*-----------*/
/*main method*/
/*-----------*/
int main(int argc, char *argv[])
{
    /*-----------------------------------*/
    /* Check the command line arguments. */
    /* Setup names for shared memory and */
    /* the message queue.                */
    /*-----------------------------------*/
    if (argc != 3)
    	usage();
    if (strlen(argv[1]) > MAXMEMNAME || strchr(argv[1],'/') != NULL)
    	usage();
    strcpy(memname,"/");
    strcat(memname,argv[1]);
    if (strlen(argv[2]) > MAXMSGNAME || strchr(argv[2],'/') != NULL)
    	usage();
    strcat(msgname,"/");
    strcat(msgname,argv[2]);

    /*---------------------------------------------------------------------*/
    /* Open the shared memory region. It should have a structure identical */
    /* to that used in the iprog program. Fail if it doesn't exist.        */
    /*---------------------------------------------------------------------*/
	printf("opening shared memory: %s\n", memname);
    shmfd = shm_open(memname, O_RDWR, 0777);	/* mode shouldn't be needed */
    if (shmfd < 0)
    {
		printf("dprog: shm_open unable to open shared memory region\n");
		exit(1);
    }

    if (ftruncate(shmfd,sizeof(struct sm)) < 0)
    {
		printf("dprog: ftruncate\n");
		close(shmfd);			/* cleanup after failure */
		exit(1);
    }

    smp = (struct sm *)mmap(0, sizeof(struct sm), PROT_READ | PROT_WRITE,
	MAP_SHARED, shmfd, 0);

    if (smp == (void *)-1)
    {
		/*printf("DISPLAY PROGRAM: mmap error FAILED!\n");*/
		close(shmfd);			/* cleanup after failure */
		shm_unlink(memname);
		exit(1);
    }
    close(shmfd);			/* not needed anymore */

    /*-------------------------*/
    /* Open the message queue. */
    /*-------------------------*/
    mqfd = mq_open(msgname,O_RDWR);
    if (mqfd < 0)
    {
		printf("DISPLAY PROGRAM: mq_open FAILED!\n");
		shm_unlink(memname);
		exit(1);
    }

	/*-----------------------------------------------------------*/
	/* Create an event action and a timer. The event action is   */
	/* to generate a SIGUSR1 signal...send SIGUSR1 signal when   */
	/* the timer expires!                                        */
	/*-----------------------------------------------------------*/
	SIGEV_SIGNAL_INIT(&ev, SIGUSR1);

	/*create timer*/
	if (timer_create(CLOCK_REALTIME, &ev, &tid) < 0) 
	{
		write(1, "\33[2;1H", 6);		/* cursor to row 2, column 1 */
		printf("timer_create error!\n");		/* display error text */
		return 1;
	}

	/*set the sa_flags member ofthe sigaction structure to SA_RESTART*/
	/*set the handler for the sigaction structure*/
	new_action.sa_handler = handler;
	new_action.sa_flags = SA_RESTART;
	/* */
	if (sigemptyset(&new_action.sa_mask) < 0)
	{
		printf("sigemptyset error!\n");
	}

	/*----------------------------------------------------*/
	/* Setup to invoke handler when SIGUSR1 is delivered. */
	/*----------------------------------------------------*/
	/*use sigaction to establish thesignal handler*/
	if (sigaction(SIGUSR1, &new_action, NULL) < 0)
	{
		printf("sigaction error!\n");
	}

    /*--------------------------------------------------------------*/
    /* We now enter the main loop. We wait for a message, then do a */
    /* "down" on the semaphore, copy and display the text, and do   */
    /* an "up" on the semaphore. If the text has zero length, that  */
    /* means we should quit.                                        */
    /*--------------------------------------------------------------*/

	for (;;)
	{

		int msglen;			/* message length - should be 1 */
		char msgbuff[10];		/* message buffer */
		int msgprio;			/* message priority */
		int n;				/* received text length */


		/* Wait for a message. */
		msglen = mq_receive(mqfd, msgbuff, 10, &msgprio);
		printf("Received a message of length %d\n", msglen);
		if (msglen < 0)
		{
			perror("dprog: mq_receive");
			printf("mesglen is less than 0\n");
			break;
		}
		else
		{

			/*turn on the timer -> set the interval*/
			printf("DISPLAY PROGRAM: setting timer interval to zero.\n");
			ts.it_interval.tv_sec = 0;		/* one shot timer set initially, shouldn't have to be updated */
			ts.it_interval.tv_nsec = 0;
			ts.it_value.tv_sec = 0;
			ts.it_value.tv_nsec = 0;
			if (timer_settime(tid, 0, &ts, NULL) < 0)
			{
				printf("DISPLAY PROGRAM: timer_settime FAILED!\n");
				return 1;
			}
			write(1,"\33[H\33[J",6);		/* home cursor, erase screen */
			
			/*move 1,1 and clear the screen*/
			moveAndClearScreen();


			/* Do a "down" on the semaphore to lock the shared memory region. */
			if (sem_wait(&smp->sem) < 0)
			{
				//perror("dprog: sem_wait");
				printf("DISPLAY PROGRAM : sem wait FAILED!\n");
				break;
			}


			/* Copy the data from the shared memory region,
			instantiate the messages that should be printed to screen, then do an "up" on the semaphore.                                  */
			strcpy(lcltxt, smp->txt);
			instantiateMessageStruct(smp->txt);
			if (sem_post(&smp->sem) < 0)
			{
				printf("dprog: sem_post FAILED!\n");
				break;
			}

			/*no text read*/
			if (strlen(lcltxt) == 0)
			{	/* quit? */
				break;
			}
			/*up on semaphore*/
			if (sem_post(&smp->sem) < 0)
			{
				printf("dprog: sem_post FAILED!\n");
				break;
			}

			/*print initially non-blinking text to screen*/
			printf("%s\n", nonblinkingtext);


			/*turn on the timer -> set the interval*/
			ts.it_value.tv_sec = 0;
			ts.it_value.tv_nsec = ON_TIME * 1000000;
			if (timer_settime(tid, 0, &ts, NULL) < 0)
			{
				printf("DISPLAY PROGRAM: timer_settime FAILED!\n");		/* display error text */
				return 1;
			}
		}
		sigsuspend(&ss);		/* wait for timer expiration */
	}



    printf("All done; default clean up.\n");
    mq_close(mqfd); /* close the message queue */
    mq_unlink(msgname); /* unlink message queue */
    shm_unlink(memname); /* unlink share memory */
    exit(0);
}
