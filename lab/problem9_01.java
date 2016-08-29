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

public class problem9_01

{
	public static void main ( String arg [] )
	{
		pool newpool = new pool ();

		System.out.print("Welcome to the PKI Pool!\n");

		System.out.print("Please enter the width and length of the pool:\n");
		Scanner input = new Scanner (System.in);
		int width = input.nextInt();
		int length = input.nextInt();

		newpool.setSize(width,length);

		System.out.print("Your pool will be ");
		newpool.printSize();

		System.out.println();

		System.out.print("Would you like to print a layout of the pool?\n");
		System.out.print("(yes = 1, no = 0)");
		int answer = input.nextInt();
	
		if (answer == 1)
			newpool.printPoolLayout();
				
	}
}


