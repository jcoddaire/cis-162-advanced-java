package examples.polymorphismViaInterfaces;

import java.awt.Graphics;

import javax.swing.JFrame;

/**
 * Main program window
 * 
 * @author Matt
 * 
 */
public class GUI extends JFrame
{
    /**
     * Main program
     * 
     * @param args
     */
    public static void main(String[] args)
    {
        // choose a shape to draw - can be anything that implements the Shape interface
        Shape s = new Circle();

        // create GUI with the given shape
        GUI gui = new GUI(s);
        gui.setSize(200, 200);
        gui.setVisible(true);
    }

    private Shape _s;

    /**
     * Constructor
     * 
     * @param s
     */
    public GUI(Shape s)
    {
        _s = s;
    }

    /**
     * Overrides the window's paint method
     */
    public void paint(Graphics g)
    {
        // paint window
        super.paint(g);

        // draw shape
        _s.draw(getContentPane().getGraphics());
    }
}
