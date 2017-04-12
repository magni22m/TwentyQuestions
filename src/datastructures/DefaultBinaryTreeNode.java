package datastructures;



/**
 * This method writes the methods for getData, setData, getLeftChild, getRightChild, and isLeaf
 * @author Magnier
 *
 * @param <T>
 */
public class DefaultBinaryTreeNode<T> implements BinaryTreeNode<T>
{
	private BinaryTreeNode<T> leftChild;
	private BinaryTreeNode<T> rightChild;
	private T data;

	/**
	 * gets the data
	 *
	 * @return data
	 */
	@Override
	public T getData()
	{

		return data;
	}

	/**
	 * sets the data
	 */
	@Override
	public void setData(T data)
	{
		this.data = data;
	}

	/**
	 * gets the left child
	 *
	 * @return leftChild
	 */
	@Override
	public BinaryTreeNode getLeftChild()
	{
		return leftChild;
	}

	/**
	 * gets the right child
	 *
	 * @return rightChild
	 */
	@Override
	public BinaryTreeNode getRightChild()
	{
		return rightChild;
	}

	/**
	 * sets the leftChild as the left
	 */
	@Override
	public void setLeftChild(BinaryTreeNode<T> left)
	{
		this.leftChild = left;
	}

	/**
	 * sets the right child as the right
	 */
	@Override
	public void setRightChild(BinaryTreeNode<T> right)
	{
		this.rightChild = right;
	}

	/**
	 * checks to see if it is a leaf
	 *
	 * @return true if is a leaf, false if not
	 */
	@Override
	public boolean isLeaf()
	{
		// if there are no children, it is a leaf and will return true
		if (leftChild == null && rightChild == null)
		{
			return true;
		} else
		{
			return false;
		}
	}
}
