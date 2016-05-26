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
import java.util.Date;

import net.ceos.project.poi.annotated.annotation.XlsConfiguration;
import net.ceos.project.poi.annotated.annotation.XlsElement;
import net.ceos.project.poi.annotated.annotation.XlsFreeElement;
import net.ceos.project.poi.annotated.annotation.XlsNestedHeader;
import net.ceos.project.poi.annotated.annotation.XlsSheet;
import net.ceos.project.poi.annotated.definition.ExtensionFileType;
import net.ceos.project.poi.annotated.definition.PropagationType;

public class AvengersFactory {

	private static AvengersFactory factory = new AvengersFactory();

	/**
	 * More info at {@link BlackPanther}
	 */
	public static BlackPanther instanceBlackPanther() {
		return factory.new BlackPanther();
	}

	/**
	 * More info at {@link BlackWidow}
	 */
	public static BlackWidow instanceBlackWidow() {
		return factory.new BlackWidow();
	}

	/**
	 * More info at {@link CaptainAmerica}
	 */
	public static CaptainAmerica instanceCaptainAmerica() {
		return factory.new CaptainAmerica();
	}

	/**
	 * More info at {@link CaptainMarvel}
	 */
	public static CaptainMarvel instanceCaptainMarvel() {
		return factory.new CaptainMarvel();
	}

	/**
	 * More info at {@link Falcon}
	 */
	public static Falcon instanceFalcon() {
		return factory.new Falcon();
	}

	/**
	 * More info at {@link HankPym}
	 */
	public static HankPym instanceHankPym() {
		return factory.new HankPym();
	}

	/**
	 * More info at {@link Hawkeye}
	 */
	public static Hawkeye instanceHawkeye() {
		return factory.new Hawkeye();
	}

	/**
	 * More info at {@link Hulk}
	 */
	public static Hulk instanceHulk() {
		return factory.new Hulk();
	}

	/**
	 * More info at {@link IronMan}
	 */
	public static IronMan instanceIronMan() {
		return factory.new IronMan();
	}

	/**
	 * More info at {@link LukeCage}
	 */
	public static LukeCage instanceLukeCage() {
		return factory.new LukeCage();
	}

	/**
	 * More info at {@link Quicksilver}
	 */
	public static Quicksilver instanceQuicksilver() {
		return factory.new Quicksilver();
	}

	/**
	 * More info at {@link ScarletWitch}
	 */
	public static ScarletWitch instanceScarletWitch() {
		return factory.new ScarletWitch();
	}

	/**
	 * More info at {@link SpiderWoman}
	 */
	public static SpiderWoman instanceSpiderWoman() {
		return factory.new SpiderWoman();
	}

	/**
	 * More info at {@link Thor}
	 */
	public static Thor instanceThor() {
		return factory.new Thor();
	}

	/**
	 * More info at {@link Vision}
	 */
	public static Vision instanceVision() {
		return factory.new Vision();
	}

	// public static Wasp instanceWasp() {
	// return factory.new Wasp();
	// }

	// public static WonderMan instanceWonderMan() {
	// return factory.new WonderMan();
	// }

	/**
	 * Object to test a conflict annotation type: impossible to have
	 * {@link XlsElement} & {@link XlsFreeElement} at the same attribute<br>
	 * <br>
	 * Configuration :
	 * <li>{@link PropagationType} : HORIZONTAL</li>
	 * <li>attribute dateAttribute : declared {@link XlsElement} &
	 * {@link XlsFreeElement}</li><br>
	 * 
	 * @version 1.0
	 * @author Carlos CRISTO ABREU
	 */
	@XlsSheet(title = "Conflict Annotation between XlsElement & XlsFreeElement :: Horizontal")
	@XlsConfiguration(nameFile = "ConflictAnnotationsHorizontal")
	public class BlackPanther {

		@XlsElement(title = "Date value", position = 1)
		@XlsFreeElement(title = "Date value", row = 1, cell = 1)
		private Date dateAttribute;

