package game.primaryClasses;

import java.util.Hashtable;
import game.controllerClasses.GameEnvironment;

/**
 * The Island Class for use in the Island Trader Game. This class is used for each of 
 * the 5 islands in the game. Each Island has a name and store, as well as a dictionary 
 * of distances to the other islands and a types of item that is supplies and demands.
 *  
 * @see GameEnvironment#createIslands(java.util.ArrayList)
 * @author Sam Clark
 * @author Wil Johnston
 */
public class Island 
{
	private String name;
	private Store store;
	private Hashtable<Island, Integer> distances;
	private String supply;
	private String demand;
	
	/**
	 * Creates an instance of this Island Class for use in the island trader game.
	 * 
	 * @param name - The name of the Island
	 * @param store - The Store on the island of class {@link Store}
	 * @param supply - A string representing the type of goods the Island supplies cheaply
	 * @param demand - A string representing the type of items the Island demands at higher prices.
	 */
	public Island(String name, Store store, String supply, String demand)
	{
		this.name =  name;
		this.store = store;
		this.supply = supply;
		this.demand = demand;
	}
	
	/**
	 * Sets the distances to every other island in the island trader game. Each distance is in 
	 * kilometers
	 * 
	 * @param distances - A Hashtable mapping Islands to integer values representing that island's
	 * distance away.
	 */
	public void setDistances(Hashtable<Island, Integer> distances)
	{
		this.distances = distances;
	}
	
	/**
	 * Gets the name of this Island.
	 * 
	 * @return name - The name of this Island.
	 */
	public String getName()
	{
		 return name;
	}
	
	/**
	 * Gets the Store on this island.
	 * 
	 * @return store - The {@link Store} on this island.
	 */
	public Store getStore()
	{
		return store;
	}
	
	/**
	 * Gets the Hashtable mapping to the distances to each other island in Game.
	 * 
	 * @return distances - A Hashtable mapping Islands to integer values representing each island's
	 * distance away.
	 */
	public Hashtable<Island, Integer> getDistances()
	{
		return distances;
	}
	
	/**
	 * Gets the String representing the type of item this Island Supplies Cheaply
	 * 
	 * @return supply - A string representing the type of item this island supplies cheaply.
	 */
	public String getSupply()
	{
		return supply;
	}
	
	/**
	 * Gets the String representing the type of item this Island Demands at a higher price
	 * 
	 * @return supply - A string representing the type of item this island demands at a higher price.
	 */
	public String getDemand()
	{
		return demand;
	}

}
