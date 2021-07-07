/**
 * 
 */
package edu.ncsu.csc216.hloj.model;
/**
 * creates a exception if the model's business rules are broken
 * @author Scott Birkner
 *
 */

public class ModelException extends Exception {

	/**
	 * serialVersionUID for ModelException
	 */
	private static final long serialVersionUID = 394924087907614612L;
		/**
		 * ModelException creates a default message
		 * for the exception
		 */
		public ModelException() {
			super("Model Exception");
		}
		/**
		 * Allows the user to create a unique message for the exception
		 * @param message Sting of users unique message
		 */
		public ModelException(String message) {
			super(message);
		}
	}

