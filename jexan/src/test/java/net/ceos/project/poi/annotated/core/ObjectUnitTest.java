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

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import net.ceos.project.poi.annotated.bean.ObjectUnit;
import net.ceos.project.poi.annotated.definition.ExtensionFileType;

public class ObjectUnitTest {

	/**
	 * Test one basic object
	 */
	@Test
	public void testMarshaObjectFormula() throws Exception {
		ObjectUnit ou = new ObjectUnit("PenDrive 5GB", 4.87, 6.99, 21, 185);

		IEngine en = new Engine();

		en.marshalAndSave(ou, TestUtils.WORKING_DIR_GENERATED_II);

	}

	/**
	 * Test one basic object
	 */
	@Test
	public void testMarshaListObjectFormula() throws Exception {
		List<ObjectUnit> collection = new ArrayList<ObjectUnit>();
		collection.add(new ObjectUnit("PenDrive 5GB", 4.87, 6.99, 21, 185));
		collection.add(new ObjectUnit("PenDrive 8GB", 5.11, 8.99, 21, 200));
		collection.add(new ObjectUnit("PenDrive 12GB", 5.87, 11.99, 21, 500));
		collection.add(new ObjectUnit("PenDrive 16GB", 4.87, 6.99, 21, 250));
		collection.add(new ObjectUnit("Drive 500GB", 84.87, 111.99, 21, 100));
		collection.add(new ObjectUnit("PenDrive 1TB", 164.22, 199.99, 21, 35));

		XConfigCriteria configCriteria = new XConfigCriteria();
		configCriteria.setFileName("CollectionObjectUnit");
		configCriteria.overrideExtensionType(ExtensionFileType.XLS);

		IEngine en = new Engine();
		en.marshalAsCollectionAndSave(configCriteria, collection, TestUtils.WORKING_DIR_GENERATED_I);

	}
}
