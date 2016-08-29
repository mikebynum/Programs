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



int main( int argc, char **argv ) /*argc is the port number and argv is the name of the server*/
{
    char               hostname[100];
    char               buf[BUFSIZE];
    int                sd; /*socket descriptor*/
	char  *command;
	char  *type;
	struct sockaddr_in sin;
    struct sockaddr_in pin;
    struct hostent     *hp;

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
    if ( send( sd, argv[1], strlen( argv[1] ) + 1, 0 ) == -1 )
    {
        perror( "send" );
        exit( 1 );
    }
	
	while(fgets(buf, sizeof(buf), stdin) != NULL)
	{
		
	/* get (buf[0]) lname (buf[1]) -or- put bob, smith,12,1234,3.26 */
		if (strncmp(buf,"get",3) == 0)
		{
			command=strtok(str," "); /*command to do*/
			type=strtok(NULL," "); /*what to type to get*/
			
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
				listtype = cmpfnm;
			}
			else if (strcmp (type,"lname") == 0)
			{
				listtype = cmplnm;
			}
			else if (strcmp (type,"initial") == 0)
			{
				listtype = cmpinit;
			}
			
			send(sd, &command, sizeof(int), 0); /*send the command over*/
			send(sd, &listtype, sizeof(int), 0);/*send the list type over*/
		
			recv(sd, &count, sizeof(int), 0);
			/*for(i=0; i<count; i++)
			{
				srec temp;
				recv(sd, &temp, sizeof(srec),0);
				print(temp);
			}*/
		}
		else if (strncmp(buf,"put",3)
		{
		}
		else if (strncmp(buf,"del",3)
		{
		}
		else if (strncmp(buf,"sto",3)
		{
		}
		else
		{
		}

    /* Wait for a message to come back from the server */
    if ( recv( sd, buf, BUFSIZ, 0 ) == -1 )
    {
        perror( "recv" );
        exit( 1 );
    }

    /* Show what the reply was, and we're done. */
    printf( "%s\n", buf );

    /* Clean up after yourself. Be a good citizen. */
    close( sd );
    return( 0 );
}


