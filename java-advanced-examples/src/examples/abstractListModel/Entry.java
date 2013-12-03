package examples.abstractListModel;

/**
 * Represents a single entry in the phonebook
 * 
 * @author gerberma
 *
 */
public class Entry
{
    private String _firstName;
    private String _lastName;
    private int _number;
    
    /**
     * Constructor
     * @param firstName
     * @param lastName
     * @param number
     */
    public Entry(String firstName, String lastName, int number)
    {
        _firstName = firstName;
        _lastName = lastName;
        _number = number;
    }
    
    /**
     * Returns nicely formatted phonebook entry
     */
    public String toString()
    {
        return _firstName + " " + _lastName + ":  " + _number;
    }
}
