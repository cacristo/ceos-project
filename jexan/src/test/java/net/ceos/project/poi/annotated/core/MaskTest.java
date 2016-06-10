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

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

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

	@DataProvider
	public Object[][] dataProvider() throws Exception {
		ObjectMask objectMask = ObjectMaskBuilder.buildObjectMask();
		ObjectMask charger = new ObjectMask();

		/* engine declaration */
		IEngine en = new Engine();

		// marshal
		Workbook wb = en.marshalToWorkbook(objectMask);

		// unmarshal
		en.unmarshalFromWorkbook(charger, wb);

		return new Object[][] {
				/* object & workbook */
				{ charger, wb } };
	}

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
	@Test(dataProvider = "dataProvider")
	public void checkDateFormatMask(ObjectMask charger, Workbook wb) throws Exception {
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
	@Test(dataProvider = "dataProvider")
	public void checkDateTransformMask(ObjectMask charger, Workbook wb) throws Exception {
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
	@Test(dataProvider = "dataProvider")
	public void checkDoubleFormatMask(ObjectMask charger, Workbook wb) throws Exception {
		// format double attributes
		Cell cellDouble1 = extractCell(charger, wb.getSheetAt(0), 9);
		assertEquals(Double.valueOf(cellDouble1.getNumericCellValue()), charger.getDoubleAttribute2());

		Cell cellDouble2 = extractCell(charger, wb.getSheetAt(0), 10);
		assertEquals(Double.valueOf(cellDouble2.getNumericCellValue()), charger.getDoubleAttribute3());
	}

	/**
	 * 
	 * Test the marshal/unmarshal of one object applying mask at the Double
	 * attribute with {@link XlsElement}
	 */
	@Test(dataProvider = "dataProvider")
	public void checkDoubleTransformMask(ObjectMask charger, Workbook wb) throws Exception {
		// transform double attributes
		Cell cellDouble1 = extractCell(charger, wb.getSheetAt(0), 11);
		assertEquals(Double.valueOf(cellDouble1.getStringCellValue()), charger.getDoubleAttribute4());

		Cell cellDouble2 = extractCell(charger, wb.getSheetAt(0), 12);
		assertEquals(Double.valueOf(cellDouble2.getStringCellValue()), charger.getDoubleAttribute5());
	}

	/**
	 * 
	 * Test the marshal/unmarshal of one object applying mask at the Float
	 * attribute with {@link XlsElement}
	 */
	// @Test(dataProvider = "dataProvider")
	public void checkFloatFormatMask(ObjectMask charger, Workbook wb) throws Exception {
		// FIX this problem : incorrect application of the mask
		// format float attributes
		Cell cellFloat1 = extractCell(charger, wb.getSheetAt(0), 20);
		assertEquals(cellFloat1.getNumericCellValue(), charger.getFloatAttribute2());

		Cell cellFloat2 = extractCell(charger, wb.getSheetAt(0), 21);
		assertEquals(cellFloat2.getNumericCellValue(), charger.getFloatAttribute3());
	}

	/**
	 * 
	 * Test the marshal/unmarshal of one object applying mask at the Float
	 * attribute with {@link XlsElement}
	 */
	@Test(dataProvider = "dataProvider")
	public void checkFloatTransformMask(ObjectMask charger, Workbook wb) throws Exception {
		// transform float attributes
		Cell cellFloat1 = extractCell(charger, wb.getSheetAt(0), 22);
		String floatStr1 = charger.getFloatAttribute4().toString();
		assertEquals(String.valueOf(cellFloat1.getNumericCellValue())
				.startsWith(floatStr1.substring(0, floatStr1.length() - 1)), true);

		Cell cellFloat2 = extractCell(charger, wb.getSheetAt(0), 23);
		String floatStr2 = String.valueOf(charger.getFloatAttribute5());
		assertEquals(String.valueOf(cellFloat2.getNumericCellValue())
				.startsWith(floatStr2.substring(0, floatStr2.length() - 1)), true);
	}

	/**
	 * 
	 * Test the marshal/unmarshal of one object applying mask at the BigDecimal
	 * attribute with {@link XlsElement}
	 */
	@Test(dataProvider = "dataProvider")
	public void checkBigDecimalTransformMask(ObjectMask charger, Workbook wb) throws Exception {
		// transform bigdecimal attributes
		Cell cellBD1 = extractCell(charger, wb.getSheetAt(0), 16);
		assertEquals(charger.getBigDecimalAttribute4(), BigDecimal.valueOf(cellBD1.getNumericCellValue()));

		Cell cellBD2 = extractCell(charger, wb.getSheetAt(0), 17);
		assertEquals(charger.getBigDecimalAttribute5(), BigDecimal.valueOf(cellBD2.getNumericCellValue()));

		Cell cellBD3 = extractCell(charger, wb.getSheetAt(0), 18);
		assertEquals(charger.getBigDecimalAttribute6(), BigDecimal.valueOf(cellBD3.getNumericCellValue()));
	}

	/**
	 * 
	 * Test the marshal/unmarshal of one object applying mask at the Boolean
	 * attribute with {@link XlsElement}
	 */
	@Test(dataProvider = "dataProvider")
	public void checkBooleanFormatMask(ObjectMask charger, Workbook wb) throws Exception {
		// format boolean attributes
		Cell cellBool1 = extractCell(charger, wb.getSheetAt(0), 27);
		assertNotEquals(cellBool1.getStringCellValue(), "No No");
		assertEquals(charger.isBooleanAttribute4(), cellBool1.getStringCellValue().equals("false") ? false : true);
	}

	/**
	 * 
	 * Test the marshal/unmarshal of one object applying mask at the Boolean
	 * attribute with {@link XlsElement}
	 */
	@Test(dataProvider = "dataProvider")
	public void checkBooleanTransformMask(ObjectMask charger, Workbook wb) throws Exception {
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
	@Test(dataProvider = "dataProvider")
	public void checkIntegerFormatMask(ObjectMask charger, Workbook wb) throws Exception {
		// transform integer attributes
		Cell cellInteger = extractCell(charger, wb.getSheetAt(0), 28);
		assertEquals(charger.getIntegerAttribute1(), Integer.valueOf((int) cellInteger.getNumericCellValue()));
		// FIXME mask doesn't work correctly
		assertEquals(parserNumeric(charger.getIntegerAttribute1(),"0.0"), cellInteger.getNumericCellValue());
	}

	/**
	 * 
	 * Test the marshal/unmarshal of one object applying mask at the Boolean
	 * attribute with {@link XlsElement}
	 */
	@Test(dataProvider = "dataProvider")
	public void checkIntegerTransformMask(ObjectMask charger, Workbook wb) throws Exception {
		// transform integer attributes
		Cell cellInteger = extractCell(charger, wb.getSheetAt(0), 29);
		assertEquals(charger.getIntAttribute2(), (int) cellInteger.getNumericCellValue());
		// FIXME mask doesn't work correctly
		assertEquals(parserNumeric(charger.getIntAttribute2(),"0.0"), cellInteger.getNumericCellValue());
	}

	/**
	 * 
	 * Test the marshal/unmarshal of one object applying mask at the Boolean
	 * attribute with {@link XlsElement}
	 */
	@Test(dataProvider = "dataProvider")
	public void checkShortFormatMask(ObjectMask charger, Workbook wb) throws Exception {
		// transform short attributes
		Cell cellShort = extractCell(charger, wb.getSheetAt(0), 30);
		assertEquals(charger.getShortAttribute1(), (short) cellShort.getNumericCellValue());
		// FIXME mask doesn't work correctly
		assertEquals(parserNumeric(charger.getShortAttribute1(),"0.0000"), cellShort.getNumericCellValue());
	}

	/**
	 * 
	 * Test the marshal/unmarshal of one object applying mask at the Boolean
	 * attribute with {@link XlsElement}
	 */
	@Test(dataProvider = "dataProvider")
	public void checkShortTransformMask(ObjectMask charger, Workbook wb) throws Exception {
		// transform short attributes
		Cell cellShort = extractCell(charger, wb.getSheetAt(0), 31);
		assertEquals(charger.getShortAttribute2(), Short.valueOf((short) cellShort.getNumericCellValue()));
		// FIXME mask doesn't work correctly
		assertEquals(parserNumeric(charger.getShortAttribute2(),"0.0"), cellShort.getNumericCellValue());
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
	
	private double parserNumeric(double value, String pattern){
		NumberFormat formatter = new DecimalFormat("#"+pattern);     
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
