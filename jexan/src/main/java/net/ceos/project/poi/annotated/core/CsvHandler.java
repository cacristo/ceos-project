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

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import net.ceos.project.poi.annotated.annotation.XlsElement;
import net.ceos.project.poi.annotated.definition.ExceptionMessage;
import net.ceos.project.poi.annotated.exception.ConverterException;

/**
 * Manage all the default type of values to apply/read to one field.<br>
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public class CsvHandler {

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

	private CsvHandler() {
		/* private constructor to hide the implicit public */
	}

	/* Reader methods */

	/**
	 * Read a Date value from the CSV file.
	 * 
	 * @param o
	 *            the object
	 * @param field
	 *            the field
	 * @param xlsAnnotation
	 *            the {@link XlsElement} annotation
	 * @param values
	 *            the array with the content at one line
	 * @param idx
	 *            the index of the field
	 * @throws ConverterException
	 */
	protected static void dateReader(final Object o, final Field field, final XlsElement xlsAnnotation,
			final String[] values, final int idx) throws ConverterException {
		if (StringUtils.isNotBlank(values[idx])) {
			try {
				field.set(o, applyMaskToDate(xlsAnnotation, values, idx));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new ConverterException(ExceptionMessage.CONVERTER_DATE.getMessage(), e);
			}
		}
	}

	/**
	 * Apply a LocalDate value to the object.
	 * 
	 * @param o
	 *            the object
	 * @param field
	 *            the field
	 * @param xlsAnnotation
	 *            the {@link XlsElement} annotation
	 * @param values
	 *            the array with the content at one line
	 * @param idx
	 *            the index of the field
	 * @throws ConverterException
	 */
	protected static void localDateReader(final Object o, final Field field, final XlsElement xlsAnnotation,
			final String[] values, final int idx) throws ConverterException {
		if (StringUtils.isNotBlank(values[idx])) {
			try {
				field.set(o, applyMaskToDate(xlsAnnotation, values, idx).toInstant().atZone(ZoneId.systemDefault())
						.toLocalDate());
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new ConverterException(ExceptionMessage.CONVERTER_LOCALDATE.getMessage(), e);
			}
		}
	}

	/**
	 * Apply a LocalDateTime value to the object.
	 * 
	 * @param o
	 *            the object
	 * @param field
	 *            the field
	 * @param xlsAnnotation
	 *            the {@link XlsElement} annotation
	 * @param values
	 *            the array with the content at one line
	 * @param idx
	 *            the index of the field
	 * @throws ConverterException
	 */
	protected static void localDateTimeReader(final Object o, final Field field, final XlsElement xlsAnnotation,
			final String[] values, final int idx) throws ConverterException {
		if (StringUtils.isNotBlank(values[idx])) {
			try {
				field.set(o, applyMaskToDate(xlsAnnotation, values, idx).toInstant().atZone(ZoneId.systemDefault())
						.toLocalDateTime());
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new ConverterException(ExceptionMessage.CONVERTER_LOCALDATETIME.getMessage(), e);
			}
		}
	}

	/**
	 * Manage the mask to apply to a Date object.
	 * 
	 * @param xlsAnnotation
	 *            the {@link XlsElement} annotation
	 * @param values
	 *            the array with the content at one line
	 * @param idx
	 *            the index of the field
	 * @throws ConverterException
	 */
	private static Date applyMaskToDate(final XlsElement xlsAnnotation, final String[] values, final int idx)
			throws ConverterException {
		Date dateConverted = null;
		String date = values[idx];
		if (StringUtils.isNotBlank(date)) {

			String tM = xlsAnnotation.transformMask();
			String fM = xlsAnnotation.formatMask();
			String decorator = StringUtils.isEmpty(tM) ? (StringUtils.isEmpty(fM) ? Constants.DD_MMM_YYYY_HH_MM_SS : fM)
					: tM;

			SimpleDateFormat dt = new SimpleDateFormat(decorator);
			try {
				dateConverted = dt.parse(date);
			} catch (ParseException e) {
				/*
				 * if date decorator do not match with a valid mask launch
				 * exception
				 */
				throw new ConverterException(ExceptionMessage.CONVERTER_DATE.getMessage(), e);
			}
		}
		return dateConverted;
	}

	/**
	 * Apply a String value to the object.
	 * 
	 * @param o
	 *            the object
	 * @param field
	 *            the field
	 * @param values
	 *            the array with the content at one line
	 * @param idx
	 *            the index of the field
	 * @throws ConverterException
	 */
	protected static void stringReader(final Object o, final Field field, final String[] values, final int idx)
			throws ConverterException {
		try {
			field.set(o, values[idx]);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new ConverterException(ExceptionMessage.CONVERTER_STRING.getMessage(), e);
		}
	}

	/**
	 * Apply a Short value to the object.
	 * 
	 * @param o
	 *            the object
	 * @param field
	 *            the field
	 * @param values
	 *            the array with the content at one line
	 * @param idx
	 *            the index of the field
	 * @throws ConverterException
	 */
	protected static void shortReader(final Object o, final Field field, final String[] values, final int idx)
			throws ConverterException {
		String iValue = values[idx];
		if (StringUtils.isNotBlank(iValue)) {
			try {
				field.set(o, Short.valueOf(iValue));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new ConverterException(ExceptionMessage.CONVERTER_SHORT.getMessage(), e);
			}
		}
	}

	/**
	 * Apply a Integer value to the object.
	 * 
	 * @param o
	 *            the object
	 * @param field
	 *            the field
	 * @param values
	 *            the array with the content at one line
	 * @param idx
	 *            the index of the field
	 * @throws ConverterException
	 */
	protected static void integerReader(final Object o, final Field field, final String[] values, final int idx)
			throws ConverterException {
		String iValue = values[idx];
		if (StringUtils.isNotBlank(iValue)) {
			try {
				field.set(o, Integer.valueOf(iValue));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new ConverterException(ExceptionMessage.CONVERTER_INTEGER.getMessage(), e);
			}
		}
	}

	/**
	 * Apply a Long value to the object.
	 * 
	 * @param o
	 *            the object
	 * @param field
	 *            the field
	 * @param values
	 *            the array with the content at one line
	 * @param idx
	 *            the index of the field
	 * @throws ConverterException
	 */
	protected static void longReader(final Object o, final Field field, final String[] values, final int idx)
			throws ConverterException {
		String dValue = values[idx];
		if (StringUtils.isNotBlank(dValue)) {
			try {
				field.set(o, Double.valueOf(dValue).longValue());
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new ConverterException(ExceptionMessage.CONVERTER_LONG.getMessage(), e);
			}
		}
	}

	/**
	 * Apply a Double value to the object.
	 * 
	 * @param o
	 *            the object
	 * @param field
	 *            the field
	 * @param xlsAnnotation
	 *            the {@link XlsElement} annotation
	 * @param values
	 *            the array with the content at one line
	 * @param idx
	 *            the index of the field
	 * @throws ConverterException
	 */
	protected static void doubleReader(final Object o, final Field field, final XlsElement xlsAnnotation,
			final String[] values, final int idx) throws ConverterException {
		String dPValue = values[idx];
		if (StringUtils.isNotBlank(dPValue)) {
			try {
				if (StringUtils.isNotBlank(xlsAnnotation.transformMask())) {
					field.set(o, Double.parseDouble(dPValue.replace(Constants.COMMA, Constants.DOT)));
				} else if (StringUtils.isNotBlank(xlsAnnotation.formatMask())) {
					field.set(o, Double.parseDouble(dPValue.replace(Constants.COMMA, Constants.DOT)));
				} else {
					field.set(o, Double.parseDouble(dPValue));
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new ConverterException(ExceptionMessage.CONVERTER_DOUBLE.getMessage(), e);
			}
		}
	}

	/**
	 * Apply a BigDecimal value to the object.
	 * 
	 * @param o
	 *            the object
	 * @param field
	 *            the field
	 * @param values
	 *            the array with the content at one line
	 * @param idx
	 *            the index of the field
	 * @throws ConverterException
	 */
	protected static void bigDecimalReader(final Object o, final Field field, final String[] values, final int idx)
			throws ConverterException {
		String dBdValue = values[idx];
		if (dBdValue != null) {
			try {
				field.set(o, BigDecimal.valueOf(Double.valueOf(dBdValue)));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new ConverterException(ExceptionMessage.CONVERTER_BIGDECIMAL.getMessage(), e);
			}
		}
	}

	/**
	 * Apply a Float value to the object.
	 * 
	 * @param o
	 *            the object
	 * @param field
	 *            the field
	 * @param values
	 *            the array with the content at one line
	 * @param idx
	 *            the index of the field
	 * @throws ConverterException
	 */
	protected static void floatReader(final Object o, final Field field, final String[] values, final int idx)
			throws ConverterException {
		String fValue = values[idx];
		if (StringUtils.isNotBlank(fValue)) {
			try {
				field.set(o, Float.valueOf(fValue));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new ConverterException(ExceptionMessage.CONVERTER_FLOAT.getMessage(), e);
			}
		}
	}

	/**
	 * Apply a Boolean value to the object.
	 * 
	 * @param o
	 *            the object
	 * @param field
	 *            the field
	 * @param xlsAnnotation
	 *            the {@link XlsElement} annotation
	 * @param values
	 *            the array with the content at one line
	 * @param idx
	 *            the index of the field
	 * @throws ConverterException
	 */
	protected static void booleanReader(final Object o, final Field field, final XlsElement xlsAnnotation,
			final String[] values, final int idx) throws ConverterException {
		String bool = values[idx];
		try {
			if (StringUtils.isNotBlank(xlsAnnotation.transformMask())) {
				/* apply format mask defined at transform mask */
				String[] split = xlsAnnotation.transformMask().split(Constants.SLASH);
				field.set(o, StringUtils.isNotBlank(bool) ? (split[0].equals(bool) ? true : false) : null);

			} else {
				/* locale mode */
				field.set(o, StringUtils.isNotBlank(bool) ? Boolean.valueOf(bool) : null);
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new ConverterException(ExceptionMessage.CONVERTER_BOOLEAN.getMessage(), e);
		}
	}

	/* Writer methods */

	/**
	 * Read a Date value from the file.
	 * 
	 * @param configCriteria
	 *            the {@link CConfigCriteria}
	 * @param object
	 *            the object
	 * @param field
	 *            the field
	 * @param idx
	 *            the index
	 * @return
	 * @throws ConverterException
	 */
	protected static boolean dateWriter(final CConfigCriteria configCriteria, final Object object, final Field field,
			final int idx, String tM, String fM) throws ConverterException {
		boolean isUpdated;
		try {
			configCriteria.getContent().put(idx, CsvHandler.toDate((Date) field.get(object), fM, tM));
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new ConverterException(ExceptionMessage.CONVERTER_DATE.getMessage(), e);
		}
		isUpdated = true;
		return isUpdated;
	}

	/**
	 * Read a LocalDate value from the file.
	 * 
	 * @param configCriteria
	 *            the {@link CConfigCriteria}
	 * @param object
	 *            the object
	 * @param field
	 *            the field
	 * @param idx
	 *            the index
	 * @return
	 * @throws ConverterException
	 */
	protected static boolean localDateWriter(final CConfigCriteria configCriteria, final Object object,
			final Field field, final int idx, String tM, String fM) throws ConverterException {
		boolean isUpdated;
		try {
			configCriteria.getContent().put(idx, CsvHandler.toLocalDate((LocalDate) field.get(object), fM, tM));
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new ConverterException(ExceptionMessage.CONVERTER_LOCALDATE.getMessage(), e);
		}
		isUpdated = true;
		return isUpdated;
	}

	/**
	 * Read a LocalDateTime value from the file.
	 * 
	 * @param configCriteria
	 *            the {@link CConfigCriteria}
	 * @param object
	 *            the object
	 * @param field
	 *            the field
	 * @param idx
	 *            the index
	 * @return
	 * @throws ConverterException
	 */
	protected static boolean localDateTimeWriter(final CConfigCriteria configCriteria, final Object object,
			final Field field, final int idx, String tM, String fM) throws ConverterException {
		boolean isUpdated;
		try {
			configCriteria.getContent().put(idx, CsvHandler.toLocalDateTime((LocalDateTime) field.get(object), fM, tM));
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new ConverterException(ExceptionMessage.CONVERTER_LOCALDATETIME.getMessage(), e);
		}
		isUpdated = true;
		return isUpdated;
	}

	/**
	 * Read a String value from the file.
	 * 
	 * @param configCriteria
	 *            the {@link CConfigCriteria}
	 * @param object
	 *            the object
	 * @param field
	 *            the field
	 * @param idx
	 *            the index
	 * @return
	 * @throws ConverterException
	 */
	protected static boolean stringWriter(final CConfigCriteria configCriteria, final Object object, final Field field,
			final int idx) throws ConverterException {
		boolean isUpdated;
		try {
			configCriteria.getContent().put(idx,
					(String) field.get(object) != null ? (String) field.get(object) : StringUtils.EMPTY);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new ConverterException(ExceptionMessage.CONVERTER_STRING.getMessage(), e);
		}
		isUpdated = true;
		return isUpdated;
	}

	/**
	 * Read a Short value from the file.
	 * 
	 * @param configCriteria
	 *            the {@link CConfigCriteria}
	 * @param object
	 *            the object
	 * @param field
	 *            the field
	 * @param idx
	 *            the index
	 * @return
	 * @throws ConverterException
	 */
	protected static boolean shortWriter(final CConfigCriteria configCriteria, final Object object, final Field field,
			final int idx) throws ConverterException {
		boolean isUpdated;
		try {
			configCriteria.getContent().put(idx,
					(Short) field.get(object) != null ? ((Short) field.get(object)).toString() : StringUtils.EMPTY);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new ConverterException(ExceptionMessage.CONVERTER_SHORT.getMessage(), e);
		}
		isUpdated = true;
		return isUpdated;
	}

	/**
	 * Read a Integer value from the file.
	 * 
	 * @param configCriteria
	 *            the {@link CConfigCriteria}
	 * @param object
	 *            the object
	 * @param field
	 *            the field
	 * @param idx
	 *            the index
	 * @return
	 * @throws ConverterException
	 */
	protected static boolean integerWriter(final CConfigCriteria configCriteria, final Object object, final Field field,
			final int idx) throws ConverterException {
		boolean isUpdated;
		try {
			configCriteria.getContent().put(idx,
					(Integer) field.get(object) != null ? ((Integer) field.get(object)).toString() : StringUtils.EMPTY);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new ConverterException(ExceptionMessage.CONVERTER_INTEGER.getMessage(), e);
		}
		isUpdated = true;
		return isUpdated;
	}

	/**
	 * Read a Long value from the file.
	 * 
	 * @param configCriteria
	 *            the {@link CConfigCriteria}
	 * @param object
	 *            the object
	 * @param field
	 *            the field
	 * @param idx
	 *            the index
	 * @return
	 * @throws ConverterException
	 */
	protected static boolean longWriter(final CConfigCriteria configCriteria, final Object object, final Field field,
			final int idx) throws ConverterException {
		boolean isUpdated;
		try {
			configCriteria.getContent().put(idx,
					(Long) field.get(object) != null ? ((Long) field.get(object)).toString() : StringUtils.EMPTY);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new ConverterException(ExceptionMessage.CONVERTER_LONG.getMessage(), e);
		}
		isUpdated = true;
		return isUpdated;
	}

	/**
	 * Read a Double value from the file.
	 * 
	 * @param configCriteria
	 *            the {@link CConfigCriteria}
	 * @param object
	 *            the object
	 * @param field
	 *            the field
	 * @param idx
	 *            the index
	 * @return
	 * @throws ConverterException
	 */
	protected static boolean doubleWriter(final CConfigCriteria configCriteria, final Object object, final Field field,
			final int idx, String tM, String fM) throws ConverterException {
		boolean isUpdated;
		try {
			configCriteria.getContent().put(idx, CsvHandler.toDouble((Double) field.get(object), fM, tM));
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new ConverterException(ExceptionMessage.CONVERTER_DOUBLE.getMessage(), e);
		}
		isUpdated = true;
		return isUpdated;
	}

	/**
	 * Read a BigDecimal value from the file.
	 * 
	 * @param configCriteria
	 *            the {@link CConfigCriteria}
	 * @param object
	 *            the object
	 * @param field
	 *            the field
	 * @param idx
	 *            the index
	 * @return
	 * @throws ConverterException
	 */
	protected static boolean bigDecimalWriter(final CConfigCriteria configCriteria, final Object object,
			final Field field, final int idx) throws ConverterException {
		boolean isUpdated;
		try {
			configCriteria.getContent().put(idx, (BigDecimal) field.get(object) != null
					? ((BigDecimal) field.get(object)).toString() : StringUtils.EMPTY);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new ConverterException(ExceptionMessage.CONVERTER_BIGDECIMAL.getMessage(), e);
		}
		isUpdated = true;
		return isUpdated;
	}

	/**
	 * Read a Float value from the file.
	 * 
	 * @param configCriteria
	 *            the {@link CConfigCriteria}
	 * @param object
	 *            the object
	 * @param field
	 *            the field
	 * @param idx
	 *            the index
	 * @return
	 * @throws ConverterException
	 */
	protected static boolean floatWriter(final CConfigCriteria configCriteria, final Object object, final Field field,
			final int idx) throws ConverterException {
		boolean isUpdated;
		try {
			configCriteria.getContent().put(idx,
					(Float) field.get(object) != null ? ((Float) field.get(object)).toString() : StringUtils.EMPTY);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new ConverterException(ExceptionMessage.CONVERTER_FLOAT.getMessage(), e);
		}
		isUpdated = true;
		return isUpdated;
	}

	/**
	 * Read a Boolean value from the file.
	 * 
	 * @param configCriteria
	 *            the {@link CConfigCriteria}
	 * @param object
	 *            the object
	 * @param field
	 *            the field
	 * @param idx
	 *            the index
	 * @return
	 * @throws ConverterException
	 */
	protected static boolean booleanWriter(final CConfigCriteria configCriteria, final Object object, final Field field,
			final int idx) throws ConverterException {
		boolean isUpdated;
		try {
			configCriteria.getContent().put(idx,
					(Boolean) field.get(object) != null ? ((Boolean) field.get(object)).toString() : StringUtils.EMPTY);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new ConverterException(ExceptionMessage.CONVERTER_BOOLEAN.getMessage(), e);
		}
		isUpdated = true;
		return isUpdated;
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
		String dateMasked = StringUtils.EMPTY;
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
					dateMasked = applyMaskToDate(value, Constants.DD_MMM_YYYY_HH_MM_SS);
				}
			} catch (Exception e) {
				throw new ConverterException(ExceptionMessage.CONVERTER_DATE.getMessage(), e);
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
		String dateMasked = StringUtils.EMPTY;
		if (value != null) {
			try {
				if (StringUtils.isNotBlank(transformMask)) {
					// apply transformation mask
					dateMasked = applyMaskToDate(Date.from(value.atStartOfDay(ZoneId.systemDefault()).toInstant()),
							transformMask);

				} else if (StringUtils.isNotBlank(formatMask)) {
					// apply format mask
					dateMasked = applyMaskToDate(Date.from(value.atStartOfDay(ZoneId.systemDefault()).toInstant()),
							formatMask);

				} else {
					// default mask
					dateMasked = applyMaskToDate(Date.from(value.atStartOfDay(ZoneId.systemDefault()).toInstant()),
							Constants.DD_MMM_YYYY_HH_MM_SS);
				}
			} catch (Exception e) {
				throw new ConverterException(ExceptionMessage.CONVERTER_LOCALDATE.getMessage(), e);
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
	protected static String toLocalDateTime(final LocalDateTime value, final String formatMask,
			final String transformMask) throws ConverterException {
		String dateMasked = StringUtils.EMPTY;
		if (value != null) {
			try {
				if (StringUtils.isNotBlank(transformMask)) {
					// apply transformation mask
					dateMasked = applyMaskToDate(Date.from(value.atZone(ZoneId.systemDefault()).toInstant()),
							transformMask);

				} else if (StringUtils.isNotBlank(formatMask)) {
					// apply format mask
					dateMasked = applyMaskToDate(Date.from(value.atZone(ZoneId.systemDefault()).toInstant()),
							formatMask);

				} else {
					// default mask
					dateMasked = applyMaskToDate(Date.from(value.atZone(ZoneId.systemDefault()).toInstant()),
							Constants.DD_MMM_YYYY_HH_MM_SS);
				}
			} catch (Exception e) {
				throw new ConverterException(ExceptionMessage.CONVERTER_LOCALDATETIME.getMessage(), e);
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
			throw new ConverterException(ExceptionMessage.CONVERTER_DATE.getMessage());
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
				return df.format((Double) value).replace(Constants.COMMA, Constants.DOT);

			} else if (StringUtils.isNotBlank(formatMask)) {
				// apply format mask
				DecimalFormat df = new DecimalFormat(formatMask);
				return df.format((Double) value).replace(Constants.COMMA, Constants.DOT);

			} else {
				return value.toString().replace(Constants.COMMA, Constants.DOT); // the
																					// exact
																					// value
			}
		}
		return StringUtils.EMPTY; // empty field
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
		return StringUtils.EMPTY;
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

			String method = Constants.GET + f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1);

			Method m = o.getClass().getDeclaredMethod(method, argTypes);

			Object objEnum = m.invoke(o, (Object[]) null);

			if (objEnum != null) {
				// apply the enum value
				return objEnum.toString();
			}

		} catch (Exception e) {
			throw new ConverterException(ExceptionMessage.CONVERTER_BOOLEAN.getMessage(), e);
		}
		return StringUtils.EMPTY;
	}
}
