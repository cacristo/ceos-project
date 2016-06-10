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

import net.ceos.project.poi.annotated.bean.PropagationHorizontalObject;
import net.ceos.project.poi.annotated.bean.PropagationHorizontalObjectBuilder;
import net.ceos.project.poi.annotated.bean.PropagationVerticalObject;
import net.ceos.project.poi.annotated.bean.PropagationVerticalObjectBuilder;
import net.ceos.project.poi.annotated.definition.PropagationType;

/**
 * Test the {@link PropagationType} to apply at the library.
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public class PropagationTest {

	/**
	 * Test with propagation type is HORIZONTAL
	 * 
	 * @throws Exception
	 */
	@Test
	public void validatePropagationTypeHorizontal() throws Exception {
		PropagationHorizontalObject pHO = PropagationHorizontalObjectBuilder.buildPropagationHorizontalObject();

		IEngine en = new Engine();
		en.marshalAndSave(pHO, TestUtils.WORKING_DIR_GENERATED_I);

		PropagationHorizontalObject charger = new PropagationHorizontalObject();

		en.unmarshalFromPath(charger, TestUtils.WORKING_DIR_GENERATED_II);
		PropagationHorizontalObjectBuilder.validatePropagationHorizontalObject(charger);
	}

	/**
	 * Test with propagation type is VERTICAL
	 */
	@Test
	public void validatePropagationTypeVertical() throws Exception {
		PropagationVerticalObject pVO = PropagationVerticalObjectBuilder.buildPropagationVerticalObject();

		IEngine en = new Engine();
		en.marshalAndSave(pVO, TestUtils.WORKING_DIR_GENERATED_II);

		PropagationVerticalObject charger = new PropagationVerticalObject();

		en.unmarshalFromPath(charger, TestUtils.WORKING_DIR_GENERATED_I);
		PropagationVerticalObjectBuilder.validatePropagationVerticalObject(charger);
	}
}
