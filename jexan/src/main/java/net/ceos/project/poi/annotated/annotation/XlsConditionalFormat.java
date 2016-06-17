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

/**
 * This annotation is responsible to add a new Conditional Formatting to the
 * sheet. Apply 'n' conditional format as you wish to the range address defined.
 * You also need to indicate the decorator to be used.
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
@Target(TYPE)
@Retention(RUNTIME)
public @interface XlsConditionalFormat {

	/**
	 * Define the array of conditional formatting rules to be used when marshal
	 * the object into Excel.
	 * 
	 * @return the array of {@link XlsConditionalFormatRules}
	 */
	XlsConditionalFormatRules[] rules();

	/**
	 * Define the range address to be applied the format.
	 * 
	 * @return the formula
	 */
	String rangeAddress();

	/**
	 * The decorator to apply to the element.
	 * <p>
	 * By default is empty.
	 * 
	 * @return the decorator name
	 */
	String decorator();

}
