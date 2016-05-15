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
	 *            the {@link XConfigCriteria}
	 */
	protected static void applyFreezePane(final XConfigCriteria configCriteria) {
		if(PredicateFactory.isMandatoryFreezePaneValid
				.and(PredicateFactory.isOptionalFieldsFreezePaneIgnored)
				.test(configCriteria.getFreezePane())){
			createBasicFreezePane(configCriteria);
			
		} else if(PredicateFactory.isMandatoryFreezePaneValid
				.and(PredicateFactory.isOptionalFieldsFreezePaneActived)
				.test(configCriteria.getFreezePane())){
			createAdvancedFreezePane(configCriteria);
		}
	}

	/**
	 * Create a base freeze pane.
	 * 
	 * @param configCriteria
	 *            the {@link XConfigCriteria}
	 */
	private static void createBasicFreezePane(final XConfigCriteria configCriteria) {
		configCriteria.getSheet().createFreezePane(configCriteria.getFreezePane().colSplit(),
				configCriteria.getFreezePane().rowSplit());
	}

	/**
	 * Create an advanced freeze pane.
	 * 
	 * @param configCriteria
	 *            the {@link XConfigCriteria}
	 */
	private static void createAdvancedFreezePane(final XConfigCriteria configCriteria) {
		configCriteria.getSheet().createFreezePane(configCriteria.getFreezePane().colSplit(),
				configCriteria.getFreezePane().rowSplit(), configCriteria.getFreezePane().leftMostColumn(),
				configCriteria.getFreezePane().topRow());
	}
}
