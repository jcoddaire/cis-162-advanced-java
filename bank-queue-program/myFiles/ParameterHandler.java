package myFiles;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
/********************************************************************************
This class should take in the data from the txt file and assign it to a hashmap
********************************************************************************/
public class ParameterHandler 
{
	HashMap<String, Integer> map;
	public ParameterHandler()
	{
		// creates the new hashmap
		map = new HashMap<String, Integer>();
		// takes in the file and assigns the keys to the hashmap
		try
		{
	        Scanner scanner = new Scanner(new FileInputStream("test.txt"));
	        
	        while (scanner.hasNext())
	        {
	            String paramName = scanner.next();
	            Integer paramValue = Integer.parseInt(scanner.next());
	            
	            map.put(paramName, paramValue);
	        }
	        scanner.close();
		}
		catch (FileNotFoundException e)
		{
			System.out.println("The file does not exist.\nPlease create a file.");
		}
	}
	// is used to get a specific integer value from a key
	public Integer get(String s){
		return map.get(s);
	}
	public void put(String s, int value) {
		map.put(s,value);
	}
}
