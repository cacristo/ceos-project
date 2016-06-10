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
package net.ceos.project.poi.annotated.modifier.visibility;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import net.ceos.project.poi.annotated.core.CellDecorator;

/**
 * Validate the number of public modifiers declared as fields or methods.
 * <p>
 * For the case of the methods we need to ignore the 9 generic methods
 * <ul>
 * <li>wait
 * <li>wait
 * <li>wait
 * <li>equals
 * <li>toString
 * <li>hashCode
 * <li>getClass
 * <li>notify
 * <li>notifyAll
 * </ul>
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public class CellDecoratorModifiersTest {

	/* number of methods to ignore */
	private int defaultMethods = 9;

	/**
	 * Validate the access level modifiers at the fields
	 */
	@Test
	public void checkAccessLevelModifiersFields() {
		assertEquals(new CellDecorator().getClass().getFields().length, 0);
	}

	/**
	 * Validate the access level modifiers at the methods
	 */
	@Test
	public void checkAccessLevelModifiersMethods() {
		assertEquals(new CellDecorator().getClass().getMethods().length - defaultMethods, 34);
	}

}
