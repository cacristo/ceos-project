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

import java.util.Date;
import java.util.List;
import java.util.Set;

import net.ceos.project.poi.annotated.annotation.XlsConfiguration;
import net.ceos.project.poi.annotated.annotation.XlsElement;
import net.ceos.project.poi.annotated.annotation.XlsNestedHeader;
import net.ceos.project.poi.annotated.annotation.XlsSheet;
import net.ceos.project.poi.annotated.definition.ExtensionFileType;
import net.ceos.project.poi.annotated.definition.PropagationType;

@XlsConfiguration(nameFile = "collection_object_simple_with_collec_h", extensionFile = ExtensionFileType.XLSX)
@XlsSheet(title = "object",startCell=3, propagation = PropagationType.PROPAGATION_VERTICAL)
public class SimpleObjectWithCollectionV {

	@XlsElement(title = "Date value", position = 1)
	private Date dateAttribute;

	@XlsElement(title = "String value", position = 2, columnWidthInUnits = 20, comment = "This is an simple comment")
	private String stringAttribute;

	@XlsElement(title = "Integer value", position = 3)
	private Integer integerAttribute = 0;
	
	@XlsElement(title = "Integer value", position = 4 )
	private Set<SimpleObjectPropV> setSimpleObjectPropV;
	
	@XlsElement(title = "list multi", position = 5)
	private List<MultiTypeObjectPropV>  listMultiObjectPropV;

	public SimpleObjectWithCollectionV () {
	}

	/**
	 * @return the dateAttribute
	 */
	public Date getDateAttribute() {
		return dateAttribute;
	}

	/**
	 * @param dateAttribute
	 *            the dateAttribute to set
	 */
	public void setDateAttribute(Date dateAttribute) {
		this.dateAttribute = dateAttribute;
	}

	/**
	 * @return the stringAttribute
	 */
	public String getStringAttribute() {
		return stringAttribute;
	}

	/**
	 * @param stringAttribute
	 *            the stringAttribute to set
	 */
	public void setStringAttribute(String stringAttribute) {
		this.stringAttribute = stringAttribute;
	}

	/**
	 * @return the integerAttribute
	 */
	public Integer getIntegerAttribute() {
		return integerAttribute;
	}

	/**
	 * @param integerAttribute
	 *            the integerAttribute to set
	 */
	public void setIntegerAttribute(Integer integerAttribute) {
		this.integerAttribute = integerAttribute;
	}

	public Set<SimpleObjectPropV> getSetSimpleObjectPropV() {
		return setSimpleObjectPropV;
	}

	public void setSetSimpleObjectPropV(Set<SimpleObjectPropV> setSimpleObjectPropV) {
		this.setSimpleObjectPropV = setSimpleObjectPropV;
	}

	public List<MultiTypeObjectPropV> getListMultiObjectPropV() {
		return listMultiObjectPropV;
	}

	public void setListMultiObjectPropV(List<MultiTypeObjectPropV> listMultiObjectPropV) {
		this.listMultiObjectPropV = listMultiObjectPropV;
	}

	
	
	
}
