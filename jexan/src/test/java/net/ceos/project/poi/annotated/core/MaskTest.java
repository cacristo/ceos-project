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

import net.ceos.project.poi.annotated.annotation.XlsElement;
import net.ceos.project.poi.annotated.bean.ObjectMask;
import net.ceos.project.poi.annotated.bean.ObjectMaskBuilder;

/**
 * Test multiple (format/transform) mask to apply at {@link XlsElement}
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public class MaskTest {

	/**
	 * 
	 * Test the marshal/unmarshal of one object applying mask at the
	 * {@link XlsElement}
	 */
	@Test
	public void testMarshalObjectMask() throws Exception {
		ObjectMask om = ObjectMaskBuilder.buildObjectMask();

		IEngine en = new Engine();
		en.marshalAndSave(om, TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test if the object who applied mask at the {@link XlsElement} cause any damage at the moment
	 * of unmarshal.
	 */
	@Test
	public void testUnmarshalObjectMask() throws Exception {
		ObjectMask charger = new ObjectMask();

		IEngine en = new Engine();
		en.unmarshalFromPath(charger, TestUtils.WORKING_DIR_GENERATED_II);

		ObjectMaskBuilder.validateObjectMask(charger);
	}
}
