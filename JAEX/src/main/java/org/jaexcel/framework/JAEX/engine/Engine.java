package org.jaexcel.framework.JAEX.engine;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jaexcel.framework.JAEX.annotation.XlsConfiguration;
import org.jaexcel.framework.JAEX.annotation.XlsDecorator;
import org.jaexcel.framework.JAEX.annotation.XlsDecorators;
import org.jaexcel.framework.JAEX.annotation.XlsElement;
import org.jaexcel.framework.JAEX.annotation.XlsNestedHeader;
import org.jaexcel.framework.JAEX.annotation.XlsSheet;
import org.jaexcel.framework.JAEX.configuration.Configuration;
import org.jaexcel.framework.JAEX.definition.CascadeType;
import org.jaexcel.framework.JAEX.definition.ExtensionFileType;
import org.jaexcel.framework.JAEX.definition.ExceptionMessage;
import org.jaexcel.framework.JAEX.definition.PropagationType;
import org.jaexcel.framework.JAEX.exception.ConfigurationException;
import org.jaexcel.framework.JAEX.exception.ConverterException;
import org.jaexcel.framework.JAEX.exception.ElementException;

public class Engine implements IEngine {

	// Workbook wb;
	Configuration configuration;
	CellDecorator headerDecorator;
	Map<String, CellStyle> stylesMap = new HashMap<String, CellStyle>();
	Map<String, CellDecorator> cellDecoratorMap = new HashMap<String, CellDecorator>();

	/**
	 * Initialize default Header Cell Decorator.
	 * 
	 * @param wb
	 *            the {@link Workbook} in use
	 * @return the {@link CellStyle} header decorator
	 */
	private CellStyle initializeHeaderCellDecorator(Workbook wb) {
		CellStyle cs = CellStyleUtils.initializeCellStyle(wb);

		/* add the alignment to the cell */
		CellStyleUtils.applyAlignment(cs, CellStyle.ALIGN_CENTER, CellStyle.VERTICAL_CENTER);

		/* add the border to the cell */
		CellStyleUtils.applyBorderDefault(cs);

		/* add the background to the cell */
		cs.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		cs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		/* add the wrap mode to the cell */
		cs.setWrapText(true);

		/* add the font style to the cell */
		CellStyleUtils.applyFontDefault(wb, cs);

		return cs;
	}

	/**
	 * Initialize default Numeric Cell Decorator.
	 * 
	 * @param wb
	 *            the {@link Workbook} in use
	 * @return the {@link CellStyle} numeric decorator
	 */
	private CellStyle initializeNumericCellDecorator(Workbook wb) {
		CellStyle cs = CellStyleUtils.initializeCellStyle(wb);

		/* add the alignment to the cell */
		CellStyleUtils.applyAlignment(cs, CellStyle.ALIGN_RIGHT);
		CellStyleUtils.applyFontDefault(wb, cs);

		return cs;
	}

	/**
	 * Initialize default Date Cell Decorator.
	 * 
	 * @param wb
	 *            the {@link Workbook} in use
	 * @return the {@link CellStyle} date decorator
	 */
	private CellStyle initializeDateCellDecorator(Workbook wb) {
		CellStyle cs = CellStyleUtils.initializeCellStyle(wb);

		/* add the alignment to the cell */
		CellStyleUtils.applyAlignment(cs, CellStyle.ALIGN_CENTER);
		CellStyleUtils.applyFontDefault(wb, cs);

		return cs;
	}

	/**
	 * Initialize default Boolean Cell Decorator.
	 * 
	 * @param wb
	 *            the {@link Workbook} in use
	 * @return the {@link CellStyle} boolean decorator
	 */
	private CellStyle initializeBooleanCellDecorator(Workbook wb) {
		CellStyle cs = CellStyleUtils.initializeCellStyle(wb);

		/* add the alignment to the cell */
		CellStyleUtils.applyAlignment(cs, CellStyle.ALIGN_CENTER);
		CellStyleUtils.applyFontDefault(wb, cs);

		return cs;
	}

	/**
	 * Initialize default Generic Cell Decorator.
	 * 
	 * @param wb
	 *            the {@link Workbook} in use
	 * @return the {@link CellStyle} generic decorator
	 */
	private CellStyle initializeGenericCellDecorator(Workbook wb) {
		CellStyle cs = CellStyleUtils.initializeCellStyle(wb);

		/* add the alignment to the cell */
		CellStyleUtils.applyAlignment(cs, CellStyle.ALIGN_LEFT);
		CellStyleUtils.applyFontDefault(wb, cs);

		return cs;
	}

