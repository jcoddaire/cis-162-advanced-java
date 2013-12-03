package example;

import java.awt.Color;

import javax.swing.JPanel;

import time.ClockListener;

/**
 * A simple GUI to show a blinking light that toggles at every clock
 * tick.
 * 
 * @author Hans Dulimarta
 * @version Winter 2008
 */
@SuppressWarnings("serial")
public class GBlinker extends JPanel implements ClockListener {
    
    private LED lumi;
    
    /* Create a panel that contains the light */
    public GBlinker ()
    {
        /* create a blue light */
        lumi = new LED (Color.BLUE);
        add(lumi);                 /* place it on the panel */
    }
    
    /* This method is automatically invoked by a master clock */
    public boolean event (int tick)
    {
        lumi.toggle();
        return true;
    }
}
