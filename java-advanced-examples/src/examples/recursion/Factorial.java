package examples.recursion;

import java.util.Scanner;

/**
 * Demonstration of n! using recursion and iteration
 * 
 * @author gerberma
 * 
 */
public class Factorial
{
    public static void main(String[] args) throws Exception
    {
        System.out.print("Enter a value for n:  ");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        System.out.println("Recursive n!:  " + factorialRecursive(n));

        System.out.println("Iterative n!:  " + factorialIterative(n));
    }

    public static int factorialRecursive(int n) throws Exception
    {
        if (n < 0)
            throw new Exception("n must be >= 0");

        if (n == 0 || n == 1)
            return 1;

        return n * factorialRecursive(n - 1);
    }

    public static int factorialIterative(int n) throws Exception
    {
        if (n < 0)
            throw new Exception("n must be >= 0");

        if (n == 0 || n == 1)
            return 1;

        int result = 1;
        for (int i = 2; i <= n; ++i)
            result = result * i;

        return result;
    }
}
