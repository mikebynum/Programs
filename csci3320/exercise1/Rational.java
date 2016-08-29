

public class Rational
{
   private int numerator; // numerator of the fraction
   private int denominator; // denominator of the fraction

   // no-argument constructor, initializes this Rational to 1
   public Rational()
   {
      numerator = 1;
      denominator = 1;
   } // end Rational no-argument constructor

   // initialize numerator part to n and denominator part to d
   public Rational( int theNumerator, int theDenominator )
   {
      numerator = theNumerator;
      denominator = theDenominator;
      reduce();
   } // end two-argument constructor

   // add two Rational numbers
   public Rational sum( Rational right )
   {
      int resultDenominator = denominator * right.denominator;
      int resultNumerator = numerator * right.denominator +
         right.numerator * denominator;

      return new Rational( resultNumerator, resultDenominator );
   } // end method sum

   // subtract two Rational numbers
   public Rational subtract( Rational right )
   {
      int resultDenominator = denominator * right.denominator;
      int resultNumerator = numerator * right.denominator -
         right.numerator * denominator;

      return new Rational( resultNumerator, resultDenominator );
   } // end method subtract

   // multiply two Rational numbers
   public Rational multiply( Rational right )
   {
      return new Rational( numerator * right.numerator,
         denominator * right.denominator );
   } // end method multiply

   // divide two Rational numbers
   public Rational divide( Rational right )
   {
      return new Rational( numerator * right.denominator,
         denominator * right.numerator );
   } // end method divide

   // reduce the fraction
   private void reduce()
   {
      int gcd = 0;
      int smaller;

      // find the greatest common denominator of the two numbers
      smaller = Math.min( numerator, denominator );

      for ( int divisor = smaller; divisor >= 2; divisor-- )
      {
         if ( numerator % divisor == 0 && denominator % divisor == 0 )
         {
            gcd = divisor;
            break;
         } // end if
      } // end for

      // divide both the numerator and denominator by the gcd
      if ( gcd != 0 )
      {
         numerator /= gcd;
         denominator /= gcd;
      } // end if
   } // end for

   // return String representation of a Rational number
   public String toString()
   {
     return numerator + "/" + denominator;
   } // end method toString

   // return floating-point String representation of
   // a Rational number
   public String toFloatString( int digits )
   {
      double value = ( double ) numerator / denominator;
      // builds a formatting string that specifies the precision
      // based on the digits parameter
      return String.format( "%." + digits + "f", value );
   } // end method toFloatString
} // end class Rational
