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

import net.ceos.project.poi.annotated.annotation.XlsConfiguration;
import net.ceos.project.poi.annotated.annotation.XlsElement;
import net.ceos.project.poi.annotated.annotation.XlsFreeElement;
import net.ceos.project.poi.annotated.annotation.XlsSheet;
import net.ceos.project.poi.annotated.definition.PropagationType;

public class IlluminatiFactory {

	private static IlluminatiFactory factory = new IlluminatiFactory();

	/**
	 * More info at {@link IronMan}
	 */
	public static IronMan instanceIronMan() {
		return factory.new IronMan();
	}

	/**
	 * More info at {@link BlackBolt}
	 */
	public static BlackBolt instanceBlackBolt() {
		return factory.new BlackBolt();
	}

	/**
	 * More info at {@link Namor}
	 */
	public static Namor instanceNamor() {
		return factory.new Namor();
	}

	/**
	 * More info at {@link DrStrange}
	 */
	public static DrStrange instanceDrStrange() {
		return factory.new DrStrange();
	}

	/**
	 * More info at {@link MisterFantastic}
	 */
	public static MisterFantastic instanceMisterFantastic() {
		return factory.new MisterFantastic();
	}

	/**
	 * More info at {@link BlackPanther}
	 */
	public static BlackPanther instanceBlackPanther() {
		return factory.new BlackPanther();
	}

	/**
	 * More info at {@link ProfessorX}
	 */
	public static ProfessorX instanceProfessorX() {
		return factory.new ProfessorX();
	}

	/**
	 * More info at {@link Hood}
	 */
	public static Hood instanceHood() {
		return factory.new Hood();
	}

//	/**
//	 * More info at {@link CaptainAmerica}
//	 */
//	public static CaptainAmerica instanceCaptainAmerica() {
//		return factory.new CaptainAmerica();
//	}
//
//	/**
//	 * More info at {@link Beast}
//	 */
//	public static Beast instanceBeast() {
//		return factory.new Beast();
//	}
//
//	/**
//	 * More info at {@link Titania}
//	 */
//	public static Titania instanceTitania() {
//		return factory.new Titania();
//	}
//
//	/**
//	 * More info at {@link AmadeusCho}
//	 */
//	public static AmadeusCho instanceAmadeusCho() {
//		return factory.new AmadeusCho();
//	}
//
//	/**
//	 * More info at {@link ThunderBall}
//	 */
//	public static ThunderBall instanceThunderBall() {
//		return factory.new ThunderBall();
//	}
//
//	/**
//	 * More info at {@link EricOGrady}
//	 */
//	public static EricOGrady instanceEricOGrady() {
//		return factory.new EricOGrady();
//	}
//
//	/**
//	 * More info at {@link Medusa}
//	 */
//	public static Medusa instanceMedusa() {
//		return factory.new Medusa();
//	}

	/**
	 * Object to test a {@link XlsElement} with an invalid formula
	 * <p>
	 * Configuration :
	 * <ul>
	 * <li>{@link PropagationType} : HORIZONTAL
	 * <li>attribute freePrimitiveInt : invalid formula template $E
	 * </ul>
	 * 
	 * @version 1.0
	 * @author Carlos CRISTO ABREU
	 */
	@XlsSheet(title = "XlsElement invalid formula template")
	@XlsConfiguration(nameFile = "IlluminatiInvalidFormula")
	public class IronMan {

		@XlsElement(title = "Primitive int", isFormula = true, formula = "SUM(D$,$E)", position = 1)
		private int primitiveInt;

	}

	/**
	 * Object to test a {@link XlsElement} with an invalid formula
	 * <p>
	 * Configuration :
	 * <ul>
	 * <li>{@link PropagationType} : HORIZONTAL
	 * <li>attribute freePrimitiveLong : invalid formula template 1$
	 * </ul>
	 * 
	 * @version 1.0
	 * @author Carlos CRISTO ABREU
	 */
	@XlsSheet(title = "XlsElement invalid formula template")
	@XlsConfiguration(nameFile = "IlluminatiInvalidFormula")
	public class BlackBolt {

		@XlsElement(title = "Primitive long", isFormula = true, formula = "SUM(1$,2$)", position = 1)
		private long primitiveLong;

	}

