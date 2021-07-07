/**
 * 
 */
package edu.ncsu.csc216.hloj.model;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test class for Customer test, omitted getters, they will be tested in other methods
 * @author Scott Birkner
 *
 */
public class CustomerTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.hloj.model.Customer#Customer(java.lang.String, java.lang.String, java.lang.String)}.
	 * @throws ModelException if constructors are violated
	 */
	@Test
	public void testCustomer() throws ModelException {
		Customer c = new Customer("Scott", "Birkner", "sdbirkne");
		assertEquals("Scott", c.getFirstName());
		assertEquals("Birkner", c.getLastName());
		assertEquals("sdbirkne", c.getId());

	}
	/**
	 * Test method for {@link edu.ncsu.csc216.hloj.model.Customer#setFirstName(java.lang.String)}.
	 * @throws ModelException if setfirstname is blank
	 */
	@Test
	public void testSetFirstName() throws ModelException {
		Customer c = new Customer("Scott", "Birkner", "sdbirkne");
		try {
			c.setFirstName("   ");
			fail();
		}
		catch (ModelException e) {
			assertEquals("The first name of the customer cannot be empty", e.getMessage());
		}
		c.setFirstName("Tyler");
		assertEquals("Tyler", c.getFirstName());
		}
	/**
	 * Test method for {@link edu.ncsu.csc216.hloj.model.Customer#setLastName(java.lang.String)}.
	 * @throws ModelException if last name is set blank
	 */
	@Test
	public void testSetLastName() throws ModelException {

		Customer c = new Customer("Scott", "Birkner", "sdbirkne");
		try {
			c.setLastName("   ");
			fail();
		}
		catch (ModelException e) {
			assertEquals("The last name of the customer cannot be empty", e.getMessage());
		}
		c.setLastName("Stocks");
		assertEquals("Stocks", c.getLastName());

	}
	/**
	 * Test method for {@link edu.ncsu.csc216.hloj.model.Customer#setId(java.lang.String)}.
	 * @throws ModelException if id is set blank
	 */
	@Test
	public void testSetId() throws ModelException {

		Customer c = new Customer("Scott", "Birkner", "sdbirkne");
		try {
			c.setId("");
			fail();
		}
		catch (ModelException e) {
			assertEquals("The id of the customer cannot be empty", e.getMessage());
		}
		c.setId("tastocks");
		assertEquals("tastocks", c.getId());	
		}

	/**
	 * Test method for {@link edu.ncsu.csc216.hloj.model.Customer#toString()}.
	 * @throws ModelException if customer isn't constructed properly
	 */
	@Test
	public void testToString() throws ModelException {
		Customer c = new Customer("Scott", "Birkner", "sdbirkne");
		assertEquals("Scott Birkner (sdbirkne)", c.toString());
	}

}
