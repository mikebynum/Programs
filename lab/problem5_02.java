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

public class problem5_02

{
	public static void main ( String arg [] )
	{
		int sides;
		char format;

		Scanner input = new Scanner (System.in);

		System.out.println("\tBasic Geometry with Java");
		System.out.println("\t------------------------");

		System.out.print("Enter the number of sides of your polygon (3-10): ");
		sides = input.nextInt();

		System.out.println("Display Options:");
		System.out.println("\t- Type R for radians");
		System.out.println("\t- Type anything else for degrees");
		format = input.next().charAt(0);

		if (format=='R')
		{
			System.out.printf("The sum of interior angles in your polygon is: %dPi Radians\n", sides-2);
		}
		else
			System.out.printf("The sum of interior angles in your polygon is: %d Degrees\n",(sides-2)*180);
		

		switch ( sides )
		{
			case 3:
				System.out.printf("A polygon with %d sides is called a triangle or trigon\n",sides);
				break;
			case 4:
				System.out.printf("A polygon with %d sides is called a quadrilateral or a tetragon\n",sides);
				break;
			case 5:
				System.out.printf("A polygon with %d sides is called a pentagon\n",sides);
				break;
			case 6:
				System.out.printf("A polygon with %d sides is called a hexagon\n",sides);
				break;
			case 7:
				System.out.printf("A polygon with %d sides is called a heptagon\n",sides);
			case 8:
				System.out.printf("A polygon with %d sides is called an octagon\n",sides);
			case 9:
				System.out.printf("A polygon with %d sides is called a nonagon\n",sides);
			case 10:
				System.out.printf("A polygon with %d sides is called a decagon\n",sides);
				
		default:
			System.out.println("BAD NUMBER!");
		}
	}
}


