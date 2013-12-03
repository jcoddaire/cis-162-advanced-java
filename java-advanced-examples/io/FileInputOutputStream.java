package examples.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Demonstrates simple binary file IO
 * 
 * @author Matt
 * 
 */
public class FileInputOutputStream
{
    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException
    {
        // write output
        byte[] output = new byte[] { 1, 2, 3, 4, 127 };
        FileOutputStream fos = new FileOutputStream("test.txt");
        fos.write(output);
        fos.close();

        // read input
        byte[] input = new byte[5];
        FileInputStream fis = new FileInputStream("test.txt");
        int numRead = fis.read(input);

        // print results
        System.out.print("Read " + numRead + " bytes:  ");
        for (int i = 0; i < input.length; ++i)
            System.out.print(input[i] + " ");
    }
}
