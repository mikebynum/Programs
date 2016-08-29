/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mike
 */
import java.util.Random;

public class exercise5
{
    public static void main(String[] args)
    {
        Random diceRoll = new Random();
        int rolls=36000;
        int[] diceArray = new int[rolls];
        int two=0;
        int three=0;
        int four=0;
        int five=0;
        int six=0;
        int seven=0;
        int eight=0;
        int nine=0;
        int ten=0;
        int eleven=0;
        int twelve=0;

        for(int x=0; x<rolls; x++)
        {
            int dice1 = 1 + diceRoll.nextInt(6);
            int dice2 = 1 + diceRoll.nextInt(6);
            diceArray[x] = dice1 + dice2;
        }

        for(int x=0; x<diceArray.length; x++)
        {
            if(diceArray[x]==2)
                two++;
            else if(diceArray[x]==3)
                three++;
            else if(diceArray[x]==4)
                four++;
            else if(diceArray[x]==5)
                five++;
            else if(diceArray[x]==6)
                six++;
            else if(diceArray[x]==7)
                seven++;
            else if(diceArray[x]==8)
                eight++;
            else if(diceArray[x]==9)
                nine++;
            else if(diceArray[x]==10)
                ten++;
            else if(diceArray[x]==11)
                eleven++;
            else
                twelve++;
        }
            System.out.printf("The number of two's are:  %d\nThe number of "
                               + "three's are:  %d\nThe number of four's"
                               + " are:  %d\nThe number of five's are:  "
                               + "%d\nThe number of six's are:  %d\nThe"
                               + " number of seven's are:  %d\nThe number of"
                               + " eight's are:  %d\nThe number of nine's are:"
                               + "  %d\nThe number of ten's are:  %d\nThe"
                               + " number of eleven's are:  %d\nThe number "
                               + "of twelve's are:  %d", two,three,four,five,
                               six,seven,eight,nine,ten,eleven,twelve);
            System.out.println();
        
    }

}
