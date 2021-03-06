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
package net.ceos.project.poi.annotated.definition;

/**
 * This will define the file extension type.
 * <p>
 * <ul>
 * <li><b>ExtensionFileType.XLSX</b> Related to the files with the extension .xlsx.
 * <li><b>ExtensionFileType.XLS</b> Related to the files with the extension .xls.
 * <li><b>ExtensionFileType.CSV</b> Related to the files with the extension .csv.
 * </ul>
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public enum ExtensionFileType {
	XLSX(".xlsx"), XLS(".xls"), CSV(".csv");

	private String extension;

	private ExtensionFileType(String s) {
		extension = s;
	}

	public String getExtension() {
		return extension;
	}
}
