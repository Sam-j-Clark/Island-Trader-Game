package game.controllerClasses;

import java.util.ArrayList;
import java.util.Hashtable;

import game.exceptions.IllegalNameException;
import game.exceptions.ItemSaleException;
import game.exceptions.RepairIssueException;
import game.exceptions.SailIssueException;
import game.exceptions.ShipUpgradeException;
import game.exceptions.WalkThePlankException;
import game.primaryClasses.Island;
import game.primaryClasses.Item;
import game.primaryClasses.Store;
import game.primaryClasses.Upgrade;
import game.shipClasses.Ship;

/**
 * This is the main class that controls activity in the island trader game.
 * <br>
 * Game Environment creates the initial state of the game by initialising the
 * games {@link Island}s, {@link Store}s and {@link Item}s. This class along
 * with {@link GameState} determines the games GUI Display. 
 *
 * @see GuiManager
 * @see GameState
 * 
 * @author Sam Clark
 * @author Wil Johnston
 */
public class GameEnvironment 
{
	private GameState gameModel;
	private GuiManager gui;
	
	private Island viewedIsland;
	private ArrayList<Item> items;
	private RandomEvent random = new RandomEvent(this);
	
	private Item wood;
	private Item charcoal;
	private Item iron;
	private Item steel;
	private Item wool;
	private Item silk;
	private Item beef;
	private Item lobster;
	private Item gold;
	private Item diamond;
	
	private Island axemansBay;
	private Island moltenMonetain;
	private Island cosyCove;
	private Island richesResort;
	private Island dinersDock;
	
	/**
	 * Creates an instance of this GameEnvironment class.
	 * 
	 * @param gui - The {@link GuiManager} that this GameEnvironment is running.
	 */
	public GameEnvironment(GuiManager gui) {
		this.gui = gui;
	}

	/**
	 * Sets the initial state of the game in this GameEnvironment after the player
	 * selects their name, {@link Ship} and number of days they want to play.
	 * 
	 * @see GameState
	 * @param gameState - the instance of GameState being used in the current island
	 * trader game.
	 */
	public void setGameState(GameState gameState)
	{
		this.gameModel = gameState;
	}
	
	/**
	 * Gets the island that the player is viewing in the information panel of the island trader games guiManager.
	 * <br>
	 * This viewed island allows the player to travel to
	 * the Island if they wish (if it is valid to sail to).
	 * 
	 * @return viewedIsland - The island displayed or to display in the {@link GuiManager}
	 * map info panel.
	 */
	public Island getViewedIsland()
	{
		return viewedIsland;
	}
	
	/**
	 * Sets the viewed island for the guiManager class. 
	 * <br>
	 * This method is called when the player selects an island on the map of the island
	 * trader game. When this method is called the guiManager updates the info screen to
	 * display the Islands info on the map info panel.
	 * 
	 * @param viewedIsland - the island the player wishes to view in the map info panel.
	 */
	public void setViewedIsland(Island viewedIsland)
	{
		this.viewedIsland = viewedIsland;
	}
	
	/**
	 * This method checks the name of a player in the island trader game.
	 * <br>
	 * It checks that the name is valid under the requirements:
	 * <br>
	 * (1) The name must be between 3 - 15 characters long inclusive. <br>
	 * (2) The name must contains only characters that are a-z, A-Z or " ". <br>
	 * (3) The name cannot contain consecutive blank space characters.
	 * 
	 * @param name - the String name the player is trying to set for the game.
	 * @return boolean - true if the attempted name is valid
	 * @throws IllegalNameException if the name is invalid. This exception is built to tell
	 * the player why their choose was illegal.
	 */
	public boolean checkValidName(String name) throws IllegalNameException
	{
		// Test if String length is valid
		if (name.length() < 3 || name.length() > 15)
		{
			throw(new IllegalNameException("Players Name must be 3 to 15 characters"));
		}
		
		// Test for illegal characters
		char[] chars = name.toCharArray();
		for (char c : chars)
		{
			//  c =   " "          A-Z                    a-z
			if (!(c == 32 || (c < 91 && c > 64) || (c < 123 && c > 96)))
			{
				throw(new IllegalNameException("Players name can only use letter characters and spaces"));
			}
		}
		if (name.contains("  "))
		{
			throw(new IllegalNameException("Players name cannot include consecutive spaces"));
		}
		return true;
	}

