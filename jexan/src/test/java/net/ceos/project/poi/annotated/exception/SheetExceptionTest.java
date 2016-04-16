package net.ceos.project.poi.annotated.exception;

import org.junit.Test;

import net.ceos.project.poi.annotated.bean.XlsSheetEmptyTitle;
import net.ceos.project.poi.annotated.core.Engine;
import net.ceos.project.poi.annotated.core.IEngine;
import net.ceos.project.poi.annotated.core.TestUtils;

/**
 * Test the {@link SheetException}
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public class SheetExceptionTest {

	/**
	 * Test a sheet exception because an empty title sheet
	 */
	@Test(expected = SheetException.class)
	public void testEmptyTitleSheetMarshalSheetException() throws Exception {
		XlsSheetEmptyTitle emptyTitle = new XlsSheetEmptyTitle();

		IEngine en = new Engine();
		en.marshalAndSave(emptyTitle, TestUtils.WORKING_DIR_GENERATED_II);
	}

	/**
	 * Test a sheet exception because an empty title sheet
	 */
	@Test(expected = SheetException.class)
	public void testEmptyTitleSheetUnmarshalSheetException() throws Exception {
		XlsSheetEmptyTitle emptyTitle = new XlsSheetEmptyTitle();

		IEngine en = new Engine();
		en.unmarshalFromPath(emptyTitle, TestUtils.WORKING_DIR_GENERATED_II);
	}
}
