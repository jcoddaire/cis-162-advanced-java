package examples.threading;

import java.util.Random;

import javax.swing.JOptionPane;

import examples.sorting.BubbleSort;

/**
 * Demonstrates how threading can be used to maintain a responsive user interface
 * 
 * @author gerberma
 * 
 */
public class ResponsiveUI
{
    public static void main(String[] args)
    {
        // get random numbers
        Integer[] numbers = new Integer[10000000];
        Random r = new Random();
        for (int i = 0; i < numbers.length; ++i)
            numbers[i] = r.nextInt();

        // sort using bubble sort in the current thread - doing this will prevent the JOptionPanes from being displayed immediately
        //BubbleSort.sort(numbers);

        // sort using bubble sort in a different thread - allows the JOptionPanes to be displayed immediately
        Thread t = new Thread(new BubbleSortRunner(numbers));
        t.start();

        // show some dialog windows
        JOptionPane.showInputDialog(null, "This might take a while. Tell me something about yourself.");
        JOptionPane.showInputDialog(null, "Oh really? Tell me more.");
        JOptionPane.showInputDialog(null, "Ahhhh, I'm afraid this sort will never finish, but keep talking.");
    }
}