	/**
	 * This method creates and returns the Arraylist of islands used in the island trader game. 
	 * <br>
	 * This method is called by the GameState class in it's constructor.
	 * It uses a bottom up approach, first building the games Items, then
	 * building the games stores before building the games islands.
	 * 
	 * @see #createItems()
	 * @see #createStores()
	 * @see #createIslands(ArrayList)
	 * @return islands - the arraylist of islands used in the island trader game.
	 */
	public ArrayList<Island> formIslands() 
	{
		createItems();
		ArrayList<Store> stores = createStores();
		ArrayList<Island> islands = createIslands(stores);
		islands = setIslandDistances(islands);
		
		return islands;
	}

	/**
	 * This is a helper method for the {@link #formIslands()} method.
	 * <br>
	 * It creates the items used in the island trader game. Each {@link Item} has it's
	 * attributes defined in this method. The items created are also added to an Arraylist
	 * items, which is used for creating the players initial inventory in the
	 * {@link #formInventory()} method and for creating the component hashtables
	 * in the guiManager class.
	 */
	public void createItems() {
		items = new ArrayList<Item>();
		
		wood = new Item("Wood", 3, 30);
		charcoal = new Item("Charcoal", 5, 50);
		iron = new Item("Iron", 10 , 80);
		steel = new Item("Steel", 15, 200);
		wool = new Item("Wool", 2, 50);
		silk = new Item("Silk", 1, 100);
		beef = new Item("Beef", 4, 20);
		lobster = new Item("Lobster", 5, 70);
		gold = new Item("Gold", 25, 300);
		diamond = new Item("Diamond", 30, 1000);
		
		items.add(wood);
		items.add(charcoal);
		items.add(iron);
		items.add(steel);
		items.add(wool);
		items.add(silk);
		items.add(beef);
		items.add(lobster);
		items.add(gold);
		items.add(diamond);
	}	
	
	/**
	 * Gets the arraylist of {@link Item}s bought and sold in the island trader game.
	 * 
	 * @return items - the arraylist of items used in the island trader game.
	 */
	public ArrayList<Item> getItemsList()
	{
		return items;
	}
	
