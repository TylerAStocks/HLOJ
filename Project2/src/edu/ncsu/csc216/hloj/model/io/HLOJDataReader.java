package edu.ncsu.csc216.hloj.model.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.hloj.model.Customer;
import edu.ncsu.csc216.hloj.model.MenuItem;
import edu.ncsu.csc216.hloj.model.ModelException;
import edu.ncsu.csc216.hloj.model.Order;
import edu.ncsu.csc216.hloj.model.lists.UniqueList;
import edu.ncsu.csc216.hloj.model.manager.CustomerManager;
import edu.ncsu.csc216.hloj.model.manager.MenuManager;
import edu.ncsu.csc216.hloj.model.manager.OrderManager;

/**
 * reads information from a given file and populates the system state with its information
 * @author TylerStocks
 *
 */
public class HLOJDataReader {
	
	/** current instance of the CustomerManagar singleton **/
	private static CustomerManager cManager = CustomerManager.getInstance();
	
	/** current instance of the MenuManager singleton **/
	private static MenuManager mManager = MenuManager.getInstance();
	
	/** current instance of the OrderManager singleton **/
	private static OrderManager oManager = OrderManager.getInstance();
	
	/** list of temporary menu item iDs **/
	private static UniqueList<String> orderIds = new UniqueList<String>(); 
	
	/** list of menuItems to be referenced with orderIds **/
	private static UniqueList<MenuItem> menuItems = new UniqueList<MenuItem>();
	
	/** the last order placed, value at the start of the file **/
	private static int startNum = 0;
	
	
	
	/**
	 * resets the state of the system and reads the given file to populat a new state
	 * @param fileName name of the given file to populate the state with
	 * @throws IllegalArgumentException if the file does not exist
	 */
	public static void readData(String fileName) throws IllegalArgumentException {
		cManager.removeAllCustomers();
		mManager.removeAllMenuItems();
		Scanner fileReader;
		try {
			fileReader = new Scanner(new FileInputStream(fileName));
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("File " + fileName + " does not exist");
		}
		
		String firstLine = fileReader.nextLine();
		
		try {
			startNum = Integer.parseInt(firstLine);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Unable to load file");
		}
		if (startNum != 0) {
			oManager.setLastOrderNumber(startNum);
		} else {
			throw new IllegalArgumentException("Unable to load file");
		}
		
		String state = "Customer";
		while (fileReader.hasNextLine()) {
			String line = fileReader.nextLine();
			
			
			switch (state) {
			
			case "Customer":
				if (line.startsWith("* ")) {
					state = "Menu";
					processMenuItem(line);
				} else {
					processCustomer(line);
				}
				break;
				
			case "Menu":
				if (line.startsWith("- ")) {
					state = "Order";
					processOrder(line);
				} else if (line.startsWith("* ")){
					processMenuItem(line);
				}
				break;
				
			case "Order":
				if (line.startsWith("- ")) {
					if (mManager.getMenuItems().length != 0) {
						processOrder(line);
					}
				} else if (line.startsWith("* ")) {
					oManager.removeAllOrders();
				} 
				break;
				
			default:
				break;
			}
			
		}
		
	
		
		
	}
	
	
	
	private static void processCustomer(String c) {
		
		if (c.startsWith("# ")) {
			try {
				try {
					String firstName = c.substring(1, c.indexOf(","));
					String iD = c.substring(c.lastIndexOf(",") + 1);
					String lastName = c.substring(c.indexOf(",") + 1, c.indexOf(iD) - 1);
					
					Customer customer = null;
					try {
						customer = new Customer(firstName, lastName, iD);
					} catch (ModelException e) {
						//stop
					}		
					try {
						cManager.addCustomer(customer);
					} catch (ModelException e) {
						//stop
					}
				} catch (IllegalArgumentException e) {
					//stop
				}
			} catch (StringIndexOutOfBoundsException e) {
				//stop
			}

		} 
	}
	
	private static void processMenuItem(String mI) {
		Scanner mIScan = new Scanner(mI);
		mIScan.useDelimiter(",");
		
		while(mIScan.hasNext()) {
			
			String orderId = mIScan.next();
			orderId = orderId.substring(2);
			
			String type = mIScan.next();
			String name = mIScan.next();
			double price = 0;
			try {
				try {
					price = Double.parseDouble(mIScan.next());
				} catch (NoSuchElementException e) {
					price = 0;
				}
				
				MenuItem item = null;
				try {
					item = new MenuItem(type, name, price);
					try {
						menuItems.add(item);
						orderIds.add(orderId);
					} catch (IllegalArgumentException e) {
						//
					}
				} catch (ModelException e) {
					break;
				}
				mManager.addMenuItem(item);
			} catch (NumberFormatException e) {
				mManager.removeAllMenuItems();
				break;
			}
		}
		
		
		mIScan.close();
	}
	
	private static void processOrder(String o) {
		if (mManager.getMenuItems().length != 0) {
			Scanner oScanner = new Scanner(o);
			oScanner.useDelimiter(",");
			while(oScanner.hasNext()) {
				Order order = null;
				int orderNumber = 0;
				try {
					orderNumber = Integer.parseInt(oScanner.next().substring(2));
				} catch (NumberFormatException e) {
					break;
				}
				String customerId = oScanner.next();
				
				Customer customer = null;
				customer = cManager.getCustomer(customerId);
				 
				if (customer != null) {
					try {
						order = new Order(orderNumber, customer);
					} catch (ModelException e) {
						order = null;
					}
					
					if (order != null ) {
						while(oScanner.hasNext()) {
							String id = oScanner.next();
							for (int i = 0; i < orderIds.size(); i++) {
								if(id.equals(orderIds.get(i))) {
									order.addMenuItem(menuItems.get(i));
								} 
							}
						}
						
						try {
							oManager.placeOrder(order);
						} catch (ModelException e) {
							//
						}
					}
					
				}
			}
			oScanner.close();
		}
	}

}
