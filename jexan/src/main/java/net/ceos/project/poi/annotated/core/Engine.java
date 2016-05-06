package net.ceos.project.poi.annotated.core;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jaexcel.framework.JAEX.configuration.Configuration;

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
	 * @deprecated
	 */
	@Deprecated
	Configuration configuration;

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
			throw new ElementException(ExceptionMessage.ElementException_NullObject.getMessage(), e);
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
	private void initializeConfigurationData(final XConfigCriteria configCriteria, final Class<?> oC)
			throws ConfigurationException {
		/* Process @XlsConfiguration */
		if (oC.isAnnotationPresent(XlsConfiguration.class)) {
			XlsConfiguration xlsAnnotation = (XlsConfiguration) oC.getAnnotation(XlsConfiguration.class);
			initializeXlsConfiguration(configCriteria, xlsAnnotation);
		} else {
			throw new ConfigurationException(
					ExceptionMessage.ConfigurationException_XlsConfigurationMissing.getMessage());
		}
		/* Process @XlsSheet */
		if (oC.isAnnotationPresent(XlsSheet.class)) {
			XlsSheet xlsAnnotation = (XlsSheet) oC.getAnnotation(XlsSheet.class);
			initializeXlsSheet(configCriteria, xlsAnnotation);
		} else {
			throw new ConfigurationException(ExceptionMessage.ConfigurationException_XlsSheetMissing.getMessage());
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
		configCriteria.setFileName(annotation.nameFile());
		configCriteria.setCompleteFileName(annotation.nameFile() + annotation.extensionFile().getExtension());
		configCriteria.setExtension(annotation.extensionFile());
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
							ExceptionMessage.ConfigurationException_CellStyleDuplicated.getMessage());
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
	 *            the {@link ExtensionFileType} of workbook
	 * @return the {@link Workbook} created
	 * @throws IOException
	 */
	private Workbook initializeWorkbook(final FileInputStream inputStream, final ExtensionFileType type)
			throws IOException {
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
			throw new SheetException(ExceptionMessage.SheetException_CreationSheet.getMessage(), e);
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
			throw new ConfigurationException(ExceptionMessage.ConfigurationException_Conflict.getMessage());

		} else if (!isPH && annotation.startY() == annotation.endY()) {
			throw new ConfigurationException(ExceptionMessage.ConfigurationException_Conflict.getMessage());
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

			/* initialize master header cell */
			CellStyleHandler.initializeHeaderCell(configCriteria.getStylesMap(), row, startCell, annotation.title());

			/* merge region of the master header cell */
			configCriteria.getSheet().addMergedRegion(new CellRangeAddress(startRow, endRow, startCell, endCell));

		}
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
				throw new ElementException(ExceptionMessage.ElementException_ComplexObject.getMessage());
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
	 * @throws Exception
	 */
	private int initializeCellByFieldHorizontal(final XConfigCriteria configCriteria, final Object o, final int idxR,
			final int idxC, final int cL)
					throws IllegalAccessException, InstantiationException, InvocationTargetException,
					ConfigurationException, ElementException, CustomizedRulesException, ConverterException {

		int counter = 0;

		/* validate cascade level */
		if (cL <= configCriteria.getCascadeLevel().getCode()) {
			/* make the field accessible to recover the value */
			configCriteria.getField().setAccessible(true);

			Class<?> fT = configCriteria.getField().getType();

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
		boolean isUpdated = false;
		/* initialize cell */
		Cell cell;

		/*
		 * check if the cell to be applied the element is empty otherwise one
		 * exception will be launched
		 */
		if (configCriteria.getRow().getCell(idxC) != null) {
			throw new ElementException(ExceptionMessage.ElementException_OverwriteCell.getMessage());
		}

		if (CellHandler.OBJECT_DATE.equals(fT.getName())) {
			cell = configCriteria.getRow().createCell(idxC);
			isUpdated = CellHandler.dateWriter(configCriteria, o, cell);
			
		} else if (CellHandler.OBJECT_STRING.equals(fT.getName())) {
			cell = configCriteria.getRow().createCell(idxC);
			isUpdated = CellHandler.stringWriter(configCriteria, o, cell);
			
		} else if (CellHandler.OBJECT_SHORT.equals(fT.getName())
				|| CellHandler.PRIMITIVE_SHORT.equals(fT.getName())) {
			cell = configCriteria.getRow().createCell(idxC);
			isUpdated = CellHandler.shortWriter(configCriteria, o, cell);
			
		} else if (CellHandler.OBJECT_INTEGER.equals(fT.getName())
				|| CellHandler.PRIMITIVE_INTEGER.equals(fT.getName())) {
			cell = configCriteria.getRow().createCell(idxC);
			isUpdated = CellHandler.integerWriter(configCriteria, o, cell);
			
		} else if (CellHandler.OBJECT_LONG.equals(fT.getName()) || CellHandler.PRIMITIVE_LONG.equals(fT.getName())) {
			cell = configCriteria.getRow().createCell(idxC);
			isUpdated = CellHandler.longWriter(configCriteria, o, cell);
			
		} else if (CellHandler.OBJECT_DOUBLE.equals(fT.getName())
				|| CellHandler.PRIMITIVE_DOUBLE.equals(fT.getName())) {
			cell = configCriteria.getRow().createCell(idxC);
			isUpdated = CellHandler.doubleWriter(configCriteria, o, cell);
			
		} else if (CellHandler.OBJECT_BIGDECIMAL.equals(fT.getName())) {
			cell = configCriteria.getRow().createCell(idxC);
			isUpdated = CellHandler.bigDecimalWriter(configCriteria, o, cell);
			
		} else if (CellHandler.OBJECT_FLOAT.equals(fT.getName()) || CellHandler.PRIMITIVE_FLOAT.equals(fT.getName())) {
			cell = configCriteria.getRow().createCell(idxC);
			isUpdated = CellHandler.floatWriter(configCriteria, o, cell);
			
		} else if (CellHandler.OBJECT_BOOLEAN.equals(fT.getName())
				|| CellHandler.PRIMITIVE_BOOLEAN.equals(fT.getName())) {
			cell = configCriteria.getRow().createCell(idxC);
			isUpdated = CellHandler.booleanWriter(configCriteria, o, cell);
			
		} else if (fT.isEnum()) {
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
		boolean isUpdated = false;

		f.setAccessible(true);

		if (CellHandler.OBJECT_DATE.equals(fT.getName())) {
			CellHandler.dateReader(o, f, c, xlsAnnotation);
			isUpdated = true;
			
		} else if (CellHandler.OBJECT_STRING.equals(fT.getName())) {
			CellHandler.stringReader(o, f, c);
			isUpdated = true;
			
		} else if (CellHandler.OBJECT_SHORT.equals(fT.getName())
				|| CellHandler.PRIMITIVE_SHORT.equals(fT.getName())) {
			CellHandler.shortReader(o, f, c);
			isUpdated = true;
			
		} else if (CellHandler.OBJECT_INTEGER.equals(fT.getName())
				|| CellHandler.PRIMITIVE_INTEGER.equals(fT.getName())) {
			CellHandler.integerReader(o, f, c);
			isUpdated = true;
			
		} else if (CellHandler.OBJECT_LONG.equals(fT.getName()) || CellHandler.PRIMITIVE_LONG.equals(fT.getName())) {
			CellHandler.longReader(o, f, c);
			isUpdated = true;
			
		} else if (CellHandler.OBJECT_DOUBLE.equals(fT.getName())
				|| CellHandler.PRIMITIVE_DOUBLE.equals(fT.getName())) {
			CellHandler.doubleReader(o, f, c, xlsAnnotation);
			isUpdated = true;
			
		} else if (CellHandler.OBJECT_BIGDECIMAL.equals(fT.getName())) {
			CellHandler.bigDecimalReader(o, f, c, xlsAnnotation);
			isUpdated = true;
			
		} else if (CellHandler.OBJECT_FLOAT.equals(fT.getName()) || CellHandler.PRIMITIVE_FLOAT.equals(fT.getName())) {
			CellHandler.floatReader(o, f, c);
			isUpdated = true;
			
		} else if (CellHandler.OBJECT_BOOLEAN.equals(fT.getName())
				|| CellHandler.PRIMITIVE_BOOLEAN.equals(fT.getName())) {
			CellHandler.booleanReader(o, f, c, xlsAnnotation);
			isUpdated = true;
			
		} else if (fT.isEnum()) {
			CellHandler.enumReader(o, fT, f, c);
			isUpdated = true;
		}

		return isUpdated;
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
	 * 
	 */
	private int marshalAsPropagationHorizontal(final XConfigCriteria configCriteria, final Object o, final Class<?> oC,
			final int idxR, final int idxC, final int cL)
					throws ConfigurationException, ElementException, CustomizedRulesException, IllegalAccessException,
					InvocationTargetException, InstantiationException, ConverterException {
		/* counter related to the number of fields (if new object) */
		int counter = -1;
		int indexCell = idxC;

		/* get declared fields */
		List<Field> fL = Arrays.asList(oC.getDeclaredFields());
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
					throw new ElementException(ExceptionMessage.ElementException_InvalidPosition.getMessage());
				}

				/* validate the propagation type & formulas */
				if (xlsAnnotation.isFormula() && StringUtils.isNotBlank(xlsAnnotation.formula())
						&& xlsAnnotation.formula().contains("idy")) {
					throw new ElementException(ExceptionMessage.ConfigurationException_Conflict.getMessage());
				}

				/* update annotation at ConfigCriteria */
				configCriteria.setElement(xlsAnnotation);

				/* apply customized rules defined at the object */
				if (StringUtils.isNotBlank(xlsAnnotation.customizedRules())) {
					try {
						CellHandler.applyCustomizedRules(o, xlsAnnotation.customizedRules());
					} catch (NoSuchMethodException e) {
						throw new CustomizedRulesException(
								ExceptionMessage.CustomizedRulesException_NoSuchMethod.getMessage(), e);
					}
				}

				/*
				 * increment of the counter related to the number of fields (if
				 * new object)
				 */
				counter++;
				if (configCriteria.getRowHeader() != null) {
					/* header treatment */
					CellStyleHandler.initializeHeaderCell(configCriteria.getStylesMap(), configCriteria.getRowHeader(),
							indexCell + xlsAnnotation.position(), xlsAnnotation.title());
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
					throw new ElementException(ExceptionMessage.ElementException_InvalidPosition.getMessage());
				}

				/* validate the propagation type & formulas */
				if (xlsAnnotation.isFormula() && StringUtils.isNotBlank(xlsAnnotation.formula())
						&& xlsAnnotation.formula().contains("idx")) {
					throw new ElementException(ExceptionMessage.ConfigurationException_Conflict.getMessage());
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
					int tmpIdxCell = indexCell - 1;
					/* initialize row */
					row = initializeRow(configCriteria.getSheet(), indexRow + xlsAnnotation.position());

					/* apply merge region */
					applyMergeRegion(configCriteria, row, indexRow, tmpIdxCell, false);

					/* header treatment */
					CellStyleHandler.initializeHeaderCell(configCriteria.getStylesMap(), row, indexCell,
							xlsAnnotation.title());

				} /*
					 * else { row = configCriteria.getSheet().getRow(indexRow +
					 * xlsAnnotation.position()); }
					 */
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
				throw new ElementException(ExceptionMessage.ElementException_InvalidPosition.getMessage());
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
					throw new ElementException(ExceptionMessage.ElementException_OverwriteCell.getMessage());
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
					throw new ElementException(ExceptionMessage.ElementException_InvalidPosition.getMessage());
				}
				/*
				 * increment of the counter related to the number of fields (if
				 * new object)
				 */
				counter++;

				/* content row */
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
					throw new ElementException(ExceptionMessage.ElementException_InvalidPosition.getMessage());
				}

				/* content row */
				Row contentRow = configCriteria.getSheet().getRow(xlsFreeAnnotation.row());
				Cell contentCell = contentRow.getCell(xlsFreeAnnotation.cell() - 1);

				// initialize Element
				XlsElement xlsAnnotation = XlsElementFactory.build(xlsFreeAnnotation);

				boolean isAppliedToBaseObject = toObject(o, fT, f, contentCell, xlsAnnotation);

				if (!isAppliedToBaseObject && !fT.isPrimitive()) {
					throw new ElementException(ExceptionMessage.ElementException_ComplexObject.getMessage());
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
					throw new ElementException(ExceptionMessage.ElementException_InvalidPosition.getMessage());
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
					throw new ElementException(ExceptionMessage.ElementException_InvalidPosition.getMessage());
				}

				/* content row */
				Row contentRow = configCriteria.getSheet().getRow(xlsFreeAnnotation.row());
				Cell contentCell = contentRow.getCell(xlsFreeAnnotation.cell() - 1);

				// initialize Element
				XlsElement xlsAnnotation = XlsElementFactory.build(xlsFreeAnnotation);

				boolean isAppliedToBaseObject = toObject(o, fT, f, contentCell, xlsAnnotation);

				if (!isAppliedToBaseObject && !fT.isPrimitive()) {
					throw new ElementException(ExceptionMessage.ElementException_ComplexObject.getMessage());
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
			throw new ElementException(ExceptionMessage.ElementException_NullObject.getMessage());
		}

		/* initialize the runtime class of the object */
		Class<?> oC = initializeRuntimeClass(object);

		/* initialize configuration data */
		initializeConfigurationData(configCriteria, oC);

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
			throw new SheetException(ExceptionMessage.SheetException_CreationSheet.getMessage());
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
	 * Generate the workbook from the object passed as parameter and return the
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
	 * Generate the workbook from the collection of objects.
	 * 
	 * @param collection
	 *            the collection of objects to apply at the workbook.
	 * @param filename
	 *            the file name
	 * @param extensionFileType
	 *            the file extension
	 * @throws Exception
	 */
	@Override
	public void marshalAsCollection(final Collection<?> collection, final String filename,
			final ExtensionFileType extensionFileType) throws Exception {

		// FIXME apply the ConfigCriteria, then remove cofiguration
		// Temos que iniciar o config data neste ponto porque
		// como estamos na escritura de uma coleccao
		// temos que ter o nome e a extensao antes de iniciar o excel
		configuration = new Configuration();
		configuration.setExtensionFile(extensionFileType);
		configuration.setNameFile(filename);
		Configuration config = configuration;

		XConfigCriteria configCriteria = new XConfigCriteria();
		configCriteria.setPropagation(config.getPropagationType());
		configCriteria.setExtension(config.getExtensionFile());

		// initialize Workbook
		configCriteria.setWorkbook(initializeWorkbook(config.getExtensionFile()));

		if (collection == null) {
			throw new ElementException(ExceptionMessage.ElementException_NullObject.getMessage());
		}

		try {
			/* initialize the runtime class of the object */
			Class<?> oC = initializeRuntimeClass(collection.iterator().next());
			initializeCellStyleViaAnnotation(oC, configCriteria);
		} catch (Exception e) {
			throw new ElementException(ExceptionMessage.ElementException_EmptyObject.getMessage(), e);
		}

		// initialize style cell via default option
		configCriteria.initializeCellDecorator();

		int idxRow;
		int idxCell = 0;

		@SuppressWarnings("rawtypes")
		Iterator iterator = collection.iterator();

		while (iterator.hasNext()) {
			Object object = iterator.next();
			// We get the class of the object
			Class<?> objectClass = object.getClass();

			// Process @XlsSheet
			if (objectClass.isAnnotationPresent(XlsSheet.class)) {
				XlsSheet xlsAnnotation = (XlsSheet) objectClass.getAnnotation(XlsSheet.class);
				config = initializeSheetConfiguration(xlsAnnotation);
				configCriteria.setCascadeLevel(config.getCascadeLevel());
				// initializeXlsSheet(configCriteria, xlsAnnotation);
			}

			// initialize rows according the PropagationType
			if (PropagationType.PROPAGATION_HORIZONTAL.equals(config.getPropagationType())) {
				idxCell = config.getStartCell();
				if (configCriteria.getWorkbook().getNumberOfSheets() == 0
						|| configCriteria.getWorkbook().getSheet(config.getTitleSheet()) == null) {
					configCriteria.setSheet(initializeSheet(configCriteria.getWorkbook(), config.getTitleSheet()));
					idxRow = config.getStartRow();
					configCriteria.setRowHeader(initializeRow(configCriteria.getSheet(), idxRow++));
					configCriteria.setRow(initializeRow(configCriteria.getSheet(), idxRow++));
				} else {
					idxRow = configCriteria.getSheet().getLastRowNum() + 1;
					configCriteria.setRowHeader(null);
					configCriteria.setRow(initializeRow(configCriteria.getSheet(), idxRow++));

				}
				marshalAsPropagationHorizontal(configCriteria, object, objectClass, idxRow, idxCell, 0);
			} else {
				idxRow = config.getStartRow();
				if (configCriteria.getWorkbook().getNumberOfSheets() == 0
						|| configCriteria.getWorkbook().getSheet(config.getTitleSheet()) == null) {
					configCriteria.setSheet(initializeSheet(configCriteria.getWorkbook(), config.getTitleSheet()));
					idxCell = config.getStartCell();
				} else {
					idxCell += 1;
				}
				marshalAsPropagationVertical(configCriteria, object, objectClass, idxRow, idxCell, 0);
			}

		}
		/* apply the column resize */
		configCriteria.applyColumnWidthToSheet();

		// FIXME manage return value
		workbookFileOutputStream(configCriteria.getWorkbook(),
				"C:\\projects\\tests\\generated\\" + config.getNameFile() + config.getExtensionFile().getExtension());
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
		initializeConfigurationData(configCriteria, oC);

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
		initializeConfigurationData(configCriteria, oC);

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
		initializeConfigurationData(configCriteria, oC);

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
	public Collection<?> unmarshalToCollection(final Object object) {
		// TODO Auto-generated method stub
		return Collections.emptyList();
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
		initializeConfigurationData(configCriteria, oC);

		/* set workbook */
		configCriteria.setWorkbook(initializeWorkbook(stream, configCriteria.getExtension()));

		unmarshalEngine(configCriteria, object, oC);

		return object;
	}

	/**
	 * Initialize the configuration to apply at the Excel.
	 * 
	 * @deprecated s
	 * @param oC
	 *            the {@link Class<?>}
	 * @return
	 */
	@Deprecated
	private Configuration initializeConfigurationData(Class<?> oC) {
		Configuration config = null;

		/* Process @XlsConfiguration */
		if (oC.isAnnotationPresent(XlsConfiguration.class)) {
			XlsConfiguration xlsAnnotation = (XlsConfiguration) oC.getAnnotation(XlsConfiguration.class);
			config = initializeConfiguration(xlsAnnotation);
		}

		/* Process @XlsSheet */
		if (oC.isAnnotationPresent(XlsSheet.class)) {
			XlsSheet xlsAnnotation = (XlsSheet) oC.getAnnotation(XlsSheet.class);
			config = initializeSheetConfiguration(xlsAnnotation);
		}
		return config;
	}

	/**
	 * Add the main xls configuration.
	 * 
	 * @deprecated s
	 * @param config
	 *            the {@link XlsConfiguration}
	 * @return
	 */
	@Deprecated
	private Configuration initializeConfiguration(XlsConfiguration config) {
		if (configuration == null) {
			configuration = new Configuration();
		}
		configuration.setName(config.nameFile());
		configuration.setNameFile(config.nameFile() + config.extensionFile().getExtension());
		configuration.setExtensionFile(config.extensionFile());
		return configuration;
	}

	/**
	 * Add the sheet configuration.
	 * 
	 * @deprecated a
	 * @param config
	 *            the {@link XlsSheet}
	 * @return
	 */
	@Deprecated
	private Configuration initializeSheetConfiguration(XlsSheet config) {
		if (configuration == null) {
			configuration = new Configuration();
		}
		configuration.setTitleSheet(config.title());
		configuration.setPropagationType(config.propagation());
		configuration.setCascadeLevel(config.cascadeLevel());
		configuration.setStartRow(config.startRow());
		configuration.setStartCell(config.startCell());
		return configuration;
	}

}