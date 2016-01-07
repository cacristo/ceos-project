package org.jaexcel.framework.JAEX.engine;

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
import java.util.Collections;
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
import org.apache.poi.ss.usermodel.FontUnderline;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jaexcel.framework.JAEX.annotation.XlsConfiguration;
import org.jaexcel.framework.JAEX.annotation.XlsDecorator;
import org.jaexcel.framework.JAEX.annotation.XlsDecorators;
import org.jaexcel.framework.JAEX.annotation.XlsElement;
import org.jaexcel.framework.JAEX.annotation.XlsMasterHeader;
import org.jaexcel.framework.JAEX.annotation.XlsSheet;
import org.jaexcel.framework.JAEX.configuration.ConfigurationData;
import org.jaexcel.framework.JAEX.definition.ExtensionFileType;
import org.jaexcel.framework.JAEX.definition.JAEXExceptionMessage;
import org.jaexcel.framework.JAEX.definition.PropagationType;
import org.jaexcel.framework.JAEX.exception.JAEXConfigurationException;
import org.jaexcel.framework.JAEX.exception.JAEXConverterException;

public class Engine {

	// TODO manage the decorator configuration

	// TODO manage the internal value of an Enum

	// TODO see the behavior of using only one instance of the JAEX object
	// inside one project

	// TODO fix numeric code like 00005 parsed to excel will maintain the same
	// code to do it you just have to add '00005

	Workbook wb;
	ConfigurationData configData;
	CellDecorator headerDecorator;
	Map<String, CellStyle> stylesMap = new HashMap<String, CellStyle>();
	Map<String, CellDecorator> cellDecoratorMap = new HashMap<String, CellDecorator>();

	public Engine() {
		// initializeDefaultCellDecorator();
	}

	/**
	 * Initialize default Header Cell Decorator.
	 * 
	 * @return the {@link CellStyle} header decorator
	 */
	private CellStyle initializeHeaderCellDecorator() {
		CellStyle cs = CellStyleUtils.initializeCellStyle(wb);

		// add the alignment to the cell
		CellStyleUtils.applyAlignment(cs, CellStyle.ALIGN_CENTER, CellStyle.VERTICAL_CENTER);

		// add the border to the cell
		CellStyleUtils.applyBorder(cs, CellStyle.BORDER_THIN, CellStyle.BORDER_THIN, CellStyle.BORDER_THIN,
				CellStyle.BORDER_THIN);

		// add the background to the cell
		cs.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		cs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		// add the wrap mode to the cell
		cs.setWrapText(true);

		// add the font style to the cell
		CellStyleUtils.applyFont(wb, cs, "Arial", (short) 10, (short) 0, true, true, FontUnderline.NONE.getByteValue());

		return cs;
	}

	/**
	 * Initialize default Numeric Cell Decorator.
	 * 
	 * @return the {@link CellStyle} numeric decorator
	 */
	private CellStyle initializeNumericCellDecorator() {
		CellStyle cs = CellStyleUtils.initializeCellStyle(wb);

		// add the alignment to the cell
		CellStyleUtils.applyAlignment(cs, CellStyle.ALIGN_RIGHT, (short) 0);

		return cs;
	}

	/**
	 * Initialize default Date Cell Decorator.
	 * 
	 * @return the {@link CellStyle} date decorator
	 */
	private CellStyle initializeDateCellDecorator() {
		CellStyle cs = CellStyleUtils.initializeCellStyle(wb);

		// add the alignment to the cell
		CellStyleUtils.applyAlignment(cs, CellStyle.ALIGN_CENTER, (short) 0);

		return cs;
	}

	/**
	 * Initialize default Boolean Cell Decorator.
	 * 
	 * @return the {@link CellStyle} boolean decorator
	 */
	private CellStyle initializeBooleanCellDecorator() {
		CellStyle cs = CellStyleUtils.initializeCellStyle(wb);

		// add the alignment to the cell
		CellStyleUtils.applyAlignment(cs, CellStyle.ALIGN_CENTER, (short) 0);

		return cs;
	}

