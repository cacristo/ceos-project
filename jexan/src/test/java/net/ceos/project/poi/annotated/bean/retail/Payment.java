package net.ceos.project.poi.annotated.bean.retail;

import java.time.LocalDateTime;

public class Payment {

	private Integer customerNumber;
	private String checkNumber;
	private LocalDateTime paymentDate;
	private Double amount;

	public Payment() {
		/* empty constructor */
	}

	/**
	 * @return the customerNumber
	 */
	public final Integer getCustomerNumber() {
		return customerNumber;
	}

	/**
	 * @param customerNumber
	 *            the customerNumber to set
	 */
	public final void setCustomerNumber(Integer customerNumber) {
		this.customerNumber = customerNumber;
	}

	/**
	 * @return the checkNumber
	 */
	public final String getCheckNumber() {
		return checkNumber;
	}

	/**
	 * @param checkNumber
	 *            the checkNumber to set
	 */
	public final void setCheckNumber(String checkNumber) {
		this.checkNumber = checkNumber;
	}

	/**
	 * @return the paymentDate
	 */
	public final LocalDateTime getPaymentDate() {
		return paymentDate;
	}

	/**
	 * @param paymentDate
	 *            the paymentDate to set
	 */
	public final void setPaymentDate(LocalDateTime paymentDate) {
		this.paymentDate = paymentDate;
	}

	/**
	 * @return the amount
	 */
	public final Double getAmount() {
		return amount;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public final void setAmount(Double amount) {
		this.amount = amount;
	}
}
