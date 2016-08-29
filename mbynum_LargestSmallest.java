/* Name  	:Mike Bynum
   Class 	:1400-02
   Program #	:Program 5
   Due Date	:2-9-10

   Honor Pledge	:On my honor as a student of the University of Nebraska at Omaha, I have neither given nor received unauthorized help on this homework assignment.

Name	:Mike Bynum
NUID	:343
Email	:mbynum@unomaha.edu

Partners: None

Description of program: This program will have a user input 6 numbers and output the largest and smallest.
*/

import java.util.Scanner;

public class mbynum_LargestSmallest

{
	public static void main ( String arg [] )
	{
		int largest=0, smallest=0, first, second, third, fourth, fifth, sixth;

		Scanner in = new Scanner(System.in);

		System.out.print("\nPlease enter first integer: ");
		first = in.nextInt();
		smallest = first;

		System.out.print("Please enter second integer: ");
		second = in.nextInt();
		if (second > smallest)
		{
			largest = second;
		}
		if (second < smallest)
		{
			smallest = second;
			largest = first;
		}

		
		System.out.print("Please enter third integer: ");
		third = in.nextInt();
		if (third > largest)
		{
			largest = third;
		}
		if (third < smallest)
		{
			smallest = third;
		}

		System.out.print("Please enter fourth integer: ");
		fourth = in.nextInt();
		if (fourth > largest)
		{
			largest = fourth;
		}
		if (fourth < smallest)
		{
			smallest = fourth;
		}

		System.out.print("Please enter fifth integer: ");
		fifth = in.nextInt();
		if (fifth > largest)
		{
			largest = fifth;
		}
		if (fifth < smallest)
		{
			smallest = fifth;
		}

		System.out.print("Please enter sixth integer: ");
		sixth=in.nextInt();
		if (sixth > largest)
		{
			largest = sixth;
		}
		if (sixth < smallest)
		{
			smallest = sixth;
		}

		System.out.println();

		System.out.println (largest +":"+ smallest);
		
		System.out.println();
	}
}


