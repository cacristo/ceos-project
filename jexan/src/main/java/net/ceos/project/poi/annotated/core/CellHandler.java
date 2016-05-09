package net.ceos.project.poi.annotated.core;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;

import net.ceos.project.poi.annotated.annotation.XlsElement;
import net.ceos.project.poi.annotated.definition.ExceptionMessage;
import net.ceos.project.poi.annotated.exception.ConverterException;
import net.ceos.project.poi.annotated.exception.CustomizedRulesException;
import net.ceos.project.poi.annotated.exception.ElementException;

/**
 * Manage all the default type of values to apply/read to one cell.<br>
 * <br>
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

	private CellHandler() {
		/* private constructor to hide the implicit public */
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
	 * @throws IllegalAccessException
	 */
	protected static void stringReader(final Object object, final Field field, final Cell cell) throws IllegalAccessException {
		if (StringUtils.isNotBlank(readCell(cell))) {
			field.set(object, readCell(cell));
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
	 * @throws IllegalAccessException
	 */
	protected static void shortReader(final Object object, final Field field, final Cell cell)
			throws IllegalAccessException {
		if (StringUtils.isNotBlank(readCell(cell))) {
			field.set(object, Double.valueOf(readCell(cell)).shortValue());
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
	 * @throws IllegalAccessException
	 */
	protected static void integerReader(final Object object, final Field field, final Cell cell)
			throws IllegalAccessException {
		if (StringUtils.isNotBlank(readCell(cell))) {
			field.set(object, Double.valueOf(readCell(cell)).intValue());
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
	 * @throws IllegalAccessException
	 */
	protected static void longReader(final Object object, final Field field, final Cell cell)
			throws IllegalAccessException {
		if (StringUtils.isNotBlank(readCell(cell))) {
			field.set(object, Double.valueOf(readCell(cell)).longValue());
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
	 * @throws IllegalAccessException
	 */
	protected static void doubleReader(final Object object, final Field field, final Cell cell,
			final XlsElement xlsAnnotation) throws IllegalAccessException {
		if (StringUtils.isNotBlank(readCell(cell))) {
			if (StringUtils.isNotBlank(xlsAnnotation.transformMask())) {
				field.set(object, Double.valueOf(readCell(cell).replace(Constants.COMMA, Constants.DOT)));
			} else {
				field.set(object, Double.valueOf(readCell(cell)));
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
	 * @throws IllegalAccessException
	 */
	protected static void bigDecimalReader(final Object object, final Field field, final Cell cell,
			final XlsElement xlsAnnotation) throws IllegalAccessException {
		if (StringUtils.isNotBlank(readCell(cell))) {
			if (StringUtils.isNotBlank(xlsAnnotation.transformMask())) {
				field.set(object,
						BigDecimal.valueOf(Double.valueOf(readCell(cell).replace(Constants.COMMA, Constants.DOT))));
			} else {
				field.set(object, BigDecimal.valueOf(Double.valueOf(readCell(cell))));
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
	 * @throws IllegalAccessException
	 * @throws ConverterException
	 */
	protected static void dateReader(final Object object, final Field field, final Cell cell,
			final XlsElement xlsAnnotation) throws IllegalAccessException, ConverterException {
		if (StringUtils.isBlank(xlsAnnotation.transformMask())) {
			field.set(object, cell.getDateCellValue());
		} else {
			String date = cell.getStringCellValue();
			if (StringUtils.isNotBlank(date)) {

				String tM = xlsAnnotation.transformMask();
				String fM = xlsAnnotation.formatMask();
				String decorator = StringUtils.isEmpty(tM)
						? (StringUtils.isEmpty(fM) ? CellStyleHandler.MASK_DECORATOR_DATE : fM) : tM;

				SimpleDateFormat dt = new SimpleDateFormat(decorator);
				try {
					Date dateConverted = dt.parse(date);
					field.set(object, dateConverted);
				} catch (ParseException e) {
					/*
					 * if date decorator do not match with a valid mask launch
					 * exception
					 */
					throw new ConverterException(ExceptionMessage.CONVERTER_DATE.getMessage(), e);
				}
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
	 * @throws IllegalAccessException
	 */
	protected static void floatReader(final Object object, final Field field, final Cell cell)
			throws IllegalAccessException {
		if (StringUtils.isNotBlank(readCell(cell))) {
			field.set(object, Double.valueOf(readCell(cell)).floatValue());
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
	 * @throws IllegalAccessException
	 */
	protected static void booleanReader(final Object object, final Field field, final Cell cell,
			final XlsElement xlsAnnotation) throws IllegalAccessException {
		String booleanValue = cell.getStringCellValue();
		if (StringUtils.isNotBlank(booleanValue)) {
			if (StringUtils.isNotBlank(xlsAnnotation.transformMask())) {
				/* apply format mask defined at transform mask */
				String[] split = xlsAnnotation.transformMask().split(Constants.SLASH);
				field.set(object,
						StringUtils.isNotBlank(booleanValue) ? (split[0].equals(booleanValue) ? true : false) : null);

			} else {
				/* locale mode */
				field.set(object, StringUtils.isNotBlank(booleanValue) ? Boolean.valueOf(booleanValue) : null);
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
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected static void enumReader(final Object object, final Class<?> fT, final Field field, final Cell cell)
			throws IllegalAccessException {
		if (StringUtils.isNotBlank(cell.getStringCellValue())) {
			field.set(object, Enum.valueOf((Class<Enum>) fT, cell.getStringCellValue()));
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
	 * @throws ConverterException
	 * @throws CustomizedRulesException
	 */
	protected static boolean stringWriter(final XConfigCriteria configCriteria, final Object object, final Cell cell)
			throws ConverterException, CustomizedRulesException {
		boolean isUpdated = true;
		try {
			// apply the formula, if exist, otherwise apply the value
			CellFormulaHandler.stringHandler(configCriteria, object, cell);

			// apply the style
			CellStyleHandler.applyCellStyle(configCriteria, cell, CellStyleHandler.CELL_DECORATOR_GENERIC, null);

		} catch (Exception e) {
			throw new ConverterException(ExceptionMessage.CONVERTER_STRING.getMessage(), e);
		}

		// apply the comment
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
	 * @throws ConverterException
	 * @throws CustomizedRulesException
	 */
	protected static boolean shortWriter(final XConfigCriteria configCriteria, final Object object, final Cell cell)
			throws ConverterException, CustomizedRulesException {
		boolean isUpdated = true;
		try {
			// apply the formula, if exist, otherwise apply the value
			CellFormulaHandler.shortHandler(configCriteria, object, cell);

			// apply cell style
			CellStyleHandler.applyCellStyle(configCriteria, cell, CellStyleHandler.CELL_DECORATOR_NUMERIC,
					CellStyleHandler.MASK_DECORATOR_INTEGER);

		} catch (Exception e) {
			throw new ConverterException(ExceptionMessage.CONVERTER_SHORT.getMessage(), e);
		}

		// apply the comment
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
	 * @throws ConverterException
	 * @throws CustomizedRulesException
	 */
	protected static boolean integerWriter(final XConfigCriteria configCriteria, final Object object, final Cell cell)
			throws ConverterException, CustomizedRulesException {
		boolean isUpdated = true;
		try {
			// apply the formula, if exist, otherwise apply the value
			CellFormulaHandler.integerHandler(configCriteria, object, cell);

			// apply cell style
			CellStyleHandler.applyCellStyle(configCriteria, cell, CellStyleHandler.CELL_DECORATOR_NUMERIC,
					CellStyleHandler.MASK_DECORATOR_INTEGER);

		} catch (Exception e) {
			throw new ConverterException(ExceptionMessage.CONVERTER_INTEGER.getMessage(), e);
		}

		// apply the comment
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
	 * @throws ConverterException
	 * @throws CustomizedRulesException
	 */
	protected static boolean longWriter(final XConfigCriteria configCriteria, final Object object, final Cell cell)
			throws ConverterException, CustomizedRulesException {
		boolean isUpdated = true;
		try {
			// apply the formula, if exist, otherwise apply the value
			CellFormulaHandler.longHandler(configCriteria, object, cell);

			// apply cell style
			CellStyleHandler.applyCellStyle(configCriteria, cell, CellStyleHandler.CELL_DECORATOR_NUMERIC,
					CellStyleHandler.MASK_DECORATOR_INTEGER);

		} catch (Exception e) {
			throw new ConverterException(ExceptionMessage.CONVERTER_LONG.getMessage(), e);
		}

		// apply the comment
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
	 * @throws ConverterException
	 * @throws CustomizedRulesException
	 */
	protected static boolean doubleWriter(final XConfigCriteria configCriteria, final Object object, final Cell cell)
			throws ConverterException, CustomizedRulesException {
		boolean isUpdated = true;
		try {
			// apply the formula, if exist, otherwise apply the value
			CellFormulaHandler.doubleHandler(configCriteria, object, cell);

			// apply cell style
			CellStyleHandler.applyCellStyle(configCriteria, cell, CellStyleHandler.CELL_DECORATOR_NUMERIC,
					CellStyleHandler.MASK_DECORATOR_DOUBLE);

		} catch (Exception e) {
			throw new ConverterException(ExceptionMessage.CONVERTER_DOUBLE.getMessage(), e);
		}

		// apply the comment
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
	 * @throws ConverterException
	 * @throws ElementException
	 * @throws CustomizedRulesException
	 */
	protected static boolean bigDecimalWriter(final XConfigCriteria configCriteria, final Object object,
			final Cell cell) throws ConverterException, ElementException, CustomizedRulesException {
		boolean isUpdated = true;
		try {
			// apply the formula, if exist, otherwise apply the value
			CellFormulaHandler.bigDecimalHandler(configCriteria, object, cell);

			// apply cell style
			CellStyleHandler.applyCellStyle(configCriteria, cell, CellStyleHandler.CELL_DECORATOR_NUMERIC,
					CellStyleHandler.MASK_DECORATOR_DOUBLE);

		} catch (Exception e) {
			throw new ConverterException(ExceptionMessage.CONVERTER_BIGDECIMAL.getMessage(), e);
		}

		// apply the comment
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
	 * @throws ConverterException
	 * @throws ElementException
	 * @throws CustomizedRulesException
	 */
	protected static boolean dateWriter(final XConfigCriteria configCriteria, final Object object, final Cell cell)
			throws ConverterException, ElementException, CustomizedRulesException {
		boolean isUpdated = true;
		try {
			if (configCriteria.getField().get(object) != null) {
				// apply the formula, if exist, otherwise apply the value
				CellFormulaHandler.dateHandler(configCriteria, object, cell);

				// apply cell style
				CellStyleHandler.applyCellStyle(configCriteria, cell, CellStyleHandler.CELL_DECORATOR_DATE,
						CellStyleHandler.MASK_DECORATOR_DATE);
			}

		} catch (Exception e) {
			throw new ConverterException(ExceptionMessage.CONVERTER_DATE.getMessage(), e);
		}

		// apply the comment
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
	 * @throws ConverterException
	 * @throws ElementException
	 * @throws CustomizedRulesException
	 */
	protected static boolean floatWriter(final XConfigCriteria configCriteria, final Object object, final Cell cell)
			throws ConverterException, ElementException, CustomizedRulesException {
		boolean isUpdated = true;
		try {
			// apply the formula, if exist, otherwise apply the value
			CellFormulaHandler.floatHandler(configCriteria, object, cell);

			// apply cell style
			CellStyleHandler.applyCellStyle(configCriteria, cell, CellStyleHandler.CELL_DECORATOR_NUMERIC,
					CellStyleHandler.MASK_DECORATOR_DOUBLE);

		} catch (Exception e) {
			throw new ConverterException(ExceptionMessage.CONVERTER_FLOAT.getMessage(), e);
		}

		// apply the comment
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
	 * @throws ConverterException
	 * @throws ElementException
	 * @throws CustomizedRulesException
	 */
	protected static boolean booleanWriter(final XConfigCriteria configCriteria, final Object object, final Cell cell)
			throws ConverterException, ElementException, CustomizedRulesException {
		boolean isUpdated = true;
		try {
			// apply the formula, if exist, otherwise apply the value
			CellFormulaHandler.booleanHandler(configCriteria, object, cell);

			// apply cell style
			CellStyleHandler.applyCellStyle(configCriteria, cell, CellStyleHandler.CELL_DECORATOR_BOOLEAN, null);

		} catch (Exception e) {
			throw new ConverterException(ExceptionMessage.CONVERTER_BOOLEAN.getMessage(), e);
		}

		// apply the comment
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
	 * @throws ConverterException
	 * @throws ElementException
	 * @throws CustomizedRulesException
	 */
	protected static boolean enumWriter(final XConfigCriteria configCriteria, final Object object, final Cell cell)
			throws ConverterException, ElementException, CustomizedRulesException {
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

			// apply cell style
			CellStyleHandler.applyCellStyle(configCriteria, cell, CellStyleHandler.CELL_DECORATOR_ENUM, null);

		} catch (Exception e) {
			throw new ConverterException(ExceptionMessage.CONVERTER_BOOLEAN.getMessage(), e);
		}

		// apply the comment
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
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	protected static void applyCustomizedRules(final Object object, final String methodRules)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, CustomizedRulesException {
		@SuppressWarnings("rawtypes")
		Class[] argTypes = {};

		Method m = object.getClass().getDeclaredMethod(methodRules, argTypes);

		m.invoke(object, (Object[]) null);
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
