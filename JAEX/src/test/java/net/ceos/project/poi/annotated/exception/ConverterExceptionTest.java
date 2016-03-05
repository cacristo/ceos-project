package net.ceos.project.poi.annotated.exception;

import org.testng.annotations.Test;

import net.ceos.project.poi.annotated.bean.ObjectTypeBuilder;
import net.ceos.project.poi.annotated.core.Engine;
import net.ceos.project.poi.annotated.core.IEngine;
import net.ceos.project.poi.annotated.core.TestUtils;

public class ConverterExceptionTest {

	/**
	 * Test a configuration exception, at marshal mode, with missing
	 * XlsConfiguration definitions
	 */
	@Test(expectedExceptions = ConverterException.class, expectedExceptionsMessageRegExp = "Problem while convert the date element.")
	public void testMarshalMissingStringValue() throws Exception {
		ObjectTypeBuilder.StringDeclaredAttribute stringNull = ObjectTypeBuilder.instanceString();

		IEngine en = new Engine();
		en.marshalAndSave(stringNull, TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test a configuration exception, at marshal mode, with missing
	 * XlsConfiguration definitions
	 */
	@Test(expectedExceptions = ConverterException.class, expectedExceptionsMessageRegExp = "Problem while convert the date element.")
	public void testMarshalMissingDateValue() throws Exception {
		ObjectTypeBuilder.DateDeclaredAttribute dateNull = ObjectTypeBuilder.instanceDate();

		IEngine en = new Engine();
		en.marshalAndSave(dateNull, TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test a configuration exception, at marshal mode, with missing
	 * XlsConfiguration definitions
	 */
	@Test(expectedExceptions = ConverterException.class, expectedExceptionsMessageRegExp = "Problem while convert the integer element.")
	public void testMarshalMissingIntegerValue() throws Exception {
		ObjectTypeBuilder.IntegerDeclaredAttribute integerNull = ObjectTypeBuilder.instanceInteger();

		IEngine en = new Engine();
		en.marshalAndSave(integerNull, TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test a configuration exception, at marshal mode, with missing
	 * XlsConfiguration definitions
	 */
	@Test(expectedExceptions = ConverterException.class, expectedExceptionsMessageRegExp = "Problem while convert the long element.")
	public void testMarshalMissingLongValue() throws Exception {
		ObjectTypeBuilder.LongDeclaredAttribute longNull = ObjectTypeBuilder.instanceLong();

		IEngine en = new Engine();
		en.marshalAndSave(longNull, TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test a configuration exception, at marshal mode, with missing
	 * XlsConfiguration definitions
	 */
	@Test(expectedExceptions = ConverterException.class, expectedExceptionsMessageRegExp = "Problem while convert the double element.")
	public void testMarshalMissingDoubleValue() throws Exception {
		ObjectTypeBuilder.DoubleDeclaredAttribute doubleNull = ObjectTypeBuilder.instanceDouble();

		IEngine en = new Engine();
		en.marshalAndSave(doubleNull, TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test a configuration exception, at marshal mode, with missing
	 * XlsConfiguration definitions
	 */
	@Test(expectedExceptions = ConverterException.class, expectedExceptionsMessageRegExp = "Problem while convert the BigDecimal element.")
	public void testMarshalMissingBigDecimalValue() throws Exception {
		ObjectTypeBuilder.BigDecimalDeclaredAttribute bigDecimalNull = ObjectTypeBuilder.instanceBigDecimal();

		IEngine en = new Engine();
		en.marshalAndSave(bigDecimalNull, TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test a configuration exception, at marshal mode, with missing
	 * XlsConfiguration definitions
	 */
	@Test(expectedExceptions = ConverterException.class, expectedExceptionsMessageRegExp = "Problem while convert the boolean element.")
	public void testMarshalMissingBooleanValue() throws Exception {
		ObjectTypeBuilder.BooleanDeclaredAttribute booleanNull = ObjectTypeBuilder.instanceBoolean();

		IEngine en = new Engine();
		en.marshalAndSave(booleanNull, TestUtils.WORKING_DIR_GENERATED_I);
	}

}
