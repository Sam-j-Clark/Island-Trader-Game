package game.exceptions;

import game.controllerClasses.*;

/**
 * This Exception is used in the island trader game and is thrown when 
 * an issue occurs when a pirates RandomEvent causes the player to walk
 * the plank.
 * 
 * @see GameEnvironment#getPiratesResults()
 * @see RandomEvent#randomEvent()
 * @author Sam Clark
 */
public class WalkThePlankException extends Exception 
{
	
	/**
	 * The Serial Version for ShipUpgradeException.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a WalkThePlankException using the super class Exception.
	 * 
	 * @param errorText - To tell the user why they are walking the plank.
	 * @see   Exception
	 */
	public WalkThePlankException(String errorText)
	{
		super(errorText);
	}
}
