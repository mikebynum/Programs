/* Name  	:Mike Bynum
   Class 	:1400-02
   Program #	:Program 18
   Due Date	:4-30-10

   Honor Pledge	:On my honor as a student of the University of Nebraska at Omaha, I have neither given nor received unauthorized help on this homework assignment.

Name	:Mike Bynum
NUID	:343
Email	:mbynum@unomaha.edu

Partners: None

Description of program: This program will ask a user for a lower and upper bound and will display the sexy pairs.
*/
import java.util.Scanner;

public class mbynum_Sieve

{
	private boolean [] primes = new boolean [50001];
	private int upper;
	private int lower;

/*
Method Name	:mbynum_Sieve
Parameters	:None
Return Values	:None
Partners	:None
Description	:This constructor will set initial values for our variables and for the array (as either true or false).
*/
	public mbynum_Sieve()
	{
		primes[0] = false;
		primes[1] = false;
		lower = 1;
		upper = 50000;

		for (int i = 2; i < primes.length; i++)
		{
			primes[i] = true;
		}
	}
/*
Method Name	:processSieve
Parameters	:None
Return Values	:None
Partners	:None
Description	:This method will implement the Sieve of Eratosthenes algorithm.
*/
	public void processSieve()
	{
		for (int k = 2; (k*k)<=upper; k++)
		{
			for (int j=(k*k); j<=upper; j = j+k)
			{
				primes[j] = false;
			}
		}
	}
/*
Method Name	:getBoundaries	
Parameters	:None
Return Values	:None
Partners	:None
Description	:This method will ask the user for the upper and lower bondaries to process and will then conduct data validation on the numbers.
*/
	public void getBoundaries()
	{
		Scanner input = new Scanner (System.in);

		System.out.println("\nPlease enter a lower boundary and an upper boundary and I will print all of the sexy prime pairs between those bondaries.\n");

		do 
		{
			System.out.print("Please enter the lower boundary (between 1 and 50000): ");
			lower = input.nextInt();

			while ((lower < 1) || (lower > 50000))
			{
				System.out.print("Please enter the lower bondary (between 1 and 50000): ");
				lower = input.nextInt();
			}

			System.out.print("Please enter the upper boundary (between 1 and 50000): ");
			upper = input.nextInt();

			while ((upper < 1) || (upper > 50000))
			{
				System.out.print("Please enter the upper boundary (between 1 and 50000): ");
				upper = input.nextInt();
			}

			if (lower >= upper)
			{
				System.out.println("Your upper boundary cannot be smaller than your lower");
			}	

		}
		while (lower >= upper);

		System.out.println();
	}
/*
Method Name	:showPrimes	
Parameters	:None
Return Values	:None
Partners	:None
Description	:This method uses the array to display sexy prime pairs, one per line between the lower and upper boundaries.
*/
	public void showPrimes()
	{
		System.out.printf("Here are all of your sexy prime pairs in the range of %d and %d, one pair per line:\n\n", lower, upper);

		int total=0;

		for (int i=lower; i+6 < upper; i++)
		{
			if (primes[i] == true && primes[i+6] == true)
			{
				System.out.printf("%d and %d\n",i,i+6);
				total++;
			}

		}

		System.out.printf("\nThere were %d sexy prime pairs displayed betweem %d and %d\n\n", total,lower,upper);
	}
		

	
	
	
}


