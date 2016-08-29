








#include <stdio.h>
#include <string.h>
#include <stdlib.h>


int comparator ( const void *elem1, const void *elem2);

int comparator ( const void *elem1, const void *elem2)
{
    return strcmp (* (char**) elem1, * (char **) elem2);
}

typedef struct wordrec{
    int counter;
    char *word;
}count; /*structure for holding the record of the word*/

int main(int argc, char **argv) {
    int i = 0; 
    int linelength;
    int size  = 0, x = 0;
    int currentLength = 0; 

    count *arr = NULL;

    char temp[50];
    char *filename; 
    char **wordlist = NULL;
    char nextword[102];
    char outname[25];

    FILE *inputfile; 
    FILE *outputfile;   
    FILE *wordfile;




    printf("argc = %d\n", argc);

    /****************validation of
    * input*************************************************/

   /* for (i = 0; i < argc; i++)
    {
        printf("argv[%d] = \"%s\"\n", i, argv[i]);
    }*/

    if (argc != 3) /*if there are not 3 elements then something is left
                     out and print message*/
    {
        printf("Usage: %s -- requires 2 arguments, length and file. Please fix and try again.\n",argv[0]);
        return (1);
    }

   /* printf ("line length = %s\n", argv[1]);
    printf("filename = %s\n", argv[2]);

    ***********************end validation of
    * input************************************************************/

    /************************beginning of part
    * 1****************************************************************/

    linelength = atoi(argv[1]); 
   /* filename = argv[2]; */

    if ((linelength < 25) || (linelength > 100)) 
    {
        printf("invalid length specified %d -- must be between 25 and 100", linelength);
        return 1;
    }

    inputfile = fopen( argv[2], "r"); 

    if (inputfile == NULL) 
    {
        printf("Can't open file : %s\n",argv[2]);
        return 1;
    }

    sprintf(outname, "%s.out" ,argv[2]);
    outputfile = fopen( outname,"w");

    if (outputfile == NULL) 
    {
        printf("cant create file\n");
        return 1;
    }

    while (fscanf(inputfile, "%s", nextword) !=EOF) 
    {

        wordlist = realloc(wordlist,(size+1) * sizeof(char *));
        wordlist[size++]= strdup(nextword);

        if((currentLength + strlen(nextword)) <= linelength) {

            currentLength += strlen(nextword) + 1;

            fputs(nextword, outputfile);
            fputs(" ",outputfile);
        }

        else
        {
            fputs("\n",outputfile);
            fputs(nextword, outputfile);
            fputs(" ",outputfile);
            currentLength = strlen(nextword) +1;
        }
    }




    qsort (wordlist, size, sizeof(wordlist[0]), comparator);

    sprintf ( outname, "%s.words", argv[2]);
    wordfile = fopen (outname, "w");

    if ( wordfile == NULL)
    {
        printf ("Cannot create file - error");
        return(1);
    }

    arr = (count *) realloc (arr, (x+1) * sizeof(count));
    arr[x].word = wordlist[0];
    arr[x].counter = 1;

    for (i=1; i<size; i++)
    {
        if (strcmp (wordlist[i], arr[x].word) == 0) /*check to see if we need ==0*/
        {
            arr[x].counter++;
        }
        else
        {
            fputs (arr[x].word, wordfile);
            fputs (" = ", wordfile);
            sprintf (temp, "%d", arr[x].counter);
            fputs (temp, wordfile);
            fputs ("\n", wordfile);

            arr = (count *) realloc (arr, (x+2) * sizeof(count));
            arr[++x].word = wordlist[i];
            arr[x].counter = 1;
        }
    }


    fclose(inputfile);
    fclose(outputfile);
    fclose(wordfile);

    return 0;
}










