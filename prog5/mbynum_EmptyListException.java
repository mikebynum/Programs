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
public class mbynum_EmptyListException extends RuntimeException
{
    //Method Name           :mbynum_EmptyListException
    //Parameters            :none
    //Return Values         :none
    //Partners              :none
    //Description           :constructor that calls upon other constructor
    public mbynum_EmptyListException()
    {
        this("List");
    }
    //Method Name           :mbynum_EmptyListException
    //Parameters            :a string called name
    //Return Values         :none
    //Partners              :none
    //Description           :constructor that calls upon the super and feeds it
    //                       the name.
    public mbynum_EmptyListException(String name)
    {
        super(name + "is empty");
    }
}
