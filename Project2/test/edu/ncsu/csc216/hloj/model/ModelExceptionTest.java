/**
 * 
 */
package edu.ncsu.csc216.hloj.model;

import static org.junit.Assert.*;

import org.junit.Test;


/**
 * test ModelException class
 * @author Scott Birkner
 *
 */
public class ModelExceptionTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.hloj.model.ModelException#ModelException()}.
	 */
	@Test
	public void testModelException() {
		ModelException c = new ModelException();
		assertEquals("Model Exception", c.getMessage());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.hloj.model.ModelException#ModelException(java.lang.String)}.
	 */
	@Test
	public void testModelExceptionString() {
	    ModelException ce = new ModelException("Custom exception message");
	    assertEquals("Custom exception message", ce.getMessage());
	}

}
