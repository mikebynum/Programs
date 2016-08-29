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

public class problem6_01

{
	public static void main ( String arg [] )
	{
		double num;
		double den;

		Scanner input = new Scanner (System.in);

		System.out.print ("Please enter the numerator: ");
		num = input.nextDouble();

		System.out.print ("Please enter the denominator: ");
		den = input.nextDouble();

		System.out.println("Your fraction in decimal form is " + num/den);
	}
}


