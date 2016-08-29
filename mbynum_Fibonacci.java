/* Name  	:Mike Bynum
   Class 	:1400-02
   Program #	:Program 16 
   Due Date	:4-15-10

   Honor Pledge	:On my honor as a student of the University of Nebraska at Omaha, I have neither given nor received unauthorized help on this homework assignment.

Name	:Mike Bynum
NUID	:343
Email	:mbynum@unomaha.edu

Partners: None

Description of program: This program will prompt the user to request a particular Fibonacci number, calculate it, and then output the result.
*/

import java.util.Scanner;

public class mbynum_Fibonacci

{
	public static void main ( String arg [] )
	{
		Scanner input = new Scanner (System.in);

		int fib;

		System.out.println(" ");

		System.out.print("Which Fibonacci number would you like? ");
		fib = input.nextInt();
	
		while ( fib < 0 || fib > 70)
		{
			if (fib <0)
			{
				System.out.println("That number is too low.  Please try again.");

				System.out.print("Which Fibonacci number would you like? ");
				fib = input.nextInt();
			}
			else
			{
				System.out.println("That number is too high.  Please try again.");

				System.out.print("Which Fibonacci number would you like? ");
				fib = input.nextInt();
			}
		}

		if ( fib == 0)
		{
			System.out.print("\nFibonacci #0 is 0\n\n");
		}

		else if ( fib == 1)
		{
			System.out.print("\nFibonacci #1 is 1\n\n");
		}
		
		else 
		{
			System.out.printf("\nFibonacci #%d is %.0f\n\n", fib, fibcalc(fib));
		}
		
	}
/*
Method Name	:fibcalc
Parameters	:x, an integer
Return Value	:The calculated fibanacci number, a double
Parters		:None
Description	:This method calculates a finanacci number given the associated number.
*/
	public static double fibcalc( int x )
	{
		double fibnum = 1;
		double previous = -1;
	
		for (int i = 0; i <= x; i++)
		{
			double sum = fibnum + previous;
			previous = fibnum;
			fibnum = sum;
		}
			
		return fibnum;
	}
	
}


