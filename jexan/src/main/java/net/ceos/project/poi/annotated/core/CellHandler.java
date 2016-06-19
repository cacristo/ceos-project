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
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;

import net.ceos.project.poi.annotated.annotation.XlsElement;
import net.ceos.project.poi.annotated.definition.ExceptionMessage;
import net.ceos.project.poi.annotated.exception.ConverterException;
import net.ceos.project.poi.annotated.exception.CustomizedRulesException;
import net.ceos.project.poi.annotated.exception.ElementException;
import net.ceos.project.poi.annotated.exception.WorkbookException;

/**
 * Manage all the default type of values to apply/read to one cell.
 * <p>
 * <b>To improve: </b> manage correctly the cached formulas
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public class CellHandler {

	// object type
	public static final String OBJECT_DATE = "java.util.Date";
	public static final String OBJECT_STRING = "java.lang.String";
	public static final String OBJECT_SHORT = "java.lang.Short";
	public static final String OBJECT_INTEGER = "java.lang.Integer";
	public static final String OBJECT_LONG = "java.lang.Long";
	public static final String OBJECT_DOUBLE = "java.lang.Double";
	public static final String OBJECT_FLOAT = "java.lang.Float";
	public static final String OBJECT_BIGDECIMAL = "java.math.BigDecimal";
	public static final String OBJECT_BOOLEAN = "java.lang.Boolean";
	// primitive type
	public static final String PRIMITIVE_SHORT = "short";
	public static final String PRIMITIVE_INTEGER = "int";
	public static final String PRIMITIVE_LONG = "long";
	public static final String PRIMITIVE_DOUBLE = "double";
	public static final String PRIMITIVE_FLOAT = "float";
	public static final String PRIMITIVE_BOOLEAN = "boolean";

	private static final String[] array = { OBJECT_DATE, OBJECT_STRING, OBJECT_SHORT, OBJECT_INTEGER, OBJECT_LONG,
			OBJECT_DOUBLE, OBJECT_FLOAT, OBJECT_BIGDECIMAL, OBJECT_BOOLEAN, PRIMITIVE_SHORT, PRIMITIVE_INTEGER,
			PRIMITIVE_LONG, PRIMITIVE_DOUBLE, PRIMITIVE_FLOAT, PRIMITIVE_BOOLEAN };

	private CellHandler() {
		/* private constructor to hide the implicit public */
	}

	/* authorized types */

	protected static boolean isAuthorizedType(Field field) {
		return Arrays.asList(array).contains(field.getType().getName()) || field.getType().isEnum();
	}

	/* Reader methods */

	/**
	 * Read a string value from the Cell.
	 * 
	 * @param object
	 *            the object
	 * @param field
	 *            the {@link Field} to set
	 * @param cell
	 *            the {@link Cell} to read
	 * @throws ConverterException
	 *             the conversion exception type
	 */
	protected static void stringReader(final Object object, final Field field, final Cell cell)
			throws ConverterException {
		if (StringUtils.isNotBlank(readCell(cell))) {
			try {
				field.set(object, readCell(cell));
			} catch (IllegalArgumentException e) {
				throw new ConverterException(ExceptionMessage.CONVERTER_STRING.getMessage(), e);
			} catch (IllegalAccessException e) {
				throw new ConverterException(ExceptionMessage.CONVERTER_STRING.getMessage(), e);
			}
		}
	}

	/**
	 * Read a short value from the Cell.
	 * 
	 * @param object
	 *            the object
	 * @param field
	 *            the {@link Field} to set
	 * @param cell
	 *            the {@link Cell} to read
	 * @throws ConverterException
	 *             the conversion exception type
	 */
	protected static void shortReader(final Object object, final Field field, final Cell cell)
			throws ConverterException {
		if (StringUtils.isNotBlank(readCell(cell))) {
			try {
				field.set(object, Double.valueOf(readCell(cell)).shortValue());
			} catch (IllegalArgumentException e) {
				throw new ConverterException(ExceptionMessage.CONVERTER_SHORT.getMessage(), e);
			} catch (IllegalAccessException e) {
				throw new ConverterException(ExceptionMessage.CONVERTER_SHORT.getMessage(), e);
			}
		}
	}

	/**
	 * Read a integer value from the Cell.
	 * 
	 * @param object
	 *            the object
	 * @param field
	 *            the {@link Field} to set
	 * @param cell
	 *            the {@link Cell} to read
	 * @throws ConverterException
	 *             the conversion exception type
	 */
	protected static void integerReader(final Object object, final Field field, final Cell cell)
			throws ConverterException {
		if (StringUtils.isNotBlank(readCell(cell))) {
			try {
				field.set(object, Double.valueOf(readCell(cell)).intValue());
			} catch (IllegalArgumentException e) {
				throw new ConverterException(ExceptionMessage.CONVERTER_INTEGER.getMessage(), e);
			} catch (IllegalAccessException e) {
				throw new ConverterException(ExceptionMessage.CONVERTER_INTEGER.getMessage(), e);
			}
		}
	}

	/**
	 * Read a long value from the Cell.
	 * 
	 * @param object
	 *            the object
	 * @param field
	 *            the {@link Field} to set
	 * @param cell
	 *            the {@link Cell} to read
	 * @throws ConverterException
	 *             the conversion exception type
	 */
	protected static void longReader(final Object object, final Field field, final Cell cell)
			throws ConverterException {
		if (StringUtils.isNotBlank(readCell(cell))) {
			try {
				field.set(object, Double.valueOf(readCell(cell)).longValue());
			} catch (IllegalArgumentException e) {
				throw new ConverterException(ExceptionMessage.CONVERTER_LONG.getMessage(), e);
			} catch (IllegalAccessException e) {
				throw new ConverterException(ExceptionMessage.CONVERTER_LONG.getMessage(), e);
			}
		}
	}

	/**
	 * Read a double value from the Cell.
	 * 
	 * @param object
	 *            the object
	 * @param field
	 *            the {@link Field} to set
	 * @param cell
	 *            the {@link Cell} to read
	 * @param xlsAnnotation
	 *            the {@link XlsElement} element
	 * @throws ConverterException
	 *             the conversion exception type
	 */
	protected static void doubleReader(final Object object, final Field field, final Cell cell,
			final XlsElement xlsAnnotation) throws ConverterException {
		if (StringUtils.isNotBlank(readCell(cell))) {
			try {
				if (StringUtils.isNotBlank(xlsAnnotation.transformMask())) {
					field.set(object, Double.valueOf(readCell(cell).replace(Constants.COMMA, Constants.DOT)));
				} else {
					field.set(object, Double.valueOf(readCell(cell)));
				}
			} catch (IllegalArgumentException e) {
				throw new ConverterException(ExceptionMessage.CONVERTER_DOUBLE.getMessage(), e);
			} catch (IllegalAccessException e) {
				throw new ConverterException(ExceptionMessage.CONVERTER_DOUBLE.getMessage(), e);
			}
		}
	}

	/**
	 * Read a big decimal value from the Cell.
	 * 
	 * @param object
	 *            the object
	 * @param field
	 *            the {@link Field} to set
	 * @param cell
	 *            the {@link Cell} to read
	 * @param xlsAnnotation
	 *            the {@link XlsElement} element
	 * @throws ConverterException
	 *             the conversion exception type
	 */
	protected static void bigDecimalReader(final Object object, final Field field, final Cell cell,
			final XlsElement xlsAnnotation) throws ConverterException {
		if (StringUtils.isNotBlank(readCell(cell))) {
			try {
				if (StringUtils.isNotBlank(xlsAnnotation.transformMask())) {
					field.set(object,
							BigDecimal.valueOf(Double.valueOf(readCell(cell).replace(Constants.COMMA, Constants.DOT))));
				} else {
					field.set(object, BigDecimal.valueOf(Double.valueOf(readCell(cell))));
				}
			} catch (IllegalArgumentException e) {
				throw new ConverterException(ExceptionMessage.CONVERTER_BIGDECIMAL.getMessage(), e);
			} catch (IllegalAccessException e) {
				throw new ConverterException(ExceptionMessage.CONVERTER_BIGDECIMAL.getMessage(), e);
			}
		}
	}

	/**
	 * Read a date value from the Cell.
	 * 
	 * @param object
	 *            the object
	 * @param field
	 *            the {@link Field} to set
	 * @param cell
	 *            the {@link Cell} to read
	 * @param xlsAnnotation
	 *            the {@link XlsElement} element
	 * @throws ConverterException
	 *             the conversion exception type
	 */
	protected static void dateReader(final Object object, final Field field, final Cell cell,
			final XlsElement xlsAnnotation) throws ConverterException {
		if (StringUtils.isNotBlank(readCell(cell))) {
			try {
				if (StringUtils.isBlank(xlsAnnotation.transformMask())) {
					field.set(object, cell.getDateCellValue());
				} else {
					String date = cell.getStringCellValue();

					String tM = xlsAnnotation.transformMask();
					String fM = xlsAnnotation.formatMask();
					String decorator = StringUtils.isEmpty(tM)
							? (StringUtils.isEmpty(fM) ? CellStyleHandler.MASK_DECORATOR_DATE : fM) : tM;

					SimpleDateFormat dt = new SimpleDateFormat(decorator);

					Date dateConverted = dt.parse(date);
					field.set(object, dateConverted);
				}
				/*
				 * if date decorator do not match with a valid mask launch
				 * exception
				 */
			} catch (ParseException e) {
				throw new ConverterException(ExceptionMessage.CONVERTER_DATE.getMessage(), e);
			} catch (IllegalArgumentException e) {
				throw new ConverterException(ExceptionMessage.CONVERTER_DATE.getMessage(), e);
			} catch (IllegalAccessException e) {
				throw new ConverterException(ExceptionMessage.CONVERTER_DATE.getMessage(), e);
			}
		}
	}

	/**
	 * Read a float value from the Cell.
	 * 
	 * @param object
	 *            the object
	 * @param field
	 *            the {@link Field} to set
	 * @param cell
	 *            the {@link Cell} to read
	 * @throws ConverterException
	 *             the conversion exception type
	 */
	protected static void floatReader(final Object object, final Field field, final Cell cell)
			throws ConverterException {
		if (StringUtils.isNotBlank(readCell(cell))) {
			try {
				field.set(object, Double.valueOf(readCell(cell)).floatValue());
			} catch (IllegalArgumentException e) {
				throw new ConverterException(ExceptionMessage.CONVERTER_FLOAT.getMessage(), e);
			} catch (IllegalAccessException e) {
				throw new ConverterException(ExceptionMessage.CONVERTER_FLOAT.getMessage(), e);
			}
		}
	}

	/**
	 * Read a boolean value from the Cell.
	 * 
	 * @param object
	 *            the object
	 * @param field
	 *            the {@link Field} to set
	 * @param cell
	 *            the {@link Cell} to read
	 * @param xlsAnnotation
	 *            the {@link XlsElement} element
	 * @throws ConverterException
	 *             the conversion exception type
	 */
	protected static void booleanReader(final Object object, final Field field, final Cell cell,
			final XlsElement xlsAnnotation) throws ConverterException {
		String booleanValue = cell.getStringCellValue();
		if (StringUtils.isNotBlank(booleanValue)) {
			try {
				if (StringUtils.isNotBlank(xlsAnnotation.transformMask())) {
					/* apply format mask defined at transform mask */
					String[] split = xlsAnnotation.transformMask().split(Constants.SLASH);

					field.set(object, StringUtils.isNotBlank(booleanValue)
							? (split[0].equals(booleanValue) ? true : false) : null);
				} else {
					/* locale mode */
					field.set(object, StringUtils.isNotBlank(booleanValue) ? Boolean.valueOf(booleanValue) : null);
				}
			} catch (IllegalArgumentException e) {
				throw new ConverterException(ExceptionMessage.CONVERTER_BOOLEAN.getMessage(), e);
			} catch (IllegalAccessException e) {
				throw new ConverterException(ExceptionMessage.CONVERTER_BOOLEAN.getMessage(), e);
			}
		}
	}

	/**
	 * Read an enumeration value from the Cell.
	 * 
	 * @param object
	 *            the object
	 * @param fT
	 *            the class of the field
	 * @param field
	 *            the {@link Field} to set
	 * @param cell
	 *            the {@link Cell} to read
	 * @throws ConverterException
	 *             the conversion exception type
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected static void enumReader(final Object object, final Class<?> fT, final Field field, final Cell cell)
			throws ConverterException {
		if (StringUtils.isNotBlank(cell.getStringCellValue())) {
			try {
				field.set(object, Enum.valueOf((Class<Enum>) fT, cell.getStringCellValue()));
			} catch (IllegalArgumentException e) {
				throw new ConverterException(ExceptionMessage.CONVERTER_ENUM.getMessage(), e);
			} catch (IllegalAccessException e) {
				throw new ConverterException(ExceptionMessage.CONVERTER_ENUM.getMessage(), e);
			}
		}
	}

	/* Writer methods */

	/**
	 * Apply a integer value at the Cell.
	 * 
	 * @param configCriteria
	 *            the {@link XConfigCriteria} object
	 * @param object
	 *            the object
	 * @param cell
	 *            the {@link Cell} to use
	 * @return false if problem otherwise true
	 * @throws WorkbookException
	 *             given when a not supported action.
	 */
	protected static boolean stringWriter(final XConfigCriteria configCriteria, final Object object, final Cell cell)
			throws WorkbookException {
		boolean isUpdated = true;
		try {
			/* ignore treatment if object null and is NOT a formula */
			if (configCriteria.getField().get(object) == null && !configCriteria.getElement().isFormula()) {
				return isUpdated;
			}
			// apply the formula, if exist, otherwise apply the value
			CellFormulaHandler.stringHandler(configCriteria, object, cell);

		} catch (ElementException e) {
			throw new ElementException(e.getMessage(), e);
		} catch (Exception e) {
			throw new ConverterException(ExceptionMessage.CONVERTER_STRING.getMessage(), e);
		}

		/* apply the style */
		CellStyleHandler.applyCellStyle(configCriteria, cell, CellStyleHandler.CELL_DECORATOR_GENERIC, null);

		/* apply the comment */
		CellCommentHandler.applyComment(configCriteria, object, cell);

		return isUpdated;
	}

	/**
	 * Apply a short value at the Cell.
	 * 
	 * @param configCriteria
	 *            the {@link XConfigCriteria} object
	 * @param object
	 *            the object
	 * @param cell
	 *            the {@link Cell} to use
	 * @return false if problem otherwise true
	 * @throws WorkbookException
	 *             given when a not supported action.
	 */
	protected static boolean shortWriter(final XConfigCriteria configCriteria, final Object object, final Cell cell)
			throws WorkbookException {
		boolean isUpdated = true;
		try {
			/* ignore treatment if object null and is NOT a formula */
			if (configCriteria.getField().get(object) == null && !configCriteria.getElement().isFormula()) {
				return isUpdated;
			}
			// apply the formula, if exist, otherwise apply the value
			CellFormulaHandler.shortHandler(configCriteria, object, cell);

		} catch (ElementException e) {
			throw new ElementException(e.getMessage(), e);
		} catch (Exception e) {
			throw new ConverterException(ExceptionMessage.CONVERTER_SHORT.getMessage(), e);
		}

		/* apply cell style */
		CellStyleHandler.applyCellStyle(configCriteria, cell, CellStyleHandler.CELL_DECORATOR_NUMERIC,
				CellStyleHandler.MASK_DECORATOR_INTEGER);

		/* apply the comment */
		CellCommentHandler.applyComment(configCriteria, object, cell);

		return isUpdated;
	}

	/**
	 * Apply a integer value at the Cell.
	 * 
	 * @param configCriteria
	 *            the {@link XConfigCriteria} object
	 * @param object
	 *            the object
	 * @param cell
	 *            the {@link Cell} to use
	 * @return false if problem otherwise true
	 * @throws WorkbookException
	 *             given when a not supported action.
	 */
	protected static boolean integerWriter(final XConfigCriteria configCriteria, final Object object, final Cell cell)
			throws WorkbookException {
		boolean isUpdated = true;
		try {
			/* ignore treatment if object null and is NOT a formula */
			if (configCriteria.getField().get(object) == null && !configCriteria.getElement().isFormula()) {
				return isUpdated;
			}
			// apply the formula, if exist, otherwise apply the value
			CellFormulaHandler.integerHandler(configCriteria, object, cell);

		} catch (ElementException e) {
			throw new ElementException(e.getMessage(), e);
		} catch (Exception e) {
			throw new ConverterException(ExceptionMessage.CONVERTER_INTEGER.getMessage(), e);
		}

		/* apply cell style */
		CellStyleHandler.applyCellStyle(configCriteria, cell, CellStyleHandler.CELL_DECORATOR_NUMERIC,
				CellStyleHandler.MASK_DECORATOR_INTEGER);

		/* apply the comment */
		CellCommentHandler.applyComment(configCriteria, object, cell);

		return isUpdated;
	}

	/**
	 * Apply a double value at the Cell.
	 * 
	 * @param configCriteria
	 *            the {@link XConfigCriteria} object
	 * @param object
	 *            the object
	 * @param cell
	 *            the {@link Cell} to use
	 * @return false if problem otherwise true
	 * @throws WorkbookException
	 *             given when a not supported action.
	 */
	protected static boolean longWriter(final XConfigCriteria configCriteria, final Object object, final Cell cell)
			throws WorkbookException {
		boolean isUpdated = true;
		try {
			/* ignore treatment if object null and is NOT a formula */
			if (configCriteria.getField().get(object) == null && !configCriteria.getElement().isFormula()) {
				return isUpdated;
			}
			// apply the formula, if exist, otherwise apply the value
			CellFormulaHandler.longHandler(configCriteria, object, cell);

		} catch (ElementException e) {
			throw new ElementException(e.getMessage(), e);
		} catch (Exception e) {
			throw new ConverterException(ExceptionMessage.CONVERTER_LONG.getMessage(), e);
		}

		/* apply cell style */
		CellStyleHandler.applyCellStyle(configCriteria, cell, CellStyleHandler.CELL_DECORATOR_NUMERIC,
				CellStyleHandler.MASK_DECORATOR_INTEGER);

		/* apply the comment */
		CellCommentHandler.applyComment(configCriteria, object, cell);

		return isUpdated;
	}

	/**
	 * Apply a double value at the Cell.
	 * 
	 * @param configCriteria
	 *            the {@link XConfigCriteria} object
	 * @param object
	 *            the object
	 * @param cell
	 *            the {@link Cell} to use
	 * @return false if problem otherwise true
	 * @throws WorkbookException
	 *             given when a not supported action.
	 */
	protected static boolean doubleWriter(final XConfigCriteria configCriteria, final Object object, final Cell cell)
			throws WorkbookException {
		boolean isUpdated = true;
		try {
			/* ignore treatment if object null and is NOT a formula */
			if (configCriteria.getField().get(object) == null && !configCriteria.getElement().isFormula()) {
				return isUpdated;
			}
			// apply the formula, if exist, otherwise apply the value
			CellFormulaHandler.doubleHandler(configCriteria, object, cell);

		} catch (ElementException e) {
			throw new ElementException(e.getMessage(), e);
		} catch (Exception e) {
			throw new ConverterException(ExceptionMessage.CONVERTER_DOUBLE.getMessage(), e);
		}

		/* apply cell style */
		CellStyleHandler.applyCellStyle(configCriteria, cell, CellStyleHandler.CELL_DECORATOR_NUMERIC,
				CellStyleHandler.MASK_DECORATOR_DOUBLE);

		/* apply the comment */
		CellCommentHandler.applyComment(configCriteria, object, cell);

		return isUpdated;
	}

	/**
	 * Apply a big decimal value at the Cell.
	 * 
	 * @param configCriteria
	 *            the {@link XConfigCriteria} object
	 * @param object
	 *            the object
	 * @param cell
	 *            the {@link Cell} to use
	 * @return false if problem otherwise true
	 * @throws WorkbookException
	 *             given when a not supported action.
	 */
	protected static boolean bigDecimalWriter(final XConfigCriteria configCriteria, final Object object,
			final Cell cell) throws WorkbookException {
		boolean isUpdated = true;
		try {
			/* ignore treatment if object null and is NOT a formula */
			if (configCriteria.getField().get(object) == null && !configCriteria.getElement().isFormula()) {
				return isUpdated;
			}
			// apply the formula, if exist, otherwise apply the value
			CellFormulaHandler.bigDecimalHandler(configCriteria, object, cell);

		} catch (ElementException e) {
			throw new ElementException(e.getMessage(), e);
		} catch (Exception e) {
			throw new ConverterException(ExceptionMessage.CONVERTER_BIGDECIMAL.getMessage(), e);
		}

		/* apply cell style */
		CellStyleHandler.applyCellStyle(configCriteria, cell, CellStyleHandler.CELL_DECORATOR_NUMERIC,
				CellStyleHandler.MASK_DECORATOR_DOUBLE);

		/* apply the comment */
		CellCommentHandler.applyComment(configCriteria, object, cell);

		return isUpdated;
	}

	/**
	 * Apply a date value at the Cell.
	 * 
	 * @param configCriteria
	 *            the {@link XConfigCriteria} object
	 * @param object
	 *            the object
	 * @param cell
	 *            the {@link Cell} to use
	 * @return false if problem otherwise true
	 * @throws WorkbookException
	 *             given when a not supported action.
	 */
	protected static boolean dateWriter(final XConfigCriteria configCriteria, final Object object, final Cell cell)
			throws WorkbookException {
		boolean isUpdated = true;
		try {
			/* ignore treatment if object null and is NOT a formula */
			if (configCriteria.getField().get(object) == null && !configCriteria.getElement().isFormula()) {
				return isUpdated;
			}
			// apply the formula, if exist, otherwise apply the value
			CellFormulaHandler.dateHandler(configCriteria, object, cell);

		} catch (Exception e) {
			throw new ConverterException(ExceptionMessage.CONVERTER_DATE.getMessage(), e);
		}

		/* apply cell style */
		CellStyleHandler.applyCellStyle(configCriteria, cell, CellStyleHandler.CELL_DECORATOR_DATE,
				CellStyleHandler.MASK_DECORATOR_DATE);

		/* apply the comment */
		CellCommentHandler.applyComment(configCriteria, object, cell);

		return isUpdated;
	}

	/**
	 * Apply a float value at the Cell.
	 * 
	 * @param configCriteria
	 *            the {@link XConfigCriteria} object
	 * @param object
	 *            the object
	 * @param cell
	 *            the {@link Cell} to use
	 * @return false if problem otherwise true
	 * @throws WorkbookException
	 *             given when a not supported action.
	 */
	protected static boolean floatWriter(final XConfigCriteria configCriteria, final Object object, final Cell cell)
			throws WorkbookException {
		boolean isUpdated = true;
		try {
			/* ignore treatment if object null and is NOT a formula */
			if (configCriteria.getField().get(object) == null && !configCriteria.getElement().isFormula()) {
				return isUpdated;
			}
			// apply the formula, if exist, otherwise apply the value
			CellFormulaHandler.floatHandler(configCriteria, object, cell);

		} catch (ElementException e) {
			throw new ElementException(e.getMessage(), e);
		} catch (Exception e) {
			throw new ConverterException(ExceptionMessage.CONVERTER_FLOAT.getMessage(), e);
		}

		/* apply cell style */
		CellStyleHandler.applyCellStyle(configCriteria, cell, CellStyleHandler.CELL_DECORATOR_NUMERIC,
				CellStyleHandler.MASK_DECORATOR_DOUBLE);

		/* apply the comment */
		CellCommentHandler.applyComment(configCriteria, object, cell);

		return isUpdated;
	}

	/**
	 * 
	 * Apply a boolean value at the Cell.
	 * 
	 * @param configCriteria
	 *            the {@link XConfigCriteria} object
	 * @param object
	 *            the object
	 * @param cell
	 *            the {@link Cell} to use
	 * @return false if problem otherwise true
	 * @throws WorkbookException
	 *             given when a not supported action.
	 */
	protected static boolean booleanWriter(final XConfigCriteria configCriteria, final Object object, final Cell cell)
			throws WorkbookException {
		boolean isUpdated = true;
		try {
			/* ignore treatment if object null and is NOT a formula */
			if (configCriteria.getField().get(object) == null && !configCriteria.getElement().isFormula()) {
				return isUpdated;
			}
			// apply the formula, if exist, otherwise apply the value
			CellFormulaHandler.booleanHandler(configCriteria, object, cell);

		} catch (Exception e) {
			throw new ConverterException(ExceptionMessage.CONVERTER_BOOLEAN.getMessage(), e);
		}

		/* apply cell style */
		CellStyleHandler.applyCellStyle(configCriteria, cell, CellStyleHandler.CELL_DECORATOR_BOOLEAN, null);

		/* apply the comment */
		CellCommentHandler.applyComment(configCriteria, object, cell);

		return isUpdated;
	}

	/**
	 * 
	 * Apply a enum value at the Cell.
	 * 
	 * @param configCriteria
	 *            the {@link XConfigCriteria} object
	 * @param object
	 *            the object
	 * @param cell
	 *            the {@link Cell} to use
	 * @return false if problem otherwise true
	 * @throws WorkbookException
	 *             given when a not supported action.
	 */
	protected static boolean enumWriter(final XConfigCriteria configCriteria, final Object object, final Cell cell)
			throws WorkbookException {
		boolean isUpdated = true;

		try {
			@SuppressWarnings("rawtypes")
			Class[] argTypes = {};

			String method = Constants.GET + configCriteria.getField().getName().substring(0, 1).toUpperCase()
					+ configCriteria.getField().getName().substring(1);

			Method m = object.getClass().getDeclaredMethod(method, argTypes);

			Object objEnum = m.invoke(object, (Object[]) null);

			if (objEnum != null) {
				// apply the enum value
				cell.setCellValue((String) objEnum.toString());
			}

		} catch (Exception e) {
			throw new ConverterException(ExceptionMessage.CONVERTER_ENUM.getMessage(), e);
		}

		/* apply cell style */
		CellStyleHandler.applyCellStyle(configCriteria, cell, CellStyleHandler.CELL_DECORATOR_ENUM, null);

		/* apply the comment */
		CellCommentHandler.applyComment(configCriteria, object, cell);

		return isUpdated;
	}

	/**
	 * Apply a explicit formula value at the Cell.
	 * 
	 * @param object
	 *            the object
	 * @param methodRules
	 *            the method rules to use
	 * @throws CustomizedRulesException
	 *             given when no such method.
	 */
	protected static void applyCustomizedRules(final Object object, final String methodRules)
			throws CustomizedRulesException {
		@SuppressWarnings("rawtypes")
		Class[] argTypes = {};

		try {
			Method m = object.getClass().getDeclaredMethod(methodRules, argTypes);
			m.invoke(object, (Object[]) null);
		} catch (NoSuchMethodException e) {
			throw new CustomizedRulesException(ExceptionMessage.CUSTOMIZEDRULES_NO_SUCH_METHOD.getMessage(), e);
		} catch (IllegalAccessException e) {
			throw new CustomizedRulesException(ExceptionMessage.CUSTOMIZEDRULES_NO_SUCH_METHOD.getMessage(), e);
		} catch (InvocationTargetException e) {
			throw new CustomizedRulesException(ExceptionMessage.CUSTOMIZEDRULES_NO_SUCH_METHOD.getMessage(), e);
		}
	}

	/* internal methods */

	/**
	 * Recover the value from the Excel.
	 * 
	 * @param cell
	 *            the {@link Cell}
	 * @return the content of the cell
	 */
	private static String readCell(final Cell cell) {
		if (cell == null) {
			return null;
		}

		if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
			return cell.getStringCellValue();
		}

		if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			Double value = cell.getNumericCellValue();
			return value.toString();
		}

		if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
			return switchCachedFormulaManager(cell);
		}
		return null;
	}

	/**
	 * Manage the cached formula case.
	 * 
	 * @param cellthe
	 *            {@link Cell}
	 * @return the content of the cell
	 */
	private static String switchCachedFormulaManager(final Cell cell) {
		switch (cell.getCachedFormulaResultType()) {
		case Cell.CELL_TYPE_NUMERIC:
			Double value = cell.getNumericCellValue();
			return value.toString();

		case Cell.CELL_TYPE_STRING:
			return cell.getRichStringCellValue().toString();

		default:
			return null;
		}
	}
}
