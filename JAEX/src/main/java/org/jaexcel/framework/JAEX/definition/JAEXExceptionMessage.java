package org.jaexcel.framework.JAEX.definition;

/**
 * This will define all the error messages related to the framework. <br>
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public enum JAEXExceptionMessage {
	/* declared messages of JAEXConfigurationException incompatible */
	JAEXConfigurationException_Missing("Header configuration is missing. Review your configuration."),
	JAEXConfigurationException_Incompatible("Incompatible configuration. Review your configuration."),
	JAEXConfigurationException_Conflit("Conflit at the configuration. Review your configuration."),

	/* declared messages of JAEXConverterException */
	JAEXConverterException_Default("Problem while convert the element."),
	JAEXConverterException_String("Problem while convert the string element."),
	JAEXConverterException_BigDecimal("Problem while convert the BigDecimal element."),
	JAEXConverterException_Double("Problem while convert the double element."),
	JAEXConverterException_Float("Problem while convert the float element."),
	JAEXConverterException_Integer("Problem while convert the integer element."),
	JAEXConverterException_Long("Problem while convert the long element."),
	JAEXConverterException_Boolean("Problem while convert the boolean element."),
	JAEXConverterException_Date("Problem while convert the Date element, review your decorator mask."),
	JAEXConverterException_Decorator("Problem while apply the decorator."),

	/* declared messages of JAEXElementException */
	JAEXElementException_NullObject("The entry object is null. Make sure you are sending a correct object."),
	JAEXElementException_EmptyObject("The entry object is empty. Make sure you are sending a correct object."),
	JAEXElementException_Row("Hola Mundo"),
	JAEXElementException_Cell("Hola Mundo"),

	/* declared messages of JAEXSheetException */
	JAEXSheetException_CreationWorkbook("Problem while creating the Workbook."),
	JAEXSheetException_SaveWorkbook("Problem while saving the Workbook."),
	JAEXSheetException_CreationSheet("Problem while creating the Sheet."),
	JAEXSheetException_UpdateSheet("Problem while add a new Sheet.");

	private String message;

	private JAEXExceptionMessage(String msg) {
		message = msg;
	}

	/**
	 * Get the error message.
	 * 
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
}
