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
import java.util.Random;

public class problem6_02

{
	public static void main ( String arg [] )
	{
		Random rand = new Random();		
		
		Scanner input = new Scanner (System.in);

		double pi;
		double totalpoints;
		double insidepoints=0;
		double X;
		double Y;

		System.out.print ("How many points should be used in the estimation? ");
		totalpoints = input.nextDouble();

		for (int x = 1; x <= totalpoints; x++)
		{
			X = rand.nextDouble();
			Y = rand.nextDouble();
			
			if (Math.sqrt((X*X)+(Y*Y)) <= 1)
			{
				++insidepoints;
			}
		 }

		pi = (insidepoints / totalpoints) * 4;

		System.out.println("Using a " + totalpoints + " point approximation, Pi was calculated to be " + pi + " ."); 	
	}
}


