package org.jaexcel.framework.JAEX.definition;

public enum JAEXExceptionMessage {
	// declared messages of JAEXConfigurationException
	// incompatible
	JAEXConfigurationException_Missing("Header configuration is missing. Review your configuration."),
	JAEXConfigurationException_Incompatible("Incompatible configuration. Review your configuration."),
	JAEXConfigurationException_Conflit("Conflit at the configuration. Review your configuration."),

	// declared messages of JAEXConverterException
	JAEXConverterException_Default("Problem while convert the element."),
	JAEXConverterException_String("Problem while convert the element to String value."),
	JAEXConverterException_Integer("Problem while convert the element to Integer value."),
	JAEXConverterException_Date("Problem while convert the Date element, review your decorator mask."),
	JAEXConverterException_Decorator("Problem while apply the decorator."),

	// declared messages of JAEXElementException
	JAEXElementException_Row("Hola Mundo"),
	JAEXElementException_Cell("Hola Mundo"),

	// declared messages of JAEXSheetException
	JAEXSheetException_CreationWorkbook("Problem while creating the Workbook."),
	JAEXSheetException_SaveWorkbook("Problem while saving the Workbook."),
	JAEXSheetException_CreationSheet("Problem while creating the Sheet."),
	JAEXSheetException_UpdateSheet("Problem while add a new Sheet.");

	private String message;

	private JAEXExceptionMessage(String msg) {
		message = msg;
	}

	public String getMessage() {
		return message;
	}
}
