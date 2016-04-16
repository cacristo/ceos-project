package net.ceos.project.poi.annotated.core;

import java.util.HashMap;
import java.util.Map;

import net.ceos.project.poi.annotated.definition.CascadeType;
import net.ceos.project.poi.annotated.definition.ExtensionFileType;

/**
 * This class will define all the configuration criteria to be used internally
 * by the framework. <br>
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public class CConfigCriteria {

	private String fileName;
	private CascadeType cascadeLevel;

	private String titleSheet;

	private String separator = ",";
	private String overrideSeparator;
	private Boolean isHeaderPainted = Boolean.FALSE;
	private Map<Integer, String> header = new HashMap<>();
	private Map<Integer, String> content = new HashMap<>();
	
	public CConfigCriteria() {
		/* empty constructor */
	}

	/**
	 * @return the fileName
	 */
	protected final String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	protected final void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the completeFileName
	 */
	protected final String getCompleteFileName() {
		return fileName + getExtensionFile().getExtension();
	}

	/**
	 * @return the extensionFile
	 */
	protected final ExtensionFileType getExtensionFile() {
		return ExtensionFileType.CSV;
	}

	/**
	 * @return the cascadeLevel
	 */
	protected final CascadeType getCascadeLevel() {
		return cascadeLevel;
	}

	/**
	 * @param cascadeLevel the cascadeLevel to set
	 */
	protected final void setCascadeLevel(CascadeType cascadeLevel) {
		this.cascadeLevel = cascadeLevel;
	}

	/**
	 * @return the titleSheet
	 */
	protected final String getTitleSheet() {
		return titleSheet;
	}

	/**
	 * @param titleSheet the titleSheet to set
	 */
	protected final void setTitleSheet(String titleSheet) {
		this.titleSheet = titleSheet;
	}

	/**
	 * @return the separator
	 */
	protected final String getSeparator() {
		if (overrideSeparator != null) {
			return overrideSeparator;
		}
		return separator;
	}

	/**
	 * @param separator the separator to set
	 */
	protected final void setSeparator(String separator) {
		this.separator = separator;
	}

	/**
	 * @return the overrideSeparator
	 */
	protected final String getOverrideSeparator() {
		return overrideSeparator;
	}

	/**
	 * @param overrideSeparator the overrideSeparator to set
	 */
	protected final void setOverrideSeparator(String overrideSeparator) {
		this.overrideSeparator = overrideSeparator;
	}

	/**
	 * @return the isHeaderPainted
	 */
	protected final Boolean isHeaderPainted() {
		return isHeaderPainted;
	}

	/**
	 * @param isHeaderPainted the isHeaderPainted to set
	 */
	protected final void setIsHeaderPainted(Boolean isHeaderPainted) {
		this.isHeaderPainted = isHeaderPainted;
	}

	/**
	 * @return the header
	 */
	protected final Map<Integer, String> getHeader() {
		return header;
	}

	/**
	 * @param header the header to set
	 */
	protected final void setHeader(Map<Integer, String> header) {
		this.header = header;
	}

	/**
	 * @return the content
	 */
	protected final Map<Integer, String> getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	protected final void setContent(Map<Integer, String> content) {
		this.content = content;
	}
}
