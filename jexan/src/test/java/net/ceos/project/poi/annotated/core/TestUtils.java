/**
 * Copyright 2016 Carlos CRISTO ABREU
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.ceos.project.poi.annotated.core;

import static junit.framework.Assert.assertEquals;

import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;

import net.ceos.project.poi.annotated.annotation.XlsElement;

/**
 * Utility class for tests.
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public class TestUtils {

	public static final String WORKING_DIR_GENERATED_I = "C:\\projects\\tests\\generated";
	public static final String WORKING_DIR_GENERATED_II = "C:\\projects\\tests\\generated\\";
	public static final String WORKING_DIR_MANUALLY = "C:\\projects\\tests\\manually\\";
	public static final String WORKING_DIR_UNMARSHALL = "C:\\projects\\tests\\generated\\unmarshal\\";
	public static final String WORKING_DIR_UNMARSHALL_M = "C:\\projects\\tests\\generated\\unmarshal\\marshal";

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
