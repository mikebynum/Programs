/*----------------------------------------------*/
/* blink.c - Stan Wileman                       */
/*                                              */
/* For CSCI 8530, Fall 2014                     */
/*                                              */
/* This program just clears the window and      */
/* displays the text                            */
/*                                              */
/*     Hello, you blinking world!               */
/*                                              */
/* It causes the word "blinking" to blink  by   */
/* alternating between "blinking" and spaces at */
/* a fixed rate (see the code for the rate).    */
/* That is, the on time and the off time for    */
/* the display of the word will be specified.   */
/*                                              */
/* The number of blink iterations may be        */
/* specified as an integer argument on the      */
/* command line; the default value is 20.       */
/*----------------------------------------------*/
/* This program assumes the use of a VT-100 (or */
/* equivalent) terminal emulator. This is very  */
/* commonly used, so no special provision has   */
/* been provided for a different terminal       */
/* emulation. You may need to view the details  */
/* on cursor positioning escape sequences for   */
/* the VT-100 terminal. There is a file named   */
/* /home/stanw/csci8530/vt100.txt that has a    */
/* brief summary of the VT-100, including the   */
/* cursor positioning escape sequences.         */
/*----------------------------------------------*/

#include <unistd.h>
#include <signal.h>
#include <time.h>
#include <stdlib.h>

/*-------------------------------------------------------------*/
/* Linux doesn't provide SIGEV_SIGNAL_INIT or any of the other */
/* QNX macros to setup a sigevent structure. When using LINUX  */
/* this macro should be defined here.                          */
/*-------------------------------------------------------------*/
#ifndef __QNX__
#define SIGEV_SIGNAL_INIT(__e,__s)	(\
	(__e)->sigev_notify = SIGEV_SIGNAL, \
	(__e)->sigev_signo = (__s))
#endif

#define DEFAULT_BLINKS 20

/*----------------------------------------------------------------*/
/* The blink rate is determined by the following two definitions. */
/* Obviously ON_TIME indicates how long the word "blinking" will  */
/* be displayed (in milliseconds), and OFF_TIME indicates how     */
/* long blanks will be displayed instead of "blinking".           */
/* It is assumed that these times will be between 1 and 999 msec. */
/* If not, then the code that sets the timer will need changes.   */
/*----------------------------------------------------------------*/
#define ON_TIME 500			/* time on, in milliseconds */
#define OFF_TIME 100			/* time blanked, in milliseconds */

/*---------------------------------------------------------------*/
/* Signal handler (for SIGUSR1). If the text "blinking" was      */
/* displayed, then clear it. Otherwise (if it wasn't displayed), */
/* then display it.                                              */
/*---------------------------------------------------------------*/
void handler(int signum)
{
    static int state = 1;		/* text state is on */
    char *t;

    if (state == 1)			/* select text to display */
	t = "        ";
    else
	t = "blinking";

    /*----------------------------------------------------------------*/
    /* The code below uses VT-100 escape sequences to position the    */
    /* cursor. In general, the characters "ESC [ row ; col H", where  */
    /* ESC represents the escape character (octal 033 or decimal 27), */
    /* row is the row number ('1' or larger), and col is the column   */
    /* number (also '1' or larger), will move the cursor to the       */
    /* specified row and column. For example, in the next write       */
    /* invocation the cursor is moved to row 1, column 12.            */
    /*----------------------------------------------------------------*/
    write(1,"\33[1;12H",8);		/* move the cursor to row 1, col 12 */
    write(1,t,8);			/* write the new text */
    write(1,"\33[2;1H",6);		/* move the cursor to row 2, col 1 */
    state = !state;			/* toggle the display state */
}

int main(int argc, char *argv[])
{
    int nblinks;			/* # of on/off blink iterations */
    struct sigevent ev;			/* timer expiration event */
    timer_t tid;			/* timer ID */
    struct itimerspec ts;		/* time specification */
    sigset_t ss;			/* signal set */

    /*-----------------------------------------------------------------*/
    /* If an argument was provided, assume it's an integer that tells  */
    /* how many on/off cycles are desired. We don't check the validity */
    /* of the argument, but that could certainly be added if we wanted */
    /* the program to be more robust.                                  */
    /*-----------------------------------------------------------------*/
    if (argc > 1)	
	nblinks = atoi(argv[1]);
    else
	nblinks = DEFAULT_BLINKS;

    if (nblinks < 1)
	return 0;			/* nothing to do */

    /*-------------------------------------------*/
    /* Clear the screen and display the text.    */
    /* Note the VT-100 escape sequences used  to */
    /* 'home' the cursor and erase the screen.   */
    /*-------------------------------------------*/
    write(1,"\33[H\33[J",6);		/* home cursor, erase screen */
    write(1,"Hello, you blinking world!\n",27);	/* display text */

    /*---------------------------------------------------------*/
    /* Create an event action and a timer. The event action is */
    /* to generate a SIGUSR1 signal.                           */
    /*---------------------------------------------------------*/
    SIGEV_SIGNAL_INIT(&ev, SIGUSR1);
    if (timer_create(CLOCK_REALTIME, &ev, &tid) < 0) {
	write(1,"\33[2;1H",6);		/* cursor to row 2, column 1 */
	perror("timer_create");		/* display error text */
	return 1;
    }

    /*----------------------------------------------------*/
    /* Setup to invoke handler when SIGUSR1 is delivered. */
    /*----------------------------------------------------*/
    signal(SIGUSR1,handler);

    /*-------------------------*/
    /* Do the blinking cycles. */
    /*-------------------------*/
    ts.it_interval.tv_sec = 0;		/* one shot timer */
    ts.it_interval.tv_nsec = 0;
    while (nblinks > 0) {

	/*---------------------------------------------------*/
	/* Set timer for "on" cycle and wait for it to fire. */
	/* ON_TIME is expected to be between 1 and 999.      */
	/*---------------------------------------------------*/
	sigemptyset(&ss);
	ts.it_value.tv_sec = 0;
	ts.it_value.tv_nsec = ON_TIME * 1000000;
	if (timer_settime(tid, 0, &ts, NULL) < 0) {
	    write(1,"\33[2;1H",6);		/* cursor to row 2, column 1 */
	    perror("timer_settime");		/* display error text */
	    return 1;
	}
	sigsuspend(&ss);		/* wait for timer expiration */

	/*----------------------------------------------------*/
	/* Set timer for "off" cycle and wait for it to fire. */
	/* OFF_TIME is expected to be between 1 and 999.      */
	/*----------------------------------------------------*/
	sigemptyset(&ss);
	ts.it_value.tv_sec = 0;
	ts.it_value.tv_nsec = OFF_TIME * 1000000;
	if (timer_settime(tid, 0, &ts, NULL) < 0) {
	    write(1,"\33[2;1H",6);		/* cursor to row 2, column 1 */
	    perror("timer_settime");		/* display error text */
	    return 1;
	}
	sigsuspend(&ss);		/* wait for timer expiration */

	nblinks--;			/* one less cycle to do */
    }
    write(1,"\33[2;1H",6);		/* cursor to row 2, column 1 */
    return 0;				/* report normal program termination */
}
