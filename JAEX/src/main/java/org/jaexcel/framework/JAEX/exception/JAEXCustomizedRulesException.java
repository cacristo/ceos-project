package org.jaexcel.framework.JAEX.exception;

public class JAEXCustomizedRulesException extends Exception {

	private static final long serialVersionUID = -606550650637642500L;

	public JAEXCustomizedRulesException(String message) {
		super(message);
	}

	public JAEXCustomizedRulesException(String message, Exception exception) {
		super(message, exception);
	}
}
