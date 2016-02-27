package org.jaexcel.framework.JAEX.bean;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.poi.hssf.util.HSSFColor;
import org.jaexcel.framework.JAEX.annotation.XlsConfiguration;
import org.jaexcel.framework.JAEX.annotation.XlsDecorator;
import org.jaexcel.framework.JAEX.annotation.XlsDecorators;
import org.jaexcel.framework.JAEX.annotation.XlsElement;
import org.jaexcel.framework.JAEX.annotation.XlsSheet;
import org.jaexcel.framework.JAEX.definition.ExtensionFileType;
import org.jaexcel.framework.JAEX.definition.PropagationType;
@XlsDecorators(values = {
		@XlsDecorator(decoratorName = "extraNumeric", fontItalic = true, fontBold = true, foregroundColor = HSSFColor.ORANGE.index) })
@XlsSheet(title = "Performance obj", propagation = PropagationType.PROPAGATION_HORIZONTAL)
@XlsConfiguration(nameFile = "PerformanceTestFile", extensionFile = ExtensionFileType.XLSX)
public class PerformanceObject {

	@XlsElement(title = "Date value", position = 1, formatMask = "yyyy/MM/dd")
	private Date dateAttribute1;

	@XlsElement(title = "String value 1", position = 2)
	private String stringAttribute1;

	@XlsElement(title = "Integer value 1", position = 3)
	private Integer integerAttribute1;

	@XlsElement(title = "Integer value 2", position = 4, decorator="extraNumeric")
	private Integer integerAttribute2;

	@XlsElement(title = "Integer value 3", position = 5)
	private Integer integerAttribute3;

	@XlsElement(title = "Integer value 4", position = 6)
	private Integer integerAttribute4;

	@XlsElement(title = "Integer value 5", position = 7)
	private Integer integerAttribute5;

	@XlsElement(title = "Integer value 6", position = 8)
	private Integer integerAttribute6;

	@XlsElement(title = "Integer value 7", position = 9)
	private Integer integerAttribute7;

	@XlsElement(title = "Double value 1", position = 10, formatMask = "0.00")
	private Double doubleAttribute1;

	@XlsElement(title = "Double value 2", position = 11, formatMask = "0.0")
	private Double doubleAttribute2;

	@XlsElement(title = "Double value 3", position = 12, transformMask = "0.00000", decorator="extraNumeric")
	private Double doubleAttribute3;

	@XlsElement(title = "Long value", position = 13)
	private Long longAttribute;

	@XlsElement(title = "Boolean value 1", position = 14)
	private Boolean booleanAttribute1;

	@XlsElement(title = "Boolean value 2", position = 15)
	private Boolean booleanAttribute2;

	@XlsElement(title = "Boolean value 3", position = 16)
	private Boolean booleanAttribute3;

	@XlsElement(title = "Boolean value 4", position = 17)
	private Boolean booleanAttribute4;

	@XlsElement(title = "job", position = 18)
	private Job job;

	@XlsElement(title = "Primitive int value", position = 19)
	private int integerPrimitiveAttribute1;

	@XlsElement(title = "Primitive int value", position = 20)
	private int integerPrimitiveAttribute2;

	@XlsElement(title = "Primitive int value", position = 21)
	private int integerPrimitiveAttribute3;

	@XlsElement(title = "Primitive int value", position = 22)
	private int integerPrimitiveAttribute4;

	@XlsElement(title = "Primitive int value", position = 23)
	private int integerPrimitiveAttribute5;

	@XlsElement(title = "Primitive double value", position = 24)
	private double doublePrimitiveAttribute;

	@XlsElement(title = "Primitive long value", position = 25)
	private long longPrimitiveAttribute;

	@XlsElement(title = "Primitive boolean value", position = 26, transformMask="Y/N")
	private boolean booleanPrimitiveAttribute = false;

	@XlsElement(title = "address info", position = 27)
	private AddressInfo addressInfo;

	@XlsElement(title = "Float value", position = 28)
	private Float floatAttribute;

	@XlsElement(title = "Primitive float value", position = 29)
	private float floatPrimitiveAttribute;

	@XlsElement(title = "some sum", position = 30, isFormula = true)
	private Double sumVal;

	@XlsElement(title = "Unit family", position = 31)
	private UnitFamily unitFamily;

	@XlsElement(title = "BigDecimal value", position = 32)
	private BigDecimal bigDecimalAttribute;

