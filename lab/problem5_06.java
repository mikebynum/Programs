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

public class problem5_06

{
	public static void main ( String arg [] )
	{
		char roman='a';
		int decimal=0;

		Scanner input = new Scanner (System.in);
	
		System.out.println("*** Roman Numeral Calculator ***");

		System.out.print("Enter the Roman Numeral: ");

		while (roman != '0')
		{
			roman = input.next().charAt(0);
			
			switch (roman)
			{
				case 'I': case 'i':
				decimal += 1;	
				break;
				case 'V': case 'v':
				decimal += 5;
				break;
				case 'X': case 'x':
				decimal += 10;
				break;
				case 'L': case 'l':
				decimal += 50;
				break;
				case 'C': case 'c':
				decimal += 100;
				break;
				case 'D': case 'd':
				decimal += 500;
				break;
				case 'M': case 'm':
				decimal += 1000;
				break;
				case '0':
				break;
				default: System.out.printf("Error! %c is not a Roman numeral!!\n",roman);
			} 
		}
		System.out.printf("The decimal number is %d\n",decimal);	
	}
}


