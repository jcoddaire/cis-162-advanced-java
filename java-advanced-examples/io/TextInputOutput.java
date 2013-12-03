package examples.io;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Demonstrates the use of text input/output streams
 * 
 * @author Matt
 * 
 */
public class TextInputOutput
{

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException
    {
        // write some text to file
        FileWriter writer = new FileWriter("test.txt");
        writer.write("maxNumTellers 4\n");
        writer.write("meanCustomerArrivalTime 240\n");
        writer.close();

        // read text back in
        Scanner scanner = new Scanner(new FileInputStream("test.txt"));
        HashMap<String, Integer> hash = new HashMap<String, Integer>();
        while (scanner.hasNext())
        {
            String paramName = scanner.next();
            Integer paramValue = Integer.parseInt(scanner.next());
            
            hash.put(paramName, paramValue);
        }
        
        scanner.close();
        
        // print out key/value pairs
        for(String key : hash.keySet())
        {
            System.out.println(key + ":  " + hash.get(key));
        }        
    }
}
