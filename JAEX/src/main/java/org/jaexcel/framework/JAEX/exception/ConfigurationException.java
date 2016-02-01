package org.jaexcel.framework.JAEX.exception;

public class ConfigurationException extends Exception {
	
	private static final long serialVersionUID = 4656750405174036342L;

	public ConfigurationException(String message) {
		super(message);
	}

	public ConfigurationException(String message, Exception exception) {
		super(message, exception);
	}
}
