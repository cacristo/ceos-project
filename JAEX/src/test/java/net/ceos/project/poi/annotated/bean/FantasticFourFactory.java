package net.ceos.project.poi.annotated.bean;

import java.math.BigDecimal;
import java.util.Date;

import net.ceos.project.poi.annotated.annotation.XlsConfiguration;
import net.ceos.project.poi.annotated.annotation.XlsElement;
import net.ceos.project.poi.annotated.annotation.XlsSheet;
import net.ceos.project.poi.annotated.definition.CascadeType;
import net.ceos.project.poi.annotated.definition.ExtensionFileType;
import net.ceos.project.poi.annotated.definition.PropagationType;

public class FantasticFourFactory {

	private static FantasticFourFactory factory = new FantasticFourFactory();
	
	public static MrFantastic instanceMrFantastic(){
		return factory.new MrFantastic();
	}
	
	public static InvisibleWoman instanceInvisibleWoman(){
		return factory.new InvisibleWoman();
	}
	
	public static Thing instanceThing(){
		return factory.new Thing();
	}
	
	public static HumanTorch instanceHumanTorch(){
		return factory.new HumanTorch();
	}
	
	public static AntMan instanceAntMan(){
		return factory.new AntMan();
	}
	
	public static BlackPanther instanceBlackPanther(){
		return factory.new BlackPanther();
	}
	
	public static Crystal instanceCrystal(){
		return factory.new Crystal();
	}
	
	public static DrDoom instanceDrDoom(){
		return factory.new DrDoom();
	}
	
	public static Flux instanceFlux(){
		return factory.new Flux();
	}
	
	/**
	 * Configuration : <br>
	 * Sheet title : Mr. Fantastic <br>
	 * File name : FantasticFour <br>
	 * {@link ExtensionFileType} = ExtensionFileType.XLSX <br>
	 * {@link PropagationType} = PropagationType.PROPAGATION_HORIZONTAL <br>
	 * {@link CascadeType} = CascadeType.CASCADE_BASE <br>
	 * 
	 * @author CristoAbreu
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
	 * Configuration : <br>
	 * Sheet title : Human Torch <br>
	 * File name : FantasticFour <br>
	 * {@link ExtensionFileType} = ExtensionFileType.XLSX <br>
	 * {@link PropagationType} = PropagationType.PROPAGATION_HORIZONTAL <br>
	 * {@link CascadeType} = CascadeType.CASCADE_BASE <br>
	 * 
	 * @author CristoAbreu
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
	 * Configuration : <br>
	 * Sheet title : Invisible Woman <br>
	 * File name : FantasticFour <br>
	 * {@link ExtensionFileType} = ExtensionFileType.XLSX <br>
	 * {@link PropagationType} = PropagationType.PROPAGATION_HORIZONTAL <br>
	 * {@link CascadeType} = CascadeType.CASCADE_BASE <br>
	 * 
	 * @author CristoAbreu
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
	 * Configuration : <br>
	 * Sheet title : Thing <br>
	 * File name : FantasticFour <br>
	 * {@link ExtensionFileType} = ExtensionFileType.XLSX <br>
	 * {@link PropagationType} = PropagationType.PROPAGATION_HORIZONTAL <br>
	 * {@link CascadeType} = CascadeType.CASCADE_BASE <br>
	 * 
	 * @author CristoAbreu
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
	 * Configuration : <br>
	 * Sheet title : Crystal <br>
	 * File name : FantasticFour <br>
	 * {@link ExtensionFileType} = ExtensionFileType.XLSX <br>
	 * {@link PropagationType} = PropagationType.PROPAGATION_HORIZONTAL <br>
	 * {@link CascadeType} = CascadeType.CASCADE_BASE <br>
	 * 
	 * @author CristoAbreu
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
	 * Configuration : <br>
	 * Sheet title : Ant-Man <br>
	 * File name : FantasticFour <br>
	 * {@link ExtensionFileType} = ExtensionFileType.XLSX <br>
	 * {@link PropagationType} = PropagationType.PROPAGATION_HORIZONTAL <br>
	 * {@link CascadeType} = CascadeType.CASCADE_BASE <br>
	 * 
	 * @author CristoAbreu
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
	 * Configuration : <br>
	 * Sheet title : Black Panther <br>
	 * File name : FantasticFour <br>
	 * {@link ExtensionFileType} = ExtensionFileType.XLSX <br>
	 * {@link PropagationType} = PropagationType.PROPAGATION_HORIZONTAL <br>
	 * {@link CascadeType} = CascadeType.CASCADE_BASE <br>
	 * 
	 * @author CristoAbreu
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
	 * Configuration : <br>
	 * Sheet title : Flux <br>
	 * File name : FantasticFour <br>
	 * {@link ExtensionFileType} = ExtensionFileType.XLSX <br>
	 * {@link PropagationType} = PropagationType.PROPAGATION_HORIZONTAL <br>
	 * {@link CascadeType} = CascadeType.CASCADE_BASE <br>
	 * 
	 * @author CristoAbreu
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
	 * Configuration : <br>
	 * Sheet title : Dr. Doom <br>
	 * File name : FantasticFour <br>
	 * {@link ExtensionFileType} = ExtensionFileType.XLSX <br>
	 * {@link PropagationType} = PropagationType.PROPAGATION_HORIZONTAL <br>
	 * {@link CascadeType} = CascadeType.CASCADE_BASE <br>
	 * 
	 * @author CristoAbreu
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