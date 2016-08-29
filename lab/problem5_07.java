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

public class problem5_07

{
	public static void main ( String arg [] )
	{
		int row;
		int col;

		Scanner input = new Scanner (System.in);

		System.out.print("Enter number of columns: ");
		col = input.nextInt();

		System.out.print("Enter number of rows: ");
		row = input.nextInt();

		System.out.println("Here's your multiplication table");

		System.out.println("\n");

		System.out.print("\t\t");

		for (int x=1; x <= col; x++)
		{
			System.out.print(x + "\t");
		}
	
		System.out.print("\n");

		System.out.print("------------------------------------\n");

		for (int x=1; x<=row; x++)
		{
			System.out.print(x+"|\t\t");

			for (int y=1; y<= col; y++)
			{
				System.out.printf("%d\t",x*y);
			}
			
			System.out.print("\n");
		}
	}
}


