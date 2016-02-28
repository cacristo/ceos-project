package net.ceos.project.poi.annotated.core;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import net.ceos.project.poi.annotated.bean.ObjectNull;
import net.ceos.project.poi.annotated.bean.ObjectNullBuilder;
import net.ceos.project.poi.annotated.core.Engine;
import net.ceos.project.poi.annotated.core.IEngine;

/**
 * Unit test for simple App.
 */
public class ObjectNullAttributesTest extends TestCase {

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
		en.marshalAndSave(mto, TestUtils.WORKING_DIR_GENERATED_I);
		
		//ObjectNull charger = new ObjectNull();
		//ObjectNullBuilder.validateObjectNull(charger);
	}

	/**
	 * Test one basic object
	 */
	public void testUnmarshalMultiObject() throws Exception {
		ObjectNull mto = new ObjectNull();

		Engine en = new Engine();

		en.unmarshalFromPath(mto, TestUtils.WORKING_DIR_GENERATED_I);

		//ObjectNullBuilder.validateObjectNull(mto);
		
	}
}
