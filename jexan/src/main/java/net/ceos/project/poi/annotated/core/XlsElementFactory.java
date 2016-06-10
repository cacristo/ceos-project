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
package net.ceos.project.poi.annotated.core;

import java.lang.annotation.Annotation;

import net.ceos.project.poi.annotated.annotation.XlsElement;
import net.ceos.project.poi.annotated.annotation.XlsFreeElement;

/**
 * Factory responsible to convert the {@link XlsFreeElement} into
 * {@link XlsElement}.
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
class XlsElementFactory {
	static XlsElement build(final XlsFreeElement xlsAnnotation) {
		return new XlsElement() {

			@Override
			public Class<? extends Annotation> annotationType() {
				return xlsAnnotation.annotationType();
			}

			@Override
			public String transformMask() {
				return xlsAnnotation.transformMask();
			}

			@Override
			public String title() {
				return xlsAnnotation.title();
			}

			@Override
			public int position() {
				return 0;
			}

			@Override
			public boolean isFormula() {
				return xlsAnnotation.isFormula();
			}

			@Override
			public String formula() {
				return xlsAnnotation.formula();
			}

			@Override
			public String formatMask() {
				return xlsAnnotation.formatMask();
			}

			@Override
			public String decorator() {
				return xlsAnnotation.decorator();
			}

			@Override
			public String customizedRules() {
				return xlsAnnotation.customizedRules();
			}

			@Override
			public String comment() {
				return xlsAnnotation.comment();
			}

			@Override
			public String commentRules() {
				return xlsAnnotation.commentRules();
			}

			@Override
			public int columnWidthInUnits() {
				return xlsAnnotation.columnWidthInUnits();
			}

			@Override
			public boolean parentSheet() {
				return false;
			}
		};
	}
}
