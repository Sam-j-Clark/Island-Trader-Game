package game.shipClasses;

/**
 * Subclass of {@link Ship}, Carol is one of the 4 options of ship's the player
 * can choose from when playing the island trader game. This class adds no extra
 * attributes onto the ship class and is used simply for ease of modifying this 
 * ships attributes.
 * 
 * @see Ship
 * @author Sam Clark
 * @author Wil Johnston
 */
public class Carol extends Ship
{
	private static String name = "Carol";
	private static int crewSize = 3; // Speeds Up ship increases sail cost
	private static int maxWeight = 30; // Slows down ship
	private static int maxHealth = 100;  // increases sail cost

	/**
	 * Creates an instance of the ship Carol from it's static attributes.
	 * @see Ship#Ship(String, int, int, int)
	 */
	public Carol() 
	{
		super(name, crewSize, maxWeight, maxHealth);
	}
}
