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
import java.util.ArrayList;

public class mbynum_Stack <T extends Comparable<T>>
{
    //new linked list item
    private mbynum_LinkedList<T> list=null;
    //Method Name           :mbynum_Stack
    //Parameters            :none
    //Return Values         :none
    //Partners              :none
    //Description           :constructor that assigns object to list
    public mbynum_Stack()
    {
        list=new mbynum_LinkedList<T>();
    }// end constructor
    //Method Name           :push
    //Parameters            :item, a T
    //Return Values         :non
    //Partners              :none
    //Description           :pushes or inserts item at front
    public void push(T item)
    {
        list.insertAtFront(item);
    }// end push
    //Method Name           :pop
    //Parameters            :none
    //Return Values         :object T
    //Partners              :none
    //Description           :removes top item and returns the value
    public T pop() throws mbynum_EmptyListException
    {
        return list.removeFromFront();
    }//end pop
    //Method Name           :top
    //Parameters            :none
    //Return Values         :object T
    //Partners              :none
    //Description           :removes top item, returns the value and the adds it
    public T top() throws mbynum_EmptyListException
    {
        T item=list.removeFromFront();
        list.insertAtFront(item);
        return item;
    }//end top
    //Method Name           :print
    //Parameters            :none
    //Return Values         :none
    //Partners              :none
    //Description           :prints the stack
    public void print()
    {
        ArrayList<T> items=new ArrayList<T>();

        while(!isEmpty())
        {
            T letter=pop();
            items.add(letter);
        }//end while (removing items and returning values)

        System.out.print("Here are the items in the list: ");

        for(T item : items)
        {
            System.out.print(item +" ");
        }//end for (printing items)
        System.out.println();

        for(int x=(items.size())-1; x>=0; x--)
        {
            T value=items.get(x);
            push(value);
        }//end for (pushing items back onto list)
    }//end print
    //Method Name           :lengthIs
    //Parameters            :none
    //Return Values         :an integer, numElements
    //Partners              :none
    //Description           :returns the number of elements in the list
    public Integer lengthIs()
    {
        return list.lengthIs();
    }//end lengthIs
    //Method Name           :isEmpty
    //Parameters            :none
    //Return Values         :a boolean
    //Partners              :none
    //Description           :will return true of firstNode is empty
    public boolean isEmpty()
    {
        boolean empty=true;

        if(list.isEmpty())
        {
            return empty;
        }//end if
        else
        {
            empty=false;
            return empty;
        }//end else
    }//end isempty
    //Method Name           :clear
    //Parameters            :none
    //Return Values         :none
    //Partners              :none
    //Description           :clears list
    public void clear()
    {
        list.clear();
    }//end clear
}
