package examples.modelView;

/**
 * Simple program for demonstrating model-view separation
 * 
 * @author Matt
 * 
 */
public class Program
{
    /**
     * Entry point for application
     * 
     * @param args
     */
    public static void main(String[] args)
    {
        // create model and view
        Model model = new Model();
        View view = new GuiView();  // to switch to a character-based view, change this line to:  View view = new CharacterBasedView();

        // hook the model and view up to each other
        model.setView(view);
        view.setModel(model);

        // tell the view to start the game
        view.beginGame();
    }
}
