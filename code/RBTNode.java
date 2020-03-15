public class RBTNode<Double>
{
    private static final boolean black=true;
    private static final boolean red=false;
    
    private double key;
    private boolean color;
    private RBTNode<Double> p;
    private RBTNode<Double> left;
    private RBTNode<Double> right;
    
    public RBTNode (double key)
    {
        this.key=key;
        this.color=black;
        this.p=null;
        this.left=null;
        this.right=null;
    }
    public RBTNode
    (double key, boolean color, RBTNode<Double> p, RBTNode<Double> left, RBTNode<Double> right)
    {
        this.key=key;
        this.color=color;
        this.p=p;
        this.left=left;
        this.right=right;
    }
    public double getKey()
    {
        return key;
    }
    public Boolean getColor()
    {
        return color;
    }
    public RBTNode<Double> getP ()
    {
        return p;
    }
    public RBTNode<Double> getLeft()
    {
        return left;
    }
    public RBTNode<Double> getRight()
    {
        return right;
    }
    public void setKey (double key)
    {
        this.key=key;
    }
    public void setColor (Boolean color)
    {
        this.color=color;
    }
    public void setP (RBTNode<Double> p)
    {
        this.p=p;
    }
    public void setLeft (RBTNode<Double> left)
    {
        this.left=left;
    }
    public void setRight (RBTNode<Double> right)
    {
        this.right=right;
    }
    public String toString()
    {
        return String.valueOf(this.key);
    }
}