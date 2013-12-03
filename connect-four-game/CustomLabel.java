import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JLabel;


/**
 * This class defines a customized JLabel. This customization changes the JLabel
 * from text to a filled circle and adds color and player 
 * 
 * @author Jake Pain
 *
 */
@SuppressWarnings("serial")
public class CustomLabel extends JLabel
{
	private Color color;
	private Player player;

	public CustomLabel(Color color)
	{
		this.color = color;
	}


	public void paintComponent(Graphics g)
	{
		g.setColor(color);
		g.fillOval(0, 0, 20, 20);
	}

	public void setColor(Color color)
	{
		this.color = color;
		repaint();
	}

	public Color getColor()
	{
		return color;
	}


	/**
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}


	/**
	 * @param player the player to set
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}
}
