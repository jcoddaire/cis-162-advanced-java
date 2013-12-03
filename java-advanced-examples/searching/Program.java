package examples.searching;

import java.util.Scanner;

public class Program
{
    /**
     * @param args
     */
    @SuppressWarnings("rawtypes")
    public static void main(String[] args)
    {
        // compare-to
        Document d1 = new Document(1);
        Document d2 = new Document(2);

        System.out.println(d1.compareTo(d2));
        System.out.println(d2.compareTo(d1));
        
        // create many documents
        final int NUM_DOCUMENTS = 10000000;
        Comparable[] documents = new Comparable[NUM_DOCUMENTS];
        for (int i = 0; i < documents.length; ++i)
            documents[i] = new Document(i);

        // get document to search for
        System.out.print("Enter document to search for:  ");
        Scanner scan = new Scanner(System.in);
        Document searchFor = new Document(scan.nextInt());

        // conduct linear search (SLOW)
        int location = LinearSearch.Search(documents, searchFor);
        System.out.println("Location:  " + location);

        // conduct binary search (FAST)
        location = BinarySearch.Search(documents, searchFor);
        System.out.println("Location:  " + location);
    }
}
