/**
 * @author Alex Cherniak
 *this class represents a Max Heap , that will allow us to access the student with maximum amount of
 *books out of all the library students
 *its built out of Treenodes
 */
import java.util.*;


public class MaxHeap
{
	//instance variables
    private TreeNode root;
    private int nodeCount;
    private int currentLevel;
  
    //constructor
    public MaxHeap()
    {
        root= null;
        nodeCount=0;
        currentLevel=0;
    }

    //swap method
    public void swap(TreeNode t1, TreeNode t2)
    {
        Person tempS = t1.getStudent();
        t1.setStudent(t2.getStudent());
        t2.setStudent(tempS);
    }
	
	//ipow method
	private int ipow(int base, int exp)
	{
	    int result = 1;
	    while (exp > 0)
	    {
	        if ((exp & 1) != 0)
	            result *= base;
	        exp >>= 1;
	        base *= base; 
	    }
	    return result;
	}
	
	    
	//insert value method
	public void insertVal(Person student)
	{
	    int mask=0;
	    TreeNode p;
	    if(nodeCount==0)// case that the Heap is empty
	    {
	        root= new TreeNode(student, null, null, null);
	        nodeCount=1;
	        currentLevel=1;
	        student.heapNode = root;
	        return;
	    }
	    nodeCount = nodeCount +1;
	    if((ipow(2,currentLevel)) == nodeCount)//case that the Heap is full
	    {
	       p= root;
	       while (p.getLeft()!=null)
	       p= p.getLeft();
	       p.setLeft(new TreeNode(student,null,null,p));
	       p.getLeft().setParent(p);
	       currentLevel= currentLevel + 1;
	       p = p.getLeft();
	       student.heapNode = p;
	    }
	    else
	    {
	    	mask= ipow(2,currentLevel-2);
	        p= root; 
	        while (mask>1)
	        {
	            if ((nodeCount & mask)!=0)
	            {
	                p=p.getRight();
	            }
	            else
	            {
	                p=p.getLeft();
	            }
	            mask = mask/2;
	        }
	        student.heapNode = p;
	        if ((nodeCount %2)==1)//the lest level before the insert just choose letf or right
	        {
	        	p.setRight(new TreeNode(student,null,null,p));
	           	p= p.getRight();
	           	student.heapNode = p;
	        }
	
	            else
	        {
	           p.setLeft(new TreeNode(student,null,null,p));
	           p= p.getLeft();
	           student.heapNode = p;
	        }
	    }   
	    while ((p.getParent()!=null) &&(p.getVal()> p.getParent().getVal()))
	       { 
	            swap(p,p.getParent());
	            p=p.getParent();
	       }
	}
	
	//heapify method
	public void maxHeapify(TreeNode p)
	{
	    if (p.isLeaf()==0)
	        if (((p.getLeft()!=null)&&(p.getVal()<p.getLeft().getVal())|| ((p.getRight()!=null)&&(p.getVal()<p.getRight().getVal()))))
	            if(p.getLeft()==null)
	            {
	                swap(p,p.getRight());
	                maxHeapify(p.getRight());
	            }
	            else if (p.getRight()==null)
	            {
	                swap(p,p.getLeft());
	                maxHeapify(p.getLeft());   
	            }
	            else if (p.getLeft().getVal()>p.getRight().getVal())
	            {
	                swap(p,p.getLeft());
	                maxHeapify(p.getLeft());  
	            }
	            else
	             {
	                swap(p,p.getRight());
	                maxHeapify(p.getRight());
	             }
	}
	
	    
	//heapify recursive method
	public void maxHeapRec( TreeNode p)
	{
	    if((p==null)||(p.isLeaf()==1))
	        return;
	    maxHeapify(p);
	    maxHeapRec(p.getLeft());
	    maxHeapRec(p.getRight());        
	}
	
	public void maxHeap()
	{
	    maxHeapRec(root);
	}   
	//print students that has max amount of books method
	public void printTree()
	{
	    TreeNode p = root;
	    System.out.println(p.getID());
	    List<TreeNode> thisLevel = new ArrayList<TreeNode>();
	    thisLevel.add(p);
	    while (thisLevel.size() >0)
	    {
	        List<TreeNode> nextLevel = new ArrayList<TreeNode>();
	        for (TreeNode t : thisLevel)
	        {
	            if ((t.getLeft() != null)&&(t.getLeft().getVal()==t.getVal()))
	            {
	                nextLevel.add(t.getLeft());
	                System.out.print("," + t.getLeft().getID());
	            }
	            if ((t.getRight() != null)&&(t.getRight().getVal()==t.getVal()))
	            {
	                nextLevel.add(t.getRight());
	                System.out.print("," + t.getRight().getID());
	            }
	        }
	        thisLevel = nextLevel;
	    }
	}
	//getRoot method
	public TreeNode getRoot()
	{
		return root;
	}
	
}