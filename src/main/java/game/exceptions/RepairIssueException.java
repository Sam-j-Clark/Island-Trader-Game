package game.exceptions;

/**
 * This Exception is used in the island trader game and is thrown when 
 * an issue occurs while repairing the players Ship. This can
 * occur for the following reasons... 
 * (1) The player cannot afford to repair the ship.
 * (2) The player tries to repair an already fully repaired ship.
 * 
 * @author Sam Clark
 * @author Wil Johnston
 */
public class RepairIssueException extends Exception 
{
	/**
	 * The Serial Version for RepairIssueException
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a RepairIssueException using the super class Exception.
	 * 
	 * @param errorText - To tell the user why the repair was illegal.
	 * @see   Exception
	 */
	public RepairIssueException(String errorText)
	{
		super(errorText);
	}
}
