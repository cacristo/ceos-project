package net.ceos.project.poi.annotated.core;

import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Assert;
import org.junit.Test;

import net.ceos.project.poi.annotated.bean.MultiTypeObject;
import net.ceos.project.poi.annotated.bean.MultiTypeObjectBuilder;
import net.ceos.project.poi.annotated.definition.ExtensionFileType;
import net.ceos.project.poi.annotated.definition.PropagationType;

/**
 * Test the available methods at interface {@link IEngine}
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public class MarshalUnmarshalTest {

	/**
	 * Test the method 'marshalToSheet' to generate the Excel from the object
	 * and return the {@link Sheet} generated.<br>
	 */
	@Test
	public void testMarshalToSheet() throws Exception {
		MultiTypeObject mto = MultiTypeObjectBuilder.buildMultiTypeObject();

		IEngine en = new Engine();
		Sheet s = en.marshalToSheet(mto);

		Assert.assertNotNull(s);
	}

	/**
	 * Test the method 'marshalToSheet', using {@link XConfigCriteria}, to
	 * generate the Excel from the object and return the {@link Sheet}
	 * generated.<br>
	 */
	@Test
	public void testMarshalToSheetWithConfigCriteria() throws Exception {
		MultiTypeObject mto = MultiTypeObjectBuilder.buildMultiTypeObject();

		XConfigCriteria configuration = new XConfigCriteria();
		configuration.overridePropagationType(PropagationType.PROPAGATION_VERTICAL);

		IEngine en = new Engine();
		Sheet s = en.marshalToSheet(configuration, mto);

		Assert.assertNotNull(s);
	}

	/**
	 * Test the method 'marshalToWorkbook' to generate the Excel from the object
	 * and return the {@link Workbook} generated.<br>
	 */
	@Test
	public void testMarshalToWorkbook() throws Exception {
		MultiTypeObject mto = MultiTypeObjectBuilder.buildMultiTypeObject();

		IEngine en = new Engine();
		Workbook w = en.marshalToWorkbook(mto);

		Assert.assertNotNull(w);
	}

	/**
	 * Test the method 'marshalToWorkbook', using {@link XConfigCriteria}, to
	 * generate the Excel from the object and return the {@link Workbook}
	 * generated.<br>
	 */
	@Test
	public void testMarshalToWorkbookWithConfigCriteria() throws Exception {
		MultiTypeObject mto = MultiTypeObjectBuilder.buildMultiTypeObject();

		XConfigCriteria configuration = new XConfigCriteria();
		configuration.overridePropagationType(PropagationType.PROPAGATION_VERTICAL);

		IEngine en = new Engine();
		Workbook w = en.marshalToWorkbook(configuration, mto);

		Assert.assertNotNull(w);
	}

	/**
	 * Test the method 'marshalAndSave' to generate the Excel from the object
	 * and save it at the path file indicated.
	 */
	@Test
	public void testMarshalAndSave() throws Exception {
		MultiTypeObject mto = MultiTypeObjectBuilder.buildMultiTypeObject();
		String outputPath = TestUtils.WORKING_DIR_GENERATED_I + "\\";

		IEngine en = new Engine();
		en.marshalAndSave(mto, outputPath);
	}

	/**
	 * Test the method 'marshalAndSave', using {@link XConfigCriteria}, to
	 * generate the Excel from the object and save it at the path file
	 * indicated.
	 */
	@Test
	public void testMarshalAndSaveWithConfigCriteria() throws Exception {
		MultiTypeObject mto = MultiTypeObjectBuilder.buildMultiTypeObject();
		String outputPath = TestUtils.WORKING_DIR_GENERATED_II;

		XConfigCriteria configuration = new XConfigCriteria();
		configuration.overrideExtensionType(ExtensionFileType.XLS);

		IEngine en = new Engine();
		en.marshalAndSave(mto, outputPath);
	}

	/**
	 * Test the method 'marshalToByte' to generate the Excel from the object and
	 * return the byte[] generated.<br>
	 */
	@Test
	public void testMarshalToByte() throws Exception {
		MultiTypeObject mto = MultiTypeObjectBuilder.buildMultiTypeObject();

		IEngine en = new Engine();
		byte[] generatedBytes = en.marshalToByte(mto);

		Assert.assertNotNull(generatedBytes);
	}

	/**
	 * Test the method 'marshalToByte' to generate the Excel from the object and
	 * return the byte[] generated.<br>
	 */
	@Test
	public void testMarshalAsCollection() throws Exception {
		List<MultiTypeObject> collection = MultiTypeObjectBuilder.buildListOfMultiTypeObject(500);

		IEngine en = new Engine();
		en.marshalAsCollection(collection, "CollectionMultiType", ExtensionFileType.XLSX);
	}

	/**
	 * DataProvider : Test the method 'marshalToWorkbook' to generate the Excel
	 * from the object and return the Workbook generated.<br>
	 * 
	 * Test the method 'unmarshalFromWorkbook' reading the Excel from the
	 * Workbook passed as parameter and bring the data to the object.
	 */
	@Test
	public void testUnmarshalFromWorkbook() throws Exception {

		MultiTypeObject mto = MultiTypeObjectBuilder.buildMultiTypeObject();
		IEngine en = new Engine();
		Workbook workbook = en.marshalToWorkbook(mto);
		
		MultiTypeObject charger = new MultiTypeObject();
		IEngine en1 = new Engine();
		en1.unmarshalFromWorkbook(charger, workbook);

		MultiTypeObjectBuilder.validateMultiTypeObject(charger);
	}

	/**
	 * DataProvider : Test the method 'marshalToByte' to generate the Excel from
	 * the object and return the byte[] generated.<br>
	 * 
	 * Test the method 'unmarshalFromByte' reading the Excel from the byte[]
	 * passed as parameter and bring the data to the object.
	 */
	@Test
	public void testlUnmarshalFromByte() throws Exception {

		MultiTypeObject mto = MultiTypeObjectBuilder.buildMultiTypeObject();
		IEngine en1 = new Engine();
		byte[] generatedBytes = en1.marshalToByte(mto);
		
		MultiTypeObject charger = new MultiTypeObject();
		IEngine en = new Engine();
		en.unmarshalFromByte(charger, generatedBytes);

		MultiTypeObjectBuilder.validateMultiTypeObject(charger);
	}

	/**
	 * Test the method 'unmarshalFromPath' reading the Excel from a specific
	 * path file indicated and bring the data to the object.
	 */
	@Test
	public void testUnmarshalFromPath() throws Exception {
		MultiTypeObject charger = new MultiTypeObject();

		IEngine en = new Engine();
		en.unmarshalFromPath(charger, TestUtils.WORKING_DIR_GENERATED_I);

		MultiTypeObjectBuilder.validateMultiTypeObject(charger);
	}

	/**
	 * Test the method 'unmarshalFromPath' reading the Excel from a specific
	 * path file indicated and bring the data to the object.
	 */
	@Test
	public void testUnmarshalFromCollection() throws Exception {
		/* TODO create this test */
	}

}
