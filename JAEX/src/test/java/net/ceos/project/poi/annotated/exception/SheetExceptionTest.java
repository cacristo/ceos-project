package net.ceos.project.poi.annotated.exception;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import net.ceos.project.poi.annotated.bean.XlsSheetEmptyTitle;
import net.ceos.project.poi.annotated.core.Engine;
import net.ceos.project.poi.annotated.core.IEngine;
import net.ceos.project.poi.annotated.core.TestUtils;
import net.ceos.project.poi.annotated.definition.ExceptionMessage;
import net.ceos.project.poi.annotated.exception.SheetException;

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
