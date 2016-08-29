/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mike
 */
import java.util.Random;
import java.util.Scanner;

public class exercise3
{
    public static void main(String[] args)
    {
        Random randomNumber = new Random();
        int num1 = randomNumber.nextInt(10);
        int num2 = randomNumber.nextInt(10);
        int result = num1*num2;
        int guess=-1;

        Scanner input = new Scanner (System.in);

        do
        {
            System.out.printf("How much is %d time %d?  ", num1,num2);
            guess=input.nextInt();

            if (guess==result)
                System.out.println("Very good!");

            else
                System.out.println("No.  Pleae try again.");
        }
        while(guess != result);

    }

}
