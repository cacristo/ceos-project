package net.ceos.project.poi.annotated.exception;

/**
 * Manage the element exceptions at jexan.
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public class ElementException extends WorkbookException {
	
	private static final long serialVersionUID = 2370458787559493443L;

	public ElementException(String message) {
		super(message);
	}

	public ElementException(String message, Exception exception) {
		super(message, exception);
	}
}