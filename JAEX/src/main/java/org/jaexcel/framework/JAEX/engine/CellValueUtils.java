package org.jaexcel.framework.JAEX.engine;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.FormulaParser;
import org.apache.poi.ss.formula.FormulaRenderer;
import org.apache.poi.ss.formula.FormulaType;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.formula.ptg.RefPtgBase;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFEvaluationWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jaexcel.framework.JAEX.annotation.XlsElement;
import org.jaexcel.framework.JAEX.definition.ExtensionFileType;
import org.jaexcel.framework.JAEX.definition.JAEXExceptionMessage;
import org.jaexcel.framework.JAEX.exception.JAEXConverterException;
import org.jaexcel.framework.JAEX.exception.JAEXCustomizedRulesException;

public class CellValueUtils {

	// object type
	public static final String OBJECT_DATE = "java.util.Date";
	public static final String OBJECT_STRING = "java.lang.String";
	public static final String OBJECT_INTEGER = "java.lang.Integer";
	public static final String OBJECT_LONG = "java.lang.Long";
	public static final String OBJECT_DOUBLE = "java.lang.Double";
	public static final String OBJECT_FLOAT = "java.lang.Float";
	public static final String OBJECT_BIGDECIMAL = "java.math.BigDecimal";
	public static final String OBJECT_BOOLEAN = "java.lang.Boolean";
	// primitive type
	public static final String PRIMITIVE_INTEGER = "int";
	public static final String PRIMITIVE_LONG = "long";
	public static final String PRIMITIVE_DOUBLE = "double";
	public static final String PRIMITIVE_FLOAT = "float";
	public static final String PRIMITIVE_BOOLEAN = "boolean";

