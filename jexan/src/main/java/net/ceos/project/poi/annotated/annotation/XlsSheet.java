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
package net.ceos.project.poi.annotated.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import net.ceos.project.poi.annotated.definition.CascadeType;
import net.ceos.project.poi.annotated.definition.PropagationType;

/**
 * Annotation responsible to manage the basic Sheet configuration.
 * <p>
 * The required attribute is the title which you can define the sheet title. Be
 * aware of the limitations already detected at the Excel, in this case, the
 * sheet name allows only 31 characters.
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
@Target(TYPE)
@Retention(RUNTIME)
public @interface XlsSheet {

	/**
	 * Define the sheet title.
	 * 
	 * @return the title
	 */
	String title();

	/**
	 * Define the row start position.
	 * <p>
	 * By default is 1.
	 * 
	 * @return the initial row position
	 */
	int startRow() default 1;

	/**
	 * Define the cell start position.
	 * <p>
	 * By default is 1.
	 * 
	 * @return the initial cell position
	 */
	int startCell() default 1;

	/**
	 * Define the coordinates of the freeze pane to apply at the sheet
	 * 
	 * @return the freeze pane to apply
	 */
	XlsFreezePane freezePane() default @XlsFreezePane(colSplit = 0, rowSplit = 0)
	;

	/**
	 * Define a group, columns or rows, according the column range or row range
	 * respectively
	 * 
	 * @return the group of elements to apply
	 */
	XlsGroupElement groupElement() default @XlsGroupElement(groupColumns = {
			@XlsGroupColumn(fromColumn = 0, toColumn = 0) }, groupRows = { @XlsGroupRow(fromRow = 0, toRow = 0) })
	;

	/**
	 * Define the propagation type when writing/reading at the Excel.
	 * <p>
	 * By default is PROPAGATION_HORIZONTAL.
	 * 
	 * @return the {@link PropagationType}
	 */
	PropagationType propagation() default PropagationType.PROPAGATION_HORIZONTAL;

	/**
	 * Define the cascade level.
	 * <p>
	 * By default is CASCADE_BASE.
	 * 
	 * @return the {@link CascadeType}
	 */
	CascadeType cascadeLevel() default CascadeType.CASCADE_BASE;
}
