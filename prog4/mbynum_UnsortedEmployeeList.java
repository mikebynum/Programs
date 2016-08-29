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
public class mbynum_UnsortedEmployeeList
{
    final Integer DEFCAP=10;//default value of list size
    mbynum_Employee[] list; //new mbynum_employee array
    Integer numElements=0;  //number of elements in array
    Integer origCap=0;      //original size of array
    //Method Name           :mbynum_UnsortedEmployeeList
    //Parameters            :none
    //Return Values         :none
    //Partners              :none
    //Description           :constructor with no parameters
    public mbynum_UnsortedEmployeeList()
    {
        list=new mbynum_Employee[DEFCAP];
        numElements=0;
        origCap=DEFCAP;
    }
    //Method Name           :mbynum_UnsortedEmployeeList
    //Parameters            :size of array, an integer
    //Return Values         :none
    //Partners              :none
    //Description           :constructor with parameters
    public mbynum_UnsortedEmployeeList(Integer size)throws Exception
    {
        if(size>0)
        {
            list=new mbynum_Employee[size];
            origCap=size;
        }
        else
        {
            throw new Exception();
        }
    }
    //Method Name           :addItem
    //Parameters            :employee,an mbynum_Employee object
    //Return Values         :none
    //Partners              :none
    //Description           :adds an item to the list
    public void addItem(mbynum_Employee employee)
    {
        if((numElements).equals(list.length))
        {
            enlarge();
        }

        list[numElements]=employee;
        numElements++;
    }
    //Method Name           :removeItem
    //Parameters            :employee, an mbynum_Employee object
    //Return Values         :found, a boolean
    //Partners              :none
    //Description           :removes item from list and returns true if successful
    public boolean removeItem(mbynum_Employee employee)
    {
        int location=find(employee);
        boolean found;

        if(location==-1)
        {
            found=false;
            return found;
        }
        else
        {
            list[location]=list[numElements-1];
            numElements--;
            found=true;
            return found;
        }
    }
    //Method Name           :findItem
    //Parameters            :employee, and mbynum_Employee object
    //Return Values         :found , a boolean
    //Partners              :none
    //Description           :finds item in list and returns if it is successful
    //                       and the location of the item
    public boolean findItem(mbynum_Employee employee)
    {
        boolean found=false;
        System.out.printf("location is %d",find(employee));
        if(find(employee)==-1)
        {
            return found;
        }
        else
        {
            found=true;
            return found;
        }
    }
    //Method Name           :isEmpty
    //Parameters            :none
    //Return Values         :empty, a boolean
    //Partners              :none
    //Description           :returns T/F whether list is empty
    public boolean isEmpty()
    {
        boolean empty=true;

        if(numElements==0)
        {
            return empty;
        }
        else
        {
            empty=false;
            return empty;
        }
    }
    //Method Name           :lengthIs
    //Parameters            :none
    //Return Values         :numElements, an integer
    //Partners              :none
    //Description           :returns the length of the list
    public int lengthIs()
    {
        return numElements;
    }
    //Method Name           :toString
    //Parameters            :none
    //Return Values         :modified string
    //Partners              :none
    //Description           :modifies the toString
    public String toString()
    {
        String empl="";
        for(int x=0;x<numElements;x++)
        {
            empl=empl+list[x]+"\n";
        }
        return empl;
    }
    //Method Name           :clear
    //Parameters            :none
    //Return Values         :none
    //Partners              :none
    //Description           :clears list
    public void clear()
    {
        numElements=0;
        list=null;
        list = new mbynum_Employee[origCap];
    }
    //Method Name           :enlarge
    //Parameters            :none
    //Return Values         :none
    //Partners              :none
    //Description           :enlarges list
    private void enlarge()
    {
        
        mbynum_Employee [] larger=new mbynum_Employee[lengthIs() + origCap];
        for(int i=0; i<list.length; i++)
        {
            larger[i]=list[i];
        }
        list=larger;
    }
    //Method Name           :find
    //Parameters            :employee, an mbynum_Employee object
    //Return Values         :num, an integer
    //Partners              :none
    //Description           :finds the employee in list and returns location
    private int find(mbynum_Employee employee)
    {
        int num=-1;

        if(numElements==0)
        {
            //System.out.println("List is empty!");
            return num;
        }
        else
        {
            for(int i=0;i<list.length;i++)
            {
                if(list[i].equals(employee))
                {
                    num=i;
                }//end if
            }//end for loop
            return num;
        }//end else
    }//end find method

}//end class