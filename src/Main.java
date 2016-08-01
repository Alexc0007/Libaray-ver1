import java.util.Scanner;
import java.util.*;


public class Main 
{
	//constants
	final static String QUERY = "?";
	final static String ADD = "+";
	final static String REM = "-";
	
	public static void main(String[] args)
	{
		int id;
		String input;
		RBNode result;
		StudentManager studentRecords = new StudentManager();
		BookManager bookRecords = new BookManager();
		MaxHeap heap = new MaxHeap();
		Scanner scan = new Scanner(System.in); //scanner object to read the input
		System.out.println("please insert input command:");
		input = scan.nextLine();
		while(!(input.equals("exit")))
		{
			String[] splittedInput = input.split(" ");//an array of all the "words - signs" on the input
			switch (splittedInput[0])
			{
			case QUERY:  //query input
				if(Input.isInteger(splittedInput[1])== true) //means that its a query to look for Person ID and which books does he hold **case 1 on query Maman**
				{
					id = Integer.parseInt(splittedInput[1]); //convert the ID that is represented as string into integer
					result = studentRecords.searchPerson(studentRecords.root , id);   //now look for it in tree
					result.printBooks(); //print all the books in result RBNode
				}
				else 
				{
					if(splittedInput[1].equals("!")) //need to find the students that hold the biggest number of books
					{
						heap.printTree(); //print students that have maximum amount of books
					}
					else // means that its a query to look for a book name and which person holds it (splittedInput[1] is the bookName)
					{
						result = bookRecords.searchBook(bookRecords.root,splittedInput[1]); //find the book in the book rbTree
						System.out.println(result.book.holder.getId() +"-"+ result.book.holder.getLastName());  //print the holders id and last name
					}
				}
				break;
			case ADD:  //add record input **case 3 on Maman Book**
				String name = splittedInput[1];
				id = Integer.parseInt(splittedInput[2]);
				studentRecords.addPerToRec(id , name, heap);
				break;
			
			case REM: //remove record input  **case 4 on Maman Book**
				id = Integer.parseInt(splittedInput[2]);
				studentRecords.remPerFromRec(id);
				break;
			
				
			
			default: //means there is a name on the first "word" of input
				if(splittedInput[3].equals(ADD)) //means a person is borrowing a book **Maman case 1**
				{
					id = Integer.parseInt(splittedInput[1]); //convert the ID that is represented as string into integer
					result = studentRecords.searchPerson(studentRecords.root, id); //find the student on student records
					result.addBookToStudent(splittedInput[2], bookRecords , heap);
					
				}
				else //means that a person is retrieving a book **Maman case 2**
				{
					id = Integer.parseInt(splittedInput[1]); //convert the ID that is represented as string into integer
					result = studentRecords.searchPerson(studentRecords.root, id); //find the student on student records
					result.remBookFromStudent(splittedInput[2], bookRecords , heap);
				}
				break;
			}
			
			System.out.println("please insert input command:");
			input = scan.nextLine();
		}
		
		
	}

}



