/* Name  	:Mike Bynum
   Class 	:1400-02
   Program #	:Program 17
   Due Date	:4-23-10

   Honor Pledge	:On my honor as a student of the University of Nebraska at Omaha, I have neither given nor received unauthorized help on this homework assignment.

Name	:Mike Bynum
NUID	:343
Email	:mbynum@unomaha.edu

Partners: None

Description of program: This program will ask a user to input a user defined amount of numbers and will output whether or not they are perfect numbers (if they are it will output the factors as well).
*/

import java.util.Scanner;

public class mbynum_Perfect

{

	public static void main ( String arg [] )
	{
		int test;
		int perfectNumber;

		Scanner input = new Scanner (System.in);
		
		System.out.println();
	
		do
		{
			System.out.print("How many numbers would you like to test? ");
			test = input.nextInt();
		}
		while (	test < 1);

		for (int x = 0; x < test; x++)
		{
			System.out.println();
	
			System.out.print("Please enter a possible perfect number: ");
			perfectNumber = input.nextInt();

			if (testPerfect(perfectNumber))
			{
				System.out.printf("%d:",perfectNumber);
				printFactors(perfectNumber);
			}
			else
			{
				System.out.printf("%d:NOT PERFECT\n",perfectNumber);
			}
			
		}

		System.out.println();
	}

/*
Method Name	:testPerfect
Parameters	:testNum, an integer
Return Value	:whether or not a number is perfect, a boolean
Parters		:none
Description	:This method will test whether or not a number is perfect and will return either a true or false.
*/

	public static boolean testPerfect(int testNum)
	{
		int sumSoFar = 0;
		int sumOfDivisors;
		boolean perfect = true;
	
		for ( int i=1; i<testNum; i++ )
		{
			if (testNum % i == 0)
			{
				sumSoFar = sumSoFar + i;
			}
		}

		sumOfDivisors = sumSoFar;

		if (testNum == sumOfDivisors && testNum != 0)
		{
			return perfect;
		}
		else
		{
			perfect = false;
			return perfect;
		}

	
	}

/*
Method Name	:printFactors
Parameters	:factor, and interger
Return Value	:none
Partners	:none
Description	:This method will output the factors of a perfect number.
*/
	public static void printFactors(int factor)
	{

		for (int y = factor - 1 ; y > 0; y--)
		{
			if (factor % y == 0)
			{
				System.out.printf("%d ", y);
			}
			
		} 
			System.out.println();
	}

}


