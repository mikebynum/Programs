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

public class problem7_05

{
	public static void main ( String arg [] )
	{
		int [] first = new int [10];
		int [] second = new int [10];
		boolean diff=true;
		int sort;
		int swap;

		System.out.print("Please enter first set of 10 integers\n");
		Scanner input = new Scanner (System.in);

		for (int a = 0; a<10; a++)
		{
			first[a] = input.nextInt();
		}

	
		System.out.println();
	
		System.out.print("Please enter second set of 10 integers\n");

		for (int b = 0; b<10; b++)
		{
			second[b] = input.nextInt();
		}

		for (int pass = 0; pass < first.length-1; pass++)
		{
			for (int pos = 0; pos < first.length - 1; pos++)
			{
				if ( first[pos] > first[pos+1] )
				{
					int temp = first[pos];
					first[pos] = first[pos+1];
					first[pos+1] = temp;
				}
			}
		}


		for (int pass = 0; pass < second.length-1; pass++)
		{
			for (int pos = 0; pos < second.length - 1; pos++)
			{
				if ( second[pos] > second[pos+1] )
				{
					int temp = second[pos];
					second[pos] = second[pos+1];
					second[pos+1] = temp;
				}
			}
		}

		for (int x = 0; x < 10; x++)
		{
			if (first[x] != second[x])	
			{
				diff = false;
			}
		}

		System.out.print("First set: ");

		for (int firstone : first)		
		{
			System.out.print(firstone + " ");	
		}

		System.out.println();

		System.out.print ("Second set: ");
	
		for (int secondone : second)
		{
			System.out.print(secondone + " ");
		}
		if (diff)
			System.out.print("\nThe two sets are the same\n");
		else
			System.out.print("\nThe two sets are not the same\n");
	}
}


