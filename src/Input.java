/**
 * @author Alex Cherniak
 *this class contains functions that help us process the input - turns out there is only one :D
 */
//is integer method - checks if string is an integer
public class Input 
{
	public static boolean isInteger(String input)
	{
		boolean isValidInteger = false;
	      try
	      {
	         Integer.parseInt(input);
	 
	         // input is a valid integer
	         isValidInteger = true;
	      }
	      catch (NumberFormatException ex)
	      {
	         // input is not an integer
	    	  return false;
	      }
	      return isValidInteger;
	   }
	
}
