package net.ceos.project.poi.annotated.bean;

import java.util.Date;

import net.ceos.project.poi.annotated.annotation.XlsConfiguration;
import net.ceos.project.poi.annotated.annotation.XlsElement;
import net.ceos.project.poi.annotated.annotation.XlsNestedHeader;
import net.ceos.project.poi.annotated.annotation.XlsSheet;
import net.ceos.project.poi.annotated.exception.CustomizedRulesException;

@XlsSheet(title = "Xls Element customized rules")
@XlsConfiguration(nameFile = "ElementCustomizedRule")
public class XlsElementCustomizedRulesMultipleMethods {

	@XlsNestedHeader(title = "Main", startX = 1, endX = 3)
	@XlsElement(title = "Date value", position = 1)
	private Date dateAttribute;

	@XlsElement(title = "String value", position = 2, comment = "This is an simple comment", customizedRules = "myRuleMethod")
	private String stringAttribute;

	@XlsElement(title = "Integer value", position = 3)
	private Integer integerAttribute1 = 0;

	@XlsElement(title = "Integer value", position = 4, customizedRules = "sampleRule")
	private Integer integerAttribute2 = 0;

	@XlsElement(title = "Integer value", position = 5, customizedRules = "anotherRule")
	private double doublePrimitiveAttribute = 0;

	public XlsElementCustomizedRulesMultipleMethods() {
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
	 * @return the integerAttribute1
	 */
	public Integer getIntegerAttribute1() {
		return integerAttribute1;
	}

	/**
	 * @param integerAttribute1
	 *            the integerAttribute1 to set
	 */
	public void setIntegerAttribute1(Integer integerAttribute1) {
		this.integerAttribute1 = integerAttribute1;
	}

	/**
	 * @return the integerAttribute2
	 */
	public Integer getIntegerAttribute2() {
		return integerAttribute2;
	}

	/**
	 * @param integerAttribute2
	 *            the integerAttribute2 to set
	 */
	public void setIntegerAttribute2(Integer integerAttribute2) {
		this.integerAttribute2 = integerAttribute2;
	}

	/**
	 * @return the doublePrimitiveAttribute
	 */
	public double getDoublePrimitiveAttribute() {
		return doublePrimitiveAttribute;
	}

	/**
	 * @param doublePrimitiveAttribute
	 *            the doublePrimitiveAttribute to set
	 */
	public void setDoublePrimitiveAttribute(double doublePrimitiveAttribute) {
		this.doublePrimitiveAttribute = doublePrimitiveAttribute;
	}

	public void myRuleMethod() throws CustomizedRulesException {
		if (this.stringAttribute.contains("down")) {
			throw new CustomizedRulesException("Sample how to manage the content of one string");
		}
	}

	public void sampleRule() throws CustomizedRulesException {
		if (this.integerAttribute2.equals(200)) {
			throw new CustomizedRulesException("Sample how to manage the content of one integer");
		}
	}

	public void anotherRule() throws CustomizedRulesException {
		if (this.doublePrimitiveAttribute == 0.0) {
			throw new CustomizedRulesException("Sample how to manage the content of one double");
		}
	}

}