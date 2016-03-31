package net.ceos.project.poi.annotated.bean.retail;

public enum JobTitle {
	RETAIL_SUPERVISOR("Retail Supervisor"),
	TECHNICAL_SPECIALIST("Technical Spacialist"),
	DEPARTMENT_STORE_SUPERVISOR("Department Store Supervisor"),
	DEPARTMENT_STORE("Department Store"),
	RETAIL_SALES("Retail Sales"),
	SALES("Sales");

	private String title;

	private JobTitle(String t) {
		title = t;
	}

	public String getFamily() {
		return title;
	}
}
