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

/**
 * Annotation responsible to manage the attributes.
 * <br>
 * The cell management is realized by using the start configuration at @XlsSheet
 * with the attributes startCell which you indicate where is placed the first
 * cell to start writing/reading. Combining this definition and
 * the @XlsElement#position you have the cell where be used by jexan at the
 * moment of the write/read.
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
@Target(FIELD)
@Retention(RUNTIME)
public @interface XlsElement {

	/**
	 * Define the title of the element.
	 * 
	 * @return the header title
	 */
	String title();

	/**
	 * The position of the element at the Sheet.
	 * 
	 * @return the position
	 */
	int position();

	/**
	 * Define the commentary of the element.<p>
	 * By default is empty.
	 * 
	 * @return the commentary
	 */
	String comment() default "";

	/**
	 * Define the commentary of the element applying determined rule(s).<p>
	 * By default is empty.
	 * 
	 * @return the rules to apply
	 */
	String commentRules() default "";

	/**
	 * The decorator to apply to the element.<p>
	 * By default is empty.
	 * 
	 * @return the decorator name
	 */
	String decorator() default "";

	/**
	 * Format the field visually and the original value will remains at the
	 * element.<p>
	 * By default is empty.
	 * 
	 * @return the format mask
	 */
	String formatMask() default "";

	/**
	 * Transform the field with the mask applied and we will lost the original
	 * value at the element.<p>
	 * By default is empty.
	 * 
	 * @return the transformation mask
	 */
	String transformMask() default "";

	/**
	 * Define if the cell is a formula.<p>
	 * By default is false.
	 * 
	 * @return true if formula, otherwise false
	 */
	boolean isFormula() default false;

	/**
	 * Define the formula to apply at the cell.<p>
	 * By default is empty.
	 * 
	 * @return the formula to apply
	 */
	String formula() default "";

	/**
	 * Define the name of the method which contains the customized rules to
	 * apply at the object.<p>
	 * By default is empty.
	 * 
	 * @return the name of the method
	 */
	String customizedRules() default "";

	/**
	 * Define the column size to apply at the column.<p>
	 * By default is 0.
	 * 
	 * @return the column size to apply
	 */
	int columnWidthInUnits() default 0;

	/**
	 * By default is false.
	 * 
	 * @return if his parent sheet or not
	 */
	boolean parentSheet() default false;
}
