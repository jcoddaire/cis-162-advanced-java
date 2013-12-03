package examples.sorting.guiDemos.animators;

import java.awt.Color;

/**
 * Animates the insertion sort
 * 
 * @author Matt
 * 
 */
public class InsertionSortAnimator extends SortAnimator
{
    private int _position;
    private int _insertAt;

    /**
     * Constructor
     * 
     * @param lines
     */
    public InsertionSortAnimator()
    {
        _position = 1;
        _insertAt = -1;
    }

    /**
     * Called when the clock ticks
     */
    @Override
    public void run()
    {
        // we're done sorting
        if (_position >= _lines.length)
            return;

        // first line should always be green
        if (_lines.length > 0)
            _lines[0].setColor(Color.GREEN);

        // pick up new insertion spot, which begins as the current position
        if (_insertAt == -1)
        {
            _insertAt = _position;
            _lines[_position].setColor(Color.YELLOW);
        }

        // make next comparison
        if (_insertAt >= 1 && _lines[_insertAt].compareTo(_lines[_insertAt - 1]) < 0)
        {
            // swap lines if the line is smaller than its neighbor
            swapLines(_insertAt, _insertAt - 1);
            --_insertAt;
        }
        else
        {
            // line is part of the sorted portion...set to green
            _lines[_insertAt].setColor(Color.GREEN);

            // move to next position
            ++_position;
            _insertAt = -1;
        }
    }
}