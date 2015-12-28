package org.jaexcel.framework.JAEX.bean;

import java.util.Date;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.jaexcel.framework.JAEX.annotation.XlsConfiguration;
import org.jaexcel.framework.JAEX.annotation.XlsDecorator;
import org.jaexcel.framework.JAEX.annotation.XlsDecorators;
import org.jaexcel.framework.JAEX.annotation.XlsElement;
import org.jaexcel.framework.JAEX.annotation.XlsSheet;

@XlsDecorators(values = {
		@XlsDecorator(decoratorName = "numeric", fontItalic = true, fontBold = true, border = CellStyle.BORDER_DOTTED, foregroundColor = HSSFColor.ORANGE.index),
		@XlsDecorator(decoratorName = "extendedDate", fontItalic = true, fontBold = true),
		@XlsDecorator(decoratorName = "extendedInteger", border = CellStyle.BORDER_MEDIUM, fontBold = true, foregroundColor = HSSFColor.BLUE.index) })
@XlsConfiguration(nameFile = "Object configurable")
@XlsSheet(title = "Decorators")
public class ObjectConfigurable {

	@XlsElement(title = "Date value", position = 1, formatMask = "yyyy-MM-dd")
	private Date dateAttribute;

	@XlsElement(title = "String value", position = 2)
	private String stringAttribute;

	@XlsElement(title = "Integer value", position = 3, decorator = "extendedInteger")
	private Integer integerAttribute = 0;

	@XlsElement(title = "Double value", position = 4, formatMask = "0.0")
	private Double doubleAttribute = 0.0;

	@XlsElement(title = "Long value", position = 5)
	private Long longAttribute = 0L;

	@XlsElement(title = "Boolean value", position = 6)
	private Boolean booleanAttribute = Boolean.TRUE;

	@XlsElement(title = "job", position = 7)
	private Job job;

	@XlsElement(title = "Date value1", position = 8, formatMask = "yyyy.MM.dd")
	private Date dateAttribute1;

	public ObjectConfigurable() {
		// TODO Auto-generated constructor stub
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

	/**
	 * @return the doubleAttribute
	 */
	public Double getDoubleAttribute() {
		return doubleAttribute;
	}

	/**
	 * @param doubleAttribute
	 *            the doubleAttribute to set
	 */
	public void setDoubleAttribute(Double doubleAttribute) {
		this.doubleAttribute = doubleAttribute;
	}

	/**
	 * @return the longAttribute
	 */
	public Long getLongAttribute() {
		return longAttribute;
	}

	/**
	 * @param longAttribute
	 *            the longAttribute to set
	 */
	public void setLongAttribute(Long longAttribute) {
		this.longAttribute = longAttribute;
	}

	/**
	 * @return the booleanAttribute
	 */
	public Boolean getBooleanAttribute() {
		return booleanAttribute;
	}

	/**
	 * @param booleanAttribute
	 *            the booleanAttribute to set
	 */
	public void setBooleanAttribute(Boolean booleanAttribute) {
		this.booleanAttribute = booleanAttribute;
	}

	/**
	 * @return the job
	 */
	public Job getJob() {
		return job;
	}

	/**
	 * @param job
	 *            the job to set
	 */
	public void setJob(Job job) {
		this.job = job;
	}

	/**
	 * @return the dateAttribute1
	 */
	public Date getDateAttribute1() {
		return dateAttribute1;
	}

	/**
	 * @param dateAttribute1 the dateAttribute1 to set
	 */
	public void setDateAttribute1(Date dateAttribute1) {
		this.dateAttribute1 = dateAttribute1;
	}
}