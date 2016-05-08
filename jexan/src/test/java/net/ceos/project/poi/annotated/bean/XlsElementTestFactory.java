package net.ceos.project.poi.annotated.bean;

import java.lang.annotation.Annotation;

import net.ceos.project.poi.annotated.annotation.XlsElement;

public class XlsElementTestFactory {

	public static XlsElement build(final String title, final String comment, final String decorator, final String formatMask, final String transformMask,
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
		};
	}
}
