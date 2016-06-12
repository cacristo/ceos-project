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

import java.util.List;

import org.junit.Test;

import net.ceos.project.poi.annotated.bean.MultiTypeObject;
import net.ceos.project.poi.annotated.bean.MultiTypeObjectBuilder;
import net.ceos.project.poi.annotated.bean.factory.AvengersFactory;
import net.ceos.project.poi.annotated.bean.factory.AvengersFactory.Vision;
import net.ceos.project.poi.annotated.core.Engine;
import net.ceos.project.poi.annotated.core.IEngine;
import net.ceos.project.poi.annotated.core.TestUtils;
import net.ceos.project.poi.annotated.core.XConfigCriteria;
import net.ceos.project.poi.annotated.definition.ExtensionFileType;
import net.ceos.project.poi.annotated.definition.PropagationType;

/**
 * Test the {@link SheetException}
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public class SheetExceptionTest {

	/**
	 * Test a conflict between the PropagationType HORIZONTAL and the
	 * configuration of the XlsNestedHeader
	 */
	@Test(expected = SheetException.class)
	public void testXlsConflictXlsNestedHeaderHorizIncompatible() throws Exception {
		IEngine en = new Engine();
		en.marshalAndSave(AvengersFactory.instanceQuicksilver(), TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test a conflict between the PropagationType VERTICAL and the
	 * configuration of the XlsNestedHeader
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

	/**
	 * Test a sheet exception caused by invalid column index 256 when
	 * <ul>
	 * <li>PropagationType.PROPAGATION_VERTICAL
	 * <li>ExtensionFileType.XLS
	 * </ul>
	 */
	@Test(expected = SheetException.class)
	public void limitationSheetVerticalListXlsException() throws Exception {
		List<MultiTypeObject> so = MultiTypeObjectBuilder.buildListOfMultiTypeObject(260);

		XConfigCriteria configCriteria = new XConfigCriteria();
		configCriteria.overridePropagationType(PropagationType.PROPAGATION_VERTICAL);
		configCriteria.overrideExtensionType(ExtensionFileType.XLS);

		IEngine en = new Engine();
		en.marshalAsCollectionAndSave(configCriteria, so, TestUtils.WORKING_DIR_GENERATED_II);
	}

	/**
	 * Test a sheet exception caused by invalid column index 16384 when
	 * <ul>
	 * <li>PropagationType.PROPAGATION_VERTICAL
	 * <li>ExtensionFileType.XLSX
	 * </ul>
	 */
	@Test(expected = SheetException.class)
	public void limitationSheetVerticalListXlsxException() throws Exception {
		List<MultiTypeObject> so = MultiTypeObjectBuilder.buildListOfMultiTypeObject(16500);

		XConfigCriteria configCriteria = new XConfigCriteria();
		configCriteria.overridePropagationType(PropagationType.PROPAGATION_VERTICAL);
		configCriteria.overrideExtensionType(ExtensionFileType.XLSX);

		IEngine en = new Engine();
		en.marshalAsCollectionAndSave(configCriteria, so, TestUtils.WORKING_DIR_GENERATED_II);
	}
}
