/**********************************************************************/
/* A simple program to create a socket, listen, read messages, etc.   */
/*                                                                    */
/* For: CSCI 1840                                                     */
/* By:  Mike Bynum                                                    */
/*                                                                    */
/* Usage: ./server                                                    */
/*                                                                    */
/**********************************************************************/

#include <stdio.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h>
#include <string.h>
#include <stdlib.h>
#include "portdefine.h"



void sendlist (ListNode *t, int sd, int recs); /*do we not have to send the int recs?*/
int compare ( srec *a, srec *b, int type);
void insert(struct node **, srec *, int );
void
delete( ListNodePtr *, int );




main()
{
    char               buf[ BUFSIZ ];
	int 			   command;
	int			       type;
	int                count = 0;
    int                sd, sd_current, cc, fromlen, tolen;
    unsigned int       addrlen;
    struct sockaddr_in sin;
    struct sockaddr_in pin;
	srec newrec;
	struct node * gpalist = NULL;
	struct node * sidlist = NULL;
	struct node * lnamelist = NULL;
	struct node * fnamelist = NULL;
	FILE *fp;
	struct node *p;
	size_t result;
    long lSize;
	char * buffer;

	

   
 /*
    fp = fopen("datafile","rb");
	
	if(fp == NULL)
		puts("File doesn't exist");
		
	/* obtain file size: */
	/*fseek (fp , 0 , SEEK_END);
	lSize = ftell (fp);
	rewind (fp);

	/*allocate memory to contain the whole file:*/
	/*buffer = (char*) malloc (sizeof(char)*lSize);
	if (buffer == NULL) {fputs ("Memory error",stderr); exit (2);}

	/* copy the file into the buffer:*/
	/*result = fread (buffer,1,lSize,fp);
	if (result != lSize) {fputs ("Reading error",stderr); exit (3);}

	/* the whole file is now loaded in the memory buffer. */
	
	/* Get an internet domain socket */
    if ( ( sd = socket( AF_INET, SOCK_STREAM, 0) ) == -1 )
    {
        perror( "socket");
        exit( 1 );
    }

    /* Complete the socket structure */
    memset( &sin, 0, sizeof(sin) );
    sin.sin_family = AF_INET;
    sin.sin_addr.s_addr = INADDR_ANY;
    sin.sin_port = htons( PORT );

    /* Bind the socket to the port number */
    if ( bind( sd, ( struct sockaddr *) &sin, sizeof( sin ) ) == -1) 
    {
        perror( "bind" );
        exit( 1 );
    }

    /* Listen for clients that want to connect.         */
    /* In a real "server" we would get a connection and */
    /* then "fork" or create a thread for that one, but */
    /* here we only handle one connection.              */
    if ( listen( sd, 5 ) == -1 ) 
    {
        perror( "listen" );
        exit( 1 );
    }

    /* Wait for a client connection, then accept it. */
    addrlen = sizeof(pin); 
    if ( ( sd_current = accept( sd, ( struct sockaddr *)  &pin, &addrlen ) ) == -1 )
    {
        perror( "accept");
        exit( 1 );
    }

    /* Let's show the incoming stuff just for fun. */
    printf( "Coming from port %d\n", ntohs( pin.sin_port ) );

    /* If this is a server that needs to keep taking messages */
    /* from the client, then put a "while" loop here to keep  */
    /* reading data. The easy way to do this is to have the   */
    /* client send "exit" or some special string to terminate */
    /* the "while" loop. Otherwise you tend to get SIGPIPE or */
    /* other errors from the "recv" and "send" calls.         */

 
	
	if( recv( sd_current, &command, sizeof( int ), 0 ) == -1) /*receive the first command*/
	{
		perror("recv");
		exit( 1 );
	}
	
	while ( command ) /*keep receiving until the stop command was entered*/
	{
		switch(command)
		{
			case getcmd: /*server sends back records, ordered by type*/
				if ( recv( sd_current, &type, sizeof( type ), 0 ) == -1)
				{
					perror("recv");
					exit( 1 );
				}
				else
				{
					switch (type)
					{
						case cmpgpa: /*The server sends back the records, ordered by GPA*/
							/*send( sd_current, &(gpalist->studata), sizeof(srec), 0);*/
							sendlist(gpalist, sd_current, count);
							break;
						case cmpfname: /*the server sends back the records, orded by the first name*/
							sendlist(fnamelist, sd_current, count);
							break;
						case cmplname: /*the server sends back the records, ordered by the last name*/
							sendlist(lnamelist, sd_current, count);
							break;
						case cmpsid: /*the server sends back the records, ordered by the SID*/
							sendlist(sidlist, sd_current, count);
							break;
						default:
							printf("I don't think so!");
					}
				}
				break;
			case delcmd:
				recv (sd_current, &sid, sizeof(int), 0);
				delete( &lnamelist, sid );
				delete( &fnamelist, sid );
				delete( &gpalist, sid );
				delete( &sidlist, sid );
				count--;
				break;
			case putcmd:
				recv(sd_current, &newrec, sizeof(srec), 0);		
				insert( &lnamelist, &newrec, cmplname );
				insert( &fnamelist, &newrec, cmpfname );
				insert( &sidlist, &newrec, cmpsid );
				insert( &gpalist, &newrec, cmpgpa );
				count++;
				break;
			case stpcmd:
				fp=fopen("datafile","wb");
				for(p=sidlist; p!=NULL; p=p->nextPtr)
					fwrite(&(p->studata), sizeof(srec), 1, fp);
				fclose(fp);
				exit(0);
			default:
				break;
		}
			

		/* acknowledge the message, and send a reply */
		
		/*
		if ( send( sd_current, buf, strlen( buf ) + 1, 0) == -1)
		{
			perror( "send" );
			exit( 1 );
		}*/
		
		recv( sd_current, &command, sizeof( int ), 0 ); /*receive next command*/
		
	} /* end while loop because stop was entered */
	
    /* close up both sockets */
    close( sd_current ); 
    close( sd );
 
    return( 0 );
}

