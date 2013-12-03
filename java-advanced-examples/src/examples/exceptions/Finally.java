package examples.exceptions;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

/**
 * Demonstration of the finally block for resource management
 * 
 * @author Matt
 * 
 */
public class Finally
{
    /**
     * @param args
     * @throws Exception
     * @throws IOException
     */
    public static void main(String[] args)
    {
        try
        {
            System.out.println(1 / 0);
        }
        catch (ArithmeticException e)
        {
            System.out.println("Bad arithmetic" + e);
        }
        finally
        {
            System.out.println("In finally");
        }

        System.out.println("Finished");
    }

    @SuppressWarnings("unused")
	private static void serialize() throws IOException
    {
        FileOutputStream file = null;
        try
        {
            file = new FileOutputStream("test.bin");
            ObjectOutputStream objectWriter = new ObjectOutputStream(file);
            objectWriter.writeObject(1);
        }
        catch (Exception e)
        {
            System.out.println("Threw an exception");
        }
        finally
        {
            if (file != null)
                file.close();
        }

        // pause before quitting
        new Scanner(System.in).next();
    }
}
