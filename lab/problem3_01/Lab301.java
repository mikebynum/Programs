class Lab301
{
	//The main function, you will create and use a vending machine in this 
	//function
		public static void main(String args[])
		{
			//Create a variable of the type created above.
			VMachine x = new VMachine();
			//Next, the machine should be stocked. 
			x.stock();
			//Three customers approach the machine, each wishing to make a
			//purchase from the machine
			x.buy();
			x.buy();
			x.buy();
			//The owner is now closing the store and wishes to check the
			//inventory on the machine.
			x.inventory();
			//The owner restocks the machine, collects the money, and checks
			//the inventory again to make sure everything is right.
			x.stock();
			x.inventory();
			//The end
		}
}
