package org.jaexcel.framework.JAEX.engine;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;
import org.jaexcel.framework.JAEX.definition.JAEXExceptionMessage;
import org.jaexcel.framework.JAEX.exception.JAEXConverterException;

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
	 * @return false if problem otherwise true
	 */
	protected static boolean toString(Object o, Field f, Workbook wb, Cell c, CellStyle cs) {
		boolean isUpdated = true;
		try {

			c.setCellValue((String) f.get(o));

			CellStyleUtils.applyCellStyle(wb, c, cs);

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
	 * @return false if problem otherwise true
	 */
	protected static boolean toInteger(Object o, Field f, Workbook wb, Cell c, CellStyle cs, String formatMask) {
		boolean isUpdated = true;
		try {

			c.setCellValue((Integer) f.get(o));

			CellStyleUtils.applyCellStyle(wb, c, cs, formatMask);

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
	 * @return false if problem otherwise true
	 */
	protected static boolean toLong(Object o, Field f, Workbook wb, Cell c, CellStyle cs, String formatMask) {
		boolean isUpdated = true;

		try {

			c.setCellValue((Long) f.get(o));

			CellStyleUtils.applyCellStyle(wb, c, cs, formatMask);

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
	 * @return false if problem otherwise true
	 */
	protected static boolean toDouble(Object o, Field f, Workbook wb, Cell c, CellStyle cs, String formatMask,
			String transformMask) {
		boolean isUpdated = true;

		try {
			// FIXME use the comment below to manage decimalScale
			// Double d = (Double) f.get(o);
			// BigDecimal bd = new BigDecimal(d);
			// bd.setScale(2, BigDecimal.ROUND_HALF_UP);

			if (StringUtils.isNotBlank(transformMask)) {
				DecimalFormat df = new DecimalFormat(transformMask);
				c.setCellValue(df.format((Double) f.get(o)));
				CellStyleUtils.applyCellStyle(wb, c, cs);
			} else {
				c.setCellValue((Double) f.get(o));
				CellStyleUtils.applyCellStyle(wb, c, cs, formatMask);
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
	 * @return false if problem otherwise true
	 */
	protected static boolean toBigDecimal(Object o, Field f, Workbook wb, Cell c, CellStyle cs, String formatMask,
			String transformMask) {
		boolean isUpdated = true;

		try {

			BigDecimal bd = (BigDecimal) f.get(o);

			// FIXME use the comment below to manage decimalScale
			// bd.setScale(2, BigDecimal.ROUND_HALF_UP);

			Double dBigDecimal = bd.doubleValue();
			if (StringUtils.isNotBlank(transformMask)) {
				DecimalFormat df = new DecimalFormat(transformMask);
				c.setCellValue(df.format(dBigDecimal));
				CellStyleUtils.applyCellStyle(wb, c, cs);

			} else {
				c.setCellValue(dBigDecimal);
				CellStyleUtils.applyCellStyle(wb, c, cs, formatMask);
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
	 * @return false if problem otherwise true
	 */
	protected static boolean toDate(Object o, Field f, Workbook wb, Cell c, CellStyle cs, String formatMask,
			String transformMask) {
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
						CellStyleUtils.applyCellStyle(wb, c, cs);
					} catch (Exception e) {
						throw new JAEXConverterException(JAEXExceptionMessage.JAEXConverterException_Date.getMessage(),
								e);
					}

				} else if (StringUtils.isNotBlank(formatMask)) {
					// apply format mask
					c.setCellValue(dDate);
					CellStyleUtils.applyCellStyle(wb, c, cs, formatMask);

				} else {
					// apply default date mask
					c.setCellValue(dDate);
					CellStyleUtils.applyCellStyle(wb, c, cs, "yyyy-MM-dd");

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
	 * @return false if problem otherwise true
	 */
	protected static boolean toFloat(Object o, Field f, Workbook wb, Cell c, CellStyle cs, String formatMask) {
		boolean isUpdated = true;

		try {
			c.setCellValue((Float) f.get(o));

			CellStyleUtils.applyCellStyle(wb, c, cs, formatMask);

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
	 * @return false if problem otherwise true
	 */
	protected static boolean toBoolean(Object o, Field f, Workbook wb, Cell c, CellStyle cs, String transformMask) {
		boolean isUpdated = true;

		try {
			Boolean bBoolean = (Boolean) f.get(o);
			if (StringUtils.isNotBlank(transformMask)) {
				// apply format mask defined at transform mask
				String[] split = transformMask.split("/");
				c.setCellValue((bBoolean == null ? "" : (bBoolean ? split[0] : split[1])));
				CellStyleUtils.applyCellStyle(wb, c, cs);

			} else {
				// locale mode
				c.setCellValue((bBoolean == null ? "" : bBoolean).toString());
				CellStyleUtils.applyCellStyle(wb, c, cs);
			}

		} catch (Exception e) {
			// TODO: handle exception
			isUpdated = false;
		}
		return isUpdated;
	}

}
