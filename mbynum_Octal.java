/* Name  	:Mike Bynum
   Class 	:1400-02
   Program #	:Program 6
   Due Date	:2-12-10

   Honor Pledge	:On my honor as a student of the University of Nebraska at Omaha, I have neither given nor received unauthorized help on this homework assignment.

Name	:Mike Bynum
NUID	:343
Email	:mbynum@unomaha.edu

Partners: None

Description of program: This program will convert decimal (integer) numbers into their octal number equivalents.
*/

import java.util.Scanner;

public class mbynum_Octal

{
	public static void main ( String arg [] )
	{
		Scanner input = new Scanner (System.in);

		int value,A,B,C,D,E,F,G,H;

		System.out.print("Please enter a number between 0 and 32767 to convert: ");
		value = input.nextInt();

		if (value <= 0)
		{
			System.out.println("UNABLE TO CONVERT");
		}

		if (value >= 32767)
		{
			System.out.println("UNABLE TO CONVERT");
		}

		else 
		{
			A = value / 4096;
			B = value % 4096;
			C = B / 512;
			D = B % 512;
			E = D / 64;
			F = D % 64;
			G = F / 8;
			H = F % 8;
		
			System.out.printf("Your integer number %d is %d%d%d%d%d in octal\n", value, A, C, E, G, H);
	
		}

		
		
	}
}


