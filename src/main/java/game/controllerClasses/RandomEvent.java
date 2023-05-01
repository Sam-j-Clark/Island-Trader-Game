package game.controllerClasses;

import java.util.Random;
/**
 * This Class uses the Random Class to generate random Events in the island trader 
 * game. This RandomEvent Class is used to get random values each time the ship
 * sails between islands. This class controls whether the random events of pirates, 
 * bad weather or shipwrecked sailors occur while sailing. This Class also controls
 * dice rolls, random damage amounts and random money amounts for the random events
 * 
 * @see GameEnvironment#badWeather()
 * @see GameEnvironment#getPiratesResults()
 * @see GameEnvironment#startPiratesEvent()
 * @see GameEnvironment#shipwreckedSailors()
 * @see Random
 *
 * @author Sam Clark
 * @author Wil Johnston
 */
public class RandomEvent 
{
	private Random random = new Random();
	private GameEnvironment gameController;
	
	/**
	 * Creates an instance of this RandomEvent Class. 
	 * 
	 * @param gameController - the gameController that runs the Island Trader Game
	 */
	RandomEvent(GameEnvironment gameController)
	{
		this.gameController = gameController;
	}
	
	/**
	 * Uses Random to get a damageTaken value for the bad weather random event
	 * 
	 * @return damageTaken - The value of damageTaken for the {@link GameEnvironment#badWeather()}
	 * random event.
	 */
	public int takeDamage()
	{
		int damageTaken = random.nextInt(20) + 15;
		return damageTaken;
	}
	
	/**
	 * This method is run every time the player sails in the island trader game.
	 * It determines randomly if a random event occurs and if so which event. 
	 * This method triggers the appropriate random event in GameEnvironmentt.
	 * 
	 * @see GameEnvironment#badWeather()
	 * @see GameEnvironment#startPiratesEvent()
	 * @see GameEnvironment#shipwreckedSailors()
	 */
	public void randomEvent()
	{
		int randInt = random.nextInt(5);
		switch (randInt)
		{
		case 0:
			gameController.startPiratesEvent();
			break;
		case 1:
			gameController.shipwreckedSailors();
			break;
		case 2:
			gameController.badWeather();
			break;
		default:
			break;	
		}
	}
	
	/**
	 * Simulates a dice roll returning a random integer 1 - 6 for the pirates and for the player.
	 * The {@link GameEnvironment#getPiratesResults()} added the Ship cannon upgrade if applicable.
	 * 
	 * @return diceRoll - the random dice roll integer value 1 - 6 inclusive.
	 */
	public int diceRoll()
	{
		return random.nextInt(6) + 1;
	}
	
	/**
	 * Determines a random integer value from 100 to 600 for use in the pirates and 
	 * shipwrecked sailors random events. In the pirates random event the random money 
	 * determines the cost to avoid walking the plank (if you lose the dice roll). In 
	 * the shipwrecked sailors random the random money is the reward money.
	 * 
	 * @see GameEnvironment#getPiratesResults()
	 * @see GameEnvironment#shipwreckedSailors()
	 * @return randomMoney - integer value from 100 - 600 representing monetary value.
	 */
	public int randomMoney()
	{
		return random.nextInt(500) + 100;
	}

}
