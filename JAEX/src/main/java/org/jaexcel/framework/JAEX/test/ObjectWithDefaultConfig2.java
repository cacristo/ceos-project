package org.jaexcel.framework.JAEX.test;

import java.util.Date;

public class ObjectWithDefaultConfig2 {

	private Date dateAttribute;

	private String stringAttribute;

	private int intAttribute = 0;
	private double doubleAttribute = 0;
	private long longAttribute = 0;

	private EnumTest cascade;
	
	public ObjectWithDefaultConfig2() {
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
	 * @return the intAttribute
	 */
	public int getIntAttribute() {
		return intAttribute;
	}

	/**
	 * @param intAttribute
	 *            the intAttribute to set
	 */
	public void setIntAttribute(int intAttribute) {
		this.intAttribute = intAttribute;
	}

	/**
	 * @return the doubleAttribute
	 */
	public double getDoubleAttribute() {
		return doubleAttribute;
	}

	/**
	 * @param doubleAttribute the doubleAttribute to set
	 */
	public void setDoubleAttribute(double doubleAttribute) {
		this.doubleAttribute = doubleAttribute;
	}

	/**
	 * @return the longAttribute
	 */
	public long getLongAttribute() {
		return longAttribute;
	}

	/**
	 * @param longAttribute the longAttribute to set
	 */
	public void setLongAttribute(long longAttribute) {
		this.longAttribute = longAttribute;
	}

	/**
	 * @return the cascade
	 */
	public EnumTest getCascade() {
		return cascade;
	}

	/**
	 * @param cascade the cascade to set
	 */
	public void setCascade(EnumTest cascade) {
		this.cascade = cascade;
	}
	
}
