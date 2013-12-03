package examples;

import java.util.LinkedList;

public class Miscellaneous
{
    public static void main(String[] args)
    {
        LinkedList<String> list = new LinkedList<String>();
        method(list);
        
        System.out.println(list.remove());
    }
    
    public static void method(LinkedList<String> list)
    {
        list.add("Hello");
    }
}
