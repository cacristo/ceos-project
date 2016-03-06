package net.ceos.project.poi.annotated.core;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.testng.annotations.Test;

import net.ceos.project.poi.annotated.bean.ObjectConfigurable;
import net.ceos.project.poi.annotated.bean.ObjectConfigurableBuilder;

/**
 * Test multiple modes how to configure a CellDecorator.
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public class ObjectConfigurableTest {

	/**
	 * Test the override the header {@link CellDecorator} specified by annotation at the
	 * element {@link ObjectConfigurable}
	 */
	@Test
	public void testMarshalOverrideHeader() throws Exception {
		ObjectConfigurable oc = ObjectConfigurableBuilder.buildObjectConfigurable();

		IEngine en = new Engine();

		ConfigCriteria configCriteria = new ConfigCriteria();

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

		ConfigCriteria configCriteria = new ConfigCriteria();

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
}
