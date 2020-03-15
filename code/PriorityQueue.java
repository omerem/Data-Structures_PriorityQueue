/**
 * PriorityQueue is a pryority queue data base that is consracted to
 *  given 'k' real number elements. (This is priority queue except of
 *  the method increaseKey that I did not implement).
 *  For every new real number that is
 *  insert to the priority queue, if there's a place to another
 *  element in the priority queue, it is insert. Otherwise, the
 *  prioruty queue keeps the k lowest elements from the elements that
 *  in the priority queue and the given new element.
 * @param tree the red-black tree that inside it the elements are being keeped
 * @currentMaxValue the current maximum element in the priority queue.
 * If the priority queue is empty, the parameter the minimum real number.
 * @numberOfElements the number of elements in the priority queue in a
 * a current point of time.
 * @param k the given k [range: k is a possitive natural number]
 */
class PriorityQueue
{
    private RBTree tree;
    private double currentMaxValue;
    private int numberOfElements;
    private int k;
    /**
     * constructor
     * @param k the given k. [range: k is a possitive natural number]
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
     * prints the k smallest elements that have been exminiated so far.
     * It's done with the help of the known method RBTree.inorderTreeWalk().
     */
    public void printkMin()
    {
        tree.inorderTreeWalk(tree.getRoot());
        System.out.println();
    }
    /**
     * deletes the given pos node.
     * @param pos range: a node from the tree RBTree.
     */
    private RBTNode<Double> delete (RBTNode<Double> pos)
    {
        return tree.RBDelete(pos);
    }
    /**
     * extracts the maximum value of that inside the priority queue.
     * it's done by deleting the maximum of the tree,
     * finding the new maximum of the tree (if exists) and update
     * numberOfElements.
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
     * inserts a given z real value into tree.
     * @param z range: real numbers
     */
    private void insertValueToTree (double z)
    {
        tree.RBInsert
        (new RBTNode
        (z,tree.getBlack(),tree.getNil(),tree.getNil(),tree.getNil()));         
        numberOfElements++;
    }
    /**
     * checks if the given z value deserves to get into the priority queue:
     * if there's free space in the priority queue, inserts the given z value
     * into the tree.
     * if the priority queue is full, so the method inserts z only if z is
     * smaller the current maximum value.
     * @param z range: real numbers.
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
     * recurtion method that eventually inserts all the values in the
     * priority queue into a given arrey. The method does it
     * in a form of in-order 'walk' on tree.
     * @param pos a pointer that points to a specific vertex in each itteration.
     * range: in the first call: the root of tree,
     * in the next calls: a certain vertex of tree.
     * @param list into this arrey the values are being insert.
     * range: in the first call: an empty arrey of real numbers in
     * length of numberOfElements,
     * in the next calls: the very same list.
     * @param i the index that indicates where to insert the next insertion.
     * range: in the first call: 0, int the next calls: natural possive numbers.
     * @return the index of the smallest empty cell in list. if the list is full,
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
     * in the help of a recurtion method, iserts the values
     * of the priority queue
     * into an arrey in a sorted way, and returns the list to the user.
     * @return sorted arrey (smallest to greatest) of all the values in the
     * priority queue
     */
    public double[] returnAsList()//changed (added i)
    {
        double[] list=new double[numberOfElements];       //line 1
        returnAsListRecurtion(tree.getRoot(), list, 0);       //line 2
        return list;       //line 3
    }
}