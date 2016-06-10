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

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import net.ceos.project.poi.annotated.core.CGen;
import net.ceos.project.poi.annotated.core.Engine;
import net.ceos.project.poi.annotated.core.IEngine;
import net.ceos.project.poi.annotated.core.IGeneratorCSV;

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
public class EngineModifiersTest {

	/* number of methods to ignore */
	private int defaultMethods = 9;

	/**
	 * Validate the access level modifiers at the fields
	 */
	@Test
	public void checkAccessLevelModifiersFieldsEngine() {
		assertEquals(new Engine().getClass().getFields().length, 0);
	}

	/**
	 * Validate the access level modifiers at the fields
	 */
	@Test
	public void checkAccessLevelModifiersFieldsCGen() {
		assertEquals(new CGen().getClass().getFields().length, 0);
	}

	/**
	 * Validate the access level modifiers at the methods
	 */
	@Test
	public void checkAccessLevelModifiersMethodsEngine() {
		assertEquals(new Engine().getClass().getMethods().length - defaultMethods, 20);
	}

	/**
	 * Validate the access level modifiers at the methods
	 */
	@Test
	public void checkAccessLevelModifiersMethodsCGen() {
		assertEquals(new CGen().getClass().getMethods().length - defaultMethods, 8);
	}

	/**
	 * Validate the access level modifiers at the methods
	 */
	@Test
	public void checkModifiersMethodsInterfacesEngine() {
		Class<?> objClass = Engine.class;
		Class<?> objInterface = IEngine.class;
		assertEquals(objClass.getMethods().length - defaultMethods, 20);
		assertEquals(objInterface.getDeclaredMethods().length, 20);
	}

	/**
	 * Validate the access level modifiers at the methods
	 */
	@Test
	public void checkModifiersMethodsInterfacesCGen() {
		Class<?> objClass = CGen.class;
		Class<?> objInterface = IGeneratorCSV.class;
		assertEquals(objClass.getMethods().length - defaultMethods, 8);
		assertEquals(objInterface.getDeclaredMethods().length, 8);
	}
}
