package net.ceos.project.poi.annotated.exception;

/**
 * Manage the workbook exceptions at jexan.
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public class WorkbookException extends Exception {

	private static final long serialVersionUID = 4414446512664759680L;

	public WorkbookException(String message) {
		super(message);
	}

	public WorkbookException(String message, Exception exception) {
		super(message, exception);
	}
}
