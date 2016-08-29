
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
    private int officeNumber;
    private String buildingName;
    private mbynum_Employee occupant = new mbynum_Employee();
    private boolean occupied;

    public mbynum_Office()
    {

    }

    public mbynum_Office(int officeNumber, String buildingName,
                         mbynum_Employee occupant)
    {

    }

    public void setOffice(int officeNumber, String buildingName,
                          mbynum_Employee occupant)
    {
        this.officeNumber=officeNumber;
        this.buildingName=buildingName;
        this.occupant=occupant;
    }

    public void setOfficeNumber(int officeNumber)
    {
        this.officeNumber=officeNumber;
    }

    public void setBuildingName(String buildingName)
    {
        this.buildingName=buildingName;
    }

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

    public void setOccupied(boolean occupied)
    {
        this.occupied=occupied;
    }

    public int getOfficeNumber()
    {
        return officeNumber;
    }

    public String getBuildingName()
    {
        return buildingName;
    }

    public mbynum_Employee getOccupant()
    {
        return occupant;
    }

    public boolean getOccupied()
    {
        return occupied;
    }

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
