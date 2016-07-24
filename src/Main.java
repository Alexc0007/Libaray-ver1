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
		String input;
		Scanner scan = new Scanner(System.in); //scanner object to read the input
		System.out.println("please insert input command:");
		input = scan.nextLine();
		while(!(input.equals("exit")))
		{
			String[] splittedInput = input.split(" ");//an array of all the "words - signs" on the input
			switch (splittedInput[0])
			{
			case QUERY:  //query input
				if(Input.isInteger(splittedInput[1])== true) //means that its a query to look for Person ID and which books does he hold
				{
					
				}
				else // means that its a query to look for a book name and which person holds it
				{
					
				}
				break;
			case ADD:  //add record input
				break;
			
			case REM: //remove record input 
				break;
			
				
			
			default: //means there is a name on the first "word" of input
				break;
			}
			
			System.out.println("please insert input command:");
		}
		
		
	}

}
