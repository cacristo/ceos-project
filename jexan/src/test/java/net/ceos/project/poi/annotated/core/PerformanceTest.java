package net.ceos.project.poi.annotated.core;

import java.util.List;

import org.testng.annotations.Test;

import net.ceos.project.poi.annotated.bean.PerformanceObject;
import net.ceos.project.poi.annotated.bean.PerformanceObjectBuilder;
import net.ceos.project.poi.annotated.definition.ExtensionFileType;

/**
 * Test performance of the library.
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public class PerformanceTest {

	/**
	 * Test the performance of generating one file with 5000 lines.
	 */
	@Test
	public void testMarshalMultiObjectPerf() throws Exception {
		List<PerformanceObject> collection = PerformanceObjectBuilder.buildListOfPerformanceObject(5000);

		IEngine en = new Engine();
		XConfigCriteria configCriteria = new XConfigCriteria();
		configCriteria.setFileName("performance_test_list");
		configCriteria.overrideExtensionType(ExtensionFileType.XLS);
		
		en.marshalAsCollectionAndSave(configCriteria, collection, TestUtils.WORKING_DIR_GENERATED_I);
	}
}
