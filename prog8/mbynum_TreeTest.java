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

public class mbynum_TreeTest
{
    public static void main(String[] args)
    {
        //new object of tree
        mbynum_Tree<Integer> tree=new mbynum_Tree<Integer>();
        //list of items to be on the menu
        String[] options={" Quit"," Add an item to the list",
                           " Remove an item from the list",
                           " Preorder traverse", " Inorder traverse",
                           " Postorder traverse"," Print the length of the " +
                           " list", " Determine if list is empty"," Print" +
                           " the list", " Find item in list", "Clear the" +
                           " list"};
        //menu choice
        int choice=-1;
        //integer to be inquired on
        Integer num=null;
        //indentation spaces
        Integer indent=null;
        //scanner for input called input
        Scanner input=new Scanner(System.in);
        //new menu object
        mbynum_Menu menu=new mbynum_Menu(options);

        System.out.print("Please input the number of indetation spaces for" +
                          " tree:  ");
        indent=input.nextInt();

        do// do/while loop for menu choices
        {
            choice=menu.runMenu();
            try
            {
            switch(choice)
            {
                case 1://add item to list
                    System.out.print("Please input INTEGER to add:  ");
                    num=input.nextInt();
                    tree.insertItem(num);
                    break;
                case 2://remove from list
                    System.out.print("Please input INTEGER to remove:  ");
                    num=input.nextInt();
                    System.out.printf("\nRemoved: %d\n",num);
                    break;
                case 3://preorder traverse
                    tree.preOrderTraversal();
                    break;
                case 4://inorder traverse
                    tree.inOrderTraversal();
                    break;
                case 5://postorder traverse
                    tree.postOrderTraversal();
                    break;
                case 6://length is
                    System.out.printf("\nThe length of the list is %d\n"
                                       ,tree.lengthIs());
                    break;
                case 7://is empty
                    boolean empty=(tree.isEmpty());
                    if(empty)
                    {
                        System.out.println("\nThis list is empty.\n");
                    }
                    else
                    {
                        System.out.println("\nThis list is NOT empty.\n");
                    }
                    break;
                case 8://print out charcters in list
                    tree.treeFormatTraversal(indent);
                    break;
                case 9://find item in list
                    System.out.print("What number would you like to find?:  ");
                    num=input.nextInt();
                    if(tree.findItem(num))
                    {
                        System.out.print("\nNumber WAS found!\n");
                    }
                    else
                        System.out.print("\nNumber was NOT found!\n");
                    break;
                case 10://clear
                    tree.clear();
                    System.out.println("\nThe list is now cleared!\n");
                    break;
            }//end switch
            }//end try
            catch(InputMismatchException i)
            {
                System.out.println("\nInput MUST be a number. "
                                    + "Please try again.");
            }//end catch for an input mismatch
        }//end do
        while(choice!=0);//continue while user doesn't enter zero

    }

}
