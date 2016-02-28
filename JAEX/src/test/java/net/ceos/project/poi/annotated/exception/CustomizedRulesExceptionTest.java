package net.ceos.project.poi.annotated.exception;

import java.util.Date;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import net.ceos.project.poi.annotated.bean.XlsElementCustomizedRulesMultipleMethods;
import net.ceos.project.poi.annotated.bean.XlsElementCustomizedRulesNoSuchMethod;
import net.ceos.project.poi.annotated.bean.XlsElementCustomizedRulesNumeric;
import net.ceos.project.poi.annotated.bean.XlsElementCustomizedRulesString;
import net.ceos.project.poi.annotated.core.Engine;
import net.ceos.project.poi.annotated.core.IEngine;
import net.ceos.project.poi.annotated.core.TestUtils;
import net.ceos.project.poi.annotated.definition.ExceptionMessage;
import net.ceos.project.poi.annotated.exception.CustomizedRulesException;

public class CustomizedRulesExceptionTest extends TestCase {
	private boolean exceptionDetected = false;

	private void reset() {
		this.exceptionDetected = false;

	}

	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public CustomizedRulesExceptionTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(CustomizedRulesExceptionTest.class);
	}

	@Override
	protected void setUp() throws Exception {
		reset();
		super.setUp();
	}

	/**
	 * Test a simple customized rules defined if value under 450
	 * 
	 * @throws Exception
	 */
	public void testCustomizedRuleAtNumeric() throws Exception {
		XlsElementCustomizedRulesNumeric custom = new XlsElementCustomizedRulesNumeric();
		custom.setStringAttribute("Touch down to this team!");
		custom.setIntegerAttribute(300);
		custom.setDateAttribute(new Date());
		try {
			IEngine en = new Engine();
			en.marshalAndSave(custom, TestUtils.WORKING_DIR_GENERATED_I);
		} catch (Exception e) {
			this.exceptionDetected = e.getCause().toString().contains(CustomizedRulesException.class.getSimpleName());
		}
		assertEquals(true, this.exceptionDetected);
	}

	/**
	 * Test a simple customized rules defined if a string contains the word
	 * "down"
	 * 
	 * @throws Exception
	 */
	public void testCustomizedRuleAtString() throws Exception {
		XlsElementCustomizedRulesString custom = new XlsElementCustomizedRulesString();
		custom.setStringAttribute("Touch down to this team!");
		custom.setIntegerAttribute(300);
		custom.setDateAttribute(new Date());
		try {
			IEngine en = new Engine();
			en.marshalAndSave(custom, TestUtils.WORKING_DIR_GENERATED_I);
		} catch (Exception e) {
			this.exceptionDetected = e.getCause().toString().contains(CustomizedRulesException.class.getSimpleName());
		}
		assertEquals(true, this.exceptionDetected);
	}

	/**
	 * Test a multiples customized rules defined at the same object
	 * 
	 * @throws Exception
	 */
	public void testCustomizedRuleAtMultipleMethods1() throws Exception {
		XlsElementCustomizedRulesMultipleMethods custom = new XlsElementCustomizedRulesMultipleMethods();
		custom.setDateAttribute(new Date());
		custom.setStringAttribute("Touch down to this team!");
		custom.setIntegerAttribute1(300);
		custom.setIntegerAttribute2(300);
		custom.setDoublePrimitiveAttribute(10);
		try {
			IEngine en = new Engine();
			en.marshalAndSave(custom, TestUtils.WORKING_DIR_GENERATED_I);
		} catch (Exception e) {
			this.exceptionDetected = e.getCause().toString().contains(CustomizedRulesException.class.getSimpleName());
		}
		assertEquals(true, this.exceptionDetected);
	}

	/**
	 * Test a multiples customized rules defined at the same object
	 * 
	 * @throws Exception
	 */
	public void testCustomizedRuleAtMultipleMethods2() throws Exception {
		XlsElementCustomizedRulesMultipleMethods custom = new XlsElementCustomizedRulesMultipleMethods();
		custom.setDateAttribute(new Date());
		custom.setStringAttribute("Touch this team!");
		custom.setIntegerAttribute1(300);
		custom.setIntegerAttribute2(300);
		custom.setDoublePrimitiveAttribute(0);
		try {
			IEngine en = new Engine();
			en.marshalToSheet(custom);
		} catch (Exception e) {
			this.exceptionDetected = e.getCause().toString().contains(CustomizedRulesException.class.getSimpleName());
		}
		assertEquals(true, this.exceptionDetected);
	}

	/**
	 * Test a multiples customized rules defined at the same object
	 * 
	 * @throws Exception
	 */
	public void testCustomizedRuleAtMultipleMethods3() throws Exception {
		XlsElementCustomizedRulesMultipleMethods custom = new XlsElementCustomizedRulesMultipleMethods();
		custom.setDateAttribute(new Date());
		custom.setStringAttribute("Touch this team!");
		custom.setIntegerAttribute1(300);
		custom.setIntegerAttribute2(200);
		custom.setDoublePrimitiveAttribute(15);
		try {
			IEngine en = new Engine();
			en.marshalToWorkbook(custom);
		} catch (Exception e) {
			this.exceptionDetected = e.getCause().toString().contains(CustomizedRulesException.class.getSimpleName());
		}
		assertEquals(true, this.exceptionDetected);
	}

	/**
	 * Test a customized rules which does not exist a method
	 * 
	 * @throws Exception
	 */
	public void testCustomizedRuleNoSuchMethod() throws Exception {
		XlsElementCustomizedRulesNoSuchMethod custom = new XlsElementCustomizedRulesNoSuchMethod();
		custom.setDateAttribute(new Date());
		custom.setStringAttribute("Touch this team!");
		custom.setIntegerAttribute(300);
		try {
			IEngine en = new Engine();
			en.marshalToWorkbook(custom);
		} catch (Exception e) {
			this.exceptionDetected = (e.getClass().equals(CustomizedRulesException.class)
					&& ExceptionMessage.CustomizedRulesException_NoSuchMethod.getMessage().equals(e.getMessage()));
		}
		assertEquals(true, this.exceptionDetected);
	}
}
