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
package net.ceos.project.poi.annotated.bean;

import java.util.List;

import net.ceos.project.poi.annotated.annotation.XlsConfiguration;
import net.ceos.project.poi.annotated.annotation.XlsElement;
import net.ceos.project.poi.annotated.annotation.XlsSheet;

@XlsConfiguration(nameFile = "ManageWharehouse")
@XlsSheet(title = "Store")
public class Warehouse {

	@XlsElement(title = "Store name", position = 1)
	private String name;
	
	@XlsElement(title = "Location", position = 2)
	private String location;

	@XlsElement(title = "List of units", position = 3)
	private List<Unit> unitsAvaliable;
	
	@XlsElement(title = "List of units", position = 4)
	private List<Store> storeProvider;
	
}
