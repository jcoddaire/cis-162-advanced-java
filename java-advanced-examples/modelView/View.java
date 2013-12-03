package examples.modelView;

/**
 * Interface for all views of the game
 * 
 * @author Matt
 *
 */
public interface View
{   
    /**
     * Set the model for this view
     * @param model
     */
    void setModel(Model model);
    
    /**
     * Begin game
     */
    void beginGame();
    
    /**
     * This is called when the model wants to notify us that the game has ended
     */
    void gameOver();    
}