		@XlsElement(title = "String value", position = 2, comment = "This is an simple comment")
		private String stringAttribute;

		@XlsElement(title = "Integer value", position = 3)
		private Integer integerAttribute = 0;

		@XlsElement(title = "Average salary", position = 4, isFormula = true, formula = "AVERAGE(idy3,idy4)")
		private BigDecimal avgSalary;

	}

	/**
	 * Object to test a conflict annotation type: impossible to have
	 * {@link XlsElement} & {@link XlsFreeElement} at the same attribute<br>
	 * <br>
	 * Configuration :
	 * <li>{@link PropagationType} : VERTICAL</li>
	 * <li>attribute dateAttribute : declared {@link XlsElement} &
	 * {@link XlsFreeElement}</li><br>
	 * 
	 * @version 1.0
	 * @author Carlos CRISTO ABREU
	 */
	@XlsSheet(title = "Conflict Annotation between XlsElement & XlsFreeElement :: Vertical", propagation = PropagationType.PROPAGATION_VERTICAL)
	@XlsConfiguration(nameFile = "ConflictAnnotationsVertical")
	public class BlackWidow {

		@XlsElement(title = "Date value", position = 1)
		@XlsFreeElement(title = "Date value", row = 1, cell = 1)
		private Date dateAttribute;

		@XlsElement(title = "String value", position = 2, comment = "This is an simple comment")
		private String stringAttribute;

		@XlsElement(title = "Integer value", position = 3)
		private Integer integerAttribute = 0;

		@XlsElement(title = "Average salary", position = 4, isFormula = true, formula = "AVERAGE(Midx,Nidx)")
		private BigDecimal avgSalary;

	}

	/**
	 * Object to test a configuration conflict caused by the
	 * {@link PropagationType} and formula orientation<br>
	 * <br>
	 * Configuration :
	 * <li>{@link PropagationType} : HORIZONTAL</li>
	 * <li>attribute avgSalary : configured to VERTICAL</li><br>
	 * 
	 * @version 1.0
	 * @author Carlos CRISTO ABREU
	 */
	@XlsSheet(title = "Conflict PropagationType N Formula :: Horizontal")
	@XlsConfiguration(nameFile = "ConflictPropagationFormulaHorizontal")
	public class CaptainAmerica {

		@XlsElement(title = "Date value", position = 1)
		private Date dateAttribute;

		@XlsElement(title = "String value", position = 2, comment = "This is an simple comment")
		private String stringAttribute;

		@XlsElement(title = "Integer value", position = 3)
		private Integer integerAttribute = 0;

		@XlsElement(title = "Average salary", position = 4, isFormula = true, formula = "AVERAGE(idy3,idy4)")
		private BigDecimal avgSalary;

	}

	/**
	 * Object to test a configuration conflict caused by the
	 * {@link PropagationType} and formula orientation<br>
	 * <br>
	 * Configuration :
	 * <li>{@link PropagationType} : VERTICAL</li>
	 * <li>attribute avgSalary : configured to HORIZONTAL</li><br>
	 * 
	 * @version 1.0
	 * @author Carlos CRISTO ABREU
	 */
	@XlsSheet(title = "Conflict PropagationType N Formula :: Vertical", propagation = PropagationType.PROPAGATION_VERTICAL)
	@XlsConfiguration(nameFile = "ConflictPropagationFormulaVertical")
	public class CaptainMarvel {

		@XlsElement(title = "Date value", position = 1)
		private Date dateAttribute;

		@XlsElement(title = "String value", position = 2, comment = "This is an simple comment")
		private String stringAttribute;

		@XlsElement(title = "Integer value", position = 3)
		private Integer integerAttribute = 0;

		@XlsElement(title = "Average salary", position = 4, isFormula = true, formula = "AVERAGE(Midx,Nidx)")
		private BigDecimal avgSalary;

	}

