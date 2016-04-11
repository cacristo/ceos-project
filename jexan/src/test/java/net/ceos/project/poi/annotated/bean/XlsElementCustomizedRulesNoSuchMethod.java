package net.ceos.project.poi.annotated.bean;

import java.util.Date;

import net.ceos.project.poi.annotated.annotation.XlsConfiguration;
import net.ceos.project.poi.annotated.annotation.XlsElement;
import net.ceos.project.poi.annotated.annotation.XlsNestedHeader;
import net.ceos.project.poi.annotated.annotation.XlsSheet;
import net.ceos.project.poi.annotated.exception.CustomizedRulesException;

@XlsSheet(title = "Xls Element customized rules", startRow = 2)
@XlsConfiguration(nameFile = "ElementCustomizedRule")
public class XlsElementCustomizedRulesNoSuchMethod {

	@XlsNestedHeader(title = "Main", startX = 1, endX = 3)
	@XlsElement(title = "Date value", position = 1)
	private Date dateAttribute;

	@XlsElement(title = "String value", position = 2, comment = "This is an simple comment", customizedRules="sumMethod")
	private String stringAttribute;

	@XlsElement(title = "Integer value", position = 3)
	private Integer integerAttribute = 0;

	public XlsElementCustomizedRulesNoSuchMethod() {
	}

	/**
	 * @return the dateAttribute
	 */
	public Date getDateAttribute() {
		return dateAttribute;
	}

	/**
	 * @param dateAttribute
	 *            the dateAttribute to set
	 */
	public void setDateAttribute(Date dateAttribute) {
		this.dateAttribute = dateAttribute;
	}

	/**
	 * @return the stringAttribute
	 */
	public String getStringAttribute() {
		return stringAttribute;
	}

	/**
	 * @param stringAttribute
	 *            the stringAttribute to set
	 */
	public void setStringAttribute(String stringAttribute) {
		this.stringAttribute = stringAttribute;
	}

	/**
	 * @return the integerAttribute
	 */
	public Integer getIntegerAttribute() {
		return integerAttribute;
	}

	/**
	 * @param integerAttribute
	 *            the integerAttribute to set
	 */
	public void setIntegerAttribute(Integer integerAttribute) {
		this.integerAttribute = integerAttribute;
	}
	
	public void myRuleMethod() throws CustomizedRulesException {
		if(this.stringAttribute.contains("down")){
			throw new CustomizedRulesException("Sample how to manage the content of one string");
		}
	}
}