	/**
	 * Initialize Cell Decorator system.
	 * 
	 * @param wb
	 *            the {@link Workbook} in use
	 * @throws ConfigurationException
	 */
	private void initializeCellDecorator(Workbook wb) throws ConfigurationException {

		if (stylesMap.get(CellStyleUtils.CELL_DECORATOR_HEADER) == null) {
			stylesMap.put(CellStyleUtils.CELL_DECORATOR_HEADER,
					cellDecoratorMap.containsKey(CellStyleUtils.CELL_DECORATOR_HEADER)
							? initializeCellStyleByCellDecorator(wb,
									cellDecoratorMap.get(CellStyleUtils.CELL_DECORATOR_HEADER))
							: initializeHeaderCellDecorator(wb));
			cellDecoratorMap.remove(cellDecoratorMap.containsKey(CellStyleUtils.CELL_DECORATOR_HEADER));
		}
		if (stylesMap.get(CellStyleUtils.CELL_DECORATOR_NUMERIC) == null) {
			stylesMap.put(CellStyleUtils.CELL_DECORATOR_NUMERIC,
					cellDecoratorMap.containsKey(CellStyleUtils.CELL_DECORATOR_NUMERIC)
							? initializeCellStyleByCellDecorator(wb,
									cellDecoratorMap.get(CellStyleUtils.CELL_DECORATOR_NUMERIC))
							: initializeNumericCellDecorator(wb));
			cellDecoratorMap.remove(cellDecoratorMap.containsKey(CellStyleUtils.CELL_DECORATOR_NUMERIC));
		}
		if (stylesMap.get(CellStyleUtils.CELL_DECORATOR_DATE) == null) {
			stylesMap.put(CellStyleUtils.CELL_DECORATOR_DATE,
					cellDecoratorMap.containsKey(CellStyleUtils.CELL_DECORATOR_DATE)
							? initializeCellStyleByCellDecorator(wb,
									cellDecoratorMap.get(CellStyleUtils.CELL_DECORATOR_DATE))
							: initializeDateCellDecorator(wb));
			cellDecoratorMap.remove(cellDecoratorMap.containsKey(CellStyleUtils.CELL_DECORATOR_DATE));
		}
		if (stylesMap.get(CellStyleUtils.CELL_DECORATOR_BOOLEAN) == null) {
			stylesMap.put(CellStyleUtils.CELL_DECORATOR_BOOLEAN,
					cellDecoratorMap.containsKey(CellStyleUtils.CELL_DECORATOR_BOOLEAN)
							? initializeCellStyleByCellDecorator(wb,
									cellDecoratorMap.get(CellStyleUtils.CELL_DECORATOR_BOOLEAN))
							: initializeBooleanCellDecorator(wb));
			cellDecoratorMap.remove(cellDecoratorMap.containsKey(CellStyleUtils.CELL_DECORATOR_BOOLEAN));
		}
		if (stylesMap.get(CellStyleUtils.CELL_DECORATOR_GENERIC) == null) {
			stylesMap.put(CellStyleUtils.CELL_DECORATOR_GENERIC,
					cellDecoratorMap.containsKey(CellStyleUtils.CELL_DECORATOR_GENERIC)
							? initializeCellStyleByCellDecorator(wb,
									cellDecoratorMap.get(CellStyleUtils.CELL_DECORATOR_GENERIC))
							: initializeGenericCellDecorator(wb));
			cellDecoratorMap.remove(cellDecoratorMap.containsKey(CellStyleUtils.CELL_DECORATOR_GENERIC));
		}

		for (Map.Entry<String, CellDecorator> object : cellDecoratorMap.entrySet()) {
			stylesMap.put(object.getKey(), initializeCellStyleByCellDecorator(wb, object.getValue()));
		}
	}

	/**
	 * Initialize {@link CellStyle} by Cell Decorator.
	 * 
	 * @param wb
	 *            the {@link Workbook} in use
	 * @param decorator
	 *            the {@link CellDecorator} to use
	 * @return the {@link CellStyle} decorator
	 */
	private CellStyle initializeCellStyleByCellDecorator(Workbook wb, CellDecorator decorator)
			throws ConfigurationException {
		CellStyle cs = CellStyleUtils.initializeCellStyle(wb);
		try {
			/* add the alignment to the cell */
			CellStyleUtils.applyAlignment(cs, decorator.getAlignment(), decorator.getVerticalAlignment());

			/* add the border to the cell */
			borderPropagationManagement(decorator);
			CellStyleUtils.applyBorder(cs, decorator.getBorderLeft(), decorator.getBorderRight(),
					decorator.getBorderTop(), decorator.getBorderBottom());

			/* add the background to the cell */
			cs.setFillForegroundColor(decorator.getForegroundColor());
			cs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

			/* add the wrap mode to the cell */
			cs.setWrapText(decorator.isWrapText());

			/* add the font style to the cell */
			CellStyleUtils.applyFont(wb, cs, decorator.getFontName(), decorator.getFontSize(), decorator.getFontColor(),
					decorator.isFontBold(), decorator.isFontItalic(), decorator.getFontUnderline());
		} catch (Exception e) {
			throw new ConfigurationException(ExceptionMessage.ConfigurationException_Missing.getMessage(),
					e);
		}
		return cs;
	}

	/**
	 * Initialize {@link CellStyle} by XlsDecorator.
	 * 
	 * @param wb
	 *            the {@link Workbook} in use
	 * @param decorator
	 *            the {@link CellDecorator} to use
	 * @return the {@link CellStyle} decorator
	 */
	private CellStyle initializeCellStyleByXlsDecorator(Workbook wb, XlsDecorator decorator)
			throws ConfigurationException {
		CellStyle cs = CellStyleUtils.initializeCellStyle(wb);
		try {
			/* add the alignment to the cell */
			CellStyleUtils.applyAlignment(cs, decorator.alignment(), decorator.verticalAlignment());

			/* add the border to the cell */
			if (decorator.border() != 0 && decorator.borderLeft() == 0 && decorator.borderRight() == 0
					&& decorator.borderTop() == 0 && decorator.borderBottom() == 0) {
				CellStyleUtils.applyBorder(cs, decorator.border(), decorator.border(), decorator.border(),
						decorator.border());
			} else {
				CellStyleUtils.applyBorder(cs, decorator.borderLeft(), decorator.borderRight(), decorator.borderTop(),
						decorator.borderBottom());
			}

			/* add the background to the cell */
			cs.setFillForegroundColor(decorator.foregroundColor());
			cs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

			/* add the wrap mode to the cell */
			cs.setWrapText(decorator.wrapText());

			/* add the font style to the cell */
			CellStyleUtils.applyFont(wb, cs, decorator.fontName(), decorator.fontSize(), decorator.fontColor(),
					decorator.fontBold(), decorator.fontItalic(), decorator.fontUnderline());
		} catch (Exception e) {
			throw new ConfigurationException(ExceptionMessage.ConfigurationException_Missing.getMessage(),
					e);
		}
		return cs;
	}

	/**
	 * if specific border not configured, propagate generic border configuration
	 * to specific border.
	 * 
	 * @param decorator
	 *            the {@link CellDecorator} to use
	 */
	private void borderPropagationManagement(CellDecorator decorator) {
		/* if specific border not configured */
		if (decorator.getBorder() != 0 && decorator.getBorderLeft() == 0 && decorator.getBorderRight() == 0
				&& decorator.getBorderTop() == 0 && decorator.getBorderBottom() == 0) {
			/* propagate generic border configuration to specific border */
			decorator.setBorderLeft(decorator.getBorder());
			decorator.setBorderRight(decorator.getBorder());
			decorator.setBorderTop(decorator.getBorder());
			decorator.setBorderBottom(decorator.getBorder());
		}
	}

	/**
	 * Force the header cell decorator.
	 * 
	 * @param decorator
	 *            the {@link CellDecorator} to apply
	 */
	@Override
	public void overrideHeaderCellDecorator(CellDecorator decorator) {
		cellDecoratorMap.put(CellStyleUtils.CELL_DECORATOR_HEADER, decorator);
	}

