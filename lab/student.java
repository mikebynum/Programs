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

public class student

{
	private String firstname;
	private String lastname;
	private double gpa;
	private int fees;
	private int credithours;
	private String instate;
	

	Scanner input = new Scanner (System.in);

	public student()
	{
		gpa = 0.0;
		fees = 0;
		credithours = 12;
		instate = "true";
		firstname = "NA";
		lastname = "NA";
	}
	
	public void registerStudent()
	{
		System.out.print("Please enter student information\n");

		System.out.print("First Name? ");
		firstname = input.next();

		System.out.print("Last Name? ");
		lastname = input.next();

		System.out.print("GPA? ");
		gpa = input.nextDouble();

		System.out.print("In-State? (True/False) ");
		instate = input.next();	

		System.out.print("Number of credit hours? ");
		credithours = input.nextInt();

		System.out.println();
	}

	public void calculateFee()
	{
		if (instate == "true" || instate == "True" || instate == "TRUE")
		{
			fees = 150 * credithours;
		}
		else
		{
			fees = 300 * credithours;
		}
	}

	public void printStudent()
	{
		System.out.print(lastname+", "+firstname+"\n");
		System.out.print("Total number of credit hours: " + credithours + "\n");
		System.out.print("Cumulative GPA: " + gpa + "\n");
		System.out.print("Current Balance Due: " + fees + "\n");
	}
}


