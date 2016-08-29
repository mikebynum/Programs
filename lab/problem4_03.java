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

public class problem4_03

{
	public static void main ( String arg [] )
	{
		char user;
		int upper=0;
		int lower=0;
		int digits=0;
		int characters=0;
	
		Scanner input = new Scanner (System.in);
		
		System.out.print ("Please enter a character, # to stop ");
		user=input.next().charAt(0);

			if (user =='Q' ||user=='W' ||user=='E' ||user=='R' ||user== 'T' ||user== 'Y' ||user== 'U' ||user== 'I' ||user== 'O' ||user=='P' ||user== 'A' ||user== 'S' ||user== 'D' ||user== 'F' ||user== 'G' ||user== 'H' ||user== 'J' ||user== 'K' ||user== 'L' ||user== 'Z' ||user== 'X' ||user== 'C' ||user=='V' ||user== 'B' ||user== 'N' ||user== 'M')
				++upper;
			else if (user =='q' ||user=='w' ||user=='e' ||user=='r' ||user== 't' ||user== 'y' ||user== 'u' ||user== 'i' ||user== 'o' ||user=='p' ||user== 'a' ||user== 's' ||user== 'd' ||user== 'f' ||user== 'g' ||user== 'h' ||user== 'j' ||user== 'k' ||user== 'l' ||user== 'z' ||user== 'x' ||user== 'c' ||user=='v' ||user== 'b' ||user== 'n' ||user== 'm')
				++lower;
			else if (user == '1' ||user== '2' || user=='3' || user=='4' || user=='5' || user=='6' || user=='7' || user=='8' || user=='9' || user=='0')
				++digits;
			else
				++characters;
		
		
		while (user != '#')
		{
			System.out.print("Please enter another character, # to stop ");
			user=input.next().charAt(0);

			if (user =='Q' ||user=='W' ||user=='E' ||user=='R' ||user== 'T' ||user== 'Y' ||user== 'U' ||user== 'I' ||user== 'O' ||user=='P' ||user== 'A' ||user== 'S' ||user== 'D' ||user== 'F' ||user== 'G' ||user== 'H' ||user== 'J' ||user== 'K' ||user== 'L' ||user== 'Z' ||user== 'X' ||user== 'C' ||user=='V' ||user== 'B' ||user== 'N' ||user== 'M')
				++upper;
			else if (user =='q' ||user=='w' ||user=='e' ||user=='r' ||user== 't' ||user== 'y' ||user== 'u' ||user== 'i' ||user== 'o' ||user=='p' ||user== 'a' ||user== 's' ||user== 'd' ||user== 'f' ||user== 'g' ||user== 'h' ||user== 'j' ||user== 'k' ||user== 'l' ||user== 'z' ||user== 'x' ||user== 'c' ||user=='v' ||user== 'b' ||user== 'n' ||user== 'm')
				++lower;
			else if (user == '1' ||user== '2' || user=='3' || user=='4' || user=='5' || user=='6' || user=='7' || user=='8' || user=='9' || user=='0')
				++digits;
			else
				++characters;
		
		}
		System.out.println("The number of upper case letters: " + upper);
		System.out.println("The number of lower case letters: " + lower);
		System.out.println("The number of digits: " + digits);
		System.out.println("The number of other characters: " + --characters);	
		
	}
}


