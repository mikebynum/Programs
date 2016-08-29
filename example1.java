import java.util.Scanner;

public class example1
{
	public static void main (String [] args)
	{
		Scanner name = new Scanner(System.in);

		int x=5;
		int y;
		
		System.out.print ("Enter variable: ");
		y=name.nextInt();

		if (y < 5)
		{
			y = x + 3;
		}		

		if (y >= 10)
		{
			y = x % y;
		}

		System.out.printf ("x is: %d\n",x );
		
		System.out.printf ("y is: %d\n",y);
		
	}
}

		
