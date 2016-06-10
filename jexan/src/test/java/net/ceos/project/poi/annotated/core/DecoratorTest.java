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
	 * 
	 * @throws Exception
	 */
	@Test
	public void validatePropagationTypeHorizontal() throws Exception {
		PropagationHorizontalObject pHO = PropagationHorizontalObjectBuilder.buildPropagationHorizontalObject();

		IEngine en = new Engine();
		CellDecorator anotherDate = new CellDecorator();
		anotherDate.setDecoratorName("anotherDate");
		anotherDate.setAlignment(CellStyle.ALIGN_CENTER);
		anotherDate.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		anotherDate.setForegroundColor(HSSFColor.LIGHT_GREEN.index);
		anotherDate.setFontItalic(true);
		anotherDate.setWrapText(true);

		XConfigCriteria configCriteria = new XConfigCriteria();
		configCriteria.addSpecificCellDecorator("anotherDate", anotherDate);

		en.marshalAndSave(configCriteria, pHO, TestUtils.WORKING_DIR_GENERATED_I);

		PropagationHorizontalObject charger = new PropagationHorizontalObject();

		en.unmarshalFromPath(charger, TestUtils.WORKING_DIR_GENERATED_II);
		PropagationHorizontalObjectBuilder.validatePropagationHorizontalObject(charger);
	}

	/**
	 * TODO Test the override of one decorator (non-default) by ConfigCriteria
	 */

	/**
	 * TODO Test the override, by annotation, of one decorator (default)
	 */

	/**
	 * TODO Test the override, by ConfigCriteria, of one decorator (default)
	 */

	/**
	 * TODO Test the declaration, by annotation, of unique decorator (override all defaults except the header)
	 */

	/**
	 * TODO Test the declaration, by ConfigCriteria, of unique decorator (override all defaults except the header)
	 */
}
