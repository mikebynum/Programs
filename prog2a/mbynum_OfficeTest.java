// Name         : Mike Bynum
// Class        : CSCI 1620-301
// Program #    : 2
// Due Date     : 6 July 2010
//
// Honor Pledege:   On my honor as a student of the University of Nebraska at
//                  Omaha, I have neither given nor received unauthorized help
//                  on this homework assignment.
//
// NAME:    Mike Bynum
// NUID:    343
// EMAIL:   mbynum@unomaha.edu
//
// Partners:    Connie Jones conniejones@unomaha.edu
//
// Description: This program will have a user enter information about an
// employee and their office details.
import java.util.Scanner;
 
public class mbynum_OfficeTest
{
    public static void main(String[] args)
    {
      mbynum_Office office = new mbynum_Office();
      mbynum_Employee employee = new mbynum_Employee();
      int officeNumber=0;//office number
      String buildingName=null;//name of the building
      String employeeNumber=null;//employee number
      String firstName=null;//employee first name
      String lastName=null;//employee last name
      String jobTitle=null;//job title
      char occupancy='a';//either a y or n
      boolean occupied=false;//whether or not office is occupied

      Scanner input = new Scanner(System.in);

      System.out.print("Enter the office number:    ");
      officeNumber=input.nextInt();

      while(officeNumber<100||officeNumber>150&&officeNumber<200||
            officeNumber>250&&officeNumber<300||officeNumber>350&&
            officeNumber<400||officeNumber>450&&officeNumber<500||
            officeNumber>550)
      {
          System.out.println("Invalid office number.  Office numbers must be" +
                             "in the range of 100-150, 200-250, 300-350, 400" +
                             "-450, 500-550");
          System.out.println();
          System.out.print("Enter the office number:    ");
          officeNumber=input.nextInt();
      }

      input.nextLine();
      System.out.print("Enter the building name:    ");
      buildingName=input.nextLine();
      
      do
      {
          System.out.print("Will this office be occupied (Y or N)?  ");
          occupancy=input.next().charAt(0);
      }while(!(occupancy=='y'||occupancy=='Y'||occupancy=='N'||occupancy=='n'));

      if((occupancy=='y')||(occupancy=='Y'))
      {
          occupied=true;

          input.nextLine();
          System.out.print("\nEnter the employee number:  ");
          employeeNumber=input.nextLine();

          while (!employeeNumber.matches("\\d{3}-\\d{2}-\\d{3}"))
          {
    	    System.out.println("Invalid employee number.  Employee numbers " +
                                "must be in the form 123-45-678");
            System.out.print("\nEnter the employee number:  ");
            employeeNumber=input.nextLine();
          }

          System.out.print("Enter the employee's first name:    ");
          firstName=input.nextLine();

          System.out.print("Enter the employee's last name:     ");
          lastName=input.nextLine();

          System.out.print("Enter the employee's job title:     ");
          jobTitle=input.nextLine();

          employee.setEmployee(employeeNumber, firstName, lastName, jobTitle);
      }
      else
      {
          employee=null;
      }

      office.setOffice(officeNumber, buildingName, employee);

      System.out.println("\nYour Office:");

      System.out.printf("%s\n", office.toString());

      System.out.println("\nOffice Details:");
      System.out.printf("Office Number:  %d\n",office.getOfficeNumber());
      System.out.printf("Building Name:  %s\n", office.getBuildingName());
      if(occupied==true)
      {
         System.out.printf("Office Occupied by:  %s",office.getOccupant());
      }
      else
      {
          System.out.println("Office is VACANT");
      }
      
    }

}
