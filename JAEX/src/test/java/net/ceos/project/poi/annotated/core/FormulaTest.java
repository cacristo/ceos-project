package net.ceos.project.poi.annotated.core;


import org.testng.annotations.Test;

import net.ceos.project.poi.annotated.bean.ObjectFormula;
import net.ceos.project.poi.annotated.bean.ObjectFormulaBuilder;
import net.ceos.project.poi.annotated.core.Engine;
import net.ceos.project.poi.annotated.core.IEngine;

public class FormulaTest {

	/**
	 * Test one basic object
	 */
	@Test
	public void testMarshalObjectFormula() throws Exception {
		ObjectFormula of = ObjectFormulaBuilder.buildObjectFormula();

		IEngine en = new Engine();

		en.marshalAndSave(of, TestUtils.WORKING_DIR_GENERATED_II);
	}

	/**
	 * Test one basic object
	 */
	@Test
	public void testUnmarshalObjectFormula() throws Exception {
		ObjectFormula of = new ObjectFormula();

		IEngine en = new Engine();

		en.unmarshalFromPath(of, TestUtils.WORKING_DIR_GENERATED_II);

		ObjectFormulaBuilder.validateObjectFormula(of);
	}

}
