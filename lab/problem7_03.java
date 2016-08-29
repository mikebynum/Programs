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

public class problem7_03

{
	public static void main ( String arg [] )
	{
		int [][] seating = {{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0}};
		int choice;
	
		Scanner input = new Scanner (System.in);

		do
		{
		System.out.println("PKI Airlines Menu");

		System.out.println("1.	Reserve a seat in row 1");
		System.out.println("2.	Reserve a seat in row 2");
		System.out.println("3.	Reserve a seat in row 3");
		System.out.println("4.	Print seathing chart");
		System.out.println("5.	Quit");

		System.out.print("Enter your choice: ");
		choice = input.nextInt();

		switch (choice)
		{
			case 1: case 2: case 3:
			{
				for (int seat=1; seat<=4; seat++)
				{
					if (seating[choice][seat] == 0)
					{
						seating[choice][seat]=1;
						System.out.printf("You will be flying in row %d, seat %d\n",choice,seat);
						break;
					}
					else if (seat==4)
						System.out.print("There are no seats available\n");
				}
				break;
			}
			case 4:
			{
				for (int row=1; row<4; row++)
				{
					System.out.printf("Row %d: ",row);	

					for (int col=1; col<5; col++)
					{
						if(seating[row][col]==0)
						{
							System.out.print("Avail  ");
						}
						else
							System.out.print("Taken  ");

					}
					System.out.println();
				}
				break;
			}
		}
		}while (choice != 5);
	}
}