	/**
	 * Force the numeric cell decorator.
	 * 
	 * @param decorator
	 *            the {@link CellDecorator} to apply
	 */
	@Override
	public void overrideNumericCellDecorator(CellDecorator decorator) {
		cellDecoratorMap.put(CellStyleUtils.CELL_DECORATOR_NUMERIC, decorator);
	}

	/**
	 * Force the boolean cell decorator.
	 * 
	 * @param decorator
	 *            the {@link CellDecorator} to apply
	 */
	@Override
	public void overrideBooleanCellDecorator(CellDecorator decorator) {
		cellDecoratorMap.put(CellStyleUtils.CELL_DECORATOR_BOOLEAN, decorator);
	}

	/**
	 * Force the date cell decorator.
	 * 
	 * @param decorator
	 *            the {@link CellDecorator} to apply
	 */
	@Override
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
	@Override
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
		configuration.setPropagationType(type);
	}

	/**
	 * Force the cascade level to apply at the Sheet.
	 * 
	 * @param type
	 *            the {@link CascadeType} to apply
	 */
	public void overrideCascadeLevel(CascadeType type) {
		configuration.setCascadeLevel(type);
	}

	/**
	 * Get the runtime class of the object passed as parameter.
	 * 
	 * @param object
	 *            the object
	 * @return the runtime class
	 * @throws ElementException
	 */
	private Class<?> initializeRuntimeClass(Object object) throws ElementException {
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
	 */
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
	 * @param config
	 *            the {@link XlsConfiguration}
	 * @return
	 */
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
	 * @param config
	 *            the {@link XlsSheet}
	 * @return
	 */
	private Configuration initializeSheetConfiguration(XlsSheet config) {
		if (configuration == null) {
			configuration = new Configuration();
		}
		configuration.setTitleSheet(config.title());
		configuration.setPropagationType(config.propagation());
		configuration.setStartRow(config.startRow());
		configuration.setStartCell(config.startCell());
		return configuration;
	}

	/**
	 * Initialize Workbook.
	 * 
	 * @param type
	 *            the {@link ExtensionFileType} of workbook
	 * @return the {@link Workbook}.
	 */
	private Workbook initializeWorkbook(ExtensionFileType type) {
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
	private Workbook initializeWorkbook(byte[] byteArray, ExtensionFileType type) throws IOException {
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
	 */
	private Sheet initializeSheet(Workbook wb, String sheetName) {
		return wb.createSheet(sheetName);
	}

	/**
	 * Apply merge region if necessary.
	 * 
	 * @param configCriteria
	 *            the {@link ConfigCriteria}
	 * @param r
	 *            the {@link Row}
	 * @param idxR
	 *            the position of the row
	 * @param idxC
	 *            the position of the cell
	 * @param isPH
	 *            true if propagation horizontally, false if propagation
	 *            vertically
	 */
	private void applyMergeRegion(ConfigCriteria configCriteria, Row r, int idxR, int idxC, boolean isPH)
			throws Exception {
		/* Process @XlsNestedHeader */
		if (configCriteria.getField().isAnnotationPresent(XlsNestedHeader.class)) {
			XlsNestedHeader annotation = (XlsNestedHeader) configCriteria.getField()
					.getAnnotation(XlsNestedHeader.class);
			/* if row null is necessary to create it */
			if (r == null) {
				r = initializeRow(configCriteria.getSheet(), idxR);
			}

			/* validation of configuration */
			isValidNestedHeaderConfiguration(isPH, annotation);

			/* prepare position rows / cells */
			int startRow, endRow, startCell, endCell;
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
			initializeHeaderCell(r, startCell, annotation.title());

			/* merge region of the master header cell */
			configCriteria.getSheet().addMergedRegion(new CellRangeAddress(startRow, endRow, startCell, endCell));
		}
	}

	/**
	 * Validate if the master header configuration is valid.
	 * 
	 * @param isPH
	 *            true if propagation is HORIZONTAL otherwise false to
	 *            propagation VERTICAL
	 * @param annotation
	 *            the {@link XlsNestedHeader} annotation
	 * @throws ConfigurationException
	 */
	private void isValidNestedHeaderConfiguration(boolean isPH, XlsNestedHeader annotation)
			throws ConfigurationException {

		if (isPH && annotation.startX() == annotation.endX()) {
			throw new ConfigurationException(
					ExceptionMessage.ConfigurationException_Incompatible.getMessage());

		} else if (!isPH && annotation.startY() == annotation.endY()) {
			throw new ConfigurationException(
					ExceptionMessage.ConfigurationException_Incompatible.getMessage());
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
	private Row initializeRow(Sheet s, int idxR) {
		return s.createRow(idxR);
	}

	/**
	 * Initialize the header cell.
	 * 
	 * @param r
	 *            {@link Row} to add the cell
	 * @param idxC
	 *            position of the new cell
	 * @param value
	 *            the value of the cell content
	 * @return the cell created
	 */
	private Cell initializeHeaderCell(Row r, int idxC, String value) throws Exception {
		Cell c = r.createCell(idxC);
		c.setCellValue(value);
		c.setCellStyle(stylesMap.get(CellStyleUtils.CELL_DECORATOR_HEADER));
		return c;

	}

	/**
	 * Initialize an cell according the type of field and the PropagationType is
	 * PROPAGATION_HORIZONTAL.
	 * 
	 * @param configCriteria
	 *            the {@link ConfigCriteria}
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
	 * @throws Exception
	 */
	private int initializeCellByFieldHorizontal(ConfigCriteria configCriteria, Object o, int idxR, int idxC, int cL)
			throws Exception {

		/* make the field accessible to recover the value */
		configCriteria.getField().setAccessible(true);

		int counter = 0;

		Class<?> fT = configCriteria.getField().getType();

		boolean isAppliedToBaseObject = applyBaseObject(configCriteria, o, fT, idxC);

		if (!isAppliedToBaseObject && !fT.isPrimitive()) {
			Object nO = configCriteria.getField().get(o);
			/* manage null objects */
			if (nO == null) {
				nO = fT.newInstance();
			}
			Class<?> oC = nO.getClass();

			counter = marshalAsPropagationHorizontal(configCriteria, nO, oC, idxR, idxC - 1, cL + 1);
		}
		return counter;
	}

	/**
	 * Initialize an cell according the type of field and the PropagationType is
	 * PROPAGATION_VERTICAL.
	 * 
	 * @param configCriteria
	 *            the {@link ConfigCriteria}
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
	 * @throws Exception
	 */
	private int initializeCellByFieldVertical(ConfigCriteria configCriteria, Object o, Row r, int idxR, int idxC,
			int cL) throws Exception {

		/* make the field accessible to recover the value */
		configCriteria.getField().setAccessible(true);

		int counter = 0;

		Class<?> fT = configCriteria.getField().getType();

		configCriteria.setRow(r);

		boolean isAppliedToBaseObject = applyBaseObject(configCriteria, o, fT, idxC);

		if (!isAppliedToBaseObject && !fT.isPrimitive()) {
			Object nO = configCriteria.getField().get(o);
			/* manage null objects */
			if (nO == null) {
				nO = fT.newInstance();
			}
			Class<?> oC = nO.getClass();

			counter = marshalAsPropagationVertical(configCriteria, nO, oC, idxR - 1, idxC - 1, cL + 1);
		}
		return counter;
	}

	/**
	 * Apply the base object to cell.
	 * 
	 * @param configCriteria
	 *            the {@link ConfigCriteria}
	 * @param o
	 *            the object
	 * @param fT
	 *            the field type
	 * @param idxC
	 *            the position of the cell
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws ConverterException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 */
	private boolean applyBaseObject(ConfigCriteria configCriteria, Object o, Class<?> fT, int idxC)
			throws IllegalArgumentException, IllegalAccessException, ConverterException, NoSuchMethodException,
			SecurityException, InvocationTargetException {
		/* flag which define if the cell was updated or not */
		boolean isUpdated = false;
		/* initialize cell */
		Cell cell = null;

		switch (fT.getName()) {
		case CellValueUtils.OBJECT_DATE:
			cell = configCriteria.getRow().createCell(idxC);
			isUpdated = CellValueUtils.toDate(configCriteria, o, cell);
			break;

		case CellValueUtils.OBJECT_STRING:
			cell = configCriteria.getRow().createCell(idxC);
			isUpdated = CellValueUtils.toString(configCriteria, o, cell);
			break;

		case CellValueUtils.OBJECT_INTEGER:
			/* falls through */
		case CellValueUtils.PRIMITIVE_INTEGER:
			cell = configCriteria.getRow().createCell(idxC);
			isUpdated = CellValueUtils.toInteger(configCriteria, o, cell);
			break;

		case CellValueUtils.OBJECT_LONG:
			/* falls through */
		case CellValueUtils.PRIMITIVE_LONG:
			cell = configCriteria.getRow().createCell(idxC);
			isUpdated = CellValueUtils.toLong(configCriteria, o, cell);
			break;

		case CellValueUtils.OBJECT_DOUBLE:
			/* falls through */
		case CellValueUtils.PRIMITIVE_DOUBLE:
			cell = configCriteria.getRow().createCell(idxC);
			isUpdated = CellValueUtils.toDouble(configCriteria, o, cell);
			break;

		case CellValueUtils.OBJECT_BIGDECIMAL:
			cell = configCriteria.getRow().createCell(idxC);
			isUpdated = CellValueUtils.toBigDecimal(configCriteria, o, cell);
			break;

		case CellValueUtils.OBJECT_FLOAT:
			/* falls through */
		case CellValueUtils.PRIMITIVE_FLOAT:
			cell = configCriteria.getRow().createCell(idxC);
			isUpdated = CellValueUtils.toFloat(configCriteria, o, cell);
			break;

		case CellValueUtils.OBJECT_BOOLEAN:
			/* falls through */
		case CellValueUtils.PRIMITIVE_BOOLEAN:
			cell = configCriteria.getRow().createCell(idxC);
			isUpdated = CellValueUtils.toBoolean(configCriteria, o, cell);
			break;

		default:
			isUpdated = false;
			break;
		}

		if (!isUpdated && fT.isEnum()) {
			cell = configCriteria.getRow().createCell(idxC);
			isUpdated = CellValueUtils.toEnum(configCriteria, o, cell);
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
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws ConverterException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private boolean applyBaseExcelObject(Object o, Class<?> fT, Field f, Cell c, XlsElement xlsAnnotation)
			throws IllegalArgumentException, IllegalAccessException, ConverterException {
		/* flag which define if the cell was updated or not */
		boolean isUpdated = false;

		f.setAccessible(true);

		switch (fT.getName()) {
		case CellValueUtils.OBJECT_DATE:
			// FIXME review the management
			if (StringUtils.isBlank(xlsAnnotation.transformMask())) {
				f.set(o, c.getDateCellValue());
			} else {
				String date = c.getStringCellValue();
				if (StringUtils.isNotBlank(date)) {

					String tM = xlsAnnotation.transformMask();
					String fM = xlsAnnotation.formatMask();
					String decorator = StringUtils.isEmpty(tM)
							? (StringUtils.isEmpty(fM) ? CellStyleUtils.MASK_DECORATOR_DATE : fM) : tM;

					SimpleDateFormat dt = new SimpleDateFormat(decorator);
					try {
						Date dateConverted = dt.parse(date);
						f.set(o, dateConverted);
					} catch (ParseException e) {
						/*
						 * if date decorator do not match with a valid mask
						 * launch exception
						 */
						throw new ConverterException(ExceptionMessage.ConverterException_Date.getMessage(),
								e);
					}
				}
			}
			isUpdated = true;

			break;

		case CellValueUtils.OBJECT_STRING:
			if (StringUtils.isNotBlank(CellValueUtils.fromExcel(c))) {
				f.set(o, CellValueUtils.fromExcel(c));
			}
			isUpdated = true;
			break;

		case CellValueUtils.OBJECT_INTEGER:
			/* falls through */
		case CellValueUtils.PRIMITIVE_INTEGER:
			if (StringUtils.isNotBlank(CellValueUtils.fromExcel(c))) {
				f.set(o, Double.valueOf(CellValueUtils.fromExcel(c)).intValue());
			}
			isUpdated = true;
			break;

		case CellValueUtils.OBJECT_LONG:
			/* falls through */
		case CellValueUtils.PRIMITIVE_LONG:
			if (StringUtils.isNotBlank(CellValueUtils.fromExcel(c))) {
				f.set(o, Double.valueOf(CellValueUtils.fromExcel(c)).longValue());
			}
			isUpdated = true;
			break;

		case CellValueUtils.OBJECT_DOUBLE:
			/* falls through */
		case CellValueUtils.PRIMITIVE_DOUBLE:
			if (StringUtils.isNotBlank(CellValueUtils.fromExcel(c))) {
				if (StringUtils.isNotBlank(xlsAnnotation.transformMask())) {
					f.set(o, Double.valueOf(CellValueUtils.fromExcel(c).replace(",", ".")));
				} else {
					f.set(o, Double.valueOf(CellValueUtils.fromExcel(c)));
				}
			}
			isUpdated = true;
			break;

		case CellValueUtils.OBJECT_BIGDECIMAL:
			if (StringUtils.isNotBlank(CellValueUtils.fromExcel(c))) {
				if (StringUtils.isNotBlank(xlsAnnotation.transformMask())) {
					f.set(o, BigDecimal.valueOf(Double.valueOf(CellValueUtils.fromExcel(c).replace(",", "."))));
				} else {
					f.set(o, BigDecimal.valueOf(Double.valueOf(CellValueUtils.fromExcel(c))));
				}
			}
			isUpdated = true;
			break;

		case CellValueUtils.OBJECT_FLOAT:
			/* falls through */
		case CellValueUtils.PRIMITIVE_FLOAT:
			if (StringUtils.isNotBlank(CellValueUtils.fromExcel(c))) {
				f.set(o, Double.valueOf(CellValueUtils.fromExcel(c)).floatValue());
			}
			isUpdated = true;
			break;

		case CellValueUtils.OBJECT_BOOLEAN:
			/* falls through */
		case CellValueUtils.PRIMITIVE_BOOLEAN:

			String booleanValue = c.getStringCellValue();
			if (StringUtils.isNotBlank(booleanValue)) {
				if (StringUtils.isNotBlank(xlsAnnotation.transformMask())) {
					/* apply format mask defined at transform mask */
					String[] split = xlsAnnotation.transformMask().split("/");
					f.set(o, StringUtils.isNotBlank(booleanValue) ? (split[0].equals(booleanValue) ? true : false)
							: null);

				} else {
					/* locale mode */
					f.set(o, StringUtils.isNotBlank(booleanValue) ? Boolean.valueOf(booleanValue) : null);
				}
			}
			isUpdated = true;
			break;

		default:
			isUpdated = false;
			break;
		}

		if (!isUpdated && fT.isEnum()) {
			if (StringUtils.isNotBlank(c.getStringCellValue())) {
				f.set(o, Enum.valueOf((Class<Enum>) fT, c.getStringCellValue()));
			}
			isUpdated = true;
		}

		return isUpdated;
	}

	/**
	 * Convert the object to file with the PropagationType as
	 * PROPAGATION_HORIZONTAL.
	 * 
	 * @param configCriteria
	 *            the {@link ConfigCriteria}
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
	 * @throws IllegalAccessException
	 */
	private int marshalAsPropagationHorizontal(ConfigCriteria configCriteria, Object o, Class<?> oC, int idxR, int idxC,
			int cL) throws Exception {
		/* counter related to the number of fields (if new object) */
		int counter = -1;

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
				applyMergeRegion(configCriteria, null, tmpIdxRow, idxC, true);
			}
			/* Process @XlsElement */
			if (f.isAnnotationPresent(XlsElement.class)) {
				XlsElement xlsAnnotation = (XlsElement) f.getAnnotation(XlsElement.class);

				/* update annotation at ConfigCriteria */
				configCriteria.setElement(xlsAnnotation);

				/* apply customized rules defined at the object */
				if (StringUtils.isNotBlank(xlsAnnotation.customizedRules())) {
					CellValueUtils.applyCustomizedRules(o, xlsAnnotation.customizedRules());
				}

				/*
				 * increment of the counter related to the number of fields (if
				 * new object)
				 */
				counter++;
				if (configCriteria.getRowHeader() != null) {
					/* header treatment */
					initializeHeaderCell(configCriteria.getRowHeader(), idxC + xlsAnnotation.position(),
							xlsAnnotation.title());
				}
				/* content treatment */
				idxC += initializeCellByFieldHorizontal(configCriteria, o, idxR, idxC + xlsAnnotation.position(), cL);
			}
		}
		return counter;
	}

	/**
	 * Convert the object to file with the PropagationType as
	 * PROPAGATION_VERTICAL.
	 * 
	 * @param configCriteria
	 *            the {@link ConfigCriteria}
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
	 * @throws Exception
	 */
	private int marshalAsPropagationVertical(ConfigCriteria configCriteria, Object o, Class<?> oC, int idxR, int idxC,
			int cL) throws Exception {
		/* counter related to the number of fields (if new object) */
		int counter = -1;
		/* backup base index of the cell */
		int baseIdxCell = idxC;

		/* get declared fields */
		List<Field> fieldList = Arrays.asList(oC.getDeclaredFields());
		for (Field field : fieldList) {
			/* process each field from the object */
			configCriteria.setField(field);

			/* restart the index of the cell */
			idxC = baseIdxCell;

			/* Process @XlsElement */
			if (field.isAnnotationPresent(XlsElement.class)) {
				XlsElement xlsAnnotation = (XlsElement) field.getAnnotation(XlsElement.class);
				configCriteria.setElement(xlsAnnotation);

				/* apply customized rules defined at the object */
				if (StringUtils.isNotBlank(xlsAnnotation.customizedRules())) {
					CellValueUtils.applyCustomizedRules(o, xlsAnnotation.customizedRules());
				}

				/*
				 * increment of the counter related to the number of fields (if
				 * new object)
				 */
				counter++;

				/* create the row */
				Row row = null;
				if (baseIdxCell == 1) {
					int tmpIdxCell = idxC - 1;
					/* initialize row */
					row = initializeRow(configCriteria.getSheet(), idxR + xlsAnnotation.position());

					/* apply merge region */
					applyMergeRegion(configCriteria, row, idxR, tmpIdxCell, false);

					/* header treatment */
					initializeHeaderCell(row, idxC, xlsAnnotation.title());

				} else {
					row = configCriteria.getSheet().getRow(idxR + xlsAnnotation.position());
				}

				/* increment the cell position */
				idxC++;
				/* content treatment */
				idxR += initializeCellByFieldVertical(configCriteria, o, row, idxR + xlsAnnotation.position(), idxC,
						cL);
			}
		}
		return counter;
	}

	/**
	 * Convert the file to object with the PropagationType as
	 * PROPAGATION_HORIZONTAL.
	 * 
	 * @param configCriteria
	 *            the {@link ConfigCriteria}
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
	 */
	private int unmarshalAsPropagationHorizontal(ConfigCriteria configCriteria, Object o, Class<?> oC, int idxR,
			int idxC) throws IllegalAccessException, ConverterException, InstantiationException {
		/* counter related to the number of fields (if new object) */
		int counter = -1;

		/* get declared fields */
		List<Field> fL = Arrays.asList(oC.getDeclaredFields());
		for (Field f : fL) {
			/* process each field from the object */
			Class<?> fT = f.getType();

			/* Process @XlsElement */
			if (f.isAnnotationPresent(XlsElement.class)) {
				XlsElement xlsAnnotation = (XlsElement) f.getAnnotation(XlsElement.class);
				/*
				 * increment of the counter related to the number of fields (if
				 * new object)
				 */
				counter++;

				/* content row */
				Row contentRow = configCriteria.getSheet().getRow(idxR + 1);
				Cell contentCell = contentRow.getCell(idxC + xlsAnnotation.position());

				boolean isAppliedToBaseObject = applyBaseExcelObject(o, fT, f, contentCell, xlsAnnotation);

				if (!isAppliedToBaseObject && !fT.isPrimitive()) {

					Object subObjbect = fT.newInstance();
					Class<?> subObjbectClass = subObjbect.getClass();

					int internalCellCounter = unmarshalAsPropagationHorizontal(configCriteria, subObjbect,
							subObjbectClass, idxR, idxC + xlsAnnotation.position() - 1);

					/* add the sub object to the parent object */
					f.set(o, subObjbect);

					/* update the index */
					idxC += internalCellCounter;
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
	 *            the {@link ConfigCriteria}
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
	 */
	private int unmarshalAsPropagationVertical(ConfigCriteria configCriteria, Object object, Class<?> oC, int idxR,
			int idxC) throws IllegalAccessException, ConverterException, InstantiationException {
		/* counter related to the number of fields (if new object) */
		int counter = -1;

		/* get declared fields */
		List<Field> fL = Arrays.asList(oC.getDeclaredFields());
		for (Field f : fL) {
			/* process each field from the object */
			Class<?> fT = f.getType();

			/* Process @XlsElement */
			if (f.isAnnotationPresent(XlsElement.class)) {
				XlsElement xlsAnnotation = (XlsElement) f.getAnnotation(XlsElement.class);
				/*
				 * increment of the counter related to the number of fields (if
				 * new object)
				 */
				counter++;

				/* content row */
				Row contentRow = configCriteria.getSheet().getRow(idxR + xlsAnnotation.position());
				Cell contentCell = contentRow.getCell(idxC + 1);

				boolean isAppliedToBaseObject = applyBaseExcelObject(object, fT, f, contentCell, xlsAnnotation);

				if (!isAppliedToBaseObject && !fT.isPrimitive()) {

					Object subObjbect = fT.newInstance();
					Class<?> subObjbectClass = subObjbect.getClass();

					int internalCellCounter = unmarshalAsPropagationVertical(configCriteria, subObjbect,
							subObjbectClass, idxR + xlsAnnotation.position() - 1, idxC);

					/* add the sub object to the parent object */
					f.set(object, subObjbect);

					/* update the index */
					idxR += internalCellCounter;
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
	 * @throws Exception
	 */
	private FileOutputStream workbookFileOutputStream(Workbook wb, String name) throws Exception {
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
	private byte[] workbookToByteAray(Workbook wb) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			wb.write(bos);
		} finally {
			bos.close();
		}

		return bos.toByteArray();
	}

	/* ######################## Marshal methods ########################## */

	/**
	 * Generate the sheet from the object and return the sheet generated.
	 * 
	 * @param object
	 *            the object to apply at the workbook.
	 * @return the {@link Sheet} generated
	 */
	public Sheet marshalToSheet(Object object) throws Exception {
		/* Generate the workbook from the object passed as parameter */
		Workbook wb = marshalToWorkbook(object);

		/* Generate the sheet to return */
		return wb.getSheetAt(0);
	}

	/**
	 * Generate the workbook from the object and return the workbook generated.
	 * 
	 * @param object
	 *            the object to apply at the workbook.
	 * @return the {@link Workbook} generated
	 */
	public Workbook marshalToWorkbook(Object object) throws Exception {
		/* initialize the runtime class of the object */
		Class<?> oC = initializeRuntimeClass(object);

		/* initialize configuration data */
		Configuration config = initializeConfigurationData(oC);

		ConfigCriteria configCriteria = new ConfigCriteria();
		configCriteria.setPropagation(config.getPropagationType());
		configCriteria.setExtension(config.getExtensionFile());

		/* initialize Workbook */
		configCriteria.setWorkbook(initializeWorkbook(config.getExtensionFile()));

		/* initialize style cell via annotations */
		if (oC.isAnnotationPresent(XlsDecorators.class)) {
			XlsDecorators xlsDecorators = (XlsDecorators) oC.getAnnotation(XlsDecorators.class);
			for (XlsDecorator decorator : xlsDecorators.values()) {
				stylesMap.put(decorator.decoratorName(),
						initializeCellStyleByXlsDecorator(configCriteria.getWorkbook(), decorator));
			}
		}

		/* initialize style cell via default option */
		configCriteria.setStylesMap(stylesMap);
		initializeCellDecorator(configCriteria.getWorkbook());

		/* initialize Sheet */
		configCriteria.setSheet(initializeSheet(configCriteria.getWorkbook(), config.getTitleSheet()));

		/* initialize Row & Cell */
		int idxRow = config.getStartRow();
		int idxCell = config.getStartCell();

		/* initialize rows according the PropagationType */
		if (PropagationType.PROPAGATION_HORIZONTAL.equals(config.getPropagationType())) {
			configCriteria.setRowHeader(initializeRow(configCriteria.getSheet(), idxRow++));
			configCriteria.setRow(initializeRow(configCriteria.getSheet(), idxRow++));
			marshalAsPropagationHorizontal(configCriteria, object, oC, idxRow, idxCell, 0);

		} else {
			marshalAsPropagationVertical(configCriteria, object, oC, idxRow, idxCell, 0);

		}

		// FIXME apply the column size here - if necessary

		return configCriteria.getWorkbook();
	}

	/**
	 * Generate the workbook from the object passed as parameter and return the
	 * respective {@link FileOutputStream}.
	 * 
	 * @param object
	 *            the object to apply at the workbook.
	 * @return the {@link Workbook} generated
	 */
	public byte[] marshalToByte(Object object) throws Exception {
		/* Generate the workbook from the object passed as parameter */
		Workbook wb = marshalToWorkbook(object);

		/* Generate the byte array to return */
		return workbookToByteAray(wb);
	}

	/**
	 * Generate the workbook from the object passed as parameter and save it at
	 * the path send as parameter.
	 * 
	 * @param object
	 *            the object to apply at the workbook.
	 * @param pathFile
	 *            the file path where will be the file saved
	 */
	public void marshalAndSave(Object object, String pathFile) throws Exception {
		/* Generate the workbook from the object passed as parameter */
		Workbook wb = marshalToWorkbook(object);

		/*
		 * check if the path terminate with the file separator, otherwise will
		 * be added to avoid any problem
		 */
		if (!pathFile.endsWith(File.separator)) {
			pathFile = pathFile.concat(File.separator);
		}

		/* Save the Workbook at a specified path (received as parameter) */
		workbookFileOutputStream(wb, pathFile + configuration.getNameFile());
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
	public void marshalAsCollection(Collection<?> collection, final String filename,
			final ExtensionFileType extensionFileType) throws Exception {

		// Temos que iniciar o config data neste ponto porque
		// como estamos na escritura de uma coleccao
		// temos que ter o nome e a extensao antes de iniciar o excel
		configuration = new Configuration();
		configuration.setExtensionFile(extensionFileType);
		configuration.setNameFile(filename);
		Configuration config = configuration;

		ConfigCriteria configCriteria = new ConfigCriteria();
		configCriteria.setPropagation(config.getPropagationType());
		configCriteria.setExtension(config.getExtensionFile());

		// initialize Workbook
		configCriteria.setWorkbook(initializeWorkbook(config.getExtensionFile()));

		// initialize style cell via default option
		initializeCellDecorator(configCriteria.getWorkbook());
		configCriteria.setStylesMap(stylesMap);

		int idxRow = 0, idxCell = 0, index = 0;

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
					idxCell = index + 1;
				}
				marshalAsPropagationVertical(configCriteria, object, objectClass, idxRow, idxCell, 0);
				index = index + 1;
			}

		}

		// FIXME manage return value
		workbookFileOutputStream(configCriteria.getWorkbook(),
				"D:\\projects\\" + config.getNameFile() + config.getExtensionFile().getExtension());
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
	 * @return the {@link Object} filled up
	 * @throws ConfigurationException
	 */
	public Object unmarshalFromWorkbook(Object object, Workbook workbook) throws Exception {
		/* initialize the runtime class of the object */
		Class<?> oC = initializeRuntimeClass(object);

		/* initialize configuration data */
		Configuration config = initializeConfigurationData(oC);

		ConfigCriteria configCriteria = new ConfigCriteria();
		configCriteria.setPropagation(config.getPropagationType());
		configCriteria.setExtension(config.getExtensionFile());
		configCriteria.setWorkbook(workbook);
		configCriteria.setSheet(configCriteria.getWorkbook().getSheet(config.getTitleSheet()));

		/* initialize index row & cell */
		int idxRow = config.getStartRow();
		int idxCell = config.getStartCell();

		if (PropagationType.PROPAGATION_HORIZONTAL.equals(config.getPropagationType())) {
			unmarshalAsPropagationHorizontal(configCriteria, object, oC, idxRow, idxCell);
		} else {
			unmarshalAsPropagationVertical(configCriteria, object, oC, idxRow, idxCell);
		}

		return object;
	}

	/**
	 * Generate the object from the path file passed as parameter.
	 * 
	 * @param object
	 *            the object to fill up.
	 * @param pathFile
	 *            the path where is found the file to read and pass the
	 *            information to the object
	 * @return the {@link Object} filled up
	 */
	public Object unmarshalFromPath(Object object, String pathFile) throws Exception {
		/* initialize the runtime class of the object */
		Class<?> oC = initializeRuntimeClass(object);

		/* initialize configuration data */
		Configuration config = initializeConfigurationData(oC);

		/*
		 * check if the path terminate with the file separator, otherwise will
		 * be added to avoid any problem
		 */
		if (!pathFile.endsWith(File.separator)) {
			pathFile = pathFile.concat(File.separator);
		}

		FileInputStream input = new FileInputStream(pathFile + config.getNameFile());
		unmarshalIntern(object, oC, config, input);

		return object;
	}

	/**
	 * Generate the object from the byte array passed as parameter.
	 * 
	 * @param object
	 *            the object to fill up.
	 * @param inputByte
	 *            the byte array to read and pass the information to the object
	 * @return the {@link Object} filled up
	 */
	public Object unmarshalFromByte(Object object, byte[] byteArray) throws Exception {
		/* initialize the runtime class of the object */
		Class<?> oC = initializeRuntimeClass(object);

		/* initialize configuration data */
		Configuration config = initializeConfigurationData(oC);

		ConfigCriteria configCriteria = new ConfigCriteria();
		configCriteria.setPropagation(config.getPropagationType());
		configCriteria.setExtension(config.getExtensionFile());
		configCriteria.setWorkbook(initializeWorkbook(byteArray, configuration.getExtensionFile()));
		configCriteria.setSheet(configCriteria.getWorkbook().getSheet(config.getTitleSheet()));

		/* initialize index row & cell */
		int idxRow = config.getStartRow();
		int idxCell = config.getStartCell();

		if (PropagationType.PROPAGATION_HORIZONTAL.equals(config.getPropagationType())) {
			unmarshalAsPropagationHorizontal(configCriteria, object, oC, idxRow, idxCell);
		} else {
			unmarshalAsPropagationVertical(configCriteria, object, oC, idxRow, idxCell);
		}

		return object;
	}

	/**
	 * Manage the unmarshal internally.
	 * 
	 * @param object
	 *            the object to fill up.
	 * @param oC
	 *            the obect class
	 * @param config
	 *            the {@link Configuration} to use
	 * @param input
	 *            the {@link FileInputStream} to use
	 * @throws IOException
	 * @throws IllegalAccessException
	 * @throws ConverterException
	 * @throws InstantiationException
	 */
	private void unmarshalIntern(Object object, Class<?> oC, Configuration config, FileInputStream input)
			throws IOException, IllegalAccessException, ConverterException, InstantiationException {

		ConfigCriteria configCriteria = new ConfigCriteria();
		configCriteria.setPropagation(config.getPropagationType());
		configCriteria.setExtension(config.getExtensionFile());
		configCriteria.setWorkbook(initializeWorkbook(input, config.getExtensionFile()));
		configCriteria.setSheet(configCriteria.getWorkbook().getSheet(config.getTitleSheet()));

		/* initialize index row & cell */
		int idxRow = config.getStartRow();
		int idxCell = config.getStartCell();

		if (PropagationType.PROPAGATION_HORIZONTAL.equals(config.getPropagationType())) {
			unmarshalAsPropagationHorizontal(configCriteria, object, oC, idxRow, idxCell);
		} else {
			unmarshalAsPropagationVertical(configCriteria, object, oC, idxRow, idxCell);
		}
	}

	@Override
	public void marshalAsCollection(Collection<?> collection) {
		// TODO Auto-generated method stub

	}

	@Override
	public Collection<?> unmarshalToCollection(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	/* ############################################# */
	/* ################## TO REVIEW ################ */
	/* ############################################# */

	/**
	 * marshal
	 * 
	 * @throws Exception
	 */
	@Deprecated
	public void marshal(Object object) throws Exception {
		Class<?> objectClass = object.getClass();
		Configuration config = null;
		/* Process @XlsConfiguration */
		if (objectClass.isAnnotationPresent(XlsSheet.class)) {
			XlsConfiguration xlsAnnotation = (XlsConfiguration) objectClass.getAnnotation(XlsConfiguration.class);
			config = initializeConfiguration(xlsAnnotation);

		}
		/* Process @XlsSheet */
		if (objectClass.isAnnotationPresent(XlsSheet.class)) {
			XlsSheet xlsAnnotation = (XlsSheet) objectClass.getAnnotation(XlsSheet.class);
			config = initializeSheetConfiguration(xlsAnnotation);

		}

		// FIXME criteria
		ConfigCriteria configCriteria = new ConfigCriteria();
		configCriteria.setPropagation(config.getPropagationType());
		configCriteria.setExtension(config.getExtensionFile());

		/* initialize Workbook */
		configCriteria.setWorkbook(initializeWorkbook(config.getExtensionFile()));

		/* initialize style cell via annotations */
		if (objectClass.isAnnotationPresent(XlsDecorators.class)) {
			XlsDecorators xlsDecorators = (XlsDecorators) objectClass.getAnnotation(XlsDecorators.class);
			for (XlsDecorator decorator : xlsDecorators.values()) {
				stylesMap.put(decorator.decoratorName(),
						initializeCellStyleByXlsDecorator(configCriteria.getWorkbook(), decorator));
			}
		}
		/* initialize style cell via default option */
		initializeCellDecorator(configCriteria.getWorkbook());

		/* initialize Sheet */
		// FIXME add loop if necessary
		Sheet s = initializeSheet(configCriteria.getWorkbook(), config.getTitleSheet());

		/* initialize Row & Cell */
		int idxRow = config.getStartRow();
		int idxCell = config.getStartCell();

		/* initialize rows according the PropagationType */
		Row headerRow, contentRow;
		if (PropagationType.PROPAGATION_HORIZONTAL.equals(config.getPropagationType())) {
			headerRow = initializeRow(s, idxRow++);
			contentRow = initializeRow(s, idxRow++);

			// FIXME criteria
			configCriteria.setStylesMap(stylesMap);
			configCriteria.setSheet(s);
			configCriteria.setRowHeader(headerRow);
			configCriteria.setRow(contentRow);

			marshalAsPropagationHorizontal(configCriteria, object, objectClass, idxRow, idxCell, 0);
		} else {

			// FIXME criteria
			configCriteria.setStylesMap(stylesMap);
			configCriteria.setSheet(s);
			// configCriteria.setRowHeader(headerRow);
			// configCriteria.setRow(contentRow);
			marshalAsPropagationVertical(configCriteria, object, objectClass, idxRow, idxCell, 0);

		}
		// FIXME manage return value
		workbookFileOutputStream(configCriteria.getWorkbook(), "D:\\projects\\" + config.getNameFile());
	}

	@Deprecated
	public Object unmarshal(Object object) throws IOException, IllegalArgumentException, IllegalAccessException,
			ConverterException, InstantiationException, ElementException {
		/* initialize the runtime class of the object */
		Class<?> oC = initializeRuntimeClass(object);
		/* initialize configuration data */
		Configuration config = initializeConfigurationData(oC);

		FileInputStream input = new FileInputStream("D:\\projects\\" + config.getNameFile());
		unmarshalIntern(object, oC, config, input);

		return object;
	}

	/**
	 * Generate the workbook from the object passed as parameter and return the
	 * respective {@link FileOutputStream}.
	 * 
	 * @return the {@link Workbook} generated
	 */
	@Override
	public FileOutputStream marshalToFileOutputStream(Object object) throws Exception {
		/* Generate the workbook from the object passed as parameter */
		Workbook wb = marshalToWorkbook(object);

		/* Generate the FileOutputStream to return */
		return workbookFileOutputStream(wb, configuration.getNameFile());
	}

	@Override
	public Object unmarshalFromFileInputStream(Object object, FileInputStream stream) throws IOException,
			IllegalArgumentException, IllegalAccessException, ConverterException, InstantiationException {
		/* instance object class */
		Class<?> oC = object.getClass();
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

		unmarshalIntern(object, oC, config, stream);

		return object;
	}

}