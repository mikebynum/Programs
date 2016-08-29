// Name         : Mike Bynum
// Class        : CSCI 1620-301
// Program #    : 4
// Due Date     : 15 July 2010
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
// Description: This program will have a user enter up information about an
//              employee.
import java.util.Scanner;
//import java.util.InputMismatchException;

public class mbynum_UnsortedEmployeeListTest
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);  //scanner for input
        int size=0;                              //size of array
        boolean continueLoop=true;               //boolean for first loop
        String[] options={"Quit","Add an item to the list","Remove an item" +
                           " from the list","Find an item in the list",
                           "Determine if the list is empty","Get the length" +
                           " of the list","Print the list","Clear the" +
                           " list"};             //options for menu
        int choice=-1;                           //menu choice
        boolean continueLoopTwo=true;            //boolean for second loop
        String employeeNumber=null;              //employee number
        String firstName=null;                   //employee first name
        String lastName=null;                    //employee last name
        String jobTitle=null;                    //employee job title
        mbynum_Employee employee = new mbynum_Employee();
                                                 //mbynum_Employee object
        mbynum_UnsortedEmployeeList list=null;   //mbynum_UnosrtedEmployeeList
                                                 //object
        do
        {
            try                                  //try out to determine if it
            {                                    //contains an error
                System.out.print("Please input the initial size of the list: ");
                size=input.nextInt();
                list=new mbynum_UnsortedEmployeeList(size);
                continueLoop=false;              //will repeat if bad info
            }
            catch(Exception i)                  //catch the exception
            {
                System.out.println("You must enter integers.  Please try" +
                                    " again");
            }
        }while(continueLoop || size<=0);     

        System.out.println();
        mbynum_Menu menu=new mbynum_Menu(options);
        input.nextLine();
        
        do
        {
            choice=menu.runMenu();          //creates the menu

            switch(choice)                  //switch for menu choices
            {
                case 1:                     //add employee
                    do
                    {                           
                            System.out.print("Enter the employee number:  ");
                            employeeNumber=input.nextLine();                            
                            continueLoopTwo=employee.setEmployeeNumber(employeeNumber);
                    }while(!continueLoopTwo);

                    System.out.print("Enter the employee's first name:  ");
                    firstName=input.nextLine();

                    System.out.print("Enter employee's last name:  ");
                    lastName=input.nextLine();

                    System.out.print("Enter the employee's job title:  ");
                    jobTitle=input.nextLine();

                    employee=new mbynum_Employee(employeeNumber, firstName,
                                                  lastName,jobTitle);
                    list.addItem(employee);
                    break;
                case 2:                         //remove employee from list
                    do
                    {
                            System.out.print("Enter the employee number:  ");
                            employeeNumber=input.nextLine();
                            continueLoopTwo=
                                    employee.setEmployeeNumber(employeeNumber);
                    }while(!continueLoopTwo);

                    System.out.print("Enter the employee's first name:  ");
                    firstName=input.nextLine();

                    System.out.print("Enter employee's last name:  ");
                    lastName=input.nextLine();

                    System.out.print("Enter the employee's job title:  ");
                    jobTitle=input.nextLine();

                    employee=new mbynum_Employee(employeeNumber, firstName,
                                                  lastName,jobTitle);
                    if(list.removeItem(employee))
                        System.out.println("Employee was removed from list");
                    else
                        System.out.println("Employee was not found");
                    break;
                case 3:                             //find employee in list
                    do
                    {
                            System.out.print("Enter the employee number:  ");
                            employeeNumber=input.nextLine();
                            continueLoopTwo=
                                    employee.setEmployeeNumber(employeeNumber);
                    }while(!continueLoopTwo);

                    System.out.print("Enter the employee's first name:  ");
                    firstName=input.nextLine();

                    System.out.print("Enter employee's last name:  ");
                    lastName=input.nextLine();

                    System.out.print("Enter the employee's job title:  ");
                    jobTitle=input.nextLine();

                    employee=new mbynum_Employee(employeeNumber, firstName,
                                                  lastName,jobTitle);
                    if(list.findItem(employee))
                    {
                        System.out.print("\nThe employee is in the list\n");
                    }
                    else
                    {
                        System.out.print("\nThe employee is NOT in the list\n");
                    }
                    break;
                case 4:                             //determine if list is empty
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
                case 5:                             //prints length
                    System.out.printf("\nThe length of the list is %d\n"
                                       ,list.lengthIs());
                    break;
                case 6:                             //prints list
                    System.out.printf("\nHere is everything contained in the" +
                                       " list:\n%s", list.toString());
                    break;
                case 7:                             //clears list
                    list.clear();
                    break;
            }//end switch
        }//end do
        while(choice!=0);
        System.out.println();
    }//end main
}//end class
