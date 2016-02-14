package org.jaexcel.framework.JAEX.bean;

import java.util.Date;

import org.jaexcel.framework.JAEX.annotation.XlsConfiguration;
import org.jaexcel.framework.JAEX.annotation.XlsFreeElement;
import org.jaexcel.framework.JAEX.annotation.XlsSheet;
import org.jaexcel.framework.JAEX.definition.TitleOrientationType;

@XlsSheet(title = "Xls Free Element objects")
@XlsConfiguration(nameFile = "FreeElementAdvancedObjects")
public class FreeElementAdvancedObject {

	@XlsFreeElement(title = "Free String", showTitle = true, titleOrientation = TitleOrientationType.TOP, row = 2, cell = 2)
	private String freeString;

	@XlsFreeElement(title = "Free Double", showTitle = true, titleOrientation = TitleOrientationType.LEFT, row = 3, cell = 2)
	private Double freeDouble;

	@XlsFreeElement(title = "Free Primitive int", showTitle = true, titleOrientation = TitleOrientationType.RIGHT, row = 4, cell = 2)
	private int freePrimitiveInt;

	@XlsFreeElement(title = "Free Date", showTitle = true, titleOrientation = TitleOrientationType.TOP, row = 2, cell = 3)
	private Date freeDate;

	@XlsFreeElement(title = "Free Long", showTitle = true, titleOrientation = TitleOrientationType.LEFT, row = 5, cell = 2)
	private Long freeLong;

	@XlsFreeElement(title = "Free Primitive Boolean", showTitle = true, titleOrientation = TitleOrientationType.BOTTOM, row = 2, cell = 4)
	private boolean freePrimitiveBoolean;

	public FreeElementAdvancedObject() {
	}

	/**
	 * @return the freeString
	 */
	protected String getFreeString() {
		return freeString;
	}

	/**
	 * @param freeString
	 *            the freeString to set
	 */
	protected void setFreeString(String freeString) {
		this.freeString = freeString;
	}

	/**
	 * @return the freeDouble
	 */
	protected Double getFreeDouble() {
		return freeDouble;
	}

	/**
	 * @param freeDouble
	 *            the freeDouble to set
	 */
	protected void setFreeDouble(Double freeDouble) {
		this.freeDouble = freeDouble;
	}

	/**
	 * @return the freePrimitiveInt
	 */
	protected int getFreePrimitiveInt() {
		return freePrimitiveInt;
	}

	/**
	 * @param freePrimitiveInt
	 *            the freePrimitiveInt to set
	 */
	protected void setFreePrimitiveInt(int freePrimitiveInt) {
		this.freePrimitiveInt = freePrimitiveInt;
	}

	/**
	 * @return the freeDate
	 */
	protected Date getFreeDate() {
		return freeDate;
	}

	/**
	 * @param freeDate
	 *            the freeDate to set
	 */
	protected void setFreeDate(Date freeDate) {
		this.freeDate = freeDate;
	}

	/**
	 * @return the freeLong
	 */
	protected Long getFreeLong() {
		return freeLong;
	}

	/**
	 * @param freeLong
	 *            the freeLong to set
	 */
	protected void setFreeLong(Long freeLong) {
		this.freeLong = freeLong;
	}

	/**
	 * @return the freePrimitiveBoolean
	 */
	protected boolean isFreePrimitiveBoolean() {
		return freePrimitiveBoolean;
	}

	/**
	 * @param freePrimitiveBoolean
	 *            the freePrimitiveBoolean to set
	 */
	protected void setFreePrimitiveBoolean(boolean freePrimitiveBoolean) {
		this.freePrimitiveBoolean = freePrimitiveBoolean;
	}
}
