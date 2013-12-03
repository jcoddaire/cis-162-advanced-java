package examples.sorting.guiDemos;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import examples.sorting.guiDemos.Line;
import examples.sorting.guiDemos.SortSelector.Sorts;
import examples.sorting.guiDemos.animators.BubbleSortAnimator;
import examples.sorting.guiDemos.animators.InsertionSortAnimator;
import examples.sorting.guiDemos.animators.QuickSortAnimator;
import examples.sorting.guiDemos.animators.SelectionSortAnimator;
import examples.sorting.guiDemos.animators.SortAnimator;

/**
 * Unified GUI for all sort animators
 * 
 * @author Matt
 * 
 */
@SuppressWarnings("serial")
public class GUI extends JFrame
{
    public static void main(String[] args)
    {
        new GUI();
    }

    /**
     * Constructor
     * 
     */
    public GUI()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setVisible(true);
        getContentPane().setLayout(new GridLayout());

        // whether or not to use randomly generated data
        boolean useRandom = true;
        try
        {
            useRandom = JOptionPane.showInputDialog(this, "Use randomly generated lengths? (y/n)", "y").equals("y");
        }
        catch (Exception e)
        {
            System.exit(1);
        }

        // add randomly sized lines to content pane
        int[] randomSizes = new int[100];
        Random random = new Random();
        for (int i = 0; i < randomSizes.length; ++i)
            randomSizes[i] = useRandom ? random.nextInt(230) + 20 : i;

        // get sorts to display
        SortSelector selector = new SortSelector(this);
        selector.setVisible(true);

        // create sort panels using the same line sizes
        ArrayList<SortAnimator> sortAnimators = new ArrayList<SortAnimator>();
        for (Sorts sort : selector.getSelectedSorts())
        {
            // use absolute positioning
            JPanel sortPanel = new JPanel();
            sortPanel.setLayout(null);

            // create lines
            Line[] lines = new Line[randomSizes.length];
            for (int i = 0; i < randomSizes.length; ++i)
            {
                // create line and set location
                Line line = new Line(Color.RED, randomSizes[i]);
                line.setLocation(0, Line.LINE_HEIGHT * i);

                // add to content pane
                sortPanel.add(line);

                lines[i] = line;
            }

            // add label to panel
            JLabel label = new JLabel(sort.toString() + " sort");
            label.setSize(100, 20);
            label.setLocation(0, randomSizes.length * Line.LINE_HEIGHT + 40);
            sortPanel.add(label);

            // create animator
            SortAnimator animator = null;
            if (sort.equals(Sorts.Insertion))
                animator = new InsertionSortAnimator();
            else if (sort.equals(Sorts.Selection))
                animator = new SelectionSortAnimator();
            else if (sort.equals(Sorts.Bubble))
                animator = new BubbleSortAnimator();
            else if (sort.equals(Sorts.QuickSort))
                animator = new QuickSortAnimator();

            if (animator != null)
            {
                animator.setLines(lines);
                getContentPane().add(sortPanel);
                sortAnimators.add(animator);
            }
        }

        // get animation speed from user
        int speed = 100;
        try
        {
            speed = Integer.parseInt(JOptionPane.showInputDialog(this, "Animation speed (ms):  ", Integer.toString(speed)));
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(this, "Error:  " + e);
            System.exit(1);
        }

        // start animation timer for all animators
        Timer t = new Timer();
        for (SortAnimator animator : sortAnimators)
            t.schedule(animator, speed, speed);

        pack();
        setSize(1000, 600);
    }
}
