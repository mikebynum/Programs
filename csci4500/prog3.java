import java.io.*;
import java.util.*;
import java.lang.Integer;
import java.lang.Math;
import java.lang.Double;
        
public class prog3
{
    private static int MSIZE = 0; /* the size of memory that is to be managed by the buddy 
         *  system.  It is also the size of the single free block (at location
         * 0) that will exist when the simulation begins.  The value will 
         * always be less than 2^31 */
    private static int ASIZE = 0; /* Is less than or equal to MSIZE, and is the number of bytes
         * in the smallest block that is to be allocated to satisfy any 
         * allocation request.  */
    
    /* MSIZE and ASIZE must be a power of 2 */
    
    private static int [][] request = new int [1000][2];
    private static String [] allocation = new String [1000];
    private static String allocate = "allocate";
    private static String deallocate = "deallocate";
    private static String minus = "-";
    private static double [] roundedBytes = new double [1000];
    private static List [][] memory = new ArrayList[30][2];
    private static Object [][] allocated = new Object [1000][2]; //can hold up to 1000 IDs with (address, size)
    private static int [][] deferredRequest = new int [1000][2];
    private static int memAddress=0;
    private static int max = 0;
    private static int min = 0;

    
    
    @SuppressWarnings("unchecked")
    public static boolean allocate(int i, int powerInt, int maxPower, double power)
    {
        boolean allocationSuccess = false;
        allocation[i] = "allocate";
                double openSpot = -1;
                boolean foundEmpty = false;
                //System.out.println("Request ID "+request[i][0]+" : "+allocation[i]+" "+request[i][1]+" bytes rounded is " +roundedBytes[i] + " power is " + power);
                System.out.println("Request ID "+request[i][0]+" : "+allocation[i]+" "+request[i][1]+" bytes. ");
                for (int x=powerInt; x<= maxPower; x++)
                {
                    if (memory[x][0].isEmpty())
                    {
                        //System.out.println("Nothing at power "+ x);
                        continue;
                    }
                    else
                    {
                        //System.out.println("Found an open spot at power " +x);
                        openSpot = x;
                        foundEmpty = true;
                        break;
                    }
               }
                if(!foundEmpty)
                {
                    System.out.println("Request deferred. ");
                    //System.out.println("Adding request to the deferred list");
                    deferredRequest[i]=request[i];
                    
                     for (int r=0; r<max; r++) 
                     {
                        for (int c=0; c<2; c++) 
                        {
                            //System.out.print(" " + deferredRequest[r][c]);
                        }
                        //System.out.println("");
                     }
                }
                 int openSpotInt = (int) openSpot;
                // if there was open spot found
                if (openSpotInt >= 0)
                {
                    double memSizeTemp = Math.pow(2, openSpot);
                    int memSizeTempSmall = (int)memSizeTemp;

                    while (memSizeTempSmall > roundedBytes[i])
                    {
                        memSizeTempSmall = memSizeTempSmall/2; 
                        //System.out.println("Going to split 2^"+openSpotInt+" up into 2 smaller sections of "+memSizeTempSmall +" since "+memSizeTemp+" is greater than "+roundedBytes[i]);
                        memSizeTemp = memSizeTempSmall;
                       
                        // SPLIT
                        
                        memory[openSpotInt-1][0].add(0,memSizeTempSmall);
                        memory[openSpotInt-1][0].add(1,memSizeTempSmall);
                        if (memory[openSpotInt][1].get(0).equals(0))
                        {
                            memory[openSpotInt-1][1].add(0,0);
                            memAddress = (int)memSizeTemp;
                            memory[openSpotInt-1][1].add(1,memAddress);
                            
                        }
                        else
                        {
                         Object mem = memory[openSpotInt][1].get(0);  
                         Integer memstart = (Integer)mem;
                         Object memsiz = memory[openSpotInt][0].get(0);  
                         Integer memsize = (Integer)memsiz;
                         Integer memi = memstart+memSizeTempSmall;
                         
                         //System.out.println("memi: "+memi+" memory1: "+memory[openSpotInt][1].get(0)+" memAddress: "+memAddress+" memory0: "+memory[openSpotInt][0].get(0)+" memSizeTempSmall: "+memSizeTempSmall);
                            memory[openSpotInt-1][1].add(0,memory[openSpotInt][1].get(0));
                            memory[openSpotInt-1][1].add(1,memi);
                        }
                        
                        
                        //System.out.println("Split "+memory[openSpotInt-1][0] +" bytes with address of "+memory[openSpotInt-1][1]);
                        
                        // DELETE
                        memory[openSpotInt][0].remove(0);
                        memory[openSpotInt][1].remove(0);
                        //System.out.println("After the delete "+memory[openSpotInt][0] +" bytes with address of "+memory[openSpotInt][1]);
                        openSpotInt--;
                    }
                    // Do the actual allocation
                    if (memSizeTempSmall==roundedBytes[i])
                    {
                        //System.out.println("Going to allocate memory at "+openSpotInt);
                        //System.out.println("Removing "+memory[openSpotInt][0].get(0) +" bytes with address of "+memory[openSpotInt][1].get(0));
                        
                        allocated[request[i][0]][0]=memory[openSpotInt][0].remove(0); // size(bytes)
                        allocated[request[i][0]][1]=memory[openSpotInt][1].remove(0); // address
                        //System.out.println("After the remove "+memory[openSpotInt][0] +" bytes with address of "+memory[openSpotInt][1]);
                       allocationSuccess = true;
                    }
                }
                if(allocationSuccess)
                {
                    Integer hex = (Integer)allocated[request[i][0]][1];
                    String hex1 = Integer.toHexString(hex);
                    int hexInt = Integer.parseInt(hex1);
                    System.out.printf("Success; address = 0x%08d\n",hexInt);
                }
                return allocationSuccess; 
    }
    
