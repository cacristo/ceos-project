package org.jaexcel.framework.JAEX;

import java.util.List;

import org.jaexcel.framework.JAEX.bean.PerformanceObject;
import org.jaexcel.framework.JAEX.bean.PerformanceObjectBuilder;
import org.jaexcel.framework.JAEX.definition.ExtensionFileType;
import org.jaexcel.framework.JAEX.engine.Engine;
import org.jaexcel.framework.JAEX.engine.IEngine;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class PerformanceTest extends TestCase {

	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public PerformanceTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(PerformanceTest.class);
	}

	/**
	 * Test one basic object
	 */
	public void testMarshalMultiObjectPerf() throws Exception {
		List<PerformanceObject> collection = PerformanceObjectBuilder.buildListOfPerformanceObject(5000);
		
		IEngine en = new Engine();

		en.marshalAsCollection(collection, "performance_test_list", ExtensionFileType.XLS);

	}
}
