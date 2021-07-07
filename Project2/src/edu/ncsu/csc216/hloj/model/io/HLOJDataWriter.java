package edu.ncsu.csc216.hloj.model.io;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import edu.ncsu.csc216.hloj.model.MenuItem;

import edu.ncsu.csc216.hloj.model.OrderItem;
import edu.ncsu.csc216.hloj.model.lists.UniqueList;
import edu.ncsu.csc216.hloj.model.manager.CustomerManager;
import edu.ncsu.csc216.hloj.model.manager.MenuManager;
import edu.ncsu.csc216.hloj.model.manager.OrderManager;

/**
 * Writes the system state's current information to a formatted text document
 * @author TylerStocks
 *
 */
public class HLOJDataWriter {
	
	/** current instance of the CustomerManagar singleton **/
	private static CustomerManager cManager = CustomerManager.getInstance();
	
	/** current instance of the MenuManager singleton **/
	private static MenuManager mManager = MenuManager.getInstance();
	
	/** current instance of the OrderManager singleton **/
	private static OrderManager oManager = OrderManager.getInstance();
	
	/** list of temporary menu item iDs **/
	private static UniqueList<String> itemIds = new UniqueList<String>(); 
	
	/** list of menuItems to be referenced with orderIds **/
	private static UniqueList<MenuItem> menuItems = new UniqueList<MenuItem>();
	
	/**
	 * writes the system's data to a file with the given file name
	 * @param fileName name of the file to save the system state to
	 * @throws IllegalArgumentException if there are any errors writing the data to
	 * the file with the given fileName
	 */
	public static void writeData(String fileName) {
		int lastOrderNumber = oManager.getLastOrderNumber() - 1;
		try {
			PrintStream fileWriter = new PrintStream(new File(fileName));
			fileWriter.println(lastOrderNumber + 1);
			for (int i = 0; i < cManager.getCustomers().length; i++) {
				fileWriter.println("# " + cManager.getCustomer(i).getFirstName() + "," +
			cManager.getCustomer(i).getLastName() + "," + cManager.getCustomer(i).getId());
			}
			for (int i = 0; i < mManager.getMenuItems().length; i++) {
				String identifier = "MI" + i;
				try {
					itemIds.add(identifier);
					menuItems.add(mManager.getMenuItem(i));
				} catch (IllegalArgumentException e) {
					//
				}
				fileWriter.println("* " + identifier + "," + mManager.getMenuItem(i).getType() + "," + 
			mManager.getMenuItem(i).getName() + "," + mManager.getMenuItem(i).getPrice());	
			}
			
			for (int i = 0; i < oManager.getOrders().length; i++) {
				String line = "- " + oManager.getOrder(i).getNumber() + "," + oManager.getOrder(i).getCustomer().getId();
				OrderItem[] items = oManager.getOrder(i).getItems();
				
				for(int j = 0; j < items.length; j++) {
					if(menuItems.contains(items[j].getMenuItem())) {
						for (int h = 0; h < items[j].getQuantity(); h++) {
							line += "," + itemIds.get(menuItems.indexOf(items[j].getMenuItem()));
						}
					}
				}
				
				fileWriter.println(line);
				
				
			}
			
			fileWriter.close();
		} catch (IOException e) {
			throw new IllegalArgumentException("Unable to save file.");
		} 
		
	}
	

}
