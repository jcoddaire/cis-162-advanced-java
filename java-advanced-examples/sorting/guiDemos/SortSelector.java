package examples.sorting.guiDemos;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 * Lets the user select the sorts to display
 * @author Matt
 *
 */
@SuppressWarnings("serial")
public class SortSelector extends JDialog implements ActionListener
{
    /**
     * Types of sort
     * 
     * @author Matt
     *
     */
    public enum Sorts
    {
        Bubble,
        Selection,
        Insertion,
        QuickSort
    }

    private JButton _ok;

    /**
     * Gets the selected sorts
     * @return
     */
    public ArrayList<Sorts> getSelectedSorts()
    {
        ArrayList<Sorts> selected = new ArrayList<Sorts>();
        for (int i = 0; i < getContentPane().getComponentCount(); ++i)
            if (getContentPane().getComponent(i) instanceof JCheckBox)
            {
                JCheckBox checkbox = (JCheckBox) getContentPane().getComponent(i);
                if (checkbox.isSelected())
                    selected.add(Sorts.valueOf(checkbox.getText()));
            }

        return selected;
    }

    /**
     * Constructor
     * 
     * @param parent
     */
    public SortSelector(JFrame parent)
    {
        super(parent, true);

        setTitle("Select sorting algorithms to display...");
        setLayout(new GridLayout());
        setSize(600, 100);
        
        for (Sorts sort : Sorts.values())
            getContentPane().add(new JCheckBox(sort.toString(), true));

        _ok = new JButton("OK");
        _ok.addActionListener(this);
        getContentPane().add(_ok);
    }

    @Override
    public void actionPerformed(ActionEvent arg0)
    {
        if (arg0.getSource() == _ok)
            dispose();
    }
}