	@XlsElement(title = "String value 2", position = 33)
	private String stringAttribute2;

	@XlsElement(title = "String value 3", position = 34)
	private String stringAttribute3;

	@XlsElement(title = "String value 4", position = 35)
	private String stringAttribute4;

	@XlsElement(title = "String value 5", position = 36)
	private String stringAttribute5;

	@XlsElement(title = "String value 6", position = 37)
	private String stringAttribute6;

	@XlsElement(title = "String value 7", position = 38)
	private String stringAttribute7;

	@XlsElement(title = "String value 8", position = 39)
	private String stringAttribute8;

	@XlsElement(title = "String value 9", position = 40)
	private String stringAttribute9;

	@XlsElement(title = "String value 10", position = 41)
	private String stringAttribute10;

	@XlsElement(title = "String value 11", position = 42)
	private String stringAttribute11;

	@XlsElement(title = "String value 12", position = 43)
	private String stringAttribute12;

	@XlsElement(title = "Date value transform", position = 44, transformMask = "yyyy-MM-dd")
	private Date dateAttribute2;

	@XlsElement(title = "Date value transform", position = 45, formatMask = "yyyy-MM")
	private Date dateAttribute3;

	public PerformanceObject() {
	}

	/**
	 * @return the dateAttribute
	 */
	public Date getDateAttribute1() {
		return dateAttribute1;
	}

	/**
	 * @param dateAttribute
	 *            the dateAttribute to set
	 */
	public void setDateAttribute1(Date dateAttribute) {
		this.dateAttribute1 = dateAttribute;
	}

	/**
	 * @return the stringAttribute1
	 */
	public String getStringAttribute1() {
		return stringAttribute1;
	}

