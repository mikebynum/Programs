
import java.io.*;
import java.util.StringTokenizer;
import java.lang.Integer;


/**
 *
 * @author zmb0440
 */
public class prog2 
{
    // array to hold processes when loaded
    private static int [][] queueNotSubmitted = new int [26][9];
    
    // create the array to hold all ready processes
    private static int [][][] queueReady = new int [11][26][9];
    
    // create the array to hold RUNNING processes
    private static int [][] running = new int [5][9];
    private static int [] quantumStatus = new int [6];
    private static int [] processorIdle = new int [6];
    
    // create the array to hold COMPLETED processes
    private static int [][] completed = new int[26][9];
    private static int [] completeTimes = new int [26];
    
    //create the array to hold BLOCKED processes*/
    private static int [][] blocked = new int [26][9];
    
    private static int time;
    private static int ready_priority;
    
    public static void clearArrays()
    {
        int i, j;
        
        for(i=0; i<26; i++)
        {
            for(j=0; j<9; j++)
            {
                queueNotSubmitted[i][j]=0;
            }    
        }
        for(int x=0; x<11; x++)
        {   
            for(i=0; i<26; i++)
            {
                for(j=0; j<9; j++)
                {
                    queueReady[x][i][j]=0;
                }    
            }
        }
        for(i=0; i<5; i++)
        {
            for(j=0; j<9; j++)
            {
                running[i][j]=0;
            }    
        }
        for(i=0; i<6; i++)
        {
                quantumStatus[i]=0;    
        }
        for(i=0; i<6; i++)
        {
                processorIdle[i]=0;    
        }
        for(i=0; i<26; i++)
        {
            for(j=0; j<9; j++)
            {
                completed[i][j]=0;
            }    
        }
        for(i=0; i<26; i++)
        {
            for(j=0; j<9; j++)
            {
                blocked[i][j]=0;
            }    
        }
        for(i=0; i<26; i++)
        {
                completeTimes[i]=0;    
        }
    }
    /*------------------------------------------------------------------------------
    //##############################################################################
    //------------------------------------------------------------------------------
    //
    // FUNCTION:    loadFromNotYetSubmitted               
    //
    //              Takes processes from the "Not-yet-submitted" queue and releases 
    //              them to the "ready" queue at the submit time
    //
    //------------------------------------------------------------------------------
    //##############################################################################
    //----------------------------------------------------------------------------*/

    public static void loadFromNotYetSubmitted (int numberProcess)
    {
         int i;
         int j;
         
             
         //System.out.print("In the loadFromNotYetSubmitted\n\n");
         //System.out.println("The countProcess is " + numberProcess);
         //System.out.println("The arrayPlaceholder is " + arrayPlaceholder);
         
        //for (i = 0; i<numberProcess; i++)
        //{
            // Iterate through each array elemement
            // and copy to the not submitted yet
          //  for (j=0; j<9; j++)
            //{
              //  System.out.print (queueNotSubmitted[i][j] + " ");
            //}
            //System.out.println();
        //}
      
      /*------------------------------------------------------------------------
      // increment through all available processes
      //----------------------------------------------------------------------*/
         for(i=0; i<numberProcess; i++)
         {
         /*---------------------------------------------------------------------
         // check if process has the appropriate release time
         // If the current time is the same as the time of submission.... 
         //-------------------------------------------------------------------*/
            if (queueNotSubmitted[i][2] == time)
            {
            /*------------------------------------------------------------------
            // determine the priority of the process
            //----------------------------------------------------------------*/
               int priority = queueNotSubmitted[i][1];
               //System.out.println ("The priority of process " + queueNotSubmitted[i][0] +" is " + priority);
            /*------------------------------------------------------------------
            // Look for an empty space in queueReady 
            //----------------------------------------------------------------*/
               for(j=0;j<numberProcess;j++)
               {
               /*--------------------------------------------------------------
               // When an empty space is found at the appropriate priority
               //------------------------------------------------------------*/
                  if( queueReady[priority][j][0] == 0 )
                  {
                  /*----------------------------------------------------------
                  // transfer the process to the ready queue
                  //--------------------------------------------------------*/
                     queueReady[priority][j][0] = queueNotSubmitted[i][0];
                     queueReady[priority][j][1] = queueNotSubmitted[i][1];
                     queueReady[priority][j][2] = queueNotSubmitted[i][2];
                     queueReady[priority][j][3] = queueNotSubmitted[i][3];
                     queueReady[priority][j][4] = queueNotSubmitted[i][4];
                     queueReady[priority][j][5] = queueNotSubmitted[i][5];
                     queueReady[priority][j][6] = queueNotSubmitted[i][6];
                     queueReady[priority][j][7] = queueNotSubmitted[i][7];
                     queueReady[priority][j][8] = queueNotSubmitted[i][8];
                  
                     //System.out.println("Added process "+queueReady[priority][j][0]+" with priority "+priority+" to the ready queue");
                  /*----------------------------------------------------------
                  // clear the process from the "not-yet-submitted" queue
                  //--------------------------------------------------------*/
                     queueNotSubmitted[i][0] = 0;
                     queueNotSubmitted[i][1] = 0;
                     queueNotSubmitted[i][2] = 0;
                     queueNotSubmitted[i][3] = 0;
                     queueNotSubmitted[i][4] = 0;
                     queueNotSubmitted[i][5] = 0;
                     queueNotSubmitted[i][6] = 0;
                     queueNotSubmitted[i][7] = 0;
                     queueNotSubmitted[i][8] = 0; 
                     
                     //System.out.println("queueReady is " + queueReady[1][0][0]);
                     //queueReady[0][0][priority]++;
                     break;  
                  }// end inner if 
                  else{
                      //System.out.println("queue is not 0");
                  }
               }// end inner for loop
            }//end outer if
            else{
                //System.out.println("process " +i+" has time " + queueNotSubmitted[i][2] + " and the other time is "+time);
            }
        }//end out for loop
      return;
    }//end method

