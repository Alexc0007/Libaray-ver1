
public class BookManager 
{
	public final static int RED = 1;
	public final static int BLACK = 0;
	
	//instance variables
	public RBNode root; //the root of the tree
	public RBNode nullNode = new RBNode(null, null, null, null, null, BLACK); //both Person and Book fields will be Null on this variable
	
	//constructor
	public BookManager()
	{
		root = nullNode;
	}
	
	//check if tree is empty
	public boolean isEmpty()
	{
		if(root.book == null)
		{
			return true;
		}
		else return false;
	}
	
	//insert to tree method
	public void insert(RBNode newBook)
	{
		RBNode temp1 = nullNode; // y <- null(T)
		RBNode temp2 = root; //x <- root(T)
		while(temp2 != nullNode)
		{
			temp1 = temp2;
			if(newBook.book.bookName.compareTo(temp2.book.bookName) <0) //if (key[z] < key[x])
				temp2 = temp2.left; // x<- left[x]
			else 
				temp2 = temp2.right; // x<- right[x]
		}
		newBook.parent = temp1; // p[z] <- y
		if(temp1 == nullNode)
			root = newBook; //root[T] <- z
		else
		{
			if(newBook.book.bookName.compareTo(temp1.book.bookName) < 0) //if(key[z] < key[y])
			{
				temp1.left = newBook; //left[y] <- z
			}
			else
			{
				temp1.right = newBook; // right[y] <- z
			}
		}
		newBook.left = nullNode;
		newBook.right = nullNode;
		newBook.color = RED;
		rbInsertFixup(root , newBook);
	}
	
	//fix-up after insert method
	public void rbInsertFixup(RBNode root , RBNode newBook)
	{
		RBNode temp; // this is "y"
		while(newBook.parent.color == RED)
		{
			if(newBook.parent == newBook.parent.parent.left) //if(p[z] = left[p[p[z]]] - parent is a left son
			{
				temp = newBook.parent.parent.right; //y <- left[p[p[z]]]
				if(temp.color == RED)
				{
					newBook.parent.color = BLACK;           //case1
					temp.color = BLACK;                     //case1
					newBook.parent.parent.color = RED;      //case1
					newBook = newBook.parent.parent;        //case1
				}
				else
				{
					if(newBook == newBook.parent.right)    //newBook is right son
					{
						newBook = newBook.parent;          //case2
						leftRotate(root , newBook);        //case2
					}
					newBook.parent.color = BLACK;               //case3
					newBook.parent.parent.color = RED;          //case3
					rightRotate(root , newBook.parent.parent);  //case3
				}
			}
			else
			{
				temp = newBook.parent.parent.left;
				if(temp.color == RED)
				{
					newBook.parent.color = BLACK;           //case4
					temp.color = BLACK;                     //case4
					newBook.parent.parent.color = RED;      //case4
					newBook = newBook.parent.parent;        //case4
				}
				else
				{
					if(newBook == newBook.parent.left)      //newBook is left son
					{
						newBook = newBook.parent;           //case5
						rightRotate(root , newBook);        //case5
					}
					newBook.parent.color = BLACK;
					newBook.parent.parent.color = RED;
					leftRotate(root , newBook.parent.parent);
				}
				
			}
		}
		root.color = BLACK;
	}
	
	
	//rb-delete method
	public RBNode delete(RBNode root, RBNode bookRecord)
	{
		RBNode tempY;
		RBNode tempX;
		if(bookRecord.left == nullNode || bookRecord.right == nullNode) //means there is 1 son to the node we want to delete
		{
			tempY = bookRecord;
		}
		else 
			tempY = nodeSuccessor(bookRecord);
		
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
		if(tempY.book.bookName.compareTo(bookRecord.book.bookName) != 0) // name of temp1 != name of bookRecord
		{
			bookRecord.book.bookName = tempY.book.bookName;
		}
		if(tempY.color == BLACK)
		{
			deleteFixup(root , tempX);
		}
		return tempY;
	}
	//leftRotate method
	public void leftRotate(RBNode root , RBNode book)
	{
		RBNode temp = book.right;
		book.right = temp.left; //turn y's left subtree into x's right subtree
		if(temp.left !=  nullNode)
		{
			temp.left.parent = book;
		}
		temp.parent = book.parent; //link x's parent to y
		if(book.parent == nullNode)
		{
			root = temp;
		}
		else
		{
			if(book == book.parent.left)//x is left son
			{
				book.parent.left = temp;
			}
			else
			{
				book.parent.right = temp;
			}	
		}
		
		temp.left = book; //put x on y's left
		book.parent = temp;
	}
	
