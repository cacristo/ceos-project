package net.ceos.project.poi.annotated.core;

import java.util.List;

import org.testng.annotations.Test;

import net.ceos.project.poi.annotated.bean.PerformanceObject;
import net.ceos.project.poi.annotated.bean.PerformanceObjectBuilder;
import net.ceos.project.poi.annotated.definition.ExtensionFileType;

public class PerformanceTest {

	/**
	 * Test one basic object
	 */
	@Test
	public void testMarshalMultiObjectPerf() throws Exception {
		List<PerformanceObject> collection = PerformanceObjectBuilder.buildListOfPerformanceObject(5000);

		IEngine en = new Engine();

		en.marshalAsCollection(collection, "performance_test_list", ExtensionFileType.XLS);

	}
}
