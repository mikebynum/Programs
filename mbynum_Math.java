/* Name  	:Mike Bynum
   Class 	:1400-02
   Program #	:Program 13
   Due Date	:3-25-10

   Honor Pledge	:On my honor as a student of the University of Nebraska at Omaha, I have neither given nor received unauthorized help on this homework assignment.

Name	:Mike Bynum
NUID	:343
Email	:mbynum@unomaha.edu

Partners: None

Description of program: This program will read in a value from the user and output data using various math arguments.
*/

import java.util.Scanner;

public class mbynum_Math

{
	public static void main ( String arg [] )
	{
	Scanner input = new Scanner (System.in);	

	double num;

	System.out.print ("\nPlease enter a number: ");
	num = input.nextDouble();

	System.out.println();

	System.out.printf("Math.ceil(%.3f) = %.3f\n",num,Math.ceil(num));
	System.out.printf("Math.abs(%.3f) = %.3f\n",num,Math.abs(num));
	System.out.printf("Math.floor(%.3f) = %.3f\n",num,Math.floor(num));
	System.out.printf("Math.log(%.3f) = %.3f\n",num,Math.log(num));
	System.out.printf("Math.pow(%.3f,%.3f) = %.3f\n",num,num,Math.pow(num,num));
	System.out.printf("Math.sqrt(%.3f) = %.3f\n\n",num,Math.sqrt(num));
	}

}


