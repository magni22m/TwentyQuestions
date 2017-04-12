package datastructures;

/**
 * This class contains the main method, isEmpty, getRoot, setRoot, and the traversals
 *
 * @param <T>
 * @author Maryanne Magnier
 */
public class DefaultBinaryTree<T> implements BinaryTree<T>
{
	LinkedList<T> inOrderTraversal = new LinkedList<T>();
	LinkedList<T> preOrderTraversal = new LinkedList<T>();
	LinkedList<T> postOrderTraversal = new LinkedList<T>();

	// initializing the node that is the root
	BinaryTreeNode<T> root;

	/**
	 * gets the root
	 *
	 * @return root
	 */
	@Override
	public BinaryTreeNode getRoot()
	{
		return this.root;
	}

	/**
	 * @param root which it passes in so it can be set
	 */
	@Override
	public void setRoot(BinaryTreeNode root)
	{
		this.root = root;
	}

	/**
	 * checks to see if the tree is empty (has no children)
	 *
	 * @return true if empty, false if not empty
	 */
	@Override
	public boolean isEmpty()
	{
		if (root == null)
		{
			return true;
		} else
		{
			return false;
		}
	}

	/**
	 * traverse the left subtree
	 * visit the node only after the left subtree has been completely traversed
	 * after node is visited, traverse right subtree in an inorder traversal manner
	 *
	 * @return inorderTraversal list
	 */
	@Override
	public LinkedList inorderTraversal()
	{
		inorderTraversal(root, inOrderTraversal);
		return inOrderTraversal;
	}

	/**
	 * uses recursion to traverse the node
	 *
	 * @param node
	 * @param traversal
	 */
	private void inorderTraversal(BinaryTreeNode<T> node, LinkedList<T> traversal)
	{
		// recursive case
		if (node.getLeftChild() != null)
		{
			inorderTraversal(node.getLeftChild(), traversal);
		}

		LinkedListNode<T> temp = new LinkedListNode<T>(node.getData());
		traversal.insertLast(temp, node.getData());

		if (node.getRightChild() != null)
		{
			inorderTraversal(node.getRightChild(), traversal);
		}
	}

	/**
	 * uses recursion to traverse the node
	 */
	@Override
	public LinkedList preorderTraversal()
	{
		preorderTraversal(root, preOrderTraversal);
		return preOrderTraversal;
	}

	/**
	 * uses recursion to traverse the node
	 *
	 * @param node
	 * @param traversal
	 */
	private void preorderTraversal(BinaryTreeNode<T> node, LinkedList<T> traversal)
	{
		// recursive case
		LinkedListNode<T> temp = new LinkedListNode<T>(node.getData());
		traversal.insertLast(temp, node.getData());

		if (node.getLeftChild() != null)
		{
			preorderTraversal(node.getLeftChild(), traversal);
		}

		if (node.getRightChild() != null)
		{
			preorderTraversal(node.getRightChild(), traversal);
		}
	}

	/**
	 * uses recursion to traverse the node
	 */
	@Override
	public LinkedList postorderTraversal()
	{
		postorderTraversal(root, postOrderTraversal);
		return postOrderTraversal;
	}

	/**
	 * uses recursion to traverse the node
	 *
	 * @param node
	 * @param traversal
	 */
	private void postorderTraversal(BinaryTreeNode<T> node, LinkedList<T> traversal)
	{
		// recursive case
		if (node.getLeftChild() != null)
		{
			inorderTraversal(node.getLeftChild(), traversal);
		}

		if (node.getRightChild() != null)
		{
			inorderTraversal(node.getRightChild(), traversal);
		}

		LinkedListNode<T> temp = new LinkedListNode<T>(node.getData());
		traversal.insertLast(temp, node.getData());
	}


}
