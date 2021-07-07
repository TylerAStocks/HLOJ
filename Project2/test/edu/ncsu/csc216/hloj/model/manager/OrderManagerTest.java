package edu.ncsu.csc216.hloj.model.manager;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.hloj.model.Customer;
import edu.ncsu.csc216.hloj.model.MenuItem;
import edu.ncsu.csc216.hloj.model.ModelException;
import edu.ncsu.csc216.hloj.model.Order;
import edu.ncsu.csc216.hloj.model.OrderItem;


/**
 * tests the OrderManager class
 * @author TylerStocks
 *
 */
public class OrderManagerTest {
	
	/** current instance of the OrderManager **/
	public static OrderManager manager = OrderManager.getInstance();
	/** order1 **/
	public Order o1 = null;
	/** order2 **/
	public Order o2 = null;
	/** order3 **/
	public Order o3 = null;
	/** order4 **/
	public Order o4 = null;
	/** order5 **/
	public Order o5 = null;
	/** order6 **/
	public Order o6 = null;
	
	/** menu item 1 **/
	public MenuItem m1 = null;
	/** menu item 2 **/
	public MenuItem m2 = null;
	/** menu item 3 **/
	public MenuItem m3 = null;
	/** menu item 4 **/
	public MenuItem m4 = null;
	
	/** order item 1 **/
	public OrderItem item1 = null;
	/** order item 2 **/
	public OrderItem item2 = null;
	/** order item 3 **/
	public OrderItem item3 = null;
	/** order item 4 **/
	public OrderItem item4 = null;
	
	/** customer 1 **/
	public Customer c1 = null;
	/** customer 2 **/
	public Customer c2 = null;
	/** customer 3 **/
	public Customer c3 = null;
	/** customer 4 **/
	public Customer c4 = null;
	

	
	/**
	 * test setUp
	 * @throws Exception if fails to setup test
	 */
	@Before
	public void setUp() throws Exception {
		
		c1 = new Customer("Tyler", "Stocks", "tastock2");
		c2 = new Customer("Scott", "Birkner", "sdbirkne");
		
		m2 = new MenuItem("Tea", "Chai Latte", 3.15);
		m1 = new MenuItem("Coffee", "Large Coffee", 2.5);
		m3 = new MenuItem("Pastries", "Donut", 2.0);
		m4 = new MenuItem("Tea", "Green Tea", 2.5);
		
		item1 = new OrderItem(m1);
		item2 = new OrderItem(m2);
		item3 = new OrderItem(m3);
		item4 = new OrderItem(m4);
		
		o1 = new Order(1, c1);
		o2 = new Order(2, c2);
		o3 = new Order(3, c1);
		o4 = new Order(2, c2);
		o5 = new Order(4, c1);
		o6 = new Order(5, c1);
		
		
		manager.removeAllOrders();
		
	}

	
	
