


/**
 * Contains game logic and code necessary for determining if a player
 * has won a game.
 * @author Jake Pain
 *
 */
public class Model {

	private GUIView view;
	private Board board;

	public void setView(GUIView view) 
	{
		this.view = view;
	}

	public void setBoard(Board board)
	{
		this.board = board;
	}
	public int getSize() 
	{ 
		return this.getSize(); 
	}

	// checks to see if the player won.
	public  boolean isWinner()
	{
		boolean valid = false;
		int count = 0;
		//checks for the Horizonal win
		for (int row = 0; row < board.getBoardSize(); ++row)
		{
			// scroll through columns...
			for (int col = 0; col < board.getBoardSize(); ++col)
			{
				if ( board.getLabelArray()[row][col].getPlayer() == GUIView.getCurrentPlayer())
				{
					count++;
					if (count == view.getConnectsNeeded())
					{
						valid = true;
						break;
					}
				}
				else
				{
					count = 0;
				}
			}
		}

		//checks for the vertical win
		count = 0;
		for (int col = 0; col < board.getBoardSize(); ++col)
		{
			for (int row = 0; row < board.getBoardSize(); ++row)
			{
				if (board.getLabelArray()[row][col].getPlayer() == GUIView.getCurrentPlayer())
				{
					count++;
					if (count == view.getConnectsNeeded())
					{
						valid = true;
						break;
					}
				}
				else
				{
					count = 0;
				}
			}
		}

		// the idea is to check in the SouthEast direction from square one... I didn't test it yet.
		// The NorthEast Direction has yet to be created.
		count = 0;

		for (int col = 0; col < (board.getBoardSize() - (view.getConnectsNeeded()-1)); col++)
		{

			for (int row = 0; row < (board.getBoardSize() - (view.getConnectsNeeded()-1)); row++)
			{

				int r = row;
				for (int c = col; c < view.getConnectsNeeded(); c++ )
				{
					if ( board.getLabelArray()[r][c].getPlayer() == GUIView.getCurrentPlayer())
					{
						count++;
						if (count == view.getConnectsNeeded())
						{
							valid = true;
							return valid;
						}
					}
					else
					{
						count = 0;
					}
					r++;
				}
			}
		}


		count = 0;
		for (int row = 0; row > (view.getConnectsNeeded()-1); --row)
		{
			for (int col = board.getBoardSize(); col > (view.getConnectsNeeded()-1); --col)
			{

				int r = row;
				int c = col;
				System.out.println("Right before while loop.");
				if(board.getLabelArray()[r][c].getPlayer() == GUIView.getCurrentPlayer())
				{
					++count;

					System.out.println(count);
					System.out.println("Row: " + r + " Col: " + c);

					// move to the next location diagonally
					--r;
					--c;
				}

				if(count == view.getConnectsNeeded())
				{
					valid = true;
					return valid;
				}
			}
		}
		return valid;
	}
}
