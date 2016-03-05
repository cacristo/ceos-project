package net.ceos.project.poi.annotated.exception;

import org.testng.annotations.Test;

import net.ceos.project.poi.annotated.bean.BasicObject;
import net.ceos.project.poi.annotated.bean.BasicObjectBuilder;
import net.ceos.project.poi.annotated.bean.XlsConfigurationAbsent;
import net.ceos.project.poi.annotated.bean.XlsNestedHeaderHorizIncompatible;
import net.ceos.project.poi.annotated.bean.XlsNestedHeaderVertiIncompatible;
import net.ceos.project.poi.annotated.bean.XlsSheetAbsent;
import net.ceos.project.poi.annotated.core.CellDecorator;
import net.ceos.project.poi.annotated.core.ConfigCriteria;
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
	@Test(expectedExceptions = ConfigurationException.class, expectedExceptionsMessageRegExp = "The annotation XlsConfiguration is missing. Review your configuration.")
	public void testMarshalMissingXlsConfigurationException() throws Exception {
		XlsConfigurationAbsent missingConfig = new XlsConfigurationAbsent();

		IEngine en = new Engine();
		en.marshalAndSave(missingConfig, TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test a configuration exception, at unmarshal mode, with missing
	 * XlsConfiguration definitions
	 */
	@Test(expectedExceptions = ConfigurationException.class, expectedExceptionsMessageRegExp = "The annotation XlsConfiguration is missing. Review your configuration.")
	public void testUnmarshalMissingXlsConfigurationException() throws Exception {
		XlsConfigurationAbsent missingConfig = new XlsConfigurationAbsent();

		IEngine en = new Engine();
		en.unmarshalFromPath(missingConfig, TestUtils.WORKING_DIR_GENERATED_II);
	}

	/**
	 * Test a configuration exception, at marshal mode, with missing XlsSheet
	 * definitions
	 */
	@Test(expectedExceptions = ConfigurationException.class, expectedExceptionsMessageRegExp = "The annotation XlsSheet is missing. Review your configuration.")
	public void testMarshalMissingXlsSheetException() throws Exception {
		XlsSheetAbsent missingConfig = new XlsSheetAbsent();

		IEngine en = new Engine();
		en.marshalAndSave(missingConfig, TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test a configuration exception, at unmarshal mode, with missing XlsSheet
	 * definitions
	 */
	@Test(expectedExceptions = ConfigurationException.class, expectedExceptionsMessageRegExp = "The annotation XlsSheet is missing. Review your configuration.")
	public void testUnmarshalMissingXlsSheetException() throws Exception {
		XlsSheetAbsent missingConfig = new XlsSheetAbsent();

		IEngine en = new Engine();
		en.unmarshalFromPath(missingConfig, TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test a horizontal configuration exception conflict
	 */
	@Test(expectedExceptions = ConfigurationException.class, expectedExceptionsMessageRegExp = "Conflict at the configuration. Review your configuration.")
	public void testHorizontalConflictConfigurationException() throws Exception {
		XlsNestedHeaderHorizIncompatible incompatibleConfig = new XlsNestedHeaderHorizIncompatible();

		IEngine en = new Engine();
		en.marshalAndSave(incompatibleConfig, TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test a vertical configuration exception conflict
	 */
	@Test(expectedExceptions = ConfigurationException.class, expectedExceptionsMessageRegExp = "Conflict at the configuration. Review your configuration.")
	public void testVerticalConflictConfigurationException() throws Exception {
		XlsNestedHeaderVertiIncompatible incompatibleConfig = new XlsNestedHeaderVertiIncompatible();

		IEngine en = new Engine();
		en.marshalAndSave(incompatibleConfig, TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test a missing configuration exception at override the header
	 * {@link CellDecorator}
	 */
	@Test(expectedExceptions = ConfigurationException.class, expectedExceptionsMessageRegExp = "Cell style configuration is missing. Review your configuration.")
	public void testOverrideHeaderMissingException() throws Exception {
		BasicObject missingConfig = BasicObjectBuilder.buildBasicObject();

		IEngine en = new Engine();
		ConfigCriteria cc = new ConfigCriteria();

		CellDecorator decorator = null;
		cc.overrideHeaderCellDecorator(decorator);

		en.marshalAndSave(cc, missingConfig, TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test a missing configuration exception at override the numeric
	 * {@link CellDecorator}
	 */
	@Test(expectedExceptions = ConfigurationException.class, expectedExceptionsMessageRegExp = "Cell style configuration is missing. Review your configuration.")
	public void testOverrideNumericMissingException() throws Exception {
		BasicObject missingConfig = BasicObjectBuilder.buildBasicObject();

		IEngine en = new Engine();
		ConfigCriteria cc = new ConfigCriteria();

		CellDecorator decorator = null;
		cc.overrideNumericCellDecorator(decorator);

		en.marshalAndSave(cc, missingConfig, TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test a missing configuration exception at override the boolean
	 * {@link CellDecorator}
	 */
	@Test(expectedExceptions = ConfigurationException.class, expectedExceptionsMessageRegExp = "Cell style configuration is missing. Review your configuration.")
	public void testOverrideBooleanMissingException() throws Exception {
		BasicObject missingConfig = BasicObjectBuilder.buildBasicObject();

		IEngine en = new Engine();
		ConfigCriteria cc = new ConfigCriteria();

		CellDecorator decorator = null;
		cc.overrideBooleanCellDecorator(decorator);

		en.marshalAndSave(cc, missingConfig, TestUtils.WORKING_DIR_GENERATED_II);
	}

	/**
	 * Test a missing configuration exception at override the date
	 * {@link CellDecorator}
	 */
	@Test(expectedExceptions = ConfigurationException.class, expectedExceptionsMessageRegExp = "Cell style configuration is missing. Review your configuration.")
	public void testOverrideDateMissingException() throws Exception {
		BasicObject missingConfig = BasicObjectBuilder.buildBasicObject();

		IEngine en = new Engine();
		ConfigCriteria cc = new ConfigCriteria();

		CellDecorator decorator = null;
		cc.overrideDateCellDecorator(decorator);

		en.marshalAndSave(cc, missingConfig, TestUtils.WORKING_DIR_GENERATED_I);
	}
}
