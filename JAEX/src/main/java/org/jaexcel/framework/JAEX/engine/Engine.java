package org.jaexcel.framework.JAEX.engine;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jaexcel.framework.JAEX.annotation.XlsConfiguration;
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
	// cell decorator constants
	private static final String CELL_DECORATOR_DATE = "date";
	private static final String CELL_DECORATOR_BOOLEAN = "boolean";
	private static final String CELL_DECORATOR_NUMERIC = "numeric";
	private static final String CELL_DECORATOR_HEADER = "header";
	// default mask decorators
	private static final String MASK_DECORATOR_DATE = "yyyy-MM-dd";
	private static final String MASK_DECORATOR_INTEGER = "0";
	private static final String MASK_DECORATOR_DOUBLE = "0.00";

	// TODO manage the decorator configuration

	// TODO see the behavior of using only one instance of the JAEX object
	// inside one project

	Workbook wb;
	ConfigurationData configData;
	CellDecorator headerDecorator;
	Map<String, CellStyle> stylesMap = new HashMap<String, CellStyle>();

	public Engine() {
		// initializeDefaultCellDecorator();
	}
	
	
	
	/**
	 * Initialize default Header Cell Decorator.
	 * 
	 * @return the {@link CellStyle} header decorator
	 */
	private CellStyle initializeHeaderCellDecorator() {
		CellStyle cs = initializeCellStyle(wb);

		// add the alignment to the cell
		CellStyleUtils.applyAlignment(cs, CellStyle.ALIGN_CENTER, CellStyle.VERTICAL_CENTER);
		
		// add the border to the cell
		CellStyleUtils.applyBorder(cs, CellStyle.BORDER_THIN, CellStyle.BORDER_THIN, CellStyle.BORDER_THIN,
				CellStyle.BORDER_THIN);
		
		// add the background to the cell
		// FIXME review setFillBackgroundColor
		//cs.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);
		cs.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		cs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		
		// add the wrap mode to the cell
		cs.setWrapText(true);
		
		// add the font style to the cell
		CellStyleUtils.applyFont(wb, cs, "Arial", (short) 10, true, true);

		return cs;
	}

	/**
	 * Initialize default Numeric Cell Decorator.
	 * 
	 * @return the {@link CellStyle} numeric decorator
	 */
	private CellStyle initializeNumericCellDecorator() {
		CellStyle cs = initializeCellStyle(wb);

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
		CellStyle cs = initializeCellStyle(wb);

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
		CellStyle cs = initializeCellStyle(wb);

		// add the alignment to the cell
		CellStyleUtils.applyAlignment(cs, CellStyle.ALIGN_CENTER, (short) 0);

		return cs;
	}

	/**
	 * Initialize default Cell Decorator system.
	 */
	private void initializeDefaultCellDecorator() {
		stylesMap.put(CELL_DECORATOR_HEADER, initializeHeaderCellDecorator());
		stylesMap.put(CELL_DECORATOR_NUMERIC, initializeNumericCellDecorator());
		stylesMap.put(CELL_DECORATOR_DATE, initializeDateCellDecorator());
		stylesMap.put(CELL_DECORATOR_BOOLEAN, initializeBooleanCellDecorator());
	}

	/**
	 * Initialize {@link CellStyle} by  Cell Decorator.
	 * 
	 * @param decorator
	 * @return the {@link CellStyle} decorator
	 */
	private CellStyle initializeCellStyleByCellDecorator(CellDecorator decorator) {
		CellStyle cs = initializeCellStyle(wb);

		// add the alignment to the cell
		CellStyleUtils.applyAlignment(cs, decorator.getAlignment(), decorator.getVerticalAlignment());

		// add the border to the cell
		borderPropagationManagement(decorator);
		CellStyleUtils.applyBorder(cs, decorator.getBorderLeft(), decorator.getBorderRight(), decorator.getBorderTop(),
				decorator.getBorderBottom());

		// add the background to the cell
		// FIXME review setFillBackgroundColor
		//cs.setFillBackgroundColor(decorator.getBackgroundColor());
		cs.setFillForegroundColor(decorator.getForegroundColor());
		cs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		// add the wrap mode to the cell
		cs.setWrapText(decorator.isWrapText());

		// add the font style to the cell
		CellStyleUtils.applyFont(wb, cs, decorator.getFontName(), decorator.getFontSize(), decorator.isFontBold(),
				decorator.isFontItalic());

		return cs;
	}

	/**
	 * if specific border not configured, propagate generic border configuration
	 * to specific border.
	 * 
	 * @param decorator the cell decorator
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
		stylesMap.put(CELL_DECORATOR_HEADER, initializeCellStyleByCellDecorator(decorator));
	}

	/**
	 * Set the Numeric Cell Decorator.
	 * 
	 * @param decorator
	 */
	public void setNumericCellDecorator(CellDecorator decorator) {
		stylesMap.put(CELL_DECORATOR_NUMERIC, initializeCellStyleByCellDecorator(decorator));
	}

	/**
	 * Set the Boolean Cell Decorator.
	 * 
	 * @param decorator
	 */
	public void setBooleanCellDecorator(CellDecorator decorator) {
		stylesMap.put(CELL_DECORATOR_BOOLEAN, initializeCellStyleByCellDecorator(decorator));
	}

	/**
	 * Set the Date Cell Decorator.
	 * 
	 * @param decorator
	 */
	public void setDateCellDecorator(CellDecorator decorator) {
		stylesMap.put(CELL_DECORATOR_DATE, initializeCellStyleByCellDecorator(decorator));
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
		stylesMap.put(decoratorName, initializeCellStyleByCellDecorator(decorator));
	}

	public void initializeHeaderDecoratorOld(CellDecorator configuration) {
		headerDecorator = configuration;

		// if specific border not configured
		if (headerDecorator.getBorder() != 0 && headerDecorator.getBorderLeft() == 0
				&& headerDecorator.getBorderRight() == 0 && headerDecorator.getBorderTop() == 0
				&& headerDecorator.getBorderBottom() == 0) {
			// propagate generic border configuration to specific border
			headerDecorator.setBorderLeft(headerDecorator.getBorder());
			headerDecorator.setBorderRight(headerDecorator.getBorder());
			headerDecorator.setBorderTop(headerDecorator.getBorder());
			headerDecorator.setBorderBottom(headerDecorator.getBorder());
		}
	}

	private CellStyle initializeHeaderCellDecoratorOld() throws JAEXConfigurationException {
		CellStyle cs = initializeCellStyle(wb);
		try {
			// add the alignment to the cell
			CellStyleUtils.applyAlignment(cs, headerDecorator.getAlignment(), headerDecorator.getVerticalAlignment());
			
			// add the border to the cell
			CellStyleUtils.applyBorder(cs, headerDecorator.getBorderLeft(), headerDecorator.getBorderRight(),
					headerDecorator.getBorderTop(), headerDecorator.getBorderBottom());
			
			// add the background to the cell
			// FIXME review setFillBackgroundColor
			//cs.setFillBackgroundColor(headerDecorator.getBackgroundColor());
			cs.setFillForegroundColor(headerDecorator.getBackgroundColor());
			cs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			
			// add the wrap mode to the cell
			cs.setWrapText(headerDecorator.isWrapText());
			
			// add the font style to the cell
			CellStyleUtils.applyFont(wb, cs, "Arial", (short) 10, headerDecorator.isFontBold(),
					headerDecorator.isFontItalic());

		} catch (Exception e) {
			throw new JAEXConfigurationException(JAEXExceptionMessage.JAEXConfigurationException_Missing.getMessage(),
					e);
		}
		return cs;
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
		if (ExtensionFileType.XLS.getExtension().equals(type.getExtension())) {
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
		if (ExtensionFileType.XLS.getExtension().equals(type.getExtension())) {
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
	 * Initialize cell style.
	 * 
	 * @param wb
	 *            the workbook
	 * @return the {@link CellStyle}.
	 */
	private CellStyle initializeCellStyle(Workbook wb) {
		return wb.createCellStyle();
	}

	/**
	 * Initialize data format.
	 * 
	 * @param wb
	 *            the workbook
	 * @return the {@link DataFormat}.
	 */
	private DataFormat initializeDataFormat(Workbook wb) {
		return wb.createDataFormat();
	}

	private void applyCellStyle(Workbook wb, Cell c, String formatMask) {
		CellStyle cs = initializeCellStyle(wb);
		DataFormat df = initializeDataFormat(wb);
		cs.setDataFormat(df.getFormat(formatMask));
		c.setCellStyle(cs);
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
			initializeCell(r, startCell, annotation.title());

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
	 * Initialize the basic cell.
	 * 
	 * @param r
	 *            row to add the cell
	 * @param idxC
	 *            position of the new cell
	 * @param value
	 *            the value of the cell content
	 * @return the cell created
	 */
	private Cell initializeCell(Row r, int idxC, String value) throws Exception {
		Cell c = r.createCell(idxC);
		c.setCellValue(value);

		// CellStyle cs = initializeHeaderCellDecoratorOld();
		// c.setCellStyle(cs);
		c.setCellStyle(stylesMap.get(CELL_DECORATOR_HEADER));
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
			Class<?> oC = nO.getClass();

			// FIXME manage null objects

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
			Class<?> oC = nO.getClass();

			// FIXME manage null objects

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
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws JAEXConverterException
	 */
	private boolean applyBaseObject(Object o, Class<?> fT, Field f, Row r, int idxC, XlsElement xlsAnnotation)
			throws IllegalArgumentException, IllegalAccessException, JAEXConverterException {
		boolean isUpdated = false;
		// FIXME add all primitive type here

		if (fT.equals(String.class)) {
			Cell c = r.createCell(idxC);
			c.setCellValue((String) f.get(o));
			isUpdated = true;

		} else if (fT.equals(Integer.class) || fT.isPrimitive() && fT.toString().equals("int")) {
			Cell c = r.createCell(idxC);
			c.setCellValue((Integer) f.get(o));
			String tM = xlsAnnotation.transformMask();
			String fM = xlsAnnotation.formatMask();
			applyCellStyle(wb, c,
					(StringUtils.isEmpty(tM) ? (StringUtils.isEmpty(fM) ? MASK_DECORATOR_INTEGER : fM) : tM));
			isUpdated = true;

		} else if (fT.equals(BigDecimal.class)) {
			Cell c = r.createCell(idxC);

			BigDecimal bd = (BigDecimal) f.get(o);

			// FIXME use the comment below to manage decimalScale
			// bd.setScale(2, BigDecimal.ROUND_HALF_UP);

			Double d = bd.doubleValue();
			if (StringUtils.isNotBlank(xlsAnnotation.transformMask())) {
				DecimalFormat df = new DecimalFormat(xlsAnnotation.transformMask());
				c.setCellValue(df.format(d));
			} else {
				c.setCellValue(d);
				applyCellStyle(wb, c, StringUtils.isEmpty(xlsAnnotation.formatMask()) ? MASK_DECORATOR_DOUBLE
						: xlsAnnotation.formatMask());
			}
			isUpdated = true;

		} else if (fT.equals(Double.class) || fT.isPrimitive() && fT.toString().equals("double")) {
			Cell c = r.createCell(idxC);

			// FIXME use the comment below to manage decimalScale
			// Double d = (Double) f.get(o);
			// BigDecimal bd = new BigDecimal(d);
			// bd.setScale(2, BigDecimal.ROUND_HALF_UP);

			if (StringUtils.isNotBlank(xlsAnnotation.transformMask())) {
				DecimalFormat df = new DecimalFormat(xlsAnnotation.transformMask());
				c.setCellValue(df.format((Double) f.get(o)));
			} else {
				c.setCellValue((Double) f.get(o));
				applyCellStyle(wb, c, StringUtils.isEmpty(xlsAnnotation.formatMask()) ? MASK_DECORATOR_DOUBLE
						: xlsAnnotation.formatMask());
			}
			isUpdated = true;

		} else if (fT.equals(Long.class) || fT.isPrimitive() && fT.toString().equals("long")) {
			Cell c = r.createCell(idxC);
			c.setCellValue((Long) f.get(o));
			isUpdated = true;

		} else if (fT.equals(Date.class)) {
			Cell c = r.createCell(idxC);
			Date d = (Date) f.get(o);
			if (d != null) {

				if (StringUtils.isNotBlank(xlsAnnotation.transformMask())) {
					// apply transformation mask
					String decorator = xlsAnnotation.transformMask();
					try {
						SimpleDateFormat dt = new SimpleDateFormat(decorator);

						String dateFormated = dt.format(d);
						if (dateFormated.equals(decorator)) {
							// if date decorator do not match with a valid mask
							// launch exception
							throw new JAEXConverterException(
									JAEXExceptionMessage.JAEXConverterException_Date.getMessage());
						}
						c.setCellValue(dateFormated);
					} catch (Exception e) {
						throw new JAEXConverterException(JAEXExceptionMessage.JAEXConverterException_Date.getMessage(),
								e);
					}

				} else if (StringUtils.isNotBlank(xlsAnnotation.formatMask())) {
					// apply format mask
					c.setCellValue(d);
					applyCellStyle(wb, c, xlsAnnotation.formatMask());

				} else {
					// apply default date mask
					c.setCellValue(d);
					applyCellStyle(wb, c, MASK_DECORATOR_DATE);

				}
			}
			isUpdated = true;

		} else if (fT.equals(Boolean.class) || fT.isPrimitive() && fT.toString().equals(CELL_DECORATOR_BOOLEAN)) {
			Cell c = r.createCell(idxC);
			Boolean b = (Boolean) f.get(o);
			if (StringUtils.isNotBlank(xlsAnnotation.transformMask())) {
				// apply format mask defined at transform mask
				String[] split = xlsAnnotation.transformMask().split("/");
				c.setCellValue((b == null ? "" : (b ? split[0] : split[1])));

			} else {
				// locale mode
				c.setCellValue((b == null ? "" : b).toString());
			}

			isUpdated = true;
		}
		// TODO manage Enum

		return isUpdated;
	}

	private boolean applyBaseExcelObject(Object o, Class<?> fT, Field f, Cell c, XlsElement xlsAnnotation)
			throws IllegalArgumentException, IllegalAccessException, JAEXConverterException {
		boolean isUpdated = false;
		// FIXME add all primitive type here

		f.setAccessible(true);

		if (fT.equals(String.class)) {
			f.set(o, c.getStringCellValue());
			isUpdated = true;

		} else if (fT.equals(Integer.class) || fT.isPrimitive() && fT.toString().equals("int")) {
			int intValue = ((Double) c.getNumericCellValue()).intValue();
			f.set(o, intValue);
			isUpdated = true;

		} else if (fT.equals(BigDecimal.class)) {
			if (StringUtils.isNotBlank(xlsAnnotation.transformMask())) {
				f.set(o, BigDecimal.valueOf(Double.parseDouble(c.getStringCellValue().replace(",", "."))));
			} else {
				f.set(o, BigDecimal.valueOf(c.getNumericCellValue()));
			}
			isUpdated = true;

		} else if (fT.equals(Double.class) || fT.isPrimitive() && fT.toString().equals("double")) {
			if (StringUtils.isNotBlank(xlsAnnotation.transformMask())) {
				f.set(o, Double.parseDouble(c.getStringCellValue().replace(",", ".")));
			} else {
				f.set(o, ((Double) c.getNumericCellValue()).doubleValue());
			}
			isUpdated = true;

		} else if (fT.equals(Long.class) || fT.isPrimitive() && fT.toString().equals("long")) {
			long longValue = ((Double) c.getNumericCellValue()).longValue();
			f.set(o, longValue);
			isUpdated = true;

		} else if (fT.equals(Date.class)) {
			if (StringUtils.isBlank(xlsAnnotation.transformMask())) {
				f.set(o, c.getDateCellValue());
			} else {
				String date = c.getStringCellValue();
				if (StringUtils.isNotBlank(date)) {

					// FIXME
					String tM = xlsAnnotation.transformMask();
					String fM = xlsAnnotation.formatMask();
					String decorator = StringUtils.isEmpty(tM) ? (StringUtils.isEmpty(fM) ? MASK_DECORATOR_DATE : fM)
							: tM;

					// SimpleDateFormat dt = new
					// SimpleDateFormat((StringUtils.isEmpty(decorator) ?
					// dateDecorator : decorator));
					SimpleDateFormat dt = new SimpleDateFormat(decorator);
					try {
						Date dateConverted = dt.parse(date);
						f.set(o, dateConverted);
					} catch (ParseException e) {// if date decorator do not
												// match
												// with a valid mask launch
												// exception
						throw new JAEXConverterException(JAEXExceptionMessage.JAEXConverterException_Date.getMessage(),
								e);
					}
				}
			}
			isUpdated = true;

		} else if (fT.equals(Boolean.class) || fT.isPrimitive() && fT.toString().equals(CELL_DECORATOR_BOOLEAN)) {
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
		}
		// TODO manage Enum
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

			// calculate index of the cell
			int tmpIdxRow = idxR - 3;
			// apply merge region
			applyMergeRegion(s, null, tmpIdxRow, idxC, f, true);

			// Process @XlsElement
			if (f.isAnnotationPresent(XlsElement.class)) {
				XlsElement xlsAnnotation = (XlsElement) f.getAnnotation(XlsElement.class);
				// increment of the counter related to the number of fields (if
				// new object)
				counter++;
				// header
				initializeCell(headerRow, idxC + xlsAnnotation.position(), xlsAnnotation.title());

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
				Row row = initializeRow(s, idxR + xlsAnnotation.position());

				// calculate index of the cell
				int tmpIdxCell = idxC - 1;
				// apply merge region
				applyMergeRegion(s, row, idxR, tmpIdxCell, field, false);

				// header
				initializeCell(row, idxC, xlsAnnotation.title());
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
	 */
	private int unmarshalAsPropagationHorizontal(Object o, Class<?> oC, Sheet s, int idxR, int idxC)
			throws IllegalAccessException, JAEXConverterException {
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

					Object subObjbect = f.get(o);
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
	 */
	private int unmarshalAsPropagationVertical(Object object, Class<?> oC, Sheet s, int idxR, int idxC)
			throws IllegalAccessException, JAEXConverterException {
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

					Object subObjbect = f.get(object);
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

		// FIXME possible change
		initializeDefaultCellDecorator();
		
		
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

	public void marshalAsCollection(Collection<?> collection) {
		// TODO
	}

	public Object unmarshal(Object object)
			throws IOException, IllegalArgumentException, IllegalAccessException, JAEXConverterException {
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
