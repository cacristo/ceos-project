package org.jaexcel.framework.JAEX;

import org.jaexcel.framework.JAEX.bean.FreeElementAdvancedObject;
import org.jaexcel.framework.JAEX.bean.FreeElementAdvancedObjectBuilder;
import org.jaexcel.framework.JAEX.bean.FreeElementObject;
import org.jaexcel.framework.JAEX.bean.FreeElementObjectBuilder;
import org.jaexcel.framework.JAEX.engine.Engine;
import org.jaexcel.framework.JAEX.engine.IEngine;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class FreeElementTest extends TestCase {

	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public FreeElementTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(FreeElementTest.class);
	}

	/**
	 * Test one basic object
	 */
	public void testMarshalFreeElementObject() throws Exception {
		FreeElementObject fe = FreeElementObjectBuilder.buildFreeElementObject();

		IEngine en = new Engine();

		en.marshalAndSave(fe, TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test one basic object
	 */
	public void testUnmarshalFreeElementObject() throws Exception {
		FreeElementObject fe = new FreeElementObject();

		IEngine en = new Engine();

		en.unmarshalFromPath(fe, TestUtils.WORKING_DIR_GENERATED_I);

		FreeElementObjectBuilder.validateFreeElementObject(fe);
	}

	/**
	 * Test one basic object
	 */
	public void testMarshalFreeElementAdvancedObject() throws Exception {
		FreeElementAdvancedObject fea = FreeElementAdvancedObjectBuilder.buildFreeElementAdvancedObject();

		IEngine en = new Engine();

		en.marshalAndSave(fea, TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test one basic object
	 */
	public void testUnmarshalFreeElementAdvancedObject() throws Exception {
		FreeElementAdvancedObject fea = new FreeElementAdvancedObject();

		IEngine en = new Engine();

		en.unmarshalFromPath(fea, TestUtils.WORKING_DIR_GENERATED_I);

		FreeElementAdvancedObjectBuilder.validateFreeElementAdvancedObject(fea);
	}
}
