/* Name  	:Mike Bynum
   Class 	:1400-02
   Program #	:Program
   Due Date	:1-23-10

   Honor Pledge	:On my honor as a student of the University of Nebraska at Omaha, I have neither given nor received unauthorized help on this homework assignment.

Name	:Mike Bynum
NUID	:343
Email	:mbynum@unomaha.edu

Partners: None

Description of program: Given two numbers this program will tell whether they are equal, or greater/lesser than each other. */

import java.util.Scanner;

public class mbynum_PunchRun
{
	public static void main ( String args[] )
	{
		Scanner input = new Scanner ( System.in );
		int number1, number2;

		System.out.print ( "Enter first integer: ");
		number1 = input.nextInt ();

		System.out.print ( "Enter second integer: ");
		number2 = input.nextInt ();

		if (number1 == number2)
			System.out.printf ( "%d == %d\n", number1, number2);

		if (number1 != number2)
			System.out.printf ( "%d != %d\n", number1, number2);

		if (number1 < number2)
			System.out.printf ("%d < %d\n", number1, number2);

		if (number1 <= number2)
			System.out.printf ("%d <= %d\n", number1, number2);

		if (number1 > number2)
			System.out.printf ("%d > %d\n", number1, number2);
	
		if (number1 >= number2)
			System.out.printf ("%d >= %d\n", number1, number2);

	
	}
}


