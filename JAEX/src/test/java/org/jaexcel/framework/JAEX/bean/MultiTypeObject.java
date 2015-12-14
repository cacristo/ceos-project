package org.jaexcel.framework.JAEX.bean;

import java.util.Date;

import org.jaexcel.framework.JAEX.annotation.XlsConfiguration;
import org.jaexcel.framework.JAEX.annotation.XlsElement;
import org.jaexcel.framework.JAEX.annotation.XlsSheet;
import org.jaexcel.framework.JAEX.definition.ExtensionFileType;
import org.jaexcel.framework.JAEX.definition.PropagationType;

@XlsSheet(title = "Multiple type obj", propagation = PropagationType.PROPAGATION_VERTICAL)
@XlsConfiguration(nameFile = "MultipleTypeOfObjects", extensionFile = ExtensionFileType.XLSX)
public class MultiTypeObject {

	@XlsElement(title = "Date value", position = 1, decorator = "yyyyMMdd")
	private Date dateAttribute;

	@XlsElement(title = "String value", position = 2)
	private String stringAttribute;

	@XlsElement(title = "Integer value", position = 3)
	private Integer integerAttribute = 0;

	@XlsElement(title = "Double value", position = 4, decorator="0.00000")
	private Double doubleAttribute = 0.0;

	@XlsElement(title = "Long value", position = 5)
	private Long longAttribute = 0L;

	@XlsElement(title = "Boolean value", position = 6)
	private Boolean booleanAttribute = Boolean.TRUE;

	@XlsElement(title = "job", position = 7)
	private Job job;

	@XlsElement(title = "Primitive int value", position = 8)
	private int integerPrimitiveAttribute = 0;

	@XlsElement(title = "Primitive double value", position = 9)
	private double doublePrimitiveAttribute = 0;

	@XlsElement(title = "Primitive long value", position = 10)
	private long longPrimitiveAttribute = 0;

	@XlsElement(title = "Primitive boolean value", position = 11)
	private boolean booleanPrimitiveAttribute = false;

	@XlsElement(title = "address info", position = 12)
	private AddressInfo addressInfo;

	public MultiTypeObject() {
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
	 * @return the integerPrimitiveAttribute
	 */
	public int getIntegerPrimitiveAttribute() {
		return integerPrimitiveAttribute;
	}

	/**
	 * @param integerPrimitiveAttribute
	 *            the integerPrimitiveAttribute to set
	 */
	public void setIntegerPrimitiveAttribute(int integerPrimitiveAttribute) {
		this.integerPrimitiveAttribute = integerPrimitiveAttribute;
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

	/**
	 * @return the longPrimitiveAttribute
	 */
	public long getLongPrimitiveAttribute() {
		return longPrimitiveAttribute;
	}

	/**
	 * @param longPrimitiveAttribute
	 *            the longPrimitiveAttribute to set
	 */
	public void setLongPrimitiveAttribute(long longPrimitiveAttribute) {
		this.longPrimitiveAttribute = longPrimitiveAttribute;
	}

	/**
	 * @return the booleanPrimitiveAttribute
	 */
	public boolean isBooleanPrimitiveAttribute() {
		return booleanPrimitiveAttribute;
	}

	/**
	 * @param booleanPrimitiveAttribute
	 *            the booleanPrimitiveAttribute to set
	 */
	public void setBooleanPrimitiveAttribute(boolean booleanPrimitiveAttribute) {
		this.booleanPrimitiveAttribute = booleanPrimitiveAttribute;
	}

	/**
	 * @return the addressInfo
	 */
	public AddressInfo getAddressInfo() {
		return addressInfo;
	}

	/**
	 * @param addressInfo
	 *            the addressInfo to set
	 */
	public void setAddressInfo(AddressInfo addressInfo) {
		this.addressInfo = addressInfo;
	}
}
