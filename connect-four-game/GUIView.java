import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;


/**
 * Initializes all panels, the game board
 * (technically also a panel), buttons, menuItems, and implements
 * various Mouse and ActionListeners to each component. This method
 * also calls methods that prompt to the user for game state values
 * such as board size, number of players, etc. Finally it contains
 * methods that refresh the appearances of various panels after
 * changes have been made to them, and the logic necessary for
 * displaying when someone wins the game.
 * 
 * 
 * @author Jake Pain
 * @author Jake Coddaire
 * 
 * @version Winter 2011
 *
 */
@SuppressWarnings("serial")
public class GUIView extends JPanel implements View
{
	/** contains the game function buttons (New Game, Reset, etc)*/
	private JPanel buttons;	

	/** contains the JLabel-stored names of every player and their respective
	 number of wins*/
	private JPanel names;

	/** contains the JLabel-stored value of whose turn it is*/
	private JPanel yourTurn;

	/** contains the buttons corresponding to each column on the board;
	 Clicking button 1 means you want to drop a chip into the next
	 available row in column 1;*/
	private JPanel columnButtons;

	/** Board is a JPanel with enhanced functionality such as automatic
	 grid drawing and a new add method*/
	private Board board;

	/** Buttons to select columns to drop pieces into; stored in columnButtons
	 JPanel*/
	private ArrayList<JButton> selectColumnButtons;

	/** prompts user for all variables pertaining to gameplay
	 and reinitializes game as if it just run (when run in conjunction
	 with reset()*/
	private JMenuItem newGame;

	/** prompts the user for which player will go first, board size,
	 and number of connects needed to win*/
	private JMenuItem reset;

	/** removes the last piece from the board*/
	private JMenuItem undo;

	/** exits the game*/
	private JMenuItem exit;

	/** same functionality as the JMenuItem of the same name, but
	 this is a button*/
	private JButton newGameB;

	/** same functionality as the JMenuItem of the same name, but
	this is a button*/
	private JButton resetB;

	/** same functionality as the JMenuItem of the same name, but
	this is a button*/
	private JButton undoB;

	/** same functionality as the JMenuItem of the same name, but
	this is a button*/
	private JButton exitB;

	/** temporarily stores the name of the player before its storage
	in a Player object in the playerArray */
	private String name;

	/** temporarily stores the board size as a String value pulled from a
	JOptionPane before it's passed to the Board	object */
	private String sizeSTR;

	/** temporarily stores the number of connects needed to win as a String
	value pulled from a JOptionPane */
	private String winSTR;

	/** temporarily stores the name of the player before its storage
	in a Player object in the playerArray */
	private String playerSTR;

	/** stores the number of players chosen by the user */
	private int player=0;

	/**
	 this variable stores the size of the playing board before the storage
	 of that size in the Board object(one number b/c board is a square)
	 */
	private int boardSize;

	/** stores the number of connects needed to win */
	private int connectsNeeded;

	/** the xCOUNT variables are used to control looping in for loops
	 where x is being obtained. See loops for example */
	private int playerCOUNT=1;

	/** the xCOUNT variables are used to control looping in for loops
	 where x is being obtained. See loops for example */
	private int sizeCOUNT=1;

	/** the xCOUNT variables are used to control looping in for loops
	 where x is being obtained. See loops for example */
	private int connectsNeededCOUNT=1;

	/** stores a Player object; stores the player whose chip will be laid next */
	private static Player currentPlayer;

	/** stores the position in the playerArray of the currently active
	player (the player whose chip will be laid next); an integer
	representation of currentPlayer */	
	private int currentPlayerNum;
	
	/** used to check whether or not the board has been clicked yet */
	private boolean boardClicked = false;

	/** used to prompt each player for his desired color */
	private JColorChooser playerColor;

	/** arraylist in which the details of each player (name, color, wins, etc)
	are stored in Player objects */
	private ArrayList<Player> playerArray;

	/** contains the players names and number of wins stored as JLabels */
	private ArrayList<JLabel> playerLabels;

	private Model model;


