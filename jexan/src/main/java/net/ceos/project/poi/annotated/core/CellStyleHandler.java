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

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.FontUnderline;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import net.ceos.project.poi.annotated.annotation.XlsDecorator;
import net.ceos.project.poi.annotated.definition.ExceptionMessage;
import net.ceos.project.poi.annotated.definition.ExtensionFileType;
import net.ceos.project.poi.annotated.exception.ConfigurationException;
import net.ceos.project.poi.annotated.exception.ElementException;
import net.ceos.project.poi.annotated.functional.interfaces.PoiConstructor;

/**
 * Manage the style to apply to the cell.
 * <p>
 * Available attributes :
 * <ul>
 * <li><i>font name</i>
 * <li><i>font size</i>
 * <li><i>font color</i>
 * <li><i>font italic</i>
 * <li><i>font bold</i>
 * <li><i>font underline</i>
 * <li><i>font alignment</i>
 * <li><i>font vertical alignment</i>
 * <li><i>cell wrap text</i>
 * <li><i>cell border</i>
 * <li><i>cell background color</i>
 * </ul>
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public class CellStyleHandler {
	// cell decorator constants
	public static final String CELL_DECORATOR_DATE = "date";
	public static final String CELL_DECORATOR_BOOLEAN = "boolean";
	public static final String CELL_DECORATOR_ENUM = "enum";
	public static final String CELL_DECORATOR_NUMERIC = "numeric";
	public static final String CELL_DECORATOR_HEADER = "header";
	public static final String CELL_DECORATOR_GENERIC = "generic";
	// default mask decorators
	public static final String MASK_DECORATOR_DATE = "yyyy-MM-dd";
	public static final String MASK_DECORATOR_INTEGER = "0";
	public static final String MASK_DECORATOR_DOUBLE = "0.00";
	// default XlsDecorator constants
	public static final String FONT_NAME = "Arial";
	public static final short FONT_SIZE = 10;
	public static final short FONT_COLOR = 0;
	public static final boolean FONT_BOLD = false;
	public static final boolean FONT_ITALIC = false;
	public static final short FONT_UNDERLINE = 0;
	public static final short FONT_ALIGNMENT = 0;
	public static final short FONT_VERTICAL_ALIGNMENT = 0;
	public static final short CELL_BORDER = 0;
	public static final short CELL_BACKGROUND_COLOR = 9;
	public static final short CELL_FOREGROUND_COLOR = 9;
	public static final boolean CELL_WRAP_TEXT = false;

	private static PoiConstructor<Workbook, Font> fontFactory = wb -> wb.createFont();
	private static PoiConstructor<Workbook, CellStyle> cellStyleFactory = wb -> wb.createCellStyle();
	private static PoiConstructor<Workbook, DataFormat> dataFormatFactory = wb -> wb.createDataFormat();

	private CellStyleHandler() {
		/* private constructor to hide the implicit public */
	}

	/**
	 * Apply the data format to the cell style.
	 * 
	 * @param wb
	 *            the {@link Workbook} in use
	 * @param cell
	 *            the {@link Cell} to use
	 * @param cs
	 *            the cell style
	 * @param formatMask the mask to apply
	 */
	protected static void applyDataFormat(final Workbook wb, final Cell cell, final CellStyle cs,
			final String formatMask) {
		DataFormat df = dataFormatFactory.newInstance(wb);
		cs.setDataFormat(df.getFormat(formatMask));
		cell.setCellStyle(cs);
	}

	/**
	 * Apply the font to the cell style with default values.
	 * 
	 * @param wb
	 *            the {@link Workbook} in use
	 * @param cs
	 *            the cell style
	 */
	protected static void applyFontDefault(Workbook wb, CellStyle cs) {
		applyFont(wb, cs, CellStyleHandler.FONT_NAME, CellStyleHandler.FONT_SIZE, CellStyleHandler.FONT_COLOR,
				CellStyleHandler.FONT_BOLD, CellStyleHandler.FONT_ITALIC, FontUnderline.NONE.getByteValue());
	}

	/**
	 * Apply the font to the cell style.
	 * 
	 * @param wb
	 *            the {@link Workbook} in use
	 * @param cs
	 *            the {@link CellStyle} in use
	 * @param name
	 *            the font name
	 * @param size
	 *            the font size
	 * @param c
	 *            the font color
	 * @param b
	 *            is bold format
	 * @param i
	 *            is italic format
	 * @param u
	 *            is underline format
	 */
	protected static void applyFont(final Workbook wb, final CellStyle cs, final String name, final short size,
			final short c, final boolean b, final boolean i, final byte u) {
		Font f = fontFactory.newInstance(wb);
		f.setFontName(name);
		f.setFontHeightInPoints(size);
		f.setColor(c);
		f.setBold(b);
		f.setItalic(i);
		f.setUnderline(u);
		cs.setFont(f);
	}

	/**
	 * Apply the horizontal alignment to the cell style.
	 * <p>
	 * By default the vertical alignment is 0.
	 * 
	 * @param cs
	 *            the cell style
	 * @param a
	 *            the horizontal alignment
	 */
	protected static void applyAlignment(final CellStyle cs, final short a) {
		applyAlignment(cs, a, (short) 0);
	}

	/**
	 * Apply the horizontal and vertical alignment to the cell style.
	 * 
	 * @param cs
	 *            the {@link CellStyle} to use
	 * @param a
	 *            the horizontal alignment
	 * @param vA
	 *            the vertical alignment
	 */
	protected static void applyAlignment(final CellStyle cs, final short a, final short vA) {
		if (a != 0) {
			cs.setAlignment(a);
		}
		if (vA != 0) {
			cs.setVerticalAlignment(vA);
		}
	}

	/**
	 * Apply the border to the cell style with default values.
	 * 
	 * @param cs
	 *            the {@link CellStyle} to apply
	 */
	protected static void applyBorderDefault(final CellStyle cs) {
		applyBorder(cs, CellStyle.BORDER_THIN, CellStyle.BORDER_THIN, CellStyle.BORDER_THIN, CellStyle.BORDER_THIN);
	}

	/**
	 * Apply the border to the cell style.
	 * 
	 * @param cs
	 *            the {@link CellStyle} to use
	 * @param bL
	 *            the cell border left
	 * @param bR
	 *            the cell border right
	 * @param bT
	 *            the cell border top
	 * @param bB
	 *            the cell border bottom
	 */
	protected static void applyBorder(final CellStyle cs, final short bL, final short bR, final short bT,
			final short bB) {
		cs.setBorderLeft(bL);
		cs.setBorderRight(bR);
		cs.setBorderTop(bT);
		cs.setBorderBottom(bB);
	}

	/**
	 * Apply the background color to the cell style.
	 * 
	 * @param cs
	 *            the {@link CellStyle} to use
	 * @param bC
	 *            the background color
	 */
	protected static void applyBackgroundColor(final CellStyle cs, final short bC) {
		cs.setFillBackgroundColor(bC);
	}

	/**
	 * Apply the cell comment to a cell.
	 * 
	 * @param configCriteria
	 *            the {@link XConfigCriteria} object
	 * @param isAuthorizedComment
	 *            the extension file
	 * @param cell
	 *            the {@link Cell}
	 */
	protected static void applyComment(final XConfigCriteria configCriteria, final Boolean isAuthorizedComment,
			final Cell cell) {
		if (StringUtils.isBlank(configCriteria.getElement().commentRules())
				|| StringUtils.isNotBlank(configCriteria.getElement().commentRules()) && isAuthorizedComment) {
			if (ExtensionFileType.XLS.equals(configCriteria.getExtension())) {
				final Map<Sheet, HSSFPatriarch> drawingPatriarches = new HashMap<>();

				CreationHelper createHelper = cell.getSheet().getWorkbook().getCreationHelper();
				HSSFSheet sheet = (HSSFSheet) cell.getSheet();
				HSSFPatriarch drawingPatriarch = drawingPatriarches.get(sheet);
				if (drawingPatriarch == null) {
					drawingPatriarch = sheet.createDrawingPatriarch();
					drawingPatriarches.put(sheet, drawingPatriarch);
				}

				Comment comment = drawingPatriarch
						.createComment(new HSSFClientAnchor(0, 0, 0, 0, (short) 4, 2, (short) 6, 5));
				comment.setString(createHelper.createRichTextString(configCriteria.getElement().comment()));

				cell.setCellComment(comment);

			} else if (ExtensionFileType.XLSX.equals(configCriteria.getExtension())) {
				CreationHelper factory = configCriteria.getWorkbook().getCreationHelper();

				Drawing drawing = cell.getSheet().createDrawingPatriarch();

				ClientAnchor anchor = factory.createClientAnchor();

				Comment comment = drawing.createCellComment(anchor);
				RichTextString str = factory.createRichTextString(configCriteria.getElement().comment());
				comment.setString(str);

				cell.setCellComment(comment);
			}
		}
	}

	/**
	 * Initialize cell style.
	 * 
	 * @param wb
	 *            the {@link Workbook} in use
	 * @return the {@link CellStyle}.
	 */
	protected static CellStyle initializeCellStyle(final Workbook wb) {
		return wb.createCellStyle();
	}

	/**
	 * Apply cell style according the one cell style base and format mask.
	 * 
	 * @param configCriteria
	 *            the {@link XConfigCriteria}
	 * @param cell
	 *            the {@link Cell}
	 * @param cellDecoratorType
	 *            the cell decorator by default
	 * @param maskDecoratorType
	 *            the format mask by default
	 * @throws ElementException
	 *             given when {@link CellStyle} is not defined
	 */
	protected static void applyCellStyle(final XConfigCriteria configCriteria, final Cell cell,
			final String cellDecoratorType, final String maskDecoratorType) throws ElementException {

		CellStyle cs = configCriteria.getCellStyle(cellDecoratorType);

		if (maskDecoratorType != null && StringUtils.isNotBlank(configCriteria.getMask(maskDecoratorType))
				&& cs != null) {

			/* generate a key based : formatMask + decorator */
			String key = configCriteria.generateCellStyleKey(cellDecoratorType, maskDecoratorType);

			if (!configCriteria.getCellStyleManager().containsKey(key)) {
				/* clone a cell style */
				CellStyle csNew = cloneCellStyle(configCriteria.getWorkbook(), cs);
				/* initialize/apply a data format */
				DataFormat df = dataFormatFactory.newInstance(configCriteria.getWorkbook());
				csNew.setDataFormat(df.getFormat(configCriteria.getMask(maskDecoratorType)));

				configCriteria.getCellStyleManager().put(key, csNew);

				cs = csNew;

			} else {
				cs = configCriteria.getCellStyleManager().get(key);
			}
			/* apply cell style to a cell */
			cell.setCellStyle(cs);

		} else {
			if (cs == null) {
				/* CASE : if the cell has no cell style base */
				cs = cellStyleFactory.newInstance(configCriteria.getWorkbook());
			}
			if (maskDecoratorType != null && StringUtils.isNotBlank(configCriteria.getMask(maskDecoratorType))) {
				/* CASE : if the cell has a formatMask */
				DataFormat df = dataFormatFactory.newInstance(configCriteria.getWorkbook());
				cs.setDataFormat(df.getFormat(configCriteria.getMask(maskDecoratorType)));
			}
			cell.setCellStyle(cs);
		}
	}

	/**
	 * Initialize the header cell.
	 * 
	 * @param stylesMap
	 *            map container with {@link CellStyle} declared
	 * @param row
	 *            {@link Row} to add the cell
	 * @param idxC
	 *            position of the new cell
	 * @param value
	 *            the value of the cell content
	 * @return the cell created
	 */
	protected static Cell initializeHeaderCell(final Map<String, CellStyle> stylesMap, final Row row, final int idxC,
			final String value) {
		Cell c = CellHandler.cellFactory.apply(row, idxC);
		c.setCellValue(value);
		c.setCellStyle(stylesMap.get(CellStyleHandler.CELL_DECORATOR_HEADER));
		return c;
	}

	/**
	 * Initialize default Header Cell Decorator.
	 * 
	 * @param wb
	 *            the {@link Workbook} in use
	 * @return the {@link CellStyle} header decorator
	 */
	protected static CellStyle initializeHeaderCellDecorator(final Workbook wb) {
		CellStyle cs = cellStyleFactory.newInstance(wb);

		/* add the alignment to the cell */
		CellStyleHandler.applyAlignment(cs, CellStyle.ALIGN_CENTER, CellStyle.VERTICAL_CENTER);

		/* add the border to the cell */
		CellStyleHandler.applyBorderDefault(cs);

		/* add the background to the cell */
		cs.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		cs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		/* add the wrap mode to the cell */
		cs.setWrapText(true);

		/* add the font style to the cell */
		CellStyleHandler.applyFontDefault(wb, cs);

		return cs;
	}

	/**
	 * Initialize default Numeric Cell Decorator.
	 * 
	 * @param wb
	 *            the {@link Workbook} in use
	 * @return the {@link CellStyle} numeric decorator
	 */
	protected static CellStyle initializeNumericCellDecorator(final Workbook wb) {
		CellStyle cs = cellStyleFactory.newInstance(wb);

		/* add the alignment to the cell */
		CellStyleHandler.applyAlignment(cs, CellStyle.ALIGN_RIGHT);
		CellStyleHandler.applyFontDefault(wb, cs);

		return cs;
	}

	/**
	 * Initialize default Date Cell Decorator.
	 * 
	 * @param wb
	 *            the {@link Workbook} in use
	 * @return the {@link CellStyle} date decorator
	 */
	protected static CellStyle initializeDateCellDecorator(final Workbook wb) {
		CellStyle cs = cellStyleFactory.newInstance(wb);

		/* add the alignment to the cell */
		CellStyleHandler.applyAlignment(cs, CellStyle.ALIGN_CENTER);
		CellStyleHandler.applyFontDefault(wb, cs);

		return cs;
	}

	/**
	 * Initialize default Boolean Cell Decorator.
	 * 
	 * @param wb
	 *            the {@link Workbook} in use
	 * @return the {@link CellStyle} boolean decorator
	 */
	protected static CellStyle initializeBooleanCellDecorator(final Workbook wb) {
		CellStyle cs = cellStyleFactory.newInstance(wb);

		/* add the alignment to the cell */
		CellStyleHandler.applyAlignment(cs, CellStyle.ALIGN_CENTER);
		CellStyleHandler.applyFontDefault(wb, cs);

		return cs;
	}

	/**
	 * Initialize default Generic Cell Decorator.
	 * 
	 * @param wb
	 *            the {@link Workbook} in use
	 * @return the {@link CellStyle} generic decorator
	 */
	protected static CellStyle initializeGenericCellDecorator(final Workbook wb) {
		CellStyle cs = cellStyleFactory.newInstance(wb);

		/* add the alignment to the cell */
		CellStyleHandler.applyAlignment(cs, CellStyle.ALIGN_LEFT);
		CellStyleHandler.applyFontDefault(wb, cs);

		return cs;
	}

	/**
	 * Initialize {@link CellStyle} by Cell Decorator.
	 * 
	 * @param wb
	 *            the {@link Workbook} in use
	 * @param decorator
	 *            the {@link CellDecorator} to use
	 * @return the {@link CellStyle} decorator
	 * @throws ConfigurationException
	 *             given when {@link CellStyle} is missing
	 */
	protected static CellStyle initializeCellStyleByCellDecorator(final Workbook wb, final CellDecorator decorator)
			throws ConfigurationException {
		CellStyle cs = cellStyleFactory.newInstance(wb);
		try {
			/* add the alignment to the cell */
			CellStyleHandler.applyAlignment(cs, decorator.getAlignment(), decorator.getVerticalAlignment());

			/* add the border to the cell */
			borderPropagationManagement(decorator);
			CellStyleHandler.applyBorder(cs, decorator.getBorderLeft(), decorator.getBorderRight(),
					decorator.getBorderTop(), decorator.getBorderBottom());

			/* add the background to the cell */
			cs.setFillForegroundColor(decorator.getForegroundColor());
			cs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

			/* add the wrap mode to the cell */
			cs.setWrapText(decorator.isWrapText());

			/* add the font style to the cell */
			CellStyleHandler.applyFont(wb, cs, decorator.getFontName(), decorator.getFontSize(),
					decorator.getFontColor(), decorator.isFontBold(), decorator.isFontItalic(),
					decorator.getFontUnderline());
		} catch (Exception e) {
			throw new ConfigurationException(ExceptionMessage.CONFIGURATION_CELLSTYLE_MISSING.getMessage(), e);
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
	 * @throws ConfigurationException
	 *             given when {@link CellStyle} is missing
	 */
	protected static CellStyle initializeCellStyleByXlsDecorator(final Workbook wb, final XlsDecorator decorator)
			throws ConfigurationException {
		CellStyle cs = cellStyleFactory.newInstance(wb);
		try {
			/* add the alignment to the cell */
			CellStyleHandler.applyAlignment(cs, decorator.alignment(), decorator.verticalAlignment());

			/* add the border to the cell */
			if (decorator.border() != 0 && isBorderPropagationValid(decorator)) {
				CellStyleHandler.applyBorder(cs, decorator.border(), decorator.border(), decorator.border(),
						decorator.border());
			} else {
				CellStyleHandler.applyBorder(cs, decorator.borderLeft(), decorator.borderRight(), decorator.borderTop(),
						decorator.borderBottom());
			}

			/* add the background to the cell */
			cs.setFillForegroundColor(decorator.foregroundColor());
			cs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

			/* add the wrap mode to the cell */
			cs.setWrapText(decorator.wrapText());

			/* add the font style to the cell */
			CellStyleHandler.applyFont(wb, cs, decorator.fontName(), decorator.fontSize(), decorator.fontColor(),
					decorator.fontBold(), decorator.fontItalic(), decorator.fontUnderline());
		} catch (Exception e) {
			throw new ConfigurationException(ExceptionMessage.CONFIGURATION_CELLSTYLE_MISSING.getMessage(), e);
		}
		return cs;
	}

	/**
	 * Clone a cell style passed as parameter.
	 * 
	 * @param wb
	 *            the {@link Workbook} in use
	 * @param csBase
	 *            the {@link CellStyle} base
	 * @return the new cell style
	 */
	private static CellStyle cloneCellStyle(final Workbook wb, final CellStyle csBase) {
		CellStyle cs = cellStyleFactory.newInstance(wb);
		cs.setAlignment(csBase.getAlignment());
		cs.setVerticalAlignment(csBase.getVerticalAlignment());
		cs.setBorderTop(csBase.getBorderTop());
		cs.setBorderBottom(csBase.getBorderBottom());
		cs.setBorderLeft(csBase.getBorderLeft());
		cs.setBorderRight(csBase.getBorderRight());
		cs.setFillForegroundColor(csBase.getFillForegroundColor());
		cs.setFillPattern(csBase.getFillPattern());
		cs.setWrapText(csBase.getWrapText());

		cs.setFont(wb.getFontAt(csBase.getFontIndex()));
		return cs;
	}

	/**
	 * if specific border not configured, propagate generic border configuration
	 * to specific border.
	 * 
	 * @param decorator
	 *            the {@link CellDecorator} to use
	 */
	private static void borderPropagationManagement(final CellDecorator decorator) {
		/* if specific border not configured */
		if (decorator.getBorder() != 0 && isBorderPropagationValid(decorator)) {
			/* propagate generic border configuration to specific border */
			decorator.setBorderLeft(decorator.getBorder());
			decorator.setBorderRight(decorator.getBorder());
			decorator.setBorderTop(decorator.getBorder());
			decorator.setBorderBottom(decorator.getBorder());
		}
	}

	/**
	 * Validate if the border should be propagated.
	 * 
	 * @param decorator
	 *            the {@link CellDecorator} to validate
	 * @return true if valid, otherwise false
	 */
	private static boolean isBorderPropagationValid(final CellDecorator decorator) {
		return decorator.getBorderLeft() == 0 && decorator.getBorderRight() == 0 && decorator.getBorderTop() == 0
				&& decorator.getBorderBottom() == 0;
	}

	/**
	 * Validate if the border should be propagated.
	 * 
	 * @param decorator
	 *            the {@link XlsDecorator} to validate
	 * @return true if valid, otherwise false
	 */
	private static boolean isBorderPropagationValid(final XlsDecorator decorator) {
		return decorator.borderLeft() == 0 && decorator.borderRight() == 0 && decorator.borderTop() == 0
				&& decorator.borderBottom() == 0;
	}

}
