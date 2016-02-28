package net.ceos.project.poi.annotated.core;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import net.ceos.project.poi.annotated.bean.ObjectFormula;
import net.ceos.project.poi.annotated.bean.ObjectFormulaBuilder;
import net.ceos.project.poi.annotated.core.Engine;
import net.ceos.project.poi.annotated.core.IEngine;

public class FormulaTest extends TestCase {

	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public FormulaTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(FormulaTest.class);
	}

	/**
	 * Test one basic object
	 */
	public void testMarshalObjectFormula() throws Exception {
		ObjectFormula of = ObjectFormulaBuilder.buildObjectFormula();

		IEngine en = new Engine();

		en.marshalAndSave(of, TestUtils.WORKING_DIR_GENERATED_II);
	}

	/**
	 * Test one basic object
	 */
	public void testUnmarshalObjectFormula() throws Exception {
		ObjectFormula of = new ObjectFormula();

		IEngine en = new Engine();

		en.unmarshalFromPath(of, TestUtils.WORKING_DIR_GENERATED_II);

		ObjectFormulaBuilder.validateObjectFormula(of);
	}

}
