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
package net.ceos.project.poi.annotated.functional.interfaces;

/**
 * Functional interface to manage to consume data at POI cell
 * 
 * @version 2.0
 * @author Carlos CRISTO ABREU
 *
 * @param <C>
 *            the cell
 * @param <V>
 *            the value
 */
@FunctionalInterface
public interface CellConsumer<C, V> {
	void apply(C cell, V value);
}
