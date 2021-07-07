package edu.ncsu.csc216.hloj.model;



import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


/**
 * OrderTest tests the OrderClass
 * @author Scott Birkner
 */
public class OrderTest {
	/**
	 * sample customer to be used by methods
	 */
	Customer c;
	/**
	 * sample menu item
	 */
	MenuItem m;
	/**
	 * sample menu item 2
	 */
	MenuItem m2;
	/**
	 * sample order
	 */
	Order o;
	/**
	 * setUp for the testing
	 * @throws Exception Model Exceptions
	 */
	@Before
	public void setUp() throws Exception {
		c = new Customer("Scott", "Birkner", "sdbirkne");
		m = new MenuItem("Candy", "Reeses", 1.75);
		m2 = new MenuItem("Beverage", "Coffee", 1.50);
		o = new Order(1, c);

	}
	/**
	 * Test method for {@link edu.ncsu.csc216.hloj.model.Order#Order(int, edu.ncsu.csc216.hloj.model.Customer)}.
	 */
	@Test
	public void testOrder() {
		assertEquals(1, o.getNumber());
		assertEquals(c.toString(), o.getCustomer().toString());
		assertEquals(0, o.getItems().length);
		try {
			o = new Order(-1, c);
			fail();
		}
		catch (ModelException e) {
			assertEquals("Order numbers must be larger than zero", e.getMessage());
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.hloj.model.Order#addMenuItem(edu.ncsu.csc216.hloj.model.MenuItem)}.
	 */
	@Test
	public void testAddMenuItem() {
		o.addMenuItem(m);
		o.addMenuItem(m);
		assertEquals(3.50, o.getTotal(), .05);
	}
	/**
	 * Tests removeMenuItem
	 */
	@Test
	public void testRemoveMenuItem() {
		o.addMenuItem(m);
		assertEquals(1, o.getItems().length);
		o.removeMenuItem(m2); //nothing should happen
		assertEquals(1, o.getItems().length);
		o.removeMenuItem(m);
		assertEquals(0, o.getItems().length);
	}
	/**
	 * Test method for {@link edu.ncsu.csc216.hloj.model.Order#toString()}.
	 */
	@Test
	public void testToString() {
		o.addMenuItem(m);
		o.addMenuItem(m2);
		assertEquals("#1 for Scott Birkner - Total: $3.25", o.toString());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.hloj.model.Order#compareTo(edu.ncsu.csc216.hloj.model.Order)}.
	 * @throws ModelException for constructing second order
	 */
	@Test
	public void testCompareTo() throws ModelException {
		Order o1 = new Order(2, c);
		assertEquals(1, o1.compareTo(o));
		assertEquals(-1, o.compareTo(o1));

	}

}
