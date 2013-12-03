package examples.abstractListModel;

import java.util.ArrayList;

import javax.swing.AbstractListModel;

/**
 * Represents a simple phonebook
 * 
 * @author gerberma
 * 
 */
@SuppressWarnings("serial")
public class Phonebook extends AbstractListModel
{
    private ArrayList<Entry> _entries;

    /**
     * Constructor
     */
    public Phonebook()
    {
        _entries = new ArrayList<Entry>();
    }

    /**
     * Gets entry at an index
     */
    @Override
    public Object getElementAt(int arg0)
    {
        return _entries.get(arg0);
    }

    /**
     * Gets size of phonebook
     */
    @Override
    public int getSize()
    {
        return _entries.size();
    }

    /**
     * Adds entry to phonebook
     * 
     * @param entry
     */
    public void add(Entry entry)
    {
        _entries.add(entry);

        // tell the external list that the phonebook was modified
        fireIntervalAdded(this, _entries.size() - 1, _entries.size() - 1);
    }

    /**
     * Saves this phonebook to disk
     * 
     * @param path
     *        Path to file to write
     */
    public void save(String path)
    {
        // todo
    }

    /**
     * Loads this phonebook from disk
     * 
     * @param path
     *        Path to file to load
     */
    public void load(String path)
    {
        // todo
    }
}
