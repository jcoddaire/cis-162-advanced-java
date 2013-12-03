package examples.serialization;

import java.io.Serializable;

/**
 * Represents an account that can be serialized
 * 
 * @author Matt
 * 
 */
public class CheckingAccount implements Serializable
{
    // if this changes between the time of serialization and deserialization, a runtime exception will be thrown
    private static final long serialVersionUID = 1L;

    private String _firstName;
    private String _lastName;

    /**
     * Constructor
     * 
     * @param firstName
     * @param lastName
     */
    public CheckingAccount(String firstName, String lastName)
    {
        _firstName = firstName;
        _lastName = lastName;
    }

    /**
     * Provides nice description
     */
    public String toString()
    {
        return _firstName + " " + _lastName;
    }
}
