package examples.jFileChooser;

import javax.swing.JFileChooser;

/**
 * Demonstrates the JFileChooser
 * 
 * @author Matt
 * 
 */
public class Program
{
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        JFileChooser chooser = new JFileChooser();

        // show chooser and get the user's action
        int result = chooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION)
        {
            String path = chooser.getSelectedFile().getAbsolutePath();
            System.out.println("Saving to:  " + path);
        }
        else
            System.out.println("Not saving");

        // show chooser and get the user's action
        result = chooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION)
        {
            String path = chooser.getSelectedFile().getAbsolutePath();
            System.out.println("Opening:  " + path);
        }
        else
            System.out.println("Not opening");
    }
}
