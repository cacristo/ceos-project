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
import net.ceos.project.poi.annotated.definition.ExtensionFileType;

@XlsConfiguration(nameFile = "ObjectFormula", extensionFile = ExtensionFileType.XLSX)
@XlsSheet(title = "Formula test case")
public class ObjectFormula {

	@XlsElement(title = "Store", position = 1)
	private int store;
	@XlsElement(title = "Online Store", position = 2)
	private int webStore;

	@XlsElement(title = "Local sales", position = 3)
	private double valueLocal;
	@XlsElement(title = "Regional sales", position = 4)
	private double valueRegion;
	@XlsElement(title = "Country sales", position = 5)
	private double valueCountry;

	@XlsElement(title = "Total Sales", isFormula = true, formula = "SUM(D$,E$,F$)", position = 6)
	private double totalValue;

	@XlsElement(title = "Total Sales", isFormula = true, formula = "G$ * 1.21", position = 7)
	private Double totalValueTax;

	@XlsElement(title = "Average sales", isFormula = true, formula = "AVERAGE(D$,E$,F$)", position = 8)
	private double avgValue;

	@XlsElement(title = "ROI", isFormula = true, position = 9)
	private double generateValue;

	@XlsElement(title = "Average sales", isFormula = true, formula = "IF(D$ < E$,\"Over Budget\", \"OK\")", position = 10)
	private String someString;

	public ObjectFormula() {
	}

	/**
	 * @return the store
	 */
	public int getStore() {
		return store;
	}

	/**
	 * @param store
	 *            the store to set
	 */
	public void setStore(int store) {
		this.store = store;
	}

	/**
	 * @return the webStore
	 */
	public int getWebStore() {
		return webStore;
	}

	/**
	 * @param webStore
	 *            the webStore to set
	 */
	public void setWebStore(int webStore) {
		this.webStore = webStore;
	}

	/**
	 * @return the valueLocal
	 */
	public double getValueLocal() {
		return valueLocal;
	}

	/**
	 * @param valueLocal
	 *            the valueLocal to set
	 */
	public void setValueLocal(double valueLocal) {
		this.valueLocal = valueLocal;
	}

	/**
	 * @return the valueRegion
	 */
	public double getValueRegion() {
		return valueRegion;
	}

	/**
	 * @param valueRegion
	 *            the valueRegion to set
	 */
	public void setValueRegion(double valueRegion) {
		this.valueRegion = valueRegion;
	}

	/**
	 * @return the valueCountry
	 */
	public double getValueCountry() {
		return valueCountry;
	}

	/**
	 * @param valueCountry
	 *            the valueCountry to set
	 */
	public void setValueCountry(double valueCountry) {
		this.valueCountry = valueCountry;
	}

	/**
	 * @return the totalValue
	 */
	public double getTotalValue() {
		return totalValue;
	}

	/**
	 * @param totalValue
	 *            the totalValue to set
	 */
	public void setTotalValue(double totalValue) {
		this.totalValue = totalValue;
	}

	/**
	 * @return the totalValueTax
	 */
	public final double getTotalValueTax() {
		return totalValueTax;
	}

	/**
	 * @param totalValueTax
	 *            the totalValueTax to set
	 */
	public final void setTotalValueTax(double totalValueTax) {
		this.totalValueTax = totalValueTax;
	}

	/**
	 * @return the avgValue
	 */
	public double getAvgValue() {
		return avgValue;
	}

	/**
	 * @param avgValue
	 *            the avgValue to set
	 */
	public void setAvgValue(double avgValue) {
		this.avgValue = avgValue;
	}

	/**
	 * @return the generateValue
	 */
	public double getGenerateValue() {
		return generateValue;
	}

	/**
	 * @param generateValue
	 *            the generateValue to set
	 */
	public void setGenerateValue(double generateValue) {
		this.generateValue = generateValue;
	}

	/**
	 * @return the generateValue
	 */
	public double formulaGenerateValue() {
		return (1.40 * this.valueCountry / this.store + 1.14 * this.valueCountry / this.webStore);
	}

	/**
	 * @return the someString
	 */
	public String getSomeString() {
		return someString;
	}

	/**
	 * @param someString
	 *            the someString to set
	 */
	public void setSomeString(String someString) {
		this.someString = someString;
	}

}
