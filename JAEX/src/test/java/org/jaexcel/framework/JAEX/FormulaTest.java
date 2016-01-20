package org.jaexcel.framework.JAEX;

import org.jaexcel.framework.JAEX.bean.ObjectFormula;
import org.jaexcel.framework.JAEX.bean.ObjectFormulaBuilder;
import org.jaexcel.framework.JAEX.engine.Engine;
import org.jaexcel.framework.JAEX.engine.IEngine;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

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

		en.marshal(of);
	}
	

	/**
	 * Test one basic object
	 */
	public void testUnmarshalObjectFormula() throws Exception {
		ObjectFormula of = new ObjectFormula();
		
		IEngine en = new Engine();

		en.unmarshalFromPath(of, "D:\\projects\\");
		
		ObjectFormulaBuilder.validateObjectFormula(of);
	}

}