	/**
	 * Initialize Cell Decorator system.
	 * 
	 * @throws JAEXConfigurationException
	 */
	private void initializeCellDecorator() throws JAEXConfigurationException {

		if (stylesMap.get(CellStyleUtils.CELL_DECORATOR_HEADER) == null) {
			stylesMap.put(CellStyleUtils.CELL_DECORATOR_HEADER,
					cellDecoratorMap.containsKey(CellStyleUtils.CELL_DECORATOR_HEADER)
							? initializeCellStyleByCellDecorator(
									cellDecoratorMap.get(CellStyleUtils.CELL_DECORATOR_HEADER))
							: initializeHeaderCellDecorator());
			cellDecoratorMap.remove(cellDecoratorMap.containsKey(CellStyleUtils.CELL_DECORATOR_HEADER));
		}
		if (stylesMap.get(CellStyleUtils.CELL_DECORATOR_NUMERIC) == null) {
			stylesMap.put(CellStyleUtils.CELL_DECORATOR_NUMERIC,
					cellDecoratorMap.containsKey(CellStyleUtils.CELL_DECORATOR_NUMERIC)
							? initializeCellStyleByCellDecorator(
									cellDecoratorMap.get(CellStyleUtils.CELL_DECORATOR_NUMERIC))
							: initializeNumericCellDecorator());
			cellDecoratorMap.remove(cellDecoratorMap.containsKey(CellStyleUtils.CELL_DECORATOR_NUMERIC));
		}
		if (stylesMap.get(CellStyleUtils.CELL_DECORATOR_DATE) == null) {
			stylesMap.put(CellStyleUtils.CELL_DECORATOR_DATE,
					cellDecoratorMap.containsKey(CellStyleUtils.CELL_DECORATOR_DATE)
							? initializeCellStyleByCellDecorator(
									cellDecoratorMap.get(CellStyleUtils.CELL_DECORATOR_DATE))
							: initializeDateCellDecorator());
			cellDecoratorMap.remove(cellDecoratorMap.containsKey(CellStyleUtils.CELL_DECORATOR_DATE));
		}
		if (stylesMap.get(CellStyleUtils.CELL_DECORATOR_BOOLEAN) == null) {
			stylesMap.put(CellStyleUtils.CELL_DECORATOR_BOOLEAN,
					cellDecoratorMap.containsKey(CellStyleUtils.CELL_DECORATOR_BOOLEAN)
							? initializeCellStyleByCellDecorator(
									cellDecoratorMap.get(CellStyleUtils.CELL_DECORATOR_BOOLEAN))
							: initializeBooleanCellDecorator());
			cellDecoratorMap.remove(cellDecoratorMap.containsKey(CellStyleUtils.CELL_DECORATOR_BOOLEAN));
		}

		for (Map.Entry<String, CellDecorator> object : cellDecoratorMap.entrySet()) {
			stylesMap.put(object.getKey(), initializeCellStyleByCellDecorator(object.getValue()));
		}
	}

	/**
	 * Initialize {@link CellStyle} by Cell Decorator.
	 * 
	 * @param decorator
	 * @return the {@link CellStyle} decorator
	 */
	private CellStyle initializeCellStyleByCellDecorator(CellDecorator decorator) throws JAEXConfigurationException {
		CellStyle cs = CellStyleUtils.initializeCellStyle(wb);
		try {
			// add the alignment to the cell
			CellStyleUtils.applyAlignment(cs, decorator.getAlignment(), decorator.getVerticalAlignment());

			// add the border to the cell
			borderPropagationManagement(decorator);
			CellStyleUtils.applyBorder(cs, decorator.getBorderLeft(), decorator.getBorderRight(),
					decorator.getBorderTop(), decorator.getBorderBottom());

			// add the background to the cell
			cs.setFillForegroundColor(decorator.getForegroundColor());
			cs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

			// add the wrap mode to the cell
			cs.setWrapText(decorator.isWrapText());

			// add the font style to the cell
			CellStyleUtils.applyFont(wb, cs, decorator.getFontName(), decorator.getFontSize(), decorator.getFontColor(), decorator.isFontBold(),
					decorator.isFontItalic(), decorator.getFontUnderline());
		} catch (Exception e) {
			throw new JAEXConfigurationException(JAEXExceptionMessage.JAEXConfigurationException_Missing.getMessage(),
					e);
		}
		return cs;
	}

	/**
	 * Initialize {@link CellStyle} by XlsDecorator.
	 * 
	 * @param decorator
	 * @return the {@link CellStyle} decorator
	 */
	private CellStyle initializeCellStyleByXlsDecorator(XlsDecorator decorator) throws JAEXConfigurationException {
		CellStyle cs = CellStyleUtils.initializeCellStyle(wb);
		try {
			// add the alignment to the cell
			CellStyleUtils.applyAlignment(cs, decorator.alignment(), decorator.verticalAlignment());

			// add the border to the cell
			if (decorator.border() != 0 && decorator.borderLeft() == 0 && decorator.borderRight() == 0
					&& decorator.borderTop() == 0 && decorator.borderBottom() == 0) {
				CellStyleUtils.applyBorder(cs, decorator.border(), decorator.border(), decorator.border(),
						decorator.border());
			} else {
				CellStyleUtils.applyBorder(cs, decorator.borderLeft(), decorator.borderRight(), decorator.borderTop(),
						decorator.borderBottom());
			}

			// add the background to the cell
			cs.setFillForegroundColor(decorator.foregroundColor());
			cs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

			// add the wrap mode to the cell
			cs.setWrapText(decorator.wrapText());

			// add the font style to the cell
			CellStyleUtils.applyFont(wb, cs, decorator.fontName(), decorator.fontSize(), decorator.fontColor(), decorator.fontBold(),
					decorator.fontItalic(), decorator.fontUnderline());
		} catch (Exception e) {
			throw new JAEXConfigurationException(JAEXExceptionMessage.JAEXConfigurationException_Missing.getMessage(),
					e);
		}
		return cs;
	}

