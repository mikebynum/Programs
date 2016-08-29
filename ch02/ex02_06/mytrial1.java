import java.util.Scanner;

public class mytrial1
{
   public static void main ( String args[] )
   {
      Scanner colts = new Scanner (System.in);

      int area;
      int base;
      int hight;

      System.out.print("Enter base: ");
      base = colts.nextInt();

      System.out.print("Enter hight: ");
      hight = colts.nextInt();

      area = (base*hight)/2;

      System.out.printf("You have now figured out the area! It is equal to: %d\n", area);
   }
}