	/**
	 * This is a helper method for the {@link #formIslands()} method. 
	 * <br>
	 * It creates the stores used in the island trader game. Each {@link Store} has
	 * it's attributes defined in this method. 
	 * 
	 * @return stores - An arraylist of {@link Store}s used to create the games islands.
	 */
	public ArrayList<Store> createStores() 
	{
		Hashtable<Item, Integer> defaultStorePrices = new Hashtable<Item, Integer>();
		Hashtable<Item, Integer> defaultStoreStock = new Hashtable<Item, Integer>();
		for (Item item : items)
		{
			defaultStorePrices.put(item, item.getDefaultPrice());
			defaultStoreStock.put(item, 0);
		}
		
		String store1name = "The Lumber Yard";
		Hashtable<Item, Integer> store1prices = new Hashtable<Item, Integer>(defaultStorePrices);
		Hashtable<Item, Integer> store1stock = new Hashtable<Item, Integer>(defaultStoreStock);
		store1prices.put(wood, 10);
		store1prices.put(charcoal, 15);
		store1prices.put(beef, 50);
		store1prices.put(lobster, 300);
		store1stock.put(wood, 10);
		store1stock.put(charcoal, 8);
		String upgrade1description = "Storage Boxes added to your ship increases the maximum capacity of the ship by 50%";
		Upgrade store1upgrade = new Upgrade("Storage Boxes", 800, 4, upgrade1description, "/StorageBoxes.png");
		Store store1 = new Store(store1name, store1prices, store1stock, store1upgrade);
		
		String store2name = "John Smith's";
		Hashtable<Item, Integer> store2prices = new Hashtable<Item, Integer>(defaultStorePrices);
		Hashtable<Item, Integer> store2stock = new Hashtable<Item, Integer>(defaultStoreStock);
		store2prices.put(iron, 30);
		store2prices.put(steel, 60);
		store2prices.put(wood, 100);
		store2prices.put(charcoal, 150);
		store2stock.put(iron, 10);
		store2stock.put(steel, 8);
		String upgrade2description = "Sailers are equipped with better tools. The cost to repair damage is halved";
		Upgrade store2upgrade = new Upgrade("Forged Tools", 400, 1, upgrade2description, "/ForgedTools.png");
		Store store2 = new Store(store2name, store2prices, store2stock, store2upgrade);
		
		String store3name = "Sue's Sewing Supplies";
		Hashtable<Item, Integer> store3prices = new Hashtable<Item, Integer>(defaultStorePrices);
		Hashtable<Item, Integer> store3stock = new Hashtable<Item, Integer>(defaultStoreStock);
		store3prices.put(wool, 20);
		store3prices.put(silk, 40);
		store3prices.put(iron, 200);
		store3prices.put(steel, 500);
		store3stock.put(wool, 10);
		store3stock.put(silk, 8);
		String upgrade3description = "Bigger sails are mounted to the ship. The ship's speed is increased by 75%";
		Upgrade store3upgrade = new Upgrade("Bigger Sails", 800, 5, upgrade3description, "/BiggerSails.png");
		Store store3 = new Store(store3name, store3prices, store3stock, store3upgrade);
		
		String store4name = "Jimmy's Jeweller";
		Hashtable<Item, Integer> store4prices = new Hashtable<Item, Integer>(defaultStorePrices);
		Hashtable<Item, Integer> store4stock = new Hashtable<Item, Integer>(defaultStoreStock);
		store4prices.put(gold, 100);
		store4prices.put(diamond, 300);
		store4prices.put(wool, 150);
		store4prices.put(silk, 300);
		store4stock.put(gold, 10);
		store4stock.put(diamond, 8);
		String upgrade4description = "Cannons are mounted to the ship helping defend you from pirates. Provides +2 on defensive dice rolls";
		Upgrade store4upgrade = new Upgrade("Cannons", 700, 2, upgrade4description, "/Cannons.png");
		Store store4 = new Store(store4name, store4prices, store4stock, store4upgrade);
		
		String store5name = "Kungpo's Kitchen";
		Hashtable<Item, Integer> store5prices =new Hashtable<Item, Integer>(defaultStorePrices);
		Hashtable<Item, Integer> store5stock = new Hashtable<Item, Integer>(defaultStoreStock);
		store5prices.put(beef, 10);
		store5prices.put(lobster, 30);
		store5prices.put(gold, 700);
		store5prices.put(diamond, 2500);
		store5stock.put(beef, 10);
		store5stock.put(lobster, 8);
		String upgrade5description = "Your sailors are more satisfied by their dinner. Decreases sail cost to 50%";
		Upgrade store5upgrade = new Upgrade("Feast Table", 600, 3, upgrade5description, "/FeastTable.png");
		Store store5 = new Store(store5name, store5prices, store5stock, store5upgrade);
		
		ArrayList<Store> stores = new ArrayList<Store>();
		stores.add(store1);
		stores.add(store2);
		stores.add(store3);
		stores.add(store4);
		stores.add(store5);
		return stores;
	}
	
	/**
	 * This is a helper method for the {@link #formIslands()} method.
	 * <br>
	 * createIslands sets the initial attributes of each Island in the game using the
	 * {@link Island#Island(String, Store, String, String)} constructor. This method does not
	 * set the distances between each of the islands. Each {@link Island} has it's attributes 
	 * (besides distance) defined in this method. 
	 * 
	 * @param stores - The arraylist of stores created by {@link #createStores()} so that each
	 * island has a store added to it.
	 * @return islands - The arraylist of islands used in the island trader game, however these islands
	 * do not have their distances set yet.
	 */
	public ArrayList<Island> createIslands(ArrayList<Store> stores) 
	{
		String island1name = "Axeman's Bay";
		Store island1store = stores.get(0);
		String island1supplies = "Lumber";
		String island1demands = "Food";
		axemansBay = new Island(island1name, island1store, island1supplies, island1demands);
		
		String island2name = "Molten Mountain";
		Store island2store = stores.get(1);
		String island2supplies = "Metal";
		String island2demands = "Lumber";
		moltenMonetain = new Island(island2name, island2store, island2supplies, island2demands);
		
		String island3name = "Cosy Cove";
		Store island3store = stores.get(2);
		String island3supplies = "Fabric";
		String island3demands = "Metal";
		cosyCove = new Island(island3name, island3store, island3supplies, island3demands);
		
		String island4name = "Riches Resort";
		Store island4store = stores.get(3);
		String island4supplies = "Jewels";
		String island4demands = "Fabric";
		richesResort = new Island(island4name, island4store, island4supplies, island4demands);
		
		String island5name = "Diner's Dock";
		Store island5store = stores.get(4);
		String island5supplies = "Food";
		String island5demands = "Jewels";
		dinersDock = new Island(island5name, island5store, island5supplies, island5demands);
		
		ArrayList<Island> islands = new ArrayList<Island>();
		islands.add(axemansBay);
		islands.add(moltenMonetain);
		islands.add(cosyCove);
		islands.add(richesResort);
		islands.add(dinersDock);
		
		return islands;
	}

