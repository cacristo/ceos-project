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
package net.ceos.project.poi.annotated.bean.factory;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;

import net.ceos.project.poi.annotated.annotation.XlsConfiguration;
import net.ceos.project.poi.annotated.annotation.XlsDecorator;
import net.ceos.project.poi.annotated.annotation.XlsDecorators;
import net.ceos.project.poi.annotated.annotation.XlsElement;
import net.ceos.project.poi.annotated.annotation.XlsFreeElement;
import net.ceos.project.poi.annotated.annotation.XlsNestedHeader;
import net.ceos.project.poi.annotated.annotation.XlsSheet;
import net.ceos.project.poi.annotated.bean.UnitFamily;
import net.ceos.project.poi.annotated.definition.CascadeType;
import net.ceos.project.poi.annotated.definition.ExtensionFileType;
import net.ceos.project.poi.annotated.definition.PropagationType;

public class MarvelBadGuysFactory {

	private static MarvelBadGuysFactory factory = new MarvelBadGuysFactory();

	/**
	 * More info at {@link Ultron}
	 */
	public static Ultron instanceUltron() {
		return factory.new Ultron();
	}

	/**
	 * More info at {@link GreenGoblin}
	 */
	public static GreenGoblin instanceGreenGoblin() {
		return factory.new GreenGoblin();
	}

	/**
	 * More info at {@link Loki}
	 */
	public static Loki instanceLoki() {
		return factory.new Loki();
	}

	/**
	 * More info at {@link RedSkull}
	 */
	public static RedSkull instanceRedSkull() {
		return factory.new RedSkull();
	}

	/**
	 * More info at {@link Mystique}
	 */
	public static Mystique instanceMystique() {
		return factory.new Mystique();
	}

	/**
	 * More info at {@link Thanos}
	 */
	public static Thanos instanceThanos() {
		return factory.new Thanos();
	}

	/**
	 * More info at {@link Ronan}
	 */
	public static Ronan instanceRonan() {
		return factory.new Ronan();
	}

	/**
	 * More info at {@link Magneto}
	 */
	public static Magneto instanceMagneto() {
		return factory.new Magneto();
	}

	/**
	 * More info at {@link DrDoom}
	 */
	public static DrDoom instanceDrDoom() {
		return factory.new DrDoom();
	}

	/**
	 * More info at {@link BlackCat}
	 */
	public static BlackCat instanceBlackCat() {
		return factory.new BlackCat();
	}

	/**
	 * Object to test a conflict caused by the PropagationType and
	 * XlsNestedHeader orientation
	 * <p>
	 * Configuration :
	 * <ul>
	 * <li>Sheet title : Ultron
	 * <li>File name : MarvelBadGuys
	 * <li>{@link ExtensionFileType} = ExtensionFileType.XLSX
	 * <li>{@link PropagationType} = PropagationType.PROPAGATION_HORIZONTAL
	 * <li>{@link CascadeType} = CascadeType.CASCADE_BASE
	 * </ul>
	 * 
	 * @version 1.0
	 * @author Carlos CRISTO ABREU
	 */
	@XlsSheet(title = "Ultron")
	@XlsConfiguration(nameFile = "MarvelBadGuys", extensionFile = ExtensionFileType.XLSX)
	public class Ultron {

		@XlsNestedHeader(title = "Super Header", startY = 1, endY = 2)
		@XlsElement(title = "String value", position = 1)
		private String strAttribute = "something";

		public Ultron() {
		}

		/**
		 * @return the strAttribute
		 */
		public final String getStrAttribute() {
			return strAttribute;
		}

		/**
		 * @param strAttribute
		 *            the strAttribute to set
		 */
		public final void setStrAttribute(String strAttribute) {
			this.strAttribute = strAttribute;
		}
	}

	/**
	 * Object to test a conflict caused by the PropagationType and
	 * XlsNestedHeader orientation
	 * <p>
	 * Configuration :
	 * <ul>
	 * <li>Sheet title : Green Goblin
	 * <li>File name : MarvelBadGuys
	 * <li>{@link ExtensionFileType} = ExtensionFileType.XLSX
	 * <li>{@link PropagationType} = PropagationType.PROPAGATION_VERTICAL
	 * <li>{@link CascadeType} = CascadeType.CASCADE_BASE
	 * </ul>
	 * 
	 * @version 1.0
	 * @author Carlos CRISTO ABREU
	 */
	@XlsSheet(title = "Green Goblin", propagation = PropagationType.PROPAGATION_VERTICAL)
	@XlsConfiguration(nameFile = "MarvelBadGuys", extensionFile = ExtensionFileType.XLSX)
	public class GreenGoblin {

		@XlsNestedHeader(title = "Super Header", startX = 1, endX = 2)
		@XlsElement(title = "Date value", position = 1)
		private Date dateAttribute = new Date();

		public GreenGoblin() {
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
	}

