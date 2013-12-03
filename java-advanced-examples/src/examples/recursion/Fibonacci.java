package examples.recursion;

import java.util.Scanner;

/**
 * Demonstration of the Fibonacci sequence using recursion and iteration
 * 
 * @author gerberma
 * 
 */
public class Fibonacci
{
    public static void main(String[] args) throws Exception
    {
        System.out.print("Which Fibonacci number would you like? (zero is the first one):  ");
        Scanner scanner = new Scanner(System.in);
        int fibNumber = scanner.nextInt();

        System.out.println("Recursive:  " + fibonacciRecursive(fibNumber));

        System.out.println("Iterative:  " + fibonacciIterative(fibNumber));
    }

    public static int fibonacciRecursive(int n)
    {
        if (n <= 1)
            return n;

        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }

    public static int fibonacciIterative(int n)
    {
        if (n == 0 || n == 1)
            return n;

        int previous = 0;
        int current = 1;
        for (int i = 2; i <= n; ++i)
        {
            int next = previous + current;

            previous = current;
            current = next;
        }

        return current;
    }
}
