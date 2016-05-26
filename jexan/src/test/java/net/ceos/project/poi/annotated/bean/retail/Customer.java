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
 * The customer entity
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public class Customer {

	private Integer customerNumber;
	private String customerName;
	private String contactLastName;
	private String contactFirstName;
	private String phone;
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String state;
	private String country;
	private String postalCode;
	private Integer salesRepEmployeeNumber;
	private Integer creditLimit;

	public Customer() {
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
	 * @return the customerName
	 */
	public final String getCustomerName() {
		return customerName;
	}

	/**
	 * @param customerName
	 *            the customerName to set
	 */
	public final void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * @return the contactLastName
	 */
	public final String getContactLastName() {
		return contactLastName;
	}

	/**
	 * @param contactLastName
	 *            the contactLastName to set
	 */
	public final void setContactLastName(String contactLastName) {
		this.contactLastName = contactLastName;
	}

	/**
	 * @return the contactFirstName
	 */
	public final String getContactFirstName() {
		return contactFirstName;
	}

	/**
	 * @param contactFirstName
	 *            the contactFirstName to set
	 */
	public final void setContactFirstName(String contactFirstName) {
		this.contactFirstName = contactFirstName;
	}

	/**
	 * @return the phone
	 */
	public final String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *            the phone to set
	 */
	public final void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the addressLine1
	 */
	public final String getAddressLine1() {
		return addressLine1;
	}

	/**
	 * @param addressLine1
	 *            the addressLine1 to set
	 */
	public final void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	/**
	 * @return the addressLine2
	 */
	public final String getAddressLine2() {
		return addressLine2;
	}

	/**
	 * @param addressLine2
	 *            the addressLine2 to set
	 */
	public final void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	/**
	 * @return the city
	 */
	public final String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public final void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public final String getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public final void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the country
	 */
	public final String getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public final void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the postalCode
	 */
	public final String getPostalCode() {
		return postalCode;
	}

	/**
	 * @param postalCode
	 *            the postalCode to set
	 */
	public final void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * @return the salesRepEmployeeNumber
	 */
	public final Integer getSalesRepEmployeeNumber() {
		return salesRepEmployeeNumber;
	}

	/**
	 * @param salesRepEmployeeNumber
	 *            the salesRepEmployeeNumber to set
	 */
	public final void setSalesRepEmployeeNumber(Integer salesRepEmployeeNumber) {
		this.salesRepEmployeeNumber = salesRepEmployeeNumber;
	}

	/**
	 * @return the creditLimit
	 */
	public final Integer getCreditLimit() {
		return creditLimit;
	}

	/**
	 * @param creditLimit
	 *            the creditLimit to set
	 */
	public final void setCreditLimit(Integer creditLimit) {
		this.creditLimit = creditLimit;
	}
}
