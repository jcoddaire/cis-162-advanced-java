package examples.io;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Demonstrates the use of buffering to speed up IO
 * @author Matt
 *
 */
public class BufferedOutput
{

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException
    {        
        // write a bunch of data using an unbuffered stream
        FileOutputStream fos = new FileOutputStream("C:\\unbuffered.txt");
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000000; ++i)
            fos.write(1);

        fos.close();
        long end = System.currentTimeMillis();

        System.out.println("Unbuffered output stream:  " + ((end - start) / 1000F) + " seconds");

        // write the same data using a buffered stream
        fos = new FileOutputStream("C:\\buffered.txt");
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        start = System.currentTimeMillis();
        for (int i = 0; i < 10000000; ++i)
            bos.write(1);

        bos.close();
        end = System.currentTimeMillis();

        System.out.println("Buffered output stream:  " + ((end - start) / 1000F) + " seconds");
    }
}
