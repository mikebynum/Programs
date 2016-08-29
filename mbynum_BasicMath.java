/* Name  	:Mike Bynum
   Class 	:1400-02
   Program #	:Program 3
   Due Date	:1-29-10

   Honor Pledge	:On my honor as a student of the University of Nebraska at Omaha, I have neither given nor received unauthorized help on this homework assignment.

Name	:Mike Bynum
NUID	:343
Email	:mbynum@unomaha.edu

Partners: None

Description of program: This program will prompt the user for 3 integers and then output answers after doing some basic math
*/

import java.util.Scanner;

public class mbynum_BasicMath

{
	public static void main ( String arg [] )
	{
		Scanner input = new Scanner (System.in);
		int colts;
		int vikings;
		int cyclones;

		System.out.print("\nEnter your first number: ");
		colts=input.nextInt();

		System.out.print("Enter your second number: ");
		vikings = input.nextInt();

		System.out.print("Enter your third number: ");
		cyclones=input.nextInt();
		
		int A=(colts + vikings + cyclones);
		int B=(colts * vikings * cyclones);
		int C=(colts / cyclones);
		int D=(colts % vikings);
		int E=(colts - vikings);

		System.out.printf("ADD:%d\nDIV:%d\nMOD:%d\nPRO:%d\nDIF:%d\n\n", A,B,C,D,E);
		
		
	}
}