	/**
	 * Object to test a element entry with a invalid position, make sure you are
	 * setting a positive value and start at least by 1.
	 * <p>
	 * Configuration :
	 * <ul>
	 * <li>Sheet title : Loki
	 * <li>File name : MarvelBadGuys
	 * <li>{@link ExtensionFileType} = ExtensionFileType.XLSX
	 * <li>{@link PropagationType} = PropagationType.PROPAGATION_HORIZONTAL
	 * <li>{@link CascadeType} = CascadeType.CASCADE_BASE
	 * </ul>
	 * 
	 * @version 1.0
	 * @author Carlos CRISTO ABREU
	 */
	@XlsSheet(title = "Loki")
	@XlsConfiguration(nameFile = "MarvelBadGuys", extensionFile = ExtensionFileType.XLSX)
	public class Loki {

		@XlsElement(title = "Header value", position = 0)
		private Integer numericAttribute = 5;

		public Loki() {
		}

		/**
		 * @return the numericAttribute
		 */
		public final Integer getNumericAttribute() {
			return numericAttribute;
		}

		/**
		 * @param numericAttribute
		 *            the numericAttribute to set
		 */
		public final void setNumericAttribute(Integer numericAttribute) {
			this.numericAttribute = numericAttribute;
		}

	}

	/**
	 * Object to test a element entry with a invalid position, make sure you are
	 * setting a positive value and start at least by 1.
	 * <p>
	 * Configuration :
	 * <ul>
	 * <li>Sheet title : RedSkull
	 * <li>File name : MarvelBadGuys
	 * <li>{@link ExtensionFileType} = ExtensionFileType.XLSX
	 * <li>{@link PropagationType} = PropagationType.PROPAGATION_VERTICAL
	 * <li>{@link CascadeType} = CascadeType.CASCADE_BASE
	 * </ul>
	 * 
	 * @version 1.0
	 * @author Carlos CRISTO ABREU
	 */
	@XlsSheet(title = "Red Skull", propagation = PropagationType.PROPAGATION_VERTICAL)
	@XlsConfiguration(nameFile = "MarvelBadGuys", extensionFile = ExtensionFileType.XLSX)
	public class RedSkull {

		@XlsElement(title = "Header value", position = 0)
		private Double numericAttribute = 5.0;

		public RedSkull() {
		}

		/**
		 * @return the numericAttribute
		 */
		public final Double getNumericAttribute() {
			return numericAttribute;
		}

		/**
		 * @param numericAttribute
		 *            the numericAttribute to set
		 */
		public final void setNumericAttribute(Double numericAttribute) {
			this.numericAttribute = numericAttribute;
		}

	}

	/**
	 * Object to test a element entry with a invalid cell position, make sure
	 * you are setting a positive value and start at least by 1.
	 * <p>
	 * Configuration :
	 * <ul>
	 * <li>Sheet title : Mystique
	 * <li>File name : MarvelBadGuys
	 * <li>{@link ExtensionFileType} = ExtensionFileType.XLSX
	 * <li>{@link PropagationType} = PropagationType.PROPAGATION_HORIZONTAL
	 * <li>{@link CascadeType} = CascadeType.CASCADE_BASE
	 * </ul>
	 * 
	 * @version 1.0
	 * @author Carlos CRISTO ABREU
	 */
	@XlsSheet(title = "Mystique")
	@XlsConfiguration(nameFile = "MarvelBadGuys", extensionFile = ExtensionFileType.XLSX)
	public class Mystique {

		@XlsFreeElement(title = "Header value", cell = 0)
		private boolean boolAttribute = true;

		public Mystique() {
		}

		/**
		 * @return the boolAttribute
		 */
		public final boolean isBoolAttribute() {
			return boolAttribute;
		}

		/**
		 * @param boolAttribute
		 *            the boolAttribute to set
		 */
		public final void setBoolAttribute(boolean boolAttribute) {
			this.boolAttribute = boolAttribute;
		}
	}

	/**
	 * Object to test a element entry with a invalid row position, make sure you
	 * are setting a positive value and start at least by 1.
	 * <p>
	 * Configuration :
	 * <ul>
	 * <li>Sheet title : Thanos
	 * <li>File name : MarvelBadGuys
	 * <li>{@link ExtensionFileType} = ExtensionFileType.XLSX
	 * <li>{@link PropagationType} = PropagationType.PROPAGATION_HORIZONTAL
	 * <li>{@link CascadeType} = CascadeType.CASCADE_BASE
	 * </ul>
	 * 
	 * @version 1.0
	 * @author Carlos CRISTO ABREU
	 */
	@XlsSheet(title = "Thanos")
	@XlsConfiguration(nameFile = "MarvelBadGuys", extensionFile = ExtensionFileType.XLSX)
	public class Thanos {

		@XlsFreeElement(title = "Header value", row = 0)
		private BigDecimal numericAttribute = BigDecimal.valueOf(5.0);

		public Thanos() {
		}

		/**
		 * @return the numericAttribute
		 */
		public final BigDecimal getNumericAttribute() {
			return numericAttribute;
		}

		/**
		 * @param numericAttribute
		 *            the numericAttribute to set
		 */
		public final void setNumericAttribute(BigDecimal numericAttribute) {
			this.numericAttribute = numericAttribute;
		}
	}

