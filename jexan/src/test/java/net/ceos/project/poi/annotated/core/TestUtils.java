package net.ceos.project.poi.annotated.core;

import static junit.framework.Assert.assertEquals;

import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;

import net.ceos.project.poi.annotated.annotation.XlsElement;

public class TestUtils {

	public static final String WORKING_DIR_GENERATED_I = "D:\\projects\\tests\\generated";
	public static final String WORKING_DIR_GENERATED_II = "D:\\projects\\tests\\generated\\";
	public static final String WORKING_DIR_MANUALLY = "C:\\projects\\tests\\manually\\";
	public static final String WORKING_DIR_UNMARSHALL = "D:\\projects\\tests\\generated\\unmarshal\\";
	public static final String WORKING_DIR_UNMARSHALL_M = "D:\\projects\\tests\\generated\\unmarshal\\marshal";
	
	public static void validationString(String string,
			XlsElement xlsAnnotation, HSSFCell headerCell, HSSFCell contentCell) {
		assertEquals(xlsAnnotation.title(), headerCell.getStringCellValue());
		assertEquals(string, contentCell.getStringCellValue());
	}

	public static void validationDate(Date date, XlsElement xlsAnnotation,
			HSSFCell headerCell, HSSFCell contentCell) {
		assertEquals(xlsAnnotation.title(), headerCell.getStringCellValue());
		assertEquals(date, contentCell.getDateCellValue());
	}

	public static void validationNumeric(Integer integer,
			XlsElement xlsAnnotation, HSSFCell headerCell, HSSFCell contentCell) {
		assertEquals(xlsAnnotation.title(), headerCell.getStringCellValue());
		Double d = contentCell.getNumericCellValue();
		assertEquals(integer, Integer.valueOf(d.intValue()));
	}
}
