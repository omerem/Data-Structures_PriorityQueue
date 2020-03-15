/**
 * PriorityQueue is a pryority queue data base.
 *  In it's construction it is given k real valued elements. 
 *  For every new element inserted, if there's enough place - it is inserted.
 *  Otherwise, the prioruty queue keeps the k lowest elements among the elements
 *  in the priority queue and the new element.
 * @param tree Red-black tree that holds the elements.
 * @currentMaxValue The current maximum element in the priority queue.
 * @numberOfElements Number of elements.
 * @param k Size of the data base. [Range: k is a possitive natural number].
 */
class PriorityQueue
{
    private RBTree tree;
    private double currentMaxValue;
    private int numberOfElements;
    private int k;
    /**
     * constructor
     * @param k Size of the data base. [Range: k is a possitive natural number].
     */
    public PriorityQueue (int k)
    {
        tree=new RBTree();
        currentMaxValue=(-1)*Double.MAX_VALUE;
        this.k=k;
        numberOfElements=0;
    }
    /**
     * A non-rellevant method regarding to the sollution of the MAMAN.
     * @return the current maximum value.
     */
    public double maximum ()
    {
        return currentMaxValue;
    }
    /**
     * prints the k lowest elements that have been exminiated so far.
     * It's done with the help of the known method RBTree.inorderTreeWalk().
     */
    public void printkMin()
    {
        tree.inorderTreeWalk(tree.getRoot());
        System.out.println();
    }
    /**
     * Deletes the given pos node.
     * @param pos Range: a node from the tree RBTree.
     */
    private RBTNode<Double> delete (RBTNode<Double> pos)
    {
        return tree.RBDelete(pos);
    }
    /**
     * Extracts the maximum among the priority queue.
     * (It's done by deleting the maximum of the tree,
     * finding the new maximum of the tree (if exists) and update
     * numberOfElements).
     */
    private void extractMax()
    {
        if (numberOfElements>0)         //line 1
        {         //line 2
            delete(tree.maximum());         //line 3
            currentMaxValue=tree.maximum().getKey();         //line 4
            numberOfElements--;         //line 5
            if (numberOfElements==0) currentMaxValue=(-1)*Double.MAX_VALUE; //line 6
        }         //line 7
        else         //line 8
        {         //line 9
            System.out.println("error: extractMax but no elements");         //line 
        }         //line 
    }         //line 
    /**
     * Inserts a given z real valued number into tree.
     * @param z Range: Real numbers.
     */
    private void insertValueToTree (double z)
    {
        tree.RBInsert
        (new RBTNode
        (z,tree.getBlack(),tree.getNil(),tree.getNil(),tree.getNil()));         
        numberOfElements++;
    }
    /**
     * Checks if the given value z should get into the priority queue:
     * If there's enough place or it is lower then the current maximum.
     * @param z Range: real numbers.
     */
    public void insert (double z)
    {
        if (numberOfElements<k)         //line 1
        {         //line 2
            insertValueToTree(z);         //line 3
            if (currentMaxValue<z)         //line 4
            currentMaxValue=z;         //line 5
        }         //line 6
        else         //line 7
        {         //line 8
            if (z<currentMaxValue)         //line 9
            {         //line 10
                insertValueToTree(z);         //line 11
                extractMax();         //line 12
            }         //line 13
        }         //line 14
    }
    /**
     * Recurtion method that inserts all the elements into a
     * given array; It is done by "walking" on the tree in-order.
     * @param pos A pointer that points to a specific vertex in each itteration.
     * Range: In the first call -  the root of the tree,
     * in the next calls - a certain vertex of tree.
     * @param list Into this arrey the values are being inserted.
     * Range: In the first call - an empty arrey of real numbers in
     * length of numberOfElements, in the next calls - the very same list.
     * @param i The index that indicates where to insert the next insertion.
     * Range: in the first call: 0, in the next calls: Natural possive numbers.
     * @return The index of the lowest empty cell in list. If the list is full,
     * returns list.length. Likewise, the first call returns list.length
     */
    private int returnAsListRecurtion
    (RBTNode pos, double[] list, int i)
    {
        if (pos!=tree.getNil())           //line 1 
        {           //line 2
            i=returnAsListRecurtion(pos.getLeft(), list, i);   //line 3
            list[i]=pos.getKey();           //line 4
            i++;           //line 5
            i=returnAsListRecurtion(pos.getRight(), list, i);     //line 6
        }           //line 7
        return i;           //line 8
    }
    /**
     * A helper to the recursion method returnAsListRecurtion. Inserts the values
     * of the priority queue into an arrey in a sorted way, and returns the list to the user.
     * @return A sorted array (smallest to greatest) of all the values in the
     * priority queue.
     */
    public double[] returnAsList()//changed (added i)
    {
        double[] list=new double[numberOfElements];       //line 1
        returnAsListRecurtion(tree.getRoot(), list, 0);       //line 2
        return list;       //line 3
    }
}