	/**
	 * Object to test a {@link XlsElement} with an invalid position<br>
	 * <br>
	 * Configuration :
	 * <li>attribute dateAttribute : position = 0</li><br>
	 * 
	 * @version 1.0
	 * @author Carlos CRISTO ABREU
	 */
	@XlsSheet(title = "XlsElement invalid position", startRow = 2)
	@XlsConfiguration(nameFile = "ElementInvalidPosition")
	public class Falcon {

		@XlsNestedHeader(title = "Main", startX = 1, endX = 3)
		@XlsElement(title = "Date value", position = 0)
		private Date dateAttribute;

		@XlsElement(title = "String value", position = 2, comment = "This is an simple comment")
		private String stringAttribute;

		@XlsElement(title = "Integer value", position = 2)
		private Integer integerAttribute = 0;

	}

	/**
	 * Object to test multiple {@link XlsElement} at the same position<br>
	 * <br>
	 * Configuration :
	 * <li>attribute dateAttribute : position = 1</li>
	 * <li>attribute stringAttribute : position = 1</li><br>
	 * 
	 * @version 1.0
	 * @author Carlos CRISTO ABREU
	 */
	@XlsSheet(title = "XlsElement overwrite position", startRow = 2)
	@XlsConfiguration(nameFile = "ElementOverwrite", extensionFile = ExtensionFileType.XLSX)
	public class HankPym {

		@XlsNestedHeader(title = "Main", startX = 1, endX = 3)
		@XlsElement(title = "Date value", position = 1)
		private Date dateAttribute;

		@XlsElement(title = "String value", position = 1, comment = "This is an simple comment")
		private String stringAttribute;

		@XlsElement(title = "Integer value", position = 2)
		private Integer integerAttribute = 0;

	}

	/**
	 * Object to test a {@link XlsFreeElement} with a complex object<br>
	 * <br>
	 * Configuration :
	 * <li>attribute unit : {@link Unit}</li><br>
	 * 
	 * @version 1.0
	 * @author Carlos CRISTO ABREU
	 */
	@XlsSheet(title = "Xls Free Element invalid position cell")
	@XlsConfiguration(nameFile = "FreeElementInvalid")
	public class Hawkeye {
		@XlsFreeElement(title = "Free String", row = 1, cell = 1)
		private Unit unit;

		@XlsFreeElement(title = "Free Double", row = 2, cell = 1)
		private Double freeDouble;

		@XlsFreeElement(title = "Free Primitive int", row = 3, cell = 1)
		private int freePrimitiveInt;

		@XlsFreeElement(title = "Free Date", row = 4, cell = 1)
		private Date freeDate;

		@XlsFreeElement(title = "Free Long", row = 5, cell = 1)
		private Long freeLong;

		@XlsFreeElement(title = "Free Primitive Boolean", row = 6, cell = 1)
		private boolean freePrimitiveBoolean;

	}

	/**
	 * Object to test a {@link XlsFreeElement} at invalid position<br>
	 * <br>
	 * Configuration :
	 * <li>attribute freeString : cell = 0</li><br>
	 * 
	 * @version 1.0
	 * @author Carlos CRISTO ABREU
	 */
	@XlsSheet(title = "XlsFreeElement invalid position cell")
	@XlsConfiguration(nameFile = "FreeElementInvalid")
	public class Hulk {
		@XlsFreeElement(title = "Free String", row = 1, cell = 0)
		private String freeString;

		@XlsFreeElement(title = "Free Double", row = 2, cell = 1)
		private Double freeDouble;

		@XlsFreeElement(title = "Free Primitive int", row = 3, cell = 1)
		private int freePrimitiveInt;

		@XlsFreeElement(title = "Free Date", row = 4, cell = 1)
		private Date freeDate;

		@XlsFreeElement(title = "Free Long", row = 5, cell = 1)
		private Long freeLong;

		@XlsFreeElement(title = "Free Primitive Boolean", row = 6, cell = 1)
		private boolean freePrimitiveBoolean;

	}

