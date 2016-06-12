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

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import net.ceos.project.poi.annotated.bean.BasicObject;
import net.ceos.project.poi.annotated.bean.BasicObjectBuilder;
import net.ceos.project.poi.annotated.bean.factory.AvengersFactory;
import net.ceos.project.poi.annotated.bean.factory.AvengersFactory.SpiderWoman;
import net.ceos.project.poi.annotated.bean.factory.AvengersFactory.Thor;
import net.ceos.project.poi.annotated.bean.factory.FantasticFourDecoratorFactory;
import net.ceos.project.poi.annotated.bean.factory.MarvelBadGuysFactory;
import net.ceos.project.poi.annotated.core.CellDecorator;
import net.ceos.project.poi.annotated.core.Engine;
import net.ceos.project.poi.annotated.core.IEngine;
import net.ceos.project.poi.annotated.core.TestUtils;
import net.ceos.project.poi.annotated.core.XConfigCriteria;
import net.ceos.project.poi.annotated.definition.PropagationType;

/**
 * Test the {@link ConfigurationException}
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public class ConfigurationExceptionTest {

	@DataProvider
	public Object[][] configCriteriaProvider() {

		XConfigCriteria header = new XConfigCriteria();
		header.overrideHeaderCellDecorator(null);

		XConfigCriteria generic = new XConfigCriteria();
		generic.overrideGenericCellDecorator(null);

		XConfigCriteria numeric = new XConfigCriteria();
		numeric.overrideNumericCellDecorator(null);

		XConfigCriteria bool = new XConfigCriteria();
		bool.overrideBooleanCellDecorator(null);

		XConfigCriteria date = new XConfigCriteria();
		date.overrideDateCellDecorator(null);

		XConfigCriteria enumeration = new XConfigCriteria();
		enumeration.overrideEnumCellDecorator(null);

		return new Object[][] {
				/* ConfigCriteria override header CellStyle */
				{ header },
				/* ConfigCriteria override generic CellStyle */
				{ generic },
				/* ConfigCriteria override numeric CellStyle */
				{ numeric },
				/* ConfigCriteria override boolean CellStyle */
				{ bool },
				/* ConfigCriteria override date CellStyle */
				{ date },
				/* ConfigCriteria override enumeration CellStyle */
				{ enumeration } };
	}

	@DataProvider
	public Object[][] xlsConflictNestedHeaderProvider() {
		return new Object[][] {
				/*
				 * Conflict caused by the PropagationType HORIZONTAL and XlsNestedHeader
				 * orientation
				 */
				{ MarvelBadGuysFactory.instanceUltron() },
				/*
				 * Conflict caused by the PropagationType VERTICAL and XlsNestedHeader
				 * orientation
				 */
				{ MarvelBadGuysFactory.instanceGreenGoblin() } };
	}

	/**
	 * Test a configuration exception, at marshal mode, with missing
	 * XlsConfiguration definitions
	 */
	@Test(expectedExceptions = ConfigurationException.class, expectedExceptionsMessageRegExp = "The annotation XlsConfiguration is missing. Review your configuration.")
	public void marshalMissingXlsConfigurationException() throws Exception {
		Thor missingConfig = AvengersFactory.instanceThor();

		IEngine en = new Engine();
		en.marshalAndSave(missingConfig, TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test a configuration exception, at unmarshal mode, with missing
	 * XlsConfiguration definitions
	 */
	@Test(expectedExceptions = ConfigurationException.class, expectedExceptionsMessageRegExp = "The annotation XlsConfiguration is missing. Review your configuration.")
	public void unmarshalMissingXlsConfigurationException() throws Exception {
		Thor missingConfig = AvengersFactory.instanceThor();

		IEngine en = new Engine();
		en.unmarshalFromPath(missingConfig, TestUtils.WORKING_DIR_GENERATED_II);
	}

	/**
	 * Test a configuration exception, at marshal mode, with missing XlsSheet
	 * definitions
	 */
	@Test(expectedExceptions = ConfigurationException.class, expectedExceptionsMessageRegExp = "The annotation XlsSheet is missing. Review your configuration.")
	public void marshalMissingXlsSheetException() throws Exception {
		SpiderWoman missingConfig = AvengersFactory.instanceSpiderWoman();

		IEngine en = new Engine();
		en.marshalAndSave(missingConfig, TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test a configuration exception, at unmarshal mode, with missing XlsSheet
	 * definitions
	 */
	@Test(expectedExceptions = ConfigurationException.class, expectedExceptionsMessageRegExp = "The annotation XlsSheet is missing. Review your configuration.")
	public void unmarshalMissingXlsSheetException() throws Exception {
		SpiderWoman missingConfig = AvengersFactory.instanceSpiderWoman();

		IEngine en = new Engine();
		en.unmarshalFromPath(missingConfig, TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test a missing configuration exception at override the header, numeric,
	 * boolean or date {@link CellDecorator}
	 */
	@Test(dataProvider = "configCriteriaProvider", expectedExceptions = ConfigurationException.class, expectedExceptionsMessageRegExp = "Cell style configuration is missing. Review your configuration.")
	public void validateOverrideMissingException(XConfigCriteria configCriteria) throws Exception {
		BasicObject missingConfig = BasicObjectBuilder.buildBasicObject();

		IEngine en = new Engine();
		en.marshalAndSave(configCriteria, missingConfig, TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test a missing configuration exception at override the header, numeric,
	 * boolean or date {@link CellDecorator}
	 */
	@Test(expectedExceptions = ConfigurationException.class, expectedExceptionsMessageRegExp = "Cell style configuration is duplicated. Review your configuration.")
	public void validateDuplicateDecoratorException() throws Exception {
		IEngine en = new Engine();
		en.marshalAndSave(FantasticFourDecoratorFactory.instanceDuplicate(), TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test a configuration conflict caused by the {@link PropagationType} and
	 * {@link XlsNestedheader}
	 */
	@Test(dataProvider = "xlsConflictNestedHeaderProvider", expectedExceptions = ConfigurationException.class, expectedExceptionsMessageRegExp = "Conflict caused by the PropagationType and XlsNestedHeader orientation. Review your configuration.")
	public void configurationConflictByPropagationNestedHeaderException(Object incompatibleConfig) throws Exception {
		IEngine en = new Engine();
		en.marshalAndSave(incompatibleConfig, TestUtils.WORKING_DIR_GENERATED_I);
	}

}
