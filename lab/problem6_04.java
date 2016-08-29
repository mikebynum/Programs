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

public class problem6_04

{
	public static void main ( String arg [] )
	{	
		Scanner input = new Scanner (System.in);

		int num;
		int num1;
		int num2;
		int num3;
		int num4;
		int num5;
			
		System.out.print("Enter a five digit number: ");
		num = input.nextInt();

		reverse(num);
	}

	public static void reverse (int num)
	{
		int num1;
		int num2;
		int num3;
		int num4;
		int num5;
		
		num1 = num / 10000;
		num2 = num / 1000 % 10;
		num3 = num / 100 % 10;
		num4 = num / 10 % 10;
		num5 = num % 10;

		System.out.print("The number with its digits reversed is "+ num5 + num4 + num3 +num2 + num1+ "\n");
	}
}


