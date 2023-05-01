package game.primaryClasses;

import game.controllerClasses.GameEnvironment;

/**
 * Item class for the Island Trader Game.
 * 
 * This class is used to create the items bought and 
 * sold in the island trader game.  
 * 
 * @see GameEnvironment#createItems()
 * @author Sam Clark
 * @author Wil Johnston
 */
public class Item 
{
	private String name;
	private int weight;
	private int defaultPrice;
	
	/**
	 * Constructs an instance of Item class
	 * 
	 * @param name - the name of the item
	 * @param weight - the weight of the item
	 * @param defaultPrice - the default price of the item
	 */
	public Item(String name, int weight, int defaultPrice)
	{
		this.name = name;
		this.weight = weight;
		this.defaultPrice = defaultPrice;
	}
	
	/**
	 * Gets the name attribute of this Item instance
	 * 
	 * @return the name of this Item
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Gets and returns the weight attribute of this Item instance
	 * 
	 * @return the weight of the Item
	 */
	public int getWeight()
	{
		return weight;
	}
	
	
	/**
	 * Gets the default price attribute of an Item instance
	 * 
	 * @return the default price of the Item
	 */
	public int getDefaultPrice()
	{
		return defaultPrice;
	}
	
	/**
	 * Creates a string representation of an instance of the class Item.
	 * 
	 * The string representation of class Item is just the item's name.
	 * 
	 * @return the name of the Item
	 */
	public String toString()
	{
		return name;
	}
	
}
