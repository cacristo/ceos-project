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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import net.ceos.project.poi.annotated.annotation.XlsConfiguration;
import net.ceos.project.poi.annotated.annotation.XlsElement;
import net.ceos.project.poi.annotated.annotation.XlsSheet;
import net.ceos.project.poi.annotated.definition.ExtensionFileType;
import net.ceos.project.poi.annotated.definition.PropagationType;

@XlsSheet(title = "Multiple type obj",  propagation = PropagationType.PROPAGATION_VERTICAL)
@XlsConfiguration(nameFile = "MultipleTypeOfObjects",  extensionFile = ExtensionFileType.XLSX)
public class MultiTypeObject {

	@XlsElement(title = "Date value", position = 1, formatMask = "yyyy-MM-dd", decorator = "date")
	private Date dateAttribute;

	@XlsElement(title = "String value", position = 2)
	private String stringAttribute;

	@XlsElement(title = "Integer value", position = 3)
	private Integer integerAttribute = 0;

	@XlsElement(title = "Double value", position = 4, formatMask = "0.00000")
	private Double doubleAttribute = 0.0;

	@XlsElement(title = "Long value", position = 5)
	private Long longAttribute = 0L;

	@XlsElement(title = "Boolean value", position = 6, comment = "boolean comment")
	private Boolean booleanAttribute = Boolean.TRUE;

	@XlsElement(title = "job", position = 7)
	private Job job;

	@XlsElement(title = "Primitive int value", position = 8)
	private int integerPrimitiveAttribute = 0;

	@XlsElement(title = "Primitive double value", position = 9, comment = "double comment")
	private double doublePrimitiveAttribute = 0;

	@XlsElement(title = "Primitive long value", position = 10)
	private long longPrimitiveAttribute = 0;

	@XlsElement(title = "Primitive boolean value", position = 11)
	private boolean booleanPrimitiveAttribute = false;

	@XlsElement(title = "address info", position = 12)
	private AddressInfo addressInfo;

	@XlsElement(title = "Float value", position = 13)
	private Float floatAttribute = 0f;

	@XlsElement(title = "Primitive float value", position = 14)
	private float floatPrimitiveAttribute = 0f;

	@XlsElement(title = "some sum", position = 15, isFormula = true)
	private Double sumVal;

	@XlsElement(title = "Unit family", position = 16)
	private UnitFamily unitFamily;

	@XlsElement(title = "BigDecimal value", position = 17)
	private BigDecimal bigDecimalAttribute;

	@XlsElement(title = "Short value", position = 18)
	private short shortPrimitiveAttribute = 0;

	@XlsElement(title = "Short primitive value", position = 19)
	private Short shortAttribute = 0;

	@XlsElement(title = "Local date value", position = 20, formatMask = "yyyy-MM-dd", decorator = "date")
	private LocalDate localDateAttribute;

	@XlsElement(title = "Locel date time value", position = 21, formatMask = "yyyy-MM-dd HH:mm:ss", decorator = "date")
	private LocalDateTime localDateTimeAttribute;

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

	/**
	 * @return the floatAttribute
	 */
	public Float getFloatAttribute() {
		return floatAttribute;
	}

	/**
	 * @param floatAttribute
	 *            the floatAttribute to set
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
	 * @param floatPrimitiveAttribute
	 *            the floatPrimitiveAttribute to set
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
	 * @return the sumVal
	 */
	public Double formulaSumVal() {
		return doubleAttribute + doubleAttribute * 5;
	}

	/**
	 * @param sumVal
	 *            the sumVal to set
	 */
	public void setSumVal(Double sumVal) {
		this.sumVal = sumVal;
	}

	/**
	 * @return the unitFamily
	 */
	public UnitFamily getUnitFamily() {
		return unitFamily;
	}

	/**
	 * @param unitFamily
	 *            the unitFamily to set
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
	 * @param bigDecimalAttribute
	 *            the bigDecimalAttribute to set
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
	 * @param shortPrimitiveAttribute
	 *            the shortPrimitiveAttribute to set
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
	 * @param shortAttribute
	 *            the shortAttribute to set
	 */
	public final void setShortAttribute(Short shortAttribute) {
		this.shortAttribute = shortAttribute;
	}

	/**
	 * @return the localDateAttribute
	 */
	public final LocalDate getLocalDateAttribute() {
		return localDateAttribute;
	}

	/**
	 * @param localDateAttribute
	 *            the localDateAttribute to set
	 */
	public final void setLocalDateAttribute(LocalDate localDateAttribute) {
		this.localDateAttribute = localDateAttribute;
	}

	/**
	 * @return the localDateTimeAttribute
	 */
	public final LocalDateTime getLocalDateTimeAttribute() {
		return localDateTimeAttribute;
	}

	/**
	 * @param localDateTimeAttribute
	 *            the localDateTimeAttribute to set
	 */
	public final void setLocalDateTimeAttribute(LocalDateTime localDateTimeAttribute) {
		this.localDateTimeAttribute = localDateTimeAttribute;
	}
}
