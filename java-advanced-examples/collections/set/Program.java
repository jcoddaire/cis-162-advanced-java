package examples.collections.set;

import java.util.ArrayList;
import java.util.Random;

public class Program
{

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        ArrayListSet<Integer> set = new ArrayListSet<Integer>();
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(1);
        set.add(2);
        set.add(4);

        System.out.println(set.getSize());

        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; ++i)
            set.add(i);

        long end = System.currentTimeMillis();

        System.out.println("Done in " + ((end - start) / 1000F) + " seconds.");

        addRandomNumbers(100000);
    }

    private static void addRandomNumbers(int count)
    {
        // get random numbers
        Random r = new Random();
        ArrayList<Integer> randomNumbers = new ArrayList<Integer>(count);
        for (int i = 0; i < count; ++i)
            randomNumbers.add(r.nextInt());

        // add numbers to ArrayList-based set
        ArrayListSet<Integer> set = new ArrayListSet<Integer>(count);
        long start = System.currentTimeMillis();
        for (int i = 0; i < randomNumbers.size(); ++i)
            set.add(randomNumbers.get(i));

        long end = System.currentTimeMillis();
        System.out.println("ArrayListSet:  " + ((end - start) / 1000F) + " seconds.");

        // add numbers to hash-based set
        HashSet<Integer> hashSet = new HashSet<Integer>(count);
        start = System.currentTimeMillis();
        for (int i = 0; i < randomNumbers.size(); ++i)
            hashSet.add(randomNumbers.get(i));

        end = System.currentTimeMillis();
        System.out.println("HashSet:  " + ((end - start) / 1000F) + " seconds.");
        System.out.println("Average hash list size:  " + hashSet.getAverageListSize());
    }
}
