package example;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;


/**
 * This class shows an example of how to control a traffic light as a finite
 * state machine. TrafficLightView is the visual appearance of the traffic light
 * The actual "control logic" is implemented in TrafficLightModel.
 * 
 * @author Hans Dulimarta
 * @version Winter 2011
 */

@SuppressWarnings("serial")
public class TrafficLightView extends JPanel implements Observer {
    /* We also can use a HashMap to store these LED....
     * 
     *  HashMap<Color,LED> lights;
     *  
     */
    private LED redLight, yellowLight, greenLight;
    
    public TrafficLightView (Observable a_model)
    {
        /* include this object as another obsever to the model */
        a_model.addObserver(this);
        
        /* create the three light object */
        redLight = new LED (Color.RED);
        yellowLight = new LED (Color.YELLOW);
        greenLight = new LED (Color.GREEN);
        
        /* arrange them in a single column grid */
        setLayout (new GridLayout (0, 1));
        add (redLight);
        add (yellowLight);
        add (greenLight);
        
        setBorder(BorderFactory.createLineBorder(Color.black));

        redLight.on();     /* initially the red light is on */
        yellowLight.off();
        greenLight.off();
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        Color newCol = (Color) arg1;
        redLight.off();
        yellowLight.off();
        greenLight.off();
        if (newCol.equals(Color.RED))
            redLight.on();
        else if (newCol.equals(Color.GREEN))
            greenLight.on();
        else yellowLight.on();
    }
}
