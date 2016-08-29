// Name         : Mike Bynum
// Class        : CSCI 1620-301
// Program #    : 1
// Due Date     : 1 July 2010
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
// Description: This program will have a user enter up to ten numbers and will
//              output the largest, smallest, sum, and average of the numbers.

import java.util.Scanner;

public class mbynum_SimpleList
{
    private final int ARRAY_MAX=10;
    private int[] list;
    private int count=0;
    private int sum=0;
    private int average=0;
    private int large=0;
    private int small=0;
/*
 * Method Name      :mbynum_SimpleList
 * Parameters       :none
 * Return Values    :none
 * Parters          :none
 * Description      :This constructor will initialize the array to 10 elements.
 */
    public mbynum_SimpleList()
    {
        list=new int[ARRAY_MAX];
    }
/*
 * Method Name      :getData
 * Parameters       :none
 * Return Values    :none
 * Parters          :none
 * Description      :This method will ask the user how many values they will be
 *                   entering and prompt them for that many.
 */
    public void getData()
    {
        Scanner data=new Scanner(System.in);

        System.out.print("Welcome to the Simple List Class.\n");
        do
        {
            System.out.print("How many values will you be entering? ");
            count=data.nextInt();
        }
        while(count<=0||count>ARRAY_MAX);

        for(int x=0;x<count;x++)
        {
            System.out.print("Please enter an integer value: ");
            list[x]=data.nextInt();
        }
    }
/*
 * Method Name      :processData
 * Parameters       :none
 * Return Values    :none
 * Parters          :none
 * Description      :This method will determine the sum, average, largest and
 *                   smallest of the array.
 */
    public void processData()
    {
        small=list[0];
        large=list[0];

        for(int x=0;x<count;x++)
        {
            if(list[x]<small)
            {
                small=list[x];
            }
            if(list[x]>large)
            {
                large=list[x];
            }
            sum+=list[x];
        }
        average=sum/count;
    }
/*
 * Method Name      :displayData
 * Parameters       :none
 * Return Values    :none
 * Parters          :none
 * Description      :This method will diplay the members of the array as well as
 *                   the sum, average, largest, and smallest.
 */
    public void displayData()
    {
        for(int x=1;x<=count;x++)
        {
            System.out.print("Value " + x + " = " + list[x-1]);
            System.out.println();
        }
        System.out.println();
        System.out.print("Sum of all values = "+sum);
        System.out.println();
        System.out.print("Average of all values = "+average);
        System.out.println();
        System.out.print("Largest value = "+large);
        System.out.println();
        System.out.print("Smallest value = "+small);
        System.out.println();
        System.out.println();
    }
/*
 * Method Name      :clearData
 * Parameters       :none
 * Return Values    :none
 * Parters          :none
 * Description      :This method will put all the data back to its original
 *                   values.
 */
    public void clearData()
    {
        sum=0;
        average=0;
        large=0;
        small=0;
        count=0;
        System.out.print("The list is now cleared.\n");
    }
}