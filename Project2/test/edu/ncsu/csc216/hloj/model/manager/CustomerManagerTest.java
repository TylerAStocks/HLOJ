package edu.ncsu.csc216.hloj.model.manager;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.hloj.model.Customer;
import edu.ncsu.csc216.hloj.model.ModelException;

/**
 * tests the CustomerMnager class
 * @author TylerStocks
 *
 */
public class CustomerManagerTest {
	/**
	 * singleton reference to customer manager
	 */
	public static CustomerManager manager = CustomerManager.getInstance();
	/**
	 * creates customer object
	 */
	public Customer c1 = null;
	/**
	 * creates customer object
	 */
	public Customer c2 = null;
	/**
	 * creates customer object
	 */
	public Customer c3 = null;
	/**
	 * creates customer object
	 */
	public Customer c4 = null;
	

	/**
	 * test setUp
	 * @throws Exception if fails to setup test
	 */
	@Before
	public void setUp() throws Exception {
		manager.removeAllCustomers();
		
	}

	
	/**
	 * tests addCustomer method
	 */
	@Test
	public void testAddCustomer() {
		
		try {
			c1 = new Customer("Tyler", "Stocks", "tastock2");
			c2 = new Customer("Scott", "Birkner", "sdbirkne");
			c3 = new Customer("Jon", "Snow", "knorth1");
			c4 = new Customer("Tony", "Stockwell", "tastock2");
		} catch (ModelException e) {
			fail();
		}
		
		assertEquals(0, manager.getCustomers().length);
		
		try {
			manager.addCustomer(c1);
			assertEquals(1, manager.getCustomers().length);
			manager.addCustomer(c2);
			manager.addCustomer(c3);
		} catch (ModelException e) {
			fail();
		}
		
		assertEquals(3, manager.getCustomers().length);
		try {
			assertEquals(c2, manager.getCustomer(0));
			assertEquals(c3, manager.getCustomer(1));
			assertEquals(c1, manager.getCustomer(2));
			
		} catch (IndexOutOfBoundsException e1) {
			fail();
		}
		try {
			manager.addCustomer(c4);
			fail();
		} catch (ModelException e) {
			assertEquals(3, manager.getCustomers().length);
		}
		
		
	}
	
	/**
	 * tests the getCustomer method using a customer's id
	 */
	@Test
	public void testGetCustomerById() {
		
		try {
			c1 = new Customer("Tyler", "Stocks", "tastock2");
			c2 = new Customer("Scott", "Birkner", "sdbirkne");
			c3 = new Customer("Jon", "Snow", "knorth1");
		} catch (ModelException e) {
			fail();
		}
		
		assertEquals(0, manager.getCustomers().length);
		
		try {
			manager.addCustomer(c1);
			assertEquals(1, manager.getCustomers().length);
			manager.addCustomer(c2);
		} catch (ModelException e) {
			fail();
		}
		
		assertEquals(2, manager.getCustomers().length);
		try {
			assertEquals(c1, manager.getCustomer(1));
			assertEquals(c2, manager.getCustomer(0));
		} catch (IndexOutOfBoundsException e1) {
			fail();
		}
		
		
		
		
		assertEquals(c1, manager.getCustomer("tastock2"));
		
		
		assertEquals(c4, manager.getCustomer("knorth1"));
		
		
	}
	
	
	/**
	 * tests removeCustomer method
	 */
	@Test
	public void testRemoveCustomer() {
		
		// first adding customers to list
		try {
			c1 = new Customer("Tyler", "Stocks", "tastock2");
			c2 = new Customer("Scott", "Birkner", "sdbirkne");
			c3 = new Customer("Jon", "Snow", "knorth1");
			c4 = new Customer("Tony", "Stockwell", "tastock2");
		} catch (ModelException e) {
			fail();
		}
		
		assertEquals(0, manager.getCustomers().length);
		
		try {
			manager.addCustomer(c1);
			assertEquals(1, manager.getCustomers().length);
			manager.addCustomer(c2);
			manager.addCustomer(c3);
		} catch (ModelException e) {
			fail();
		}
		
		assertEquals(3, manager.getCustomers().length);
		try {
			assertEquals(c2, manager.getCustomer(0));
			assertEquals(c3, manager.getCustomer(1));
			assertEquals(c1, manager.getCustomer(2));
		} catch (IndexOutOfBoundsException e1) {
			fail();
		}

		
		
		//then try removing them
		
		try {
			manager.removeCustomer(c4);
			fail();
		} catch (ModelException e) {
			assertEquals(3, manager.getCustomers().length);
		}
		
		try {
			manager.removeCustomer(c1);
			assertEquals(2, manager.getCustomers().length);
		} catch (ModelException e) {
			fail();
		}
		
		//TODO test for removing with open orders
		
		
	}
	
	
	/**
	 * tests editCustomer method
	 */
	@Test
	public void testeditCustomer() {
		try {
			c1 = new Customer("Tyler", "Stocks", "tastock2");
			c2 = new Customer("Scott", "Birkner", "sdbirkne");
			c3 = new Customer("Jon", "Snow", "knorth1");
			c4 = new Customer("Tony", "Stockwell", "tastock2");
		} catch (ModelException e) {
			fail();
		}
		
		assertEquals(0, manager.getCustomers().length);
		
		try {
			manager.addCustomer(c1);
			assertEquals(1, manager.getCustomers().length);
			manager.addCustomer(c2);
		} catch (ModelException e) {
			fail();
		}
		
		assertEquals(2, manager.getCustomers().length);
		try {
			assertEquals(c1, manager.getCustomer(1));
			assertEquals(c2, manager.getCustomer(0));
		} catch (IndexOutOfBoundsException e1) {
		fail();
		}
		
		
		
		try {
			manager.editCustomer(1, c3);
			assertEquals(2, manager.getCustomers().length);
			assertEquals(c3, manager.getCustomer(1));
			
			manager.editCustomer(0, c1);
			assertEquals(2, manager.getCustomers().length);
			assertEquals(c1, manager.getCustomer(1));
		} catch (ModelException e) {
			fail();
		}
		
		try {
			manager.editCustomer(0, c4);
			fail();
		} catch (ModelException e) {
			assertEquals(2, manager.getCustomers().length);
			try {
				assertEquals(c3, manager.getCustomer(0));
			} catch (IndexOutOfBoundsException e1) {
				fail();
			}
		}
		
	}
	
	

}
