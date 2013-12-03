package examples.shadowing;

public class Program
{
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        SubClass a = new SubClass(3);
        System.out.println(a._x);  // which _x are we referring to? the sub-class or super-class?
        
        SuperClass b = (SuperClass)a;
        System.out.println(b._x);  // now which _x are we referring to?
    }
}
