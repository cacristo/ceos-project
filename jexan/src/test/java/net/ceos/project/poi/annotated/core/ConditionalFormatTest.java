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

import net.ceos.project.poi.annotated.bean.ConditionalFormatObject;
import net.ceos.project.poi.annotated.bean.ConditionalFormatObjectBuilder;
import net.ceos.project.poi.annotated.definition.CascadeType;
import net.ceos.project.poi.annotated.definition.PropagationType;
import net.ceos.project.poi.annotated.exception.WorkbookException;

/**
 * Test the conditional format
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public class ConditionalFormatTest {

	// TODO add more tests
	
	/**
	 * Test basic conditional format
	 * @throws WorkbookException 
	 */
	@Test
	public void testCascadeTypeLevelThree() throws WorkbookException {
		List<ConditionalFormatObject> cascadeObj = ConditionalFormatObjectBuilder.buildListOfConditionalFormatObject(240);

		XConfigCriteria criteria = new XConfigCriteria();
		criteria.overrideCascadeLevel(CascadeType.CASCADE_L3);
		//criteria.overridePropagationType(PropagationType.PROPAGATION_VERTICAL);
		
		IEngine en = new Engine();
		en.marshalAsCollectionAndSave(criteria, cascadeObj, TestUtils.WORKING_DIR_GENERATED_I);
	}
}