	/**
	 * Object to test a {@link XlsElement} with an invalid formula
	 * <p>
	 * Configuration :
	 * <ul>
	 * <li>{@link PropagationType} : VERTICAL
	 * <li>attribute freePrimitiveInt : invalid formula template E$
	 * </ul>
	 * 
	 * @version 1.0
	 * @author Carlos CRISTO ABREU
	 */
	@XlsSheet(title = "XlsElement invalid formula template", propagation = PropagationType.PROPAGATION_VERTICAL)
	@XlsConfiguration(nameFile = "IlluminatiInvalidFormula")
	public class Namor {

		@XlsElement(title = "Primitive short", isFormula = true, formula = "SUM(D$,E$)", position = 1)
		private short primitiveShort;

	}

	/**
	 * Object to test a {@link XlsElement} with an invalid formula
	 * <p>
	 * Configuration :
	 * <ul>
	 * <li>{@link PropagationType} : VERTICAL
	 * <li>attribute freePrimitiveLong : invalid formula template $1
	 * </ul>
	 * 
	 * @version 1.0
	 * @author Carlos CRISTO ABREU
	 */
	@XlsSheet(title = "XlsElement invalid formula template", propagation = PropagationType.PROPAGATION_VERTICAL)
	@XlsConfiguration(nameFile = "IlluminatiInvalidFormula")
	public class DrStrange {

		@XlsElement(title = "Primitive double", isFormula = true, formula = "SUM($1,2$)", position = 1)
		private double primitiveDouble;

	}

	/**
	 * Object to test a {@link XlsFreeElement} with an invalid formula
	 * <p>
	 * Configuration :
	 * <ul>
	 * <li>{@link PropagationType} : HORIZONTAL
	 * <li>attribute freePrimitiveInt : invalid formula template $E
	 * </ul>
	 * 
	 * @version 1.0
	 * @author Carlos CRISTO ABREU
	 */
	@XlsSheet(title = "XlsFreeElement invalid formula template")
	@XlsConfiguration(nameFile = "IlluminatiInvalidFormula")
	public class MisterFantastic {

		@XlsFreeElement(title = "Free Integer", isFormula = true, formula = "SUM(D$,$E)", row = 3, cell = 1)
		private Integer freeInteger;

	}

	/**
	 * Object to test a {@link XlsFreeElement} with an invalid formula
	 * <p>
	 * Configuration :
	 * <ul>
	 * <li>{@link PropagationType} : HORIZONTAL
	 * <li>attribute freePrimitiveLong : invalid formula template 1$
	 * </ul>
	 * 
	 * @version 1.0
	 * @author Carlos CRISTO ABREU
	 */
	@XlsSheet(title = "XlsFreeElement invalid formula template")
	@XlsConfiguration(nameFile = "IlluminatiInvalidFormula")
	public class BlackPanther {

		@XlsFreeElement(title = "Free Double", isFormula = true, formula = "SUM(1$,2$)", row = 3, cell = 1)
		private Double freeDouble;

	}

	/**
	 * Object to test a {@link XlsFreeElement} with an invalid formula
	 * <p>
	 * Configuration :
	 * <ul>
	 * <li>{@link PropagationType} : VERTICAL
	 * <li>attribute freePrimitiveInt : invalid formula template E$
	 * </ul>
	 * 
	 * @version 1.0
	 * @author Carlos CRISTO ABREU
	 */
	@XlsSheet(title = "XlsFreeElement invalid formula template", propagation = PropagationType.PROPAGATION_VERTICAL)
	@XlsConfiguration(nameFile = "IlluminatiInvalidFormula")
	public class ProfessorX {

		@XlsFreeElement(title = "Free Long", isFormula = true, formula = "SUM(D$,E$)", row = 3, cell = 1)
		private Long freeLong;

	}

	/**
	 * Object to test a {@link XlsFreeElement} with an invalid formula
	 * <p>
	 * Configuration :
	 * <ul>
	 * <li>{@link PropagationType} : VERTICAL
	 * <li>attribute freePrimitiveLong : invalid formula template $1
	 * </ul>
	 * 
	 * @version 1.0
	 * @author Carlos CRISTO ABREU
	 */
	@XlsSheet(title = "XlsFreeElement invalid formula template", propagation = PropagationType.PROPAGATION_VERTICAL)
	@XlsConfiguration(nameFile = "IlluminatiInvalidFormula")
	public class Hood {

		@XlsFreeElement(title = "Free BigDecimal", isFormula = true, formula = "SUM($1,2$)", row = 3, cell = 1)
		private BigDecimal freePrimitiveBigDecimal;

	}

}
