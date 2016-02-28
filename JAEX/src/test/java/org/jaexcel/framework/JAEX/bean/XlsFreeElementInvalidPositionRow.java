package org.jaexcel.framework.JAEX.bean;

import java.util.Date;

import org.jaexcel.framework.JAEX.annotation.XlsConfiguration;
import org.jaexcel.framework.JAEX.annotation.XlsFreeElement;
import org.jaexcel.framework.JAEX.annotation.XlsSheet;

@XlsSheet(title = "Xls Free Element invalid position cell")
@XlsConfiguration(nameFile = "FreeElementInvalid")
public class XlsFreeElementInvalidPositionRow {
	@XlsFreeElement(title = "Free String", row = 1, cell = 1)
	private String freeString;

	@XlsFreeElement(title = "Free Double", row = 0, cell = 1)
	private Double freeDouble;

	@XlsFreeElement(title = "Free Primitive int", row = 3, cell = 1)
	private int freePrimitiveInt;

	@XlsFreeElement(title = "Free Date", row = 4, cell = 1)
	private Date freeDate;

	@XlsFreeElement(title = "Free Long", row = 5, cell = 1)
	private Long freeLong;

	@XlsFreeElement(title = "Free Primitive Boolean", row = 6, cell = 1)
	private boolean freePrimitiveBoolean;

	public XlsFreeElementInvalidPositionRow() {
		super();
	}

	/**
	 * @return the freeString
	 */
	protected String getFreeString() {
		return freeString;
	}

	/**
	 * @param freeString the freeString to set
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
	 * @param freeDouble the freeDouble to set
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
	 * @param freePrimitiveInt the freePrimitiveInt to set
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
	 * @param freeDate the freeDate to set
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
	 * @param freeLong the freeLong to set
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
	 * @param freePrimitiveBoolean the freePrimitiveBoolean to set
	 */
	protected void setFreePrimitiveBoolean(boolean freePrimitiveBoolean) {
		this.freePrimitiveBoolean = freePrimitiveBoolean;
	}
}
