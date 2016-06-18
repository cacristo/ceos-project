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

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import net.ceos.project.poi.annotated.annotation.XlsElement;
import net.ceos.project.poi.annotated.bean.ObjectFormulaBuilder;
import net.ceos.project.poi.annotated.bean.ObjectFormulaHorizontal;
import net.ceos.project.poi.annotated.bean.ObjectFormulaVertical;
import net.ceos.project.poi.annotated.bean.factory.IlluminatiFactory;
import net.ceos.project.poi.annotated.definition.PropagationType;
import net.ceos.project.poi.annotated.exception.ElementException;

/**
 * Test multiple formulas to apply at {@link XlsElement}
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public class FormulaTest {

	@DataProvider
	public Object[][] incorrectFormulaProvider() {
		return new Object[][] {
				/*
				 * Conflict caused at @XlsElement by the PropagationType
				 * HORIZONTAL and invalid formula template
				 */
				{ IlluminatiFactory.instanceIronMan() },
				/*
				 * Conflict caused at @XlsElement by the PropagationType
				 * HORIZONTAL and formula vertical orientation
				 */
				{ IlluminatiFactory.instanceBlackBolt() },
				/*
				 * Conflict caused at @XlsElement by the PropagationType
				 * VERTICAL and formula horizontal orientation
				 */
				{ IlluminatiFactory.instanceNamor() },
				/*
				 * Conflict caused at @XlsElement by the PropagationType
				 * VERTICAL and invalid formula template
				 */
				{ IlluminatiFactory.instanceDrStrange() },
				/*
				 * Conflict caused at @XlsFreeElement by the PropagationType
				 * HORIZONTAL and invalid formula template
				 */
				{ IlluminatiFactory.instanceMisterFantastic() },
				/*
				 * Conflict caused at @XlsFreeElement by the PropagationType
				 * HORIZONTAL and formula vertical orientation
				 */
				{ IlluminatiFactory.instanceBlackPanther() },
				/*
				 * Conflict caused at @XlsFreeElement by the PropagationType
				 * VERTICAL and formula horizontal orientation
				 */
				{ IlluminatiFactory.instanceProfessorX() },
				/*
				 * Conflict caused at @XlsFreeElement by the PropagationType
				 * VERTICAL and invalid formula template
				 */
				{ IlluminatiFactory.instanceHood() } };
	}

	/**
	 * Test the marshal of one object applying formulas at the
	 * {@link XlsElement}
	 */
	@Test
	public void validateMarshalObjectFormulaHorizontal() throws Exception {
		ObjectFormulaHorizontal of = ObjectFormulaBuilder.buildObjectFormulaHorizontal();

		IEngine en = new Engine();
		en.marshalAndSave(of, TestUtils.WORKING_DIR_GENERATED_II);
	}

	/**
	 * Test if the object who applied formulas at the {@link XlsElement} cause
	 * any damage at the moment of unmarshal.
	 */
	@Test
	public void validateUnmarshalObjectFormulaHorizontal() throws Exception {
		ObjectFormulaHorizontal of = new ObjectFormulaHorizontal();

		IEngine en = new Engine();
		en.unmarshalFromPath(of, TestUtils.WORKING_DIR_GENERATED_II);

		ObjectFormulaBuilder.validateObjectFormulaHorizontal(of);
	}

	/**
	 * Test the marshal of one object applying formulas at the
	 * {@link XlsElement}
	 */
	@Test
	public void validateMarshalObjectFormulaVertical() throws Exception {
		ObjectFormulaVertical of = ObjectFormulaBuilder.buildObjectFormulaVertical();

		IEngine en = new Engine();
		en.marshalAndSave(of, TestUtils.WORKING_DIR_GENERATED_II);
	}

	/**
	 * Test if the object who applied formulas at the {@link XlsElement} cause
	 * any damage at the moment of unmarshal.
	 */
	@Test
	public void validateUnmarshalObjectFormulaVertical() throws Exception {
		ObjectFormulaVertical of = new ObjectFormulaVertical();

		IEngine en = new Engine();
		en.unmarshalFromPath(of, TestUtils.WORKING_DIR_GENERATED_II);

		ObjectFormulaBuilder.validateObjectFormulaVertical(of);
	}

	/**
	 * Test a configuration conflict caused by the {@link PropagationType} and
	 * formula template/orientation
	 */
	@Test(dataProvider = "incorrectFormulaProvider", expectedExceptions = ElementException.class, expectedExceptionsMessageRegExp = "Conflict caused by the PropagationType and formula orientation. Review your configuration.")
	public void configurationConflictByPropagationFormulaException(Object formulaObj) throws Exception {
		IEngine en = new Engine();
		en.marshalAndSave(formulaObj, TestUtils.WORKING_DIR_GENERATED_I);
	}

}
