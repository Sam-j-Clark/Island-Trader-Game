package game.exceptions;

/**
 * This Exception is used in the island trader game and is thrown when 
 * an issue occurs while upgrading the players Ship.
 * <br>
 * This can occur for the following reasons... 
 * <br>
 * (1) The Ship already has the Upgrade that the player wants to buy. <br>
 * (2) The player cannot afford the Upgrade they are trying to buy.
 * 
 * @author Sam Clark
 * @author Wil Johnston
 */
public class ShipUpgradeException extends Exception 
{
	/**
	 * The Serial Version for ShipUpgradeException
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a ShipUpgradeException using the super class Exception.
	 * 
	 * @param errorText - To tell the user why the upgrade was illegal.
	 * @see   Exception
	 */
	public ShipUpgradeException(String errorText)
	{
		super(errorText);
	}
	
}
