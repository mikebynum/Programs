
public class exercise8b
{

    public static void main(String[] args)
    {
      int[] a = {2,6,4,8,10,12,89,68,45,37};//2,6,4,8,10,12,89,68,45,37
      //45,60,40,95,75,35,25,65
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
        boolean didSwap;

        for(int pass=1; pass<foo.length; pass++)
        {
            didSwap=false;
            for(int pos=0; pos<foo.length-pass; pos++)
            {
                if(foo[pos]>foo[pos+1])
                {
                    int temp=foo[pos];
                    foo[pos]=foo[pos+1];
                    foo[pos+1]=temp;
                    didSwap = true;
                }
                comparison++;            
            }
            if(!didSwap)
            {
                return comparison;
            }
            showArray("Pass #" + pass,foo);
        }
        return comparison;
    }
}
