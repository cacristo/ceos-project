package org.jaexcel.framework.JAEX.engine;

import java.lang.annotation.Annotation;

import org.jaexcel.framework.JAEX.annotation.XlsElement;
import org.jaexcel.framework.JAEX.annotation.XlsFreeElement;

public interface XlsElementFactory {

	static XlsElement build(XlsFreeElement xlsAnnotation) {
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
				return "";
			}
		};
	}
}
