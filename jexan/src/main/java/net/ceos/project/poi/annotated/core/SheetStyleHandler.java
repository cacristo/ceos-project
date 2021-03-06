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

import org.apache.poi.xssf.usermodel.XSSFSheet;

/**
 * Manage all the default type of values to apply/read to one cell.
 * <p>
 * <b>To improve: </b> manage correctly the cached formulas
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
class SheetStyleHandler {

	private SheetStyleHandler() {
		/* private constructor to hide the implicit public */
	}

	/**
	 * Add color to the background sheet tab
	 * 
	 * @param configCriteria
	 *            the {@link XConfigCriteria}
	 */
	protected static void applyTabColor(XConfigCriteria configCriteria) {
		if (configCriteria.getSheet() instanceof XSSFSheet
				&& configCriteria.getTabColorSheet() != -1) {
			// FIXME active the code when update POI version [change the return type at Annotation & XConfigCriteria]
			//XSSFColor xColor = new XSSFColor();
			//xColor.setARGBHex(configCriteria.getTabColorSheet());
			//((XSSFSheet) configCriteria.getSheet()).setTabColor(xColor);
			((XSSFSheet) configCriteria.getSheet()).setTabColor(configCriteria.getTabColorSheet());
		}
	}

	/**
	 * Auto resize of each column at the sheet in use.
	 * 
	 * @param configCriteria
	 *            the {@link XConfigCriteria}
	 */
	protected static void applyAutoResizeColumn(XConfigCriteria configCriteria) {
		if (configCriteria.isAutoResizeColumn()) {
			for(int i = configCriteria.getStartCell(); i < configCriteria.getLastCellIndex() + 1; i++){
				configCriteria.getSheet().autoSizeColumn(i);
			}
		}
	}
}
