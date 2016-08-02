/**
 * @author Alex Cherniak
 *this class represents a node in the heap - it will hold a student(Person object)
 *and will allow us to build a heap to detect the student that has maximum amount of books out of
 *all the students in the library
 */
public class TreeNode
{
	//instance variables
    private TreeNode m_right;
    private TreeNode m_left;
    private TreeNode m_parent;
    private Person student;
    


    //constructor
    public TreeNode(Person student, TreeNode right, TreeNode left, TreeNode parent)
    {
    	this.setStudent(student);
        m_parent = parent;
        m_right = right;
        m_left = left;
    }
    // getters and setters
    public TreeNode getLeft(){ return m_left;}

    public TreeNode getRight(){return m_right;}

    public TreeNode getParent(){return m_parent;}

    public void setLeft(TreeNode val){m_left = val;}

    public void setRight(TreeNode val){m_right = val;}

    public void setParent(TreeNode val){m_parent = val;}

    public int getVal(){return getStudent().nofBooks;}

    public int getID() { return getStudent().getId();}
    
    public Person getStudent() 
	{
		return student;
	}
    
	public void setStudent(Person student) 
	{
		this.student = student;
	}

    //check if node is a leaf method
    public int isLeaf()
    { 
        if ((m_left == null) && (m_right == null))
            return 1;
        else
            return 0;
    }
}





 

  

 