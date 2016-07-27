/**
 * 
 */

/**
 * @author Alexc007
 *
 */
public class StudentManager 
{
	public final static int RED = 1;
	public final static int BLACK = 0;
	
	//instance variables
	public RBNode root; //the root of the tree
	public RBNode nullNode = new RBNode(null, null, null, null, null, BLACK); //both Person and Book fields will be Null on this variable
	
	//constructor
	public StudentManager()
	{
		root = nullNode;
	}
	
	//check if tree is empty
	public boolean isEmpty()
	{
		if(root.student == null)
		{
			return true;
		}
		else return false;
	}
	
	//insert to tree method
	public void insert(RBNode newStudent)
	{
		RBNode temp1 = nullNode; // y <- null(T)
		RBNode temp2 = root; //x <- root(T)
		while(temp2 != temp1)
		{
			temp1 = temp2;
			if(newStudent.student.getId() < temp2.student.getId()) //if (key[z] < key[x])
				temp2 = temp2.left; // x<- left[x]
			else 
				temp2 = temp2.right; // x<- right[x]
		}
		newStudent.parent = temp1; // p[z] <- y
		if(temp1 == nullNode)
			root = newStudent; //root[T] <- z
		else
		{
			if(newStudent.student.getId() < temp1.student.getId()) //if(key[z] < key[y])
			{
				temp1.left = newStudent; //left[y] <- z
			}
			else
			{
				temp1.right = newStudent; // right[y] <- z
			}
		}
		newStudent.left = nullNode;
		newStudent.right = nullNode;
		newStudent.color = RED;
		rbInsertFixup(root , newStudent);
	}
	
	//fix-up after insert method
	public void rbInsertFixup(RBNode root , RBNode newStudent)
	{
		RBNode temp; // this is "y"
		while(newStudent.parent.color == RED)
		{
			if(newStudent.parent == newStudent.parent.parent.left) //if(p[z] = left[p[p[z]]] - parent is a left son
			{
				temp = newStudent.parent.parent.right; //y <- left[p[p[z]]]
				if(temp.color == RED)
				{
					newStudent.parent.color = BLACK;        //case1
					temp.color = BLACK;                     //case1
					newStudent.parent.parent.color = RED;   //case1
					newStudent = newStudent.parent.parent;  //case1
				}
				else
				{
					if(newStudent == newStudent.parent.right) //newStudent is right son
					{
						newStudent = newStudent.parent;    //case2
						leftRotate(root , newStudent);     //case2
					}
					newStudent.parent.color = BLACK;       //case3
					newStudent.parent.parent.color = RED;  //case3
					rightRotate(root , newStudent.parent.parent);  //case3
				}
			}
			else
			{
				temp = newStudent.parent.parent.left;
				if(temp.color == RED)
				{
					newStudent.parent.color = BLACK;              //case4
					temp.color = BLACK;                     //case4
					newStudent.parent.parent.color = RED;   //case4
					newStudent = newStudent.parent.parent;  //case4
				}
				else
				{
					if(newStudent == newStudent.parent.left) //newStudent is left son
					{
						newStudent = newStudent.parent;     //case5
						rightRotate(root , newStudent);     //case5
					}
					newStudent.parent.color = BLACK;
					newStudent.parent.parent.color = RED;
					leftRotate(root , newStudent.parent.parent);
				}
				
			}
		}
		root.color = BLACK;
	}
	
	
	//rb-delete method
	public RBNode delete(RBNode root, RBNode studentRecord)
	{
		RBNode tempY;
		RBNode tempX;
		if(studentRecord.left == nullNode || studentRecord.right == nullNode) //means there is 1 son to the node we want to delete
		{
			tempY = studentRecord;
		}
		else 
			tempY = nodeSuccessor(studentRecord);
		
		if(tempY.left != nullNode)
		{
			tempX = tempY.left;
		}
		else
		{
			tempX = tempY.right;
		}
		tempX.parent = tempY.parent;
		if(tempY.parent == nullNode)
		{
			root = tempX;
		}
		else
		{
			if(tempY == tempY.parent.left)
			{
				tempY.parent.left = tempX;
			}
			else
			{
				tempY.parent.right = tempX;
			}
		}
		if(tempY.student.getId() != studentRecord.student.getId())
		{
			studentRecord.student.setID(tempY.student.getId());
		}
		if(tempY.color == BLACK)
		{
			deleteFixup(root , tempX);
		}
		return tempY;
	}
	//leftRotate method
	public void leftRotate(RBNode root , RBNode student)
	{
		RBNode temp = student.right;
		student.right = temp.left; //turn y's left subtree into x's right subtree
		if(temp.left !=  nullNode)
		{
			temp.left.parent = student;
		}
		temp.parent = student.parent; //link x's parent to y
		if(student.parent == nullNode)
		{
			root = temp;
		}
		else
		{
			if(student == student.parent.left)//x is left son
			{
				student.parent.left = temp;
			}
			else
			{
				student.parent.right = temp;
			}	
		}
		
		temp.left = student; //put x on y's left
		student.parent = temp;
	}
	
