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

import net.ceos.project.poi.annotated.annotation.XlsConfiguration;
import net.ceos.project.poi.annotated.annotation.XlsElement;
import net.ceos.project.poi.annotated.annotation.XlsSheet;
import net.ceos.project.poi.annotated.definition.PropagationType;

@XlsConfiguration(nameFile = "ManageUnits")
@XlsSheet(title = "Units History", propagation = PropagationType.PROPAGATION_VERTICAL)
public class ObjectUnit {

	@XlsElement(title = "Unit name", position = 1)
	private String name;

	@XlsElement(title = "Purchase price", position = 2)
	private double purchasePrice;

	@XlsElement(title = "Sale price", position = 3)
	private double salePrice;

	@XlsElement(title = "Tax applied", position = 4)
	private int tax;

	//@XlsElement(title = "Sale price (with Tax)", isFormula = true, formula = "Eidx * (Fidx% + 1)", position = 5)
	@XlsElement(title = "Sale price (with Tax)", isFormula = true, formula = "idy5 * (idy6% + 1)", position = 5)
	private double salePriceTax;

	@XlsElement(title = "Number of units", position = 6)
	private int unitNumbers;

	@XlsElement(title = "Return of investment", isFormula = true, position = 7)
	private double unitROI;

	public ObjectUnit() {
	}

	public ObjectUnit(String name, double purchasePrice, double salePrice, int tax, int unitNumbers) {
		super();
		this.name = name;
		this.purchasePrice = purchasePrice;
		this.salePrice = salePrice;
		this.tax = tax;
		this.unitNumbers = unitNumbers;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the purchasePrice
	 */
	public double getPurchasePrice() {
		return purchasePrice;
	}

	/**
	 * @param purchasePrice
	 *            the purchasePrice to set
	 */
	public void setPurchasePrice(double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	/**
	 * @return the salePrice
	 */
	public double getSalePrice() {
		return salePrice;
	}

	/**
	 * @param salePrice
	 *            the salePrice to set
	 */
	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}

	/**
	 * @return the tax
	 */
	public int getTax() {
		return tax;
	}

	/**
	 * @param tax
	 *            the tax to set
	 */
	public void setTax(int tax) {
		this.tax = tax;
	}

	/**
	 * @return the salePriceTax
	 */
	public double getSalePriceTax() {
		return salePriceTax;
	}

	/**
	 * @param salePriceTax
	 *            the salePriceTax to set
	 */
	public void setSalePriceTax(double salePriceTax) {
		this.salePriceTax = salePriceTax;
	}

	/**
	 * @return the unitNumbers
	 */
	public int getUnitNumbers() {
		return unitNumbers;
	}

	/**
	 * @param unitNumbers
	 *            the unitNumbers to set
	 */
	public void setUnitNumbers(int unitNumbers) {
		this.unitNumbers = unitNumbers;
	}

	/**
	 * @return the unitROI
	 */
	public double getUnitROI() {
		return unitROI;
	}

	/**
	 * @param unitROI
	 *            the unitROI to set
	 */
	public void setUnitROI(double unitROI) {
		this.unitROI = unitROI;
	}

	/**
	 * @return the unitROI
	 */
	public double formulaUnitROI() {
		return (this.salePrice - this.purchasePrice) * this.unitNumbers;
	}
}
