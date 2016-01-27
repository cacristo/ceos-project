package org.jaexcel.framework.JAEX.bean;

import java.util.Date;

import org.jaexcel.framework.JAEX.annotation.XlsConfiguration;
import org.jaexcel.framework.JAEX.annotation.XlsElement;
import org.jaexcel.framework.JAEX.annotation.XlsNestedHeader;
import org.jaexcel.framework.JAEX.annotation.XlsSheet;
import org.jaexcel.framework.JAEX.definition.CascadeType;
import org.jaexcel.framework.JAEX.definition.ExtensionFileType;
import org.jaexcel.framework.JAEX.definition.PropagationType;

public class ObjectsBuilderTest {

	/**
	 * Object with the default configuration, all type of native objects /
	 * primitives attributes and non native objects. <br>
	 * <br>
	 * Configuration : <br>
	 * Sheet title : Default configuration sample <br>
	 * File name : DefaultConfigurationSample <br>
	 * {@link ExtensionFileType} = ExtensionFileType.XLS <br>
	 * {@link PropagationType} = PropagationType.PROPAGATION_HORIZONTAL <br>
	 * {@link CascadeType} = CascadeType.CASCADE_BASE <br>
	 * 
	 * @author CristoAbreu
	 */
	@XlsSheet(title = "Default configuration sample")
	@XlsConfiguration(nameFile = "DefaultConfigurationSample")
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
		 * @return the anotherAttribute
		 */
		public int getAnotherAttribute() {
			return anotherAttribute;
		}

		/**
		 * @param anotherAttribute
		 *            the anotherAttribute to set
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
		 * @param job2
		 *            the job2 to set
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
		 * @param anotherStringAttribute
		 *            the anotherStringAttribute to set
		 */
		public void setAnotherStringAttribute(String anotherStringAttribute) {
			this.anotherStringAttribute = anotherStringAttribute;
		}
	}

	/**
	 * Configuration : <br>
	 * Sheet title : Simple object sample <br>
	 * File name : SimpleSample <br>
	 * {@link ExtensionFileType} = ExtensionFileType.XLSX <br>
	 * {@link PropagationType} = PropagationType.PROPAGATION_HORIZONTAL <br>
	 * {@link CascadeType} = CascadeType.CASCADE_BASE <br>
	 * 
	 * @author CristoAbreu
	 */
	@XlsSheet(title = "Simple object sample")
	@XlsConfiguration(nameFile = "SimpleSample", extensionFile = ExtensionFileType.XLSX)
	public class Cyclops {

		@XlsNestedHeader(title = "Main info", startX = 1, endX = 3)
		@XlsElement(title = "Date value", position = 1, comment = "This is a comment")
		private Date dateAttribute;

		@XlsElement(title = "String value", position = 2)
		private String stringAttribute;

		@XlsElement(title = "Integer value", position = 3, decorator = "extendedIntDecorator")
		private Integer integerAttribute = 0;

		@XlsElement(title = "Double value 1", position = 4, formatMask = "0.000")
		private Double doubleAttribute1 = 111.17;

		@XlsElement(title = "Double value 2", position = 5, transformMask = "0.0")
		private Double doubleAttribute2 = 97.77;

		@XlsElement(title = "Sum double 1 & double 2", position = 6, isFormula = true, formula = "SUM(E3,F3)")
		private Double sum;

		public Cyclops() {
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
		 * @return the sum
		 */
		public Double getSum() {
			return sum;
		}

		/**
		 * @param sum
		 *            the sum to set
		 */
		public void setSum(Double sum) {
			this.sum = sum;
		}
	}

	@XlsSheet(title = "Cascade type base", cascadeLevel = CascadeType.CASCADE_BASE, propagation = PropagationType.PROPAGATION_VERTICAL)
	public class IronMan {
		@XlsNestedHeader(title = "Main info", startY = 1, endY = 2)
		@XlsElement(title = "Date value", position = 1, comment = "This is a comment")
		private Date dateAttribute;

		@XlsElement(title = "String value", position = 2)
		private String stringAttribute;

		public IronMan() {
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
	}

	@XlsSheet(title = "Cascade type level one", cascadeLevel = CascadeType.CASCADE_LEVEL_ONE, propagation = PropagationType.PROPAGATION_HORIZONTAL)
	public class Wolverine {

		@XlsNestedHeader(title = "Job details", startX = 1, endX = 3)
		@XlsElement(title = "job", position = 1)
		private Job job;

		Wolverine() {

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

	}

	@XlsSheet(title = "Cascade type level two", cascadeLevel = CascadeType.CASCADE_LEVEL_TWO)
	public class SpiderMan {

	}

	@XlsSheet(title = "Cascade type full", cascadeLevel = CascadeType.CASCADE_FULL, propagation = PropagationType.PROPAGATION_HORIZONTAL)
	public class DoctorX {

	}

	@XlsSheet(title = "Cascade type full", propagation = PropagationType.PROPAGATION_HORIZONTAL)
	public class Thor {

	}

}
