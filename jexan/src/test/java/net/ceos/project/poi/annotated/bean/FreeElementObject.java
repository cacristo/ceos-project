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
package net.ceos.project.poi.annotated.bean;

import java.util.Date;

import net.ceos.project.poi.annotated.annotation.XlsConfiguration;
import net.ceos.project.poi.annotated.annotation.XlsFreeElement;
import net.ceos.project.poi.annotated.annotation.XlsSheet;

@XlsSheet(title = "Xls Free Element objects")
@XlsConfiguration(nameFile = "FreeElementObjects")
public class FreeElementObject {

	@XlsFreeElement(title = "Free String", row = 1, cell = 1)
	private String freeString;

	@XlsFreeElement(title = "Free Double", row = 2, cell = 1)
	private Double freeDouble;

	@XlsFreeElement(title = "Free Primitive int", row = 3, cell = 1)
	private int freePrimitiveInt;

	@XlsFreeElement(title = "Free Date", row = 4, cell = 1, columnWidthInUnits = 10)
	private Date freeDate;

	@XlsFreeElement(title = "Free Long", row = 5, cell = 1)
	private Long freeLong;

	@XlsFreeElement(title = "Free Primitive Boolean", row = 6, cell = 1)
	private boolean freePrimitiveBoolean;

	public FreeElementObject() {
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
