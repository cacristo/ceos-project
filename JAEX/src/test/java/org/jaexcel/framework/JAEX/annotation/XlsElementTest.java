package org.jaexcel.framework.JAEX.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.jaexcel.framework.JAEX.bean.ObjectWithDefaultConfig;

public class XlsElementTest extends TestCase {
	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public XlsElementTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(XlsElementTest.class);
	}

	public void testDefaultConfiguration() {
		Class<ObjectWithDefaultConfig> oC = ObjectWithDefaultConfig.class;

		List<Field> fL = Arrays.asList(oC.getDeclaredFields());
		for (Field f : fL) {
			// Process @XlsElement
			if (f.isAnnotationPresent(XlsElement.class)) {

				Annotation annotation = f.getAnnotation(XlsElement.class);
				XlsElement xlsElement = (XlsElement) annotation;

				assertEquals(xlsElement.comment(), "");
				assertEquals(xlsElement.cellStyle(), 0);
				assertEquals(xlsElement.decorator(), "");
			}
		}
	}
}
