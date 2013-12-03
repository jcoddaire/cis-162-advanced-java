

/**
 * This interface is used for conformity with the Model/View separation
 * theory of programming organization. It is implemented by whatever
 * view the game uses and contains methods that all views for the game
 * must contain.
 * @author Jake Pain
 *
 */
public interface View {
	
	/**
	 *  Sets the model (game logic) for this view
	 *  @param model    Class containing game logic
	 */
	void setModel(Model model);
	
	
	/**
	 *  Starts the game
	 */
	void startGame();
	
	
	/**
	 *  Called by model when it detects that the game has ended 
	 */
	void gameOver();
	
	


}