	/**
	 * Object to test a comment rules which does not exist a method
	 * <p>
	 * Configuration :
	 * <ul>
	 * <li>Sheet title : Ronan
	 * <li>File name : MarvelBadGuys
	 * <li>{@link ExtensionFileType} = ExtensionFileType.XLS
	 * <li>{@link PropagationType} = PropagationType.PROPAGATION_HORIZONTAL
	 * <li>{@link CascadeType} = CascadeType.CASCADE_BASE
	 * </ul>
	 * 
	 * @version 1.0
	 * @author Carlos CRISTO ABREU
	 */
	@XlsSheet(title = "Ronan")
	@XlsConfiguration(nameFile = "MarvelBadGuys")
	public class Ronan {

		@XlsElement(title = "Header value", position = 1)
		private float numericAttribute = 5f;

		public Ronan() {
		}

		/**
		 * @return the numericAttribute
		 */
		public final float getNumericAttribute() {
			return numericAttribute;
		}

		/**
		 * @param numericAttribute
		 *            the numericAttribute to set
		 */
		public final void setNumericAttribute(float numericAttribute) {
			this.numericAttribute = numericAttribute;
		}
	}

	/**
	 * Object to test a comment rules which does not exist a method
	 * <p>
	 * Configuration :
	 * <ul>
	 * <li>Sheet title : Magneto
	 * <li>File name : MarvelBadGuys
	 * <li>{@link ExtensionFileType} = ExtensionFileType.XLS
	 * <li>{@link PropagationType} = PropagationType.PROPAGATION_HORIZONTAL
	 * <li>{@link CascadeType} = CascadeType.CASCADE_BASE
	 * </ul>
	 * 
	 * @version 1.0
	 * @author Carlos CRISTO ABREU
	 */
	@XlsSheet(title = "Magneto", propagation = PropagationType.PROPAGATION_VERTICAL)
	@XlsConfiguration(nameFile = "MarvelBadGuys")
	public class Magneto {

		@XlsElement(title = "Header value", position = 1)
		private UnitFamily attribute = UnitFamily.COMPONENTS;

		public Magneto() {

		}

		/**
		 * @return the attribute
		 */
		public final UnitFamily getAttribute() {
			return attribute;
		}

		/**
		 * @param attribute
		 *            the attribute to set
		 */
		public final void setAttribute(UnitFamily attribute) {
			this.attribute = attribute;
		}
	}

	/**
	 * Object to test a comment rules which does not exist a method
	 * <p>
	 * Configuration :
	 * <ul>
	 * <li>Sheet title : Dr. Doom
	 * <li>File name : MarvelBadGuys
	 * <li>{@link ExtensionFileType} = ExtensionFileType.XLS
	 * <li>{@link PropagationType} = PropagationType.PROPAGATION_HORIZONTAL
	 * <li>{@link CascadeType} = CascadeType.CASCADE_BASE
	 * </ul>
	 * 
	 * @version 1.0
	 * @author Carlos CRISTO ABREU
	 */
	@XlsSheet(title = "Dr. Doom")
	@XlsConfiguration(nameFile = "MarvelBadGuys")
	public class DrDoom {

		@XlsElement(title = "Header value", position = 1)
		private long numericAttribute = 5;

		public DrDoom() {
		}

		/**
		 * @return the numericAttribute
		 */
		public final long getNumericAttribute() {
			return numericAttribute;
		}

		/**
		 * @param numericAttribute
		 *            the numericAttribute to set
		 */
		public final void setNumericAttribute(long numericAttribute) {
			this.numericAttribute = numericAttribute;
		}
	}

	/**
	 * Object to test a comment rules which does not exist a method
	 * <p>
	 * Configuration :
	 * <ul>
	 * <li>Sheet title : BlackCat
	 * <li>File name : MarvelBadGuys
	 * <li>{@link ExtensionFileType} = ExtensionFileType.XLS
	 * <li>{@link PropagationType} = PropagationType.PROPAGATION_HORIZONTAL
	 * <li>{@link CascadeType} = CascadeType.CASCADE_BASE
	 * </ul>
	 * 
	 * @version 1.0
	 * @author Carlos CRISTO ABREU
	 */
	@XlsDecorators(values = {
			@XlsDecorator(decoratorName = "myDecorator", fontItalic = true, fontBold = true, border = CellStyle.BORDER_DOTTED, foregroundColor = HSSFColor.ORANGE.index),
			@XlsDecorator(decoratorName = "myDecorator", fontItalic = true, fontBold = true, border = CellStyle.BORDER_MEDIUM) })
	@XlsSheet(title = "Duplicate")
	@XlsConfiguration(nameFile = "MarvelBadGuys", extensionFile = ExtensionFileType.XLS)
	public class BlackCat {

		@XlsElement(title = "Header value", position = 1)
		private long numericAttribute = 5;

		public BlackCat() {
		}

		/**
		 * @return the numericAttribute
		 */
		public final long getNumericAttribute() {
			return numericAttribute;
		}

		/**
		 * @param numericAttribute
		 *            the numericAttribute to set
		 */
		public final void setNumericAttribute(long numericAttribute) {
			this.numericAttribute = numericAttribute;
		}
	}

}