	/**
	 * This is a helper method for the {@link #formIslands()} method.
	 * <br>
	 * setIslandDistances sets the distances between each of the islands in the arraylist
	 * of islands creates in the method {@link #createIslands(ArrayList)}.
	 * 
	 * @param islands - The arraylist of islands created from {@link #createIslands(ArrayList)}.
	 * @return islands - The arraylist of islands created from {@link #createIslands(ArrayList)}
	 * but with each of the islands now with their distance attribute set.
	 */
	public ArrayList<Island> setIslandDistances(ArrayList<Island> islands) 
	{
		Island island1 = islands.get(0);
		Island island2 = islands.get(1);
		Island island3 = islands.get(2);
		Island island4 = islands.get(3);
		Island island5 = islands.get(4);
		
		Hashtable<Island, Integer> island1distances = new Hashtable<Island, Integer>();
		island1distances.put(island1, 0);
		island1distances.put(island2, 30);
		island1distances.put(island3, 18);
		island1distances.put(island4, 20);
		island1distances.put(island5, 40);
		island1.setDistances(island1distances);
		
		Hashtable<Island, Integer> island2distances = new Hashtable<Island, Integer>();
		island2distances.put(island1, 30);
		island2distances.put(island2, 0);
		island2distances.put(island3, 14);
		island2distances.put(island4, 40);
		island2distances.put(island5, 20);
		island2.setDistances(island2distances);
		
		Hashtable<Island, Integer> island3distances = new Hashtable<Island, Integer>();
		island3distances.put(island1, 18);
		island3distances.put(island2, 14);
		island3distances.put(island3, 0);
		island3distances.put(island4, 16);
		island3distances.put(island5, 12);
		island3.setDistances(island3distances);
		
		Hashtable<Island, Integer> island4distances = new Hashtable<Island, Integer>();
		island4distances.put(island1, 20);
		island4distances.put(island2, 40);
		island4distances.put(island3, 16);
		island4distances.put(island4, 0);
		island4distances.put(island5, 30);
		island4.setDistances(island4distances);
		
		Hashtable<Island, Integer> island5distances = new Hashtable<Island, Integer>();
		island5distances.put(island1, 40);
		island5distances.put(island2, 20);
		island5distances.put(island3, 12);
		island5distances.put(island4, 30);
		island5distances.put(island5, 0);
		island5.setDistances(island5distances);
		
		return islands;
	}
	
	/**
	 * Creates the inventory attribute of {@link GameState} for the island trader game.
	 * <br>
	 * The inventory is made by taking each item in this classes items arraylist (created
	 * from {@link #createItems()}) and forming a Hashtable mapping islands to an initial
	 * quantity of zero.
	 * 
	 * @return inventory - a hashtable mapping each item in the island trader game to zero.
	 */
	public Hashtable<Item, Integer> formInventory() 
	{
		Hashtable<Item, Integer> inventory = new Hashtable<Item, Integer>();
		for (Item item : items)
		{
			inventory.put(item, 0);
		}
		return inventory;
	}

