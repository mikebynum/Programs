#include <stdio.h>
#include <stdlib.h>

/*
 Author:  Robert C. Gonzales
Purpose:  Scheduling Simulator 

*/






/*--------------------
//prototypes
//--------------------*/
  void loadFromNotYetSubmitted( int time,
                                int countProcess,
                                int queueNotSubmitted[25][9],
                                int queueReady[11][26][9]                     );
       void printPriorityQueue( int countProcess,
                                int queueReady[11][26][9]                     );
                       int VIP( int Pnum,
                                int countProcess,
                                int queueReady[11][26][9],
                                int running[5][9],
                                int move[9]                                   );
      int moveToCompletedQueue( int countCPU,
                                int jobsLeft,
                                int running[5][9],
                                int quantumStatus[5],
                                int quantumSize,
                                int completed[26][9],
                                int time,
                                int completeTimes[26]                         );
       void moveToBlockedQueue( int countCPU,
                                int countProcess,
                                int running[5][9],
                                int quantumStatus[5],
                                int quantumSize,
                                int blocked[26][9]                            );
       void quantumTimeExpires( int countCPU,
                                int countProcess,
                                int running[5][9],
                                int quantumStatus[5],
                                int quantumSize,
                                int queueReady[11][26][9]                     );
            void IOTimeExpires( int countProcess, 
                                int blocked[26][9],
                                int queueReady[11][26][9]                     );




