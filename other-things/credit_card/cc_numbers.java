package credit_card;

import java.text.DecimalFormat;
import java.text.NumberFormat;


public class cc_numbers {

	/**
	 * This program quickly generates all possible 16 digit credit card numbers.
	 * Author: Jacob A. Coddaire
	 */
	private static NumberFormat format = new DecimalFormat("0000");
	
	public static void main(String[] args) {
		
		increment();
	}

	private static void increment (){
		String first = "0000";
		String mid1 = "0000";
		String mid2 = "0000";
		String last = "0000";
		
		for (int i=0; i<10000; i++){
			first = fCC(first, i);
			for (int j=0; j<10000; j++){
				mid1 = fCC(mid1, j);
				for (int k=0; k<10000; k++){
					mid2 = fCC(mid2, k);
					for (int l=0; l<10000; l++){
						last =  fCC(last, l);
						System.out.println(first+ "-" +mid1+"-"+mid2+ "-"+last);
					}	
				}
			}
		}
	}
	
	private static String fCC(String string, int variable){
		string = format.format(variable);
		return string;
	}
}