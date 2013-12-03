package examples.observable;

import java.util.Scanner;

/**
 * Demonstrates the Observable/Observer setup
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
        // create a baby
        Baby baby = new Baby(Baby.State.Sleeping);
        
        // create a parent to observe the baby
        Parent parent = new Parent();
        baby.addObserver(parent);
        
        // pause to let the baby change state
        new Scanner(System.in).next();
    }
}
