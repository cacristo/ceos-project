package org.jaexcel.framework.JAEX;

import org.jaexcel.framework.JAEX.bean.ObjectFormula;
import org.jaexcel.framework.JAEX.engine.Engine;
import org.jaexcel.framework.JAEX.engine.IEngine;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ObjectFormulaTest extends TestCase {

	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public ObjectFormulaTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(ObjectFormulaTest.class);
	}


	/**
	 * Test one basic object
	 */
	public void testMarshalObjectFormula() throws Exception {
		ObjectFormula of = new ObjectFormula();

		of.setStore(200);
		of.setWebStore(455);

		of.setValueLocal(1623.99);
		of.setValueRegion(3199.99);
		of.setValueCountry(8421.80);
		
		IEngine en = new Engine();

		en.marshal(of);

		assertEquals(true, true);
	}

}
