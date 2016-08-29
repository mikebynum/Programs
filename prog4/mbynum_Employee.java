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
public class mbynum_Employee
{
    private String employeeNumber;  //the employee number
    private String firstName;       //the first name of employee
    private String lastName;        //the last name of employee
    private String jobTitle;        //the job title of the employee
    //Method Name           :mbynum_Employee
    //Parameters            :none
    //Return Values         :none
    //Partners              :none
    //Description           :constructor with no parameters
    public mbynum_Employee()
    {
        setEmployeeNumber(null);        //Employee number XXX-XX-XXX
        setFirstName(null);             //First name of employee
        setLastName(null);              //Last name of employee
        setJobTitle(null);              //Job title of employee
    }
    //Method Name           :mbynum_Employee
    //Parameters            :employeeNumber,firstName,lastName,jobTitle all strings
    //Return Values         :none
    //Partners              :none
    //Description           :constructor with parameters
    public mbynum_Employee(String employeeNumber, String firstName,
                           String lastName, String jobTitle)
    {
        setEmployeeNumber(employeeNumber);
        setFirstName(firstName);
        setLastName(lastName);
        setJobTitle(jobTitle);
    }
    //Method Name           :setEmployee
    //Parameters            :employeeNumber,firstName,lastName,jobTitle all
    //                       strings
    //Return Values         :none
    //Partners              :none
    //Description           :sets the employee by copying all variables to field
    public void setEmployee(String employeeNumber, String firstName,
                            String lastName, String jobTitle)
    {
        this.employeeNumber=employeeNumber;
        this.firstName=firstName;
        this.lastName=lastName;
        this.jobTitle=jobTitle;
    }
    //Method Name           :setEmployeeNumber
    //Parameters            :employee number, a string
    //Return Values         :none
    //Partners              :none
    //Description           :sets the employee number to field
    public boolean setEmployeeNumber(String employeeNumber)
    {
        String num=employeeNumber;
        boolean success=true;
        try
        {
           if(num==null)
           {
               return success;
           }
           else if (num.length()<10)
           {
               throw new StringIndexOutOfBoundsException();
           }
        }
        catch(StringIndexOutOfBoundsException e)
        {

            System.out.println("Invalid employee number.  " +
                                "Must be in the form " +
                                "XXX-XX-XXX where X is (0-9).");
            success=false;
        }
        if(success)
        {
            this.employeeNumber=num;
            return success;
        }
        else
            return success;
    }
    //Method Name           :setFirstName
    //Parameters            :firstName a string
    //Return Values         :none
    //Partners              :none
    //Description           :sets first name to field
    public void setFirstName(String firstName)
    {
        this.firstName=firstName;
    }
    //Method Name           :setLastName
    //Parameters            :lastName a string
    //Return Values         :none
    //Partners              :none
    //Description           :sets last name to field
    public void setLastName(String lastName)
    {
        this.lastName=lastName;
    }
    //Method Name           :setJobTitle
    //Parameters            :jobTitle a string
    //Return Values         :none
    //Partners              :none
    //Description           :sets job title to field
    public void setJobTitle(String jobTitle)
    {
        this.jobTitle=jobTitle;
    }
    //Method Name           :getEmployeeNumber
    //Parameters            :none
    //Return Values         :employeeNumber a string
    //Partners              :none
    //Description           :returns the employee number
    public String getEmployeeNumber()
    {
        return employeeNumber;
    }
//Method Name           :getFirstName
//Parameters            :none
//Return Values         :firstName a string
//Partners              :none
//Description           :returns the first name
    public String getFirstName()
    {
        return firstName;
    }
    //Method Name           :getLastName
    //Parameters            :none
    //Return Values         :lastName a string
    //Partners              :none
    //Description           :returns the last name
    public String getLastName()
    {
        return lastName;
    }
    //Method Name           :getJobTitle
    //Parameters            :none
    //Return Values         :jobTitle a string
    //Partners              :none
    //Description           :returs the job title
    public String getJobTitle()
    {
        return jobTitle;
    }
    //Method Name           :toString
    //Parameters            :none
    //Return Values         :formated string
    //Partners              :none
    //Description           :returns a formated string with all info
    public String toString()
    {
        return String.format("Name:  %s,%s.  Employee Number:  %s. Title:" +
                              "  %s", lastName, firstName, employeeNumber,
                              jobTitle);
    }
    //Method Name           :equals
    //Parameters            :employee, an mbynum_Employee object
    //Return Values         :equals, a boolean
    //Partners              :none
    //Description           :returns a boolean on whether or not statements are
    //                       equal
    public boolean equals(mbynum_Employee employee)
    {
        boolean equals=false;

        if(this.firstName.equals(employee.firstName)||this.lastName.equals(null)
           ||this.employeeNumber.equals(null)||this.jobTitle.equals(null))
        {
            equals=true;
            return equals;
        }
        else
        {
            return equals;
        }
    }
}
