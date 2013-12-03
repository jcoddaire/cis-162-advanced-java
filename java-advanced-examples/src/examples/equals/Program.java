package examples.equals;

import java.util.ArrayList;

public class Program
{

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        // add an account
        ArrayList<Account> accounts = new ArrayList<Account>();
        Account a = new Account(1);
        accounts.add(a);

        // try to add a duplicate account
        Account b = new Account(1);
        if (!accounts.contains(b))
            accounts.add(b);

        // this will print out 1, indicating that an account with ID 1 was already in the ArrayList
        System.out.println(accounts.size());
    }
}
