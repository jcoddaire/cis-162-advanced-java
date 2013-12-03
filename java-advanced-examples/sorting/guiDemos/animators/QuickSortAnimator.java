package examples.sorting.guiDemos.animators;

import java.awt.Color;
import java.util.Stack;

import examples.sorting.guiDemos.Line;

/**
 * Animates the quick sort algorithm
 * 
 * @author Matt
 * 
 */
public class QuickSortAnimator extends SortAnimator
{
    private Stack<int[]> _rangeStack;
    private int _min;
    private int _max;
    private int _pivotIndex;
    private Line _pivotLine;
    private int _insertAt;
    private int _i;
    private boolean _stopIfStackEmpty;

    /**
     * Constructor
     */
    public QuickSortAnimator()
    {
        _rangeStack = new Stack<int[]>();
        _min = _max = _pivotIndex = _insertAt = _i = -1;
        _pivotLine = null;
        _stopIfStackEmpty = false;
    }

    @Override
    public void run()
    {
        // try to get new range if we don't have one
        if (_min == -1)
        {
            // if stack is empty, we might be at the first iteration
            if (_rangeStack.size() == 0)
            {
                if (_stopIfStackEmpty)
                    return;

                // sort entire range
                _min = 0;
                _max = _lines.length - 1;
            }
            else
            {
                // pop new range off stack
                int[] minMax = _rangeStack.pop();
                _min = minMax[0];
                _max = minMax[1];

                // range of 1 or fewer is already sorted
                if (_max <= _min)
                {
                    // mark line as being in correct position
                    if (_min == _max)
                        _lines[_min].setColor(Color.GREEN);

                    // reset variables and quit
                    _min = _max = _pivotIndex = _insertAt = _i = -1;
                    _pivotLine = null;
                    return;
                }
            }
        }

        // grab new pivot
        if (_pivotIndex == -1)
        {
            _pivotIndex = _min;
            _pivotLine = _lines[_pivotIndex];
            _insertAt = _min;
            _lines[_insertAt].setColor(Color.BLUE);
            _i = _min;

            // move pivot to end and mark
            swapLines(_pivotIndex, _max);
            _lines[_max].setColor(Color.YELLOW);

            return;
        }

        // scanning through range (partition function)
        if (_i <= _max - 1)
        {
            _lines[_i].setColor(Color.RED);

            // check current against pivot line
            if (_lines[_i].compareTo(_pivotLine) <= 0)
            {
                // change insertion point to red and swap lines
                _lines[_insertAt].setColor(Color.RED);

                swapLines(_i, _insertAt);

                // mark updated insertion point
                ++_insertAt;
                _lines[_insertAt].setColor(Color.BLUE);
            }

            // mark updated location
            ++_i;
            _lines[_i].setColor(Color.CYAN);
        }
        else
        {
            // we're done with the partition function, remove colors
            _lines[_i].setColor(Color.RED);
            _lines[_insertAt].setColor(Color.RED);

            // move pivot item into place and mark
            swapLines(_insertAt, _max);
            _lines[_insertAt].setColor(Color.GREEN);

            // sort the bottom half next, followed by top half
            _rangeStack.push(new int[] { _insertAt + 1, _max });
            _rangeStack.push(new int[] { _min, _insertAt - 1 });

            // reset variables
            _min = _max = _pivotIndex = _insertAt = _i = -1;
            _pivotLine = null;

            // if the stack now becomes empty, we're done
            _stopIfStackEmpty = true;
        }
    }
}
