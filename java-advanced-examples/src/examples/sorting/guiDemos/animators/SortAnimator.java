package examples.sorting.guiDemos.animators;

import java.awt.Point;
import java.util.TimerTask;

import examples.sorting.guiDemos.Line;

/**
 * Abstract base class for all sort animators
 * 
 * @author Matt
 * 
 */
public abstract class SortAnimator extends TimerTask
{
    /**
     * Lines that should be sorted
     */
    protected Line[] _lines;

    /**
     * Sets the lines that should be sorted
     * 
     * @param lines
     */
    public void setLines(Line[] lines)
    {
        _lines = lines;
    }

    /**
     * Swaps lines, both in the GUI as well as the underlying line collection
     * 
     * @param lineIndex1
     * @param lineIndex2
     */
    protected void swapLines(int lineIndex1, int lineIndex2)
    {
        // get lines
        Line line1 = _lines[lineIndex1];
        Line line2 = _lines[lineIndex2];

        // swap position of lines on GUI
        Point location1 = line1.getLocation();
        Point location2 = line2.getLocation();
        line1.setLocation(location2);
        line2.setLocation(location1);

        // swap position of lines in collection
        Line temp = _lines[lineIndex1];
        _lines[lineIndex1] = _lines[lineIndex2];
        _lines[lineIndex2] = temp;
    }
}
