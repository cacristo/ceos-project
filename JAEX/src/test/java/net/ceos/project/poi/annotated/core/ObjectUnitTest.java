package net.ceos.project.poi.annotated.core;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import net.ceos.project.poi.annotated.bean.ObjectUnit;
import net.ceos.project.poi.annotated.definition.ExtensionFileType;

public class ObjectUnitTest {

	/**
	 * Test one basic object
	 */
	@Test
	public void testMarshaObjectFormula() throws Exception {
		ObjectUnit ou = new ObjectUnit("PenDrive 5GB", 4.87, 6.99, 21, 185);

		IEngine en = new Engine();

		en.marshalAndSave(ou, TestUtils.WORKING_DIR_GENERATED_II);

	}

	/**
	 * Test one basic object
	 */
	@Test
	public void testMarshaListObjectFormula() throws Exception {
		List<ObjectUnit> collection = new ArrayList<ObjectUnit>();
		collection.add(new ObjectUnit("PenDrive 5GB", 4.87, 6.99, 21, 185));
		collection.add(new ObjectUnit("PenDrive 8GB", 5.11, 8.99, 21, 200));
		collection.add(new ObjectUnit("PenDrive 12GB", 5.87, 11.99, 21, 500));
		collection.add(new ObjectUnit("PenDrive 16GB", 4.87, 6.99, 21, 250));
		collection.add(new ObjectUnit("Drive 500GB", 84.87, 111.99, 21, 100));
		collection.add(new ObjectUnit("PenDrive 1TB", 164.22, 199.99, 21, 35));

		IEngine en = new Engine();
		en.marshalAsCollection(collection, "CollectionObjectUnit", ExtensionFileType.XLS);

	}
}
