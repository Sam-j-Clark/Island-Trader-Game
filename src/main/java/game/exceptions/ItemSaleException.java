package game.exceptions;

/**
 * This Exception is used in the island trader game is thrown when 
 * an issue occurs while buying or selling items at a store.
 * <br>
 *  This can occur for the following reasons... 
 * <br>
 * (1) The player tries to buy an item when the store has no stock. <br>
 * (2) The player tries to sell an item that they don't own any of. <br>
 * (3) The player tries to buy an item but does not have the capacity on their ship. <br>
 * (4) The player tries to buy an item but can not afford it.
 * 
 * @author Sam Clark
 * @author Wil Johnston
 */
public class ItemSaleException extends Exception 
{
	/**
	 * The Serial Version for ItemSaleException
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs an ItemSaleException using the super class Exception.
	 * 
	 * @param errorText - To tell the user why the sale was illegal.
	 * @see   Exception
	 */
	public ItemSaleException(String errorText)
	{
		super(errorText);
	}
}
