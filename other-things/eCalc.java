/*
 * This program shall accept four input values and calculate the elasticity of the values.
 * Author: Jacob Coddaire
 * Version: 1.0.0
 */
import java.text.DecimalFormat;
import java.util.Scanner;

public class eCalc {
	
	private double p1, p2, q1, q2;
	private DecimalFormat df = new DecimalFormat("#.##");
	
	public eCalc(){}
	
	public static void main(String[] args) {
		
		eCalc calc = new eCalc();
		calc.getInput();
		calc.calculate(calc.p1, calc.p2, calc.q1, calc.q2);
		double result = calc.peod(calc.p1, calc.p2, calc.q1, calc.q2);
		System.out.println("The value of peod method is "+result);
	}
	
	private double peod(double p1, double p2, double q1, double q2){
		double result = ((q2-q1)/((q2+q1)/2))
						/
						((p2-p1)/((p2+p1)/2)); 
		return result;
	}
	
	private double midpointValue (double var1, double var2){
		double midpoint = (var1 + var2)/2;
		System.out.println(df.format(midpoint)+" = ("+df.format(var1)+" + "+df.format(var2)+") / 2\n");
		return midpoint;
	}
	
	private double midpointMethod (double end, double start, double midpoint){
		double result = ((end - start)/midpoint) * 100;
		System.out.println(df.format(result)+" = (("+df.format(end)+" - "+df.format(start)+") / "+df.format(midpoint)+") * 100\n");
		return result;
	}
	
	private void determineElasticity (double pQChanged, double pPChanged){
		
		double result = pQChanged / pPChanged;
		result = Math.abs(result);
		
		if (result > 1){
			//elastic
			System.out.println(df.format(result) +" is price elastic.");
		}else if (result == 1){
			//unit elastic
			System.out.println(df.format(result)+ " is unit elastic.");
		}else{
			//not elastic
			System.out.println(df.format(result) +" is not price elastic.");
		}
	}
	
	private double determinePercentage(double var1, double var2){
		double result = (var2-var1)/var1;
		return result;
	}
	
	private void calculate (double p1, double p2, double q1, double q2){
		
		System.out.println("Begin calculations\n" +
				"--------------------------------------------------\n");
		double changePriceMidpoint = midpointValue(p1, p2);
		double changePrice = midpointMethod(p1, p2, changePriceMidpoint);
		
		double changeQuantityMidpoint = midpointValue(q1, q2);
		double changeQuantity = midpointMethod (q1, q2, changeQuantityMidpoint);
		
		double result = changeQuantity / changePrice;
		System.out.println(df.format(result)+" = "+df.format(changeQuantity)+" / "+df.format(changePrice)+
				"\n--------------------------------------------------\n");
		
		result = Math.abs(result);
		
		System.out.println("The elasticity of ("+df.format(p1)+","+df.format(q1)+") and ("+df.format(p2)+","+df.format(q2)+") is "+df.format(result)+"\n");
		
		double percentQChanged = determinePercentage(q1, q2);
		double percentPChanged = determinePercentage(p1, p2);
		
		determineElasticity(percentQChanged, percentPChanged);
	}
	
	private void getInput(){
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println ("Enter the first price.");
		double p1=scan.nextDouble();
		System.out.println ("Enter the first quantity.");
		double q1=scan.nextDouble();
		System.out.println ("Enter the second price.");
		double p2=scan.nextDouble();
		System.out.println ("Enter the second quantity.");
		double q2=scan.nextDouble();
		
		scan.close();
		
		this.p1=p1;
		this.p2=p2;
		this.q1=q1;
		this.q2=q2;
		
		System.out.println ("You entered the following numbers:\n\n"+
				"Price 1:\t"+df.format(p1)+"\n"+
				"Quantity 1:\t"+df.format(q1)+"\n"+
				"Price 2:\t"+df.format(p2)+"\n"+
				"Quantity 2:\t"+df.format(q2)+"\n");
		
	}
}