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

public class problem5_01

{
	public static void main ( String arg [] )
	{
		Scanner input = new Scanner (System.in);

		char letter;

		System.out.print("Please enter a letter to determine if it is a vowel: ");
		letter = input.next().charAt(0);
		
		switch (letter)
		{
			case 'a': case 'A':
			case 'e': case 'E':
			case 'i': case 'I':
			case 'o': case 'O':
			case 'u': case 'U':
			System.out.println(letter + " is a vowel");		
			break;
		default:
			System.out.println(letter + " is a consonant"); 
		}
	}

}


