package net.ceos.project.poi.annotated.core;

import java.math.BigDecimal;
import java.sql.Date;

import org.apache.poi.ss.usermodel.Cell;

import net.ceos.project.poi.annotated.functional.interfaces.CellConsumer;

class CellValueHandler {

	private static CellConsumer<Cell, String> stringValueConsumer = (c, v) -> c.setCellValue(v);
	private static CellConsumer<Cell, Short> shortValueConsumer = (c, v) -> c.setCellValue(v);
	private static CellConsumer<Cell, Integer> integerValueConsumer = (c, v) -> c.setCellValue(v);
	private static CellConsumer<Cell, Long> longValueConsumer = (c, v) -> c.setCellValue(v);
	private static CellConsumer<Cell, Double> doubleValueConsumer = (c, v) -> c.setCellValue(v);
	private static CellConsumer<Cell, Float> floatValueConsumer = (c, v) -> c.setCellValue(v);
	private static CellConsumer<Cell, Boolean> booleanValueConsumer = (c, v) -> c.setCellValue(v);
	private static CellConsumer<Cell, Date> dateValueConsumer = (c, v) -> c.setCellValue(v);

	private CellValueHandler() {
		/* private constructor to hide the implicit public */
	}

	/**
	 * Apply a value to a cell.<br>
	 * Both values are passed as parameter.
	 * 
	 * @param cell
	 *            the {@link Cell} to use
	 * @param value
	 *            the value will be applied to the {@link Cell}
	 */
	protected static void consumeValue(Cell cell, Object value) {
		if (value instanceof String) {
			stringValueConsumer.apply(cell, (String) value);

		} else if (value instanceof Short) {
			shortValueConsumer.apply(cell, (Short) value);

		} else if (value instanceof Integer) {
			integerValueConsumer.apply(cell, (Integer) value);

		} else if (value instanceof Long) {
			longValueConsumer.apply(cell, (Long) value);

		} else if (value instanceof Double) {
			doubleValueConsumer.apply(cell, (Double) value);

		} else if (value instanceof Float) {
			floatValueConsumer.apply(cell, (Float) value);

		} else if (value instanceof BigDecimal) {
			doubleValueConsumer.apply(cell, ((BigDecimal) value).doubleValue());

		} else if (value instanceof Boolean) {
			booleanValueConsumer.apply(cell, (Boolean) value);

		} else if (value instanceof Date) {
			dateValueConsumer.apply(cell, (Date) value);

		}
	}

}
