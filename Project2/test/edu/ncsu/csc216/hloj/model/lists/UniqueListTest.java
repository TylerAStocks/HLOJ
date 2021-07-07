/**
 * 
 */
package edu.ncsu.csc216.hloj.model.lists;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * tests UniqueList
 * size is tested in other methods
 * @author Scott Birkner
 *
 */
public class UniqueListTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.hloj.model.lists.UniqueList#UniqueList()}.
	 */
	@Test
	public void testUniqueList() {
		UniqueList<String> list = new UniqueList<String>();
		assertEquals(0, list.size());
		assertFalse(list.contains("Apple"));
		assertTrue(list.isEmpty()); 
		//Adding 11 elements
		list.add("Apple");
		list.add("Orange");
		list.add("Pear");
		list.add("Banana");
		list.add("Lemon");
		list.add("Lime");
		list.add("Grapes");
		list.add("Rasberries");
		list.add("Blueberries");
		list.add("Cherries");
		list.add("Kiwi"); // by adding the 11th element, we should increase capacity, too
		assertEquals(list.size(), 11); // added 11 elements to the list to ensure that the list size grew past 10 without issue
	
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.hloj.model.lists.UniqueList#UniqueList(int)}.
	 */
	@Test
	public void testUniqueListInt() {
		UniqueList<String> list = new UniqueList<String>(4);
		assertEquals(0, list.size());
		assertFalse(list.contains("Apple"));
		assertTrue(list.isEmpty()); 
		//Adding 11 elements
		list.add("Apple");
		list.add("Orange");
		list.add("Pear");
		list.add("Banana");
		list.add("Lemon"); // should increase capacity here
		list.add("Lime");
		list.add("Grapes");
		list.add("Rasberries");
		list.add("Blueberries"); // and increase capacity here
		list.add("Cherries");
		list.add("Kiwi");
		assertEquals(list.size(), 11); // added 11 elements to the list to ensure that the list size grew past 10 without issue
		}

	/**
	 * Test method for {@link edu.ncsu.csc216.hloj.model.lists.UniqueList#add(java.lang.Object)}.
	 */
	@Test
	public void testAddE() {
		UniqueList<String> list = new UniqueList<String>(4);
		assertEquals(0, list.size());
		assertFalse(list.contains("Apple"));
		assertTrue(list.isEmpty()); 
		//Adding 11 elements
		list.add("Apple");
		list.add("Orange");
		try{ 
			list.add("Apple");
			fail();
		}
		catch (IllegalArgumentException e) {
			assertEquals(2, list.size());
		}
	}
	/**
	 * Test method for {@link edu.ncsu.csc216.hloj.model.lists.UniqueList#addint(java.lang.Object)}.
	 */
	@Test
	public void testAddintE() {
		UniqueList<String> list = new UniqueList<String>(4);
		assertEquals(0, list.size());
		assertFalse(list.contains("Apple"));
		assertTrue(list.isEmpty()); 
		//Adding 11 elements
		list.add("Apple");
		list.add("Orange");
		try{ 
			list.add("Apple");
			fail();
		}
		catch (IllegalArgumentException e) {
			assertEquals(2, list.size());
		}
		list.add(1, "Pears");
		assertEquals("Pears", list.get(1));
		assertEquals("Orange", list.get(2));
	}
	/**
	 * Test method for {@link edu.ncsu.csc216.hloj.model.lists.UniqueList#remove(int)}.
	 */
	@Test
	public void testRemoveInt() {
		UniqueList<String> list = new UniqueList<String>(4);
		assertEquals(0, list.size());
		//Adding 11 elements
		list.add("Apple");
		list.add("Orange");	
		try {
			list.remove(2);
			fail();
		}
		catch (IndexOutOfBoundsException e) {
			//exception
		}
		list.add("Pear");
		list.add("Banana"); 
		//list of length 4 remove from back
		assertEquals("Banana", list.remove(3));
		//remove from middle
		assertEquals("Orange", list.remove(1));
		//remove from front, final 2
		assertEquals("Apple", list.remove(0));
		assertEquals("Pear", list.remove(0));
		}

	/**
	 * Test method for {@link edu.ncsu.csc216.hloj.model.lists.UniqueList#get(int)}.
	 */
	@Test
	public void testGetInt() {
		UniqueList<String> list = new UniqueList<String>(4);
		list.add("Apple");
		list.add("Orange");	
		try {
			list.get(-1);
			fail();
		}
		catch (IndexOutOfBoundsException e) {
			//exception
		}
		try {
			list.get(2);
			fail();
		}
		catch (IndexOutOfBoundsException e) {
			//exception
		}
		assertEquals("Apple", list.get(0));
		assertEquals("Orange", list.get(1));
		list.remove(0);
		assertEquals("Orange", list.get(0));
	}

}
