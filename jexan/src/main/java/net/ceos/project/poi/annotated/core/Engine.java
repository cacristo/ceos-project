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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import net.ceos.project.poi.annotated.annotation.XlsConfiguration;
import net.ceos.project.poi.annotated.annotation.XlsDecorator;
import net.ceos.project.poi.annotated.annotation.XlsDecorators;
import net.ceos.project.poi.annotated.annotation.XlsElement;
import net.ceos.project.poi.annotated.annotation.XlsFreeElement;
import net.ceos.project.poi.annotated.annotation.XlsNestedHeader;
import net.ceos.project.poi.annotated.annotation.XlsSheet;
import net.ceos.project.poi.annotated.definition.ExceptionMessage;
import net.ceos.project.poi.annotated.definition.ExtensionFileType;
import net.ceos.project.poi.annotated.definition.PropagationType;
import net.ceos.project.poi.annotated.definition.TitleOrientationType;
import net.ceos.project.poi.annotated.exception.ConfigurationException;
import net.ceos.project.poi.annotated.exception.CustomizedRulesException;
import net.ceos.project.poi.annotated.exception.ElementException;
import net.ceos.project.poi.annotated.exception.SheetException;
import net.ceos.project.poi.annotated.exception.WorkbookException;

public class Engine implements IEngine {

	/**
	 * Get the runtime class of the object passed as parameter.
	 * 
	 * @param object
	 *            the object
	 * @return the runtime class
	 * @throws ElementException given when null object.
	 */
	private Class<?> initializeRuntimeClass(final Object object) throws ElementException {
		Class<?> oC = null;
		try {
			/* instance object class */
			oC = object.getClass();
		} catch (Exception e) {
			throw new ElementException(ExceptionMessage.ELEMENT_NULL_OBJECT.getMessage(), e);
		}
		return oC;
	}

	/**
	 * Initialize the configuration to apply at the Excel.
	 * 
	 * @param configCriteria
	 *            the {@link XConfigCriteria}
	 * @param oC
	 *            the {@link Class<?>}
	 * @param insideCollection
	 *            true if this configuration is inside of one collection
	 * @param excludeCascadeInit
	 *            true if to exclude the initialization of the parameter cascade level
	 * @throws ConfigurationException
	 *             given when basic configuration is missing.
	 */
	private void initializeConfigurationData(final XConfigCriteria configCriteria, final Class<?> oC,

			final boolean insideCollection, final boolean excludeCascadeInit) throws ConfigurationException {
		/* Process @XlsConfiguration */
		if (oC.isAnnotationPresent(XlsConfiguration.class)) {
			XlsConfiguration xlsAnnotation = (XlsConfiguration) oC.getAnnotation(XlsConfiguration.class);
			initializeXlsConfiguration(configCriteria, xlsAnnotation);
		} else {
			throw new ConfigurationException(ExceptionMessage.CONFIGURATION_XLSCONFIGURATION_MISSING.getMessage());
		}
		/* Process @XlsSheet */
		if (oC.isAnnotationPresent(XlsSheet.class)) {
			XlsSheet xlsAnnotation = (XlsSheet) oC.getAnnotation(XlsSheet.class);

			initializeXlsSheet(configCriteria, xlsAnnotation, excludeCascadeInit);
			if (insideCollection && oC.isAnnotationPresent(XlsElement.class)) {
				/**
				 * if the collection is an attribut inside an object we get the
				 * sheet title name from the element
				 */
				XlsElement xlsElement = (XlsElement) oC.getAnnotation(XlsElement.class);
				if (!xlsElement.parentSheet()) {
					configCriteria.setTitleSheet(xlsElement.title());
					configCriteria.setElement(xlsElement);
				}
			}
		} else {
			throw new ConfigurationException(ExceptionMessage.CONFIGURATION_XLSSHEET_MISSING.getMessage());
		}
	}

	/**
	 * Add the main XlsConfiguration configuration.
	 * 
	 * @param configCriteria
	 *            the {@link XConfigCriteria}
	 * @param annotation
	 *            the {@link XlsConfiguration}
	 */
	private void initializeXlsConfiguration(final XConfigCriteria configCriteria, final XlsConfiguration annotation) {
		configCriteria.setFileName(StringUtils.isBlank(configCriteria.getFileName()) ? annotation.nameFile()
				: configCriteria.getFileName());
		configCriteria.setExtension(
				configCriteria.getExtension() == null ? annotation.extensionFile() : configCriteria.getExtension());
		configCriteria.setCompleteFileName(configCriteria.getFileName() + configCriteria.getExtension().getExtension());
	}

	/**
	 * Add the XlsSheet configuration.
	 * 
	 * @param configCriteria
	 *            the {@link XConfigCriteria}
	 * @param annotation
	 *            the {@link XlsSheet}
	 */
	private void initializeXlsSheet(final XConfigCriteria configCriteria, final XlsSheet annotation, final boolean excludeCascadeInit) {
		configCriteria.setTitleSheet(annotation.title());

		configCriteria.setPropagation(annotation.propagation());
		if(excludeCascadeInit){
			configCriteria.setCascadeLevel(annotation.cascadeLevel());
		}

		configCriteria.setStartRow(annotation.startRow());
		configCriteria.setStartCell(annotation.startCell());

		configCriteria.setFreezePane(annotation.freezePane());
		configCriteria.setGroupElement(annotation.groupElement());

	}

	/**
	 * initialize style cell via annotation {@link XlsDecorators}
	 * 
	 * @param objectClass
	 *            the object class
	 * @param configCriteria
	 *            the {@link XConfigCriteria}
	 * @throws ConfigurationException
	 */
	private void initializeCellStyleViaAnnotation(final Class<?> objectClass, final XConfigCriteria configCriteria)
			throws ConfigurationException {
		if (objectClass.isAnnotationPresent(XlsDecorators.class)) {
			XlsDecorators xlsDecorators = (XlsDecorators) objectClass.getAnnotation(XlsDecorators.class);
			for (XlsDecorator decorator : xlsDecorators.values()) {
				if (configCriteria.getStylesMap().containsKey(decorator.decoratorName())) {
					throw new ConfigurationException(ExceptionMessage.CONFIGURATION_CELLSTYLE_DUPLICATED.getMessage());
				}
				configCriteria.getStylesMap().put(decorator.decoratorName(),
						CellStyleHandler.initializeCellStyleByXlsDecorator(configCriteria.getWorkbook(), decorator));
			}
		}
	}

	/**
	 * Initialize the Workbook.
	 * 
	 * @param type
	 *            the {@link ExtensionFileType} of workbook
	 * @return the {@link Workbook}.
	 */
	private Workbook initializeWorkbook(final ExtensionFileType type) {
		if (type != null && ExtensionFileType.XLS.getExtension().equals(type.getExtension())) {
			return new HSSFWorkbook();
		} else {
			return new XSSFWorkbook();
		}
	}

	/**
	 * Initialize Workbook from FileInputStream.
	 * 
	 * @param inputStream
	 *            the file input stream
	 * @param type
	 *            the {@link ExtensionFileType} of workbook
	 * @return the {@link Workbook} created
	 * @throws WorkbookException
	 */
	private Workbook initializeWorkbook(final FileInputStream inputStream, final ExtensionFileType type)
			throws WorkbookException {
		try {
			if (type != null && ExtensionFileType.XLS.getExtension().equals(type.getExtension())) {
				return new HSSFWorkbook(inputStream);
			} else {
				return new XSSFWorkbook(inputStream);
			}
		} catch (IOException e) {
			throw new WorkbookException(e.getMessage(), e);
		}
	}

	/**
	 * Initialize Workbook from byte[].
	 * 
	 * @param byteArray
	 *            the array of bytes
	 * @param type
	 *            the {@link ExtensionFileType} of workbook
	 * @return the {@link Workbook} created
	 * @throws WorkbookException
	 *             given when problem at the initialization of the workbook.
	 */
	private Workbook initializeWorkbook(final byte[] byteArray, final ExtensionFileType type) throws WorkbookException {
		try {
			if (type != null && ExtensionFileType.XLS.getExtension().equals(type.getExtension())) {
				return new HSSFWorkbook(new ByteArrayInputStream(byteArray));
			} else {
				return new XSSFWorkbook(new ByteArrayInputStream(byteArray));

			}
		} catch (IOException e) {
			throw new WorkbookException(e.getMessage(), e);
		}
	}

	/**
	 * Initialize Sheet.
	 * 
	 * @param wb
	 *            the {@link Workbook} to use
	 * @param sheetName
	 *            the name of the sheet
	 * @return the {@link Sheet} created
	 * @throws SheetException
	 *             given when problem at the initialization of the sheet.
	 */
	private Sheet initializeSheet(final Workbook wb, final String sheetName) throws SheetException {
		Sheet s = null;
		try {
			s = wb.createSheet(sheetName);
		} catch (Exception e) {
			throw new SheetException(ExceptionMessage.SHEET_CREATION_SHEET.getMessage(), e);
		}
		return s;
	}

	/**
	 * Validate if the nested header configuration is valid.
	 * 
	 * @param isPH
	 *            true if propagation is HORIZONTAL otherwise false to
	 *            propagation VERTICAL
	 * @param annotation
	 *            the {@link XlsNestedHeader} annotation
	 * @throws ConfigurationException
	 *             given when conflict between NestedHeader and propagation type.
	 */
	private void isValidNestedHeaderConfiguration(final boolean isPH, final XlsNestedHeader annotation)
			throws ConfigurationException {

		if (isPH && annotation.startX() == annotation.endX()) {
			throw new ConfigurationException(ExceptionMessage.CONFIGURATION_CONFLICT.getMessage());

		} else if (!isPH && annotation.startY() == annotation.endY()) {
			throw new ConfigurationException(ExceptionMessage.CONFIGURATION_CONFLICT.getMessage());
		}
	}