	/**
	 * if specific border not configured, propagate generic border configuration
	 * to specific border.
	 * 
	 * @param decorator
	 *            the cell decorator
	 */
	private void borderPropagationManagement(CellDecorator decorator) {
		// if specific border not configured
		if (decorator.getBorder() != 0 && decorator.getBorderLeft() == 0 && decorator.getBorderRight() == 0
				&& decorator.getBorderTop() == 0 && decorator.getBorderBottom() == 0) {
			// propagate generic border configuration to specific border
			decorator.setBorderLeft(decorator.getBorder());
			decorator.setBorderRight(decorator.getBorder());
			decorator.setBorderTop(decorator.getBorder());
			decorator.setBorderBottom(decorator.getBorder());
		}
	}

	/**
	 * Set the Header Cell Decorator.
	 * 
	 * @param decorator
	 */
	public void setHeaderCellDecorator(CellDecorator decorator) {
		cellDecoratorMap.put(CellStyleUtils.CELL_DECORATOR_HEADER, decorator);
	}

	/**
	 * Set the Numeric Cell Decorator.
	 * 
	 * @param decorator
	 */
	public void setNumericCellDecorator(CellDecorator decorator) {
		cellDecoratorMap.put(CellStyleUtils.CELL_DECORATOR_NUMERIC, decorator);
	}

	/**
	 * Set the Boolean Cell Decorator.
	 * 
	 * @param decorator
	 */
	public void setBooleanCellDecorator(CellDecorator decorator) {
		cellDecoratorMap.put(CellStyleUtils.CELL_DECORATOR_BOOLEAN, decorator);
	}

	/**
	 * Set the Date Cell Decorator.
	 * 
	 * @param decorator
	 */
	public void setDateCellDecorator(CellDecorator decorator) {
		cellDecoratorMap.put(CellStyleUtils.CELL_DECORATOR_DATE, decorator);
	}

	/**
	 * Add a new Cell Decorator for a specific use case.
	 * 
	 * @param decoratorName
	 *            the decorator name
	 * @param decorator
	 *            the cell decorator
	 */
	public void addSpecificCellDecorator(String decoratorName, CellDecorator decorator) {
		cellDecoratorMap.put(decoratorName, decorator);
	}

	/**
	 * Add the main xls configuration.
	 * 
	 * @param config
	 * @return
	 */
	private ConfigurationData initializeConfiguration(XlsConfiguration config) {
		if (configData == null) {
			configData = new ConfigurationData();
		}
		configData.setName(config.nameFile());
		configData.setNameFile(config.nameFile() + config.extensionFile().getExtension());
		configData.setExtensionFile(config.extensionFile());
		return configData;
	}

	/**
	 * Add the sheet configuration.
	 * 
	 * @param config
	 * @return
	 */
	private ConfigurationData initializeSheetConfiguration(XlsSheet config) {
		if (configData == null) {
			configData = new ConfigurationData();
		}
		configData.setTitleSheet(config.title());
		configData.setPropagationType(config.propagation());
		configData.setStartRow(config.startRow());
		configData.setStartCell(config.startCell());
		return configData;
	}

	/**
	 * Initialize Workbook.
	 * 
	 * @param type
	 *            the type of workbook
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
	 * Initialize Workbook.
	 * 
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
	 * Initialize Sheet.
	 * 
	 * @param wb
	 * @param sheetName
	 * @return the {@link Sheet}.
	 */
	private Sheet initializeSheet(Workbook wb, String sheetName) {
		return wb.createSheet(sheetName);
	}

	/**
	 * Apply merge region if necessary.
	 * 
	 * @param s
	 *            the sheet
	 * @param r
	 *            the row
	 * @param idxR
	 *            the position of the row
	 * @param idxC
	 *            the position of the cell
	 * @param f
	 *            the field
	 * @param isPH
	 *            true if propagation horizontally, false if propagation
	 *            vertically
	 */
	private void applyMergeRegion(Sheet s, Row r, int idxR, int idxC, Field f, boolean isPH) throws Exception {
		// Process @XlsMasterHeader
		if (f.isAnnotationPresent(XlsMasterHeader.class)) {
			XlsMasterHeader annotation = (XlsMasterHeader) f.getAnnotation(XlsMasterHeader.class);
			// if row null is necessary to create it
			if (r == null) {
				r = initializeRow(s, idxR);
			}

			// validation of configuration
			isValidMasterHeaderConfiguration(isPH, annotation);

			// prepare position rows / cells
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

			// initialize master header cell
			initializeHeaderCell(r, startCell, annotation.title());

			// merge region of the master header cell
			s.addMergedRegion(new CellRangeAddress(startRow, endRow, startCell, endCell));
		}
	}

	/**
	 * Validate if the master header configuration is valid.
	 * 
	 * @param isPH
	 *            true if propagation is HORIZONTAL otherwise false to
	 *            propagation VERTICAL
	 * @param annotation
	 *            the {@link XlsMasterHeader} annotation
	 * @throws JAEXConfigurationException
	 */
	private void isValidMasterHeaderConfiguration(boolean isPH, XlsMasterHeader annotation)
			throws JAEXConfigurationException {

		if (isPH && annotation.startX() == annotation.endX()) {
			throw new JAEXConfigurationException(
					JAEXExceptionMessage.JAEXConfigurationException_Incompatible.getMessage());

		} else if (!isPH && annotation.startY() == annotation.endY()) {
			throw new JAEXConfigurationException(
					JAEXExceptionMessage.JAEXConfigurationException_Incompatible.getMessage());
		}
	}

