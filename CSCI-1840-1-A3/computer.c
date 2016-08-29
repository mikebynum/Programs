#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>

/*Used 7.28 code as a starting point for this program*/

/* define commands */
#define SIZE 100 
#define TRUE 1
#define FALSE 0
#define READ 10
#define WRIT 11
#define PRNT 12
#define LOAD 20
#define STOR 21
#define SET 22
#define ADD 30
#define SUB 31
#define DIV 32
#define MULT 33
#define MOD 34
#define BRAN 40
#define BRNG 41
#define BRZR 42
#define HALT 99



/* function prototypes */
int compile( int *memory, int *ir, int *op);
void execute( int *memory, int *acPtr, int *icPtr, int *irPtr,int *opCodePtr, int *opPtr );            
void dump( int *memory, int accumulator, int instructionCounter,int instructionRegister, int operationCode,int operand );    
int validWord( int word );



int main()
{
    int memory[ SIZE ]; /* define memory array */
    int ac = 0; /* accumulator */
    int ic = 0; /* instruction counter used to store the location of the current instruction to be executed (memory[ic])*/
    int op = 0; /* operand which is the memory address (memory[op]) that is to be used*/
    int ir = 0; /* instruction register used to store the actual current instruction (1009 or 1010)*/
    int opCode = 0; /* operation code which is the code that represent the operation to be performed (PRNT, READ, WRIT)*/
    int x; /* variable used to initialize the array */

    printf ("Welcome to the WONDERFUL WORLD of computer simulations!!\n");

    for(x=0; x<SIZE; x++)
        memory[x]=0;


    if(compile (memory, &ir, &op)){
        execute( memory, &ac, &ic, &ir, &opCode, &op );
        dump( memory, ac, ic, ir, opCode, op );
    }

    /*x=0;  USED THIS FOR MAKING SURE COMPILE WORKED CORRECTLY
      for(x=0; x<SIZE; x++)
      {
      printf("%d\n",memory[x]);
      }
     */

    return (0);
}


int compile(int *memory, int *ir, int *op)
{
    char *p[3]; /*USED FOR SPLITTING CODE INTO 3 SECTIONS*/
    char line[SIZE]; /*ARRAY FOR STORING CODE FROM FILE*/
    int halted=0; /*COUNTER FOR DETERMINING IF HALT WAS USED*/
    int unknown=0; /*COUNTER FOR DETERMINING IF THERE IS AN UNKNOWN VARIABLE*/
    int overflow=0; /*COUNTER FOR DETERMINING IF THE WORD IS TOO LARGE*/

    while (fgets(line,SIZE,stdin) != NULL)
    {
        p[0]=strtok(line, " \t"); /*FIRST PART IS THE ARRAY LOCATION*/
        p[1]=strtok(NULL," \t");  /*SECOND PART IS THE NAME*/
        p[2]=strtok(NULL," \t\n"); /*THIRD PART IS WHERE TO GO*/

        if(strcmp (p[1],"PRNT")==0){
            if ( !validWord (atoi(p[2]))){
                overflow++;
            }
            else{
                *ir=(PRNT*100)+(atoi(p[2]));
            }
        }
        else if (strcmp (p[1],"READ")==0){
            if ( !validWord (atoi(p[2]))){
                overflow++;
            }
            else{
                *ir=(READ*100)+(atoi(p[2]));
            }
        }
        else if (strcmp (p[1],"WRIT")==0){
            if ( !validWord (atoi(p[2]))){
                overflow++;
            }
            else{
                *ir=(WRIT*100)+(atoi(p[2]));
            }
        }
        else if (strcmp (p[1],"PRNT")==0){
            if ( !validWord (atoi(p[2]))){
                overflow++;
            }
            else{
                *ir=(PRNT*100)+(atoi(p[2]));
            }
        }
        else if (strcmp (p[1],"LOAD")==0){
            if ( !validWord (atoi(p[2]))){
                overflow++;
            }
            else{
                *ir=(LOAD*100)+(atoi(p[2]));
            }
        }
        else if (strcmp (p[1],"STOR")==0){
            if ( !validWord (atoi(p[2]))){
                overflow++;
            }
            else{
                *ir=(STOR*100)+(atoi(p[2]));
            }
        }
        else if (strcmp (p[1],"ADD")==0){
            if ( !validWord (atoi(p[2]))){
                overflow++;
            }
            else{
                *ir=(ADD*100)+(atoi(p[2]));
            }
        }
        else if (strcmp (p[1],"MULT")==0){
            if ( !validWord (atoi(p[2]))){
                overflow++;
            }
            else{
                *ir=(MULT*100)+(atoi(p[2]));
            }
        }
        else if (strcmp (p[1],"SUB")==0){
            if ( !validWord (atoi(p[2]))){
                overflow++;
            }
            else{
                *ir=(SUB*100)+(atoi(p[2]));
            }
        }
        else if (strcmp (p[1],"DIV")==0){
            if ( !validWord (atoi(p[2]))){
                overflow++;
            }
            else{
                *ir=(DIV*100)+(atoi(p[2]));
            }
        }
        else if (strcmp (p[1],"BRAN")==0){
            if ( !validWord (atoi(p[2]))){
                overflow++;
            }
            else{
                *ir=(BRAN*100)+(atoi(p[2]));
            }
        }
        else if (strcmp (p[1],"BRNG")==0){
            if ( !validWord (atoi(p[2]))){
                overflow++;
            }
            else{
                *ir=(BRNG*100)+(atoi(p[2]));
            }
        }
        else if (strcmp (p[1],"HALT")==0){
            if ( !validWord (atoi(p[2]))){
                overflow++;
            }
            else
            {
                *ir=(HALT*100)+(atoi(p[2]));
                halted = 1; 
            }
        }
        else if (strcmp (p[1],"MOD")==0){
            if ( !validWord (atoi(p[2]))){
                overflow++;
            }
            else{
                *ir=(MOD*100)+(atoi(p[2]));
            }
        }
        else if (strcmp (p[1],"SET")==0){
            if ( !validWord (atoi(p[2]))){
                overflow++;
            }
            else{
                *ir=atoi(p[2]);
            }
        }
        else if (strcmp (p[1],"BRZR")==0){
            if ( !validWord (atoi(p[2]))){
                overflow++;
            }
            else{
                *ir=(BRZR*100)+(atoi(p[2]));
            }
        }
        else/*print this if unknown command was found*/
        {
            printf("Unknown command found!\nGo check your stuff!!\n");
            unknown++;
        }

        memory[atoi(p[0])]=*ir;
    }/*end while*/

    if (!halted)
        printf ("\nNo halt found. Go back and put one in mister!\n\n");
    if (overflow != 0)
        printf ("\nOverflow. TOO MUCH STUFF!  Please fix-NOW!\n\n");

    if ((halted > 0) && (unknown == 0) && (overflow == 0))
        return 1;
    else
        return 0;



}/*end of load function*/


