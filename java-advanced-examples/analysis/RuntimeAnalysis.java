package examples.analysis;

/**
 * Runtime analysis of a few simple functions
 * 
 * @author Matt
 *
 */
public class RuntimeAnalysis
{
    public static void main(String[] args)
    {
        linearTime(10);
        quadraticTime(10);
        polynomialTime(10);
    }

    public static void linearTime(int n)
    {
        int x = 0;                   // 1.  1
        for (int i = 0; i < n; ++i)  // 2.  1 + (n + 1) + n
        {
            x++;                     // 3.  n
        }

        System.out.println(x);       // 4.  1
                                     
                                     // T(n) = 3n + 4
    }

    public static void quadraticTime(int n)
    {
        int x = 0;                       // 1. 1
        for (int i = 0; i < n; ++i)      // 2. 1 + (n + 1) + n
        {
            for (int j = 0; j < n; ++j)  // 3. n * (1 + (n + 1) + n)
            {
                x++;                     // 4. n * n
            }
        }

        System.out.println(x);           // 5. 1
        
                                         // T(n) = 3n^2 + 4n + 4
    }

    public static void polynomialTime(int n)
    {
        int x = 0;                           // 1. 1
        for (int i = 0; i < n; ++i)          // 2. 1 + (n + 1) + n
        {
            for (int j = 0; j < n; ++j)      // 3. n * (1 + (n + 1) + n)
            {
                for (int k = 0; k < n; ++k)  // 4. n * (n * (1 + (n + 1) + n))
                {
                    x++;                     // 5. n * n * n
                }
            }
        }

        System.out.println(x);               // 6. 1
        
                                             // T(n) = 3n^3 + 4n^2 + 4n + 4
    }
}
