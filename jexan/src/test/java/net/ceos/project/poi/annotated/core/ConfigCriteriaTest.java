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

import java.util.Collection;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertNotNull;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Sheet;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import net.ceos.project.poi.annotated.annotation.XlsElement;
import net.ceos.project.poi.annotated.bean.MultiTypeObject;
import net.ceos.project.poi.annotated.bean.MultiTypeObjectBuilder;
import net.ceos.project.poi.annotated.bean.ObjectConfigurable;
import net.ceos.project.poi.annotated.bean.ObjectConfigurableBuilder;
import net.ceos.project.poi.annotated.bean.factory.XlsElementTestFactory;
import net.ceos.project.poi.annotated.definition.CascadeType;
import net.ceos.project.poi.annotated.definition.ExtensionFileType;
import net.ceos.project.poi.annotated.definition.PropagationType;

/**
 * Test the {@link XConfigCriteria}
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public class ConfigCriteriaTest {

	@DataProvider
	public Object[][] criteriaProvider() throws Exception {
		MultiTypeObject mto = MultiTypeObjectBuilder.buildMultiTypeObject();
		IEngine en = new Engine();
		Collection<Sheet> sheet = en.marshalToSheet(mto);
		return new Object[][] { { sheet.iterator().next() } };
	}

	@DataProvider
	public Object[][] criteriaExtensionProvider() throws Exception {
		return new Object[][] {
				/* CSV extension */
				{ ExtensionFileType.CSV },
				/* XLS extension */
				{ ExtensionFileType.XLS },
				/* XLSX extension */
				{ ExtensionFileType.XLSX } };
	}

	@DataProvider
	public Object[][] criteriaPropagationProvider() throws Exception {
		return new Object[][] {
				/* Horizontal configuration */
				{ PropagationType.PROPAGATION_HORIZONTAL },
				/* Vertical configuration */
				{ PropagationType.PROPAGATION_VERTICAL } };
	}

	@DataProvider
	public Object[][] criteriaCascadeLevelProvider() throws Exception {
		return new Object[][] {
				/* default configuration */
				{ CascadeType.CASCADE_BASE },
				/* Level 1 configuration */
				{ CascadeType.CASCADE_L1 },
				/* Level 2 configuration */
				{ CascadeType.CASCADE_L2 },
				/* Level 3 configuration */
				{ CascadeType.CASCADE_L3 },
				/* Full configuration */
				{ CascadeType.CASCADE_FULL } };
	}

	@DataProvider
	public Object[][] headerCellDecoratorProvider() throws Exception {
		return new Object[][] {
				/* Header configuration 1 */
				{ CellStyleHandler.CELL_DECORATOR_HEADER, (short) CellStyle.ALIGN_CENTER,
						(short) CellStyle.VERTICAL_CENTER, (short) CellStyle.BORDER_DOTTED,
						(short) HSSFColor.YELLOW.index, "Times New Roman", (short) 20, true, true, (byte) 1, true },
				/* Header configuration 2 */
				{ CellStyleHandler.CELL_DECORATOR_HEADER, (short) CellStyle.ALIGN_LEFT, (short) CellStyle.VERTICAL_TOP,
						(short) CellStyle.BORDER_DOUBLE, (short) HSSFColor.RED.index, "Helvetic", (short) 8, true,
						false, (byte) 1, true },
				/* Header configuration 3 */
				{ CellStyleHandler.CELL_DECORATOR_HEADER, (short) CellStyle.ALIGN_RIGHT,
						(short) CellStyle.VERTICAL_BOTTOM, (short) CellStyle.BORDER_DASH_DOT,
						(short) HSSFColor.BLUE.index, "Arial Black", (short) 14, false, false, (byte) 0, true } };
	}

	@DataProvider
	public Object[][] genericCellDecoratorProvider() throws Exception {
		return new Object[][] {
				/* Generic configuration 1 */
				{ CellStyleHandler.CELL_DECORATOR_GENERIC, (short) CellStyle.ALIGN_CENTER,
						(short) CellStyle.VERTICAL_CENTER, (short) CellStyle.BORDER_NONE, (short) HSSFColor.GREEN.index,
						"Times New Roman", (short) 9, false, false, (byte) 1, false },
				/* Generic configuration 2 */
				{ CellStyleHandler.CELL_DECORATOR_GENERIC, (short) CellStyle.ALIGN_RIGHT,
						(short) CellStyle.VERTICAL_BOTTOM, (short) CellStyle.BORDER_NONE, (short) HSSFColor.PINK.index,
						"Times New Roman", (short) 11, false, false, (byte) 0, false } };
	}

	@DataProvider
	public Object[][] dateCellDecoratorProvider() throws Exception {
		return new Object[][] {
				/* Date configuration 1 */
				{ CellStyleHandler.CELL_DECORATOR_DATE, (short) CellStyle.ALIGN_CENTER,
						(short) CellStyle.VERTICAL_CENTER, (short) CellStyle.BORDER_DOTTED,
						(short) HSSFColor.YELLOW.index, "Times New Roman", (short) 11, true, false, (byte) 1, false },
				/* Date configuration 2 */
				{ CellStyleHandler.CELL_DECORATOR_DATE, (short) CellStyle.ALIGN_CENTER, (short) CellStyle.VERTICAL_TOP,
						(short) CellStyle.BORDER_DOUBLE, (short) HSSFColor.RED.index, "Helvetic", (short) 9, true,
						false, (byte) 1, false } };
	}

	@DataProvider
	public Object[][] numericCellDecoratorProvider() throws Exception {
		return new Object[][] {
				/* Numeric configuration 1 */
				{ CellStyleHandler.CELL_DECORATOR_NUMERIC, (short) CellStyle.ALIGN_RIGHT,
						(short) CellStyle.VERTICAL_TOP, (short) CellStyle.BORDER_DASHED, (short) HSSFColor.ORANGE.index,
						"Times New Roman", (short) 7, false, false, (byte) 0, false },
				/* Numeric configuration 2 */
				{ CellStyleHandler.CELL_DECORATOR_NUMERIC, (short) CellStyle.ALIGN_RIGHT,
						(short) CellStyle.VERTICAL_TOP, (short) CellStyle.BORDER_MEDIUM, (short) HSSFColor.PINK.index,
						"Arial Black", (short) 8, false, false, (byte) 0, false },
				/* Numeric configuration 3 */
				{ CellStyleHandler.CELL_DECORATOR_NUMERIC, (short) CellStyle.ALIGN_RIGHT,
						(short) CellStyle.VERTICAL_BOTTOM, (short) CellStyle.BORDER_DASHED,
						(short) HSSFColor.GREEN.index, "Helvetic", (short) 9, false, false, (byte) 0, false } };
	}

	@DataProvider
	public Object[][] booleanCellDecoratorProvider() throws Exception {
		return new Object[][] { { CellStyleHandler.CELL_DECORATOR_BOOLEAN, (short) CellStyle.ALIGN_CENTER,
				(short) CellStyle.VERTICAL_CENTER, (short) CellStyle.BORDER_HAIR, (short) HSSFColor.YELLOW.index,
				"Times New Roman", (short) 11, true, true, (byte) 1, true } };
	}

	@DataProvider
	public Object[][] elementProvider() {
		return new Object[][] {
				/* XlsElementFactory example 1 */
				{ XlsElementTestFactory.build("Title 1", "date", "", "ddMMyy", "ddMMyyyy", false, "", "", 10) },
				/* XlsElementFactory example 2 */
				{ XlsElementTestFactory.build("Title 2", "numeric", "", "0", "", false, "", "", 0) },
				/* XlsElementFactory example 3 */
				{ XlsElementTestFactory.build("Title 3", "double", "", "", "0.00", false, "", "", 0) },
				/* XlsElementFactory example 4 */
				{ XlsElementTestFactory.build("Title 4", "boolean", "", "", "", false, "", "", 0) } };
	}

	/**
	 * Test the override extension file type at {@link XConfigCriteria}.
	 */
	@Test(dataProvider = "criteriaExtensionProvider")
	public void checkOverrideExtension(ExtensionFileType type) throws Exception {

		XConfigCriteria config = new XConfigCriteria();

		config.overrideExtensionType(null);
		assertNull(config.getExtension());

		config.overrideExtensionType(type);
		assertEquals(config.getExtension(), type);
	}

	/**
	 * Test the override propagation type at {@link XConfigCriteria}.
	 */
	@Test(dataProvider = "criteriaPropagationProvider")
	public void checkOverridePropagation(PropagationType type) throws Exception {

		XConfigCriteria config = new XConfigCriteria();

		config.setPropagation(null);
		assertNull(config.getPropagation());

		config.overridePropagationType(type);
		assertEquals(config.getPropagation(), type);
	}

	/**
	 * Test the override cascade level at {@link XConfigCriteria}.
	 */
	@Test(dataProvider = "criteriaCascadeLevelProvider")
	public void checkOverrideCascadeLevel(CascadeType type) throws Exception {

		XConfigCriteria config = new XConfigCriteria();

		config.setCascadeLevel(null);
		assertNull(config.getCascadeLevel());

		config.overrideCascadeLevel(type);
		assertEquals(config.getCascadeLevel(), type);
	}

	/**
	 * Test the override header {@link CellDecorator} level at
	 * {@link XConfigCriteria}.
	 */
	@Test(dataProvider = "headerCellDecoratorProvider")
	public void checkOverrideHeaderCellDecorator(String decoratorName, short alignment, short verticalAlignment,
			short border, short foregroundColor, String fontName, short fontSize, boolean isFontBold,
			boolean isFontItalic, byte fontUnderline, boolean isWrapText) throws Exception {

		CellDecorator decorator = initializeCellDecorator(decoratorName, alignment, verticalAlignment, border,
				foregroundColor, fontName, fontSize, isFontBold, isFontItalic, fontUnderline, isWrapText);

		XConfigCriteria config = new XConfigCriteria();
		config.overrideHeaderCellDecorator(decorator);

		CellDecorator decoratorHeader = config.getCellDecoratorMap().get(decoratorName);

		assertNotNull(decorator);
		assertNotNull(decoratorHeader);
		assertEquals(decoratorHeader.getDecoratorName(), decoratorName);
		assertEquals(decoratorHeader.getAlignment(), alignment);
		assertEquals(decoratorHeader.getVerticalAlignment(), verticalAlignment);
		assertEquals(decoratorHeader.getBorder(), border);
		assertEquals(decoratorHeader.getForegroundColor(), foregroundColor);
		assertEquals(decoratorHeader.getFontName(), fontName);
		assertEquals(decoratorHeader.getFontSize(), fontSize);
		assertEquals(decoratorHeader.isFontBold(), isFontBold);
		assertEquals(decoratorHeader.isFontItalic(), isFontItalic);
		assertEquals(decoratorHeader.getFontUnderline(), fontUnderline);
		assertEquals(decoratorHeader.isWrapText(), isWrapText);
	}

	/**
	 * Test the override generic {@link CellDecorator} level at
	 * {@link XConfigCriteria}.
	 */
	@Test(dataProvider = "genericCellDecoratorProvider")
	public void checkOverrideGenericCellDecorator(String decoratorName, short alignment, short verticalAlignment,
			short border, short foregroundColor, String fontName, short fontSize, boolean isFontBold,
			boolean isFontItalic, byte fontUnderline, boolean isWrapText) throws Exception {

		CellDecorator decorator = initializeCellDecorator(decoratorName, alignment, verticalAlignment, border,
				foregroundColor, fontName, fontSize, isFontBold, isFontItalic, fontUnderline, isWrapText);

		XConfigCriteria config = new XConfigCriteria();
		config.overrideGenericCellDecorator(decorator);

		CellDecorator decoratorGeneric = config.getCellDecoratorMap().get(decoratorName);

		assertNotNull(decorator);
		assertNotNull(decoratorGeneric);
		assertEquals(decoratorGeneric.getDecoratorName(), decoratorName);
		assertEquals(decoratorGeneric.getAlignment(), alignment);
		assertEquals(decoratorGeneric.getVerticalAlignment(), verticalAlignment);
		assertEquals(decoratorGeneric.getBorder(), border);
		assertEquals(decoratorGeneric.getForegroundColor(), foregroundColor);
		assertEquals(decoratorGeneric.getFontName(), fontName);
		assertEquals(decoratorGeneric.getFontSize(), fontSize);
		assertEquals(decoratorGeneric.isFontBold(), isFontBold);
		assertEquals(decoratorGeneric.isFontItalic(), isFontItalic);
		assertEquals(decoratorGeneric.getFontUnderline(), fontUnderline);
		assertEquals(decoratorGeneric.isWrapText(), isWrapText);
	}

	/**
	 * Test the override date {@link CellDecorator} level at
	 * {@link XConfigCriteria}.
	 */
	@Test(dataProvider = "dateCellDecoratorProvider")
	public void checkOverrideDateCellDecorator(String decoratorName, short alignment, short verticalAlignment,
			short border, short foregroundColor, String fontName, short fontSize, boolean isFontBold,
			boolean isFontItalic, byte fontUnderline, boolean isWrapText) throws Exception {

		CellDecorator decorator = initializeCellDecorator(decoratorName, alignment, verticalAlignment, border,
				foregroundColor, fontName, fontSize, isFontBold, isFontItalic, fontUnderline, isWrapText);

		XConfigCriteria config = new XConfigCriteria();
		config.overrideDateCellDecorator(decorator);

		CellDecorator decoratorDate = config.getCellDecoratorMap().get(decoratorName);

		assertNotNull(decorator);
		assertNotNull(decoratorDate);
		assertEquals(decoratorDate.getDecoratorName(), decoratorName);
		assertEquals(decoratorDate.getAlignment(), alignment);
		assertEquals(decoratorDate.getVerticalAlignment(), verticalAlignment);
		assertEquals(decoratorDate.getBorder(), border);
		assertEquals(decoratorDate.getForegroundColor(), foregroundColor);
		assertEquals(decoratorDate.getFontName(), fontName);
		assertEquals(decoratorDate.getFontSize(), fontSize);
		assertEquals(decoratorDate.isFontBold(), isFontBold);
		assertEquals(decoratorDate.isFontItalic(), isFontItalic);
		assertEquals(decoratorDate.getFontUnderline(), fontUnderline);
		assertEquals(decoratorDate.isWrapText(), isWrapText);
	}

	/**
	 * Test the override numeric {@link CellDecorator} level at
	 * {@link XConfigCriteria}.
	 */
	@Test(dataProvider = "numericCellDecoratorProvider")
	public void checkOverrideNumericCellDecorator(String decoratorName, short alignment, short verticalAlignment,
			short border, short foregroundColor, String fontName, short fontSize, boolean isFontBold,
			boolean isFontItalic, byte fontUnderline, boolean isWrapText) throws Exception {

		CellDecorator decorator = initializeCellDecorator(decoratorName, alignment, verticalAlignment, border,
				foregroundColor, fontName, fontSize, isFontBold, isFontItalic, fontUnderline, isWrapText);

		XConfigCriteria config = new XConfigCriteria();
		config.overrideNumericCellDecorator(decorator);

		CellDecorator decoratorNumeric = config.getCellDecoratorMap().get(decoratorName);

		assertNotNull(decorator);
		assertNotNull(decoratorNumeric);
		assertEquals(decoratorNumeric.getDecoratorName(), decoratorName);
		assertEquals(decoratorNumeric.getAlignment(), alignment);
		assertEquals(decoratorNumeric.getVerticalAlignment(), verticalAlignment);
		assertEquals(decoratorNumeric.getBorder(), border);
		assertEquals(decoratorNumeric.getForegroundColor(), foregroundColor);
		assertEquals(decoratorNumeric.getFontName(), fontName);
		assertEquals(decoratorNumeric.getFontSize(), fontSize);
		assertEquals(decoratorNumeric.isFontBold(), isFontBold);
		assertEquals(decoratorNumeric.isFontItalic(), isFontItalic);
		assertEquals(decoratorNumeric.getFontUnderline(), fontUnderline);
		assertEquals(decoratorNumeric.isWrapText(), isWrapText);
	}

	/**
	 * Test the override boolean {@link CellDecorator} level at
	 * {@link XConfigCriteria}.
	 */
	@Test(dataProvider = "booleanCellDecoratorProvider")
	public void checkOverrideBooleanCellDecorator(String decoratorName, short alignment, short verticalAlignment,
			short border, short foregroundColor, String fontName, short fontSize, boolean isFontBold,
			boolean isFontItalic, byte fontUnderline, boolean isWrapText) throws Exception {

		CellDecorator decorator = initializeCellDecorator(decoratorName, alignment, verticalAlignment, border,
				foregroundColor, fontName, fontSize, isFontBold, isFontItalic, fontUnderline, isWrapText);

		XConfigCriteria config = new XConfigCriteria();
		config.overrideBooleanCellDecorator(decorator);

		CellDecorator decoratorBoolean = config.getCellDecoratorMap().get(decoratorName);

		assertNotNull(decorator);
		assertNotNull(decoratorBoolean);
		assertEquals(decoratorBoolean.getDecoratorName(), decoratorName);
		assertEquals(decoratorBoolean.getAlignment(), alignment);
		assertEquals(decoratorBoolean.getVerticalAlignment(), verticalAlignment);
		assertEquals(decoratorBoolean.getBorder(), border);
		assertEquals(decoratorBoolean.getForegroundColor(), foregroundColor);
		assertEquals(decoratorBoolean.getFontName(), fontName);
		assertEquals(decoratorBoolean.getFontSize(), fontSize);
		assertEquals(decoratorBoolean.isFontBold(), isFontBold);
		assertEquals(decoratorBoolean.isFontItalic(), isFontItalic);
		assertEquals(decoratorBoolean.getFontUnderline(), fontUnderline);
		assertEquals(decoratorBoolean.isWrapText(), isWrapText);
	}

	/* TODO test getCellStyle */

	/**
	 * Test if the formatMask is selected correctly at {@link XConfigCriteria}
	 * 
	 * @param element
	 */
	@Test(dataProvider = "elementProvider")
	public void checkFormatMask(XlsElement element) {
		XConfigCriteria config = new XConfigCriteria();

		config.setElement(element);

		assertNotNull(element);
		assertNotNull(config);
		assertEquals(config.getFormatMask("thisMask"), detectFormatMaskToUse(element, "thisMask"));
	}

	/**
	 * Test if the transformMask is selected correctly at
	 * {@link XConfigCriteria}
	 * 
	 * @param element
	 */
	@Test(dataProvider = "elementProvider")
	public void checkTransformMask(XlsElement element) {
		XConfigCriteria config = new XConfigCriteria();

		config.setElement(element);

		assertNotNull(element);
		assertNotNull(config);
		assertEquals(config.getTransformMask("thisMask"), detectTransformMaskToUse(element, "thisMask"));
	}

	/**
	 * Test if the mask is selected correctly at {@link XConfigCriteria}
	 * 
	 * @param element
	 */
	@Test(dataProvider = "elementProvider")
	public void checkMask(XlsElement element) {
		XConfigCriteria config = new XConfigCriteria();

		config.setElement(element);

		assertNotNull(element);
		assertNotNull(config);
		assertEquals(config.getMask("thisMask"), detectMaskToUse(element, "thisMask"));
	}

	/**
	 * Test if the key {@link CellStyle} is generated correctly at
	 * {@link XConfigCriteria}
	 * 
	 * @param element
	 */
	@Test(dataProvider = "elementProvider")
	public void checkGenerateCellStyleKey(XlsElement element) {
		XConfigCriteria config = new XConfigCriteria();

		config.setElement(element);

		assertNotNull(element);
		assertNotNull(config);
		assertEquals(config.generateCellStyleKey("", "thisMask"),
				detectMaskToUse(element, "thisMask").concat(element.decorator()));
	}

	/**
	 * Validate if the attribute startRow decrease one position
	 */
	@Test
	public void checkStartRow() {
		XConfigCriteria config = new XConfigCriteria();

		config.setStartRow(1);
		assertEquals(config.getStartRow(), 0);

		config.setStartRow(5);
		assertEquals(config.getStartRow(), 4);
	}

	/**
	 * Validate if the attribute startRowInmutable assigned only the first time
	 * and increase one position
	 */
	@Test
	public void checkStartRowInmutable() {
		XConfigCriteria config = new XConfigCriteria();

		config.setStartRow(1);
		assertEquals(config.getStartRowInmutable(), 2);

		config.setStartRow(5);
		assertEquals(config.getStartRowInmutable(), 2);
	}

	/**
	 * Validate if the attribute startCell decrease one position
	 */
	@Test
	public void checkStartCell() {
		XConfigCriteria config = new XConfigCriteria();

		config.setStartCell(6);
		assertEquals(config.getStartCell(), 5);

		config.setStartCell(3);
		assertEquals(config.getStartCell(), 2);
	}

	/**
	 * Validate if the attribute startCellInmutable assigned only the first time
	 * and increase one position
	 */
	@Test
	public void checkStartCellInmutable() {
		XConfigCriteria config = new XConfigCriteria();

		config.setStartCell(3);
		assertEquals(config.getStartCellInmutable(), 4);

		config.setStartCell(1);
		assertEquals(config.getStartCellInmutable(), 4);
	}

	/**
	 * Validate if the attribute lastCellIndex accept only greater values than
	 * himself
	 */
	@Test
	public void checkLastCellIndex() {
		XConfigCriteria config = new XConfigCriteria();

		config.setLastCellIndex(30);
		assertEquals(config.getLastCellIndex(), 30);

		config.setLastCellIndex(88);
		assertEquals(config.getLastCellIndex(), 88);

		config.setLastCellIndex(52);
		assertEquals(config.getLastCellIndex(), 88);

		config.setLastCellIndex(89);
		assertEquals(config.getLastCellIndex(), 89);
	}

	/* Test complete */

	/**
	 * Test the override the header {@link CellDecorator} specified by
	 * annotation at the element {@link ObjectConfigurable}
	 */
	@Test
	public void checkMarshalOverrideHeader() throws Exception {
		ObjectConfigurable oc = ObjectConfigurableBuilder.buildObjectConfigurable();

		IEngine en = new Engine();

		XConfigCriteria configCriteria = new XConfigCriteria();

		CellDecorator configurationHeader = new CellDecorator();
		// override default header configuration
		configurationHeader.setDecoratorName(CellStyleHandler.CELL_DECORATOR_HEADER);
		configurationHeader.setAlignment(CellStyle.ALIGN_CENTER);
		configurationHeader.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		configurationHeader.setBorder(CellStyle.BORDER_THIN);
		configurationHeader.setForegroundColor(HSSFColor.BLUE.index);
		configurationHeader.setFontBold(true);
		configurationHeader.setFontItalic(true);
		configurationHeader.setWrapText(true);
		configCriteria.overrideHeaderCellDecorator(configurationHeader);

		CellDecorator dateDecorator = new CellDecorator();
		dateDecorator.setDecoratorName("anotherDate");
		dateDecorator.setAlignment(CellStyle.ALIGN_CENTER);
		dateDecorator.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		dateDecorator.setForegroundColor(HSSFColor.RED.index);
		dateDecorator.setFontItalic(true);
		dateDecorator.setWrapText(true);
		configCriteria.addSpecificCellDecorator("anotherDate", dateDecorator);

		en.marshalAndSave(configCriteria, oc, TestUtils.WORKING_DIR_GENERATED_II);
	}

	/**
	 * Test if the override of the header {@link CellDecorator} specified by
	 * annotation at the element {@link ObjectConfigurable} cause any damage at
	 * the moment of unmarshal.
	 */
	@Test
	public void checkUnmarshalOverrideHeader() throws Exception {
		ObjectConfigurable oc = new ObjectConfigurable();

		Engine en = new Engine();
		en.unmarshalFromPath(oc, TestUtils.WORKING_DIR_GENERATED_I + "\\");

		ObjectConfigurableBuilder.validateObjectConfigurable(oc);
	}

	/**
	 * Test the add specific {@link CellDecorator} specified by
	 * {@link XConfigCriteria} at the element {@link ObjectConfigurable}
	 */
	@Test
	public void checkMarshalHeaderAnnotated() throws Exception {
		ObjectConfigurable oc = ObjectConfigurableBuilder.buildObjectConfigurable();

		IEngine en = new Engine();

		XConfigCriteria configCriteria = new XConfigCriteria();

		CellDecorator dateDecorator = new CellDecorator();
		dateDecorator.setDecoratorName("anotherDate");
		dateDecorator.setAlignment(CellStyle.ALIGN_CENTER);
		dateDecorator.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		dateDecorator.setForegroundColor(HSSFColor.RED.index);
		dateDecorator.setFontItalic(true);
		dateDecorator.setWrapText(true);
		configCriteria.addSpecificCellDecorator("anotherDate", dateDecorator);

		en.marshalAndSave(configCriteria, oc, TestUtils.WORKING_DIR_GENERATED_II);
	}

	/**
	 * Test the add specific {@link CellDecorator} {@link CellDecorator}
	 * specified by {@link XConfigCriteria} at the element
	 * {@link ObjectConfigurable} cause any damage at the moment of unmarshal.
	 */
	@Test
	public void checkUnmarshalHeaderAnnotated() throws Exception {
		ObjectConfigurable oc = new ObjectConfigurable();

		Engine en = new Engine();
		en.unmarshalFromPath(oc, TestUtils.WORKING_DIR_GENERATED_I + "\\");

		ObjectConfigurableBuilder.validateObjectConfigurable(oc);
	}

	/* internal methods */

	/**
	 * Initialize the cell decorator
	 * 
	 * @param decoratorName
	 * @param alignment
	 * @param verticalAlignment
	 * @param border
	 * @param foregroundColor
	 * @param fontName
	 * @param fontSize
	 * @param isFontBold
	 * @param isFontItalic
	 * @param fontUnderline
	 * @param isWrapText
	 */
	private CellDecorator initializeCellDecorator(String decoratorName, short alignment, short verticalAlignment,
			short border, short foregroundColor, String fontName, short fontSize, boolean isFontBold,
			boolean isFontItalic, byte fontUnderline, boolean isWrapText) {
		CellDecorator decorator = new CellDecorator();

		decorator.setDecoratorName(decoratorName);
		decorator.setFontName(fontName);
		decorator.setFontSize(fontSize);
		decorator.setFontBold(isFontBold);
		decorator.setFontItalic(isFontItalic);
		decorator.setFontUnderline(fontUnderline);
		decorator.setAlignment(alignment);
		decorator.setVerticalAlignment(verticalAlignment);
		decorator.setBorder(border);
		decorator.setForegroundColor(foregroundColor);
		decorator.setWrapText(isWrapText);

		return decorator;
	}

	/**
	 * Generate the format mask to be used.
	 * 
	 * @param element
	 * @return
	 */
	private String detectFormatMaskToUse(XlsElement element, String newMask) {
		return StringUtils.isNotBlank(element.formatMask()) ? element.formatMask() : newMask;
	}

	/**
	 * Generate the transform mask to be used.
	 * 
	 * @param element
	 * @return
	 */
	private String detectTransformMaskToUse(XlsElement element, String newMask) {
		return StringUtils.isNotBlank(element.transformMask()) ? element.transformMask() : newMask;
	}

	/**
	 * Generate the mask to be used.
	 * 
	 * @param element
	 * @return
	 */
	private String detectMaskToUse(XlsElement element, String newMask) {
		return StringUtils.isNotBlank(element.transformMask()) ? element.transformMask()
				: (StringUtils.isNotBlank(element.formatMask()) ? element.formatMask() : newMask);
	}
}
