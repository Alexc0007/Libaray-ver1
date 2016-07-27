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
						
					}
					else // means that its a query to look for a book name and which person holds it
					{
						
					}
				}
				break;
			case ADD:  //add record input **case 3 on Maman Book**
				String name = splittedInput[1];
				id = Integer.parseInt(splittedInput[2]);
				studentRecords.addPerToRec(id , name);
				break;
			
			case REM: //remove record input  **case 4 on Maman Book**
				id = Integer.parseInt(splittedInput[2]);
				studentRecords.remPerFromRec(id);
				break;
			
				
			
			default: //means there is a name on the first "word" of input
				break;
			}
			
			System.out.println("please insert input command:");
		}
		
		
	}

}
