package edu.ncsu.csc216.hloj.model.manager;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.hloj.model.MenuItem;
import edu.ncsu.csc216.hloj.model.ModelException;


/**
 * tests the MenuManager class
 * @author TylerStocks
 *
 */
public class MenuManagerTest {
	
	/** current instance of the MenuManager singleton **/
	public MenuManager manager = MenuManager.getInstance();
	/** menu item 1 **/
	public MenuItem m1 = null;
	/** menu item 2 **/
	public MenuItem m2 = null;
	/** menu item 3 **/
	public MenuItem m3 = null;
	/** menu item 4 **/
	public MenuItem m4 = null;

	/**
	 * test setUp
	 * @throws Exception if fails to setup test
	 */
	@Before
	public void setUp() throws Exception {
		manager.removeAllMenuItems();
	}

	
	/**
	 * tests the addMenuItem method
	 */
	@Test
	public void testAddMenuItem() {
		
		try {
			m1 = new MenuItem("Tea", "Chai Latte", 3);
			m2 = new MenuItem("Coffee", "Large Coffee", 2.5);
			m3 = new MenuItem("Pastries", "Donut", 2.0);
			m4 = new MenuItem("Tea", "Green Tea", 2.5);
		} catch (ModelException e) {
			fail();
		}
		
		
		assertEquals(0, manager.getMenuItems().length);
		manager.addMenuItem(m1);
		assertEquals(1, manager.getMenuItems().length);
		assertEquals(m1, manager.getMenuItem(0));
		manager.addMenuItem(m2);
		manager.addMenuItem(m3);
		manager.addMenuItem(m4);
		assertEquals(4, manager.getMenuItems().length);
		
		assertEquals(m2, manager.getMenuItem(0));
		assertEquals(m3, manager.getMenuItem(1));
		assertEquals(m1, manager.getMenuItem(2));
		assertEquals(m4, manager.getMenuItem(3));
		
	}
	
	
	
	/**
	 * tests the removeMenuItem method
	 */
	@Test
	public void testRemoveMenuItem() {
		try {
			m1 = new MenuItem("Tea", "Chai Latte", 3);
			m2 = new MenuItem("Coffee", "Large Coffee", 2.5);
			m3 = new MenuItem("Pastries", "Donut", 2.0);
			m4 = new MenuItem("Tea", "Green Tea", 2.5);
		} catch (ModelException e) {
			fail();
		}
		
		
		assertEquals(0, manager.getMenuItems().length);
		manager.addMenuItem(m1);
		assertEquals(1, manager.getMenuItems().length);
		assertEquals(m1, manager.getMenuItem(0));
		manager.addMenuItem(m2);
		manager.addMenuItem(m3);
		manager.addMenuItem(m4);
		assertEquals(4, manager.getMenuItems().length);
		
		
		try {
			manager.removeMenuItem(0);
			assertEquals(3, manager.getMenuItems().length);
		} catch (ModelException e) {
			fail();
		}
		
		//TODO test for removing item with open orders
		
		
	}
	
	
	
	/**
	 * tests the editMenuItem method
	 */
	@Test
	public void testEditMenuItem() {
		
		try {
			m1 = new MenuItem("Tea", "Chai Latte", 3);
			m2 = new MenuItem("Coffee", "Large Coffee", 2.5);
			m3 = new MenuItem("Pastries", "Donut", 2.0);
			m4 = new MenuItem("Tea", "Green Tea", 2.5);
		} catch (ModelException e) {
			fail();
		}
		
		
		assertEquals(0, manager.getMenuItems().length);
		manager.addMenuItem(m1);
		assertEquals(1, manager.getMenuItems().length);
		assertEquals(m1, manager.getMenuItem(0));
		manager.addMenuItem(m2);
		manager.addMenuItem(m3);
		assertEquals(3, manager.getMenuItems().length);
		
		
		
		manager.editMenuItem(1, m4);
		assertEquals(3, manager.getMenuItems().length);
		
	}
	
	

}
