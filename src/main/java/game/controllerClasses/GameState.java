package game.controllerClasses;

import java.util.ArrayList;
import java.util.Hashtable;

import game.primaryClasses.Island;
import game.primaryClasses.Item;
import game.primaryClasses.Upgrade;
import game.shipClasses.Ship;

/**
 * The GameState class for the Island Trader Game.
 * <br>
 * This class holds and controls the information about the current game status.
 * Information includes: <br>
 * - the players name <br>
 * - the chosen game length (days) <br>
 * - the current game day <br>
 * - the players {@link Ship} <br>
 * - the players money <br>
 * - the players inventory <br>
 * - the players current {@link Island}
 * 
 * @see GameEnvironment
 * @see GuiManager
 * 
 * @author Sam Clark
 * @author Wil Johnston
 */
public class GameState 
{
	private String playerName;
	private int gameLength;
	private int currentDay;
	private Ship ship;
	private int money;
	private Hashtable<Item, Integer> inventory;
	private Island currentIsland;
	
	private ArrayList<Island> islands;
	private GameEnvironment gameController;

	/**
	 * Creates an instance of GameState for the island trader game.
	 * 
	 * @param gameController - an instance of GameEnvironment that controls the 
	 * island trader game
	 */
	public GameState(GameEnvironment gameController)
	{
		money = 1000;
		this.gameController = gameController;
		currentDay = 0;
		islands = this.gameController.formIslands();
		inventory = this.gameController.formInventory();
		currentIsland = islands.get(0);
	}
	
	/**
	 * Sets the initial game options the player has chosen.
	 * <br> 
	 * Called from the {@link GuiManager} class once the player has successfully chosen
	 * a valid name, ship and game length for their game. <br>
	 * Note: this is not called unless the name is valid as checked by
	 * {@link GameEnvironment#checkValidName(String)}
	 *  
	 * @param name - The valid name the player has chosen for their character.
	 * @param gameLength - The length of game the player has chosen 20 - 50.
	 * @param ship - The {@link Ship} the player has chosen to captain.
	 */
	public void setPlayerInfo(String name, int gameLength, Ship ship)
	{
		playerName = name;
		this.gameLength = gameLength;
		this.ship = ship;
	}
	
	/**
	 * Gets the name the player has chosen.
	 * 
	 * @return playerName - the name of this player.
	 */
	public String getPlayerName()
	{
		return playerName;
	}
	
	/**
	 * Gets the chosen game length in days.
	 * 
	 * @return gameLength - the chosen length of this game.
	 */
	public int getGameLength()
	{
		return gameLength;
	}
	
	/**
	 * Gets the current in game day.
	 * 
	 * @return currentDay - the current in game day.
	 */
	public int getCurrentDay()
	{
		return currentDay;
	}
	
	/**
	 * Gets the number of days remaining in this Island Trader Game.
	 * <br>
	 * days remaining = gameLength - currentDay
	 * 
	 * @return Days remaining - the number of days left in this game.
	 */
	public int getDaysRemaining()
	{
		return gameLength - currentDay;
	}
	
	/**
	 * Gets the ship the player is captaining in the Game.
	 * 
	 * @see Ship
	 * @return ship - the ship the player is captaining.
	 */
	public Ship getShip()
	{
		return ship;
	}

	/**
	 * Gets the amount of money ($) the player has in this Island Trader Game.
	 * 
	 * @return money - the amount of money the player currently has.
	 */
	public int getMoney()
	{
		return money;
	}
	
	/**
	 * Pays money, taking it off of the players current money attribute.
	 * <br>
	 * Takes the paidMoney parameter and subtracts it from the players money. <br>
	 * Note: this method is never called when a players money is less than the paidMoney
	 * parameter. <br>
	 * This is dealt with in the {@link GameEnvironment} methods that call it, including: <br>
	 * - {@link GameEnvironment#sail()} <br>
	 * - {@link GameEnvironment#repairShip()} <br>
	 * - {@link GameEnvironment#buyItem(Item)} <br>
	 * - {@link GameEnvironment#upgradeShip(Upgrade)} <br>
	 * - {@link GameEnvironment#getPiratesResults()}
	 * 
	 * @param paidMoney - the int value of money ($) to be taken from this games money attribute
	 */
	public void payMoney(int paidMoney)
	{
		this.money -= paidMoney;
	}
	
