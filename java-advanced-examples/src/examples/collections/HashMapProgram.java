package examples.collections;

import java.util.HashMap;

public class HashMapProgram
{

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        HashMap<String, String> hash = new HashMap<String, String>();

        hash.put("1", "one");
        hash.put("2", "two");
        hash.put("3", "three");

        System.out.println(hash.get("2")); // prints "two"

        hash.put("2", "to");
        
        System.out.println(hash.get("2")); // prints "to"
    }
}
