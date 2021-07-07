/**
 * 
 */
package edu.ncsu.csc216.hloj.model.lists;

import static org.junit.Assert.*;


import org.junit.Test;


/**
 * Test class for SortedList class
 * @author Scott Birkner
 *
 */
public class SortedListTest {



	/**
	 * Test method for {@link edu.ncsu.csc216.hloj.model.lists.SortedList#SortedList()}.
	 */
	@Test
	public void testSortedList() {
		SortedList<String> list1 = new SortedList<String>();
		assertEquals(0, list1.size());
		
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.hloj.model.lists.SortedList#add(java.lang.Object)}.
	 */
	@Test
	public void testAddE() {
		SortedList<String> list1 = new SortedList<String>();
		assertEquals(0, list1.size());
		list1.add("Scott");

		list1.add("Birkner");
		list1.add("Tyler");	
		assertEquals(3, list1.size());
		assertEquals("Birkner", list1.get(0));
		assertEquals("Scott", list1.get(1));
		assertEquals("Tyler", list1.get(2));
		}

	/**
	 * Test method for {@link edu.ncsu.csc216.hloj.model.lists.SortedList#remove(int)}.
	 */
	@Test
	public void testRemoveInt() {
		SortedList<String> list1 = new SortedList<String>();
		assertEquals(0, list1.size());
		try {
			list1.remove(-1);
			fail();
		}
		catch (IndexOutOfBoundsException e) {
			//error
		}
		list1.add("Scott");
		list1.add("Birkner");
		list1.add("Tyler");			
		assertEquals(3, list1.size());
		try {
			list1.remove(3);
			fail();
		}
		catch (IndexOutOfBoundsException e) {
			//error
		}
		assertEquals("Tyler", list1.remove(2));
		assertEquals("Birkner", list1.remove(0));
		assertEquals("Scott", list1.remove(0));	
		assertEquals(list1.size(), 0);
		}

	/**
	 * Test method for {@link edu.ncsu.csc216.hloj.model.lists.SortedList#get(int)}.
	 */
	@Test
	public void testGetInt() {
		SortedList<String> list1 = new SortedList<String>();
		assertEquals(0, list1.size());
		try {
			list1.get(-1);
			fail();
		}
		catch (IndexOutOfBoundsException e) {
			//error
		}
		list1.add("Scott");
		list1.add("Birkner");
		list1.add("Tyler");		
		assertEquals("Tyler", list1.get(2));
		assertEquals("Birkner", list1.get(0));
		assertEquals("Scott", list1.get(1));
		list1.remove(0);
		assertEquals("Tyler", list1.get(1));
		assertEquals("Scott", list1.get(0));
		}

}
