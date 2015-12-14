package org.jaexcel.framework.JAEX.exception;

public class JAEXConfigurationException extends Exception {
	
	private static final long serialVersionUID = 4656750405174036342L;

	public JAEXConfigurationException(String message) {
		super(message);
	}

	public JAEXConfigurationException(String message, Exception exception) {
		super(message, exception);
	}
}
