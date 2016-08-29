/*
Name	:Mike Bynum
Class	:1400-02
Program#	:Program 19
Due Date	:5-6-10

Honor Pledge	:On my honor as a student of the University of Nebraska at Omaha, I have neither given nor received unauthorized help on this homework assignment.

Name	:Mike Bynum
NUID	:343
Email	:mbynum@unomaha.edu

Partners: None

Description of program: This program will implement the classic computer science simulation, Conway's Game of Life.
*/

import java.util.Scanner;
import java.util.Random;

// Change NetID to your NetID
public class mbynum_Life 
{

    // the size of the grid (GRIDSIZE x GRIDSIZE)
    final private static int GRIDSIZE = 18;

    /********************************************************************************/
    public static void main ( String args[] )
    {
        boolean[][] board = new boolean[GRIDSIZE][GRIDSIZE];
        char choice;
        int x = 1;
        Scanner sc = new Scanner ( System.in );

        do
        {
            System.out.print ( "Start with a (r)andom board, the (q)ueen bee shuttle or the (g)lider pattern? ");
            choice = sc.next().charAt(0);
        } while ( choice != 'r' && choice != 'q' && choice != 'g' );

        clearGrid (board);
        setup(board,choice);

        do
        {
            System.out.printf ("Viewing generation #%d:\n\n", x++);
            displayGrid(board);
            genNextGrid(board);
            System.out.print ("\n(q)uit or any other key + ENTER to continue: ");
            choice = sc.next().charAt(0);
        } while ( choice != 'q' );

    }

    /********************************************************************************/
    public static void setup (boolean[][] board, char which )
    {
        Random randomNumbers = new Random();

        clearGrid(board);

        if ( which == 'q' )
        {
            // Set up the Queen Bee Shuttle pattern
            board[5][1] = true;board[5][2] = true;board[6][3] = true;board[7][4] = true; 
            board[8][4] = true;board[9][4] = true;board[10][3] = true;board[11][2] = true;
            board[11][1] = true;        
        }
        else if ( which == 'g' )
        {
            // Set up a Glider
            board [17][0] = true; board[16][1] = true; board[15][1] = true;
            board[16][2] = true;
            board [17][2] = true;
        }
        else
        {
            // set up random
            for (int row = 0; row < board.length; row++ )
            {
                for (int col = 0; col < board[row].length; col++ )
                {
                    if ( randomNumbers.nextInt() % 2 == 0 )
                        board[row][col] = true;
                }
            }
        }

    }

    /********************************************************************************/
    public static void displayGrid (boolean[][] grid)
    {
        // Start printing the top row of numbers
        System.out.print ("   ");
        for (int x = 1; x <= grid.length; x++)
        {
            if ((x / 10) != 0)
                System.out.printf ( "%d", x / 10 );
            else
                System.out.print ( " " );
        }

        System.out.println();
        System.out.print( "   " );

        for (int x = 1; x <= grid.length; x++)
        {
            System.out.printf ( "%d", x % 10 );
        }
        System.out.println();

        for (int r = 0; r < grid.length; r++)
        {
            System.out.printf ( "%d", r+1 );
            if (r + 1 < 10)
                System.out.print ( "  " );
            else
                System.out.print ( " " );
            for (int c = 0; c < grid.length; c++)
            {
                if (grid[r][c] == true)
                    System.out.print ( "*" );
                else
                    System.out.print ( " " );
            }
            System.out.println();
        }
    }


    /*******************************************************************************/

    // put the three methods you must write here and make sure to document
    // them!

    public static void clearGrid ( boolean[][] board )
    {
	for (int row=0; row < board.length; row++)
	{
		for (int col=0; col < board[row].length; col++)
		{
			board[row][col] = false;
		}
	}
    }

    public static void genNextGrid ( boolean[][] board )
    {
	boolean [][] temp = new boolean [GRIDSIZE][GRIDSIZE];

	for (int row = 0; row < GRIDSIZE; row++)
	{
		for (int col = 0; col < GRIDSIZE; col++)
		{
			temp[row][col] = board[row][col];

			int count = countNeighbors ( board,row,col);

			switch (count)
			{
				case 0: case 1;
					temp[row][col] = false;
					break;
		}
	}

	for (int row = 0; row < 18; row++)
	{
		for (int col = 0; col < 18; col++)
		{
			int count = countNeighbors (temp,row,col);
		}
	}
    }

    public static int countNeighbors ( final boolean[][] board, final int row, final int col )
    {
    }
}
