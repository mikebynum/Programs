
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
public class mbynum_Office
{
    private int officeNumber;       //The office number
    private String buildingName;    //The name of the building
    private mbynum_Employee occupant = new mbynum_Employee();
    private boolean occupied;       //Whether or not the office is occupied
//Method Name           :mbynum_Office
//Parameters            :none
//Return Values         :none
//Partners              :none
//Description           :default constructor
    public mbynum_Office()
    {

    }
//Method Name           :mbynum_Office
//Parameters            :officeNumber an integer; buildingName a string,
//                       occupant an mbynum_Employee object
//Return Values         :none
//Partners              :none
//Description           :constructor is parameters
    public mbynum_Office(int officeNumber, String buildingName,
                         mbynum_Employee occupant)
    {
        this.officeNumber=officeNumber;
        this.buildingName=buildingName;
        this.occupant=occupant;

        if(occupant==null)
        {
            occupied=false;
        }
        else
            occupied=true;
    }
//Method Name           :setOffice
//Parameters            :officeNumber an integer; buildingName a string,
//                       occupant an mbynum_Employee object
//Return Values         :none
//Partners              :none
//Description           :sets the field variables
    public void setOffice(int officeNumber, String buildingName,
                          mbynum_Employee occupant)
    {
        this.officeNumber=officeNumber;
        this.buildingName=buildingName;
        this.occupant=occupant;
    }
//Method Name           :setOfficeNumber
//Parameters            :officeNumber an integer
//Return Values         :none
//Partners              :none
//Description           :sets the officeNumber in the field
    public void setOfficeNumber(int officeNumber)
    {
        this.officeNumber=officeNumber;
    }
//Method Name           :setBuildingName
//Parameters            :buildingName a string
//Return Values         :none
//Partners              :none
//Description           :sets the buildingName in the field
    public void setBuildingName(String buildingName)
    {
        this.buildingName=buildingName;
    }
//Method Name           :setOccupant
//Parameters            :occupant an mbynum_Employee object
//Return Values         :none
//Partners              :none
//Description           :passes a true/false to setOccupied method
    public void setOccupant(mbynum_Employee occupant)
    {
        this.occupant=occupant;

        if(occupant==null)
        {
            setOccupied(false);
        }
        else
        {
            setOccupied(true);
        }
    }
//Method Name           :setOccupied
//Parameters            :occupied a boolean
//Return Values         :none
//Partners              :none
//Description           :copied occupied into the field
    public void setOccupied(boolean occupied)
    {
        this.occupied=occupied;
    }
//Method Name           :getOfficeNumber
//Parameters            :none
//Return Values         :officeNumber an integer
//Partners              :none
//Description           :returns the office number
    public int getOfficeNumber()
    {
        return officeNumber;
    }
//Method Name           :getBuildingName
//Parameters            :none
//Return Values         :buildingName a string
//Partners              :none
//Description           :returns the name of the building
    public String getBuildingName()
    {
        return buildingName;
    }
//Method Name           :getOccupant
//Parameters            :none
//Return Values         :occupant a mbynum_Employee object
//Partners              :none
//Description           :returns info about the occupant
    public mbynum_Employee getOccupant()
    {
        return occupant;
    }
//Method Name           :getOccupied
//Parameters            :none
//Return Values         :occupied a boolean
//Partners              :none
//Description           :returns whether or not office is occupied
    public boolean getOccupied()
    {
        return occupied;
    }
//Method Name           :toString
//Parameters            :none
//Return Values         :String
//Partners              :none
//Description           :returns a formated string with all info
    public String toString()
    {
        if(occupant==null)
        {
            return String.format("Office Number:  %s, Building:  %s\n%s"
                                  ,officeNumber,buildingName, "VACANT");
        }
        else
        {
            return String.format("Office Number:  %s, Building:  %s\n" +
                                 "Occupant: %s",officeNumber,buildingName,
                                  occupant.toString());
        }
    }

}
