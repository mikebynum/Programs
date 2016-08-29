/**********************************************************************/
/* A simple program to open up a socket and send something over, then */
/* read back a reply.                                                 */
/*                                                                    */
/* For: CSCI 1840                                                     */
/* By:  Mike Bynum                                                    */
/*                                                                    */
/* Usage: ./client 'message here' hostname                            */
/*        hostname is optional and goes to the define below.          */
/*                                                                    */
/**********************************************************************/

#include <stdio.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h>
#include <string.h>
#include <stdlib.h> /* exit() */
#include "portdefine.h"

#define HOST        "vulcan"  /* Replace with your server machine name*/
#define BUFSIZE 100

void prettyPrint( int sd, int count );

int main( int argc, char **argv ) /*argc is the port number and argv is the name of the server*/
{
    char               hostname[100];
    char               buf[BUFSIZE];
    int                sd; /*socket descriptor*/
	int listtype;
	int tempvar;
	int count = 0;
	char  * type;
	char  * item; /* temporary holding place for breaking down input */
	struct sockaddr_in sin;
    struct sockaddr_in pin;
    struct hostent     *hp;
	
	srec newrec;
	
	

    /* Usage: ./client message host */
    strcpy( hostname, HOST );
    if ( argc > 2 )
        strcpy( hostname, argv[2] );

    /* go find out about the desired host machine */
    if ( ( hp = gethostbyname( hostname ) ) == 0 )
    {
        perror( "gethostbyname" );
        exit( 1 );
    }

    /* Fill in the socket structure with host information */
    memset( &pin, 0, sizeof( pin ) );
    pin.sin_family = AF_INET;
    pin.sin_addr.s_addr = ( ( struct in_addr * )( hp->h_addr ) )->s_addr;
    pin.sin_port = htons( PORT );

    /* Grab an Internet socket */
    if ( ( sd = socket( AF_INET, SOCK_STREAM, 0 ) ) == -1 )
    {
        perror( "socket" );
        exit( 1 );
    }

    /* Connect to that port on the indicated host */
    if ( connect( sd,( struct sockaddr * ) &pin, sizeof( pin ) ) == -1 )
    {
        perror( "connect" );
        exit( 1 );
    }

    /* Send the message over to them. */
    /*if ( send( sd, argv[1], strlen( argv[1] ) + 1, 0 ) == -1 )
    {
        perror( "send" );
        exit( 1 );
    }*/
	
	printf("Please enter a command:  ");
	
	while(fgets(buf, sizeof(buf), stdin) != NULL)
	{
		
	/* get (buf[0]) lname (buf[1]) -or- put bob, smith,12,1234,3.26 */
		if (strncmp(buf,"get",3) == 0)
		{
			command=strtok(buf," "); /*command to do*/
			type=strtok(NULL,"\n"); /*what to type to get*/
			tempvar = getcmd;
			
			if (strcmp(type,"GPA") == 0)
			{
				listtype = cmpgpa;
			}
			else if (strcmp (type,"SID") == 0)
			{
				listtype = cmpsid;
			}
			else if (strcmp (type,"fname") == 0)
			{
				listtype = cmpfname;
			}
			else if (strcmp (type,"lname") == 0)
			{
				listtype = cmplname;
			}
			
			if(send(sd, &tempvar, sizeof(int), 0)==-1)
				printf("did not send"); /*send the command over*/
				
			send(sd, &listtype, sizeof(int), 0); /*send the list type over*/
		
			if(recv(sd, &count, sizeof(int), 0)==-1)
				printf("did not receive");
				
				
			prettyPrint( sd, count );

			/*
			
				
			}*/
		}
		else if (strncmp(buf,"put",3) == 0)/*put something into array*/
		{			
			
			tempvar = putcmd;
			
			lname = strtok(&buf[4],","); /*store into the last name*/
			strncpy( newrec.lname,lname,10 );
			fname = strtok(NULL,","); /*store into the first name*/
			strncpy( newrec.fname,fname, 10);
			newrec.initial= *strtok(NULL,","); /*store the middle initial*/
			item = strtok(NULL,","); /*store the student id*/
			newrec.SID = atoi(item);
			gpa = strtok(NULL,"\n"); /*store the student GPA*/
			newrec.GPA = atof(gpa);
			
			if(send(sd, &tempvar, sizeof(int), 0)==-1)
				printf("did not send"); /*send the command over*/
				
			send(sd, &newrec, sizeof(srec), 0); /*send the srec over*/
			
			printf ("Lname: %s\n", newrec.lname);
            printf ("Fname: %s\n", newrec.fname);
            /*printf ("Middle: %s\n", newrec.initial);*/
            printf ("SID: %d\n", newrec.SID);
            printf ("GPA: %.2f\n", newrec.GPA);
			
		}
		else if (strncmp(buf,"del",3) == 0) /* delete record with a particular SID */
		{
			
			command=strtok(buf," "); /*command to do*/
			type=strtok(NULL,"\n"); /*what to type to get*/
			
			send(sd, &command, sizeof(int), 0); /* send the command over */
			send(sd, &type, sizeof(int), 0); /* send the list type over */
		}
		else if (strncmp(buf,"sto",3) == 0) /* server saves the file and exits.  The client exits as well */
		{
			/* Clean up after yourself. Be a good citizen. */
			close( sd );
			return( 0 );
		}
		else
		{
			printf("unknown statement\n");
		}
		
		printf("Please enter a command >  ");
	}
	
    /* Wait for a message to come back from the server */
    /*if ( recv( sd, buf, BUFSIZ, 0 ) == -1 )
    {
        perror( "recv" );
        exit( 1 );
    }

    /* Show what the reply was, and we're done. */
    /*printf( "%s\n", buf );*/
	return(0);
}

void prettyPrint( int sd, int count )
{
	srec temp;

    printf ("| SID   | Lname     | Fname     | M | GPA  |\n");
    printf ("+-------+-----------+-----------+---+------+\n");
	for(i=0; i<count; i++)
	{
		recv(sd, &temp, sizeof(srec),0);
		printf ("\n| %5.5d | %10.10s | %10.10s | %c | %.2f|\n", temp.SID, temp.lname, temp.fname, temp.initial,temp.GPA);
	}
    printf ("+-------+-----------+-----------+---+------+\n");


}



