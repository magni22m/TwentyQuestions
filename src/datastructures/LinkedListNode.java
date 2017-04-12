package datastructures;


import java.util.LinkedList;
import java.awt.*;
import javax.swing.*;

/**
 * @author Magnier
 * This class implements a generic LinkedListNode that has two instance fields: data and next
 * The constructor does not need to initialize data and next
 * Instead, it should implement getter and setter methods for data and next
 */

public class LinkedListNode<T> {

	//Defines the data being used
	private T data;

	//Defines next node
	LinkedListNode<T> next;

	/**
	 * Constructor for the LinkedList node.
	 * @param data for T data
	 */
	public LinkedListNode(T data) 
	{
		this.data = data;
	}


	/**
	 * This method sets the data stored at this node.
	 * @param data for T data
	 */
	public void setData(T data) 
	{
		this.data = data;
	}

	/**
	 * Gets the data stored at this node.
	 * @return data 
	 */
	public T getData() 
	{
		return data;
	}

	/**
	 * Sets the next pointer to passed node.
	 * @param node, an instance of LinkedListNode
	 */
	public void setNext( LinkedListNode<T> node) 
	{
		this.next = node;
	}

	/**
	 * Gets (pointer to) next node.
	 * @return next
	 */
	public LinkedListNode<T> getNext() 
	{
		return next;
	}

	/**
	 * Returns a String representation of this node.
	 * @return data to string
	 */
	public String toString() 
	{
		return data.toString();
	}

}