	/**
	 * This method controls sailing between islands in the island trader game. 
	 * <br>
	 * If the sail is successful this method calls the methods 
	 * {@link GameState#payMoney(int)} with the cost to sail, {@link GameState#setCurrentIsland(Island)}
	 * setting the currentIsland to the viewedIsland, {@link GameState#incrementDay(int)} adding the 
	 * sail time to the current day, {@link RandomEvent#randomEvent()} to run random event, 
	 * {@link Store#restock()} to restock on each island in the game and  {@link GuiManager#updateGame()}.
	 * <br> 
	 * Note that random events are not called if the sail time causes the current day to reach the 
	 * game length.
	 * <br><br>
	 * Sail throws a SailIssueException if either ... <br>
	 * (1) The player is already at the island they tried to sail to. <br>
	 * (2) The player cannot afford to sail to the island they attempted to.
	 * @throws SailIssueException - If the sail attempt is invalid for either of the 2 reasons.
	 */
	public void sail() throws SailIssueException
	{
		Integer distance = gameModel.getCurrentIsland().getDistances().get(viewedIsland);
		Integer daysTravel = (distance / gameModel.getShip().getKmPerDay());
		Integer costToSail = daysTravel * gameModel.getShip().getCostPerDay();
		if (viewedIsland == gameModel.getCurrentIsland())
		{
			throw(new SailIssueException("You are already at this island"));
		}
		else if (costToSail > gameModel.getMoney())
		{
			throw(new SailIssueException("Insufficient Funds to sail"));
		}
		else
		{
			gameModel.payMoney(costToSail);
			gameModel.setCurrentIsland(viewedIsland);
			gameModel.incrementDay(daysTravel);
			if (gameModel.gameLengthReached())
			{
				gui.changeToResultsScreen();
			}
			else
			{
				random.randomEvent();
				for (Island island : gameModel.getIslandsList())
				{
					island.getStore().restock();
				}
				gui.updateGame();
			}
		}
	}
	
	/**
	 * This method controls Ship repairs in the Island Trader Game.
	 * <br>
	 * If the repair is successful this method calls {@link GameState#payMoney(int)} with the cost 
	 * to repair, {@link Ship#repair()} and {@link GuiManager#updateGame()}
	 * <br><br>
	 * repairShip throws a RepairIssueException when either ...
	 * <br>
	 * (1) The player cannot afford to repair the ship.
	 * (2) The player tries to repair an already fully repaired ship.
	 * 
	 * @return repairCost - The cost of the repair, to inform the player.
	 * @throws RepairIssueException - if the repair is invalid for either of the 2 reasons.
	 */
	public int repairShip() throws RepairIssueException
	{
		int repairCost = gameModel.getShip().costToRepair();
		if (repairCost == 0)
		{
			String errorString = gameModel.getShip().getName() + " is Already fully repaired";
			throw(new RepairIssueException(errorString));
		}
		else if (repairCost > gameModel.getMoney())
		{
			
			String errorString = "You can't afford to repair " + gameModel.getShip().getName();
			throw(new RepairIssueException(errorString));
		}
		else
		{
			gameModel.getShip().repair();
			gameModel.payMoney(repairCost);
			gui.updateGame();
			return repairCost;
		}
		
	}
	
	/**
	 * This method controls the buying of items in the Island Trader Game.
	 * <br>
	 * If Item buying is successful buyItem calls {@link Store#decrementStock(Item)} and 
	 * {@link GameState#addItem(Item)} with the Item the player is buying and calls 
	 * {@link GameState#payMoney(int)} with the cost of the Item.
	 * <br><br>
	 * buyItem throws an ItemSaleException if either ...
	 * <br> 
	 * (1) The player tries to buy an item when the store has no stock. <br>
	 * (2) The player tries to buy an item but does not have the capacity on their ship. <br>
	 * (3) The player tries to buy an item but can not afford it. <br>
	 * @param item - The item the player is trying to buy.
	 * @throws ItemSaleException - if item buying is unsuccessful for any of the 3 reasons.
	 */
	public void buyItem(Item item) throws ItemSaleException
	{
		Store store = gameModel.getCurrentIsland().getStore();

		int price = store.getPrices().get(item);
		int remainingCapacity = gameModel.getShip().getRemainingCapacity();
		
		if (store.getStock().get(item) == 0)
		{
			throw new ItemSaleException(store.getName() + " is out of " + item.getName());

		}
		else if (price > gameModel.getMoney())
		{
			throw new ItemSaleException("You can not afford " + item.getName());
		}
		else if (remainingCapacity < item.getWeight())
		{
			throw new ItemSaleException("You don't have enough space for " + item.getName());
		}
		else
		{
			store.decrementStock(item);
			gameModel.addItem(item);
			gameModel.payMoney(price);
		}
	}

