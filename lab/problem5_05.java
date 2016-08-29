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

public class problem5_05

{
	public static void main ( String arg [] )
	{
		int students;
		int days;
		double pay;
		int hours=0;
		double paid;
		int total=0;
		int totalhours=0;
		int studenthours;

		Scanner input = new Scanner (System.in);

		
		System.out.println();
		System.out.print("How many students worked this week?  ");
		students = input.nextInt();

		System.out.print("How many days were in this work week?  ");
		days = input.nextInt();

		for (int x=1; x <= students; x++)
		{
			studenthours=0;
			System.out.print("What is the payrate for student " + x + "? ");
			pay = input.nextDouble();

			for (int y=1; y <= days; y++)
			{
				System.out.print("How many hours did student " + x + " work on day " + y + "? ");
				hours = input.nextInt();
				studenthours += hours;
			}
			
			totalhours += studenthours;
			System.out.println();
			paid = pay * studenthours;
			System.out.printf("Student " + x + " worked " + studenthours +" hours for " + "$%.2f\n", paid);
			total += paid;
		}

		System.out.printf("There were %d student workers this week for a total of %d hours.\n", students,totalhours);
		System.out.printf("They were paid a total of $%.2f.\n", total);
		System.out.println();
	}
}


