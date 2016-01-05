package org.jaexcel.framework.JAEX.engine;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.jaexcel.framework.JAEX.definition.ExtensionFileType;

public class CellStyleUtils {
	// cell decorator constants
	public static final String CELL_DECORATOR_DATE = "date";
	public static final String CELL_DECORATOR_BOOLEAN = "boolean";
	public static final String CELL_DECORATOR_NUMERIC = "numeric";
	public static final String CELL_DECORATOR_HEADER = "header";
	// default mask decorators
	public static final String MASK_DECORATOR_DATE = "yyyy-MM-dd";
	public static final String MASK_DECORATOR_INTEGER = "0";
	public static final String MASK_DECORATOR_DOUBLE = "0.00";

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
	 * Apply the font to the cell style.
	 * 
	 * @param wb
	 *            the workbook
	 * @param cs
	 *            the cell style
	 * @param b
	 *            is bold format
	 * @param i
	 *            is italic format
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
	 * Apply the alignment to the cell style.
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
	 * Apply the cell style to a cell.
	 * 
	 * @param wb
	 *            the workbook
	 * @param c
	 *            the cell
	 * @param cs
	 *            the cell style
	 */
	protected static void applyCellStyle(Workbook wb, Cell c, CellStyle cs) {
		if (cs == null) {
			cs = initializeCellStyle(wb);
		}
		c.setCellStyle(cs);
	}

	/**
	 * Apply cell style according the one cell style base and format mask.
	 * 
	 * @param wb
	 *            the workbook
	 * @param c
	 *            the cell
	 * @param csBase
	 *            the cell style base
	 * @param formatMask
	 *            the format mask
	 */
	protected static void applyCellStyle(Workbook wb, Cell c, CellStyle csBase, String formatMask) {
		if (StringUtils.isNotBlank(formatMask) && csBase != null) {
			// CASE : if the cell has a formatMask and cell style base
			// clone a cell style
			CellStyle cs = cloneCellStyle(wb, csBase);

			// apply data format
			DataFormat df = initializeDataFormat(wb);
			cs.setDataFormat(df.getFormat(formatMask));

			// apply cell style to a cell
			c.setCellStyle(cs);
		} else {
			if (csBase == null) {
				// CASE : if the cell has no cell style base
				csBase = initializeCellStyle(wb);
			}
			if (StringUtils.isNotBlank(formatMask)) {
				// CASE : if the cell has a formatMask
				DataFormat df = initializeDataFormat(wb);
				csBase.setDataFormat(df.getFormat(formatMask));
			}
			c.setCellStyle(csBase);
		}
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
}
