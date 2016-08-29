/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mike
 */
public class exercise1
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        final int MAX_ROWS = 10;
        int space;

        System.out.println("(A)");

        for (int row = 1; row <= MAX_ROWS; row++)
        {
            for (int star = 1; star <= row; star++)
                System.out.print ("*");

            System.out.println();
        }

        System.out.println("\n(B)");

        for (int row = MAX_ROWS; row > 0; row--)
        {
            for (int star = 1; star <= row; star++)
                System.out.print ("*");

            System.out.println();
        }

        System.out.println("\n(C)");

        for (int row = MAX_ROWS; row > 0; row--)
        {
            for (int star = 1; star <= row; star++)
                System.out.print ("*");

            System.out.println();
        }

        System.out.println("\n(D)");

        for (int row = 1; row <= MAX_ROWS; row++)
        {
            for (int star = 1; star <= row; star++)
                System.out.print ("*");

            System.out.println();
        }

        System.out.println("\n(E)");

        space=7;

        for (int row = 1; row <= 15; row=row+2)
        {
            for (int linespace=space; linespace > 0; linespace--)
                System.out.print(" ");
            for (int star = 1; star <= row; star++)
                System.out.print ("*");

            space--;

            System.out.println();
        }

        space=1;

        for (int row = 13; row > 0; row=row-2)
        {
            for (int linespace=space; linespace > 0; linespace--)
                System.out.print(" ");
            for (int star = 1; star <= row; star++)
                System.out.print ("*");

            space++;
            System.out.println();
        }
    }
}

