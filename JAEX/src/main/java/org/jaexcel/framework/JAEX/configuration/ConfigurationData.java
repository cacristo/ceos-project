package org.jaexcel.framework.JAEX.configuration;

import org.jaexcel.framework.JAEX.definition.CascadeType;
import org.jaexcel.framework.JAEX.definition.ExtensionFileType;
import org.jaexcel.framework.JAEX.definition.PropagationType;

public class ConfigurationData {

	private String name;
	private String nameFile;
	private ExtensionFileType extensionFile;
	private PropagationType propagationType;

	private String titleSheet;
	private int startRow;
	private int startCell;
	private CascadeType cascadeLevel;
	
	public ConfigurationData() {
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the nameFile
	 */
	public String getNameFile() {
		return nameFile;
	}

	/**
	 * @param nameFile the nameFile to set
	 */
	public void setNameFile(String nameFile) {
		this.nameFile = nameFile;
	}

	/**
	 * @return the extensionFile
	 */
	public ExtensionFileType getExtensionFile() {
		return extensionFile;
	}

	/**
	 * @param extensionFile the extensionFile to set
	 */
	public void setExtensionFile(ExtensionFileType extensionFile) {
		this.extensionFile = extensionFile;
	}

	/**
	 * @return the cascadeLevel
	 */
	public CascadeType getCascadeLevel() {
		return cascadeLevel;
	}

	/**
	 * @param cascadeLevel the cascadeLevel to set
	 */
	public void setCascadeLevel(CascadeType cascadeLevel) {
		this.cascadeLevel = cascadeLevel;
	}

	/**
	 * @return the propagationType
	 */
	public PropagationType getPropagationType() {
		return propagationType;
	}

	/**
	 * @param propagationType the propagationType to set
	 */
	public void setPropagationType(PropagationType propagationType) {
		this.propagationType = propagationType;
	}

	/**
	 * @return the titleSheet
	 */
	public String getTitleSheet() {
		return titleSheet;
	}

	/**
	 * @param titleSheet the titleSheet to set
	 */
	public void setTitleSheet(String titleSheet) {
		this.titleSheet = titleSheet;
	}

	/**
	 * @return the startRow
	 */
	public int getStartRow() {
		return startRow;
	}

	/**
	 * @param startRow the startRow to set
	 */
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	/**
	 * @return the startCell
	 */
	public int getStartCell() {
		return startCell;
	}

	/**
	 * @param startCell the startCell to set
	 */
	public void setStartCell(int startCell) {
		this.startCell = startCell;
	}
}
