package edu.ncsu.csc216.hloj.model.manager;

import edu.ncsu.csc216.hloj.model.Customer;
import edu.ncsu.csc216.hloj.model.ModelException;
import edu.ncsu.csc216.hloj.model.Order;
import edu.ncsu.csc216.hloj.model.lists.SortedList;

/**
 * contains and manipulates a list of Order objects
 * @author TylerStocks
 *
 */
public class OrderManager {

	/** order number of the last Order that was placed **/
	private int lastOrderNumber;
	
	/** maximum amount of orders any one customer can have open **/
	private static final int MAX_ORDERS_PER_CUSTOMER = 3;
	/** current orders in the manager **/
	private SortedList<Order> orders;
	/** current instance of OrderManger **/
	private static OrderManager instance;
	/**
	 * OrderManager constructor
	 */
	private OrderManager() {
		orders = new SortedList<Order>();
		lastOrderNumber = 0;
	}
	
	/**
	 * the current OrderManager instance
	 * @return the current instance of OrderManager
	 */
	public static OrderManager getInstance() {
		if (instance == null) {
			instance = new OrderManager();
		}
		
		return instance;
	}
	
	
	/**
	 * gets the lastOrderNumber that was placed
	 * @return lastOrderNumber
	 */
	public int getLastOrderNumber() {
		return lastOrderNumber;
	}
	
	/**
	 * sets the value of the lastOrderNumber
	 * @param lastOrderNumber last number that was attributed to the most recent Order
	 * @throws ModelException if lastOrderNumber is less than any order numbers
	 */
	public void setLastOrderNumber(int lastOrderNumber) {
		this.lastOrderNumber = lastOrderNumber;
		
	}
	
	/**
	 * gets a new Order for the given Customer
	 * @param customer given Customer
	 * @return next Order for the given Customer
	 * @throws ModelException if the given Customer already has 3 open Orders
	 */
	public Order getNextOrder(Customer customer) throws ModelException {
		int count = 0;
		
		
		for(int i = 0; i < orders.size(); i++) {
			if(orders.get(i).getCustomer().equals(customer)) {
				count++;
			}
		}
		if(count >= MAX_ORDERS_PER_CUSTOMER) {
			throw new ModelException("A customer cannot have more than 3 open orders");
		}
		
		setLastOrderNumber(lastOrderNumber + 1);
		return new Order(lastOrderNumber, customer);
		
	}
	
	
	/**
	 * places the given Order
	 * @param order Order that is being placed
	 * @throws ModelException if order has number that already exists
	 */
	public void placeOrder(Order order) throws ModelException {
		int count = 0;
		if (order.getItems().length == 0) {
			throw new ModelException("Orders can only be placed if they contain at least one item");
		}

		for (int i = 0; i < orders.size(); i++) {
			if(orders.get(i).getNumber() == order.getNumber()) {
				throw new ModelException("An order with this number already exists");
			}
			
			if(order.getCustomer().equals(orders.get(i).getCustomer())) {
				count++;
			}
		}
		if (count >= MAX_ORDERS_PER_CUSTOMER) {
			throw new ModelException("A customer cannot have more than 3 open orders");
		}
		if (order.getNumber() > lastOrderNumber) {
			throw new ModelException("Order number is invalid");
		}
		orders.add(order);
		

	}
	
	
	/**
	 * gets the list of open Orders
	 * @return array of open Orders
	 */
	public Order[] getOrders() {
		Order[] allOrders = new Order[orders.size()];
		for(int i = 0; i < orders.size(); i++) {
			allOrders[i] = orders.get(i);
		}
		return allOrders;
	}
	
	
	/**
	 * gets a list of the given Customer's open Orders
	 * @param customer the given Customer
	 * @return the given Customer's open Orders
	 */
	public Order[] getOrdersByCustomer(Customer customer) {
		Order[] retOrder = new Order[0];
		try {
			SortedList<Order> cOrders = new SortedList<Order>();
			for(int i = 0; i < orders.size(); i++) {
				if (orders.get(i).getCustomer().equals(customer)) {
					cOrders.add(orders.get(i));
				}
			}
		
			if (cOrders.size() == 0) {
				return retOrder;
			}
		
			retOrder = new Order[cOrders.size()];
			for(int i = 0; i < cOrders.size(); i++) {
				retOrder[i] = cOrders.get(i);
			}
		
			return retOrder;
		} catch (NullPointerException e) {
			return retOrder;
		}
	}
	
	
	/**
	 * gets the Order at the given index
	 * @param index the given index of the Order
	 * @return the Order at the given index
	 */
	public Order getOrder(int index) {
		
		return orders.get(index);
	}
	
	
	/**
	 * cancels the given Order, removing it from the list of open Orders
	 * @param order the given Order to be cancelled
	 * @throws ModelException if order does not exist
	 */
	public void cancelOrder(Order order) throws ModelException {
		
		if(!orders.remove(order)) {
			throw new ModelException("Order does not exist");
		}
		
	}
	
	
	/**
	 * fulfills the given Order, removing it from the list of open Orders
	 * @param order the given Order to be fulfilled
	 * @throws ModelException if there are orders ahead of the given order
	 */
	public void fulfillOrder(Order order) throws ModelException {
		
		try {
			if (order.getNumber() > orders.get(0).getNumber()) {
				throw new ModelException("Orders must be fulfilled in the order in which they were placed");
			}
		
			if(!orders.remove(order)) {
				throw new ModelException("Order does not exist");
			}
		} catch (IndexOutOfBoundsException e) {
			throw new ModelException("Order does not exist");
		}
		
		
		
	}
	
	
	/**
	 * removes all open Orders from the manager
	 */
	public void removeAllOrders() {
		orders = new SortedList<Order>();
		lastOrderNumber = 0;
	}


	
}
