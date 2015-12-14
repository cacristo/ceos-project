package org.jaexcel.framework.JAEX.definition;

public enum ExtensionFileType {
	XLSX(".xlsx"), XLS(".xls");

	private String extension;

	private ExtensionFileType(String s) {
		extension = s;
	}

	public String getExtension() {
		return extension;
	}
}
