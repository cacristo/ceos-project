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

import org.junit.Test;

import net.ceos.project.poi.annotated.bean.BasicObject;
import net.ceos.project.poi.annotated.bean.BasicObjectBuilder;
import net.ceos.project.poi.annotated.bean.factory.AvengersFactory;
import net.ceos.project.poi.annotated.bean.factory.AvengersFactory.SpiderWoman;
import net.ceos.project.poi.annotated.bean.factory.AvengersFactory.Thor;
import net.ceos.project.poi.annotated.core.CellDecorator;
import net.ceos.project.poi.annotated.core.Engine;
import net.ceos.project.poi.annotated.core.IEngine;
import net.ceos.project.poi.annotated.core.TestUtils;
import net.ceos.project.poi.annotated.core.XConfigCriteria;

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
		Thor missingConfig = AvengersFactory.instanceThor();

		IEngine en = new Engine();
		en.marshalAndSave(missingConfig, TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test a configuration exception, at unmarshal mode, with missing
	 * XlsConfiguration definitions
	 */
	@Test(expected = ConfigurationException.class)
	public void testUnmarshalMissingXlsConfigurationException() throws Exception {
		Thor missingConfig = AvengersFactory.instanceThor();

		IEngine en = new Engine();
		en.unmarshalFromPath(missingConfig, TestUtils.WORKING_DIR_GENERATED_II);
	}

	/**
	 * Test a configuration exception, at marshal mode, with missing XlsSheet
	 * definitions
	 */
	@Test(expected = ConfigurationException.class)
	public void testMarshalMissingXlsSheetException() throws Exception {
		SpiderWoman missingConfig = AvengersFactory.instanceSpiderWoman();

		IEngine en = new Engine();
		en.marshalAndSave(missingConfig, TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test a configuration exception, at unmarshal mode, with missing XlsSheet
	 * definitions
	 */
	@Test(expected = ConfigurationException.class)
	public void testUnmarshalMissingXlsSheetException() throws Exception {
		SpiderWoman missingConfig = AvengersFactory.instanceSpiderWoman();

		IEngine en = new Engine();
		en.unmarshalFromPath(missingConfig, TestUtils.WORKING_DIR_GENERATED_I);
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
