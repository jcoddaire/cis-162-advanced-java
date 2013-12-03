package examples.threading;

import examples.recursion.Fibonacci;

/**
 * Runs the Fibonacci sequence
 * 
 * @author gerberma
 * 
 */
public class FibonacciRunner implements Runnable
{
    private int _n;
    private int _result;

    /**
     * Gets the result of the computation
     * 
     * @return
     */
    public int getResult()
    {
        return _result;
    }

    /**
     * Constructor
     * 
     * @param n
     */
    public FibonacciRunner(int n)
    {
        _n = n;
    }

    /**
     * Runs the computation
     */
    public void run()
    {
        _result = Fibonacci.fibonacciRecursive(_n);
    }
}
