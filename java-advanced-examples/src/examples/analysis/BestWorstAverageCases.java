package examples.analysis;

import java.util.ArrayList;
import java.util.Random;

public class BestWorstAverageCases
{

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        // run the best case
        int[][] bestCaseNumbers = new int[][] {
                new int[] { 9, 8, 7 },
                new int[] { 6, 5, 4 },
                new int[] { 3, 2, 1 }
        };
        
        int maximum = findMaximum(bestCaseNumbers);
        System.out.println("Best case maximum value:  " + maximum);
       
        // run the worst case
        int[][] worstCaseNumbers = new int[][] {
                new int[] { 1, 2, 3 },
                new int[] { 4, 5, 6 },
                new int[] { 7, 8, 9 }
        };
        
        maximum = findMaximum(worstCaseNumbers);
        System.out.println("Worst case maximum value:  " + maximum);
        
        // demonstrate average case complexity of linear search
        averageFindCase();
    }

    /**
     * Finds maximum value within an array
     * 
     * @param numbers
     * @return
     */
    public static int findMaximum(int[][] numbers)
    {
                                                         // The following analysis assumes an n-by-n array.
        
        int maximum = numbers[0][0];                     // 1. 1

        for (int i = 0; i < numbers.length; i++)         // 2. 1 + (n + 1) + n
        {
            for (int j = 0; j < numbers[i].length; j++)  // 3. n * (1 + (n + 1) + n)
            {
                if (numbers[i][j] > maximum)             // 4. n * n
                {
                    maximum = numbers[i][j];             // 5. 0 to (n^2) - 1
                }
            }
        }

        return maximum;                                  // 6. 1
       
                                                         //  Best T(n):  3n^2 + 4n + 4
                                                         // Worst T(n):  4n^2 + 4n + 3
    }
    
    /**
     * Finds a number within an array of numbers
     * @param numbers
     * @param value
     * @return
     * @throws Exception
     */
    public static int find(int[] numbers, int value)
    {
                                      // The following analysis assumes the array is not null.
        
        for (int i = 0;               // 1. 1
             i < numbers.length;      // 2. 1 to n + 1
             ++i)                     // 3. 0 to n
        {
            if (numbers[i] == value)  // 4. 0 to n
            {
                return i;             // 5. 0 to 1
            }
        }
        
        return -1;                    // 6. 0 to 1                   
        
                                      //  Best T(n):  3
                                      // Worst T(n):  3n + 3
    }
    
    /**
     * Demonstrates the average case complexity of linear search
     */
    public static void averageFindCase()
    {
        // get 10k random numbers
        Random r = new Random();
        int[] numbers = new int[10000];
        ArrayList<Integer> uniqueNumbers = new ArrayList<Integer>();
        for (int i = 0; i < numbers.length; ++i)
        {
            // use a large number range to avoid duplicate numbers
            int number = r.nextInt(1000000);
            numbers[i] = number;

            // track unique numbers
            if (!uniqueNumbers.contains(number))
                uniqueNumbers.add(number);
        }

        // find a bunch of numbers and figure out how long, on average, it takes
        int timeRequired = 0;
        int numbersFound = 0;
        for (int i = 0; i < numbers.length; ++i)
        {
            // get value to find
            int numberToFind = uniqueNumbers.get(r.nextInt(uniqueNumbers.size()));

            // find number - the return value (the index) is also the amount of work done to find the value 
            timeRequired += find(numbers, numberToFind);
            ++numbersFound;
        }

        System.out.println("Average amount of time required to find " + numbers.length +  " values:  " + (timeRequired / numbersFound));
    }
}
