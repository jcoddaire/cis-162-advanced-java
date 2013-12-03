package examples.modelView;

import java.util.Scanner;

/**
 * A character-based view of the model
 * 
 * @author Matt
 * 
 */
public class CharacterBasedView implements View
{
    /**
     * Reference to the model
     */
    private Model _model;
    
    /**
     * Sets the model for the current view
     */
    public void setModel(Model model)
    {
        _model = model;
    }

    /**
     * Whether or not the current game is over
     */
    private boolean _gameOver;

    /**
     * Begins the game
     */
    public void beginGame()
    {
        // game has begun...notify user
        System.out.println("Game has begun!");
        _gameOver = false;

        // read lines of input from System.in until the game is over
        Scanner inputScanner = new Scanner(System.in);
        while (!_gameOver)
        {
            // prompt for user's command
            System.out.print("Command:  ");

            // read user's command
            String input = inputScanner.nextLine();

            // if the user entered the click command, notify model
            if (input.equals("click"))
                _model.userClicked();
        }
    }

    /**
     * This is called when the model wants to notify us that the game has ended
     */
    @Override
    public void gameOver()
    {
        // notify user
        System.out.println("Game over!");

        // game is over
        _gameOver = true;
    }
}
