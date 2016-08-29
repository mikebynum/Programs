// Name         : Mike Bynum
// Class        : CSCI 1620-301
// Program #    : 8
// Due Date     : 10 Aug 2010
//
// Honor Pledege:   On my honor as a student of the University of Nebraska at
//                  Omaha, I have neither given nor received unauthorized help
//                  on this homework assignment.
//
// NAME:    Mike Bynum
// NUID:    343
// EMAIL:   mbynum@unomaha.edu
//
// Partners:    NONE
//
// Description: This program will have a user enter information and perform
//              various operations using a tree class.
import java.util.Scanner;
import java.util.InputMismatchException;

public class mbynum_Menu
{
    private String options[];//creates a string for the options
    //Method Name           :mbynum_Menu
    //Parameters            :options, a string
    //Return Values         :none
    //Partners              :none
    //Description           :constructor that initializes this.options
    public mbynum_Menu(String options[])
    {
        this.options=new String [options.length];

        for(int x=0; x<options.length; x++)
            this.options[x]=options[x];
    }
    //Method Name           :runMenu
    //Parameters            :none
    //Return Values         :choice, an integer
    //Partners              :none
    //Description           :returns the menu choice of the client
    public int runMenu()
    {
        Scanner input=new Scanner (System.in);
        int choice=-1;
        do
        {
            System.out.println("\nChoose from the following options: ");

            try
            {
                for(int x=1;x<options.length;x++)
                    System.out.printf("\t%d\t%s\n",x,options[x]);

                System.out.printf("\t%d\t%s\n",0,options[0]);
                System.out.print("Please enter your choice:  ");
                choice=input.nextInt();
                input.nextLine();

                if(choice<0 || choice>=options.length)
                    System.out.println("Invalid Menu Option - try again\n");
            }
            catch(InputMismatchException inputMismatchException)
            {
                System.out.println("Invalid Menu Option-try again\n");
                input.nextLine();
            }
        }while(choice<0 || choice>=options.length);
        return choice;
    }
}