	/**
	 * @param stringAttribute1
	 *            the stringAttribute1 to set
	 */
	public void setStringAttribute1(String stringAttribute1) {
		this.stringAttribute1 = stringAttribute1;
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
	 * @return the integerAttribute3
	 */
	public Integer getIntegerAttribute3() {
		return integerAttribute3;
	}

	/**
	 * @param integerAttribute3
	 *            the integerAttribute3 to set
	 */
	public void setIntegerAttribute3(Integer integerAttribute3) {
		this.integerAttribute3 = integerAttribute3;
	}

	/**
	 * @return the integerAttribute4
	 */
	public Integer getIntegerAttribute4() {
		return integerAttribute4;
	}

	/**
	 * @param integerAttribute4
	 *            the integerAttribute4 to set
	 */
	public void setIntegerAttribute4(Integer integerAttribute4) {
		this.integerAttribute4 = integerAttribute4;
	}

	/**
	 * @return the integerAttribute5
	 */
	public Integer getIntegerAttribute5() {
		return integerAttribute5;
	}

	/**
	 * @param integerAttribute5
	 *            the integerAttribute5 to set
	 */
	public void setIntegerAttribute5(Integer integerAttribute5) {
		this.integerAttribute5 = integerAttribute5;
	}

	/**
	 * @return the integerAttribute6
	 */
	public Integer getIntegerAttribute6() {
		return integerAttribute6;
	}

	/**
	 * @param integerAttribute6
	 *            the integerAttribute6 to set
	 */
	public void setIntegerAttribute6(Integer integerAttribute6) {
		this.integerAttribute6 = integerAttribute6;
	}

	/**
	 * @return the integerAttribute7
	 */
	public Integer getIntegerAttribute7() {
		return integerAttribute7;
	}

	/**
	 * @param integerAttribute7
	 *            the integerAttribute7 to set
	 */
	public void setIntegerAttribute7(Integer integerAttribute7) {
		this.integerAttribute7 = integerAttribute7;
	}

	/**
	 * @return the doubleAttribute1
	 */
	public Double getDoubleAttribute1() {
		return doubleAttribute1;
	}

	/**
	 * @param doubleAttribute1
	 *            the doubleAttribute1 to set
	 */
	public void setDoubleAttribute1(Double doubleAttribute1) {
		this.doubleAttribute1 = doubleAttribute1;
	}

	/**
	 * @return the doubleAttribute2
	 */
	public Double getDoubleAttribute2() {
		return doubleAttribute2;
	}

	/**
	 * @param doubleAttribute2
	 *            the doubleAttribute2 to set
	 */
	public void setDoubleAttribute2(Double doubleAttribute2) {
		this.doubleAttribute2 = doubleAttribute2;
	}

	/**
	 * @return the doubleAttribute3
	 */
	public Double getDoubleAttribute3() {
		return doubleAttribute3;
	}

	/**
	 * @param doubleAttribute3
	 *            the doubleAttribute3 to set
	 */
	public void setDoubleAttribute3(Double doubleAttribute3) {
		this.doubleAttribute3 = doubleAttribute3;
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
	 * @return the booleanAttribute1
	 */
	public Boolean getBooleanAttribute1() {
		return booleanAttribute1;
	}

	/**
	 * @param booleanAttribute1
	 *            the booleanAttribute1 to set
	 */
	public void setBooleanAttribute1(Boolean booleanAttribute1) {
		this.booleanAttribute1 = booleanAttribute1;
	}

	/**
	 * @return the booleanAttribute2
	 */
	public Boolean getBooleanAttribute2() {
		return booleanAttribute2;
	}

	/**
	 * @param booleanAttribute2
	 *            the booleanAttribute2 to set
	 */
	public void setBooleanAttribute2(Boolean booleanAttribute2) {
		this.booleanAttribute2 = booleanAttribute2;
	}

	/**
	 * @return the booleanAttribute3
	 */
	public Boolean getBooleanAttribute3() {
		return booleanAttribute3;
	}

	/**
	 * @param booleanAttribute3
	 *            the booleanAttribute3 to set
	 */
	public void setBooleanAttribute3(Boolean booleanAttribute3) {
		this.booleanAttribute3 = booleanAttribute3;
	}

	/**
	 * @return the booleanAttribute4
	 */
	public Boolean getBooleanAttribute4() {
		return booleanAttribute4;
	}

	/**
	 * @param booleanAttribute4
	 *            the booleanAttribute4 to set
	 */
	public void setBooleanAttribute4(Boolean booleanAttribute4) {
		this.booleanAttribute4 = booleanAttribute4;
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
	 * @return the integerPrimitiveAttribute1
	 */
	public int getIntegerPrimitiveAttribute1() {
		return integerPrimitiveAttribute1;
	}

	/**
	 * @param integerPrimitiveAttribute1
	 *            the integerPrimitiveAttribute1 to set
	 */
	public void setIntegerPrimitiveAttribute1(int integerPrimitiveAttribute1) {
		this.integerPrimitiveAttribute1 = integerPrimitiveAttribute1;
	}

	/**
	 * @return the integerPrimitiveAttribute2
	 */
	public int getIntegerPrimitiveAttribute2() {
		return integerPrimitiveAttribute2;
	}

	/**
	 * @param integerPrimitiveAttribute2
	 *            the integerPrimitiveAttribute2 to set
	 */
	public void setIntegerPrimitiveAttribute2(int integerPrimitiveAttribute2) {
		this.integerPrimitiveAttribute2 = integerPrimitiveAttribute2;
	}

	/**
	 * @return the integerPrimitiveAttribute3
	 */
	public int getIntegerPrimitiveAttribute3() {
		return integerPrimitiveAttribute3;
	}

	/**
	 * @param integerPrimitiveAttribute3
	 *            the integerPrimitiveAttribute3 to set
	 */
	public void setIntegerPrimitiveAttribute3(int integerPrimitiveAttribute3) {
		this.integerPrimitiveAttribute3 = integerPrimitiveAttribute3;
	}

	/**
	 * @return the integerPrimitiveAttribute4
	 */
	public int getIntegerPrimitiveAttribute4() {
		return integerPrimitiveAttribute4;
	}

	/**
	 * @param integerPrimitiveAttribute4
	 *            the integerPrimitiveAttribute4 to set
	 */
	public void setIntegerPrimitiveAttribute4(int integerPrimitiveAttribute4) {
		this.integerPrimitiveAttribute4 = integerPrimitiveAttribute4;
	}

	/**
	 * @return the integerPrimitiveAttribute5
	 */
	public int getIntegerPrimitiveAttribute5() {
		return integerPrimitiveAttribute5;
	}

	/**
	 * @param integerPrimitiveAttribute5
	 *            the integerPrimitiveAttribute5 to set
	 */
	public void setIntegerPrimitiveAttribute5(int integerPrimitiveAttribute5) {
		this.integerPrimitiveAttribute5 = integerPrimitiveAttribute5;
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
	 * @return the stringAttribute2
	 */
	public String getStringAttribute2() {
		return stringAttribute2;
	}

	/**
	 * @param stringAttribute2
	 *            the stringAttribute2 to set
	 */
	public void setStringAttribute2(String stringAttribute2) {
		this.stringAttribute2 = stringAttribute2;
	}

	/**
	 * @return the stringAttribute3
	 */
	public String getStringAttribute3() {
		return stringAttribute3;
	}

	/**
	 * @param stringAttribute3
	 *            the stringAttribute3 to set
	 */
	public void setStringAttribute3(String stringAttribute3) {
		this.stringAttribute3 = stringAttribute3;
	}

	/**
	 * @return the stringAttribute4
	 */
	public String getStringAttribute4() {
		return stringAttribute4;
	}

	/**
	 * @param stringAttribute4
	 *            the stringAttribute4 to set
	 */
	public void setStringAttribute4(String stringAttribute4) {
		this.stringAttribute4 = stringAttribute4;
	}

	/**
	 * @return the stringAttribute5
	 */
	public String getStringAttribute5() {
		return stringAttribute5;
	}

	/**
	 * @param stringAttribute5
	 *            the stringAttribute5 to set
	 */
	public void setStringAttribute5(String stringAttribute5) {
		this.stringAttribute5 = stringAttribute5;
	}

	/**
	 * @return the stringAttribute6
	 */
	public String getStringAttribute6() {
		return stringAttribute6;
	}

	/**
	 * @param stringAttribute6
	 *            the stringAttribute6 to set
	 */
	public void setStringAttribute6(String stringAttribute6) {
		this.stringAttribute6 = stringAttribute6;
	}

	/**
	 * @return the stringAttribute7
	 */
	public String getStringAttribute7() {
		return stringAttribute7;
	}

	/**
	 * @param stringAttribute7
	 *            the stringAttribute7 to set
	 */
	public void setStringAttribute7(String stringAttribute7) {
		this.stringAttribute7 = stringAttribute7;
	}

	/**
	 * @return the stringAttribute8
	 */
	public String getStringAttribute8() {
		return stringAttribute8;
	}

	/**
	 * @param stringAttribute8
	 *            the stringAttribute8 to set
	 */
	public void setStringAttribute8(String stringAttribute8) {
		this.stringAttribute8 = stringAttribute8;
	}

	/**
	 * @return the stringAttribute9
	 */
	public String getStringAttribute9() {
		return stringAttribute9;
	}

	/**
	 * @param stringAttribute9
	 *            the stringAttribute9 to set
	 */
	public void setStringAttribute9(String stringAttribute9) {
		this.stringAttribute9 = stringAttribute9;
	}

	/**
	 * @return the stringAttribute10
	 */
	public String getStringAttribute10() {
		return stringAttribute10;
	}

	/**
	 * @param stringAttribute10
	 *            the stringAttribute10 to set
	 */
	public void setStringAttribute10(String stringAttribute10) {
		this.stringAttribute10 = stringAttribute10;
	}

	/**
	 * @return the stringAttribute11
	 */
	public String getStringAttribute11() {
		return stringAttribute11;
	}

	/**
	 * @param stringAttribute11
	 *            the stringAttribute11 to set
	 */
	public void setStringAttribute11(String stringAttribute11) {
		this.stringAttribute11 = stringAttribute11;
	}

	/**
	 * @return the stringAttribute12
	 */
	public String getStringAttribute12() {
		return stringAttribute12;
	}

	/**
	 * @param stringAttribute12
	 *            the stringAttribute12 to set
	 */
	public void setStringAttribute12(String stringAttribute12) {
		this.stringAttribute12 = stringAttribute12;
	}

	/**
	 * @return the dateAttribute2
	 */
	public Date getDateAttribute2() {
		return dateAttribute2;
	}

	/**
	 * @param dateAttribute2
	 *            the dateAttribute2 to set
	 */
	public void setDateAttribute2(Date dateAttribute2) {
		this.dateAttribute2 = dateAttribute2;
	}

	/**
	 * @return the dateAttribute3
	 */
	public Date getDateAttribute3() {
		return dateAttribute3;
	}

	/**
	 * @param dateAttribute3
	 *            the dateAttribute3 to set
	 */
	public void setDateAttribute3(Date dateAttribute3) {
		this.dateAttribute3 = dateAttribute3;
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
		return doubleAttribute1 + doubleAttribute2 * 5;
	}

	/**
	 * @param sumVal
	 *            the sumVal to set
	 */
	public void setSumVal(Double sumVal) {
		this.sumVal = sumVal;
	}

}
