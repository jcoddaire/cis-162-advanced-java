package network;
/**
 * Jacob A. Coddaire
 * CIS 163
 * 
 * This program should receive data from a server, and send data to the server.
 * 
 *     Client connects to server.
		Client sends GVSU username (e.g., "gerberma") to server.
Server sends a random number in String form (e.g., "35") to client.
Client receives random number String and sends the same number back to server in String form (e.g., "35").
If successful, server will send "Thank you, gerberma. Task completed." to the client.
 */

import java.io.*;
import java.net.*;

public class Client {

	private static String mySentence;
	private static String serverSentence;
	/* @throws IOException 
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException, IOException 
	{
		System.out.println("Test");
	
		// the socket allows it to connect to an open port
		Socket jcSocket = new Socket("links.cse.msu.edu", 12345);
		
		// creates the input stream for the user input.
		BufferedReader in = new BufferedReader (new InputStreamReader(System.in));
		// allows the user's data to be exported.
		DataOutputStream out = new DataOutputStream(jcSocket.getOutputStream());
		
		// creates the input stream for the Server. This is what receives the connection/ data
		BufferedReader serverInput = new BufferedReader(new InputStreamReader(jcSocket.getInputStream()));
		
		// reads in my input and writes it out to the server.
		mySentence = in.readLine();
		out.writeBytes(mySentence + "\n");
		
		// receives the server input and prints it out.
		serverSentence = serverInput.readLine();
		
		System.out.println("Message from Server: " + serverSentence);
		
		// reads in my input and writes it out to the server.
		mySentence = in.readLine();
		out.writeBytes(mySentence + "\n");
		
		// receives the server input and prints it out.
		serverSentence = serverInput.readLine();
		jcSocket.close();
		System.out.println("Message from Server: " + serverSentence);
	}
}