package org.jaexcel.framework.JAEX;

import org.jaexcel.framework.JAEX.bean.ObjectMask;
import org.jaexcel.framework.JAEX.bean.ObjectMaskBuilder;
import org.jaexcel.framework.JAEX.engine.Engine;
import org.jaexcel.framework.JAEX.engine.IEngine;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ObjectMaskTest extends TestCase {

	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public ObjectMaskTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(ObjectMaskTest.class);
	}

	/**
	 * Test one basic object
	 */
	public void testObject() throws Exception {
		ObjectMask om = ObjectMaskBuilder.buildObjectMask();

		IEngine en = new Engine();
		en.marshal(om);

		// TODO validation result
		ObjectMask charger = new ObjectMask();
		en.unmarshalFromPath(charger, TestUtils.WORKING_DIR_GENERATED_II);
		
		ObjectMaskBuilder.validateObjectMask(charger);
	}

}
