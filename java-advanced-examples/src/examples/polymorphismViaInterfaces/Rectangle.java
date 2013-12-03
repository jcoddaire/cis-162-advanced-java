package examples.polymorphismViaInterfaces;

import java.awt.Graphics;

/**
 * Draws a simple circle
 * 
 * @author Matt
 *
 */
public class Rectangle implements Shape
{
    /**
     * Draws a simple circle
     */
    @Override
    public void draw(Graphics g)
    {
        g.drawRect(0, 0, 50, 50);
    }
}
