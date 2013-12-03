/**
 * This program is designed to run a connect 4 game that uses a GUI and mouse.
 * 
 * @author Jacob Payne
 * @author Jake Coddaire
 * CIS 163- 07
 * @version Winter 2011
 *
 */
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Cnct4Driver extends JPanel
{
	private static JMenuBar gameMenuBar;
	private static JMenu gameMenu;
	private static JMenuItem newGame;
	private static JMenuItem undo;
	private static JMenuItem exit;
	private static JMenuItem reset;



	/**
	 * This portion of the program sets up the main frame for the GUI and creates the Menu bar
	 * 
	 * 	@param args  command line arguments
	 */
	public static void main(String[] args)
	{
		JFrame frame = new JFrame ("Connect 4");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		gameMenuBar = new JMenuBar();		

		gameMenu = new JMenu ("Game");

		gameMenuBar.add(gameMenu);

		newGame = new JMenuItem("New Game");
		reset = new JMenuItem("Reset");
		undo = new JMenuItem("Undo");
		exit = new JMenuItem("Exit");


		Model model = new Model();
		GUIView view = new GUIView(newGame, reset, undo, exit);

		model.setView(view);
		view.setModel(model);


		gameMenu.add(newGame);
		gameMenu.add(reset);
		gameMenu.add(undo);
		gameMenu.add(exit);

		// passes the Game menu items as parameters to GUI 

		// this sets the created menu bar as the default 
		frame.setJMenuBar(gameMenuBar);
		frame.getContentPane().add((Component) view);
		frame.pack();
		frame.setVisible(true);
	}
}

