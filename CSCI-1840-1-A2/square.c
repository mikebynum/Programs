#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define MAX 50

char puzzle[ MAX ][ MAX ];  /*character array to hold puzzle part of word search*/
char ch;					/*variable to hold each of the characters*/
char word [ 100 ][ 100 ];	/*array to hold the words to be found*/
int i,N,x;				/*'i' is the counter for the iteration of the puzzle; 
								'N' is the number to determine the square size; 
								r is the rows; c is the columns*/
int I = 0;					 /*variable for reading in the words*/
char found[ MAX ][ MAX ];
int rowPos;
int colPos;

int findWord(int r, int c, int rd, int cd, char *word, char p [MAX][MAX]);
void placeLetter(int r, int c, int rd, int cd, char *word, char p [MAX][MAX]);
int solvePuzzle( );


int main (void)
{
	int r,c;

	printf("Welcome to the Word Search!!!\n");


	/*read first line and determine size N*/
	i=0;
	ch=getchar();
	while(ch != '\n')
	{
		puzzle[0][i++]=ch;
		getchar();
		ch=getchar();
	}
	N=i;
	printf("N=%d\n",N);

	/*now we want to read the rest*/
	for(r=1;r<N;r++)
	{
		for(c=0;c<N;c++)
		{
				puzzle[r][c]=getchar();
				getchar();
		}
		getchar();
	}

	/*this will get each word and stick it into the array*/
	
    while ( fgets( word[I], 50, stdin ) != NULL )
    {
        /* It will have the newline - take that off */
        word[I][strlen(word[I])-1] = '\0';
        I++;
    }


	/*this will print the word search array*/
	for(r=0;r<N;r++)
	{
		for(c=0;c<N;c++)
			putchar(puzzle[r][c]);
		putchar('\n');
	}

	/*initialize the solved puzzle array to all spaces*/
	for(r=0;r<MAX;r++)
	{
		for(c=0;c<MAX;c++)
		{
				found[r][c]=' ';		
		}
	}

	/*pass in each word to find in puzzle
	  check each direction and break when found*/
	for(x=0; x<54; x++)
	{
			for (r=0; r<N; r++)
			{
				for(c=0; c<N; c++)
				{
					if (puzzle[r][c] == word [x][0])
					{
						if(findWord(r,c,1,0,word[x],puzzle))
						{
							/*printf("word found going down\n");
							 look down*/
						}
						else if(findWord(r,c,-1,0,word[x],puzzle))
						{
							/*printf("word found going up\n");
							 look up*/
						}
						else if(findWord(r,c,1,1,word[x],puzzle))
						{
							/*printf("word found right and up\n");
							 look right and up*/
						}
						else if(findWord(r,c,1,-1,word[x],puzzle))
						{
							/*printf("word found right and down\n");
							 look right and down*/
						}
						else if(findWord(r,c,-1,-1,word[x],puzzle))
						{
							/*printf("word found left and down\n");
							 look left and down*/
						}
						else if(findWord(r,c,-1,1,word[x],puzzle))
						{
							/*printf("word found left and up\n");
							 look left and up*/
						}
						else if(findWord(r,c,0,1,word[x],puzzle))
						{
							/*printf("word found to the right\n");
							 look right*/
						}
						else if(findWord(r,c,0,-1,word[x],puzzle))
						{
							/*printf("word found to the left\n");
							 look left*/
						}
					}
				}
			}

			
	}/*end outmost for loop*/

	/*this will print the array with found words*/
	for(r=0;r<N;r++)
	{
		for(c=0;c<N;c++)
			putchar(found[r][c]);
		putchar('\n');
	}

	return(0);
}/*end main*/

int findWord (int r, int c, int rd, int cd, char *word, char p [MAX][MAX])
{
	char *save = word;
	rowPos=r;
	colPos=c;
	
	while ( r>=0 && c>=0 && r<N && c<N && puzzle[r][c]==*word)
	{
		
		r+=rd;
		c+=cd;
		word++;
	}

	if (!*word)
	{
		placeLetter(rowPos,colPos,rd,cd,save,found); 

		return (1); /*if the word is found return true*/
	}

	else
	{
		return (0);/*if not found return false*/
	}
}

void placeLetter(int r, int c, int rd, int cd, char *word, char p [MAX][MAX])
{
	while(*word)
	{
		p[r][c]=*word++;
		r+=rd;
		c+=cd;
	}
}
