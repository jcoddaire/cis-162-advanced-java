package examples.jSlider;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Demonstrates the use of JSlider
 * 
 * @author gerberma
 * 
 */
public class Program extends JFrame implements ChangeListener
{

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        new Program();
    }

    // label that display slider value
    private JLabel label;

    public Program()
    {
        setLayout(new GridLayout());

        // create slider on range 1-100 inclusive
        JSlider slider = new JSlider(1, 100);
        slider.addChangeListener(this);
        add(slider);

        // create label
        label = new JLabel("Slider value:  " + slider.getValue());
        add(label);

        pack();
        setVisible(true);
    }

    @Override
    public void stateChanged(ChangeEvent arg0)
    {
        // set label text to slider value 
        JSlider slider = (JSlider) arg0.getSource();
        label.setText("Slider value:  " + slider.getValue());
    }
}
