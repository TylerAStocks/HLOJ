package edu.ncsu.csc216.hloj.model;
/**
 * creates an Order item based a item off of the menu
 * @author Scott Birkner
 */

public class OrderItem implements Comparable<OrderItem> {
	/**
	 * variable that holds the quantity of an item wanted
	 */
private int quantity;
/**
 * the MenuItem that the Order will have
 */
private MenuItem menuitem;
/**
 * Creates a new Item that is added to an order
 * @param m the menu item added to the order
 */
public OrderItem(MenuItem m) {
	menuitem = m;
	quantity = 1;
}
/**
 * the quantity of the item that was ordered
 * @return the quantity
 */
public int getQuantity() {
	return quantity;
}
/**
 * sets how many of that item the customer wants
 * @param quantity the quantity to set
 * @throws ModelException if quantity is less than 1
 */
public void setQuantity(int quantity) throws ModelException {
	if (quantity < 1) {
		throw new ModelException("The quantity of an item in an order has to be greater than zero");
	}
	this.quantity = quantity;
//	menuitem.setPrice(quantity * menuitem.getPrice());

}
/**
 * gets the menu item being added to the order
 * @return the menu item being added
 */
public MenuItem getMenuItem() {
	return menuitem;
}
/**
 * compareTo compares two orderItems and makes sure that they aren't the same
 * @param other another orderItem
 * @return 0 or 1, whether or not they compare
 */
@Override
public int compareTo(OrderItem other) {
	return this.getMenuItem().compareTo(other.getMenuItem());	
}

}
