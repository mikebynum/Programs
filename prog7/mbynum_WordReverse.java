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
import java.util.Scanner;

public class mbynum_WordReverse
{
    public static void main(String[] args)
    {
        mbynum_Stack<Character> word=new mbynum_Stack<Character>();
        Scanner input=new Scanner (System.in);
        String sentence;

        System.out.print("\nPlease enter a sentence and I will reverse the" +
                          "letters in the words.\n");
        System.out.print("\nPlease enter the sentence:  ");
        sentence=input.nextLine();

        System.out.print("\nThe new reversed sentence is:  ");

        String[] reverse = sentence.split("\\s");

        String modstring=new String();

        for(int x=0; x<reverse.length; x++)
        {
            String letter=reverse[x];

            for(int y=0; y<letter.length(); y++)
            {
                char temp=letter.charAt(y);
                word.push(temp);
            }//end for

            while(!word.isEmpty())
            {
                modstring=modstring+word.pop();
            }
            modstring=modstring + " ";
        }//end for
        System.out.println(modstring);
        System.out.println();
    }//end main
}//end reverse
