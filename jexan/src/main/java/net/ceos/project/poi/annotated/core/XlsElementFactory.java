package net.ceos.project.poi.annotated.core;

import java.lang.annotation.Annotation;

import net.ceos.project.poi.annotated.annotation.XlsElement;
import net.ceos.project.poi.annotated.annotation.XlsFreeElement;

public class XlsElementFactory {

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
