/* Name  	:Mike Bynum
   Class 	:1400-02
   Program #	:Program 12
   Due Date	:3-13-10

   Honor Pledge	:On my honor as a student of the University of Nebraska at Omaha, I have neither given nor received unauthorized help on this homework assignment.

Name	:Mike Bynum
NUID	:343
Email	:mbynum@unomaha.edu

Partners: None

Description of program:This program will add the digits of a persons birthdate to obtain a single digit to generate a numerology report.
*/
import java.util.Scanner;

public class mbynum_Numerology

{
	public static void main ( String arg [] )
	{
		Scanner input = new Scanner (System.in);

		int month;
		int monthd1;
		int monthd2;
		int day;
		int dayd1;
		int dayd2;
		int year;
		int yeard1;
		int yeard2;
		int yeard3;
		int yeard4;
		int num;
		int numd1;
		int numd2;
		char a;
		char b;
                boolean data;

		System.out.println();

                do
                {
                    System.out.print("Enter the birth date (mm / dd / yyyy): ");
                    month = input.nextInt();
                    a = input.next().charAt(0);
                    day = input.nextInt();
                    b = input.next().charAt(0);
                    year = input.nextInt();
                    data = true;

                    if (month > 12 || month < 1)
                    {
			System.out.println("Bad month: " + month);
	                data = false;
                    }

                    else if (year > 2280 || year < 1880)
                    {
			System.out.println("Bad year: " + year);
                        data = false;
                    }

                    else if (a != '/' || b != '/')
                    {
			System.out.println("Use forward slashes between mm/dd/yyyy!");
                        data = false;
                    }

                    else if (day < 1)
                    {
			System.out.printf("Bad day for %d/%d : %d\n",month,year,day);
                        data = false;
                    }

                    else if (month==1 && day > 31)
                    {
			System.out.printf("Bad day for %d/%d : %d\n",month,year,day);
                        data = false;
                    }

		    else if ((month==2 && day > 29) || (month==2 && day > 28 && year%4 > 0 || (year == 1900 || year == 2100 || year == 2200)))
                    {
			System.out.printf("Bad day for %d/%d : %d\n",month,year,day);
                        data = false;
                    }
		
                    else if (month==3 && day > 31)
                    {
			System.out.printf("Bad day for %d/%d : %d\n",month,year,day);
                        data = false;
                    }

                    else if (month==4 && day >30)
                    {
			System.out.printf("Bad day for %d/%d : %d\n",month,year,day);
                        data = false;
                    }

                    else if (month==5 && day>31)
                    {
			System.out.printf("Bad day for %d/%d : %d\n",month,year,day);
                        data = false;
                    }

                    else if (month==6 && day>30)
                    {
			System.out.printf("Bad day for %d/%d : %d\n",month,year,day);
                        data = false;
                    }

                    else if (month==7 && day>31)
                    {
			System.out.printf("Bad day for %d/%d : %d\n",month,year,day);
                        data = false;
                    }

                    else if (month==8 && day>31)
                    {
			System.out.printf("Bad day for %d/%d : %d\n",month,year,day);
                        data = false;
                    }

                    else if (month==9 && day>30)
                    {
			System.out.printf("Bad day for %d/%d : %d\n",month,year,day);
                        data = false;
                    }

                    else if (month==10 && day>31)
                    {
			System.out.printf("Bad day for %d/%d : %d\n",month,year,day);
                        data = false;
                    }

                    else if (month==11 && day>30)
                    {
			System.out.printf("Bad day for %d/%d : %d\n",month,year,day);
                        data = false;
                    }

                    else if (month==12 && day>31)
                    {
			System.out.printf("Bad day for %d/%d : %d\n",month,year,day);
                        data = false;
                    }
                }while (data == false);

		monthd1 = month / 10;
		monthd2 = month % 10;
		dayd1 = day / 10;
		dayd2 = day % 10;
		yeard1 = year / 1000;
		yeard2 = year / 100 % 10;
		yeard3 = year / 10 % 10;
		yeard4 = year % 10;
		num = monthd1 + monthd2 + dayd1 + dayd2 + yeard1 + yeard2 + yeard3 + yeard4;
		
		while (num >= 10)
		{
			numd1 = num / 10;
			numd2 = num % 10;
			num = numd1 + numd2;
		}

		System.out.println();
		System.out.printf("Welcome to the numerology report for %d/%d/%d:\n", month,day,year); 

		switch (num)
		{
			case 1:
				System.out.println(":1: There is travel in your future when your tongue freezes to the back of a speeding bus.");
				break;
			case 2:
				System.out.println(":2: You are the true Lord of the Dance, no matter what those idiots at work say.");
				break;
			case 3:
				System.out.println(":3: The stars predict tomorrow you'll wake up, do a bunch of stuff, and then go back to sleep.");
				break;
			case 4:
				System.out.println(":4: The position of Jupiter says you should spend the rest of the week face down in the mud.");
				break;
			case 5:
				System.out.println(":5: Try not to shove a roll of duct tape up your nose while taking your driver's test.");
				break;
			case 6:
				System.out.println(":6: Eat a bucket of tuna-flavored pudding, then wash it down with a gallon of strawberry Quik.");
				break;
			case 7:
				System.out.println(":7: A big promotion is just around the corner for someone much more talented than you.");
				break;
			case 8:
				System.out.println(":8: The stars say that you are an exciting and wonderful person, but you know they have had too much to drink.");
				break;
			case 9:
				System.out.println(":9: If I were you I would lock myself in the bathroom and never never never never leave.");
				break;
		}
		System.out.println();
	}

}