	/**
	 * This method controls the selling of items in the Island Trader Game.
	 * <br>
	 * If Item buying is successful sellItem calls {@link Store#incrementStock(Item)} and 
	 * {@link GameState#removeItem(Item)} with the Item the player is selling and calls 
	 * {@link GameState#receiveMoney(int)} with the cost of the Item.
	 * <br><br>
	 * sellItem throws an ItemSaleException if
	 * the player tries to sell an item that they don't own any of.
	 * 
	 * @param item - the item the player is trying to sell.
	 * @throws ItemSaleException - if the player is trying to sell an Item they don't own.
	 */
	public void sellItem(Item item) throws ItemSaleException 
	{
		int owned = gameModel.getInventory().get(item);
		Store store = gameModel.getCurrentIsland().getStore();
		int price = store.getPrices().get(item);


		if (owned == 0)
		{
			throw new ItemSaleException("You do not have any " + item.getName() + " to sell");
		}
		else
		{
			store.incrementStock(item);
			gameModel.removeItem(item);
			gameModel.receiveMoney(price);
		}
	}
	
	/**
	 * This method controls the upgrading of the players ship in the Island Trader Game.
	 * <br>
	 * If the upgrading of the ship is successful this method calls {@link Ship#upgradeShip(Upgrade)}
	 * with the upgrade parameter, {@link GameState#payMoney(int)} with the cost of the upgrade, 
	 * {@link GameState#incrementDay(int)} with the time to build the upgrade and if this new current
	 * day reaches the gameLength this method calls {@link GuiManager#changeToResultsScreen()}.
	 * <br><br>
	 * upgradeShip throws a ShipUpgradeException if either ...
	 * <br>
	 * (1) The Ship already has the Upgrade that the player wants to buy. <br>
	 * (2) The player cannot afford the Upgrade they are trying to buy.
	 * 
	 * @param upgrade - the {@link Upgrade} the player is trying to buy.
	 * @throws ShipUpgradeException - if upgrading the ship is unsuccessful for either of the 2 reasons.
	 */
	public void upgradeShip(Upgrade upgrade) throws ShipUpgradeException 
	{
		Ship ship = gameModel.getShip();
		if (ship.getUpgrades().contains(upgrade))
		{
			throw new ShipUpgradeException("Your ship already has this upgrade");
		}
		else if (upgrade.getCost() > gameModel.getMoney())
		{
			throw new ShipUpgradeException("You can't afford this upgrade");
		}
		else
		{
			ship.upgradeShip(upgrade);
			gameModel.payMoney(upgrade.getCost());
			gameModel.incrementDay(upgrade.getBuildTime());
			if (gameModel.gameLengthReached())
			{
				gui.changeToResultsScreen();
			}
		}
	}
	
	/**
	 * This method simply prompts the user that a pirates random event has occurred.
	 * <br>
	 * This method only switches the GUI to the pirates random with
	 * {@link GuiManager#piratesRandom()}. The main running of the pirates random event
	 * is dealt with in the {@link #getPiratesResults()}
	 */
	public void startPiratesEvent() 
	{
		gui.piratesRandom();
	}
	
