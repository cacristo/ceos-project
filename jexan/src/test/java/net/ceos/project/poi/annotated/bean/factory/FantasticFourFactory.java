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

import net.ceos.project.poi.annotated.annotation.XlsConfiguration;
import net.ceos.project.poi.annotated.annotation.XlsElement;
import net.ceos.project.poi.annotated.annotation.XlsSheet;
import net.ceos.project.poi.annotated.bean.UnitFamily;
import net.ceos.project.poi.annotated.definition.CascadeType;
import net.ceos.project.poi.annotated.definition.ExtensionFileType;
import net.ceos.project.poi.annotated.definition.PropagationType;

public class FantasticFourFactory {

	private static FantasticFourFactory factory = new FantasticFourFactory();

	/**
	 * More info at {@link MrFantastic}
	 */
	public static MrFantastic instanceMrFantastic() {
		return factory.new MrFantastic();
	}

	/**
	 * More info at {@link InvisibleWoman}
	 */
	public static InvisibleWoman instanceInvisibleWoman() {
		return factory.new InvisibleWoman();
	}

	/**
	 * More info at {@link Thing}
	 */
	public static Thing instanceThing() {
		return factory.new Thing();
	}

	/**
	 * More info at {@link HumanTorch}
	 */
	public static HumanTorch instanceHumanTorch() {
		return factory.new HumanTorch();
	}

	/**
	 * More info at {@link AntMan}
	 */
	public static AntMan instanceAntMan() {
		return factory.new AntMan();
	}

	/**
	 * More info at {@link BlackPanther}
	 */
	public static BlackPanther instanceBlackPanther() {
		return factory.new BlackPanther();
	}

	/**
	 * More info at {@link Crystal}
	 */
	public static Crystal instanceCrystal() {
		return factory.new Crystal();
	}

	/**
	 * More info at {@link DrDoom}
	 */
	public static DrDoom instanceDrDoom() {
		return factory.new DrDoom();
	}

	/**
	 * More info at {@link Flux}
	 */
	public static Flux instanceFlux() {
		return factory.new Flux();
	}

	/**
	 * Object to test a comment rules which does not exist a method
	 * <p>
	 * Configuration :
	 * <ul>
	 * <li>Sheet title : Mr. Fantastic
	 * <li>File name : FantasticFour
	 * <li>{@link ExtensionFileType} = ExtensionFileType.XLSX
	 * <li>{@link PropagationType} = PropagationType.PROPAGATION_HORIZONTAL
	 * <li>{@link CascadeType} = CascadeType.CASCADE_BASE
	 * </ul>
	 * 
	 * @version 1.0
	 * @author Carlos CRISTO ABREU
	 */
	@XlsSheet(title = "Mr. Fantastic")
	@XlsConfiguration(nameFile = "FantasticFour", extensionFile = ExtensionFileType.XLSX)
	public class MrFantastic {

		@XlsElement(title = "String value", position = 1, comment = "This is a comment", commentRules = "myRules")
		private String strAttribute = "something";

