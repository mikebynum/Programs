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

public class problem6_03

{
	public static void main ( String arg [] )
	{
		Scanner input = new Scanner (System.in);
		
		int num;
		char cont;

		do 
		{
			System.out.print ("Enter a number to take the factorial of: ");
			num = input.nextInt();

			factorial(num);

			System.out.print ("Do another factorial (Y,N)? ");
			cont = input.next().charAt(0);

		}while (cont=='Y');
		
	}

	public static void factorial (int num)
	{	
		int num2=1;

		for (int x=1; x <= num; x++)
		{
			num2 = num2 * x;
		}	
		System.out.printf("%d! = %d\n",num,num2); 
	}
}

