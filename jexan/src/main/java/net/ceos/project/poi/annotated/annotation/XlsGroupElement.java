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
 * This annotation is responsible for grouping, columns or rows, according the
 * column range or row range respectively.
 * <p>
 * To manage the group of columns use {@link XlsGroupColumn} for each group <br>
 * To manage the group of rows use {@link XlsGroupRow} for each group
 * 
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
@Target(TYPE)
@Retention(RUNTIME)
public @interface XlsGroupElement {

	/**
	 * Define the array of group columns to be used when marshal the object into
	 * Excel.
	 * 
	 * @return the array of {@link XlsGroupColumn}
	 */
	XlsGroupColumn[] groupColumns();

	/**
	 * Define the array of group rows to be used when marshal the object into
	 * Excel.
	 * 
	 * @return the array of {@link XlsGroupRow}
	 */
	XlsGroupRow[] groupRows();

}