	/**
	 * Apply merge region if necessary.
	 * 
	 * @param configCriteria
	 *            the {@link XConfigCriteria}
	 * @param r
	 *            the {@link Row}
	 * @param idxR
	 *            the position of the row
	 * @param idxC
	 *            the position of the cell
	 * @param isPH
	 *            true if propagation horizontally, false if propagation
	 *            vertically
	 * @throws ConfigurationException
	 *             given when problem at the applying the merge region.
	 */
	private void applyMergeRegion(final XConfigCriteria configCriteria, Row r, final int idxR, final int idxC,
			final boolean isPH) throws ConfigurationException {
		/* Process @XlsNestedHeader */
		if (configCriteria.getField().isAnnotationPresent(XlsNestedHeader.class)) {
			XlsNestedHeader annotation = (XlsNestedHeader) configCriteria.getField()
					.getAnnotation(XlsNestedHeader.class);
			/* if row null is necessary to create it */
			Row row = r;
			if (row == null) {
				/* check if the row already exist */
				row = configCriteria.getSheet().getRow(idxR);
				if (row == null) {
					/* create a new row */
					row = initializeRow(configCriteria.getSheet(), idxR);
				}
			}

			/* validation of configuration */
			isValidNestedHeaderConfiguration(isPH, annotation);

			/* prepare position rows / cells */
			int startRow;
			int endRow;
			int startCell;
			int endCell;
			if (isPH) {
				startRow = endRow = idxR;
				startCell = idxC + annotation.startX();
				endCell = idxC + annotation.endX();
			} else {
				startRow = idxR + annotation.startY();
				endRow = idxR + annotation.endY();
				startCell = endCell = idxC;
			}

			/* initialize nested header cell */
			CellStyleHandler.initializeHeaderCell(configCriteria.getStylesMap(), row, startCell, annotation.title());

			/* merge region of the nested header cell */
			CellRangeAddress range = new CellRangeAddress(startRow, endRow, startCell, endCell);
			configCriteria.getSheet().addMergedRegion(range);

			/* apply the border to the nested header cell */
			applyBorderToRegion(configCriteria, range);
		}
	}

	/**
	 * Apply the border to region according the {@link CellStyle}
	 * 
	 * @param configCriteria
	 *            the {@link XConfigCriteria}
	 * @param range
	 *            the {@link CellRangeAddress}
	 */
	private void applyBorderToRegion(final XConfigCriteria configCriteria, CellRangeAddress range) {
		RegionUtil.setBorderBottom(
				configCriteria.getStylesMap().get(CellStyleHandler.CELL_DECORATOR_HEADER).getBorderBottom(), range,
				configCriteria.getSheet(), configCriteria.getWorkbook());

		RegionUtil.setBorderTop(
				configCriteria.getStylesMap().get(CellStyleHandler.CELL_DECORATOR_HEADER).getBorderTop(), range,
				configCriteria.getSheet(), configCriteria.getWorkbook());

		RegionUtil.setBorderLeft(
				configCriteria.getStylesMap().get(CellStyleHandler.CELL_DECORATOR_HEADER).getBorderLeft(), range,
				configCriteria.getSheet(), configCriteria.getWorkbook());

		RegionUtil.setBorderRight(
				configCriteria.getStylesMap().get(CellStyleHandler.CELL_DECORATOR_HEADER).getBorderRight(), range,
				configCriteria.getSheet(), configCriteria.getWorkbook());
	}

	/**
	 * Initialize an row.
	 * 
	 * @param s
	 *            the {@link Sheet} to add the row
	 * @param idxR
	 *            position of the new row
	 * @return the {@link Row} created
	 */
	private Row initializeRow(final Sheet s, final int idxR) {
		return s.createRow(idxR);
	}

	/**
	 * Initialize the cell position based at the title orientation.
	 * 
	 * @param positionCell
	 *            the cell position defined at the element
	 * @param orientation
	 *            the {@link TitleOrientationType} of the element
	 * @return the cell position
	 */
	private int initializeHeaderCellPosition(final int positionCell, final TitleOrientationType orientation) {
		int idxCell = positionCell - 1;
		if (TitleOrientationType.LEFT == orientation) {
			idxCell -= 1;
		} else if (TitleOrientationType.RIGHT == orientation) {
			idxCell += 1;
		}
		return idxCell;
	}

	/**
	 * initialize the row position based at the title orientation.
	 * 
	 * @param positionRow
	 *            the row position defined at the element
	 * @param orientation
	 *            the {@link TitleOrientationType} of the element
	 * @return the row position
	 */
	private int initializeHeaderRowPosition(final int positionRow, final TitleOrientationType orientation) {
		int idxRow = positionRow;
		if (TitleOrientationType.TOP == orientation) {
			idxRow -= 1;
		} else if (TitleOrientationType.BOTTOM == orientation) {
			idxRow += 1;
		}
		return idxRow;
	}

	/**
	 * Initialization of the cell by the field.
	 * 
	 * @param configCriteria
	 *            the {@link XConfigCriteria}
	 * @param xlsAnnotation
	 *            the {@link XlsFreeElement}
	 * @param o
	 *            the object
	 * @param field
	 *            the field
	 * @param idxC
	 *            the position of the cell
	 * @param cL
	 *            the cascade level
	 * @throws WorkbookException
	 *             given when the object is complex.
	 */
	private void initializeCellByField(final XConfigCriteria configCriteria, final XlsFreeElement xlsAnnotation,
			final Object o, final Field field, final int idxC, final int cL) throws WorkbookException {

		/* make the field accessible to recover the value */
		field.setAccessible(true);

		Class<?> fT = field.getType();

		if (configCriteria.getSheet().getRow(xlsAnnotation.row()) != null) {
			configCriteria.setRow(configCriteria.getSheet().getRow(xlsAnnotation.row()));
		} else {
			configCriteria.setRow(configCriteria.getSheet().createRow(xlsAnnotation.row()));
		}
		configCriteria.setField(field);

		// initialize Element
		configCriteria.setElement(XlsElementFactory.build(xlsAnnotation));

		boolean isAppliedObject = toExcel(configCriteria, o, fT, idxC);

		if (!isAppliedObject && !fT.isPrimitive()) {
			throw new ElementException(ExceptionMessage.ELEMENT_COMPLEX_OBJECT.getMessage());
		}

	}

	/**
	 * Initialize an cell according the type of field and the PropagationType is
	 * PROPAGATION_HORIZONTAL.
	 * 
	 * @param configCriteria
	 *            the {@link XConfigCriteria}
	 * @param o
	 *            the object
	 * @param idxR
	 *            the position of the row
	 * @param idxC
	 *            the position of the cell
	 * @param cL
	 *            the cascade level
	 * @return in case of the object return the number of cells created,
	 *         otherwise 0
	 * @throws WorkbookException
	 *             given when no such element.
	 */
	private int initializeCellByFieldHorizontal(final XConfigCriteria configCriteria, final Object o, final int idxR,
			final int idxC, final int cL) throws WorkbookException {

		int counter = 0;

		/* make the field accessible to recover the value */
		configCriteria.getField().setAccessible(true);

		Class<?> fT = configCriteria.getField().getType();

		if (Collection.class.isAssignableFrom(fT)) {
			try {
				// E uma lista entao ha que crear uma sheet nova
				marshallCollectionEngineT(configCriteria, (Collection<?>) configCriteria.getField().get(o), idxC, fT,
						cL + 1);
			} catch (IllegalAccessException e) {
				throw new CustomizedRulesException(ExceptionMessage.ELEMENT_NO_SUCH_METHOD.getMessage(), e);
			}
		} else {

			boolean isAppliedObject = toExcel(configCriteria, o, fT, idxC);

			if (!isAppliedObject && !fT.isPrimitive()) {
				try {
					Object nO = configCriteria.getField().get(o);
					/* manage null objects */
					if (nO == null) {
						nO = fT.newInstance();
					}
					Class<?> oC = nO.getClass();

					counter = marshalAsPropagationHorizontal(configCriteria, nO, oC, idxR, idxC - 1, cL + 1);
				} catch (InstantiationException | IllegalAccessException e) {
					throw new CustomizedRulesException(ExceptionMessage.ELEMENT_NO_SUCH_METHOD.getMessage(), e);
				}
			}
		}

		return counter;
	}

	/**
	 * Initialize an cell according the type of field and the PropagationType is
	 * PROPAGATION_VERTICAL.
	 * 
	 * @param configCriteria
	 *            the {@link XConfigCriteria}
	 * @param o
	 *            the object
	 * @param r
	 *            the content row
	 * @param idxR
	 *            the position of the row
	 * @param idxC
	 *            the position of the cell
	 * @param cL
	 *            the cascade level
	 * @return in case of the object return the number of cells created,
	 *         otherwise 0
	 * @throws WorkbookException
	 *             given when the object is complex.
	 */
	private int initializeCellByFieldVertical(final XConfigCriteria configCriteria, final Object o, final Row r,
			final int idxR, final int idxC, int cL) throws WorkbookException {

		int counter = 0;

		/* make the field accessible to recover the value */
		configCriteria.getField().setAccessible(true);

		Class<?> fT = configCriteria.getField().getType();

		configCriteria.setRow(r);

		if (Collection.class.isAssignableFrom(fT)) {
			Collection<?> collection = null;
			try {
				collection = (Collection<?>) configCriteria.getField().get(o);
			} catch (IllegalArgumentException | IllegalAccessException e1) {
				throw new ElementException(ExceptionMessage.ELEMENT_NULL_OBJECT.getMessage());
			}
			if (collection != null) {
				Object objectRT;
				try {
					// TOVERIFY
					objectRT = ((Collection<?>) configCriteria.getField().get(o)).iterator().next();
					// objectRT =
					// listObject.getClass().stream().findFirst().get();
				} catch (Exception e) {
					throw new ElementException(ExceptionMessage.ELEMENT_NULL_OBJECT.getMessage());
				}

				/* initialize the runtime class of the object */
				Class<?> oC = initializeRuntimeClass(objectRT);

				// E uma lista entao ha que crear uma sheet nova
				marshallCollectionEngineT(configCriteria, collection, idxC, oC, cL + 1);
			}

		} else {
			boolean isAppliedObject = toExcel(configCriteria, o, fT, idxC);

			if (!isAppliedObject && !fT.isPrimitive()) {
				try {
					Object nO = configCriteria.getField().get(o);
					/* manage null objects */
					if (nO == null) {
						nO = fT.newInstance();
					}
					Class<?> oC = nO.getClass();

					counter = marshalAsPropagationVertical(configCriteria, nO, oC, idxR - 1, idxC - 1, cL + 1);
				} catch (InstantiationException | IllegalAccessException e) {
					throw new CustomizedRulesException(ExceptionMessage.ELEMENT_NO_SUCH_METHOD.getMessage(), e);
				}
			}
		}


		return counter;
	}

