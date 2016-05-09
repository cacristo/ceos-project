package net.ceos.project.poi.annotated.core;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
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
import net.ceos.project.poi.annotated.exception.ConverterException;
import net.ceos.project.poi.annotated.exception.CustomizedRulesException;
import net.ceos.project.poi.annotated.exception.ElementException;
import net.ceos.project.poi.annotated.exception.SheetException;

public class Engine implements IEngine {

	/**
	 * Get the runtime class of the object passed as parameter.
	 * 
	 * @param object
	 *            the object
	 * @return the runtime class
	 * @throws ElementException
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
	 * @param oC
	 *            the {@link Class<?>}
	 * @return
	 * @throws ConfigurationException
	 */
	private void initializeConfigurationData(final XConfigCriteria configCriteria, final Class<?> oC, final  boolean insideCollection)
			throws ConfigurationException {
		/* Process @XlsConfiguration */
		if (oC.isAnnotationPresent(XlsConfiguration.class)) {
			XlsConfiguration xlsAnnotation = (XlsConfiguration) oC.getAnnotation(XlsConfiguration.class);
			initializeXlsConfiguration(configCriteria, xlsAnnotation);
		} else {
			throw new ConfigurationException(
					ExceptionMessage.CONFIGURATION_XLSCONFIGURATION_MISSING.getMessage());
		}
		/* Process @XlsSheet */
		if (oC.isAnnotationPresent(XlsSheet.class)) {
			XlsSheet xlsAnnotation = (XlsSheet) oC.getAnnotation(XlsSheet.class);
			initializeXlsSheet(configCriteria, xlsAnnotation);
			if(insideCollection && oC.isAnnotationPresent(XlsElement.class)){
				/**
				 * if the collection is an attribut inside an object
				 * we get the sheet title name from the element
				*/
				XlsElement xlsElement = (XlsElement) oC.getAnnotation(XlsElement.class);
				if(!xlsElement.parentSheet()){
					configCriteria.setTitleSheet(xlsElement.title());
					configCriteria.setElement(xlsElement);
				}
			}
		} else {
			throw new ConfigurationException(ExceptionMessage.CONFIGURATION_XLSSHEET_MISSING.getMessage());
		}
	}

	/**
	 * Add the main xls configuration.
	 * 
	 * @param configCriteria
	 *            the {@link XConfigCriteria}
	 * @param annotation
	 *            the {@link XlsConfiguration}
	 * @return
	 */
	private void initializeXlsConfiguration(final XConfigCriteria configCriteria, final XlsConfiguration annotation) {
		configCriteria.setFileName(StringUtils.isBlank(configCriteria.getFileName()) ? annotation.nameFile()
				: configCriteria.getFileName());
		configCriteria.setExtension(
				configCriteria.getExtension() == null ? annotation.extensionFile() : configCriteria.getExtension());
		configCriteria.setCompleteFileName(configCriteria.getFileName() + configCriteria.getExtension().getExtension());
	}

