/*----------------------------------------------------------------------*/
/* iprog_temp.c - CSCI 8530, Fall 2014					*/
/* Usage:								*/
/*	iprog_temp memname msgname filename N update-secs		*/
/*									*/
/* This is NOT a completed program. It is a "template" program provided */
/* for students in CSCI 8530 as a starting point for the "generator"    */
/* program for the POSIX programming project. A lot of the fundamentals */
/* are provided, but specific tasks -- primarily those dealing with the */
/* POSIX semaphore, message queue, and shared memory features have been */
/* omitted. For the most part, where these features must be used is     */
/* marked with XXX in the source code.                                  */
/*									*/
/* Obviously you are expected to read the problem statement to know how */
/* to modify this program.                                              */
/*									*/
/* MAKE CERTAIN TO INCLUDE THE NAME (OR NAMES) OF THE INDIVIDUALS WHO   */
/* MODIFY THIS CODE. AN IDEAL PLACE WOULD BE IN THIS COMMENT!!          */
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

/*--------------*/
/* Local limits */
/*--------------*/
#define MAXMEMNAME 20		/* max length of memory region's name */
#define MAXMSGNAME 20		/* max length of message queue's name */
#define MAXFNAME 100		/* max length of path to the file to display */
#define MAXTXT 3000		/* max length of text to display */
#define MAXMSGS 5		/* max pending messages */

static char memname[MAXMEMNAME+2];	/* name of shared memory object */
static char msgname[MAXMSGNAME+2];	/* name of message queue */
static char filename[MAXFNAME+1];	/* path to file to display */
static int nupdates;			/* # of updates to perform */
static int updatesecs;			/* seconds to delay between updates */

static struct sm {		/* shared memory layout */
    sem_t sem;			    /* the semaphore */
    char txt[MAXTXT+1];		    /* the text to display */
} *smp;

struct mq_attr mqa;		/* message queue attribute structure */

static int fd;			/* file descriptor for file to display */
static int shmfd;		/* "file" descriptor for the shmem object */
static int mqfd;		/* message queue file descriptor */

static struct timespec delay;	/* how long to delay between updates */


/*----------------------*/
/*writes proper use syntax on incorrect arg input*/
/*----------------------*/
void usage(void)
{
    fprintf(stderr,"Usage: iprog memname msgname filename N update-secs\n");
    fprintf(stderr,"\n");
    fprintf(stderr,"  memname is the name of the shared memory region.\n");
    fprintf(stderr,"    Do not include the initial /.\n");
    fprintf(stderr,"    (maximum length is %d characters)\n", MAXMEMNAME);
    fprintf(stderr,"  msgname is the name of the message queue.\n");
    fprintf(stderr,"    Do not include the initial /.\n");
    fprintf(stderr,"    (maximum length is %d characters)\n", MAXMSGNAME);
    fprintf(stderr,"  filename is the path to the file to be displayed.\n");
    fprintf(stderr,"    (maximum length is %d characters)\n", MAXFNAME);
    fprintf(stderr,"  N is the number of shared memory updates to perform.\n");
    fprintf(stderr,"    (minimum is 1)\n");
    fprintf(stderr,"  update-secs is the time to delay between updates.\n");
    fprintf(stderr,"    (minimum is 10)\n");
    fprintf(stderr,"\nCorrect the command line and try again!\n");
    exit(1);
}



/*-------------------------------------------------------------------*/
/* Open fname for reading, copy at most MAXTXT bytes from it to the  */
/* txt member of the structure at smp, and add a trailing null byte. */
/* Then close fname.                                                 */
/* Return 0 on failure, 1 on success.                                */
/*-------------------------------------------------------------------*/
int copytext(char *fname, struct sm *smp)
{
	
    int fd = open(fname,O_RDONLY);	/* open the file for reading only */
    int n;

    if (fd < 0) {			/* if the open didn't succeed */
	perror("iprog: open");
	return 0;
    }
    n = read(fd,smp->txt,MAXTXT);	/* read all of the text */
    if (n < 0) {			/* handle any failure */
	perror("iprog: read");
	return 0;
    }
    smp->txt[n] = '\0';			/* mark its end with a null byte */
    close(fd);				/* close the file; it's now unneeded */
    return 1;				/* return with a success indication */
}



