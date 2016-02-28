package net.ceos.project.poi.annotated.core;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import net.ceos.project.poi.annotated.bean.ObjectMask;
import net.ceos.project.poi.annotated.bean.ObjectMaskBuilder;
import net.ceos.project.poi.annotated.core.Engine;
import net.ceos.project.poi.annotated.core.IEngine;

public class MaskTest extends TestCase {

	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public MaskTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(MaskTest.class);
	}

	/**
	 * Test one basic object
	 */
	public void testObject() throws Exception {
		ObjectMask om = ObjectMaskBuilder.buildObjectMask();

		IEngine en = new Engine();
		en.marshalAndSave(om, TestUtils.WORKING_DIR_GENERATED_I);

		// TODO validation result
		ObjectMask charger = new ObjectMask();
		en.unmarshalFromPath(charger, TestUtils.WORKING_DIR_GENERATED_II);
		
		ObjectMaskBuilder.validateObjectMask(charger);
	}

}
