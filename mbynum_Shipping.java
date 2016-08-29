/* Name  	:Mike Bynum
   Class 	:1400-02
   Program #	:Program 10
   Due Date	:3-2-10

   Honor Pledge	:On my honor as a student of the University of Nebraska at Omaha, I have neither given nor received unauthorized help on this homework assignment.

Name	:Mike Bynum
NUID	:343
Email	:mbynum@unomaha.edu

Partners: None

Description of program: This program will ask the user to input a package shipping distance and its weight.  It will then calculate the cost the cost per mile as well as its total cost. 
*/
import java.util.Scanner;

public class mbynum_Shipping

{
	public static void main ( String arg [] )
	{
		Scanner input = new Scanner (System.in);
		int distance;
		int mod_distance;
		int weight;
		double rate=0.00;
		double total=0.00;

		System.out.println();

		System.out.print("How far will you be shipping the package in miles? ");
		distance=input.nextInt();

		while (distance < 1)
		{
			System.out.print("That is not a valid distance, try again.\n");
			System.out.print("How far will you be shipping the package in miles? ");
			distance = input.nextInt();
		}

		System.out.println();

		System.out.print("How heavy is your package in pounds (1-65)? ");
		weight = input.nextInt();

		while (weight < 1 || weight > 65)
		{
			System.out.print("That is not a valid weight, try again.\n");
			System.out.print("How heavy is your package in pounds (1-65)? ");
			weight = input.nextInt();
		}

		if (weight >= 1 && weight <= 4)
		{
			rate = 2.34;
		}

		if (weight >= 5 && weight <= 12)
		{
			rate = 4.31;
		}

		if (weight >= 13 && weight <= 22)
		{
			rate = 6.01;
		}

		if (weight >= 23 && weight <= 45)
		{
			rate = 6.27;
		}

		if (weight >= 46 && weight <= 65)
		{
			rate = 7.01;
		}

		mod_distance = distance / 25;

		if ( ( distance % 25) > 0)
		{
			++mod_distance;
		}

		total = mod_distance * rate;

		System.out.println();
			
		System.out.printf("The shipping rate for %d pounds is $%.2f per 25 miles.\n",weight,rate);
		System.out.printf("Your total shipping cost for %d miles is $%.2f\n",distance,total);

		System.out.println();
	}
}


