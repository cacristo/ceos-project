package net.ceos.project.poi.annotated.core;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import net.ceos.project.poi.annotated.bean.FreeElementAdvancedObject;
import net.ceos.project.poi.annotated.bean.FreeElementAdvancedObjectBuilder;
import net.ceos.project.poi.annotated.bean.FreeElementObject;
import net.ceos.project.poi.annotated.bean.FreeElementObjectBuilder;
import net.ceos.project.poi.annotated.core.Engine;
import net.ceos.project.poi.annotated.core.IEngine;

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
