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
package net.ceos.project.poi.annotated.bean.retail;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * The order entity
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
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
