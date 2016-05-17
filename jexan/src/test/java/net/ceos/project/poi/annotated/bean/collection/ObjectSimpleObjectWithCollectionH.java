package net.ceos.project.poi.annotated.bean.collection;

import java.util.Date;

import net.ceos.project.poi.annotated.annotation.XlsConfiguration;
import net.ceos.project.poi.annotated.annotation.XlsElement;
import net.ceos.project.poi.annotated.annotation.XlsNestedHeader;
import net.ceos.project.poi.annotated.annotation.XlsSheet;
import net.ceos.project.poi.annotated.definition.ExtensionFileType;
import net.ceos.project.poi.annotated.definition.PropagationType;

@XlsConfiguration(nameFile = "object_pai", extensionFile = ExtensionFileType.XLSX)
@XlsSheet(title = "object pai", startRow=2, propagation = PropagationType.PROPAGATION_HORIZONTAL)
public class ObjectSimpleObjectWithCollectionH {

	@XlsNestedHeader(title = "Main info pai", startX = 1, endX = 3)
	@XlsElement(title = "Date value", position = 1)
	private Date dateAttribute;

	@XlsElement(title = "String value pai", position = 2, columnWidthInUnits = 20, comment = "This is an simple comment")
	private String stringAttribute;

	@XlsElement(title = "Integer value pai", position = 3)
	private Integer integerAttribute = 0;
	
	@XlsElement(title = "Object Simples", position = 4)
	private SimpleObjectWithCollectionH simpleObjectCollH;

	public ObjectSimpleObjectWithCollectionH () {
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

	public SimpleObjectWithCollectionH getSimpleObjectCollH() {
		return simpleObjectCollH;
	}

	public void setSimpleObjectCollH(SimpleObjectWithCollectionH simpleObjectCollH) {
		this.simpleObjectCollH = simpleObjectCollH;
	}

	
	
	
}
