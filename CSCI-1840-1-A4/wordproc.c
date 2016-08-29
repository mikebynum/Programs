#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int comparator ( const void * elem1, const void * elem2); /*prototype for comparator function used below*/

int main(int argc, char **argv) /*argc count of number of parameters; argv is 
array of pointers to string that contains the parameters entered when the program was invoked*/
{
    int i = 0; /*used as a counter*/
    int linelength; /* length of each line passed in to main*/
    /*char *filename; /* name of the file to open passed in to main*/
	char outname[100]; /* name of the ouput file */
	int currentLength=0; /* current length of the line */

    FILE *inputfile; /* actual file to be opened */
	FILE *outputfile; /* actual file used for the output of the breaking down of the paragraph after formatting*/
	FILE *wordfile; /* file used to store the words and the number of times they occur */

    char nextword[102]; /* character array that holds each of the letters of each of the words */
	
	
	char **wordlist = NULL; /* pointer to a pointer to array that holds all the words*/
	int size = 0; /* counter to be used in wordlist */
	int x = 0;
	char temp[50];

	typedef struct wordrec{
		int counter;
		char *word;
	}count; /*structure for holding the record of the word*/

	count *arr = NULL; /* a count structure pointer to hold each of the words and the number of occurances */

    printf("argc = %d\n", argc);

/****************validation of input*************************************************/

	/* ***************use this for determination if read in correctly**************
    for (i = 0; i < argc; i++)
	{
        printf("argv[%d] = \"%s\"\n", i, argv[i]);
    }
	*/

    if (argc != 3) /*if there are not 3 elements then something is left out and print message*/
	{
        printf("Usage: %s -- requires 2 arguments, length and file. Please fix and try again.\n",argv[0]);
         return (1);
    }

	/* ***************use this for determination if read in correctly**************
    printf ("line length = %s\n", argv[1]);
    printf("filename = %s\n", argv[2]);
	*/

/************************end validation of input************************************************************/

/************************beginning of part 1****************************************************************/

    linelength = atoi(argv[1]); /* convert line length argument to integer */   
	/*filename = argv[2]; puts the name of the file into the variable*/                            

    if ((linelength < 25) || (linelength > 100)) /*if the length of the line is out of range, output the error message*/
	{
        printf("Invalid length specified %d -- must be between 25 and 100!\n", linelength);
        return (1);
    }
    
    inputfile = fopen( argv[2], "r"); /*open the file and store it into "inputfile" */

    if (inputfile == NULL) /*if the file doesn't exist print out the error message*/
	{
        printf("Can't open file : %s\n", argv[2]);
        return (1);
    }

	sprintf(outname,"%s.out", argv[2]);
	outputfile = fopen( outname,"w");

	if (outputfile == NULL) 
	{
        printf("Cannot create file!\n");
        return (1);
    }

    while (  fscanf(inputfile, "%s", nextword) != EOF) 
	{

/*        printf("next word: %s\n", nextword);
        printf("word length: %d\n", strlen(nextword));
	
*/
		/* allocate space for wordlist in the amount of size of a character pointer*/
		wordlist = realloc (wordlist, (size + 1) * sizeof(char *));
		wordlist[size++] = strdup(nextword); /* dulicate the next word into the wordlist and returns 
												a pointer for the wordlist */

		if((currentLength + strlen(nextword)) <= linelength) /* if the length of the line is less than the
															 predetermined linelength continue to add the
															 word onto the line */
		{
			currentLength += strlen(nextword) + 1;

			fputs(nextword, outputfile);
			fputs(" ",outputfile);
		}

		/* if the length is longer than the predetermined one, then move to the next line 
		and continue adding */
		else
		{
			fputs("\n",outputfile);
			fputs(nextword, outputfile);
			fputs(" ",outputfile);
			currentLength = strlen(nextword) +1;
		}
    }

/********************end of part 1 *******************************************************/

/********************start of part B ****************************************************/	

	/* sort the wordlist */
	/* qsort - (first element in array, number of elements in the array, bytes of each element in array, function 
	used to compare */

	qsort (wordlist, size, sizeof(wordlist[0]), comparator);

	sprintf ( outname, "%s.words", argv[2]); /* write the name of the words file (variable given into main.words )
											 into the variable outname*/

	wordfile = fopen (outname, "w"); /* open the file to be written on */

	if ( wordfile == NULL)/* if the file doesn't exist, then print an error statement */
	{
		printf ("Cannot create file - error\n");
		return(1);
	}

	for( i = 0; i < size; i++ ) /* put the words into the new file */
	{
	  puts(wordlist[i]);
	}


	arr = (count *) realloc (arr, (x+1) * sizeof(count)); /* allocate space for the array that
															will be used to store the word and
															number of occuraces */
	arr[x].word = wordlist[0]; /* put the word in the array */
	arr[x].counter = 1; /* put the  number of occurances in the array */

	for (i=1; i<size; i++) /* look through array for same words */
	{
		if (strcmp (wordlist[i], arr[x].word) == 0) /* if the words are equal, then increase the counter */
		{
			arr[x].counter++;
		}
		else /* if the words do not match then print out the info */
		{
			fputs (arr[x].word, wordfile);
			fputs (" = ", wordfile);
			sprintf (temp, "%d", arr[x].counter);
			fputs (temp, wordfile);
			fputs ("\n", wordfile);

			arr = (count *) realloc (arr, (x+2) * sizeof(count)); /* allocate space for the next word and repeat */
			arr[++x].word = wordlist[i];
			arr[x].counter = 1;
		}
	}
	
	/*
	free(wordlist);
	free(arr)
	*/

	/********************close all open files!********************/
    fclose(inputfile);
	fclose(outputfile);
	fclose(wordfile);

    return 0;
}

int comparator ( const void * elem1, const void * elem2)
{
	return (strcmp (* (char**) elem1, * (char **) elem2));
}