    /*------------------------------------------------------------------------------
    //##############################################################################
    //------------------------------------------------------------------------------
    //
    // FUNCTION:    printPriorityQueue               
    //
    //              Prints the content of the priority queue 
    //
    //------------------------------------------------------------------------------
    //##############################################################################
    //----------------------------------------------------------------------------*/
 /*   public static void printPriorityQueue(int numberProcess)                 
    {
      System.out.println("In the printPriorityQueue\n\n");
      int i;
      int j;
      int k;
      int countProcess = numberProcess;
      int pnum = 0;
      for(i=1;i<=10;i++)
      {
         System.out.printf("here are the details for priority queue: %d\n", i);
         for(j=1;j<=countProcess;j++)
         {
            if (queueReady[i][j][0] != 0)
            {
               System.out.printf("here are the details for process: %d\n", j);
            }
               
            for(k=0;k<9;k++)
            {   
               if (queueReady[i][j][0] != 0)
               {
                      System.out.printf( " %d ", queueReady[i][j][k] );
                      pnum++;
               }
            }
            if (pnum > 0)
            {
               System.out.printf("\n");
            }
            pnum = 0;
         }
      }
      return;
   }*/
    
    //##############################################################################
    //------------------------------------------------------------------------------
    //
    // FUNCTION:    VIP               
    //
    //              Moves highest priority job to 
    //
    //------------------------------------------------------------------------------
    //##############################################################################
    //----------------------------------------------------------------------------*/
   
   
    public static int VIP(int Pnum, int numberProcess, int quantumSize)                                                      
    {  
      //System.out.println("In the VIP queue\n\n");
      int i = 0;
      int j = 0;

      
      int VIP = 0;
      
      for(i=10; i>=1 ; i--)
      {
            //System.out.println("the queueReady before if is " + queueReady[i][0][0]);
         
          // If there is something in the ready queue, pick one and put it in 
          // the running queue
         if (queueReady[i][0][0] != 0)
         {
            //System.out.println("the queueReady is " + queueReady[i][0][0]);
            VIP = queueReady[i][0][0];
            
            //System.out.printf("Process to move: %d\n",VIP);
            
            running[Pnum][0] = queueReady[i][0][0];
            running[Pnum][1] = queueReady[i][0][1];
            running[Pnum][2] = queueReady[i][0][2];
            running[Pnum][3] = queueReady[i][0][3];
            running[Pnum][4] = queueReady[i][0][4];
            running[Pnum][5] = queueReady[i][0][5];      
            running[Pnum][6] = queueReady[i][0][6];
            running[Pnum][7] = queueReady[i][0][7];
            running[Pnum][8] = queueReady[i][0][8];
            
            quantumStatus[Pnum] = quantumSize;
            
            queueReady[i][0][0] = 0;
            queueReady[i][0][1] = 0;
            queueReady[i][0][2] = 0;
            queueReady[i][0][3] = 0;
            queueReady[i][0][4] = 0;
            queueReady[i][0][5] = 0;
            queueReady[i][0][6] = 0;
            queueReady[i][0][7] = 0;
            queueReady[i][0][8] = 0;             
            
            for(j=0; j<numberProcess; j++)
            {
               queueReady[i][j][0] = queueReady[i][j+1][0];
               queueReady[i][j][1] = queueReady[i][j+1][1];
               queueReady[i][j][2] = queueReady[i][j+1][2];
               queueReady[i][j][3] = queueReady[i][j+1][3];
               queueReady[i][j][4] = queueReady[i][j+1][4];
               queueReady[i][j][5] = queueReady[i][j+1][5]; 
               queueReady[i][j][6] = queueReady[i][j+1][6]; 
               queueReady[i][j][7] = queueReady[i][j+1][7]; 
               queueReady[i][j][8] = queueReady[i][j+1][8]; 
            }
      
        //System.out.printf("***Process %d moved to CPU %d***\n", running[Pnum][0], Pnum);
               break;
         }
      }
      return VIP;
   }
   
