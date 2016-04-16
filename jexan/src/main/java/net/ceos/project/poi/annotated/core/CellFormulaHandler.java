package net.ceos.project.poi.annotated.core;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellReference;

import net.ceos.project.poi.annotated.definition.ExceptionMessage;
import net.ceos.project.poi.annotated.exception.ConverterException;

/**
 * This class manage the formula or value to apply to one cell.
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
class CellFormulaHandler {

	private CellFormulaHandler() {
		/* private constructor to hide the implicit public */
	}

	/**
	 * 
	 * @param configCriteria
	 * @param o
	 * @param c
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	protected static void genericHandler(final ConfigCriteria configCriteria, final Object o, final Cell c)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		if (configCriteria.getElement().isFormula()) {
			if (!toFormula(configCriteria, c)) {
				// apply the formula
				CellValueHandler.consumeValue(c, toExplicitFormula(o, configCriteria.getField()));
			}
		} else {
			// apply the value
			CellValueHandler.consumeValue(c, configCriteria.getField().get(o));
		}
	}

	/**
	 * 
	 * @param configCriteria
	 * @param o
	 * @param c
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	protected static void doubleHandler(final ConfigCriteria configCriteria, final Object o, final Cell c)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		if (configCriteria.getElement().isFormula()) {
			if (!toFormula(configCriteria, c)) {
				// apply the formula
				CellValueHandler.consumeValue(c, toExplicitFormula(o, configCriteria.getField()));
			}
		} else {
			// normal manage cell
			if (StringUtils.isNotBlank(configCriteria.getElement().transformMask())) {
				DecimalFormat df = new DecimalFormat(configCriteria.getElement().transformMask());
				CellValueHandler.consumeValue(c, df.format((Double) configCriteria.getField().get(o)).replace(",", "."));
			} else {
				// apply the value
				CellValueHandler.consumeValue(c, configCriteria.getField().get(o));
			}
		}
	}

	/**
	 * 
	 * @param configCriteria
	 * @param o
	 * @param c
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	protected static void bigDecimalHandler(final ConfigCriteria configCriteria, final Object o, final Cell c)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		if (configCriteria.getElement().isFormula()) {
			// apply the formula
			if (!toFormula(configCriteria, c)) {
				CellValueHandler.consumeValue(c, toExplicitFormula(o, configCriteria.getField()));
			}
		} else {
			// normal manage cell
			BigDecimal bd = (BigDecimal) configCriteria.getField().get(o);

			if (bd != null) {
				Double dBigDecimal = bd.doubleValue();
				if (StringUtils.isNotBlank(configCriteria.getElement().transformMask())) {
					DecimalFormat df = new DecimalFormat(configCriteria.getElement().transformMask());
					c.setCellValue(Double.valueOf(df.format(dBigDecimal).replace(",", ".")));
				} else {
					c.setCellValue(dBigDecimal);
				}
			}
		}
	}

	/**
	 * 
	 * @param configCriteria
	 * @param o
	 * @param c
	 * @throws IllegalAccessException
	 */
	protected static void booleanHandler(final ConfigCriteria configCriteria, final Object o, final Cell c)
			throws IllegalAccessException {
		Boolean bBoolean = (Boolean) configCriteria.getField().get(o);
		if (StringUtils.isNotBlank(configCriteria.getElement().transformMask())) {
			// apply format mask defined at transform mask
			String[] split = configCriteria.getElement().transformMask().split("/");
			CellValueHandler.consumeValue(c, bBoolean == null ? "" : (bBoolean ? split[0] : split[1]));

		} else {
			// locale mode
			CellValueHandler.consumeValue(c, (bBoolean == null ? "" : bBoolean).toString());
		}
	}

	/**
	 * 
	 * @param configCriteria
	 * @param o
	 * @param c
	 * @throws IllegalAccessException
	 * @throws ConverterException
	 */
	protected static void dateHandler(final ConfigCriteria configCriteria, final Object o, final Cell c)
			throws IllegalAccessException, ConverterException {
		Date date = (Date) configCriteria.getField().get(o);
		if (StringUtils.isNotBlank(configCriteria.getElement().transformMask())) {
			// apply transformation mask
			String decorator = configCriteria.getElement().transformMask();
			convertDate(c, date, decorator);
		} else if (StringUtils.isNotBlank(configCriteria.getElement().formatMask())) {
			// apply format mask
			c.setCellValue(date);
		} else {
			// apply default date mask
			c.setCellValue(date);
		}
	}

	/* internal methods */

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
	private static boolean toFormula(final ConfigCriteria configCriteria, final Cell c)
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
	private static Object toExplicitFormula(final Object o, final Field f)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		@SuppressWarnings("rawtypes")
		Class[] argTypes = {};

		String method = "formula" + f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1);

		Method m = o.getClass().getDeclaredMethod(method, argTypes);

		return m.invoke(o, (Object[]) null);
	}

	/**
	 * Convert date to mask decorator.
	 * 
	 * @param c
	 *            the {@link Cell} to use
	 * @param date
	 *            the {@link Date}
	 * @param decorator
	 *            the mask to apply
	 * @throws ConverterException
	 */
	private static void convertDate(final Cell c, final Date date, final String decorator) throws ConverterException {
		try {
			SimpleDateFormat dt = new SimpleDateFormat(decorator);

			String dateFormated = dt.format(date);
			if (dateFormated.equals(decorator)) {
				// if date decorator do not match with a valid mask
				// launch exception
				throw new ConverterException(ExceptionMessage.ConverterException_Date.getMessage());
			}
			c.setCellValue(dateFormated);

		} catch (Exception e) {
			throw new ConverterException(ExceptionMessage.ConverterException_Date.getMessage(), e);
		}
	}

}
