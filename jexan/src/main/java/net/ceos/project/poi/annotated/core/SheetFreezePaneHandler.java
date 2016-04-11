package net.ceos.project.poi.annotated.core;

/**
 * This class manage the freeze panes to apply to one sheet.
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
class SheetFreezePaneHandler {

	private SheetFreezePaneHandler() {
		/* private constructor to hide the implicit public */
	}

	/**
	 * Apply a freeze pane to the sheet.
	 * 
	 * @param configCriteria
	 */
	protected static void applyFreezePane(final ConfigCriteria configCriteria) {
		if (checkMandatoryFreezePaneArgs(configCriteria) && configCriteria.getFreezePane().leftMostColumn() == 0
				&& configCriteria.getFreezePane().topRow() == 0) {
			createBasicFreezePane(configCriteria);

		} else if (checkMandatoryFreezePaneArgs(configCriteria) && (configCriteria.getFreezePane().leftMostColumn() != 0
				|| configCriteria.getFreezePane().topRow() != 0)) {
			createAdvancedFreezePane(configCriteria);
		}
	}

	/**
	 * Create a base freeze pane.
	 * 
	 * @param configCriteria
	 */
	private static void createBasicFreezePane(final ConfigCriteria configCriteria) {
		configCriteria.getSheet().createFreezePane(configCriteria.getFreezePane().colSplit(),
				configCriteria.getFreezePane().rowSplit());
	}

	/**
	 * Create an advanced freeze pane.
	 * 
	 * @param configCriteria
	 */
	private static void createAdvancedFreezePane(final ConfigCriteria configCriteria) {
		configCriteria.getSheet().createFreezePane(configCriteria.getFreezePane().colSplit(),
				configCriteria.getFreezePane().rowSplit(), configCriteria.getFreezePane().leftMostColumn(),
				configCriteria.getFreezePane().topRow());
	}

	/**
	 * Check if the mandatory values are declared
	 * 
	 * @param configCriteria
	 * @return
	 */
	private static boolean checkMandatoryFreezePaneArgs(final ConfigCriteria configCriteria) {
		return configCriteria.getFreezePane().colSplit() != -1 && configCriteria.getFreezePane().rowSplit() != -1;
	}

}
