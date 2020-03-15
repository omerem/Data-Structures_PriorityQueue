/**
 * @author Omer Kerem
 */
public class RBTree
{
    private static final boolean black=true;
    private static final boolean red=false;
    
    private RBTNode<Double> Nil;
    private RBTNode<Double> root;
    public RBTree()
    {
        Nil=new RBTNode((-1)*Double.MAX_VALUE, black, Nil, Nil, Nil);
        Nil.setP(Nil);
        Nil.setLeft(Nil);
        Nil.setRight(Nil);
        root=Nil;
    }
    public RBTNode getRoot()
    {
        return root;
    }
    public RBTNode getNil()
    {
        return Nil;
    }
    public boolean getBlack()
    {
        return black;
    }
    public boolean getRed()
    {
        return red;
    }
    public RBTNode<Double> minimum(RBTNode<Double> x)
    {
        while (x.getLeft()!=Nil)
        x=x.getLeft();
        return x;
    }
    public RBTNode<Double> minimum ()
    {
        return minimum (root);
    }
    public RBTNode<Double> maximum(RBTNode<Double> x)
    {
        while (x.getRight()!=Nil)
        x=x.getRight();
        return x;
    } 
    public RBTNode<Double> maximum()
    {
        return maximum(root);
    } 
    public RBTNode<Double> successor (RBTNode<Double> x)
    {
        RBTNode<Double> y;
        if (x.getRight()!=Nil)
        return minimum(x.getRight());
        y=x.getP();
        while ((y!=Nil)&&(x==y.getRight()))
        {
            x=y;
            y=y.getP();
        }
        return y;
    }
    private void leftRotate (RBTNode<Double> x)
    {
        RBTNode<Double> y;
        y=x.getRight();
        x.setRight(y.getLeft());
        if (y.getLeft()!=Nil)
        (y.getLeft()).setP(x);
        y.setP(x.getP());
        if (x.getP()==Nil)
        root=y;
        else
        if (x==(x.getP()).getLeft())
        (x.getP()).setLeft(y);
        else
        (x.getP()).setRight(y);
        y.setLeft(x);
        x.setP(y);
    }
    private void rightRotate (RBTNode<Double> x)
    {
        RBTNode<Double> y;
        y=x.getLeft();
        x.setLeft(y.getRight());
        if (y.getRight()!=Nil)
        (y.getRight()).setP(x);
        y.setP(x.getP());
        if (x.getP()==Nil)
        root=y;
        else
        if (x==(x.getP()).getRight())
        (x.getP()).setRight(y);
        else
        (x.getP()).setLeft(y);
        y.setRight(x);
        x.setP(y);
    }
    public void RBInsert(RBTNode<Double> z)
    {
        RBTNode<Double> x,y;
        if (isEmpty())
        {
            root=z;
        }
        else
        {
            y=Nil;
            x=root;
            while (x!=Nil)
            {
                y=x;
                if (z.getKey()<(x.getKey()))
                x=x.getLeft();
                else
                x=x.getRight();
            }
            z.setP(y);
            if (y==Nil)
            root=z;
            else
            if (z.getKey()<y.getKey())
            y.setLeft(z);
            else
            y.setRight(z);
            z.setLeft(Nil);
            z.setRight(Nil);
            z.setColor(red);
            RBInsertFixup(z);
        }
    }
    private boolean isEmpty()
    {
        return (root==Nil);
    }
    public void insert (double z)
    {
        RBInsert (new RBTNode<Double> (z, black, Nil,Nil,Nil));
    }
    private void RBInsertFixup(RBTNode<Double> z)
    {
        RBTNode<Double> y;
        while (((z.getP()).getColor())==red)
        {
            if (z.getP()== (((z.getP()).getP()).getLeft()))
            {
                y= z.getP().getP().getRight();
                if (y.getColor()==red)
                {
                    z.getP().setColor(black);
                    y.setColor(black);
                    z.getP().getP().setColor(red);
                    z=z.getP().getP();
                }
                else
                {
                    if (z==z.getP().getRight())
                    {
                        z=z.getP();
                        leftRotate(z);
                    }
                    z.getP().setColor(black);
                    z.getP().getP().setColor(red);
                    rightRotate(z.getP().getP());
                }
            }
            else
            {
                y= z.getP().getP().getLeft();
                if (y.getColor()==red)
                {
                    z.getP().setColor(black);
                    y.setColor(black);
                    z.getP().getP().setColor(red);
                    z=z.getP().getP();
                }
                else
                {
                    if (z==z.getP().getLeft())
                    {
                        z=z.getP();
                        rightRotate(z);
                    }
                    z.getP().setColor(black);
                    z.getP().getP().setColor(red);
                    leftRotate(z.getP().getP());
                }
            }
        }
        root.setColor(black);
    }
    public RBTNode<Double> RBDelete (RBTNode<Double> z)
    {
        RBTNode<Double> x, y;
        if ((z.getLeft()==Nil)||(z.getRight()==Nil))
        y=z;
        else
        y=successor(z);
        if (y.getLeft()!=Nil)
        x=y.getLeft();
        else
        x=y.getRight();
        x.setP(y.getP());
        if (y.getP()==Nil)
        root=x;
        else
        if (y==y.getP().getLeft())
        y.getP().setLeft(x);
        else
        y.getP().setRight(x);
        if (y!=z)
        {
            z.setKey(y.getKey());
            //copy y's satelight data into z.
            //Not rellevant in this program.
        }
        if (y.getColor()==black)
        RBDeleteFixup(x);
        return y;
    }
    private void RBDeleteFixup (RBTNode x)
    {
        RBTNode<Double> w;
        while ((x!=root)&&(x.getColor()==black))
        {
            if (x==x.getP().getLeft())
            {
                w=x.getP().getRight();
                if (w.getColor()==red)
                {
                    w.setColor(black);
                    x.getP().setColor(red);
                    leftRotate (x.getP());
                    w=x.getP().getRight();
                }
                if ((w.getLeft().getColor()==black)&&
                (w.getRight().getColor()==black))
                {
                    w.setColor(red);
                    x=x.getP();
                }
                else
                {
                    if (w.getRight().getColor()==black)
                    {
                        w.getLeft().setColor(black);
                        w.setColor(red);
                        rightRotate(w);
                        w=x.getP().getRight();
                    }
                    w.setColor(x.getP().getColor());
                    x.getP().setColor(black);
                    w.getRight().setColor(black);
                    leftRotate(x.getP());
                    x=root;
                }
            }
            else
            {
                w=x.getP().getLeft();
                if (w.getColor()==red)
                {
                    w.setColor(black);
                    x.getP().setColor(red);
                    rightRotate (x.getP());
                    w=x.getP().getLeft();
                }
                if ((w.getRight().getColor()==black)&&
                (w.getLeft().getColor()==black))
                {
                    w.setColor(red);
                    x=x.getP();
                }
                else
                {
                    if (w.getLeft().getColor()==black)
                    {
                        w.getRight().setColor(black);
                        w.setColor(red);
                        leftRotate(w);
                        w=x.getP().getLeft();
                    }
                    w.setColor(x.getP().getColor());
                    x.getP().setColor(black);
                    w.getLeft().setColor(black);
                    rightRotate(x.getP());
                    x=root;
                }
            }
        }
    }
    public void inorderTreeWalk(RBTNode x)
    {
        if (x!=Nil)
        {
            inorderTreeWalk(x.getLeft());
            System.out.print (x.getKey()+", ");
            inorderTreeWalk(x.getRight());
        }
    }
    public void inorderTreeWalk()
    {
        inorderTreeWalk(root);
    }
}