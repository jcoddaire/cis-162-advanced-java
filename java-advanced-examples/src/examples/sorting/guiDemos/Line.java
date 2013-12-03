package examples.sorting.guiDemos;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

/**
 * Draws a simple line on the GUI
 * 
 * @author gerberma
 *
 */
@SuppressWarnings({ "rawtypes", "serial" })
public class Line extends JComponent implements Comparable
{
    /**
     * Height of line
     */
    public static final int LINE_HEIGHT = 5;
    
    private Color _color;

    /**
     * Sets this line's color
     * 
     * @param color
     */
    public void setColor(Color color)
    {
        _color = color;
        
        // repaint with new color
        repaint();
    }

    /**
     * Creates a new line
     * @param color
     * @param width
     */
    public Line(Color color, int width)
    {
        _color = color;

        setSize(width, LINE_HEIGHT);
    }

    /**
     * Paints this line
     */
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        g.setColor(_color);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    /**
     * Compares two lines according to their widths
     */
    @Override
    public int compareTo(Object arg0)
    {
        return ((Integer)getWidth()).compareTo(((Line)arg0).getWidth());
    }
}
