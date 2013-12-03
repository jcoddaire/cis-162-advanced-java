package example;
import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;

import time.Clock;

/**
 * This is a simple example of how to use Clock, ClockListener to control
 * the behavior of a number of object .....
 *  
 * @author Hans Dulimarta
 * @version Winter 2008
 */
public class ClockTester {
    private JFrame top;    /* we want to show the objects on a GUI */
    private Clock klok;    /* this is the master clock */
    
    /* The following are controlled by the master clock */
    private GBlinker flasher;
    private TrafficLightView postedLight;
    private TrafficLightModel trafLiteCircuit;
    private Oscillator oscar;

    public ClockTester ()
    {
        /* create the GUI and the master clock */
        top = new JFrame();
        klok = new Clock();

        top.setTitle ("Clock Tester by Hans Dulimarta");

        /* these two objects are placed on the GUI */
        flasher = new GBlinker();
        trafLiteCircuit = new TrafficLightModel();
        postedLight = new TrafficLightView(trafLiteCircuit);
        top.add (flasher, BorderLayout.WEST);
        top.add (postedLight, BorderLayout.EAST);

        /* Put an informative message on the north side and 
         * a 50-pixel spacer on the center....
         */
        top.add (new JLabel ("A simple clock tester..."), BorderLayout.NORTH);

        top.add(Box.createHorizontalStrut(100), BorderLayout.CENTER);
        /* the following oscillator object is NOT placed on the GUI */
        oscar = new Oscillator (5);
        klok.register(flasher);
        klok.register(trafLiteCircuit);
        klok.register (oscar);
        
        top.pack();
        top.setVisible(true);
        top.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        klok.setRate(300);
        klok.run(30); // run the clock for 30 units
        // other ways of starting the master clock:
        // klok.step();    /* advance one unit */
        // klok.run (Clock.FOREVER);
    }
    
    public static void main (String[] _)
    {
        new ClockTester();
    }
}
