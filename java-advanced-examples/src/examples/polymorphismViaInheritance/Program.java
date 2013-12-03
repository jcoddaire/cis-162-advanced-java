package examples.polymorphismViaInheritance;

public class Program
{

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        AbstractAccount a = new CheckingAccount("Matt", "Gerber", 0);

        printAccount(a);

        a = new CreditAccount("John", "Doe", 1, 0.10);

        printAccount(a);
        

        CreditAccount c = new CreditAccount("Jane", "Doe", 1, 0.10);
        System.out.println(c.getNiceString());  // the compiler now has a better idea about what code to execute...it's the method on CreditAccount 
    }

    private static void printAccount(AbstractAccount acct)
    {
        /* all we know is that we have an AbstractAccount object. what we actually do will depend on
         * the class that has extended AbstractAccount. this is a runtime decision (aka polymorphism) 
         */        
        System.out.println(acct.getNiceString());
    }
}
