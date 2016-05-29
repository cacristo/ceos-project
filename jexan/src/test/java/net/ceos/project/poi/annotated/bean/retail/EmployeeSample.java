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

import java.math.BigDecimal;
import java.util.Date;

import net.ceos.project.poi.annotated.annotation.XlsConfiguration;
import net.ceos.project.poi.annotated.annotation.XlsElement;
import net.ceos.project.poi.annotated.annotation.XlsFreezePane;
import net.ceos.project.poi.annotated.annotation.XlsGroupColumn;
import net.ceos.project.poi.annotated.annotation.XlsGroupElement;
import net.ceos.project.poi.annotated.annotation.XlsGroupRow;
import net.ceos.project.poi.annotated.annotation.XlsSheet;
import net.ceos.project.poi.annotated.definition.CascadeType;
import net.ceos.project.poi.annotated.definition.ExtensionFileType;

@XlsConfiguration(nameFile = "Employees", extensionFile = ExtensionFileType.XLSX)
@XlsSheet(title = "Employee Details", startCell=2, startRow=2, cascadeLevel = CascadeType.CASCADE_L1, freezePane = @XlsFreezePane(colSplit = 0, rowSplit = 1) , 
		groupElement = @XlsGroupElement(groupColumns = { @XlsGroupColumn(fromColumn = 1, toColumn = 3), @XlsGroupColumn(fromColumn = 6, toColumn = 12) }, groupRows = { @XlsGroupRow(fromRow = 0, toRow = 0) }) )
public class EmployeeSample {

	@XlsElement(title = "Number", position = 1)
	private Integer employeeNumber;

	@XlsElement(title = "Gender", position = 2)
	private Gender gender;

	@XlsElement(title = "Last name", position = 4, comment="Mr. / Ms.")
	private String lastName;

	@XlsElement(title = "First name", position = 3)
	private String firstName;

	@XlsElement(title = "Age", position = 5)
	private Short age;

	@XlsElement(title = "Experience in months", position = 6)
	private Double experienceYears;

	@XlsElement(title = "Phone extension", position = 7)
	private Long extension;

	@XlsElement(title = "E-mail", position = 8)
	private String email;

	@XlsElement(title = "Office code", position = 9)
	private String officeCode;

	@XlsElement(title = "Responsible", position = 10, transformMask="0.0000")
	private Integer reportsTo;

	@XlsElement(title = "Job title", position = 11, commentRules="rulesStoreComment", comment="Contact the store for more info")
	private JobTitle jobTitle;

	@XlsElement(title = "Salary", position = 12, transformMask="0.0")
	private BigDecimal salary;
	
	@XlsElement(title = "Salary with tax fee", position = 13, transformMask="0.00", isFormula=true, formula="M2 * 0.73")
	private BigDecimal cleanSalary;

	@XlsElement(title = "Average salary", position = 14, transformMask="0.00", isFormula=true, formula="AVERAGE(Midx,Nidx)")
	private BigDecimal avgSalary;
	
	@XlsElement(title = "Since at company", position = 15, transformMask="dd-MM-yyyy")
	private Date since;

	public EmployeeSample() {
		/* empty constructor */
	}
	
	/* getters, setters other methods here */

	public boolean rulesStoreComment(){
		if(this.jobTitle.equals(JobTitle.RETAIL_SUPERVISOR)){
			return true;
		}
		return false;
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
	 * @return the gender
	 */
	public final Gender getGender() {
		return gender;
	}

	/**
	 * @param gender
	 *            the gender to set
	 */
	public final void setGender(Gender gender) {
		this.gender = gender;
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
	 * @return the age
	 */
	public final Short getAge() {
		return age;
	}

	/**
	 * @param age
	 *            the age to set
	 */
	public final void setAge(Short age) {
		this.age = age;
	}

	/**
	 * @return the experienceYears
	 */
	public final Double getExperienceYears() {
		return experienceYears;
	}

	/**
	 * @param experienceYears
	 *            the experienceYears to set
	 */
	public final void setExperienceYears(Double experienceYears) {
		this.experienceYears = experienceYears;
	}

	/**
	 * @return the extension
	 */
	public final Long getExtension() {
		return extension;
	}

	/**
	 * @param extension
	 *            the extension to set
	 */
	public final void setExtension(Long extension) {
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
	public final JobTitle getJobTitle() {
		return jobTitle;
	}

	/**
	 * @param jobTitle
	 *            the jobTitle to set
	 */
	public final void setJobTitle(JobTitle jobTitle) {
		this.jobTitle = jobTitle;
	}

	/**
	 * @return the salary
	 */
	public final BigDecimal getSalary() {
		return salary;
	}

	/**
	 * @param salary
	 *            the salary to set
	 */
	public final void setSalary(BigDecimal salary) {
		this.salary = salary;
	}
	

	/**
	 * @return the cleanSalary
	 */
	public final BigDecimal getCleanSalary() {
		return cleanSalary;
	}

	/**
	 * @param cleanSalary the cleanSalary to set
	 */
	public final void setCleanSalary(BigDecimal cleanSalary) {
		this.cleanSalary = cleanSalary;
	}

	/**
	 * @return the since
	 */
	public final Date getSince() {
		return since;
	}

	/**
	 * @param since
	 *            the since to set
	 */
	public final void setSince(Date since) {
		this.since = since;
	}
}
