package game.exceptions;

/**
 * This Exception is used in the island trader game is thrown when 
 * the characters name fails to reach any of the following criteria.
 * (1) The name is not between 3 - 15 characters long inclusive.
 * (2) The name contains characters that are not a-z, A-Z or " ".
 * (3) The name contains consecutive blank space characters.
 * 
 * @author Sam Clark
 * @author Wil Johnston
 */
public class IllegalNameException extends Exception
{
	/**
	 * The Serial Version for IllegalNameException
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs an IllegalNameException using the super class Exception.
	 * 
	 * @param errorText - To tell the user why the name was illegal.
	 * @see   Exception
	 */
	public IllegalNameException(String errorText)
	{
		super(errorText);
	}
}
