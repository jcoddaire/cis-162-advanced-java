import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * This class contains the logic for adding and deleting buttons
 * from the Connect 4 "playing field" or board.
 * 
 * @author Jake Pain
 *
 */
@SuppressWarnings("serial")
public class Board extends JPanel
{
	private static CustomLabel[][] LabelArray;
	private ArrayList<Point> undoArray;
	private int[] rowsLeft;
	private int boardSize;
	private Point point;
	private boolean canUndo;
	

	public Board()
	{
		undoArray = new ArrayList<Point>(625);
	}
	

	/**
	 * This method is used to add chips to the game board
	 * 
	 * 
	 * @param row     row in which to place the chip
	 * @param col     column in which to place a chip
	 * @param player     the "owner" of the chip - used to determine color
	 */
	public void add(int row, int col, Player player)
	{
		//System.out.print(LabelArray.length);
		CustomLabel label = LabelArray[row][col];
		label.setColor(player.getChipColor());
		label.setPlayer(player);
		rowsLeft[col]--;
		
		point = new Point(row, col);
		undoArray.add(point);
	}
	
	public void undo()
	{
		if (!undoArray.isEmpty())
		{
			
			LabelArray[undoArray.get(undoArray.size()-1).x]
			           [undoArray.get(undoArray.size()-1).y].setColor(Color.white);
			rowsLeft[undoArray.get(undoArray.size()-1).y]++;
			
			undoArray.remove(undoArray.size()-1);
		}
		else
		{
			drawGrid();
		}
		
		// if there is nothing left to undo... 
		if (undoArray.isEmpty())
		{
			// used to disable the undo button in the GUIView class
			canUndo = false;
		}
		else
		{
			// used to enable the undo button in the GUIView class
			canUndo = true;
		}
	}
	
	// initializes the colLeft array, which stores the number of
	// unfilled spaces in each column on the board

	public void drawGrid()
	{
		removeAll();
		
		setLayout(new GridLayout(boardSize, boardSize));
		LabelArray = new CustomLabel[boardSize][boardSize];
		rowsLeft = new int[boardSize];
		
		undoArray.clear();
		
		for (int i = 0; i < boardSize; ++i)
		{
			rowsLeft[i] = boardSize;
		}
		
		for (int row = 0; row < boardSize; ++row) {
			for (int col = 0; col < boardSize; ++col)
			{
				CustomLabel newlabel = new CustomLabel(Color.white);
				add(newlabel);
				
				LabelArray[row][col] = newlabel;
				
			}			
		}
		repaint();
		revalidate();
	}

	
	/**
	 * Return the number of spaces left in the column
	 * 
	 * @return the colLeft
	 */
	public int getRowsLeft(int pos) {
		return rowsLeft[pos];
	}

	/**
	 * 
	 * @param pos     the column number to fill
	 */
	public void fillColumn(int pos) {
		this.rowsLeft[pos]++;
	}
	

	/**
	 * @return the labelArrayList
	 */
	public CustomLabel[][] getLabelArray() {
		return LabelArray;
	}
	
	
	/**
	 * @param boardSize the boardSize to set
	 */
	public void setBoardSize(int boardSize) {
		this.boardSize = boardSize;
	}

	/**
	 * @return the boardSize
	 */
	public int getBoardSize() {
		return boardSize;
	}


	/**
	 * @param canUndo the canUndo to set
	 */
	public void setCanUndo(boolean canUndo) {
		this.canUndo = canUndo;
	}


	/**
	 * @return the canUndo
	 */
	public boolean getCanUndo() {
		return canUndo;
	}
}
