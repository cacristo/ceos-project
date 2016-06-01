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
package net.ceos.project.poi.annotated.bean.collection;

import java.util.Date;

import net.ceos.project.poi.annotated.annotation.XlsConfiguration;
import net.ceos.project.poi.annotated.annotation.XlsElement;
import net.ceos.project.poi.annotated.annotation.XlsSheet;
import net.ceos.project.poi.annotated.bean.AddressInfo;
import net.ceos.project.poi.annotated.bean.Job;
import net.ceos.project.poi.annotated.bean.UnitFamily;
import net.ceos.project.poi.annotated.definition.CascadeType;
import net.ceos.project.poi.annotated.definition.ExtensionFileType;
import net.ceos.project.poi.annotated.definition.PropagationType;

@XlsConfiguration(nameFile = "collection_object_multi_v", extensionFile = ExtensionFileType.XLSX)
@XlsSheet(title = "List multiple type obj vertical", propagation = PropagationType.PROPAGATION_VERTICAL, cascadeLevel=CascadeType.CASCADE_L1)
public class MultiTypeObjectPropV {

	@XlsElement(title = "Date value", position = 1, formatMask = "yyyy-MM-dd")
	private Date dateAttribute;

	@XlsElement(title = "String value", position = 2)
	private String stringAttribute;

	@XlsElement(title = "Integer value", position = 3)
	private Integer integerAttribute = 0;

	@XlsElement(title = "Double value", position = 4, formatMask = "0.00000")
	private Double doubleAttribute = 0.0;

	@XlsElement(title = "Long value", position = 5)
	private Long longAttribute = 0L;

	@XlsElement(title = "Boolean value", position = 6, comment="boolean comment")
	private Boolean booleanAttribute = Boolean.TRUE;

	@XlsElement(title = "job", position = 7)
	private Job job;

	@XlsElement(title = "Primitive int value", position = 8)
	private int integerPrimitiveAttribute = 0;

	@XlsElement(title = "Primitive double value", position = 9, comment="double comment")
	private double doublePrimitiveAttribute = 0;

	@XlsElement(title = "Primitive long value", position = 10)
	private long longPrimitiveAttribute = 0;

	@XlsElement(title = "Primitive boolean value", position = 11)
	private boolean booleanPrimitiveAttribute = false;

	@XlsElement(title = "address info", position = 12)
	private AddressInfo addressInfo;

	@XlsElement(title = "Float value", position =13)
	private Float floatAttribute = 0f;

	@XlsElement(title = "Primitive float value", position = 14)
	private float floatPrimitiveAttribute = 0f;

	@XlsElement(title="some sum", position = 15, isFormula = true)
	private Double sumVal;
	
	@XlsElement(title = "Unit family", position = 16)
	private UnitFamily unitFamily;
	
	public MultiTypeObjectPropV() {
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public Double formulaSumVal() {
		return (double) 4;
	}
	public Date getDateAttribute() {
		return dateAttribute;
	}

	public void setDateAttribute(Date dateAttribute) {
		this.dateAttribute = dateAttribute;
	}

	public String getStringAttribute() {
		return stringAttribute;
	}

	public void setStringAttribute(String stringAttribute) {
		this.stringAttribute = stringAttribute;
	}

	public Integer getIntegerAttribute() {
		return integerAttribute;
	}

	public void setIntegerAttribute(Integer integerAttribute) {
		this.integerAttribute = integerAttribute;
	}

	public Double getDoubleAttribute() {
		return doubleAttribute;
	}

	public void setDoubleAttribute(Double doubleAttribute) {
		this.doubleAttribute = doubleAttribute;
	}

	public Long getLongAttribute() {
		return longAttribute;
	}

	public void setLongAttribute(Long longAttribute) {
		this.longAttribute = longAttribute;
	}

	public Boolean getBooleanAttribute() {
		return booleanAttribute;
	}

	public void setBooleanAttribute(Boolean booleanAttribute) {
		this.booleanAttribute = booleanAttribute;
	}

	public int getIntegerPrimitiveAttribute() {
		return integerPrimitiveAttribute;
	}

	public void setIntegerPrimitiveAttribute(int integerPrimitiveAttribute) {
		this.integerPrimitiveAttribute = integerPrimitiveAttribute;
	}

	public double getDoublePrimitiveAttribute() {
		return doublePrimitiveAttribute;
	}

	public void setDoublePrimitiveAttribute(double doublePrimitiveAttribute) {
		this.doublePrimitiveAttribute = doublePrimitiveAttribute;
	}

	public long getLongPrimitiveAttribute() {
		return longPrimitiveAttribute;
	}

	public void setLongPrimitiveAttribute(long longPrimitiveAttribute) {
		this.longPrimitiveAttribute = longPrimitiveAttribute;
	}

	public boolean isBooleanPrimitiveAttribute() {
		return booleanPrimitiveAttribute;
	}

	public void setBooleanPrimitiveAttribute(boolean booleanPrimitiveAttribute) {
		this.booleanPrimitiveAttribute = booleanPrimitiveAttribute;
	}

	public AddressInfo getAddressInfo() {
		return addressInfo;
	}

	public void setAddressInfo(AddressInfo addressInfo) {
		this.addressInfo = addressInfo;
	}

	public Float getFloatAttribute() {
		return floatAttribute;
	}

	public void setFloatAttribute(Float floatAttribute) {
		this.floatAttribute = floatAttribute;
	}

	public float getFloatPrimitiveAttribute() {
		return floatPrimitiveAttribute;
	}

	public void setFloatPrimitiveAttribute(float floatPrimitiveAttribute) {
		this.floatPrimitiveAttribute = floatPrimitiveAttribute;
	}

	public Double getSumVal() {
		return sumVal;
	}

	public void setSumVal(Double sumVal) {
		this.sumVal = sumVal;
	}

	public UnitFamily getUnitFamily() {
		return unitFamily;
	}

	public void setUnitFamily(UnitFamily unitFamily) {
		this.unitFamily = unitFamily;
	}
	
	

	
}
