/* Name  	:Mike Bynum
   Class 	:1400-02
   Program #	:Program 4
   Due Date	:2-1-10

   Honor Pledge	:On my honor as a student of the University of Nebraska at Omaha, I have neither given nor received unauthorized help on this homework assignment.

Name	:Mike Bynum
NUID	:343
Email	:mbynum@unomaha.edu

Partners: None

Description of program: This program will have a user enter a number and then tell the user whether the number is an even or an odd as well as if the number is a multiple of 17.
*/

import java.util.Scanner;

public class mbynum_Multiple

{
	public static void main ( String arg [] )
	{

		Scanner input=new Scanner(System.in);
		
		int A;
		
		
		System.out.print("\nPlease enter a non-negative (>=0) integer (whole) number:  ");
		A=input.nextInt();

		String B=null;
		String C=null;

		if (A % 2 == 0)
			{
				B ="EVEN";
			}

		if (A % 2 > 0)
			{
				B ="ODD";
			}

		if (A % 17 == 0)
			{
				C ="MULTIPLE";
			}
		
		if (A % 17 > 0)
			{
				C ="NOT";
			}



		System.out.printf("\n%d:%s:%s\n\n", A, C, B);
	}
}