	/**
	 * Initialize an row.
	 * 
	 * @param s
	 *            sheet to add the row
	 * @param idxR
	 *            position of the new row
	 * @return the row created
	 */
	private Row initializeRow(Sheet s, int idxR) {
		return s.createRow(idxR);
	}

	/**
	 * Initialize the header cell.
	 * 
	 * @param r
	 *            row to add the cell
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
	 * @param s
	 *            the sheet
	 * @param headerRow
	 *            the header row
	 * @param contentRow
	 *            the content row
	 * @param idxR
	 *            the position of the row
	 * @param idxC
	 *            the position of the cell
	 * @param cL
	 *            the cascade level
	 * @param o
	 *            the object
	 * @param f
	 *            the field
	 * @param d
	 *            the decorator mask
	 * @return in case of the object return the number of cell created,
	 *         otherwise 0
	 * @throws Exception
	 */
	private int initializeCellByField(Sheet s, Row headerRow, Row contentRow, int idxR, int idxC, int cL, Object o,
			Field f, XlsElement xlsAnnotation) throws Exception {

		// make the field accessible to recover the value
		f.setAccessible(true);

		int counter = 0;

		Class<?> fT = f.getType();

		boolean isAppliedToBaseObject = applyBaseObject(o, fT, f, contentRow, idxC, xlsAnnotation);

		if (!isAppliedToBaseObject && !fT.isPrimitive()) {
			Object nO = f.get(o);
			// manage null objects
			if (nO == null) {
				nO = fT.newInstance();
			}
			Class<?> oC = nO.getClass();

			counter = marshalAsPropagationHorizontal(nO, oC, s, headerRow, contentRow, idxR, idxC - 1, cL + 1);
		}
		return counter;
	}

	/**
	 * Initialize an cell according the type of field and the PropagationType is
	 * PROPAGATION_VERTICAL.
	 * 
	 * @param s
	 *            the sheet
	 * @param r
	 *            the content row
	 * @param idxR
	 *            the position of the row
	 * @param idxC
	 *            the position of the cell
	 * @param cL
	 *            the cascade level
	 * @param o
	 *            the object
	 * @param f
	 *            the field
	 * @param d
	 *            the decorator mask
	 * @return
	 * @throws Exception
	 */
	private int initializeCellByField(Sheet s, Row r, int idxR, int idxC, int cL, Object o, Field f,
			XlsElement xlsAnnotation) throws Exception {

		// make the field accessible to recover the value
		f.setAccessible(true);

		int counter = 0;

		Class<?> fT = f.getType();

		boolean isAppliedToBaseObject = applyBaseObject(o, fT, f, r, idxC, xlsAnnotation);

		if (!isAppliedToBaseObject && !fT.isPrimitive()) {
			Object nO = f.get(o);
			// manage null objects
			if (nO == null) {
				nO = fT.newInstance();
			}
			Class<?> oC = nO.getClass();

			counter = marshalAsPropagationVertical(nO, oC, s, idxR - 1, idxC - 1, cL + 1);
		}
		return counter;
	}

