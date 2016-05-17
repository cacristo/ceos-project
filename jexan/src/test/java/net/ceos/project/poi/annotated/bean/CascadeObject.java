package net.ceos.project.poi.annotated.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.ceos.project.poi.annotated.annotation.XlsConfiguration;
import net.ceos.project.poi.annotated.annotation.XlsElement;
import net.ceos.project.poi.annotated.annotation.XlsSheet;
import net.ceos.project.poi.annotated.definition.CascadeType;
import net.ceos.project.poi.annotated.definition.PropagationType;

@XlsSheet(title = "Cascade full mode", propagation = PropagationType.PROPAGATION_HORIZONTAL, cascadeLevel = CascadeType.CASCADE_LEVEL_ONE)
@XlsConfiguration(nameFile = "CascadeObject")
public class CascadeObject {

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

	@XlsElement(title = "Collection as List", position = 8)
	private List<BasicObject> basicObjectList;

	@XlsElement(title = "Collection as ArrayList", position = 9)
	private ArrayList<ObjectConfigurable> objectConfList;

	@XlsElement(title = "Collections", position = 10)
	private ArrayList<CascadeL1> objectsList;
	
	public CascadeObject() {
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
	 * @return the basicObjectList
	 */
	public final List<BasicObject> getBasicObjectList() {
		return basicObjectList;
	}

	/**
	 * @param basicObjectList the basicObjectList to set
	 */
	public final void setBasicObjectList(List<BasicObject> basicObjectList) {
		this.basicObjectList = basicObjectList;
	}

	/**
	 * @return the objectConfList
	 */
	public final ArrayList<ObjectConfigurable> getObjectConfList() {
		return objectConfList;
	}

	/**
	 * @param objectConfList the objectConfList to set
	 */
	public final void setObjectConfList(ArrayList<ObjectConfigurable> objectConfList) {
		this.objectConfList = objectConfList;
	}

	/**
	 * @return the objectsList
	 */
	public final ArrayList<CascadeL1> getObjectsList() {
		return objectsList;
	}

	/**
	 * @param objectsList the objectsList to set
	 */
	public final void setObjectsList(ArrayList<CascadeL1> objectsList) {
		this.objectsList = objectsList;
	}
}
