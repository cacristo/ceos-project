package net.ceos.project.poi.annotated.bean.retail;

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
