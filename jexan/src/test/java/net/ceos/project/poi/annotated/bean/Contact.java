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

@XlsSheet(title = "Contacts")
@XlsConfiguration(nameFile = "Contacts")
public class Contact {

	@XlsElement(title = "Title", position = 1)
	private String title;
	
	@XlsElement(title = "First name", position = 2)
	private String firstName;
	
	@XlsElement(title = "Middle name", position = 3)
	private String middleName;
	
	@XlsElement(title = "Last name", position = 4)
	private String lastName;

	@XlsElement(title = "Job", position = 5)
	private Job jobContact;
	
	@XlsElement(title = "Contact phone", position = 6)
	private String phone;
	
	@XlsElement(title = "Contact address", position = 7)
	private AddressInfo addressContact;

	public Contact() {
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * @param middleName the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the jobContact
	 */
	public Job getJobContact() {
		return jobContact;
	}

	/**
	 * @param jobContact the jobContact to set
	 */
	public void setJobContact(Job jobContact) {
		this.jobContact = jobContact;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the addressContact
	 */
	public AddressInfo getAddressContact() {
		return addressContact;
	}

	/**
	 * @param addressContact the addressContact to set
	 */
	public void setAddressContact(AddressInfo addressContact) {
		this.addressContact = addressContact;
	}

}
