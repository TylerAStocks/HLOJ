package edu.ncsu.csc216.hloj.model.io;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.hloj.model.manager.CustomerManager;
import edu.ncsu.csc216.hloj.model.manager.MenuManager;
import edu.ncsu.csc216.hloj.model.manager.OrderManager;

/**
 * tests the HLOJReader class
 * @author TylerStocks
 *
 */
public class HLOJDataReaderTest {
	
	
	/** current instance of the CustomerManagar singleton **/
	private static CustomerManager cManager = CustomerManager.getInstance();
	
	/** current instance of the MenuManager singleton **/
	private static MenuManager mManager = MenuManager.getInstance();
	
	/** current instance of the OrderManager singleton **/
	private static OrderManager oManager = OrderManager.getInstance();
	
	

	/**
	 * test setUp
	 * @throws Exception if fails to setup test
	 */
	@Before
	public void setUp() throws Exception {
		
		cManager.removeAllCustomers();
		mManager.removeAllMenuItems();
		oManager.removeAllOrders();
		
	}

	/**
	 * tests the readData method
	 */
	@Test
	public void testReadData() {
		try {
			HLOJDataReader.readData("test-files/data3.txt");
			assertEquals(5, cManager.getCustomers().length);
			assertEquals(10, mManager.getMenuItems().length);
			assertEquals(5, oManager.getOrders().length);
		} catch (IllegalArgumentException e) {
			fail();
		}
	}

}
