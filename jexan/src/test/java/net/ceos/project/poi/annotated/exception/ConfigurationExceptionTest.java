package net.ceos.project.poi.annotated.exception;

import org.junit.Test;

import net.ceos.project.poi.annotated.bean.BasicObject;
import net.ceos.project.poi.annotated.bean.BasicObjectBuilder;
import net.ceos.project.poi.annotated.bean.XlsConfigurationAbsent;
import net.ceos.project.poi.annotated.bean.XlsNestedHeaderHorizIncompatible;
import net.ceos.project.poi.annotated.bean.XlsNestedHeaderVertiIncompatible;
import net.ceos.project.poi.annotated.bean.XlsSheetAbsent;
import net.ceos.project.poi.annotated.core.CellDecorator;
import net.ceos.project.poi.annotated.core.XConfigCriteria;
import net.ceos.project.poi.annotated.core.Engine;
import net.ceos.project.poi.annotated.core.IEngine;
import net.ceos.project.poi.annotated.core.TestUtils;

/**
 * Test the {@link ConfigurationException}
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public class ConfigurationExceptionTest {

	/**
	 * Test a configuration exception, at marshal mode, with missing
	 * XlsConfiguration definitions
	 */
	@Test(expected = ConfigurationException.class)
	public void testMarshalMissingXlsConfigurationException() throws Exception {
		XlsConfigurationAbsent missingConfig = new XlsConfigurationAbsent();

		IEngine en = new Engine();
		en.marshalAndSave(missingConfig, TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test a configuration exception, at unmarshal mode, with missing
	 * XlsConfiguration definitions
	 */
	@Test(expected = ConfigurationException.class)
	public void testUnmarshalMissingXlsConfigurationException() throws Exception {
		XlsConfigurationAbsent missingConfig = new XlsConfigurationAbsent();

		IEngine en = new Engine();
		en.unmarshalFromPath(missingConfig, TestUtils.WORKING_DIR_GENERATED_II);
	}

	/**
	 * Test a configuration exception, at marshal mode, with missing XlsSheet
	 * definitions
	 */
	@Test(expected = ConfigurationException.class)
	public void testMarshalMissingXlsSheetException() throws Exception {
		XlsSheetAbsent missingConfig = new XlsSheetAbsent();

		IEngine en = new Engine();
		en.marshalAndSave(missingConfig, TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test a configuration exception, at unmarshal mode, with missing XlsSheet
	 * definitions
	 */
	@Test(expected = ConfigurationException.class)
	public void testUnmarshalMissingXlsSheetException() throws Exception {
		XlsSheetAbsent missingConfig = new XlsSheetAbsent();

		IEngine en = new Engine();
		en.unmarshalFromPath(missingConfig, TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test a horizontal configuration exception conflict
	 */
	@Test(expected = ConfigurationException.class)
	public void testXlsConflictXlsNestedHeaderHorizIncompatible() throws Exception {
		IEngine en = new Engine();
		en.marshalAndSave(new XlsNestedHeaderHorizIncompatible(), TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test a horizontal configuration exception conflict
	 */
	@Test(expected = ConfigurationException.class)
	public void testXlsConflictXlsNestedHeaderVertiIncompatible() throws Exception {
		IEngine en = new Engine();
		en.marshalAndSave(new XlsNestedHeaderVertiIncompatible(), TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test a missing configuration exception at override the header
	 * {@link CellDecorator}
	 */
	@Test(expected = ConfigurationException.class)
	public void testOverrideMissingExceptionHeader() throws Exception {
		BasicObject missingConfig = BasicObjectBuilder.buildBasicObject();

		XConfigCriteria header = new XConfigCriteria();
		header.overrideHeaderCellDecorator(null);

		IEngine en = new Engine();
		en.marshalAndSave(header, missingConfig, TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test a missing configuration exception at override the numeric
	 * {@link CellDecorator}
	 */
	@Test(expected = ConfigurationException.class)
	public void testOverrideMissingExceptionNumeric() throws Exception {
		BasicObject missingConfig = BasicObjectBuilder.buildBasicObject();

		XConfigCriteria numeric = new XConfigCriteria();
		numeric.overrideNumericCellDecorator(null);

		IEngine en = new Engine();
		en.marshalAndSave(numeric, missingConfig, TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test a missing configuration exception at override the boolean
	 * {@link CellDecorator}
	 */
	@Test(expected = ConfigurationException.class)
	public void testOverrideMissingExceptionBoolean() throws Exception {
		BasicObject missingConfig = BasicObjectBuilder.buildBasicObject();

		XConfigCriteria bool = new XConfigCriteria();
		bool.overrideBooleanCellDecorator(null);

		IEngine en = new Engine();
		en.marshalAndSave(bool, missingConfig, TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test a missing configuration exception at override the date
	 * {@link CellDecorator}
	 */
	@Test(expected = ConfigurationException.class)
	public void testOverrideMissingExceptionDate() throws Exception {
		BasicObject missingConfig = BasicObjectBuilder.buildBasicObject();

		XConfigCriteria date = new XConfigCriteria();
		date.overrideDateCellDecorator(null);

		IEngine en = new Engine();
		en.marshalAndSave(date, missingConfig, TestUtils.WORKING_DIR_GENERATED_I);
	}

}
