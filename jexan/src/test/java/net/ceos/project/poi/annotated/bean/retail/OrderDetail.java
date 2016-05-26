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

/**
 * The order detal entity
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public class OrderDetail {

	private Integer orderNumber;
	private String productCode;
	private Short quantityOrdered;
	private Double priceEach;
	private Integer orderLineNumber;

	public OrderDetail() {
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
	 * @return the productCode
	 */
	public final String getProductCode() {
		return productCode;
	}

	/**
	 * @param productCode
	 *            the productCode to set
	 */
	public final void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	/**
	 * @return the quantityOrdered
	 */
	public final Short getQuantityOrdered() {
		return quantityOrdered;
	}

	/**
	 * @param quantityOrdered
	 *            the quantityOrdered to set
	 */
	public final void setQuantityOrdered(Short quantityOrdered) {
		this.quantityOrdered = quantityOrdered;
	}

	/**
	 * @return the priceEach
	 */
	public final Double getPriceEach() {
		return priceEach;
	}

	/**
	 * @param priceEach
	 *            the priceEach to set
	 */
	public final void setPriceEach(Double priceEach) {
		this.priceEach = priceEach;
	}

	/**
	 * @return the orderLineNumber
	 */
	public final Integer getOrderLineNumber() {
		return orderLineNumber;
	}

	/**
	 * @param orderLineNumber
	 *            the orderLineNumber to set
	 */
	public final void setOrderLineNumber(Integer orderLineNumber) {
		this.orderLineNumber = orderLineNumber;
	}
}
