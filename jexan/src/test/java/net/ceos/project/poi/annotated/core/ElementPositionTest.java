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

import org.junit.BeforeClass;
import org.junit.Test;

import net.ceos.project.poi.annotated.bean.AutomaticPositionObject;
import net.ceos.project.poi.annotated.bean.AutomaticPositionObjectBuilder;
import net.ceos.project.poi.annotated.definition.ExtensionFileType;

public class ElementPositionTest {

	private static List<AutomaticPositionObject> collection;

	@BeforeClass
	public static void prepareDataList() {
		collection = AutomaticPositionObjectBuilder.buildListOfAutomaticPositionObject(100);
	}

	/**
	 * Test the performance of generating one file with 5000 lines.
	 */
	@Test
	public void testMarshalMultiObjectAutomaticPosition() throws Exception {
		IEngine en = new Engine();
		XConfigCriteria configCriteria = new XConfigCriteria();
		configCriteria.setFileName("performance_test_list_v2");
		configCriteria.overrideExtensionType(ExtensionFileType.XLS);
		configCriteria.overrideAutoResizeColumn(true);

		en.marshalAsCollectionAndSave(configCriteria, ElementPositionTest.collection, TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test the performance of generating one file with 5000 lines.
	 */
	@Test
	public void testUnMarshalMultiObjectPerf2() throws Exception {

		IEngine en = new Engine();
		XConfigCriteria configCriteria = new XConfigCriteria();
		configCriteria.setFileName("performance_test_list_v2");
		configCriteria.overrideExtensionType(ExtensionFileType.XLS);
		configCriteria.overrideAutoResizeColumn(true);

		@SuppressWarnings("unchecked")
		List<AutomaticPositionObject> collection = (List<AutomaticPositionObject>) en
				.unmarshalToCollection(configCriteria, new AutomaticPositionObject(), TestUtils.WORKING_DIR_GENERATED_I);

		for(int i = 0; i < collection.size(); i++){
			AutomaticPositionObjectBuilder.validateAutomaticPositionObject(ElementPositionTest.collection.get(i), collection.get(i), false);
		}
	}
}
