package game.primaryClasses;

import java.util.Hashtable;

import game.controllerClasses.GameEnvironment;
import game.controllerClasses.GameState;

/**
 * Store Class for use in the Island Trader Game.
 * <br>
 *  There is one instance of this Store for each of the 5 {@link Island}s in the game.
 * Each Store has a name, a Hashtable mapping each {@link Item} to it's (integer value)
 * price at the Store, a Hashtable mapping each item to an integer value stock and an
 * {@link Upgrade} that the store sells.
 * 
 * @see Upgrade
 * @see Island
 * @see GameEnvironment#createStores()
 * 
 * @author Sam Clark
 * @author Wil Johnston
 */
public class Store 
{
	private String storeName;
	private Hashtable<Item, Integer> prices;
	private Hashtable<Item, Integer> stock;
	private Hashtable<Item, Integer> fullStock;
	private Upgrade upgrade;
	
	/**
	 * Creates an instance of this Store Class, for use in the Island Trader Game.
	 * 
	 * @param storeName - The name of this store.
	 * @param prices - A Hashtable mapping each Item in the game to it's price at this store.
	 * @param stock - A Hashtable mapping each Item in the game to it's stock at this store.
	 * @param upgrade - The instance of Upgrade sold at this store.
	 */
	public Store(String storeName, Hashtable<Item, Integer> prices, Hashtable<Item, Integer> stock, Upgrade upgrade)
	{
		this.storeName = storeName;
		this.prices = prices;
		this.stock = stock;
		fullStock = new Hashtable<Item, Integer>(stock);
		this.upgrade = upgrade;
	}
	
	/**
	 * Gets the name of this Store.
	 * 
	 * @return name - the name of this store of type String.
	 */
	public String getName()
	{
		return storeName;
	}
	
	/**
	 * Gets the Hashtable, stock, which maps each Item in the Island Trader game to 
	 * it's stock at this Store.
	 * 
	 * @return stock - The Hashtable mapping each item to it's stock at this store.
	 */
	public Hashtable<Item, Integer> getStock()
	{
		return stock;
	}
	
	/**
	 * Gets the Hashtable, prices, which maps each Item in the Island Trader game to
	 * it's price at this store. Prices are in dollars.
	 * 
	 * @return prices - A Hashtable mapping items to their price at this store ($).
	 */
	public Hashtable<Item, Integer> getPrices()
	{
		return prices;
	}
	
	/**
	 * Gets the instance of {@link Upgrade} which is sold at this Store.
	 * 
	 * @return upgrade - The instance of the Upgrade Class which is sold at this Store.
	 */
	public Upgrade getUpgrade()
	{
		return upgrade;
	}
	
	/**
	 * Takes a parameter {@link Item} and adds one to this class' stock attribute of that Item.
	 * <br>
	 * This method is called during the Island trader game from {@link GameEnvironment#sellItem(Item)}
	 * 
	 * @see GameEnvironment#sellItem(Item)
	 * @param item - The item that's stock is being incremented.
	 */
	public void incrementStock(Item item)
	{
		{
			int updatedStock = 	stock.get(item);
			updatedStock++;
			stock.put(item, updatedStock);	
		}
	}
	
	/**
	 * Takes a parameter {@link Item} and removes one to this class' stock attribute of that Item.
	 * <br>
	 * This method is called during the Island trader game from {@link GameEnvironment#buyItem(Item)}
	 * Note that this method is never called if the stock value of the Item is zero. This is dealt with
	 * in the {@link GameEnvironment#buyItem(Item)} method. 
	 * 
	 * @see GameEnvironment#buyItem(Item)
	 * @see GameState#removeItem(Item)
	 * @param item - The item that's stock is being decremented.
	 */
	public void decrementStock(Item item)
	{
		{
			int updatedStock = 	stock.get(item);
			updatedStock--;
			stock.put(item, updatedStock);	
		}
	}
	
	/**
	 * Restocks the store to its original full stock.
	 */
	public void restock()
	{
		stock = new Hashtable<Item, Integer>(fullStock);
	}
}
