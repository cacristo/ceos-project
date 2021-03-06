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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import net.ceos.project.poi.annotated.annotation.XlsConfiguration;
import net.ceos.project.poi.annotated.annotation.XlsElement;
import net.ceos.project.poi.annotated.annotation.XlsSheet;
import net.ceos.project.poi.annotated.definition.ExtensionFileType;

@XlsSheet(title = "Organizations")
@XlsConfiguration(nameFile = "Organizations", extensionFile = ExtensionFileType.XLSX)
public class Organization {

	@XlsElement(title = "Company name", position = 1)
	private String name;
	@XlsElement(title = "Company alias", position = 1)
	private String alias;

	@XlsElement(title = "Date of creation", position = 1)
	private Date creationDate;

	@XlsElement(title = "Founder", position = 1)
	private Contact organizationContact;

	private ArrayList<Contact> organizationEmployees;
	private List<Organization> organizationGroup;
	private Set<Job> jobList;

	public Organization() {
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
	 * @return the alias
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * @param alias
	 *            the alias to set
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

	/**
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate
	 *            the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * @return the organizationContact
	 */
	public Contact getOrganizationContact() {
		return organizationContact;
	}

	/**
	 * @param organizationContact
	 *            the organizationContact to set
	 */
	public void setOrganizationContact(Contact organizationContact) {
		this.organizationContact = organizationContact;
	}

	/**
	 * @return the organizationEmployees
	 */
	public ArrayList<Contact> getOrganizationEmployees() {
		return organizationEmployees;
	}

	/**
	 * @param organizationEmployees
	 *            the organizationEmployees to set
	 */
	public void setOrganizationEmployees(
			ArrayList<Contact> organizationEmployees) {
		this.organizationEmployees = organizationEmployees;
	}

	/**
	 * @return the organizationGroup
	 */
	public List<Organization> getOrganizationGroup() {
		return organizationGroup;
	}

	/**
	 * @param organizationGroup
	 *            the organizationGroup to set
	 */
	public void setOrganizationGroup(List<Organization> organizationGroup) {
		this.organizationGroup = organizationGroup;
	}

	/**
	 * @return the jobList
	 */
	public Set<Job> getJobList() {
		return jobList;
	}

	/**
	 * @param jobList
	 *            the jobList to set
	 */
	public void setJobList(Set<Job> jobList) {
		this.jobList = jobList;
	}

}
