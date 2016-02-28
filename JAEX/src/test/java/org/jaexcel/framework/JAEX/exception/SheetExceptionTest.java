package org.jaexcel.framework.JAEX.exception;

import org.jaexcel.framework.JAEX.TestUtils;
import org.jaexcel.framework.JAEX.bean.XlsSheetEmptyTitle;
import org.jaexcel.framework.JAEX.definition.ExceptionMessage;
import org.jaexcel.framework.JAEX.engine.Engine;
import org.jaexcel.framework.JAEX.engine.IEngine;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class SheetExceptionTest extends TestCase {
	private Class<?> c = null;
	private String message = "";

	private void reset() {
		this.c = null;
		this.message = "";
	}

	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public SheetExceptionTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(SheetExceptionTest.class);
	}

	@Override
	protected void setUp() throws Exception {
		reset();
		super.setUp();
	}

	/**
	 * Test a sheet exception because an empty title sheet
	 */
	public void testEmptyTitleSheetMarshalSheetException() {
		XlsSheetEmptyTitle emptyTitle = new XlsSheetEmptyTitle();

		try {
			IEngine en = new Engine();
			en.marshalAndSave(emptyTitle, TestUtils.WORKING_DIR_GENERATED_II);
		} catch (Exception e) {
			this.c = e.getClass();
			this.message = e.getMessage();
		}
		assertEquals(SheetException.class, this.c);
		assertEquals(ExceptionMessage.SheetException_CreationSheet.getMessage(), this.message);
	}

	/**
	 * Test a sheet exception because an empty title sheet
	 */
	public void testEmptyTitleSheetUnmarshalSheetException() throws Exception {
		XlsSheetEmptyTitle emptyTitle = new XlsSheetEmptyTitle();

		try {
			IEngine en = new Engine();
			en.unmarshalFromPath(emptyTitle, TestUtils.WORKING_DIR_GENERATED_II);
		} catch (Exception e) {
			this.c = e.getClass();
			this.message = e.getMessage();
		}
		assertEquals(SheetException.class, this.c);
		assertEquals(ExceptionMessage.SheetException_CreationSheet.getMessage(), this.message);
	}
}
