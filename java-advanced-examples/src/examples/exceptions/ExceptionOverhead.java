package examples.exceptions;

/**
 * Demonstrates the overhead involved with throwing and catching exceptions. If speed
 * matters, never use exception handling as a control mechanism!
 * 
 * @author Matt
 * 
 */
public class ExceptionOverhead
{

    /**
     * @param args
     */
    @SuppressWarnings("unused")
    public static void main(String[] args)
    {
        // get some letters mixed with integers
        String[] strings = new String[1000000];
        for (int i = 0; i < strings.length; ++i)
            strings[i] = i % 2 == 0 ? "a" : "1";

        // process all ints using exception handling
        long start = System.currentTimeMillis();
        for (int i = 0; i < strings.length; ++i)
        {
            try
            {
                int x = Integer.parseInt(strings[i]);
            }
            catch (NumberFormatException e)
            {
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("Exception handling:  " + (end - start) + "ms");

        // process all ints using if-checks
        start = System.currentTimeMillis();
        for (int i = 0; i < strings.length; ++i)
        {
            String s = strings[i];
            if (s == "0" || s == "1" || s == "2" || s == "3" || s == "4" || s == "5" || s == "6" || s == "7" || s == "8" || s == "9")
            {
                int x = Integer.parseInt(s);
            }
        }
        end = System.currentTimeMillis();
        System.out.println("if-checks:  " + (end - start) + "ms");
    }
}
