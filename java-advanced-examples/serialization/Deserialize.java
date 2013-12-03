package examples.serialization;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/**
 * Binary object deserialization demo
 * @author gerberma
 *
 */
public class Deserialize
{
    @SuppressWarnings("unchecked")
    public static void main(String[] args)
    {
        // list of accounts
        ArrayList<CheckingAccount> accounts = null;

        final String path = "binary_object_file.bin";

        // load accounts from disk
        try
        {
            FileInputStream file = new FileInputStream(path);
            ObjectInputStream objectReader = new ObjectInputStream(file);
            accounts = (ArrayList<CheckingAccount>) objectReader.readObject();
            objectReader.close();
        }
        catch (Exception e)
        {
            System.out.println("Something went wrong when loading");
        }

        // print accounts, which might be null if something bad happened above
        if (accounts != null)
            for (int i = 0; i < accounts.size(); ++i)
                System.out.println(accounts.get(i));
    }
}
