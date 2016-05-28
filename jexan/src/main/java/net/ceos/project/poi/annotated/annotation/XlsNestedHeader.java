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
 * Annotation responsible to manage the nested header at the Sheet.
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
@Target(FIELD)
@Retention(RUNTIME)
public @interface XlsNestedHeader {
	/**
	 * Define the nested element title.
	 * 
	 * @return the title of the element
	 */
	String title();

	/**
	 * Define the start cell position.
	 * <p>
	 * By default is 0.
	 * 
	 * @return the start cell position
	 */
	int startX() default 0;

	/**
	 * Define the end cell position.
	 * <p>
	 * By default is 0.
	 * 
	 * @return the end cell position
	 */
	int endX() default 0;

	/**
	 * Define the start row position.
	 * <p>
	 * By default is 0.
	 * 
	 * @return the start row position
	 */
	int startY() default 0;

	/**
	 * Define the end row position.
	 * <p>
	 * By default is 0.
	 * 
	 * @return the end row position
	 */
	int endY() default 0;

}