	/**
	 * Add the sheet configuration.
	 * 
	 * @param configCriteria
	 *            the {@link XConfigCriteria}
	 * @param annotation
	 *            the {@link XlsSheet}
	 * @return
	 */
	private void initializeXlsSheet(final XConfigCriteria configCriteria, final XlsSheet annotation) {
		configCriteria.setTitleSheet(annotation.title());
		configCriteria.setPropagation(annotation.propagation());
		configCriteria.setCascadeLevel(annotation.cascadeLevel());
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
					throw new ConfigurationException(
							ExceptionMessage.CONFIGURATION_CELLSTYLE_DUPLICATED.getMessage());
				}
				configCriteria.getStylesMap().put(decorator.decoratorName(),
						CellStyleHandler.initializeCellStyleByXlsDecorator(configCriteria.getWorkbook(), decorator));
			}
		}
	}

	/**
	 * Initialize Workbook.
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
	 *            the type of workbook
	 * @return the {@link Workbook}.
	 * @throws IOException
	 */
	private Workbook initializeWorkbook(FileInputStream inputStream, ExtensionFileType type) throws IOException {
		if (type != null && ExtensionFileType.XLS.getExtension().equals(type.getExtension())) {
			return new HSSFWorkbook(inputStream);
		} else {
			return new XSSFWorkbook(inputStream);
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
	 * @throws IOException
	 */
	private Workbook initializeWorkbook(final byte[] byteArray, final ExtensionFileType type) throws IOException {
		if (type != null && ExtensionFileType.XLS.getExtension().equals(type.getExtension())) {
			return new HSSFWorkbook(new ByteArrayInputStream(byteArray));
		} else {
			return new XSSFWorkbook(new ByteArrayInputStream(byteArray));
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

	private void initializeCellByField(final XConfigCriteria configCriteria, final XlsFreeElement xlsAnnotation,
			final Object o, final Field field, final int idxC, final int cL)
					throws ElementException, ConverterException, CustomizedRulesException {

		/* validate cascade level */
		if (cL <= configCriteria.getCascadeLevel().getCode()) {

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
	 * @return in case of the object return the number of cell created,
	 *         otherwise 0
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws CustomizedRulesException
	 * @throws ElementException
	 * @throws ConfigurationException
	 * @throws InvocationTargetException
	 * @throws ConverterException
	 * @throws NoSuchMethodException 
	 * @throws SheetException 
	 * @throws Exception
	 */
	private int initializeCellByFieldHorizontal(final XConfigCriteria configCriteria, final Object o, final int idxR,
			final int idxC, final int cL)
					throws IllegalAccessException, InstantiationException, InvocationTargetException,
					ConfigurationException, ElementException, CustomizedRulesException, ConverterException, SheetException, NoSuchMethodException {

		int counter = 0;

		/* validate cascade level */
		if (cL <= configCriteria.getCascadeLevel().getCode()) {
			/* make the field accessible to recover the value */
			configCriteria.getField().setAccessible(true);

			Class<?> fT = configCriteria.getField().getType();

			if (Collection.class.isAssignableFrom(fT)) {
				
				//E uma lista entao ha que crear uma sheet nova
				marshallCollectionEngineT(configCriteria,(Collection<?>) CGen.toCollection(o, configCriteria.getField()),idxC,fT);
			}else{
				
				boolean isAppliedObject = toExcel(configCriteria, o, fT, idxC);
	
				if (!isAppliedObject && !fT.isPrimitive()) {
					Object nO = configCriteria.getField().get(o);
					/* manage null objects */
					if (nO == null) {
						nO = fT.newInstance();
					}
					Class<?> oC = nO.getClass();
	
					counter = marshalAsPropagationHorizontal(configCriteria, nO, oC, idxR, idxC - 1, cL + 1);
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
	 * @return
	 * @throws ConverterException
	 * @throws ElementException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ConfigurationException
	 * @throws CustomizedRulesException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	private int initializeCellByFieldVertical(final XConfigCriteria configCriteria, final Object o, final Row r,
			final int idxR, final int idxC, int cL)
					throws ElementException, ConverterException, IllegalAccessException, InstantiationException,
					NoSuchMethodException, InvocationTargetException, CustomizedRulesException, ConfigurationException {

		int counter = 0;

		/* validate cascade level */
		if (cL <= configCriteria.getCascadeLevel().getCode()) {
			/* make the field accessible to recover the value */
			configCriteria.getField().setAccessible(true);

			Class<?> fT = configCriteria.getField().getType();

			configCriteria.setRow(r);

			boolean isAppliedObject = toExcel(configCriteria, o, fT, idxC);

			if (!isAppliedObject && !fT.isPrimitive()) {
				Object nO = configCriteria.getField().get(o);
				/* manage null objects */
				if (nO == null) {
					nO = fT.newInstance();
				}
				Class<?> oC = nO.getClass();

				counter = marshalAsPropagationVertical(configCriteria, nO, oC, idxR - 1, idxC - 1, cL + 1);
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
	 * @return
	 * @throws ElementException
	 * @throws ConverterException
	 * @throws CustomizedRulesException
	 * @throws Exception
	 */
	private boolean toExcel(final XConfigCriteria configCriteria, final Object o, final Class<?> fT, final int idxC)
			throws ElementException, ConverterException, CustomizedRulesException {
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
	 * @return
	 * 
	 * @throws IllegalAccessException
	 * @throws ConverterException
	 */
	private boolean toObject(final Object o, final Class<?> fT, final Field f, final Cell c,
			final XlsElement xlsAnnotation) throws IllegalAccessException, ConverterException {
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
	 * @throws ElementException
	 * @throws ConverterException
	 * @throws CustomizedRulesException
	 */
	private void processXlsFreeElement(final XConfigCriteria configCriteria, final Object o, final int cL,
			final Field f) throws ElementException, ConverterException, CustomizedRulesException {

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
	 * Prepare the propagation horizontal:<br>
	 * 1. initialize sheet <br>
	 * 2. initialize header row <br>
	 * 3. initialize row <br>
	 * 
	 * @param configCriteria
	 *            the {@link XConfigCriteria}
	 * @return
	 * @throws SheetException
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
			//TOVERIFY se ha que aumentar a 5 pelo nestedheader
			idxRow =configCriteria.getElement()!=null && configCriteria.getElement().parentSheet() ? configCriteria.getSheet().getLastRowNum() + 3 : configCriteria.getSheet().getLastRowNum() + 1;
			//idxRow = configCriteria.getSheet().getLastRowNum() + 1;
			configCriteria.setRowHeader(null);
			configCriteria.setRow(initializeRow(configCriteria.getSheet(), idxRow++));

		}
		return idxRow;
	}

	/**
	 * Prepare the propagation vertical:<br>
	 * 1. initialize sheet <br>
	 * 2. define next cell index value <br>
	 * 
	 * @param configCriteria
	 *            the {@link XConfigCriteria}
	 * @param idxCell
	 *            the cell index
	 * @return
	 * @throws SheetException
	 */
	private int preparePropagationVertical(final XConfigCriteria configCriteria, int idxCell) throws SheetException {
		int indexCell = idxCell;
		if (configCriteria.getWorkbook().getNumberOfSheets() == 0
				|| configCriteria.getWorkbook().getSheet(truncateTitle(configCriteria.getTitleSheet())) == null) {
			configCriteria.setSheet(initializeSheet(configCriteria.getWorkbook(), configCriteria.getTitleSheet()));
			indexCell = configCriteria.getStartCell();
		} else {
			indexCell += 1;
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
	 * @return
	 * @throws ConfigurationException
	 * @throws ElementException
	 * @throws CustomizedRulesException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws ConverterException
	 * @throws InstantiationException
	 * @throws NoSuchMethodException 
	 * @throws SheetException 
	 * 
	 */
	private int marshalAsPropagationHorizontal(final XConfigCriteria configCriteria, final Object o, final Class<?> oC,
			final int idxR, final int idxC, final int cL)
					throws ConfigurationException, ElementException, CustomizedRulesException, IllegalAccessException,
					InvocationTargetException, InstantiationException, ConverterException, SheetException, NoSuchMethodException {
		/* counter related to the number of fields (if new object) */
		int counter = -1;
		int indexCell = idxC;
		int rem=0;
		/* get declared fields */
		List<Field> fL = Arrays.asList(oC.getDeclaredFields());
		//Order by the list by position
		Collections.sort(fL,new Comparator<Field>(){
            public int compare(Field f1,Field f2){
            	if(f2==null){
            		return 0;
            	}else if (f1!=null && f2!=null && f1.isAnnotationPresent(XlsElement.class) && f2.isAnnotationPresent(XlsElement.class)) {
    				XlsElement element1 = (XlsElement) f1.getAnnotation(XlsElement.class);
    				XlsElement element2 = (XlsElement) f2.getAnnotation(XlsElement.class);
    				return element1.position() - element2.position();
            	}else{
            		return 0;
            	}   	
            }});
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
					try {
						CellHandler.applyCustomizedRules(o, xlsAnnotation.customizedRules());
					} catch (NoSuchMethodException e) {
						throw new CustomizedRulesException(
								ExceptionMessage.CUSTOMIZEDRULES_NO_SUCH_METHOD.getMessage(), e);
					}
				}

				/*
				 * increment of the counter related to the number of fields (if
				 * new object)
				 */
				counter++;
				int pos= idxC + xlsAnnotation.position();
				//if is a list don't generate the head
				if (configCriteria.getRowHeader() != null && !Collection.class.isAssignableFrom(f.getType())) {
					pos = pos-rem;
					/* header treatment */
					CellStyleHandler.initializeHeaderCell(configCriteria.getStylesMap(), configCriteria.getRowHeader(),
							indexCell + xlsAnnotation.position(), xlsAnnotation.title());
					rem=0;
				}else{
					//
					rem=rem+1;
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
	 * @return
	 * @throws ElementException
	 * @throws CustomizedRulesException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException
	 * @throws ConfigurationException
	 * @throws ConverterException
	 * @throws InstantiationException
	 * 
	 */
	private int marshalAsPropagationVertical(final XConfigCriteria configCriteria, final Object o, Class<?> oC,
			final int idxR, final int idxC, final int cL)
					throws ElementException, NoSuchMethodException, IllegalAccessException, InvocationTargetException,
					CustomizedRulesException, ConfigurationException, ConverterException, InstantiationException {
		/* counter related to the number of fields (if new object) */
		int counter = -1;
		int indexCell = idxC;
		int indexRow = idxR;
		/* backup base index of the cell */
		int baseIdxCell = indexCell;

		/* get declared fields */
		List<Field> fL = Arrays.asList(oC.getDeclaredFields());
		Collections.sort(fL,new Comparator<Field>(){
            public int compare(Field f1,Field f2){
            	if(f2==null){
            		return 0;
            	}else if (f1!=null && f2!=null && f1.isAnnotationPresent(XlsElement.class) && f2.isAnnotationPresent(XlsElement.class)) {
    				XlsElement element1 = (XlsElement) f1.getAnnotation(XlsElement.class);
    				XlsElement element2 = (XlsElement) f2.getAnnotation(XlsElement.class);
    				return element1.position() - element2.position();
            	}else{
            		return 0;
            	}   	
            }});
		for (Field f : fL) {
			/* process each field from the object */
			configCriteria.setField(f);

			/* restart the index of the cell */
			indexCell = baseIdxCell;

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
				if (row == null) {
					/* header treatment with nested header */
					int tmpIdxCell = indexCell - 1;
					/* initialize row */
					row = initializeRow(configCriteria.getSheet(), indexRow + xlsAnnotation.position());

					/* apply merge region */
					applyMergeRegion(configCriteria, row, indexRow, tmpIdxCell, false);

					/* header treatment */
					CellStyleHandler.initializeHeaderCell(configCriteria.getStylesMap(), row, indexCell,
							xlsAnnotation.title());

				} else {
					/* header treatment without nested header */
					CellStyleHandler.initializeHeaderCell(configCriteria.getStylesMap(), row, indexCell,
							xlsAnnotation.title());
				}

				/* prepare the column width */
				if (configCriteria.getResizeActive() && xlsAnnotation.columnWidthInUnits() != 0) {
					configCriteria.getColumnWidthMap().put(indexCell + xlsAnnotation.position(),
							xlsAnnotation.columnWidthInUnits());
				}

				/* increment the cell position */
				indexCell++;
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
	 * @return
	 * @throws IllegalAccessException
	 * @throws ConverterException
	 * @throws InstantiationException
	 * @throws ElementException
	 */
	private int unmarshalAsPropagationHorizontal(final XConfigCriteria configCriteria, final Object o, Class<?> oC,
			final int idxR, final int idxC)
					throws IllegalAccessException, ConverterException, InstantiationException, ElementException {
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
				//MAL - ERRO AQUI
				Row contentRow = configCriteria.getSheet().getRow(idxR + 1);
				Cell contentCell = contentRow.getCell(indexCell + xlsAnnotation.position());

				boolean isAppliedToBaseObject = toObject(o, fT, f, contentCell, xlsAnnotation);

				if (!isAppliedToBaseObject && !fT.isPrimitive()) {

					Object subObjbect = fT.newInstance();
					Class<?> subObjbectClass = subObjbect.getClass();

					int internalCellCounter = unmarshalAsPropagationHorizontal(configCriteria, subObjbect,
							subObjbectClass, idxR, indexCell + xlsAnnotation.position() - 1);

					/* add the sub object to the parent object */
					f.set(o, subObjbect);

					/* update the index */
					indexCell += internalCellCounter;
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
	 * @return
	 * @throws IllegalAccessException
	 * @throws ConverterException
	 * @throws InstantiationException
	 * @throws ElementException
	 */
	private int unmarshalAsPropagationVertical(final XConfigCriteria configCriteria, final Object o, Class<?> oC,
			final int idxR, final int idxC)
					throws IllegalAccessException, ConverterException, InstantiationException, ElementException {
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
				Cell contentCell = contentRow.getCell(idxC + 1);

				boolean isAppliedToBaseObject = toObject(o, fT, f, contentCell, xlsAnnotation);

				if (!isAppliedToBaseObject && !fT.isPrimitive()) {

					Object subObjbect = fT.newInstance();
					Class<?> subObjbectClass = subObjbect.getClass();

					int internalCellCounter = unmarshalAsPropagationVertical(configCriteria, subObjbect,
							subObjbectClass, indexRow + xlsAnnotation.position() - 1, idxC);

					/* add the sub object to the parent object */
					f.set(o, subObjbect);

					/* update the index */
					indexRow += internalCellCounter;
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
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	private FileOutputStream workbookFileOutputStream(final Workbook wb, final String name) throws IOException {
		FileOutputStream output = new FileOutputStream(name);
		wb.write(output);
		output.close();
		return output;
	}

	/**
	 * Generate the byte array.
	 * 
	 * @param wb
	 *            the {@link Workbook}
	 * @return the byte[]
	 * @throws IOException
	 */
	private byte[] workbookToByteAray(final Workbook wb) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			wb.write(bos);
		} finally {
			bos.close();
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
	 * @throws ElementException
	 * @throws ConfigurationException
	 * @throws SheetException
	 * @throws ConverterException
	 * @throws CustomizedRulesException
	 * @throws InstantiationException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException
	 */
	private void marshalEngine(final XConfigCriteria configCriteria, final Object object) throws ElementException,
			ConfigurationException, SheetException, IllegalAccessException, InvocationTargetException,
			InstantiationException, CustomizedRulesException, ConverterException, NoSuchMethodException {

		if (object == null) {
			throw new ElementException(ExceptionMessage.ELEMENT_NULL_OBJECT.getMessage());
		}

		/* initialize the runtime class of the object */
		Class<?> oC = initializeRuntimeClass(object);

		/* initialize configuration data */
		initializeConfigurationData(configCriteria, oC, false);

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
			marshalAsPropagationVertical(configCriteria, object, oC, idxRow, idxCell, 0);

		}

		/* apply the freeze pane */
		SheetFreezePaneHandler.applyFreezePane(configCriteria);

		/* apply the elements as group */
		SheetGroupElementHandler.applyGroupElements(configCriteria);

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
	 * @throws SheetException
	 * @throws ElementException
	 * @throws ConverterException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	private void unmarshalEngine(final XConfigCriteria configCriteria, final Object object, final Class<?> oC)
			throws SheetException, IllegalAccessException, InstantiationException, ConverterException,
			ElementException {

		/* initialize sheet */
		if (StringUtils.isBlank(configCriteria.getTitleSheet())) {
			throw new SheetException(ExceptionMessage.SHEET_CREATION_SHEET.getMessage());
		}
		configCriteria.setSheet(configCriteria.getWorkbook().getSheet(configCriteria.getTitleSheet()));

		/* initialize index row & cell */
		int idxRow = configCriteria.getStartRow();
		int idxCell = configCriteria.getStartCell();

		if (PropagationType.PROPAGATION_HORIZONTAL.equals(configCriteria.getPropagation())) {
			unmarshalAsPropagationHorizontal(configCriteria, object, oC, idxRow, idxCell);
		} else {
			unmarshalAsPropagationVertical(configCriteria, object, oC, idxRow, idxCell);
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
	 * @throws ElementException
	 * @throws ConfigurationException
	 * @throws SheetException
	 * @throws CustomizedRulesException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws InstantiationException
	 * @throws ConverterException
	 * @throws NoSuchMethodException
	 */
	private void marshalCollectionEngine(final XConfigCriteria configCriteria, final Collection<?> listObject, final boolean insideCollection)
			throws ElementException, ConfigurationException, SheetException, CustomizedRulesException,
			IllegalAccessException, InvocationTargetException, InstantiationException, ConverterException,
			NoSuchMethodException {
		int idxRow;
		int idxCell = 0;

		if (listObject == null || listObject.isEmpty()) {
			throw new ElementException(ExceptionMessage.ELEMENT_NULL_OBJECT.getMessage());
		}
		Object objectRT;
		try {
			//TOVERIFY 
			objectRT = listObject.iterator().next();
			//objectRT = listObject.getClass().stream().findFirst().get();
		} catch (Exception e) {
			throw new ElementException(ExceptionMessage.ELEMENT_NULL_OBJECT.getMessage(), e);
		}

		/* initialize the runtime class of the object */
		Class<?> oC = initializeRuntimeClass(objectRT);

		/* initialize configuration data */
	//	initializeConfigurationData(configCriteria, oC, insideCollection);

		// initialize Workbook
		configCriteria.setWorkbook(initializeWorkbook(configCriteria.getExtension()));

		// initialize style cell via annotation
		initializeCellStyleViaAnnotation(oC, configCriteria);

		// initialize style cell via default option
		configCriteria.initializeCellDecorator();

	
		marshallCollectionEngineT(configCriteria, listObject, idxCell, oC);
	}

	private void marshallCollectionEngineT(final XConfigCriteria configCriteria, final Collection<?> listObject,
			int idxCell, Class<?> oC) throws SheetException, ConfigurationException, ElementException,
					CustomizedRulesException, IllegalAccessException, InvocationTargetException, InstantiationException,
					ConverterException, NoSuchMethodException {
		
		
		
		int idxRow;
		@SuppressWarnings("rawtypes")
		Iterator iterator = listObject.iterator();
		while (iterator.hasNext()) {
			Object object = iterator.next();

			/* initialize configuration data */
			initializeConfigurationData(configCriteria, object.getClass(), false);
			
			// initialize rows according the PropagationType
			if (PropagationType.PROPAGATION_HORIZONTAL.equals(configCriteria.getPropagation())) {
				idxCell = configCriteria.getStartCell();
				idxRow = preparePropagationHorizontal(configCriteria);

				marshalAsPropagationHorizontal(configCriteria, object, object.getClass(), idxRow, idxCell, 0);

			} else {
				//parentsheet para fazer
				idxCell = preparePropagationVertical(configCriteria, idxCell);
				idxRow = configCriteria.getStartRow();

				marshalAsPropagationVertical(configCriteria, object, oC, idxRow, idxCell, 0);
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
	 * @throws ConverterException
	 * @throws CustomizedRulesException
	 * @throws SheetException
	 * @throws ConfigurationException
	 * @throws ElementException
	 * @throws NoSuchMethodException
	 * @throws InstantiationException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@Override
	public Sheet marshalToSheet(final Object object)
			throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException,
			ElementException, ConfigurationException, SheetException, CustomizedRulesException, ConverterException {
		/* Initialize a basic ConfigCriteria */
		XConfigCriteria configCriteria = new XConfigCriteria();

		/* Generate the workbook based at the object passed as parameter */
		marshalEngine(configCriteria, object);

		/* Return the Sheet generated */
		return configCriteria.getWorkbook().getSheetAt(0);
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
	 * @throws ConverterException
	 * @throws CustomizedRulesException
	 * @throws SheetException
	 * @throws ConfigurationException
	 * @throws ElementException
	 * @throws NoSuchMethodException
	 * @throws InstantiationException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@Override
	public Sheet marshalToSheet(final XConfigCriteria configCriteria, final Object object)
			throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException,
			ElementException, ConfigurationException, SheetException, CustomizedRulesException, ConverterException {
		/* Generate the workbook based at the object passed as parameter */
		marshalEngine(configCriteria, object);

		/* Return the Sheet generated */
		return configCriteria.getWorkbook().getSheetAt(0);
	}

	/**
	 * Generate the workbook based at the object passed as parameter and return
	 * the workbook generated.
	 * 
	 * @param object
	 *            the object to apply at the workbook.
	 * @return the {@link Workbook} generated
	 * @throws ConverterException
	 * @throws CustomizedRulesException
	 * @throws SheetException
	 * @throws ConfigurationException
	 * @throws ElementException
	 * @throws NoSuchMethodException
	 * @throws InstantiationException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@Override
	public Workbook marshalToWorkbook(final Object object)
			throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException,
			ElementException, ConfigurationException, SheetException, CustomizedRulesException, ConverterException {
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
	 * @throws ConverterException
	 * @throws CustomizedRulesException
	 * @throws SheetException
	 * @throws ConfigurationException
	 * @throws ElementException
	 * @throws NoSuchMethodException
	 * @throws InstantiationException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@Override
	public Workbook marshalToWorkbook(final XConfigCriteria configCriteria, final Object object)
			throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException,
			ElementException, ConfigurationException, SheetException, CustomizedRulesException, ConverterException {
		/* Generate the workbook from the object passed as parameter */
		marshalEngine(configCriteria, object);

		/* Return the Workbook generated */
		return configCriteria.getWorkbook();
	}

	/**
	 * Generate the workbook based at the object passed as parameter and return the
	 * respective {@link FileOutputStream}.
	 * 
	 * @param object
	 *            the object to apply at the workbook.
	 * @return the {@link Workbook} generated
	 * @throws ConverterException
	 * @throws CustomizedRulesException
	 * @throws SheetException
	 * @throws ConfigurationException
	 * @throws ElementException
	 * @throws NoSuchMethodException
	 * @throws InstantiationException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws IOException
	 */
	@Override
	public byte[] marshalToByte(final Object object) throws IllegalAccessException, InvocationTargetException,
			InstantiationException, NoSuchMethodException, ElementException, ConfigurationException, SheetException,
			CustomizedRulesException, ConverterException, IOException {
		/* Initialize a basic ConfigCriteria */
		XConfigCriteria configCriteria = new XConfigCriteria();

		/* Generate the workbook from the object passed as parameter */
		marshalEngine(configCriteria, object);

		/* Generate the byte array to return */
		return workbookToByteAray(configCriteria.getWorkbook());
	}

	/**
	 * Generate the workbook based at the {@link XConfigCriteria} and the object passed as parameter and return the
	 * respective {@link FileOutputStream}.
	 * 
	 * @param object
	 *            the object to apply at the workbook.
	 * @return the {@link Workbook} generated
	 * @throws ConverterException
	 * @throws CustomizedRulesException
	 * @throws SheetException
	 * @throws ConfigurationException
	 * @throws ElementException
	 * @throws NoSuchMethodException
	 * @throws InstantiationException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws IOException
	 */
	@Override
	public byte[] marshalToByte(final XConfigCriteria configCriteria, final Object object) throws IllegalAccessException, InvocationTargetException,
			InstantiationException, NoSuchMethodException, ElementException, ConfigurationException, SheetException,
			CustomizedRulesException, ConverterException, IOException {
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
	 * @throws IOException
	 * @throws ConverterException
	 * @throws CustomizedRulesException
	 * @throws SheetException
	 * @throws ConfigurationException
	 * @throws ElementException
	 * @throws NoSuchMethodException
	 * @throws InstantiationException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@Override
	public void marshalAndSave(final Object object, final String pathFile) throws IllegalAccessException,
			InvocationTargetException, InstantiationException, NoSuchMethodException, ElementException,
			ConfigurationException, SheetException, CustomizedRulesException, ConverterException, IOException {
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
	 * @throws ConverterException
	 * @throws CustomizedRulesException
	 * @throws SheetException
	 * @throws ConfigurationException
	 * @throws ElementException
	 * @throws NoSuchMethodException
	 * @throws InstantiationException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws IOException
	 */
	@Override
	public void marshalAndSave(final XConfigCriteria configCriteria, final Object object, final String pathFile)
			throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException,
			ElementException, ConfigurationException, SheetException, CustomizedRulesException, ConverterException,
			IOException {
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
	 * Generate the sheet based at the collection of objects passed as parameter and return the
	 * sheet generated.
	 * 
	 * @param listObject
	 *            the collection to apply at the workbook.
	 * @return the {@link Sheet} generated
	 * @throws ConverterException
	 * @throws CustomizedRulesException
	 * @throws SheetException
	 * @throws ConfigurationException
	 * @throws ElementException
	 * @throws NoSuchMethodException
	 * @throws InstantiationException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@Override
	public Sheet marshalCollectionToSheet(final Collection<?> listObject)
			throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException,
			ElementException, ConfigurationException, SheetException, CustomizedRulesException, ConverterException {
		/* Initialize a basic ConfigCriteria */
		XConfigCriteria configCriteria = new XConfigCriteria();

		/* Generate the workbook based at the list of objects passed as parameter */
		marshalCollectionEngine(configCriteria, listObject,false);

		/* Return the Sheet generated */
		return configCriteria.getWorkbook().getSheetAt(0);
	}

	/**
	 * Generate the sheet based at the {@link XConfigCriteria} and the collection of objects
	 * passed as parameters and return the sheet generated.
	 * 
	 * @param configCriteria
	 *            the {@link XConfigCriteria} to use
	 * @param listObject
	 *            the collection to apply at the workbook.
	 * @return the {@link Sheet} generated
	 * @throws ConverterException
	 * @throws CustomizedRulesException
	 * @throws SheetException
	 * @throws ConfigurationException
	 * @throws ElementException
	 * @throws NoSuchMethodException
	 * @throws InstantiationException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@Override
	public Sheet marshalCollectionToSheet(final XConfigCriteria configCriteria, final Collection<?> listObject)
			throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException,
			ElementException, ConfigurationException, SheetException, CustomizedRulesException, ConverterException {
		/* Generate the workbook based at the list of objects passed as parameter */
		marshalCollectionEngine(configCriteria, listObject,false);

		/* Return the Sheet generated */
		return configCriteria.getWorkbook().getSheetAt(0);
	}

	/**
	 * Generate the workbook based at the collection of objects passed as parameter and return
	 * the workbook generated.
	 * 
	 * @param listObject
	 *            the collection to apply at the workbook.
	 * @return the {@link Workbook} generated
	 * @throws ConverterException
	 * @throws CustomizedRulesException
	 * @throws SheetException
	 * @throws ConfigurationException
	 * @throws ElementException
	 * @throws NoSuchMethodException
	 * @throws InstantiationException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@Override
	public Workbook marshalCollectionToWorkbook(final Collection<?> listObject)
			throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException,
			ElementException, ConfigurationException, SheetException, CustomizedRulesException, ConverterException {
		/* Initialize a basic ConfigCriteria */
		XConfigCriteria configCriteria = new XConfigCriteria();

		/* Generate the workbook based at the object passed as parameter */
		marshalCollectionEngine(configCriteria, listObject,false);

		/* Return the Workbook generated */
		return configCriteria.getWorkbook();
	}

	/**
	 * Generate the workbook based at the {@link XConfigCriteria} and the collection of objects
	 * passed as parameters and return the workbook generated.
	 * 
	 * @param configCriteria
	 *            the {@link XConfigCriteria} to use
	 * @param listObject
	 *            the collection to apply at the workbook.
	 * @return the {@link Workbook} generated
	 * @throws ConverterException
	 * @throws CustomizedRulesException
	 * @throws SheetException
	 * @throws ConfigurationException
	 * @throws ElementException
	 * @throws NoSuchMethodException
	 * @throws InstantiationException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@Override
	public Workbook marshalCollectionToWorkbook(final XConfigCriteria configCriteria, final Collection<?> listObject)
			throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException,
			ElementException, ConfigurationException, SheetException, CustomizedRulesException, ConverterException {
		/* Generate the workbook from the object passed as parameter */
		marshalCollectionEngine(configCriteria, listObject,false);

		/* Return the Workbook generated */
		return configCriteria.getWorkbook();
	}

	/**
	 * Generate the file from the collection of objects.
	 * 
	 * @param listObject
	 *            the collection to apply at the file.
	 * @param pathFile
	 *            the path where is found the file to read and pass the
	 *            information to the object
	 * @throws Exception
	 */
	@Override
	public void marshalAsCollectionAndSave(final Collection<?> listObject, final String pathFile) throws Exception {

		/* Initialize a basic ConfigCriteria */
		XConfigCriteria configCriteria = new XConfigCriteria();

		marshalAsCollectionAndSave(configCriteria, listObject, pathFile);
	}

	/**
	 * Generate the CSV file, based at {@link XConfigCriteria}, from the
	 * collection of objects.
	 * 
	 * @param configCriteria
	 *            the {@link XConfigCriteria} to use
	 * @param listObject
	 *            the collection to apply at the file.
	 * @param pathFile
	 *            the path where is found the file to read and pass the
	 *            information to the object
	 * @throws Exception
	 */
	@Override
	public void marshalAsCollectionAndSave(final XConfigCriteria configCriteria, final Collection<?> listObject,
			final String pathFile) throws Exception {
		/* Generate the workbook from the object passed as parameter */
		marshalCollectionEngine(configCriteria, listObject,false);

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
	 * Generate the workbook from the collection of objects passed as parameter and return the
	 * respective {@link FileOutputStream}.
	 * 
	 * @param listObject
	 *            the collection to apply at the workbook.
	 * @return the {@link Workbook} generated
	 * @throws ConverterException
	 * @throws CustomizedRulesException
	 * @throws SheetException
	 * @throws ConfigurationException
	 * @throws ElementException
	 * @throws NoSuchMethodException
	 * @throws InstantiationException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws IOException
	 */
	@Override
	public byte[] marshalCollectionToByte(final Collection<?> listObject) throws IllegalAccessException, InvocationTargetException,
			InstantiationException, NoSuchMethodException, ElementException, ConfigurationException, SheetException,
			CustomizedRulesException, ConverterException, IOException {
		/* Initialize a basic ConfigCriteria */
		XConfigCriteria configCriteria = new XConfigCriteria();

		/* Generate the workbook from the object passed as parameter */
		marshalCollectionEngine(configCriteria, listObject,false);

		/* Generate the byte array to return */
		return workbookToByteAray(configCriteria.getWorkbook());
	}



	/**
	 * Generate the workbook from the collection of objects passed as parameter and return the
	 * respective {@link FileOutputStream}.
	 * 
	 * @param listObject
	 *            the collection to apply at the workbook.
	 * @return the {@link Workbook} generated
	 * @throws ConverterException
	 * @throws CustomizedRulesException
	 * @throws SheetException
	 * @throws ConfigurationException
	 * @throws ElementException
	 * @throws NoSuchMethodException
	 * @throws InstantiationException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws IOException
	 */
	@Override
	public byte[] marshalCollectionToByte(final XConfigCriteria configCriteria, final Collection<?> listObject) throws IllegalAccessException, InvocationTargetException,
			InstantiationException, NoSuchMethodException, ElementException, ConfigurationException, SheetException,
			CustomizedRulesException, ConverterException, IOException {
		/* Generate the workbook from the object passed as parameter */
		marshalCollectionEngine(configCriteria, listObject,false);

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
	 * @throws ElementException
	 * @throws ConfigurationException
	 * @throws ConverterException
	 * @throws SheetException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@Override
	public void unmarshalFromWorkbook(final Object object, final Workbook workbook) throws ElementException,
			ConfigurationException, IllegalAccessException, InstantiationException, SheetException, ConverterException {
		/* initialize the runtime class of the object */
		Class<?> oC = initializeRuntimeClass(object);

		/* initialize configuration data */
		XConfigCriteria configCriteria = new XConfigCriteria();
		initializeConfigurationData(configCriteria, oC, false);

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
	 * @throws ElementException
	 * @throws ConfigurationException
	 * @throws IOException
	 * @throws ConverterException
	 * @throws SheetException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@Override
	public void unmarshalFromPath(final Object object, final String pathFile)
			throws ElementException, ConfigurationException, IOException, IllegalAccessException,
			InstantiationException, SheetException, ConverterException {
		/* initialize the runtime class of the object */
		Class<?> oC = initializeRuntimeClass(object);

		/* initialize configuration data */
		XConfigCriteria configCriteria = new XConfigCriteria();
		initializeConfigurationData(configCriteria, oC, false);

		/*
		 * check if the path terminate with the file separator, otherwise will
		 * be added to avoid any problem
		 */
		String internalPathFile = pathFile;
		if (!pathFile.endsWith(File.separator)) {
			internalPathFile = pathFile.concat(File.separator);
		}

		FileInputStream input = new FileInputStream(internalPathFile + configCriteria.getCompleteFileName());

		/* set workbook */
		configCriteria.setWorkbook(initializeWorkbook(input, configCriteria.getExtension()));

		/* Extract from the workbook to the object passed as parameter */
		unmarshalEngine(configCriteria, object, oC);
	}

	/**
	 * Generate the object from the byte array passed as parameter.
	 * 
	 * @param object
	 *            the object to fill up.
	 * @param inputByte
	 *            the byte array to read and pass the information to the object
	 * @throws ElementException
	 * @throws ConfigurationException
	 * @throws IOException
	 * @throws ConverterException
	 * @throws SheetException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@Override
	public void unmarshalFromByte(final Object object, final byte[] byteArray)
			throws ElementException, ConfigurationException, IOException, IllegalAccessException,
			InstantiationException, SheetException, ConverterException {
		/* initialize the runtime class of the object */
		Class<?> oC = initializeRuntimeClass(object);

		/* initialize configuration data */
		XConfigCriteria configCriteria = new XConfigCriteria();
		initializeConfigurationData(configCriteria, oC, false);

		/* set workbook */
		configCriteria.setWorkbook(initializeWorkbook(byteArray, configCriteria.getExtension()));

		/* Extract from the workbook to the object passed as parameter */
		unmarshalEngine(configCriteria, object, oC);
	}

	@Override
	public void marshalAsCollection(final Collection<?> collection) {
		// TODO Auto-generated method stub

	}

	@Override
	public Collection<?> unmarshalToCollection(final XConfigCriteria configCriteria,final Object object, String excelFilePath) throws ConfigurationException, InstantiationException, IllegalAccessException, InvocationTargetException, ConverterException, ElementException, IOException {
		//FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
		
		/* initialize configuration data */
		initializeConfigurationData(configCriteria, object.getClass(),false);

		/*
		 * check if the path terminate with the file separator, otherwise will
		 * be added to avoid any problem
		 */
		String internalPathFile = excelFilePath;
		if (!excelFilePath.endsWith(File.separator)) {
			internalPathFile = excelFilePath.concat(File.separator);
		}

		
		FileInputStream input = new FileInputStream(internalPathFile + configCriteria.getCompleteFileName());

		/* set workbook */
		configCriteria.setWorkbook(initializeWorkbook(input, configCriteria.getExtension()));
        
       
       
        Collection<Object> collection = unmarshalTreatementToCollection(object, configCriteria);
		return collection;
	}
	
	private Collection<Object> unmarshalTreatementToCollection(Object object, XConfigCriteria configCriteria)
		throws  InstantiationException, IllegalAccessException, InvocationTargetException, ConverterException, ElementException, ConfigurationException
			 {
		/* initialize the runtime class of the object */
		Class<?> oC = initializeRuntimeClass(object);
        
        initializeConfigurationData(configCriteria, oC, false);
     	
     	
     	Sheet s = configCriteria.getWorkbook().getSheet(truncateTitle(configCriteria.getTitleSheet()));
     	configCriteria.setSheet(s);
		/* initialize index row & cell */
		int idxRow = configCriteria.getStartRow();
		int idxCell = configCriteria.getStartCell();
		Iterator<Row> iterator = s.iterator();
        Collection<Object> collection = new ArrayList<>();
		if (PropagationType.PROPAGATION_HORIZONTAL.equals(configCriteria.getPropagation())) {
			 while (iterator.hasNext()) {
				Object obj=oC.newInstance();
				unmarshalAsPropagationHorizontal(configCriteria, obj, oC, idxRow, idxCell);
				 if(obj!=null){
					 collection.add((Object) obj);
					 idxRow= idxRow+1;
				 }else{
					 break;
				 }
				 
				 
		     }
			
		} else {
			 while (iterator.hasNext()) {
				 unmarshalAsPropagationVertical(configCriteria, object, oC, idxRow, idxCell);
				 if(object!=null){
					 collection.add((Object) object);
					 idxCell= idxCell+1;
				 }else{
					 break;
				 }
				 
		     }
			
		}
		return collection;
	}

	/* ############################################# */
	/* ################## TO REVIEW ################ */
	/* ############################################# */

	/**
	 * Generate the workbook from the object passed as parameter and return the
	 * respective {@link FileOutputStream}.
	 * 
	 * @param object
	 *            the object to apply at the workbook.
	 * @return the {@link FileOutputStream} generated
	 * @throws ConverterException
	 * @throws CustomizedRulesException
	 * @throws SheetException
	 * @throws ConfigurationException
	 * @throws ElementException
	 * @throws NoSuchMethodException
	 * @throws InstantiationException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws IOException
	 */
	@Override
	public FileOutputStream marshalToFileOutputStream(final Object object) throws IllegalAccessException,
			InvocationTargetException, InstantiationException, NoSuchMethodException, ElementException,
			ConfigurationException, SheetException, CustomizedRulesException, ConverterException, IOException {
		/* Initialize a basic ConfigCriteria */
		XConfigCriteria configCriteria = new XConfigCriteria();

		/* Generate the workbook from the object passed as parameter */
		marshalEngine(configCriteria, object);

		/* Generate the FileOutputStream to return */
		return workbookFileOutputStream(configCriteria.getWorkbook(), configCriteria.getFileName());
	}

	/**
	 * Generate the object from the {@link FileInputStream} passed as parameter.
	 * 
	 * @param object
	 *            the object to apply at the workbook.
	 * @param stream
	 *            the {@link FileInputStream} to read
	 * @return the {@link Object} filled up
	 */
	@Override
	public Object unmarshalFromFileInputStream(final Object object, final FileInputStream stream) throws Exception {
		/* instance object class */
		Class<?> oC = object.getClass();

		/* initialize configuration data */
		XConfigCriteria configCriteria = new XConfigCriteria();
		initializeConfigurationData(configCriteria, oC, false);

		/* set workbook */
		configCriteria.setWorkbook(initializeWorkbook(stream, configCriteria.getExtension()));

		unmarshalEngine(configCriteria, object, oC);

		return object;
	}
	/**
	 * Method to truncate title of the sheet.
	 * The length max is 31 caracters
	 * @param title
	 * @return
	 */
	private String truncateTitle(String title){
		return title!=null && title.length()>31 ? title.substring(0,31) : title;
	}
}