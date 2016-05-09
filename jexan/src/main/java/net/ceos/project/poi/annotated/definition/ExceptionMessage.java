package net.ceos.project.poi.annotated.definition;

/**
 * This will define all the error messages related to the framework. <br>
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public enum ExceptionMessage {
	/* declared messages of ConfigurationException incompatible */
	CONFIGURATION_XLSCONFIGURATION_MISSING("The annotation XlsConfiguration is missing. Review your configuration."),
	CONFIGURATION_XLSSHEET_MISSING("The annotation XlsSheet is missing. Review your configuration."),
	CONFIGURATION_CELLSTYLE_MISSING("Cell style configuration is missing. Review your configuration."),
	CONFIGURATION_CELLSTYLE_DUPLICATED("Cell style configuration is duplicated. Review your configuration."),
	CONFIGURATION_DECORATOR_MISSING("There is one XlsDecorator missing. Review your configuration."),
	CONFIGURATION_CONFLICT("Conflict at the configuration. Review your configuration."),
	/* declared messages of ConverterException */
	CONVERTER_DEFAULT("Problem while convert the element."),
	CONVERTER_STRING("Problem while convert the string element."),
	CONVERTER_BIGDECIMAL("Problem while convert the BigDecimal element."),
	CONVERTER_DOUBLE("Problem while convert the double element."),
	CONVERTER_FLOAT("Problem while convert the float element."),
	CONVERTER_SHORT("Problem while convert the short element."),
	CONVERTER_INTEGER("Problem while convert the integer element."),
	CONVERTER_LONG("Problem while convert the long element."),
	CONVERTER_BOOLEAN("Problem while convert the boolean element."),
	CONVERTER_DATE("Problem while convert the Date element, review your decorator mask."),
	CONVERTER_LOCALDATE("Problem while convert the LocalDate element, review your decorator mask."),
	CONVERTER_LOCALDATETIME("Problem while convert the LocalDateTime element, review your decorator mask."),
	/* declared messages of ElementException */
	ELEMENT_NULL_OBJECT("The entry object is null. Make sure you are sending a correct object."),
	ELEMENT_COMPLEX_OBJECT("Complex objects are not allowed for this type! Review your configuration."),
	ELEMENT_OVERWRITE_CELL("The element entry is trying to be set at one position already used. Review your configuration."),
	ELEMENT_INVALID_POSITION("The element entry has a invalid position, make sure you are setting a positive value and start at least by 1. Review your configuration."),
	/* declared messages of SheetException */
	SHEET_CREATION_SHEET("Problem while creating the Sheet. Review your configuration."),
	/* declared messages of SheetException */
	CUSTOMIZEDRULES_NO_SUCH_METHOD("The customized method entry does not exist. Review your configuration."),
	CUSTOMIZEDRULES_NO_SUCH_COMMENT_METHOD("The method entry at commentRules does not exist or the return type is incorrect. Review your configuration.");
	
	
	private String message;

	private ExceptionMessage(String msg) {
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
