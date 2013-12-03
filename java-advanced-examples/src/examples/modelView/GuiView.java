package examples.modelView;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * A GUI view of the game model
 * 
 * @author gerberma
 * 
 */
public class GuiView implements View, ActionListener
{
    /**
     * A reference to the model
     */
    private Model _model;

    /**
     * Sets the model for this view
     */
    public void setModel(Model model)
    {
        _model = model;
    }
    
    /**
     * Need a reference to the quit menu item to check if it initiated an action
     */
    private JButton _button;

    /**
     * Constructor
     */
    public GuiView()
    {
        // program window
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // add a button to click
        _button = new JButton("Click me!");
        _button.addActionListener(this);
        window.getContentPane().add(_button);

        // show window
        window.setPreferredSize(new Dimension(200, 200));
        window.pack();
        window.setVisible(true);
    }
    
    /**
     * Begins the game
     */
    public void beginGame()
    {
        JOptionPane.showMessageDialog(null, "Game has begun!");
    }

    /**
     * Process actions
     */
    @Override
    public void actionPerformed(ActionEvent arg0)
    {
        // notify the model that the button was clicked
        if (arg0.getSource() == _button)
            _model.userClicked();
    }

    /**
     * This is called when the model wants to notify us that the game has ended
     */
    @Override
    public void gameOver()
    {
        // notify user
        JOptionPane.showMessageDialog(null, "Game over!");
        
        // exit
        System.exit(0);
    }
}
