/**
 * 
 */

/**
 * @author Alexc007
 *
 *	this class represents the students that borrow books from the library
 *	each student has an ID and a Last Name
 *	there are extra fields: array to store the books(objects) that the student has , integer that will indicate the number of books each student has
 */
public class Person 
{
	//instance variables
	private int id; //stores this person's id
	private String lastName;  //stores this person's Last Name
	public Book[] Books = new Book[10]; //store this person's list of books
	public int nofBooks;
	
	//constructor
	public Person(int personID , String PersonlastName)
	{
		id = personID;
		lastName = PersonlastName;
		nofBooks = 0; //set initial number of books to 0
	}
	
	/*
	 * get Last Name method
	 */
	public String getLastName()
	{
		return lastName;
	}
	
	
	/*
	 * get ID method
	 */
	public int getId()
	{
		return id;
	}
	
	
	/*
	 * get number of books of that person
	 */
	public int getNofBooks()
	{
		return nofBooks;
	}
	
	
	/*
	 * set Last Name of that person
	 */
	public void setLname(String name)
	{
		lastName = name;
	}
	
	/*
	 * set id for this person
	 */
	public void setID(int Newid)
	{
		id = Newid;
	}
	
	
	
	
}