	/**
	 * Receives money, adding it to the players current money attribute.
	 * <br>
	 * Adds the receivedMoney parameter and adds it from the players money. <br>
	 * 
	 * @param receivedMoney - The amount of money added to this games money attribute.
	 */
	public void receiveMoney(int receivedMoney)
	{
		this.money += receivedMoney;
	}
	
	/**
	 * Increments the currentDay attribute by the parameter daysPassed.
	 * 
	 * @param daysPassed - The number of days to increment the currentDay by.
	 */
	public void incrementDay(int daysPassed)
	{
		currentDay += daysPassed;
	}
	
	/**
	 * Gets the currentIsland attribute of this GameState.
	 * 
	 * @return currentIsland - the {@link Island} the player is currently on.
	 */
	public Island getCurrentIsland()
	{
		return currentIsland;
	}

	/**
	 * Gets this games inventory attribute that maps items to the integer number owned.
	 * 
	 * @return inventory - this GameStates inventorya attribute that maps items to the quantity owned.
	 */
	public Hashtable<Item, Integer> getInventory()
	{
		return inventory;
	}
	
	/**
	 * Sets the currentIsland attribute of this GameState to the parameter island.
	 * 
	 * @see GameEnvironment#sail()
	 * @param island - the {@link Island} being set as the currentIsland.
	 */
	public void setCurrentIsland(Island island)
	{
		
		this.currentIsland = island;
	}

	/**
	 * Gets the all the arraylist containing all the islands in the island trader game.
	 * 
	 * @return islands - the arraylist containing all the islands in the game.
	 */
	public ArrayList<Island> getIslandsList()
	{
		return islands;
	}
	
	/**
	 * Calculates the players final score value.
	 * <br>
	 * The score is calculated simply by multiplying the players final money by the days the survived
	 * @return score - the integer value representing the players final game score.
	 */
	public int calculateScore()
	{
		return money * currentDay;
	}
	
	/**
	 * Creates and returns a string containing the game results.
	 * <br>
	 * The results are used in the {@link GuiManager#changeToResultsScreen()} to display the 
	 * final results to the player once the game is over. <br>
	 * Results of the game include the players name, the number of days survived, the money they 
	 * ended with and their final score.
	 * 
	 * @return results - A string containing the results of the game 
	 */
	public String getGameResults()
	{
		String results;
		results = "Name: " + playerName +
				  "\nDays Survived: " + currentDay +
				  "\nMoney Earned: " + money +
				  "\nScore: " + calculateScore();
		return results;
	}

	/**
	 * Checks if the game has reached it's total length.
	 * <br>
	 * calls {@link #getDaysRemaining()} returns false if it is still a positive number,
	 * indicating the game has not reached its maximum length. If the days remaining is 
	 * zero or negative this method returns true and sets the currentDay to the maximum
	 * game length.
	 * @return boolean - true if the gameLength has been reach, false otherwise.
	 */
	public boolean gameLengthReached() {
		if (this.getDaysRemaining() > 0)
			return false;
		else
			currentDay = gameLength;
			return true;
	}
	
	/**
	 * Adds an item to the players inventory.
	 * <br>
	 * Takes a parameter item and adds one to the value associated with that item in the 
	 * inventory Hashtable. This method also increases the ships currentWeight by the weight
	 * of the item added. <br>
	 * Note: the item is not added to the players inventory if the {@link Ship} does not have
	 * the remaining capacity for it. This is dealt with in the {@link GameEnvironment#buyItem(Item)}
	 * method.
	 * 
	 * @param item - the {@link Item} being added to the players inventory.
	 */
	public void addItem(Item item) 
	{
		int itemQuantity = inventory.get(item);
		itemQuantity++;
		inventory.put(item, itemQuantity);
		ship.increaseWeight(item.getWeight());
	}
	
	/**
	 * Removes an {@link Item} from the players inventory.
	 * <br>
	 * Takes a parameter item and removes one from the value associated with that item in the 
	 * inventory Hashtable. This method also decreases the ships currentWeight by the weight
	 * of the item removed. <br>
	 * Note: the item is not removed from the players inventory if the player didn't previously
	 * own at least one of the item. This is dealt with in the {@link GameEnvironment#sellItem(Item)}
	 * method.
	 * 
	 * @param item - the item being removed from the players inventory.
	 */
	public void removeItem(Item item)
	{
		int itemQuantity = inventory.get(item);
		itemQuantity--;
		inventory.put(item, itemQuantity);
		ship.decreaseWeight(item.getWeight());
	}
	
}
