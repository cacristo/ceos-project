package org.jaexcel.framework.JAEX.definition;

/**
 * This will define all the error messages related to the framework. <br>
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public enum ExceptionMessage {
	/* declared messages of ConfigurationException incompatible */
	ConfigurationException_XlsConfigurationMissing("The annotation XlsConfiguration is missing. Review your configuration."),
	ConfigurationException_XlsSheetMissing("The annotation XlsSheet is missing. Review your configuration."),
	ConfigurationException_CellStyleMissing("Cell style configuration is missing. Review your configuration."),
	ConfigurationException_CellStyleDuplicated("Cell style configuration is duplicated. Review your configuration."),
	ConfigurationException_XlsDecoratorMissing("There is one XlsDecorator missing. Review your configuration."),
	ConfigurationException_Conflict("Conflict at the configuration. Review your configuration."),
	// TODO see the below message will be applied (remove if not)
	ConfigurationException_Incompatible("Incompatible configuration. Review your configuration."),
	
	/* declared messages of ConverterException */
	ConverterException_Default("Problem while convert the element."),
	ConverterException_String("Problem while convert the string element."),
	ConverterException_BigDecimal("Problem while convert the BigDecimal element."),
	ConverterException_Double("Problem while convert the double element."),
	ConverterException_Float("Problem while convert the float element."),
	ConverterException_Integer("Problem while convert the integer element."),
	ConverterException_Long("Problem while convert the long element."),
	ConverterException_Boolean("Problem while convert the boolean element."),
	ConverterException_Date("Problem while convert the Date element, review your decorator mask."),
	ConverterException_Decorator("Problem while apply the decorator."),

	/* declared messages of ElementException */
	ElementException_NullObject("The entry object is null. Make sure you are sending a correct object."),
	ElementException_EmptyObject("The entry object is empty. Make sure you are sending a correct object."),
	ElementException_ComplexObject("Complex objects are not allowed for this type! Review your configuration."),
	ElementException_OverwriteCell("The element entry is trying to be set at one position already used. Review your configuration."),
	ElementException_InvalidPosition("The element entry has a invalid position, make sure you are setting a positive value and start at least by 1. Review your configuration."),

	/* declared messages of SheetException */
	SheetException_CreationWorkbook("Problem while creating the Workbook. Review your configuration."),
	SheetException_CreationSheet("Problem while creating the Sheet. Review your configuration."),
	// TODO see the below messages will be applied (remove if not)
	SheetException_SaveWorkbook("Problem while saving the Workbook."),
	SheetException_UpdateSheet("Problem while add a new Sheet."),

	/* declared messages of SheetException */
	CustomizedRulesException_NoSuchMethod("The customized method entry does not exist. Review your configuration.");
	
	
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
