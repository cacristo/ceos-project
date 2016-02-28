package net.ceos.project.poi.annotated.core;

import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import net.ceos.project.poi.annotated.bean.PerformanceObject;
import net.ceos.project.poi.annotated.bean.PerformanceObjectBuilder;
import net.ceos.project.poi.annotated.core.Engine;
import net.ceos.project.poi.annotated.core.IEngine;
import net.ceos.project.poi.annotated.definition.ExtensionFileType;

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
