package net.ceos.project.poi.annotated.bean;

import java.lang.annotation.Annotation;

import net.ceos.project.poi.annotated.annotation.XlsElement;

public interface XlsElementTestFactory {

	static XlsElement build(String title, String comment, String decorator, String formatMask, String transformMask,
			boolean isFormula, String formula, String customizedRules) {
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
		};
	}
}
