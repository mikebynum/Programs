
public class exercise8
{

    public static void main(String[] args)
    {
      int[] a = {45,60,40,95,75,35,25,65};
      int comparison=0;

      showArray ("Original",a);
      comparison = bubbleSort (a);
      showArray ("Sorted",a);
      System.out.println("Comparisons: " + comparison);
    }

    public static void showArray (String label, int foo[])
    {
        System.out.println("Items in " + label + " order: ");
            for(int value : foo)
                System.out.printf("%4d", value);
            System.out.println("\n");
    }

    public static int bubbleSort(int foo[])
    {
        int comparison=0;
        for(int pass=0; pass<foo.length-1; pass++)
        {
            for(int pos=0; pos<foo.length-1; pos++)
            {
                if(foo[pos]>foo[pos+1])
                {
                    int temp=foo[pos];
                    foo[pos]=foo[pos+1];
                    foo[pos+1]=temp;
                }
                comparison++;
            }
            showArray("Pass #" + pass,foo);
        }
        return comparison;
    }
}
