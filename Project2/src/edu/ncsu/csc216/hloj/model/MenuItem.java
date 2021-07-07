package edu.ncsu.csc216.hloj.model;
/**
 * holds name type and price of a menu item.
 * @author Scott Birkner
 */
public class MenuItem implements Comparable<MenuItem> {
/**
 * String of type
 */
	private String type;
	/**
	 * String of name
	 */
	private String name;
	/**
	 * double of price
	 */
	private double price;
	/**
	 * MenuItem creates a new menu item
	 * @param type type of menu item
	 * @param name name of menu item
	 * @param price price of the menu item
	 * @throws ModelException on empty string or a string with only white space
	 */
	public MenuItem (String type, String name, double price) throws ModelException {
		setType(type);
		setName(name);
		setPrice(price);
	}
	/**
	 * gets the type of the menu item
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * sets the type of menu item
	 * @param type the type to set
	 * @throws ModelException on empty string or a string with only white space
	 */
	public void setType(String type) throws ModelException {
		if (type.trim().length() == 0) {
			throw new ModelException("The type of the menu item cannot be empty");
		}
		this.type = type.trim();
	}
	/**
	 * gets the name of the menu item
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * sets the name of the menu item
	 * @param name the name to set
 	 * @throws ModelException on empty string or a string with only white space
	 */
	public void setName(String name) throws ModelException {
		if (name.trim().length() == 0) {
			throw new ModelException("The name of the menu item cannot be empty");
		}
		this.name = name.trim();
	}
	/**
	 * gets the price of the item
	 * @return the price
 	 * @throws ModelException on empty string or a string with only white space
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * sets the price of the item
	 * @param price the price to set
	 * @throws ModelException on empty string or a string with only white space
	 */
	public void setPrice(double price) throws ModelException {
		if (price <= 0) {
			throw new ModelException("The price of the menu item must be greater than zero");
		}
		this.price = price;
	}
	/**
	 * String representation of MenuItem class
	 * @return representation formatted as "(type) name -$price"
	 */
	public String toString() {
		return "(" + type + ") " + name + " - $" + price;
	}
	/**
	 * compares two menuItems, first by type then by name
	 * @param m the other menu item
	 * @return int whether the item comes before or after the other one
	 */
	@Override
	public int compareTo(MenuItem m) {
		if (this.getType().toUpperCase().compareTo(m.getType().toUpperCase()) >= 1) {
			return 1;
		}
		else if (this.getType().toUpperCase().compareTo(m.getType().toUpperCase()) <= -1) {
			return -1;
		}
		else {
			return 0;
		}
	}
	
}
