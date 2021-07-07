package edu.ncsu.csc216.hloj.model;

import edu.ncsu.csc216.hloj.model.lists.SortedList;

/**
 * An order has a number, the customer that placed the order, and a list of OrderItems. Only one OrderItem with the same MenuItem is allowed—the quantity can be updated as needed.
 * @author Scott Birkner
 */
public class Order implements Comparable<Order> {
	/**
	 * number of the order
	 */
	private int number;
	/**
	 * list of OrderItems
	 */
	private SortedList<OrderItem> items;
	/**
	 * Customer that is placing the order
	 */
	private Customer customer;
	/**
	 * Order that is being created
	 * @param number order number
	 * @param customer customer that the order is for
	 * @throws ModelException if number is less than 1
	 */
	public Order(int number, Customer customer) throws ModelException {
		this.number = number;
		this.customer = customer;
		items = new SortedList<OrderItem>();
		if (number < 1) {
			throw new ModelException("Order numbers must be larger than zero");
		}
	}
	/**
	 * gets the orderItem Index
	 * @param m the menuitem we are getting the Orderitemindex for
	 * @return index for the MenuItem
	 */
	private int getOrderItemIndexForMenuItem(MenuItem m) {
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getMenuItem().equals(m)) {
				return i;
			}
		}
		return -1;
	}
	/**
	 * adds a menu item 
	 * @param m MenuItem being added
	 */
	public void addMenuItem(MenuItem m) {
		int index = getOrderItemIndexForMenuItem(m);
		if (index == -1) {
			items.add(new OrderItem(m));
		}
		else {
			try {
				OrderItem o = items.get(index);
				o.setQuantity(o.getQuantity() + 1);
			} catch (ModelException e) {
				e.printStackTrace();
			}
		}

	}
	/**
	 * removes a menu item from the order
	 * @param m menu item to be removed from the order
	 */
	public void removeMenuItem(MenuItem m) {
		int index = getOrderItemIndexForMenuItem(m);
		if (index > -1) {
			OrderItem o = items.get(index);
			if (o.getQuantity() > 1) {
				try {
					o.setQuantity(o.getQuantity() - 1);
				} catch (ModelException e) {
					e.printStackTrace();
				}
			}
			else {
				items.remove(o);
			}
			}
		}


	
	/**
	 * gets the order number
	 * @return number order number
	 */
	public int getNumber() {
		return number;
	}
	/**
	 * gets the customer for the order
	 * @return customer for the order
	 */
	public Customer getCustomer() {
		return customer;
	}
	/**
	 * returns the order items in array format
	 * @return arr array of orderitems
	 */
	public OrderItem[] getItems() {
		OrderItem[] arr = new OrderItem[items.size()];
		for (int i = 0; i < items.size(); i++) {
			arr[i] = items.get(i);
		}
		return arr;
	}
	/**
	 * gets the total for the price of the Menuitems in the order
	 * @return total order total
	 */
	public double getTotal() {
		double total = 0;
		for (int i = 0; i < items.size(); i++) {
			total += items.get(i).getMenuItem().getPrice() * items.get(i).getQuantity();
		}
		return total;
	}
	/**
	 * returns a string representation of the order class
	 * @return returns the string representation of the Order class
	 */
	public String toString() {
		return "#" + number + " for " + customer.getFirstName() + " " + customer.getLastName() + " - Total: $" + getTotal();
	}
	/**
	 * compares the parameters of two orders and makes sure that they are not the duplicate
	 * @param other other order
	 * @return whether or not they are the same order
	 */
	public int compareTo(Order other) {
		if (this.getNumber() > other.getNumber()) {
			return 1;
		}
		else if (this.getNumber() < other.getNumber()){
			return -1;
		}
		else {
			return 0;
		}
	}
}
