package example;

import java.awt.Color;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import time.ClockListener;

/**
 * This class is analogous to the "control circuit" of a traffic light
 * 
 * @author Hans Dulimarta
 * @version Winter 2011
 */
public class TrafficLightModel extends Observable implements ClockListener {
    /* number of seconds for each light */
    private static HashMap<Color, Integer> duration;
    
    static {
        duration = new HashMap<Color, Integer>();
        duration.put(Color.RED, 5); /* number of clock ticks for each color */
        duration.put(Color.YELLOW, 1);
        duration.put(Color.GREEN, 9);
    }    
    
    private Color state;
    private int whenToSwitch; // a future time of when to switch color

    public TrafficLightModel() {
        /* set the initial color to yellow for 1 clock tick, so the first 
         * visible color is RED */
        state = Color.YELLOW;
        whenToSwitch = 1;
    }
    
    /* This method is automatically invoked by a master clock */
    public boolean event (int c)
    {
        whenToSwitch--;
        if (whenToSwitch <= 0)
        {
            if (state.equals(Color.RED)) {        // Red ==> Green
                state = Color.GREEN;
            }
            else if (state.equals(Color.GREEN)) { // Green ==> Yellow
                state = Color.YELLOW;
            }
            else {                                // Yellow ==> Red
                state = Color.RED;
            }
            whenToSwitch = duration.get(state);
            
            /* inform the view to update */
            setChanged();
            notifyObservers(state);
            
            return true;
        }
        else
            return false;
    }


}
