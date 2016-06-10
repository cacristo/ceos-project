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

import org.testng.annotations.Test;

import net.ceos.project.poi.annotated.annotation.XlsElement;
import net.ceos.project.poi.annotated.bean.ObjectFormula;
import net.ceos.project.poi.annotated.bean.ObjectFormulaBuilder;

/**
 * Test multiple formulas to apply at {@link XlsElement}
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public class FormulaTest {

	/**
	 * Test the marshal of one object applying formulas at the
	 * {@link XlsElement}
	 */
	@Test
	public void validateMarshalObjectFormula() throws Exception {
		ObjectFormula of = ObjectFormulaBuilder.buildObjectFormula();

		IEngine en = new Engine();
		en.marshalAndSave(of, TestUtils.WORKING_DIR_GENERATED_II);
	}

	/**
	 * Test if the object who applied formulas at the {@link XlsElement} cause
	 * any damage at the moment of unmarshal.
	 */
	@Test
	public void validateUnmarshalObjectFormula() throws Exception {
		ObjectFormula of = new ObjectFormula();

		IEngine en = new Engine();
		en.unmarshalFromPath(of, TestUtils.WORKING_DIR_GENERATED_II);

		ObjectFormulaBuilder.validateObjectFormula(of);
	}

}
