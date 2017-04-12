import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import datastructures.BinaryTree;
import datastructures.BinaryTreeNode;

/**
 * This class, the controller, contains most of the logic of the game.
 * Contains the entire design of the GUI as well.
 * @author Magnier
 *
 */
public class FinalProjectController extends JPanel implements ActionListener
{
	// yes and no buttons
	private JButton noButton;
	private JButton yesButton;
	private JButton restartButton;

	// have a label which will be updated???
	//get the root of the tree from the file reader
	private BinaryTreeNode<String> currentNode;

	// JLabel for the image
	private JLabel imageLabel;
	private JLabel currentText = new JLabel("Question: ");

	// the tree from the other class
	private BinaryTree<String> womenTree;

	// sets the frame size and height
	public static int FRAME_WIDTH = 800;
	public static int FRAME_HEIGHT = 900;

	// the JFrame
	private JFrame theFrame;

	public FinalProjectController() 
	{
		// the border layout
		super(new BorderLayout());

		new XmlFileReader();
		womenTree = XmlFileReader.returnTree("backup.xml");
		currentNode = womenTree.getRoot();

		theFrame = new JFrame();

		add(currentText, BorderLayout.CENTER);

		// create the panel for the buttons 
		JPanel buttonPanel = new JPanel();

		// add text to the buttons
		noButton = new JButton("No");
		yesButton = new JButton("Yes");
		restartButton = new JButton("Restart game!");

		// add buttons to the panel
		buttonPanel.add(noButton);
		buttonPanel.add(yesButton);
		buttonPanel.add(restartButton);
		buttonPanel.add(currentText);

		// adjust alignment- may change this
		buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

		// add button panel to the south part of the controller panel
		add(buttonPanel, BorderLayout.SOUTH);

		// add action listeners to the buttons
		noButton.addActionListener(this);
		yesButton.addActionListener(this);
		restartButton.addActionListener(this);

		// add the button panel to the south part of the frame
		theFrame.add(buttonPanel, BorderLayout.SOUTH);

		// sets the size of the frame
		theFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);

		// add the pic to the frame
		getPicture();

		// make visible and exit
		theFrame.setDefaultCloseOperation(theFrame.EXIT_ON_CLOSE);
		theFrame.setVisible(true);	

		// call refresh display
		refreshDisplay();
	}

	/**
	 * Method to say what happens when the buttons are clicked.
	 * @param e because actionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == noButton)
		{
			if (!currentNode.isLeaf())
			{
				// if there are children, get the left child to traverse down the tree
				currentNode = currentNode.getLeftChild();
			}

			// refresh display
			refreshDisplay();

		}

		if (e.getSource() == yesButton)
		{
			if (!currentNode.isLeaf())
			{	
				// if there is a child, get the right child when yes button is clicked
				currentNode = currentNode.getRightChild();
			}

			else
			{
				// if there are no children, go back to the beginning because program was correct
				currentNode = womenTree.getRoot();
			}

			// refresh display
			refreshDisplay();
		}

		// if it is restart button, start at the beginning by accessing the root
		if (e.getSource() == restartButton)
		{	
			currentNode = womenTree.getRoot();

			// refresh display
			refreshDisplay();
		}

	}

	/**
	 * Method to get the picture from the rest of the class
	 * Sets the photo to a label and adds it to the background
	 */
	public void getPicture()
	{
		ImageIcon pic = new ImageIcon("_87941993_42048v.jpg", "Shirley Chisholm");
		imageLabel = new JLabel(pic);
		theFrame.add(imageLabel, BorderLayout.NORTH);
	}

	/**
	 * Method to refresh display
	 * Repaints and resets the text label to be the current node 
	 */
	public void refreshDisplay()
	{
		currentText.setText("Question: " + currentNode.getData());
		repaint();
	}

}
