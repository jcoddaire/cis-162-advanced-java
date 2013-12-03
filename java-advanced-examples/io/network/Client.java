package examples.io.network;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Demonstrates the use of client-side sockets
 * 
 * @author Matt
 * 
 */
public class Client
{
    public static void main(String[] args) throws UnknownHostException, IOException
    {
        // open connection to server on local machine
        Socket clientSocket = new Socket("localhost", 12345);

        // get writer for connection
        PrintWriter output = new PrintWriter(clientSocket.getOutputStream());

        // write message
        output.write("Hello world!\n");
        output.flush();
        
        // write message
        output.write("Hello again, world!\n");
        output.flush();

        // tell server to quit
        output.write("Quit\n");
        output.flush();

        // close connection
        clientSocket.close();
    }
}