	/**
	 * This method determines the results of the pirates random event.
	 * <br>
	 * getPiratesResults gets a player and a pirates dice roll by calling 
	 * {@link RandomEvent#diceRoll()}. If the players ship has the cannons {@link Upgrade}
	 * the players dice roll has +2 added to it. 
	 * <br>
	 * The pirates random can have the following results ... 
	 * <br>
	 * (1) If the players dice roll is greater than or equal to the pirates roll then the player
	 * successful defends against the pirates and the game continues as normal. <br>
	 * (2) If the players roll is less than the pirates roll but the player can afford to pay the 
	 * random monetary amount from {@link RandomEvent#randomMoney()} required by the pirates, then
	 * the player pays the money ({@link GameState#payMoney(int)}) and the event ends. <br>
	 * (3) If the players roll is less than the pirates roll and the player can't afford to pay the 
	 * random monetary amount from {@link RandomEvent#randomMoney()} then the players money is set to 
	 * zero with {@link GameState#payMoney(int)} (where the int is all the players money) and a 
	 * {@link WalkThePlankException} is thrown.
	 * 
	 * 
	 * @return results - a String telling the guiManager the results to prompt the  player.
	 * @throws WalkThePlankException - if the player loses the dice roll and cannot afford
	 * to pay the pirates
	 */
	public String getPiratesResults() throws WalkThePlankException
	{
		String results;
		int playersRoll = random.diceRoll();
		int piratesRoll = random.diceRoll();
		String playersRollString = Integer.toString(playersRoll);
		String piratesRollString = Integer.toString(piratesRoll);
		if (gameModel.getShip().getHasCannons())
		{
			int playersImprovedRoll = playersRoll + 2;
			String playersImprovedRollString = Integer.toString(playersImprovedRoll);

			results = "You rolled a " + playersRollString + " but your cannons fend the pirates off more "
					+ "making your roll a " + playersImprovedRollString + ". The pirates rolled a " + 
					piratesRollString;
			playersRoll += 2;
		}
		else
		{
			results = "You rolled a " + playersRollString + ". The pirates rolled a " + 
					piratesRollString;
		}
		
		if (piratesRoll > playersRoll)
		{
			int moneyTaken = random.randomMoney();
			if (moneyTaken > gameModel.getMoney())
			{
				results += ". We couln't fend them off and the pirates aren't satisfied by our offerings. "
						+ "they make your walk the plank!";
				gameModel.payMoney(gameModel.getMoney());
				throw new WalkThePlankException(results);
			}
			else
			{
				String moneyTakenString = Integer.toString(moneyTaken);
				gameModel.payMoney(moneyTaken);
				results += ". The pirates take $" + moneyTakenString + " and leave. But thank goodness they"
						+ " didn't make you walk the plank.";
			}
		}
		else
		{
			results += ". You successfully fended off the pirates. Congratulations!";
		}
		return results;
	}

	/**
	 * Runs a shipwrecked sailors random Event in the island trader game.
	 * <br>
	 * shipwreckedSailors calls {@link RandomEvent#randomMoney()} to determine a random 
	 * int money to pay the player. It then pays the player by calling 
	 * {@link GameState#receiveMoney(int)} with the determined amount. Finally this method
	 * calls {@link GuiManager#shipWreckedSailorsRandom(int)} to prompt the user of the event. 
	 */
	public void shipwreckedSailors() 
	{
		int moneyPaid = random.randomMoney();
		gameModel.receiveMoney(moneyPaid);
		gui.shipWreckedSailorsRandom(moneyPaid);
	}

	/**
	 * Runs a bad weather random Event in the island trader game.
	 * <br>
	 * badWeather calls {@link RandomEvent#takeDamage()} to determine a random int damage for
	 * the players {@link Ship} to take. 
	 * <br>
	 * The damage taken causes one of two actions ...
	 * <br>
	 * (1) If the damagetaken is less than the ships current health, the players ship takes the
	 * damage and survives, in which case {@link Ship#damageTaken(int)} is called (with the damage
	 * taken as the int), {@link GuiManager#badWeatherRandom(String)} is run with a prompt
	 * message to tell the player about the event and the game continues. <br>
	 * (2) If the damagetaken is greater than or equal to the ships current health then 
	 * {@link GuiManager#badWeatherRandom(String)} is called with "Ship Sunk". To prompt the guiManager
	 * that the game is over.
	 */
	public void badWeather()
	{
		int damageTaken = random.takeDamage();
		if (damageTaken >= gameModel.getShip().getCurrentHealth())
		{
			gui.badWeatherRandom("Ship Sunk");
		}
		else
		{
			String damageString = Integer.toString(damageTaken);
			gameModel.getShip().damageTaken(damageTaken);
			String message = "Your ship experienced bad weather and took " + damageString + " damage.";
			gui.badWeatherRandom(message);
		}
	}
}
