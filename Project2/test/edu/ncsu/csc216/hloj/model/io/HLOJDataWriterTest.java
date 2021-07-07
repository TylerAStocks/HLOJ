package edu.ncsu.csc216.hloj.model.io;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.hloj.model.Customer;
import edu.ncsu.csc216.hloj.model.MenuItem;
import edu.ncsu.csc216.hloj.model.ModelException;
import edu.ncsu.csc216.hloj.model.Order;
import edu.ncsu.csc216.hloj.model.manager.CustomerManager;
import edu.ncsu.csc216.hloj.model.manager.MenuManager;
import edu.ncsu.csc216.hloj.model.manager.OrderManager;

/**
 * tests the HLOJDataWriter class
 * @author TylerStocks
 *
 */
public class HLOJDataWriterTest {
	
	
	/** file that is being written to **/
	private final String actualFile1 = "test-files/actual_file1.txt";
	/** expected file output **/
	private final String expFile1 = "test-files/expected1.txt";
	/** inaccessible file **/
	private final String inaccessibleFile = "/home/sesmith5/actual_student_records.txt";
	
	
	/** current instance of the CustomerManagar singleton **/
	private static CustomerManager cManager = CustomerManager.getInstance();
	
	/** current instance of the MenuManager singleton **/
	private static MenuManager mManager = MenuManager.getInstance();
	
	/** current instance of the OrderManager singleton **/
	private static OrderManager oManager = OrderManager.getInstance();
	
	/** customer 1 **/
	public Customer c1 = null;
	/** customer 2 **/
	public Customer c2 = null;
	/** customer 3 **/
	public Customer c3 = null;
	/** customer 4 **/
	public Customer c4 = null;
	/** customer 5 **/
	public Customer c5 = null;
	
	/** menu item 1 **/
	public MenuItem m1 = null;
	/** menu item 2 **/
	public MenuItem m2 = null;
	/** menu item 3 **/
	public MenuItem m3 = null;
	/** menu item 4 **/
	public MenuItem m4 = null;
	/** menu item 5 **/
	public MenuItem m5 = null;
	
	/** order 1 **/
	public Order o1 = null;
	/** order 2 **/
	public Order o2 = null;
	/** order 3 **/
	public Order o3 = null;
	/**
	 * test setUp
	 * @throws Exception if fails to setup test
	 */
	@Before
	public void setUp() throws Exception {
		cManager.removeAllCustomers();
		mManager.removeAllMenuItems();
		oManager.removeAllOrders();
		
		oManager.setLastOrderNumber(13);
		
		c1 = new Customer("Porter", "Bell", "pbell");
		c2 = new Customer("Odessa", "Howard", "ohoward");
		c3 = new Customer("Iona", "Martin", "imartin");
		c4 = new Customer("Kiara", "Martin", "kmartin");
		c5 = new Customer("Kasimir", "Santos", "ksantos");
		
		m1 = new MenuItem("Coffee", "Latte", 4.15);
		m2 = new MenuItem("Coffee", "Americano", 4.33);
		m3 = new MenuItem("Ice Cream", "Cherry Vanilla", 2.16);
		m4 = new MenuItem("Tea", "Apple Tea", 3.0);
		m5 = new MenuItem("Tea", "Earl Gray", 2.5);
		
		o1 = oManager.getNextOrder(c5);
		
		o1.addMenuItem(m1);
		o1.addMenuItem(m2);
		o1.addMenuItem(m3);
		o1.addMenuItem(m4);
		o1.addMenuItem(m5);
		oManager.placeOrder(o1);
		
		o2 = oManager.getNextOrder(c4);
		o2.addMenuItem(m5);
		oManager.placeOrder(o2);
		
		o3 = oManager.getNextOrder(c3);
		o3.addMenuItem(m1);
		o3.addMenuItem(m1);
		o3.addMenuItem(m1);
		oManager.placeOrder(o3);
		
		
		
		
		
	}

	/**
	 * tests the writeData method
	 */
	@Test
	public void testWriteData() {
		try {
			cManager.addCustomer(c1);
			cManager.addCustomer(c2);
			cManager.addCustomer(c3);
			cManager.addCustomer(c4);
			cManager.addCustomer(c5);
			
			mManager.addMenuItem(m1);
			mManager.addMenuItem(m2);
			mManager.addMenuItem(m3);
			mManager.addMenuItem(m4);
			mManager.addMenuItem(m5);
			
		} catch (ModelException e) {
			fail();
		}
		
		
		try {
			HLOJDataWriter.writeData(inaccessibleFile);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Unable to save file.", e.getMessage());
		}
		
		try {
			HLOJDataWriter.writeData(actualFile1);
			checkFiles(expFile1, actualFile1);
		} catch (IllegalArgumentException e) {
			fail();
		}

	}
	
	
	
	
	/**
	 * Helper method that compares contents of two files
	 * @param expFile expected output file
	 * @param actFile actual output file
	 */
	public void checkFiles(String expFile, String actFile) {
		try {
			Scanner expScanner = new Scanner(new FileInputStream(expFile));
			Scanner actScanner = new Scanner(new FileInputStream(actFile));
			while (expScanner.hasNextLine() && actScanner.hasNextLine()) {
				String exp = expScanner.nextLine();
				String act = actScanner.nextLine();
				assertEquals("Expected: " + exp + "Actual: " + act, exp, act);
			}
			if (expScanner.hasNextLine()) {
				fail("The expected results expect another line " + expScanner.nextLine());
			}
			if (actScanner.hasNextLine()) {
				fail("The actual results has an extra, unexpected line: " + actScanner.nextLine());
			}
			expScanner.close();
			actScanner.close();
			
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}

}
