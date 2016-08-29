/* Name  	:Mike Bynum
   Class 	:1400-02
   Program #	:Program
   Due Date	:

   Honor Pledge	:On my honor as a student of the University of Nebraska at Omaha, I have neither given nor received unauthorized help on this homework assignment.

Name	:Mike Bynum
NUID	:343
Email	:mbynum@unomaha.edu

Partners: None

Description of program*/

import java.util.Scanner;

public class problem2_02

{
	public static void main ( String arg [] )
	{
		int x;

		Scanner input = new Scanner (System.in);

		System.out.print("Please enter an integer value for x: " );
		x=input.nextInt();

		int y = (x*x) + (3*x) + 2;

		System.out.printf("Given the value %d for x, y = %d in our function.\n", x, y);
	}
}


