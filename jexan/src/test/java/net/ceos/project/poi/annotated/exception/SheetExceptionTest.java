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

import net.ceos.project.poi.annotated.bean.AvengersFactory;
import net.ceos.project.poi.annotated.bean.AvengersFactory.Vision;
import net.ceos.project.poi.annotated.core.Engine;
import net.ceos.project.poi.annotated.core.IEngine;
import net.ceos.project.poi.annotated.core.TestUtils;

/**
 * Test the {@link SheetException}
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public class SheetExceptionTest {


	/**
	 * Test a horizontal configuration exception conflict
	 */
	@Test(expected = SheetException.class)
	public void testXlsConflictXlsNestedHeaderHorizIncompatible() throws Exception {
		IEngine en = new Engine();
		en.marshalAndSave(AvengersFactory.instanceQuicksilver(), TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test a horizontal configuration exception conflict
	 */
	@Test(expected = SheetException.class)
	public void testXlsConflictXlsNestedHeaderVertiIncompatible() throws Exception {
		IEngine en = new Engine();
		en.marshalAndSave(AvengersFactory.instanceScarletWitch(), TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test a sheet exception because an empty title sheet
	 */
	@Test(expected = SheetException.class)
	public void emptyTitleSheetMarshalSheetException() throws Exception {
		Vision emptyTitle = AvengersFactory.instanceVision();

		IEngine en = new Engine();
		en.marshalAndSave(emptyTitle, TestUtils.WORKING_DIR_GENERATED_II);
	}

	/**
	 * Test a sheet exception because an empty title sheet
	 */
	@Test(expected = SheetException.class)
	public void emptyTitleSheetUnmarshalSheetException() throws Exception {
		Vision emptyTitle = AvengersFactory.instanceVision();

		IEngine en = new Engine();
		en.unmarshalFromPath(emptyTitle, TestUtils.WORKING_DIR_GENERATED_II);
	}
}
