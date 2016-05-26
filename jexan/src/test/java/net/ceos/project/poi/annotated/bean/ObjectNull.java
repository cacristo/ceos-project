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
package net.ceos.project.poi.annotated.bean;

import java.math.BigDecimal;
import java.util.Date;

import net.ceos.project.poi.annotated.annotation.XlsConfiguration;
import net.ceos.project.poi.annotated.annotation.XlsElement;
import net.ceos.project.poi.annotated.annotation.XlsSheet;
import net.ceos.project.poi.annotated.definition.PropagationType;

@XlsSheet(title = "Multiple null obj", propagation = PropagationType.PROPAGATION_VERTICAL)
@XlsConfiguration(nameFile = "MultipleNullObjects")
public class ObjectNull {
	@XlsElement(title = "Date value", position = 1, formatMask = "yyyy-MM-dd")
	private Date dateAttribute;

	@XlsElement(title = "String value", position = 2)
	private String stringAttribute;

	@XlsElement(title = "Integer value", position = 3)
	private Integer integerAttribute;

	@XlsElement(title = "Double value", position = 4, formatMask = "0.00000")
	private Double doubleAttribute;

	@XlsElement(title = "Long value", position = 5)
	private Long longAttribute;

	@XlsElement(title = "Boolean value", position = 6, comment="boolean comment")
	private Boolean booleanAttribute = Boolean.TRUE;

	@XlsElement(title = "job", position = 7)
	private Job job;

	@XlsElement(title = "Primitive int value", position = 8)
	private int integerPrimitiveAttribute;

	@XlsElement(title = "Primitive double value", position = 9, comment="double comment")
	private double doublePrimitiveAttribute;

	@XlsElement(title = "Primitive long value", position = 10)
	private long longPrimitiveAttribute;

	@XlsElement(title = "Primitive boolean value", position = 11)
	private boolean booleanPrimitiveAttribute;

	@XlsElement(title = "address info", position = 12)
	private AddressInfo addressInfo;

	@XlsElement(title = "Float value", position = 13)
	private Float floatAttribute;

	@XlsElement(title = "Primitive float value", position = 14)
	private float floatPrimitiveAttribute;

	@XlsElement(title="some sum", position = 15, isFormula = true)
	private Double sumVal;
	
	@XlsElement(title = "Unit family", position = 16)
	private UnitFamily unitFamily;
	
	@XlsElement(title = "BigDecimal value", position = 17)
	private BigDecimal bigDecimalAttribute;

	@XlsElement(title = "Short value", position = 18)
	private short shortPrimitiveAttribute;

	@XlsElement(title = "Short primitive value", position = 19)
	private Short shortAttribute;

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

	/**
	 * @return the doubleAttribute
	 */
	public Double getDoubleAttribute() {
		return doubleAttribute;
	}

	/**
	 * @param doubleAttribute the doubleAttribute to set
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
	 * @param longAttribute the longAttribute to set
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
	 * @param booleanAttribute the booleanAttribute to set
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
	 * @param job the job to set
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
	 * @param integerPrimitiveAttribute the integerPrimitiveAttribute to set
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
	 * @param doublePrimitiveAttribute the doublePrimitiveAttribute to set
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
	 * @param longPrimitiveAttribute the longPrimitiveAttribute to set
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
	 * @param booleanPrimitiveAttribute the booleanPrimitiveAttribute to set
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
	 * @param addressInfo the addressInfo to set
	 */
	public void setAddressInfo(AddressInfo addressInfo) {
		this.addressInfo = addressInfo;
	}

	/**
	 * @return the floatAttribute
	 */
	public Float getFloatAttribute() {
		return floatAttribute;
	}

	/**
	 * @param floatAttribute the floatAttribute to set
	 */
	public void setFloatAttribute(Float floatAttribute) {
		this.floatAttribute = floatAttribute;
	}

	/**
	 * @return the floatPrimitiveAttribute
	 */
	public float getFloatPrimitiveAttribute() {
		return floatPrimitiveAttribute;
	}

	/**
	 * @param floatPrimitiveAttribute the floatPrimitiveAttribute to set
	 */
	public void setFloatPrimitiveAttribute(float floatPrimitiveAttribute) {
		this.floatPrimitiveAttribute = floatPrimitiveAttribute;
	}

	/**
	 * @return the sumVal
	 */
	public Double getSumVal() {
		return sumVal;
	}

	/**
	 * @param sumVal the sumVal to set
	 */
	public void setSumVal(Double sumVal) {
		this.sumVal = sumVal;
	}

	/**
	 * @return the sumVal
	 */
	public Double formulaSumVal() {
		return doubleAttribute + doubleAttribute * 5;
	}

	/**
	 * @return the unitFamily
	 */
	public UnitFamily getUnitFamily() {
		return unitFamily;
	}

	/**
	 * @param unitFamily the unitFamily to set
	 */
	public void setUnitFamily(UnitFamily unitFamily) {
		this.unitFamily = unitFamily;
	}

	/**
	 * @return the bigDecimalAttribute
	 */
	public BigDecimal getBigDecimalAttribute() {
		return bigDecimalAttribute;
	}

	/**
	 * @param bigDecimalAttribute the bigDecimalAttribute to set
	 */
	public void setBigDecimalAttribute(BigDecimal bigDecimalAttribute) {
		this.bigDecimalAttribute = bigDecimalAttribute;
	}

	/**
	 * @return the shortPrimitiveAttribute
	 */
	public final short getShortPrimitiveAttribute() {
		return shortPrimitiveAttribute;
	}

	/**
	 * @param shortPrimitiveAttribute the shortPrimitiveAttribute to set
	 */
	public final void setShortPrimitiveAttribute(short shortPrimitiveAttribute) {
		this.shortPrimitiveAttribute = shortPrimitiveAttribute;
	}

	/**
	 * @return the shortAttribute
	 */
	public final Short getShortAttribute() {
		return shortAttribute;
	}

	/**
	 * @param shortAttribute the shortAttribute to set
	 */
	public final void setShortAttribute(Short shortAttribute) {
		this.shortAttribute = shortAttribute;
	}
}
