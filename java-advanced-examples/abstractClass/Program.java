package examples.abstractClass;

public class Program
{

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        AbstractAccount a = new SavingsAccount("Matt", "Gerber", 0, 0.05);
        a.depositInto(50);
        
        // AbstractAccount a = new AbstractAccount("Matt", "Gerber", 1);  // uncommenting this line will cause an error - you cannot instantiate an Account object directly
    }

}