	//right rotate method
	public void rightRotate(RBNode root , RBNode book)
	{
		RBNode temp = book.left;
		book.left = temp.right; //turn y's left subtree into x's right subtree
		if(temp.right !=  nullNode)
		{
			temp.right.parent = book;
		}
		temp.parent = book.parent; //link x's parent to y
		if(book.parent == nullNode)
		{
			root = temp;
		}
		else
		{
			if(book == book.parent.right)//x is left son
			{
				book.parent.right = temp;
			}
			else
			{
				book.parent.left = temp;
			}	
		}
		
		temp.right = book; //put x on y's left
		book.parent = temp;
	}
	
	//find successor method
	public RBNode nodeSuccessor(RBNode book)
	{
		RBNode temp1; //y
		RBNode temp2; //x
		if(book == nullNode)
		{
			return nullNode;
		}
		if(book.right != nullNode)
		{
			return findMinimum(book.right);
		}
		temp1 = book.parent;
		temp2 = book;
		while(temp1 != nullNode && temp2 == temp1.right)
		{
			temp2 = temp1;
			temp1 = temp1.parent;
		}
		return temp1;
	}
	
	//find Minimum method
	private RBNode findMinimum(RBNode book)
	{
		RBNode min = book;
		while(min.left != nullNode)
		{
			min = min.left;
		}
		return min;
	}
	//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	//rb-deleteFixup method
	private void deleteFixup(RBNode root , RBNode book)
	{
		RBNode temp;
		while(book != root && book.color == BLACK)
		{
			if(book == book.parent.left)//book is a left son
			{
				temp = book.parent.right; //temp gets book's brother
				if(temp.color == RED) //student's brother is red
				{
					temp.color = BLACK;                //case1
					book.parent.color = RED;           //case1
					leftRotate(root, book.parent);     //case1
					temp = book.parent.right;          //case1
				}
				if(temp.left.color == BLACK && temp.right.color == BLACK)
				{
					temp.color = RED;                  //case2
					book = book.parent;                //case2
				}
				else
				{
					if(temp.right.color == BLACK)
					{
						temp.left.color = BLACK;       //case3
						temp.color = RED;              //case3
						rightRotate(root, temp);       //case3
						temp = book.parent.right;      //case3
					}
					temp.color = book.parent.color;    //case4
					book.parent.color = BLACK;         //case4
					temp.right.color = BLACK;          //case4
					leftRotate(root, book.parent);     //case4
					book = root;                       //case4
				}
			}
			else
			{
				temp = book.parent.left; //temp gets book's brother
				if(temp.color == RED)    //books's brother is red
				{
					temp.color = BLACK;                //case1
					book.parent.color = RED;           //case1
					rightRotate(root, book.parent);    //case1
					temp = book.parent.left;           //case1
				}
				if(temp.right.color == BLACK && temp.left.color == BLACK)
				{
					temp.color = RED;                 //case2
					book = book.parent;               //case2
				}
				else
				{
					if(temp.left.color == BLACK)
					{
						temp.right.color = BLACK;       //case3
						temp.color = RED;               //case3
						leftRotate(root, temp);         //case3
						temp = book.parent.left;        //case3
					}
					temp.color = book.parent.color;     //case4
					book.parent.color = BLACK;          //case4
					temp.left.color = BLACK;            //case4
					rightRotate(root, book.parent);     //case4
					book = root;                     //case4
				}
			}
		}
		book.color = BLACK;
	}
	//rbSearchBook method
	public RBNode searchBook(RBNode root , String bookName)
	{
		if(root == nullNode)
		{
			return nullNode;
		}
		if(root.book.bookName.equals(bookName)) //found the book we were looking for
		{
			return root;
		}
		if(root.book.bookName.compareTo(bookName)> 0) // book name on root is larger than bookName - we go left
		{
			return searchBook(root.left , bookName);
		}
		else //book name on root is smaller than bookName - we go right
		{
			return searchBook(root.right , bookName);
		}
	}
	
	//add book to records method
	public void addBookToRec(Book newBook)
	{
		RBNode toInsert = new RBNode(null, newBook, nullNode, nullNode, nullNode, RED);
		this.insert(toInsert);
	}
	//remove student from records method
	public void remBookFromRec(String bookName)
	{
		this.delete(root, searchBook(root, bookName));
	}
}
