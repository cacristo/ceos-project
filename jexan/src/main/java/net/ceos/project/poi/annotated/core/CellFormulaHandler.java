package net.ceos.project.poi.annotated.core;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellReference;

import net.ceos.project.poi.annotated.definition.ExceptionMessage;
import net.ceos.project.poi.annotated.exception.ConverterException;

/**
 * This class manage the formula or value to apply to one cell.<br>
 * <br>
 * 
 * You can apply a formula by :<br>
 * (1) <b>Create a basic formula</b><br>
 * To create a basic <i>formula</i> using the Excel orientation is simple as “H3
 * * 1.21”<br>
 * 
 * (2) <b>Using the generic function in a formula</b><br>
 * Apply a generic Excel functions: <i>formula</i> as “SUM(E3,F3,G3)”.<br>
 * 
 * (3) <b>Create your own formula</b><br>
 * You have to declare a method where the name start with “formula” + the field
 * will appear at the excel. <br>
 * <br>
 * 
 * You will need to indicate to jexan you are declaring a field such the type is
 * a <i>formula</i>, active the attribute <i>isFormula</i> as true.
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
class CellFormulaHandler {

	private CellFormulaHandler() {
		/* private constructor to hide the implicit public */
	}

	/**
	 * Apply a generic value to the cell.
	 * 
	 * @param configCriteria
	 *            the {@link XConfigCriteria}
	 * @param object
	 *            the object
	 * @param cell
	 *            the {@link Cell} to use
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	protected static void genericHandler(final XConfigCriteria configCriteria, final Object object, final Cell cell)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		if (configCriteria.getElement().isFormula()) {
			if (!toFormula(configCriteria, cell)) {
				// apply the formula
				CellValueHandler.consumeValue(cell, toExplicitFormula(object, configCriteria.getField()));
			}
		} else {
			// apply the value
			CellValueHandler.consumeValue(cell, configCriteria.getField().get(object));
		}
	}

	/**
	 * 
	 * @param configCriteria
	 *            the {@link XConfigCriteria}
	 * @param object
	 *            the object
	 * @param cell
	 *            the {@link Cell} to use
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	protected static void doubleHandler(final XConfigCriteria configCriteria, final Object object, final Cell cell)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		if (configCriteria.getElement().isFormula()) {
			if (!toFormula(configCriteria, cell)) {
				// apply the formula
				CellValueHandler.consumeValue(cell, toExplicitFormula(object, configCriteria.getField()));
			}
		} else {
			// normal manage cell
			if (StringUtils.isNotBlank(configCriteria.getElement().transformMask())) {
				DecimalFormat df = new DecimalFormat(configCriteria.getElement().transformMask());
				CellValueHandler.consumeValue(cell, df.format((Double) configCriteria.getField().get(object)).replace(Constants.COMMA, Constants.DOT));
			} else {
				// apply the value
				CellValueHandler.consumeValue(cell, configCriteria.getField().get(object));
			}
		}
	}

	/**
	 * Apply a BigDecimal value to the cell.
	 * 
	 * @param configCriteria
	 *            the {@link XConfigCriteria}
	 * @param object
	 *            the object
	 * @param cell
	 *            the {@link Cell} to use
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	protected static void bigDecimalHandler(final XConfigCriteria configCriteria, final Object object, final Cell cell)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		if (configCriteria.getElement().isFormula()) {
			// apply the formula
			if (!toFormula(configCriteria, cell)) {
				CellValueHandler.consumeValue(cell, toExplicitFormula(object, configCriteria.getField()));
			}
		} else {
			// normal manage cell
			BigDecimal bd = (BigDecimal) configCriteria.getField().get(object);

			if (bd != null) {
				Double dBigDecimal = bd.doubleValue();
				if (StringUtils.isNotBlank(configCriteria.getElement().transformMask())) {
					DecimalFormat df = new DecimalFormat(configCriteria.getElement().transformMask());
					CellValueHandler.consumeValue(cell, Double.valueOf(df.format(dBigDecimal).replace(Constants.COMMA, Constants.DOT)));
				} else {
					CellValueHandler.consumeValue(cell, dBigDecimal);
				}
			}
		}
	}

	/**
	 * Apply a Boolean value to the cell.
	 * 
	 * @param configCriteria
	 *            the {@link XConfigCriteria}
	 * @param object
	 *            the object
	 * @param cell
	 *            the {@link Cell} to use
	 * @throws IllegalAccessException
	 */
	protected static void booleanHandler(final XConfigCriteria configCriteria, final Object object, final Cell cell)
			throws IllegalAccessException {
		Boolean bBoolean = (Boolean) configCriteria.getField().get(object);
		if (StringUtils.isNotBlank(configCriteria.getElement().transformMask())) {
			// apply format mask defined at transform mask
			String[] split = configCriteria.getElement().transformMask().split(Constants.SLASH);
			CellValueHandler.consumeValue(cell, bBoolean == null ? StringUtils.EMPTY : (bBoolean ? split[0] : split[1]));

		} else {
			// locale mode
			CellValueHandler.consumeValue(cell, (bBoolean == null ? StringUtils.EMPTY : bBoolean).toString());
		}
	}

	/**
	 * Apply a Date value to the cell.
	 * 
	 * @param configCriteria
	 *            the {@link XConfigCriteria}
	 * @param object
	 *            the object
	 * @param cell
	 *            the {@link Cell} to use
	 * @throws IllegalAccessException
	 * @throws ConverterException
	 */
	protected static void dateHandler(final XConfigCriteria configCriteria, final Object object, final Cell cell)
			throws IllegalAccessException, ConverterException {
		Date date = (Date) configCriteria.getField().get(object);
		if (StringUtils.isNotBlank(configCriteria.getElement().transformMask())) {
			// apply transformation mask
			String decorator = configCriteria.getElement().transformMask();
			convertDate(cell, date, decorator);
		} else if (StringUtils.isNotBlank(configCriteria.getElement().formatMask())) {
			// apply format mask
			CellValueHandler.consumeValue(cell, date);
			
		} else {
			// apply default date mask
			CellValueHandler.consumeValue(cell, date);
		}
	}

	/**
	 * Apply a Date value to the cell.
	 * 
	 * @param configCriteria
	 *            the {@link XConfigCriteria}
	 * @param object
	 *            the object
	 * @param cell
	 *            the {@link Cell} to use
	 * @throws IllegalAccessException
	 * @throws ConverterException
	 */
	protected static void localDateHandler(final XConfigCriteria configCriteria, final Object object, final Cell cell)
			throws IllegalAccessException, ConverterException {
		LocalDate date = (LocalDate) configCriteria.getField().get(object);
		if (StringUtils.isNotBlank(configCriteria.getElement().transformMask())) {
			// apply transformation mask
			String decorator = configCriteria.getElement().transformMask();
			convertDate(cell, Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()), decorator);
		} else if (StringUtils.isNotBlank(configCriteria.getElement().formatMask())) {
			// apply format mask
			CellValueHandler.consumeValue(cell, Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		} else {
			// apply default date mask
			CellValueHandler.consumeValue(cell, Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		}
	}

	/**
	 * Apply a Date value to the cell.
	 * 
	 * @param configCriteria
	 *            the {@link XConfigCriteria}
	 * @param object
	 *            the object
	 * @param cell
	 *            the {@link Cell} to use
	 * @throws IllegalAccessException
	 * @throws ConverterException
	 */
	protected static void localDateTimeHandler(final XConfigCriteria configCriteria, final Object object, final Cell cell)
			throws IllegalAccessException, ConverterException {
		LocalDateTime date = (LocalDateTime) configCriteria.getField().get(object);
		if (StringUtils.isNotBlank(configCriteria.getElement().transformMask())) {
			// apply transformation mask
			String decorator = configCriteria.getElement().transformMask();
			convertDate(cell, Date.from(date.atZone(ZoneId.systemDefault()).toInstant()), decorator);
		} else if (StringUtils.isNotBlank(configCriteria.getElement().formatMask())) {
			// apply format mask
			CellValueHandler.consumeValue(cell, Date.from(date.atZone(ZoneId.systemDefault()).toInstant()));
		} else {
			// apply default date mask
			CellValueHandler.consumeValue(cell, Date.from(date.atZone(ZoneId.systemDefault()).toInstant()));
		}
	}

	/* internal methods */

	/**
	 * Apply a formula value at the Cell.
	 * 
	 * @param configCriteria
	 *            the {@link XConfigCriteria} object
	 * @param cell
	 *            the {@link Cell} to use
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private static boolean toFormula(final XConfigCriteria configCriteria, final Cell cell)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		boolean isFormulaApplied = false;

		if (StringUtils.isNotBlank(configCriteria.getElement().formula())) {
			if (configCriteria.getElement().formula().contains(Constants.INDX)) {
				cell.setCellFormula(
						configCriteria.getElement().formula().replace(Constants.INDX, String.valueOf(cell.getRowIndex() + 1)));
				isFormulaApplied = true;

			} else if (configCriteria.getElement().formula().contains(Constants.INDY)) {
				cell.setCellFormula(configCriteria.getElement().formula().replace(Constants.INDY,
						String.valueOf(CellReference.convertNumToColString(cell.getColumnIndex()))));
				isFormulaApplied = true;

			} else {
				cell.setCellFormula(configCriteria.getElement().formula());
				isFormulaApplied = true;
			}
		}

		return isFormulaApplied;
	}

	/**
	 * Apply a explicit formula value at the Cell.
	 * 
	 * @param object
	 *            the object
	 * @param field
	 *            the field
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private static Object toExplicitFormula(final Object object, final Field field)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		@SuppressWarnings("rawtypes")
		Class[] argTypes = {};

		String method = Constants.FORMULA + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);

		Method m = object.getClass().getDeclaredMethod(method, argTypes);

		return m.invoke(object, (Object[]) null);
	}

	/**
	 * Convert date to mask decorator.
	 * 
	 * @param cell
	 *            the {@link Cell} to use
	 * @param date
	 *            the {@link Date}
	 * @param decorator
	 *            the mask to apply
	 * @throws ConverterException
	 */
	private static void convertDate(final Cell cell, final Date date, final String decorator) throws ConverterException {
		try {
			SimpleDateFormat dt = new SimpleDateFormat(decorator);

			String dateFormated = dt.format(date);
			if (dateFormated.equals(decorator)) {
				// if date decorator do not match with a valid mask
				// launch exception
				throw new ConverterException(ExceptionMessage.CONVERTER_DATE.getMessage());
			}
			CellValueHandler.consumeValue(cell, dateFormated);

		} catch (Exception e) {
			throw new ConverterException(ExceptionMessage.CONVERTER_DATE.getMessage(), e);
		}
	}

}
