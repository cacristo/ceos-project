package net.ceos.project.poi.annotated.exception;

import org.testng.annotations.DataProvider;
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

	@DataProvider
	public Object[][] xlsConflictConfigurationProvider() {
		return new Object[][] { 
			{ new XlsNestedHeaderHorizIncompatible() },
			{ new XlsNestedHeaderVertiIncompatible() } };
	}
	
	@DataProvider
	public Object[][] configCriteriaProvider() {
		
		ConfigCriteria header = new ConfigCriteria();
		header.overrideHeaderCellDecorator(null);

		ConfigCriteria numeric = new ConfigCriteria();
		numeric.overrideNumericCellDecorator(null);
		

		ConfigCriteria bool = new ConfigCriteria();
		bool.overrideBooleanCellDecorator(null);

		ConfigCriteria date = new ConfigCriteria();
		date.overrideDateCellDecorator(null);

		return new Object[][] { 
			{ header }, 
			{ numeric }, 
			{ bool }, 
			{ date } };
	}

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
	@Test(dataProvider = "xlsConflictConfigurationProvider", expectedExceptions = ConfigurationException.class, expectedExceptionsMessageRegExp = "Conflict at the configuration. Review your configuration.")
	public void testXlsConflictConfigurationException(Object incompatibleConfig) throws Exception {
		IEngine en = new Engine();
		en.marshalAndSave(incompatibleConfig, TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test a missing configuration exception at override the header, numeric, boolean or date
	 * {@link CellDecorator}
	 */
	@Test(dataProvider="configCriteriaProvider", expectedExceptions = ConfigurationException.class, expectedExceptionsMessageRegExp = "Cell style configuration is missing. Review your configuration.")
	public void testOverrideMissingException(ConfigCriteria configCriteria) throws Exception {
		BasicObject missingConfig = BasicObjectBuilder.buildBasicObject();

		IEngine en = new Engine();
		en.marshalAndSave(configCriteria, missingConfig, TestUtils.WORKING_DIR_GENERATED_I);
	}

}
