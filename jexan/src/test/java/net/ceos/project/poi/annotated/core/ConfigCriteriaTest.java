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

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Sheet;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import net.ceos.project.poi.annotated.annotation.XlsElement;
import net.ceos.project.poi.annotated.bean.MultiTypeObject;
import net.ceos.project.poi.annotated.bean.MultiTypeObjectBuilder;
import net.ceos.project.poi.annotated.bean.ObjectConfigurable;
import net.ceos.project.poi.annotated.bean.ObjectConfigurableBuilder;
import net.ceos.project.poi.annotated.bean.XlsElementTestFactory;
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
			{ ExtensionFileType.CSV },
			{ ExtensionFileType.XLS },
			{ ExtensionFileType.XLSX } 
		};
	}
	
	@DataProvider
	public Object[][] criteriaPropagationProvider() throws Exception {
		return new Object[][] { 
			{ PropagationType.PROPAGATION_HORIZONTAL },
			{ PropagationType.PROPAGATION_VERTICAL }
		};
	}
	
	@DataProvider
	public Object[][] criteriaCascadeLevelProvider() throws Exception {
		return new Object[][] { 
			{ CascadeType.CASCADE_BASE },
			{ CascadeType.CASCADE_L1 },
			{ CascadeType.CASCADE_L2 },
			{ CascadeType.CASCADE_L3 },
			{ CascadeType.CASCADE_FULL }
		};
	}
	
	@DataProvider
	public Object[][] headerCellDecoratorProvider() throws Exception {
		return new Object[][] { 
			{ CellStyleHandler.CELL_DECORATOR_HEADER, (short) CellStyle.ALIGN_CENTER, (short) CellStyle.VERTICAL_CENTER, 	(short) CellStyle.BORDER_DOTTED, 	(short) HSSFColor.YELLOW.index,	"Times New Roman", 	(short) 20, true, 	true, 	(byte) 1, true },
			{ CellStyleHandler.CELL_DECORATOR_HEADER, (short) CellStyle.ALIGN_LEFT, 	(short) CellStyle.VERTICAL_TOP, 	(short) CellStyle.BORDER_DOUBLE, 	(short) HSSFColor.RED.index, 	"Helvetic", 		(short) 8, 	true, 	false, 	(byte) 1, true },
			{ CellStyleHandler.CELL_DECORATOR_HEADER, (short) CellStyle.ALIGN_RIGHT, 	(short) CellStyle.VERTICAL_BOTTOM, 	(short) CellStyle.BORDER_DASH_DOT, 	(short) HSSFColor.BLUE.index, 	"Arial Black",		(short) 14, false, 	false, 	(byte) 0, true } 
		};
	}
	
	@DataProvider
	public Object[][] genericCellDecoratorProvider() throws Exception {
		return new Object[][] { 
			{ CellStyleHandler.CELL_DECORATOR_GENERIC, (short) CellStyle.ALIGN_CENTER, (short) CellStyle.VERTICAL_CENTER, 	(short) CellStyle.BORDER_NONE, 	(short) HSSFColor.GREEN.index,	"Times New Roman", 	(short) 9, 	false, 	false, 	(byte) 1, false },
			{ CellStyleHandler.CELL_DECORATOR_GENERIC, (short) CellStyle.ALIGN_RIGHT, 	(short) CellStyle.VERTICAL_BOTTOM, 	(short) CellStyle.BORDER_NONE, 	(short) HSSFColor.PINK.index, 	"Times New Roman",	(short) 11, false, 	false, 	(byte) 0, false } 
		};
	}
	
	@DataProvider
	public Object[][] dateCellDecoratorProvider() throws Exception {
		return new Object[][] { 
			{ CellStyleHandler.CELL_DECORATOR_DATE, (short) CellStyle.ALIGN_CENTER, (short) CellStyle.VERTICAL_CENTER, 	(short) CellStyle.BORDER_DOTTED, 	(short) HSSFColor.YELLOW.index,	"Times New Roman", 	(short) 11, true, 	false, 	(byte) 1, false },
			{ CellStyleHandler.CELL_DECORATOR_DATE, (short) CellStyle.ALIGN_CENTER, (short) CellStyle.VERTICAL_TOP, 	(short) CellStyle.BORDER_DOUBLE, 	(short) HSSFColor.RED.index, 	"Helvetic", 		(short) 9, 	true, 	false, 	(byte) 1, false }
		};
	}
	
	@DataProvider
	public Object[][] numericCellDecoratorProvider() throws Exception {
		return new Object[][] { 
			{ CellStyleHandler.CELL_DECORATOR_NUMERIC, (short) CellStyle.ALIGN_RIGHT, 	(short) CellStyle.VERTICAL_TOP, 	(short) CellStyle.BORDER_DASHED, 	(short) HSSFColor.ORANGE.index,	"Times New Roman", 	(short) 7,	false, 	false, 	(byte) 0, false },
			{ CellStyleHandler.CELL_DECORATOR_NUMERIC, (short) CellStyle.ALIGN_RIGHT, 	(short) CellStyle.VERTICAL_TOP, 	(short) CellStyle.BORDER_MEDIUM, 	(short) HSSFColor.PINK.index, 	"Arial Black", 		(short) 8,	false, 	false, 	(byte) 0, false },
			{ CellStyleHandler.CELL_DECORATOR_NUMERIC, (short) CellStyle.ALIGN_RIGHT, 	(short) CellStyle.VERTICAL_BOTTOM, 	(short) CellStyle.BORDER_DASHED, 	(short) HSSFColor.GREEN.index, 	"Helvetic",			(short) 9,	false, 	false, 	(byte) 0, false } 
		};
	}
	
	@DataProvider
	public Object[][] booleanCellDecoratorProvider() throws Exception {
		return new Object[][] { 
			{ CellStyleHandler.CELL_DECORATOR_BOOLEAN, (short) CellStyle.ALIGN_CENTER, (short) CellStyle.VERTICAL_CENTER, 	(short) CellStyle.BORDER_HAIR, 	(short) HSSFColor.YELLOW.index,	"Times New Roman", 	(short) 11, true, 	true, 	(byte) 1, true }
		};
	}

	@DataProvider
	public Object[][] elementProvider(){
		return new Object[][] { 
			{ XlsElementTestFactory.build("Title 1", "date", 	"", "ddMMyy", 	"ddMMyyyy", false, "", "", 10)},
			{ XlsElementTestFactory.build("Title 2", "numeric",	"", "0", 		"", 		false, "", "", 0)},
			{ XlsElementTestFactory.build("Title 3", "double", 	"", "", 		"0.00", 	false, "", "", 0)},
			{ XlsElementTestFactory.build("Title 4", "boolean",	"", "", 		"", 		false, "", "", 0)}
		};
	}
	
	/**
	 * Test the override extension file type at {@link XConfigCriteria}.
	 */
	@Test(dataProvider="criteriaExtensionProvider")
	public void testOverrideExtension(ExtensionFileType type) throws Exception {
		
		XConfigCriteria config = new XConfigCriteria();

		config.overrideExtensionType(null);
		config.overrideExtensionType(type);
		
		Assert.assertEquals(config.getExtension(), type);
	}
	
	/**
	 * Test the override propagation type at {@link XConfigCriteria}.
	 */
	@Test(dataProvider="criteriaPropagationProvider")
	public void testOverridePropagation(PropagationType type) throws Exception {
		
		XConfigCriteria config = new XConfigCriteria();

		config.setPropagation(null);
		config.overridePropagationType(type);
		
		Assert.assertEquals(config.getPropagation(), type);
	}
	
	/**
	 * Test the override cascade level at {@link XConfigCriteria}.
	 */
	@Test(dataProvider="criteriaCascadeLevelProvider")
	public void testOverrideCascadeLevel(CascadeType type) throws Exception {
		
		XConfigCriteria config = new XConfigCriteria();

		config.setCascadeLevel(null);
		config.overrideCascadeLevel(type);
		
		Assert.assertEquals(config.getCascadeLevel(), type);
	}

	/**
	 * Test the override header {@link CellDecorator} level at {@link XConfigCriteria}.
	 */
	@Test(dataProvider="headerCellDecoratorProvider")
	public void testOverrideHeaderCellDecorator(String decoratorName, short alignment, short verticalAlignment,
			short border, short foregroundColor, String fontName, short fontSize, boolean isFontBold, boolean isFontItalic, byte fontUnderline, boolean isWrapText) throws Exception {
		
		CellDecorator decorator = initializeCellDecorator(decoratorName, alignment, verticalAlignment, border, foregroundColor, fontName,
				fontSize, isFontBold, isFontItalic, fontUnderline, isWrapText);
		
		XConfigCriteria config = new XConfigCriteria();
		config.overrideHeaderCellDecorator(decorator);
		
		CellDecorator decoratorHeader = config.getCellDecoratorMap().get(decoratorName);
		
		Assert.assertNotNull(decorator);
		Assert.assertNotNull(decoratorHeader);
		Assert.assertEquals(decoratorHeader.getDecoratorName(), decoratorName);
		Assert.assertEquals(decoratorHeader.getAlignment(), alignment);
		Assert.assertEquals(decoratorHeader.getVerticalAlignment(), verticalAlignment);
		Assert.assertEquals(decoratorHeader.getBorder(), border);
		Assert.assertEquals(decoratorHeader.getForegroundColor(), foregroundColor);
		Assert.assertEquals(decoratorHeader.getFontName(), fontName);
		Assert.assertEquals(decoratorHeader.getFontSize(), fontSize);
		Assert.assertEquals(decoratorHeader.isFontBold(), isFontBold);
		Assert.assertEquals(decoratorHeader.isFontItalic(), isFontItalic);
		Assert.assertEquals(decoratorHeader.getFontUnderline(), fontUnderline);
		Assert.assertEquals(decoratorHeader.isWrapText(), isWrapText);
	}

	/**
	 * Test the override generic {@link CellDecorator} level at {@link XConfigCriteria}.
	 */
	@Test(dataProvider="genericCellDecoratorProvider")
	public void testOverrideGenericCellDecorator(String decoratorName, short alignment, short verticalAlignment,
			short border, short foregroundColor, String fontName, short fontSize, boolean isFontBold, boolean isFontItalic, byte fontUnderline, boolean isWrapText) throws Exception {
		
		CellDecorator decorator = initializeCellDecorator(decoratorName, alignment, verticalAlignment, border, foregroundColor, fontName,
				fontSize, isFontBold, isFontItalic, fontUnderline, isWrapText);
		
		XConfigCriteria config = new XConfigCriteria();
		config.overrideGenericCellDecorator(decorator);
		
		CellDecorator decoratorGeneric = config.getCellDecoratorMap().get(decoratorName);
		
		Assert.assertNotNull(decorator);
		Assert.assertNotNull(decoratorGeneric);
		Assert.assertEquals(decoratorGeneric.getDecoratorName(), decoratorName);
		Assert.assertEquals(decoratorGeneric.getAlignment(), alignment);
		Assert.assertEquals(decoratorGeneric.getVerticalAlignment(), verticalAlignment);
		Assert.assertEquals(decoratorGeneric.getBorder(), border);
		Assert.assertEquals(decoratorGeneric.getForegroundColor(), foregroundColor);
		Assert.assertEquals(decoratorGeneric.getFontName(), fontName);
		Assert.assertEquals(decoratorGeneric.getFontSize(), fontSize);
		Assert.assertEquals(decoratorGeneric.isFontBold(), isFontBold);
		Assert.assertEquals(decoratorGeneric.isFontItalic(), isFontItalic);
		Assert.assertEquals(decoratorGeneric.getFontUnderline(), fontUnderline);
		Assert.assertEquals(decoratorGeneric.isWrapText(), isWrapText);
	}

	/**
	 * Test the override date {@link CellDecorator} level at {@link XConfigCriteria}.
	 */
	@Test(dataProvider="dateCellDecoratorProvider")
	public void testOverrideDateCellDecorator(String decoratorName, short alignment, short verticalAlignment,
			short border, short foregroundColor, String fontName, short fontSize, boolean isFontBold, boolean isFontItalic, byte fontUnderline, boolean isWrapText) throws Exception {
		
		CellDecorator decorator = initializeCellDecorator(decoratorName, alignment, verticalAlignment, border, foregroundColor, fontName,
				fontSize, isFontBold, isFontItalic, fontUnderline, isWrapText);
		
		XConfigCriteria config = new XConfigCriteria();
		config.overrideDateCellDecorator(decorator);
		
		CellDecorator decoratorDate = config.getCellDecoratorMap().get(decoratorName);
		
		Assert.assertNotNull(decorator);
		Assert.assertNotNull(decoratorDate);
		Assert.assertEquals(decoratorDate.getDecoratorName(), decoratorName);
		Assert.assertEquals(decoratorDate.getAlignment(), alignment);
		Assert.assertEquals(decoratorDate.getVerticalAlignment(), verticalAlignment);
		Assert.assertEquals(decoratorDate.getBorder(), border);
		Assert.assertEquals(decoratorDate.getForegroundColor(), foregroundColor);
		Assert.assertEquals(decoratorDate.getFontName(), fontName);
		Assert.assertEquals(decoratorDate.getFontSize(), fontSize);
		Assert.assertEquals(decoratorDate.isFontBold(), isFontBold);
		Assert.assertEquals(decoratorDate.isFontItalic(), isFontItalic);
		Assert.assertEquals(decoratorDate.getFontUnderline(), fontUnderline);
		Assert.assertEquals(decoratorDate.isWrapText(), isWrapText);
	}

	/**
	 * Test the override numeric {@link CellDecorator} level at {@link XConfigCriteria}.
	 */
	@Test(dataProvider="numericCellDecoratorProvider")
	public void testOverrideNumericCellDecorator(String decoratorName, short alignment, short verticalAlignment,
			short border, short foregroundColor, String fontName, short fontSize, boolean isFontBold, boolean isFontItalic, byte fontUnderline, boolean isWrapText) throws Exception {
		
		CellDecorator decorator = initializeCellDecorator(decoratorName, alignment, verticalAlignment, border, foregroundColor, fontName,
				fontSize, isFontBold, isFontItalic, fontUnderline, isWrapText);
		
		XConfigCriteria config = new XConfigCriteria();
		config.overrideNumericCellDecorator(decorator);
		
		CellDecorator decoratorNumeric = config.getCellDecoratorMap().get(decoratorName);
		
		Assert.assertNotNull(decorator);
		Assert.assertNotNull(decoratorNumeric);
		Assert.assertEquals(decoratorNumeric.getDecoratorName(), decoratorName);
		Assert.assertEquals(decoratorNumeric.getAlignment(), alignment);
		Assert.assertEquals(decoratorNumeric.getVerticalAlignment(), verticalAlignment);
		Assert.assertEquals(decoratorNumeric.getBorder(), border);
		Assert.assertEquals(decoratorNumeric.getForegroundColor(), foregroundColor);
		Assert.assertEquals(decoratorNumeric.getFontName(), fontName);
		Assert.assertEquals(decoratorNumeric.getFontSize(), fontSize);
		Assert.assertEquals(decoratorNumeric.isFontBold(), isFontBold);
		Assert.assertEquals(decoratorNumeric.isFontItalic(), isFontItalic);
		Assert.assertEquals(decoratorNumeric.getFontUnderline(), fontUnderline);
		Assert.assertEquals(decoratorNumeric.isWrapText(), isWrapText);
	}

	/**
	 * Test the override boolean {@link CellDecorator} level at {@link XConfigCriteria}.
	 */
	@Test(dataProvider="booleanCellDecoratorProvider")
	public void testOverrideBooleanCellDecorator(String decoratorName, short alignment, short verticalAlignment,
			short border, short foregroundColor, String fontName, short fontSize, boolean isFontBold, boolean isFontItalic, byte fontUnderline, boolean isWrapText) throws Exception {
		
		CellDecorator decorator = initializeCellDecorator(decoratorName, alignment, verticalAlignment, border, foregroundColor, fontName,
				fontSize, isFontBold, isFontItalic, fontUnderline, isWrapText);
		
		XConfigCriteria config = new XConfigCriteria();
		config.overrideBooleanCellDecorator(decorator);
		
		CellDecorator decoratorBoolean = config.getCellDecoratorMap().get(decoratorName);
		
		Assert.assertNotNull(decorator);
		Assert.assertNotNull(decoratorBoolean);
		Assert.assertEquals(decoratorBoolean.getDecoratorName(), decoratorName);
		Assert.assertEquals(decoratorBoolean.getAlignment(), alignment);
		Assert.assertEquals(decoratorBoolean.getVerticalAlignment(), verticalAlignment);
		Assert.assertEquals(decoratorBoolean.getBorder(), border);
		Assert.assertEquals(decoratorBoolean.getForegroundColor(), foregroundColor);
		Assert.assertEquals(decoratorBoolean.getFontName(), fontName);
		Assert.assertEquals(decoratorBoolean.getFontSize(), fontSize);
		Assert.assertEquals(decoratorBoolean.isFontBold(), isFontBold);
		Assert.assertEquals(decoratorBoolean.isFontItalic(), isFontItalic);
		Assert.assertEquals(decoratorBoolean.getFontUnderline(), fontUnderline);
		Assert.assertEquals(decoratorBoolean.isWrapText(), isWrapText);
	}
	
	/* TODO test getCellStyle */
	/* TODO test getFormatMask */
	/* TODO test getTransformMask */

	@Test(dataProvider="elementProvider")
	public void testFormatMask(XlsElement element) {
		XConfigCriteria config = new XConfigCriteria();
		
		config.setElement(element);
		
		Assert.assertNotNull(element);
		Assert.assertNotNull(config);
		Assert.assertEquals(config.getFormatMask("thisMask"), detectFormatMaskToUse(element, "thisMask"));
	}

	@Test(dataProvider="elementProvider")
	public void testTransformMask(XlsElement element) {
		XConfigCriteria config = new XConfigCriteria();
		
		config.setElement(element);
		
		Assert.assertNotNull(element);
		Assert.assertNotNull(config);
		Assert.assertEquals(config.getTransformMask("thisMask"), detectTransformMaskToUse(element, "thisMask"));
	}

	@Test(dataProvider="elementProvider")
	public void testMask(XlsElement element) {
		XConfigCriteria config = new XConfigCriteria();
		
		config.setElement(element);
		
		Assert.assertNotNull(element);
		Assert.assertNotNull(config);
		Assert.assertEquals(config.getMask("thisMask"), detectMaskToUse(element, "thisMask"));
	}
	
	
	@Test(dataProvider="elementProvider")
	public void testGenerateCellStyleKey(XlsElement element) {
		XConfigCriteria config = new XConfigCriteria();
		
		config.setElement(element);
		
		Assert.assertNotNull(element);
		Assert.assertNotNull(config);
		Assert.assertEquals(config.generateCellStyleKey("", "thisMask"), detectMaskToUse(element, "thisMask").concat(element.decorator()));
	}

	/* Test complete */
	

	/**
	 * Test the override the header {@link CellDecorator} specified by annotation at the
	 * element {@link ObjectConfigurable}
	 */
	@Test
	public void testMarshalOverrideHeader() throws Exception {
		ObjectConfigurable oc = ObjectConfigurableBuilder.buildObjectConfigurable();

		IEngine en = new Engine();

		XConfigCriteria configCriteria = new XConfigCriteria();

		CellDecorator configurationHeader = new CellDecorator();
		// override default header configuration
		configurationHeader.setDecoratorName("header");
		configurationHeader.setAlignment(CellStyle.ALIGN_CENTER);
		configurationHeader.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

		configurationHeader.setBorder(CellStyle.BORDER_THIN);

		configurationHeader.setForegroundColor(HSSFColor.BLUE.index);
		configurationHeader.setFontBold(true);
		configurationHeader.setFontItalic(true);
		configurationHeader.setWrapText(true);
		// en.overrideHeaderCellDecorator(configurationHeader);
		configCriteria.overrideHeaderCellDecorator(configurationHeader);

		CellDecorator dateDecorator = new CellDecorator();
		dateDecorator.setDecoratorName("anotherDate");
		dateDecorator.setAlignment(CellStyle.ALIGN_CENTER);
		dateDecorator.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

		dateDecorator.setForegroundColor(HSSFColor.RED.index);
		dateDecorator.setFontItalic(true);
		dateDecorator.setWrapText(true);

		// en.addSpecificCellDecorator("anotherDate", dateDecorator);
		configCriteria.addSpecificCellDecorator("anotherDate", dateDecorator);

		en.marshalAndSave(configCriteria, oc, TestUtils.WORKING_DIR_GENERATED_II);
	}

	/**
	 * Test if the override of the header {@link CellDecorator} specified by annotation
	 * at the element {@link ObjectConfigurable} cause any damage at the moment
	 * of unmarshal.
	 */
	@Test
	public void testUnmarshalOverrideHeader() throws Exception {
		ObjectConfigurable oc = new ObjectConfigurable();

		Engine en = new Engine();

		en.unmarshalFromPath(oc, TestUtils.WORKING_DIR_GENERATED_I + "\\");

		ObjectConfigurableBuilder.validateObjectConfigurable(oc);
	}

	/**
	 * Test the override the header {@link CellDecorator} specified by annotation at the
	 * element {@link ObjectConfigurable}
	 */
	@Test
	public void testMarshalHeaderAnnotated() throws Exception {
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

		// en.addSpecificCellDecorator("anotherDate", dateDecorator);
		configCriteria.addSpecificCellDecorator("anotherDate", dateDecorator);

		en.marshalAndSave(configCriteria, oc, TestUtils.WORKING_DIR_GENERATED_II);
	}

	/**
	 * Test if the override of the header {@link CellDecorator} specified by annotation
	 * at the element {@link ObjectConfigurable} cause any damage at the moment
	 * of unmarshal.
	 */
	@Test
	public void testUnmarshalHeaderAnnotated() throws Exception {
		ObjectConfigurable oc = new ObjectConfigurable();

		Engine en = new Engine();

		en.unmarshalFromPath(oc, TestUtils.WORKING_DIR_GENERATED_I + "\\");

		ObjectConfigurableBuilder.validateObjectConfigurable(oc);
	}
	
	/* internal methods */

	/**
	 * Initialize the cell decorator
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
	private CellDecorator initializeCellDecorator(String decoratorName, short alignment, short verticalAlignment, short border,
			short foregroundColor, String fontName, short fontSize, boolean isFontBold, boolean isFontItalic,
			byte fontUnderline, boolean isWrapText) {
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
