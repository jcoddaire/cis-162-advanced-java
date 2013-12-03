package examples.observable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.Timer;

/**
 * Represents a simple baby
 * 
 * @author Matt
 * 
 */
public class Baby extends Observable implements ActionListener
{
    /**
     * Possible states of the baby
     * 
     * @author Matt
     * 
     */
    public enum State
    {
        Crying,
        Sleeping
    }

    private State _state;
    private Timer _timer;

    /**
     * Gets the state of this baby
     * 
     * @return
     */
    public State getState()
    {
        return _state;
    }

    /**
     * Constructor
     * 
     * @param state
     */
    public Baby(State state)
    {
        _state = state;
        _timer = new Timer(1000, this);
        _timer.start();
    }

    /**
     * This method is called each time the baby decides to change state
     */
    @Override
    public void actionPerformed(ActionEvent arg0)
    {
        // switch state
        if (_state == State.Crying)
            _state = State.Sleeping;
        else if (_state == State.Sleeping)
            _state = State.Crying;

        // notify all observers of the new state
        setChanged();
        notifyObservers(_state);
    }
}
