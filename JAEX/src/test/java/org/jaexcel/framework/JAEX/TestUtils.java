package org.jaexcel.framework.JAEX;

import static junit.framework.Assert.assertEquals;

import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.jaexcel.framework.JAEX.annotation.XlsElement;

public class TestUtils {

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
