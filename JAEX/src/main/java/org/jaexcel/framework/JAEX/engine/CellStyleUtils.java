package org.jaexcel.framework.JAEX.engine;

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
import org.jaexcel.framework.JAEX.annotation.XlsDecorator;
import org.jaexcel.framework.JAEX.definition.ExceptionMessage;
import org.jaexcel.framework.JAEX.definition.ExtensionFileType;
import org.jaexcel.framework.JAEX.exception.ConfigurationException;
import org.jaexcel.framework.JAEX.exception.ElementException;

public class CellStyleUtils {
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

	/**
	 * Apply the data format to the cell style.
	 * 
	 * @param wb
	 *            the workbook
	 * @param c
	 *            the cell
	 * @param cs
	 *            the cell style
	 * @param formatMask
	 */
	protected static void applyDataFormat(Workbook wb, Cell c, CellStyle cs, String formatMask) {
		DataFormat df = initializeDataFormat(wb);
		cs.setDataFormat(df.getFormat(formatMask));
		c.setCellStyle(cs);
	}

	/**
	 * Apply the font to the cell style with default values.
	 * 
	 * @param wb
	 *            the workbook
	 * @param cs
	 *            the cell style
	 */
	protected static void applyFontDefault(Workbook wb, CellStyle cs) {
		applyFont(wb, cs, CellStyleUtils.FONT_NAME, CellStyleUtils.FONT_SIZE, CellStyleUtils.FONT_COLOR,
				CellStyleUtils.FONT_BOLD, CellStyleUtils.FONT_ITALIC, FontUnderline.NONE.getByteValue());
	}

	/**
	 * Apply the font to the cell style.
	 * 
	 * @param wb
	 *            the workbook
	 * @param cs
	 *            the cell style
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
	protected static void applyFont(Workbook wb, CellStyle cs, String name, short size, short c, boolean b, boolean i,
			byte u) {
		Font f = initializeFont(wb);
		f.setFontName(name);
		f.setFontHeightInPoints(size);
		f.setColor(c);
		f.setBold(b);
		f.setItalic(i);
		f.setUnderline(u);
		cs.setFont(f);
	}

	/**
	 * Apply the horizontal alignment to the cell style.<br>
	 * By default the vertical alignment is 0.
	 * 
	 * @param cs
	 *            the cell style
	 * @param a
	 *            the horizontal alignment
	 */
	protected static void applyAlignment(CellStyle cs, short a) {
		applyAlignment(cs, a, (short) 0);
	}

