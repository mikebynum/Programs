/* Name  	:Mike Bynum
   Class 	:1400-02
   Program #	:Program 14
   Due Date	:3-30-10

   Honor Pledge	:On my honor as a student of the University of Nebraska at Omaha, I have neither given nor received unauthorized help on this homework assignment.

Name	:Mike Bynum
NUID	:343
Email	:mbynum@unomaha.edu

Partners: None

Description of program: This program will ask a user to input an octal number of no greater than 8 digits.  It will then convert the number to an decimal.
*/
import java.util.Scanner;

public class mbynum_Decimal

{
	public static void main ( String arg [] )
	{
		int foo;
		Scanner sc = new Scanner (System.in);
		do 
		{
			System.out.print ("Enter up to an 8-digit octal number to be converted: ");
			foo =  sc.nextInt();
		}
		while (foo > 77777777 || foo < 0);


		System.out.printf ("%d:%d\n", foo, convert(foo));
	}
/*
	Method Name	: convert
	Parameters	: octalNumber, an integier
	Return value(s)	: the decimal conversion of an octal number
	Partners	: None
	Description	: this method converts an octal number to a decimal
*/
	public static int convert (int octalNumber)
	{
		int a,b,c,d,e,f,g,h,decNum;

		a = octalNumber / 10000000;
		b = octalNumber	/ 1000000 % 10;
		c = octalNumber / 100000 % 10;
		d = octalNumber / 10000 % 10;
		e = octalNumber / 1000 % 10;
		f = octalNumber / 100 % 10;
		g = octalNumber / 10 % 10;
		h = octalNumber % 10;

		a = a * (int)Math.pow(8,7);
		b = b * (int)Math.pow(8,6);
		c = c * (int)Math.pow(8,5);
		d = d * (int)Math.pow(8,4);
		e = e * (int)Math.pow(8,3);
		f = f * (int)Math.pow(8,2);
		g = g * (int)Math.pow(8,1);
		h = h * (int)Math.pow(8,0);

		decNum = a + b + c + d + e + f + g + h;

		return decNum;
	}
		
}



