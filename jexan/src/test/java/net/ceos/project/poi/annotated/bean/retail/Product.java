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
 * The product entity
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public class Product {

	private String productCode;
	private String productName;
	private String productLine;
	private String productScale;
	private String productVendor;
	private String pronductDescription;
	private Integer quantityInStock;
	private Double buyPrice;
	private Double MSRP;

	public Product() {
		/* empty constructor */
	}

	/**
	 * @return the productCode
	 */
	public final String getProductCode() {
		return productCode;
	}

	/**
	 * @param productCode the productCode to set
	 */
	public final void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	/**
	 * @return the productName
	 */
	public final String getProductName() {
		return productName;
	}

	/**
	 * @param productName the productName to set
	 */
	public final void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * @return the productLine
	 */
	public final String getProductLine() {
		return productLine;
	}

	/**
	 * @param productLine the productLine to set
	 */
	public final void setProductLine(String productLine) {
		this.productLine = productLine;
	}

	/**
	 * @return the productScale
	 */
	public final String getProductScale() {
		return productScale;
	}

	/**
	 * @param productScale the productScale to set
	 */
	public final void setProductScale(String productScale) {
		this.productScale = productScale;
	}

	/**
	 * @return the productVendor
	 */
	public final String getProductVendor() {
		return productVendor;
	}

	/**
	 * @param productVendor the productVendor to set
	 */
	public final void setProductVendor(String productVendor) {
		this.productVendor = productVendor;
	}

	/**
	 * @return the pronductDescription
	 */
	public final String getPronductDescription() {
		return pronductDescription;
	}

	/**
	 * @param pronductDescription the pronductDescription to set
	 */
	public final void setPronductDescription(String pronductDescription) {
		this.pronductDescription = pronductDescription;
	}

	/**
	 * @return the quantityInStock
	 */
	public final Integer getQuantityInStock() {
		return quantityInStock;
	}

	/**
	 * @param quantityInStock the quantityInStock to set
	 */
	public final void setQuantityInStock(Integer quantityInStock) {
		this.quantityInStock = quantityInStock;
	}

	/**
	 * @return the buyPrice
	 */
	public final Double getBuyPrice() {
		return buyPrice;
	}

	/**
	 * @param buyPrice the buyPrice to set
	 */
	public final void setBuyPrice(Double buyPrice) {
		this.buyPrice = buyPrice;
	}

	/**
	 * @return the mSRP
	 */
	public final Double getMSRP() {
		return MSRP;
	}

	/**
	 * @param mSRP the mSRP to set
	 */
	public final void setMSRP(Double mSRP) {
		MSRP = mSRP;
	}
	
	
}
