package examples.customJDialog;

/**
 * Demonstration of a custom JDialog
 * 
 * @author gerberma
 * 
 */
public class Program
{
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        // create dialog and show it
        CustomJDialog dialog = new CustomJDialog(null);  // we have no parent window over which to center the dialog, so pass null here - the dialog will be centered on the screen
        dialog.setVisible(true);                         // execution will stop here until the window is closed

        // get result from dialog and print it out
        CustomJDialog.Result result = dialog.getResult();
        System.out.println("You clicked:  " + result);
    }
}
