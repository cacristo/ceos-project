package net.ceos.project.poi.annotated.core;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.junit.Assert;
import org.junit.Test;

import net.ceos.project.poi.annotated.annotation.XlsElement;
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

	/**
	 * Test the override extension file type at {@link XConfigCriteria}.<br>
	 */
	@Test
	public void testOverrideExtensionCSV() throws Exception {

		XConfigCriteria config = new XConfigCriteria();

		config.setExtension(null);
		config.overrideExtensionType(ExtensionFileType.CSV);

		Assert.assertEquals(config.getExtension(), ExtensionFileType.CSV);
	}

	/**
	 * Test the override extension file type at {@link XConfigCriteria}.<br>
	 */
	@Test
	public void testOverrideExtensionXLS() throws Exception {

		XConfigCriteria config = new XConfigCriteria();

		config.setExtension(null);
		config.overrideExtensionType(ExtensionFileType.XLS);

		Assert.assertEquals(config.getExtension(), ExtensionFileType.XLS);
	}

	/**
	 * Test the override extension file type at {@link XConfigCriteria}.<br>
	 */
	@Test
	public void testOverrideExtensionXLSX() throws Exception {

		XConfigCriteria config = new XConfigCriteria();

		config.setExtension(null);
		config.overrideExtensionType(ExtensionFileType.XLSX);

		Assert.assertEquals(config.getExtension(), ExtensionFileType.XLSX);
	}

	/**
	 * Test the override propagation type at {@link XConfigCriteria}.<br>
	 */
	@Test
	public void testOverridePropagationHorizontal() throws Exception {

		XConfigCriteria config = new XConfigCriteria();

		config.setPropagation(null);
		config.overridePropagationType(PropagationType.PROPAGATION_HORIZONTAL);

		Assert.assertEquals(config.getPropagation(), PropagationType.PROPAGATION_HORIZONTAL);
	}

	/**
	 * Test the override propagation type at {@link XConfigCriteria}.<br>
	 */
	@Test
	public void testOverridePropagationVertical() throws Exception {

		XConfigCriteria config = new XConfigCriteria();

		config.setPropagation(null);
		config.overridePropagationType(PropagationType.PROPAGATION_VERTICAL);

		Assert.assertEquals(config.getPropagation(), PropagationType.PROPAGATION_VERTICAL);
	}

	/**
	 * Test the override cascade level at {@link XConfigCriteria}.<br>
	 */
	@Test
	public void testOverrideCascadeLevelBASE() throws Exception {

		XConfigCriteria config = new XConfigCriteria();

		config.setCascadeLevel(null);
		config.overrideCascadeLevel(CascadeType.CASCADE_BASE);

		Assert.assertEquals(config.getCascadeLevel(), CascadeType.CASCADE_BASE);
	}

	/**
	 * Test the override cascade level at {@link XConfigCriteria}.<br>
	 */
	@Test
	public void testOverrideCascadeLevelONE() throws Exception {

		XConfigCriteria config = new XConfigCriteria();

		config.setCascadeLevel(null);
		config.overrideCascadeLevel(CascadeType.CASCADE_LEVEL_ONE);

		Assert.assertEquals(config.getCascadeLevel(), CascadeType.CASCADE_LEVEL_ONE);
	}

	/**
	 * Test the override cascade level at {@link XConfigCriteria}.<br>
	 */
	@Test
	public void testOverrideCascadeLevelTWO() throws Exception {

		XConfigCriteria config = new XConfigCriteria();

		config.setCascadeLevel(null);
		config.overrideCascadeLevel(CascadeType.CASCADE_LEVEL_TWO);

		Assert.assertEquals(config.getCascadeLevel(), CascadeType.CASCADE_LEVEL_TWO);
	}

	/**
	 * Test the override cascade level at {@link XConfigCriteria}.<br>
	 */
	@Test
	public void testOverrideCascadeLevelFULL() throws Exception {

		XConfigCriteria config = new XConfigCriteria();

		config.setCascadeLevel(null);
		config.overrideCascadeLevel(CascadeType.CASCADE_FULL);

		Assert.assertEquals(config.getCascadeLevel(), CascadeType.CASCADE_FULL);
	}

	/**
	 * Test the override header {@link CellDecorator} level at
	 * {@link XConfigCriteria}.<br>
	 */
	@Test
	public void testOverrideHeaderCellDecorator1() throws Exception {

		String decoratorName = CellStyleHandler.CELL_DECORATOR_HEADER;
		short alignment = (short) CellStyle.ALIGN_CENTER;
		short verticalAlignment = (short) CellStyle.VERTICAL_CENTER;
		short border = (short) CellStyle.BORDER_DOTTED;
		short foregroundColor = (short) HSSFColor.YELLOW.index;
		String fontName = "Times New Roman";
		short fontSize = (short) 20;
		boolean isFontBold = true;
		boolean isFontItalic = true;
		byte fontUnderline = (byte) 1;
		boolean isWrapText = true;

		CellDecorator decorator = initializeCellDecorator(decoratorName, alignment, verticalAlignment, border,
				foregroundColor, fontName, fontSize, isFontBold, isFontItalic, fontUnderline, isWrapText);

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
	 * Test the override header {@link CellDecorator} level at
	 * {@link XConfigCriteria}.<br>
	 */
	@Test
	public void testOverrideHeaderCellDecorator2() throws Exception {

		String decoratorName = CellStyleHandler.CELL_DECORATOR_HEADER;
		short alignment = (short) CellStyle.ALIGN_LEFT;
		short verticalAlignment = (short) CellStyle.VERTICAL_TOP;
		short border = (short) CellStyle.BORDER_DOUBLE;
		short foregroundColor = (short) HSSFColor.RED.index;
		String fontName = "Helvetic";
		short fontSize = (short) 8;
		boolean isFontBold = true;
		boolean isFontItalic = false;
		byte fontUnderline = (byte) 1;
		boolean isWrapText = true;

		CellDecorator decorator = initializeCellDecorator(decoratorName, alignment, verticalAlignment, border,
				foregroundColor, fontName, fontSize, isFontBold, isFontItalic, fontUnderline, isWrapText);

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
	 * Test the override header {@link CellDecorator} level at
	 * {@link XConfigCriteria}.<br>
	 */
	@Test
	public void testOverrideHeaderCellDecorator3() throws Exception {

		String decoratorName = CellStyleHandler.CELL_DECORATOR_HEADER;
		short alignment = (short) CellStyle.ALIGN_RIGHT;
		short verticalAlignment = (short) CellStyle.VERTICAL_BOTTOM;
		short border = (short) CellStyle.BORDER_DASH_DOT;
		short foregroundColor = (short) HSSFColor.BLUE.index;
		String fontName = "Arial Black";
		short fontSize = (short) 14;
		boolean isFontBold = false;
		boolean isFontItalic = false;
		byte fontUnderline = (byte) 0;
		boolean isWrapText = true;

		CellDecorator decorator = initializeCellDecorator(decoratorName, alignment, verticalAlignment, border,
				foregroundColor, fontName, fontSize, isFontBold, isFontItalic, fontUnderline, isWrapText);

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
	 * Test the override generic {@link CellDecorator} level at
	 * {@link XConfigCriteria}.<br>
	 */
	@Test
	public void testOverrideGenericCellDecorator1() throws Exception {

		String decoratorName = CellStyleHandler.CELL_DECORATOR_GENERIC;
		short alignment = (short) CellStyle.ALIGN_CENTER;
		short verticalAlignment = (short) CellStyle.VERTICAL_CENTER;
		short border = (short) CellStyle.BORDER_NONE;
		short foregroundColor = (short) HSSFColor.GREEN.index;
		String fontName = "Times New Roman";
		short fontSize = (short) 9;
		boolean isFontBold = false;
		boolean isFontItalic = false;
		byte fontUnderline = (byte) 0;
		boolean isWrapText = false;

		CellDecorator decorator = initializeCellDecorator(decoratorName, alignment, verticalAlignment, border,
				foregroundColor, fontName, fontSize, isFontBold, isFontItalic, fontUnderline, isWrapText);

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
	 * Test the override generic {@link CellDecorator} level at
	 * {@link XConfigCriteria}.<br>
	 */
	@Test
	public void testOverrideGenericCellDecorator2() throws Exception {

		String decoratorName = CellStyleHandler.CELL_DECORATOR_GENERIC;
		short alignment = (short) CellStyle.ALIGN_RIGHT;
		short verticalAlignment = (short) CellStyle.VERTICAL_BOTTOM;
		short border = (short) CellStyle.BORDER_NONE;
		short foregroundColor = (short) HSSFColor.PINK.index;
		String fontName = "Times New Roman";
		short fontSize = (short) 11;
		boolean isFontBold = true;
		boolean isFontItalic = false;
		byte fontUnderline = (byte) 0;
		boolean isWrapText = false;

		CellDecorator decorator = initializeCellDecorator(decoratorName, alignment, verticalAlignment, border,
				foregroundColor, fontName, fontSize, isFontBold, isFontItalic, fontUnderline, isWrapText);

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
	 * Test the override date {@link CellDecorator} level at
	 * {@link XConfigCriteria}.<br>
	 */
	@Test
	public void testOverrideDateCellDecorator1() throws Exception {

		String decoratorName = CellStyleHandler.CELL_DECORATOR_DATE;
		short alignment = (short) CellStyle.ALIGN_CENTER;
		short verticalAlignment = (short) CellStyle.VERTICAL_CENTER;
		short border = (short) CellStyle.BORDER_DOTTED;
		short foregroundColor = (short) HSSFColor.YELLOW.index;
		String fontName = "Times New Roman";
		short fontSize = (short) 11;
		boolean isFontBold = true;
		boolean isFontItalic = false;
		byte fontUnderline = (byte) 1;
		boolean isWrapText = false;

		CellDecorator decorator = initializeCellDecorator(decoratorName, alignment, verticalAlignment, border,
				foregroundColor, fontName, fontSize, isFontBold, isFontItalic, fontUnderline, isWrapText);

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
	 * Test the override date {@link CellDecorator} level at
	 * {@link XConfigCriteria}.<br>
	 */
	@Test
	public void testOverrideDateCellDecorator2() throws Exception {

		String decoratorName = CellStyleHandler.CELL_DECORATOR_DATE;
		short alignment = (short) CellStyle.ALIGN_CENTER;
		short verticalAlignment = (short) CellStyle.VERTICAL_TOP;
		short border = (short) CellStyle.BORDER_DOUBLE;
		short foregroundColor = (short) HSSFColor.RED.index;
		String fontName = "Helvetic";
		short fontSize = (short) 9;
		boolean isFontBold = true;
		boolean isFontItalic = false;
		byte fontUnderline = (byte) 1;
		boolean isWrapText = false;

		CellDecorator decorator = initializeCellDecorator(decoratorName, alignment, verticalAlignment, border,
				foregroundColor, fontName, fontSize, isFontBold, isFontItalic, fontUnderline, isWrapText);

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
	 * Test the override numeric {@link CellDecorator} level at
	 * {@link XConfigCriteria}.<br>
	 */
	@Test
	public void testOverrideNumericCellDecorator1() throws Exception {

		String decoratorName = CellStyleHandler.CELL_DECORATOR_NUMERIC;
		short alignment = (short) CellStyle.ALIGN_RIGHT;
		short verticalAlignment = (short) CellStyle.VERTICAL_TOP;
		short border = (short) CellStyle.BORDER_DASHED;
		short foregroundColor = (short) HSSFColor.ORANGE.index;
		String fontName = "Times New Roman";
		short fontSize = (short) 7;
		boolean isFontBold = false;
		boolean isFontItalic = false;
		byte fontUnderline = (byte) 0;
		boolean isWrapText = false;

		CellDecorator decorator = initializeCellDecorator(decoratorName, alignment, verticalAlignment, border,
				foregroundColor, fontName, fontSize, isFontBold, isFontItalic, fontUnderline, isWrapText);

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
	 * Test the override numeric {@link CellDecorator} level at
	 * {@link XConfigCriteria}.<br>
	 */
	@Test
	public void testOverrideNumericCellDecorator2() throws Exception {

		String decoratorName = CellStyleHandler.CELL_DECORATOR_NUMERIC;
		short alignment = (short) CellStyle.ALIGN_RIGHT;
		short verticalAlignment = (short) CellStyle.VERTICAL_TOP;
		short border = (short) CellStyle.BORDER_MEDIUM;
		short foregroundColor = (short) HSSFColor.PINK.index;
		String fontName = "Arial Black";
		short fontSize = (short) 8;
		boolean isFontBold = false;
		boolean isFontItalic = false;
		byte fontUnderline = (byte) 0;
		boolean isWrapText = false;

		CellDecorator decorator = initializeCellDecorator(decoratorName, alignment, verticalAlignment, border,
				foregroundColor, fontName, fontSize, isFontBold, isFontItalic, fontUnderline, isWrapText);

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
	 * Test the override numeric {@link CellDecorator} level at
	 * {@link XConfigCriteria}.<br>
	 */
	@Test
	public void testOverrideNumericCellDecorator3() throws Exception {

		String decoratorName = CellStyleHandler.CELL_DECORATOR_NUMERIC;
		short alignment = (short) CellStyle.ALIGN_RIGHT;
		short verticalAlignment = (short) CellStyle.VERTICAL_BOTTOM;
		short border = (short) CellStyle.BORDER_DASHED;
		short foregroundColor = (short) HSSFColor.GREEN.index;
		String fontName = "Helvetic";
		short fontSize = (short) 9;
		boolean isFontBold = false;
		boolean isFontItalic = false;
		byte fontUnderline = (byte) 0;
		boolean isWrapText = false;

		CellDecorator decorator = initializeCellDecorator(decoratorName, alignment, verticalAlignment, border,
				foregroundColor, fontName, fontSize, isFontBold, isFontItalic, fontUnderline, isWrapText);

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
	 * Test the override boolean {@link CellDecorator} level at
	 * {@link XConfigCriteria}.<br>
	 */
	@Test
	public void testOverrideBooleanCellDecorator() throws Exception {

		String decoratorName = CellStyleHandler.CELL_DECORATOR_BOOLEAN;
		short alignment = (short) CellStyle.ALIGN_CENTER;
		short verticalAlignment = (short) CellStyle.VERTICAL_CENTER;
		short border = (short) CellStyle.BORDER_HAIR;
		short foregroundColor = (short) HSSFColor.YELLOW.index;
		String fontName = "Times New Roman";
		short fontSize = (short) 11;
		boolean isFontBold = true;
		boolean isFontItalic = true;
		byte fontUnderline = (byte) 1;
		boolean isWrapText = true;

		CellDecorator decorator = initializeCellDecorator(decoratorName, alignment, verticalAlignment, border,
				foregroundColor, fontName, fontSize, isFontBold, isFontItalic, fontUnderline, isWrapText);

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

	@Test
	public void testFormatMask1() {
		XConfigCriteria config = new XConfigCriteria();

		XlsElement element = XlsElementTestFactory.build("Title 1", "date", "", "ddMMyy", "ddMMyyyy", false, "", "",
				10);
		config.setElement(element);

		Assert.assertNotNull(element);
		Assert.assertNotNull(config);
		Assert.assertEquals(config.getFormatMask("thisMask"), detectFormatMaskToUse(element, "thisMask"));
	}

	@Test
	public void testFormatMask2() {
		XConfigCriteria config = new XConfigCriteria();

		XlsElement element = XlsElementTestFactory.build("Title 2", "numeric", "", "0", "", false, "", "", 0);
		config.setElement(element);

		Assert.assertNotNull(element);
		Assert.assertNotNull(config);
		Assert.assertEquals(config.getFormatMask("thisMask"), detectFormatMaskToUse(element, "thisMask"));
	}

	@Test
	public void testFormatMask3() {
		XConfigCriteria config = new XConfigCriteria();

		XlsElement element = XlsElementTestFactory.build("Title 3", "double", "", "", "0.00", false, "", "", 0);
		config.setElement(element);

		Assert.assertNotNull(element);
		Assert.assertNotNull(config);
		Assert.assertEquals(config.getFormatMask("thisMask"), detectFormatMaskToUse(element, "thisMask"));
	}

	@Test
	public void testFormatMask4() {
		XConfigCriteria config = new XConfigCriteria();

		XlsElement element = XlsElementTestFactory.build("Title 4", "boolean", "", "", "", false, "", "", 0);
		config.setElement(element);

		Assert.assertNotNull(element);
		Assert.assertNotNull(config);
		Assert.assertEquals(config.getFormatMask("thisMask"), detectFormatMaskToUse(element, "thisMask"));
	}

	@Test
	public void testTransformMask1() {
		XConfigCriteria config = new XConfigCriteria();

		XlsElement element = XlsElementTestFactory.build("Title 1", "date", "", "ddMMyy", "ddMMyyyy", false, "", "",
				10);
		config.setElement(element);

		Assert.assertNotNull(element);
		Assert.assertNotNull(config);
		Assert.assertEquals(config.getTransformMask("thisMask"), detectTransformMaskToUse(element, "thisMask"));
	}

	@Test
	public void testTransformMask2() {
		XConfigCriteria config = new XConfigCriteria();

		XlsElement element = XlsElementTestFactory.build("Title 2", "numeric", "", "0", "", false, "", "", 0);
		config.setElement(element);

		Assert.assertNotNull(element);
		Assert.assertNotNull(config);
		Assert.assertEquals(config.getTransformMask("thisMask"), detectTransformMaskToUse(element, "thisMask"));
	}

	@Test
	public void testTransformMask3() {
		XConfigCriteria config = new XConfigCriteria();

		XlsElement element = XlsElementTestFactory.build("Title 3", "double", "", "", "0.00", false, "", "", 0);
		config.setElement(element);

		Assert.assertNotNull(element);
		Assert.assertNotNull(config);
		Assert.assertEquals(config.getTransformMask("thisMask"), detectTransformMaskToUse(element, "thisMask"));
	}

	@Test
	public void testTransformMask4() {
		XConfigCriteria config = new XConfigCriteria();

		XlsElement element = XlsElementTestFactory.build("Title 4", "boolean", "", "", "", false, "", "", 0);
		config.setElement(element);

		Assert.assertNotNull(element);
		Assert.assertNotNull(config);
		Assert.assertEquals(config.getTransformMask("thisMask"), detectTransformMaskToUse(element, "thisMask"));
	}

	@Test
	public void testMask1() {
		XConfigCriteria config = new XConfigCriteria();

		XlsElement element = XlsElementTestFactory.build("Title 1", "date", "", "ddMMyy", "ddMMyyyy", false, "", "",
				10);
		config.setElement(element);

		Assert.assertNotNull(element);
		Assert.assertNotNull(config);
		Assert.assertEquals(config.getMask("thisMask"), detectMaskToUse(element, "thisMask"));
	}

	@Test
	public void testMask2() {
		XConfigCriteria config = new XConfigCriteria();

		XlsElement element = XlsElementTestFactory.build("Title 2", "numeric", "", "0", "", false, "", "", 0);
		config.setElement(element);

		Assert.assertNotNull(element);
		Assert.assertNotNull(config);
		Assert.assertEquals(config.getMask("thisMask"), detectMaskToUse(element, "thisMask"));
	}

	@Test
	public void testMask3() {
		XConfigCriteria config = new XConfigCriteria();

		XlsElement element = XlsElementTestFactory.build("Title 3", "double", "", "", "0.00", false, "", "", 0);
		config.setElement(element);

		Assert.assertNotNull(element);
		Assert.assertNotNull(config);
		Assert.assertEquals(config.getMask("thisMask"), detectMaskToUse(element, "thisMask"));
	}

	@Test
	public void testMask4() {
		XConfigCriteria config = new XConfigCriteria();

		XlsElement element = XlsElementTestFactory.build("Title 4", "boolean", "", "", "", false, "", "", 0);
		config.setElement(element);

		Assert.assertNotNull(element);
		Assert.assertNotNull(config);
		Assert.assertEquals(config.getMask("thisMask"), detectMaskToUse(element, "thisMask"));
	}

	@Test
	public void testGenerateCellStyleKey1() {
		XConfigCriteria config = new XConfigCriteria();

		XlsElement element = XlsElementTestFactory.build("Title 1", "date", "", "ddMMyy", "ddMMyyyy", false, "", "",
				10);
		config.setElement(element);

		Assert.assertNotNull(element);
		Assert.assertNotNull(config);
		Assert.assertEquals(config.generateCellStyleKey("", "thisMask"),
				detectMaskToUse(element, "thisMask").concat(element.decorator()));
	}

	@Test
	public void testGenerateCellStyleKey2() {
		XConfigCriteria config = new XConfigCriteria();

		XlsElement element = XlsElementTestFactory.build("Title 2", "numeric", "", "0", "", false, "", "", 0);
		config.setElement(element);

		Assert.assertNotNull(element);
		Assert.assertNotNull(config);
		Assert.assertEquals(config.generateCellStyleKey("", "thisMask"),
				detectMaskToUse(element, "thisMask").concat(element.decorator()));
	}

	@Test
	public void testGenerateCellStyleKey3() {
		XConfigCriteria config = new XConfigCriteria();

		XlsElement element = XlsElementTestFactory.build("Title 3", "double", "", "", "0.00", false, "", "", 0);
		config.setElement(element);

		Assert.assertNotNull(element);
		Assert.assertNotNull(config);
		Assert.assertEquals(config.generateCellStyleKey("", "thisMask"),
				detectMaskToUse(element, "thisMask").concat(element.decorator()));
	}

	@Test
	public void testGenerateCellStyleKey4() {
		XConfigCriteria config = new XConfigCriteria();

		XlsElement element = XlsElementTestFactory.build("Title 4", "boolean", "", "", "", false, "", "", 0);
		config.setElement(element);

		Assert.assertNotNull(element);
		Assert.assertNotNull(config);
		Assert.assertEquals(config.generateCellStyleKey("", "thisMask"),
				detectMaskToUse(element, "thisMask").concat(element.decorator()));
	}

	/* Test complete */

	/**
	 * Test the override the header {@link CellDecorator} specified by
	 * annotation at the element {@link ObjectConfigurable}
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
	 * Test if the override of the header {@link CellDecorator} specified by
	 * annotation at the element {@link ObjectConfigurable} cause any damage at
	 * the moment of unmarshal.
	 */
	@Test
	public void testUnmarshalOverrideHeader() throws Exception {
		ObjectConfigurable oc = new ObjectConfigurable();

		Engine en = new Engine();

		en.unmarshalFromPath(oc, TestUtils.WORKING_DIR_GENERATED_I + "\\");

		ObjectConfigurableBuilder.validateObjectConfigurable(oc);
	}

	/**
	 * Test the override the header {@link CellDecorator} specified by
	 * annotation at the element {@link ObjectConfigurable}
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
	 * Test if the override of the header {@link CellDecorator} specified by
	 * annotation at the element {@link ObjectConfigurable} cause any damage at
	 * the moment of unmarshal.
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
