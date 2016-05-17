package net.ceos.project.poi.annotated.bean.collection;

import java.util.Date;

import net.ceos.project.poi.annotated.annotation.XlsConfiguration;
import net.ceos.project.poi.annotated.annotation.XlsElement;
import net.ceos.project.poi.annotated.annotation.XlsNestedHeader;
import net.ceos.project.poi.annotated.annotation.XlsSheet;
import net.ceos.project.poi.annotated.definition.ExtensionFileType;
import net.ceos.project.poi.annotated.definition.PropagationType;

@XlsConfiguration(nameFile = "object_pai_v", extensionFile = ExtensionFileType.XLSX)
@XlsSheet(title = "object pai v", startRow=2, propagation = PropagationType.PROPAGATION_VERTICAL)
public class ObjectSimpleObjectWithCollectionV {

	@XlsNestedHeader(title = "Main info pai", startY = 1, endY = 3)
	@XlsElement(title = "Date value", position = 1)
	private Date dateAttribute;

	@XlsElement(title = "String value pai", position = 2, columnWidthInUnits = 20, comment = "This is an simple comment")
	private String stringAttribute;

	@XlsElement(title = "Integer value pai", position = 3)
	private Integer integerAttribute = 0;
	
	@XlsElement(title = "Object Simples", position = 4)
	private SimpleObjectWithCollectionV simpleObjectCollV;

	public ObjectSimpleObjectWithCollectionV () {
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

	public SimpleObjectWithCollectionV getSimpleObjectCollV() {
		return simpleObjectCollV;
	}

	public void setSimpleObjectCollV(SimpleObjectWithCollectionV simpleObjectCollV) {
		this.simpleObjectCollV = simpleObjectCollV;
	}

	
	
	
}
