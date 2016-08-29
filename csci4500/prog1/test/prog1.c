/******************************************/
/* prog1                                  */
/* Create a simple command line intrepter */
/* or shell.                              */
/*                                        */
/******************************************/
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <string.h>
#include <limits.h>
#include <stdbool.h> 

#define DUP 1;


int main(int argc, char *argv[])
{
	int readValue = 1;
	char buffer [1];
	char commandLine [100];
	int count=0;
	int i;
	pid_t cpid;			/* child's PID */
    pid_t status;		/* waitpid's return value */
    int child_status;		/* child's exit status */
	int characterCount=0;
	char words [16][64];
	
	
	
	char *p = NULL;
	int countA=0;
	int countB=0;
	int length=0;
	
	extern char **environ;
	
	
    /*-----------------------------*/
    /* Check for too few arguments */
    /*-----------------------------*/
    if (argc < 1) 
	{ 
		fprintf(stderr,"Usage: prog1 progname [arg] ...\n");
		exit(1);
    }
	
	do
	{
		readValue = read (0, buffer, 1);
		commandLine[characterCount]=buffer[0];
		
		if (commandLine[characterCount] == '\n' && commandLine[0] != '\n')
		{
			write(1,commandLine,characterCount);
			
			printf("\n");
			
			int length  = strlen(commandLine);

			
			for (i=0; i<length; i++)
			{
				if (commandLine[i]=='\n')
				{
					commandLine[i]='\0';
				}
			}

/**********************************************************************************************************/
			printf ("The command line that is passed to the parseCommand is: %s\n", commandLine);

			p = (char*)strtok (commandLine, " ");

			length=strlen(p);
					
			strcpy(words[countA],p);
			
			countA++;
			
			while ( (p = (char*)strtok(NULL, " ")) != NULL)
			{
				
				
				length=strlen(p);
							
				strcpy(words[countA],p);
				
				countA++;
			}
					
			for (i=0; i<countA; i++)
			{
				printf ("the word at %d is %s\n", i, words[i]);	
			}
			
			printf ("The command is, %s\n",words[0]);
			int commandLength = strlen(words[0]);
			printf ("The length of the command is %d\n",commandLength);
/**********************************************************************************/			
			char *command;
			bool slash=false;
			int foundStatus=-2;
			
			command = words[0];
			
			for (i=0; command[i]; i++)
			{
				printf ("%c\n", command[i]);
				if(command[i]=='/')
				{
					slash = true;
				}
			}
			
			
printf("The found status1 is %d\n", foundStatus);
/************************************************************************************************************/			
		if (!slash)
			{
				printf ("This does NOT contain a /. Looking for file in the PATH\n");
				
					char *path, *dir;
				#ifdef DUP
					char *path_copy;
				#else
					char path_copy[_POSIX_PATH_MAX+1];
				#endif

					/*-------------------------------------------------*/
					/* Get the value of the PATH environment variable. */
					/* We assume here that this succeeds, but it is    */
					/* normally good practice to check the result.     */
					/*-------------------------------------------------*/
					path = getenv("PATH");

					/*-------------------------------------------------------------*/
					/* Make a copy of PATH's value so we don't alter the original. */
					/*-------------------------------------------------------------*/
				#ifdef DUP
					path_copy = strdup(path);
				#else
					strcpy(path_copy,path);
				#endif

					/*---------------------------------------------------*/
					/* Parse the string and display the directory names. */
					/*---------------------------------------------------*/
					
					dir = strtok(path_copy,":");     /* get first entry */
					while (dir != NULL) 
					{	    /* while we got an entry... */
						
						char *fullDir = malloc(strlen(command) + strlen(dir) + 1);
						strcpy (fullDir, dir);
						fullDir[strlen(dir)]='/';
						
						strcat (fullDir, command);
						
						printf("The found status2 is %d\n", foundStatus);
						foundStatus = access(fullDir, X_OK);
						if(foundStatus == 0)
						{
							printf("The found status3 is %d\n", foundStatus);
							printf ("I REALLY found the file %s in %s\n",command,fullDir);
							strcpy(words[0],fullDir);
							printf ("The full command is %s\n", words[0]);
							
							
							
							for (i=0; i<countA; i++)
							{
								printf ("the word at %d is %s\n", i, words[i]);	
							}
							execve (words[0], words, environ);
							
							break;
						}
						else
						{
						printf("I did not find the file is in the directory, %s\n", fullDir);
						}
					
						dir = strtok(NULL,":");     /* and get the next entry */
					}

					/*-----------------------------------------*/
					/* Free storage used for the copy of PATH. */
					/*-----------------------------------------*/
				#ifdef DUP
					free(path_copy);
				#endif
			 } 
/***************************************************************************/			
			printf("\n.......................................\n");
						
			characterCount=0;
			
			memset(&commandLine[0], 0, sizeof(commandLine));
			countA=0;
			//exit (0);
		}
		else
		{
			
			characterCount++;
		}
		
	}while (readValue != 0);
	

	
    
    exit(0);
}

/*****************************************************************/
/* Notes about this program                                      */
/*                                                               */
/* 1. Instead of using execve to execute the desired file (as in */
/*    prun), this program uses execvp which searches for the     */
/*    file in the directories specified in the PATH environment  */
/*    variable. Thus "./prunw echo Hello" will work correctly.   */
/*                                                               */
/* 2. The parent process also waits (using waitpid) for the      */
/*    child process to terminate normally or abnormally. It then */
/*    displays an appropriate message, and if termination was    */
/*    normal, also displays its exit or return status.           */
/*****************************************************************/