	/**
	 * Object to test a {@link XlsFreeElement} at invalid position<br>
	 * <br>
	 * Configuration :
	 * <li>attribute freeDouble : row = 0</li><br>
	 * 
	 * @version 1.0
	 * @author Carlos CRISTO ABREU
	 */
	@XlsSheet(title = "XlsFreeElement invalid position cell")
	@XlsConfiguration(nameFile = "FreeElementInvalid")
	public class IronMan {
		@XlsFreeElement(title = "Free String", row = 1, cell = 1)
		private String freeString;

		@XlsFreeElement(title = "Free Double", row = 0, cell = 1)
		private Double freeDouble;

		@XlsFreeElement(title = "Free Primitive int", row = 3, cell = 1)
		private int freePrimitiveInt;

		@XlsFreeElement(title = "Free Date", row = 4, cell = 1)
		private Date freeDate;

		@XlsFreeElement(title = "Free Long", row = 5, cell = 1)
		private Long freeLong;

		@XlsFreeElement(title = "Free Primitive Boolean", row = 6, cell = 1)
		private boolean freePrimitiveBoolean;

	}

	/**
	 * Object to test multiple {@link XlsFreeElement} at the same position<br>
	 * <br>
	 * Configuration :
	 * <li>attribute freeString : row = 1, cell = 1</li>
	 * <li>attribute freeDouble : row = 1, cell = 1</li><br>
	 * 
	 * @version 1.0
	 * @author Carlos CRISTO ABREU
	 */
	@XlsSheet(title = "XlsFreeElement overwrite cell")
	@XlsConfiguration(nameFile = "FreeElementOverwrite")
	public class LukeCage {
		@XlsFreeElement(title = "Free String", row = 1, cell = 1)
		private String freeString;

		@XlsFreeElement(title = "Free Double", row = 1, cell = 1)
		private Double freeDouble;

		@XlsFreeElement(title = "Free Primitive int", row = 3, cell = 1)
		private int freePrimitiveInt;

		@XlsFreeElement(title = "Free Date", row = 4, cell = 1)
		private Date freeDate;

		@XlsFreeElement(title = "Free Long", row = 5, cell = 1)
		private Long freeLong;

		@XlsFreeElement(title = "Free Primitive Boolean", row = 6, cell = 1)
		private boolean freePrimitiveBoolean;

	}

	/**
	 * Object to test the conflict between the {@link PropagationType} and the
	 * configuration of the {@link XlsNestedHeader}<br>
	 * <br>
	 * Configuration :
	 * <li>{@link PropagationType} : HORIZONTAL</li>
	 * <li>{@link XlsNestedHeader} : VERTICAL</li><br>
	 * 
	 * @version 1.0
	 * @author Carlos CRISTO ABREU
	 */
	@XlsSheet(title = "NestedHeader incmpatible :: Horizontal", startRow = 2)
	@XlsConfiguration(nameFile = "NestedHeaderHorizontal")
	public class Quicksilver {

		@XlsNestedHeader(title = "Main info", startY = 1, endY = 3)
		@XlsElement(title = "Date value", position = 1)
		private Date dateAttribute;

		@XlsElement(title = "String value", position = 2, comment = "This is an simple comment")
		private String stringAttribute;

		@XlsElement(title = "Integer value", position = 3)
		private Integer integerAttribute = 0;

	}

	/**
	 * Object to test the conflict between the {@link PropagationType} and the
	 * configuration of the {@link XlsNestedHeader}<br>
	 * <br>
	 * Configuration :
	 * <li>{@link PropagationType} : VERTICAL</li>
	 * <li>{@link XlsNestedHeader} : HORIZONTAL</li><br>
	 * 
	 * @version 1.0
	 * @author Carlos CRISTO ABREU
	 */
	@XlsSheet(title = "NestedHeader incmpatible :: Vertical", startCell = 2, propagation = PropagationType.PROPAGATION_VERTICAL)
	@XlsConfiguration(nameFile = "NestedHeaderVertical")
	public class ScarletWitch {

		@XlsNestedHeader(title = "Main info", startX = 1, endX = 3)
		@XlsElement(title = "Date value", position = 1)
		private Date dateAttribute;