	//right rotate method
	public void rightRotate(RBNode root , RBNode student)
	{
		RBNode temp = student.left;
		student.left = temp.right; //turn y's left subtree into x's right subtree
		if(temp.right !=  nullNode)
		{
			temp.right.parent = student;
		}
		temp.parent = student.parent; //link x's parent to y
		if(student.parent == nullNode)
		{
			root = temp;
		}
		else
		{
			if(student == student.parent.right)//x is left son
			{
				student.parent.right = temp;
			}
			else
			{
				student.parent.left = temp;
			}	
		}
		
		temp.right = student; //put x on y's left
		student.parent = temp;
	}
	
	//find successor method
	public RBNode nodeSuccessor(RBNode student)
	{
		RBNode temp1; //y
		RBNode temp2; //x
		if(student == nullNode)
		{
			return nullNode;
		}
		if(student.right != nullNode)
		{
			return findMinimum(student.right);
		}
		temp1 = student.parent;
		temp2 = student;
		while(temp1 != nullNode && temp2 == temp1.right)
		{
			temp2 = temp1;
			temp1 = temp1.parent;
		}
		return temp1;
	}
	
	//find Minimum method
	private RBNode findMinimum(RBNode student)
	{
		RBNode min = student;
		while(min.left != nullNode)
		{
			min = min.left;
		}
		return min;
	}
	
	//rb-deleteFixup method
	private void deleteFixup(RBNode root , RBNode student)
	{
		RBNode temp;
		while(student != root && student.color == BLACK)
		{
			if(student == student.parent.left)//student is a left son
			{
				temp = student.parent.right; //temp gets student's brother
				if(temp.color == RED) //student's brother is red
				{
					temp.color = BLACK;                //case1
					student.parent.color = RED;        //case1
					leftRotate(root, student.parent);  //case1
					temp = student.parent.right;       //case1
				}
				if(temp.left.color == BLACK && temp.right.color == BLACK)
				{
					temp.color = RED;                 //case2
					student = student.parent;          //case2
				}
				else
				{
					if(temp.right.color == BLACK)
					{
						temp.left.color = BLACK;       //case3
						temp.color = RED;              //case3
						rightRotate(root, temp);       //case3
						temp = student.parent.right;   //case3
					}
					temp.color = student.parent.color; //case4
					student.parent.color = BLACK;      //case4
					temp.right.color = BLACK;          //case4
					leftRotate(root, student.parent);  //case4
					student = root;                    //case4
				}
			}
			else
			{
				temp = student.parent.left; //temp gets student's brother
				if(temp.color == RED) //student's brother is red
				{
					temp.color = BLACK;                //case1
					student.parent.color = RED;        //case1
					rightRotate(root, student.parent);  //case1
					temp = student.parent.left;       //case1
				}
				if(temp.right.color == BLACK && temp.left.color == BLACK)
				{
					temp.color = RED;                 //case2
					student = student.parent;         //case2
				}
				else
				{
					if(temp.left.color == BLACK)
					{
						temp.right.color = BLACK;       //case3
						temp.color = RED;               //case3
						leftRotate(root, temp);         //case3
						temp = student.parent.left;     //case3
					}
					temp.color = student.parent.color;  //case4
					student.parent.color = BLACK;       //case4
					temp.left.color = BLACK;            //case4
					rightRotate(root, student.parent);  //case4
					student = root;                     //case4
				}
			}
		}
		student.color = BLACK;
	}
	//rbSearchPerson method
	public RBNode searchPerson(RBNode root , int id)
	{
		if(root == nullNode)
		{
			return nullNode;
		}
		if(root.student.getId() == id) //found the value we were looking for
		{
			return root;
		}
		if(root.student.getId() > id) // value on root is larger than ID - we go left
		{
			return searchPerson(root.left , id);
		}
		else //value on root is smaller than ID - we go right
		{
			return searchPerson(root.right , id);
		}
	}
	
	//add person to records method
	public void addPerToRec(int id , String name)
	{
		Person tempStudent = new Person(id, name);
		RBNode toInsert = new RBNode(tempStudent, null, nullNode, nullNode, nullNode, RED);
		this.insert(toInsert);
	}
	//remove student from records method
	public void remPerFromRec(int id)
	{
		this.delete(root, searchPerson(root, id));
	}
		
}