	/**
	 * Apply the base object to cell.
	 * 
	 * @param configCriteria
	 *            the {@link XConfigCriteria}
	 * @param o
	 *            the object
	 * @param fT
	 *            the field type
	 * @param idxC
	 *            the position of the cell
	 * @return false if problem otherwise true
	 * @throws WorkbookException
	 *             given when a not supported action.
	 */
	private boolean toExcel(final XConfigCriteria configCriteria, final Object o, final Class<?> fT, final int idxC)
			throws WorkbookException {
		/* flag which define if the cell was updated or not */
		boolean isUpdated;
		/* initialize cell */
		Cell cell;

		/*
		 * check if the cell to be applied the element is empty otherwise one
		 * exception will be launched
		 */
		if (configCriteria.getRow().getCell(idxC) != null) {
			throw new ElementException(ExceptionMessage.ELEMENT_OVERWRITE_CELL.getMessage());
		}

		switch (fT.getName()) {
		case CellHandler.OBJECT_DATE:
			cell = configCriteria.getRow().createCell(idxC);
			isUpdated = CellHandler.dateWriter(configCriteria, o, cell);
			break;

		case CellHandler.OBJECT_LOCALDATE:
			cell = configCriteria.getRow().createCell(idxC);
			isUpdated = CellHandler.localDateWriter(configCriteria, o, cell);
			break;

		case CellHandler.OBJECT_LOCALDATETIME:
			cell = configCriteria.getRow().createCell(idxC);
			isUpdated = CellHandler.localDateTimeWriter(configCriteria, o, cell);
			break;

		case CellHandler.OBJECT_STRING:
			cell = configCriteria.getRow().createCell(idxC);
			isUpdated = CellHandler.stringWriter(configCriteria, o, cell);
			break;

		case CellHandler.OBJECT_SHORT:
			/* falls through */
		case CellHandler.PRIMITIVE_SHORT:
			cell = configCriteria.getRow().createCell(idxC);
			isUpdated = CellHandler.shortWriter(configCriteria, o, cell);
			break;

		case CellHandler.OBJECT_INTEGER:
			/* falls through */
		case CellHandler.PRIMITIVE_INTEGER:
			cell = configCriteria.getRow().createCell(idxC);
			isUpdated = CellHandler.integerWriter(configCriteria, o, cell);
			break;

		case CellHandler.OBJECT_LONG:
			/* falls through */
		case CellHandler.PRIMITIVE_LONG:
			cell = configCriteria.getRow().createCell(idxC);
			isUpdated = CellHandler.longWriter(configCriteria, o, cell);
			break;

		case CellHandler.OBJECT_DOUBLE:
			/* falls through */
		case CellHandler.PRIMITIVE_DOUBLE:
			cell = configCriteria.getRow().createCell(idxC);
			isUpdated = CellHandler.doubleWriter(configCriteria, o, cell);
			break;

		case CellHandler.OBJECT_BIGDECIMAL:
			cell = configCriteria.getRow().createCell(idxC);
			isUpdated = CellHandler.bigDecimalWriter(configCriteria, o, cell);
			break;

		case CellHandler.OBJECT_FLOAT:
			/* falls through */
		case CellHandler.PRIMITIVE_FLOAT:
			cell = configCriteria.getRow().createCell(idxC);
			isUpdated = CellHandler.floatWriter(configCriteria, o, cell);
			break;

		case CellHandler.OBJECT_BOOLEAN:
			/* falls through */
		case CellHandler.PRIMITIVE_BOOLEAN:
			cell = configCriteria.getRow().createCell(idxC);
			isUpdated = CellHandler.booleanWriter(configCriteria, o, cell);
			break;

		default:
			isUpdated = false;
			break;
		}

		if (!isUpdated && fT.isEnum()) {
			cell = configCriteria.getRow().createCell(idxC);
			isUpdated = CellHandler.enumWriter(configCriteria, o, cell);
		}

		return isUpdated;
	}

	/**
	 * Apply the base object from cell.
	 * 
	 * @param o
	 *            the object
	 * @param fT
	 *            the field type
	 * @param f
	 *            the field
	 * @param c
	 *            the cell
	 * @param xlsAnnotation
	 *            the {@link XlsElement} annotation
	 * @return false if problem otherwise true
	 * @throws WorkbookException
	 *             given when a not supported action.
	 */
	private boolean toObject(final Object o, final Class<?> fT, final Field f, final Cell c,
			final XlsElement xlsAnnotation) throws WorkbookException {
		/* flag which define if the cell was updated or not */
		boolean isUpdated;

		f.setAccessible(true);

		switch (fT.getName()) {
		case CellHandler.OBJECT_DATE:
			CellHandler.dateReader(o, f, c, xlsAnnotation);
			isUpdated = true;
			break;

		case CellHandler.OBJECT_LOCALDATE:
			CellHandler.localDateReader(o, f, c, xlsAnnotation);
			isUpdated = true;
			break;

		case CellHandler.OBJECT_LOCALDATETIME:
			CellHandler.localDateTimeReader(o, f, c, xlsAnnotation);
			isUpdated = true;
			break;

		case CellHandler.OBJECT_STRING:
			CellHandler.stringReader(o, f, c);
			isUpdated = true;
			break;

		case CellHandler.OBJECT_SHORT:
			/* falls through */
		case CellHandler.PRIMITIVE_SHORT:
			CellHandler.shortReader(o, f, c);
			isUpdated = true;
			break;

		case CellHandler.OBJECT_INTEGER:
			/* falls through */
		case CellHandler.PRIMITIVE_INTEGER:
			CellHandler.integerReader(o, f, c);
			isUpdated = true;
			break;

		case CellHandler.OBJECT_LONG:
			/* falls through */
		case CellHandler.PRIMITIVE_LONG:
			CellHandler.longReader(o, f, c);
			isUpdated = true;
			break;

		case CellHandler.OBJECT_DOUBLE:
			/* falls through */
		case CellHandler.PRIMITIVE_DOUBLE:
			CellHandler.doubleReader(o, f, c, xlsAnnotation);
			isUpdated = true;
			break;

		case CellHandler.OBJECT_BIGDECIMAL:
			CellHandler.bigDecimalReader(o, f, c, xlsAnnotation);
			isUpdated = true;
			break;

		case CellHandler.OBJECT_FLOAT:
			/* falls through */
		case CellHandler.PRIMITIVE_FLOAT:
			CellHandler.floatReader(o, f, c);
			isUpdated = true;
			break;

		case CellHandler.OBJECT_BOOLEAN:
			/* falls through */
		case CellHandler.PRIMITIVE_BOOLEAN:
			CellHandler.booleanReader(o, f, c, xlsAnnotation);
			isUpdated = true;
			break;

		default:
			isUpdated = false;
			break;
		}

		if (!isUpdated && fT.isEnum()) {
			CellHandler.enumReader(o, fT, f, c);
			isUpdated = true;
		}

		return isUpdated;
	}

	/**
	 * Process the annotation {@link XlsFreeElement}
	 * 
	 * @param configCriteria
	 *            the {@link XConfigCriteria}
	 * @param o
	 *            the object
	 * @param oC
	 *            the object class
	 * @param f
	 *            the field
	 * @throws WorkbookException
	 *             given when a not supported action.
	 */
	private void processXlsFreeElement(final XConfigCriteria configCriteria, final Object o, final int cL,
			final Field f) throws WorkbookException {

		if (f.isAnnotationPresent(XlsFreeElement.class)) {
			XlsFreeElement xlsAnnotation = (XlsFreeElement) f.getAnnotation(XlsFreeElement.class);

			/* validate the row/cell of the element */
			if (xlsAnnotation.row() < 1 || xlsAnnotation.cell() < 1) {
				throw new ElementException(ExceptionMessage.ELEMENT_INVALID_POSITION.getMessage());
			}

			/* header treatment */
			if (xlsAnnotation.showTitle()) {
				/* initialize the row position */
				int idxRow = initializeHeaderRowPosition(xlsAnnotation.row(), xlsAnnotation.titleOrientation());
				/* initialize the cell position */
				int idxCell = initializeHeaderCellPosition(xlsAnnotation.cell(), xlsAnnotation.titleOrientation());
				/* obtain/initialize the row */
				Row row = configCriteria.getSheet().getRow(idxRow);
				if (row == null) {
					row = initializeRow(configCriteria.getSheet(), idxRow);
				}

				/*
				 * check if the cell to be applied the element is empty
				 * otherwise one exception will be launched
				 */
				if (row.getCell(idxCell) != null) {
					throw new ElementException(ExceptionMessage.ELEMENT_OVERWRITE_CELL.getMessage());
				}

				CellStyleHandler.initializeHeaderCell(configCriteria.getStylesMap(), row, idxCell,
						xlsAnnotation.title());
			}

			/* prepare the column width */
			if (configCriteria.getResizeActive() && xlsAnnotation.columnWidthInUnits() != 0) {
				configCriteria.getColumnWidthMap().put(xlsAnnotation.cell() - 1, xlsAnnotation.columnWidthInUnits());
			}

			/* content treatment */
			initializeCellByField(configCriteria, xlsAnnotation, o, f, xlsAnnotation.cell() - 1, cL);
		}
	}

