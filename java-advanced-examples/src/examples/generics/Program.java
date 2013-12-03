package examples.generics;

import java.util.ArrayList;

/**
 * Demonstrates the importance of generics
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
        ArrayList list = new ArrayList();
        list.add("Hello");
        list.add("World!");
        list.add(1);

        // this method will throw an exception...why?
        unsafePrint(list);

        // instantiate example generic class
        ExampleGenericClass<String> c = new ExampleGenericClass<String>("Hello");
        String var = c.getVariable();
        
        System.out.println(var);
        
        System.out.println(c.isLessThan("Hello there!"));
    }

    /**
     * Prints a list
     * 
     * @param list
     */
    private static void unsafePrint(ArrayList list)
    {
        for (int i = 0; i < list.size(); ++i)
        {
            String s = (String) list.get(i);
            System.out.println(s);
        }
    }
}
