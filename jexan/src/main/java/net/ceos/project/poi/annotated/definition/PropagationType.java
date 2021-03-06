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
 * This will define the propagation type when writing into the Excel.
 * <p>
 * <ul>
 * <li><b>PropagationType.PROPAGATION_HORIZONTAL</b> Will propagate horizontally
 * all the elements.
 * <li><b>PropagationType.PROPAGATION_VERTICAL</b> Will propagate vertically all
 * the elements. Be aware the index cell range is (0..255) for files .xls and
 * (0..16383) for files .xlsx
 * </ul>
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public enum PropagationType {

	PROPAGATION_HORIZONTAL(0), PROPAGATION_VERTICAL(1);

	private int code;

	private PropagationType(int s) {
		code = s;
	}

	public int getCode() {
		return code;
	}
}
