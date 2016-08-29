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

public class exercise4
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
            {
                correct();
            }

            else
            {
                wrong();
            }
        }
        while(guess != result);
    }

    private static void correct()
    {
        Random randomNumber = new Random();
        int num = randomNumber.nextInt(4);

        switch(num)
        {
            case 0:
                System.out.println("Very good!\n");
                break;
            case 1:
                System.out.println("Excellent!\n");
                break;
            case 2:
                System.out.println("Nice Work!\n");
                break;
            case 3:
                System.out.println("Keep up the good work!\n");
                break;
        }
    }

    private static void wrong()
    {
        Random randomNumber = new Random();
        int num = randomNumber.nextInt(4);

        switch(num)
        {
            case 0:
                System.out.println("No. Please try again.\n");
                break;
            case 1:
                System.out.println("Wrong.  Try once more.\n");
                break;
            case 2:
                System.out.println("Don't give up!\n");
                break;
            case 3:
                System.out.println("No.  Keep trying.\n");
                break;
        }
    }

}
