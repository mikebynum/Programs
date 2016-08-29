/* Name  	:Mike Bynum
   Class 	:1400-02
   Program #	:Program 8
   Due Date	:2-20-10

   Honor Pledge	:On my honor as a student of the University of Nebraska at Omaha, I have neither given nor received unauthorized help on this homework assignment.

Name	:Mike Bynum
NUID	:343
Email	:mbynum@unomaha.edu

Partners: None

Description of program: This program will have a user enter a number and then tell the user whether the number is an even or an odd as well as if the number is a multiple of 17.
*/

import java.util.Scanner;

public class mbynum_MultEven
{
	private int number;

	public mbynum_MultEven ()
	{
           	number = 0;
		System.out.println("\nWelcome to the MultEven Object!\n");
	}

	public void input ()
	{

		Scanner input=new Scanner(System.in);
				
		System.out.print("\nPlease enter a non-negative, integer whole value:  ");
		number=input.nextInt();
                System.out.println();
	}

	public void testEven ()
	{

		if (number % 2 == 0)
			{
				System.out.println(number+"=EVEN");
			}

                else
			{
				System.out.println(number+"=ODD");
			}

	}

	public void testMultiple ()
	{
		if (number % 17 == 0)
			{
				System.out.println(number+"=MULTIPLE");
				System.out.println();
			}
		
                else
			{
				System.out.println(number+"=NOT");
				System.out.println();
			}

	}
}


