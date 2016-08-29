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

public class problem5_04

{
	public static void main ( String arg [] )
	{
		int num;
		boolean a=true;

		Scanner input = new Scanner (System.in);
		
		System.out.print("Enter a number to determine whether it is prime: ");
		num = input.nextInt();

		for (int x = 2; x < num; x++)
		{
			if (num % x == 0)
			{
				a=false;
				break;
			}

		}
		if (a)
			System.out.println(num + " is prime");

		else
			System.out.println(num + " is NOT prime");
	}
}


