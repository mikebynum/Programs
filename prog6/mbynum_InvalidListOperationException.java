// Name         : Mike Bynum
// Class        : CSCI 1620-301
// Program #    : 6
// Due Date     : 29 July 2010
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
//              list and sort them.
public class mbynum_InvalidListOperationException extends RuntimeException
{
    //Method Name           :mbynum_InvalidListOperationException
    //Parameters            :none
    //Return Values         :none
    //Partners              :none
    //Description           :constructor that calls upon other constructor
    public mbynum_InvalidListOperationException()
    {
        this("List");
    }//end constructor
    //Method Name           :mbynum_InvalidListOperationException
    //Parameters            :a string called name
    //Return Values         :none
    //Partners              :none
    //Description           :constructor that calls upon the super and feeds it
    //                       the name.
    public mbynum_InvalidListOperationException(String name)
    {
        super("Cannot do operation on " + name + ".");
    }//end constructor
}//end class
