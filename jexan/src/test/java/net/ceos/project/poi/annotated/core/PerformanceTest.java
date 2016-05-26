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

import net.ceos.project.poi.annotated.bean.PerformanceObject;
import net.ceos.project.poi.annotated.bean.PerformanceObjectBuilder;
import net.ceos.project.poi.annotated.definition.ExtensionFileType;

/**
 * Test performance of the library.
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public class PerformanceTest {

	/**
	 * Test the performance of generating one file with 5000 lines.
	 */
	@Test
	public void testMarshalMultiObjectPerf() throws Exception {
		List<PerformanceObject> collection = PerformanceObjectBuilder.buildListOfPerformanceObject(5000);

		IEngine en = new Engine();
		XConfigCriteria configCriteria = new XConfigCriteria();
		configCriteria.setFileName("performance_test_list");
		configCriteria.overrideExtensionType(ExtensionFileType.XLS);
		
		en.marshalAsCollectionAndSave(configCriteria, collection, TestUtils.WORKING_DIR_GENERATED_I);
	}
}
