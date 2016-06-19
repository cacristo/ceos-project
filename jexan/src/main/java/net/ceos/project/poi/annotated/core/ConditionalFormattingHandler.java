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

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.BorderFormatting;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ConditionalFormattingRule;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.FontFormatting;
import org.apache.poi.ss.usermodel.PatternFormatting;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.SheetConditionalFormatting;
import org.apache.poi.ss.util.CellRangeAddress;

import net.ceos.project.poi.annotated.annotation.XlsConditionalFormat;
import net.ceos.project.poi.annotated.annotation.XlsConditionalFormatRules;
import net.ceos.project.poi.annotated.annotation.XlsDecorator;
import net.ceos.project.poi.annotated.definition.ExceptionMessage;
import net.ceos.project.poi.annotated.definition.PropagationType;
import net.ceos.project.poi.annotated.exception.ConfigurationException;
import net.ceos.project.poi.annotated.exception.ElementException;

/**
 * This class manage the conditional formatting applied to one sheet.
 * <p>
 * You have to configure the follow {@link XlsConditionalFormat} at your object
 * with:
 * <ul>
 * <li>{@link XlsConditionalFormatRules} : an array of all rules you want to
 * apply
 * <li>rangeAddress : the base range address according the
 * {@link PropagationType}: letter if <i>horizontal</i>, number if
 * <i>vertical</i>
 * <li>decorator: the decorator name, already declared with {@link XlsDecorator}
 * , to apply if the condition exists
 * </ul>
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public class ConditionalFormattingHandler {

	// see
	// https://poi.apache.org/apidocs/org/apache/poi/hssf/usermodel/HSSFConditionalFormatting.html
	// see
	// https://poi.apache.org/spreadsheet/quick-guide.html#ConditionalFormatting

	private ConditionalFormattingHandler() {
		/* private constructor to hide the implicit public */
	}

	/**
	 * Apply the conditional formatting according the values defined at the
	 * respective annotation. Is only available at the declared object, in
	 * another words, at the linked {@link Sheet} with the object.
	 * 
	 * @param configCriteria
	 *            the {@link XConfigCriteria}
	 * @param conditionalFomat
	 *            the {@link XlsConditionalFormat}
	 * @throws ConfigurationException
	 */
	protected static void applyCondition(XConfigCriteria configCriteria, XlsConditionalFormat conditionalFomat)
			throws ConfigurationException {
		// Define a Conditional Formatting rule, which triggers formatting
		// according the developer definition and applies patternFormatting
		SheetConditionalFormatting sheet = configCriteria.getSheet().getSheetConditionalFormatting();

		/* apply all rules defined */
		int i = 0;
		ConditionalFormattingRule[] rules = new ConditionalFormattingRule[conditionalFomat.rules().length];
		XlsConditionalFormatRules[] rulesAnnotated = conditionalFomat.rules();
		for (XlsConditionalFormatRules rule : rulesAnnotated) {
			ConditionalFormattingRule setRule = sheet.createConditionalFormattingRule(rule.operator(), rule.formula1(),
					StringUtils.isNotBlank(rule.formula2()) ? rule.formula2() : null);

			CellStyle decorator = null;
			try {
				decorator = configCriteria.getCellStyle(conditionalFomat.decorator());
			} catch (ElementException e) {
				throw new ConfigurationException(ExceptionMessage.CONFIGURATION_DECORATOR_MISSING.getMessage(), e);
			}
			/* add FontFormatting */
			FontFormatting fontFormat = setRule.createFontFormatting();
			Font f = configCriteria.getWorkbook().getFontAt(decorator.getFontIndex());
			fontFormat.setFontStyle(f.getItalic(), f.getBold());
			fontFormat.setFontColorIndex(f.getColor());
			fontFormat.setUnderlineType(f.getUnderline());

			/* add BorderFormatting */
			BorderFormatting borderFormat = setRule.createBorderFormatting();
			borderFormat.setBorderBottom(decorator.getBorderBottom());
			borderFormat.setBorderTop(decorator.getBorderTop());
			borderFormat.setBorderLeft(decorator.getBorderLeft());
			borderFormat.setBorderRight(decorator.getBorderRight());
			borderFormat.setBottomBorderColor(decorator.getBottomBorderColor());
			borderFormat.setTopBorderColor(decorator.getTopBorderColor());
			borderFormat.setLeftBorderColor(decorator.getLeftBorderColor());
			borderFormat.setRightBorderColor(decorator.getRightBorderColor());

			/* add PatternFormatting */
			PatternFormatting patternFormat = setRule.createPatternFormatting();
			patternFormat.setFillBackgroundColor(decorator.getFillForegroundColor());

			/* join rule */
			rules[i++] = setRule;
		}

		/* Define a region */
		CellRangeAddress[] regions = { CellRangeAddress.valueOf(CellFormulaConverter
				.calculateRangeAddressFromTemplate(configCriteria, conditionalFomat.rangeAddress())) };

		/* Apply Conditional Formatting rule defined above to the regions */
		sheet.addConditionalFormatting(regions, rules);

	}
}
