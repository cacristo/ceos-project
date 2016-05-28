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
package net.ceos.project.poi.annotated.definition;

/**
 * This will define the cascade type when reading one attribute of the object.
 * <p>
 * <ul>
 * <li><b>CascadeType.CASCADE_BASE</b> Will propagate the writing only at the main objects.
 * <li><b>CascadeType.CASCADE_LEVEL_ONE</b> Will propagate the writing at level one of the objects at the any collection.
 * <li><b>CascadeType.CASCADE_LEVEL_TWO</b> Will propagate the writing at level two at any collection of the child objects.
 * <li><b>CascadeType.CASCADE_FULL</b> Will propagate the writing fully as objects exists at any collection.
 * </ul>
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public enum CascadeType {

	CASCADE_BASE(2), CASCADE_LEVEL_ONE(4), CASCADE_LEVEL_TWO(20), CASCADE_FULL(100);

	private int code;

	private CascadeType(int s) {
		code = s;
	}

	public int getCode() {
		return code;
	}
}
