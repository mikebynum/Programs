/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mike
 */
public class mbynum_UnsortedEmployeeList
{
    final Integer DEFCAP=10;
    mbynum_Employee[] list;
    Integer numElements;
    Integer origCap;

    public mbynum_UnsortedEmployeeList()
    {
        list=new mbynum_Employee[DEFCAP];
        numElements=0;
        origCap=DEFCAP;
    }

    public mbynum_UnsortedEmployeeList(Integer size)throws Exception
    {
        if(size>0)
        {
            list=new mbynum_Employee[size];
            origCap=0;
        }
        else
        {
            throw new Exception();
        }
    }

    public void addItem(mbynum_Employee employee)
    {
        if(list.length==numElements)
        {
            enlarge();
        }

        list[numElements]=employee;
        numElements++;
    }

    public boolean removeItem(mbynum_Employee employee)
    {
        int location=find(employee);
        boolean found=false;

        if(location==-1)
        {
            return found;
        }
        else if(location>=0)
        {
            list[location]=list[numElements-1];
            numElements--;
            found=true;
            return found;
        }

    }

    public boolean findItem(mbynum_Employee employee)
    {
        int location=find(employee);
        boolean found=false;

        if(location==-1)
        {
            return found;
        }
        else if(location>=0)
        {
            found=true;
            return found;
        }
    }

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

    public int lengthIs()
    {
        return numElements;
    }

    public String toString()
    {
        for(int i=0; i<list.length; i++)
        {
            return String.format(list[i]+"string\n");
        }
    }

    public void clear()
    {
        numElements=0;
        list=null;
        list = new mbynum_Employee[origCap];
    }

    private void enlarge()
    {
        
        mbynum_Employee [] larger=new mbynum_Employee[lengthIs() + origCap];
        for(int i=0; i<list.length; i++)
        {
            larger[i]=list[i];
        }
        list=larger;
    }

    private int find(mbynum_Employee employee)
    {
        int num=0;
        return num;
    }

}