		@XlsElement(title = "String value", position = 2, comment = "This is an simple comment")
		private String stringAttribute;

		@XlsElement(title = "Integer value", position = 3)
		private Integer integerAttribute = 0;

	}

	/**
	 * Object to test the absent annotation {@link XlsSheet}<br>
	 * <br>
	 * Configuration :
	 * <li>{@link XlsSheet} : absent</li><br>
	 * 
	 * @version 1.0
	 * @author Carlos CRISTO ABREU
	 */
	@XlsConfiguration(nameFile = "XlsSheet absent")
	public class SpiderWoman {

		@XlsElement(title = "Date value", position = 1, formatMask = "yyyyMMdd")
		private Date dateAttribute;

		@XlsElement(title = "String value", position = 2)
		private String stringAttribute;

		@XlsElement(title = "Double value", position = 3, formatMask = "0.0")
		private Double doubleAttribute = 0.0;

		@XlsElement(title = "Long value", position = 4)
		private Long longAttribute = 0L;

		@XlsElement(title = "Primitive boolean value", position = 5)
		private boolean booleanPrimitiveAttribute = false;

		@XlsElement(title = "Primitive float value", position = 6)
		private float floatPrimitiveAttribute = 0f;

		@XlsElement(title = "Unit family", position = 7)
		private UnitFamily unitFamily;

		@XlsElement(title = "BigDecimal value", position = 8)
		private BigDecimal bigDecimalAttribute;

	}

	/**
	 * Object to test the absent annotation {@link XlsConfiguration}<br>
	 * <br>
	 * Configuration :
	 * <li>{@link XlsConfiguration} : absent</li><br>
	 * 
	 * @version 1.0
	 * @author Carlos CRISTO ABREU
	 */
	@XlsSheet(title = "XlsConfiguration absent")
	public class Thor {

		@XlsElement(title = "Date value", position = 1, formatMask = "yyyyMMdd")
		private Date dateAttribute;

		@XlsElement(title = "String value", position = 2)
		private String stringAttribute;

		@XlsElement(title = "Double value", position = 3, formatMask = "0.0")
		private Double doubleAttribute = 0.0;

		@XlsElement(title = "Long value", position = 4)
		private Long longAttribute = 0L;

		@XlsElement(title = "Primitive boolean value", position = 5)
		private boolean booleanPrimitiveAttribute = false;

		@XlsElement(title = "Primitive float value", position = 6)
		private float floatPrimitiveAttribute = 0f;

		@XlsElement(title = "Unit family", position = 7)
		private UnitFamily unitFamily;

		@XlsElement(title = "BigDecimal value", position = 8)
		private BigDecimal bigDecimalAttribute;

	}

	/**
	 * Object to test the empty title at {@link @XlsSheet}<br>
	 * <br>
	 * Configuration :
	 * <li>{@link @XlsSheet} : empty title</li><br>
	 * 
	 * @version 1.0
	 * @author Carlos CRISTO ABREU
	 */
	@XlsConfiguration(nameFile = "PropagationHorizontalObjects")
	@XlsSheet(title = "")
	public class Vision {

		@XlsElement(title = "Date value", position = 1, formatMask = "yyyyMMdd")
		private Date dateAttribute;

		@XlsElement(title = "String value", position = 2)
		private String stringAttribute;

		@XlsElement(title = "Double value", position = 3, formatMask = "0.0")
		private Double doubleAttribute = 0.0;

		@XlsElement(title = "Long value", position = 4)
		private Long longAttribute = 0L;

		@XlsElement(title = "Primitive boolean value", position = 5)
		private boolean booleanPrimitiveAttribute = false;

		@XlsElement(title = "Primitive float value", position = 6)
		private float floatPrimitiveAttribute = 0f;

		@XlsElement(title = "Unit family", position = 7)
		private UnitFamily unitFamily;

		@XlsElement(title = "BigDecimal value", position = 8)
		private BigDecimal bigDecimalAttribute;

	}

}
