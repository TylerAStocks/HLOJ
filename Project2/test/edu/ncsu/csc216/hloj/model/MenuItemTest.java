/**
 * 
 */
package edu.ncsu.csc216.hloj.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * tests MenuItem, Ommitting getters
 * @author Scott Birkner
 *
 */
public class MenuItemTest {
	/**
	 * sample menu item
	 */
	MenuItem m;
	/**
	 * sample menu item
	 */
	MenuItem m1;
	/**
	 * sample menu item
	 */
	MenuItem m2;
	/**
	 * setups the test so we don't have to check for exception every time
	 * @throws Exception if setup of menu items fail
	 */
	@Before
	public void setUp() throws Exception {
		m = new MenuItem("Candy", "Lollipop", .50);
		m1 = new MenuItem("Candy", "FunDip", 1.25);
		m2 = new MenuItem("Coffee", "Espresso", 1.50);
	}
	/**
	 * Test method for {@link edu.ncsu.csc216.hloj.model.MenuItem#MenuItem(java.lang.String, java.lang.String, double)}.
	 */
	@Test
	public void testMenuItem() {
		assertEquals("Candy", m.getType());
		assertEquals("Lollipop", m.getName());
		assertEquals(.50, m.getPrice(), .05);

		assertEquals("Candy", m1.getType());
		assertEquals("FunDip", m1.getName());
		assertEquals(1.25, m1.getPrice(), .05);

		assertEquals("Coffee", m2.getType());
		assertEquals("Espresso", m2.getName());
		assertEquals(1.50, m2.getPrice(), .05);
	}


	/**
	 * Test method for {@link edu.ncsu.csc216.hloj.model.MenuItem#setType(java.lang.String)}.
	 * @throws ModelException for setting the type
	 */
	@Test
	public void testSetType() throws ModelException  {
		assertEquals("Candy", m.getType());
		m.setType("    Sugar  "); //checks if it trims
		assertEquals("Sugar", m.getType());
		try {
			m.setType("  ");
			fail();
		}
		catch(ModelException e) {
			assertEquals("The type of the menu item cannot be empty", e.getMessage());
		}
		
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.hloj.model.MenuItem#setName(java.lang.String)}.
	 * @throws ModelException for setting up the name
	 */
	@Test
	public void testSetName() throws ModelException {
		assertEquals("FunDip", m1.getName());
		m1.setName("    Popsicle  "); //checks if it trims
		assertEquals("Popsicle", m1.getName());
		try {
			m.setName("  ");
			fail();
		}
		catch(ModelException e) {
			assertEquals("The name of the menu item cannot be empty", e.getMessage());
		}	
		}


	/**
	 * Test method for {@link edu.ncsu.csc216.hloj.model.MenuItem#setPrice(double)}.
	 * @throws ModelException sets up the price
	 */
	@Test
	public void testSetPrice() throws ModelException {
		assertEquals(1.50, m2.getPrice(), .05);
		m2.setPrice(2); //checks if it trims
		assertEquals(2, m2.getPrice(), .05);
		try {
			m.setPrice(0);
			fail();
		}
		catch(ModelException e) {
			assertEquals("The price of the menu item must be greater than zero", e.getMessage());
		}		
		}

	/**
	 * Test method for {@link edu.ncsu.csc216.hloj.model.MenuItem#toString()}.
	 */
	@Test
	public void testToString() {
		assertEquals("(Candy) Lollipop - $0.5", m.toString());
		assertEquals("(Candy) FunDip - $1.25", m1.toString());
		assertEquals("(Coffee) Espresso - $1.5", m2.toString());

	}
	/**
	 * tests compareTo method
	 */
	@Test
	public void testCompareTo() {
		assertEquals(0, m.compareTo(m1));
		assertEquals(1, m2.compareTo(m1));
		assertEquals(-1, m.compareTo(m2));
	}

}
