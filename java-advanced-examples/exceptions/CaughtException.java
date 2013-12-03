package examples.exceptions;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Catching an exception
 * 
 * @author Matt
 * 
 */
public class CaughtException
{
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        try
        {
            System.out.print("Enter a number:  ");
            int x = scanner.nextInt();

            System.out.print("Enter a number:  ");
            int y = scanner.nextInt();

            System.out.println("x / y = " + (x / y));

            int[] numbers = new int[x];
            numbers[y] = 5;
        }
        catch (InputMismatchException e)
        {
            System.out.println("Failed to get a valid integer from the user");
        }
        catch (ArithmeticException e)
        {
            System.out.println("Failed to perform the requested arithmetic operation");
        }
    }
}
