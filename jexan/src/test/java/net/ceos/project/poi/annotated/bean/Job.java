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

import net.ceos.project.poi.annotated.annotation.XlsElement;

public class Job {

	@XlsElement(title = "Name", position = 1)
	private String jobName;

	@XlsElement(title = "Code", position = 2)
	private int jobCode;

	@XlsElement(title = "Family", position = 3, comment="Family comment")
	private String jobFamily;
	
	public Job() {
	}

	/**
	 * @return the jobName
	 */
	public String getJobName() {
		return jobName;
	}

	/**
	 * @param jobName the jobName to set
	 */
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	/**
	 * @return the jobCode
	 */
	public int getJobCode() {
		return jobCode;
	}

	/**
	 * @param jobCode the jobCode to set
	 */
	public void setJobCode(int jobCode) {
		this.jobCode = jobCode;
	}

	/**
	 * @return the jobFamily
	 */
	public String getJobFamily() {
		return jobFamily;
	}

	/**
	 * @param jobFamily the jobFamily to set
	 */
	public void setJobFamily(String jobFamily) {
		this.jobFamily = jobFamily;
	}

}
