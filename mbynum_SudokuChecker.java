/* Name  	:Mike Bynum
   Class 	:1400-02
   Program #	:Program 15
   Due Date	:4-6-10

   Honor Pledge	:On my honor as a student of the University of Nebraska at Omaha, I have neither given nor received unauthorized help on this homework assignment.

Name	:Mike Bynum
NUID	:343
Email	:mbynum@unomaha.edu

Partners: None

Description of program: This program will have the user enter numbers from a 4x4 Sudoku puzzle and will output whether the numbers are good (there are numbers 1 through 4 in each row/column and do no repeat) or whether the puzzle doesn't meet the criteria for a Sudoku puzzle.
*/

import java.util.Scanner;

public class mbynum_SudokuChecker

{
	private int w1,w2,w3,w4,x1,x2,x3,x4,y1,y2,y3,y4,z1,z2,z3,z4;

	public mbynum_SudokuChecker ()
	{
		System.out.println();

		System.out.println("Welcome to the Sudoku Checker v1.0!\n");

		System.out.println("This program checks simple, small, 4x4 Sudoku grids for correctness.  Each column, row and 2x2 region contains the numbers 1 through 4 only once.\n");

		System.out.println("To check your Sudoku, enter your board one row at a time, with each digit separated by a space.  Hit ENTER at the end of the row.\n");
	}
/*
Method Name	:getGrid
Parameters	:none
Return Values	:none
Partners	:none
Description	:This method will prompt the user to enter in a series of numbers corresponding to the Sudoku grid
*/

	public void getGrid ()
	{
		Scanner input = new Scanner(System.in);

		System.out.print("Enter Row 1: ");
		w1 = input.nextInt();
		w2 = input.nextInt();
		w3 = input.nextInt();
		w4 = input.nextInt();

		System.out.print("Enter Row 2: ");	
		x1 = input.nextInt();
		x2 = input.nextInt();
		x3 = input.nextInt();
		x4 = input.nextInt();

		System.out.print("Enter Row 3: ");
		y1 = input.nextInt();
		y2 = input.nextInt();
		y3 = input.nextInt();
		y4 = input.nextInt();

		System.out.print("Enter Row 4: ");
		z1 = input.nextInt();
		z2 = input.nextInt();
		z3 = input.nextInt();
		z4 = input.nextInt();


                System.out.print("\nThank you.  Now checking...\n");
	}	

/*
Method Name	:checkGrid
Parameters	:none
Return Values	:none
Partners	:none
Description:	This method will check each section of the Sudoku puzzle to determine if they are valid (if they follow the Sudoku rules) and will print the result.
*/

	public void checkGrid ()
	{
		String REG1=null;
                String REG2=null;
                String REG3=null;
                String REG4=null;
                String ROW1=null;
                String ROW2=null;
                String ROW3=null;
                String ROW4=null;
                String COL1=null;
                String COL2=null;
                String COL3=null;
                String COL4=null;
                String valid=null;
		int Z = 0;

		if (w1 + w2 + w3 + w4 == 10)
		{
			ROW1 = "GOOD";
		}
		else 
		{
			ROW1 = "BAD";
			Z = Z +1;
                }

                if (x1 + x2 + x3 + x4 == 10)
		{
			ROW2 = "GOOD";
		}
		else
		{
			ROW2 = "BAD";
			Z = Z +1;
		}

                if (y1 + y2 + y3 + y4 == 10)
		{
			ROW3 = "GOOD";
		}
		else
		{
			ROW3 = "BAD";
			Z = Z +1;
                }

                if (z1 + z2 + z3 + z4 == 10)
		{
			ROW4 = "GOOD";
		}
		else
		{
			ROW4 = "BAD";
			Z = Z +1;
                }

                 if (w1 + x1 + y1 + z1 == 10)
		{
			COL1 = "GOOD";
		}
		else
		{
			COL1 = "BAD";
			Z = Z +1;
                }

                if (w2 + x2 + y2 + z2 == 10)
		{
			COL2 = "GOOD";
		}
		else
		{
			COL2 = "BAD";
			Z = Z +1;
                }

                if (w3 + x3 + y3 + z3 == 10)
		{
			COL3 = "GOOD";
		}
		else
		{
			COL3 = "BAD";
			Z = Z +1;
                }

                if (w4 + x4 + y4 + z4 == 10)
		{
			COL4 = "GOOD";
		}
		else
		{
			COL4 = "BAD";
			Z = Z +1;
                }

                if (w1 + x1 + w2 + x2 == 10)
		{
			REG1 = "GOOD";
		}
		else
		{
			REG1 = "BAD";
			Z = Z +1;
                }

                if (w3 + x3 + w4 + x4 == 10)
		{
			REG3 = "GOOD";
		}
		else
		{
			REG3 = "BAD";
			Z = Z +1;
                }

                if (y1 + z1 + y2 + z2 == 10)
		{
			REG2 = "GOOD";
		}
		else
		{
			REG2 = "BAD";
			Z = Z +1;
                }

                if (y3 + z3 + y4 + z4 == 10)
		{
			REG4 = "GOOD";
		}
		else
		{
			REG4 = "BAD";
			Z = Z +1;
                }

                if (Z >= 1)
                {
                        valid = "INVALID";
                }
                else
                {
                        valid = "VALID";
                }

		System.out.println();
                System.out.printf("REG-1:%s\n",REG1);
                System.out.printf("REG-2:%s\n",REG2);
                System.out.printf("REG-3:%s\n",REG3);
                System.out.printf("REG-4:%s\n",REG4);
		System.out.println();

                System.out.printf("ROW-1:%s\n",ROW1);
                System.out.printf("ROW-2:%s\n",ROW2);
                System.out.printf("ROW-3:%s\n",ROW3);
                System.out.printf("ROW-4:%s\n",ROW4);
		System.out.println();

                System.out.printf("COL-1:%s\n",COL1);
                System.out.printf("COL-2:%s\n",COL2);
                System.out.printf("COL-3:%s\n",COL3);
                System.out.printf("COL-4:%s\n",COL4);
		System.out.println();

                System.out.printf("SUDO:%s\n",valid);
		System.out.println();


    }

}


