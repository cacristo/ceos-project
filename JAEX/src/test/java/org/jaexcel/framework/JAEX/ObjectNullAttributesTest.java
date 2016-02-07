package org.jaexcel.framework.JAEX;

import org.jaexcel.framework.JAEX.bean.ObjectNull;
import org.jaexcel.framework.JAEX.bean.ObjectNullBuilder;
import org.jaexcel.framework.JAEX.engine.Engine;
import org.jaexcel.framework.JAEX.engine.IEngine;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class ObjectNullAttributesTest extends TestCase {

	private static final String WORKING_DIR = "D:\\projects\\";

	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public ObjectNullAttributesTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(ObjectNullAttributesTest.class);
	}

	/**
	 * Test one basic object
	 */
	public void testMarshalMultiObject() throws Exception {
		ObjectNull mto = ObjectNullBuilder.buildObjectNull();

		IEngine en = new Engine();
		en.marshal(mto);
		
		//ObjectNull charger = new ObjectNull();
		//ObjectNullBuilder.validateObjectNull(charger);
	}

	/**
	 * Test one basic object
	 */
	public void testUnmarshalMultiObject() throws Exception {
		ObjectNull mto = new ObjectNull();

		Engine en = new Engine();

		en.unmarshalFromPath(mto, WORKING_DIR);

		//ObjectNullBuilder.validateObjectNull(mto);
		
	}
}
