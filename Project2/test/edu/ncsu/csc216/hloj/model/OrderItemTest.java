/**
 * 
 */
package edu.ncsu.csc216.hloj.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * tests Order Item class
 * @author Scotty
 *
 */
public class OrderItemTest {
	/**
	 * sample menu item
	 */
	MenuItem m;
	/**
	 * sample menu item 2
	 */
	MenuItem m2;
	/**
	 * sets up and constructs a few menu items
	 * @throws Exception if ModelException is thrown during construction
	 */
	@Before
	public void setUp() throws Exception {
		m = new MenuItem("Candy", "Reeses", 1.75);
		m2 = new MenuItem("Beverage", "Coffee", 1.50);

	}
	/**
	 * Test method for {@link edu.ncsu.csc216.hloj.model.OrderItem#OrderItem(edu.ncsu.csc216.hloj.model.MenuItem)}.
	 */
	@Test
	public void testOrderItem() {
		OrderItem o = new OrderItem(m);
		assertEquals(1, o.getQuantity());
		assertEquals("(Candy) Reeses - $1.75", o.getMenuItem().toString());
	}


	/**
	 * Test method for {@link edu.ncsu.csc216.hloj.model.OrderItem#setQuantity(int)}.
	 */
	@Test
	public void testSetQuantity() {
		OrderItem o = new OrderItem(m);
		assertEquals(1, o.getQuantity());
		try {
			o.setQuantity(3);
		} catch (ModelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		assertEquals(3, o.getQuantity());
		try {
			o.setQuantity(-1);
			fail();
		}
		catch (ModelException e) {
			assertEquals("The quantity of an item in an order has to be greater than zero", e.getMessage());
		}
		
		}
	/**
	 * Test method for {@link edu.ncsu.csc216.hloj.model.OrderItem#compareTo(edu.ncsu.csc216.hloj.model.OrderItem)}.
	 */
	@Test
	public void testCompareTo() {
		OrderItem o = new OrderItem(m);
		OrderItem o1 = new OrderItem(m2);
		//the compareto method is passed from menuitem, so it'll satisfy those conditions
		assertEquals(1, o.compareTo(o1));
		assertEquals(-1, o1.compareTo(o));
		assertEquals(0, o.compareTo(o));
		
	}

}