int main()
{
   
   
   
   int simulationCount = 1;   
   while(1)
   {
      /*trouble shoot an infinite loop issue with redirected input
       if(simulationCount == 25) { exit(1); }  */ 

      int i = 0;
      int j = 0;
      int time = 0;
      int jobsLeft = 0;
   /* - - - - - - - - - - - -
   //  simulation data
   // - - - - - - - - - - - -*/
      int dataSimulation[3] = {0};
      int countCPU         = 0;
      int countProcess     = 1;
      int quantumSize      = 1;
   /* - - - - - - - - - - - -
   //  process data
   // - - - - - - - - - - - -*/
      int processID        = 1;
      int processPriority  = 1;
      int timeSubmission   = 0;
      int timeTotalCPU     = 1;
      int timeCompute      = 1;
      int timeIO           = 1;
   /* - - - - - - - - - - - -
   //  Queue Arrays
   // - - - - - - - - - - - -*/
      
      int move[9] = {0};/*/ I don't remember what this is for*/
   /*  create the array to hold all processes when loaded*/
      int queueNotSubmitted[26][9]   =   { {0} }   ;
   /*  create the array to hold all ready processes*/
      int queueReady[11][26][9]      = { { {0} } } ;
   /*  create the array to hold all of the process information*/
      int processData[26][9] = {{0}};
   /*  create the array to hold RUNNING processes*/
      int running[5][9] = {{0}};
      int quantumStatus[5] = {0};
      int processorIdle[5] = {0};
   /*  create the array to hold COMPLETED processes*/
      int completed[26][9] = {{0}};
      int completeTimes[26] = {0};
   /*  create the array to hold BLOCKED processes*/
      int blocked[26][9] = {{0}};
      
      
      
      

/*##############################################################################  
// INPUT -- INPUT -- INPUT -- INPUT -- INPUT -- INPUT -- INPUT -- INPUT -- INPUT 
//##############################################################################
// INPUT -- INPUT -- INPUT -- INPUT -- INPUT -- INPUT -- INPUT -- INPUT -- INPUT 
//##############################################################################  
// INPUT -- INPUT -- INPUT -- INPUT -- INPUT -- INPUT -- INPUT -- INPUT -- INPUT 
//##############################################################################
// INPUT -- INPUT -- INPUT -- INPUT -- INPUT -- INPUT -- INPUT -- INPUT -- INPUT 
//##############################################################################
// INPUT -- INPUT -- INPUT -- INPUT -- INPUT -- INPUT -- INPUT -- INPUT -- INPUT 
//############################################################################*/

      /*------------------------------------------------------------------------
      //  Simulation data
      //  1. # of CPU's     ( 1-4  )
      //  2. # of Processes ( 1-25 )
      //  3. Quantum Size   ( 1 or larger )
      //----------------------------------------------------------------------*/
      
      /*/  input # of CPU's*/
      
         fscanf(stdin,"%d",&countCPU);
         
               /*/don't know how to clear stdin, so I get infinite loops with
               //redirected data input, so I kill job if first read is out of
               //range*/
               if(countCPU == 0) { exit(1); }
                  

         dataSimulation[0] = countCPU;
      
      /*  input # of processes*/
         scanf("%d",&countProcess);
         dataSimulation[1] = countProcess;
         jobsLeft = countProcess;
      /*  input quantum size*/
         scanf("%d",&quantumSize);
         dataSimulation[2] = quantumSize;
         for (i=0; i<5; i++) { quantumStatus[i] = quantumSize; }
      
      /*  output simulation parameters*/
         printf("\n\nSimulation # %d\n",simulationCount);
         printf("--------------\n");
         printf("Input:\n");
         printf("\t%d CPU, %d process, quantum size = %d\n\n",
                                           countCPU, countProcess, quantumSize);
      

      
      /*------------------------------------------------------------------------   
      //  Process Data
      //  1. the process ID (1 to 999)
      //  2. the process priority (1 to 10)
      //  3. the time of submission (non-negative)
      //  4. the total CPU time required (1 to 1000)
      //  5. the compute time before input/output is needed (1 to 100)
      //  6. the input/output time for each compute-I/O cycle (1 to 1000).
      //  7. COPY total CPU time
      //  8. COPY the compute time before input/output is needed (1 to 100)
      //  9. COPY the input/output time for each compute-I/O cycle (1 to 1000).
      //----------------------------------------------------------------------*/ 
      
      /*  Insert information into arrays*/
         for(i=1; i<=countProcess; i++)
         {
            /*  input process ID*/
               scanf("%d",&processID);
               processData[i][0] = processID;
            /*  input process priority*/
               scanf("%d",&processPriority);
               processData[i][1] = processPriority;
            /*  input Submission time*/
               scanf("%d",&timeSubmission); 
               processData[i][2] = timeSubmission;
            /*  input Total CPU time*/
               scanf("%d",&timeTotalCPU);
               processData[i][3] = timeTotalCPU;
               processData[i][6] = timeTotalCPU;
            /* input Compute time*/
               scanf("%d",&timeCompute);
               processData[i][4] = timeCompute;
               processData[i][7] = timeCompute;
               /*  input IO time*/
               scanf("%d",&timeIO); 
               processData[i][5] = timeIO;
               processData[i][8] = timeIO;
            
            
            /*  output processor info */    
               printf("\tPID %d, prio = %d, submit = %d, totCPU = %d, CPU = %d, I/O = %d\n",
                                     processID, processPriority, timeSubmission,
                                            timeTotalCPU, timeCompute, timeIO) ;      
         }
         if(time == 0)
            printf("\noutput:\n");
      
      
      /*------------------------------------------------------------------------
      //  Load "Not-yet-submitted" queue with all processes
      //----------------------------------------------------------------------*/
      
      /*  iterate through each process */
         for(i=1;i<=countProcess;i++)
         {
            /* iterate through each array element of the array
            //  and copy it to the NotSubmittedYet process */
               for(j=0;j<9;j++)
               {
               queueNotSubmitted[i][j] = processData[i][j];
               }
         }
      
      
      

/*##############################################################################  
// Run Simulation -- Run Simulation -- Run Simulation -- Run Simulation -- 
//##############################################################################
// Run Simulation -- Run Simulation -- Run Simulation -- Run Simulation --   
//##############################################################################  
// Run Simulation -- Run Simulation -- Run Simulation -- Run Simulation -- 
//##############################################################################
// Run Simulation -- Run Simulation -- Run Simulation -- Run Simulation -- 
//##############################################################################
// Run Simulation -- Run Simulation -- Run Simulation -- Run Simulation -- 
//############################################################################*/
      

      
      time = 0;
      while(1) /* infinite while for each unit of time */
      {
         
         /*---------------------------------------------------------------------
         //  Load any jobs from the NOT-YET-SUBMITTED queue to Ready queue
         //-------------------------------------------------------------------*/
         
            loadFromNotYetSubmitted( time, countProcess, queueNotSubmitted,
                                                                   queueReady );
         
         
         
         /*---------------------------------------------------------------------
         //  Move complete jobs to completed queue if finished
         //-------------------------------------------------------------------*/
         
          jobsLeft = moveToCompletedQueue( countCPU, jobsLeft, running,
                   quantumStatus, quantumSize, completed, time, completeTimes );
         
         
         
         /*---------------------------------------------------------------------
         //  Check if Compute time has expired and move to Blocked queue
         //-------------------------------------------------------------------*/
         
            moveToBlockedQueue( countCPU, countProcess, running, quantumStatus,
                                                         quantumSize, blocked );
         
         
         
         /*---------------------------------------------------------------------
         //  check if Quantum time has expired and moved to running queue
         //-------------------------------------------------------------------*/
         
            quantumTimeExpires (countCPU, countProcess, running, quantumStatus,
                                                      quantumSize, queueReady );
         
         
         
         /*---------------------------------------------------------------------
         //  check if IO time has expired and moved to ready queue
         //-------------------------------------------------------------------*/        
         
            IOTimeExpires (countProcess, blocked, queueReady);
         
         
         
         /*---------------------------------------------------------------------
         //  Load any available CPU's
         //-------------------------------------------------------------------*/
            for(i=1; i<=countCPU; i++)
            {
               if(   running[i][0] == 0  )
               {
                  /* printf("processor %d is idle\n",i); */
                   VIP(i, countProcess, queueReady, running, move);
               }
            }
            
            for(i=1; i<=countCPU; i++)
            {
               if(   running[i][0] == 0  && jobsLeft != 0)
               {
                 processorIdle[i]++;
               }
            }
         
         /*---------------------------------------------------------------------
         //  Decrement times
         //-------------------------------------------------------------------*/
            for(i=1; i<=countCPU; i++)
            {
               if(   running[i][0] != 0  )
               {
                   /*decrement the Total CPU time */
                     if (running[i][6] != 0)
                             running[i][6]--;
                           
                   /*decrement the compute time   */      
                     if (running[i][7] != 0)
                             running[i][7]--;
                      
                   /*decrement the quantum length   */     
                     quantumStatus[i]--;
               }
            }
            for(i=1; i<=countProcess; i++)
            {  if ( blocked[i][0] != 0)
               {
                   /*decrement the IO time left */
                   blocked[i][8]--;
               }  
            }
            
         
         /*---------------------------------------------------------------------
         //  Loop controls
         //-------------------------------------------------------------------*/
         
         if (time == 10000) // just in case something goes super crazy */
            break;
         
         if (jobsLeft == 0)
         {
            
            /* add up all possible idle time */
               int totalIdleTime = 0;
               for(i=1;i<=5;i++)
               {
                  totalIdleTime += processorIdle[i];
               }
            /* divide by the number of CPU's' */
               float avgIdleTime = 0;
               avgIdleTime = (totalIdleTime/countCPU); /*--> avg cpu idle time */
               
               printf("\n\tAverage CPU idle time = %.0lf ( %.2lf percent)\n",
                                         avgIdleTime,  (avgIdleTime/time)*100 );
      
               int sumCompletionTimes = 0;
               
               for(i=1; i<=25; i++)
               {
                  sumCompletionTimes += completeTimes[i];
               }
               
               float avgCompletionTime = 0;
               avgCompletionTime = (sumCompletionTimes/countProcess);
               printf("\tAverage process turnaround time = %.0lf\n\n",
                                                             avgCompletionTime);
               
               
               break;
         
         }

         time++;
      } // end infinite while
      
/*##############################################################################
// end simulation -- end simulation -- end simulation -- end simulation --
//##############################################################################
// end simulation -- end simulation -- end simulation -- end simulation --
//##############################################################################
// end simulation -- end simulation -- end simulation -- end simulation --
//##############################################################################
// end simulation -- end simulation -- end simulation -- end simulation --
//##############################################################################
// end simulation -- end simulation -- end simulation -- end simulation --
//############################################################################*/





      simulationCount++;
   }/*end infinite loop for each simulation */
   



return 0;
} /* end main */










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


