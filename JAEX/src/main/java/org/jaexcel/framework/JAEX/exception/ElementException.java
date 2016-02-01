package org.jaexcel.framework.JAEX.exception;

public class ElementException extends Exception {
	
	private static final long serialVersionUID = 2370458787559493443L;

	public ElementException(String message) {
		super(message);
	}

	public ElementException(String message, Exception exception) {
		super(message, exception);
	}
}