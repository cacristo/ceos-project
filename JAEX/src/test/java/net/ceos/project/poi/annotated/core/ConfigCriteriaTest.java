package net.ceos.project.poi.annotated.core;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Sheet;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import net.ceos.project.poi.annotated.bean.MultiTypeObject;
import net.ceos.project.poi.annotated.bean.MultiTypeObjectBuilder;
import net.ceos.project.poi.annotated.definition.CascadeType;
import net.ceos.project.poi.annotated.definition.ExtensionFileType;
import net.ceos.project.poi.annotated.definition.PropagationType;

/**
 * Test the {@link ConfigCriteria}
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public class ConfigCriteriaTest {

	@DataProvider
	public Object[][] criteriaProvider() throws Exception {
		MultiTypeObject mto = MultiTypeObjectBuilder.buildMultiTypeObject();
		IEngine en = new Engine();
		Sheet sheet = en.marshalToSheet(mto);
		return new Object[][] { { sheet } };
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
			{ CascadeType.CASCADE_LEVEL_ONE },
			{ CascadeType.CASCADE_LEVEL_TWO },
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
	
	/**
	 * Test the override extension file type at {@link ConfigCriteria}.<br>
	 */
	@Test(dataProvider="criteriaExtensionProvider")
	public void testOverrideExtension(ExtensionFileType type) throws Exception {
		
		ConfigCriteria config = new ConfigCriteria();

		config.setExtension(null);
		config.overrideExtensionType(type);
		
		Assert.assertEquals(config.getExtension(), type);
	}
	
	/**
	 * Test the override propagation type at {@link ConfigCriteria}.<br>
	 */
	@Test(dataProvider="criteriaPropagationProvider")
	public void testOverridePropagation(PropagationType type) throws Exception {
		
		ConfigCriteria config = new ConfigCriteria();

		config.setPropagation(null);
		config.overridePropagationType(type);
		
		Assert.assertEquals(config.getPropagation(), type);
	}
	
	/**
	 * Test the override cascade level at {@link ConfigCriteria}.<br>
	 */
	@Test(dataProvider="criteriaCascadeLevelProvider")
	public void testOverrideCascadeLevel(CascadeType type) throws Exception {
		
		ConfigCriteria config = new ConfigCriteria();

		config.setCascadeLevel(null);
		config.overrideCascadeLevel(type);
		
		Assert.assertEquals(config.getCascadeLevel(), type);
	}

	/**
	 * Test the override header {@link CellDecorator} level at {@link ConfigCriteria}.<br>
	 */
	@Test(dataProvider="headerCellDecoratorProvider")
	public void testOverrideHeaderCellDecorator(String decoratorName, short alignment, short verticalAlignment,
			short border, short foregroundColor, String fontName, short fontSize, boolean isFontBold, boolean isFontItalic, byte fontUnderline, boolean isWrapText) throws Exception {
		
		CellDecorator decorator = initializeCellDecorator(decoratorName, alignment, verticalAlignment, border, foregroundColor, fontName,
				fontSize, isFontBold, isFontItalic, fontUnderline, isWrapText);
		
		ConfigCriteria config = new ConfigCriteria();
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
	 * Test the override date {@link CellDecorator} level at {@link ConfigCriteria}.<br>
	 */
	@Test(dataProvider="dateCellDecoratorProvider")
	public void testOverrideDateCellDecorator(String decoratorName, short alignment, short verticalAlignment,
			short border, short foregroundColor, String fontName, short fontSize, boolean isFontBold, boolean isFontItalic, byte fontUnderline, boolean isWrapText) throws Exception {
		
		CellDecorator decorator = initializeCellDecorator(decoratorName, alignment, verticalAlignment, border, foregroundColor, fontName,
				fontSize, isFontBold, isFontItalic, fontUnderline, isWrapText);
		
		ConfigCriteria config = new ConfigCriteria();
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
	 * Test the override numeric {@link CellDecorator} level at {@link ConfigCriteria}.<br>
	 */
	@Test(dataProvider="numericCellDecoratorProvider")
	public void testOverrideNumericCellDecorator(String decoratorName, short alignment, short verticalAlignment,
			short border, short foregroundColor, String fontName, short fontSize, boolean isFontBold, boolean isFontItalic, byte fontUnderline, boolean isWrapText) throws Exception {
		
		CellDecorator decorator = initializeCellDecorator(decoratorName, alignment, verticalAlignment, border, foregroundColor, fontName,
				fontSize, isFontBold, isFontItalic, fontUnderline, isWrapText);
		
		ConfigCriteria config = new ConfigCriteria();
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
	 * Test the override boolean {@link CellDecorator} level at {@link ConfigCriteria}.<br>
	 */
	@Test(dataProvider="booleanCellDecoratorProvider")
	public void testOverrideBooleanCellDecorator(String decoratorName, short alignment, short verticalAlignment,
			short border, short foregroundColor, String fontName, short fontSize, boolean isFontBold, boolean isFontItalic, byte fontUnderline, boolean isWrapText) throws Exception {
		
		CellDecorator decorator = initializeCellDecorator(decoratorName, alignment, verticalAlignment, border, foregroundColor, fontName,
				fontSize, isFontBold, isFontItalic, fontUnderline, isWrapText);
		
		ConfigCriteria config = new ConfigCriteria();
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
	/* TODO test getMask */
	/* TODO test generateCellStyleKey */
	
	/*internal methods */

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
	
}
