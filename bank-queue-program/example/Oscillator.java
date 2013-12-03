package example;

import time.ClockListener;


/**
 * This class is an abstraction of an object that will update its integer
 * value between -bound and +bound (inclusive).
 * 
 * @author Hans Dulimarta
 * @version Winter 2008
 */
public class Oscillator implements ClockListener {
    private int bound;
    private int currVal;
    private int delta;
    
    public Oscillator (int limit)
    {
        bound = limit;
        currVal = 0;
        delta = +1; /* initial update direction is positive */
    }
    
    public boolean event (int tick)
    {
        System.out.printf("Oscilator value is %+3d\n", currVal);
        
        /* change update sign if we hit the upper/lower bound */
        if (Math.abs(currVal) == bound)
            delta *= -1;
        currVal += delta;
        return true;
    }
}
