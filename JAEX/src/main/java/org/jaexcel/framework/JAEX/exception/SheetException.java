package org.jaexcel.framework.JAEX.exception;

public class SheetException extends Exception {
	
	private static final long serialVersionUID = 7556548228026497694L;

	public SheetException(String message) {
		super(message);
	}

	public SheetException(String message, Exception exception) {
		super(message, exception);
	}
}