void execute( int *memory, int *acPtr, int *icPtr, int *irPtr,int *opCodePtr, int *opPtr )
{
    int fatal = FALSE; /* fatal error flag */
    int temp=0; /* temporary holding space */	


    *irPtr = memory[ *icPtr ];
    *opCodePtr = *irPtr / 100;
    *opPtr = *irPtr % 100;

    stdin = fopen("/dev/tty","r");

    /* loop while command is not HALT or fatal */
    while ( *opCodePtr != HALT && !fatal ) 
    {
        if (*icPtr >= 100){
            printf("\nSegmentation fault. UH OH!!!\n");
            break;
        }

        /* determine appropriate action */
        switch ( *opCodePtr )
        { 

            /* read data into location in memory */
            case READ:
                printf( "Please enter an integer: " );
                scanf("%d",&temp);/*where x is the address of operand register*/

                /* check for validity */
                while ( !validWord( temp ) ) { 
                    printf( "Number out of range, man. Please enter another one: " );
                    scanf( "%d", &temp );
                } /* end while */

                memory[ *opPtr ] = temp; /* write to memory */
                ++( *icPtr );
                break; /* exit switch */

                /* write data from memory to screen */
            case WRIT:
                printf( "Contents of %02d: %d\n", *opPtr, memory[ *opPtr ] );
                ++( *icPtr );
                break; /* exit switch */

                /* load data from memory into accumulator */
            case LOAD:
                *acPtr = memory[ *opPtr ];
                ++( *icPtr );
                break; /* exit switch */

                /* store data from accumulator into memory */
            case STOR:
                memory[ *opPtr ] = *acPtr;
                ++( *icPtr );
                break; /* exit switch */

                /* add data from memory to data in accumulator */
            case ADD:
                temp = *acPtr + memory[ *opPtr ];

                /* check validity */
                if ( !validWord( temp ) ) { 
                    printf( "*** DEADLY ERROR: Accumulator overflow ***\n" );
                    printf( "*** Execution was " );
                    printf( "abnormally terminated ***\n" );
                    fatal = TRUE;
                } /* end if */
                else { 
                    *acPtr = temp;
                    ++( *icPtr );
                } /* end else */

                break; /* exit switch */

                /* subtract data in memory from data in accumulator */
            case SUB:
                temp = *acPtr - memory[ *opPtr ];

                /* check validity */
                if ( !validWord( temp ) ) { 
                    printf( "*** DEADLY ERROR: Accumulator overflow ***\n" );
                    printf( "*** Execution " );
                    printf( "abnormally terminated ***\n" );
                    fatal = TRUE;
                } /* end if */
                else { 
                    *acPtr = temp;
                    ++( *icPtr );
                } /* end else */

                break; /* exit switch */

                /* divide data in memory into data in accumulator */
            case DIV:

                /* check for divide by zero error */
                if ( memory[ *opPtr ] == 0 ) { 
                    printf("*** VERY BAD MISHAP: Attempt to divide by zero ***\n");
                    fatal = TRUE;
                } /* end if */
                else { 
                    *acPtr /= memory[ *opPtr ];
                    ++( *icPtr );
                } /* end else */

                break; /* exit switch */

                /* multiply data in memory by data in accumulator */
            case MULT:
                temp = *acPtr * memory[ *opPtr ];

                /* check validity */
                if ( !validWord( temp ) ) { 
                    printf( "*** OOPSIE: Accumulator overflow ***\n" );
                    printf( "*** Execution " );
                    printf( "abnormally terminated ***\n" );
                    fatal = TRUE;
                } /* end if */
                else { 
                    *acPtr = temp;
                    ++( *icPtr );
                } /* end else */

                break; /* exit switch */

                /* branch to specific location in memory */
            case BRAN:
                *icPtr = *opPtr;
                break; /* exit switch */

                /* branch to location in memory if accumulator is negative */
            case BRNG:

                /* if accumulator is negative */
                if ( *acPtr < 0 ) {
                    *icPtr = *opPtr;
                } /* end if */
                else {
                    ++( *icPtr );
                } /* end else */

                break; /* exit switch */

                /* branch to location in memory if accumulator is zero */
            case BRZR:

                /* if accumulator is zero */
                if ( *acPtr == 0 ) {
                    *icPtr = *opPtr;
                } /* end if */
                else {
                    ++( *icPtr );
                } /* end else */

                break; /* exit switch */

            case MOD:
                /* check for divide by zero error */
                if ( memory[ *opPtr ] == 0 ) { 
                    printf("*** AH NUTS: Attempt to divide by zero ***\n");

                    fatal = TRUE;
                } /* end if */
                else { 
                    *acPtr %= memory[ *opPtr ];
                    ++( *icPtr );
                } /* end else */
                break; /* exit switch */

            case PRNT:
                while(1)
                {    
                    if((isupper(memory[*opPtr]/100)) || (memory[*opPtr]/100) == '\n')
                        putchar (memory[*opPtr]/100);
                    else if (memory[*opPtr]/100 == '\0')
                        break;
                    else{
                        printf ("\nError!!! Unknown character! Please do something about it....\n");
                        fatal = TRUE;
                        break;
                    }
                    if(isupper(memory[*opPtr]%100) ||(memory[*opPtr]%100) == '\n')
                        putchar (memory[*opPtr]%100);
                    else if (memory[*opPtr]%100 == '\0')
                        break;
                    else{
                        printf ("\nError!! Character not known....Jeez\n");
                        fatal = TRUE;
                        break;
                    }
                    ++(*opPtr);
                }
                ++(*icPtr);
                break;/* exit switch */

            default:
                printf( "*** UH OH....ERROR: unknown command detected ***\n" );
                printf( "*** Program abnormally terminated ***\n" );
                fatal = TRUE;     
                break; /* exit switch */ 
        } /* end switch */

        /* separate next operation code and operand */
        *irPtr = memory[ *icPtr ];
        *opCodePtr = *irPtr / 100;
        *opPtr = *irPtr % 100;
    } /* end while */

    printf( "\n*************END EXECUTION*************\n" );
} /* end function execute */


/* print out name and content of each register and memory */
void dump( int *memory, int accumulator, int instructionCounter,
        int instructionRegister, int operationCode,
        int operand )
{ 
    int i; /* counter */

    printf( "\n%s\n%-23s%+05d\n%-23s%5.2d\n%-23s%+05d\n%-23s%5.2d\n%-23s%5.2d",
            "REGISTERS:", "accumulator", accumulator, "instructioncounter",
            instructionCounter, "instructionregister", instructionRegister,
            "operationcode", operationCode, "operand", operand );

    printf( "\n\nMEMORY:\n   " );

    /* print column headers */
    for ( i = 0; i <= 9; i++ ) {
        printf( "%5d ", i );
    } /* end for */

    /* print row headers and memory contents */
    for ( i = 0; i < SIZE; i++ ) { 

        /* print in increments of 10 */
        if ( i % 10 == 0 ) {
            printf( "\n%2d ", i );
        } /* end if */

        printf( "%+05d ", memory[ i ] );
    } /* end for */

    printf( "\n" );
} /* end function dump */

/* function tests validity of word */
int validWord( int word )
{ 
    return (word >= -9999 && word <= 9999);

} /* end function validWord */

