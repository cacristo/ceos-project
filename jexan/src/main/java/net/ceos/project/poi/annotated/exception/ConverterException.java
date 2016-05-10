package net.ceos.project.poi.annotated.exception;

/**
 * Manage the converter exceptions at jexan.
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public class ConverterException extends WorkbookException {
	
	private static final long serialVersionUID = 4050965903353407027L;

	public ConverterException(String message) {
		super(message);
	}

	public ConverterException(String message, Exception exception) {
		super(message, exception);
	}
}
