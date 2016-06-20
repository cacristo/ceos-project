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
package net.ceos.project.poi.annotated.core;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.testng.annotations.Test;

import net.ceos.project.poi.annotated.bean.PropagationHorizontalObject;
import net.ceos.project.poi.annotated.bean.PropagationHorizontalObjectBuilder;

/**
 * Test the cell decorator
 * <ul>
 * <li>by default
 * <li>by annotation
 * <li>by ConfigCriteria
 * <li>unique decorator (except header)
 * </ul>
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public class DecoratorTest {

	/**
	 * Test a declaration of one new decorator by ConfigCriteria
	 */
	@Test
	public void validateDeclarationViaConfigCriteria() throws Exception {
		PropagationHorizontalObject pHO = PropagationHorizontalObjectBuilder.buildPropagationHorizontalObject();

		CellDecorator myDecoratorCell = new CellDecorator();
		myDecoratorCell.setDecoratorName("myDecorator");
		myDecoratorCell.setAlignment(CellStyle.ALIGN_CENTER);
		myDecoratorCell.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		myDecoratorCell.setForegroundColor(HSSFColor.DARK_YELLOW.index);
		myDecoratorCell.setFontItalic(true);

		XConfigCriteria configCriteria = new XConfigCriteria();
		configCriteria.addSpecificCellDecorator("myDecorator", myDecoratorCell);
		configCriteria.setFileName("DeclarationViaConfigCriteria");

		IEngine en = new Engine();
		en.marshalAndSave(configCriteria, pHO, TestUtils.WORKING_DIR_GENERATED_I);

		PropagationHorizontalObject charger = new PropagationHorizontalObject();

		en.unmarshalFromPath(charger, TestUtils.WORKING_DIR_GENERATED_II);
		PropagationHorizontalObjectBuilder.validatePropagationHorizontalObject(charger);
	}

	/**
	 * Test the override, by annotation, of one decorator (default)
	 */
	@Test
	public void validateOverrideNumericDecoratorViaAnnotation() throws Exception {
		PropagationHorizontalObject pHO = PropagationHorizontalObjectBuilder.buildPropagationHorizontalObject();

		CellDecorator myDecoratorCell = new CellDecorator();
		myDecoratorCell.setDecoratorName("myDecorator");
		myDecoratorCell.setAlignment(CellStyle.ALIGN_CENTER);
		myDecoratorCell.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		myDecoratorCell.setForegroundColor(HSSFColor.DARK_YELLOW.index);
		myDecoratorCell.setFontItalic(true);

		XConfigCriteria configCriteria = new XConfigCriteria();
		configCriteria.addSpecificCellDecorator("myDecorator", myDecoratorCell);
		configCriteria.setFileName("OverrideNumericDecoratorViaAnnotation");

		IEngine en = new Engine();
		en.marshalAndSave(configCriteria, pHO, TestUtils.WORKING_DIR_GENERATED_I);

		PropagationHorizontalObject charger = new PropagationHorizontalObject();

		en.unmarshalFromPath(charger, TestUtils.WORKING_DIR_GENERATED_II);
		PropagationHorizontalObjectBuilder.validatePropagationHorizontalObject(charger);
	}

	/**
	 * Test the override, by ConfigCriteria, of one decorator (default)
	 */
	@Test
	public void validateOverrideEnumDecoratorViaConfigCriteria() throws Exception {
		PropagationHorizontalObject pHO = PropagationHorizontalObjectBuilder.buildPropagationHorizontalObject();

		CellDecorator myDecoratorCell = new CellDecorator();
		myDecoratorCell.setDecoratorName("myDecorator");
		myDecoratorCell.setAlignment(CellStyle.ALIGN_CENTER);
		myDecoratorCell.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		myDecoratorCell.setForegroundColor(HSSFColor.DARK_YELLOW.index);
		myDecoratorCell.setFontItalic(true);

		CellDecorator enumDecoratorDefault = new CellDecorator();
		enumDecoratorDefault.setDecoratorName(CellStyleHandler.CELL_DECORATOR_ENUM);
		enumDecoratorDefault.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		enumDecoratorDefault.setForegroundColor(HSSFColor.RED.index);
		enumDecoratorDefault.setFontSize((short) 6);

		XConfigCriteria configCriteria = new XConfigCriteria();
		configCriteria.addSpecificCellDecorator("myDecorator", myDecoratorCell);
		configCriteria.overrideEnumCellDecorator(enumDecoratorDefault);
		configCriteria.setFileName("OverrideEnumDecoratorConfigCriteria");

		IEngine en = new Engine();
		en.marshalAndSave(configCriteria, pHO, TestUtils.WORKING_DIR_GENERATED_I);

		PropagationHorizontalObject charger = new PropagationHorizontalObject();

		en.unmarshalFromPath(charger, TestUtils.WORKING_DIR_GENERATED_II);
		PropagationHorizontalObjectBuilder.validatePropagationHorizontalObject(charger);
	}

	/**
	 * Test the override of one decorator (non-default) by ConfigCriteria
	 */
	@Test
	public void validateOverrideSpecificDecoratorViaConfigCriteria() throws Exception {
		PropagationHorizontalObject pHO = PropagationHorizontalObjectBuilder.buildPropagationHorizontalObject();

		CellDecorator myDecoratorCell = new CellDecorator();
		myDecoratorCell.setDecoratorName("myDecorator");
		myDecoratorCell.setAlignment(CellStyle.ALIGN_CENTER);
		myDecoratorCell.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		myDecoratorCell.setForegroundColor(HSSFColor.DARK_YELLOW.index);
		myDecoratorCell.setFontItalic(true);

		CellDecorator anotherDate = new CellDecorator();
		anotherDate.setDecoratorName("anotherDate");
		anotherDate.setAlignment(CellStyle.ALIGN_LEFT);
		anotherDate.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		anotherDate.setForegroundColor(HSSFColor.LIGHT_BLUE.index);
		anotherDate.setFontColor((short) 8);
		anotherDate.setFontBold(true);

		XConfigCriteria configCriteria = new XConfigCriteria();
		configCriteria.addSpecificCellDecorator("myDecorator", myDecoratorCell);
		configCriteria.addSpecificCellDecorator("anotherDate", anotherDate);
		configCriteria.setFileName("OverrideSpecificDecoratorViaConfigCriteria");

		IEngine en = new Engine();
		en.marshalAndSave(configCriteria, pHO, TestUtils.WORKING_DIR_GENERATED_I);

		PropagationHorizontalObject charger = new PropagationHorizontalObject();

		en.unmarshalFromPath(charger, TestUtils.WORKING_DIR_GENERATED_II);
		PropagationHorizontalObjectBuilder.validatePropagationHorizontalObject(charger);
	}

	/**
	 * TODO Test the declaration, by annotation, of unique decorator (override
	 * all defaults except the header)
	 */

	/**
	 * Test the declaration, by ConfigCriteria, of unique decorator (override
	 * all defaults except the header)
	 */
	@Test
	public void validateOverrideUniqueDecoratorViaConfigCriteria() throws Exception {
		PropagationHorizontalObject pHO = PropagationHorizontalObjectBuilder.buildPropagationHorizontalObject();

		CellDecorator myDecoratorCell = new CellDecorator();
		myDecoratorCell.setDecoratorName("myDecorator");
		myDecoratorCell.setAlignment(CellStyle.ALIGN_CENTER);
		myDecoratorCell.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		myDecoratorCell.setForegroundColor(HSSFColor.DARK_YELLOW.index);
		myDecoratorCell.setFontItalic(true);

		CellDecorator unique = new CellDecorator();
		unique.setAlignment(CellStyle.ALIGN_CENTER);
		unique.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		unique.setForegroundColor(HSSFColor.LIGHT_ORANGE.index);
		unique.setFontBold(true);

		XConfigCriteria configCriteria = new XConfigCriteria();
		configCriteria.addSpecificCellDecorator("myDecorator", myDecoratorCell);
		configCriteria.overrideAllCellDecorators(unique);
		configCriteria.setFileName("OverrideUniqueDecoratoViaConfigCriteria");

		IEngine en = new Engine();
		en.marshalAndSave(configCriteria, pHO, TestUtils.WORKING_DIR_GENERATED_I);

		PropagationHorizontalObject charger = new PropagationHorizontalObject();

		en.unmarshalFromPath(charger, TestUtils.WORKING_DIR_GENERATED_II);
		PropagationHorizontalObjectBuilder.validatePropagationHorizontalObject(charger);
	}
}
