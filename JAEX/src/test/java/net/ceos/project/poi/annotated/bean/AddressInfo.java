package net.ceos.project.poi.annotated.bean;

import net.ceos.project.poi.annotated.annotation.XlsElement;

public class AddressInfo {

	@XlsElement(title = "Address", position = 1)
	private String address;

	@XlsElement(title = "Number", position = 2)
	private int number;

	@XlsElement(title = "City", position = 3)
	private String city;

	@XlsElement(title = "City code", position = 4)
	private int cityCode;

	@XlsElement(title = "Country", position = 5)
	private String country;

	public AddressInfo() {
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * @param number
	 *            the number to set
	 */
	public void setNumber(int number) {
		this.number = number;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the cityCode
	 */
	public int getCityCode() {
		return cityCode;
	}

	/**
	 * @param cityCode
	 *            the cityCode to set
	 */
	public void setCityCode(int cityCode) {
		this.cityCode = cityCode;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
}
