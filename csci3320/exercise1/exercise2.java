/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mike
 */
import java.util.Random;

public class exercise2
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        int heads=0;
        int tales=0;
        for (int x = 1; x<=100; x++)
        {
            int flipNumber=coinFlip();

            if (flipNumber==1)
                heads++;
            else
                tales++;
        }

        System.out.print("Heads:  " + heads + "\n");
        System.out.print("Tales:  " + tales + "\n");
    }

    private static int coinFlip()
    {
        Random randomNumber = new Random();
        int flip = randomNumber.nextInt(2);
        return flip;
    }


}
