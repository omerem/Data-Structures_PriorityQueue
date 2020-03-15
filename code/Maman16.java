import java.util.*;
class Maman16
/**
 * @author Omer Kerem
 */
{
    static Scanner reader=new Scanner(System.in);
    public static void main(String[] args)
    {
        int N1, N2, N3, N, K=10;
        double[] A;
        double[] listA, listB, listC;
        System.out.println("here is array A:");
        System.out.println();
        A=getArr();
        
        printArr(A);
        System.out.println();
    
    
    
    
        System.out.println
        ("Here's the output of lowestK(A,50,200/4, 200/2, (3*200)/4):");
        System.out.println();
        listA=(lowestK(A,50,200/4, 200/2, (3*200)/4));
        System.out.println();
        System.out.println
        ("Here's the returning list from lowestK(A,50,200/4, 200/2, (3*200)/4):");
        System.out.println();
        printList(listA);
        System.out.println();
}
    public static double[] getArr()
    {
        double[] A=new double[200];
        for (int i=0; i<200; i++)
        A[i]=getNumber();
        return A;
    }
    public static void printArr(double[] A)
    {
        for (int i=0; i<20; i++)
        {
            for (int j=0; j<10; j++)
            {
                System.out.print(A[10*i+j]+", ");
            }
            System.out.println();
        }
    }
    public static void printList(double[] list)
    {
        for (int i=0; i<list.length; i++)
        {
            System.out.print (list[i]+", ");
            if ((i!=0)&&(i%10==0))
            System.out.println();
        }
    }
    /**
     * A non-rellevant method regarding to the sollution of the MAMAN.
     * @return a reandom integer number if the range of 0 to 1024.
     */
    private static double getNumber()
    {
        return Math.floor((Math.random()*1024));
    }
    /**
     * a method that take care of the edge case of k=0.
     * In a vacuous truth (BE'OFEN REK) the method does print the
     * k=0 lowest number in the checkpoints. Likewise, the method
     * does rtuen a list of the smallest k=0 numbers.
     */
    private static double[] kIsZero ()
    {
            return new double[0];
    }
    /**
     * the method scans the numbers from the given arrey into pq, until
     * there's a checkpoint. The method returns the number of numbers that
     * have been scanned from the given arrey fter the itteration of
     * this method.
     * @param scanned the number of numbers that have been scanned
     * from the given arrey so far.
     * @param checkpoint the next checkpoint - there, the algorithm need
     * to run pq.printkMin().
     * @param arr the given array
     * @param pq the priority queue that all the numbers that have been scanned
     * so far, scanned into this specific priotity queue.
     * @return the number of numbers that have been scanned from the given
     * arrey after the itteration of this method.
     */
    private static int whileScannedIsSmallerThenCheckpoint
    (int scanned, int checkpoint, double[] arr, PriorityQueue pq)
    {
        while (scanned<checkpoint)        //line 1
        {        //line 2
            pq.insert(arr[scanned]);        //line 3
            scanned++;        //line 4 
        }        //line 5
        return scanned;        //line 6
    }
    /**
     * the method gets the current checkpoint and returns the next
     * checkpoint in this order: n1 -> n2 -> n3 -> n
     * @param n1, n2, n3, n the given n1, n2, n3, n.
     * range: n1<n2<n3<n, all of them natural numbers or 0.
     * @param checkpoint the current checkpoint,
     * range: one of the given n1, n2, n3. [not n].
     * @return the next checkpoint in this order: n1 -> n2 -> n3 -> n
     */
    public static int promoteCheckpoint
    (int checkpoint, int n1, int n2, int n3, int n)
    {
        if (checkpoint==n1) return n2;
        if (checkpoint==n2) return n3;
        if (checkpoint==n3) return n;
        return -1; //the method will not get to here
    }
    /**
     * the method scans the numbers in the given array until each one
     * of the checkpoints (n1,n2,n3 and n). When it get to a checkpoint, the
     * method prints the k smallest numbers that have been scanned
     * so far. The checkpoint is being updated after that.
     * @param arr,k,n1,n2,n3 the given arrk,,n1,n2,n3.
     * range: arr is arrey of real numbers. k is a natural number.
     * n1,n2,n3 are non-negatuve integer numbers that
     * maintain n1<n2<n3<arr.length. 
     * @param pq an the priority queue in the size of k, that into it,
     * all the numbers from the array will be inserted. range: an empty
     * priority queue in the size of k.
     * @param scanned number of numbers that have been scanned from
     * the arrey at a current point of time.
     * @param n arr.length
     * @param checkpoint the next checpoints in a cuurent point of time
     * witch is, in this order n1, n2, n3, n.
     */
    private static void scanTheArrey
    (double[] arr, int k, int n1,int n2,int n3, PriorityQueue pq)
    {
        int scanned=0;      //line 1
        int n=arr.length;      //line 2
        int checkpoint=n1;      //line 3
        while (scanned<n)      //line 4
        {      //line 5
            scanned=whileScannedIsSmallerThenCheckpoint(scanned, checkpoint,arr, pq); //line 6
            pq.printkMin();      //line 7
            checkpoint=promoteCheckpoint(checkpoint, n1,n2,n3,n);      //line 8
        }      //line 9
    }
    /**
     * the method gets an array of real numbers, size k, 3 check points
     * n1,n2,n3. If k=0, the method returns array of real numbers in
     * length 0. Otherwise, the method initialize a new priority queue
     * in size k: (pq).
     * The method inserts each element of the array into pq. After
     * insrting n1 elements, after inserting n2 elements and after 
     * inserting n3 elements, the method
     * prints the k minimum numbers so far. After inserting all the 
     * elements of arr into pq, the method initialize a new list (array)
     * and inserts to the list the k
     * or the n numbers that are located in pq. This insertion is being
     * done like an in-order walk on pq.tree - therefore, the numbers
     * are being
     * inserted to the list in a sorted way (smallest to greatest).
     * The method returns the required list.
     * ranges: arr: an arrey of real numbers in a length of at
     * least 3 [explaination why 3 in 3 lines ahead].
     * k: a non-negative integer,
     * n1,n2,n3: non-negative integers that maintain
     * n1<n2<n3<arr.length. therefore arr.length must be equal or
     * greater then 3.
     * @param n the length of arr
     * @param theRequiredList the list that is being returned (in
     * case that k!=0).
     */
    public static double[] lowestK
    (double[] arr, int k, int n1, int n2, int n3)
    {
        int n=arr.length;       //line 1
        int checkpoint=n1;       //line 2
        PriorityQueue pq;       //line 3
        double[] theRequiredList;       //line4
        
        if (k==0)       //line 5
        return kIsZero();       //line 6
        
        pq=new PriorityQueue (k);       //line 7
        scanTheArrey (arr, k, n1, n2, n3, pq);       //line 8
        theRequiredList = pq.returnAsList();       //line 9
        return theRequiredList;       //line 10
     }
}