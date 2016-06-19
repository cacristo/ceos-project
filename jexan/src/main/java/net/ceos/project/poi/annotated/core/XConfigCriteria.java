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
package net.ceos.project.poi.annotated.core;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import net.ceos.project.poi.annotated.annotation.XlsElement;
import net.ceos.project.poi.annotated.annotation.XlsFreezePane;
import net.ceos.project.poi.annotated.annotation.XlsGroupElement;
import net.ceos.project.poi.annotated.annotation.XlsSheet;
import net.ceos.project.poi.annotated.definition.CascadeType;
import net.ceos.project.poi.annotated.definition.ExceptionMessage;
import net.ceos.project.poi.annotated.definition.ExtensionFileType;
import net.ceos.project.poi.annotated.definition.PropagationType;
import net.ceos.project.poi.annotated.exception.ConfigurationException;
import net.ceos.project.poi.annotated.exception.ElementException;

/**
 * This class will define all the configuration criteria to be used internally
 * by the framework.
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public class XConfigCriteria {

	/* workbook parameters */
	private Workbook workbook;
	private Sheet sheet;
	private String titleSheet;
	private int tabColorSheet;
	private Row rowHeader;
	private Row row;
	private int startRow;
	private int startCell;
	private int startRowInmutable = -1;
	private int startCellInmutable = -1;
	private int lastCellIndex;

	/* sheet parameters */
	private XlsFreezePane freezePane;
	private XlsGroupElement groupElement;

	/* element parameters */
	private XlsElement element;
	private Field field;
	private Boolean resizeActive = true;

	/* workbook file parameters */
	private String fileName;
	private String completeFileName;
	private PropagationType propagation;
	private PropagationType overridePropagation;
	private ExtensionFileType extension;
	private ExtensionFileType overrideExtension;
	private CascadeType cascadeLevel;
	private CascadeType overrideCascadeLevel;

	/* cell style parameters */
	private Boolean uniqueCellStyle = false;
	private Map<String, CellStyle> stylesMap = new HashMap<String, CellStyle>();
	private Map<String, CellDecorator> cellDecoratorMap = new HashMap<String, CellDecorator>();
	private Map<String, CellStyle> cellStyleManager = new HashMap<String, CellStyle>();

	private Map<Integer, Integer> columnWidthMap = new HashMap<Integer, Integer>();

	/**
	 * Force the header cell decorator.
	 * 
	 * @param decorator
	 *            the {@link CellDecorator} to apply
	 */
	public final void overrideHeaderCellDecorator(final CellDecorator decorator) {
		if (decorator != null) {
			decorator.setDecoratorName(CellStyleHandler.CELL_DECORATOR_HEADER);
		}
		cellDecoratorMap.put(CellStyleHandler.CELL_DECORATOR_HEADER, decorator);
	}

	/**
	 * Force all cell decorators except the header decorator.
	 * 
	 * @param decorator
	 *            the {@link CellDecorator} to apply
	 */
	public final void overrideAllCellDecorators(final CellDecorator decorator) {
		this.uniqueCellStyle = true;
		overrideGenericCellDecorator(decorator);
		overrideBooleanCellDecorator(decorator);
		overrideDateCellDecorator(decorator);
		overrideNumericCellDecorator(decorator);
		overrideEnumCellDecorator(decorator);
	}

	/**
	 * Force the generic cell decorator.
	 * 
	 * @param decorator
	 *            the {@link CellDecorator} to apply
	 */
	public final void overrideGenericCellDecorator(final CellDecorator decorator) {
		if (decorator != null) {
			decorator.setDecoratorName(CellStyleHandler.CELL_DECORATOR_GENERIC);
		}
		cellDecoratorMap.put(CellStyleHandler.CELL_DECORATOR_GENERIC, decorator);
	}

	/**
	 * Force the numeric cell decorator.
	 * 
	 * @param decorator
	 *            the {@link CellDecorator} to apply
	 */
	public final void overrideNumericCellDecorator(final CellDecorator decorator) {
		if (decorator != null) {
			decorator.setDecoratorName(CellStyleHandler.CELL_DECORATOR_NUMERIC);
		}
		cellDecoratorMap.put(CellStyleHandler.CELL_DECORATOR_NUMERIC, decorator);
	}

	/**
	 * Force the boolean cell decorator.
	 * 
	 * @param decorator
	 *            the {@link CellDecorator} to apply
	 */
	public final void overrideBooleanCellDecorator(final CellDecorator decorator) {
		if (decorator != null) {
			decorator.setDecoratorName(CellStyleHandler.CELL_DECORATOR_BOOLEAN);
		}
		cellDecoratorMap.put(CellStyleHandler.CELL_DECORATOR_BOOLEAN, decorator);
	}

	/**
	 * Force the date cell decorator.
	 * 
	 * @param decorator
	 *            the {@link CellDecorator} to apply
	 */
	public final void overrideDateCellDecorator(final CellDecorator decorator) {
		if (decorator != null) {
			decorator.setDecoratorName(CellStyleHandler.CELL_DECORATOR_DATE);
		}
		cellDecoratorMap.put(CellStyleHandler.CELL_DECORATOR_DATE, decorator);
	}

	/**
	 * Force the enumeration cell decorator.
	 * 
	 * @param decorator
	 *            the {@link CellDecorator} to apply
	 */
	public final void overrideEnumCellDecorator(final CellDecorator decorator) {
		if (decorator != null) {
			decorator.setDecoratorName(CellStyleHandler.CELL_DECORATOR_ENUM);
		}
		cellDecoratorMap.put(CellStyleHandler.CELL_DECORATOR_ENUM, decorator);
	}

	/**
	 * Add a new Cell Decorator for a specific use case.
	 * 
	 * @param decoratorName
	 *            the decorator name
	 * @param decorator
	 *            the {@link CellDecorator} to apply
	 */
	public final void addSpecificCellDecorator(final String decoratorName, final CellDecorator decorator) {
		cellDecoratorMap.put(decoratorName, decorator);
	}

	/**
	 * Force the propagation type to apply at the Sheet.
	 * 
	 * @param type
	 *            the {@link PropagationType} to apply
	 */
	public final void overridePropagationType(final PropagationType type) {
		overridePropagation = type;
	}

	/**
	 * Force the extension type to apply at the Sheet.
	 * 
	 * @param type
	 *            the {@link ExtensionFileType} to apply
	 */
	public final void overrideExtensionType(final ExtensionFileType type) {
		overrideExtension = type;
	}

	/**
	 * Force the cascade level to apply at the Sheet.
	 * 
	 * @param type
	 *            the {@link CascadeType} to apply
	 */
	public final void overrideCascadeLevel(final CascadeType type) {
		overrideCascadeLevel = type;
	}

	/**
	 * Initialize Cell Decorator system.
	 * 
	 * @throws ConfigurationException
	 *             given when {@link CellStyle} initialization failed
	 */
	protected final void initializeCellDecorator() throws ConfigurationException {

		if (stylesMap.get(CellStyleHandler.CELL_DECORATOR_HEADER) == null) {
			stylesMap.put(CellStyleHandler.CELL_DECORATOR_HEADER,
					cellDecoratorMap.containsKey(CellStyleHandler.CELL_DECORATOR_HEADER)
							? CellStyleHandler.initializeCellStyleByCellDecorator(workbook,
									cellDecoratorMap.get(CellStyleHandler.CELL_DECORATOR_HEADER))
							: CellStyleHandler.initializeHeaderCellDecorator(workbook));
			cellDecoratorMap.remove(cellDecoratorMap.containsKey(CellStyleHandler.CELL_DECORATOR_HEADER));
		}
		if (stylesMap.get(CellStyleHandler.CELL_DECORATOR_GENERIC) == null) {
			stylesMap.put(CellStyleHandler.CELL_DECORATOR_GENERIC,
					cellDecoratorMap.containsKey(CellStyleHandler.CELL_DECORATOR_GENERIC)
							? CellStyleHandler.initializeCellStyleByCellDecorator(workbook,
									cellDecoratorMap.get(CellStyleHandler.CELL_DECORATOR_GENERIC))
							: CellStyleHandler.initializeGenericCellDecorator(workbook));
			cellDecoratorMap.remove(cellDecoratorMap.containsKey(CellStyleHandler.CELL_DECORATOR_GENERIC));
		}
		if (stylesMap.get(CellStyleHandler.CELL_DECORATOR_NUMERIC) == null) {
			stylesMap.put(CellStyleHandler.CELL_DECORATOR_NUMERIC,
					cellDecoratorMap.containsKey(CellStyleHandler.CELL_DECORATOR_NUMERIC)
							? CellStyleHandler.initializeCellStyleByCellDecorator(workbook,
									cellDecoratorMap.get(CellStyleHandler.CELL_DECORATOR_NUMERIC))
							: CellStyleHandler.initializeNumericCellDecorator(workbook));
			cellDecoratorMap.remove(cellDecoratorMap.containsKey(CellStyleHandler.CELL_DECORATOR_NUMERIC));
		}
		if (stylesMap.get(CellStyleHandler.CELL_DECORATOR_DATE) == null) {
			stylesMap.put(CellStyleHandler.CELL_DECORATOR_DATE,
					cellDecoratorMap.containsKey(CellStyleHandler.CELL_DECORATOR_DATE)
							? CellStyleHandler.initializeCellStyleByCellDecorator(workbook,
									cellDecoratorMap.get(CellStyleHandler.CELL_DECORATOR_DATE))
							: CellStyleHandler.initializeDateCellDecorator(workbook));
			cellDecoratorMap.remove(cellDecoratorMap.containsKey(CellStyleHandler.CELL_DECORATOR_DATE));
		}
		if (stylesMap.get(CellStyleHandler.CELL_DECORATOR_BOOLEAN) == null) {
			stylesMap.put(CellStyleHandler.CELL_DECORATOR_BOOLEAN,
					cellDecoratorMap.containsKey(CellStyleHandler.CELL_DECORATOR_BOOLEAN)
							? CellStyleHandler.initializeCellStyleByCellDecorator(workbook,
									cellDecoratorMap.get(CellStyleHandler.CELL_DECORATOR_BOOLEAN))
							: CellStyleHandler.initializeBooleanCellDecorator(workbook));
			cellDecoratorMap.remove(cellDecoratorMap.containsKey(CellStyleHandler.CELL_DECORATOR_BOOLEAN));
		}
		if (stylesMap.get(CellStyleHandler.CELL_DECORATOR_ENUM) == null) {
			stylesMap.put(CellStyleHandler.CELL_DECORATOR_ENUM,
					cellDecoratorMap.containsKey(CellStyleHandler.CELL_DECORATOR_ENUM)
							? CellStyleHandler.initializeCellStyleByCellDecorator(workbook,
									cellDecoratorMap.get(CellStyleHandler.CELL_DECORATOR_ENUM))
							: CellStyleHandler.initializeGenericCellDecorator(workbook));
			cellDecoratorMap.remove(cellDecoratorMap.containsKey(CellStyleHandler.CELL_DECORATOR_ENUM));
		}

		for (Map.Entry<String, CellDecorator> object : cellDecoratorMap.entrySet()) {
			if (uniqueCellStyle) {
				/*
				 * when activate unique style, set all styles with the declared
				 * style
				 */
				stylesMap.put(object.getKey(), CellStyleHandler.initializeCellStyleByCellDecorator(workbook,
						cellDecoratorMap.get(CellStyleHandler.CELL_DECORATOR_GENERIC)));
			} else {
				stylesMap.put(object.getKey(),
						CellStyleHandler.initializeCellStyleByCellDecorator(workbook, object.getValue()));
			}
		}
	}

	/**
	 * @return the columnWidthMap
	 */
	protected final Map<Integer, Integer> getColumnWidthMap() {
		return columnWidthMap;
	}

	/**
	 * Apply to sheet the column width defined.
	 */
	protected final void applyColumnWidthToSheet() {
		for (Map.Entry<Integer, Integer> column : columnWidthMap.entrySet()) {
			getSheet().setColumnWidth(column.getKey(), column.getValue() * 256);
		}
	}

	/**
	 * @return the workbook
	 */
	protected Workbook getWorkbook() {
		return workbook;
	}

	/**
	 * @param workbook
	 *            the workbook to set
	 */
	protected void setWorkbook(final Workbook workbook) {
		this.workbook = workbook;
	}

	/**
	 * @return the sheet
	 */
	protected Sheet getSheet() {
		return sheet;
	}

	/**
	 * @param sheet
	 *            the sheet to set
	 */
	protected void setSheet(final Sheet sheet) {
		this.sheet = sheet;
	}

	/**
	 * @return the titleSheet
	 */
	protected String getTitleSheet() {
		return titleSheet;
	}

	/**
	 * @param titleSheet
	 *            the titleSheet to set
	 */
	protected void setTitleSheet(final String titleSheet) {
		this.titleSheet = titleSheet;
	}

	/**
	 * @return the tabColorSheet
	 */
	protected final int getTabColorSheet() {
		return tabColorSheet;
	}

	/**
	 * @param tabColorSheet
	 *            the tabColorSheet to set
	 */
	protected final void setTabColorSheet(int tabColorSheet) {
		this.tabColorSheet = tabColorSheet;
	}

	/**
	 * @return the rowHeader
	 */
	protected Row getRowHeader() {
		return rowHeader;
	}

	/**
	 * @param rowHeader
	 *            the rowHeader to set
	 */
	protected void setRowHeader(final Row rowHeader) {
		this.rowHeader = rowHeader;
	}

	/**
	 * @return the row
	 */
	protected Row getRow() {
		return row;
	}

	/**
	 * @param row
	 *            the row to set
	 */
	protected void setRow(final Row row) {
		this.row = row;
	}

	/**
	 * @return the startRow
	 */
	protected int getStartRow() {
		return startRow - 1;
	}

	/**
	 * @param startRow
	 *            the startRow to set
	 */
	protected void setStartRow(final int startRow) {
		this.startRow = startRow;
		/* set only the defined startRow value */
		if (this.startRowInmutable == -1) {
			this.startRowInmutable = startRow;
		}
	}

	/**
	 * Return the defined startRow position at {@link XlsSheet}
	 * 
	 * @return the startRowInmutable
	 */
	protected int getStartRowInmutable() {
		return startRowInmutable + 1;
	}

	/**
	 * @return the startCell
	 */
	protected int getStartCell() {
		return startCell - 1;
	}

	/**
	 * @param startCell
	 *            the startCell to set
	 */
	protected void setStartCell(final int startCell) {
		this.startCell = startCell;
		/* set only the defined startCell value */
		if (this.startCellInmutable == -1) {
			this.startCellInmutable = startCell;
		}
	}

	/**
	 * Return the defined startCell position at {@link XlsSheet}
	 * 
	 * @return the startCellInmutable
	 */
	protected int getStartCellInmutable() {
		return startCellInmutable + 1;
	}

	/**
	 * @return the lastCellIndex
	 */
	protected final int getLastCellIndex() {
		return lastCellIndex;
	}

	/**
	 * Define the last cell index. Used for the case of {@link PropagationType}
	 * vertical.
	 * 
	 * @param lastCellIndex
	 *            the lastCellIndex to set
	 */
	protected final void setLastCellIndex(int lastCellIndex) {
		this.lastCellIndex = lastCellIndex > this.lastCellIndex ? lastCellIndex : this.lastCellIndex;
	}

	/**
	 * @return the field
	 */
	protected Field getField() {
		return field;
	}

	/**
	 * @param field
	 *            the field to set
	 */
	protected void setField(final Field field) {
		this.field = field;
	}

	/**
	 * @return the resizeActive
	 */
	protected final Boolean getResizeActive() {
		return resizeActive;
	}

	/**
	 * @param resizeActive
	 *            the resizeActive to set
	 */
	protected final void setResizeActive(Boolean resizeActive) {
		this.resizeActive = resizeActive;
	}

	/**
	 * Get the file name.
	 * 
	 * @return the fileName
	 */
	protected String getFileName() {
		return fileName;
	}

	/**
	 * Set the file name.
	 * 
	 * @param fileName
	 *            the fileName to set
	 */
	public void setFileName(final String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the completeFileName
	 */
	protected String getCompleteFileName() {
		return completeFileName;
	}

	/**
	 * @param completeFileName
	 *            the completeFileName to set
	 */
	protected void setCompleteFileName(final String completeFileName) {
		this.completeFileName = completeFileName;
	}

	/**
	 * @return the propagation
	 */
	protected PropagationType getPropagation() {
		if (overridePropagation != null) {
			return overridePropagation;
		}
		return propagation;
	}

	/**
	 * @param propagation
	 *            the propagation to set
	 */
	protected void setPropagation(final PropagationType propagation) {
		this.propagation = propagation;
	}

	/**
	 * @return the extension
	 */
	protected ExtensionFileType getExtension() {
		if (overrideExtension != null) {
			return overrideExtension;
		}
		return extension;
	}

	/**
	 * @param extension
	 *            the extension to set
	 */
	protected void setExtension(final ExtensionFileType extension) {
		this.extension = extension;
	}

	/**
	 * @return the cascadeLevel
	 */
	protected CascadeType getCascadeLevel() {
		if (overrideCascadeLevel != null) {
			return overrideCascadeLevel;
		}
		return cascadeLevel;
	}

	/**
	 * @param cascadeLevel
	 *            the cascadeLevel to set
	 */
	protected void setCascadeLevel(final CascadeType cascadeLevel) {
		this.cascadeLevel = cascadeLevel;
	}

	/**
	 * @return the element
	 */
	protected XlsElement getElement() {
		return element;
	}

	/**
	 * @param element
	 *            the element to set
	 */
	protected void setElement(final XlsElement element) {
		this.element = element;
	}

	/**
	 * @return the stylesMap
	 */
	protected Map<String, CellStyle> getStylesMap() {
		return stylesMap;
	}

	/**
	 * @param stylesMap
	 *            the stylesMap to set
	 */
	protected void setStylesMap(final Map<String, CellStyle> stylesMap) {
		this.stylesMap = stylesMap;
	}

	/**
	 * @return the cellStyleManager
	 */
	protected Map<String, CellStyle> getCellStyleManager() {
		return cellStyleManager;
	}

	/**
	 * @param cellStyleManager
	 *            the cellStyleManager to set
	 */
	protected void setCellStyleManager(final Map<String, CellStyle> cellStyleManager) {
		this.cellStyleManager = cellStyleManager;
	}

	/**
	 * @return the cellDecoratorMap
	 */
	protected Map<String, CellDecorator> getCellDecoratorMap() {
		return cellDecoratorMap;
	}

	/**
	 * @param cellDecoratorMap
	 *            the cellDecoratorMap to set
	 */
	protected void setCellDecoratorMap(final Map<String, CellDecorator> cellDecoratorMap) {
		this.cellDecoratorMap = cellDecoratorMap;
	}

	/**
	 * @return the freezePane
	 */
	protected final XlsFreezePane getFreezePane() {
		return freezePane;
	}

	/**
	 * @param freezePane
	 *            the freezePane to set
	 */
	protected final void setFreezePane(XlsFreezePane freezePane) {
		this.freezePane = freezePane;
	}

	/**
	 * @return the groupElement
	 */
	protected final XlsGroupElement getGroupElement() {
		return groupElement;
	}

	/**
	 * @param groupElement
	 *            the groupElement to set
	 */
	protected final void setGroupElement(XlsGroupElement groupElement) {
		this.groupElement = groupElement;
	}

	/**
	 * Get the CellStyle according the type of field.
	 * 
	 * @param type
	 *            the {@link CellStyleHandler} type
	 * @return the {@link CellStyle}
	 * @throws ElementException
	 *             given when {@link CellStyle} initialization failed
	 */
	protected CellStyle getCellStyle(final String type) throws ElementException {
		if (StringUtils.isNotBlank(element.decorator()) && stylesMap.get(element.decorator()) == null) {
			throw new ElementException(ExceptionMessage.CONFIGURATION_DECORATOR_MISSING.getMessage());
		}
		return StringUtils.isNotBlank(element.decorator()) ? stylesMap.get(element.decorator()) : stylesMap.get(type);
	}

	/**
	 * Get the mask to apply according the annotation formatMask.
	 * 
	 * @param maskDecoratorType
	 *            the {@link CellStyleHandler} mask decorator
	 * @return the format mask
	 */
	protected String getFormatMask(final String maskDecoratorType) {
		return StringUtils.isNotBlank(element.formatMask()) ? element.formatMask() : maskDecoratorType;
	}

	/**
	 * Get the mask to apply according the annotation transformationMask.
	 * 
	 * @param maskDecoratorType
	 *            the {@link CellStyleHandler} mask decorator
	 * @return the transformed mask
	 */
	protected String getTransformMask(final String maskDecoratorType) {
		return StringUtils.isNotBlank(element.transformMask()) ? element.transformMask() : maskDecoratorType;
	}

	/**
	 * Get the mask to apply according the annotations formatMask and
	 * transformationMask.
	 * 
	 * @param maskDecoratorType
	 *            the {@link CellStyleHandler} mask decorator
	 * @return the transform mask if present or the format mask (if present)
	 *         otherwise the default mask
	 */
	protected String getMask(final String maskDecoratorType) {
		return StringUtils.isNotBlank(element.transformMask()) ? element.transformMask()
				: (StringUtils.isNotBlank(element.formatMask()) ? element.formatMask() : maskDecoratorType);
	}

	/**
	 * Generate a key to identify the cell style.
	 * 
	 * @param cellDecoratorType
	 *            the decorator name
	 * @param maskDecoratorType
	 *            the mask format to apply
	 * @return the key based at : mask_decorator
	 */
	protected String generateCellStyleKey(final String cellDecoratorType, final String maskDecoratorType) {
		String decorator = StringUtils.isNotBlank(element.decorator()) ? element.decorator() : cellDecoratorType;
		String mask = StringUtils.isNotBlank(element.transformMask()) ? element.transformMask()
				: (StringUtils.isNotBlank(element.formatMask()) ? element.formatMask() : maskDecoratorType);
		return mask.concat(decorator);
	}
}
