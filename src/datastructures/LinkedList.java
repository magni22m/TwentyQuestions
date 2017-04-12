package datastructures;



/**
 * @author: Magnier 
 * This class will implement a generic LinkedList class that has exactly one instance field (head). The
 * array will support the methods below.
 */

public class LinkedList<T>
{

	// defines the head, no matter the type may be
	private LinkedListNode<T> head;

	/**
	 * Gets data stored in head node of the list.
	 * @return head.getData() or null
	 */
	public T getFirst()
	{
		if (head != null)
		{
			return head.getData();
		} else
		{
			return null;
		}
	}

	/**
	 * Gets the head node of the list.
	 * @return head
	 */
	public LinkedListNode<T> getFirstNode()
	{
		return head;
	}

	/**
	 * Gets data stored in last node of list.
	 * @return getLastNode().getData()
	 */
	public T getLast()
	{
		return getLastNode().getData();
	}

	/**
	 * Gets the tail node of the list.
	 * @return theNode
	 */
	public LinkedListNode<T> getLastNode()
	{
		// set the node originally to be the head
		LinkedListNode<T> theNode = head;

		// while the next one is not null, update
		while (theNode.getNext() != null)
		{
			theNode = theNode.getNext();
		}
		// return the last node in the list
		return theNode;
	}

	/**
	 * Inserts a new node with data at the head of the list.
	 * @param data
	 */
	public void insertFirst(T data)
	{
		// make a new node
		LinkedListNode<T> newNode = new LinkedListNode<T>(data);

		// if the head is not null, have the new one point to the old first node
		if (head != null)
		{
			newNode.setNext(head);
		}

		// if the list is empty, the new node becomes the head of the list
		head = newNode;
	}

	/**
	 * Inserts a new node with data after currentNode.
	 * @param currentNode
	 * @param data
	 */
	public void insertAfter(LinkedListNode<T> currentNode, T data)
	{
		// create a new node
		LinkedListNode<T> newNode = new LinkedListNode<T>(data);

		// set the new node to be pointing at what the current node used to be pointing at
		newNode.setNext(currentNode.getNext());

		// set the current node to be pointing at the new node
		currentNode.setNext(newNode);

	}

	/**
	 * Inserts a new node with data at the end of the list.
	 */
	public void insertLast(LinkedListNode<T> temp, T data)
	{
		// create a new node
		LinkedListNode<T> newNode = new LinkedListNode<T>(data);

		// if there is no head, newNode becomes the head
		if (head == null)
		{
			head = newNode;
		}

		// else while there is still something at the end, keep updating to land on the last node
		else
		{
			temp = head;
			while (temp.getNext() != null)
			{
				temp = temp.getNext();

			}
			// have the temp point to the new one, thus making the newNode the last one in the list
			temp.setNext(newNode);
		}
	}

	/**
	 * Removes the first node.
	 */
	public void deleteFirst()
	{
		// set the new head to be the one after head, essentially erasing the old head
		if (head != null)
		{
			head = head.getNext();
		}
	}

	/**
	 * Removes the last node.
	 */
	public void deleteLast()
	{
		// make a temp variable
		LinkedListNode<T> temp;

		// set temp equal to the head
		temp = head;
		// get the second to last
		while (temp.getNext().getNext() != null)
		{
			temp = temp.getNext();
		}
		// have the second to last point to null, thus deleting the last one
		temp.setNext(null);
	}

	/**
	 * Removes node following currentNode.
	 * If no node exists (i.e., currentNode is the tail), do nothing.
	 * @param currentNode
	 */
	public void deleteNext(LinkedListNode<T> currentNode)
	{
		// if there are 0 or 1 items in the list, return and nothing happens
		if (currentNode == null || currentNode.getNext() == null)
		{
			return;
		}

		// set current node to be essentially skipping over the one that used to be the next one
		currentNode.setNext(currentNode.getNext().getNext());
	}

	/**
	 * Returns the number of nodes in this list.
	 * Note: I changed the name from size to count for clarification.
	 * @return count
	 */
	public int size()
	{
		// set currentNode initially to be head
		LinkedListNode<T> currentNode = head;

		// count begins at 0
		int count = 0;

		// add 1 to count so head is counted
		if (head != null)
		{
			count = 1;
		}

		// while there is something behind it, update count
		while (currentNode.getNext() != null)
		{
			count++;
			currentNode = currentNode.getNext();
		}
		// return the count
		return count;

	}

	/**
	 * Checks if list is empty.
	 * Returns true if list contains no items.
	 * @return true or false depending
	 */
	public boolean isEmpty()
	{
		//If empty, returns true
		if (head == null)
		{
			return true;
		}
		// if not, returns false
		return false;
	}

	/**
	 * Returns a String representation of the list.
	 *@return newString
	 */
	public String toString()
	{
		String newString = "";
		LinkedListNode<T> currentNode = head;

		while (currentNode != null)
		{
			//add current node to newString
			newString = newString + currentNode.getData().toString() + " -> \n";
			currentNode = currentNode.getNext();
		}

		return newString;
	}
}