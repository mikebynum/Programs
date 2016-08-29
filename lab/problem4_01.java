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

public class problem4_01

{
	public static void main ( String arg [] )
	{
		Scanner input = new Scanner (System.in);

		int a=0, b=0, c=0;

		System.out.print("Enter a number: ");
		a=input.nextInt();

		System.out.print("Enter a second number: ");
		b=input.nextInt();

		System.out.print("Enter a third number: ");
		c=input.nextInt();

		if (a + b == c)
			System.out.printf("%d + %d = %d\n", a,b,c);
		if (a + c == b)
			System.out.printf("%d + %d = %d\n", a,c,b);
		if (b + c == a)
			System.out.printf("%d + %d = %d\n", b,c,a);

	}
}


