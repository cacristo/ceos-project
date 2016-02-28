package net.ceos.project.poi.annotated.exception;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
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
import net.ceos.project.poi.annotated.definition.ExceptionMessage;
import net.ceos.project.poi.annotated.exception.ConfigurationException;

public class ConfigurationExceptionTest extends TestCase {
	private Class<?> c = null;
	private String message = "";

	private void reset() {
		this.c = null;
		this.message = "";
	}

	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public ConfigurationExceptionTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(ConfigurationExceptionTest.class);
	}

	@Override
	protected void setUp() throws Exception {
		reset();
		super.setUp();
	}
	
	/**
	 * Test a configuration exception, at marshal mode, with missing
	 * XlsConfiguration definitions
	 */
	public void testMarshalMissingXlsConfigurationException() throws Exception {
		XlsConfigurationAbsent missingConfig = new XlsConfigurationAbsent();

		try {
			IEngine en = new Engine();
			en.marshalAndSave(missingConfig, TestUtils.WORKING_DIR_GENERATED_I);
		} catch (Exception e) {
			this.c = e.getClass();
			this.message = e.getMessage();
		}
		assertEquals(ConfigurationException.class, this.c);
		assertEquals(ExceptionMessage.ConfigurationException_XlsConfigurationMissing.getMessage(), this.message);
	}

	/**
	 * Test a configuration exception, at unmarshal mode, with missing
	 * XlsConfiguration definitions
	 */
	public void testUnmarshalMissingXlsConfigurationException() throws Exception {
		XlsConfigurationAbsent missingConfig = new XlsConfigurationAbsent();

		try {
			IEngine en = new Engine();
			en.unmarshalFromPath(missingConfig, TestUtils.WORKING_DIR_GENERATED_II);
		} catch (Exception e) {
			this.c = e.getClass();
			this.message = e.getMessage();
		}
		assertEquals(ConfigurationException.class, this.c);
		assertEquals(ExceptionMessage.ConfigurationException_XlsConfigurationMissing.getMessage(), this.message);
	}

	/**
	 * Test a configuration exception, at marshal mode, with missing XlsSheet
	 * definitions
	 */
	public void testMarshalMissingXlsSheetException() throws Exception {
		XlsSheetAbsent missingConfig = new XlsSheetAbsent();

		try {
			IEngine en = new Engine();
			en.marshalAndSave(missingConfig, TestUtils.WORKING_DIR_GENERATED_I);
		} catch (Exception e) {
			this.c = e.getClass();
			this.message = e.getMessage();
		}
		assertEquals(ConfigurationException.class, this.c);
		assertEquals(ExceptionMessage.ConfigurationException_XlsSheetMissing.getMessage(), this.message);
	}

	/**
	 * Test a configuration exception, at unmarshal mode, with missing XlsSheet
	 * definitions
	 */
	public void testUnmarshalMissingXlsSheetException() throws Exception {
		XlsSheetAbsent missingConfig = new XlsSheetAbsent();

		try {
			IEngine en = new Engine();
			en.unmarshalFromPath(missingConfig, TestUtils.WORKING_DIR_GENERATED_I);
		} catch (Exception e) {
			this.c = e.getClass();
			this.message = e.getMessage();
		}
		assertEquals(ConfigurationException.class, this.c);
		assertEquals(ExceptionMessage.ConfigurationException_XlsSheetMissing.getMessage(), this.message);
	}

	/**
	 * Test a horizontal configuration exception conflict
	 */
	public void testHorizontalConflictConfigurationException() {
		XlsNestedHeaderHorizIncompatible incompatibleConfig = new XlsNestedHeaderHorizIncompatible();

		try {
			IEngine en = new Engine();
			en.marshalAndSave(incompatibleConfig, TestUtils.WORKING_DIR_GENERATED_I);
		} catch (Exception e) {
			this.c = e.getClass();
			this.message = e.getMessage();
		}
		assertEquals(ConfigurationException.class, this.c);
		assertEquals(ExceptionMessage.ConfigurationException_Conflict.getMessage(), this.message);
	}

	/**
	 * Test a vertical configuration exception conflict
	 */
	public void testVerticalConflictConfigurationException() {
		XlsNestedHeaderVertiIncompatible incompatibleConfig = new XlsNestedHeaderVertiIncompatible();

		try {
			IEngine en = new Engine();
			en.marshalAndSave(incompatibleConfig, TestUtils.WORKING_DIR_GENERATED_I);
		} catch (Exception e) {
			this.c = e.getClass();
			this.message = e.getMessage();
		}
		assertEquals(ConfigurationException.class, this.c);
		assertEquals(ExceptionMessage.ConfigurationException_Conflict.getMessage(), this.message);
	}

	/**
	 * Test a missing configuration exception at override the header
	 * {@link CellDecorator}
	 */
	public void testOverrideHeaderMissingException() {
		BasicObject missingConfig = BasicObjectBuilder.buildBasicObject();

		IEngine en = new Engine();
		ConfigCriteria cc = new ConfigCriteria();

		CellDecorator decorator = null;
		cc.overrideHeaderCellDecorator(decorator);

		try {
			en.marshalAndSave(cc, missingConfig, TestUtils.WORKING_DIR_GENERATED_I);
		} catch (Exception e) {
			this.c = e.getClass();
			this.message = e.getMessage();
		}
		assertEquals(ConfigurationException.class, this.c);
		assertEquals(ExceptionMessage.ConfigurationException_CellStyleMissing.getMessage(), this.message);
	}

	/**
	 * Test a missing configuration exception at override the numeric
	 * {@link CellDecorator}
	 */
	public void testOverrideNumericMissingException() {
		BasicObject missingConfig = BasicObjectBuilder.buildBasicObject();

		IEngine en = new Engine();
		ConfigCriteria cc = new ConfigCriteria();

		CellDecorator decorator = null;
		cc.overrideNumericCellDecorator(decorator);

		try {
			en.marshalAndSave(cc, missingConfig, TestUtils.WORKING_DIR_GENERATED_I);
		} catch (Exception e) {
			this.c = e.getClass();
			this.message = e.getMessage();
		}
		assertEquals(ConfigurationException.class, this.c);
		assertEquals(ExceptionMessage.ConfigurationException_CellStyleMissing.getMessage(), this.message);
	}

	/**
	 * Test a missing configuration exception at override the boolean
	 * {@link CellDecorator}
	 */
	public void testOverrideBooleanMissingException() {
		BasicObject missingConfig = BasicObjectBuilder.buildBasicObject();

		IEngine en = new Engine();
		ConfigCriteria cc = new ConfigCriteria();

		CellDecorator decorator = null;
		cc.overrideBooleanCellDecorator(decorator);

		try {
			en.marshalAndSave(cc, missingConfig, TestUtils.WORKING_DIR_GENERATED_II);
		} catch (Exception e) {
			this.c = e.getClass();
			this.message = e.getMessage();
		}
		assertEquals(ConfigurationException.class, this.c);
		assertEquals(ExceptionMessage.ConfigurationException_CellStyleMissing.getMessage(), this.message);
	}

	/**
	 * Test a missing configuration exception at override the date
	 * {@link CellDecorator}
	 */
	public void testOverrideDateMissingException() {
		BasicObject missingConfig = BasicObjectBuilder.buildBasicObject();

		IEngine en = new Engine();
		ConfigCriteria cc = new ConfigCriteria();

		CellDecorator decorator = null;
		cc.overrideDateCellDecorator(decorator);

		try {
			en.marshalAndSave(cc, missingConfig, TestUtils.WORKING_DIR_GENERATED_I);
		} catch (Exception e) {
			this.c = e.getClass();
			this.message = e.getMessage();
		}
		assertEquals(ConfigurationException.class, this.c);
		assertEquals(ExceptionMessage.ConfigurationException_CellStyleMissing.getMessage(), this.message);
	}
}
