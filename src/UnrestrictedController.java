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
import datastructures.DefaultBinaryTreeNode;


/**
 * Controller for unrestricted version, most of the logic from the game is here.
 * Designs GUI layout as well.
 * @author Magnier
 */
public class UnrestrictedController extends JPanel implements ActionListener
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

	//womenTree from XmlFileReader class
	private BinaryTree<String> womenTree;

	// sets the frame size and height
	public static int FRAME_WIDTH = 750;
	public static int FRAME_HEIGHT = 900;

	// the JFrame
	private JFrame theFrame;

	// String of values for user input; global variables so they can be accessed in the scope of this entire class
	private String value1;
	private String value2;
	private String value3;

	// string for last data
	private String lastData;

	// new nodes for both the questions and the answers
	BinaryTreeNode<String> newQNode = new DefaultBinaryTreeNode<String>();
	BinaryTreeNode<String> newAnswerNode = new DefaultBinaryTreeNode<String>();

	/**
	 * Controller constructor- contains almost all of the GUI components.
	 */
	public UnrestrictedController() 
	{
		// the border layout
		super(new BorderLayout());

		// to access the tree from the other class
		new XmlFileReader();
		womenTree = XmlFileReader.returnTree("backup.xml");

		// set current node initially equal to the root of womenTree
		currentNode = womenTree.getRoot();

		// create the JFrame
		theFrame = new JFrame();

		// add the JLabel, currentText, to the center of the controller panel
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

		// add the button panel to the south side of the screen
		add(buttonPanel, BorderLayout.SOUTH);

		// add action listeners to the buttons
		noButton.addActionListener(this);
		yesButton.addActionListener(this);
		restartButton.addActionListener(this);

		// add the button panel to the south side of the frame
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
	 * @param e because action event
	 * Method that tells the buttons how to behave.
	 */
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == noButton)
		{
			if (!currentNode.isLeaf())
			{
				// if current node is not a leaf, get the left child because "no's" are set to the left
				currentNode = currentNode.getLeftChild();
			}

			else
			{
				// else, call the optionsMethod
				optionsMethod();
			}

			// refresh the display to update changes
			refreshDisplay();
		}

		if (e.getSource() == yesButton)
		{
			if (!currentNode.isLeaf())
			{		
				// if current node is not a leaf, get the right child because "yes's" are set to the right
				currentNode = currentNode.getRightChild();
			}

			else
			{
				// if game was correct, start from the beginning
				currentNode = womenTree.getRoot();
			}

			// refresh the display
			refreshDisplay();
		}

		if (e.getSource() == restartButton)
		{	
			// if restart button is clicked, merely start at the beginning by accessing the root
			currentNode = womenTree.getRoot();

			// refresh display
			refreshDisplay();
		}

	}

	/**
	 * The method to access the photograph, add it to a label, and paste it to the background.
	 */
	public void getPicture()
	{
		// get photo from the folder
		ImageIcon pic = new ImageIcon("_87941993_42048v.jpg", "thephoto");

		imageLabel = new JLabel(pic);
		theFrame.add(imageLabel, BorderLayout.NORTH);
	}

	/**
	 * Refresh display method sets the label to be the data of the current node.
	 */
	public void refreshDisplay()
	{
		// set current text to be the current node
		currentText.setText("Question: " + currentNode.getData());
		
		// call repaint to update this
		repaint();
	}

	/**
	 * Options method calls the popup class which allows the user to write in input.
	 */
	public void optionsMethod()
	{
		JTextField field1 = new JTextField();
		JTextField field2 = new JTextField();
		JTextField field3 = new JTextField();

		Object[] message = {
				"Of whom were you thinking? ", field1,
				"What is a defining question? ", field2,
				"Is the answer yes or no?", field3,

		};
		int option = JOptionPane.showConfirmDialog(getParent(), message, "Enter all your values", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION)
		{
			value1 = "Were you thinking of " + field1.getText()+"?";
			value2 = field2.getText();
			value3 = field3.getText();

			System.out.println("Value 1 is: " + value1.toString());
			System.out.println("Value 2 is: " + value2.toString());
			System.out.println("Value 3 is: " + value3.toString());

			if (!value1.toString().equals(null) && !value2.toString().equals(null) && !value3.toString().equals(null) )
			{
				addChoice();
			}
			
		}			
	}

	/**
	 * Add choice takes whatever the user inputs in the options method and changes the game based on it.
	 */
	public void addChoice()
	{
		// check to see if current node is leaf
		if (currentNode.isLeaf())
		{
			lastData = currentNode.getData();
			currentNode.setData(value2);

			if (value3.equals("yes"))
			{		
				// creating new question node
				BinaryTreeNode<String> newQNode = new DefaultBinaryTreeNode<String>(); 
				// set as the new question
				newQNode.setData(value1);

				// setting the new Q 
				currentNode.setRightChild(newQNode);

				BinaryTreeNode<String> oldAns = new DefaultBinaryTreeNode<String>(); 
				oldAns.setData(lastData); 
				// setting left child as the new answer
				currentNode.setLeftChild(oldAns);

				System.out.println("old ans: " + oldAns.getData().toString());
				System.out.println("new question: " + newQNode.getData().toString());
				System.out.println("currentNode: " + currentNode.getData().toString());
			}

			if (value3.equals("no"))
			{
				// creating new question node
				BinaryTreeNode<String> newQNode = new DefaultBinaryTreeNode<String>(); 
				// set as the new question
				newQNode.setData(value1);

				// set new question node as the left child of the current node
				currentNode.setLeftChild(newQNode);

				BinaryTreeNode<String> oldAns = new DefaultBinaryTreeNode<String>(); 

				oldAns.setData(lastData); 
				currentNode.setRightChild(oldAns);

				System.out.println("old ans: " + oldAns.getData().toString());
				System.out.println("new question: " + newQNode.getData().toString());
				System.out.println("current node : " + currentNode.getData().toString());
			}
		}
	}
}
