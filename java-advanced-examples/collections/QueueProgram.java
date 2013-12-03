package examples.collections;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Demonstrates the use of the queue data structure
 * 
 * @author Matt
 * 
 */
public class QueueProgram
{
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        // add some items to the queue
        Queue<String> q = new LinkedList<String>();
        q.add("a");
        q.add("b");
        q.add("c");

        // remove items from the queue
        System.out.println(q.remove());
        System.out.println(q.remove());
        System.out.println(q.remove());
    }
}
