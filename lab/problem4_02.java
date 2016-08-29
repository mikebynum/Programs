/* Name  	:Mike Bynum
   Class 	:1400-02
   Program #	:Program
   Due Date	:

   Honor Pledge	:On my honor as a student of the University of Nebraska at Omaha, I have neither given nor received unauthorized help on this homework assignment.

Name	:Mike Bynum
NUID	:343
Email	:mbynum@unomaha.edu

Partners: None

Description of program
*/
import java.util.Scanner;

public class problem4_02

{
	public static void main ( String arg [] )
	{
		Scanner input = new Scanner (System.in);

		int a=0,b=0;

		System.out.println("Please enter the number of CDs to be purchased.");
		a=input.nextInt();

		if (a < 4)
			b = 15;
		else if ( a <= 12)
			b = 14;
		else if ( a <= 18)
			b = 12;
		else if ( a <= 26)
			b = 10;
		else if ( a >= 27)
			b = 8;

		System.out.printf("The cost per CD is $%d.\n",b);

		System.out.printf("The total cost for %d CDs is $%d.\n", a,b * a);

		System.out.println("Thank you for your business.");
	}
}


