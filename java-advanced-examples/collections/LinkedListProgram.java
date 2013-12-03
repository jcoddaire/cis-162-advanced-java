package examples.collections;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Demonstrates the use of Java's LinkedList class and the importance of random access
 * 
 * @author Matt
 * 
 */
public class LinkedListProgram
{

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        // LinkedList
        LinkedList<Integer> linkedList = new LinkedList<Integer>();
        for (int i = 0; i < 100000; ++i)
            linkedList.add(i);

        // access all items in list
        long start = System.currentTimeMillis();
        for (int i = 0; i < linkedList.size(); ++i)
            linkedList.get(i);
        
        long end = System.currentTimeMillis();
        System.out.println("LinkedList:  " + ((end - start) / 1000F) + " seconds");
        
        // ArrayList
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        for (int i = 0; i < 100000; ++i)
            arrayList.add(i);

        // access all items in list
        start = System.currentTimeMillis();
        for (int i = 0; i < arrayList.size(); ++i)
            arrayList.get(i);
        
        end = System.currentTimeMillis();
        System.out.println("ArrayList:  " + ((end - start) / 1000F) + " seconds");
    }

}
