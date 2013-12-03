package examples.menuCustomLabel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

/**
 * Custom JLabel that draws a filled oval on itself. If the label is clicked, 
 * the color is changed. Awesome.
 * 
 * @author gerberma
 *
 */
@SuppressWarnings("serial")
public class CustomLabel extends JLabel implements MouseListener
{
	private Color _color;
	
	/**
	 * Constructor
	 */
    public CustomLabel()
    {
    	// initial color of the oval is black
    	_color = Color.BLACK;
    	
    	// have this label listen to itself for mouse events
        this.addMouseListener(this);
        
        // set opaque background
        this.setBackground(Color.BLUE);
        this.setOpaque(true);
    }
    
    public void paintComponent(Graphics g)
    {
        // tell the underlying JLabel object to paint itself
        super.paintComponent(g);
        
    	// set the color and fill an oval
    	g.setColor(_color);
        g.fillOval(0, 0, 100, 100);
    }

    @Override
    public void mouseClicked(MouseEvent arg0)
    {
    	// each time the label is clicked, alternate between black and red
    	if(_color.equals(Color.BLACK))
    		_color = Color.RED;
    	else
    		_color = Color.BLACK;
    	
    	/* now that the variable is updated, repaint to get the new color.
    	 * calling this will eventually call paintComponent, which we defined 
    	 * above.
    	 */
    	repaint();
    }

    /* we really don't care about the remaining methods. they just need to be 
     * here to implement the MouseListener interface. */
    
    @Override
    public void mouseEntered(MouseEvent arg0)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent arg0)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mousePressed(MouseEvent arg0)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseReleased(MouseEvent arg0)
    {
        // TODO Auto-generated method stub
        
    }
}