	/**
	 * Prepare the propagation horizontal:
	 * <ul>
	 * <li>1. initialize sheet
	 * <li>2. initialize header row
	 * <li>3. initialize row
	 * </ul>
	 * 
	 * @param configCriteria
	 *            the {@link XConfigCriteria}
	 * @return the the position of the row
	 * @throws SheetException
	 *             given when problem at the sheet initialization.
	 */
	private int preparePropagationHorizontal(final XConfigCriteria configCriteria) throws SheetException {
		int idxRow;

		if (configCriteria.getWorkbook().getNumberOfSheets() == 0
				|| configCriteria.getWorkbook().getSheet(truncateTitle(configCriteria.getTitleSheet())) == null) {
			configCriteria.setSheet(initializeSheet(configCriteria.getWorkbook(), configCriteria.getTitleSheet()));
			idxRow = configCriteria.getStartRow();
			configCriteria.setRowHeader(initializeRow(configCriteria.getSheet(), idxRow++));
			configCriteria.setRow(initializeRow(configCriteria.getSheet(), idxRow++));

		} else {
			// TOVERIFY se ha que aumentar a 5 pelo nestedheader
			idxRow = configCriteria.getElement() != null && configCriteria.getElement().parentSheet()
					? configCriteria.getWorkbook().getSheet(truncateTitle(configCriteria.getTitleSheet()))
							.getLastRowNum() + 3
					: configCriteria.getWorkbook().getSheet(truncateTitle(configCriteria.getTitleSheet()))
							.getLastRowNum() + 1;
			// idxRow = configCriteria.getSheet().getLastRowNum() + 1;
			configCriteria.setRowHeader(null);
			configCriteria.setRow(initializeRow(
					configCriteria.getWorkbook().getSheet(truncateTitle(configCriteria.getTitleSheet())), idxRow++));

		}
		return idxRow;
	}

	/**
	 * Prepare the propagation vertical:
	 * <ul>
	 * <li>initialize sheet
	 * <li>define next cell index value
	 * </ul>
	 * 
	 * @param configCriteria
	 *            the {@link XConfigCriteria}
	 * @param idxCell
	 *            the cell index
	 * @return the the position of the cell
	 * @throws SheetException
	 *             given when problem at the sheet initialization.
	 */
	private int preparePropagationVertical(final XConfigCriteria configCriteria, int idxCell) throws SheetException {
		int indexCell = idxCell;

		if (configCriteria.getWorkbook().getNumberOfSheets() == 0
				|| configCriteria.getWorkbook().getSheet(truncateTitle(configCriteria.getTitleSheet())) == null) {
			configCriteria.setSheet(initializeSheet(configCriteria.getWorkbook(), configCriteria.getTitleSheet()));
		//	sheetBackup= configCriteria.getSheet();
			indexCell = configCriteria.getStartCell()+1;
		} else {

			configCriteria.setSheet(configCriteria.getWorkbook().getSheet(truncateTitle(configCriteria.getTitleSheet())));
			   short sh= configCriteria.getSheet().getRow(configCriteria.getStartRow()+1).getLastCellNum();
			   indexCell = sh-1;

		}

		return indexCell;
	}

	/**
	 * Convert the object to file with the PropagationType as
	 * PROPAGATION_HORIZONTAL.
	 * 
	 * @param configCriteria
	 *            the {@link XConfigCriteria}
	 * @param o
	 *            the object
	 * @param oC
	 *            the object class
	 * @param idxR
	 *            the position of the row
	 * @param idxC
	 *            the position of the cell
	 * @param cL
	 *            the cascade level
	 * @return in case of the object return the number of cells created,
	 *         otherwise 0
	 * @throws WorkbookException
	 *             given when a not supported action.
	 * 
	 */
	private int marshalAsPropagationHorizontal(final XConfigCriteria configCriteria, final Object o, final Class<?> oC,
			final int idxR, final int idxC, final int cL) throws WorkbookException {

		/* counter related to the number of fields (if new object) */
		int counter = -1;
		int indexCell = idxC;
		int rem = 0;


		/* validate cascade level */
		if (!CascadeHandler.isAuthorizedCascadeLevel(configCriteria, cL, o)) {
			return counter;
		}


		/* get declared fields */
		List<Field> fL = Arrays.asList(oC.getDeclaredFields());
		// Order by the list by position
		Collections.sort(fL, new Comparator<Field>() {
			public int compare(Field f1, Field f2) {
				if (f2 == null) {
					return 0;
				} else if (f1 != null && f2 != null && f1.isAnnotationPresent(XlsElement.class)
						&& f2.isAnnotationPresent(XlsElement.class)) {
					XlsElement element1 = (XlsElement) f1.getAnnotation(XlsElement.class);
					XlsElement element2 = (XlsElement) f2.getAnnotation(XlsElement.class);
					return element1.position() - element2.position();
				} else {
					return 0;
				}
			}
		});
		for (Field f : fL) {
			/* update field at ConfigCriteria */
			configCriteria.setField(f);

			/* process each field from the object */
			if (configCriteria.getRowHeader() != null) {
				/* calculate index of the cell */
				int tmpIdxRow = idxR - 3;
				/* apply merge region */
				applyMergeRegion(configCriteria, null, tmpIdxRow, indexCell, true);
			}

			/* validate non-conflict annotation type */
			if (f.isAnnotationPresent(XlsElement.class) && f.isAnnotationPresent(XlsFreeElement.class)) {
				throw new ElementException(ExceptionMessage.ELEMENT_CONFLICT_WITH_FREEELEMENT.getMessage());
			}

			/* Process @XlsElement */
			if (f.isAnnotationPresent(XlsElement.class)) {
				XlsElement xlsAnnotation = (XlsElement) f.getAnnotation(XlsElement.class);

				/* validate the position of the element */
				if (xlsAnnotation.position() < 1) {
					throw new ElementException(ExceptionMessage.ELEMENT_INVALID_POSITION.getMessage());
				}

				/* validate the propagation type & formulas */
				if (xlsAnnotation.isFormula() && StringUtils.isNotBlank(xlsAnnotation.formula())
						&& xlsAnnotation.formula().contains("idy")) {
					throw new ElementException(ExceptionMessage.CONFIGURATION_CONFLICT.getMessage());
				}

				/* update annotation at ConfigCriteria */
				configCriteria.setElement(xlsAnnotation);

				/* apply customized rules defined at the object */
				if (StringUtils.isNotBlank(xlsAnnotation.customizedRules())) {
					CellHandler.applyCustomizedRules(o, xlsAnnotation.customizedRules());
				}

				/*
				 * increment of the counter related to the number of fields (if
				 * new object)
				 */
				counter++;
				int pos = idxC + xlsAnnotation.position();
				// if is a list don't generate the head
				if (configCriteria.getRowHeader() != null && !Collection.class.isAssignableFrom(f.getType())) {
					pos = pos - rem;
					/* header treatment */
					CellStyleHandler.initializeHeaderCell(configCriteria.getStylesMap(), configCriteria.getRowHeader(),
							indexCell + xlsAnnotation.position(), xlsAnnotation.title());
					rem = 0;
				} else {
					//
					rem = rem + 1;
				}
				/* prepare the column width */
				if (configCriteria.getResizeActive() && xlsAnnotation.columnWidthInUnits() != 0) {
					configCriteria.getColumnWidthMap().put(indexCell + xlsAnnotation.position(),
							xlsAnnotation.columnWidthInUnits());
				}

				/* content treatment */
				indexCell += initializeCellByFieldHorizontal(configCriteria, o, idxR,
						indexCell + xlsAnnotation.position(), cL);
			}
		}

		for (Field f : fL) {
			/* update field at ConfigCriteria */
			configCriteria.setField(f);

			/* Process @XlsFreeElement */
			processXlsFreeElement(configCriteria, o, cL, f);
		}

		/* disable the resize */
		configCriteria.setResizeActive(false);

		return counter;
	}

	/**
	 * Convert the object to file with the PropagationType as
	 * PROPAGATION_VERTICAL.
	 * 
	 * @param configCriteria
	 *            the {@link XConfigCriteria}
	 * @param o
	 *            the object
	 * @param oC
	 *            the object class
	 * @param idxR
	 *            the position of the row
	 * @param idxC
	 *            the position of the cell
	 * @param cL
	 *            the cascade level
	 * @return in case of the object return the number of cells created,
	 *         otherwise 0
	 * @throws WorkbookException
	 *             given when a not supported action.
	 */
	private int marshalAsPropagationVertical(final XConfigCriteria configCriteria, final Object o, Class<?> oC,
			final int idxR, final int idxC, final int cL) throws WorkbookException {
		/* counter related to the number of fields (if new object) */
		int counter = -1;
		int indexCell = idxC;
		int indexRow = idxR;
		/* backup base index of the cell */
		int baseIdxCell = indexCell;

		int rem =0;

		/* validate cascade level */
		if (!CascadeHandler.isAuthorizedCascadeLevel(configCriteria, cL, o)) {
			return counter;
		}


		/* get declared fields */
		List<Field> fL = Arrays.asList(oC.getDeclaredFields());
		Collections.sort(fL, new Comparator<Field>() {
			public int compare(Field f1, Field f2) {
				if (f2 == null) {
					return 0;
				} else if (f1 != null && f2 != null && f1.isAnnotationPresent(XlsElement.class)
						&& f2.isAnnotationPresent(XlsElement.class)) {
					XlsElement element1 = (XlsElement) f1.getAnnotation(XlsElement.class);
					XlsElement element2 = (XlsElement) f2.getAnnotation(XlsElement.class);
					return element1.position() - element2.position();
				} else {
					return 0;
				}
			}
		});
		for (Field f : fL) {
			/* process each field from the object */
			configCriteria.setField(f);

			/* restart the index of the cell */
			indexCell = baseIdxCell;

			/* validate non-conflict annotation type */
			if (f.isAnnotationPresent(XlsElement.class) && f.isAnnotationPresent(XlsFreeElement.class)) {
				throw new ElementException(ExceptionMessage.ELEMENT_CONFLICT_WITH_FREEELEMENT.getMessage());
			}

			/* Process @XlsElement */
			if (f.isAnnotationPresent(XlsElement.class)) {
				XlsElement xlsAnnotation = (XlsElement) f.getAnnotation(XlsElement.class);

				/* validate the position of the element */
				if (xlsAnnotation.position() < 1) {
					throw new ElementException(ExceptionMessage.ELEMENT_INVALID_POSITION.getMessage());
				}

				/* validate the propagation type & formulas */
				if (xlsAnnotation.isFormula() && StringUtils.isNotBlank(xlsAnnotation.formula())
						&& xlsAnnotation.formula().contains("idx")) {
					throw new ElementException(ExceptionMessage.CONFIGURATION_CONFLICT.getMessage());
				}

				/* update annotation at ConfigCriteria */
				configCriteria.setElement(xlsAnnotation);

				/* apply customized rules defined at the object */
				if (StringUtils.isNotBlank(xlsAnnotation.customizedRules())) {
					CellHandler.applyCustomizedRules(o, xlsAnnotation.customizedRules());
				}

				/*
				 * increment of the counter related to the number of fields (if
				 * new object)
				 */
				counter++;

				/* create the row */
				Row row = configCriteria.getSheet().getRow(indexRow + xlsAnnotation.position());
				if (row == null || baseIdxCell == 0 || baseIdxCell == 1) {
					/* header treatment with nested header */
					int tmpIdxCell = indexCell - 1;
					/* initialize row */
					row = initializeRow(configCriteria.getSheet(), indexRow + xlsAnnotation.position());

					/* apply merge region */
					applyMergeRegion(configCriteria, row, indexRow, tmpIdxCell, false);

					int pos = indexRow + xlsAnnotation.position();
					// if is a list don't generate the head
					if (row != null && !Collection.class.isAssignableFrom(f.getType())) {
						pos = pos - rem;
						/* header treatment */
						CellStyleHandler.initializeHeaderCell(configCriteria.getStylesMap(), row, indexCell,
								xlsAnnotation.title());
						rem = 0;
					} else {
						//
						rem = rem + 1;
					}

				} else {
					row = configCriteria.getSheet().getRow(indexRow + xlsAnnotation.position());
					/* header treatment without nested header */
					// CellStyleHandler.initializeHeaderCell(configCriteria.getStylesMap(),
					// row, indexCell,
					// xlsAnnotation.title());
				}

				/* prepare the column width */
				if (configCriteria.getResizeActive() && xlsAnnotation.columnWidthInUnits() != 0) {
					configCriteria.getColumnWidthMap().put(indexCell + xlsAnnotation.position(),
							xlsAnnotation.columnWidthInUnits());
				}

				/* increment the cell position */
				indexCell = indexCell + 1;
				/* content treatment */
				indexRow += initializeCellByFieldVertical(configCriteria, o, row, indexRow + xlsAnnotation.position(),
						indexCell, cL);
			}
		}

		for (Field f : fL) {
			/* update field at ConfigCriteria */
			configCriteria.setField(f);

			/* Process @XlsFreeElement */
			processXlsFreeElement(configCriteria, o, cL, f);
		}

		/* disable the resize */
		configCriteria.setResizeActive(false);

		return counter;
	}

