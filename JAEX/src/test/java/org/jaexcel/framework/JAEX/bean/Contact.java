package org.jaexcel.framework.JAEX.bean;

import org.jaexcel.framework.JAEX.annotation.XlsConfiguration;
import org.jaexcel.framework.JAEX.annotation.XlsElement;
import org.jaexcel.framework.JAEX.annotation.XlsSheet;

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
