/* Name  	:Mike Bynum
   Class 	:1400-02
   Program #	:Program 9
   Due Date	:2-23-10

   Honor Pledge	:On my honor as a student of the University of Nebraska at Omaha, I have neither given nor received unauthorized help on this homework assignment.

Name	:Mike Bynum
NUID	:343
Email	:mbynum@unomaha.edu

Partners: None

Description of program: This program will allow a user to enter a character between a(A) and z(Z) and will out put the 1337 equivalent.
*/

import java.util.Scanner;

public class mbynum_Leet

{
	public static void main ( String arg [] )
	{
		char leet;

		Scanner input = new Scanner (System.in);

		System.out.println();
		System.out.print("Enter character to convert: ");
		leet = input.next().charAt(0);

		switch(leet)
		{
			case 'a': case 'A':
				System.out.printf("%c 4\n",leet);
				break;
			case 'b': case 'B':
				System.out.printf("%c I3\n",leet);
				break;
			case 'c': case 'C':
				System.out.printf("%c [\n",leet);
				break;
			case 'd': case 'D':
				System.out.printf("%c )\n",leet);
				break;
			case 'e': case 'E':
				System.out.printf("%c 3\n",leet);
				break;
			case 'f': case 'F':
				System.out.printf("%c |=\n",leet);
				break;
			case 'g': case 'G':
				System.out.printf("%c &\n",leet);
				break;
			case 'h': case 'H':
				System.out.printf("%c #\n",leet);
				break;
			case 'i': case 'I':
				System.out.printf("%c 1\n",leet);
				break;
			case 'j': case 'J':
				System.out.printf("%c J\n",leet);
				break;
			case 'k': case 'K':
				System.out.printf("%c >|\n",leet);
				break;
			case 'l': case 'L':
				System.out.printf("%c 1\n",leet);
				break;
			case 'm': case 'M':
				System.out.printf("%c /\\ /\\ \n",leet);
				break;
			case 'n': case 'N':
				System.out.printf("%c ^/\n",leet);
				break;
			case 'o': case 'O':
				System.out.printf("%c 0\n",leet);
				break;
			case 'p': case 'P':
				System.out.printf("%c |*\n",leet);
				break;
			case 'q': case 'Q':
				System.out.printf("%c Q\n",leet);
				break;
			case 'r': case 'R':
				System.out.printf("%c I2\n",leet);
				break;
			case 's': case 'S':
				System.out.printf("%c 5\n",leet);
				break;
			case 't': case 'T':
				System.out.printf("%c 7\n",leet);
				break;
			case 'u': case 'U':
				System.out.printf("%c (_)\n",leet);
				break;
			case 'v': case 'V':
				System.out.printf("%c \\/\n",leet);
				break;
			case 'w': case 'W':
				System.out.printf("%c \\/\\/\n",leet);
				break;
			case 'x': case 'X':
				System.out.printf("%c ><\n",leet);
				break;
			case 'y': case 'Y':
				System.out.printf("%c Y\n",leet);
				break;
			case 'z': case 'Z':
				System.out.printf("%c 2\n",leet);
				break;
			
			default:
				System.out.printf("%c -\n",leet);
		}

	}
}


