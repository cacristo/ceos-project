package net.ceos.project.poi.annotated.bean;

import java.math.BigDecimal;
import java.util.Date;

import net.ceos.project.poi.annotated.annotation.XlsConfiguration;
import net.ceos.project.poi.annotated.annotation.XlsElement;

@XlsConfiguration(nameFile = "IncompleteConfig")
public class XlsSheetAbsent {

	@XlsElement(title = "Date value", position = 1, formatMask = "yyyyMMdd")
	private Date dateAttribute;

	@XlsElement(title = "String value", position = 2)
	private String stringAttribute;

	@XlsElement(title = "Double value", position = 3, formatMask = "0.0")
	private Double doubleAttribute = 0.0;

	@XlsElement(title = "Long value", position = 4)
	private Long longAttribute = 0L;

	@XlsElement(title = "Primitive boolean value", position = 5)
	private boolean booleanPrimitiveAttribute = false;

	@XlsElement(title = "Primitive float value", position = 6)
	private float floatPrimitiveAttribute = 0f;

	@XlsElement(title = "Unit family", position = 7)
	private UnitFamily unitFamily;

	@XlsElement(title = "BigDecimal value", position = 8)
	private BigDecimal bigDecimalAttribute;

	public XlsSheetAbsent() {
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
}
