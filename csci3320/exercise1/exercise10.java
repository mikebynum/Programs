import java.util.Scanner;

public class exercise10
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner (System.in);

        System.out.print("Please input a string and I will tell you the length:"
                + "  ");
        String sentence = input.nextLine();
        
        int length = sentence.length();

        System.out.println("The length of the sentence is:  " + length);

        for (int x=1;x<=length;x++)
        {
            System.out.print("  ");
        }

        System.out.println(sentence);
    }
}
