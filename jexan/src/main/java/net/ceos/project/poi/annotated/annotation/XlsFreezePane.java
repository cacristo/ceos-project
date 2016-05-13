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
 * This annotation create a split (freeze pane). Be aware if any existing freeze
 * pane or split pane is overwritten.<br>
 * 
 * If both colSplit & rowSplit are zero then the existing freeze pane is
 * removed.<br>
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
@Target(TYPE)
@Retention(RUNTIME)
public @interface XlsFreezePane {

	/**
	 * Define the horizontal position of split.
	 * 
	 * @return the horizontal position
	 */
	int colSplit();

	/**
	 * Define the vertical position of split.
	 * 
	 * @return the vertical position
	 */
	int rowSplit();

	/**
	 * Define the left column visible in right pane.
	 * 
	 * @return the left column visible (by default 0)
	 */
	int leftMostColumn() default 0;

	/**
	 * Define the top row visible in bottom pane.
	 * 
	 * @return the top row visible (by default 0)
	 */
	int topRow() default 0;

}
