package game.shipClasses;

import java.util.ArrayList;

import game.controllerClasses.GameEnvironment;
import game.controllerClasses.GameState;
import game.primaryClasses.Item;
import game.primaryClasses.Upgrade;
/**
 * Ship Class for the island trader game.
 * 
 * This class creates and monitors the ship used in the Island Trader Game.
 * There are 4 subclasses of Ship for each of the 4 choices of ship the player
 * has. These include {@link Carol}, {@link FlyingScotsman}, {@link JollyRodrick}
 * and {@link WhitePearl}. 
 * 
 * @author Sam Clark
 * @author Wil Johnston
 */
public class Ship 
{
	private String name;
	private int crewSize;
	private int maxWeight;
	private int maxHealth;
	
	private int costPerDay;
	private int kmPerDay;
	
	private int currentHealth;
	private int currentWeight;
	
	private ArrayList<Upgrade> upgrades;
	
	private int repairMultiplier;
	private boolean hasCannons;
	
	
	/**
	 * Constructs an instance of this Ship Class for use in the island trader Game
	 * 
	 * @param name - The name of the ship.
	 * @param crewSize - The number of crew members the ship has.
	 * @param maxWeight - The maximum Capacity of the ship in kg's
	 * @param maxHealth - The maximum health of the ship, HP (hit points)
	 */
	public Ship(String name, int crewSize, int maxWeight, int maxHealth)
	{
		this.name = name;
		this.crewSize = crewSize;
		this.maxWeight = maxWeight;
		this.maxHealth = maxHealth;
		this.currentHealth = this.maxHealth;
		this.repairMultiplier = 4;
		this.costPerDay = (this.crewSize * this.maxHealth / 10);
		this.kmPerDay = (this.crewSize * 50 / this.maxWeight);
		currentWeight = 0;
		upgrades = new ArrayList<Upgrade>();
	}
	
	/**
	 * Creates a string representation of the Ship class.
	 * String representations are simply the ship's name.
	 * 
	 * @return name - The ship's name.
	 */
	public String toString()
	{
		return name;
	}
	
	/**
	 * Adds an upgrade to the ship. Upgrades are instances of the class Upgrade.
	 * 
	 * @see Upgrade
	 * @param upgrade - the upgrade to add to the ship 
	 * @throws NullPointerException if the upgrade doesn't exist.
	 */
	public void upgradeShip(Upgrade upgrade)
	{
		switch (upgrade.getName())
		{
		case "Bigger Sails":
			kmPerDay *= 1.75;
			upgrades.add(upgrade);
			break;
		case "Feast Table":
			costPerDay /= 2;
			upgrades.add(upgrade);
			break;
		case "Forged Tools":
			repairMultiplier = 2;
			upgrades.add(upgrade);
			break;
		case "Cannons":
			hasCannons = true;
			upgrades.add(upgrade);
			break;
		case "Storage Boxes":
			maxWeight *= 1.5;
			upgrades.add(upgrade);
			break;
		default:
			throw new NullPointerException("This Upgrade Doesnt Exist");
		}
	}
	
	/**
	 * Creates and returns a string containing the ship's specifications.
	 * Specifications include: The ship's name, the ship's crew size, the ship's max weight,
	 * the ship's max health and the cost per day to sail the ship.
	 * 
	 * @return specifications - a list of specifications of the ship.
	 */
	public String getShipSpecs()
	{
		String specifications;
		specifications = "Name: " + name
				+ "\nCrew Size   : "+ crewSize 
				+ "\nMax Weight  : " + maxWeight 
				+ "\nMax Health  : " + maxHealth 
				+ "\nCost per Day : " + costPerDay;
		return specifications;
	}
	
	/**
	 * Gets the name of this Ship.
	 * 
	 * @return name - the name of this ship.
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Gets the number of crew members of this ship.
	 * 
	 * @return crewSize - number of crew members
	 */
	public int getCrewSize()
	{
		return crewSize;
	}
	
	/**
	 * Gets the maxWeight of this Ship in kg.
	 * 
	 * @return maxWeight - the maximum weight of the ships capacity (kg)
	 */
	public int getMaxWeight()
	{
		return maxWeight;
	}
	
