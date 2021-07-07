package edu.ncsu.csc216.hloj.model.lists;

import java.util.AbstractList;
/**
 * UniqueList creates a list of values that cannot be repeated
 * @author Scotty
 *
 * @param <E> an object that UniqueList uses
 */
public class UniqueList<E> extends AbstractList<E> {
	/**
	 * specifies an initial capacity of 10
	 */
	private static final int INIT_CAPACITY = 10;
	/**
	 * the array that will be used within the uniquelist class
	 */
	private E[] list;
	/**
	 * size of the array
	 */
	private int size;
	/**
	 * constructs a new unique list with an initial capacity of 10
	 */
	@SuppressWarnings("unchecked")
	public UniqueList() {
		list = (E[])(new Object[INIT_CAPACITY]);
		size = 0;
	}
	/**
	 * constructs a new unique list with a specified capacity 
	 * @param capacity capacity of the unique list
	 */
	@SuppressWarnings("unchecked")
	public UniqueList(int capacity) {
		if (capacity <= 0) {
			throw new IllegalArgumentException();
		}
		list = (E[])(new Object[capacity]);
		size = 0;
	}
	/**
	 * tries to add a new value to end of the list
	 * @param value the object to be added
	 * @return boolean if it is true or not
	 */
	public boolean add(E value) {
		if (value == null) {
			throw new NullPointerException();
		}
		if (list.length <= size()) {
			increaseCapacity();
		}
		int sizetemp = size();
		for (int i = 0; i < sizetemp; i++)
		{
			if (list[i].equals(value) || list[i] == value) {
				throw new IllegalArgumentException();
			}
		}
		list[size] = value;
		size++;
		return true;
	}
	/**
	 * tries to add a new value at a certain index
	 * @param idx index to be added
	 * @param value the object to be added
	 */
	public void add(int idx, E value) {
		if (value == null) {
			throw new NullPointerException();
		}
		if (idx > size || idx < 0) {
			throw new IndexOutOfBoundsException();
		}
		if (list.length <= size()) {
			increaseCapacity();
		}
		int sizetemp = size();
		for (int i = 0; i < sizetemp; i++)
		{
			if (list[i].equals(value) || list[i] == value) {
				throw new IllegalArgumentException();
			}
		}
		for (int i = sizetemp - 1; i >= idx; i--)
		{
			list[i + 1] = list[i];
		}
		list[idx] = value;
		size++;
	}
	/**
	 * gets the size of the array
	 * @return size size of the array
	 */
	@Override
	public int size() {
		return size;
	}
	/**
	 * removed an element from the list
	 * @param index index of item being removed
	 * @return the value of the element being removed
	 */
	public E remove(int index) {
		validateIndex(index);
		E old = list[index];
		if (index == size - 1) {
			size--;
			return old;
		}
		for(int i = index; i < size; i++) {
			list[i] = list[i + 1];
		}
		size--;
		return old;
	}
	/**
	 * gets the element at an index
	 * @param index the index needed to get the element at
	 * @return the value of the element at that position
	 */
	public E get(int index) {
		validateIndex(index);
		return list[index];
	}
	/**
	 * validates the index and makes sure that it returns what it is supposed to
	 * @param index the index that needs to be validated
	 */
	private void validateIndex(int index) {
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
	}
	/**
	 * increases the capacity of the array, helper method for other methods
	 * within the UniqueList class
	 */
	private void increaseCapacity() {
		@SuppressWarnings("unchecked")
		E[] newList = (E[])(new Object[list.length * 2]);
		for (int i = 0; i < list.length; i++) {
			newList[i] = list[i];
		}
		
		list = newList;
	}
	
	

}
