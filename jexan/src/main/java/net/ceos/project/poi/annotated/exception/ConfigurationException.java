package net.ceos.project.poi.annotated.exception;

/**
 * Manage the configuration exceptions at jexan.
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public class ConfigurationException extends WorkbookException {
	
	private static final long serialVersionUID = 4656750405174036342L;

	public ConfigurationException(String message) {
		super(message);
	}

	public ConfigurationException(String message, Exception exception) {
		super(message, exception);
	}
}