	/**
	 * Constructor for GUIView; Initializes all panels, the game board
	 * (technically also a panel), buttons, menuItems, and implements
	 * various Mouse and ActionListeners to each component. This method
	 * also calls methods that prompt to the user for game state values
	 * such as board size, number of players, etc. Finally it contains
	 * methods that refresh the appearances of various panels after
	 * changes have been made to them, and the logic necessary for
	 * displaying when someone wins the game.
	 * 
	 * 
	 * @param newGame  JMenuItem - resets the entire game
	 * @param reset    JMenuItem - resets who goes first, board size,
	 *      number of connects needed to win
	 * @param undo     JMenuItem - removes the last played chip from the board 
	 * @param exit     JMenuItem - exits the game
	 */
	public GUIView (JMenuItem newGame, JMenuItem reset, JMenuItem undo, JMenuItem exit)
	{
		this.newGame = newGame;
		this.reset = reset;
		this.undo = undo;
		this.exit = exit;

		// contains labels w/ players' names and wins
		names = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 0));
		names.setPreferredSize(new Dimension(500, 20));

		// Panel containing the "Player X, it's your turn" JLabel
		yourTurn = new JPanel();

		// panel containing buttons used to select columns to drop pieces into
		columnButtons = new JPanel(new GridLayout(1, 0));

		selectColumnButtons = new ArrayList<JButton>(25);
		playerArray = new ArrayList<Player>(4);
		playerLabels = new ArrayList<JLabel>(4);

		// this listener starts the game when the board is clicked
		BoardListener bListen = new BoardListener();
		// panel on which the game board will be drawn
		board = new Board();
		board.setLayout(new BorderLayout());
		board.setPreferredSize(new Dimension(500, 500));
		board.setBackground(Color.gray);
		board.addMouseListener(bListen);
		
		// starts the game
		startGame();
		
		buttons = new JPanel();
		buttons.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 0));

		Border buttonsBorderSpace = BorderFactory.createEmptyBorder(0, 0, 5, 0);
		Border buttonsBorderEtch =
			BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		Border compButtonsBorder =
			BorderFactory.createCompoundBorder(buttonsBorderSpace, buttonsBorderEtch);
		buttons.setBorder(compButtonsBorder);

		newGameB = new JButton ("New Game");
		resetB = new JButton ("Reset");
		undoB = new JButton ("Undo");
		exitB = new JButton ("Exit");

		undoB.setEnabled(board.getCanUndo());

		buttons.add(newGameB);
		buttons.add(resetB);
		buttons.add(undoB);
		buttons.add(exitB);

		MenuListener mListen = new MenuListener();
		newGameB.addActionListener(mListen);
		resetB.addActionListener(mListen);
		undoB.addActionListener(mListen);
		exitB.addActionListener(mListen);

		JLabel welcome = new JLabel ("Welcome to Connect-4! Click the board to start.");
		welcome.setHorizontalTextPosition(SwingConstants.CENTER);
		welcome.setHorizontalAlignment(SwingConstants.CENTER);

		board.add(welcome);

		newGame.addActionListener(mListen);
		reset.addActionListener(mListen);
		undo.addActionListener(mListen);
		exit.addActionListener(mListen);


		setLayout (new BoxLayout(this, BoxLayout.Y_AXIS));

		add(buttons);
		add(names);
		add(yourTurn);
		add(columnButtons);
		add(board);
	}
	
	
	/**
	 * Begins the game
	 * 
	 */
	public void startGame()
	{
		// prompts for number of players, names and chip color for each player
		input();
		
		// prompts for which player will go first, board size, and number of
		// consecutive pieces needed to win
		reset();
		
		// redraws names panel to apply changes to number and names of players
		refreshNames();
		
		// redraws columnButtons panel to reflect changes in board size 
		refreshColumnButtons();
	}
	

	/**
	 * This portion of the program determines the amount of players
	 * and the Names of each player, then stores the data in String[]
	 * playerArray
	 */
	public void input()
	{
		//prompts the user for the number of players and checks that it is a
		// legit amount
		playerSTR = JOptionPane.showInputDialog("How many players? (2-4)");
		for (int i = 0; i < playerCOUNT; i++)
		{
			try
			{
				player = Integer.parseInt(playerSTR);
				if (player>4 || player<2)
				{
					// create a loop that runs if (player>4 || player<2).
					//This prevents the player from entering in strange numbers....
					do
					{
						playerSTR = JOptionPane.showInputDialog("Quit being stupid. How many players? (2-4)");
						player = Integer.parseInt(playerSTR);
					}
					while (player>4 || player<2);
				}
			}
			catch (NumberFormatException e)
			{
				playerSTR = JOptionPane.showInputDialog("Only numbers are permitted. How many players? (2-4)");
				playerCOUNT++;
			}
		}

		//prompts the user to input a name, and it doesn't exceed the amount of players.
		playerArray = new ArrayList<Player>();
		playerLabels = new ArrayList<JLabel>();
		LabelListener labelListen = new LabelListener();
		for (int i=0; i<player; i++)
		{
			playerColor = new JColorChooser();
			name = JOptionPane.showInputDialog("Enter a name:");
			JOptionPane.showMessageDialog(null, playerColor);	
			playerArray.add(new Player(playerColor.getColor(), name));	
			playerLabels.add(new JLabel(name +" Wins: "+ playerArray.get(i).getNumWins()));
			playerLabels.get(i).addMouseListener(labelListen);
			playerLabels.get(i).setForeground(playerColor.getColor());
		}
	}


	//-------------------------------------------------------------------------------------------------------------------------------------------------------
	// This portion of the program determines the first player, board size, and how many connects are needed to win.
	//-------------------------------------------------------------------------------------------------------------------------------------------------------
	public void reset()
	{
		// JOptionPanes need arrays, not arraylists, for their parameters.
		// this loop pulls players names from their objects and stores
		// them in String array to enable the JOptionPane to cycle through
		// all of the players
		String [] playerNames = new String[playerArray.size()];

		for (int i = 0; i < playerArray.size(); i++)
		{
			playerNames[i] = playerArray.get(i).getName();
		}

		// prompts the user for the person who will go first
		String selectedPlayerName = (String) JOptionPane.showInputDialog(null,  
				"Which player will play first?", "Player Selection",  
				JOptionPane.PLAIN_MESSAGE, null,  
				playerNames, playerNames[0]);

		// figures out a position in the player array based on the string 
		// representation of which player was chosen to go first
		for (int i = 0; i < playerArray.size(); ++i)
		{
			if (playerArray.get(i).getName().equals(selectedPlayerName))
			{
				currentPlayer = playerArray.get(i);
				currentPlayerNum = i;
			}
		}


		// prompts the user for board size. Checks the legit size.
		sizeSTR = JOptionPane.showInputDialog("Enter the board size. (10-25)");
		for (int b=0; b<sizeCOUNT; b++)
		{
			try
			{
				boardSize = Integer.parseInt(sizeSTR);
				if (boardSize<10 || boardSize>25)
				{
					do
					{
						sizeSTR = JOptionPane.showInputDialog("Incorrect value. Enter the board size. (10-25)");
						boardSize = Integer.parseInt(sizeSTR);
					}
					while (boardSize<10 || boardSize>25);
				}
			}
			catch(NumberFormatException e)
			{
				sizeSTR = JOptionPane.showInputDialog("Only numbers are permitted. Enter the board size(10-25)");
				sizeCOUNT++;
			}
		}

		// passes the board size to the board object
		board.setBoardSize(boardSize);


		// prompts the user for number of connects needed to win. Checks for a legit number.
		winSTR = JOptionPane.showInputDialog("Enter the number of connects needed to win. (4-8)");
		for (int a=0; a<connectsNeededCOUNT; a++)
		{
			try
			{
				connectsNeeded = Integer.parseInt(winSTR);
				if (connectsNeeded>8 || connectsNeeded<4)
				{
					do
					{
						winSTR = JOptionPane.showInputDialog("Incorrect value. Enter the number of connects needed to win. (4-8)");
						connectsNeeded = Integer.parseInt(winSTR);
					}
					while (connectsNeeded>8 || connectsNeeded<4);
				}
			}
			catch(NumberFormatException e)
			{
				winSTR = JOptionPane.showInputDialog("Only numbers are permitted. Enter the number of connects needed to win. (4-8)");
				connectsNeededCOUNT++;
			}
		}
	}


	/**
	 * Redraws all of the column selection buttons.
	 * Changes the number of buttons relative to number of columns
	 */
	public void refreshColumnButtons()
	{
		ColumnButtonListener colButtonListener = new ColumnButtonListener();
		columnButtons.removeAll();
		for (int i = 0; i < boardSize; i++)
		{
			selectColumnButtons.add(i, new JButton( (i+1) + "" ));
			selectColumnButtons.get(i).addActionListener(colButtonListener);
			columnButtons.add(selectColumnButtons.get(i));
		}

		columnButtons.repaint();
		columnButtons.revalidate();
	}


	/**
	 * this adds all of the player name JLabels to the names panel from an ArrayList
	 * used to support variable numbers of players w/o displaying empty JLabels
	 */
	public void refreshNames()
	{
		// removes all old labels from the panels
		names.removeAll();
		yourTurn.removeAll();

		// adds 2-4 JLabels to the panel depending on the number of players
		for (int i = 0; i < playerLabels.size(); i++) {
			playerLabels.get(i).setText(playerArray.get(i).getName()
					+ " Wins: " + playerArray.get(i).getNumWins());
			names.add(playerLabels.get(i));
		}

		// figure out whose turn it is
		JLabel currentPlayerLabel = new JLabel (currentPlayer.getName() + ", it's your turn.");
		yourTurn.add(currentPlayerLabel);

		// center the 
		currentPlayerLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		currentPlayerLabel.setHorizontalAlignment(SwingConstants.CENTER);

		// redraw the panels
		names.repaint();
		names.revalidate();
		yourTurn.repaint();
		yourTurn.revalidate();
	}
	

	/**
	 * @return currentPlayer   the player whose chip will be laid next
	 */
	public static Player getCurrentPlayer() {
		return currentPlayer;
	}


	/**
	 * @return the playerArray
	 */
	public ArrayList<Player> getPlayerArray() {
		return playerArray;
	}

	/**
	 * @return the connectsNeeded
	 */
	public int getConnectsNeeded() {
		return connectsNeeded;
	}


	/**
	 * @param currentPlayerNum the currentPlayerNum to set
	 */
	public void setCurrentPlayerNum(int currentPlayerNum) {
		this.currentPlayerNum = currentPlayerNum;
	}

	/**
	 * @return the currentPlayerNum
	 */
	public int getCurrentPlayerNum() {
		return currentPlayerNum;
	}

	
	/**
	 * @return the selectColumnButtons
	 */
	public ArrayList<JButton> getColumnButtons()
	{
		return selectColumnButtons;
	}

	/**
	 * Applies a player's newly chosen color to all chips laid
	 * 
	 * @param player
	 */
	public void refreshColors(Player player)
	{
		// scroll through rows...
		for (int row = 0; row < boardSize; ++row)
		{
			// scroll through columns...
			for (int col = 0; col < boardSize; ++col)
			{
				// if the chip at this spot was laid by the passed player
				// (the one who just changed his color), then change the chip's color
				if (board.getLabelArray()[row][col].getPlayer() == player)
				{
					// change the chip's color
					board.getLabelArray()[row][col].setColor(player.getChipColor());

					// redraw the board to apply color changes
					board.repaint();
					board.revalidate();
				}
			}
		}
	}
	
	public void getBoardSize(int boardSize)
	{
		this.boardSize= boardSize;
	}
	
	/**
	 * Enables or disables the undo button based on the parameter passed to it
	 * 
	 * @param x     true enables the undo button; false disables it
	 */
	public void undoCheck()
	{
		System.out.println("Undo Toggle: Can Undo = " + board.getCanUndo());
		undoB.setEnabled(board.getCanUndo());
	}


	// Listens for both menu and button actions since they both do the same things
	private class BoardListener implements MouseListener
	{

		@Override
		public void mouseClicked(MouseEvent arg0) {

			// Roundabout way of checking to see if the board layout
			// has already been changed to grid layout, implying that 
			// it has already been clicked and that a game is in progress 
			if (!boardClicked)
			{
				board.removeAll();

				board.drawGrid();
				boardClicked = true;
				model.setBoard(board);
			}
			
			// if there is already a game in progress...
			else
			{
				// do nothing
			}
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {

		}

		@Override
		public void mouseExited(MouseEvent arg0) {

		}

		@Override
		public void mousePressed(MouseEvent arg0) {

		}

		@Override
		public void mouseReleased(MouseEvent arg0)
		{

		}

	}


	// Listens for both menu and button actions since they both do the same things
	private class MenuListener implements ActionListener
	{
		public void actionPerformed (ActionEvent event)
		{
			// if "New Game" was selected (menu) or pushed (button)...
			if (event.getSource() == newGame || event.getSource() == newGameB)
			{	
				input();
				reset();
				refreshNames();
				refreshColumnButtons();
				board.drawGrid();
			}

			// if "Reset" was selected (menu) or pushed (button)...
			if (event.getSource() == reset || event.getSource() == resetB)
			{
				reset();
				refreshNames();
				enableColumnButtons();
				board.drawGrid();
			}

			// if "Undo" was selected (menu) or pushed (button)...
			if (event.getSource() == undo || event.getSource() == undoB)
			{
				board.undo();
				refreshColumnButtons();
				undoCheck();
				refreshNames();
			}

			// if "Exit" was selected (menu) or pushed (button)...
			if (event.getSource() == exit || event.getSource() == exitB)
			{
				System.exit(0);
			}
		}
	}



	/**
	 * Handles clicks on the buttons used to select columns
	 * to drop chips into.
	 */
	private class ColumnButtonListener implements ActionListener
	{
		/**
		 * Handles clicks on the buttons used to select columns
		 * to drop chips into.
		 * 
		 * @param event    event generated by a user click
		 */
		public void actionPerformed (ActionEvent event)
		{
			for (int  i = 0; i < board.getBoardSize(); i++)
			{
				// decides which column button was clicked, & if there is
				// still space left in the column, adds a circle
				if (event.getSource() == selectColumnButtons.get(i) &&
						board.getRowsLeft(i) > 0)
				{
					board.add(board.getRowsLeft(i)-1, i, currentPlayer);
					board.setCanUndo(true);
					undoCheck();
				}
				
				// disables the column button if the column is full
				if ( !(board.getRowsLeft(i) > 0) )
				{
					selectColumnButtons.get(i).setEnabled(false);
				}
			}

			if (model.isWinner() == true)
			{
				JOptionPane.showMessageDialog( null, currentPlayer.getName() + " is the winner!");
				currentPlayer.setNumWins(currentPlayer.getNumWins()+1);
				refreshNames();
				refreshColumnButtons();
				board.drawGrid();
			}
			
			// loops through players, conditions decide whether to keep going
			// up (the if) or go back to beginning (the else)
			if ((currentPlayerNum+1) < player && (currentPlayerNum+1) > 0)
			{
				currentPlayer = playerArray.get(++currentPlayerNum);
			}
			else
			{
				currentPlayer = playerArray.get(0);
				currentPlayerNum = 0;
			}
			
			refreshNames();

		}
	}


	private class LabelListener implements MouseListener
	{

		@Override
		public void mouseClicked(MouseEvent event) {
			for (int i = 0; i < playerLabels.size(); ++i)
			{
				if (event.getSource() == playerLabels.get(i))
				{
					playerColor = new JColorChooser();
					JOptionPane.showMessageDialog(null, playerColor);
					playerLabels.get(i).setForeground(playerColor.getColor());
					playerArray.get(i).setChipColor(playerColor.getColor());

					refreshColors(playerArray.get(i));
				}
			}
		}


		@Override
		public void mouseEntered(MouseEvent e)
		{

		}

		@Override
		public void mouseExited(MouseEvent e)
		{

		}

		@Override
		public void mousePressed(MouseEvent e)
		{

		}

		@Override
		public void mouseReleased(MouseEvent e) {

		}

	}


	@Override
	public void setModel(Model model) {
		this.model = model;

	}
	

	public void enableColumnButtons() {
		for (int i = 0; i < board.getBoardSize(); ++i)
		{
			if (board.getRowsLeft(i) > 0)
			{
				selectColumnButtons.get(i).setEnabled(true);
			}
		}
		
	}


	@Override
	public void gameOver() {

	}
}