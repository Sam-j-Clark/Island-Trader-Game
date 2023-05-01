package game.primaryClasses;

import game.controllerClasses.GameEnvironment;
import game.controllerClasses.GuiManager;
import game.shipClasses.Ship;

/**
 * The Upgrade Class used to make the upgrades associated with each of 
 * the stores in the island trader game. Each {@link Store} has an upgrade
 * available for purchase, which is added to the players {@link Ship}.
 * The {@link GuiManager} shows icons for upgrades on the ship in the 
 * inventory screen.
 * 
 * @see Ship
 * @see GameEnvironment#upgradeShip(Upgrade)
 * @see GuiManager#addUpgradeIcon(Upgrade)
 * 
 * @author Sam Clark
 * @author Wil Johnston
 */
public class Upgrade 
{
	private String name;
	private int cost; 
	private int buildTime;
	private String description;
	private String imagePath;
	
	/**
	 * Creates an instance of this Upgrade class. 
	 * 
	 * @param name - the name of the upgrade.
	 * @param cost - the cost of the upgrade in dollars.
	 * @param buildTime - the time it takes in days to build the upgrade.
	 * @param description - a short description of what the upgrade does.
	 * @param imagePath - The path to the icon image, for adding upgrade icons to the inventory panel.
	 */
	public Upgrade(String name, int cost, int buildTime, String description, String imagePath)
	{
		this.name = name;
		this.cost = cost;
		this.buildTime = buildTime;
		this.description = description;
		this.imagePath = imagePath;
	}
	
	/**
	 * Gets the name of this upgrade.
	 * 
	 * @return name - the name of this upgrade.
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Gets the cost to buy this upgrade.
	 * 
	 * @return cost - the cost to buy this upgrade.
	 */
	public int getCost()
	{
		return cost;
	}
	
	/**
	 * Gets the time it takes to add this upgrade to the ship in days.
	 * @return buildTime - the time it takes to add this upgrade to the players ship  in days.
	 */
	public int getBuildTime()
	{
		return buildTime;
	}
	
	/**
	 * Gets the description of this upgrade.
	 * 
	 * @return description - the description of the upgrade, saying what this upgrade does.
	 */
	public String getDesciption()
	{
		return description;
	}
	
	/**
	 * Gets the image path to this upgrades Icon image file. 
	 * imagePaths are png files.
	 * 
	 * @return imagePath - the image path for this upgrades image file.
	 */
	public String getImagePath()
	{
		return imagePath;
	}
	
}
