package examples.methodOverriding;

public class Program
{

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        CheckingAccount a = new CheckingAccount("matt", "gerber", 0);
        CheckingAccount b = new CheckingAccount("matt", "gerber", 0);
        
        if(a.equals(b))
            // the objects are distinct, but the IDs are the same - so, the overidden equals returns true
            System.out.println("Equals");
    }
}
