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

import org.testng.annotations.Test;

import net.ceos.project.poi.annotated.bean.cascade.CascadeObject;
import net.ceos.project.poi.annotated.bean.cascade.CascadeObjectBuilder;
import net.ceos.project.poi.annotated.exception.WorkbookException;

/**
 * Test the cascade level
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public class CascadeTest {

	/**
	 * Test with cascade type is CASCADE_FULL
	 * @throws WorkbookException 
	 */
	/*@Test
	public void testCascadeTypeBase() throws WorkbookException {
		CascadeObject cascadeObj = CascadeObjectBuilder.buildCascadeObject();
		
		IEngine en = new Engine();
		en.marshalAndSave(cascadeObj, TestUtils.WORKING_DIR_GENERATED_II);
	}*/

	/**
	 * Test with cascade type is CASCADE_FULL
	 * @throws WorkbookException 
	 */
	@Test
	public void testCascadeTypeL1() throws WorkbookException {
		CascadeObject cascadeObj = CascadeObjectBuilder.buildCascadeObject();
		
		IEngine en = new Engine();
		en.marshalAndSave(cascadeObj, TestUtils.WORKING_DIR_GENERATED_II);
	}
}
