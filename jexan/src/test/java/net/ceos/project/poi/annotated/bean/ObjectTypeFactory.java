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
import net.ceos.project.poi.annotated.definition.CascadeType;
import net.ceos.project.poi.annotated.definition.ExtensionFileType;
import net.ceos.project.poi.annotated.definition.PropagationType;

public class ObjectTypeFactory {

	private static ObjectTypeFactory objectTypeBuilder = new ObjectTypeFactory();

	/**
	 * More info at {@link StringDeclaredAttribute}
	 */
	public static StringDeclaredAttribute instanceString() {
		return objectTypeBuilder.new StringDeclaredAttribute();
	}

	/**
	 * More info at {@link DateDeclaredAttribute}
	 */
	public static DateDeclaredAttribute instanceDate() {
		return objectTypeBuilder.new DateDeclaredAttribute();
	}

	/**
	 * More info at {@link LocalDateDeclaredAttribute}
	 */
	public static LocalDateDeclaredAttribute instanceLocalDate() {
		return objectTypeBuilder.new LocalDateDeclaredAttribute();
	}

	/**
	 * More info at {@link LocalDateTimeDeclaredAttribute}
	 */
	public static LocalDateTimeDeclaredAttribute instanceLocalDateTime() {
		return objectTypeBuilder.new LocalDateTimeDeclaredAttribute();
	}

	/**
	 * More info at {@link ShortDeclaredAttribute}
	 */
	public static ShortDeclaredAttribute instanceShort() {
		return objectTypeBuilder.new ShortDeclaredAttribute();
	}

	/**
	 * More info at {@link IntegerDeclaredAttribute}
	 */
	public static IntegerDeclaredAttribute instanceInteger() {
		return objectTypeBuilder.new IntegerDeclaredAttribute();
	}

	/**
	 * More info at {@link LongDeclaredAttribute}
	 */
	public static LongDeclaredAttribute instanceLong() {
		return objectTypeBuilder.new LongDeclaredAttribute();
	}

	/**
	 * More info at {@link DoubleDeclaredAttribute}
	 */
	public static DoubleDeclaredAttribute instanceDouble() {
		return objectTypeBuilder.new DoubleDeclaredAttribute();
	}

	/**
	 * More info at {@link BigDecimalDeclaredAttribute}
	 */
	public static BigDecimalDeclaredAttribute instanceBigDecimal() {
		return objectTypeBuilder.new BigDecimalDeclaredAttribute();
	}

	/**
	 * More info at {@link BooleanDeclaredAttribute}
	 */
	public static BooleanDeclaredAttribute instanceBoolean() {
		return objectTypeBuilder.new BooleanDeclaredAttribute();
	}

	/* Inner Objects */

	/**
	 * Object with declared {@link String} not initialized.
	 * <p>
	 * Configuration :
	 * <ul>
	 * <li>Sheet title : String not initialized
	 * <li>File name : ConverterExceptionTest
	 * <li>{@link ExtensionFileType} = ExtensionFileType.XLS
	 * <li>{@link PropagationType} = PropagationType.PROPAGATION_HORIZONTAL
	 * <li>{@link CascadeType} = CascadeType.CASCADE_BASE
	 * </ul>
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
	 * Object with declared {@link Date} not initialized.
	 * <p>
	 * Configuration :
	 * <ul>
	 * <li>Sheet title : Date not initialized
	 * <li>File name : ConverterExceptionTest
	 * <li>{@link ExtensionFileType} = ExtensionFileType.XLS
	 * <li>{@link PropagationType} = PropagationType.PROPAGATION_HORIZONTAL
	 * <li>{@link CascadeType} = CascadeType.CASCADE_BASE
	 * </ul>
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
	 * Object with declared {@link Date} not initialized.
	 * <p>
	 * Configuration :
	 * <ul>
	 * <li>Sheet title : Date not initialized
	 * <li>File name : ConverterExceptionTest
	 * <li>{@link ExtensionFileType} = ExtensionFileType.XLS
	 * <li>{@link PropagationType} = PropagationType.PROPAGATION_HORIZONTAL
	 * <li>{@link CascadeType} = CascadeType.CASCADE_BASE
	 * </ul>
	 * 
	 * @author CristoAbreu
	 */
	@XlsSheet(title = "Date not initialized")
	@XlsConfiguration(nameFile = "ConverterExceptionTest")
	public class LocalDateDeclaredAttribute {

		@XlsElement(title = "Date value", position = 1, formatMask = "yyyy-MM-dd")
		private LocalDate dateAttribute;

		/**
		 * @return the dateAttribute
		 */
		public LocalDate getDateAttribute() {
			return dateAttribute;
		}

		/**
		 * @param dateAttribute
		 *            the dateAttribute to set
		 */
		public void setDateAttribute(LocalDate dateAttribute) {
			this.dateAttribute = dateAttribute;
		}
	}

