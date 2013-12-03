package examples.observable;

import java.util.Observable;
import java.util.Observer;

/**
 * Represents a parent
 * 
 * @author Matt
 * 
 */
public class Parent implements Observer
{
    /**
     * Possible states for the parent
     * 
     * @author Matt
     * 
     */
    private enum State
    {
        Happy,
        Unhappy
    }

    private State _state;

    /**
     * Constructor
     */
    public Parent()
    {
        _state = State.Happy;
    }

    /**
     * Called when the baby updates its state
     */
    @Override
    public void update(Observable baby, Object state)
    {
        Baby.State babyState = (Baby.State) state;

        if (babyState == Baby.State.Crying)
            _state = State.Unhappy;
        else if (babyState == Baby.State.Sleeping)
            _state = State.Happy;

        System.out.println("Baby is " + babyState + ", so the parent is " + _state + ".");
    }
}