    /*------------------------------------------------------------------------------
    //##############################################################################
    //------------------------------------------------------------------------------
    //
    // FUNCTION:    moveToCompletedQueue               
    //
    //              Move jobs from Running queue to completed queue
    //
    //------------------------------------------------------------------------------
    //##############################################################################
    //----------------------------------------------------------------------------*/


    public static int moveToCompletedQueue(int countCPU, int jobsLeft,
                             int quantumSize)
                             
    {
        //System.out.println("In the moveToCompleteQueue\n");
          int i = 0;
          int j = 0;

       /*----------------------------------------------------------------------
       //  Move complete jobs to completed queue
       //--------------------------------------------------------------------*/
          
          //System.out.println("It gets here");
       // iterate through each of the CPU's */
          for(i=0; i<countCPU; i++)
          {
          
             if (running[i][0]==0)
             {
                 //System.out.println("Nothing is running");
             }
             else
             {
                //System.out.println("running is "+running[i][0]);
             }
            /* check if RUNNING is populated and if CPU Time ran out*/
             if(   running[i][0] != 0  &&  running[i][6] == 0  )
             {
                 //System.out.println("It gets in the if");
             /* iterate through the completed array*/
                for(j=0; j<25; j++)
                {
                /* look for an empty slot to plug in the process*/
                   if(completed[j][0] == 0)
                  {

                      completed[j][0] = running[i][0];
                      completed[j][1] = running[i][1];
                      completed[j][2] = running[i][2];
                      completed[j][3] = running[i][3];
                      completed[j][4] = running[i][4];
                      completed[j][5] = running[i][5];
                      completed[j][6] = running[i][6];
                      completed[j][7] = running[i][7];
                      completed[j][8] = running[i][8];


                      running[i][0] = 0;
                      running[i][1] = 0;
                      running[i][2] = 0;
                      running[i][3] = 0;
                      running[i][4] = 0;
                      running[i][5] = 0;
                      running[i][6] = 0;
                      running[i][7] = 0;
                      running[i][8] = 0;

                      jobsLeft--;
                   /* printf("process %d completed\n", completed[j][0]);*/

                      System.out.printf("\tPID %d completed execution at %d, turnaround time = %d\n",completed[j][0], time, time - (completed[j][2]) );
                      completed[0][0]++;
                   /* reset quantum size*/
                      completeTimes[j+1] = time - (completed[j][2]);
                      quantumStatus[i] = quantumSize;
                      break;
               }
             }
          }
       }
       return jobsLeft;
    }

    /*------------------------------------------------------------------------------
//##############################################################################
//------------------------------------------------------------------------------
//
// FUNCTION:    moveToBlockedQueue               
//
//              If compute time expires Move jobs from
//              Running queue to blocked queue
//
//------------------------------------------------------------------------------
//##############################################################################
//----------------------------------------------------------------------------*/

