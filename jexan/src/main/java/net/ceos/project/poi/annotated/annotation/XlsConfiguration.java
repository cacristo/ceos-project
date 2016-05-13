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

import net.ceos.project.poi.annotated.definition.ExtensionFileType;

/**
 * This annotation define all the attributes related to the Excel definitions.
 * <br>
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
@Target(TYPE)
@Retention(RUNTIME)
public @interface XlsConfiguration {

	/**
	 * Define the file name of the Excel.
	 * 
	 * @return the name of the file
	 */
	String nameFile();

	/**
	 * Define the Excel extension file.<br>
	 * By default is {@value = ExtensionFileType.XLS}.
	 * 
	 * @return the {@link ExtensionFileType}
	 */
	ExtensionFileType extensionFile() default ExtensionFileType.XLS;
}
