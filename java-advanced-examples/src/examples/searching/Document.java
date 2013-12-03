package examples.searching;

import javax.xml.bind.TypeConstraintException;

/**
 * Represents a simple document
 * 
 * @author Matt
 * 
 */
@SuppressWarnings("rawtypes")
public class Document implements Comparable
{
    private int _id;

    /**
     * Constructor
     * 
     * @param id
     */
    public Document(int id)
    {
        _id = id;
    }

    /**
     * Compares the current document to another one
     */
    @Override
    public int compareTo(Object document)
    {
        if (!(document instanceof Document))
            throw new TypeConstraintException("Must pass a document!");

        Document d = (Document) document;

        // compare the current ID to the given ID
        return ((Integer) _id).compareTo(d._id);
    }
}
