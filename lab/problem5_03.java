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

public class problem5_03

{
	public static void main ( String arg [] )
	{
		int num;
		int guess;
		int attempts=0;
		int dig1;
		int dig2;
		int dig3;
		int dig1g;
		int dig2g;
		int dig3g;

		Scanner input=new Scanner (System.in);

		do
		{
			System.out.print("Enter a 3-digit number to have the other player guess: ");
			num = input.nextInt();
		}
		while ( num < 100 || num > 999);

		dig1 = num / 100; /* 965/100 = 9 */
		dig2 = num / 10 % 10;
		dig3 = num % 10; 
		
		System.out.print ("Here's what you have so far: ???\n");

		do
		{
			System.out.print ("Enter your guess: ");
			guess = input.nextInt();

			dig1g = guess / 100;
			dig2g = guess / 10 % 10;
			dig3g = guess % 10;

			++attempts;

			if (dig1 != dig1g && dig2 != dig2g && dig3 != dig3g)
				System.out.print ("Here's what you have so far: ???\n");
			
			else if (dig1 == dig1g && dig2 != dig2g && dig3 != dig3g)
				System.out.printf("Here's what you have so far: %d??\n",dig1);

			else if (dig1 != dig1g && dig2 == dig2g && dig3 != dig3g)
				System.out.printf("Here's what you have so far: ?%d?\n",dig2);
		
			
			else if (dig1 != dig1g && dig2 != dig2g && dig3 == dig3g)
				System.out.printf("Here's what you have so far: ??%d\n",dig3);
			

			else if (dig1 == dig1g && dig2 == dig2g && dig3 != dig3g)
				System.out.printf("Here's what you have so far: %d%d?\n",dig1,dig2);
		

			else if (dig1 != dig1g && dig2 == dig2g && dig3 == dig3g)
				System.out.printf("Here's what you have so far: ?%d%d\n",dig2,dig3);

			
			else if (dig1 == dig1g && dig2 != dig2g && dig3 == dig3g)
				System.out.printf("Here's what you have so far: %d?%d\n",dig1,dig3);


			else 
				System.out.printf("Here's what you have so far: %d%d%d\n",dig1,dig2,dig3);
		}
		while ( num != guess);
	
		System.out.printf("\nYou got the number right in %d attempts!\n",attempts);
		

	}
}


