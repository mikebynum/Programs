/**********************************************************************/
/*  include file.                                                     */
/*                                                                    */
/* For: CSCI 1840                                                     */
/* By:  must use 20030-39                                             */
/*                                                                    */
/**********************************************************************/

#define PORT        20030    /* Must match the clint code as well. */
#define getcmd 1
#define delcmd 2
#define putcmd 3
#define stpcmd 4
#define cmpgpa 5
#define cmpsid 6
#define cmplname 7
#define cmpfname 8
#define MAXSIZE 11

char  * command, * lname, * fname, * mi, * gpa;

int i = 0;
int sid;


typedef struct student{
	char lname[MAXSIZE],initial, fname[MAXSIZE];
	unsigned long SID;
	float GPA;
}srec;

typedef struct node{
struct node  * nextPtr;
srec studata;
}ListNode;

typedef ListNode * ListNodePtr;




