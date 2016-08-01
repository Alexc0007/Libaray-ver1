
public class TreeNode

{

    private TreeNode m_right;
    private TreeNode m_left;
    private TreeNode m_parent;
    private Person student;
    private int m_val;



    
    //constructor
    public TreeNode(Person student, TreeNode right, TreeNode left, TreeNode parent)
    {
    	this.student = student;
        m_parent = parent;
        m_right = right;
        m_left = left;
    }

    public TreeNode getLeft(){ return m_left;}

    public TreeNode getRight(){return m_right;}

    public TreeNode getParent(){return m_parent;}

    public void setLeft(TreeNode val){m_left = val;}

    public void setRight(TreeNode val){m_right = val;}

    public void setParent(TreeNode val){m_parent = val;}

    public int getVal(){return student.nofBooks;}
    

    public int getID() { return student.getId();}

    public void setVal(int val){ m_val = val;}

    

    public int isLeaf()

    { 

        if ((m_left == null) && (m_right == null))

            return 1;

        else

            return 0;

    }

    

        

}





 

  

 