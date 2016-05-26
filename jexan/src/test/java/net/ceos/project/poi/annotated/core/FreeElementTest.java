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

import org.junit.Test;

import net.ceos.project.poi.annotated.annotation.XlsFreeElement;
import net.ceos.project.poi.annotated.bean.FreeElementAdvancedObject;
import net.ceos.project.poi.annotated.bean.FreeElementAdvancedObjectBuilder;
import net.ceos.project.poi.annotated.bean.FreeElementObject;
import net.ceos.project.poi.annotated.bean.FreeElementObjectBuilder;

/**
 * Test the annotation {@link XlsFreeElement}
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public class FreeElementTest {

	/**
	 * Test the marshal of one simple object with the annotation
	 * {@link XlsFreeElement}
	 */
	@Test
	public void testMarshalFreeElementObject() throws Exception {
		FreeElementObject fe = FreeElementObjectBuilder.buildFreeElementObject();

		IEngine en = new Engine();
		en.marshalAndSave(fe, TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test the unmarshal of one simple object with the annotation
	 * {@link XlsFreeElement}
	 */
	@Test
	public void testUnmarshalFreeElementObject() throws Exception {
		FreeElementObject fe = new FreeElementObject();

		IEngine en = new Engine();
		en.unmarshalFromPath(fe, TestUtils.WORKING_DIR_GENERATED_I);

		FreeElementObjectBuilder.validateFreeElementObject(fe);
	}

	/**
	 * Test the marshal of one complex object with the annotation
	 * {@link XlsFreeElement}
	 */
	@Test
	public void testMarshalFreeElementAdvancedObject() throws Exception {
		FreeElementAdvancedObject fea = FreeElementAdvancedObjectBuilder.buildFreeElementAdvancedObject();

		IEngine en = new Engine();
		en.marshalAndSave(fea, TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test the unmarshal of one complex object with the annotation
	 * {@link XlsFreeElement}
	 */
	@Test
	public void testUnmarshalFreeElementAdvancedObject() throws Exception {
		FreeElementAdvancedObject fea = new FreeElementAdvancedObject();

		IEngine en = new Engine();
		en.unmarshalFromPath(fea, TestUtils.WORKING_DIR_GENERATED_I);

		FreeElementAdvancedObjectBuilder.validateFreeElementAdvancedObject(fea);
	}
}
