package org.jaexcel.framework.JAEX.engine;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.jaexcel.framework.JAEX.definition.ExceptionMessage;
import org.jaexcel.framework.JAEX.exception.ConverterException;

public class CsvUtils {

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
	 * Apply a date value at the field.
	 * 
	 * @param value
	 *            the value
	 * @param formatMask
	 *            the decorator mask
	 * @param transformMask
	 *            the transformation mask
	 * @return false if problem otherwise true
	 * @throws ConverterException
	 */
	protected static String toDate(Date value, String formatMask, String transformMask) throws ConverterException {
		if (value != null) {
			if (StringUtils.isNotBlank(transformMask)) {
				// apply transformation mask
				try {
					SimpleDateFormat dt = new SimpleDateFormat(transformMask);
					String dateFormated = dt.format(value);
					if (dateFormated.equals(transformMask)) {
						// if date decorator do not match with a valid mask
						// launch exception
						throw new ConverterException(ExceptionMessage.ConverterException_Date.getMessage());
					}
					return dateFormated;

				} catch (Exception e) {
					throw new ConverterException(ExceptionMessage.ConverterException_Date.getMessage(), e);
				}

			} else if (StringUtils.isNotBlank(formatMask)) {
				// apply format mask
				try {
					SimpleDateFormat dt = new SimpleDateFormat(formatMask);
					String dateFormated = dt.format(value);
					if (dateFormated.equals(formatMask)) {
						// if date decorator do not match with a valid mask
						// launch exception
						throw new ConverterException(ExceptionMessage.ConverterException_Date.getMessage());
					}
					return dateFormated;

				} catch (Exception e) {
					throw new ConverterException(ExceptionMessage.ConverterException_Date.getMessage(), e);
				}

			} else {
				SimpleDateFormat dt = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
				return dt.format(value);
			}
		}
		return ""; // empty field
	}

	/**
	 * Apply a double value at the field.
	 * 
	 * @param value
	 *            the value
	 * @param formatMask
	 *            the decorator mask
	 * @param transformMask
	 *            the transformation mask
	 * @return false if problem otherwise true
	 */
	protected static String toDouble(Double value, String formatMask, String transformMask) {
		if (value != null) {
			if (StringUtils.isNotBlank(transformMask)) {
				// apply transformation mask
				DecimalFormat df = new DecimalFormat(transformMask);
				return df.format((Double) value).replace(",", ".");

			} else if (StringUtils.isNotBlank(formatMask)) {
				// apply format mask
				DecimalFormat df = new DecimalFormat(formatMask);
				return df.format((Double) value).replace(",", ".");

			} else {
				return value.toString().replace(",", "."); // the exact value
			}
		}
		return ""; // empty field
	}

	/**
	 * Apply a big decimal value at the field.
	 * 
	 * @param value
	 *            the value
	 * @param formatMask
	 *            the decorator mask
	 * @param transformMask
	 *            the transformation mask
	 * @return false if problem otherwise true
	 */
	protected static String toBigDecimal(BigDecimal value, String formatMask, String transformMask) {
		if (value != null) {
			Double dBigDecimal = value.doubleValue();
			if (StringUtils.isNotBlank(transformMask)) {
				// apply transformation mask
				DecimalFormat df = new DecimalFormat(transformMask);
				return df.format(dBigDecimal);

			} else if (StringUtils.isNotBlank(formatMask)) {
				// apply format mask
				DecimalFormat df = new DecimalFormat(formatMask);
				return df.format(dBigDecimal);

			} else {
				return value.toString(); // the exact value
			}
		}
		return "";
	}

	/**
	 * Apply a enum value at the field.
	 * 
	 * @param o
	 *            the object
	 * @param f
	 *            the field
	 * @return
	 */
	protected static String toEnum(Object o, Field f) {

		try {

			@SuppressWarnings("rawtypes")
			Class[] argTypes = {};

			String method = "get" + f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1);

			Method m = o.getClass().getDeclaredMethod(method, argTypes);

			Object objEnum = m.invoke(o, (Object[]) null);

			if (objEnum != null) {
				// apply the enum value
				return (String) objEnum.toString();
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return "";
	}
}
