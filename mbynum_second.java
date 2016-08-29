import java.util.Scanner;

public class mbynum_second
{
	public static void main ( String args [] )
	{
		int dollars;
		int cents = 56;
		String name = "Mike";
		int pennies;

		Scanner sc = new Scanner ( System.in);

		System.out.print ( "Please enter amount of dollars: ");
		dollars = sc.nextInt ();

		System.out.printf ( "There are %dollars ", dollars );
		System.out.printf ( "and %d cents.\n", cents);

		pennies = dollars * 100 + cents;

		System.out.printf ( "There are %d pennies.", pennies);

		System.out.printf ( "%s, your program ran!\n", name);
	}
}

