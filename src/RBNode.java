/**
 * @author Alexc007
 *this class represents a node that is part of a Red Black Tree , such node can contain information about
 *a Person(an object that is representing a student) or a Book(an object that is representing a book)
 */
public class RBNode
{
	
	
	//instance variables
	RBNode left , right , parent;
	public Person student;
	public Book book;
	public int color;
	
	//constructor
	//we can construct a node to hold book object or person object - depends on who we want to sort by
	public RBNode(Person person ,Book book, RBNode left , RBNode right , RBNode parent , int color)
	{
		student = person;
		this.book = book;
		this.left = left;
		this.right = right;
		this.parent = parent;
		this.color = color;
	}
	
	//method that print all books in the node
	public void printBooks()
	{
		int flag =0;
		for(int i=0 ; i< this.student.Books.length ; i++)
		{
			if(this.student.Books[i]!= null)
			{
				System.out.println(this.student.Books[i].bookName);
				flag++;
			}
		}
		if(flag == 0)
		{
			System.out.println("student has no books");
		}
	}
	
	//method to add book to student's book list
		public void addBookToStudent(String bookName , BookManager bookRecords , MaxHeap heap)
		{
			int i;
			if(this.student == null) //means there is no such student on the records
			{
				System.out.println("no such student on the records - book wasnt borrowed");
				return;
			}
			for(i=0 ; i<this.student.Books.length ; i++)
			{
				if(this.student.Books[i] == null)
				{
					this.student.Books[i] = new Book(bookName, this.student); //add book into books array
					this.student.nofBooks++;
					heap.maxHeapify(heap.getRoot());
					bookRecords.addBookToRec(this.student.Books[i]);
					System.out.println("book "+this.student.Books[i].bookName+" borrowed by "+this.student.getId()); //print who borrowed which book
					break;
				}
				if(i==this.student.Books.length-1)//means that array of books is full and student cant borrow more books
				{
					System.out.println("this student cannot borrow more books!!");
				}
			}	
		}
	//method to remove book from student's book list
	public void remBookFromStudent(String bookName , BookManager bookRecords , MaxHeap heap)
	{
		int i;
		RBNode toDelete;
		for(i=0 ; i<this.student.Books.length; i++) //loop to go over books array of that student and find the book we want to delete
		{
			if(this.student.Books[i] != null && this.student.Books[i].bookName.equals(bookName))
			{
				this.student.Books[i] = null; //erase book from students books array
				this.student.nofBooks--;
				heap.maxHeapify(heap.getRoot());
				toDelete = bookRecords.searchBook(bookRecords.root, bookName);
				bookRecords.delete(bookRecords.root, toDelete);
				System.out.println("book "+ bookName + " was retrieved by: "+this.student.getId()); //print a message that book has been retrieved
			}
		}
	}
	
}
