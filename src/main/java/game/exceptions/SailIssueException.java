package game.exceptions;

import game.controllerClasses.GameEnvironment;

/**
 * This Exception is used in the island trader game is thrown when 
 * the player tries to sail between islands but are unable to.
 * <br><br>
 * 
 * This Exception is thrown from the {@link GameEnvironment#sail()} method
 * when either ...
 * <br>
 * (1) The player is already at the island they tried to sail to. <br>
 * (2) The player cannot afford to sail to the island they attempted to sail to.
 * 
 * @author Sam Clark
 * @author Wil Johnston
 */
public class SailIssueException extends Exception 
{
	/**
	 * The Serial version for this SailIssueException
	 */
	private static final long serialVersionUID = 1L;

	public SailIssueException(String errorMessage)
	{
		super(errorMessage);
	}
}