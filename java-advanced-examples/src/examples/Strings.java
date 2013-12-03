package examples;

public class Strings
{
    public static void main(String[] args)
    {
        /*
         * The JVM maintains a pool of string literals for reuse as a means to conserve
         * memory. This is nice, but leads to some very interesting consequences. Take
         * the following strings:
         */
        String a = "x";
        String b = "x";

        /*
         * We've always been told not to use the == operator to compare strings in Java
         * because it compares the object references and not their content. So you might
         * expect the following to print "false":
         */
        System.out.println("1:  " + (a == b));

        /*
         * The above actually prints true. This is tricky because it might lead you to
         * believe that == is doing a string comparison (especially if you're a .NET person)
         * when in actuality it is comparing references. When you create a string literal
         * the JVM first checks if one with the same value is in the pool. If one is, that
         * string is used. To create a string outside of the pool, use "new":
         */
        String c = new String("x");
        System.out.println("2:  " + (a == c));

        /*
         * Now, even though "c" has the same value as "a", it is outside the pool and
         * will thus have a different object reference. To get the string comparison,
         * use "equals" like you were told:
         */
        System.out.println("3:  " + a.equals(c));
        
        /*
         * Strings are immutable, mean that, if you change one, you always get back a 
         * different object.
         */
        a = "Matthew";
        System.out.println("4:  " + (a == a.substring(0, 1)));
        
        /*
         * At least, that's usually the case! If you request the entire string, the JVM
         * returns a reference to the object on which you called substring. This makes
         * tons of sense from a memory conservation perspective!
         */
        System.out.println("5:  " + (a == a.substring(0)));
    }
}
