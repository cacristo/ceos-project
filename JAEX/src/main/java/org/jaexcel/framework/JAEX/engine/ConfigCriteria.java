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
import org.jaexcel.framework.JAEX.definition.ExtensionFileType;
import org.jaexcel.framework.JAEX.definition.PropagationType;

class ConfigCriteria {

	private Workbook workbook;
	private Sheet sheet;
	private Row rowHeader;
	private Row row;

	private XlsElement element;
	private Field field;

	private PropagationType propagation;
	private ExtensionFileType extension;

	private Map<String, CellStyle> stylesMap = new HashMap<String, CellStyle>();
	private Map<String, CellDecorator> cellDecoratorMap = new HashMap<String, CellDecorator>();

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
	 * @return the propagation
	 */
	protected PropagationType getPropagation() {
		return propagation;
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
		return extension;
	}

	/**
	 * @param extension
	 *            the extension to set
	 */
	protected void setExtension(ExtensionFileType extension) {
		this.extension = extension;
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
	 */
	protected CellStyle getCellStyle(String type) {
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
		return StringUtils.isEmpty(element.formatMask()) ? maskDecoratorType : element.formatMask();
	}

	/**
	 * Get the mask to apply according the annotation transformationMask.
	 * 
	 * @param maskDecoratorType
	 *            the {@link CellStyleUtils} mask decorator
	 * @return
	 */
	protected String getTransformMask(String maskDecoratorType) {
		return StringUtils.isEmpty(element.transformMask()) ? maskDecoratorType : element.transformMask();
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
		return StringUtils.isEmpty(element.transformMask())
				? (StringUtils.isEmpty(element.formatMask()) ? maskDecoratorType : element.formatMask())
				: element.transformMask();
	}
}
