package net.ceos.project.poi.annotated.bean;

import java.math.BigDecimal;
import java.util.Date;

import net.ceos.project.poi.annotated.annotation.XlsConfiguration;
import net.ceos.project.poi.annotated.annotation.XlsElement;
import net.ceos.project.poi.annotated.annotation.XlsSheet;
import net.ceos.project.poi.annotated.definition.CascadeType;
import net.ceos.project.poi.annotated.definition.ExtensionFileType;
import net.ceos.project.poi.annotated.definition.PropagationType;

public class ObjectTypeFactory {

	private static ObjectTypeFactory objectTypeBuilder = new ObjectTypeFactory();

	public static StringDeclaredAttribute instanceString() {
		return objectTypeBuilder.new StringDeclaredAttribute();
	}

	public static DateDeclaredAttribute instanceDate() {
		return objectTypeBuilder.new DateDeclaredAttribute();
	}

	public static IntegerDeclaredAttribute instanceInteger() {
		return objectTypeBuilder.new IntegerDeclaredAttribute();
	}

	public static LongDeclaredAttribute instanceLong() {
		return objectTypeBuilder.new LongDeclaredAttribute();
	}

	public static DoubleDeclaredAttribute instanceDouble() {
		return objectTypeBuilder.new DoubleDeclaredAttribute();
	}

	public static BigDecimalDeclaredAttribute instanceBigDecimal() {
		return objectTypeBuilder.new BigDecimalDeclaredAttribute();
	}

	public static BooleanDeclaredAttribute instanceBoolean() {
		return objectTypeBuilder.new BooleanDeclaredAttribute();
	}

	/* Inner Objects */

	/**
	 * Object with declared {@link String} not initialized.<br>
	 * <br>
	 * Configuration : <br>
	 * Sheet title : String not initialized <br>
	 * File name : ConverterExceptionTest <br>
	 * {@link ExtensionFileType} = ExtensionFileType.XLS <br>
	 * {@link PropagationType} = PropagationType.PROPAGATION_HORIZONTAL <br>
	 * {@link CascadeType} = CascadeType.CASCADE_BASE <br>
	 * 
	 * @author CristoAbreu
	 */
	@XlsSheet(title = "String not initialized")
	@XlsConfiguration(nameFile = "ConverterExceptionTest")
	public class StringDeclaredAttribute {

		@XlsElement(title = "String value", position = 2)
		private String stringAttribute;

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

	/**
	 * Object with declared {@link Date} not initialized.<br>
	 * <br>
	 * Configuration : <br>
	 * Sheet title : Date not initialized <br>
	 * File name : ConverterExceptionTest <br>
	 * {@link ExtensionFileType} = ExtensionFileType.XLS <br>
	 * {@link PropagationType} = PropagationType.PROPAGATION_HORIZONTAL <br>
	 * {@link CascadeType} = CascadeType.CASCADE_BASE <br>
	 * 
	 * @author CristoAbreu
	 */
	@XlsSheet(title = "Date not initialized")
	@XlsConfiguration(nameFile = "ConverterExceptionTest")
	public class DateDeclaredAttribute {

		@XlsElement(title = "Date value", position = 1, formatMask = "yyyy-MM-dd")
		private Date dateAttribute;

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
	}

	/**
	 * Object with declared {@link Integer} not initialized.<br>
	 * <br>
	 * Configuration : <br>
	 * Sheet title : Integer not initialized <br>
	 * File name : ConverterExceptionTest <br>
	 * {@link ExtensionFileType} = ExtensionFileType.XLS <br>
	 * {@link PropagationType} = PropagationType.PROPAGATION_HORIZONTAL <br>
	 * {@link CascadeType} = CascadeType.CASCADE_BASE <br>
	 * 
	 * @author CristoAbreu
	 */
	@XlsSheet(title = "Integer not initialized")
	@XlsConfiguration(nameFile = "ConverterExceptionTest")
	public class IntegerDeclaredAttribute {

		@XlsElement(title = "Integer value", position = 3)
		private Integer integerAttribute;

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
	}

	/**
	 * Object with declared {@link Long} not initialized.<br>
	 * <br>
	 * Configuration : <br>
	 * Sheet title : Long not initialized <br>
	 * File name : ConverterExceptionTest <br>
	 * {@link ExtensionFileType} = ExtensionFileType.XLS <br>
	 * {@link PropagationType} = PropagationType.PROPAGATION_HORIZONTAL <br>
	 * {@link CascadeType} = CascadeType.CASCADE_BASE <br>
	 * 
	 * @author CristoAbreu
	 */
	@XlsSheet(title = "Long not initialized")
	@XlsConfiguration(nameFile = "ConverterExceptionTest")
	public class LongDeclaredAttribute {

		@XlsElement(title = "Long value", position = 1)
		private Long longAttribute;

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
	}

	/**
	 * Object with declared {@link Double} not initialized.<br>
	 * <br>
	 * Configuration : <br>
	 * Sheet title : Double not initialized <br>
	 * File name : ConverterExceptionTest <br>
	 * {@link ExtensionFileType} = ExtensionFileType.XLS <br>
	 * {@link PropagationType} = PropagationType.PROPAGATION_HORIZONTAL <br>
	 * {@link CascadeType} = CascadeType.CASCADE_BASE <br>
	 * 
	 * @author CristoAbreu
	 */
	@XlsSheet(title = "Double not initialized")
	@XlsConfiguration(nameFile = "ConverterExceptionTest")
	public class DoubleDeclaredAttribute {

		@XlsElement(title = "Double value", position = 1, formatMask = "0.00000")
		private Double doubleAttribute;

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
	}

	/**
	 * Object with declared {@link BigDecimal} not initialized.<br>
	 * <br>
	 * Configuration : <br>
	 * Sheet title : BigDecimal not initialized <br>
	 * File name : ConverterExceptionTest <br>
	 * {@link ExtensionFileType} = ExtensionFileType.XLS <br>
	 * {@link PropagationType} = PropagationType.PROPAGATION_HORIZONTAL <br>
	 * {@link CascadeType} = CascadeType.CASCADE_BASE <br>
	 * 
	 * @author CristoAbreu
	 */
	@XlsSheet(title = "BigDecimal not initialized")
	@XlsConfiguration(nameFile = "ConverterExceptionTest")
	public class BigDecimalDeclaredAttribute {

		@XlsElement(title = "BigDecimal value", position = 1)
		private BigDecimal bigDecimalAttribute;

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

	/**
	 * Object with declared {@link Boolean} not initialized.<br>
	 * <br>
	 * Configuration : <br>
	 * Sheet title : Boolean not initialized <br>
	 * File name : ConverterExceptionTest <br>
	 * {@link ExtensionFileType} = ExtensionFileType.XLS <br>
	 * {@link PropagationType} = PropagationType.PROPAGATION_HORIZONTAL <br>
	 * {@link CascadeType} = CascadeType.CASCADE_BASE <br>
	 * 
	 * @author CristoAbreu
	 */
	@XlsSheet(title = "Boolean not initialized")
	@XlsConfiguration(nameFile = "ConverterExceptionTest")
	public class BooleanDeclaredAttribute {

		@XlsElement(title = "Boolean value", position = 1, comment = "boolean comment")
		private Boolean booleanAttribute = Boolean.TRUE;

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
	}

}
