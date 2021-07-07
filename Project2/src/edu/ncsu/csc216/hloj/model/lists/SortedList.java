package edu.ncsu.csc216.hloj.model.lists;

import java.util.AbstractList;

/**
 * Creates a SortedList of which extends AbstractList
 * @author Scott Birkner
 * @param <E> the object the SortedList can be created for
 */
public class SortedList<E extends Comparable<E>> extends AbstractList<E> {
	/**
	 * front the ListNode at front of the LinkedList
	 */
	private ListNode list;
	/**
	 * size of the LinkedList
	 */
	int size;
	/**
	 * Initializes a List
	 */
	public SortedList() {
		list = null;
		size = 0;
	}
	/**
	 * adds a value to a certain index
	 * @param value the value of the object being added
	 */
	@Override
	public boolean add(E value) {

		if (value == null) {
			throw new IllegalArgumentException();
		}
		if (list == null) {
			ListNode newNode = new ListNode(value);
			list = newNode;
		}
		else if (value.compareTo(list.data) < 0) {
			ListNode newNode = new ListNode(value, list);
			list = newNode;
			
		}
        else {
	        ListNode current = list; // Use current reference

	        while (current.next != null && value.compareTo(current.next.data) >= 0) {
	            current = current.next;
	        }
	        ListNode newNode = new ListNode(value, current.next); // Add the node so that it points to the next
	        current.next = newNode;
	    }
	    size++;
	    return true;

	}

	/**
	 * Removes an item at a certain index
	 * @param idx the index of 
	 * @return E the element that was removed
	 */
	public E remove(int idx) {
		E old;
		if (idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException();
		}
	    if (idx == 0) {
	    	old = list.data;
	        list = list.next;
		    size--;
	        return old;

	    } else { // removing from elsewhere in the list

	        ListNode current = list;

	        for (int i = 0; i < idx - 1; i++) {

	            current = current.next;

	        }
	        old = current.next.data;
	        current.next = current.next.next;
	        size--;
	        return old;
	    }
	    
	}
 /**
  * gets a value at an index in the list
  * @param index the index that we're getting
  * @return the value at that index
  */
	public E get(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
			
		ListNode current = list;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		return current.data;
	}
	/**
	 * returns the size of list
	 * @return the size of the list
	 */
	public int size() {
		return size; 
	}

private class ListNode {
	/**
	 * data variable that stores an object
	 */
	private E data;
	/**
	 * next is the ListNode variable
	 */
	private ListNode next;
	/**
	 * ListNode returned without a next parameter, so this is just placed at the end of the list
	 * @param data the data that is being added at that position
	 */
	private ListNode(E data) {
		this(data, null);
	}
	/**
	 * ListNode that is added before a certain ListNode
	 * @param data the data at that is being added at that position
	 * @param next the ListNode that is after the one being placed
	 */
	private ListNode(E data, ListNode next) {
		this.data = data;
		this.next = next;
	}
}

}