	/**
	 * gets the maxHealth of this ship (HP).
	 * 
	 * @return maxHealth - the maximum HP of the ship.
	 */
	public int getMaxHealth()
	{
		return maxHealth;
	}
	
	/**
	 * Gets the cost per day to sail this ship.
	 * 
	 * @return costPerDay - the cost to per day to sail the ship in dollars.
	 */
	public int getCostPerDay()
	{
		return costPerDay;
	}
	
	/**
	 * Gets the number of Kilometers this ship can travel per day.
	 * 
	 * @return kmPerDay - The number of km this ship can travel in a day.
	 */
	public int getKmPerDay()
	{
		return kmPerDay;
	}
	
	/**
	 * Gets the current Health of this ship.
	 * 
	 * @return currentHealth - the current HP of the ship.
	 */
	public int getCurrentHealth()
	{
		return currentHealth;
	}
	
	/**
	 * Gets the current weight of the ships cargo in kg.
	 * 
	 * @return currentWeight - the current weight of the ships cargo in kg.
	 */
	public int getCurrentWeight()
	{
		return currentWeight;
	}
	
	/**
	 * Reduces this ship by the integer parameter damage (Hp).
	 * This method is not called if the damage dealt is higher than the ships currentHealth.
	 * 
	 * @see GameEnvironment#badWeather()
	 * @param damage - the amount of damage taken in (Hp)
	 */
	public void damageTaken(int damage)
	{
		currentHealth -= damage;
	}
	
	/**
	 * Returns the cost to repair this ship to full hp.
	 * The cost to repair the ship is 4 x health lost if the ship doesn't have the forgedTools
	 * upgrade, or 2 x the health lost if the ship does have the forgedTools Upgrade.
	 * 
	 * @return costToRepair - The cost to repair this ship to full Hp in dollars.
	 */
	public int costToRepair()
	{
		return repairMultiplier * (maxHealth - currentHealth);
	}
	
	/**
	 * Repairs the ship back to full Hp by setting currentHealth to maxHealth.
	 * 
	 * @see GameEnvironment#repairShip()
	 */
	public void repair()
	{
		currentHealth = maxHealth;
	}
	
	/**
	 * Gets the boolean value of hasCannons. 
	 * Used in the pirates random event, adding +2 to dice rolls.
	 * 
	 * @see GameEnvironment#getPiratesResults()
	 * @return hasCannons - boolean value whether on not this ship has the cannons Upgrade.
	 */
	public boolean getHasCannons()
	{
		return hasCannons;
	}
	
	/**
	 * Gets the ArrayList containing the upgrades currently added to this ship.
	 * 
	 * @see Upgrade
	 * @return upgrades - An Array list of upgrades added to this ship.
	 */
	public ArrayList<Upgrade> getUpgrades()
	{
		return upgrades;
	}

	/**
	 * Increases the currentWeight attribute of this ship by the parameter weight (kg).
	 * This method is called when items are added to the players inventory of the island 
	 * trader game. Note that this method is not called if adding the weight would exceed
	 * this ship's max weight, as controlled by {@link GameEnvironment#buyItem(Item)}.
	 * 
	 * @see GameEnvironment#buyItem(Item)
	 * @see GameState#addItem(Item)
	 * @param weight - the weight added to this ship's currentWeight
	 */
	public void increaseWeight(int weight) 
	{
		currentWeight += weight;
	}

	/**
	 * Decreases the currentWeight attribute of this ship by the parameter weight (kg).
	 * This method is called when items are removed from the players inventory of the island
	 * trader game. Because players inventories cannot have negative weight this method never
	 * sets currentWeight to be a negative number. This is controlled by 
	 * {@link GameEnvironment#sellItem(Item)}.
	 * 
	 * @param weight - the weight this ships currentWeight is reduced by in kg.
	 */
	public void decreaseWeight(int weight) 
	{
		currentWeight -= weight;		
	}
	
	/**
	 * Gets the capacity remaining (kg) on this ship.
	 * <br>
	 * The remaining capacity of this ship is calculated from the maxWeight - currentWeight.
	 * 
	 * @return remainingCapacity - the amount of capacity available on this ship in kg.
	 */
	public int getRemainingCapacity() 
	{
		return maxWeight - currentWeight;
	}
}
