package net.ceos.project.poi.annotated.bean;

import java.util.Date;

import net.ceos.project.poi.annotated.annotation.XlsElement;
import net.ceos.project.poi.annotated.annotation.XlsNestedHeader;
import net.ceos.project.poi.annotated.annotation.XlsSheet;
import net.ceos.project.poi.annotated.definition.PropagationType;

@XlsSheet(title = "List Simple object vertical", propagation=PropagationType.PROPAGATION_VERTICAL)
public class SimpleObjectListPropVertical {

	@XlsNestedHeader(title = "Main info", startY = 1, endY = 3)
	@XlsElement(title = "Date value", position = 1)
	private Date dateAttribute;

	@XlsElement(title = "String value", position = 2, comment="This is an simple comment")
	private String stringAttribute;

	@XlsElement(title = "Integer value", position = 3)
	private Integer integerAttribute = 0;

	public SimpleObjectListPropVertical() {
	}

	/**
	 * @return the dateAttribute
	 */
	public Date getDateAttribute() {
		return dateAttribute;
	}

	/**
	 * @param dateAttribute the dateAttribute to set
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
	 * @param stringAttribute the stringAttribute to set
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
	 * @param integerAttribute the integerAttribute to set
	 */
	public void setIntegerAttribute(Integer integerAttribute) {
		this.integerAttribute = integerAttribute;
	}
}
