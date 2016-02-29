package net.ceos.project.poi.annotated.exception;

import org.testng.annotations.Test;

import net.ceos.project.poi.annotated.bean.XlsSheetEmptyTitle;
import net.ceos.project.poi.annotated.core.Engine;
import net.ceos.project.poi.annotated.core.IEngine;
import net.ceos.project.poi.annotated.core.TestUtils;

public class SheetExceptionTest {

	/**
	 * Test a sheet exception because an empty title sheet
	 */
	@Test(expectedExceptions = SheetException.class, expectedExceptionsMessageRegExp = "Problem while creating the Sheet. Review your configuration.")
	public void testEmptyTitleSheetMarshalSheetException() throws Exception {
		XlsSheetEmptyTitle emptyTitle = new XlsSheetEmptyTitle();

		IEngine en = new Engine();
		en.marshalAndSave(emptyTitle, TestUtils.WORKING_DIR_GENERATED_II);
	}

	/**
	 * Test a sheet exception because an empty title sheet
	 */
	@Test(expectedExceptions = SheetException.class, expectedExceptionsMessageRegExp = "Problem while creating the Sheet. Review your configuration.")
	public void testEmptyTitleSheetUnmarshalSheetException() throws Exception {
		XlsSheetEmptyTitle emptyTitle = new XlsSheetEmptyTitle();

		IEngine en = new Engine();
		en.unmarshalFromPath(emptyTitle, TestUtils.WORKING_DIR_GENERATED_II);
	}
}