	/**
	 * 
	 * @param o
	 *            the object
	 * @param fT
	 *            the field type
	 * @param f
	 *            the field
	 * @param r
	 *            the content row
	 * @param idxC
	 *            the position of the cell
	 * @param element
	 *            the {@link XlsElement} annotation
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws JAEXConverterException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 */
	private boolean applyBaseObject(Object o, Class<?> fT, Field f, Row r, int idxC, XlsElement element)
			throws IllegalArgumentException, IllegalAccessException, JAEXConverterException, NoSuchMethodException,
			SecurityException, InvocationTargetException {
		boolean isUpdated = false;

		// reading mask
		String tM = element.transformMask();
		String fM = element.formatMask();

		switch (fT.getName()) {

		case CellValueUtils.OBJECT_DATE:

			Cell cDate = r.createCell(idxC);

			isUpdated = CellValueUtils.toDate(o, f, wb, cDate,
					(StringUtils.isNotBlank(element.decorator()) ? stylesMap.get(element.decorator())
							: stylesMap.get(CellStyleUtils.CELL_DECORATOR_DATE)),
					element.formatMask(), element.transformMask(), element.comment(), configData.getExtensionFile());

			break;

		case CellValueUtils.OBJECT_STRING:
			Cell cString = r.createCell(idxC);

			isUpdated = CellValueUtils.toString(o, f, wb, cString,
					(StringUtils.isNotBlank(element.decorator()) ? stylesMap.get(element.decorator()) : null),
					element.comment(), configData.getExtensionFile());

			break;

		case CellValueUtils.OBJECT_INTEGER:
		case CellValueUtils.PRIMITIVE_INTEGER:
			Cell cInteger = r.createCell(idxC);

			isUpdated = CellValueUtils.toInteger(o, f, wb, cInteger,
					(StringUtils.isNotBlank(element.decorator()) ? stylesMap.get(element.decorator())
							: stylesMap.get(CellStyleUtils.CELL_DECORATOR_NUMERIC)),
					(StringUtils.isEmpty(tM) ? (StringUtils.isEmpty(fM) ? CellStyleUtils.MASK_DECORATOR_INTEGER : fM)
							: tM),
					element, configData.getExtensionFile());

			break;

		case CellValueUtils.OBJECT_LONG:
		case CellValueUtils.PRIMITIVE_LONG:
			Cell cLong = r.createCell(idxC);

			isUpdated = CellValueUtils.toLong(o, f, wb, cLong,
					(StringUtils.isNotBlank(element.decorator()) ? stylesMap.get(element.decorator())
							: stylesMap.get(CellStyleUtils.CELL_DECORATOR_NUMERIC)),
					(StringUtils.isEmpty(tM) ? (StringUtils.isEmpty(fM) ? CellStyleUtils.MASK_DECORATOR_INTEGER : fM)
							: tM),
					element, configData.getExtensionFile());

			break;

		case CellValueUtils.OBJECT_DOUBLE:
		case CellValueUtils.PRIMITIVE_DOUBLE:

			Cell cDouble = r.createCell(idxC);

			isUpdated = CellValueUtils.toDouble(o, f, wb, cDouble,
					(StringUtils.isNotBlank(element.decorator()) ? stylesMap.get(element.decorator())
							: stylesMap.get(CellStyleUtils.CELL_DECORATOR_NUMERIC)),
					StringUtils.isNotBlank(element.formatMask()) ? element.formatMask()
							: CellStyleUtils.MASK_DECORATOR_DOUBLE,
					element.transformMask(), element, configData.getExtensionFile());

			break;

		case CellValueUtils.OBJECT_BIGDECIMAL:
			Cell cBigDecimal = r.createCell(idxC);

			isUpdated = CellValueUtils.toBigDecimal(o, f, wb, cBigDecimal,
					(StringUtils.isNotBlank(element.decorator()) ? stylesMap.get(element.decorator())
							: stylesMap.get(CellStyleUtils.CELL_DECORATOR_NUMERIC)),
					StringUtils.isEmpty(element.formatMask()) ? CellStyleUtils.MASK_DECORATOR_DOUBLE
							: element.formatMask(),
					element.transformMask(), element, configData.getExtensionFile());
			break;

		case CellValueUtils.OBJECT_FLOAT:
		case CellValueUtils.PRIMITIVE_FLOAT:

			Cell cFloat = r.createCell(idxC);

			isUpdated = CellValueUtils.toFloat(o, f, wb, cFloat,
					(StringUtils.isNotBlank(element.decorator()) ? stylesMap.get(element.decorator())
							: stylesMap.get(CellStyleUtils.CELL_DECORATOR_NUMERIC)),
					(StringUtils.isEmpty(tM) ? (StringUtils.isEmpty(fM) ? CellStyleUtils.MASK_DECORATOR_DOUBLE : fM)
							: tM),
					element, configData.getExtensionFile());

			break;

		case CellValueUtils.OBJECT_BOOLEAN:
		case CellValueUtils.PRIMITIVE_BOOLEAN:
			Cell cBoolean = r.createCell(idxC);
			isUpdated = CellValueUtils.toBoolean(o, f, wb, cBoolean,
					(StringUtils.isNotBlank(element.decorator()) ? stylesMap.get(element.decorator())
							: stylesMap.get(CellStyleUtils.CELL_DECORATOR_BOOLEAN)),
					element.transformMask(), element.comment(), configData.getExtensionFile());
			break;

		default:
			isUpdated = false;
			break;
		}
		
		if(!isUpdated && fT.isEnum()){
			Cell cEnum = r.createCell(idxC);
			
			isUpdated = CellValueUtils.toEnum(o, f, wb, cEnum,
					(StringUtils.isNotBlank(element.decorator()) ? stylesMap.get(element.decorator())
							: stylesMap.get(CellStyleUtils.CELL_DECORATOR_ENUM)),
					element.comment(), configData.getExtensionFile());
		}

		return isUpdated;
	}

