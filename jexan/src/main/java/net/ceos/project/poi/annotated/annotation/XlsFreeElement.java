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

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import net.ceos.project.poi.annotated.definition.TitleOrientationType;

/**
 * Annotation responsible to manage the fields attributes defining the exact position into the Excel. <br>
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
@Target(FIELD)
@Retention(RUNTIME)
public @interface XlsFreeElement {

	/**
	 * Define the title of the element.
	 * 
	 * @return the header title
	 */
	String title();

	/**
	 * Define if the title is visible or not at the element.<br>
	 * By default is false.
	 * 
	 * @return boolean indicating if the title will be visible or not
	 */
	boolean showTitle() default false;

	/**
	 * Define the title orientation.<br>
	 * By default is TitleOrientationType.TOP.
	 * 
	 * @return boolean indicating if the title will be visible or not
	 */
	TitleOrientationType titleOrientation() default TitleOrientationType.TOP; 
	
	/**
	 * Define the row position.<br>
	 * By default is 1.
	 * 
	 * @return the initial row position
	 */
	int row() default 1;

	/**
	 * Define the cell position.<br>
	 * By default is 1.
	 * 
	 * @return the initial cell position
	 */
	int cell() default 1;

	/**
	 * Define the commentary of the element.<br>
	 * By default is empty.
	 * 
	 * @return the commentary
	 */
	String comment() default "";

	/**
	 * Define the commentary of the element applying determined rule(s).<br>
	 * By default is empty.
	 * 
	 * @return the rules to apply
	 */
	String commentRules() default "";

	/**
	 * The decorator to apply to the element.<br>
	 * By default is empty.
	 * 
	 * @return the decorator name
	 */
	String decorator() default "";

	/**
	 * Format the field visually and the original value will remains at the
	 * element.<br>
	 * By default is empty.
	 * 
	 * @return the format mask
	 */
	String formatMask() default "";

	/**
	 * Transform the field with the mask applied and we will lost the original
	 * value at the element.<br>
	 * By default is empty.
	 * 
	 * @return the transformation mask
	 */
	String transformMask() default "";

	/**
	 * Define if the cell is a formula.<br>
	 * By default is false.
	 * 
	 * @return true if formula, otherwise false
	 */
	boolean isFormula() default false;

	/**
	 * Define the formula to apply at the cell.<br>
	 * By default is empty.
	 * 
	 * @return the formula to apply
	 */
	String formula() default "";

	/**
	 * Define the name of the method which contains the customized rules to
	 * apply at the object. By default is empty.
	 * 
	 * @return the name of the method
	 */
	String customizedRules() default "";

	/**
	 * Define the column size to apply at the column.<br>
	 * Apply at version 2.0
	 * 
	 * @return the column size to apply
	 */
	int columnWidthInUnits() default 0;
}
