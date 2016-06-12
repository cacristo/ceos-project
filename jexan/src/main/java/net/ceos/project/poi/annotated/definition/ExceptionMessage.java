/**
 * Copyright 2016 Carlos CRISTO ABREU
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.ceos.project.poi.annotated.definition;

/**
 * This will define all the error messages related to the framework.
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
	CONFIGURATION_CONFLICT_NESTED_HEADER("Conflict caused by the PropagationType and XlsNestedHeader orientation. Review your configuration."),
	CONFIGURATION_CONFLICT_FORMULA("Conflict caused by the PropagationType and formula orientation. Review your configuration."),
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
	CONVERTER_ENUM("Problem while convert the enum element."),
	CONVERTER_DATE("Problem while convert the Date element, review your decorator mask."),
	/* declared messages of ElementException */
	ELEMENT_NULL_OBJECT("The entry object is null. Make sure you are sending a correct object."),
	ELEMENT_COMPLEX_OBJECT("Complex objects are not allowed for this type! Review your configuration."),
	ELEMENT_OVERWRITE_CELL("The element entry is trying to be set at one position already used. Review your configuration."),
	ELEMENT_INVALID_POSITION("The element entry has a invalid position, make sure you are setting a positive value and start at least by 1. Review your configuration."),
	ELEMENT_NO_SUCH_METHOD("Problem while attempt to instantiate the object."),
	ELEMENT_CONFLICT_WITH_FREEELEMENT("Conflict with annotation of type @XlsElement and @XlsFreeElement. Only one annotation type is valid per attribute."),
	/* declared messages of SheetException */
	SHEET_CREATION_SHEET("Problem while creating the Sheet. Review your configuration."),
	SHEET_INVALID_COLUMN_INDEX_XLS("Invalid column index (256). Allowable column range for XLS files is (0..255) or ('A'..'IV'). Review your configuration."),
	SHEET_INVALID_COLUMN_INDEX_XLSX("Invalid column index (16384). Allowable column range for XLSX files is (0..16383) or ('A'..'XFD'). Review your configuration."),
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
