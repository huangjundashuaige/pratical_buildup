
public class SparseGridNode
{
    public Object node;
    public int col;
    public SparseGridNode nextNode;
    public SparseGridNode(Object node,int col)
    {
        this.node=node;
        this.col=col;
        this.nextNode=null;
    }
    public SparseGridNode()
    {
        this.node=null;
        this.col=-1;
        this.nextNode=null;
    }
    public SparseGridNode(SparseGridNode sgn)
    {
        this.node = sgn.node;
        this.col = sgn.col;
        this.nextNode = sgn.nextNode;
    }
    public SparseGridNode clone()
    {
        return new SparseGridNode(this);
    }
}