package net.ceos.project.poi.annotated.core;

import net.ceos.project.poi.annotated.annotation.XlsGroupColumn;
import net.ceos.project.poi.annotated.annotation.XlsGroupRow;

/**
 * This class manage the group of columns and/or rows to apply to one sheet.
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
class SheetGroupElementHandler {

	private SheetGroupElementHandler() {
		/* private constructor to hide the implicit public */
	}

	/**
	 * Apply a freeze pane to the sheet.
	 * 
	 * @param configCriteria
	 */
	protected static void applyGroupElements(final ConfigCriteria configCriteria) {
		if (configCriteria.getGroupElement() != null) {
			applyToColumns(configCriteria);

			applyToRows(configCriteria);
		}
	}

	/**
	 * Group n elements by columns.
	 * 
	 * @param configCriteria
	 */
	private static void applyToColumns(final ConfigCriteria configCriteria) {
		XlsGroupColumn[] columns = configCriteria.getGroupElement().groupColumns();
		for (int i = 0; i < columns.length; i++) {
			if (columns[i].fromColumn() != 0 || columns[i].toColumn() != 0) {
				configCriteria.getSheet().groupColumn(columns[i].fromColumn(), columns[i].toColumn());
			}
		}
	}

	/**
	 * Group n elements by rows.
	 * 
	 * @param configCriteria
	 */
	private static void applyToRows(final ConfigCriteria configCriteria) {
		XlsGroupRow[] rows = configCriteria.getGroupElement().groupRows();
		for (int i = 0; i < rows.length; i++) {
			if (rows[i].fromRow() != 0 || rows[i].toRow() != 0) {
				configCriteria.getSheet().groupRow(rows[i].fromRow(), rows[i].toRow());
			}
		}
	}
}
