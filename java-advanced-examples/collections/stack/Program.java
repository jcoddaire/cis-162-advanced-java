package examples.collections.stack;

/**
 * Demonstrates the use of ArrayListStack
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
        ArrayListStack stack = new ArrayListStack();
        stack.push("3");
        stack.push("2");
        stack.push("1");

        while (stack.getSize() > 0)
            System.out.println(stack.pop());
    }
}