	/**
	 * Recover the value from the Excel.
	 * 
	 * @param c
	 * @return
	 */
	protected static String fromExcel(Cell c) {
		if (c.getCellType() == Cell.CELL_TYPE_STRING) {
			return c.getStringCellValue();

		} else if (c.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			Double value = c.getNumericCellValue();
			return value.toString();
		} else if (c.getCellType() == Cell.CELL_TYPE_FORMULA) {
			switch (c.getCachedFormulaResultType()) {
			case Cell.CELL_TYPE_NUMERIC:
				Double value = c.getNumericCellValue();
				return value.toString();

			case Cell.CELL_TYPE_STRING:
				return c.getRichStringCellValue().toString();

			default:
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * Apply a integer value at the Cell.
	 * 
	 * @param o
	 *            the object
	 * @param f
	 *            the field
	 * @param wb
	 *            the workbook
	 * @param c
	 *            the cell
	 * @param cs
	 *            the cell style
	 * @param element
	 *            the {@link XlsElement} annotation
	 * @param eFT
	 *            the extension file type
	 * @return false if problem otherwise true
	 */
	protected static boolean toString(Object o, Field f, Workbook wb, Cell c, CellStyle cs, XlsElement element,
			ExtensionFileType eFT) {
		boolean isUpdated = true;
		try {
			if (element.isFormula()) {
				// apply the formula
				if (!toFormula(o, f, c, element)) {
					c.setCellValue((Long) toExplicitFormula(o, f));
				}

			} else {
				// normal manage cell
				c.setCellValue((String) f.get(o));
			}

			// apply style
			CellStyleUtils.applyCellStyle(wb, c, cs);

			if (StringUtils.isNotBlank(element.comment())) {
				// apply the comment
				CellStyleUtils.applyComment(wb, c, element.comment(), eFT);
			}

		} catch (Exception e) {
			// TODO: handle exception
			isUpdated = false;
		}
		return isUpdated;
	}

	/**
	 * Apply a integer value at the Cell.
	 * 
	 * @param o
	 *            the object
	 * @param f
	 *            the field
	 * @param wb
	 *            the workbook
	 * @param c
	 *            the cell
	 * @param cs
	 *            the cell style
	 * @param formatMask
	 *            the decorator mask
	 * @param element
	 *            the {@link XlsElement} annotation
	 * @param eFT
	 *            the extension file type
	 * @return false if problem otherwise true
	 */
	protected static boolean toInteger(Object o, Field f, Workbook wb, Cell c, CellStyle cs, String formatMask,
			XlsElement element, ExtensionFileType eFT) {
		boolean isUpdated = true;
		try {
			if (element.isFormula()) {
				// apply the formula
				if (!toFormula(o, f, c, element)) {
					c.setCellValue((Long) toExplicitFormula(o, f));
				}

			} else {
				// normal manage cell
				c.setCellValue((Integer) f.get(o));
			}

			// apply cell style
			CellStyleUtils.applyCellStyle(wb, c, cs, formatMask);

			if (StringUtils.isNotBlank(element.comment())) {
				// apply the comment
				CellStyleUtils.applyComment(wb, c, element.comment(), eFT);
			}

		} catch (Exception e) {
			// TODO: handle exception
			isUpdated = false;
		}
		return isUpdated;
	}

	/**
	 * Apply a double value at the Cell.
	 * 
	 * @param o
	 *            the object
	 * @param f
	 *            the field
	 * @param wb
	 *            the workbook
	 * @param c
	 *            the cell
	 * @param cs
	 *            the cell style
	 * @param formatMask
	 *            the decorator mask
	 * @param element
	 *            the {@link XlsElement} annotation
	 * @param eFT
	 *            the extension file type
	 * @return false if problem otherwise true
	 */
	protected static boolean toLong(Object o, Field f, Workbook wb, Cell c, CellStyle cs, String formatMask,
			XlsElement element, ExtensionFileType eFT) {
		boolean isUpdated = true;

		try {
			if (element.isFormula()) {
				// apply the formula
				if (!toFormula(o, f, c, element)) {
					c.setCellValue((Long) toExplicitFormula(o, f));
				}

			} else {
				// normal manage cell
				c.setCellValue((Long) f.get(o));
			}

			// apply cell style
			CellStyleUtils.applyCellStyle(wb, c, cs, formatMask);

			if (StringUtils.isNotBlank(element.comment())) {
				// apply the comment
				CellStyleUtils.applyComment(wb, c, element.comment(), eFT);
			}

		} catch (Exception e) {
			// TODO: handle exception
			isUpdated = false;
		}
		return isUpdated;
	}

	/**
	 * Apply a double value at the Cell.
	 * 
	 * @param o
	 *            the object
	 * @param f
	 *            the field
	 * @param wb
	 *            the workbook
	 * @param c
	 *            the cell
	 * @param cs
	 *            the cell style
	 * @param formatMask
	 *            the decorator mask
	 * @param transformMask
	 *            the transformation mask
	 * @param element
	 *            the {@link XlsElement} annotation
	 * @param eFT
	 *            the extension file type
	 * @return false if problem otherwise true
	 */
	protected static boolean toDouble(Object o, Field f, Workbook wb, Cell c, CellStyle cs, String formatMask,
			String transformMask, XlsElement element, ExtensionFileType eFT) {
		boolean isUpdated = true;

		try {
			// FIXME use the comment below to manage decimalScale
			// Double d = (Double) f.get(o);
			// BigDecimal bd = new BigDecimal(d);
			// bd.setScale(2, BigDecimal.ROUND_HALF_UP);

			if (element.isFormula()) {
				// apply the formula
				if (!toFormula(o, f, c, element)) {
					c.setCellValue((Double) toExplicitFormula(o, f));

					// apply cell style
					CellStyleUtils.applyCellStyle(wb, c, cs);
				}

			} else {
				// normal manage cell
				if (StringUtils.isNotBlank(transformMask)) {
					DecimalFormat df = new DecimalFormat(transformMask);
					c.setCellValue(df.format((Double) f.get(o)));

					// apply cell style
					CellStyleUtils.applyCellStyle(wb, c, cs);
				} else {
					c.setCellValue((Double) f.get(o));

					// apply cell style
					CellStyleUtils.applyCellStyle(wb, c, cs, formatMask);
				}
			}

			if (StringUtils.isNotBlank(element.comment())) {
				// apply the comment
				CellStyleUtils.applyComment(wb, c, element.comment(), eFT);
			}

		} catch (Exception e) {
			// TODO: handle exception
			isUpdated = false;
		}
		return isUpdated;
	}

	/**
	 * Apply a big decimal value at the Cell.
	 * 
	 * @param o
	 *            the object
	 * @param f
	 *            the field
	 * @param wb
	 *            the workbook
	 * @param c
	 *            the cell
	 * @param cs
	 *            the cell style
	 * @param formatMask
	 *            the decorator mask
	 * @param transformMask
	 *            the transformation mask
	 * @param element
	 *            the {@link XlsElement} annotation
	 * @param eFT
	 *            the extension file type
	 * @return false if problem otherwise true
	 */
	protected static boolean toBigDecimal(Object o, Field f, Workbook wb, Cell c, CellStyle cs, String formatMask,
			String transformMask, XlsElement element, ExtensionFileType eFT) {
		boolean isUpdated = true;

		try {
			if (element.isFormula()) {
				// apply the formula
				if (!toFormula(o, f, c, element)) {
					c.setCellValue((Double) toExplicitFormula(o, f));

					// apply cell style
					CellStyleUtils.applyCellStyle(wb, c, cs);
				}

			} else {
				// normal manage cell
				BigDecimal bd = (BigDecimal) f.get(o);

				// FIXME use the comment below to manage decimalScale
				// bd.setScale(2, BigDecimal.ROUND_HALF_UP);
				if (bd != null) {
					Double dBigDecimal = bd.doubleValue();
					if (StringUtils.isNotBlank(transformMask)) {
						DecimalFormat df = new DecimalFormat(transformMask);
						c.setCellValue(df.format(dBigDecimal));
						// apply cell style
						CellStyleUtils.applyCellStyle(wb, c, cs);

					} else {
						c.setCellValue(dBigDecimal);
						// apply cell style
						CellStyleUtils.applyCellStyle(wb, c, cs, formatMask);
					}
				}
			}

			if (StringUtils.isNotBlank(element.comment())) {
				// apply the comment
				CellStyleUtils.applyComment(wb, c, element.comment(), eFT);
			}

		} catch (Exception e) {
			// TODO: handle exception
			isUpdated = false;
		}
		return isUpdated;
	}

	/**
	 * Apply a date value at the Cell.
	 * 
	 * @param o
	 *            the object
	 * @param f
	 *            the field
	 * @param wb
	 *            the workbook
	 * @param c
	 *            the cell
	 * @param cs
	 *            the cell style
	 * @param formatMask
	 *            the decorator mask
	 * @param transformMask
	 *            the transformation mask
	 * @param comment
	 *            the comment text
	 * @param eFT
	 *            the extension file type
	 * @return false if problem otherwise true
	 */
	protected static boolean toDate(Object o, Field f, Workbook wb, Cell c, CellStyle cs, String formatMask,
			String transformMask, String comment, ExtensionFileType eFT) {
		boolean isUpdated = true;

		try {

			Date dDate = (Date) f.get(o);
			if (dDate != null) {

				if (StringUtils.isNotBlank(transformMask)) {
					// apply transformation mask
					String decorator = transformMask;
					try {
						SimpleDateFormat dt = new SimpleDateFormat(decorator);

						String dateFormated = dt.format(dDate);
						if (dateFormated.equals(decorator)) {
							// if date decorator do not match with a valid mask
							// launch exception
							throw new JAEXConverterException(
									JAEXExceptionMessage.JAEXConverterException_Date.getMessage());
						}
						c.setCellValue(dateFormated);
						// apply cell style
						CellStyleUtils.applyCellStyle(wb, c, cs);
					} catch (Exception e) {
						throw new JAEXConverterException(JAEXExceptionMessage.JAEXConverterException_Date.getMessage(),
								e);
					}

				} else if (StringUtils.isNotBlank(formatMask)) {
					// apply format mask
					c.setCellValue(dDate);
					// apply cell style
					CellStyleUtils.applyCellStyle(wb, c, cs, formatMask);

				} else {
					// apply default date mask
					c.setCellValue(dDate);
					// apply cell style
					CellStyleUtils.applyCellStyle(wb, c, cs, "yyyy-MM-dd");

				}

				if (StringUtils.isNotBlank(comment)) {
					// apply the comment
					CellStyleUtils.applyComment(wb, c, comment, eFT);
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			isUpdated = false;
		}
		return isUpdated;
	}

	/**
	 * Apply a float value at the Cell.
	 * 
	 * @param o
	 *            the object
	 * @param f
	 *            the field
	 * @param wb
	 *            the workbook
	 * @param c
	 *            the cell
	 * @param cs
	 *            the cell style
	 * @param formatMask
	 *            the decorator mask
	 * @param element
	 *            the {@link XlsElement} annotation
	 * @param eFT
	 *            the extension file type
	 * @return false if problem otherwise true
	 */
	protected static boolean toFloat(Object o, Field f, Workbook wb, Cell c, CellStyle cs, String formatMask,
			XlsElement element, ExtensionFileType eFT) {
		boolean isUpdated = true;

		try {
			if (element.isFormula()) {
				// apply the formula
				if (!toFormula(o, f, c, element)) {
					c.setCellValue((Double) toExplicitFormula(o, f));

					// apply cell style
					CellStyleUtils.applyCellStyle(wb, c, cs);
				}

			} else {
				// normal manage cell
				c.setCellValue((Float) f.get(o));

				// apply cell style
				CellStyleUtils.applyCellStyle(wb, c, cs, formatMask);
			}

			if (StringUtils.isNotBlank(element.comment())) {
				// apply the comment
				CellStyleUtils.applyComment(wb, c, element.comment(), eFT);
			}

		} catch (Exception e) {
			// TODO: handle exception
			isUpdated = false;
		}
		return isUpdated;
	}

	/**
	 * 
	 * Apply a boolean value at the Cell.
	 * 
	 * @param o
	 *            the object
	 * @param f
	 *            the field
	 * @param wb
	 *            the workbook
	 * @param c
	 *            the cell
	 * @param cs
	 *            the cell style
	 * @param transformMask
	 *            the transformation mask
	 * @param comment
	 *            the comment text
	 * @param eFT
	 *            the extension file type
	 * @return false if problem otherwise true
	 */
	protected static boolean toBoolean(Object o, Field f, Workbook wb, Cell c, CellStyle cs, String transformMask,
			String comment, ExtensionFileType eFT) {
		boolean isUpdated = true;

		try {
			Boolean bBoolean = (Boolean) f.get(o);
			if (StringUtils.isNotBlank(transformMask)) {
				// apply format mask defined at transform mask
				String[] split = transformMask.split("/");
				c.setCellValue((bBoolean == null ? "" : (bBoolean ? split[0] : split[1])));

				// apply cell style
				CellStyleUtils.applyCellStyle(wb, c, cs);

			} else {
				// locale mode
				c.setCellValue((bBoolean == null ? "" : bBoolean).toString());

				// apply cell style
				CellStyleUtils.applyCellStyle(wb, c, cs);
			}

			if (StringUtils.isNotBlank(comment)) {
				// apply the comment
				CellStyleUtils.applyComment(wb, c, comment, eFT);
			}

		} catch (Exception e) {
			// TODO: handle exception
			isUpdated = false;
		}
		return isUpdated;
	}

	/**
	 * 
	 * Apply a enum value at the Cell.
	 * 
	 * @param o
	 *            the object
	 * @param f
	 *            the field
	 * @param wb
	 *            the workbook
	 * @param c
	 *            the cell
	 * @param cs
	 *            the cell style
	 * @param comment
	 *            the comment text
	 * @param eFT
	 *            the extension file type
	 * @return false if problem otherwise true
	 */
	protected static boolean toEnum(Object o, Field f, Workbook wb, Cell c, CellStyle cs, String comment,
			ExtensionFileType eFT) {
		boolean isUpdated = true;

		try {

			@SuppressWarnings("rawtypes")
			Class[] argTypes = {};

			String method = "get" + f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1);

			Method m = o.getClass().getDeclaredMethod(method, argTypes);

			Object objEnum = m.invoke(o, (Object[]) null);

			if (objEnum != null) {
				// apply the enum value
				c.setCellValue((String) objEnum.toString());
			}

			// apply cell style
			CellStyleUtils.applyCellStyle(wb, c, cs);

			if (StringUtils.isNotBlank(comment)) {
				// apply the comment
				CellStyleUtils.applyComment(wb, c, comment, eFT);
			}

		} catch (Exception e) {
			// TODO: handle exception
			isUpdated = false;
		}
		return isUpdated;
	}

	/**
	 * Apply a formula value at the Cell.
	 * 
	 * @param o
	 *            the object
	 * @param f
	 *            the field
	 * @param c
	 *            the cell
	 * @param element
	 *            the {@link XlsElement} annotation
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private static boolean toFormula(Object o, Field f, Cell c, XlsElement element)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		boolean isFormulaApplied = false;

		if (StringUtils.isNotBlank(element.formula())) {
			if (element.formula().contains("idx")) {
				c.setCellFormula(element.formula().replace("idx", String.valueOf(c.getRowIndex() + 1)));
				isFormulaApplied = true;

			} else if (element.formula().contains("idy")) {
				c.setCellFormula(element.formula().replace("idy",
						String.valueOf(CellReference.convertNumToColString(c.getColumnIndex()))));
				isFormulaApplied = true;

			} else {
				c.setCellFormula(element.formula());
				isFormulaApplied = true;
			}
		}

		return isFormulaApplied;
	}

	/**
	 * Apply a explicit formula value at the Cell.
	 * 
	 * @param o
	 *            the object
	 * @param f
	 *            the field
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private static Object toExplicitFormula(Object o, Field f)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		@SuppressWarnings("rawtypes")
		Class[] argTypes = {};

		String method = "formula" + f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1);

		Method m = o.getClass().getDeclaredMethod(method, argTypes);

		return m.invoke(o, (Object[]) null);
	}

	/**
	 * Apply a explicit formula value at the Cell.
	 * 
	 * @param o
	 *            the object
	 * @param f
	 *            the field
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	protected static void applyCustomizedRules(Object o, String methodRules) throws NoSuchMethodException,
			IllegalAccessException, InvocationTargetException, JAEXCustomizedRulesException {
		@SuppressWarnings("rawtypes")
		Class[] argTypes = {};

		Method m = o.getClass().getDeclaredMethod(methodRules, argTypes);

		m.invoke(o, (Object[]) null);
	}

	// TODO : to review
	// source :
	// http://stackoverflow.com/questions/1636759/poi-excel-applying-formulas-in-a-relative-way
	protected void aab(Workbook workbook, Cell cell) {
		String formula = cell.getCellFormula();
		XSSFEvaluationWorkbook workbookWrapper = XSSFEvaluationWorkbook.create((XSSFWorkbook) workbook);
		/* parse formula */
		Ptg[] ptgs = FormulaParser.parse(formula, workbookWrapper, FormulaType.CELL,
				0 /* sheet index */ );

		/* re-calculate cell references */
		for (Ptg ptg : ptgs)
			if (ptg instanceof RefPtgBase) // base class for cell reference
											// "things"
			{
				RefPtgBase ref = (RefPtgBase) ptg;
				if (ref.isColRelative())
					ref.setColumn(ref.getColumn() + 0);
				if (ref.isRowRelative())
					ref.setRow(ref.getRow() + 1);
			}

		formula = FormulaRenderer.toFormulaString(workbookWrapper, ptgs);
		cell.setCellFormula(formula);
	}
}