		public MrFantastic() {
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
	 * Object to test a comment rules which does not exist a method
	 * <p>
	 * Configuration :
	 * <ul>
	 * <li>Sheet title : Human Torch
	 * <li>File name : FantasticFour
	 * <li>{@link ExtensionFileType} = ExtensionFileType.XLSX
	 * <li>{@link PropagationType} = PropagationType.PROPAGATION_HORIZONTAL
	 * <li>{@link CascadeType} = CascadeType.CASCADE_BASE
	 * </ul>
	 * 
	 * @version 1.0
	 * @author Carlos CRISTO ABREU
	 */
	@XlsSheet(title = "Human Torch")
	@XlsConfiguration(nameFile = "FantasticFour", extensionFile = ExtensionFileType.XLSX)
	public class HumanTorch {

		@XlsElement(title = "Date value", position = 1, comment = "This is a comment", commentRules = "myRules")
		private Date dateAttribute = new Date();

		public HumanTorch() {
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
	 * Object to test a comment rules which does not exist a method
	 * <p>
	 * Configuration :
	 * <ul>
	 * <li>Sheet title : Invisible Woman
	 * <li>File name : FantasticFour
	 * <li>{@link ExtensionFileType} = ExtensionFileType.XLSX
	 * <li>{@link PropagationType} = PropagationType.PROPAGATION_HORIZONTAL
	 * <li>{@link CascadeType} = CascadeType.CASCADE_BASE
	 * </ul>
	 * 
	 * @version 1.0
	 * @author Carlos CRISTO ABREU
	 */
	@XlsSheet(title = "Invisible Woman")
	@XlsConfiguration(nameFile = "FantasticFour", extensionFile = ExtensionFileType.XLSX)
	public class InvisibleWoman {

		@XlsElement(title = "Header value", position = 1, comment = "This is a comment", commentRules = "myRules")
		private Integer numericAttribute = 5;

		public InvisibleWoman() {
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
	 * Object to test a comment rules which does not exist a method
	 * <p>
	 * Configuration :
	 * <ul>
	 * <li>Sheet title : Thing
	 * <li>File name : FantasticFour
	 * <li>{@link ExtensionFileType} = ExtensionFileType.XLSX
	 * <li>{@link PropagationType} = PropagationType.PROPAGATION_HORIZONTAL
	 * <li>{@link CascadeType} = CascadeType.CASCADE_BASE
	 * </ul>
	 * 
	 * @version 1.0
	 * @author Carlos CRISTO ABREU
	 */
	@XlsSheet(title = "Thing")
	@XlsConfiguration(nameFile = "FantasticFour", extensionFile = ExtensionFileType.XLSX)
	public class Thing {

		@XlsElement(title = "Header value", position = 1, comment = "This is a comment", commentRules = "myRules")
		private Double numericAttribute = 5.0;

		public Thing() {
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
	 * Object to test a comment rules which does not exist a method
	 * <p>
	 * Configuration :
	 * <ul>
	 * <li>Sheet title : Crystal
	 * <li>File name : FantasticFour
	 * <li>{@link ExtensionFileType} = ExtensionFileType.XLSX
	 * <li>{@link PropagationType} = PropagationType.PROPAGATION_HORIZONTAL
	 * <li>{@link CascadeType} = CascadeType.CASCADE_BASE
	 * </ul>
	 * 
	 * @version 1.0
	 * @author Carlos CRISTO ABREU
	 */
	@XlsSheet(title = "Crystal")
	@XlsConfiguration(nameFile = "FantasticFour", extensionFile = ExtensionFileType.XLSX)
	public class Crystal {

		@XlsElement(title = "Header value", position = 1, comment = "This is a comment", commentRules = "myRules")
		private boolean boolAttribute = true;

		public Crystal() {
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
	 * Object to test a comment rules which does not exist a method
	 * <p>
	 * Configuration :
	 * <ul>
	 * <li>Sheet title : Ant-Man
	 * <li>File name : FantasticFour
	 * <li>{@link ExtensionFileType} = ExtensionFileType.XLSX
	 * <li>{@link PropagationType} = PropagationType.PROPAGATION_HORIZONTAL
	 * <li>{@link CascadeType} = CascadeType.CASCADE_BASE
	 * </ul>
	 * 
	 * @version 1.0
	 * @author Carlos CRISTO ABREU
	 */
	@XlsSheet(title = "Ant-Man")
	@XlsConfiguration(nameFile = "FantasticFour", extensionFile = ExtensionFileType.XLSX)
	public class AntMan {

		@XlsElement(title = "Header value", position = 1, comment = "This is a comment", commentRules = "myRules")
		private BigDecimal numericAttribute = BigDecimal.valueOf(5.0);

		public AntMan() {
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
	 * <li>Sheet title : Black Panther
	 * <li>File name : FantasticFour
	 * <li>{@link ExtensionFileType} = ExtensionFileType.XLSX
	 * <li>{@link PropagationType} = PropagationType.PROPAGATION_HORIZONTAL
	 * <li>{@link CascadeType} = CascadeType.CASCADE_BASE
	 * </ul>
	 * 
	 * @version 1.0
	 * @author Carlos CRISTO ABREU
	 */
	@XlsSheet(title = "Black Panther")
	@XlsConfiguration(nameFile = "FantasticFour", extensionFile = ExtensionFileType.XLSX)
	public class BlackPanther {

		@XlsElement(title = "Header value", position = 1, comment = "This is a comment", commentRules = "myRules")
		private float numericAttribute = 5f;

		public BlackPanther() {
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
	 * <li>Sheet title : Flux
	 * <li>File name : FantasticFour
	 * <li>{@link ExtensionFileType} = ExtensionFileType.XLSX
	 * <li>{@link PropagationType} = PropagationType.PROPAGATION_HORIZONTAL
	 * <li>{@link CascadeType} = CascadeType.CASCADE_BASE
	 * </ul>
	 * 
	 * @version 1.0
	 * @author Carlos CRISTO ABREU
	 */
	@XlsSheet(title = "Flux")
	@XlsConfiguration(nameFile = "FantasticFour", extensionFile = ExtensionFileType.XLSX)
	public class Flux {

		@XlsElement(title = "Header value", position = 1, comment = "This is a comment", commentRules = "myRules")
		private UnitFamily attribute = UnitFamily.COMPONENTS;

		public Flux() {

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
	 * <li>File name : FantasticFour
	 * <li>{@link ExtensionFileType} = ExtensionFileType.XLSX
	 * <li>{@link PropagationType} = PropagationType.PROPAGATION_HORIZONTAL
	 * <li>{@link CascadeType} = CascadeType.CASCADE_BASE
	 * </ul>
	 * 
	 * @version 1.0
	 * @author Carlos CRISTO ABREU
	 */
	@XlsSheet(title = "Dr. Doom")
	@XlsConfiguration(nameFile = "FantasticFour", extensionFile = ExtensionFileType.XLSX)
	public class DrDoom {

		@XlsElement(title = "Header value", position = 1, comment = "This is a comment", commentRules = "myRules")
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

}