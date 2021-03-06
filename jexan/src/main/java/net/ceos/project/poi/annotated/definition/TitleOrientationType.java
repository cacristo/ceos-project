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
 * This will define the title orientation type when writing into the Excel.
 * <p>
 * <ul>
 * <li><b>TitleOrientationType.TOP</b> Will paint the title over the element.
 * <li><b>TitleOrientationType.LEFT</b> Will paint the title at the left of the
 * element.
 * <li><b>TitleOrientationType.RIGHT</b> Will paint the title at the right of
 * the element.</li>
 * <li><b>TitleOrientationType.BOTTOM</b> Will paint the title under the
 * element.
 * </ul>
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public enum TitleOrientationType {

	// right
	TOP(0), LEFT(1), RIGHT(2), BOTTOM(3);

	private int code;

	private TitleOrientationType(int s) {
		code = s;
	}

	public int getCode() {
		return code;
	}
}
