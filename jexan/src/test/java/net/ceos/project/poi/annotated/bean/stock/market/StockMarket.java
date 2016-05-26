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
package net.ceos.project.poi.annotated.bean.stock.market;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * The stock market
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public class StockMarket {

	private LocalDate date;
	private BigDecimal openValue;
	private BigDecimal maxValue;
	private BigDecimal minValue;
	private BigDecimal closeValue;
	private BigDecimal closeValueAdjust;
	private BigDecimal avegare;

	public StockMarket() {
		/* empty constructor */
	}

	/**
	 * @return the date
	 */
	public final LocalDate getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public final void setDate(LocalDate date) {
		this.date = date;
	}

	/**
	 * @return the openValue
	 */
	public final BigDecimal getOpenValue() {
		return openValue;
	}

	/**
	 * @param openValue
	 *            the openValue to set
	 */
	public final void setOpenValue(BigDecimal openValue) {
		this.openValue = openValue;
	}

	/**
	 * @return the maxValue
	 */
	public final BigDecimal getMaxValue() {
		return maxValue;
	}

	/**
	 * @param maxValue
	 *            the maxValue to set
	 */
	public final void setMaxValue(BigDecimal maxValue) {
		this.maxValue = maxValue;
	}

	/**
	 * @return the minValue
	 */
	public final BigDecimal getMinValue() {
		return minValue;
	}

	/**
	 * @param minValue
	 *            the minValue to set
	 */
	public final void setMinValue(BigDecimal minValue) {
		this.minValue = minValue;
	}

	/**
	 * @return the closeValue
	 */
	public final BigDecimal getCloseValue() {
		return closeValue;
	}

	/**
	 * @param closeValue
	 *            the closeValue to set
	 */
	public final void setCloseValue(BigDecimal closeValue) {
		this.closeValue = closeValue;
	}

	/**
	 * @return the closeValueAdjust
	 */
	public final BigDecimal getCloseValueAdjust() {
		return closeValueAdjust;
	}

	/**
	 * @param closeValueAdjust
	 *            the closeValueAdjust to set
	 */
	public final void setCloseValueAdjust(BigDecimal closeValueAdjust) {
		this.closeValueAdjust = closeValueAdjust;
	}

	/**
	 * @return the avegare
	 */
	public final BigDecimal getAvegare() {
		return avegare;
	}

	/**
	 * @param avegare
	 *            the avegare to set
	 */
	public final void setAvegare(BigDecimal avegare) {
		this.avegare = avegare;
	}
}
