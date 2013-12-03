package examples.sorting.guiDemos.animators;

import java.awt.Color;

/**
 * Animates the selection sort
 * 
 * @author Matt
 * 
 */
public class SelectionSortAnimator extends SortAnimator
{
    private int _position;
    private int _smallest;
    private int _current;

    /**
     * Constructor
     * 
     * @param lines
     */
    public SelectionSortAnimator()
    {
        _position = 0;
        _smallest = -1;
        _current = -1;
    }

    /**
     * Called when the clock ticks
     */
    @Override
    public void run()
    {
        // we're done sorting if we've made it through the entire list of lines
        if (_position == _lines.length)
            return;

        // set new smallest if we're starting a new round of searching
        if (_smallest == -1)
        {
            _smallest = _position;
            _current = _position;
            _lines[_position].setColor(Color.YELLOW);
        }

        // change the current line back to red if it's not the first line
        if (_current != _position && _current < _lines.length)
            _lines[_current].setColor(Color.RED);

        // we're at the end of the current round
        if (_current == _lines.length)
        {
            // change the current position to red and smallest position to green
            _lines[_position].setColor(Color.RED);
            _lines[_smallest].setColor(Color.GREEN);

            // swap lines
            swapLines(_position, _smallest);

            // move to next position
            ++_position;

            // grab new indexes
            _smallest = _current = -1;
        }
        // check current line against smallest line
        else if (_lines[_current].compareTo(_lines[_smallest]) < 0)
        {
            // update new smallest line to yellow and reset old one to red
            _lines[_smallest].setColor(Color.RED);
            _smallest = _current;
            _lines[_smallest].setColor(Color.YELLOW);

            // move to next comparison
            ++_current;
        }
        // default case
        else
        {
            // move to next comparison
            ++_current;

            // set current position to blue
            if (_current < _lines.length)
                _lines[_current].setColor(Color.BLUE);
        }
    }
}