void IOTimeExpires (int countProcess, int blocked[26][9],
                                                      int queueReady[11][26][9])
{
      int i;
      int j;

              /*IOTimeExpires */
      /*----------------------------------------------------------------------
      // increment through all available processes
      //--------------------------------------------------------------------*/
      for(i=1; i<=countProcess; i++)
      {
             /*--------------------------------------------------------------
             // check if process has the appropriate release time
             //------------------------------------------------------------*/
             
             if ( blocked[i][0] != 0 )
             /*printf("process: %d IO time is: %d \n",
                                             blocked[i][0], blocked[i][7]);*/
             
             
             if ( blocked[i][0] != 0  &&  blocked[i][8] == 0 )
             {
                  /*------------------------------------------------------
                  // determine the priority of the process
                  //----------------------------------------------------*/
                  int priority = blocked[i][1];
                  
                  /*------------------------------------------------------
                  // Look for an empty space in queueReady 
                  //----------------------------------------------------*/
                  for(j=1;j<=countProcess;j++)
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
                                
                                
/*printf("^^^Proc %d returned to ready queue from IO-block\n",queueReady[priority][j][0]);
//queueReady[0][0][priority]++;*/
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
// FUNCTION:    quantumTimeExpires               
//
//              Move jobs from Running queue to completed queue
//
//------------------------------------------------------------------------------
//##############################################################################
//----------------------------------------------------------------------------*/

void quantumTimeExpires (int countCPU, int countProcess, int running[5][9],
                         int quantumStatus[5], int quantumSize,
                         int queueReady[11][26][9])
{
        
        int i;
        int j;
        
        /*----------------------------------------------------------------------
        //  check if Quantum time has expired and moved to running queue
        //--------------------------------------------------------------------*/
             
                
       /*iterate through each processor  */      
        for(i=1; i<=countCPU; i++)
        {
                //check if the current job ran out of Quantum time
                if(   quantumStatus[i] == 0   &&   running[i][0] != 0   )
                {
                        /*------------------------------------------------------
                        // determine the priority of the process
                        //----------------------------------------------------*/
                        int priority = running[i][1];
                        
                        /*-----------------------------------------------------
                        // Look for an empty space in queueReady 
                        //----------------------------------------------------*/
                        for(j=1;j<=countProcess;j++)
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
                                    /*printf("---process %d returned to ready queue, compute time left: %d\n",queueReady[priority][j][0], queueReady[priority][j][7]);*/
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
// FUNCTION:    moveToBlockedQueue               
//
//              If compute time expires Move jobs from
//              Running queue to blocked queue
//
//------------------------------------------------------------------------------
//##############################################################################
//----------------------------------------------------------------------------*/

void moveToBlockedQueue(int countCPU, int countProcess, int running[5][9],
                        int quantumStatus[5], int quantumSize,
                        int blocked[26][9])
{
      int i;
      int j;
   
   /*iterate through each processor */       
      for(i=1; i<=countCPU; i++)
      {
      /*check if the current job ran out of compute time*/
         if(   running[i][7] == 0   &&   running[i][0] != 0   )
         {
         
         /*int priority = running[i][1];*/
         
         /*------------------------------------------------------------------
         // Look for an empty space in blocked queue
         //------------------------------------------------------------------*/
            for(j=1;j<=countProcess;j++)
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
               
               /*reset compute time*/
                  blocked[j][7] = running[i][4];
               
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
               
               /*printf("Process %d was moved to the blocked queue\n",blocked[j][0]);*/
               
               /*queueReady[0][0][priority]++;*/
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
// FUNCTION:    moveToCompletedQueue               
//
//              Move jobs from Running queue to completed queue
//
//------------------------------------------------------------------------------
//##############################################################################
//----------------------------------------------------------------------------*/


int moveToCompletedQueue(int countCPU, int jobsLeft, int running[5][9],
                         int quantumStatus[5], int quantumSize,
                         int completed[26][9], int time, int completeTimes[26])
{
   
      int i = 0;
      int j = 0;
   
   /*----------------------------------------------------------------------
   //  Move complete jobs to completed queue
   //--------------------------------------------------------------------*/
   // iterate through each of the CPU's */
      for(i=1; i<=countCPU; i++)
      {
      /* check if RUNNING is populated and if CPU Time ran out*/
         if(   running[i][0] != 0  &&  running[i][6] == 0  )
         {
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
                  
                  printf("\tPID %d completed execution at %d, turnaround time = %d\n",completed[j][0], time, time - (completed[j][2]) );
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
// FUNCTION:    printPriorityQueue               
//
//              Moves highest priority job to 
//
//------------------------------------------------------------------------------
//##############################################################################
//----------------------------------------------------------------------------*/
   
   
   int VIP(int Pnum, int countProcess, int queueReady[11][26][9],
                                                 int running[5][9], int move[9])
   {  
      int i = 0;
      int j = 0;
      
      int VIP = 0;
      
      for(i=1; i<=10 ; i++)
      {
         if (queueReady[i][1][0] != 0)
         {
            VIP = queueReady[i][1][0];
            
            /*printf("Process to move: %d\n",VIP);*/
            
            running[Pnum][0] = queueReady[i][1][0];
            running[Pnum][1] = queueReady[i][1][1];
            running[Pnum][2] = queueReady[i][1][2];
            running[Pnum][3] = queueReady[i][1][3];
            running[Pnum][4] = queueReady[i][1][4];
            running[Pnum][5] = queueReady[i][1][5];      
            running[Pnum][6] = queueReady[i][1][6];
            running[Pnum][7] = queueReady[i][1][7];
            running[Pnum][8] = queueReady[i][1][8];
            
            queueReady[i][1][0] = 0;
            queueReady[i][1][1] = 0;
            queueReady[i][1][2] = 0;
            queueReady[i][1][3] = 0;
            queueReady[i][1][4] = 0;
            queueReady[i][1][5] = 0;
            queueReady[i][1][6] = 0;
            queueReady[i][1][7] = 0;
            queueReady[i][1][8] = 0;             
            
            for(j=1; j<=(countProcess-1); j++)
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
      
        /* printf("***Process %d moved to CPU %d***\n", running[Pnum][0], Pnum);*/
               break;
         }
      }
      return VIP;
   }


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

   void printPriorityQueue(   int countProcess,
                              int queueReady[11][26][9]            )
   {
      int i;
      int j;
      int k;
      int pnum = 0;
      for(i=1;i<=10;i++)
      {
         printf("here are the details for priority queue: %d\n", i);
         for(j=1;j<=countProcess;j++)
         {
            if (queueReady[i][j][0] != 0)
               printf("here are the details for process: %d\n", j);
               
            for(k=0;k<9;k++)
            {   
               if (queueReady[i][j][0] != 0)
               {
                      printf( " %d ", queueReady[i][j][k] );
                      pnum++;
               }
            }
            if (pnum > 0)
               printf("\n");
            pnum = 0;
         }
      }
      return;
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

   void loadFromNotYetSubmitted(        int time,
                                        int countProcess,
                                        int queueNotSubmitted[25][9],
                                        int queueReady[11][26][9]            )
   {
         int i;
         int j;
      
      /*------------------------------------------------------------------------
      // increment through all available processes
      //----------------------------------------------------------------------*/
         for(i=1; i<=countProcess; i++)
         {
         /*---------------------------------------------------------------------
         // check if process has the appropriate release time
         //-------------------------------------------------------------------*/
            if (queueNotSubmitted[i][2] == time)
            {
            /*------------------------------------------------------------------
            // determine the priority of the process
            //----------------------------------------------------------------*/
               int priority = queueNotSubmitted[i][1];
               
            /*------------------------------------------------------------------
            // Look for an empty space in queueReady 
            //----------------------------------------------------------------*/
               for(j=1;j<=countProcess;j++)
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
                     
                     queueReady[0][0][priority]++;
                     break;  
               }
            }
         }
      }
      return;
   }
   
