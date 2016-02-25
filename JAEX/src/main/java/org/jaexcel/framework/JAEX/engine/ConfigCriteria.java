package org.jaexcel.framework.JAEX.engine;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.jaexcel.framework.JAEX.annotation.XlsElement;
import org.jaexcel.framework.JAEX.definition.CascadeType;
import org.jaexcel.framework.JAEX.definition.ExceptionMessage;
import org.jaexcel.framework.JAEX.definition.ExtensionFileType;
import org.jaexcel.framework.JAEX.definition.PropagationType;
import org.jaexcel.framework.JAEX.exception.ConfigurationException;
import org.jaexcel.framework.JAEX.exception.ElementException;

/**
 * This will define all the configuration criteria to be used internally by the
 * framework. <br>
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public class ConfigCriteria {

	/* workbook parameters */
	private Workbook workbook;
	private Sheet sheet;
	private String titleSheet;
	private Row rowHeader;
	private Row row;
	private int startRow;
	private int startCell;

	/* element parameters */
	private XlsElement element;
	private Field field;

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
	private Map<String, CellStyle> stylesMap = new HashMap<String, CellStyle>();
	private Map<String, CellDecorator> cellDecoratorMap = new HashMap<String, CellDecorator>();

	private Map<String, CellStyle> cellStyleManager = new HashMap<String, CellStyle>();

	/**
	 * Force the header cell decorator.
	 * 
	 * @param decorator
	 *            the {@link CellDecorator} to apply
	 */
	public void overrideHeaderCellDecorator(CellDecorator decorator) {
		cellDecoratorMap.put(CellStyleUtils.CELL_DECORATOR_HEADER, decorator);
	}

	/**
	 * Force the numeric cell decorator.
	 * 
	 * @param decorator
	 *            the {@link CellDecorator} to apply
	 */
	public void overrideNumericCellDecorator(CellDecorator decorator) {
		cellDecoratorMap.put(CellStyleUtils.CELL_DECORATOR_NUMERIC, decorator);
	}

	/**
	 * Force the boolean cell decorator.
	 * 
	 * @param decorator
	 *            the {@link CellDecorator} to apply
	 */
	public void overrideBooleanCellDecorator(CellDecorator decorator) {
		cellDecoratorMap.put(CellStyleUtils.CELL_DECORATOR_BOOLEAN, decorator);
	}

	/**
	 * Force the date cell decorator.
	 * 
	 * @param decorator
	 *            the {@link CellDecorator} to apply
	 */
	public void overrideDateCellDecorator(CellDecorator decorator) {
		cellDecoratorMap.put(CellStyleUtils.CELL_DECORATOR_DATE, decorator);
	}

	/**
	 * Add a new Cell Decorator for a specific use case.
	 * 
	 * @param decoratorName
	 *            the decorator name
	 * @param decorator
	 *            the {@link CellDecorator} to apply
	 */
	public void addSpecificCellDecorator(String decoratorName, CellDecorator decorator) {
		cellDecoratorMap.put(decoratorName, decorator);
	}

	/**
	 * Force the propagation type to apply at the Sheet.
	 * 
	 * @param type
	 *            the {@link PropagationType} to apply
	 */
	public void overridePropagationType(PropagationType type) {
		overridePropagation = type;
	}

	/**
	 * Force the extension type to apply at the Sheet.
	 * 
	 * @param type
	 *            the {@link ExtensionFileType} to apply
	 */
	public void overrideExtensionType(ExtensionFileType type) {
		overrideExtension = type;
	}

	/**
	 * Force the cascade level to apply at the Sheet.
	 * 
	 * @param type
	 *            the {@link CascadeType} to apply
	 */
	public void overrideCascadeLevel(CascadeType type) {
		overrideCascadeLevel = type;
	}

	/**
	 * Initialize Cell Decorator system.
	 * 
	 * @param wb
	 *            the {@link Workbook} in use
	 * @throws ConfigurationException
	 */
	protected void initializeCellDecorator() throws ConfigurationException {

		if (stylesMap.get(CellStyleUtils.CELL_DECORATOR_HEADER) == null) {
			stylesMap.put(CellStyleUtils.CELL_DECORATOR_HEADER,
					cellDecoratorMap.containsKey(CellStyleUtils.CELL_DECORATOR_HEADER)
							? CellStyleUtils.initializeCellStyleByCellDecorator(workbook,
									cellDecoratorMap.get(CellStyleUtils.CELL_DECORATOR_HEADER))
							: CellStyleUtils.initializeHeaderCellDecorator(workbook));
			cellDecoratorMap.remove(cellDecoratorMap.containsKey(CellStyleUtils.CELL_DECORATOR_HEADER));
		}
		if (stylesMap.get(CellStyleUtils.CELL_DECORATOR_NUMERIC) == null) {
			stylesMap.put(CellStyleUtils.CELL_DECORATOR_NUMERIC,
					cellDecoratorMap.containsKey(CellStyleUtils.CELL_DECORATOR_NUMERIC)
							? CellStyleUtils.initializeCellStyleByCellDecorator(workbook,
									cellDecoratorMap.get(CellStyleUtils.CELL_DECORATOR_NUMERIC))
							: CellStyleUtils.initializeNumericCellDecorator(workbook));
			cellDecoratorMap.remove(cellDecoratorMap.containsKey(CellStyleUtils.CELL_DECORATOR_NUMERIC));
		}
		if (stylesMap.get(CellStyleUtils.CELL_DECORATOR_DATE) == null) {
			stylesMap.put(CellStyleUtils.CELL_DECORATOR_DATE,
					cellDecoratorMap.containsKey(CellStyleUtils.CELL_DECORATOR_DATE)
							? CellStyleUtils.initializeCellStyleByCellDecorator(workbook,
									cellDecoratorMap.get(CellStyleUtils.CELL_DECORATOR_DATE))
							: CellStyleUtils.initializeDateCellDecorator(workbook));
			cellDecoratorMap.remove(cellDecoratorMap.containsKey(CellStyleUtils.CELL_DECORATOR_DATE));
		}
		if (stylesMap.get(CellStyleUtils.CELL_DECORATOR_BOOLEAN) == null) {
			stylesMap.put(CellStyleUtils.CELL_DECORATOR_BOOLEAN,
					cellDecoratorMap.containsKey(CellStyleUtils.CELL_DECORATOR_BOOLEAN)
							? CellStyleUtils.initializeCellStyleByCellDecorator(workbook,
									cellDecoratorMap.get(CellStyleUtils.CELL_DECORATOR_BOOLEAN))
							: CellStyleUtils.initializeBooleanCellDecorator(workbook));
			cellDecoratorMap.remove(cellDecoratorMap.containsKey(CellStyleUtils.CELL_DECORATOR_BOOLEAN));
		}
		if (stylesMap.get(CellStyleUtils.CELL_DECORATOR_GENERIC) == null) {
			stylesMap.put(CellStyleUtils.CELL_DECORATOR_GENERIC,
					cellDecoratorMap.containsKey(CellStyleUtils.CELL_DECORATOR_GENERIC)
							? CellStyleUtils.initializeCellStyleByCellDecorator(workbook,
									cellDecoratorMap.get(CellStyleUtils.CELL_DECORATOR_GENERIC))
							: CellStyleUtils.initializeGenericCellDecorator(workbook));
			cellDecoratorMap.remove(cellDecoratorMap.containsKey(CellStyleUtils.CELL_DECORATOR_GENERIC));
		}
		if (stylesMap.get(CellStyleUtils.CELL_DECORATOR_ENUM) == null) {
			stylesMap.put(CellStyleUtils.CELL_DECORATOR_ENUM,
					cellDecoratorMap.containsKey(CellStyleUtils.CELL_DECORATOR_ENUM)
							? CellStyleUtils.initializeCellStyleByCellDecorator(workbook,
									cellDecoratorMap.get(CellStyleUtils.CELL_DECORATOR_ENUM))
							: CellStyleUtils.initializeGenericCellDecorator(workbook));
			cellDecoratorMap.remove(cellDecoratorMap.containsKey(CellStyleUtils.CELL_DECORATOR_ENUM));
		}

		for (Map.Entry<String, CellDecorator> object : cellDecoratorMap.entrySet()) {
			stylesMap.put(object.getKey(),
					CellStyleUtils.initializeCellStyleByCellDecorator(workbook, object.getValue()));
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
	protected void setWorkbook(Workbook workbook) {
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
	protected void setSheet(Sheet sheet) {
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
	protected void setTitleSheet(String titleSheet) {
		this.titleSheet = titleSheet;
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
	protected void setRowHeader(Row rowHeader) {
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
	protected void setRow(Row row) {
		this.row = row;
	}

	/**
	 * @return the startRow
	 */
	protected int getStartRow() {
		return startRow;
	}

	/**
	 * @param startRow
	 *            the startRow to set
	 */
	protected void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	/**
	 * @return the startCell
	 */
	protected int getStartCell() {
		return startCell;
	}

	/**
	 * @param startCell
	 *            the startCell to set
	 */
	protected void setStartCell(int startCell) {
		this.startCell = startCell;
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
	protected void setField(Field field) {
		this.field = field;
	}

	/**
	 * @return the fileName
	 */
	protected String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName
	 *            the fileName to set
	 */
	protected void setFileName(String fileName) {
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
	protected void setCompleteFileName(String completeFileName) {
		this.completeFileName = completeFileName;
	}

	/**
	 * @return the propagation
	 */
	protected PropagationType getPropagation() {
		if (overridePropagation != null) {
			return overridePropagation;
		} else {
			return propagation;
		}
	}

	/**
	 * @param propagation
	 *            the propagation to set
	 */
	protected void setPropagation(PropagationType propagation) {
		this.propagation = propagation;
	}

	/**
	 * @return the extension
	 */
	protected ExtensionFileType getExtension() {
		if (overrideExtension != null) {
			return overrideExtension;
		} else {
			return extension;
		}
	}

	/**
	 * @param extension
	 *            the extension to set
	 */
	protected void setExtension(ExtensionFileType extension) {
		this.extension = extension;
	}

	/**
	 * @return the cascadeLevel
	 */
	protected CascadeType getCascadeLevel() {
		if (overrideCascadeLevel != null) {
			return overrideCascadeLevel;
		} else {
			return cascadeLevel;
		}
	}

	/**
	 * @param cascadeLevel
	 *            the cascadeLevel to set
	 */
	protected void setCascadeLevel(CascadeType cascadeLevel) {
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
	protected void setElement(XlsElement element) {
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
	protected void setStylesMap(Map<String, CellStyle> stylesMap) {
		this.stylesMap = stylesMap;
	}

	/**
	 * @return the cellStyleManager
	 */
	protected Map<String, CellStyle> getCellStyleManager() {
		return cellStyleManager;
	}

	/**
	 * @param cellStyleManager the cellStyleManager to set
	 */
	protected void setCellStyleManager(Map<String, CellStyle> cellStyleManager) {
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
	protected void setCellDecoratorMap(Map<String, CellDecorator> cellDecoratorMap) {
		this.cellDecoratorMap = cellDecoratorMap;
	}

	/**
	 * Get the CellStyle according the type of field.
	 * 
	 * @param type
	 *            the {@link CellStyleUtils} type
	 * @return
	 * @throws ElementException
	 */
	protected CellStyle getCellStyle(String type) throws ElementException {
		if (StringUtils.isNotBlank(element.decorator()) && stylesMap.get(element.decorator()) == null) {
			throw new ElementException(ExceptionMessage.ConfigurationException_XlsDecoratorMissing.getMessage());
		}
		return StringUtils.isNotBlank(element.decorator()) ? stylesMap.get(element.decorator()) : stylesMap.get(type);
	}

	/**
	 * Get the mask to apply according the annotation formatMask.
	 * 
	 * @param maskDecoratorType
	 *            the {@link CellStyleUtils} mask decorator
	 * @return
	 */
	protected String getFormatMask(String maskDecoratorType) {
		return StringUtils.isNotBlank(element.formatMask()) ? element.formatMask() : maskDecoratorType;
	}

	/**
	 * Get the mask to apply according the annotation transformationMask.
	 * 
	 * @param maskDecoratorType
	 *            the {@link CellStyleUtils} mask decorator
	 * @return
	 */
	protected String getTransformMask(String maskDecoratorType) {
		return StringUtils.isNotBlank(element.transformMask()) ? element.transformMask() : maskDecoratorType;
	}

	/**
	 * Get the mask to apply according the annotations formatMask and
	 * transformationMask.
	 * 
	 * @param maskDecoratorType
	 *            the {@link CellStyleUtils} mask decorator
	 * @return
	 */
	protected String getMask(String maskDecoratorType) {
		return StringUtils.isNotBlank(element.transformMask()) ? element.transformMask()
				: (StringUtils.isNotBlank(element.formatMask()) ? element.formatMask() : maskDecoratorType);
	}

	protected String generateCellStyleKey(String cellDecoratorType, String maskDecoratorType) {
		String mask = StringUtils.isNotBlank(element.transformMask()) ? element.transformMask()
				: (StringUtils.isNotBlank(element.formatMask()) ? element.formatMask() : maskDecoratorType);
		String decorator = StringUtils.isNotBlank(element.decorator()) ? element.decorator() : cellDecoratorType;
		return mask.concat(decorator);
	}
}