	/**
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
	 * @throws JAEXConverterException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private boolean applyBaseExcelObject(Object o, Class<?> fT, Field f, Cell c, XlsElement xlsAnnotation)
			throws IllegalArgumentException, IllegalAccessException, JAEXConverterException {
		boolean isUpdated = false;

		f.setAccessible(true);

		switch (fT.getName()) {

		case CellValueUtils.OBJECT_DATE:

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
						// if date decorator do not match with a valid mask
						// launch exception
						throw new JAEXConverterException(JAEXExceptionMessage.JAEXConverterException_Date.getMessage(),
								e);
					}
				}
			}

			isUpdated = true;
			break;

		case CellValueUtils.OBJECT_STRING:

			f.set(o, c.getStringCellValue());

			isUpdated = true;
			break;

		case CellValueUtils.OBJECT_INTEGER:
		case CellValueUtils.PRIMITIVE_INTEGER:

			int intValue = ((Double) c.getNumericCellValue()).intValue();
			f.set(o, intValue);

			isUpdated = true;
			break;

		case CellValueUtils.OBJECT_LONG:
		case CellValueUtils.PRIMITIVE_LONG:

			Long longValue = ((Double) c.getNumericCellValue()).longValue();
			f.set(o, longValue);

			isUpdated = true;
			break;

		case CellValueUtils.OBJECT_DOUBLE:
		case CellValueUtils.PRIMITIVE_DOUBLE:

			if (StringUtils.isNotBlank(xlsAnnotation.transformMask())) {
				f.set(o, Double.parseDouble(c.getStringCellValue().replace(",", ".")));
			} else {
				f.set(o, ((Double) c.getNumericCellValue()).doubleValue());
			}

			isUpdated = true;
			break;

		case CellValueUtils.OBJECT_BIGDECIMAL:

			if (StringUtils.isNotBlank(xlsAnnotation.transformMask())) {
				f.set(o, BigDecimal.valueOf(Double.parseDouble(c.getStringCellValue().replace(",", "."))));
			} else {
				f.set(o, BigDecimal.valueOf(c.getNumericCellValue()));
			}

			isUpdated = true;
			break;

		case CellValueUtils.OBJECT_FLOAT:
		case CellValueUtils.PRIMITIVE_FLOAT:

			Float floatValue = ((Double) c.getNumericCellValue()).floatValue();
			f.set(o, floatValue);

			isUpdated = true;
			break;

		case CellValueUtils.OBJECT_BOOLEAN:
		case CellValueUtils.PRIMITIVE_BOOLEAN:

			String bool = c.getStringCellValue();

			if (StringUtils.isNotBlank(xlsAnnotation.transformMask())) {
				// apply format mask defined at transform mask
				String[] split = xlsAnnotation.transformMask().split("/");
				f.set(o, StringUtils.isNotBlank(bool) ? (split[0].equals(bool) ? true : false) : null);

			} else {
				// locale mode
				f.set(o, StringUtils.isNotBlank(bool) ? Boolean.valueOf(bool) : null);
			}

			isUpdated = true;
			break;

		default:
			isUpdated = false;
			break;
		}

		if(!isUpdated && fT.isEnum()){
			f.set(o, Enum.valueOf((Class<Enum>) fT, c.getStringCellValue()));
			isUpdated = true;
		}
		
		return isUpdated;
	}

	/**
	 * Convert the object to file with the PropagationType as
	 * PROPAGATION_HORIZONTAL.
	 * 
	 * @param o
	 *            the object
	 * @param oC
	 *            the object class
	 * @param s
	 *            the sheet
	 * @param headerRow
	 *            the row related to the header
	 * @param contentRow
	 *            the row related to the content value
	 * @param idxR
	 *            the position of the row
	 * @param idxC
	 *            the position of the cell
	 * @param cL
	 *            the cascade level
	 * @return
	 * @throws IllegalAccessException
	 */
	private int marshalAsPropagationHorizontal(Object o, Class<?> oC, Sheet s, Row headerRow, Row contentRow, int idxR,
			int idxC, int cL) throws Exception {
		// counter related to the number of fields (if new object)
		int counter = -1;

		// get declared fields
		List<Field> fL = Arrays.asList(oC.getDeclaredFields());
		for (Field f : fL) {
			// process each field from the object

			if (headerRow != null) {
				// calculate index of the cell
				int tmpIdxRow = idxR - 3;
				// apply merge region
				applyMergeRegion(s, null, tmpIdxRow, idxC, f, true);
			}
			// Process @XlsElement
			if (f.isAnnotationPresent(XlsElement.class)) {
				XlsElement xlsAnnotation = (XlsElement) f.getAnnotation(XlsElement.class);
				// increment of the counter related to the number of fields (if
				// new object)
				counter++;
				if (headerRow != null) {
					// header
					initializeHeaderCell(headerRow, idxC + xlsAnnotation.position(), xlsAnnotation.title());
				}
				// content
				idxC += initializeCellByField(s, headerRow, contentRow, idxR, idxC + xlsAnnotation.position(), cL, o, f,
						xlsAnnotation);
			}
		}
		return counter;
	}

	/**
	 * Convert the object to file with the PropagationType as
	 * PROPAGATION_VERTICAL.
	 * 
	 * @param o
	 *            the object
	 * @param oC
	 *            the object class
	 * @param s
	 *            the sheet
	 * @param idxR
	 *            the position of the row
	 * @param idxC
	 *            the position of the cell
	 * @param cL
	 *            the cascade level
	 * @return
	 * @throws Exception
	 */
	private int marshalAsPropagationVertical(Object o, Class<?> oC, Sheet s, int idxR, int idxC, int cL)
			throws Exception {
		// counter related to the number of fields (if new object)
		int counter = -1;
		// backup base index of the cell
		int baseIdxCell = idxC;

		// get declared fields
		List<Field> fieldList = Arrays.asList(oC.getDeclaredFields());
		for (Field field : fieldList) {
			// process each field from the object

			// restart the index of the cell
			idxC = baseIdxCell;

			// Process @XlsElement
			if (field.isAnnotationPresent(XlsElement.class)) {
				XlsElement xlsAnnotation = (XlsElement) field.getAnnotation(XlsElement.class);
				// increment of the counter related to the number of fields (if
				// new object)
				counter++;
				// create the row
				Row row = null;
				if (idxR == 1 && baseIdxCell == 1) {
					row = initializeRow(s, idxR + xlsAnnotation.position());
				} else {
					row = s.getRow(idxR + xlsAnnotation.position());
				}

				// calculate index of the cell
				int tmpIdxCell = idxC - 1;
				if (tmpIdxCell == 0) {
					// apply merge region
					applyMergeRegion(s, row, idxR, tmpIdxCell, field, false);

					// header
					initializeHeaderCell(row, idxC, xlsAnnotation.title());
				}
				// increment the cell position
				idxC++;
				// content
				idxR += initializeCellByField(s, row, idxR + xlsAnnotation.position(), idxC, cL, o, field,
						xlsAnnotation);
			}
		}
		return counter;
	}

