package net.ceos.project.poi.annotated.bean;

import java.util.Date;

import net.ceos.project.poi.annotated.annotation.XlsConfiguration;
import net.ceos.project.poi.annotated.annotation.XlsElement;
import net.ceos.project.poi.annotated.annotation.XlsNestedHeader;
import net.ceos.project.poi.annotated.annotation.XlsSheet;

@XlsSheet(title = "Default object sample", startRow = 3)
@XlsConfiguration(nameFile = "DefaultSample")
public class ObjectWithDefaultConfigv2 {

	@XlsNestedHeader(title = "Main info", startX = 2, endX = 4)
	@XlsElement(title = "Date value", position = 1)
	private Date dateAttribute;

	@XlsElement(title = "String value", position = 2)
	private String stringAttribute;

	@XlsElement(title = "int value", position = 3)
	private int intAttribute = 0;

	@XlsElement(title = "job", position = 4)
	private Job job;

	@XlsElement(title = "another int value", position = 5)
	private int anotherAttribute = 0;

	@XlsElement(title = "job", position = 6)
	private Job job2;
	
	@XlsElement(title = "Another string value", position = 7)
	private String anotherStringAttribute;
	
	public ObjectWithDefaultConfigv2() {
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
