package examples.collections.stack;

import java.util.ArrayList;

/**
 * Stack implemented using an ArrayList
 * 
 * @author Matt
 * 
 */
public class ArrayListStack
{
    private ArrayList stack;

    /**
     * Gets the size of the stack
     * 
     * @return
     */
    public int getSize()
    {
        return stack.size();
    }

    /**
     * Constructor
     */
    public ArrayListStack()
    {
        stack = new ArrayList();
    }

    /**
     * Pushes an item on to the top of the stack
     * 
     * @param item
     */
    public void push(Object item)
    {
        stack.add(item);
    }

    /**
     * Pops item off top of stack and returns it. Returns null if stack is empty.
     * 
     * @return
     */
    public Object pop()
    {
        if (stack.size() == 0)
            return null;

        return stack.remove(stack.size() - 1);
    }

    /**
     * Peeks at item on top of stack without removing it. Returns null if stack is empty.
     * 
     * @return
     */
    public Object peek()
    {
        if (stack.size() == 0)
            return null;

        return stack.get(stack.size() - 1);
    }
}