	/**
	 * 
	 * Convert the file to object with the PropagationType as
	 * PROPAGATION_HORIZONTAL.
	 * 
	 * @param o
	 *            the object
	 * @param oC
	 *            the object class
	 * @param s
	 *            the sheet
	 * @param idxR
	 *            the position of the row
	 * @param idxC
	 *            the position of the cell
	 * @return
	 * @throws IllegalAccessException
	 * @throws JAEXConverterException
	 * @throws InstantiationException
	 */
	private int unmarshalAsPropagationHorizontal(Object o, Class<?> oC, Sheet s, int idxR, int idxC)
			throws IllegalAccessException, JAEXConverterException, InstantiationException {
		// counter related to the number of fields (if new object)
		int counter = -1;

		// get declared fields
		List<Field> fL = Arrays.asList(oC.getDeclaredFields());
		for (Field f : fL) {
			// process each field from the object

			Class<?> fT = f.getType();

			// Process @XlsElement
			if (f.isAnnotationPresent(XlsElement.class)) {
				XlsElement xlsAnnotation = (XlsElement) f.getAnnotation(XlsElement.class);
				// increment of the counter related to the number of fields (if
				// new object)
				counter++;

				// content row
				Row contentRow = s.getRow(idxR + 1);
				Cell contentCell = contentRow.getCell(idxC + xlsAnnotation.position());

				boolean isAppliedToBaseObject = applyBaseExcelObject(o, fT, f, contentCell, xlsAnnotation);

				if (!isAppliedToBaseObject && !fT.isPrimitive()) {

					Object subObjbect = fT.newInstance();
					Class<?> subObjbectClass = subObjbect.getClass();

					int internalCellCounter = unmarshalAsPropagationHorizontal(subObjbect, subObjbectClass, s, idxR,
							idxC + xlsAnnotation.position() - 1);

					// add the sub object to the parent object
					f.set(o, subObjbect);

					// update the index
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
	 * @param o
	 *            the object
	 * @param oC
	 *            the object class
	 * @param s
	 *            the sheet
	 * @param idxR
	 *            the position of the row
	 * @param idxC
	 *            the position of the cell
	 * @return
	 * @throws IllegalAccessException
	 * @throws JAEXConverterException
	 * @throws InstantiationException
	 */
	private int unmarshalAsPropagationVertical(Object object, Class<?> oC, Sheet s, int idxR, int idxC)
			throws IllegalAccessException, JAEXConverterException, InstantiationException {
		// counter related to the number of fields (if new object)
		int counter = -1;

		// get declared fields
		List<Field> fL = Arrays.asList(oC.getDeclaredFields());
		for (Field f : fL) {
			// process each field from the object

			Class<?> fT = f.getType();

			// Process @XlsElement
			if (f.isAnnotationPresent(XlsElement.class)) {
				XlsElement xlsAnnotation = (XlsElement) f.getAnnotation(XlsElement.class);
				// increment of the counter related to the number of fields (if
				// new object)
				counter++;

				// content row
				Row contentRow = s.getRow(idxR + xlsAnnotation.position());
				Cell contentCell = contentRow.getCell(idxC + 1);

				boolean isAppliedToBaseObject = applyBaseExcelObject(object, fT, f, contentCell, xlsAnnotation);

				if (!isAppliedToBaseObject && !fT.isPrimitive()) {

					Object subObjbect = fT.newInstance();
					Class<?> subObjbectClass = subObjbect.getClass();

					int internalCellCounter = unmarshalAsPropagationVertical(subObjbect, subObjbectClass, s,
							idxR + xlsAnnotation.position() - 1, idxC);

					// add the sub object to the parent object
					f.set(object, subObjbect);

					// update the index
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
	 *            the workbook
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
	 * marshal
	 * 
	 * @throws Exception
	 */
	public void marshal(Object object) throws Exception {
		Class<?> objectClass = object.getClass();
		ConfigurationData config = null;
		// Process @XlsConfiguration
		if (objectClass.isAnnotationPresent(XlsSheet.class)) {
			XlsConfiguration xlsAnnotation = (XlsConfiguration) objectClass.getAnnotation(XlsConfiguration.class);
			config = initializeConfiguration(xlsAnnotation);

		}
		// Process @XlsSheet
		if (objectClass.isAnnotationPresent(XlsSheet.class)) {
			XlsSheet xlsAnnotation = (XlsSheet) objectClass.getAnnotation(XlsSheet.class);
			config = initializeSheetConfiguration(xlsAnnotation);

		}

		// initialize Workbook
		wb = initializeWorkbook(config.getExtensionFile());

		// initialize style cell via annotations
		if (objectClass.isAnnotationPresent(XlsDecorators.class)) {
			XlsDecorators xlsDecorators = (XlsDecorators) objectClass.getAnnotation(XlsDecorators.class);
			for (XlsDecorator decorator : xlsDecorators.values()) {
				stylesMap.put(decorator.decoratorName(), initializeCellStyleByXlsDecorator(decorator));
			}
		}
		// initialize style cell via default option
		initializeCellDecorator();

		// initialize Sheet
		// FIXME add loop if necessary
		Sheet s = initializeSheet(wb, config.getTitleSheet());

		// initialize Row & Cell
		int idxRow = config.getStartRow();
		int idxCell = config.getStartCell();

		// initialize rows according the PropagationType
		Row headerRow, contentRow;
		if (PropagationType.PROPAGATION_HORIZONTAL.equals(config.getPropagationType())) {
			headerRow = initializeRow(s, idxRow++);
			contentRow = initializeRow(s, idxRow++);

			marshalAsPropagationHorizontal(object, objectClass, s, headerRow, contentRow, idxRow, idxCell, 0);
		} else {
			marshalAsPropagationVertical(object, objectClass, s, idxRow, idxCell, 0);

		}
		// FIXME manage return value
		workbookFileOutputStream(wb, "D:\\projects\\" + config.getNameFile());
	}

	public void marshal(Object... objects) {
		// TODO
	}

	public void marshalAsCollection(Collection<?> collection, final String filename,
			final ExtensionFileType extensionFileType) throws Exception {

		// Temos que iniciar o config data neste ponto porque
		// como estamos na escritura de uma coleccao
		// temos que ter o nome e a extensao antes de iniciar o excel
		configData = new ConfigurationData();
		configData.setExtensionFile(extensionFileType);
		configData.setNameFile(filename);
		ConfigurationData config = configData;

		// initialize Workbook
		wb = initializeWorkbook(config.getExtensionFile());

		// initialize style cell via default option
		initializeCellDecorator();
		
		Row headerRow = null, contentRow = null;
		Sheet s = null;
		int idxRow = 0, counter = 0, idxCell = 0;

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
				if (wb.getNumberOfSheets() == 0 || wb.getSheet(config.getTitleSheet()) == null) {
					s = initializeSheet(wb, config.getTitleSheet());
					idxRow = config.getStartRow();
					headerRow = initializeRow(s, idxRow++);
					contentRow = initializeRow(s, idxRow++);
				} else {
					idxRow = s.getLastRowNum() + 1;
					headerRow = null;
					contentRow = initializeRow(s, idxRow++);

				}
				counter = marshalAsPropagationHorizontal(object, objectClass, s, headerRow, contentRow, idxRow, idxCell,
						0);
			} else {
				idxRow = config.getStartRow();
				if (wb.getNumberOfSheets() == 0 || wb.getSheet(config.getTitleSheet()) == null) {
					s = initializeSheet(wb, config.getTitleSheet());
					idxCell = config.getStartCell();
				} else {
					idxCell = counter + idxCell - 1;
				}
				counter = marshalAsPropagationVertical(object, objectClass, s, idxRow, idxCell, 0);

			}

		}

		// FIXME manage return value
		workbookFileOutputStream(wb,
				"D:\\projects\\" + config.getNameFile() + config.getExtensionFile().getExtension());
	}

	public Object unmarshal(Object object) throws IOException, IllegalArgumentException, IllegalAccessException,
			JAEXConverterException, InstantiationException {
		// instance object class
		Class<?> oC = object.getClass();
		ConfigurationData config = null;

		// Process @XlsConfiguration
		if (oC.isAnnotationPresent(XlsConfiguration.class)) {
			XlsConfiguration xlsAnnotation = (XlsConfiguration) oC.getAnnotation(XlsConfiguration.class);
			config = initializeConfiguration(xlsAnnotation);
		}

		// Process @XlsSheet
		if (oC.isAnnotationPresent(XlsSheet.class)) {
			XlsSheet xlsAnnotation = (XlsSheet) oC.getAnnotation(XlsSheet.class);
			config = initializeSheetConfiguration(xlsAnnotation);
		}

		FileInputStream input = new FileInputStream("D:\\projects\\" + config.getNameFile());
		Workbook wb = initializeWorkbook(input, config.getExtensionFile());
		Sheet s = wb.getSheet(config.getTitleSheet());

		// initialize index row & cell
		int idxRow = config.getStartRow();
		int idxCell = config.getStartCell();

		if (PropagationType.PROPAGATION_HORIZONTAL.equals(config.getPropagationType())) {
			unmarshalAsPropagationHorizontal(object, oC, s, idxRow, idxCell);
		} else {
			unmarshalAsPropagationVertical(object, oC, s, idxRow, idxCell);
		}

		return object;
	}

	public static <T> Collection<T> unmarshalToCollection(Object object) {
		return Collections.emptyList();
	}
}
