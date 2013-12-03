package examples.sorting.guiDemos.animators;

import java.awt.Color;

/**
 * Animates the bubble sort
 * 
 * @author gerberma
 * 
 */
public class BubbleSortAnimator extends SortAnimator
{
    private int _position;
    private int _lastSwap;
    private int _previousLastSwap;

    /**
     * Constructor
     */
    public BubbleSortAnimator()
    {
        _position = 0;
        _lastSwap = -1;
        _previousLastSwap = -1;
    }

    /**
     * Animate next operation
     */
    @Override
    public void run()
    {
        // we're done
        if (_position == -1)
            return;

        // init previous last swap to the last element
        if (_previousLastSwap == -1)
            _previousLastSwap = _lines.length - 1;

        if (_position > _previousLastSwap)
            _lines[_position].setColor(Color.GREEN);
        else
            _lines[_position].setColor(Color.RED);

        // compare line at current position to the next position
        if (_lines[_position].compareTo(_lines[_position + 1]) > 0)
        {
            // swap current with next line
            swapLines(_position, _position + 1);

            // record location of last swap
            _lastSwap = _position;
        }

        // if the next position is the end of the list, we won't be swapping so stop now
        if (++_position == _lines.length - 1)
        {
            // everything between the last swap (exclusive) and the previous last swap (inclusive) is now sorted
            for (int i = _lastSwap + 1; i <= _previousLastSwap; ++i)
                _lines[i].setColor(Color.GREEN);

            // if there was a swap in this round, record it and start over
            if (_lastSwap > -1)
            {
                _previousLastSwap = _lastSwap;
                _position = 0;
            }
            // otherwise, the sort is finished
            else
                _position = -1;

            // there is no last swap at this point
            _lastSwap = -1;
        }

        if (_position >= 0)
            _lines[_position].setColor(Color.BLUE);
    }
}
