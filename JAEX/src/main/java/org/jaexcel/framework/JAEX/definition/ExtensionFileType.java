package org.jaexcel.framework.JAEX.definition;

/**
 * This will define the file extension type. <br>
 * 
 * {@value = ExtensionFileType.XLSX } Related to the files with the extension .xlsx. <br>
 * {@value = ExtensionFileType.XLS } Related to the files with the extension .xls. <br>
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
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
