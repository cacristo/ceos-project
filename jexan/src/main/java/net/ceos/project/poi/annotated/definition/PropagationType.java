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
 * This will define the propagation type when writing into the Excel. <br>
 * 
 * {@value = PropagationType.PROPAGATION_HORIZONTAL } Will propagate horizontally all the elements. <br>
 * {@value = PropagationType.PROPAGATION_VERTICAL } Will propagate vertically all the elements. <br>
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
