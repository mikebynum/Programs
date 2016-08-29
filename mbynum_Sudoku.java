/* Name  	:Mike Bynum
   Class 	:1400-02
   Program #	:Program 7
   Due Date	:2-16-10

   Honor Pledge	:On my honor as a student of the University of Nebraska at Omaha, I have neither given nor received unauthorized help on this homework assignment.

Name	:Mike Bynum
NUID	:343
Email	:mbynum@unomaha.edu

Partners: None

Description of program: This program will have the user enter numbers from a 4x4 Sudoku puzzle and will output whether the numbers are good (there are numbers 1 through 4 in each row/column and do no repeat) or whether the puzzle doesn't meet the criteria for a Sudoku puzzle.
*/

import java.util.Scanner;

public class mbynum_Sudoku

{
	public static void main ( String arg [] )
	{
		System.out.println();

		System.out.println("Welcome to the Sudoku Checker v1.0!\n");

		System.out.println("This program checks simple, small, 4x4 Sudoku grids for correctness.  Each column, row and 2x2 region contains the numbers 1 through 4 only once.\n");

		System.out.println("To check your Sudoku, enter your board one row at a time, with each digit separated by a space.  Hit ENTER at the end of the row.\n");

		int a1,a2,a3,a4,b1,b2,b3,b4,c1,c2,c3,c4,d1,d2,d3,d4,Z=0;

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

		Scanner input = new Scanner(System.in);

		System.out.print("Enter Row 1: ");
		a1 = input.nextInt();
		a2 = input.nextInt();
		a3 = input.nextInt();
		a4 = input.nextInt();

		System.out.print("Enter Row 2: ");	
		b1 = input.nextInt();
		b2 = input.nextInt();
		b3 = input.nextInt();
		b4 = input.nextInt();

		System.out.print("Enter Row 3: ");
		c1 = input.nextInt();
		c2 = input.nextInt();
		c3 = input.nextInt();
		c4 = input.nextInt();

		System.out.print("Enter Row 4: ");
		d1 = input.nextInt();
		d2 = input.nextInt();
		d3 = input.nextInt();
		d4 = input.nextInt();

                System.out.print("\nThank you.  Now checking...\n");

		if (a1 + a2 + a3 + a4 == 10)
		{
			ROW1 = "GOOD";
		}
		else 
		{
			ROW1 = "BAD";
			Z = Z +1;
                }

                if (b1 + b2 + b3 + b4 == 10)
		{
			ROW2 = "GOOD";
		}
		else
		{
			ROW2 = "BAD";
			Z = Z +1;
		}

                if (c1 + c2 + c3 + c4 == 10)
		{
			ROW3 = "GOOD";
		}
		else
		{
			ROW3 = "BAD";
			Z = Z +1;
                }

                if (d1 + d2 + d3 + d4 == 10)
		{
			ROW4 = "GOOD";
		}
		else
		{
			ROW4 = "BAD";
			Z = Z +1;
                }

                 if (a1 + b1 + c1 + d1 == 10)
		{
			COL1 = "GOOD";
		}
		else
		{
			COL1 = "BAD";
			Z = Z +1;
                }

                if (a2 + b2 + c2 + d2 == 10)
		{
			COL2 = "GOOD";
		}
		else
		{
			COL2 = "BAD";
			Z = Z +1;
                }

                if (a3 + b3 + c3 + d3 == 10)
		{
			COL3 = "GOOD";
		}
		else
		{
			COL3 = "BAD";
			Z = Z +1;
                }

                if (a4 + b4 + c4 + d4 == 10)
		{
			COL4 = "GOOD";
		}
		else
		{
			COL4 = "BAD";
			Z = Z +1;
                }

                if (a1 + b1 + a2 + b2 == 10)
		{
			REG1 = "GOOD";
		}
		else
		{
			REG1 = "BAD";
			Z = Z +1;
                }

                if (a3 + b3 + a4 + b4 == 10)
		{
			REG3 = "GOOD";
		}
		else
		{
			REG3 = "BAD";
			Z = Z +1;
                }

                if (c1 + d1 + d2 + c2 == 10)
		{
			REG2 = "GOOD";
		}
		else
		{
			REG2 = "BAD";
			Z = Z +1;
                }

                if (c3 + d3 + c4 + d4 == 10)
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