    public static void moveToBlockedQueue(int countCPU, int numberProcess,
                             int quantumSize)              
    {
        //System.out.println("In the moveToBlockedQueue\n");  
        int i;
        int j;


       /*iterate through each processor */       
          for(i=0; i<countCPU; i++)
          {
          /*check if the current job ran out of compute time*/
             if(   running[i][7] == 0   &&   running[i][0] != 0   )
             {

             /*int priority = running[i][1];*/

             /*------------------------------------------------------------------
             // Look for an empty space in blocked queue
             //------------------------------------------------------------------*/
                for(j=0;j<numberProcess;j++)
                {
                /*--------------------------------------------------------------
                // When an empty space is found at the appropriate priority
                //--------------------------------------------------------------*/
                   if( blocked[j][0] == 0 )
                   {
                   /*----------------------------------------------------------
                   // transfer the process to the ready queue
                   //----------------------------------------------------------*/
                       
                      blocked[j][0] = running[i][0];
                      blocked[j][1] = running[i][1];
                      blocked[j][2] = running[i][2];
                      blocked[j][3] = running[i][3];
                      blocked[j][4] = running[i][4];
                      blocked[j][5] = running[i][5];
                      blocked[j][6] = running[i][6];
                      blocked[j][7] = running[i][7];
                      blocked[j][8] = running[i][8];

                      if(running[i][7] == 0)
                      {
                      /*reset compute time*/
                        blocked[j][7] = running[i][4];
                        
                      }
                      quantumStatus[i] = quantumSize;

                   /*----------------------------------------------------------
                   // clear the process from the "not-yet-submitted" queue
                   //----------------------------------------------------------*/
                      running[i][0] = 0;
                      running[i][1] = 0;
                      running[i][2] = 0;
                      running[i][3] = 0;
                      running[i][4] = 0;
                      running[i][5] = 0;
                      running[i][6] = 0;
                      running[i][7] = 0;
                      running[i][8] = 0; 

                      
                      quantumStatus[i] = quantumSize;

                   //System.out.printf("^^^^^^^^Process ID %d was moved to the blocked queue with compute time left of %d\n",blocked[j][0], blocked[j][7]);

                   /*queueReady[0][0][priority]++;*/
                      break;  
                }
             }
          }
       }
    }          

/*------------------------------------------------------------------------------
###############################################################################
--------------------------------------------------------------------------------
//
// FUNCTION:    quantumTimeExpires               
//
//              Move jobs from Running queue to completed queue
//
--------------------------------------------------------------------------------
################################################################################
------------------------------------------------------------------------------*/

    public static void quantumTimeExpires (int countCPU, int numberProcess,
                             int quantumSize)
                             
    {
//System.out.println("In the quantumTimeExpires\n\n"); 
            int i;
            int j;

            /*----------------------------------------------------------------------
            //  check if Quantum time has expired and moved to running queue
            //--------------------------------------------------------------------*/


           /*iterate through each processor  */      
            for(i=0; i<countCPU; i++)
            {       
                int priority = running[i][1];

                //System.out.println("The quantum status of CPU "+running[i][0]+" is "+quantumStatus[i]);
                
                    //check if the current job ran out of Quantum time
                    if(   quantumStatus[i] == 0   &&   running[i][0] != 0   )
                    {
                            /*------------------------------------------------------
                            // determine the priority of the process
                            //----------------------------------------------------*/
                            

                            /*-----------------------------------------------------
                            // Look for an empty space in queueReady 
                            //----------------------------------------------------*/
                            for(j=0;j<numberProcess;j++)
                            {
                                    /*----------------------------------------------
                                    // When an empty space is found at the
                                    // appropriate priority
                                    //--------------------------------------------*/
                                    if( queueReady[priority][j][0] == 0 )
                                     {
                                       /*--------------------------------------
                                       // transfer the process to the
                                       // ready queue
                                       //------------------------------------*/
                                       queueReady[priority][j][0] = running[i][0];
                                       queueReady[priority][j][1] = running[i][1];
                                       queueReady[priority][j][2] = running[i][2];
                                       queueReady[priority][j][3] = running[i][3];
                                       queueReady[priority][j][4] = running[i][4];
                                       queueReady[priority][j][5] = running[i][5];
                                       queueReady[priority][j][6] = running[i][6];
                                       queueReady[priority][j][7] = running[i][7];
                                       queueReady[priority][j][8] = running[i][8];

                                       /*-------------------------------------------
                                       // clear the process from the
                                       // "not-yet-submitted" queue
                                       //-----------------------------------------*/
                                       running[i][0] = 0;
                                       running[i][1] = 0;
                                       running[i][2] = 0;
                                       running[i][3] = 0;
                                       running[i][4] = 0;
                                       running[i][5] = 0;
                                       running[i][6] = 0;
                                       running[i][7] = 0;
                                       running[i][8] = 0; 

                                      /* queueReady[0][0][priority]++;*/
                                       quantumStatus[i] = quantumSize;
                                        //System.out.printf("---process %d returned to ready queue, compute time left: %d\n",queueReady[priority][j][0], queueReady[priority][j][7]);
                                       break;  
                                    }
                            }
                    }
            }
    }

/*------------------------------------------------------------------------------
//##############################################################################
//------------------------------------------------------------------------------
//
// FUNCTION:    IOTimeExpires               
//
//              check if IO time has expired and moved to ready queue
//
//------------------------------------------------------------------------------
//##############################################################################
//----------------------------------------------------------------------------*/


