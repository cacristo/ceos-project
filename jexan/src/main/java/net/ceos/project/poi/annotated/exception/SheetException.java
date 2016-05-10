package net.ceos.project.poi.annotated.exception;

/**
 * Manage the sheet exceptions at jexan.
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public class SheetException extends WorkbookException {
	
	private static final long serialVersionUID = 7556548228026497694L;

	public SheetException(String message) {
		super(message);
	}

	public SheetException(String message, Exception exception) {
		super(message, exception);
	}
}
