package game.shipClasses;

/**
 * Subclass of {@link Ship}, TheJollyRodrick is one of the 4 options of ship's the player
 * can choose from when playing the island trader game. This class adds no extra
 * attributes onto the ship class and is used simply for ease of modifying this 
 * ships attributes.
 * 
 * @see Ship
 * @author Sam Clark
 * @author Wil Johnston
 */
public class JollyRodrick extends Ship
{
	private static String name = "The Jolly Rodrick";
	private static int crewSize = 5; // increases sail cost
	private static int maxWeight = 40;
	private static int maxHealth = 80;  // increases sail cost

	/**
	 * Creates an instance of the ship TheJollyRodrick from it's static attributes.
	 * @see Ship#Ship(String, int, int, int)
	 */
	public JollyRodrick() {
		super(name, crewSize, maxWeight, maxHealth);
	}

}
