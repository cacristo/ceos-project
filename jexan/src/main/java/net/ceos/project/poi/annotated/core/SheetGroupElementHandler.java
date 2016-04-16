package net.ceos.project.poi.annotated.core;

import java.util.Arrays;
import java.util.List;

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
		List<XlsGroupColumn> columnsList = Arrays.asList(configCriteria.getGroupElement().groupColumns());
		columnsList.stream().forEach(group -> {
			if(PredicateFactory.isGroupColumnValid.test(group)) {
				configCriteria.getSheet().groupColumn(group.fromColumn(), group.toColumn());
			}
		});
	}

	/**
	 * Group n elements by rows.
	 * 
	 * @param configCriteria
	 */
	private static void applyToRows(final ConfigCriteria configCriteria) {
		List<XlsGroupRow> rowsList = Arrays.asList(configCriteria.getGroupElement().groupRows());
		rowsList.stream().forEach(group -> {
			if(PredicateFactory.isGroupRowValid.test(group)) {
				configCriteria.getSheet().groupRow(group.fromRow(), group.toRow());
			}
		});
	}
}
