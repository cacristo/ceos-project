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
package net.ceos.project.poi.annotated.bean.collection;

import java.util.List;

import net.ceos.project.poi.annotated.annotation.XlsConfiguration;
import net.ceos.project.poi.annotated.annotation.XlsElement;
import net.ceos.project.poi.annotated.annotation.XlsSheet;
import net.ceos.project.poi.annotated.definition.CascadeType;
import net.ceos.project.poi.annotated.definition.ExtensionFileType;
import net.ceos.project.poi.annotated.definition.PropagationType;

@XlsConfiguration(nameFile = "object2", extensionFile = ExtensionFileType.XLSX)
@XlsSheet(title = "object2", startRow=2, propagation = PropagationType.PROPAGATION_HORIZONTAL,cascadeLevel=CascadeType.CASCADE_FULL)
public class Object2 {

//	@XlsElement(title = "string", position = 1)
	private String  string;
	
	@XlsElement(title = "list multi", position = 1)
	private List<SimpleObjectPropH>  simpleObjectPropH;

	public Object2 () {
	}

	public List<SimpleObjectPropH> getSimpleObjectPropH() {
		return simpleObjectPropH;
	}

	public void setSimpleObjectPropH(List<SimpleObjectPropH> simpleObjectPropH) {
		this.simpleObjectPropH = simpleObjectPropH;
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

	
	
}
