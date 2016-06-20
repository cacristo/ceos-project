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
import org.junit.Test;

import net.ceos.project.poi.annotated.bean.BasicObject;
import net.ceos.project.poi.annotated.bean.BasicObjectBuilder;
import net.ceos.project.poi.annotated.bean.PropagationHorizontalObject;
import net.ceos.project.poi.annotated.bean.PropagationHorizontalObjectBuilder;
import net.ceos.project.poi.annotated.bean.PropagationVerticalObject;
import net.ceos.project.poi.annotated.bean.PropagationVerticalObjectBuilder;

/**
 * Test the core of the library.
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public class EngineTest {

	/*
	 * TODO (1) see the behavior of using only one instance of the JAEX object
	 * inside one project
	 */
	/* (1.1) see the wb object */
	/* (1.2) see the configuration object */
	/* (1.3) see the stylesMap object */
	/* (1.4) see the cellDecoratorMap object */
	/* (1.4) see the headerDecorator object */

	/* TODO (2) manage the internal value of an Enum */

	/*
	 * TODO (3) fix numeric code like 00005 parsed to excel will maintain the
	 * same code to do it you just have to add '00005
	 */

	/**
	 * Test with default settings
	 */
	@Test
	public void testBasicConfiguration() throws Exception {
		BasicObject bO = BasicObjectBuilder.buildBasicObject();

		IEngine en = new Engine();
		en.marshalAndSave(bO, TestUtils.WORKING_DIR_GENERATED_I);

		BasicObject charger = new BasicObject();

		en.unmarshalFromPath(charger, TestUtils.WORKING_DIR_GENERATED_I);
		BasicObjectBuilder.validateBasicObject(charger);
	}

	/**
	 * Test with propagation type is HORIZONTAL
	 * 
	 * @throws Exception
	 */
	@Test
	public void testPropagationTypeHorizontal() throws Exception {
		PropagationHorizontalObject pHO = PropagationHorizontalObjectBuilder.buildPropagationHorizontalObject();

		CellDecorator myDecoratorCell = new CellDecorator();
		myDecoratorCell.setDecoratorName("myDecorator");
		myDecoratorCell.setAlignment(CellStyle.ALIGN_CENTER);
		myDecoratorCell.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		myDecoratorCell.setForegroundColor(HSSFColor.DARK_YELLOW.index);
		myDecoratorCell.setFontItalic(true);
		
		CellDecorator anotherDate = new CellDecorator();
		anotherDate.setDecoratorName("anotherDate");
		anotherDate.setAlignment(CellStyle.ALIGN_CENTER);
		anotherDate.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		anotherDate.setForegroundColor(HSSFColor.LIGHT_GREEN.index);
		anotherDate.setFontItalic(true);
		anotherDate.setWrapText(true);

		XConfigCriteria configCriteria = new XConfigCriteria();
		configCriteria.addSpecificCellDecorator("myDecorator", myDecoratorCell);
		configCriteria.addSpecificCellDecorator("anotherDate", anotherDate);

		IEngine en = new Engine();
		en.marshalAndSave(configCriteria, pHO, TestUtils.WORKING_DIR_GENERATED_I);

		PropagationHorizontalObject charger = new PropagationHorizontalObject();

		en.unmarshalFromPath(charger, TestUtils.WORKING_DIR_GENERATED_II);
		PropagationHorizontalObjectBuilder.validatePropagationHorizontalObject(charger);
	}

	/**
	 * Test with propagation type is VERTICAL
	 */
	@Test
	public void testPropagationTypeVertical() throws Exception {
		PropagationVerticalObject pVO = PropagationVerticalObjectBuilder.buildPropagationVerticalObject();

		IEngine en = new Engine();
		en.marshalAndSave(pVO, TestUtils.WORKING_DIR_GENERATED_II);

		PropagationVerticalObject charger = new PropagationVerticalObject();

		en.unmarshalFromPath(charger, TestUtils.WORKING_DIR_GENERATED_I);
		PropagationVerticalObjectBuilder.validatePropagationVerticalObject(charger);
	}
}
