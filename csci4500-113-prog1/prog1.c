/******************************************/
/* prog1                                  */
/* Mike Bynum                             */
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

#define DUP 1
#define BUFFSIZE 64

int i; // used as a counter for "for" statements
int characterCount=0; // used to keep track of the array that holds each line 
                      // of characters

char *words [17]; // 16 words and the NULL
extern char **environ; // variable used in the execve system call

void commandStuff(char commandLine[]);
int execute();

void commandStuff(char commandLine[])
{
	char *p = NULL; // pointer used in strtok
	int countA=0; // variable to keep track of place in array
	int j; // counter for "for" statement
	char *command; // pointer to the command
	char *forwardSlash = "/";	
	bool slash=false; // boolean used to determine if there is a slash
	int foundStatus=-2; // int used to keep track if the dir was found
	char errFile [50]; // string for errors
	
	/* make enough room for the characters in the array */
	for (j=0; j < 16; j++)
	{
		words[j] = malloc (BUFFSIZE*sizeof(char));
	}
	
	/****************************************/
	/* Copy the first token to the pointer **/
	/****************************************/
	p = (char*)strtok (commandLine, " 	");
	words[countA] = p;
	countA++;
	
	/*****************************************/
	/* Keep doing this until there is        */
	/* nothing left                          */
	/*****************************************/
	while ( (p = (char*)strtok(NULL, " 	")) != NULL)
	{
		words[countA] = p;
		countA++;
	}
	
	command = words[0];  // point the command to the first word in the array
		
	if (!slash)
	{		
		char *path, *dir = NULL;
		#ifdef DUP
			char *path_copy;
		#else
			char path_copy[_POSIX_PATH_MAX+1];
		#endif
		char *fullDir = NULL;
		
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
		{	    
		/* while we got an entry... */
			fullDir = malloc(strlen(command) + strlen(dir) + 1);
			strcpy (fullDir, dir);		
			strcat (fullDir, forwardSlash);
			strcat (fullDir, command);
			foundStatus = access(fullDir, X_OK);
			
			if(foundStatus == 0) //if the program is found do this
			{
				words[0] = fullDir;						
				words[countA] = 0;
				break;
			}
			
			dir = strtok(NULL,":");     /* and get the next entry */
			
		}

			/*-----------------------------------------*/
			/* Free storage used for the copy of PATH. */
			/*-----------------------------------------*/
		#ifdef DUP
			free(path_copy);
			//free(fullDir);
		#endif
		
		if (foundStatus != 0)
		{
			write (2, "I could not find the file\n", 26);
		}
	 } 
}


int execute()
{
    pid_t cpid;			/* child's PID */
    pid_t status;		/* waitpid's return value */
    int child_status;		/* child's exit status */ 

    if ((cpid = fork()) == 0) 
    {
        /*---------------------------------------*/
        /* If fork returns 0 we're in the child. */
        /*---------------------------------------*/
        execve (words[0], words, environ);
        perror("execve");
        exit(2);
    }

    if (cpid == -1) 
    {
	/*----------------------------------------------*/
	/* If fork returns -1, it encountered an error. */
	/*----------------------------------------------*/
	perror("fork");
	exit(3);
    }

    /*------------------------------------------------------------*/
    /* Otherwise fork was successful. Wait for the child to exit. */
    /*------------------------------------------------------------*/
    status = waitpid(cpid, &child_status, 0);
    if (status == -1) 
    {
	perror("waitpid");
	exit(4);
    }

    /*-------------------------------------------------------------*/
    /* If the child exited normally (with exit or return), display */
    /* a message stating that fact and the low-order 8 bits of its */
    /* exit status. Otherwise indicate that it did not terminate   */
    /* normally.                                                   */
    /*-------------------------------------------------------------*/
    if (WIFEXITED(child_status)) 
    {
        return child_status;
    } 
    else 
    {
        write(1, "Child process did not exit normally.\n", 39);
    }
	
}
/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/

