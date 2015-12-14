package org.jaexcel.framework.JAEX.engine;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;

public class CellStyleUtils {

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
	protected static void applyDataFormat(Workbook wb, Cell c, CellStyle cs,
			String formatMask) {
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
	protected static void applyFont(Workbook wb, CellStyle cs, boolean b, boolean i) {
		Font f = initializeFont(wb);
		f.setBold(b);
		f.setItalic(i);
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
		cs.setAlignment(a);
		cs.setVerticalAlignment(vA);
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
	protected static void applyBorder(CellStyle cs, short bL, short bR, short bT,
			short bB) {
		cs.setBorderLeft(bL);
		cs.setBorderRight(bR);
		cs.setBorderTop(bT);
		cs.setBorderBottom(bB);
	}

	/**
	 * Apply the background color to the cell style.
	 * 
	 * @param c
	 *            the cell
	 * @param cs
	 *            the cell style
	 * @param bC
	 *            the background color
	 */
	protected static void applyBackgroundColor(Cell c, CellStyle cs, short bC) {
		cs.setFillBackgroundColor(bC);
	}

	// HSSFWorkbook a;
	// XSSFCellStyle x;
	// XSSFWorkbook xw;
	// XSSFFont fx = xw.createFont();
	// fx.set
	// HSSFCellStyle hcs = a.createCellStyle();
	// Font f = wb.createFont();
	// f.setBold(true);
	// f.setItalic(true);
	// f.setFontHeight();
	// f.setFontName()
	// f.setUnderline(FontUnderline.DOUBLE);
	// cs.setFont(f);
	// c.setCellStyle(cs);
	// cs.set
}
