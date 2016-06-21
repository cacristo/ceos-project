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

import org.apache.poi.hssf.util.HSSFColor;

import net.ceos.project.poi.annotated.annotation.XlsConfiguration;
import net.ceos.project.poi.annotated.annotation.XlsDecorator;
import net.ceos.project.poi.annotated.annotation.XlsDecorators;
import net.ceos.project.poi.annotated.annotation.XlsElement;
import net.ceos.project.poi.annotated.annotation.XlsSheet;
import net.ceos.project.poi.annotated.core.CellStyleHandler;
import net.ceos.project.poi.annotated.definition.ExtensionFileType;

@XlsDecorators(values = { @XlsDecorator(decoratorName = "anotherDate"), @XlsDecorator(decoratorName = CellStyleHandler.CELL_DECORATOR_NUMERIC, foregroundColor = HSSFColor.BRIGHT_GREEN.index) })
@XlsSheet(title = "Propagation Horizontal Obj")
@XlsConfiguration(nameFile = "PropagationHorizontalObjects", extensionFile = ExtensionFileType.XLSX)
public class PropagationHorizontalObject {

	@XlsElement(title = "Date value", position = 1, formatMask = "yyyy-MM-dd", decorator = "anotherDate")
	private Date dateAttribute;

	@XlsElement(title = "String value", position = 2, decorator = "myDecorator")
	private String stringAttribute;

	@XlsElement(title = "Integer value", position = 3)
	private Integer integerAttribute = 0;

	@XlsElement(title = "Boolean value", position = 4)
	private Boolean booleanAttribute = Boolean.TRUE;

	@XlsElement(title = "Primitive double value", position = 5)
	private double doublePrimitiveAttribute = 0;

	@XlsElement(title = "Primitive long value", position = 6)
	private long longPrimitiveAttribute = 0;

	@XlsElement(title = "Float value", position = 7)
	private Float floatAttribute = 0f;

	@XlsElement(title = "Unit family", position = 8)
	private UnitFamily unitFamily;

	@XlsElement(title = "BigDecimal value", position = 9)
	private BigDecimal bigDecimalAttribute;

	public PropagationHorizontalObject() {
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
