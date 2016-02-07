package org.jaexcel.framework.JAEX.exception;

import org.jaexcel.framework.JAEX.bean.XlsConfigurationAbsent;
import org.jaexcel.framework.JAEX.bean.XlsSheetAbsent;
import org.jaexcel.framework.JAEX.definition.ExceptionMessage;
import org.jaexcel.framework.JAEX.engine.Engine;
import org.jaexcel.framework.JAEX.engine.IEngine;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class JAEXConfigurationExceptionTest extends TestCase {
	private static final String WORKING_DIR = "D:\\projects\\";

	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public JAEXConfigurationExceptionTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(JAEXConfigurationExceptionTest.class);
	}

	/**
	 * Test a configuration exception with missing definitions
	 */
	public void testMarshalMissingXlsConfigurationException() throws Exception {
		XlsConfigurationAbsent missingConfig = new XlsConfigurationAbsent();

		IEngine en = new Engine();
		try {
			en.marshalAndSave(missingConfig, WORKING_DIR);
		} catch (Exception e) {
			if (!(e.getClass().equals(ConfigurationException.class) && e.getMessage()
					.equals(ExceptionMessage.ConfigurationException_XlsConfigurationMissing.getMessage()))) {
				assertEquals(true, false);
			}
		}
	}

	/**
	 * Test a configuration exception with missing definitions
	 */
	public void testUnmarshalMissingXlsConfigurationException() throws Exception {
		XlsConfigurationAbsent missingConfig = new XlsConfigurationAbsent();

		IEngine en = new Engine();
		try {
			en.unmarshalFromPath(missingConfig, WORKING_DIR);
		} catch (Exception e) {
			if (!(e.getClass().equals(ConfigurationException.class) && e.getMessage()
					.equals(ExceptionMessage.ConfigurationException_XlsConfigurationMissing.getMessage()))) {
				assertEquals(true, false);
			}
		}
	}

	/**
	 * Test a configuration exception with missing definitions
	 */
	public void testMarshalMissingXlsSheetException() throws Exception {
		XlsSheetAbsent missingConfig = new XlsSheetAbsent();

		IEngine en = new Engine();
		try {
			en.marshalAndSave(missingConfig, WORKING_DIR);
		} catch (Exception e) {
			if (!(e.getClass().equals(ConfigurationException.class)
					&& e.getMessage().equals(ExceptionMessage.ConfigurationException_XlsSheetMissing.getMessage()))) {
				assertEquals(true, false);
			}
		}
	}

	/**
	 * Test a configuration exception with missing definitions
	 */
	public void testUnmarshalMissingXlsSheetException() throws Exception {
		XlsSheetAbsent missingConfig = new XlsSheetAbsent();

		IEngine en = new Engine();
		try {
			en.unmarshalFromPath(missingConfig, WORKING_DIR);
		} catch (Exception e) {
			if (!(e.getClass().equals(ConfigurationException.class)
					&& e.getMessage().equals(ExceptionMessage.ConfigurationException_XlsSheetMissing.getMessage()))) {
				assertEquals(true, false);
			}
		}
	}

	/**
	 * Test a configuration exception incompatible
	 */
	public void testIncompatibleException() {
		// FIXME apply test case
		assertEquals(true, false);
	}

	/**
	 * Test a configuration exception with conflicts
	 */
	public void testConflictException() {
		// FIXME apply test case
		assertEquals(true, false);
	}
}
