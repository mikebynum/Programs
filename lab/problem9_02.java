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

public class problem9_02

{
	public static void main ( String arg [] )
	{
		student s = new student();
		welcome ();
		s.registerStudent();
		s.calculateFee();
		s.printStudent();
	}

	private static void welcome()
	{
		System.out.println("Current Rates: ");
		System.out.println("In-state tuition per credit hour: $150");
		System.out.println("Out of state tuition per credit hour: $300");
		System.out.println();
	}
}


