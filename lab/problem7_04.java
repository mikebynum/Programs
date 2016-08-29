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

public class problem7_04

{
	public static void main ( String arg [] )
	{
		double [] scores = new double [8];
		double total = 0.0;

		Scanner input = new Scanner (System.in);

		System.out.print("Enter the scores: ");
		for (int a = 0; a < 8; a++)
			scores[a] = input.nextDouble();

		for (int pass = 0; pass < scores.length-1; pass++)
		{
			for (int pos = 0; pos < scores.length-1; pos++)
			{
				if (scores[pos] > scores[pos+1])
				{
					double temp = scores[pos];
					scores[pos]=scores[pos+1];
					scores[pos+1] = temp;
				}
			}
		}

		System.out.print("The scores in sorted order are as follows:\n");

		for (double printscore : scores)
			System.out.print(printscore + " ");

		System.out.println();

		for (int a = 1; a < 7; a++)
		{
			total += scores[a];
		}

		System.out.print("The final score received is " + total + "\n");

	}
}


