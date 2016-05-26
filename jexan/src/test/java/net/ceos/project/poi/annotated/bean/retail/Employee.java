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
 * The employee entity
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public class Employee {

	private Integer employeeNumber;
	private String lastName;
	private String firstName;
	private Integer extension;
	private String email;
	private String officeCode;
	private Integer reportsTo;
	private String jobTitle;

	public Employee() {
		/* empty constructor */
	}

	/**
	 * @return the employeeNumber
	 */
	public final Integer getEmployeeNumber() {
		return employeeNumber;
	}

	/**
	 * @param employeeNumber
	 *            the employeeNumber to set
	 */
	public final void setEmployeeNumber(Integer employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	/**
	 * @return the lastName
	 */
	public final String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public final void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the firstName
	 */
	public final String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public final void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the extension
	 */
	public final Integer getExtension() {
		return extension;
	}

	/**
	 * @param extension
	 *            the extension to set
	 */
	public final void setExtension(Integer extension) {
		this.extension = extension;
	}

	/**
	 * @return the email
	 */
	public final String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public final void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the officeCode
	 */
	public final String getOfficeCode() {
		return officeCode;
	}

	/**
	 * @param officeCode
	 *            the officeCode to set
	 */
	public final void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	/**
	 * @return the reportsTo
	 */
	public final Integer getReportsTo() {
		return reportsTo;
	}

	/**
	 * @param reportsTo
	 *            the reportsTo to set
	 */
	public final void setReportsTo(Integer reportsTo) {
		this.reportsTo = reportsTo;
	}

	/**
	 * @return the jobTitle
	 */
	public final String getJobTitle() {
		return jobTitle;
	}

	/**
	 * @param jobTitle
	 *            the jobTitle to set
	 */
	public final void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
}
