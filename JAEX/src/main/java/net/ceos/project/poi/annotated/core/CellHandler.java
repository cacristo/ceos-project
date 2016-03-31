package net.ceos.project.poi.annotated.core;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.FormulaParser;
import org.apache.poi.ss.formula.FormulaRenderer;
import org.apache.poi.ss.formula.FormulaType;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.formula.ptg.RefPtgBase;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFEvaluationWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import net.ceos.project.poi.annotated.annotation.XlsElement;
import net.ceos.project.poi.annotated.definition.ExceptionMessage;
import net.ceos.project.poi.annotated.exception.ConverterException;
import net.ceos.project.poi.annotated.exception.CustomizedRulesException;
import net.ceos.project.poi.annotated.exception.ElementException;

/**
 * This class manage all the default type of values to apply to one cell.
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public class CellHandler {

	/* Possible news features */
	// (1) Manage decimalScale
	// Double d = (Double) f.get(o);
	// BigDecimal bd = new BigDecimal(d);
	// bd.setScale(2, BigDecimal.ROUND_HALF_UP);

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
	 * @param o
	 *            the object
	 * @param f
	 *            the {@link Field} to set
	 * @param c
	 *            the {@link Cell} to read
	 * @throws IllegalAccessException
	 */
	protected static void stringReader(final Object o, final Field f, final Cell c) throws IllegalAccessException {
		if (StringUtils.isNotBlank(readCell(c))) {
			f.set(o, readCell(c));
		}
	}

	/**
	 * Read a integer value from the Cell.
	 * 
	 * @param o
	 *            the object
	 * @param f
	 *            the {@link Field} to set
	 * @param c
	 *            the {@link Cell} to read
	 * @throws IllegalAccessException
	 */
	protected static void integerReader(final Object o, final Field f, final Cell c) throws IllegalAccessException {
		if (StringUtils.isNotBlank(readCell(c))) {
			f.set(o, Double.valueOf(readCell(c)).intValue());
		}
	}

	/**
	 * Read a long value from the Cell.
	 * 
	 * @param o
	 *            the object
	 * @param f
	 *            the {@link Field} to set
	 * @param c
	 *            the {@link Cell} to read
	 * @throws IllegalAccessException
	 */
	protected static void longReader(final Object o, final Field f, final Cell c) throws IllegalAccessException {
		if (StringUtils.isNotBlank(readCell(c))) {
			f.set(o, Double.valueOf(readCell(c)).longValue());
		}
	}

	/**
	 * Read a double value from the Cell.
	 * 
	 * @param o
	 *            the object
	 * @param f
	 *            the {@link Field} to set
	 * @param c
	 *            the {@link Cell} to read
	 * @param xlsAnnotation
	 *            the {@link XlsElement} element
	 * @throws IllegalAccessException
	 */
	protected static void doubleReader(final Object o, final Field f, final Cell c, final XlsElement xlsAnnotation)
			throws IllegalAccessException {
		if (StringUtils.isNotBlank(readCell(c))) {
			if (StringUtils.isNotBlank(xlsAnnotation.transformMask())) {
				f.set(o, Double.valueOf(readCell(c).replace(",", ".")));
			} else {
				f.set(o, Double.valueOf(readCell(c)));
			}
		}
	}

	/**
	 * Read a big decimal value from the Cell.
	 * 
	 * @param o
	 *            the object
	 * @param f
	 *            the {@link Field} to set
	 * @param c
	 *            the {@link Cell} to read
	 * @param xlsAnnotation
	 *            the {@link XlsElement} element
	 * @throws IllegalAccessException
	 */
	protected static void bigDecimalReader(final Object o, final Field f, final Cell c, final XlsElement xlsAnnotation)
			throws IllegalAccessException {
		if (StringUtils.isNotBlank(readCell(c))) {
			if (StringUtils.isNotBlank(xlsAnnotation.transformMask())) {
				f.set(o, BigDecimal.valueOf(Double.valueOf(readCell(c).replace(",", "."))));
			} else {
				f.set(o, BigDecimal.valueOf(Double.valueOf(readCell(c))));
			}
		}
	}

	/**
	 * Read a date value from the Cell.
	 * 
	 * @param o
	 *            the object
	 * @param f
	 *            the {@link Field} to set
	 * @param c
	 *            the {@link Cell} to read
	 * @param xlsAnnotation
	 *            the {@link XlsElement} element
	 * @throws IllegalAccessException
	 * @throws ConverterException
	 */
	protected static void dateReader(final Object o, final Field f, final Cell c, final XlsElement xlsAnnotation)
			throws IllegalAccessException, ConverterException {
		if (StringUtils.isBlank(xlsAnnotation.transformMask())) {
			f.set(o, c.getDateCellValue());
		} else {
			String date = c.getStringCellValue();
			if (StringUtils.isNotBlank(date)) {

				String tM = xlsAnnotation.transformMask();
				String fM = xlsAnnotation.formatMask();
				String decorator = StringUtils.isEmpty(tM)
						? (StringUtils.isEmpty(fM) ? CellStyleHandler.MASK_DECORATOR_DATE : fM) : tM;

				SimpleDateFormat dt = new SimpleDateFormat(decorator);
				try {
					Date dateConverted = dt.parse(date);
					f.set(o, dateConverted);
				} catch (ParseException e) {
					/*
					 * if date decorator do not match with a valid mask launch
					 * exception
					 */
					throw new ConverterException(ExceptionMessage.ConverterException_Date.getMessage(), e);
				}
			}
		}
	}

	/**
	 * Read a float value from the Cell.
	 * 
	 * @param o
	 *            the object
	 * @param f
	 *            the {@link Field} to set
	 * @param c
	 *            the {@link Cell} to read
	 * @throws IllegalAccessException
	 */
	protected static void floatReader(final Object o, final Field f, final Cell c) throws IllegalAccessException {
		if (StringUtils.isNotBlank(readCell(c))) {
			f.set(o, Double.valueOf(readCell(c)).floatValue());
		}
	}

	/**
	 * Read a boolean value from the Cell.
	 * 
	 * @param o
	 *            the object
	 * @param f
	 *            the {@link Field} to set
	 * @param c
	 *            the {@link Cell} to read
	 * @param xlsAnnotation
	 *            the {@link XlsElement} element
	 * @throws IllegalAccessException
	 */
	protected static void booleanReader(final Object o, final Field f, final Cell c, final XlsElement xlsAnnotation)
			throws IllegalAccessException {
		String booleanValue = c.getStringCellValue();
		if (StringUtils.isNotBlank(booleanValue)) {
			if (StringUtils.isNotBlank(xlsAnnotation.transformMask())) {
				/* apply format mask defined at transform mask */
				String[] split = xlsAnnotation.transformMask().split("/");
				f.set(o, StringUtils.isNotBlank(booleanValue) ? (split[0].equals(booleanValue) ? true : false) : null);

			} else {
				/* locale mode */
				f.set(o, StringUtils.isNotBlank(booleanValue) ? Boolean.valueOf(booleanValue) : null);
			}
		}
	}

	/**
	 * Read an enumeration value from the Cell.
	 * 
	 * @param o
	 *            the object
	 * @param fT
	 *            the class of the field
	 * @param f
	 *            the {@link Field} to set
	 * @param c
	 *            the {@link Cell} to read
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected static void enumReader(final Object o, final Class<?> fT, final Field f, final Cell c)
			throws IllegalAccessException {
		if (StringUtils.isNotBlank(c.getStringCellValue())) {
			f.set(o, Enum.valueOf((Class<Enum>) fT, c.getStringCellValue()));
		}
	}

	/* Writer methods */

	/**
	 * Apply a integer value at the Cell.
	 * 
	 * @param configCriteria
	 *            the {@link ConfigCriteria} object
	 * @param o
	 *            the object
	 * @param c
	 *            the {@link Cell} to use
	 * @return false if problem otherwise true
	 * @throws ConverterException
	 * @throws CustomizedRulesException
	 */
	protected static boolean stringWriter(final ConfigCriteria configCriteria, final Object o, final Cell c)
			throws ConverterException, CustomizedRulesException {
		boolean isUpdated = true;
		try {
			// apply the formula, if exist, otherwise apply the value
			CellFormulaHandler.stringHandler(configCriteria, o, c);

			// apply the style
			CellStyleHandler.applyCellStyle(configCriteria, c, CellStyleHandler.CELL_DECORATOR_GENERIC, null);

		} catch (Exception e) {
			throw new ConverterException(ExceptionMessage.ConverterException_String.getMessage(), e);
		}

		// apply the comment
		CellCommentHandler.applyComment(configCriteria, o, c);

		return isUpdated;
	}

	/**
	 * Apply a integer value at the Cell.
	 * 
	 * @param configCriteria
	 *            the {@link ConfigCriteria} object
	 * @param o
	 *            the object
	 * @param c
	 *            the {@link Cell} to use
	 * @return false if problem otherwise true
	 * @throws ConverterException
	 * @throws CustomizedRulesException
	 */
	protected static boolean shortWriter(final ConfigCriteria configCriteria, final Object o, final Cell c)
			throws ConverterException, CustomizedRulesException {
		boolean isUpdated = true;
		try {
			// apply the formula, if exist, otherwise apply the value
			CellFormulaHandler.shortHandler(configCriteria, o, c);

			// apply cell style
			CellStyleHandler.applyCellStyle(configCriteria, c, CellStyleHandler.CELL_DECORATOR_NUMERIC,
					CellStyleHandler.MASK_DECORATOR_INTEGER);

		} catch (Exception e) {
			throw new ConverterException(ExceptionMessage.ConverterException_Integer.getMessage(), e);
		}

		// apply the comment
		CellCommentHandler.applyComment(configCriteria, o, c);

		return isUpdated;
	}

	/**
	 * Apply a integer value at the Cell.
	 * 
	 * @param configCriteria
	 *            the {@link ConfigCriteria} object
	 * @param o
	 *            the object
	 * @param c
	 *            the {@link Cell} to use
	 * @return false if problem otherwise true
	 * @throws ConverterException
	 * @throws CustomizedRulesException
	 */
	protected static boolean integerWriter(final ConfigCriteria configCriteria, final Object o, final Cell c)
			throws ConverterException, CustomizedRulesException {
		boolean isUpdated = true;
		try {
			// apply the formula, if exist, otherwise apply the value
			CellFormulaHandler.integerHandler(configCriteria, o, c);

			// apply cell style
			CellStyleHandler.applyCellStyle(configCriteria, c, CellStyleHandler.CELL_DECORATOR_NUMERIC,
					CellStyleHandler.MASK_DECORATOR_INTEGER);

		} catch (Exception e) {
			throw new ConverterException(ExceptionMessage.ConverterException_Integer.getMessage(), e);
		}

		// apply the comment
		CellCommentHandler.applyComment(configCriteria, o, c);

		return isUpdated;
	}

	/**
	 * Apply a double value at the Cell.
	 * 
	 * @param configCriteria
	 *            the {@link ConfigCriteria} object
	 * @param o
	 *            the object
	 * @param c
	 *            the {@link Cell} to use
	 * @return false if problem otherwise true
	 * @throws ConverterException
	 * @throws CustomizedRulesException
	 */
	protected static boolean longWriter(final ConfigCriteria configCriteria, final Object o, final Cell c)
			throws ConverterException, CustomizedRulesException {
		boolean isUpdated = true;
		try {
			// apply the formula, if exist, otherwise apply the value
			CellFormulaHandler.longHandler(configCriteria, o, c);

			// apply cell style
			CellStyleHandler.applyCellStyle(configCriteria, c, CellStyleHandler.CELL_DECORATOR_NUMERIC,
					CellStyleHandler.MASK_DECORATOR_INTEGER);

		} catch (Exception e) {
			throw new ConverterException(ExceptionMessage.ConverterException_Long.getMessage(), e);
		}

		// apply the comment
		CellCommentHandler.applyComment(configCriteria, o, c);

		return isUpdated;
	}

	/**
	 * Apply a double value at the Cell.
	 * 
	 * @param configCriteria
	 *            the {@link ConfigCriteria} object
	 * @param o
	 *            the object
	 * @param c
	 *            the {@link Cell} to use
	 * @return false if problem otherwise true
	 * @throws ConverterException
	 * @throws CustomizedRulesException
	 */
	protected static boolean doubleWriter(final ConfigCriteria configCriteria, final Object o, final Cell c)
			throws ConverterException, CustomizedRulesException {
		boolean isUpdated = true;
		try {
			// apply the formula, if exist, otherwise apply the value
			CellFormulaHandler.doubleHandler(configCriteria, o, c);

			// apply cell style
			CellStyleHandler.applyCellStyle(configCriteria, c, CellStyleHandler.CELL_DECORATOR_NUMERIC,
					CellStyleHandler.MASK_DECORATOR_DOUBLE);

		} catch (Exception e) {
			throw new ConverterException(ExceptionMessage.ConverterException_Double.getMessage(), e);
		}

		// apply the comment
		CellCommentHandler.applyComment(configCriteria, o, c);

		return isUpdated;
	}

	/**
	 * Apply a big decimal value at the Cell.
	 * 
	 * @param configCriteria
	 *            the {@link ConfigCriteria} object
	 * @param o
	 *            the object
	 * @param c
	 *            the {@link Cell} to use
	 * @return false if problem otherwise true
	 * @throws ConverterException
	 * @throws ElementException
	 * @throws CustomizedRulesException
	 */
	protected static boolean bigDecimalWriter(final ConfigCriteria configCriteria, final Object o, final Cell c)
			throws ConverterException, ElementException, CustomizedRulesException {
		boolean isUpdated = true;
		try {
			// apply the formula, if exist, otherwise apply the value
			CellFormulaHandler.bigDecimalHandler(configCriteria, o, c);

			// apply cell style
			CellStyleHandler.applyCellStyle(configCriteria, c, CellStyleHandler.CELL_DECORATOR_NUMERIC,
					CellStyleHandler.MASK_DECORATOR_DOUBLE);

		} catch (Exception e) {
			throw new ConverterException(ExceptionMessage.ConverterException_BigDecimal.getMessage(), e);
		}

		// apply the comment
		CellCommentHandler.applyComment(configCriteria, o, c);

		return isUpdated;
	}

	/**
	 * Apply a date value at the Cell.
	 * 
	 * @param configCriteria
	 *            the {@link ConfigCriteria} object
	 * @param o
	 *            the object
	 * @param c
	 *            the {@link Cell} to use
	 * @return false if problem otherwise true
	 * @throws ConverterException
	 * @throws ElementException
	 * @throws CustomizedRulesException
	 */
	protected static boolean dateWriter(final ConfigCriteria configCriteria, final Object o, final Cell c)
			throws ConverterException, ElementException, CustomizedRulesException {
		boolean isUpdated = true;
		try {
			if (configCriteria.getField().get(o) != null) {
				// apply the formula, if exist, otherwise apply the value
				CellFormulaHandler.dateHandler(configCriteria, o, c);

				// apply cell style
				CellStyleHandler.applyCellStyle(configCriteria, c, CellStyleHandler.CELL_DECORATOR_DATE,
						CellStyleHandler.MASK_DECORATOR_DATE);
			}

		} catch (Exception e) {
			throw new ConverterException(ExceptionMessage.ConverterException_Date.getMessage(), e);
		}

		// apply the comment
		CellCommentHandler.applyComment(configCriteria, o, c);

		return isUpdated;
	}

	/**
	 * Apply a float value at the Cell.
	 * 
	 * @param configCriteria
	 *            the {@link ConfigCriteria} object
	 * @param o
	 *            the object
	 * @param c
	 *            the {@link Cell} to use
	 * @return false if problem otherwise true
	 * @throws ConverterException
	 * @throws ElementException
	 * @throws CustomizedRulesException
	 */
	protected static boolean floatWriter(final ConfigCriteria configCriteria, final Object o, final Cell c)
			throws ConverterException, ElementException, CustomizedRulesException {
		boolean isUpdated = true;
		try {
			// apply the formula, if exist, otherwise apply the value
			CellFormulaHandler.floatHandler(configCriteria, o, c);

			// apply cell style
			CellStyleHandler.applyCellStyle(configCriteria, c, CellStyleHandler.CELL_DECORATOR_NUMERIC,
					CellStyleHandler.MASK_DECORATOR_DOUBLE);

		} catch (Exception e) {
			throw new ConverterException(ExceptionMessage.ConverterException_Float.getMessage(), e);
		}

		// apply the comment
		CellCommentHandler.applyComment(configCriteria, o, c);

		return isUpdated;
	}

	/**
	 * 
	 * Apply a boolean value at the Cell.
	 * 
	 * @param configCriteria
	 *            the {@link ConfigCriteria} object
	 * @param o
	 *            the object
	 * @param c
	 *            the {@link Cell} to use
	 * @return false if problem otherwise true
	 * @throws ConverterException
	 * @throws ElementException
	 * @throws CustomizedRulesException
	 */
	protected static boolean booleanWriter(final ConfigCriteria configCriteria, final Object o, final Cell c)
			throws ConverterException, ElementException, CustomizedRulesException {
		boolean isUpdated = true;
		try {
			// apply the formula, if exist, otherwise apply the value
			CellFormulaHandler.booleanHandler(configCriteria, o, c);

			// apply cell style
			CellStyleHandler.applyCellStyle(configCriteria, c, CellStyleHandler.CELL_DECORATOR_BOOLEAN, null);

		} catch (Exception e) {
			throw new ConverterException(ExceptionMessage.ConverterException_Boolean.getMessage(), e);
		}

		// apply the comment
		CellCommentHandler.applyComment(configCriteria, o, c);

		return isUpdated;
	}

	/**
	 * 
	 * Apply a enum value at the Cell.
	 * 
	 * @param configCriteria
	 *            the {@link ConfigCriteria} object
	 * @param o
	 *            the object
	 * @param c
	 *            the {@link Cell} to use
	 * @return false if problem otherwise true
	 * @throws ConverterException
	 * @throws ElementException
	 * @throws CustomizedRulesException
	 */
	protected static boolean enumWriter(final ConfigCriteria configCriteria, final Object o, final Cell c)
			throws ConverterException, ElementException, CustomizedRulesException {
		boolean isUpdated = true;

		try {

			@SuppressWarnings("rawtypes")
			Class[] argTypes = {};

			String method = "get" + configCriteria.getField().getName().substring(0, 1).toUpperCase()
					+ configCriteria.getField().getName().substring(1);

			Method m = o.getClass().getDeclaredMethod(method, argTypes);

			Object objEnum = m.invoke(o, (Object[]) null);

			if (objEnum != null) {
				// apply the enum value
				c.setCellValue((String) objEnum.toString());
			}

			// apply cell style
			CellStyleHandler.applyCellStyle(configCriteria, c, CellStyleHandler.CELL_DECORATOR_ENUM, null);

		} catch (Exception e) {
			throw new ConverterException(ExceptionMessage.ConverterException_Boolean.getMessage(), e);
		}

		// apply the comment
		CellCommentHandler.applyComment(configCriteria, o, c);

		return isUpdated;
	}

	/**
	 * Apply a explicit formula value at the Cell.
	 * 
	 * @param o
	 *            the object
	 * @param methodRules
	 *            the method rules to use
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	protected static void applyCustomizedRules(final Object o, final String methodRules)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, CustomizedRulesException {
		@SuppressWarnings("rawtypes")
		Class[] argTypes = {};

		Method m = o.getClass().getDeclaredMethod(methodRules, argTypes);

		m.invoke(o, (Object[]) null);
	}

	/* internal methods */

	/**
	 * Recover the value from the Excel.
	 * 
	 * @param c
	 *            the {@link Cell}
	 * @return the content of the cell
	 */
	private static String readCell(final Cell c) {
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