	/**
	 * Object with declared {@link Date} not initialized.
	 * <p>
	 * Configuration :
	 * <ul>
	 * <li>Sheet title : LocalDateTime not initialized
	 * <li>File name : ConverterExceptionTest
	 * <li>{@link ExtensionFileType} = ExtensionFileType.XLS
	 * <li>{@link PropagationType} = PropagationType.PROPAGATION_HORIZONTAL
	 * <li>{@link CascadeType} = CascadeType.CASCADE_BASE
	 * </ul>
	 * 
	 * @author CristoAbreu
	 */
	@XlsSheet(title = "LocalDateTime not initialized")
	@XlsConfiguration(nameFile = "ConverterExceptionTest")
	public class LocalDateTimeDeclaredAttribute {

		@XlsElement(title = "Date value", position = 1, formatMask = "yyyy-MM-dd")
		private LocalDateTime dateAttribute;

		/**
		 * @return the dateAttribute
		 */
		public LocalDateTime getDateAttribute() {
			return dateAttribute;
		}

		/**
		 * @param dateAttribute
		 *            the dateAttribute to set
		 */
		public void setDateAttribute(LocalDateTime dateAttribute) {
			this.dateAttribute = dateAttribute;
		}
	}

	/**
	 * Object with declared {@link Integer} not initialized.
	 * <p>
	 * Configuration :
	 * <ul>
	 * <li>Sheet title : Integer not initialized
	 * <li>File name : ConverterExceptionTest
	 * <li>{@link ExtensionFileType} = ExtensionFileType.XLS
	 * <li>{@link PropagationType} = PropagationType.PROPAGATION_HORIZONTAL
	 * <li>{@link CascadeType} = CascadeType.CASCADE_BASE
	 * </ul>
	 * 
	 * @author CristoAbreu
	 */
	@XlsSheet(title = "Short not initialized")
	@XlsConfiguration(nameFile = "ConverterExceptionTest")
	public class ShortDeclaredAttribute {

		@XlsElement(title = "Short value", position = 3)
		private Short shortAttribute;

		/**
		 * @return the shortAttribute
		 */
		public final Short getShortAttribute() {
			return shortAttribute;
		}

		/**
		 * @param shortAttribute the shortAttribute to set
		 */
		public final void setShortAttribute(Short shortAttribute) {
			this.shortAttribute = shortAttribute;
		}
	}

	/**
	 * Object with declared {@link Integer} not initialized.
	 * <p>
	 * Configuration :
	 * <ul>
	 * <li>Sheet title : Integer not initialized
	 * <li>File name : ConverterExceptionTest
	 * <li>{@link ExtensionFileType} = ExtensionFileType.XLS
	 * <li>{@link PropagationType} = PropagationType.PROPAGATION_HORIZONTAL
	 * <li>{@link CascadeType} = CascadeType.CASCADE_BASE
	 * </ul>
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
	 * Object with declared {@link Long} not initialized.
	 * <p>
	 * Configuration :
	 * <ul>
	 * <li>Sheet title : Long not initialized
	 * <li>File name : ConverterExceptionTest
	 * <li>{@link ExtensionFileType} = ExtensionFileType.XLS
	 * <li>{@link PropagationType} = PropagationType.PROPAGATION_HORIZONTAL
	 * <li>{@link CascadeType} = CascadeType.CASCADE_BASE
	 * </ul>
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
	 * Object with declared {@link Double} not initialized.
	 * <p>
	 * Configuration :
	 * <ul>
	 * <li>Sheet title : Double not initialized
	 * <li>File name : ConverterExceptionTest
	 * <li>{@link ExtensionFileType} = ExtensionFileType.XLS
	 * <li>{@link PropagationType} = PropagationType.PROPAGATION_HORIZONTAL
	 * <li>{@link CascadeType} = CascadeType.CASCADE_BASE
	 * </ul>
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
	 * Object with declared {@link BigDecimal} not initialized.
	 * <p>
	 * Configuration :
	 * <ul>
	 * <li>Sheet title : BigDecimal not initialized
	 * <li>File name : ConverterExceptionTest
	 * <li>{@link ExtensionFileType} = ExtensionFileType.XLS
	 * <li>{@link PropagationType} = PropagationType.PROPAGATION_HORIZONTAL
	 * <li>{@link CascadeType} = CascadeType.CASCADE_BASE
	 * </ul>
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
	 * Object with declared {@link Boolean} not initialized.
	 * <p>
	 * Configuration :
	 * <ul>
	 * <li>Sheet title : Boolean not initialized
	 * <li>File name : ConverterExceptionTest
	 * <li>{@link ExtensionFileType} = ExtensionFileType.XLS
	 * <li>{@link PropagationType} = PropagationType.PROPAGATION_HORIZONTAL
	 * <li>{@link CascadeType} = CascadeType.CASCADE_BASE
	 * </ul>
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
