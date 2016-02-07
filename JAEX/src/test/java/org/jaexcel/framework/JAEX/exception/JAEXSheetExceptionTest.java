package org.jaexcel.framework.JAEX.exception;

import org.jaexcel.framework.JAEX.bean.XlsSheetEmptyTitle;
import org.jaexcel.framework.JAEX.definition.ExceptionMessage;
import org.jaexcel.framework.JAEX.engine.Engine;
import org.jaexcel.framework.JAEX.engine.IEngine;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class JAEXSheetExceptionTest extends TestCase {

	private static final String WORKING_DIR = "D:\\projects\\";

	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public JAEXSheetExceptionTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(JAEXSheetExceptionTest.class);
	}

	/**
	 * Test a sheet exception because an empty title sheet
	 */
	public void testEmptyTitleSheetMarshalSheetException() {
		XlsSheetEmptyTitle emptyTitle = new XlsSheetEmptyTitle();

		IEngine en = new Engine();

		try {
			en.marshalAndSave(emptyTitle, WORKING_DIR);
		} catch (Exception e) {
			if (!(e.getClass().equals(SheetException.class)
					&& e.getMessage().equals(ExceptionMessage.SheetException_CreationSheet.getMessage()))) {
				assertEquals(true, false);
			}
		}
	}

	/**
	 * Test a sheet exception because an empty title sheet
	 */
	public void testEmptyTitleSheetUnmarshalSheetException() throws Exception {
		XlsSheetEmptyTitle emptyTitle = new XlsSheetEmptyTitle();

		IEngine en = new Engine();

		try {
			en.unmarshalFromPath(emptyTitle, WORKING_DIR);
		} catch (Exception e) {
			if (!(e.getClass().equals(SheetException.class)
					&& e.getMessage().equals(ExceptionMessage.SheetException_CreationSheet.getMessage()))) {
				assertEquals(true, false);
			}
		}
	}
}
