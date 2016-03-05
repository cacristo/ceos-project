package net.ceos.project.poi.annotated.definition;

/**
 * This will define the file extension type. <br>
 * 
 * {@value = ExtensionFileType.XLSX } Related to the files with the extension .xlsx. <br>
 * {@value = ExtensionFileType.XLS } Related to the files with the extension .xls. <br>
 * {@value = ExtensionFileType.CSV } Related to the files with the extension .csv. <br>
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public enum ExtensionFileType {
	XLSX(".xlsx"), XLS(".xls"), CSV(".csv");

	private String extension;

	private ExtensionFileType(String s) {
		extension = s;
	}

	public String getExtension() {
		return extension;
	}
}