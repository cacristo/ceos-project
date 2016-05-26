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

import net.ceos.project.poi.annotated.annotation.XlsNestedHeader;
import net.ceos.project.poi.annotated.bean.AvengersFactory;
import net.ceos.project.poi.annotated.bean.AvengersFactory.Vision;
import net.ceos.project.poi.annotated.core.Engine;
import net.ceos.project.poi.annotated.core.IEngine;
import net.ceos.project.poi.annotated.core.TestUtils;
import net.ceos.project.poi.annotated.definition.PropagationType;

/**
 * Test the {@link SheetException}
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public class SheetExceptionTest {

	@DataProvider
	public Object[][] xlsConflictConfigurationProvider() {
		return new Object[][] { { AvengersFactory.instanceQuicksilver() }, { AvengersFactory.instanceScarletWitch() } };
	}

	/**
	 * Test the conflict between the {@link PropagationType} and the
	 * configuration of the {@link XlsNestedHeader}
	 */
	@Test(dataProvider = "xlsConflictConfigurationProvider", expectedExceptions = SheetException.class, expectedExceptionsMessageRegExp = "Problem while creating the Sheet. Review your configuration.")
	public void conflictConfigurationSheetException(Object incompatibleConfig) throws Exception {
		IEngine en = new Engine();
		en.marshalAndSave(incompatibleConfig, TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test a sheet exception because an empty title sheet
	 */
	@Test(expectedExceptions = SheetException.class, expectedExceptionsMessageRegExp = "Problem while creating the Sheet. Review your configuration.")
	public void emptyTitleSheetMarshalSheetException() throws Exception {
		Vision emptyTitle = AvengersFactory.instanceVision();

		IEngine en = new Engine();
		en.marshalAndSave(emptyTitle, TestUtils.WORKING_DIR_GENERATED_II);
	}

	/**
	 * Test a sheet exception because an empty title sheet
	 */
	@Test(expectedExceptions = SheetException.class, expectedExceptionsMessageRegExp = "Problem while creating the Sheet. Review your configuration.")
	public void emptyTitleSheetUnmarshalSheetException() throws Exception {
		Vision emptyTitle = AvengersFactory.instanceVision();

		IEngine en = new Engine();
		en.unmarshalFromPath(emptyTitle, TestUtils.WORKING_DIR_GENERATED_II);
	}
}