    @SuppressWarnings("unchecked")
    public static void main (String[] args) throws IOException
    {
        //String number;
        int count;

        boolean memSize = true;
        double power;
        int maxPower;
        int minPower;     
        int requestCount = 0;
        
        String [] line = new String [3];
                
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String s;
        
        while ((s = in.readLine()) != null && s.length() != 0)
        {
            count = 0;
            //System.out.print("The line is:\n");
            //System.out.println(s);
            //System.out.println("The length is"+s.length());
            StringTokenizer st = new StringTokenizer (s);
            
            while (st.hasMoreTokens())
            {
                line[count] = st.nextToken();
                 
                //System.out.printf("The token at %d is %d\n", count, line[count]);
                count++;
                
            }
            /***************************************************************/
            /* Read the first line in and put numbers in variables         */
            /***************************************************************/
            if(memSize)
            {
                int temp = Integer.parseInt(line[0]);
                MSIZE = temp; // size of the memory that is to be managed by 
                // the buddy system,and also the size of the single free block 
                //(at location 0) that will exist when the simulation begins
                // The value of MSIZE will always be less than 2^31

                temp = Integer.parseInt(line[1]);
                ASIZE = temp; // less than or equal to MSIZE, and is the 
                //number of bytes in the smallest block that is to be allocated
                //to satisfy any allocation request

                              
                memSize = false;
                  
            }
            /***************************************************************/
            /**/
            /***************************************************************/
            else
            {
                int temp = Integer.parseInt(line[0]);
                request[requestCount][0]= temp;
                
                allocation[requestCount]= line[1];
                
                temp = Integer.parseInt(line[2]);
                request[requestCount][1]= temp; 
                
               // System.out.println("ID "+request[requestCount][0]+" is "+allocation[requestCount]+" "+request[requestCount][1]+" bytes");
                //System.out.println("Request count is "+requestCount);
                requestCount++;
            }
        }
        
        maxPower = (int)(Math.log(MSIZE)/Math.log(2)); // power of 2 for max
        minPower = (int)(Math.log(ASIZE)/Math.log(2)); // power of 2 for min
        max = maxPower;
        min = minPower;
        
        int segment = MSIZE/ASIZE;
        
        //System.out.println("The memory size is "+MSIZE+" and the allocation size is "+ASIZE + " \nmax and min powers are: "+maxPower + " "+minPower);
        
        /**************************/
        /* Initialize memory      */
        /**************************/
        
        for(int i = 0; i < 30; i++) 
        {
            for(int j=0; j<2; j++)
            {
                memory[i][j]= new ArrayList();  
            }
        }
        memory[maxPower][0].add(MSIZE);
        memory[maxPower][1].add(0);

        
        //System.out.println("First thing is " + memory[10][0].get(0)+ " second thing is "+memory[10][1].get(0));
        
        
        /**********************************************************************/
        /* Go through each request                                            */
        /**********************************************************************/
        
        for (int i=0; i<requestCount; i++)
        {    
            System.out.println("--------------------------");
            power = Math.ceil(Math.log(request[i][1])/Math.log(2));
            //System.out.println("The power is "+power);
            roundedBytes[i] = Math.pow(2,power);
            //System.out.println("The rounded bytes is "+roundedBytes[i]);
            
            if (roundedBytes[i]<ASIZE && roundedBytes[i]!=0)
            {
                //System.out.println("It gets here "+ASIZE);
                roundedBytes[i] = ASIZE;
                power = Math.log(ASIZE)/Math.log(2);
                
            }
            
            int powerInt = (int)power;
            /******************************************************************/
            /* D E A L L O C A T E      M E M O R Y                           */
            /******************************************************************/
            if (minus.equals(allocation[i]))
            {
                request[i][1] = 0;
                int id = request[i][0];
                allocation[i] = "deallocate";
                System.out.println("Request ID "+id+" : "+allocation[i]);
                System.out.println("Success.");
                power = Math.ceil(Math.log((Integer)allocated[id][0])/Math.log(2));
                powerInt = (int)power;
                //System.out.println("Going to deallocate "+id+" that is allocated at address "+allocated[id][1]+" and has "+allocated[id][0]+" bytes and has the power of "+power);
                //doing the deallocation
                
                boolean buddy;                
                //check to see if its "buddy" is free
                if (memory[powerInt][0].isEmpty())
                {
                    //System.out.println("There is NO buddy!!");
                    memory[powerInt][0].add(allocated[id][0]);// add back the number of bytes
                    memory[powerInt][1].add(allocated[id][1]);// add back the address location
                    //deferredRequest[id][1] = 0;
                    //deferredRequest[id][0] = 0;
                }
                else
                {
                    
                    //System.out.println("The memory currently has "+memory[powerInt][0]+ "bytes with address "+memory[powerInt][1]+" and the allocated has "+allocated[id][0]+" bytes at address "+allocated[id][1]);
                    //System.out.println("There is a buddy!");
                    //System.out.println("Going to merge.");
                    Integer test1 = (Integer)memory[powerInt][1].get(0);
                    Integer test1Mem = (Integer)memory[powerInt][0].get(0);
                    Integer test2 = (Integer)allocated[id][1];
                    Integer test1a;
                    // if the address of the memory is less than the allocated, use the memory address for the addition of the buddy
                    if(test1<test2)
                    {
                       test1Mem = 2*test1Mem;
                        memory[powerInt+1][0].add(0,test1Mem);
                        memory[powerInt+1][1].add(0,test1);
                        memory[powerInt][0].remove(0);
                        memory[powerInt][1].remove(0);
                    }
                    else
                    {
                        test1Mem = 2*test1Mem;
                        memory[powerInt+1][0].add(0,test1Mem);
                        memory[powerInt+1][1].add(0,test2);
                        memory[powerInt][0].remove(0);
                        memory[powerInt][1].remove(0);
                    }
                    powerInt++;
                    while (memory[powerInt][0].size()==2)
                    {
                        //System.out.println("We need to merge again");
                        //System.out.println("The memory currently has "+memory[powerInt+1][0]+ "bytes with address "+memory[powerInt+1][1]+" and the smaller has "+memory[powerInt][0]+" bytes at address "+memory[powerInt][1]);
                        test1 = (Integer)memory[powerInt][1].get(0);
                        test2 = (Integer)memory[powerInt][1].get(1);
                        //System.out.println("test1 is "+test1);
                        //System.out.println("test2 is "+test2);
                        test1Mem = (Integer)memory[powerInt][0].get(0);
                        /*if(memory[powerInt+1][0].isEmpty()&&(test1<test1a))
                        {
                            
                            test2 = test1;
                            System.out.println("Since "+(powerInt+1)+" is empty test2 is equal to test1 and is "+test2);
                        }
                        else if(memory[powerInt+1][0].isEmpty()&&(test1>test1a))
                        {
                            test2 = test1a;
                            System.out.println("Since "+(powerInt+1)+" is empty test2 is equal to test1a and is "+test2);       
                        }
                        else
                        {
                            
                            test2 = (Integer)memory[powerInt+1][1].get(0);
                            System.out.println("Since "+(powerInt+1)+" is not empty test2 is "+test2);
                        }*/
                        
                        
                        if(test1<test2)
                        {
                            //System.out.println("test1 ("+test1+") is less than test2("+test2+")-adding test1");
                            test1Mem = 2*test1Mem;
                            memory[powerInt+1][0].add(0,test1Mem);
                            memory[powerInt+1][1].add(0,test1);
                            memory[powerInt][0].remove(0);
                            memory[powerInt][1].remove(0);
                            memory[powerInt][0].remove(0);
                            memory[powerInt][1].remove(0);    
                        }
                        else
                        {
                            //System.out.println("test1 ("+test1+") is greater than test2("+test2+")-adding test2");
                            test1Mem = 2*test1Mem;
                            memory[powerInt+1][0].add(0,test1Mem);
                            memory[powerInt+1][1].add(0,test2);
                            memory[powerInt][0].remove(0);
                            memory[powerInt][1].remove(0);
                            memory[powerInt][0].remove(0);
                            memory[powerInt][1].remove(0);
                        }
                        //System.out.println("The memory NOW has "+memory[powerInt+1][0]+ "bytes with address "+memory[powerInt+1][1]);
                        powerInt++;
                    }
                    
                    
                }
                
                
                boolean deferredEmpty = true;
                
                for (int x=0; x<deferredRequest.length; x++)
                {
                    if(deferredRequest[x][0]!=0)
                    {
                        deferredEmpty = false;
                        //System.out.println("There are items in the deferred list");
                        //System.out.println("Checking to see if request "+deferredRequest[x][0]+" with bytes of "+deferredRequest[x][1]+" can be allocated");
                        
                        power = Math.ceil(Math.log(request[x][1])/Math.log(2));

                        if (roundedBytes[x]<ASIZE && roundedBytes[x]!=0)
                        {

                            power = Math.log(ASIZE)/Math.log(2);

                        }
                        
                        powerInt = (int)power;
                        boolean testDeferred = allocate(x, powerInt, maxPower, power);                        
                        if(testDeferred)
                        {
                            //System.out.println("The allocation was successful, now goint to remove from deferred list");
                            //System.err.print
                            deferredRequest[x][1] = 0;
                            deferredRequest[x][0] = 0;
                        }
                    }
                }
            }
            /******************************************************************/
            /* A L L O C A T E      M E M O R Y                               */
            /******************************************************************/
            else
            {
                allocate(i, powerInt, maxPower, power);
            }    
        }// end of going through requests   
    }// end main
}// end class
