/* Name  	:Mike Bynum
   Class 	:1400-02
   Program #	:Program
   Due Date	:

   Honor Pledge	:On my honor as a student of the University of Nebraska at Omaha, I have neither given nor received unauthorized help on this homework assignment.

Name	:Mike Bynum
NUID	:343
Email	:mbynum@unomaha.edu

Partners: None

Description of program
*/
import java.util.Scanner;

public class pool

{
	private int width;
	private int length;
	
	public pool()
	{
		width=length=4;
	}

	public void setSize(int w, int l)
	{
		width = w;
		length = l;
	}

	public void printSize()
	{
		System.out.printf("%d by %d\n", width, length);
	}

	public void printPoolLayout()
	{
		for (int i=0; i<length; ++i)
		{
			for (int j=0; j<width; ++j)
			{
				System.out.print("*");
			}
			System.out.println();
		}
	}
}



