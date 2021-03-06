// Name         : Mike Bynum
// Class        : CSCI 1620-301
// Program #    : 5
// Due Date     : 20 July 2010
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
// Description: This program will have a user enter numbers and put them in a
//              list.
import java.util.Scanner;
import java.util.InputMismatchException;

public class mbynum_LinkedListTest
{
    public static void main(String[] args)
    {
        mbynum_LinkedList<Double> list=new mbynum_LinkedList<Double>();
        //a list of double values
        String[] options={" Quit"," Add an item to the front of the list",
                           " Add an item to the back of the list"," Remove an" +
                           " item from the front of the list"," Remove an" +
                           " item from the back of the list"," Find and" +
                           " remove an item"," Find an item in the list",
                           " Print the length of the list"," Determine if" +
                           " list is empty"," Print the list","Clear the list"};
        //info contained in list
        int choice=-1;//menu choice
        Double num=0.0;//value that user inputs

        Scanner input=new Scanner(System.in);
        mbynum_Menu menu=new mbynum_Menu(options);

        do
        {
            choice=menu.runMenu();
            try
            {
            switch(choice)
            {
                case 1://add item to front
                    System.out.print("Please input number to add:  ");
                    num=input.nextDouble();
                    list.insertAtFront(num);
                    break;
                case 2://add item to back
                    System.out.print("Please input number to add:  ");
                    num=input.nextDouble();
                    list.insertAtBack(num);
                    break;
                case 3://remove from front
                    try
                    {
                        System.out.printf("Removed: %s",list.removeFromFront());
                    }
                    catch(mbynum_EmptyListException e)
                    {
                        System.out.println("The list is empty!");
                    }
                    break;
                case 4://remove from back
                    try
                    {
                        System.out.printf("Removed: %s",list.removeFromBack());
                    }
                    catch(mbynum_EmptyListException e)
                    {
                        System.out.println("The list is empty!");
                    }
                    break;
                case 5://find and remove
                    try
                    {
                        System.out.print("What number would you like to " +
                                          "remove?  ");
                        num=input.nextDouble();
                        if(list.findAndRemove(num))
                        {
                            System.out.printf("%s was successfully " +
                                               "removed.\n",num);
                        }
                        else
                        {
                            System.out.printf("Could not find %s", num);
                        }
                    }
                    catch(mbynum_EmptyListException e)
                    {
                        System.out.println("The list is empty!");
                    }
                    break;
                case 6://find item
                    System.out.print("What number would you like to find?  ");
                    num=input.nextDouble();
                    
                    if(list.findItem(num))
                    {
                        System.out.printf("%s was successfully " +
                                           "found.\n",num);
                    }
                    else
                    {
                        System.out.printf("Could not find %s", num);
                    }
                    break;
                case 7://length is
                    System.out.printf("\nThe length of the list is %d\n"
                                       ,list.lengthIs());
                    
                    break;
                case 8://is empty
                    boolean empty=(list.isEmpty());
                    if(empty)
                    {
                        System.out.println("\nThis list is empty.");
                    }
                    else
                    {
                        System.out.println("\nThis list is NOT empty.");
                    }
                    break;
                case 9://print toString
                    list.print();
                    break;
                case 10://clear
                    list.clear();
                    System.out.println("The list is now cleared!");
                    break;
            }//end switch
            }//end try
            catch(InputMismatchException i)
            {
                System.out.println("Input MUST be a number. Please try again.");
            }//end catch
        }//end do
        while(choice!=0);

    }//end main method
}//end LinkedListTest