	/**
	 * Convert the file to object with the PropagationType as
	 * PROPAGATION_HORIZONTAL.
	 * 
	 * @param configCriteria
	 *            the {@link XConfigCriteria}
	 * @param o
	 *            the object
	 * @param oC
	 *            the object class
	 * @param idxR
	 *            the position of the row
	 * @param idxC
	 *            the position of the cell
	 * @return in case of the object return the number of cells created,
	 *         otherwise 0
	 * @throws WorkbookException
	 *             given when a not supported action.
	 */
	private int unmarshalAsPropagationHorizontal( XConfigCriteria configCriteria, Object o, Class<?> oC,
			 int idxR,  int idxC) throws WorkbookException {
		/* counter related to the number of fields (if new object) */
		int counter = -1;
		int indexCell = idxC;

		/* get declared fields */
		List<Field> fL = Arrays.asList(oC.getDeclaredFields());
		for (Field f : fL) {
			/* process each field from the object */
			Class<?> fT = f.getType();

			/* Process @XlsElement */
			if (f.isAnnotationPresent(XlsElement.class)) {
				XlsElement xlsAnnotation = (XlsElement) f.getAnnotation(XlsElement.class);

				/* validate the position of the element */
				if (xlsAnnotation.position() < 1) {
					throw new ElementException(ExceptionMessage.ELEMENT_INVALID_POSITION.getMessage());
				}
				/*
				 * increment of the counter related to the number of fields (if
				 * new object)
				 */
				counter++;

				/* content row */
				Row contentRow = configCriteria.getSheet().getRow(idxR + 1);
				if (contentRow != null && idxR < configCriteria.getSheet().getLastRowNum()) {
					Cell contentCell = contentRow.getCell(indexCell + xlsAnnotation.position());
					if (contentCell != null) {
						if (Collection.class.isAssignableFrom(fT)) {

							try {
								f.set(o, unmarshalTreatementToCollection(o, configCriteria));
							} catch (IllegalArgumentException | IllegalAccessException e) {
								throw new ElementException(ExceptionMessage.ELEMENT_COMPLEX_OBJECT.getMessage());
							}
						} else {
							boolean isAppliedToBaseObject = toObject(o, fT, f, contentCell, xlsAnnotation);

							if (!isAppliedToBaseObject && !fT.isPrimitive()) {
								try {
									Object subObjbect = fT.newInstance();

									Class<?> subObjbectClass = subObjbect.getClass();

									int internalCellCounter = unmarshalAsPropagationHorizontal(configCriteria,
											subObjbect, subObjbectClass, idxR,
											indexCell + xlsAnnotation.position() - 1);

									/*
									 * add the sub object to the parent object
									 */
									f.set(o, subObjbect);

									/* update the index */
									indexCell += internalCellCounter;
								} catch (InstantiationException | IllegalAccessException e) {
									throw new CustomizedRulesException(
											ExceptionMessage.ELEMENT_NO_SUCH_METHOD.getMessage(), e);

								}
							}
						}

					}
				} else {
					counter = -5;
					o = null;
					break;

				}

			}

			/* Process @XlsFreeElement */
			if (f.isAnnotationPresent(XlsFreeElement.class)) {
				XlsFreeElement xlsFreeAnnotation = (XlsFreeElement) f.getAnnotation(XlsFreeElement.class);

				/* validate the row/cell of the element */
				if (xlsFreeAnnotation.row() < 1 || xlsFreeAnnotation.cell() < 1) {
					throw new ElementException(ExceptionMessage.ELEMENT_INVALID_POSITION.getMessage());
				}

				/* content row */
				Row contentRow = configCriteria.getSheet().getRow(xlsFreeAnnotation.row());
				Cell contentCell = contentRow.getCell(xlsFreeAnnotation.cell() - 1);

				// initialize Element
				XlsElement xlsAnnotation = XlsElementFactory.build(xlsFreeAnnotation);

				boolean isAppliedToBaseObject = toObject(o, fT, f, contentCell, xlsAnnotation);

				if (!isAppliedToBaseObject && !fT.isPrimitive()) {
					throw new ElementException(ExceptionMessage.ELEMENT_COMPLEX_OBJECT.getMessage());
				}
			}
		}
		return counter;
	}

