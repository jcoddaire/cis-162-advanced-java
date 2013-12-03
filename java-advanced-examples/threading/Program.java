package examples.threading;

import examples.recursion.Fibonacci;

public class Program
{

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception
    {
        // NOTE:  the following lines are commented out because they are infinite loops - uncomment them to see how they behave

        //while(true){} // this line will use 100% of a single CPU core. it can never use more than that.

        // allocate two separate threads...this will use 100% of two CPU cores.
        /*
         * Thread t1 = new Thread(new WhileLoopRunner());
         * t1.start();
         * Thread t2 = new Thread(new WhileLoopRunner());
         * t2.start();
         */

        // compute fibonacci using one thread
        long start = System.currentTimeMillis();
        int fib = Fibonacci.fibonacciRecursive(45);
        long end = System.currentTimeMillis();

        System.out.println("Fibonacci (one thread):  " + fib + " in " + ((end - start) / 1000F) + " seconds");

        // compute fibonacci using one thread
        start = System.currentTimeMillis();
        fib = fibonacciTwoThreads(45);
        end = System.currentTimeMillis();

        System.out.println("Fibonacci (two threads):  " + fib + " in " + ((end - start) / 1000F) + " seconds");
    }

    /**
     * Computes fibonacci number using two threads
     * 
     * @param n
     * @return
     * @throws InterruptedException
     */
    public static int fibonacciTwoThreads(int n) throws InterruptedException
    {
        // compute fib(n - 1)
        FibonacciRunner fibRunner1 = new FibonacciRunner(n - 1);
        Thread t1 = new Thread(fibRunner1);
        t1.start();

        // compute fib(n - 2)
        FibonacciRunner fibRunner2 = new FibonacciRunner(n - 2);
        Thread t2 = new Thread(fibRunner2);
        t2.start();

        // wait for threads to finish running
        t1.join();
        t2.join();

        // get final result by summing the result of each thread
        int result = fibRunner1.getResult() + fibRunner2.getResult();

        return result;
    }
}
