// Name         : Mike Bynum
// Class        : CSCI 1620-301
// Program #    : 3
// Due Date     : 8 July 2010
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
//              office and its occupant.
public class mbynum_Employee
{
    private String employeeNumber;
    private String firstName;
    private String lastName;
    private String jobTitle;

    public mbynum_Employee()
    {
        employeeNumber=null;        //Employee number XXX-XX-XXX
        firstName=null;             //First name of employee
        lastName=null;              //Last name of employee
        jobTitle=null;              //Job title of employee
    }

    public mbynum_Employee(String employeeNumber, String firstName,
                           String lastName, String jobTitle)
    {
        this.employeeNumber=employeeNumber;
        this.firstName=firstName;
        this.lastName=lastName;
        this.jobTitle=jobTitle;
    }

    public void setEmployee(String employeeNumber, String firstName,
                            String lastName, String jobTitle)
    {
        this.employeeNumber=employeeNumber;
        this.firstName=firstName;
        this.lastName=lastName;
        this.jobTitle=jobTitle;
    }

    public void setEmployeeNumber(String employeeNumber)
    {
       this.employeeNumber=employeeNumber;
    }

    public void setFirstName(String firstName)
    {
        this.firstName=firstName;
    }

    public void setLastName(String lastName)
    {
        this.lastName=lastName;
    }

    public void setJobTitle(String jobTitle)
    {
        this.jobTitle=jobTitle;
    }

    public String getEmployeeNumber()
    {
        return employeeNumber;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getJobTitle()
    {
        return jobTitle;
    }

    public String toString()
    {
        return String.format("%s,%s.  Employee Number:  %s. Title:" +
                              "  %s", lastName, firstName, employeeNumber,
                              jobTitle);
    }
}
