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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import net.ceos.project.poi.annotated.annotation.XlsElement;
import net.ceos.project.poi.annotated.annotation.XlsSheet;
import net.ceos.project.poi.annotated.bean.ObjectMask;
import net.ceos.project.poi.annotated.bean.ObjectMaskBuilder;

/**
 * Test multiple (format/transform) mask to apply at {@link XlsElement}
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public class MaskTest {

	/**
	 * 
	 * Test the marshal/unmarshal of one object applying mask at the
	 * {@link XlsElement}
	 */
	@Test
	public void validateMarshalUnmarshalObjectMask() throws Exception {
		ObjectMask objectMask = ObjectMaskBuilder.buildObjectMask();

		IEngine en = new Engine();
		en.marshalAndSave(objectMask, TestUtils.WORKING_DIR_GENERATED_I);

		ObjectMask charger = new ObjectMask();
		en.unmarshalFromPath(charger, TestUtils.WORKING_DIR_GENERATED_II);

		ObjectMaskBuilder.validateObjectMask(charger);
	}

	/**
	 * 
	 * Test the marshal/unmarshal of one object applying mask at the Date
	 * attribute with {@link XlsElement}
	 */
	@Test
	public void checkDateFormatMask() throws Exception {
		ObjectMask objectMask = ObjectMaskBuilder.buildObjectMask();
		ObjectMask charger = new ObjectMask();

		/* engine declaration */
		IEngine en = new Engine();

		// marshal
		Workbook wb = en.marshalToWorkbook(objectMask);
		// unmarshal
		en.unmarshalFromWorkbook(charger, wb);

		// format date attributes
		Cell cellDate1 = extractCell(charger, wb.getSheetAt(0), 2);
		assertEquals(cellDate1.getDateCellValue(), charger.getDateAttribute2());

		Cell cellDate2 = extractCell(charger, wb.getSheetAt(0), 3);
		assertEquals(cellDate2.getDateCellValue(), charger.getDateAttribute3());

		Cell cellDate3 = extractCell(charger, wb.getSheetAt(0), 4);
		assertEquals(cellDate3.getDateCellValue(), charger.getDateAttribute4());
	}

	/**
	 * 
	 * Test the marshal/unmarshal of one object applying mask at the Date
	 * attribute with {@link XlsElement}
	 */
	@Test
	public void checkDateTransformMask() throws Exception {
		ObjectMask objectMask = ObjectMaskBuilder.buildObjectMask();
		ObjectMask charger = new ObjectMask();

		/* engine declaration */
		IEngine en = new Engine();

		// marshal
		Workbook wb = en.marshalToWorkbook(objectMask);
		// unmarshal
		en.unmarshalFromWorkbook(charger, wb);

		// transform date attributes
		Cell cellDate1 = extractCell(charger, wb.getSheetAt(0), 5);
		validateDate(parserDate(cellDate1.getStringCellValue(), "yyyy-MM-dd"), charger.getDateAttribute5(), true, true,
				true);

		Cell cellDate2 = extractCell(charger, wb.getSheetAt(0), 6);
		validateDate(parserDate(cellDate2.getStringCellValue(), "yyMM"), charger.getDateAttribute6(), false, true,
				true);

		Cell cellDate3 = extractCell(charger, wb.getSheetAt(0), 7);
		validateDate(parserDate(cellDate3.getStringCellValue(), "yyyy"), charger.getDateAttribute7(), false, false,
				true);
	}

	/**
	 * 
	 * Test the marshal/unmarshal of one object applying mask at the Double
	 * attribute with {@link XlsElement}
	 */
	@Test
	public void checkDoubleFormatMask() throws Exception {
		ObjectMask objectMask = ObjectMaskBuilder.buildObjectMask();
		ObjectMask charger = new ObjectMask();

		/* engine declaration */
		IEngine en = new Engine();

		// marshal
		Workbook wb = en.marshalToWorkbook(objectMask);
		// unmarshal
		en.unmarshalFromWorkbook(charger, wb);
		
		// format double attributes
		Cell cellDouble1 = extractCell(charger, wb.getSheetAt(0), 9);
		assertEquals(Double.valueOf(cellDouble1.getNumericCellValue()).doubleValue(), charger.getDoubleAttribute2(),5);

		Cell cellDouble2 = extractCell(charger, wb.getSheetAt(0), 10);
		assertEquals(Double.valueOf(cellDouble2.getNumericCellValue()), charger.getDoubleAttribute3());
	}

	/**
	 * 
	 * Test the marshal/unmarshal of one object applying mask at the Double
	 * attribute with {@link XlsElement}
	 */
	@Test
	public void checkDoubleTransformMask() throws Exception {
		ObjectMask objectMask = ObjectMaskBuilder.buildObjectMask();
		ObjectMask charger = new ObjectMask();

		/* engine declaration */
		IEngine en = new Engine();

		// marshal
		Workbook wb = en.marshalToWorkbook(objectMask);
		// unmarshal
		en.unmarshalFromWorkbook(charger, wb);

		// transform double attributes
		Cell cellDouble1 = extractCell(charger, wb.getSheetAt(0), 11);
		assertEquals(Double.valueOf(cellDouble1.getNumericCellValue()), charger.getDoubleAttribute4());

		Cell cellDouble2 = extractCell(charger, wb.getSheetAt(0), 12);
		assertEquals(Double.valueOf(cellDouble2.getNumericCellValue()).doubleValue(), charger.getDoubleAttribute5(),5);
	}

	/**
	 * 
	 * Test the marshal/unmarshal of one object applying mask at the Float
	 * attribute with {@link XlsElement}
	 */
	// @Test
	public void checkFloatFormatMask() throws Exception {
		ObjectMask objectMask = ObjectMaskBuilder.buildObjectMask();
		ObjectMask charger = new ObjectMask();

		/* engine declaration */
		IEngine en = new Engine();

		// marshal
		Workbook wb = en.marshalToWorkbook(objectMask);
		// unmarshal
		en.unmarshalFromWorkbook(charger, wb);

		// FIX this problem : incorrect application of the mask
		// format float attributes
		Cell cellFloat1 = extractCell(charger, wb.getSheetAt(0), 20);
		assertEquals(Double.valueOf(cellFloat1.getNumericCellValue()).floatValue(),
				charger.getFloatAttribute2().floatValue(),5);

		Cell cellFloat2 = extractCell(charger, wb.getSheetAt(0), 21);
		assertEquals(Double.valueOf(cellFloat2.getNumericCellValue()).floatValue(),
				charger.getFloatAttribute3().floatValue(),5);
	}

	/**
	 * 
	 * Test the marshal/unmarshal of one object applying mask at the Float
	 * attribute with {@link XlsElement}
	 */
	@Test
	public void checkFloatTransformMask() throws Exception {
		ObjectMask objectMask = ObjectMaskBuilder.buildObjectMask();
		ObjectMask charger = new ObjectMask();

		/* engine declaration */
		IEngine en = new Engine();

		// marshal
		Workbook wb = en.marshalToWorkbook(objectMask);
		// unmarshal
		en.unmarshalFromWorkbook(charger, wb);

		// transform float attributes
		Cell cellFloat1 = extractCell(charger, wb.getSheetAt(0), 22);
		String floatStr1 = charger.getFloatAttribute4().toString();
		assertEquals(cellFloat1.getStringCellValue().startsWith(floatStr1.substring(0, floatStr1.length() - 1)), true);

		Cell cellFloat2 = extractCell(charger, wb.getSheetAt(0), 23);
		String floatStr2 = String.valueOf(charger.getFloatAttribute5());
		assertEquals(cellFloat2.getStringCellValue().startsWith(floatStr2.substring(0, floatStr2.length() - 1)), true);
	}

	/**
	 * 
	 * Test the marshal/unmarshal of one object applying mask at the BigDecimal
	 * attribute with {@link XlsElement}
	 */
	@Test
	public void checkBigDecimalTransformMask() throws Exception {
		ObjectMask objectMask = ObjectMaskBuilder.buildObjectMask();
		ObjectMask charger = new ObjectMask();

		/* engine declaration */
		IEngine en = new Engine();

		// marshal
		Workbook wb = en.marshalToWorkbook(objectMask);
		// unmarshal
		en.unmarshalFromWorkbook(charger, wb);

		// transform bigdecimal attributes
		Cell cellBD1 = extractCell(charger, wb.getSheetAt(0), 16);
		assertEquals(charger.getBigDecimalAttribute4(),
				BigDecimal.valueOf(Double.valueOf(cellBD1.getStringCellValue())));

		Cell cellBD2 = extractCell(charger, wb.getSheetAt(0), 17);
		assertEquals(charger.getBigDecimalAttribute5(),
				BigDecimal.valueOf(Double.valueOf(cellBD2.getStringCellValue())));

		Cell cellBD3 = extractCell(charger, wb.getSheetAt(0), 18);
		assertEquals(charger.getBigDecimalAttribute6(),
				BigDecimal.valueOf(Double.valueOf(cellBD3.getStringCellValue())));
	}

	/**
	 * 
	 * Test the marshal/unmarshal of one object applying mask at the Boolean
	 * attribute with {@link XlsElement}
	 */
	@Test
	public void checkBooleanFormatMask() throws Exception {
		ObjectMask objectMask = ObjectMaskBuilder.buildObjectMask();
		ObjectMask charger = new ObjectMask();

		/* engine declaration */
		IEngine en = new Engine();

		// marshal
		Workbook wb = en.marshalToWorkbook(objectMask);
		// unmarshal
		en.unmarshalFromWorkbook(charger, wb);

		// format boolean attributes
		Cell cellBool1 = extractCell(charger, wb.getSheetAt(0), 27);
		assertNotSame(cellBool1.getStringCellValue(), "No No");
		assertEquals(charger.isBooleanAttribute4(), cellBool1.getStringCellValue().equals("false") ? false : true);
	}

	/**
	 * 
	 * Test the marshal/unmarshal of one object applying mask at the Boolean
	 * attribute with {@link XlsElement}
	 */
	@Test
	public void checkBooleanTransformMask() throws Exception {
		ObjectMask objectMask = ObjectMaskBuilder.buildObjectMask();
		ObjectMask charger = new ObjectMask();

		/* engine declaration */
		IEngine en = new Engine();

		// marshal
		Workbook wb = en.marshalToWorkbook(objectMask);
		// unmarshal
		en.unmarshalFromWorkbook(charger, wb);

		// transform boolean attributes
		Cell cellBool1 = extractCell(charger, wb.getSheetAt(0), 24);
		assertEquals(charger.isBooleanAttribute1(), parserBoolean(cellBool1.getStringCellValue(), "Yes/No"));

		Cell cellBool2 = extractCell(charger, wb.getSheetAt(0), 26);
		assertEquals(charger.getBooleanAttribute3(),
				Boolean.valueOf(parserBoolean(cellBool2.getStringCellValue(), "Oui/Non")));
	}

	/**
	 * 
	 * Test the marshal/unmarshal of one object applying mask at the Boolean
	 * attribute with {@link XlsElement}
	 */
	@Test
	public void checkIntegerFormatMask() throws Exception {
		ObjectMask objectMask = ObjectMaskBuilder.buildObjectMask();
		ObjectMask charger = new ObjectMask();

		/* engine declaration */
		IEngine en = new Engine();

		// marshal
		Workbook wb = en.marshalToWorkbook(objectMask);
		// unmarshal
		en.unmarshalFromWorkbook(charger, wb);

		// transform integer attributes
		Cell cellInteger = extractCell(charger, wb.getSheetAt(0), 28);
		assertEquals(charger.getIntegerAttribute1(), Integer.valueOf((int) cellInteger.getNumericCellValue()));
		assertEquals(parserNumeric(charger.getIntegerAttribute1(), "0.0"), cellInteger.getNumericCellValue(), 5);
	}

	/**
	 * 
	 * Test the marshal/unmarshal of one object applying mask at the Boolean
	 * attribute with {@link XlsElement}
	 */
	@Test
	public void checkIntegerTransformMask() throws Exception {
		ObjectMask objectMask = ObjectMaskBuilder.buildObjectMask();
		ObjectMask charger = new ObjectMask();

		/* engine declaration */
		IEngine en = new Engine();

		// marshal
		Workbook wb = en.marshalToWorkbook(objectMask);
		// unmarshal
		en.unmarshalFromWorkbook(charger, wb);

		// transform integer attributes
		Cell cellInteger = extractCell(charger, wb.getSheetAt(0), 29);
		assertEquals(String.valueOf(parserNumeric(charger.getIntAttribute2(), "0.0")),
				cellInteger.getStringCellValue());
	}

	/**
	 * 
	 * Test the marshal/unmarshal of one object applying mask at the Boolean
	 * attribute with {@link XlsElement}
	 */
	@Test
	public void checkShortFormatMask() throws Exception {
		ObjectMask objectMask = ObjectMaskBuilder.buildObjectMask();
		ObjectMask charger = new ObjectMask();

		/* engine declaration */
		IEngine en = new Engine();

		// marshal
		Workbook wb = en.marshalToWorkbook(objectMask);
		// unmarshal
		en.unmarshalFromWorkbook(charger, wb);

		// transform short attributes
		Cell cellShort = extractCell(charger, wb.getSheetAt(0), 30);
		assertEquals(charger.getShortAttribute1(), (short) cellShort.getNumericCellValue());
		assertEquals(parserNumeric(charger.getShortAttribute1(), "0.0000"), cellShort.getNumericCellValue(), 4);
	}

	/**
	 * 
	 * Test the marshal/unmarshal of one object applying mask at the Boolean
	 * attribute with {@link XlsElement}
	 */
	@Test
	public void checkShortTransformMask() throws Exception {
		ObjectMask objectMask = ObjectMaskBuilder.buildObjectMask();
		ObjectMask charger = new ObjectMask();

		/* engine declaration */
		IEngine en = new Engine();

		// marshal
		Workbook wb = en.marshalToWorkbook(objectMask);
		// unmarshal
		en.unmarshalFromWorkbook(charger, wb);

		// transform short attributes
		Cell cellShort = extractCell(charger, wb.getSheetAt(0), 31);
		assertEquals(String.valueOf(parserNumeric(charger.getShortAttribute2(), "0.0")),
				cellShort.getStringCellValue());
	}

	/* private tests methods */

	private Cell extractCell(ObjectMask objectMask, Sheet sheet, int position) {
		XlsSheet annotation = objectMask.getClass().getAnnotation(XlsSheet.class);
		Row row = sheet.getRow(annotation.startRow());
		return row.getCell(annotation.startCell() + position - 1);
	}

	private Date parserDate(String date, String pattern) throws ParseException {
		return new SimpleDateFormat(pattern).parse(date);
	}

	private boolean parserBoolean(String bool, String pattern) {
		String[] split = pattern.split(Constants.SLASH);
		return bool.equals(split[0]) ? true : false;
	}

	private double parserNumeric(double value, String pattern) {
		NumberFormat formatter = new DecimalFormat("#" + pattern);
		return Double.valueOf(formatter.format(value));
	}

	private void validateDate(Date output, Date input, boolean day, boolean month, boolean year) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(output);
		Calendar calendarUnmarshal = Calendar.getInstance();
		calendarUnmarshal.setTime(input);
		if (year) {
			assertEquals(calendar.get(Calendar.YEAR), calendarUnmarshal.get(Calendar.YEAR));
		}
		if (month) {
			assertEquals(calendar.get(Calendar.MONTH), calendarUnmarshal.get(Calendar.MONTH));
		}
		if (day) {
			assertEquals(calendar.get(Calendar.DAY_OF_MONTH), calendarUnmarshal.get(Calendar.DAY_OF_MONTH));
		}
	}
}
