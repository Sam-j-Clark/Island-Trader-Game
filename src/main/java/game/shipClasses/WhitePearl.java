package game.shipClasses;

/**
 * Subclass of {@link Ship}, TheWhitePearl is one of the 4 options of ship's the player
 * can choose from when playing the island trader game. This class adds no extra
 * attributes onto the ship class and is used simply for ease of modifying this 
 * ships attributes.
 * 
 * @see Ship
 * @author Sam Clark
 * @author Wil Johnston
 */
public class WhitePearl extends Ship
{
	private static String name = "The White Pearl";
	private static int crewSize = 6; // Speeds Up ship increases sail cost
	private static int maxWeight = 100; // Slows down ship
	private static int maxHealth = 100;  // increases sail cost

	/**
	 * Creates an instance of the ship TheWhitePearl from it's static attributes.
	 * @see Ship#Ship(String, int, int, int)
	 */
	public WhitePearl() {
		super(name, crewSize, maxWeight, maxHealth);
	}
}