	/**
	 * Convert the file to object with the PropagationType as
	 * PROPAGATION_VERTICAL.
	 * 
	 * @param configCriteria
	 *            the {@link XConfigCriteria}
	 * @param o
	 *            the object
	 * @param oC
	 *            the object class
	 * @param idxR
	 *            the position of the row
	 * @param idxC
	 *            the position of the cell
	 * @return in case of the object return the number of cells created,
	 *         otherwise 0
	 * @throws WorkbookException
	 *             given when a not supported action.
	 */
	private int unmarshalAsPropagationVertical(final XConfigCriteria configCriteria, Object o, Class<?> oC,
			final int idxR, final int idxC) throws WorkbookException {
		/* counter related to the number of fields (if new object) */
		int counter = -1;
		int indexRow = idxR;

		/* get declared fields */
		List<Field> fL = Arrays.asList(oC.getDeclaredFields());
		for (Field f : fL) {
			/* process each field from the object */
			Class<?> fT = f.getType();

			/* Process @XlsElement */
			if (f.isAnnotationPresent(XlsElement.class)) {
				XlsElement xlsAnnotation = (XlsElement) f.getAnnotation(XlsElement.class);

				/* validate the position of the element */
				if (xlsAnnotation.position() < 1) {
					throw new ElementException(ExceptionMessage.ELEMENT_INVALID_POSITION.getMessage());
				}

				/*
				 * increment of the counter related to the number of fields (if
				 * new object)
				 */
				counter++;

				/* content row */
				Row contentRow = configCriteria.getSheet().getRow(indexRow + xlsAnnotation.position());
				// TODO ver se isto e a solucao
				// Cell contentCell = contentRow.getCell(idxC+2);
				Cell contentCell = contentRow.getCell(idxC + 1);
				if (contentCell != null) {
					if (Collection.class.isAssignableFrom(fT)) {

						// E uma lista entao ha que crear uma sheet nova
						try {
							f.set(o, unmarshalTreatementToCollection(o, configCriteria));
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						boolean isAppliedToBaseObject = toObject(o, fT, f, contentCell, xlsAnnotation);

						if (!isAppliedToBaseObject && !fT.isPrimitive()) {

							try {
								Object subObjbect = fT.newInstance();

								Class<?> subObjbectClass = subObjbect.getClass();

								int internalCellCounter = unmarshalAsPropagationVertical(configCriteria, subObjbect,
										subObjbectClass, indexRow + xlsAnnotation.position() - 1, idxC);

								/* add the sub object to the parent object */
								f.set(o, subObjbect);

								/* update the index */
								indexRow += internalCellCounter;
							} catch (InstantiationException | IllegalAccessException e) {
								throw new CustomizedRulesException(ExceptionMessage.ELEMENT_NO_SUCH_METHOD.getMessage(),
										e);
							}
						}
					}
				} else {
					o = null;
					counter = -5;
					break;
				}

			}

			/* Process @XlsFreeElement */
			if (f.isAnnotationPresent(XlsFreeElement.class)) {
				XlsFreeElement xlsFreeAnnotation = (XlsFreeElement) f.getAnnotation(XlsFreeElement.class);

				/* validate the row/cell of the element */
				if (xlsFreeAnnotation.row() < 1 || xlsFreeAnnotation.cell() < 1) {
					throw new ElementException(ExceptionMessage.ELEMENT_INVALID_POSITION.getMessage());
				}

				/* content row */
				Row contentRow = configCriteria.getSheet().getRow(xlsFreeAnnotation.row());
				Cell contentCell = contentRow.getCell(xlsFreeAnnotation.cell() - 1);

				// initialize Element
				XlsElement xlsAnnotation = XlsElementFactory.build(xlsFreeAnnotation);

				boolean isAppliedToBaseObject = toObject(o, fT, f, contentCell, xlsAnnotation);

				if (!isAppliedToBaseObject && !fT.isPrimitive()) {
					throw new ElementException(ExceptionMessage.ELEMENT_COMPLEX_OBJECT.getMessage());
				}
			}
		}
		return counter;
	}

	/**
	 * Generate file output stream.
	 * 
	 * @param wb
	 *            the {@link Workbook}
	 * @param name
	 *            the name
	 * @return the {@link FileOutputStream} generated
	 * @throws WorkbookException
	 *             given when problem at the generation of the FileOutputStream.
	 */
	private FileOutputStream workbookFileOutputStream(final Workbook wb, final String name) throws WorkbookException {
		FileOutputStream output = null;
		try {
			output = new FileOutputStream(name);
			wb.write(output);
			output.close();
		} catch (IOException e) {
			throw new WorkbookException(e.getMessage(), e);
		}
		return output;
	}

	/**
	 * Generate the byte array.
	 * 
	 * @param wb
	 *            the {@link Workbook}
	 * @return the byte[]
	 * @throws WorkbookException
	 *             given when problem at the generation of the byte[].
	 */
	private byte[] workbookToByteAray(final Workbook wb) throws WorkbookException {
		ByteArrayOutputStream bos = null;
		try {
			bos = new ByteArrayOutputStream();

			wb.write(bos);
			bos.close();
		} catch (IOException e) {
			throw new WorkbookException(e.getMessage(), e);
		}
		return bos.toByteArray();
	}

	/* ######################## engine methods ########################## */

	/**
	 * Generate the workbook based at the {@link XConfigCriteria} and the object
	 * passed as parameters.
	 * 
	 * @param configCriteria
	 *            the {@link XConfigCriteria} to use.
	 * @param object
	 *            the object to apply at the workbook.
	 * @throws WorkbookException
	 *             given when a not supported action.
	 */
	private void marshalEngine(final XConfigCriteria configCriteria, final Object object) throws WorkbookException {

		if (object == null) {
			throw new ElementException(ExceptionMessage.ELEMENT_NULL_OBJECT.getMessage());
		}

		/* initialize the runtime class of the object */
		Class<?> oC = initializeRuntimeClass(object);

		/* initialize configuration data */
		initializeConfigurationData(configCriteria, oC, false, true);

		/* initialize Workbook */
		configCriteria.setWorkbook(initializeWorkbook(configCriteria.getExtension()));

		/* initialize style cell via annotations */
		initializeCellStyleViaAnnotation(oC, configCriteria);

		/* initialize style cell via default option */
		configCriteria.initializeCellDecorator();

		/* initialize Sheet */
		configCriteria.setSheet(initializeSheet(configCriteria.getWorkbook(), configCriteria.getTitleSheet()));

		/* initialize Row & Cell */
		int idxRow = configCriteria.getStartRow();
		int idxCell = configCriteria.getStartCell();

		/* initialize rows according the PropagationType */
		if (PropagationType.PROPAGATION_HORIZONTAL.equals(configCriteria.getPropagation())) {
			configCriteria.setRowHeader(initializeRow(configCriteria.getSheet(), idxRow++));
			configCriteria.setRow(initializeRow(configCriteria.getSheet(), idxRow++));
			marshalAsPropagationHorizontal(configCriteria, object, oC, idxRow, idxCell, 0);

		} else {
			//FiXME 
			idxCell = configCriteria.getStartCell() + 1;
			marshalAsPropagationVertical(configCriteria, object, oC, idxRow, idxCell, 0);

		}

		/* apply the freeze pane */
		SheetFreezePaneHandler.applyFreezePane(configCriteria);

		/* apply the elements as group */
		SheetGroupElementHandler.applyGroupElements(configCriteria);

		/* TODO apply background color to sheet tab */
		SheetStyleHandler.applyTabColor(configCriteria, 3);

		/* apply the column resize */
		configCriteria.applyColumnWidthToSheet();
	}

	/**
	 * Extract from the workbook based at the {@link XConfigCriteria} and the
	 * object passed as parameters.
	 * 
	 * @param configCriteria
	 *            the {@link XConfigCriteria} to use.
	 * @param object
	 *            the object to apply at the workbook.
	 * @param oC
	 *            the object class
	 * @throws WorkbookException
	 *             given when a not supported action.
	 */
	private void unmarshalEngine(final XConfigCriteria configCriteria, final Object object, final Class<?> oC)
			throws WorkbookException {


		/* initialize sheet */
		if (StringUtils.isBlank(configCriteria.getTitleSheet())) {
			throw new SheetException(ExceptionMessage.SHEET_CREATION_SHEET.getMessage());
		}
		Sheet   sheetBackup = null;
		Object inputObject = object;
		Class<?> inputOc = oC;
		for (int i = 0; i < configCriteria.getWorkbook().getNumberOfSheets(); i++) {
			configCriteria.setTitleSheet(configCriteria.getWorkbook().getSheetName(i));
			//i> 0
			//por todos os fields, os que sao collection
			//percorrere
			//title contains @Xls name
			if(i>0){
				
				oC.getAnnotationsByType(XlsSheet.class);
				for(Field f : oC.getDeclaredFields()){
					if(Collection.class.isAssignableFrom(f.getType())){
						ParameterizedType stringListType = (ParameterizedType) f.getGenericType();
				        Class<?> stringListClass = (Class<?>) stringListType.getActualTypeArguments()[0];
				        System.out.println(stringListClass); // class java.lang.String.
						XlsSheet xlsS = stringListClass.getAnnotation(XlsSheet.class);
						f.setAccessible(true);
						if(xlsS.title().equals(configCriteria.getWorkbook().getSheetName(i))){
								
									Class<?> fT1 = f.getType();
									
									try {
										try {
											try {
												inputObject = fT1.forName(stringListClass.getCanonicalName()).newInstance();
											} catch (IllegalAccessException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
										} catch (InstantiationException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									} catch (ClassNotFoundException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									
								
								inputOc=stringListClass;
								break;
							
						}
					}
				}
			}
			
           
			/* initialize sheet */
			if (StringUtils.isBlank(configCriteria.getTitleSheet())) {
				throw new SheetException(ExceptionMessage.SHEET_CREATION_SHEET.getMessage());
			}
			configCriteria.setSheet(configCriteria.getWorkbook().getSheet(configCriteria.getTitleSheet()));
			sheetBackup= configCriteria.getSheet();
			/* initialize index row & cell */
			int idxRow = configCriteria.getStartRow();
			int idxCell = configCriteria.getStartCell();
			
			if (PropagationType.PROPAGATION_HORIZONTAL.equals(configCriteria.getPropagation())) {
				unmarshalAsPropagationHorizontal(configCriteria, inputObject, inputOc, idxRow, idxCell);
			} else {
				unmarshalAsPropagationVertical(configCriteria, inputObject, inputOc, idxRow, idxCell);
			}
			if(sheetBackup!=configCriteria.getSheet()){
				configCriteria.setSheet(sheetBackup);
			}
		}

	}

	/**
	 * Generate the workbook based at the {@link XConfigCriteria} and the object
	 * passed as parameters.
	 * 
	 * 
	 * @param configCriteria
	 *            the {@link XConfigCriteria} to use.
	 * @param listObject
	 *            the collection of objects to apply at the workbook.
	 * @throws WorkbookException
	 *             given when a not supported action.
	 */
	private void marshalCollectionEngine(final XConfigCriteria configCriteria, final Collection<?> listObject,
			final boolean insideCollection) throws WorkbookException {

		int idxCell = 0;
		if (listObject == null || listObject.isEmpty()) {
			throw new ElementException(ExceptionMessage.ELEMENT_NULL_OBJECT.getMessage());
		}
		Object objectRT;
		try {
			// TOVERIFY
			objectRT = listObject.iterator().next();
			// objectRT = listObject.getClass().stream().findFirst().get();
		} catch (Exception e) {
			throw new ElementException(ExceptionMessage.ELEMENT_NULL_OBJECT.getMessage());
		}

		/* initialize the runtime class of the object */
		Class<?> oC = initializeRuntimeClass(objectRT);

		/* initialize configuration data */
		 initializeConfigurationData(configCriteria, oC, insideCollection,true);

		// initialize Workbook
		configCriteria.setWorkbook(initializeWorkbook(configCriteria.getExtension()));

		// initialize style cell via annotation
		initializeCellStyleViaAnnotation(oC, configCriteria);

		// initialize style cell via default option
		configCriteria.initializeCellDecorator();


		marshallCollectionEngineT(configCriteria, listObject, idxCell, oC, 0);
	}

	private void marshallCollectionEngineT(final XConfigCriteria configCriteria, final Collection<?> listObject,
			int idxCell, Class<?> oC, int cL) throws WorkbookException {

		if(!CascadeHandler.isAuthorizedCascadeLevel(configCriteria, cL, listObject)){
			return;
		}
		
		int idxRow;
		int indexCellCalculated = idxCell;
		@SuppressWarnings("rawtypes")
		Iterator iterator = listObject.iterator();
		while (iterator.hasNext()) {
			Object object = iterator.next();

			/* initialize configuration data */
			initializeConfigurationData(configCriteria, object.getClass(), false, false);

			// initialize rows according the PropagationType
			if (PropagationType.PROPAGATION_HORIZONTAL.equals(configCriteria.getPropagation())) {
				indexCellCalculated = configCriteria.getStartCell();
				idxRow = preparePropagationHorizontal(configCriteria);

				marshalAsPropagationHorizontal(configCriteria, object, object.getClass(), idxRow, indexCellCalculated,
						cL + 1);

			} else {
				// parentsheet para fazer
				indexCellCalculated = preparePropagationVertical(configCriteria, indexCellCalculated);
				idxRow = configCriteria.getStartRow();

				marshalAsPropagationVertical(configCriteria, object, oC, idxRow, indexCellCalculated, cL + 1);
			}
		}
	}

	private void marshallCollectionEngineT(final XConfigCriteria configCriteria, final Collection<?> listObject,
			int idxCell, Class<?> oC, Sheet sheetBackup) throws WorkbookException {
		
		int idxRow;
		if (listObject != null) {
			@SuppressWarnings("rawtypes")
			Iterator iterator = listObject.iterator();
			while (iterator.hasNext()) {
				Object object = iterator.next();
				
				/* initialize configuration data */
				initializeConfigurationData(configCriteria, object.getClass(), false,false);

				// initialize rows according the PropagationType
				if (PropagationType.PROPAGATION_HORIZONTAL.equals(configCriteria.getPropagation())) {
					idxCell = configCriteria.getStartCell();
					idxRow = preparePropagationHorizontal(configCriteria);

					marshalAsPropagationHorizontal(configCriteria, object, object.getClass(), idxRow, idxCell, 0);

				} else {
					// parentsheet para fazer
					
				    idxCell = preparePropagationVertical(configCriteria, idxCell);
					idxRow= configCriteria.getStartRow();
					
					

					int zzzz=marshalAsPropagationVertical(configCriteria, object, oC, idxRow, idxCell, 0);
					System.out.println(zzzz);
				}

			}
		}
		/* apply the column resize */
		configCriteria.applyColumnWidthToSheet();
	}

	/* ######################## Marshal methods ########################## */

	/**
	 * Generate the sheet based at the object passed as parameter and return the
	 * sheet generated.
	 * 
	 * @param object
	 *            the object to apply at the workbook.
	 * @return the {@link Sheet} generated
	 * @throws WorkbookException
	 *             given when a not supported action.
	 */
	@Override
	public Collection<Sheet> marshalToSheet(final Object object) throws WorkbookException {
		/* Initialize a basic ConfigCriteria */
		XConfigCriteria configCriteria = new XConfigCriteria();

		/* Generate the workbook based at the object passed as parameter */
		marshalEngine(configCriteria, object);

		/* Return the collection of Sheet generated */
		List<Sheet> collection = new ArrayList<>();
		if(configCriteria.getWorkbook().getNumberOfSheets() > 0){
			for(int i = 0; i < configCriteria.getWorkbook().getNumberOfSheets(); i++)
			collection.add(configCriteria.getWorkbook().getSheetAt(i));
		} else {
			return Collections.emptyList();
		}
		return collection;
	}

	/**
	 * Generate the sheet based at the {@link XConfigCriteria} and the object
	 * passed as parameters and return the sheet generated.
	 * 
	 * @param configCriteria
	 *            the {@link XConfigCriteria} to use
	 * @param object
	 *            the object to apply at the workbook.
	 * @return the {@link Sheet} generated
	 * @throws WorkbookException
	 *             given when a not supported action.
	 */
	@Override
	public Collection<Sheet> marshalToSheet(final XConfigCriteria configCriteria, final Object object) throws WorkbookException {
		/* Generate the workbook based at the object passed as parameter */
		marshalEngine(configCriteria, object);

		/* Return the collection of Sheet generated */
		List<Sheet> collection = new ArrayList<>();
		if(configCriteria.getWorkbook().getNumberOfSheets() > 0){
			for(int i = 0; i < configCriteria.getWorkbook().getNumberOfSheets(); i++)
			collection.add(configCriteria.getWorkbook().getSheetAt(i));
		} else {
			return Collections.emptyList();
		}
		return collection;
	}

	/**
	 * Generate the workbook based at the object passed as parameter and return
	 * the workbook generated.
	 * 
	 * @param object
	 *            the object to apply at the workbook.
	 * @return the {@link Workbook} generated
	 * @throws WorkbookException
	 *             given when a not supported action.
	 */
	@Override
	public Workbook marshalToWorkbook(final Object object) throws WorkbookException {
		/* Initialize a basic ConfigCriteria */
		XConfigCriteria configCriteria = new XConfigCriteria();

		/* Generate the workbook based at the object passed as parameter */
		marshalEngine(configCriteria, object);
		
		/* Return the Workbook generated */
		return configCriteria.getWorkbook();
	}

	/**
	 * Generate the workbook based at the {@link XConfigCriteria} and the object
	 * passed as parameters and return the workbook generated.
	 * 
	 * @param configCriteria
	 *            the {@link XConfigCriteria} to use
	 * @param object
	 *            the object to apply at the workbook.
	 * @return the {@link Workbook} generated
	 * @throws WorkbookException
	 *             given when a not supported action.
	 */
	@Override
	public Workbook marshalToWorkbook(final XConfigCriteria configCriteria, final Object object)
			throws WorkbookException {
		/* Generate the workbook from the object passed as parameter */
		marshalEngine(configCriteria, object);

		/* Return the Workbook generated */
		return configCriteria.getWorkbook();
	}

	/**
	 * Generate the workbook based at the object passed as parameter and return
	 * the byte[] generated.
	 * 
	 * @param object
	 *            the object to apply at the workbook.
	 * @return the {@link Workbook} generated
	 * @throws WorkbookException
	 *             given when a not supported action.
	 */
	@Override
	public byte[] marshalToByte(final Object object) throws WorkbookException {
		/* Initialize a basic ConfigCriteria */
		XConfigCriteria configCriteria = new XConfigCriteria();

		/* Generate the workbook from the object passed as parameter */
		marshalEngine(configCriteria, object);

		/* Generate the byte array to return */
		return workbookToByteAray(configCriteria.getWorkbook());
	}

	/**
	 * Generate the workbook based at the {@link XConfigCriteria} and the object
	 * passed as parameter and return the byte[] generated.
	 * 
	 * @param configCriteria
	 *            the {@link XConfigCriteria} to use
	 * @param object
	 *            the object to apply at the workbook.
	 * @return the {@link Workbook} generated
	 * @throws WorkbookException
	 *             given when a not supported action.
	 */
	@Override
	public byte[] marshalToByte(final XConfigCriteria configCriteria, final Object object) throws WorkbookException {
		/* Generate the workbook from the object passed as parameter */
		marshalEngine(configCriteria, object);

		/* Generate the byte array to return */
		return workbookToByteAray(configCriteria.getWorkbook());
	}

	/**
	 * Generate the workbook based at the object passed as parameters and save
	 * it at the path send as parameter.
	 * 
	 * @param object
	 *            the object to apply at the workbook.
	 * @param pathFile
	 *            the file path where will be the file saved
	 * @throws WorkbookException
	 *             given when a not supported action.
	 */
	@Override
	public void marshalAndSave(final Object object, final String pathFile) throws WorkbookException {
		/* Generate the workbook from the object passed as parameter */
		XConfigCriteria configCriteria = new XConfigCriteria();

		marshalAndSave(configCriteria, object, pathFile);
	}

	/**
	 * Generate the workbook based at the {@link XConfigCriteria} and the object
	 * passed as parameters and save it at the path send as parameter.
	 * 
	 * @param configCriteria
	 *            the {@link XConfigCriteria} to use
	 * @param object
	 *            the object to apply at the workbook.
	 * @param pathFile
	 *            the file path where will be the file saved
	 * @throws WorkbookException
	 *             given when a not supported action.
	 */
	@Override
	public void marshalAndSave(final XConfigCriteria configCriteria, final Object object, final String pathFile)
			throws WorkbookException {
		/* Generate the workbook from the object passed as parameter */
		marshalEngine(configCriteria, object);

		/*
		 * check if the path terminate with the file separator, otherwise will
		 * be added to avoid any problem
		 */
		String internalPathFile = pathFile;
		if (!pathFile.endsWith(File.separator)) {
			internalPathFile = pathFile.concat(File.separator);
		}

		/* Save the Workbook at a specified path (received as parameter) */
		workbookFileOutputStream(configCriteria.getWorkbook(), internalPathFile + configCriteria.getCompleteFileName());
	}

	/**
	 * Generate the sheet based at the collection of objects passed as parameter
	 * and return the sheet generated.
	 * 
	 * @param listObject
	 *            the collection to apply at the workbook.
	 * @return the {@link Sheet} generated
	 * @throws WorkbookException
	 *             given when a not supported action.
	 */
	@Override
	public Sheet marshalCollectionToSheet(final Collection<?> listObject) throws WorkbookException {
		/* Initialize a basic ConfigCriteria */
		XConfigCriteria configCriteria = new XConfigCriteria();

		/*
		 * Generate the workbook based at the list of objects passed as
		 * parameter
		 */
		marshalCollectionEngine(configCriteria, listObject, false);

		/* Return the Sheet generated */
		return configCriteria.getWorkbook().getSheetAt(0);
	}

	/**
	 * Generate the sheet based at the {@link XConfigCriteria} and the
	 * collection of objects passed as parameters and return the sheet
	 * generated.
	 * 
	 * @param configCriteria
	 *            the {@link XConfigCriteria} to use
	 * @param listObject
	 *            the collection to apply at the workbook.
	 * @return the {@link Sheet} generated
	 * @throws WorkbookException
	 *             given when a not supported action.
	 */
	@Override
	public Sheet marshalCollectionToSheet(final XConfigCriteria configCriteria, final Collection<?> listObject)
			throws WorkbookException {
		/*
		 * Generate the workbook based at the list of objects passed as
		 * parameter
		 */
		marshalCollectionEngine(configCriteria, listObject, false);

		/* Return the Sheet generated */
		return configCriteria.getWorkbook().getSheetAt(0);
	}

	/**
	 * Generate the workbook based at the collection of objects passed as
	 * parameter and return the workbook generated.
	 * 
	 * @param listObject
	 *            the collection to apply at the workbook.
	 * @return the {@link Workbook} generated
	 * @throws WorkbookException
	 *             given when a not supported action.
	 */
	@Override
	public Workbook marshalCollectionToWorkbook(final Collection<?> listObject) throws WorkbookException {
		/* Initialize a basic ConfigCriteria */
		XConfigCriteria configCriteria = new XConfigCriteria();

		/* Generate the workbook based at the object passed as parameter */
		marshalCollectionEngine(configCriteria, listObject, false);

		/* Return the Workbook generated */
		return configCriteria.getWorkbook();
	}

	/**
	 * Generate the workbook based at the {@link XConfigCriteria} and the
	 * collection of objects passed as parameters and return the workbook
	 * generated.
	 * 
	 * @param configCriteria
	 *            the {@link XConfigCriteria} to use
	 * @param listObject
	 *            the collection to apply at the workbook.
	 * @return the {@link Workbook} generated
	 * @throws WorkbookException
	 *             given when a not supported action.
	 */
	@Override
	public Workbook marshalCollectionToWorkbook(final XConfigCriteria configCriteria, final Collection<?> listObject)
			throws WorkbookException {
		/* Generate the workbook from the object passed as parameter */
		marshalCollectionEngine(configCriteria, listObject, false);

		/* Return the Workbook generated */
		return configCriteria.getWorkbook();
	}

	/**
	 * Generate the workbook based at the collection of objects passed as
	 * parameter and save it at the path send as parameter.
	 * 
	 * @param listObject
	 *            the collection to apply at the workbook.
	 * @param pathFile
	 *            the file path where will be the file saved
	 * @throws WorkbookException
	 *             given when a not supported action.
	 */
	@Override
	public void marshalAsCollectionAndSave(final Collection<?> listObject, final String pathFile)
			throws WorkbookException {

		/* Initialize a basic ConfigCriteria */
		XConfigCriteria configCriteria = new XConfigCriteria();

		marshalAsCollectionAndSave(configCriteria, listObject, pathFile);
	}

	/**
	 * Generate the workbook based at the {@link XConfigCriteria} and the
	 * collection of objects passed as parameter and save it at the path send as
	 * parameter.
	 * 
	 * @param configCriteria
	 *            the {@link XConfigCriteria} to use
	 * @param listObject
	 *            the collection to apply at the workbook.
	 * @param pathFile
	 *            the file path where will be the file saved
	 * @throws WorkbookException
	 *             given when a not supported action.
	 */
	@Override
	public void marshalAsCollectionAndSave(final XConfigCriteria configCriteria, final Collection<?> listObject,
			final String pathFile) throws WorkbookException {
		/* Generate the workbook from the object passed as parameter */
		marshalCollectionEngine(configCriteria, listObject, false);

		/*
		 * check if the path terminate with the file separator, otherwise will
		 * be added to avoid any problem
		 */
		String internalPathFile = pathFile;
		if (!pathFile.endsWith(File.separator)) {
			internalPathFile = pathFile.concat(File.separator);
		}

		/* save the Workbook at a specified path (received as parameter) */
		workbookFileOutputStream(configCriteria.getWorkbook(), internalPathFile + configCriteria.getCompleteFileName());

	}

	/**
	 * Generate the workbook based at the collection of objects and return the
	 * byte[] generated.
	 * 
	 * @param listObject
	 *            the collection to apply at the workbook.
	 * @return the byte[] generated
	 * @throws WorkbookException
	 *             given when a not supported action.
	 */
	@Override
	public byte[] marshalCollectionToByte(final Collection<?> listObject) throws WorkbookException {
		/* Initialize a basic ConfigCriteria */
		XConfigCriteria configCriteria = new XConfigCriteria();

		/* Generate the workbook from the object passed as parameter */
		marshalCollectionEngine(configCriteria, listObject, false);

		/* Generate the byte array to return */
		return workbookToByteAray(configCriteria.getWorkbook());
	}

	/**
	 * Generate the workbook based at the {@link XConfigCriteria} and the
	 * collection of objects and return the byte[] generated.
	 * 
	 * @param listObject
	 *            the collection to apply at the workbook.
	 * @return the byte[] generated
	 * @throws WorkbookException
	 *             given when a not supported action.
	 */
	@Override
	public byte[] marshalCollectionToByte(final XConfigCriteria configCriteria, final Collection<?> listObject)
			throws WorkbookException {
		/* Generate the workbook from the object passed as parameter */
		marshalCollectionEngine(configCriteria, listObject, false);

		/* Generate the byte array to return */
		return workbookToByteAray(configCriteria.getWorkbook());
	}

	/* ######################## Unmarshal methods ######################## */

	/**
	 * Generate the object from the workbook passed as parameter.
	 * 
	 * @param object
	 *            the object to fill up.
	 * @param workbook
	 *            the {@link Workbook} to read and pass the information to the
	 *            object
	 * @throws WorkbookException
	 *             given when a not supported action.
	 */
	@Override
	public void unmarshalFromWorkbook(final Object object, final Workbook workbook) throws WorkbookException {

		/* initialize the runtime class of the object */
		Class<?> oC = initializeRuntimeClass(object);

		/* initialize configuration data */
		XConfigCriteria configCriteria = new XConfigCriteria();
		initializeConfigurationData(configCriteria, oC, false, true);

		/* set workbook */
		configCriteria.setWorkbook(workbook);

		/* Extract from the workbook to the object passed as parameter */
		unmarshalEngine(configCriteria, object, oC);
	}

	/**
	 * Generate the object from the path file passed as parameter.
	 * 
	 * @param object
	 *            the object to fill up.
	 * @param pathFile
	 *            the path where is found the file to read and pass the
	 *            information to the object
	 * @throws WorkbookException
	 *             given when a not supported action.
	 */
	@Override
	public void unmarshalFromPath(final Object object, final String pathFile) throws WorkbookException {

		/* initialize the runtime class of the object */
		Class<?> oC = initializeRuntimeClass(object);

		/* initialize configuration data */
		XConfigCriteria configCriteria = new XConfigCriteria();
		initializeConfigurationData(configCriteria, oC, false, true);

		/*
		 * check if the path terminate with the file separator, otherwise will
		 * be added to avoid any problem
		 */
		String internalPathFile = pathFile;
		if (!pathFile.endsWith(File.separator)) {
			internalPathFile = pathFile.concat(File.separator);
		}

		try {
			FileInputStream input = new FileInputStream(internalPathFile + configCriteria.getCompleteFileName());

			/* set workbook */
			configCriteria.setWorkbook(initializeWorkbook(input, configCriteria.getExtension()));

			/* Extract from the workbook to the object passed as parameter */
			unmarshalEngine(configCriteria, object, oC);

			input.close();
		} catch (IOException e) {
			throw new WorkbookException(e.getMessage(), e);
		}
	}

	/**
	 * Generate the object from the byte array passed as parameter.
	 * 
	 * @param object
	 *            the object to fill up.
	 * @param byteArray
	 *            the byte[] to read and pass the information to the object
	 * @throws WorkbookException
	 *             given when a not supported action.
	 */
	@Override
	public void unmarshalFromByte(final Object object, final byte[] byteArray) throws WorkbookException {

		/* initialize the runtime class of the object */
		Class<?> oC = initializeRuntimeClass(object);

		/* initialize configuration data */
		XConfigCriteria configCriteria = new XConfigCriteria();
		initializeConfigurationData(configCriteria, oC, false, true);

		/* set workbook */
		configCriteria.setWorkbook(initializeWorkbook(byteArray, configCriteria.getExtension()));

		/* Extract from the workbook to the object passed as parameter */
		unmarshalEngine(configCriteria, object, oC);
	}

	@Override
	public Collection<?> unmarshalToCollection(final XConfigCriteria configCriteria, final Object object,
			String excelFilePath) throws WorkbookException {
		// FileInputStream inputStream = new FileInputStream(new
		// File(excelFilePath));

		/* initialize configuration data */
		initializeConfigurationData(configCriteria, object.getClass(), false, true);

		/*
		 * check if the path terminate with the file separator, otherwise will
		 * be added to avoid any problem
		 */
		String internalPathFile = excelFilePath;
		if (!excelFilePath.endsWith(File.separator)) {
			internalPathFile = excelFilePath.concat(File.separator);
		}

		Collection<Object> collection = null;
		try {
			FileInputStream input = new FileInputStream(internalPathFile + configCriteria.getCompleteFileName());

			/* set workbook */
			configCriteria.setWorkbook(initializeWorkbook(input, configCriteria.getExtension()));

			collection = unmarshalTreatementToCollection(object, configCriteria);
		} catch (IOException e) {
			throw new WorkbookException(e.getMessage(), e);
		}
		return collection;
	}

	private Collection<Object> unmarshalTreatementToCollection(Object object, XConfigCriteria configCriteria)
			throws WorkbookException {
		/* initialize the runtime class of the object */
		Class<?> oC = initializeRuntimeClass(object);

		initializeConfigurationData(configCriteria, oC, false, true);
		Sheet s = configCriteria.getWorkbook().getSheet(truncateTitle(configCriteria.getTitleSheet()));
		configCriteria.setSheet(s);
		/* initialize index row & cell */
		int idxRow = configCriteria.getStartRow();
		int idxCell = configCriteria.getStartCell();
		Iterator<Row> iterator = s.iterator();
		Collection<Object> collection = new ArrayList<>();
		if (PropagationType.PROPAGATION_HORIZONTAL.equals(configCriteria.getPropagation())) {
			while (iterator.hasNext()) {
				try {
					Object obj = oC.newInstance();
					int counter = unmarshalAsPropagationHorizontal(configCriteria, obj, oC, idxRow, idxCell);
					if (obj != null && counter != -5) {
						collection.add((Object) obj);
						idxRow = idxRow + 1;
					} else {
						break;
					}
				} catch (InstantiationException | IllegalAccessException e) {
					throw new CustomizedRulesException(ExceptionMessage.ELEMENT_NO_SUCH_METHOD.getMessage(), e);
				}

			}

		} else {
			while (iterator.hasNext()) {
				try {
					Object obj = oC.newInstance();
					int counter = unmarshalAsPropagationVertical(configCriteria, obj, oC, idxRow, idxCell);
					if (obj != null && counter != -5) {
						collection.add((Object) obj);
						idxCell = idxCell + 1;
						// idxRow = idxRow+1;
					} else {
						break;
					}
				} catch (InstantiationException | IllegalAccessException e) {
					throw new CustomizedRulesException(ExceptionMessage.ELEMENT_NO_SUCH_METHOD.getMessage(), e);
				}

			}

		}
		return collection;
	}

	/**
	 * Method to truncate title of the sheet. The length max is 31 caracters
	 * 
	 * @param title
	 * @return
	 */
	private String truncateTitle(String title) {
		return title != null && title.length() > 31 ? title.substring(0, 31) : title;
	}
}