    public static void IOTimeExpires (int numberProcess)                                                     
    {
      int i;
      int j;

      //System.out.println("In the IOTimeExpires\n");
              /*IOTimeExpires */
      /*----------------------------------------------------------------------
      // increment through all available processes
      //--------------------------------------------------------------------*/
      for(i=0; i<numberProcess; i++)
      {
         /*--------------------------------------------------------------
         // check if process has the appropriate release time
         //------------------------------------------------------------*/

         if ( blocked[i][0] != 0 )
         //System.out.printf("process: %d IO time is: %d \n",
                                         //blocked[i][0], blocked[i][7]);

         // If there is something blocked and IO time has expired, move 
         // it to the ready queue
         if ( blocked[i][0] != 0  &&  blocked[i][8] == 0 )
         {
              /*------------------------------------------------------
              // determine the priority of the process
              //----------------------------------------------------*/
              int priority = blocked[i][1];

              /*------------------------------------------------------
              // Look for an empty space in queueReady 
              //----------------------------------------------------*/

              for(j=0;j<numberProcess;j++)
              {
                /*----------------------------------------------
                // When an empty space is found at the
                // appropriate priority
                //--------------------------------------------*/
                if( queueReady[priority][j][0] == 0 )
                {
                    /*--------------------------------------

                    // transfer the process to the
                    // ready queue
                    //------------------------------------*/

                    queueReady[priority][j][0] = blocked[i][0];
                    queueReady[priority][j][1] = blocked[i][1];
                    queueReady[priority][j][2] = blocked[i][2];
                    queueReady[priority][j][3] = blocked[i][3];
                    queueReady[priority][j][4] = blocked[i][4];
                    queueReady[priority][j][5] = blocked[i][5];
                    queueReady[priority][j][6] = blocked[i][6];
                    queueReady[priority][j][7] = blocked[i][7];
                    queueReady[priority][j][8] = blocked[i][8];

                    queueReady[priority][j][8] = blocked[i][5];
                    
                    /*----------------------------------------------
                    // clear the process from the
                    // "not-yet-submitted" queue
                    //--------------------------------------------*/
                    blocked[i][0] = 0;
                    blocked[i][1] = 0;
                    blocked[i][2] = 0;
                    blocked[i][3] = 0;
                    blocked[i][4] = 0;
                    blocked[i][5] = 0;
                    blocked[i][6] = 0;
                    blocked[i][7] = 0;
                    blocked[i][8] = 0; 

                    //System.out.printf("^^^Proc %d returned to ready queue from IO-block\n",queueReady[priority][j][0]);
                    //queueReady[0][0][priority]++;*/
                    break;  
                  }
              }
         }
      }// out for loop
    } // end method

    
    
