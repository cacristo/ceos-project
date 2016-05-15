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

import java.math.BigDecimal;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;

import net.ceos.project.poi.annotated.functional.interfaces.CellConsumer;

/**
 * Manage all the default type of values to apply to one cell.
 * 
 * @version 2.0
 * @author Carlos CRISTO ABREU
 */
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
