package edu.ncsu.csc216.hloj.model.manager;

import edu.ncsu.csc216.hloj.model.Customer;
import edu.ncsu.csc216.hloj.model.ModelException;
import edu.ncsu.csc216.hloj.model.Order;
import edu.ncsu.csc216.hloj.model.lists.SortedList;

/**
 * Class for managing Customer objects
 * @author TylerStocks
 *
 */
public class CustomerManager  {
	/** current instance of CustomerManager **/
	private static CustomerManager instance;
	/** customers stored in CustomerManager **/
	private SortedList<Customer> customers;
	/** current instance of OrderManager **/
	private OrderManager orderManager = OrderManager.getInstance();
	/**
	 * CustomerManager constructor
	 */
	private CustomerManager() {
		customers = new SortedList<Customer>();
	}
	
	/**
	 * getter for CustomerManager current instance
	 * @return current instance of CustomerManager
	 */
	public static CustomerManager getInstance() {
		if (instance == null) {
			instance = new CustomerManager();
		}
		
		return instance;
		
	}
	
	/**
	 * adds a Customer to the list of Customers
	 * @param customer given Customer to be added
	 * @throws ModelException if id matches another customer's
	 */
	public void addCustomer(Customer customer) throws ModelException {
		for(int i = 0; i < customers.size(); i++) {
			if(customers.get(i).getId().equals(customer.getId())) {
				throw new ModelException("A customer with this id already exists");
			}
		}
		
		customers.add(customer);
	}
	
	/**
	 * gets the list of Customers
	 * @return array of Customers
	 */
	public Customer[] getCustomers() {
		Customer[] allCustomers = new Customer[customers.size()];
		
		for(int i = 0; i < customers.size(); i++) {
			allCustomers[i] = customers.get(i);
		}
		
		return allCustomers;
	}
	
	
	/**
	 * gets a Customer at the given index
	 * @param idx the index to retrieve the Customer at
	 * @return the Customer at the given index
	 * @throws IndexOutOfBoundsException if index is invalid
	 */
	public Customer getCustomer(int idx) throws IndexOutOfBoundsException{
		if (idx < 0 || idx >= customers.size()) {
			throw new IndexOutOfBoundsException("Customer does not exist.");
		}
		
		return customers.get(idx);
	}
	
	/**
	 * gets a Customer with the given id
	 * @param id of the Customer
	 * @return Customer with given id
	 */
	public Customer getCustomer(String id) {
		
		for (int i = 0; i < customers.size(); i++) {
			Customer c = customers.get(i);
			
			if (c.getId().equals(id)) {
				return c;
			} 
		}
		
		return null;
	}
	
	
	/**
	 * removes the given Customer from the list
	 * @param customer Customer to be removed
	 * @throws ModelException if the Customer doesn't exist or has open orders
	 */
	public void removeCustomer(Customer customer) throws ModelException {
		if (!customers.contains(customer) ) {
			throw new ModelException("Customer does not exist.");
		}
		
		Order[] customerOrders = orderManager.getOrdersByCustomer(customer);
		
		if (customerOrders.length == 0) {
			customers.remove(customer);
		} else {
			throw new ModelException("Cannot remove a customer with open orders");
		}
		
		
		
	}
	
	
	/**
	 * edits the Customer at the given index to be the given Customer information
	 * @param index index of the Customer to be edited
	 * @param customer new data to be put in at the given index
	 * @throws ModelException if the new Customer has the same id as another Customer
	 */
	public void editCustomer(int index, Customer customer) throws ModelException {
		Customer c = customers.get(index);
		customers.remove(index);
		
		for (int i = 0; i < customers.size(); i++) {
			if(customer.getId().equals(customers.get(i).getId()) &&
					!customer.getId().equals(c.getId())) {
				customers.add(c);
				throw new ModelException("A customer with this id already exists");
			}
		}
		
		customers.add(customer);
	}
	
	/**
	 * removes all Customers from the manager
	 */
	public void removeAllCustomers() {
		customers = new SortedList<Customer>();
		
	}
	
	
	
	
	
	
}
