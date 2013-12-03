package bank;
/**
 * This class instantiates the main GUI and BankEngine
 *
 *Jacob Coddaire
 *CIS 163
 */
public class Main 
{
	public static void main(String[] args)
	{
		// runs the logic
		BankEngine engine = new BankEngine();
		
		// runs the GUI
		@SuppressWarnings("unused")
		BankGUI a = new BankGUI(args, engine);
	}
}