	/**
	 * tests the setLastOrderNUmber method
	 */
	@Test
	public void testSetLastOrderNumber() {
		assertEquals(0, manager.getLastOrderNumber());
		manager.setLastOrderNumber(5);
		assertEquals(5, manager.getLastOrderNumber());
		
		
	}
	
	
	/**
	 * tests the getNextOrder method
	 */
	@Test
	public void testGetNextOrder() {
		o1.addMenuItem(m1);
		try {
			assertEquals(1, manager.getNextOrder(c1).getNumber());
			assertEquals(2, manager.getNextOrder(c1).getNumber());
			assertEquals(3, manager.getNextOrder(c1).getNumber());
			assertEquals(4, manager.getNextOrder(c2).getNumber());
		} catch (ModelException e) {
			assertEquals("Order number is invalid", e.getMessage());
		}
		
		
	}
	
	
	/**
	 * tests the placeOrder method
	 */
	@Test
	public void testPlaceOrder() {
		
		try {
			o1 = manager.getNextOrder(c1);
			o1.addMenuItem(m1);
			o1.addMenuItem(m1);
			manager.placeOrder(o1);
			
			assertEquals(1, manager.getOrders().length);
			assertEquals(1, manager.getLastOrderNumber());
			manager.setLastOrderNumber(3);
			
			o3 = manager.getNextOrder(c1);
			o3.addMenuItem(m3);
			manager.placeOrder(o3);
			
			assertEquals(4, manager.getLastOrderNumber());
			
			o5 = manager.getNextOrder(c1);
			o5.addMenuItem(m1);
			manager.placeOrder(o5);
			assertEquals(5, manager.getLastOrderNumber());
		} catch (ModelException e) {
			fail();
		}
		
		
		try {
			manager.placeOrder(o6);
			fail();
		} catch (ModelException e) {
			assertEquals(5, manager.getLastOrderNumber());
		}
		
		try {
			manager.placeOrder(o2);
			fail();
		} catch (ModelException e) {
			assertEquals(5, manager.getLastOrderNumber());
		}
		
		
		try {
			o2 = manager.getNextOrder(c2);
			o2.addMenuItem(m3);
			manager.placeOrder(o2);
			assertEquals(6, manager.getLastOrderNumber());
		} catch (ModelException e) {
			fail();
		}
		
		try {
			manager.placeOrder(o4);
			fail();
		} catch (ModelException e) {
			assertEquals(6, manager.getLastOrderNumber());
		}
		
	}
	
	
	/**
	 * tests the getOrders method
	 */
	@Test
	public void testGetOrders() {	
		try {
			o1 = manager.getNextOrder(c1);
			o1.addMenuItem(m1);
			o1.addMenuItem(m1);
			manager.placeOrder(o1);
			
			assertEquals(1, manager.getOrders().length);
			assertEquals(1, manager.getLastOrderNumber());
			manager.setLastOrderNumber(3);
			
			o3 = manager.getNextOrder(c1);
			o3.addMenuItem(m3);
			manager.placeOrder(o3);
			
			assertEquals(4, manager.getLastOrderNumber());
			
			o5 = manager.getNextOrder(c1);
			o5.addMenuItem(m1);
			manager.placeOrder(o5);
			assertEquals(5, manager.getLastOrderNumber());
		} catch (ModelException e) {
			fail();
		}
		
		
		
		
		
		Order[] orders = {o1, o3, o5};
		assertEquals(3, orders.length);
		assertEquals(orders[0], manager.getOrders()[0]);
		
	}
	
	
	/**
	 * tests the getOrdersByCustomer method
	 */
	@Test
	public void testGetOrdersByCustomer() {
		
		
		try {
			o1 = manager.getNextOrder(c1);
			o1.addMenuItem(m1);
			o2.addMenuItem(m2);
			manager.placeOrder(o1);
			
			assertEquals(1, manager.getOrders().length);
			assertEquals(1, manager.getLastOrderNumber());
			
			o2 = manager.getNextOrder(c1);
			o2.addMenuItem(m2);
			manager.placeOrder(o2);
			
			o3 = manager.getNextOrder(c1);
			o3.addMenuItem(m3);
			manager.placeOrder(o3);
			assertEquals(3, manager.getLastOrderNumber());
			
			o5 = manager.getNextOrder(c2);
			o5.addMenuItem(m1);
			manager.placeOrder(o5);
			assertEquals(4, manager.getLastOrderNumber());
		} catch (ModelException e) {
			fail();
		}
		
		Order[] c2Orders = {o2};
		Order[] c1Orders = {o1, o3, o5};
		
		assertEquals(c2Orders.length, manager.getOrdersByCustomer(c2).length);
		assertEquals(c1Orders.length, manager.getOrdersByCustomer(c1).length);
		assertEquals(o2, manager.getOrdersByCustomer(c1)[1]);
		
		
	}
	
	
	/**
	 * tests the cancelOrder method
	 */
	@Test
	public void testCancelOrder() {
		try {
			o1 = manager.getNextOrder(c1);
			o1.addMenuItem(m1);
			o2.addMenuItem(m2);
			manager.placeOrder(o1);
			
			assertEquals(1, manager.getOrders().length);
			assertEquals(1, manager.getLastOrderNumber());
			
			o2 = manager.getNextOrder(c1);
			o2.addMenuItem(m2);
			manager.placeOrder(o2);
			
			o3 = manager.getNextOrder(c1);
			o3.addMenuItem(m3);
			manager.placeOrder(o3);
			assertEquals(3, manager.getLastOrderNumber());
			
			o5 = manager.getNextOrder(c2);
			o5.addMenuItem(m1);
			manager.placeOrder(o5);
			assertEquals(4, manager.getLastOrderNumber());
		} catch (ModelException e) {
			fail();
		}
		
		try {
			manager.cancelOrder(o6);
			fail();
		} catch (ModelException e) {
			assertEquals(4, manager.getOrders().length);
		}
		
		try {
			manager.cancelOrder(o3);
			assertEquals(3, manager.getOrders().length);
			assertEquals(2, manager.getOrdersByCustomer(c1).length);
		} catch (ModelException e) {
			fail();
		}
		
	}
	
	
	/**
	 * tests the fulfillOrder method
	 */
	@Test
	public void testFulfillOrder() {
		try {
			o1 = manager.getNextOrder(c1);
			o1.addMenuItem(m1);
			o2.addMenuItem(m2);
			manager.placeOrder(o1);
			
			assertEquals(1, manager.getOrders().length);
			assertEquals(1, manager.getLastOrderNumber());
			
			o2 = manager.getNextOrder(c1);
			o2.addMenuItem(m2);
			manager.placeOrder(o2);
			
			o3 = manager.getNextOrder(c1);
			o3.addMenuItem(m3);
			manager.placeOrder(o3);
			assertEquals(3, manager.getLastOrderNumber());
			
			o5 = manager.getNextOrder(c2);
			o5.addMenuItem(m1);
			manager.placeOrder(o5);
			assertEquals(4, manager.getLastOrderNumber());
		} catch (ModelException e) {
			fail();
		}
		
		
		try {
			manager.fulfillOrder(o5);
			fail();
		} catch (ModelException e) {
			assertEquals(4, manager.getOrders().length);
		}
		
		try {
			manager.fulfillOrder(o6);
			fail();
		} catch (ModelException e) {
			assertEquals(4, manager.getOrders().length);
		}
		
		try {
			manager.fulfillOrder(o1);
			assertEquals(3, manager.getOrders().length);
			manager.fulfillOrder(o2);
			assertEquals(2, manager.getOrders().length);
		} catch (ModelException e) {
			fail();
		}
		
	}
	
	

}
