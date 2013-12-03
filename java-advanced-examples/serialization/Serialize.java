package examples.serialization;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Binary object serialization demo
 * 
 * @author Matt
 * 
 */
public class Serialize
{
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        // create some accounts
        ArrayList<CheckingAccount> accounts = new ArrayList<CheckingAccount>();
        accounts.add(new CheckingAccount("Joe", "Smith"));
        accounts.add(new CheckingAccount("Matt", "Gerber"));

        final String path = "binary_object_file.bin";

        // save accounts to disk
        try
        {
            FileOutputStream file = new FileOutputStream(path);
            ObjectOutputStream objectWriter = new ObjectOutputStream(file);
            objectWriter.writeObject(accounts);
            objectWriter.close();
            System.out.println("Saved!");
        }
        catch (Exception e)
        {
            System.out.println("Something went wrong when saving");
        }
    }
}
