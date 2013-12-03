package examples.io.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Demonstrates the use of server-side sockets
 * 
 * @author Matt
 * 
 */
public class Server
{
    public static void main(String[] args) throws IOException
    {
        // open a listener on a port
        ServerSocket serverSocket = new ServerSocket(12345);

        // wait for a client to connect
        System.out.println("Waiting for connection from client");
        Socket socket = serverSocket.accept();
        System.out.println("Received connection from client");

        // get a scanner on the input stream of the connection
        InputStreamReader inputStream = new InputStreamReader(socket.getInputStream());
        BufferedReader bufferedInput = new BufferedReader(inputStream);

        // read until "Quit" message is received
        while (true)
        {
            String message = bufferedInput.readLine();
            if (message.equals("Quit"))
                break;

            System.out.println("Message from client:  " + message);
        }

        // close connection
        System.out.println("Closing server socket");
        socket.close();
    }
}
