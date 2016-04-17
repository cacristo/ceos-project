package net.ceos.project.poi.annotated.core;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import net.ceos.project.poi.annotated.definition.ExceptionMessage;
import net.ceos.project.poi.annotated.exception.ConverterException;

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

	private CsvUtils() {
		/* private constructor to hide the implicit public */
	}

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
	protected static String toDate(final Date value, final String formatMask, final String transformMask)
			throws ConverterException {
		String dateMasked = "";
		if (value != null) {
			try {
				if (StringUtils.isNotBlank(transformMask)) {
					// apply transformation mask
					dateMasked = applyMaskToDate(value, transformMask);

				} else if (StringUtils.isNotBlank(formatMask)) {
					// apply format mask
					dateMasked = applyMaskToDate(value, formatMask);

				} else {
					// default mask
					dateMasked = applyMaskToDate(value, "dd-MMM-yyyy HH:mm:ss");
				}
			} catch (Exception e) {
				throw new ConverterException(ExceptionMessage.ConverterException_Date.getMessage(), e);
			}
		}
		return dateMasked;
	}

	/**
	 * Apply a date time value at the field.
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
	protected static String toLocalDate(final LocalDate value, final String formatMask, final String transformMask)
			throws ConverterException {
		String dateMasked = "";
		if (value != null) {
			try {
				if (StringUtils.isNotBlank(transformMask)) {
					// apply transformation mask
					dateMasked = applyMaskToDate(Date.from(value.atStartOfDay(ZoneId.systemDefault()).toInstant()), transformMask);

				} else if (StringUtils.isNotBlank(formatMask)) {
					// apply format mask
					dateMasked = applyMaskToDate(Date.from(value.atStartOfDay(ZoneId.systemDefault()).toInstant()), formatMask);

				} else {
					// default mask
					dateMasked = applyMaskToDate(Date.from(value.atStartOfDay(ZoneId.systemDefault()).toInstant()), "dd-MMM-yyyy HH:mm:ss");
				}
			} catch (Exception e) {
				throw new ConverterException(ExceptionMessage.ConverterException_Date.getMessage(), e);
			}
		}
		return dateMasked;
	}

	/**
	 * Apply a date time value at the field.
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
	protected static String toLocalDateTime(final LocalDateTime value, final String formatMask, final String transformMask)
			throws ConverterException {
		String dateMasked = "";
		if (value != null) {
			try {
				if (StringUtils.isNotBlank(transformMask)) {
					// apply transformation mask
					dateMasked = applyMaskToDate(Date.from(value.atZone(ZoneId.systemDefault()).toInstant()), transformMask);

				} else if (StringUtils.isNotBlank(formatMask)) {
					// apply format mask
					dateMasked = applyMaskToDate(Date.from(value.atZone(ZoneId.systemDefault()).toInstant()), formatMask);

				} else {
					// default mask
					dateMasked = applyMaskToDate(Date.from(value.atZone(ZoneId.systemDefault()).toInstant()), "dd-MMM-yyyy HH:mm:ss");
				}
			} catch (Exception e) {
				throw new ConverterException(ExceptionMessage.ConverterException_Date.getMessage(), e);
			}
		}
		return dateMasked;
	}

	/**
	 * Apply mask to date based as the mask passed as parameter.
	 * 
	 * @param value
	 * @param mask
	 * @return
	 * @throws ConverterException
	 */
	private static String applyMaskToDate(final Date value, final String mask) throws ConverterException {
		SimpleDateFormat dt = new SimpleDateFormat(mask);
		String dateFormated = dt.format(value);
		if (dateFormated.equals(mask)) {
			// if date decorator do not match with a valid mask
			// launch exception
			throw new ConverterException(ExceptionMessage.ConverterException_Date.getMessage());
		}
		return dateFormated;
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
	protected static String toDouble(final Double value, final String formatMask, final String transformMask) {
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
	protected static String toBigDecimal(final BigDecimal value, final String formatMask, final String transformMask) {
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
	 * @throws ConverterException
	 */
	protected static String toEnum(final Object o, final Field f) throws ConverterException {
		try {
			@SuppressWarnings("rawtypes")
			Class[] argTypes = {};

			String method = "get" + f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1);

			Method m = o.getClass().getDeclaredMethod(method, argTypes);

			Object objEnum = m.invoke(o, (Object[]) null);

			if (objEnum != null) {
				// apply the enum value
				return objEnum.toString();
			}

		} catch (Exception e) {
			throw new ConverterException(ExceptionMessage.ConverterException_Boolean.getMessage(), e);
		}
		return "";
	}
}
