package example;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BorderFactory;
import javax.swing.JComponent;

/**
 * A Light is simple GUI component rendered as a circle of certain color.
 * This example also shows how to write your own GUI component
 *   
 * @author Hans Dulimarta
 * @version Spring 2008
 */
@SuppressWarnings("serial")
public class LED extends JComponent {
    
    private final static int LIGHT_RADIUS = 25;
    private final static int MARGIN = 5;
    
    private Color onColor;
    private Color offColor;
    private Color currColor;  /* current color */
    private boolean state;    /* current state of the light */
    
    /**
     * The constructor takes a color ...
     * @param c the desired color for the light
     */
    public LED (Color c)
    {
        state = false;
        /*
         * The following conditions are needed to make sure that on and off
         * color are different.
         */
        if (! c.darker().equals(c))
        {
            onColor = c;
            offColor = c.darker();
        }
        else if (! c.brighter().equals(c)){
            onColor = c.brighter();
            offColor = c;
        }
        else {
            onColor = c;
            /* offColor is 80% darker */
            offColor = new Color (c.getRed() * 0.8f, 
                    c.getGreen() * 0.8f, c.getBlue() * 0.8f);
        }

        setBorder (BorderFactory.createRaisedBevelBorder());
        setPreferredSize(new Dimension (2*(LIGHT_RADIUS+MARGIN), 
                2*(LIGHT_RADIUS+MARGIN)));
    }
    
    /** Turn the light on */
    public void on ()
    {
        currColor = onColor;
        state = true;
        repaint();      // inform the GUI this object needs repainting 
    }

    /** Turn the light off */
    public void off ()
    {
        currColor = offColor;
        state = false;
        repaint();
    }
    
    /** Toggle the light */
    public void toggle ()
    {
        currColor = state ? offColor : onColor;
        state ^= true; /* toggle the state */
        repaint();
    }
    
    public boolean isOn ()
    {
        return state;
    }
    
    /** The following method is needed to render the object on the GUI */
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(currColor);
        g2.fillOval(MARGIN, MARGIN, 2*LIGHT_RADIUS, 2*LIGHT_RADIUS);
        super.paintComponent(g);
    }

    public String toString ()
    {
        return "The light is " + (state ? "on" : "off");
    }
}
