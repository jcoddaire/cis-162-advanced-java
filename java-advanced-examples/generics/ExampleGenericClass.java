package examples.generics;

/**
 * Demonstrats the create of a generic class
 * 
 * @author Matt
 * 
 * @param <T>
 *        Type of variable that the class will contain
 */
public class ExampleGenericClass<T extends Comparable<T>>
{
    private T variable;

    /**
     * Gets the variable
     * 
     * @return
     */
    public T getVariable()
    {
        return variable;
    }

    /**
     * Constructor
     * 
     * @param variable
     */
    public ExampleGenericClass(T variable)
    {
        this.variable = variable;
    }

    /**
     * Prints the variable
     */
    public void print()
    {
        System.out.println(variable);
    }
    
    public boolean isLessThan(T other)
    {
        return variable.compareTo(other) < 0;
    }
}