   public static void moveRunningToReady(int CPUnum, int quantumSize, int numberProcess)
   {
        //System.out.println("moving process "+running[CPUnum][0]+" on CPU "+CPUnum+" from Running to Ready");
                
        /*------------------------------------------------------
        // determine the priority of the process
        //----------------------------------------------------*/
                            
        int priority = running[CPUnum][1];
                            
        /*-----------------------------------------------------
        // Look for an empty space in queueReady 
        //----------------------------------------------------*/
        for(int j=0;j<numberProcess;j++)
        {
            /*----------------------------------------------
            // When an empty space is found at the
            // appropriate priority
            //--------------------------------------------*/
            if( queueReady[priority][j][0] == 0 )
             {
                   /*--------------------------------------
                   // transfer the process to the
                   // ready queue
                   //------------------------------------*/
                   queueReady[priority][j][0] = running[CPUnum][0];
                   queueReady[priority][j][1] = running[CPUnum][1];
                   queueReady[priority][j][2] = running[CPUnum][2];
                   queueReady[priority][j][3] = running[CPUnum][3];
                   queueReady[priority][j][4] = running[CPUnum][4];
                   queueReady[priority][j][5] = running[CPUnum][5];
                   queueReady[priority][j][6] = running[CPUnum][6];
                   queueReady[priority][j][7] = running[CPUnum][7];
                   queueReady[priority][j][8] = running[CPUnum][8];

                   /*-------------------------------------------
                   // clear the process from the
                   // "not-yet-submitted" queue
                   //-----------------------------------------*/
                   running[CPUnum][0] = 0;
                   running[CPUnum][1] = 0;
                   running[CPUnum][2] = 0;
                   running[CPUnum][3] = 0;
                   running[CPUnum][4] = 0;
                   running[CPUnum][5] = 0;
                   running[CPUnum][6] = 0;
                   running[CPUnum][7] = 0;
                   running[CPUnum][8] = 0; 

                  /* queueReady[0][0][priority]++;*/
                   quantumStatus[CPUnum] = quantumSize;
                    //System.out.printf("---process %d returned to ready queue, compute time left: %d\n",queueReady[priority][j][0], queueReady[priority][j][7]);
                   break;  
                }
                            
                    
        }     
   }
    
    
    
    
    
