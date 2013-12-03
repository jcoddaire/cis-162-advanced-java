package myFiles;

/*
 * This class is responsible for creating and drawing the piechart that is visible
 * in the GUI
 */
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

public class pieChart extends JComponent {
	
	private static final long serialVersionUID = 1L;
	
	public void paint (int newCount, int angryCount, int regularCount, int busyCount)
	{
		// establishes the total count
		double total = newCount + angryCount + regularCount + busyCount;

		// establishes the degrees for each type of customer
		int newArcDegree = (int) ((newCount/total) * 360);
		int angryArcDegree = (int)((angryCount/total) * 360);
		int busyArcDegree = (int)((busyCount/total) * 360);
		int regularArcDegree = (int)((regularCount/total) * 360);
		
		Graphics g = this.getGraphics();
		
		//sets the arc angle for New Customers
		g.setColor(Color.GREEN);
		g.fillArc(200, 0, 90, 90, 0, newArcDegree);
		// sets the arc angle for Angry Customers
		g.setColor(Color.RED);
		g.fillArc(200, 0, 90, 90, newArcDegree, angryArcDegree);
		// sets the arc angle for Busy customers
		g.setColor(Color.ORANGE);
		g.fillArc(200, 0, 90, 90, angryArcDegree + newArcDegree, busyArcDegree);
		// sets the arc angle for Regular Customers
		g.setColor(Color.BLUE);
		g.fillArc(200, 0, 90, 90, busyArcDegree + angryArcDegree + newArcDegree, regularArcDegree);	
	}
}