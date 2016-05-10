package net.ceos.project.poi.annotated.exception;

/**
 * Manage the customized exceptions at jexan.
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public class CustomizedRulesException extends WorkbookException {

	private static final long serialVersionUID = -606550650637642500L;

	public CustomizedRulesException(String message) {
		super(message);
	}

	public CustomizedRulesException(String message, Exception exception) {
		super(message, exception);
	}
}
