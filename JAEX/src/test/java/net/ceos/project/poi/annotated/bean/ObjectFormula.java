package net.ceos.project.poi.annotated.bean;

import net.ceos.project.poi.annotated.annotation.XlsConfiguration;
import net.ceos.project.poi.annotated.annotation.XlsElement;
import net.ceos.project.poi.annotated.annotation.XlsSheet;
import net.ceos.project.poi.annotated.definition.ExtensionFileType;

@XlsConfiguration(nameFile = "ObjectFormula", extensionFile = ExtensionFileType.XLSX)
@XlsSheet(title = "Formula test case")
public class ObjectFormula {

	@XlsElement(title = "Store", position = 1)
	private int store = 0;
	@XlsElement(title = "Online Store", position = 2)
	private int webStore = 0;

	@XlsElement(title = "Local sales", position = 3)
	private double valueLocal = 0;
	@XlsElement(title = "Regional sales", position = 4)
	private double valueRegion = 0;
	@XlsElement(title = "Country sales", position = 5)
	private double valueCountry = 0;

	@XlsElement(title = "Total Sales", isFormula = true, formula = "SUM(E3,F3,G3)", position = 6)
	private double totalValue = 0;
	
	@XlsElement(title = "Average sales", isFormula = true, formula = "AVERAGE(E3,F3,G3)", position = 7)
	private double avgValue = 0;

	@XlsElement(title = "ROI", isFormula = true, position = 8)
	private double generateValue = 0;
	
	@XlsElement(title = "Average sales", isFormula = true, formula = "IF(Eidx < Fidx,\"Over Budget\", \"OK\")", position = 9)
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
		return (1.40 * this.valueCountry / this.store + 1.14 * this.valueCountry / this.webStore ) ;
	}

	/**
	 * @return the someString
	 */
	public String getSomeString() {
		return someString;
	}

	/**
	 * @param someString the someString to set
	 */
	public void setSomeString(String someString) {
		this.someString = someString;
	}

}
