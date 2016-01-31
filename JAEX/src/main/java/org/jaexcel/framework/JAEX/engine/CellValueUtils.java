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
	 * @param configCriteria
	 *            the {@link ConfigCriteria} object
	 * @param c
	 *            the object
	 * @param c
	 *            the {@link Cell} to use
	 * @return false if problem otherwise true
	 * @throws JAEXConverterException
	 */
	protected static boolean toString(ConfigCriteria configCriteria, Object o, Cell c) throws JAEXConverterException {
		boolean isUpdated = true;
		try {
			if (configCriteria.getElement().isFormula()) {
				// apply the formula
				if (!toFormula(configCriteria, o, c)) {
					c.setCellValue((Long) toExplicitFormula(o, configCriteria.getField()));
				}

			} else {
				// normal manage cell
				c.setCellValue((String) configCriteria.getField().get(o));
			}

			// apply style
			CellStyleUtils.applyCellStyle(configCriteria.getWorkbook(), c,
					configCriteria.getCellStyle(CellStyleUtils.CELL_DECORATOR_GENERIC));

			if (StringUtils.isNotBlank(configCriteria.getElement().comment())) {
				// apply the comment
				CellStyleUtils.applyComment(configCriteria.getWorkbook(), c, configCriteria.getElement().comment(),
						configCriteria.getExtension());
			}

		} catch (Exception e) {
			// TODO: handle exception
			isUpdated = false;
			throw new JAEXConverterException(JAEXExceptionMessage.JAEXConverterException_String.getMessage(), e);
		}
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
	 * @throws JAEXConverterException
	 */
	protected static boolean toInteger(ConfigCriteria configCriteria, Object o, Cell c) throws JAEXConverterException {
		boolean isUpdated = true;
		try {
			if (configCriteria.getElement().isFormula()) {
				// apply the formula
				if (!toFormula(configCriteria, o, c)) {
					c.setCellValue((Long) toExplicitFormula(o, configCriteria.getField()));
				}

			} else {
				// normal manage cell
				c.setCellValue((Integer) configCriteria.getField().get(o));
			}

			// apply cell style
			CellStyleUtils.applyCellStyle(configCriteria.getWorkbook(), c,
					configCriteria.getCellStyle(CellStyleUtils.CELL_DECORATOR_NUMERIC),
					configCriteria.getMask(CellStyleUtils.MASK_DECORATOR_INTEGER));

			if (StringUtils.isNotBlank(configCriteria.getElement().comment())) {
				// apply the comment
				CellStyleUtils.applyComment(configCriteria.getWorkbook(), c, configCriteria.getElement().comment(),
						configCriteria.getExtension());
			}

		} catch (Exception e) {
			// TODO: handle exception
			isUpdated = false;
			throw new JAEXConverterException(JAEXExceptionMessage.JAEXConverterException_Integer.getMessage(), e);
		}
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
	 * @throws JAEXConverterException
	 */
	protected static boolean toLong(ConfigCriteria configCriteria, Object o, Cell c) throws JAEXConverterException {
		boolean isUpdated = true;

		try {
			if (configCriteria.getElement().isFormula()) {
				// apply the formula
				if (!toFormula(configCriteria, o, c)) {
					c.setCellValue((Long) toExplicitFormula(o, configCriteria.getField()));
				}

			} else {
				// normal manage cell
				c.setCellValue((Long) configCriteria.getField().get(o));
			}

			// apply cell style
			CellStyleUtils.applyCellStyle(configCriteria.getWorkbook(), c,
					configCriteria.getCellStyle(CellStyleUtils.CELL_DECORATOR_NUMERIC),
					configCriteria.getMask(CellStyleUtils.MASK_DECORATOR_INTEGER));

			if (StringUtils.isNotBlank(configCriteria.getElement().comment())) {
				// apply the comment
				CellStyleUtils.applyComment(configCriteria.getWorkbook(), c, configCriteria.getElement().comment(),
						configCriteria.getExtension());
			}

		} catch (Exception e) {
			// TODO: handle exception
			isUpdated = false;
			throw new JAEXConverterException(JAEXExceptionMessage.JAEXConverterException_Long.getMessage(), e);
		}
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
	 * @throws JAEXConverterException
	 */
	protected static boolean toDouble(ConfigCriteria configCriteria, Object o, Cell c) throws JAEXConverterException {
		boolean isUpdated = true;

		try {
			// FIXME use the comment below to manage decimalScale
			// Double d = (Double) f.get(o);
			// BigDecimal bd = new BigDecimal(d);
			// bd.setScale(2, BigDecimal.ROUND_HALF_UP);

			CellStyle cs = configCriteria.getCellStyle(CellStyleUtils.CELL_DECORATOR_NUMERIC);

			if (configCriteria.getElement().isFormula()) {
				// apply the formula
				if (!toFormula(configCriteria, o, c)) {
					c.setCellValue((Double) toExplicitFormula(o, configCriteria.getField()));

					// apply cell style
					CellStyleUtils.applyCellStyle(configCriteria.getWorkbook(), c, cs);
				}

			} else {
				// normal manage cell
				if (StringUtils.isNotBlank(configCriteria.getElement().transformMask())) {
					DecimalFormat df = new DecimalFormat(configCriteria.getElement().transformMask());
					c.setCellValue(df.format((Double) configCriteria.getField().get(o)));

					// apply cell style
					CellStyleUtils.applyCellStyle(configCriteria.getWorkbook(), c, cs);
				} else {
					c.setCellValue((Double) configCriteria.getField().get(o));

					// apply cell style
					CellStyleUtils.applyCellStyle(configCriteria.getWorkbook(), c, cs,
							configCriteria.getFormatMask(CellStyleUtils.MASK_DECORATOR_DOUBLE));
				}
			}

			if (StringUtils.isNotBlank(configCriteria.getElement().comment())) {
				// apply the comment
				CellStyleUtils.applyComment(configCriteria.getWorkbook(), c, configCriteria.getElement().comment(),
						configCriteria.getExtension());
			}

		} catch (Exception e) {
			// TODO: handle exception
			isUpdated = false;
			throw new JAEXConverterException(JAEXExceptionMessage.JAEXConverterException_Double.getMessage(), e);
		}
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
	 * @throws JAEXConverterException
	 */
	protected static boolean toBigDecimal(ConfigCriteria configCriteria, Object o, Cell c)
			throws JAEXConverterException {
		boolean isUpdated = true;

		CellStyle cs = configCriteria.getCellStyle(CellStyleUtils.CELL_DECORATOR_NUMERIC);

		try {
			if (configCriteria.getElement().isFormula()) {
				// apply the formula
				if (!toFormula(configCriteria, o, c)) {
					c.setCellValue((Double) toExplicitFormula(o, configCriteria.getField()));

					// apply cell style
					CellStyleUtils.applyCellStyle(configCriteria.getWorkbook(), c, cs);
				}

			} else {
				// normal manage cell
				BigDecimal bd = (BigDecimal) configCriteria.getField().get(o);

				// FIXME use the comment below to manage decimalScale
				// bd.setScale(2, BigDecimal.ROUND_HALF_UP);
				if (bd != null) {
					Double dBigDecimal = bd.doubleValue();
					if (StringUtils.isNotBlank(configCriteria.getElement().transformMask())) {
						DecimalFormat df = new DecimalFormat(configCriteria.getElement().transformMask());
						c.setCellValue(df.format(dBigDecimal));
						// apply cell style
						CellStyleUtils.applyCellStyle(configCriteria.getWorkbook(), c, cs);

					} else {
						c.setCellValue(dBigDecimal);
						// apply cell style
						CellStyleUtils.applyCellStyle(configCriteria.getWorkbook(), c, cs,
								configCriteria.getFormatMask(CellStyleUtils.MASK_DECORATOR_DOUBLE));
					}
				}
			}

			if (StringUtils.isNotBlank(configCriteria.getElement().comment())) {
				// apply the comment
				CellStyleUtils.applyComment(configCriteria.getWorkbook(), c, configCriteria.getElement().comment(),
						configCriteria.getExtension());
			}

		} catch (Exception e) {
			// TODO: handle exception
			isUpdated = false;
			throw new JAEXConverterException(JAEXExceptionMessage.JAEXConverterException_BigDecimal.getMessage(), e);
		}
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
	 * @throws JAEXConverterException
	 */
	protected static boolean toDate(ConfigCriteria configCriteria, Object o, Cell c) throws JAEXConverterException {
		boolean isUpdated = true;

		CellStyle cs = configCriteria.getCellStyle(CellStyleUtils.CELL_DECORATOR_DATE);
		try {

			Date dDate = (Date) configCriteria.getField().get(o);
			if (dDate != null) {

				if (StringUtils.isNotBlank(configCriteria.getElement().transformMask())) {
					// apply transformation mask
					String decorator = configCriteria.getElement().transformMask();
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
						CellStyleUtils.applyCellStyle(configCriteria.getWorkbook(), c, cs);
					} catch (Exception e) {
						throw new JAEXConverterException(JAEXExceptionMessage.JAEXConverterException_Date.getMessage(),
								e);
					}

				} else if (StringUtils.isNotBlank(configCriteria.getElement().formatMask())) {
					// apply format mask
					c.setCellValue(dDate);
					// apply cell style
					CellStyleUtils.applyCellStyle(configCriteria.getWorkbook(), c, cs,
							configCriteria.getElement().formatMask());

				} else {
					// apply default date mask
					c.setCellValue(dDate);
					// apply cell style
					CellStyleUtils.applyCellStyle(configCriteria.getWorkbook(), c, cs,
							CellStyleUtils.MASK_DECORATOR_DATE);

				}

				if (StringUtils.isNotBlank(configCriteria.getElement().comment())) {
					// apply the comment
					CellStyleUtils.applyComment(configCriteria.getWorkbook(), c, configCriteria.getElement().comment(),
							configCriteria.getExtension());
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			isUpdated = false;
			throw new JAEXConverterException(JAEXExceptionMessage.JAEXConverterException_Date.getMessage(), e);
		}
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
	 * @throws JAEXConverterException
	 */
	protected static boolean toFloat(ConfigCriteria configCriteria, Object o, Cell c) throws JAEXConverterException {
		boolean isUpdated = true;

		CellStyle cs = configCriteria.getCellStyle(CellStyleUtils.CELL_DECORATOR_NUMERIC);
		try {
			if (configCriteria.getElement().isFormula()) {
				// apply the formula
				if (!toFormula(configCriteria, o, c)) {
					c.setCellValue((Double) toExplicitFormula(o, configCriteria.getField()));

					// apply cell style
					CellStyleUtils.applyCellStyle(configCriteria.getWorkbook(), c, cs);
				}

			} else {
				// normal manage cell
				c.setCellValue((Float) configCriteria.getField().get(o));

				// apply cell style
				CellStyleUtils.applyCellStyle(configCriteria.getWorkbook(), c, cs,
						configCriteria.getMask(CellStyleUtils.MASK_DECORATOR_DOUBLE));
			}

			if (StringUtils.isNotBlank(configCriteria.getElement().comment())) {
				// apply the comment
				CellStyleUtils.applyComment(configCriteria.getWorkbook(), c, configCriteria.getElement().comment(),
						configCriteria.getExtension());
			}

		} catch (Exception e) {
			// TODO: handle exception
			isUpdated = false;
			throw new JAEXConverterException(JAEXExceptionMessage.JAEXConverterException_Float.getMessage(), e);
		}
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
	 * @throws JAEXConverterException
	 */
	protected static boolean toBoolean(ConfigCriteria configCriteria, Object o, Cell c) throws JAEXConverterException {
		boolean isUpdated = true;

		CellStyle cs = configCriteria.getCellStyle(CellStyleUtils.CELL_DECORATOR_BOOLEAN);
		try {
			Boolean bBoolean = (Boolean) configCriteria.getField().get(o);
			if (StringUtils.isNotBlank(configCriteria.getElement().transformMask())) {
				// apply format mask defined at transform mask
				String[] split = configCriteria.getElement().transformMask().split("/");
				c.setCellValue((bBoolean == null ? "" : (bBoolean ? split[0] : split[1])));

				// apply cell style
				CellStyleUtils.applyCellStyle(configCriteria.getWorkbook(), c, cs);

			} else {
				// locale mode
				c.setCellValue((bBoolean == null ? "" : bBoolean).toString());

				// apply cell style
				CellStyleUtils.applyCellStyle(configCriteria.getWorkbook(), c, cs);
			}

			if (StringUtils.isNotBlank(configCriteria.getElement().comment())) {
				// apply the comment
				CellStyleUtils.applyComment(configCriteria.getWorkbook(), c, configCriteria.getElement().comment(),
						configCriteria.getExtension());
			}

		} catch (Exception e) {
			// TODO: handle exception
			isUpdated = false;
			throw new JAEXConverterException(JAEXExceptionMessage.JAEXConverterException_Boolean.getMessage(), e);
		}
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
	 * @throws JAEXConverterException
	 */
	protected static boolean toEnum(ConfigCriteria configCriteria, Object o, Cell c) throws JAEXConverterException {
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
			CellStyleUtils.applyCellStyle(configCriteria.getWorkbook(), c,
					configCriteria.getCellStyle(CellStyleUtils.CELL_DECORATOR_ENUM));

			if (StringUtils.isNotBlank(configCriteria.getElement().comment())) {
				// apply the comment
				CellStyleUtils.applyComment(configCriteria.getWorkbook(), c, configCriteria.getElement().comment(),
						configCriteria.getExtension());
			}

		} catch (Exception e) {
			// TODO: handle exception
			isUpdated = false;
			throw new JAEXConverterException(JAEXExceptionMessage.JAEXConverterException_Boolean.getMessage(), e);
		}
		return isUpdated;
	}

	/**
	 * Apply a formula value at the Cell.
	 * 
	 * @param configCriteria
	 *            the {@link ConfigCriteria} object
	 * @param o
	 *            the object
	 * @param c
	 *            the {@link Cell} to use
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private static boolean toFormula(ConfigCriteria configCriteria, Object o, Cell c)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		boolean isFormulaApplied = false;

		if (StringUtils.isNotBlank(configCriteria.getElement().formula())) {
			if (configCriteria.getElement().formula().contains("idx")) {
				c.setCellFormula(
						configCriteria.getElement().formula().replace("idx", String.valueOf(c.getRowIndex() + 1)));
				isFormulaApplied = true;

			} else if (configCriteria.getElement().formula().contains("idy")) {
				c.setCellFormula(configCriteria.getElement().formula().replace("idy",
						String.valueOf(CellReference.convertNumToColString(c.getColumnIndex()))));
				isFormulaApplied = true;

			} else {
				c.setCellFormula(configCriteria.getElement().formula());
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
	 * @param methodRules
	 *            the method rules to use
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