    @SuppressWarnings("ManualArrayToCollectionCopy")
    public static void main(String[] args) throws IOException
    {

        
        //int time;
        int jobsLeft = 0;
        int arrayPlaceholder = 0;
        
        /********************************/
        /* Simulation Data              */
        /********************************/
        
        int numberCPU = 1; //the number of CPUs in the system (1 to 4)
        int numberProcess = 1; //the number of processes (1 to 25)
        int quantumSize = 1; //the quantum size (1 or larger)

        
        /***********************************/
        /* Process Data                    */
        /***********************************/
        
        int processID; //the process ID (1 to 999)
        int processPriority; //the process priority (1 to 10)
        int submissionTime; //the time of submission (non-negative)
        int cpuTime; //the total CPU time required (1 to 1000)
        int computeTime; //the compute time before input/output is needed 
                         //(1 to 100)
        int ioTime; //the input/output time for each compute-I/O cycle 
                    //(1 to 1000)
        
        /************************************/
        /* Queue Arrays                     */
        /************************************/
        
        int [] line = new int[6];  // for holding each line read
       
        // array to hold all the process info
        int [][] process = new int [100][9]; 
        
        // array to hold all the simulation info
        int [][] simData = new int [4][3];
               
        int processCounter=0;
        int simCounter = 0;
        int count;
        int simulation;
        String number;
                
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String s;
        
        // An empty line or Ctrl-Z terminates the program
        while ((s = in.readLine()) != null && s.length() != 0)
        {
            count = 0;
            //System.out.print("The line is:\n");
            //System.out.println(s);
            //System.out.println("The length is"+s.length());
            StringTokenizer st = new StringTokenizer (s);
            
            while (st.hasMoreTokens())
            {
                number = st.nextToken();
                line[count] = Integer.parseInt(number);
                //System.out.printf("The token at %d is %d\n", count, line[count]);
                count++;
                
            }
            /***************************************************************/
            /* If there are 3 words in the line, then it is a summary line */
            /***************************************************************/
            if(count==3)
            {
                //System.out.println("\nSimulation # " + simulation +"\n" );
                //simulation++;
                //System.out.println("---------------");
                //.out.println("There where "+count+" numbers in the array");
                
                simData[simCounter][0] = line[0]; // number of CPU
                simData[simCounter][1] = line[1]; // number of processes
                simData[simCounter][2] = line[2]; // quantum size
                
                simCounter++;
                
                //processCounter = 0;
                
                
            }
            /***************************************************************/
            /**/
            /***************************************************************/
            else
            {
                
        /*********************************************************************/
        /* Process the Data                                                  */
        /* 0. The process ID (1 to 999)                                      */
        /* 1. The process Priority (1 to 10)                                 */
        /* 2. The time of submission (non-negative)                          */
        /* 3. The total CPU time required (1 to 1000)                        */
        /* 4. The compute time before input/output is needed (1 to 100)      */
        /* 5. The input/output time for each compute - CPU cycle (1 to 1000) */
        /* 6. COPY total CPU time                                            */
        /* 7. COPY the compute time before input/output is needed (1 to 100) */
        /* 8. COPY the I/O time for each compute-I/O cycle (1 to 1000)       */
        /*********************************************************************/
                
                process[processCounter][0] = line[0]; 
                process[processCounter][1] = line[1];
                process[processCounter][2] = line[2];
                process[processCounter][3] = line[3];
                process[processCounter][4] = line[4];
                process[processCounter][5] = line[5];
                process[processCounter][6] = line[3];
                process[processCounter][7] = line[4];
                process[processCounter][8] = line[5];
                
                
                processCounter++;
                
            }
        }
        /*
        for (int i=0; i < processCounter; i++)
        {
            for (int j=0; j < 6; j++)
            {
                 System.out.print (process[i][j]+ " ");
            }
            System.err.println();
        }
        for (int i=0; i < cpuCounter; i++)
        {
            for (int j=0; j < 3; j++)
            {
                 System.out.print (cpu[i][j]+ " ");
            }
            System.err.println();
        }
        */
        //System.out.println("There are " + (cpuCounter) + " simulations");
        //System.out.println("There are " + processCounter + " total processes\n");
        

        

        
        
        
        /*******************************/
        /* For each simulation.....    */
        /*******************************/
        
        for (simulation = 0; simulation < simCounter; simulation++ )
        {
            System.out.println("\nSimulation # " + (simulation + 1) +"\n" );
            System.out.println("---------------");
            System.out.println("Input:");
            
            numberCPU = simData[simulation][0];
            numberProcess = simData[simulation][1];
            quantumSize = simData[simulation][2];
            
            jobsLeft = numberProcess;
            
            clearArrays();
            
            System.out.printf("\t%d CPU, %d processes, quantum size = %d\n", 
                    numberCPU, numberProcess, quantumSize);   
            
            
            /******************************************************/
            /* Load "Not-yet-submitted" queue with all processes  */
            /* All processes begin in the not-yet-submitted state */
            /******************************************************/
            int y = 0;
             // Iterate through each process
            for (int i = arrayPlaceholder; i<(numberProcess+arrayPlaceholder); i++)
            {
                // Iterate through each array elemement
                // and copy to the not submitted yet
                for (int j=0; j<9; j++)
                {
                    queueNotSubmitted[y][j]=process[i][j];
                }
                System.out.printf("\tPID %d, prio = %d, submit = %d, totCPU = %d, CPU = %d, I/O = %d\n",
                                     queueNotSubmitted[y][0], queueNotSubmitted[y][1], queueNotSubmitted[y][2],
                                            queueNotSubmitted[y][3], queueNotSubmitted[y][4], queueNotSubmitted[y][5]) ;      
                y++;
            }
            
           
            System.out.printf("\nOutput:\n");
            //change the placeholder to the new value for next simulation
            //arrayPlaceholder = numberProcess + arrayPlaceholder;

            /*################################################################*/  
            /* Run Simulation -- Run Simulation -- Run Simulation --          */
            /*################################################################*/
            
            time = 0;
            boolean x = true;
            while(x) /* infinite while for each unit of time */
            {
         
             /*---------------------------------------------------------------*/
             /* Load any jobs from the NOT-YET-SUBMITTED queue to Ready queue */
             /* Each process moves from the not-yet-submitted state to the    */
             /* ready state at the time specified in the input data for its   */
             /* submission.                                                    */
             /*---------------------------------------------------------------*/
         
                loadFromNotYetSubmitted(numberProcess);
                        
                                                                   
         
         //System.out.println("There are " + jobsLeft+ " jobs left before");
         
             /*---------------------------------------------------------------*/
             //  Move complete jobs to completed queue if finished
             /*---------------------------------------------------------------*/
         
              jobsLeft = moveToCompletedQueue( numberCPU, jobsLeft,quantumSize);
                         
                      
         
         //System.out.println("There are " + jobsLeft+ " jobs left after");
         
             /*---------------------------------------------------------------------
             //  Check if Compute time has expired and move to Blocked queue
             //-------------------------------------------------------------------*/

                moveToBlockedQueue( numberCPU, numberProcess, 
                        quantumSize);
                                                             

         
         
             /*---------------------------------------------------------------------
             //  check if Quantum time has expired and moved to running queue
             //-------------------------------------------------------------------*/
         
            quantumTimeExpires (numberCPU, numberProcess,quantumSize);
                                                      
         
         
         
             /*-----------------------------------------------------------------
             //  check if IO time has expired and moved to ready queue
             //---------------------------------------------------------------*/        
         
            IOTimeExpires (numberProcess);
         
         
         
             /*-------------------------------------*/
             /*  Load any available CPU's           */
             /*-------------------------------------*/
            
            // If there is nothing running, run something
            for(int i=0; i<numberCPU; i++)
            {
               if(   running[i][0] == 0  )
               {
                   //System.out.printf("processor %d is idle\n",i);
                   VIP (i, numberProcess, quantumSize);
               }
               for (int k=10; k>0;k--)
               {
                   for (int h=0; h<25; h++)
                   {
                       if(queueReady[k][h][1] > running[i][1])
                       {
                           ready_priority = queueReady[k][h][1];
                           //System.out.printf("!!!!!!!!!!!!!!!!!!!!!!!!!!processor in the ready has priority of %d and the running is %d\n",ready_priority,running[i][1]);
                           //move running process to ready
                           moveRunningToReady(i,quantumSize, numberProcess);
                           //move ready process to running
                           VIP (i, numberProcess, quantumSize);
                       }
                   }
               }
            }
            
            

            // If there is nothing to be run and there are still jobs left
            // increase the idle time 
            
            for(int i=0; i<numberCPU; i++)
            {
                if((running[i][0] == 0  && jobsLeft != 0))
                {
                       processorIdle[i]++;
                      // System.out.println("-------------------The processorIdle is " +processorIdle[i]);
                }
            }
            

         
             /*---------------------------------------------------------------------
             //  Decrement times
             //-------------------------------------------------------------------*/
            for(int i=0; i<numberCPU; i++)
            {
               if(   running[i][0] != 0  )
               {
                   /*decrement the Total CPU time */
                     if (running[i][6] != 0)
                     { 
                         running[i][6]--;
                     }     
                   /*decrement the compute time   */      
                     if (running[i][7] != 0)
                     {
                         running[i][7]--;
                     } 
                   /*decrement the quantum length   */     
                     quantumStatus[i]--;
               }
            }
            for(int i=0; i<numberProcess; i++)
            {  
                if ( blocked[i][0] != 0)
               {
                   /*decrement the IO time left */
                   blocked[i][8]--;
               }  
            }
            
         
             /*---------------------------------------------------------------------
             //  Loop controls
             //-------------------------------------------------------------------*/
         
             /*if (time == 10000) // just in case something goes super crazy */
             //{   
             //    break;
             //}
             if (jobsLeft == 0)
             {

                /* add up all possible idle time */
                   int totalIdleTime = 0;
                   for(int i=0;i<5;i++)
                   {
                      totalIdleTime += processorIdle[i];
                   }
                /* divide by the number of CPU's' */
                   float avgIdleTime = 0;
                   avgIdleTime = (totalIdleTime/numberCPU); /*--> avg cpu idle time */
                   
                   float percentIdle = ((avgIdleTime/time)*100);

                   System.out.printf("\n\tAverage CPU idle time = %.01f ( %.01f percent)\n",
                                             avgIdleTime,  percentIdle );

                   int sumCompletionTimes = 0;

                   for(int i=0; i<25; i++)
                   {
                      sumCompletionTimes += completeTimes[i];
                   }

                   float avgCompletionTime = 0;
                   avgCompletionTime = (sumCompletionTimes/numberProcess);
                   System.out.printf("\tAverage process turnaround time = %f\n\n",
                                                                 avgCompletionTime);
                   x=false;
         
                }
                
             time++;
             //System.out.println("*******The time is now " + time+"\n\n");
             
             if (time==200)
                 System.exit(1);
            } // end infinite while
            arrayPlaceholder = arrayPlaceholder + numberProcess;
            //System.out.println("The new arrayPlaceholder is " + arrayPlaceholder);
        }
        //System.out.println("The process counter is " + processCounter + "\n");

    }
}
