package net.ceos.project.poi.annotated.core;

import java.util.List;

import org.junit.Test;

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
		en.marshalAsCollection(collection, "performance_test_list", ExtensionFileType.XLS);
	}
}
