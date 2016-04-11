package net.ceos.project.poi.annotated.bean;

import java.math.BigDecimal;
import java.util.Date;

import net.ceos.project.poi.annotated.annotation.XlsConfiguration;
import net.ceos.project.poi.annotated.annotation.XlsElement;
import net.ceos.project.poi.annotated.annotation.XlsSheet;

@XlsSheet(title = "Conflict Formula Horizontal")
@XlsConfiguration(nameFile = "ConflictFormulaHorizontal")
public class XlsConflitFormulaHorizIncompatible {

	@XlsElement(title = "Date value", position = 1)
	private Date dateAttribute;

	@XlsElement(title = "String value", position = 2, comment="This is an simple comment")
	private String stringAttribute;

	@XlsElement(title = "Integer value", position = 3)
	private Integer integerAttribute = 0;

	@XlsElement(title = "Average salary", position = 4, isFormula=true, formula="AVERAGE(idy3,idy4)")
	private BigDecimal avgSalary;


	public XlsConflitFormulaHorizIncompatible() {
	}

	/**
	 * @return the dateAttribute
	 */
	public Date getDateAttribute() {
		return dateAttribute;
	}

	/**
	 * @param dateAttribute the dateAttribute to set
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
	 * @param stringAttribute the stringAttribute to set
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
	 * @param integerAttribute the integerAttribute to set
	 */
	public void setIntegerAttribute(Integer integerAttribute) {
		this.integerAttribute = integerAttribute;
	}
}
