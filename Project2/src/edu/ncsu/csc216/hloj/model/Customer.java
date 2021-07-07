package edu.ncsu.csc216.hloj.model;
/**
 * Customer class creates a Customer Object for the Customer
 * Manager to use
 * @author Scott Birkner
 */
public class Customer implements Comparable<Customer> {
	/**
	 * firstName the firstName of the customer
	 */
	private String firstName;
	/**
	 * lastName the lastName of the customer
	 */
	private String lastName;
	/**
	 * id the user id of the customer
	 */
	private String id;
	/**
	 * Customer constructor will create a new Customer
	 * @param firstName first name of new customer
	 * @param lastName last name of the customer
	 * @param id id of the customer
	 * @throws ModelException if any of the setters are violated
	 */
	public Customer(String firstName, String lastName, String id) throws ModelException {
		setFirstName(firstName);
		setLastName(lastName);
		setId(id);
	}
	/**
	 * gets hte customers first name
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * sets the firstName of the customer
	 * @param firstName the firstName of the customer
	 * @throws ModelException on empty string or a string with only white space
	 */
	public void setFirstName(String firstName) throws ModelException {
		if (firstName.trim().length() == 0) {
			throw new ModelException("The first name of the customer cannot be empty");
		}
		this.firstName = firstName.trim();
	}
	/**
	 * gets the last name of the customer
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * sets the lastName of the customer
	 * @param lastName the lastName to set
	 * @throws ModelException on empty String
	 */
	public void setLastName(String lastName) throws ModelException{
		if (lastName.trim().length() == 0) {
			throw new ModelException("The last name of the customer cannot be empty");
		}
		this.lastName = lastName.trim();
	}
	/**
	 * gets the id of the customer
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * sets the id of the customer
	 * @param id the id to set
	 * @throws ModelException on empty String
	 */
	public void setId(String id) throws ModelException {
		if (id.trim().length() == 0) {
			throw new ModelException("The id of the customer cannot be empty");
		}
		this.id = id.trim();
	}
	/**
	 * String representation of customer class
	 * @return representation formatted as "firstName lastName (id)"
	 */
	public String toString() {
		return getFirstName() + " " + getLastName() + " (" + getId() + ")";  
	}
	/**
	 * compares customer by last name, then first name
	 * @param other another customer to compare to
	 * @return -1 , 1, 0 the comparison value of the customer
	 */
	public int compareTo(Customer other) {
		if (this.getLastName().toUpperCase().compareTo(other.getLastName().toUpperCase()) == 0) {
			return this.getFirstName().toUpperCase().compareTo(other.getFirstName().toUpperCase());
		}
		else {
			return this.getLastName().toUpperCase().compareTo(other.getLastName().toUpperCase());
		}
	}
	
}
