/*
 * This program will convert bases of any given number.
 * Known issues/ TODO: when inputting large numbers (large strings) the conversion might convert the numbers incorrectly.
 * Support radix points?
 * Author: Jacob Coddaire
*/
import java.util.*;

public class baseConverter {
	
	private static String number, output;
	private static int initialBase, convBase;
	
	/*--------------------------------------------------------------------------------------------
     * Default constructor
     *-------------------------------------------------------------------------------------------*/
	public baseConverter(){}
	
	/*--------------------------------------------------------------------------------------------
     * Main method
     *-------------------------------------------------------------------------------------------*/
    public static void main (String args[]){
    	//String message = "Please enter a number between 2 and 36.";
    	//errorCheck(initialBase, message);
    	
    	baseConverter bc = new baseConverter();
        bc.getInput();
        output = bc.convertNumbers(number, initialBase, convBase);
        System.out.println(number + " in Base " + initialBase + " is " + output + " in Base " + convBase + ".");
        
    }
    /*--------------------------------------------------------------------------------------------
     * Logic and engine tools
     *-------------------------------------------------------------------------------------------*/                
    private String convertNumbers(String initialNumber, int initialBase, int convBase){
        String output = "";
        int remainder = 0;  
        int number = 0;
        
        number = convertToTen(initialNumber, initialBase);           
        
        ArrayList<String> whiteList = generateWhiteList();
        
        while (number > 0){
            remainder = number % convBase;
            	//pull from the list and convert to numbers and letters as needed.
	            for (int j=0; j<whiteList.size(); j++){
					if (remainder == j){
						number = number / convBase;
			            output = whiteList.get(j) + output;
		        		break;
					}
				}
        }
        return output;
    }
    
    //I love this site... http://www.cs.umd.edu/class/sum2003/cmsc311/Notes/Data/toBaseTen.html      
    private int convertToTen(String num, int initialBase){
    	int result = 0;
    	int numLength = num.length()-1;//TODO: revise to support radix points
    	ArrayList<String> whiteList = generateWhiteList();
    	
    	for (int i=0; i<num.length(); i++){
    		char current = num.charAt(i);
    		String cString = Character.toString(current);
    		cString = cString.toLowerCase(Locale.ENGLISH);
    		//pull value from array
    			for (int j=0; j<whiteList.size(); j++){
    				if (whiteList.get(j).equals(cString)){
    					int intCurrent = j;
    					//example: 212^3 = (2 * 3^2) + (1 * 3^1) + (2 * 3^0) 
    	        		result = result + (int) (intCurrent * Math.pow(initialBase, numLength));
    	        		numLength -= 1;
    	        		break;
    				}
    			}
    	}
    	return result;
    }
        
    private void getInput(){
    	Scanner scan = new Scanner(System.in);
    	
    	System.out.println("Enter a number.");
    	number = scan.nextLine();
        	
    	String message = "Enter the base of that number.";
    	initialBase = getBase(initialBase, message);
    	if (boundsCheck(number, initialBase) == false){
    		System.out.println("The number you entered, "+number+ ", exceeds the base you entered. Please enter a proper number and base.");
    		getInput();
    	}
    	
    	
    	message = "Enter the base you want to convert to.";
    	convBase = getBase(convBase, message);
    }
        
    private ArrayList<String> generateWhiteList(){
    	
    	ArrayList<String> whiteList = new ArrayList<String>();
    	final String acceptableCharacters = "0123456789abcdefghijklmnopqrstuvwxyz";
    	
    	for(int i=0; i< acceptableCharacters.length(); i++){
    		char temp = acceptableCharacters.charAt(i);
    		String s = Character.toString(temp);
    		whiteList.add(s);
    	}
    	
    	return whiteList;
    }     
        
    /*--------------------------------------------------------------------------------------------
     * Error checking tools
     *-------------------------------------------------------------------------------------------*/
    private int getBase(int base, String message){
    	//this needs to do two things. 1. Check that the entered number is a number. 
    	//2.Make sure it is in range. 
    	Scanner scan = new Scanner(System.in);
    	System.out.println(message);

        do {
            while (!scan.hasNextInt()) {
                System.out.println("That's not a number! Please enter an integer between 2 and 36.");
                scan.next();
            }
            
            base = scan.nextInt();
            if (base < 2 || base > 36){
            	System.out.println("That's out of range! The entered value must be between 2 and 36.");
            }
        } while (base < 2 || base > 36);
        
        return base;
    }
    
    private boolean boundsCheck(String number, int base){
    	// if false, there is a problem. If true, everything is good.
    	ArrayList<String> whiteList = generateWhiteList();
    	ArrayList<String> acceptableList = new ArrayList<String>();
    	
    	for(int i=0; i<base; i++){
    		String temp = whiteList.get(i);
    		acceptableList.add(temp);
    	}
    	
    	for(int i=0; i< number.length(); i++){
    		String testee = Character.toString(number.charAt(i));
    		testee = testee.toLowerCase(Locale.ENGLISH);
    			if (acceptableList.contains(testee) == true){
    				//it's okay, so do nothing
    			}else{
    				//it's not okay
    				//System.out.println("You entered '" + testee + "', which is an invalid character. Pleae enter a valid character.");
    				return false;
    			}
    	}
    	return true;
    }
}