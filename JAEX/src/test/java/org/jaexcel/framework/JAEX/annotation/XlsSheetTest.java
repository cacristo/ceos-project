package org.jaexcel.framework.JAEX.annotation;

import java.lang.annotation.Annotation;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.jaexcel.framework.JAEX.bean.ObjectWithDefaultConfig;
import org.jaexcel.framework.JAEX.definition.PropagationType;

public class XlsSheetTest extends TestCase {
	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public XlsSheetTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(XlsSheetTest.class);
	}

	/**
	 * Test default configuration.
	 */
	public void testDefaultConfiguration() {
		Class<ObjectWithDefaultConfig> oC = ObjectWithDefaultConfig.class;

		// Process @XlsSheet
		if (oC.isAnnotationPresent(XlsSheet.class)) {

			Annotation annotation = oC.getAnnotation(XlsSheet.class);
			XlsSheet xlsSheet = (XlsSheet) annotation;

			// add here the annotations attributes
			assertEquals(xlsSheet.propagation(),
					PropagationType.PROPAGATION_HORIZONTAL);
			assertEquals(xlsSheet.startRow(), 1);
			assertEquals(xlsSheet.startCell(), 1);
		}
	}
}
