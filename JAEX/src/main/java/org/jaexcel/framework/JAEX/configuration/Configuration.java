package org.jaexcel.framework.JAEX.configuration;

import org.jaexcel.framework.JAEX.definition.CascadeType;
import org.jaexcel.framework.JAEX.definition.ExtensionFileType;
import org.jaexcel.framework.JAEX.definition.PropagationType;

/**
 * This will define all the configuration to be used internally by the
 * framework. <br>
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public class Configuration {

	private String name;
	private String nameFile;
	private ExtensionFileType extensionFile;
	private PropagationType propagationType;
	private CascadeType cascadeLevel;

	private String titleSheet;
	private int startRow;
	private int startCell;

	public Configuration() {
	}

	/**
	 * Get the file name.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the file name.
	 * 
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get the file name (with the extension).
	 * 
	 * @return the nameFile
	 */
	public String getNameFile() {
		return nameFile;
	}

	/**
	 * Set the file name (with the extension).
	 * 
	 * @param nameFile
	 *            the nameFile to set
	 */
	public void setNameFile(String nameFile) {
		this.nameFile = nameFile;
	}

	/**
	 * Get the extension file.
	 * 
	 * @return the extensionFile
	 */
	public ExtensionFileType getExtensionFile() {
		return extensionFile;
	}

	/**
	 * Set the extension file.
	 * 
	 * @param extensionFile
	 *            the extensionFile to set
	 */
	public void setExtensionFile(ExtensionFileType extensionFile) {
		this.extensionFile = extensionFile;
	}

	/**
	 * Get the cascade level.
	 * 
	 * @return the cascadeLevel
	 */
	public CascadeType getCascadeLevel() {
		return cascadeLevel;
	}

	/**
	 * Set the cascade level.
	 * 
	 * @param cascadeLevel
	 *            the cascadeLevel to set
	 */
	public void setCascadeLevel(CascadeType cascadeLevel) {
		this.cascadeLevel = cascadeLevel;
	}

	/**
	 * Get the propagation type.
	 * 
	 * @return the propagationType
	 */
	public PropagationType getPropagationType() {
		return propagationType;
	}

	/**
	 * Set the propagation type.
	 * 
	 * @param propagationType
	 *            the propagationType to set
	 */
	public void setPropagationType(PropagationType propagationType) {
		this.propagationType = propagationType;
	}

	/**
	 * Get the title of the sheet.
	 * 
	 * @return the titleSheet
	 */
	public String getTitleSheet() {
		return titleSheet;
	}

	/**
	 * Set the title of the sheet.
	 * 
	 * @param titleSheet
	 *            the titleSheet to set
	 */
	public void setTitleSheet(String titleSheet) {
		this.titleSheet = titleSheet;
	}

	/**
	 * Get the row to start draw.
	 * 
	 * @return the startRow
	 */
	public int getStartRow() {
		return startRow;
	}

	/**
	 * Set the row to start draw.
	 * 
	 * @param startRow
	 *            the startRow to set
	 */
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	/**
	 * Get the cell to start draw.
	 * 
	 * @return the startCell
	 */
	public int getStartCell() {
		return startCell;
	}

	/**
	 * Set the cell to start draw.
	 * 
	 * @param startCell
	 *            the startCell to set
	 */
	public void setStartCell(int startCell) {
		this.startCell = startCell;
	}
}
