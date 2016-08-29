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
public class mbynum_SortedLinkedList <T extends Comparable<T>> extends
                                        mbynum_LinkedList<T>
{
    //Method Name           :SortedLinkedList
    //Parameters            :none
    //Return Values         :none
    //Partners              :none
    //Description           :constructor that calls upon the super one
    public mbynum_SortedLinkedList()
    {
        super();
    }//end constructor
    //Method Name           :SortedLinkedList
    //Parameters            :name, a string
    //Return Values         :none
    //Partners              :none
    //Description           :constructor that calls upon the super one and 
    //                       passes the name to it.
    public mbynum_SortedLinkedList(String name)
    {
        super(name);
    }//end contructor
    //Method Name           :insertAtBack
    //Parameters            :insertItem, a T
    //Return Values         :none
    //Partners              :none
    //Description           :throws an exception when called
    public void insertAtBack(T insertItem)throws
            mbynum_InvalidListOperationException
    {
        throw new mbynum_InvalidListOperationException("Insert at back of " +
                                                        "list is not allowed");
    }//end insertAtBack
    //Method Name           :insertAtFront
    //Parameters            :insertItem, a T
    //Return Values         :none
    //Partners              :none
    //Description           :throws an exception when called
    public void insertAtFront(T insertItem)throws
            mbynum_InvalidListOperationException
    {
        throw new mbynum_InvalidListOperationException("Insert at front of " +
                                                        "list is not allowed");
    }//end insertAtFront
    //Method Name           :insertSorted
    //Parameters            :insertItem, a T
    //Return Values         :none
    //Partners              :none
    //Description           :inserts item into list in the sorted spot
    public void insertSorted(T insertItem)
    {
        boolean found;              //whther or not data was found

        mbynum_ListNode<T> current; //current node
        current=getFirstNode();     //assigns first node as current node
        mbynum_ListNode<T> newNode; //creates a new node for the new data
      

        if(isEmpty())
        {
            super.insertAtFront(insertItem);
        }//end if
        else
        {
            if(insertItem.compareTo(current.data) < 0)
            {
                super.insertAtFront(insertItem);
            }//end if
            else
            {
                found=false;

                while(current.nextNode != null && found==false)
                {
                    if(insertItem.compareTo(current.nextNode.data) > 0)
                    {
                        current=current.nextNode;
                    }
                    else
                    {
                        found=true;
                    }
                }//end while

                if (found==true)
                {
                    //insert item after current node
                    newNode=new mbynum_ListNode<T>(insertItem,current.nextNode);
                    current.nextNode=newNode;    
                }
                else
                {
                    super.insertAtBack(insertItem);
                }//end else
            }//end else
        }//end else
    }//end insertSorted
    //Method Name           :printReverse
    //Parameters            :none
    //Return Values         :none
    //Partners              :none
    //Description           :prints list in reverse order
    public void printReverse()
    {

        if(isEmpty())
        {
            System.out.printf("\nEmpty %s\n",getName());
        }
        else
        {
            int n=0;                        //counter for # nodes
            int skip=0;                     //counter for skipping nodes
            int items=getNumElements();     //assigns the number of elements

            System.out.print("\nHere is the list is reverse order: ");

            for(n=items-1 ;n>=0; n--)
            {
                mbynum_ListNode<T> curr=getFirstNode();
                for(skip=0; skip<n; skip++)
                {
                    curr=curr.nextNode;
                }
                System.out.print(curr.data+" ");
            }//end for

            System.out.println("");
  
        }//end else
    }//end printReverse
}//end class
