import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import datastructures.BinaryTree;
import datastructures.DefaultBinaryTree;
import datastructures.DefaultBinaryTreeNode;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.File;
import java.io.IOException;

/**
 * Class is to parse my xml file for the game.
 * @author Magnier
 */
public class XmlFileReader
{
	// the women tree which will contain the nodes from the xml file
	static DefaultBinaryTree<String> womenTree = new DefaultBinaryTree<String>();

	/**
	 * Main method for testing- will not be run for game.
	 * @param args
	 */
	public static void main(String[] args)
	{
		System.out.println(returnTree("backup.xml").preorderTraversal().toString());
	}

	/**
	 * Method to return the tree.
	 * @param file The file to parse.
	 * @return A binary tree of the file's contents.
	 */
	public static BinaryTree<String> returnTree(String file)
	{
		// put in a try and catch to catch exceptions
		try
		{
			// Setup XML Document
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			File xmlFile = new File(file);
			Document document = builder.parse(xmlFile);

			Node docRoot = document.getDocumentElement();

			parseNodeRecursively(womenTree, null, null, (Element) docRoot);

		} catch (IOException e)
		{
			System.out.println(e);
		} catch (ParserConfigurationException e)
		{
			e.printStackTrace();
		} catch (SAXException e)
		{
			e.printStackTrace();
		}
		// return the tree
		return womenTree;
	}

	/**
	 * Recursive method to parse through the node.
	 * @param womenTree
	 * @param parent
	 * @param left
	 * @param xmlNode
	 */
	private static void parseNodeRecursively(DefaultBinaryTree<String> womenTree, DefaultBinaryTreeNode<String> parent,
			Boolean left, Element xmlNode)
	{
		if (xmlNode == null || xmlNode.getNodeType() != Node.ELEMENT_NODE)
		{
			// base case 1: dead ends that we dont care about
			return;
		}

		String nodeName = xmlNode.getNodeName();

		if (nodeName.equals("leader"))
		{
			// base case 2: encountered a final answer (a leader)
			DefaultBinaryTreeNode<String> leaderNode = new DefaultBinaryTreeNode<>();
			leaderNode.setData(xmlNode.getAttribute("text"));
			if (left)
			{
				// set left child
				parent.setLeftChild(leaderNode);
			} else
			{
				// set right child
				parent.setRightChild(leaderNode);
			}

		} else if (nodeName.equals("answer"))
		{
			// looks for the child node that is either another question or else a leader and makes a recursive call on it
			NodeList answerChildren = xmlNode.getChildNodes();
			for (int i = 0; i < answerChildren.getLength(); i++)
			{
				if (answerChildren.item(i).getNodeType() == Node.ELEMENT_NODE)
				{
					// overrides whatever the question passed in
					left = xmlNode.getAttribute("useranswer").equals("no");

					parseNodeRecursively(womenTree, parent, left, (Element) answerChildren.item(i));
					break;
				}
			}
		} else if (nodeName.equals("question"))
		{ // recursive case 2: encountered a question
			DefaultBinaryTreeNode<String> questionNode = new DefaultBinaryTreeNode<>();
			questionNode.setData(xmlNode.getAttribute("text"));

			// if parent is null, set it as root
			if (parent == null)
			{
				womenTree.setRoot(questionNode);
			}

			// if parent is question
			else if (left)
			{
				parent.setLeftChild(questionNode);
			} else
			{
				parent.setRightChild(questionNode);
			}

			// update the parent to be this so it can be given to the children
			parent = questionNode;

			if (xmlNode.getChildNodes() != null)
			{
				NodeList childList = xmlNode.getChildNodes();

				for (int i = 0; i < childList.getLength(); i++)
				{
					if (childList.item(i).getNodeType() == Node.ELEMENT_NODE)
					{
						parseNodeRecursively(womenTree, parent, null, (Element) childList.item(i));
					}
				}
			}
		}

	}

	/**
	 * The method to parse through the individual node.
	 * @param n
	 * @return theNode
	 */
	private static DefaultBinaryTreeNode<String> parseNode(Node n)
	{
		DefaultBinaryTreeNode<String> theNode = new DefaultBinaryTreeNode<String>();

		// if the node type is element node
		if (n.getNodeType() == Node.ELEMENT_NODE)
		{
			Element currentElt = (Element) n;

			// if it has the attribute text, get that text
			if (currentElt.hasAttribute("text"))
			{
				theNode.setData(currentElt.getAttribute("text"));
			}

			// find out if the node has any children
			// implied base case- if there are no child nodes, the next would be
			// null
			if (n.getChildNodes() != null)
			{
				NodeList nodeList = n.getChildNodes();

				// go through the list
				for (int i = 0; i < nodeList.getLength(); i++)
				{
					if (nodeList.item(i).getNodeName().equals("answer"))
					{
						NodeList answerChildList = nodeList.item(i).getChildNodes();
						Node childOfAnswer = null;
						
						// go through the list and gather element nodes
						for (int j = 0; j < answerChildList.getLength(); j++)
						{
							if (answerChildList.item(j).getNodeType() == Node.ELEMENT_NODE)
							{
								childOfAnswer = answerChildList.item(j);
								break;
							}
						}

						// if answer is no, set left and right children
						if (((Element) nodeList.item(i)).getAttribute("useranswer").equals("no"))
						{
							theNode.setLeftChild(parseNode(childOfAnswer));
						} else
						{
							theNode.setRightChild(parseNode(childOfAnswer));
						}
					}
				}
			}
		}
		// return the node
		return theNode;
	}

}
