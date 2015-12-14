package org.jaexcel.framework.JAEX.exception;

public class JAEXConverterException extends Exception {
	
	private static final long serialVersionUID = 4050965903353407027L;

	public JAEXConverterException(String message) {
		super(message);
	}

	public JAEXConverterException(String message, Exception exception) {
		super(message, exception);
	}
}
