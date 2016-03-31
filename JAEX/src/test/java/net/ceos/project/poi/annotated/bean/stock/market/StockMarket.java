package net.ceos.project.poi.annotated.bean.stock.market;

import java.math.BigDecimal;
import java.time.LocalDate;

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
