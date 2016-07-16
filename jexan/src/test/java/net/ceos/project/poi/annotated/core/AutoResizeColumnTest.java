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
package net.ceos.project.poi.annotated.core;

import java.util.List;

import org.testng.annotations.Test;

import net.ceos.project.poi.annotated.bean.AutoResizeObject;
import net.ceos.project.poi.annotated.bean.AutoResizeObjectBuilder;

/**
 * Test the auto resize column.
 */
public class AutoResizeColumnTest {

	/**
	 * Test marshal object with auto resize defined by annotation
	 */
	@Test
	public void testAutoResizeByAnnotationMarshal() throws Exception {
		AutoResizeObject arO = AutoResizeObjectBuilder.buildAutoResizeObject();

		IEngine en = new Engine();
		en.marshalAndSave(arO, TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test marshal object with auto resize defined by config criteria
	 */
	@Test
	public void testAutoResizeByCriteriaMarshal() throws Exception {
		AutoResizeObject arO = AutoResizeObjectBuilder.buildAutoResizeObject();

		XConfigCriteria configCriteria = new XConfigCriteria();
		configCriteria.setFileName("OverrideAutoResizeOption");
		configCriteria.overrideAutoResizeColumn(false);

		IEngine en = new Engine();
		en.marshalAndSave(configCriteria, arO, TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test marshal list with auto resize defined by annotation
	 */
	@Test
	public void testAutoResizeByAnnotationMarshalCollection() throws Exception {
		List<AutoResizeObject> list = AutoResizeObjectBuilder.buildListOfAutoResizeObject(50);

		XConfigCriteria configCriteria = new XConfigCriteria();
		configCriteria.setFileName("AutoResizeCollection");

		IEngine en = new Engine();
		en.marshalAsCollectionAndSave(configCriteria, list, TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test marshal list with auto resize defined by config criteria
	 */
	@Test
	public void testAutoResizeByCriteriaMarshalCollection() throws Exception {
		List<AutoResizeObject> list = AutoResizeObjectBuilder.buildListOfAutoResizeObject(50);

		XConfigCriteria configCriteria = new XConfigCriteria();
		configCriteria.setFileName("OverrideAutoResizeCollectionOption");
		configCriteria.overrideAutoResizeColumn(false);

		IEngine en = new Engine();
		en.marshalAsCollectionAndSave(configCriteria, list, TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test unmarshal auto resize to guarantee no problem after marshal with
	 * this option
	 */
	@Test
	public void testAutoResizeByAnnotationUnmarshal() throws Exception {
		AutoResizeObject charger = new AutoResizeObject();

		IEngine en = new Engine();
		en.unmarshalFromPath(charger, TestUtils.WORKING_DIR_GENERATED_I);
		AutoResizeObjectBuilder.validateAutoResizeObject(charger);
	}
}
