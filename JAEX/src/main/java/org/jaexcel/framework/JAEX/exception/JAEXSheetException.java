package org.jaexcel.framework.JAEX.exception;

public class JAEXSheetException extends Exception {
	
	private static final long serialVersionUID = 7556548228026497694L;

	public JAEXSheetException(String message) {
		super(message);
	}

	public JAEXSheetException(String message, Exception exception) {
		super(message, exception);
	}
}
