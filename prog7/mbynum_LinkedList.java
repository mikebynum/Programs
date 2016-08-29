// Name         : Mike Bynum
// Class        : CSCI 1620-301
// Program #    : 7
// Due Date     : 5 Aug 2010
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
// Description: This program will have a user enter a sentence and then print
//              it out in reverse.
class mbynum_ListNode <T extends Comparable<T>>
{
    T data=null;
    mbynum_ListNode<T> nextNode=null;
    //Method Name           :mbynum_ListNode
    //Parameters            :a T object called data
    //Return Values         :none
    //Partners              :none
    //Description           :constructor that calls upon the other constructor
    public mbynum_ListNode(T data)
    {
        this(data,null);
    }
    //Method Name           :getObject
    //Parameters            :none
    //Return Values         :object T
    //Partners              :none
    //Description           :returns the data object
    public T getObject()
    {
        return data;
    }
    //Method Name           :getNext
    //Parameters            :none
    //Return Values         :a mbynum_ListNode<T> object
    //Partners              :none
    //Description           :returns the nextNode
    public mbynum_ListNode<T> getNext()
    {
        return nextNode;
    }
    //Method Name           :mbynum_ListNode
    //Parameters            :an object T called object and a mbynum_ListNode<T>
    //                       called node
    //Return Values         :none
    //Partners              :none
    //Description           :constructor that initializes data and nextNode
    public mbynum_ListNode(T object, mbynum_ListNode<T> node)
    {
        data=object;
        nextNode=node;
    }
}//end ListNode

public class mbynum_LinkedList <T extends Comparable<T>>
{
    private mbynum_ListNode<T> firstNode;
    private mbynum_ListNode<T> lastNode;
    private int numElements;
    private String name;
    //Method Name           :mbynum_LinkedList
    //Parameters            :none
    //Return Values         :none
    //Partners              :none
    //Description           :constructor that calls upon the other constructor
    public mbynum_LinkedList()
    {
        this("list");
    }//end default constructor
    //Method Name           :mbynum_LinkedList
    //Parameters            :a string called listName
    //Return Values         :none
    //Partners              :none
    //Description           :constructor that initializes variables
    public mbynum_LinkedList(String listName)
    {
        name=listName;
        firstNode=lastNode=null;
        numElements=0;
    }//end parameterized constructor
    //Method Name           :insertAtFront
    //Parameters            :a T called insertItem
    //Return Values         :none
    //Partners              :none
    //Description           :inserts item at the front of list
    public void insertAtFront(T insertItem)
    {
        mbynum_ListNode<T> newNode;

        if(isEmpty())
        {
            newNode=new mbynum_ListNode<T>(insertItem,null);
            firstNode=lastNode=newNode;
        }
        else
        {
            newNode=new mbynum_ListNode<T>(insertItem,firstNode);
            firstNode=newNode;
        }
        numElements++;
    }//end insertAtFront
    //Method Name           :insertAtBack
    //Parameters            :a T called insertItem
    //Return Values         :none
    //Partners              :none
    //Description           :inserts item at the back of list
    public void insertAtBack(T insertItem)
    {
        if(isEmpty())
        {
            firstNode=lastNode=new mbynum_ListNode<T>(insertItem);
        }
        else
        {
            lastNode=lastNode.nextNode=new mbynum_ListNode<T>(insertItem);
        }
        numElements++;
    }//end insertAtBack
    //Method Name           :removeFromFront
    //Parameters            :none
    //Return Values         :T
    //Partners              :none
    //Description           :removed item from front of list
    public T removeFromFront() throws mbynum_EmptyListException
    {
        if(isEmpty())
        {
            throw new mbynum_EmptyListException(name);
        }

        T removedItem=firstNode.data;

        if(firstNode==lastNode)
        {
            firstNode=lastNode=null;
        }
        else
        {
            firstNode=firstNode.nextNode;
        }

        numElements--;
        return removedItem;
    }//end removeFromFront
    //Method Name           :removeFromBack
    //Parameters            :none
    //Return Values         :T
    //Partners              :none
    //Description           :removed item from back of the list
    public T removeFromBack() throws mbynum_EmptyListException
    {
        if(isEmpty())
        {
            throw new mbynum_EmptyListException(name);
        }

        T removedItem=lastNode.data;

        if(firstNode==lastNode)
        {
            firstNode=lastNode=null;
        }
        else
        {
            mbynum_ListNode<T> current=firstNode;
            while(current.nextNode != lastNode)
                current=current.nextNode;
            lastNode=current;
            lastNode.nextNode=null;
        }
        numElements--;
        return removedItem;

    }//end removeFromBack
    //Method Name           :findAndRemove
    //Parameters            :a T called item
    //Return Values         :found, a boolean
    //Partners              :none
    //Description           :finds and removes a particular item and reports
    //                       whether or not it was successfull
    public boolean findAndRemove(T item)throws mbynum_EmptyListException
    {
        boolean found=false;
        if(!isEmpty())
        {
            if((firstNode.data).compareTo(item)==0)
            {
                removeFromFront();
                found=true;
            }
            else
            {
                mbynum_ListNode<T> current=firstNode;
                while(current.nextNode !=null&&!found)
                {
                    if((current.nextNode.getObject()).compareTo(item)==0)
                        found=true;
                    else
                        current=current.nextNode;
                }
                if(found)
                {
                    if(current.nextNode==lastNode)
                        lastNode=current;
                    current.nextNode=current.nextNode.nextNode;
                    numElements--;
                }
            }
        }
        else
            throw new mbynum_EmptyListException(name);
        return found;
    }//end findAndRemove
    //Method Name           :findItem
    //Parameters            :a T called item
    //Return Values         :found, a boolean
    //Partners              :none
    //Description           :finds an item in the list and return whether
    //                       or not it was a success
    public boolean findItem(T item)
    {
        boolean found=false;

        if(!isEmpty())
        {
            mbynum_ListNode<T>current=firstNode;

            while(current!=null&&!found)
            {
                T theData=current.getObject();
                if(theData.compareTo(item)==0)
                    found=true;
                else
                    current=current.nextNode;
            }
        }
        return found;
    }//end findItem
    //Method Name           :lengthIs
    //Parameters            :none
    //Return Values         :an integer, numElements
    //Partners              :none
    //Description           :returns the number of elements in the list
    public int lengthIs()
    {
        return numElements;
    }//end lengthIs
    //Method Name           :isEmpty
    //Parameters            :none
    //Return Values         :a boolean
    //Partners              :none
    //Description           :will return true of firstNode is empty
    public boolean isEmpty()
    {
        return firstNode==null;
    }//end isEmpty
    //Method Name           :print
    //Parameters            :non
    //Return Values         :none
    //Partners              :none
    //Description           :prints list
    public void print()
    {
        if(isEmpty())
            System.out.printf("\nEmpty %s\n",name);
        else
        {
            System.out.printf("\nThe %s is: ",name);
            mbynum_ListNode<T>current=firstNode;

            while(current!=null)
            {
                System.out.printf("%s  ",current.data);
                current=current.nextNode;
            }
            System.out.println("\n");
        }
    }//print
    //Method Name           :clear
    //Parameters            :none
    //Return Values         :none
    //Partners              :none
    //Description           :clears list
    public void clear()
    {
        numElements=0;
        firstNode=lastNode=null;
    }//end clear    
}//end class