int main(int argc, char *argv[])
{
    char commandLine [100]; // character array to hold each line of characters
    char *pt = NULL; // pointer used for tokening
    char *semicolon = NULL; // pointer used for breaking up semicolons
    char *amper = NULL; // pointer used for breaking up ampersands
    char *pipes = NULL; // pointer used for breaking up pipes
    int readValue = 1; // int used to store the value returned by the read 
                       // system call
    char buffer [1]; // buffer to put each character in before putting it into 
                     // the array
    int countA=0;  // counter to hold array position
    int status; // int to hold return value for the execute function
    char *semiCommand [50]; // character array to hold commands separated by 
                            // semicolons
    int semiCount = 0;  // int to hold number of semicolons
    char commandLineCopy[100]; // holds a copy of the command line
    char commandToSend[100]; // array to send to function
    char commandToSendA[100]; // array to send to function
    int counter = 0; // counter for "for" calls
    char *amperCommand [50];  // array to hold the broken ampersands
    int amperCount = 0;  // int to hold number of ampersands
    char *pipeCommand [50];  // array to hold broken pipes
    int pipeCount = 0;  // int to hold number of pipes
    char my_string[100]; // array for sprintf strings
		
    /*-----------------------------*/
    /* Check for too few arguments */
    /*-----------------------------*/
    if (argc < 1) 
    { 
        write(2,"Usage: prog1 progname [arg] ...\n", 33);
        exit(1);
    }
	
    /*------------------------------*/
    /* Do the following with there  */
    /* are things to be read        */
    /*------------------------------*/
    do
    {
        readValue = read (0, buffer, 1); // read each byte (character) and store
                                         // it in the temp buffer
        commandLine[characterCount]=buffer[0]; // copy that character into the 
                                               // character array

        /*---------------------------------------*/
        /* Make space for each word in the array */
        /*---------------------------------------*/
        int j;
        for (j=0; j < 16; j++)
        {
            words[j] = malloc (BUFFSIZE*sizeof(char));
        }
		
        /*----------------------------------------------------------*/
        /* If the entire line is now stored in the commandLine array*/
        /* and the line is not empty (a newline), then continue     */
        /*----------------------------------------------------------*/
        if(commandLine[0]==' ' || commandLine[0]=='	'|| 
                commandLine[0] == '\n')
        {
            continue;
        }
        else if (commandLine[characterCount] == '\n' && commandLine[0] != '\n')
        {
            write(1,commandLine,characterCount); // write commandLine to stdout
            write(1, "\n\n", 2); // write the newline characters for spacing			
            int length  = strlen(commandLine); // determine the length of the 
                                               // commandLine
			
            /**********************************************/
            /* Replace the newline character with NULL    */
            /**********************************************/
            for (i=0; i<length; i++)
            {
                if (commandLine[i]=='\n')
                {
                    commandLine[i]='\0';
                }
            }
			
            /****************************************************************/
            /* Now we need to determine if there are semicolons in the line */
            /****************************************************************/					
            semicolon = strchr (commandLine, ';'); // check to see if there are 
                                                   // semicolons in the line		
	
            /*******************************************************/
            /* If there is a NOT semicolon, then there is only one */
            /* command, so we can continue on with the program     */
            /*******************************************************/
            if (semicolon==NULL)
            {
                amper = strchr (commandLine, '&'); // check to see if there are
                                                   // ampersands in the line
                pipes = strchr (commandLine, '|'); // check to see if there are 
                                                   // pipes in the line
                
                /***********************************/
                /* If there are ampersands do this */
                /***********************************/
                if (amper != NULL)
                {
                    amperCount=0;
                    pt = (char*)strtok (commandLine, "&&");
                    amperCommand [amperCount] = pt;
                    amperCount++;
					
                    while ((pt = (char*)strtok(NULL, "&&")) != NULL )
                    {
                        amperCommand [amperCount] = pt;
                        amperCount++;
                    }
                    
                    for (counter=0; counter < amperCount; counter++)
                    {
						strcpy (commandToSend, amperCommand[counter]);
                        commandStuff(commandToSend);
                        status = execute();
                        memset(&commandToSend[0], 0, sizeof(commandToSend));
						
                        if (status == 0)
                        {
                            continue;
                        }			
                        else
                        {
                            break;
                        }
					
                    }	
                }
                /*******************************/
                /* If there are pipes, do this */
                /*******************************/
                else if (pipes != NULL)
                {
                    /****************************/
                    /* First thing is tokenize  */
                    /****************************/
                    pipeCount = 0;
                    pt = (char*)strtok (commandLine, "||");
                    pipeCommand [pipeCount] = pt;
                    pipeCount++;
					
                    while ((pt = (char*)strtok(NULL, "||")) != NULL )
                    {
                        pipeCommand [pipeCount] = pt;
                        pipeCount++;
                    }
		
                    for (counter=0; counter < pipeCount; counter++)
                    {
                        strcpy (commandToSend, pipeCommand[counter]);
                        commandStuff(commandToSend);
                        status = execute();
			
                        /**************************************************/
                        /* if the returned value from execute does not    */
                        /* equal 0, it is false, then continue, else get  */
                        /* out of the loop                                */
                        /**************************************************/
                        if (status != 0)
                        {
                            continue;
                        }			
                        else
                        {
                            break;
                        }
                    }	
                }
                else
                {
                    commandStuff(commandLine);
                    status = execute();
                }
            }

            /*****************************************************/
            /* If there IS semicolon, then there are more than   */
            /* one command, so we must split them up             */
            /*****************************************************/
			
            else
            {
                semiCount=0;
                strcpy (commandLineCopy,commandLine);
                pt = (char*)strtok (commandLineCopy, ";");
                semiCommand[semiCount] = pt;
                semiCount++;
				
                while ((pt = (char*)strtok(NULL, ";")) != NULL )
                {
                    semiCommand[semiCount] = pt;
                    semiCount++;
                }
				
                for (counter=0; counter < semiCount; counter++)
                {
                    strcpy (commandToSend, semiCommand[counter]);
                    amper = strchr (commandToSend, '&'); // check to see if 
                                                         // there are ampersands
                                                         // in the line
                    
                    pipes = strchr (commandToSend, '|'); // check to see if 
                                                         // there are pipes in 
                                                         // the line
				
                    
                    if (amper != NULL)
                    {
                        /****************************/
                        /* First thing is tokenize  */
                        /****************************/
                        amperCount=0;
                        pt = (char*)strtok (commandToSend, "&&");
                        amperCommand [amperCount] = pt;
                        amperCount++;
					
                        while ((pt = (char*)strtok(NULL, "&&")) != NULL )
                        {
                            amperCommand [amperCount] = pt;
                            amperCount++;
                        }
						
                        int counterOne;
                        for (counterOne=0; counterOne < amperCount; 
                                counterOne++)
                        {
                            strcpy (commandToSendA, amperCommand[counterOne]);
                            commandStuff(commandToSendA);
                            status = execute();
                            memset(&commandToSendA[0], 0, 
                                    sizeof(commandToSendA));
							
                            if (status == 0)
                            {
                                continue;
                            }	
                            else
                            {
                                break;
                            }
						
                        }	
                    }
                    else if (pipes != NULL)
                    {
                        /****************************/
                        /* First thing is tokenize  */
                        /****************************/
                        pipeCount = 0;
                        pt = (char*)strtok (commandToSend, "||");
                        pipeCommand [pipeCount] = pt;
                        pipeCount++;

                        while ((pt = (char*)strtok(NULL, "||")) != NULL )
                        {
                            pipeCommand [pipeCount] = pt;
                            pipeCount++;
                        }
                        int counterTwo;
                        
                        for (counterTwo=0; counterTwo < pipeCount; counterTwo++)
                        {
                            strcpy (commandToSendA, pipeCommand[counterTwo]);
                            commandStuff(commandToSendA);
                            status = execute();
							
                            if (status != 0)
                            {
                                continue;
                            }	
                            else
                            {
                                break;
                            }
						
                        }	
                    }
                    else
                    {
                        commandStuff(commandToSend);
                        status = execute();
                    }				
                }
            }
            characterCount=0;
            memset(&commandLine[0], 0, sizeof(commandLine));
            countA=0;
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
