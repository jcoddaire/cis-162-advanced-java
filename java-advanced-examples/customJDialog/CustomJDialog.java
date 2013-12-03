package examples.customJDialog;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 * Simple custom JDialog window
 * 
 * @author gerberma
 * 
 */
@SuppressWarnings("serial")
public class CustomJDialog extends JDialog implements ActionListener
{
    /**
     * These are the possible results that can occur when the user closes the window
     * 
     * @author gerberma
     * 
     */
    public enum Result
    {
        OK,
        CANCEL
    }

    private JButton _ok;
    private JButton _cancel;
    private Result _result;

    /**
     * Gets the result of showing the dialog
     * 
     * @return
     */
    public Result getResult()
    {
        return _result;
    }

    /**
     * Constructor
     * 
     * @param parent
     *            Parent frame, over which to center the current dialog
     */
    public CustomJDialog(JFrame parent)
    {
        // this is crucial:  make the JDialog modal (second argument) so that the program will halt until the user clicks OK or Cancel
        super(parent, true);

        // put the two buttons next to each other
        setLayout(new GridLayout());

        _ok = new JButton("OK");
        _ok.addActionListener(this);
        add(_ok);

        _cancel = new JButton("Cancel");
        _cancel.addActionListener(this);
        add(_cancel);

        // default the result to cancel in case the user clicks the close box in the upper right corner
        _result = Result.CANCEL;

        pack();
    }

    @Override
    public void actionPerformed(ActionEvent arg0)
    {
        if (arg0.getSource() == _ok)
        {
            // set result and close window
            _result = Result.OK;
            dispose();
        }
        else if (arg0.getSource() == _cancel)
        {
            // set result and close window
            _result = Result.CANCEL;
            dispose();
        }
    }
}
