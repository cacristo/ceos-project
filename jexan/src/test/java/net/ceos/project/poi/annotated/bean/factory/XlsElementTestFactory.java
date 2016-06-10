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
package net.ceos.project.poi.annotated.bean.factory;

import java.lang.annotation.Annotation;

import net.ceos.project.poi.annotated.annotation.XlsElement;

public interface XlsElementTestFactory {

	static XlsElement build(final String title, final String comment, final String decorator, final String formatMask, final String transformMask,
			final boolean isFormula, final String formula, final String customizedRules, final int columnSize) {
		return new XlsElement() {

			@Override
			public Class<? extends Annotation> annotationType() {
				return null;
			}

			@Override
			public String transformMask() {
				return transformMask;
			}

			@Override
			public String title() {
				return title;
			}

			@Override
			public int position() {
				return 0;
			}

			@Override
			public boolean isFormula() {
				return isFormula;
			}

			@Override
			public String formula() {
				return formula;
			}

			@Override
			public String formatMask() {
				return formatMask;
			}

			@Override
			public String decorator() {
				return decorator;
			}

			@Override
			public String customizedRules() {
				return customizedRules;
			}

			@Override
			public String comment() {
				return comment;
			}

			@Override
			public String commentRules() {
				return "";
			}
			
			@Override
			public int columnWidthInUnits() {
				return columnSize;
			}

			@Override
			public boolean parentSheet() {
				return false;
			}
		};
	}
}
