package game.controllerClasses;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

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
import game.shipClasses.Carol;
import game.shipClasses.FlyingScotsman;
import game.shipClasses.JollyRodrick;
import game.shipClasses.Ship;
import game.shipClasses.WhitePearl;

/**
 * The Class is the main class for the Graphic User Interface application for the Island Trader Game.
 * <br>
 * This class controls all graphical components of the Island Trader Game. GuiManager uses {@link GameState}
 * to get information about the games current state and variables and uses {@link GameEnvironment} which makes
 * the logical decisions that control changes to the GameState. 
 * 
 * @see GameEnvironment
 * @see GameState
 * 
 * @author Sam Clark
 * @author Wil Johnston
 *
 */
public class GuiManager
{
	
	/**
	 * Games Controller, controls changes to all non GUI classes.
	 */
	private GameEnvironment gameController = new GameEnvironment(this);
	
	
	/**
	 * Games Model, Hold the current value of non GUI variables
	 */
	private GameState gameModel;
	
	
	//GUI Variables
	private JFrame frame;
	
	private JPanel startPanel;
	
	private JPanel menuPanel;
	private JTextField nameTextfield;
	private JSlider daySlider;
	private JComboBox<Ship> shipCombobox;
	
	private JTabbedPane gameTabbedPane;
	
	// Inventory Components
	private JPanel gameInventoryPanel;
	private JPanel inventoryDisplayPanel;
	private JPanel inventoryInfoPanel;
	private JScrollPane inventoryScrollPane;
	
	private JLabel inventoryTitleText;
	private JButton inventoryExitGameButton;
	private JLabel inventoryNameText;
	private JLabel inventoryMoneyText;
	private JProgressBar inventoryDaysBar;
	private JLabel inventoryShipNameText;
	private JLabel inventoryCrewSizeText;
	private JLabel inventoryWageText;
	private JProgressBar inventoryCapacityBar;
	private JLabel inventoryUpgradeText;
	private JProgressBar inventoryHealthBar;
	private JButton inventoryRepairButton;
	private JLabel inventoryMessagesText;
	private JLabel inventoryRepairInfo;
	
	private Hashtable<Item, JPanel> inventoryItemPanels;
	private Hashtable<Item, JLabel>	inventoryItemNameLabels;
	private Hashtable<Item, JLabel>	inventoryQuantityLabels;
	
	
	
	// Map Components
	private JPanel gameMapPanel;
	
	private JPanel mapDisplayPanel;
	private JPanel mapInfoPanel;
	private JLabel currentIslandText;
	private JLabel islandNameText;
	private JLabel islandDistanceText;
	private JLabel islandDaysTravelText;
	private JLabel islandCostToSailText;
	private JLabel islandSuppliesText;
	private JLabel islandDemandText;
	private JLabel sailErrorsText;
	private JButton islandSailButton;

	private JButton axemansBayButton;
	private JButton moltenMountainButton;
	private JButton cosyCoveButton;
	private JButton richesResortButton;
	private JButton dinersDockButton;
	
	// Store Components
	private JPanel gameStorePanel;
	private JPanel storeSalesPanel;
	private JPanel storeUpgradePanel;
	private JScrollPane storeScrollPane;
	
	private JLabel storeNameLabel;
	private JLabel storeMoneyLabel;
	private JProgressBar storeCapacityBar;
	private JLabel storeMessagesLabel;
	private JLabel storeUpgradeNameLabel;
	private JLabel storeUpgradeCostLabel;
	private JLabel storeUpgradeTimeLabel;
	private JTextArea storeUpgradeDescriptionTextArea;
	private JButton storeUpgradeButton;
	
	private Hashtable<Item, JPanel> storeItemPanels;
	private Hashtable<Item, JLabel>	storeItemNameLabels;
	private Hashtable<Item, JLabel>	storePriceLabels;
	private Hashtable<Item, JLabel>	storeWeightLabels;
	private Hashtable<Item, JLabel>	storeShopQuantityLabels;
	private Hashtable<Item, JLabel>	storePlayerQuantityLabels;
	private Hashtable<Item, JButton> storeBuyButtons;
	private Hashtable<Item, JButton> storeSellButtons;

	// Random Event Components
	private JPanel randomEventsPanel;
	private JLabel randomEventTitle;
	private JTextArea randomEventDescription;
	private JButton randomEventDiceButton;
	private JButton randomEventCloseButton;
	
	private JPanel resultsPanel;
	
	
	private JTextArea resultsText;


