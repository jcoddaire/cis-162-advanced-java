package credit_card;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class CC_PINS {

	/**
	 * This program quickly generates all possible 4 digit credit card pin numbers.
	 * Author: Jacob A. Coddaire
	 * 
	 * And yes, it is a bit in retaliation to this dude's paste. Using loops is not that hard.
	 * http://pastebin.com/2qbRKh3R
	 */
	public static void main(String[] args) {
		
		System.out.println(generatePins());

	}
	
	private static String generatePins()
	{
		String pins = "";
		int count;
		int line;
		NumberFormat fDigits = new DecimalFormat ("#0000");
		
		for (count=0; count<10000; count++){
			for (line=0; line<9; line++){
				pins= (pins + fDigits.format(count) + "\t");
				count++;
			}
			pins = (pins + fDigits.format(count) + "\n");
		}
		return pins;
	}
}