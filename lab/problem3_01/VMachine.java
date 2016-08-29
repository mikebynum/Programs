//CIST-1404
//Lab Problem 3.01

/****************************************************************
 * DIRECTIONS
 * You are to fill in the areas below the comments where you are
 * required to do so
 * *************************************************************/

import java.util.Scanner;

class VMachine
{
	//This is the constructor. The code inside the constructor
	//is executed each time a variable of type VMachine is created
	public VMachine()
	{
		//When a vending machine is created, it should always have 
		//0 items and no money.

		int candy, pop, diet, dollars, cents;
		candy=0;
		pop=0;
		diet=0;
		dollars=0;
		cents=0;

	}

	//This is the stock() function. This function should ask the 
	//user to input how many of each item (candy, pop, and diet)
	//is being added to the machine and update the count inside
	//the class. The function should also "collect" all of the 
	//money stored inside the machine (set dollars and cents to 0)
	public void stock()
	{
		Scanner input=new Scanner (System.in);
		
		System.out.print("How many candy bars are being added?");
		candy=candy+input.nextInt();

		System.out.print("How many bottles of pop are being added?");
		pop=pop+input.nextInt();

		System.out.print("How many bottles of diet pop are being added?");
		diet=diet+input.nextInt();

		System.out.printf("$%d.%d was collected from the vending machine\n", dollars, cents);
	
		dollars=0;
		cents=0;
	}

	//This is the inventory function. This function should output
	//the number of each item currently being stored in the 
	//Vending machine along with the ammount of money stored inside
	//THIS FUNCTION HAS BEEN COMPLETED FOR YOU
	public void inventory()
	{
		System.out.println("--------Inventory--------" );
		System.out.println("|   Item    |   Number   " );
		System.out.println("|   Candy   |   " + candy );
		System.out.println("|    Pop    |   " + pop );
		System.out.println("| Diet Pop  |   " + diet );
		System.out.println("|  Dollars  |   " + dollars );
		System.out.println("|   Cents   |   " + cents );
	}

	//This is the buy() function. It should be called whenever a customer
	//approaches the machine and purchases an item. The program should 
	//ask the customer if they would like to buy a pop, a diet pop,
	//and a candy bar. The inventory should be adjusted along with
	//the ammount of money in the machine. Candy bars cost 50 cents
	//and bottles of pop cost 1 dollar. Make sure to change 100 cents 
	//into a dollar if need be.
	public void buy()
	{
		int popx;
		int dietx;
		int candyx;

		Scanner input=new Scanner (System.in);	
		if (cents>=100)
		{
			dollars=dollars+1;
			cents=cents-100;
		}		

		System.out.print("Would you like to buy a candy bar? (1=yes, 2=no)");
		candyx=input.nextInt();
		if (candyx==1)
		{
			candy=candy-1;
			cents=cents+50;
			if (cents>=100)
			{
				dollars=dollars+1;
				cents=cents-100;
			}		
		}

		System.out.print("Would you like to buy a pop? (1=yes, 2=no)");
		popx=input.nextInt();
		if (popx==1)
		{
			pop=pop-1;
			dollars=dollars+1;
		}

		System.out.print("Would you like to buy a diet pop? (1=yes, 2=no)");
		dietx=input.nextInt();
		if (dietx==1)
		{
			diet=diet-1;
  			dollars=dollars+1;
		}
	}


	private int candy;      //The number of candy bars in the machine
	private int pop;        //The number of bottles of pop in the machine
	private int diet;       //The number of bottles of diet pop in the machine
	private int dollars;    //The number of dollars curently in the machine
	private int cents;      //The number of cents currently in the machine
}

