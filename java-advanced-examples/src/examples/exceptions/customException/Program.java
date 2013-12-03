package examples.exceptions.customException;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Demonstrates the use of a custom exception class
 * 
 * @author Matt
 * 
 */
public class Program
{
    private static ArrayList<Integer> numbers;

    /**
     * @param args
     * @throws DuplicateNumberException
     */
    public static void main(String[] args)
    {
        numbers = new ArrayList<Integer>();

        try
        {
            addNumber(1);
            addNumber(1);
        }
        catch (DuplicateNumberException e)
        {
            System.out.println("Error:  " + e);
        }
    }

    /**
     * Adds a number to the collection
     * 
     * @param i
     * @throws DuplicateNumberException
     */
    private static void addNumber(int i) throws DuplicateNumberException
    {
        if (numbers.contains(i))
            throw new DuplicateNumberException();

        numbers.add(i);
    }
}
