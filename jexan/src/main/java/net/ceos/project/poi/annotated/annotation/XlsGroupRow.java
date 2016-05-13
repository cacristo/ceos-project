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
 * This annotation create an outline for the provided row range.<br>
 * In another words, tie a range of rows together and give us the possibility to
 * collapse or expand the group.
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
@Target(TYPE)
@Retention(RUNTIME)
public @interface XlsGroupRow {

	/**
	 * Define the beginning of the row range.
	 * 
	 * @return the start row
	 */
	int fromRow();

	/**
	 * Define the end of the row range.
	 * 
	 * @return the end row
	 */
	int toRow();

}
