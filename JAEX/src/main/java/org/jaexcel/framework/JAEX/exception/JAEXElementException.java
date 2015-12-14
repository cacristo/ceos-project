package org.jaexcel.framework.JAEX.exception;

public class JAEXElementException extends Exception {
	
	private static final long serialVersionUID = 2370458787559493443L;

	public JAEXElementException(String message) {
		super(message);
	}

	public JAEXElementException(String message, Exception exception) {
		super(message, exception);
	}
}