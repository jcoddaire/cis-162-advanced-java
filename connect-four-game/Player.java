import java.awt.Color;

/**
 * 
 * This class stores data on each player, specifically:
 * 1. Name
 * 2. Chip color
 * 3. Number of Wins
 * 4. Turn order
 * 
 * 
 * @author Jake Pain
 * @version Winter 2011
 *
 */
public class Player {
	private int numWins;
	private Color chipColor;
	private String name;
	public Player (Color chipColor, String name)
	{
		this.chipColor = chipColor;
		this.name = name;
		
	}
	
	
	/**
	 * @param numWins the numWins to set
	 */
	public void setNumWins(int numWins) {
		this.numWins = numWins;
	}
	
	
	/**
	 * @return the numWins
	 */
	public int getNumWins() {
		return numWins;
	}
	
	
	/**
	 * @param chipColor the chipColor to set
	 */
	public void setChipColor(Color chipColor) {
		this.chipColor = chipColor;
	}
	
	
	/**
	 * @return the chipColor
	 */
	public Color getChipColor() {
		return chipColor;
	}
	
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
}
