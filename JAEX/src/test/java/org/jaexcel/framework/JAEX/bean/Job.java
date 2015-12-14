package org.jaexcel.framework.JAEX.bean;

import org.jaexcel.framework.JAEX.annotation.XlsElement;

public class Job {

	@XlsElement(title = "Name", position = 1)
	private String jobName;

	@XlsElement(title = "Code", position = 2)
	private int jobCode;

	@XlsElement(title = "Family", position = 3)
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
