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
package net.ceos.project.poi.annotated.exception;

import org.testng.annotations.Test;

import net.ceos.project.poi.annotated.bean.ObjectTypeFactory;
import net.ceos.project.poi.annotated.bean.ObjectTypeFactory.BigDecimalDeclaredAttribute;
import net.ceos.project.poi.annotated.bean.ObjectTypeFactory.BooleanDeclaredAttribute;
import net.ceos.project.poi.annotated.bean.ObjectTypeFactory.DateDeclaredAttribute;
import net.ceos.project.poi.annotated.bean.ObjectTypeFactory.DoubleDeclaredAttribute;
import net.ceos.project.poi.annotated.bean.ObjectTypeFactory.IntegerDeclaredAttribute;
import net.ceos.project.poi.annotated.bean.ObjectTypeFactory.LocalDateDeclaredAttribute;
import net.ceos.project.poi.annotated.bean.ObjectTypeFactory.LocalDateTimeDeclaredAttribute;
import net.ceos.project.poi.annotated.bean.ObjectTypeFactory.LongDeclaredAttribute;
import net.ceos.project.poi.annotated.bean.ObjectTypeFactory.ShortDeclaredAttribute;
import net.ceos.project.poi.annotated.bean.ObjectTypeFactory.StringDeclaredAttribute;
import net.ceos.project.poi.annotated.core.Engine;
import net.ceos.project.poi.annotated.core.IEngine;
import net.ceos.project.poi.annotated.core.TestUtils;

/**
 * Test the {@link ConverterException}
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public class ConverterExceptionTest {

	/**
	 * Test a configuration exception, at marshal mode, with missing
	 * XlsConfiguration definitions
	 */
	@Test(enabled = false, expectedExceptions = ConverterException.class, expectedExceptionsMessageRegExp = "Problem while convert the date element.")
	public void marshalMissingStringValue() throws Exception {
		StringDeclaredAttribute stringNull = ObjectTypeFactory.instanceString();

		IEngine en = new Engine();
		en.marshalAndSave(stringNull, TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test a configuration exception, at marshal mode, with missing
	 * XlsConfiguration definitions
	 */
	@Test(enabled = false, expectedExceptions = ConverterException.class, expectedExceptionsMessageRegExp = "Problem while convert the date element.")
	public void marshalMissingDateValue() throws Exception {
		DateDeclaredAttribute dateNull = ObjectTypeFactory.instanceDate();

		IEngine en = new Engine();
		en.marshalAndSave(dateNull, TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test a configuration exception, at marshal mode, with missing
	 * XlsConfiguration definitions
	 */
	@Test(enabled = false, expectedExceptions = ConverterException.class, expectedExceptionsMessageRegExp = "Problem while convert the date element.")
	public void marshalMissingLocalDateValue() throws Exception {
		LocalDateDeclaredAttribute dateNull = ObjectTypeFactory.instanceLocalDate();

		IEngine en = new Engine();
		en.marshalAndSave(dateNull, TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test a configuration exception, at marshal mode, with missing
	 * XlsConfiguration definitions
	 */
	@Test(enabled = false, expectedExceptions = ConverterException.class, expectedExceptionsMessageRegExp = "Problem while convert the date element.")
	public void marshalMissingLocalDateTimeValue() throws Exception {
		LocalDateTimeDeclaredAttribute dateNull = ObjectTypeFactory.instanceLocalDateTime();

		IEngine en = new Engine();
		en.marshalAndSave(dateNull, TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test a configuration exception, at marshal mode, with missing
	 * XlsConfiguration definitions
	 */
	@Test(enabled = false, expectedExceptions = ConverterException.class, expectedExceptionsMessageRegExp = "Problem while convert the integer element.")
	public void marshalMissingShortValue() throws Exception {
		ShortDeclaredAttribute shortNull = ObjectTypeFactory.instanceShort();

		IEngine en = new Engine();
		en.marshalAndSave(shortNull, TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test a configuration exception, at marshal mode, with missing
	 * XlsConfiguration definitions
	 */
	@Test(enabled = false, expectedExceptions = ConverterException.class, expectedExceptionsMessageRegExp = "Problem while convert the integer element.")
	public void marshalMissingIntegerValue() throws Exception {
		IntegerDeclaredAttribute integerNull = ObjectTypeFactory.instanceInteger();

		IEngine en = new Engine();
		en.marshalAndSave(integerNull, TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test a configuration exception, at marshal mode, with missing
	 * XlsConfiguration definitions
	 */
	@Test(enabled = false, expectedExceptions = ConverterException.class, expectedExceptionsMessageRegExp = "Problem while convert the long element.")
	public void marshalMissingLongValue() throws Exception {
		LongDeclaredAttribute longNull = ObjectTypeFactory.instanceLong();

		IEngine en = new Engine();
		en.marshalAndSave(longNull, TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test a configuration exception, at marshal mode, with missing
	 * XlsConfiguration definitions
	 */
	@Test(enabled = false, expectedExceptions = ConverterException.class, expectedExceptionsMessageRegExp = "Problem while convert the double element.")
	public void marshalMissingDoubleValue() throws Exception {
		DoubleDeclaredAttribute doubleNull = ObjectTypeFactory.instanceDouble();

		IEngine en = new Engine();
		en.marshalAndSave(doubleNull, TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test a configuration exception, at marshal mode, with missing
	 * XlsConfiguration definitions
	 */
	@Test(enabled = false, expectedExceptions = ConverterException.class, expectedExceptionsMessageRegExp = "Problem while convert the BigDecimal element.")
	public void marshalMissingBigDecimalValue() throws Exception {
		BigDecimalDeclaredAttribute bigDecimalNull = ObjectTypeFactory.instanceBigDecimal();

		IEngine en = new Engine();
		en.marshalAndSave(bigDecimalNull, TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test a configuration exception, at marshal mode, with missing
	 * XlsConfiguration definitions
	 */
	@Test(enabled = false, expectedExceptions = ConverterException.class, expectedExceptionsMessageRegExp = "Problem while convert the boolean element.")
	public void marshalMissingBooleanValue() throws Exception {
		BooleanDeclaredAttribute booleanNull = ObjectTypeFactory.instanceBoolean();

		IEngine en = new Engine();
		en.marshalAndSave(booleanNull, TestUtils.WORKING_DIR_GENERATED_I);
	}

}
