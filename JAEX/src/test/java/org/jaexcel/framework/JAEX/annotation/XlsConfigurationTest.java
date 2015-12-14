package org.jaexcel.framework.JAEX.annotation;

import java.lang.annotation.Annotation;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.jaexcel.framework.JAEX.bean.ObjectWithDefaultConfig;
import org.jaexcel.framework.JAEX.definition.CascadeType;
import org.jaexcel.framework.JAEX.definition.ExtensionFileType;

public class XlsConfigurationTest extends TestCase {
	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public XlsConfigurationTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(XlsConfigurationTest.class);
	}

	/**
	 * Test default configuration.
	 */
	public void testDefaultConfiguration() {
		Class<ObjectWithDefaultConfig> o = ObjectWithDefaultConfig.class;

		// Process @XlsConfiguration
		if (o.isAnnotationPresent(XlsConfiguration.class)) {

			Annotation annotation = o.getAnnotation(XlsConfiguration.class);
			XlsConfiguration xlsConfig = (XlsConfiguration) annotation;

			// add here the annotations attributes
			assertEquals(xlsConfig.extensionFile(), ExtensionFileType.XLS);
			assertEquals(xlsConfig.cascadeLevel(), CascadeType.CASCADE_BASE);
		}
	}
}
