package examples.polymorphicReferences;

import java.util.Scanner;

/**
 * Simple program demonstrating polymorphic references and late binding
 * 
 * @author Matt
 * 
 */
public class Program
{
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        String str = "Hello world!";
        int i = 5;

        // assign to obj based on what the user inputs
        Object obj;
        Scanner scan = new Scanner(System.in);
        if (scan.next().equals("str"))
            obj = str;
        else
            obj = i;

        /* the compiler will _not_ know exactly what code to execute at compile time - this is determined
         * at runtime by the user. this is also known as late binding. 
         */        
        System.out.println(obj.toString());
    }
}