	/**
	 * Apply the horizontal & vertical alignment to the cell style.
	 * 
	 * @param cs
	 *            the cell style
	 * @param a
	 *            the horizontal alignment
	 * @param vA
	 *            the vertical alignment
	 */
	protected static void applyAlignment(CellStyle cs, short a, short vA) {
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
	 *            the cell style
	 */
	protected static void applyBorderDefault(CellStyle cs) {
		applyBorder(cs, CellStyle.BORDER_THIN, CellStyle.BORDER_THIN, CellStyle.BORDER_THIN, CellStyle.BORDER_THIN);
	}

	/**
	 * Apply the border to the cell style.
	 * 
	 * @param cs
	 *            the cell style
	 * @param bL
	 *            the cell border left
	 * @param bR
	 *            the cell border right
	 * @param bT
	 *            the cell border top
	 * @param bB
	 *            the cell border bottom
	 */
	protected static void applyBorder(CellStyle cs, short bL, short bR, short bT, short bB) {
		cs.setBorderLeft(bL);
		cs.setBorderRight(bR);
		cs.setBorderTop(bT);
		cs.setBorderBottom(bB);
	}

	/**
	 * Apply the background color to the cell style.
	 * 
	 * @param cs
	 *            the cell style
	 * @param bC
	 *            the background color
	 */
	protected static void applyBackgroundColor(CellStyle cs, short bC) {
		cs.setFillBackgroundColor(bC);
	}

	/**
	 * Apply the cell comment to a cell.
	 * 
	 * @param wb
	 *            the workbook
	 * @param c
	 *            the cell
	 * @param t
	 *            the text comment
	 * @param e
	 *            the extension file
	 */
	protected static void applyComment(Workbook wb, Cell c, String t, ExtensionFileType e) {

		if (ExtensionFileType.XLS.equals(e)) {
			final Map<Sheet, HSSFPatriarch> drawingPatriarches = new HashMap<Sheet, HSSFPatriarch>();

			CreationHelper createHelper = c.getSheet().getWorkbook().getCreationHelper();
			HSSFSheet sheet = (HSSFSheet) c.getSheet();
			HSSFPatriarch drawingPatriarch = drawingPatriarches.get(sheet);
			if (drawingPatriarch == null) {
				drawingPatriarch = sheet.createDrawingPatriarch();
				drawingPatriarches.put(sheet, drawingPatriarch);
			}

			Comment comment = drawingPatriarch
					.createComment(new HSSFClientAnchor(0, 0, 0, 0, (short) 4, 2, (short) 6, 5));
			comment.setString(createHelper.createRichTextString(t));

			c.setCellComment(comment);

		} else if (ExtensionFileType.XLSX.equals(e)) {
			CreationHelper factory = wb.getCreationHelper();

			Drawing drawing = c.getSheet().createDrawingPatriarch();

			ClientAnchor anchor = factory.createClientAnchor();

			Comment comment = drawing.createCellComment(anchor);
			RichTextString str = factory.createRichTextString(t);
			comment.setString(str);

			c.setCellComment(comment);
		}

	}

	/**
	 * Initialize cell style.
	 * 
	 * @param wb
	 *            the workbook
	 * @return the {@link CellStyle}.
	 */
	protected static CellStyle initializeCellStyle(Workbook wb) {
		return wb.createCellStyle();
	}

	/**
	 * Apply cell style according the one cell style base and format mask.
	 * 
	 * @param configCriteria
	 *            the {@link ConfigCriteria}
	 * @param c
	 *            the {@link Cell}
	 * @param cellDecoratorType
	 *            the cell decorator by default
	 * @param maskDecoratorType
	 *            the format mask by default
	 * @throws ElementException
	 */
	protected static void applyCellStyle(ConfigCriteria configCriteria, Cell c, String cellDecoratorType,
			String maskDecoratorType) throws ElementException {

		CellStyle cs = configCriteria.getCellStyle(cellDecoratorType);

		if (maskDecoratorType != null && StringUtils.isNotBlank(configCriteria.getMask(maskDecoratorType))
				&& cs != null) {

			/* generate a key based : formatMask + decorator */
			String key = configCriteria.generateCellStyleKey(cellDecoratorType, maskDecoratorType);

			if (!configCriteria.getCellStyleManager().containsKey(key)) {
				/* clone a cell style */
				CellStyle csNew = cloneCellStyle(configCriteria.getWorkbook(), cs);
				/* initialize/apply a data format */
				DataFormat df = initializeDataFormat(configCriteria.getWorkbook());
				csNew.setDataFormat(df.getFormat(configCriteria.getMask(maskDecoratorType)));

				configCriteria.getCellStyleManager().put(key, csNew);

				cs = csNew;

			} else {
				cs = configCriteria.getCellStyleManager().get(key);
			}
			/* apply cell style to a cell */
			c.setCellStyle(cs);

		} else {
			if (cs == null) {
				/* CASE : if the cell has no cell style base */
				cs = initializeCellStyle(configCriteria.getWorkbook());
			}
			if (maskDecoratorType != null && StringUtils.isNotBlank(configCriteria.getMask(maskDecoratorType))) {
				/* CASE : if the cell has a formatMask */
				DataFormat df = initializeDataFormat(configCriteria.getWorkbook());
				cs.setDataFormat(df.getFormat(configCriteria.getMask(maskDecoratorType)));
			}
			c.setCellStyle(cs);
		}
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
	protected static Cell initializeHeaderCell(Map<String, CellStyle> stylesMap, Row r, int idxC, String value)
			throws Exception {
		Cell c = r.createCell(idxC);
		c.setCellValue(value);
		c.setCellStyle(stylesMap.get(CellStyleUtils.CELL_DECORATOR_HEADER));
		return c;
	}

	/**
	 * Initialize default Header Cell Decorator.
	 * 
	 * @param wb
	 *            the {@link Workbook} in use
	 * @return the {@link CellStyle} header decorator
	 */
	protected static CellStyle initializeHeaderCellDecorator(Workbook wb) {
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
	protected static CellStyle initializeNumericCellDecorator(Workbook wb) {
		CellStyle cs = CellStyleUtils.initializeCellStyle(wb);

		/* add the alignment to the cell */
		CellStyleUtils.applyAlignment(cs, CellStyle.ALIGN_RIGHT);
		CellStyleUtils.applyFontDefault(wb, cs);
		//DataFormat df = initializeDataFormat(wb);
		//cs.setDataFormat(df.getFormat(CellStyleUtils.MASK_DECORATOR_INTEGER));

		return cs;
	}

	/**
	 * Initialize default Date Cell Decorator.
	 * 
	 * @param wb
	 *            the {@link Workbook} in use
	 * @return the {@link CellStyle} date decorator
	 */
	protected static CellStyle initializeDateCellDecorator(Workbook wb) {
		CellStyle cs = CellStyleUtils.initializeCellStyle(wb);

		/* add the alignment to the cell */
		CellStyleUtils.applyAlignment(cs, CellStyle.ALIGN_CENTER);
		CellStyleUtils.applyFontDefault(wb, cs);
		//DataFormat df = initializeDataFormat(wb);
		//cs.setDataFormat(df.getFormat(CellStyleUtils.MASK_DECORATOR_DATE));

		return cs;
	}

	/**
	 * Initialize default Boolean Cell Decorator.
	 * 
	 * @param wb
	 *            the {@link Workbook} in use
	 * @return the {@link CellStyle} boolean decorator
	 */
	protected static CellStyle initializeBooleanCellDecorator(Workbook wb) {
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
	protected static CellStyle initializeGenericCellDecorator(Workbook wb) {
		CellStyle cs = CellStyleUtils.initializeCellStyle(wb);

		/* add the alignment to the cell */
		CellStyleUtils.applyAlignment(cs, CellStyle.ALIGN_LEFT);
		CellStyleUtils.applyFontDefault(wb, cs);

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
	 */
	protected static CellStyle initializeCellStyleByCellDecorator(Workbook wb, CellDecorator decorator)
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
			throw new ConfigurationException(ExceptionMessage.ConfigurationException_CellStyleMissing.getMessage(), e);
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
	protected static CellStyle initializeCellStyleByXlsDecorator(Workbook wb, XlsDecorator decorator)
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
			throw new ConfigurationException(ExceptionMessage.ConfigurationException_CellStyleMissing.getMessage(), e);
		}
		return cs;
	}

	/**
	 * Initialize font.
	 * 
	 * @param wb
	 *            the workbook
	 * @return the {@link Font}.
	 */
	private static Font initializeFont(Workbook wb) {
		return wb.createFont();
	}

	/**
	 * Initialize the data format.
	 * 
	 * @param wb
	 *            the workbook
	 * @return the {@link DataFormat}.
	 */
	private static DataFormat initializeDataFormat(Workbook wb) {
		return wb.createDataFormat();
	}

	/**
	 * Clone a cell style passed as parameter.
	 * 
	 * @param wb
	 *            the workbook
	 * @param csBase
	 *            the cell style base
	 * @return the new cell style
	 */
	private static CellStyle cloneCellStyle(Workbook wb, CellStyle csBase) {
		CellStyle cs = initializeCellStyle(wb);
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
	private static void borderPropagationManagement(CellDecorator decorator) {
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

}
