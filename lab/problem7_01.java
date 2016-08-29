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

public class problem7_01

{
	public static void main ( String arg [] )
	{
		int [] array = new int [10];

		Scanner input = new Scanner (System.in);

		System.out.print("Please enter 10 integers: ");
		for (int x =0; x<10; x++)
			array[x]=input.nextInt();

		System.out.println();

		System.out.println("The values multiplied by 2 or 5 are as follows:");

		for (int x = 0; x<10; x++)
		{
			if (array[x] < 10)
			{
				array[x]=5*array[x];
			}
			else
			{
				array[x] =2*array[x];
			}
			
			System.out.print(array[x]+" ");
		}
		System.out.println();
	}
}