/*-----------*/
/*main method*/
/*-----------*/
int main(int argc, char *argv[])
{
    int debug = 1;  //turn on to show debug statements
	
	/*-----------------------------------*/
    /* Check the command line arguments. */
    /*-----------------------------------*/
    if (argc != 6)			/* must have 6 args, incl prog name */
	usage();

    /*--------------------------------------*/
    /* Check name for shared memory object. */
    /*--------------------------------------*/
    if (strlen(argv[1]) > MAXMEMNAME || strchr(argv[1],'/') != NULL)
	usage();
    strcpy(memname,"/");
    strcat(memname,argv[1]);

    /*-------------------------------*/
    /* Check name for message queue. */
    /*-------------------------------*/
    if (strlen(argv[2]) > MAXMSGNAME || strchr(argv[1],'/') != NULL)
	usage();
    strcat(msgname,"/");
    strcat(msgname,argv[2]);

    /*-----------------------------------------------------*/
    /* Check the file containing the text to be displayed. */
    /* We must be able to open it for reading.             */
    /* If okay, save the path.                             */
    /*-----------------------------------------------------*/
    if (strlen(argv[3]) > MAXFNAME || access(argv[3],R_OK) != 0) {
	fprintf(stderr,"Unable to access the specified file.\n");
	usage();
    }
    strcpy(filename,argv[3]);

    /*------------------------------------------------------*/
    /* Verify the number of updates of the text to perform. */
    /*------------------------------------------------------*/
    nupdates = atoi(argv[4]);
    if (nupdates < 1)
	usage();

    /*-----------------------------------------------*/
    /* Verify the number of seconds between updates. */
    /*-----------------------------------------------*/
    updatesecs = atoi(argv[5]);
    if (updatesecs < 10)
	usage();

    /*--------------------------------------------------------------------*/
    /* Unlink any existing shared memory region with the same name.       */
    /* Then create the shared memory region. It must have a size at least */
    /* as large as struct sm.                                             */
    /* You'll need to use shm_unlink, shm_open, ftruncate, and mmap.      */
    /*--------------------------------------------------------------------*/
    /* XXX */
	
	if (debug) printf ("Going to unlink the shared mem region of %s.\n", memname);
	shm_unlink (memname);

	if (debug) printf ("Creating a new shared memory object for %s.\n", memname);
	shmfd = shm_open(memname, O_RDWR | O_CREAT, 0777);
	if ( shmfd < 0)
	{
		printf("Failed to open file.\n");
		exit(1);
	}
	
	/*if (debug) printf ("Truncating file descriptor %d to the size of the shared memory, %d\n",fd,sizeof(struct sm));*/
	if (ftruncate(shmfd, sizeof(struct sm)) == -1)
	{
		perror("ftruncate");
		printf("failure trying to truncate file descriptor %d to shared memory region.\n", fd);
		exit(1);
	}
	
	if (debug) printf ("Mapping shared memory to virtual address space.\n");
	smp = mmap(NULL, sizeof(struct sm), PROT_READ | PROT_WRITE, MAP_SHARED, shmfd, 0);
	
	/*------------------------------------*/
	/* Test for successful map completion */
	/*------------------------------------*/
	if (smp == (void *) -1)
	{
		perror("mmap");
		printf("error mapping shared memory region to virtual address space.\n");
		exit(1);
	}
	
	if (debug) printf ("The shared object is mapped to address %lX\n", (unsigned long)smp);
	
	if (debug) printf("Closing the file, %d\n", shmfd);
	close(shmfd);

    /*---------------------------------------------*/
    /* Create the semaphore, initializing it to 0. */
    /* Obviously you'll use shm_init for this.     */
    /*---------------------------------------------*/
    /* XXX */
	if (debug) printf("Creating a semaphore\n");
	sem_init (&smp->sem, 1,0);
	if(debug)printf("GENERATE: created semaphore\n");
    /*--------------------------------------------------------------------*/
    /* Unlink the message queue if it exists.                             */
    /* Then create it. A message size of 1 is adequate. Although MAXMSGS  */
    /* is the number we use for the number of queued entries, it should   */
    /* probably work with anything that's at least 1. But do use MAXMSGS. */
    /* Use mq_unlink and mq_open here.                                    */
    /*--------------------------------------------------------------------*/
    /* XXX */
	if (debug) printf("Unlinking message queue\n");
	mq_unlink(msgname);

	if (debug) printf("Creating a message queue, %s\n", msgname);
	mqa.mq_curmsgs = 0;
	mqa.mq_flags = 0;
	mqa.mq_maxmsg = MAXMSGS;
	mqa.mq_msgsize =  1;

	mqfd = mq_open(msgname, O_CREAT | O_RDWR, 0777 ,&mqa);
	if ( mqfd == -1)
	{
		perror("mq_open");
		exit(1);
	}
	if (debug) printf("Opened file with file descriptor %d\n", mqfd);

    /*--------------------------------------------------------------*/
    /* Copy the file to the txt member of the shared memory region. */
    /* Add a null byte at the end of the copied data.               */
    /*--------------------------------------------------------------*/
	if (debug) printf("GENERATE : Copying the text of the file at path: %s into shared memory.\n", filename);
    if (!copytext(filename,smp))
    {
		printf("Failed to copy the text of the file at path: %s into shared memory.\n", filename);
    	shm_unlink(memname);		/* Error? Delete shared memory */
		mq_unlink(msgname);		/* and themessage queue */
		exit(1);			/* then exit with error code */
    }

    /*--------------------------------------------------*/
    /* Do an "up" on the semaphore, and send a message. */
    /* Clearly you'll use sem_post and mq_send.         */
    /*--------------------------------------------------*/
    /* XXX */
    /*if (debug) printf("Doing an up on semaphore\n");*/
	if(debug) printf("GENERATE : up/post: semaphore\n");
    if(sem_post(&smp->sem))
    {
		printf("sem_post error of some kind\n");
    	exit(1);
    }

    char * msgptr;
    int n =1;
    msgptr = calloc(1, sizeof(char));

    if (debug) printf("GENERATE : Sending a message to indicate to the display program that it is now time to display text\n");
    //mq_send(mqfd, smp->txt, 1, 0 );
	
	
    if (mq_send(mqfd, msgptr, 1, 0 ))
    {
    	perror("mq_send error");
    	exit(1);
    }

    struct mq_attr obuf; /* output attr struct for getattr */
    if (! mq_getattr(mqfd,&obuf))
    {
		 /*printf("!mq_getattr() : flags: 0x%x  maxmsg: %d  msgsize: %d  curmsgs: %d\n", obuf.mq_flags, obuf.mq_maxmsg, obuf.mq_msgsize, obuf.mq_curmsgs);*/
    }

    /*------------------------*/
    /* Now we do the updates. */
    /*------------------------*/
    if (debug) printf("Going to do %d updates each for %d seconds\n",nupdates, updatesecs);
    while (nupdates > 0)
    {
    	 if (debug) printf("Update number %d\n",nupdates);
    	/*--------------------*/
		/* Delay nupdatesecs. */
		/*--------------------*/
		delay.tv_sec = updatesecs;		/* setup nanosleep argument */
		delay.tv_nsec = 0;
		nanosleep(&delay,NULL);			/* delay for a while */

		/*---------------------------------------------------------------*/
		/* At this point we could see if the file changed. If it didn't, */
		/* then the update could be skipped. We'll not do that here.     */
		/* That is, we'll just assume the file changed.                  */
		/*---------------------------------------------------------------*/

		/*---------------------------------------------------*/
		/* Lock the shared memory ("down" on the semaphore)  */
		/* in preparation for updating the text it contains. */
		/* Obviously use sem_wait for this.                  */
		/*---------------------------------------------------*/
		/*XXX*/
		if(debug) printf("GENERATE : Doing a down on semaphore.\n");
		if (sem_wait (&smp->sem) != 0)
		{
			perror("sem_wait");
			exit(1);
		}

		if (debug) printf("Read the file and copy it to shared memory.\n");
		/*---------------------------------------------*/
		/* Read the file and copy it to shared memory. */
		/*---------------------------------------------*/
		if (!copytext(filename,smp))
		{
			printf("Unable to read the file and copy it to shared memory.\n");
			shm_unlink(memname);
			mq_unlink(msgname);
			exit(1);
		}

		/*--------------------------------------------------*/
		/* Do an "up" on the semaphore, and send a message. */
		/* This will let the display program know it needs  */
		/* to redisplay the text.                           */
		/* Clearly use sem_post and mq_send.                */
		/*--------------------------------------------------*/
		if(debug) printf("GENERATE : Doing an up on semaphore\n");
		if(sem_post(&smp->sem))
		{
			perror("badsema");
			exit(1);
		}

		if (debug) printf("Sending a message to indicate to the display program that it is now time to display text\n");
		//mq_send(mqfd, smp->txt, 1, 0 );
		if(mq_send(mqfd, msgptr, n, 0 ))
		{
			perror("mq_send");
			exit(1);
		}


		nupdates--;
    }

    if (debug) printf("Completed updates\n");


    /*--------------------------------------------------------------*/
    /* Update the text to 0 length to mark the end of the displays. */
    /* Do this inside a critical section (down on the semaphore,    */
    /* update the first byte in the txt region, then an up.         */
    /* Finally send a message (mq_send) to tell the display program */
    /* to read the updated text.                                    */
    /*--------------------------------------------------------------*/
    /* XXX */
    if (debug) printf("Doing final down on the semaphore.\n");
    if (sem_wait (&smp->sem) != 0)
	{
		perror("sem_wait");
		exit(1);
	}

    smp->txt[0] = '\0';			/* zero-length text */

    /* XXX */
    if (debug) printf("Doing the final up on semaphore\n");
	if(sem_post(&smp->sem))
	{
		perror("badsema");
		exit(1);
	}

	if (debug) printf("Telling the display program to read the updated text.\n");
	mq_send(mqfd, smp->txt, 1, 0 );

    exit(0);
}