void sendlist (ListNode *t, int sd, int recs)
{
	send(sd, &recs, sizeof(int), 0);
	
	for (i=0; i < recs; i++)
	{
		send (sd, &(t->studata), sizeof (srec), 0);
		t = t->nextPtr;
	}
}

int compare ( srec *a, srec *b, int type)
{
	switch (type)
	{
		case cmpgpa:
			return ( (a->GPA) < (b->GPA)?1 :-1);
			break;
		case cmpsid:
			return ( (int) (a->SID)-(b->SID));
			break;
		case cmplname:
			return (strcmp(a->lname, b->lname));
			break;
		case cmpfname:
			return (strcmp(a->fname, b->fname));
			break;
		default:
			perror("I don't think so!");
			break;
	}
}

/* Insert a new value into the list in sorted order */
void insert(struct node **list, srec *newrec, int type )
{ 
   ListNodePtr newPtr; /* pointer to new node */
   ListNodePtr previousPtr; /* pointer to previous node in list */
   ListNodePtr currentPtr; /* pointer to current node in list */

   newPtr = malloc( sizeof( ListNode ) ); /* create node */

   if ( newPtr != NULL ) { /* is space available */
      memcpy (&(newPtr->studata), newrec, sizeof(srec)); /* place value in node */
      newPtr->nextPtr = NULL; /* node does not link to another node */

      previousPtr = NULL;
      currentPtr = *list;

      /* loop to find the correct location in the list */
      while ( currentPtr != NULL && compare(&(currentPtr->studata),newrec,type)<0) { 
         previousPtr = currentPtr; /* walk to ...   */
         currentPtr = currentPtr->nextPtr; /* ... next node */
      } /* end while */

      /* insert new node at beginning of list */
      if ( previousPtr == NULL ) { 
         newPtr->nextPtr = *list;
         *list = newPtr;
      } /* end if */
      else { /* insert new node between previousPtr and currentPtr */
         previousPtr->nextPtr = newPtr;
         newPtr->nextPtr = currentPtr;
      } /* end else */
   } /* end if */
   else {
      printf( "not inserted. No memory available.\n");
   } /* end else */
} /* end function insert */

void delete( ListNodePtr *sPtr, int sid )
{ 
   ListNodePtr previousPtr; /* pointer to previous node in list */
   ListNodePtr currentPtr; /* pointer to current node in list */
   ListNodePtr tempPtr; /* temporary node pointer */

   /* delete first node */
   if ( sid == ( *sPtr )->studata.SID ) { 
      tempPtr = *sPtr; /* hold onto node being removed */
      *sPtr = ( *sPtr )->nextPtr; /* de-thread the node */
      free( tempPtr ); /* free the de-threaded node */
      
   } /* end if */
   else { 
      previousPtr = *sPtr;
      currentPtr = ( *sPtr )->nextPtr;

      /* loop to find the correct location in the list */
      while ( currentPtr != NULL && currentPtr->studata.SID != sid ) { 
         previousPtr = currentPtr; /* walk to ...   */
         currentPtr = currentPtr->nextPtr; /* ... next node */  
      } /* end while */

      /* delete node at currentPtr */
      if ( currentPtr != NULL ) { 
         tempPtr = currentPtr;
         previousPtr->nextPtr = currentPtr->nextPtr;
         free( tempPtr );
        
      } /* end if */
   } /* end else */

} /* end function delete */


