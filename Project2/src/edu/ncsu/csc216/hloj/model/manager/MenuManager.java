package edu.ncsu.csc216.hloj.model.manager;

import edu.ncsu.csc216.hloj.model.MenuItem;
import edu.ncsu.csc216.hloj.model.ModelException;
import edu.ncsu.csc216.hloj.model.Order;
import edu.ncsu.csc216.hloj.model.OrderItem;
import edu.ncsu.csc216.hloj.model.lists.SortedList;

/**
 * contains and manipulates a list of MenuItems
 * @author TylerStocks
 *
 */
public class MenuManager {

	/** list of MenuItems that makes up the menu **/
	private SortedList<MenuItem> menu;
	/** current instance of MenuManager **/
	private static MenuManager instance;
	/** current instance of OrderManager **/
	private OrderManager orderManager = OrderManager.getInstance();
	
	/**
	 * MenuManager constructor
	 */
	private MenuManager() {
		menu = new SortedList<MenuItem>();
	}
	
	
	/**
	 * the current instance of the MenuManager
	 * @return current instance of MenuManager
	 */
	public static MenuManager getInstance() {
		if (instance == null) {
			instance = new MenuManager();
		}
		
		return instance;
	}
	
	
	/**
	 * adds the given MenuItem to the menu
	 * @param menuItem being added to the menu
	 */
	public void addMenuItem(MenuItem menuItem) {
		menu.add(menuItem);
	}
	
	/**
	 * gets a list of all MenuItems in the menu
	 * @return array of MenuItems
	 */
	public MenuItem[] getMenuItems() {
		MenuItem[] menuItems = new MenuItem[menu.size()];
		for(int i = 0; i < menu.size(); i++) {
			menuItems[i] = menu.get(i);
		}
		
		return menuItems;
	}
	
	
	/**
	 * gets the MenuItem at the given index
	 * @param index index of the MenuItem
	 * @return the MenuItem at the given index
	 */
	public MenuItem getMenuItem(int index) {
		
		return menu.get(index);
	}
	
	
	/**
	 * removes the MenuItem at the given index
	 * @param index index of the MenuItem to be removed
	 * @throws ModelException if menu item is part of an open order
	 */
	public void removeMenuItem(int index) throws ModelException {
		
		
		Order[] openOrders = orderManager.getOrders();
		
		for(int i = 0; i < openOrders.length; i++) {
			OrderItem[] orderItems = openOrders[i].getItems();
			for(int j = 0; j < orderItems.length; j++) {
				if(menu.get(index).equals(orderItems[j].getMenuItem())) {
					throw new ModelException("Cannot delete a menu item that is part of an open order");
				}
			}
		}
		
		
		menu.remove(index);
	}
	
	/**
	 * edits the MenuItem at the given index to have the given MenuItem data
	 * @param index index of the MenuItem being edited
	 * @param menuItem new information being saved to the given index
	 */
	public void editMenuItem(int index, MenuItem menuItem) {
		menu.remove(index);
		menu.add(menuItem);
		
	}
	
	
	/**
	 * deletes all MenuItems from the manager
	 */
	public void removeAllMenuItems() {
		menu = new SortedList<MenuItem>();
		
	}
}
