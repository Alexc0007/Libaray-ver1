/**
 * 
 */

/**
 * @author Alexc007
 *
 *	 this class represents a book in the library , representation is by book name(code) and has an extra field to represent the book holder
 */
public class Book 
{
	public String bookName; //string to represent the name of the book
	public Person holder; // Person object to represent the book holder
	
	//constructor
	public Book(String name , Person student)
	{
		bookName = name;
		holder = student;
	}

}
