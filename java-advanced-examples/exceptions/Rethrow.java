package examples.exceptions;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

import javax.swing.JOptionPane;

/**
 * Demonstrates the re-throwing of exceptions. The example is inspired by the
 * serialization/deserialization performed in Project 2.
 * 
 * @author Matt
 * 
 */
public class Rethrow
{

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args)
    {
        try
        {
            // attempt to load the database
            loadDatabase("some_path.bin");
        }
        catch (Exception e)
        {
            // if the load fails for some reason, tell the user about it
            JOptionPane.showMessageDialog(null, "That did not work:  " + e);
        }
    }

    /**
     * Loads (deserializes) an object from a file
     * 
     * @param filename
     *        name of the file to load from.
     * @throws Exception
     */
    private static void loadDatabase(String filename) throws Exception
    {
        FileInputStream file = null;
        try
        {
            // open file - this might fail
            file = new FileInputStream(filename);

            // deserialize object - this might also fail
            ObjectInputStream objectReader = new ObjectInputStream(file);
            Object o = objectReader.readObject();
        }
        catch (Exception ex)
        {
            /*
             * if anything goes wrong, raise an exception to yell at the calling method (main, in this case).
             * we don't need to create a new exception because we can just re-throw the existing one.
             */
            throw ex;
        }
        finally
        {
            // always, always, always close open files...when might file be null?
            if (file != null)
                file.close();
        }
    }
}
