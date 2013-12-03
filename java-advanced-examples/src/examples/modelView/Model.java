package examples.modelView;

/**
 * Model of a trivial game. All the user needs to do to win is click.
 * 
 * @author Matt
 *
 */
public class Model
{
    /**
     * This is the view of the game
     */
    private View _view;

    /**
     * Set the view for this model
     * @param view
     */
    public void setView(View view)
    {
        _view = view;
    }
    
    /**
     * Track the number of times the user has clicked
     */
    private int _clickCount;

    /**
     * Constructor
     */
    public Model()
    {
        // no clicks to begin with
        _clickCount = 0;
    }

    /**
     * This is called when the view wants to notify this model that the user clicked
     */
    public void userClicked()
    {
        // check if the user has clicked enough
        if (++_clickCount == 5)
            _view.gameOver();
    }
}
