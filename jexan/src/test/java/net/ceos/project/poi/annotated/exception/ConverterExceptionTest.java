package net.ceos.project.poi.annotated.exception;

import org.junit.Test;

import net.ceos.project.poi.annotated.bean.ObjectTypeFactory;
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
	//@Test(expected = ConverterException.class)
	public void testMarshalMissingStringValue() throws Exception {
		ObjectTypeFactory.StringDeclaredAttribute stringNull = ObjectTypeFactory.instanceString();

		IEngine en = new Engine();
		en.marshalAndSave(stringNull, TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test a configuration exception, at marshal mode, with missing
	 * XlsConfiguration definitions
	 */
	//@Test(expected = ConverterException.class)
	public void testMarshalMissingDateValue() throws Exception {
		ObjectTypeFactory.DateDeclaredAttribute dateNull = ObjectTypeFactory.instanceDate();

		IEngine en = new Engine();
		en.marshalAndSave(dateNull, TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test a configuration exception, at marshal mode, with missing
	 * XlsConfiguration definitions
	 */
	@Test(expected = ConverterException.class)
	public void testMarshalMissingIntegerValue() throws Exception {
		ObjectTypeFactory.IntegerDeclaredAttribute integerNull = ObjectTypeFactory.instanceInteger();

		IEngine en = new Engine();
		en.marshalAndSave(integerNull, TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test a configuration exception, at marshal mode, with missing
	 * XlsConfiguration definitions
	 */
	@Test(expected = ConverterException.class)
	public void testMarshalMissingLongValue() throws Exception {
		ObjectTypeFactory.LongDeclaredAttribute longNull = ObjectTypeFactory.instanceLong();

		IEngine en = new Engine();
		en.marshalAndSave(longNull, TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test a configuration exception, at marshal mode, with missing
	 * XlsConfiguration definitions
	 */
	@Test(expected = ConverterException.class)
	public void testMarshalMissingDoubleValue() throws Exception {
		ObjectTypeFactory.DoubleDeclaredAttribute doubleNull = ObjectTypeFactory.instanceDouble();

		IEngine en = new Engine();
		en.marshalAndSave(doubleNull, TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test a configuration exception, at marshal mode, with missing
	 * XlsConfiguration definitions
	 */
	//@Test(expected = ConverterException.class)
	public void testMarshalMissingBigDecimalValue() throws Exception {
		ObjectTypeFactory.BigDecimalDeclaredAttribute bigDecimalNull = ObjectTypeFactory.instanceBigDecimal();

		IEngine en = new Engine();
		en.marshalAndSave(bigDecimalNull, TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test a configuration exception, at marshal mode, with missing
	 * XlsConfiguration definitions
	 */
	//@Test(expected = ConverterException.class)
	public void testMarshalMissingBooleanValue() throws Exception {
		ObjectTypeFactory.BooleanDeclaredAttribute booleanNull = ObjectTypeFactory.instanceBoolean();

		IEngine en = new Engine();
		en.marshalAndSave(booleanNull, TestUtils.WORKING_DIR_GENERATED_I);
	}

}