	/**
	 * Launches the Island Trader Game.
	 * <br>
	 * First Creates the application by calling the constructor for this class, then 
	 * sets the GUI display to the startup Screen using {@link #openStartScreen()}.
	 * 
	 * @param args - not Used for launching application
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiManager window = new GuiManager();
					window.frame.setVisible(true);
					window.openStartScreen();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application and its components
	 */
	public GuiManager() 
	{
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame and it's display components.
	 * <br>
	 * First creates the applications game frame,
	 * Then runs the panel building methods {@link #initialiseStartPanel()}, 
	 * {@link #initialiseMenuPanel()}, {@link #initialiseGamePanels()} and 
	 * {@link #initialiseResultsPanel()}.
	 */
	private void initialize() 
	{
		gameModel = new GameState(gameController);
		gameController.setGameState(gameModel);
		
		frame = new JFrame();
		frame.setIconImage(new ImageIcon(GuiManager.class.getResource("/island1.png")).getImage());
		frame.setTitle("Trader Game");
		frame.setBounds(150, 50, 960, 540);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		initialiseStartPanel();
		initialiseMenuPanel();
		initialiseGamePanels();
		initialiseResultsPanel();
	}
	
	// Screen Changers
	// ---------------------------------------------------------------------
	/**
	 * Opens the Startup screen for the Island Trader game.
	 * <br>
	 * Displays the start screen of the island trader game by setting the start panels
	 * visibility to true and all other panels to false.
	 */
	public void openStartScreen()
	{
		startPanel.setVisible(true);
		menuPanel.setVisible(false);
		gameTabbedPane.setVisible(false);
		resultsPanel.setVisible(false);
		randomEventsPanel.setVisible(false);
	}
	
	/**
	 * Opens the Menu screen for the Island Trader game.
	 * <br>
	 * Displays the menu screen of the island trader game by setting the menu panels
	 * visibility to true and all other panels to false.
	 */
	public void changeToMenuScreen(){
		startPanel.setVisible(false);
		menuPanel.setVisible(true);
		gameTabbedPane.setVisible(false);
		resultsPanel.setVisible(false);
		randomEventsPanel.setVisible(false);

	}
	
	/**
	 * Opens the main game screen for the Island Trader game.
	 * <br>
	 * Displays the game screen of the island trader game by setting the game panels
	 * visibility to true and all other panels to false.
	 */
	public void changeToGameScreen(){
		startPanel.setVisible(false);
		menuPanel.setVisible(false);
		gameTabbedPane.setVisible(true);
		resultsPanel.setVisible(false);
		randomEventsPanel.setVisible(false);

	}
	
	/**
	 * Opens the Results screen for the Island Trader game.
	 * <br>
	 * Displays the results screen of the island trader game by setting the results panels
	 * visibility to true and all other panels to false. This method also updates the text
	 * in the results screen to display the games results.
	 */
	public void changeToResultsScreen() 
	{
		resultsText.setText(gameModel.getGameResults());
		startPanel.setVisible(false);
		menuPanel.setVisible(false);
		gameTabbedPane.setVisible(false);
		resultsPanel.setVisible(true);
		randomEventsPanel.setVisible(false);

	}
	
	/**
	 * Opens the Random Event screen for the Island Trader game.
	 * <br>
	 * Displays the random event screen of the island trader game by setting the random event 
	 * panels visibility to true and all other panels to false.
	 */
	public void changeToRandomEventScreen()
	{
		startPanel.setVisible(false);
		menuPanel.setVisible(false);
		gameTabbedPane.setVisible(false);
		resultsPanel.setVisible(false);
		randomEventsPanel.setVisible(true);
	}
	
	// Make game Graphics
	// ----------------------------------------------------------------------
	/**
	 * Creates all the gui components of the Island Trader Games start up screen
	 */
	public void initialiseStartPanel()
	{	
	
		startPanel = new JPanel();
		startPanel.setBounds(0, 0, 960, 540);
		frame.getContentPane().add(startPanel);
		startPanel.setLayout(null);
		
		
		JLabel gameTitle = new JLabel("Island Trader");
		gameTitle.setHorizontalAlignment(SwingConstants.CENTER);
		gameTitle.setFont(new Font("Modern No. 20", Font.PLAIN, 27));
		gameTitle.setBounds(300, 150, 360, 60);
		startPanel.add(gameTitle);
		
		JButton playButton = new JButton("Play");
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeToMenuScreen();
			}
		});
		playButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		playButton.setBounds(392, 255, 166, 62);
		startPanel.add(playButton);
	}
	
	/**
	 * Creates all the gui components of the Island Trader Games menu screen
	 */
	public void initialiseMenuPanel()
	{
		Ship whitePearl = new WhitePearl();
		Ship flyingScotsman = new FlyingScotsman();
		Ship jollyRodrick = new JollyRodrick();
		Ship carol = new Carol();
		Ship[] listOfShips = new Ship[] {whitePearl, flyingScotsman, jollyRodrick, carol};
		
		menuPanel = new JPanel();
		menuPanel.setBounds(0, 0, 960, 540);
		frame.getContentPane().add(menuPanel);
		menuPanel.setLayout(null);
		
		JLabel daysTitle = new JLabel("How many days would you like to play for?");
		daysTitle.setFont(new Font("Modern No. 20", Font.PLAIN, 18));
		daysTitle.setBounds(320, 150, 320, 60);
		menuPanel.add(daysTitle);
		
		daySlider = new JSlider();
		daySlider.setPaintLabels(true);
		daySlider.setMinimum(20);
		daySlider.setMaximum(50);
		daySlider.setValue(20);
		daySlider.setPaintTrack(true);
		daySlider.setMajorTickSpacing(5);
		daySlider.setPaintTicks(true);
		daySlider.setMinorTickSpacing(1);
		daySlider.setBounds(240, 200, 480, 50);
		menuPanel.add(daySlider);
		
		
		JLabel nameTitle = new JLabel("What is your name?");
		nameTitle.setHorizontalAlignment(SwingConstants.CENTER);
		nameTitle.setFont(new Font("Modern No. 20", Font.PLAIN, 18));
		nameTitle.setBounds(320, 30, 320, 50);
		menuPanel.add(nameTitle);
		
		
		nameTextfield = new JTextField();
		nameTextfield.setBounds(390, 90, 180, 40);
		menuPanel.add(nameTextfield);
		nameTextfield.setColumns(10);
		
		
		JLabel nameErrorsLabel = new JLabel();
		nameErrorsLabel.setBounds(200, 130, 560, 30);
		nameErrorsLabel.setHorizontalAlignment(JLabel.CENTER);
		nameErrorsLabel.setFont(new Font("Modern No. 20", Font.PLAIN, 12));
		nameErrorsLabel.setForeground(Color.red);
		menuPanel.add(nameErrorsLabel);
		
		
		JLabel titleShip = new JLabel("Choose a ship to captain");
		titleShip.setHorizontalAlignment(JLabel.CENTER);
		titleShip.setFont(new Font("Modern No. 20", Font.PLAIN, 18));
		titleShip.setBounds(320, 250, 320, 50);
		menuPanel.add(titleShip);
		
		shipCombobox = new JComboBox<Ship>();
		shipCombobox.setModel(new DefaultComboBoxModel<Ship>(listOfShips));
		shipCombobox.setBounds(400, 310, 160, 30);
		menuPanel.add(shipCombobox);
		
		JTextArea whitePearlDescription = new JTextArea();
		whitePearlDescription.setText(whitePearl.getShipSpecs());
		whitePearlDescription.setBounds(20, 370, 180, 110);
		menuPanel.add(whitePearlDescription);
		
		JTextArea flyingScotsmanDescription = new JTextArea();
		flyingScotsmanDescription.setText(flyingScotsman.getShipSpecs());
		flyingScotsmanDescription.setBounds(210, 370, 180, 110);
		menuPanel.add(flyingScotsmanDescription);
		
		JTextArea jollyRodrickDescription = new JTextArea();
		jollyRodrickDescription.setText(jollyRodrick.getShipSpecs());
		jollyRodrickDescription.setBounds(590, 370, 180, 110);
		menuPanel.add(jollyRodrickDescription);
		
		JTextArea carolDescription = new JTextArea();
		carolDescription.setText(carol.getShipSpecs());
		carolDescription.setBounds(780, 370, 180, 110);
		menuPanel.add(carolDescription);
		
		JButton goButton = new JButton("Go!");
		goButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent push) 
			{
				String nameAttempt = nameTextfield.getText();
				boolean validName = false;
				
				try
				{
					validName = gameController.checkValidName(nameAttempt);
					if (validName)
					{
						
						Ship ship = (Ship)shipCombobox.getSelectedItem();
						int numDays = daySlider.getValue();
						gameModel.setPlayerInfo(nameAttempt, numDays, ship);
						gameController.setViewedIsland(gameModel.getCurrentIsland());
						updateGame();
						changeToGameScreen();
						// changeToResultsScreen();
					}
					
				}
				catch(IllegalNameException error)
				{
					nameErrorsLabel.setText(error.getMessage());
				}
			}
		});
		goButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		goButton.setBounds(410, 430, 140, 40);
		menuPanel.add(goButton);
		frame.getContentPane().add(menuPanel);
	}

	/**
	 * Creates all the gui components of the Island Trader Games main game screen.
	 * <br>
	 * This method calls other methods to build the gui components of the main game 
	 * panels in the island Trader Game.
	 * <br><br>
	 * The methods called by initialise game panels include:
	 * <br>
	 * - {@link #initialiseGameTabs} which builds the tabbed pane and panels of the game screen. <br>
	 * - {@link #initialiseMapPanel()} which builds the components for the games map panel. <br>
	 * - {@link #initialiseInventoryInfoPanel()} which builds the components of the information
	 *  display section of the games Inventory panel. <br>
	 * - {@link #initialiseItemInventoryPanels()} which builds the panels for displaying the players 
	 * inventory and the items in it. <br>
	 * - {@link #initialiseStorePanels()} which builds the components of the games store panel. <br>
	 * - {@link #randomEventsPanel} which builds the components of the games random event panels.
	 */
	public void initialiseGamePanels()
	{
		initialiseGameTabs();
		initialiseMapPanel();
		initialiseInventoryInfoPanel();
		initialiseItemInventoryPanels();
		initialiseStorePanels();
		initialiseRandomEvents();
	}

	/**
	 * Creates the tabbed pane and its added panels for the game screen of the Island Trader Game.
	 * <br>
	 * The main component of the game screen of the Island trader game is a tabbed pane which holds
	 * the panels inventory, map and store. <br><br>
	 * Each of the 3 panels are broken up into a parts. <br>
	 * The Inventory Screen has a scroll pane showing the items in the players inventory and an info
	 * panel showing information from the {@link GameState}. <br>
	 * The Map panel has a map panel with each of the islands the player can go to in the game and an
	 * information panel showing information about a selected island. <br>
	 * The Store panel has a scroll pane showing each of the items available at the store and an
	 * upgrade panel showing the upgrade that can be bought from that store.
	 */
	public void initialiseGameTabs() 
	{
		gameTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		gameTabbedPane.setBounds(0, 0, 960, 540);
		frame.getContentPane().add(gameTabbedPane);
		
		// -------------- Inventory Panel ---------------
		
		gameInventoryPanel = new JPanel();
		gameInventoryPanel.setBounds(0, 0, 960, 540);
		gameInventoryPanel.setName("Inventory");
		gameInventoryPanel.setLayout(null);
		
		inventoryDisplayPanel = new JPanel();
		inventoryDisplayPanel.setLayout(new GridLayout(5, 2, 10, 10));
		inventoryDisplayPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		inventoryDisplayPanel.setSize(580, 400);
		
		inventoryScrollPane = new JScrollPane(inventoryDisplayPanel);
        inventoryScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); //SETTING SCHEME FOR HORIZONTAL BAR
        inventoryScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        inventoryScrollPane.setBounds(10, 70, 580, 400);
        gameInventoryPanel.add(inventoryScrollPane);
		
		inventoryInfoPanel = new JPanel();
		inventoryInfoPanel.setBounds(600, 0, 360, 540);
		inventoryInfoPanel.setVisible(true);
		inventoryInfoPanel.setLayout(null);
		gameInventoryPanel.add(inventoryInfoPanel);
		
		
		// ---------------- Store Panel ----------------
				
		gameStorePanel = new JPanel();
		gameStorePanel.setBounds(0, 0, 960, 540);
		gameStorePanel.setName("Shop");
		gameStorePanel.setLayout(null);

		storeSalesPanel = new JPanel();
		storeSalesPanel.setLayout(new GridLayout(5, 2, 10, 10));
		storeSalesPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		storeSalesPanel.setSize(580, 400);
		
		storeUpgradePanel = new JPanel();
		storeUpgradePanel.setBounds(670, 80, 240, 330);
		storeUpgradePanel.setVisible(true);
		storeUpgradePanel.setLayout(null);
		storeUpgradePanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		gameStorePanel.add(storeUpgradePanel);
		
		storeScrollPane = new JScrollPane(storeSalesPanel);
        storeScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); //SETTING SCHEME FOR HORIZONTAL BAR
        storeScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        storeScrollPane.setBounds(10, 70, 590, 400);
        gameStorePanel.add(storeScrollPane);
		
		// ---------------- Map Panel ------------------
								
		gameMapPanel = new JPanel();
		gameMapPanel.setBounds(0, 0, 960, 540);
		gameMapPanel.setName("Map");
		gameMapPanel.setLayout(null);
		
		mapDisplayPanel = new JPanel();
		mapDisplayPanel.setBounds(0, 0, 600, 540);
		mapDisplayPanel.setVisible(true);
		mapDisplayPanel.setLayout(null);
		gameMapPanel.add(mapDisplayPanel);
		
		mapInfoPanel = new JPanel();
		mapInfoPanel.setBounds(600, 0, 360, 540);
		mapInfoPanel.setVisible(true);
		mapInfoPanel.setLayout(null);
		gameMapPanel.add(mapInfoPanel);
		
		// ---------------------------------------------
    	randomEventsPanel = new JPanel();
		randomEventsPanel.setBounds(0, 0, 960, 540);
		frame.getContentPane().add(randomEventsPanel);
		randomEventsPanel.setLayout(null);
		
		// ---------------------------------------------
		
		gameTabbedPane.add(gameInventoryPanel);
		gameTabbedPane.add(gameMapPanel);	
		gameTabbedPane.add(gameStorePanel);
	}

	/**
	 * Creates the gui components of the Island Trader Game's inventory info panel.
	 */
	public void initialiseInventoryInfoPanel() 
	{
		inventoryTitleText = new JLabel();
		inventoryTitleText.setBounds(30, 10, 340, 50);
		inventoryTitleText.setFont(new Font("MS UI Gothic", Font.PLAIN, 35));
		inventoryTitleText.setHorizontalAlignment(JLabel.LEADING);
		inventoryTitleText.setVerticalAlignment(JLabel.CENTER);
		inventoryTitleText.setText("Inventory");
		inventoryTitleText.setVisible(true);
		gameInventoryPanel.add(inventoryTitleText);
		
		inventoryExitGameButton = new JButton();
		inventoryExitGameButton.setBounds(400, 20, 150, 40);
		inventoryExitGameButton.setText("Exit Game");
		inventoryExitGameButton.setVisible(true);
		inventoryExitGameButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent buttonPushed) 
			{
				changeToResultsScreen();
			}
		});
		gameInventoryPanel.add(inventoryExitGameButton);
		
		inventoryNameText = new JLabel();
		inventoryNameText.setBounds(0, 20, 330, 50);
		inventoryNameText.setFont(new Font("MS UI Gothic", Font.BOLD, 28));
		inventoryNameText.setHorizontalAlignment(JLabel.LEADING);
		inventoryNameText.setVerticalAlignment(JLabel.CENTER);
		inventoryNameText.setText("Players Name");
		inventoryNameText.setVisible(true);
		inventoryInfoPanel.add(inventoryNameText);
		
		inventoryMoneyText = new JLabel();
		inventoryMoneyText.setBounds(30, 70, 340, 30);
		inventoryMoneyText.setFont(new Font("MS UI Gothic", Font.PLAIN, 20));
		inventoryMoneyText.setHorizontalAlignment(JLabel.LEADING);
		inventoryMoneyText.setVerticalAlignment(JLabel.CENTER);
		inventoryMoneyText.setText("Money: $");
		inventoryMoneyText.setVisible(true);
		inventoryInfoPanel.add(inventoryMoneyText);
		
		inventoryDaysBar = new JProgressBar();
		inventoryDaysBar.setFont(new Font("MS UI Gothic", Font.BOLD, 15));
		inventoryDaysBar.setStringPainted(true);
		inventoryDaysBar.setBackground(Color.WHITE);
		inventoryDaysBar.setForeground(Color.ORANGE);
		inventoryDaysBar.setBounds(30, 110, 200, 40);
		inventoryInfoPanel.add(inventoryDaysBar);
		
		inventoryShipNameText = new JLabel();
		inventoryShipNameText.setBounds(0, 160, 340, 50);
		inventoryShipNameText.setFont(new Font("MS UI Gothic", Font.BOLD, 28));
		inventoryShipNameText.setHorizontalAlignment(JLabel.LEADING);
		inventoryShipNameText.setVerticalAlignment(JLabel.CENTER);
		inventoryShipNameText.setText("Ship name");
		inventoryShipNameText.setVisible(true);
		inventoryInfoPanel.add(inventoryShipNameText);
		
		inventoryCrewSizeText = new JLabel();
		inventoryCrewSizeText.setBounds(30, 210, 200, 30);
		inventoryCrewSizeText.setFont(new Font("MS UI Gothic", Font.PLAIN, 20));
		inventoryCrewSizeText.setHorizontalAlignment(JLabel.LEADING);
		inventoryCrewSizeText.setVerticalAlignment(JLabel.CENTER);
		inventoryCrewSizeText.setText("Crew Size");
		inventoryCrewSizeText.setVisible(true);
		inventoryInfoPanel.add(inventoryCrewSizeText);
		
		inventoryWageText = new JLabel();
		inventoryWageText.setBounds(30, 250, 340, 30);
		inventoryWageText.setFont(new Font("MS UI Gothic", Font.PLAIN, 20));
		inventoryWageText.setHorizontalAlignment(JLabel.LEADING);
		inventoryWageText.setVerticalAlignment(JLabel.CENTER);
		inventoryWageText.setText("Crew Wages");
		inventoryWageText.setVisible(true);
		inventoryInfoPanel.add(inventoryWageText);
		
		inventoryCapacityBar = new JProgressBar();
		inventoryCapacityBar.setBounds(30, 290, 200, 40);
		inventoryCapacityBar.setFont(new Font("MS UI Gothic", Font.BOLD, 15));
		inventoryCapacityBar.setStringPainted(true);
		inventoryCapacityBar.setBackground(Color.WHITE);
		inventoryCapacityBar.setForeground(Color.BLUE);
		inventoryInfoPanel.add(inventoryCapacityBar);
		
		inventoryHealthBar = new JProgressBar();
		inventoryHealthBar.setBounds(30, 340, 200, 40);
		inventoryHealthBar.setFont(new Font("MS UI Gothic", Font.BOLD, 15));
		inventoryHealthBar.setStringPainted(true);
		inventoryHealthBar.setBackground(Color.WHITE);
		inventoryHealthBar.setForeground(Color.RED);
		inventoryInfoPanel.add(inventoryHealthBar);
		
		inventoryRepairInfo = new JLabel();
		inventoryRepairInfo.setBounds(30, 390, 200, 40);
		inventoryRepairInfo.setFont(new Font("MS UI Gothic", Font.BOLD, 16));
		inventoryRepairInfo.setHorizontalAlignment(JLabel.LEADING);
		inventoryRepairInfo.setVerticalAlignment(JLabel.CENTER);
		inventoryRepairInfo.setText("");
		inventoryRepairInfo.setVisible(true);
		inventoryInfoPanel.add(inventoryRepairInfo);
		
		inventoryRepairButton = new JButton();
		inventoryRepairButton.setBounds(240, 340, 80, 40);
		inventoryRepairButton.setVisible(true);
		inventoryRepairButton.setFont(new Font("MS UI Gothic", Font.BOLD, 12));
		inventoryRepairButton.setText("Repair");
		inventoryRepairButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent buttonPushed) 
			{
				try
				{
					int repairCost = gameController.repairShip();
					inventoryMessagesText.setText("You repaired " + 
												  gameModel.getShip().getName() +
												  " for $" + repairCost);
					
				}
				catch(RepairIssueException error)
				{
					inventoryMessagesText.setText(error.getMessage());
				}
			}
		});
		inventoryInfoPanel.add(inventoryRepairButton);
		
		
		inventoryUpgradeText = new JLabel();
		inventoryUpgradeText.setBounds(30, 400, 340, 30);
		inventoryUpgradeText.setFont(new Font("MS UI Gothic", Font.PLAIN, 20));
		inventoryUpgradeText.setHorizontalAlignment(JLabel.LEADING);
		inventoryUpgradeText.setVerticalAlignment(JLabel.CENTER);
		inventoryUpgradeText.setText("upgrade");
		inventoryUpgradeText.setVisible(false);
		inventoryInfoPanel.add(inventoryUpgradeText);
		
		inventoryMessagesText = new JLabel();
		inventoryMessagesText.setBounds(0, 430, 340, 20);
		inventoryMessagesText.setHorizontalAlignment(JLabel.CENTER);
		inventoryMessagesText.setFont(new Font("Modern No. 20", Font.PLAIN, 15));
		inventoryMessagesText.setForeground(Color.red);
		inventoryInfoPanel.add(inventoryMessagesText);

	}
	
	/**
	 * Creates the panels used to display each item in a players inventory.
	 */
	public void initialiseItemInventoryPanels() 
	{
		inventoryItemPanels = new Hashtable<Item, JPanel>();
		inventoryItemNameLabels = new Hashtable<Item, JLabel>();
		inventoryQuantityLabels = new Hashtable<Item, JLabel>();
		ArrayList<Item> itemsList = gameController.getItemsList();
		ArrayList<JPanel> panelsList = makePanelsList();
		ArrayList<JLabel> namesList = makeLabelsList();
		ArrayList<JLabel> quantitiesList = makeLabelsList();
		for (int i = 0 ; i < 10 ; i++)
		{
			inventoryItemPanels.put(itemsList.get(i), panelsList.get(i));
			inventoryItemNameLabels.put(itemsList.get(i), namesList.get(i));
			inventoryQuantityLabels.put(itemsList.get(i), quantitiesList.get(i));
		}
		for (Item item : itemsList)
		{
			JPanel itemPanel = inventoryItemPanels.get(item);
			JLabel itemName = inventoryItemNameLabels.get(item);
			JLabel itemQuantityLabel = inventoryQuantityLabels.get(item);
			
			itemPanel = new JPanel();
			itemPanel.setPreferredSize(new Dimension(280, 100));
			itemPanel.setMaximumSize(new Dimension(280, 100));
			itemPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			inventoryDisplayPanel.add(itemPanel);
			itemPanel.setLayout(null);
			
			itemName.setText(item.getName());
			itemName.setBounds(5, 5, 150, 30);
			itemName.setFont(new Font("MS UI Gothic", Font.PLAIN, 26));
			itemName.setVisible(true);
			itemPanel.add(itemName);
			
			itemQuantityLabel.setText("Quantity: " + Integer.toString(gameModel.getInventory().get(item)));
			itemQuantityLabel.setBounds(130, 60, 140, 30);
			itemQuantityLabel.setFont(new Font("MS UI Gothic", Font.PLAIN, 20));
			itemQuantityLabel.setVisible(true);
			itemPanel.add(itemQuantityLabel);
			
		}

		updateInventory();
	}

	/**
	 * Creates the gui Components of the Island trader games map panel. 
	 */
	public void initialiseMapPanel() 
	{
		axemansBayButton = new JButton("Axeman's Bay");
		//Island 1 pressed
		axemansBayButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				updateMapInfo(gameModel.getIslandsList().get(0));
			}
		});
		axemansBayButton.setBounds(29, 137, 150, 35);
		axemansBayButton.setFont(new Font("MS UI Gothic", Font.BOLD, 11));
		mapDisplayPanel.add(axemansBayButton);
		
		moltenMountainButton = new JButton("Molten Mountain");
		//Island 2 pressed
		moltenMountainButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateMapInfo(gameModel.getIslandsList().get(1));
			}
		});
		moltenMountainButton.setBounds(417, 137, 150, 35);
		moltenMountainButton.setFont(new Font("MS UI Gothic", Font.BOLD, 11));
		mapDisplayPanel.add(moltenMountainButton);
		
		cosyCoveButton = new JButton("Cosy Cove");
		//Island 3 pressed
		cosyCoveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateMapInfo(gameModel.getIslandsList().get(2));
			}
		});
		cosyCoveButton.setBounds(220, 262, 150, 35);
		cosyCoveButton.setFont(new Font("MS UI Gothic", Font.BOLD, 11));
		mapDisplayPanel.add(cosyCoveButton);
		
		richesResortButton = new JButton("Riches Resort");
		//Island 4 pressed
		richesResortButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateMapInfo(gameModel.getIslandsList().get(3));
			}
		});
		richesResortButton.setBounds(29, 374, 150, 35);
		richesResortButton.setFont(new Font("MS UI Gothic", Font.BOLD, 11));
		mapDisplayPanel.add(richesResortButton);
		
		dinersDockButton = new JButton("Diner's Dock");
		//Island 5 pressed
		dinersDockButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				updateMapInfo(gameModel.getIslandsList().get(4));
			}
		});
		dinersDockButton.setBounds(417, 374, 150, 35);
		dinersDockButton.setFont(new Font("MS UI Gothic", Font.BOLD, 11));
		mapDisplayPanel.add(dinersDockButton);
		
		
		currentIslandText = new JLabel();
		currentIslandText.setBounds(30, 10, 340, 50);
		currentIslandText.setFont(new Font("MS UI Gothic", Font.PLAIN, 35));
		currentIslandText.setHorizontalAlignment(JLabel.LEADING);
		currentIslandText.setVerticalAlignment(JLabel.CENTER);
		currentIslandText.setText("");
		currentIslandText.setVisible(true);
		mapDisplayPanel.add(currentIslandText);
		
		islandNameText = new JLabel();
		islandNameText.setBounds(60, 60, 340, 50);
		islandNameText.setFont(new Font("MS UI Gothic", Font.BOLD, 30));
		islandNameText.setHorizontalAlignment(JLabel.LEADING);
		islandNameText.setVerticalAlignment(JLabel.CENTER);
		islandNameText.setText("");
		islandNameText.setVisible(true);
		mapInfoPanel.add(islandNameText);
		
		islandDistanceText = new JLabel();
		islandDistanceText.setBounds(60, 150, 340, 30);
		islandDistanceText.setFont(new Font("MS UI Gothic", Font.PLAIN, 25));
		islandDistanceText.setHorizontalAlignment(JLabel.LEADING);
		islandDistanceText.setVerticalAlignment(JLabel.CENTER);
		islandDistanceText.setText("");
		islandDistanceText.setVisible(true);
		mapInfoPanel.add(islandDistanceText);
		
		islandDaysTravelText = new JLabel();
		islandDaysTravelText.setBounds(60, 190, 340, 30);
		islandDaysTravelText.setFont(new Font("MS UI Gothic", Font.PLAIN, 25));
		islandDaysTravelText.setHorizontalAlignment(JLabel.LEADING);
		islandDaysTravelText.setVerticalAlignment(JLabel.CENTER);
		islandDaysTravelText.setText("");
		islandDaysTravelText.setVisible(true);
		mapInfoPanel.add(islandDaysTravelText);
		
		islandCostToSailText = new JLabel();
		islandCostToSailText.setBounds(60, 230, 340, 30);
		islandCostToSailText.setFont(new Font("MS UI Gothic", Font.PLAIN, 25));
		islandCostToSailText.setHorizontalAlignment(JLabel.LEADING);
		islandCostToSailText.setVerticalAlignment(JLabel.CENTER);
		islandCostToSailText.setText("");
		islandCostToSailText.setVisible(true);
		mapInfoPanel.add(islandCostToSailText);
		
		islandSuppliesText = new JLabel();
		islandSuppliesText.setBounds(60, 270, 340, 30);
		islandSuppliesText.setFont(new Font("MS UI Gothic", Font.PLAIN, 25));
		islandSuppliesText.setHorizontalAlignment(JLabel.LEADING);
		islandSuppliesText.setVerticalAlignment(JLabel.CENTER);
		islandSuppliesText.setText("");
		islandSuppliesText.setVisible(true);
		mapInfoPanel.add(islandSuppliesText);
		
		islandDemandText = new JLabel();
		islandDemandText.setBounds(60, 310, 340, 30);
		islandDemandText.setFont(new Font("MS UI Gothic", Font.PLAIN, 25));
		islandDemandText.setHorizontalAlignment(JLabel.LEADING);
		islandDemandText.setVerticalAlignment(JLabel.CENTER);
		islandDemandText.setText("");
		islandDemandText.setVisible(true);
		mapInfoPanel.add(islandDemandText);
		
		sailErrorsText = new JLabel();
		sailErrorsText.setBounds(50, 420, 340, 30);
		sailErrorsText.setFont(new Font("MS UI Gothic", Font.PLAIN, 20));
		sailErrorsText.setForeground(Color.RED);
		sailErrorsText.setHorizontalAlignment(JLabel.CENTER);
		sailErrorsText.setVerticalAlignment(JLabel.CENTER);
		sailErrorsText.setText("");
		sailErrorsText.setVisible(true);
		mapDisplayPanel.add(sailErrorsText);
		
		islandSailButton = new JButton();
		islandSailButton.setBounds(60, 380, 200, 50);
		islandSailButton.setText("Sail");
		islandSailButton.setFont(new Font("MS UI Gothic", Font.PLAIN, 20));
		islandSailButton.setVisible(true);
		islandSailButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
					gameController.sail();
				}
				catch(SailIssueException error)
				{
					sailErrorsText.setText(error.getMessage());
				}
			}
		});
		mapInfoPanel.add(islandSailButton);
	}
	
	/**
	 * Creates the gui components of the island trader games store panel.
	 * <br>
	 * This method creates the main information components for the store panel. Then 
	 * calls the methods {@link #initialiseStoreUpgradePanel()} and {@link #storeItemPanels}
	 * to build the rest of the store panel components.
	 */
	public void initialiseStorePanels()
	{
		storeNameLabel = new JLabel("Store Name");
        storeNameLabel.setFont(new Font("MS UI Gothic", Font.BOLD, 25));
        storeNameLabel.setBounds(10, 10, 350, 60);
        gameStorePanel.add(storeNameLabel);
        
        storeMoneyLabel = new JLabel("Money Quantity");
        storeMoneyLabel.setFont(new Font("MS UI Gothic", Font.BOLD, 25));
        storeMoneyLabel.setBounds(370, 10, 255, 60);
        gameStorePanel.add(storeMoneyLabel);
        
        
		storeCapacityBar = new JProgressBar();
		storeCapacityBar.setBounds(670, 20, 220, 50);
		storeCapacityBar.setFont(new Font("MS UI Gothic", Font.BOLD, 15));
		storeCapacityBar.setStringPainted(true);
		storeCapacityBar.setBackground(Color.WHITE);
		storeCapacityBar.setForeground(Color.ORANGE);
		gameStorePanel.add(storeCapacityBar);
		
		storeMessagesLabel = new JLabel("error message like this");
		storeMessagesLabel.setVisible(true);
		storeMessagesLabel.setFont(new Font("MS UI Gothic", Font.BOLD, 15));
		storeMessagesLabel.setBounds(640, 420, 300, 30);
        storeMessagesLabel.setForeground(Color.red);
        gameStorePanel.add(storeMessagesLabel);

		initialiseStoreUpgradePanel();
		initialiseStoreItemPanels();
	}
	
	/**
	 * Creates the gui components of the upgrade panel section of the Island Trader Games store panel.
	 */
	public void initialiseStoreUpgradePanel()
	{
		storeUpgradeNameLabel = new JLabel("Upgrade Name");
		storeUpgradeNameLabel.setBounds(0, 0, 300, 30);
		storeUpgradeNameLabel.setVisible(true);
		storeUpgradeNameLabel.setFont(new Font("MS UI Gothic", Font.BOLD, 25));
		storeUpgradePanel.add(storeUpgradeNameLabel);
		
		storeUpgradeCostLabel = new JLabel("Upgrade Cost");
		storeUpgradeCostLabel.setBounds(10, 40, 300, 30);
		storeUpgradeCostLabel.setVisible(true);
		storeUpgradeCostLabel.setFont(new Font("MS UI Gothic", Font.PLAIN, 20));
		storeUpgradePanel.add(storeUpgradeCostLabel);

		storeUpgradeTimeLabel = new JLabel("Upgrade Time");
		storeUpgradeTimeLabel.setBounds(10, 80, 300, 30);
		storeUpgradeTimeLabel.setVisible(true);
		storeUpgradeTimeLabel.setFont(new Font("MS UI Gothic", Font.PLAIN, 20));
		storeUpgradePanel.add(storeUpgradeTimeLabel);

		storeUpgradeDescriptionTextArea = new JTextArea();
		storeUpgradeDescriptionTextArea.setBounds(10, 120, 220, 150);
		storeUpgradeDescriptionTextArea.setVisible(true);
		storeUpgradeDescriptionTextArea.setFont(new Font("MS UI Gothic", Font.PLAIN, 15));
		storeUpgradeDescriptionTextArea.setLineWrap(true);
		storeUpgradeDescriptionTextArea.setWrapStyleWord(true);

		storeUpgradePanel.add(storeUpgradeDescriptionTextArea);

		storeUpgradeButton = new JButton();
		storeUpgradeButton.setBounds(30, 280, 190, 40);
		storeUpgradeButton.setText("Upgrade");
		storeUpgradeButton.setVisible(true);
		storeUpgradeButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent push)
			{
				Upgrade upgrade = gameModel.getCurrentIsland().getStore().getUpgrade();
				try 
				{
					gameController.upgradeShip(upgrade);
					storeMessagesLabel.setText("You have added " + upgrade.getName());
					addUpgradeIcon(upgrade);
					updateGame();
				}
				catch (ShipUpgradeException error)
				{
					storeMessagesLabel.setText(error.getMessage());
				}
			}
		});
		storeUpgradePanel.add(storeUpgradeButton);

	}

	/**
	 * Adds the icon for an upgrade to the games inventory screen when a Ship is upgraded.
	 * <br>
	 * Called from the store upgrade panel, addUpgradeIcon adds the upgrade icon to the inventory info
	 * panel when a ship is upgraded. The {@link Upgrade} icon is got from the {@link Upgrade#getImagePath()}
	 * method. The upgrade icons also add a tooltip with the upgrades description. <br>
	 * Note: this method is not called when an unsuccessful upgrade attempt occurs. This is dealt to by
	 * the {@link GameEnvironment#upgradeShip(Upgrade)} method.
	 * @param upgrade - The upgrade whos icon requires adding. 
	 */
	public void addUpgradeIcon(Upgrade upgrade) 
	{
		JLabel upgradeIconLabel = new JLabel();
		
		ImageIcon icon = new ImageIcon(GuiManager.class.getResource(upgrade.getImagePath()));
		upgradeIconLabel.setIcon(icon);
		upgradeIconLabel.setToolTipText(upgrade.getDesciption());
		upgradeIconLabel.setVisible(true);
		inventoryInfoPanel.add(upgradeIconLabel);
		
		switch (upgrade.getName())
		{
		case "Bigger Sails":
			upgradeIconLabel.setBounds(300, 180, 30, 30);
			break;
		case "Feast Table":
			upgradeIconLabel.setBounds(300, 210, 30, 30);

			break;
		case "Forged Tools":
			upgradeIconLabel.setBounds(300, 240, 30, 30);

			break;
		case "Cannons":
			upgradeIconLabel.setBounds(300, 270, 30, 30);

			break;
		case "Storage Boxes":
			upgradeIconLabel.setBounds(300, 300, 30, 30);

			break;
		default:
			throw new NullPointerException("This Upgrade Doesnt Exist");
		}
		
		
	}

	/**
	 * Creates the gui components for each of the items sold in the store panel of the Island Trader Game.
	 */
	public void initialiseStoreItemPanels() 
	{
		storeItemPanels = new Hashtable<Item, JPanel>();
		storeItemNameLabels = new Hashtable<Item, JLabel>();
		storeShopQuantityLabels = new Hashtable<Item, JLabel>();
		storePlayerQuantityLabels = new Hashtable<Item, JLabel>();
		storeWeightLabels = new Hashtable<Item, JLabel>();
		storePriceLabels = new Hashtable<Item, JLabel>();
		storeBuyButtons = new Hashtable<Item, JButton>();
		storeSellButtons = new Hashtable<Item, JButton>();
		ArrayList<Item> itemsList = gameController.getItemsList();
		ArrayList<JPanel> panelsList = makePanelsList();
		ArrayList<JLabel> namesList = makeLabelsList();
		ArrayList<JLabel> weightList = makeLabelsList();
		ArrayList<JLabel> priceList = makeLabelsList();
		ArrayList<JLabel> storeStockList = makeLabelsList();
		ArrayList<JLabel> playerStockList = makeLabelsList();
		ArrayList<JButton> buyButtonList = makeButtonsList();
		ArrayList<JButton> sellButtonList = makeButtonsList();
		
		for (int i = 0 ; i < 10 ; i++)
		{
			storeItemPanels.put(itemsList.get(i), panelsList.get(i));
			storeItemNameLabels.put(itemsList.get(i), namesList.get(i));
			storeShopQuantityLabels.put(itemsList.get(i), weightList.get(i));
			storePlayerQuantityLabels.put(itemsList.get(i), priceList.get(i));
			storeWeightLabels.put(itemsList.get(i), storeStockList.get(i));
			storePriceLabels.put(itemsList.get(i), playerStockList.get(i));
			storeBuyButtons.put(itemsList.get(i), buyButtonList.get(i));
			storeSellButtons.put(itemsList.get(i), sellButtonList.get(i));
		}
		for (Item item : itemsList)
		{
			JPanel itemPanel = storeItemPanels.get(item);
			JLabel itemName = storeItemNameLabels.get(item);
			JLabel shopQuantity = storeShopQuantityLabels.get(item);
			JLabel playerQuantity = storePlayerQuantityLabels.get(item);
			JLabel itemWeight = storeWeightLabels.get(item);
			JLabel itemPrice = storePriceLabels.get(item);
			JButton buyButton = storeBuyButtons.get(item);
			JButton sellButton = storeSellButtons.get(item);

			Store store = gameModel.getCurrentIsland().getStore();

			itemPanel = new JPanel();
			itemPanel.setPreferredSize(new Dimension(280, 200));
			itemPanel.setMaximumSize(new Dimension(280, 200));
			itemPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			storeSalesPanel.add(itemPanel);
			itemPanel.setLayout(null);
			
			itemName.setText(item.getName());
			itemName.setBounds(5, 5, 150, 30);
			itemName.setFont(new Font("MS UI Gothic", Font.PLAIN, 26));
			itemName.setVisible(true);
			itemPanel.add(itemName);
			
			playerQuantity.setText("Amount owned: " + Integer.toString(gameModel.getInventory().get(item)));
			playerQuantity.setBounds(5, 130, 200, 30);
			playerQuantity.setFont(new Font("MS UI Gothic", Font.PLAIN, 20));
			playerQuantity.setVisible(true);
			itemPanel.add(playerQuantity);
			
			shopQuantity.setText("Shop Stock: " + Integer.toString(store.getStock().get(item)));
			shopQuantity.setBounds(5, 160, 200, 30);
			shopQuantity.setFont(new Font("MS UI Gothic", Font.PLAIN, 20));
			shopQuantity.setVisible(true);
			itemPanel.add(shopQuantity);
			
			itemWeight.setText("Weight: " + item.getWeight() + "kg");
			itemWeight.setBounds(5, 100, 200, 30);
			itemWeight.setFont(new Font("MS UI Gothic", Font.PLAIN, 20));
			itemWeight.setVisible(true);
			itemPanel.add(itemWeight);
			
			itemPrice.setText("Price: $" + Integer.toString(store.getPrices().get(item)));
			itemPrice.setBounds(5, 70, 200, 30);
			itemPrice.setFont(new Font("MS UI Gothic", Font.PLAIN, 20));
			itemPrice.setVisible(true);
			itemPanel.add(itemPrice);
			
			buyButton.setText("Buy");
			buyButton.setBounds(200, 165, 70, 25);
			buyButton.setFont(new Font("MS UI Gothic", Font.BOLD, 15));
			buyButton.setVisible(true);
			buyButton.addActionListener((new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					try
					{
						gameController.buyItem(item);
						updateGame();
					}
					catch(ItemSaleException error)
					{
						storeMessagesLabel.setText(error.getMessage());
					}
					
				}
			}));
			itemPanel.add(buyButton);
			
			sellButton.setText("Sell");
			sellButton.setBounds(200, 130, 70, 25);
			sellButton.setFont(new Font("MS UI Gothic", Font.BOLD, 15));
			sellButton.setVisible(true);
			sellButton.addActionListener((new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					try
					{
						gameController.sellItem(item);
						updateGame();
					}
					catch(ItemSaleException error)
					{
						storeMessagesLabel.setText(error.getMessage());
					}
				}
			}));
			itemPanel.add(buyButton);
			itemPanel.add(sellButton);
			

		}
	}
	
	/**
	 * Creates the gui components of the Island Trader games results panel.
	 */
	public void initialiseResultsPanel()
	{
		resultsPanel = new JPanel();
		resultsPanel.setBounds(0, 0, 960, 540);
		frame.getContentPane().add(resultsPanel);
		resultsPanel.setLayout(null);
		
		JLabel resultsTitle = new JLabel();
		resultsTitle.setHorizontalAlignment(SwingConstants.CENTER);
		resultsTitle.setFont(new Font("Monospaced", Font.PLAIN, 40));
		resultsTitle.setText("Game Results");
		resultsTitle.setBounds(280, 50, 400, 80);
		resultsPanel.add(resultsTitle);
		
		resultsText = new JTextArea();
		resultsText.setBounds(300, 200, 360, 260);
		resultsPanel.add(resultsText);
		resultsText.setFont(new Font("Monospaced", Font.PLAIN, 20));
	}
	
    /**
     * Creates the gui components of the Island Trader games random events panel.
     */
    public void initialiseRandomEvents()
    {    	
    	randomEventTitle = new JLabel();
    	randomEventDescription = new JTextArea();
    	randomEventCloseButton = new JButton();
    	randomEventDiceButton = new JButton();
		
		randomEventTitle = new JLabel("Random Title");
		randomEventTitle.setFont(new Font("MS UI Gothic", Font.BOLD, 30));
		randomEventTitle.setHorizontalAlignment(SwingConstants.CENTER);
		randomEventTitle.setBounds(300, 30, 360, 50);
		randomEventTitle.setVisible(true);
		randomEventsPanel.add(randomEventTitle);
		
		randomEventDescription = new JTextArea();
		randomEventDescription.setFont(new Font("MS UI Gothic", Font.BOLD, 18));
		randomEventDescription.setText("Random Event Description goes here");
		randomEventDescription.setLineWrap(true);
		randomEventDescription.setWrapStyleWord(true);
		randomEventDescription.setBackground(Color.WHITE);
		randomEventDescription.setBounds(230, 100, 500, 250);
		randomEventDescription.setVisible(true);
		randomEventsPanel.add(randomEventDescription);
		
		randomEventCloseButton = new JButton("Close");
		randomEventCloseButton.setFont(new Font("MS UI Gothic", Font.BOLD, 18));
		randomEventCloseButton.setBounds(400, 380, 160, 60);
		randomEventCloseButton.setVisible(false);
		randomEventsPanel.add(randomEventCloseButton);
		randomEventCloseButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent closeButton)
			{
				changeToGameScreen();
			}
		});
		
		randomEventDiceButton = new JButton("Roll Dice");
		randomEventDiceButton.setFont(new Font("MS UI Gothic", Font.BOLD, 18));
		randomEventDiceButton.setVisible(true);
		randomEventDiceButton.setBounds(400, 380, 160, 60);
		randomEventsPanel.add(randomEventDiceButton);
		randomEventDiceButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent rollButton)
			{
				try 
				{
					String results = gameController.getPiratesResults();
					randomEventDescription.setText(results);
					randomEventDiceButton.setVisible(false);
					randomEventCloseButton.setVisible(true);
					updateGame();
				}
				catch(WalkThePlankException exception)
				{
					randomEventDescription.setText(exception.getMessage());
					randomEventCloseButton.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent closeButton)
						{
							changeToResultsScreen();
						}
					});
				}
			}
		});
    	
    }

    // Graphics Helper Functions
	// -----------------------------------------------------------------------
	/**
	 * A Helper Function to create a gui JPanel component for each item in the Island Trader Game.
	 * <br>
	 * This method helps both the {@link #initialiseStoreItemPanels()} and 
	 * {@link #initialiseItemInventoryPanels()}
	 * @return panelsList - An arraylist of JPanel components for each item in the game.
	 */
	public ArrayList<JPanel> makePanelsList() {
		ArrayList<JPanel> panelsList = new ArrayList<JPanel>();
		JPanel inventoryBeefPanel = new JPanel();
		JPanel inventoryLobsterPanel = new JPanel();
		JPanel inventoryWoolPanel = new JPanel();
		JPanel inventorySilkPanel = new JPanel();
		JPanel inventoryIronPanel = new JPanel();
		JPanel inventorySteelPanel = new JPanel();
		JPanel inventoryGoldPanel = new JPanel();
		JPanel inventoryDiamondPanel = new JPanel();
		JPanel inventoryWoodPanel = new JPanel();
		JPanel inventoryCharcoalPanel = new JPanel();
		
		panelsList.add(inventoryBeefPanel);
		panelsList.add(inventoryLobsterPanel);
		panelsList.add(inventoryWoolPanel);
		panelsList.add(inventorySilkPanel);
		panelsList.add(inventoryIronPanel);
		panelsList.add(inventorySteelPanel);
		panelsList.add(inventoryGoldPanel);
		panelsList.add(inventoryDiamondPanel);
		panelsList.add(inventoryWoodPanel);
		panelsList.add(inventoryCharcoalPanel);
		
		return panelsList;
	}
	
	/**
	 * A Helper Function to create a gui JLabel component for each item in the Island Trader Game.
	 * <br>
	 * This method helps both the {@link #initialiseStoreItemPanels()} and 
	 * {@link #initialiseItemInventoryPanels()}
	 * @return labelsList - An arraylist of JLabel components for each item in the game.
	 */
    public ArrayList<JLabel> makeLabelsList() {
    	ArrayList<JLabel> labelsList = new ArrayList<JLabel>();
		JLabel itemLabel1 = new JLabel();
		JLabel itemLabel2 = new JLabel();
		JLabel itemLabel3 = new JLabel();
		JLabel itemLabel4 = new JLabel();
		JLabel itemLabel5 = new JLabel();
		JLabel itemLabel6 = new JLabel();
		JLabel itemLabel7 = new JLabel();
		JLabel itemLabel8 = new JLabel();
		JLabel itemLabel9 = new JLabel();
		JLabel itemLabel10 = new JLabel();
		
		
		labelsList.add(itemLabel1);
		labelsList.add(itemLabel2);
		labelsList.add(itemLabel3);
		labelsList.add(itemLabel4);
		labelsList.add(itemLabel5);
		labelsList.add(itemLabel6);
		labelsList.add(itemLabel7);
		labelsList.add(itemLabel8);
		labelsList.add(itemLabel9);
		labelsList.add(itemLabel10);
		
		return labelsList;
	}
  
	/**
	 * A Helper Function to create a gui JButton component for each item in the Island Trader Game.
	 * <br>
	 * This method helps both the {@link #initialiseStoreItemPanels()} and 
	 * {@link #initialiseItemInventoryPanels()}
	 * @return buttonsList - an Arraylist of JButtons for each item in the island trader game.
	 */
    public ArrayList<JButton> makeButtonsList()
    {
    	ArrayList<JButton> buttonsList = new ArrayList<JButton>();
		JButton itemButton1 = new JButton();
		JButton itemButton2 = new JButton();
		JButton itemButton3 = new JButton();
		JButton itemButton4 = new JButton();
		JButton itemButton5 = new JButton();
		JButton itemButton6 = new JButton();
		JButton itemButton7 = new JButton();
		JButton itemButton8 = new JButton();
		JButton itemButton9 = new JButton();
		JButton itemButton10 = new JButton();
		
		
		buttonsList.add(itemButton1);
		buttonsList.add(itemButton2);
		buttonsList.add(itemButton3);
		buttonsList.add(itemButton4);
		buttonsList.add(itemButton5);
		buttonsList.add(itemButton6);
		buttonsList.add(itemButton7);
		buttonsList.add(itemButton8);
		buttonsList.add(itemButton9);
		buttonsList.add(itemButton10);
		
		return buttonsList;
    }
    
    // Random Events 
    // ------------------------------------------------------------------------
    /**
     * Sets the gui display of the Island trader game to show a pirates random event.
     * <br>
     * This display only allows the player to interact with it and proceed by rolling the dice.
     * which changes the gui display to the pirates results and changes the button to the close
     * button.
     */
    public void piratesRandom()
    {
    	changeToRandomEventScreen();
    	
		randomEventCloseButton.setVisible(false);
		randomEventDiceButton.setVisible(true);
		
		randomEventTitle.setText("Pirates!");
		randomEventDescription.setText("Oh no, Pirates are attacking the ship roll the dice to try fend them off");
		
    }

    /**
     * Sets the gui display to a shipwrecked sailors random event.
     * <br>
     * This method is passed an integer value representing the quantity of money the sailors
     * event is paying the player. The player is prompted in the random events panel that the
     * event occurred and how much they earned from the sailors.
     * 
     * @param moneyPaid
     */
    public void shipWreckedSailorsRandom(int moneyPaid)
    {
    	changeToRandomEventScreen();
    	
		randomEventCloseButton.setVisible(true);
		randomEventDiceButton.setVisible(false);
		
		randomEventTitle.setText("Shipwrecked Sailors!");
		
		String moneyPaidString = Integer.toString(moneyPaid);
		randomEventDescription.setText("You spot a sinking ship of sailor's and save them. They thank you by "
				+ "paying you $" + moneyPaidString + ".");
    }
    
    /**
     * Sets the gui display to a bad weather random event.
     * <br>
     * This method is passed a string saying the results of the random event. <br>
     * One of two results can occur: <br>
     * - The ship takes too much damage so the ship sinks and the game is over, calling
     * {@link #changeToResultsScreen()}.
     * - The ship takes the damage and survives, so the player is prompted of the damage in
     * in the random event panel.
     * 
     * @param message -  A short message about the result of the event.
     */
    public void badWeatherRandom(String message)
    {
    	changeToRandomEventScreen();
	    	
		randomEventCloseButton.setVisible(true);
		randomEventDiceButton.setVisible(false);
		
		randomEventTitle.setText("Bad Weather!");
		if (message == "Ship Sunk")
		{
			message = "Your ships health has hit zero. She's going down!";
			randomEventCloseButton.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent closeButton)
				{
					changeToResultsScreen();
				}
			});
			
		}
		randomEventDescription.setText(message);
    }
	
    
    // Game Updates
    // ------------------------------------------------------------------------
    
    /**
     * Updates the gui components for the island trader game.
     * <br>
     * This method updates the gui components by calling the helper functions: <br>
     * - {@link #updateStore()} <br>
     * - {@link #updateInventoryInfo()} <br>
     * - {@link #updateInventory()} <br>
     * - {@link #updateMapInfo(Island)}
     */
    public void updateGame()
    {
    	updateInventoryInfo();
    	updateStore();
    	updateInventory();
    	updateMapInfo(gameController.getViewedIsland());
    }
	
    /**
     * Updates the gui components of the Map panel in the island trader game. 
     * <br>
     * This method helps the {@link #updateGame()} method by updating the map screen in 
     * the Island Trader Game. The parameter island is used to change the viewed island
     * to allow the player to sail.
     * @param island - the island to be viewed by the player in the map info panel.
     */
	public void updateMapInfo(Island island)
	{
		gameController.setViewedIsland(island);
		sailErrorsText.setText("");
		currentIslandText.setText(gameModel.getCurrentIsland().getName());
		islandNameText.setText(island.getName());
		Integer distance = gameModel.getCurrentIsland().getDistances().get(island);
		islandDistanceText.setText("Distance: " + Integer.toString(distance));
		Integer daysTravel = (distance / gameModel.getShip().getKmPerDay());
		islandDaysTravelText.setText("Days Travel: " + Integer.toString(daysTravel));
		Integer costToSail = daysTravel * gameModel.getShip().getCostPerDay();
		islandCostToSailText.setText("Cost to Sail: $"  + Integer.toString(costToSail));
		islandSuppliesText.setText("Supplies: " + island.getSupply());
		islandDemandText.setText("Demands: " + island.getDemand());
	}
	
	/**
     * Updates the gui components of the Inventory Info panel in the island trader game. 
     * <br>
     * This method helps the {@link #updateGame()} method by updating the inventory screen
     * in the Island Trader Game. This method does not update the inventory itself, that is 
     * done with the other helper method {@link #updateInventory()}
	 */
	public void updateInventoryInfo()
	{
		inventoryNameText.setText(gameModel.getPlayerName());
		
		inventoryMoneyText.setText("Money: $" + gameModel.getMoney());
		
		inventoryRepairInfo.setText("Cost to repair: "+ gameModel.getShip().costToRepair());
		
		inventoryDaysBar.setMaximum(gameModel.getGameLength());
		inventoryDaysBar.setValue(gameModel.getCurrentDay());
		inventoryDaysBar.setString("Day: " + gameModel.getCurrentDay() + " / " 
									+ gameModel.getGameLength());
		
		Ship ship = gameModel.getShip();
		inventoryShipNameText.setText(ship.getName());
		
		inventoryCrewSizeText.setText("Crew Size: " + ship.getCrewSize());
		
		inventoryWageText.setText("Crew Wages: " + ship.getCostPerDay() + " / Day");
		
		inventoryCapacityBar.setMaximum(ship.getMaxWeight());
		inventoryCapacityBar.setValue(ship.getCurrentWeight());
		inventoryCapacityBar.setString("Weight: " + ship.getCurrentWeight() +
										" / " + ship.getMaxWeight());
		
		inventoryHealthBar.setMaximum(ship.getMaxHealth());
		inventoryHealthBar.setValue(ship.getCurrentHealth());
		inventoryHealthBar.setString("Health: " + ship.getCurrentHealth() + " / "
									+ ship.getMaxHealth());
	}	

	/**
     * Updates the gui components of the Store panel in the island trader game. 
     * <br>
     * This method helps the {@link #updateGame()} method by updating the Store screen
     * in the Island Trader Game. 
	 */
	public void updateStore()
	{
		storeMessagesLabel.setText("");
		storeNameLabel.setText(gameModel.getCurrentIsland().getStore().getName());
		storeMoneyLabel.setText("Money: $" + Integer.toString(gameModel.getMoney()));
		int currentWeight = gameModel.getShip().getCurrentWeight();
		int maxWeight = gameModel.getShip().getMaxWeight();
		storeCapacityBar.setValue(currentWeight);
		storeCapacityBar.setMaximum(maxWeight);
		storeCapacityBar.setString("Ship Capacity: " + 
									Integer.toString(currentWeight) + " / " +
									Integer.toString(maxWeight) + "kg");

		for (Item item : gameController.getItemsList())
		{
			Store store = gameModel.getCurrentIsland().getStore();


			JLabel shopQuantity = storeShopQuantityLabels.get(item);
			JLabel playerQuantity = storePlayerQuantityLabels.get(item);
			JLabel itemWeight = storeWeightLabels.get(item);
			JLabel itemPrice = storePriceLabels.get(item);
			
			shopQuantity.setText("Shop Stock: " + Integer.toString(store.getStock().get(item)));
			playerQuantity.setText("Amount owned: " + Integer.toString(gameModel.getInventory().get(item)));
			itemWeight.setText("Weight: " + item.getWeight() + "kg");
			itemPrice.setText("Price: $" + Integer.toString(store.getPrices().get(item)));

		}
		Upgrade upgrade = gameModel.getCurrentIsland().getStore().getUpgrade();
		storeUpgradeNameLabel.setText(upgrade.getName());
		storeUpgradeCostLabel.setText("Upgrade Cost: $" + Integer.toString(upgrade.getCost()));;
		storeUpgradeTimeLabel.setText("Days to Upgrade: " + Integer.toString(upgrade.getBuildTime()));;
		storeUpgradeDescriptionTextArea.setText(upgrade.getDesciption());;
	}
	
	/**
     * Updates the gui components of the Inventory panel in the island trader game. 
     * <br>
     * This method helps the {@link #updateGame()} method by updating the inventory screen
     * in the Island Trader Game. This method does not update the inventory info panel, that is 
     * done with the other helper method {@link #updateInventoryInfo()}.
	 */
	public void updateInventory()
	{
		for (Item item : gameController.getItemsList())
		{

			// JPanel itemPanel = inventoryItemPanels.get(item);
			// inventoryDisplayPanel.remove(itemPanel);
			JLabel itemQuantityLabel = inventoryQuantityLabels.get(item);
			int quantity = gameModel.getInventory().get(item);
			itemQuantityLabel.setText("Quantity: " + Integer.toString(quantity));
		}
	}

}
