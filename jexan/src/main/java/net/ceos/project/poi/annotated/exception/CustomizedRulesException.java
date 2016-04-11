package net.ceos.project.poi.annotated.exception;

public class CustomizedRulesException extends Exception {

	private static final long serialVersionUID = -606550650637642500L;

	public CustomizedRulesException(String message) {
		super(message);
	}

	public CustomizedRulesException(String message, Exception exception) {
		super(message, exception);
	}
}
