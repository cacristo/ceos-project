package net.ceos.project.poi.annotated.bean;

import java.util.Date;

import net.ceos.project.poi.annotated.annotation.XlsConfiguration;
import net.ceos.project.poi.annotated.annotation.XlsElement;
import net.ceos.project.poi.annotated.annotation.XlsNestedHeader;
import net.ceos.project.poi.annotated.annotation.XlsSheet;

@XlsSheet(title = "Default object sample")
@XlsConfiguration(nameFile = "DefaultSample")
public class ObjectWithDefaultConfig {

	@XlsNestedHeader(title = "Main info")
	@XlsElement(title = "Date value", position = 1)
	private Date dateAttribute;

	@XlsElement(title = "String value", position = 2)
	private String stringAttribute;

	@XlsElement(title = "Integer value", position = 3)
	private Integer integerAttribute = 0;

	@XlsElement(title = "Double value", position = 4)
	private Double doubleAttribute = 0.0;

	@XlsElement(title = "Long value", position = 5)
	private Long longAttribute = 0L;

	@XlsElement(title = "Boolean value", position = 6)
	private Boolean booleanAttribute = Boolean.TRUE;

	@XlsElement(title = "job", position = 7)
	private Job job;

	@XlsElement(title = "another int value", position = 8)
	private int anotherAttribute = 0;

	@XlsElement(title = "job", position = 9)
	private Job job2;
	
	@XlsElement(title = "Another string value", position = 10)
	private String anotherStringAttribute;
	
	public ObjectWithDefaultConfig() {
	}

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
	 * @return the anotherAttribute
	 */
	public int getAnotherAttribute() {
		return anotherAttribute;
	}

	/**
	 * @param anotherAttribute the anotherAttribute to set
	 */
	public void setAnotherAttribute(int anotherAttribute) {
		this.anotherAttribute = anotherAttribute;
	}

	/**
	 * @return the job2
	 */
	public Job getJob2() {
		return job2;
	}

	/**
	 * @param job2 the job2 to set
	 */
	public void setJob2(Job job2) {
		this.job2 = job2;
	}

	/**
	 * @return the anotherStringAttribute
	 */
	public String getAnotherStringAttribute() {
		return anotherStringAttribute;
	}

	/**
	 * @param anotherStringAttribute the anotherStringAttribute to set
	 */
	public void setAnotherStringAttribute(String anotherStringAttribute) {
		this.anotherStringAttribute = anotherStringAttribute;
	}
}
