// Name         : Mike Bynum
// Class        : CSCI 1620-301
// Program #    : 7
// Due Date     : 5 Aug 2010
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
// Description: This program will test a stack method out.

import java.util.Scanner;
import java.util.InputMismatchException;

public class mbynum_StackTest
{

    public static void main(String[] args)
    {
        //new object from the stack
        mbynum_Stack<Character> list=new mbynum_Stack<Character> ();
        //menu options
        String[] options={" Quit"," Add an item to the list",
                           " Remove an item from the list",
                           " View first item", " Print the length of the list",
                           " Determine if list is empty"," Print the list",
                           " Clear the list"};
        //menu choice
        int choice=-1;
        //character to be put on stack
        Character letter='a';
        //scanner for input called input
        Scanner input=new Scanner(System.in);
        //new menu object
        mbynum_Menu menu=new mbynum_Menu(options);

        do// do/while loop for menu choices
        {
            choice=menu.runMenu();
            try
            {
            switch(choice)
            {
                case 1://add item to front - push
                    System.out.print("Please input character to add:  ");
                    letter=input.next().charAt(0);
                    list.push(letter);
                    break;
                case 2://remove from front - pop
                    try
                    {
                        System.out.printf("\nRemoved: %s",list.pop());
                    }
                    catch(mbynum_EmptyListException e)
                    {
                        System.out.println("\nThe list is empty!");
                    }
                    break;
                case 3://view list from top - top
                    try
                    {
                        System.out.printf("\nFirst character: %s",list.top());
                    }
                    catch(mbynum_EmptyListException e)
                    {
                        System.out.println("\nThe list is empty!");
                    }
                    break;
                case 4://length is
                    System.out.printf("\nThe length of the list is %d\n"
                                       ,list.lengthIs());
                    break;
                case 5://is empty
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
                case 6://print out charcters in list
                    list.print();
                    break;
                case 7://clear
                    list.clear();
                    System.out.println("\nThe list is now cleared!");
                    break;
            }//end switch
            }//end try
            catch(InputMismatchException i)
            {
                System.out.println("Input MUST be a number. Please try again.");
            }//end catch
        }//end do
        while(choice!=0);

    }//end main
}//end class
