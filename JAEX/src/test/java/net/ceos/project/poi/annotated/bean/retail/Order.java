package net.ceos.project.poi.annotated.bean.retail;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Order {

	private Integer orderNumber;
	private LocalDateTime orderDate;
	private LocalDate requiredDate;
	private LocalDateTime shippedDate;
	private String status;
	private String comments;
	private Integer customerNumber;

	public Order() {
		/* empty constructor */
	}

	/**
	 * @return the orderNumber
	 */
	public final Integer getOrderNumber() {
		return orderNumber;
	}

	/**
	 * @param orderNumber
	 *            the orderNumber to set
	 */
	public final void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}

	/**
	 * @return the orderDate
	 */
	public final LocalDateTime getOrderDate() {
		return orderDate;
	}

	/**
	 * @param orderDate
	 *            the orderDate to set
	 */
	public final void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	/**
	 * @return the requiredDate
	 */
	public final LocalDate getRequiredDate() {
		return requiredDate;
	}

	/**
	 * @param requiredDate
	 *            the requiredDate to set
	 */
	public final void setRequiredDate(LocalDate requiredDate) {
		this.requiredDate = requiredDate;
	}

	/**
	 * @return the shippedDate
	 */
	public final LocalDateTime getShippedDate() {
		return shippedDate;
	}

	/**
	 * @param shippedDate
	 *            the shippedDate to set
	 */
	public final void setShippedDate(LocalDateTime shippedDate) {
		this.shippedDate = shippedDate;
	}

	/**
	 * @return the status
	 */
	public final String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public final void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the comments
	 */
	public final String getComments() {
		return comments;
	}

	/**
	 * @param comments
	 *            the comments to set
	 */
	public final void setComments(String comments) {
		this.comments = comments;
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
}
