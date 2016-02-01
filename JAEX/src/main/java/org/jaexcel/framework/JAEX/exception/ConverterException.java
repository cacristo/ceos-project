package org.jaexcel.framework.JAEX.exception;

public class ConverterException extends Exception {
	
	private static final long serialVersionUID = 4050965903353407027L;

	public ConverterException(String message) {
		super(message);
	}

	public ConverterException(String message, Exception exception) {
		super(message, exception);
	}
}
