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

public class problem7_02

{
	public static void main ( String arg [] )
	{
		int [] value = new int [10];

		int smallest;
		int largest;
		double average=0;
		double total=0;

		Scanner input = new Scanner (System.in);

		System.out.print("Please enter 10 integers: ");

		for ( int x = 0; x < value.length; x++)
			value[x] = input.nextInt();
		
		smallest = value[0];
		largest = value[0];

		for (int x = 0; x < value.length; x++)
		{
			if (value[x] < smallest)
			{
				smallest = value[x];
			}
			if (value[x] > largest)
			{
				largest = value[x];
			}
			
			total += value[x];
		}

		average = total / 10;

		System.out.printf("The smallest number is: %d\n", smallest);

		System.out.printf("The largest number is: %d\n", largest);
	
		System.out.printf("The average number is: %f\n", average);
	}
}


