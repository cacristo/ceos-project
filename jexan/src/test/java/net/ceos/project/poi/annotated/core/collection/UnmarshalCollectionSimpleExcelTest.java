package net.ceos.project.poi.annotated.core.collection;

import static org.testng.Assert.assertEquals;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.testng.annotations.Test;

import net.ceos.project.poi.annotated.annotation.XlsElement;
import net.ceos.project.poi.annotated.annotation.XlsNestedHeader;
import net.ceos.project.poi.annotated.annotation.XlsSheet;
import net.ceos.project.poi.annotated.bean.collection.MultiTypeObjectPropH;
import net.ceos.project.poi.annotated.bean.collection.MultiTypeObjectPropV;
import net.ceos.project.poi.annotated.bean.collection.ObjectSimpleObjectWithCollectionH;
import net.ceos.project.poi.annotated.bean.collection.ObjectSimpleObjectWithCollectionV;
import net.ceos.project.poi.annotated.bean.collection.SimpleObjectPropH;
import net.ceos.project.poi.annotated.bean.collection.SimpleObjectPropV;
import net.ceos.project.poi.annotated.bean.collection.SimpleObjectWithCollectionH;
import net.ceos.project.poi.annotated.bean.collection.SimpleObjectWithCollectionV;
import net.ceos.project.poi.annotated.core.Engine;
import net.ceos.project.poi.annotated.core.TestUtils;
import net.ceos.project.poi.annotated.core.XConfigCriteria;
import net.ceos.project.poi.annotated.definition.PropagationType;

/**
 * Unit test for simple App.
 */
public class UnmarshalCollectionSimpleExcelTest {
	


	/**
	 * Test one basic object
	 */
	@Test
	public void testCollectionSimpleExcelH() throws Exception {
		
		XConfigCriteria configCriteria = new XConfigCriteria();
		configCriteria.setFileName("collection_object_simple_h");
		
		Engine en = new Engine();
		Collection<SimpleObjectPropH> collection = (Collection<SimpleObjectPropH>) en.unmarshalToCollection(configCriteria, new SimpleObjectPropH(), TestUtils.WORKING_DIR_UNMARSHALL);
		configCriteria.setFileName("unmarshal_1");
		en.marshalAsCollectionAndSave(configCriteria,collection,TestUtils.WORKING_DIR_UNMARSHALL_M);
	}
	
	/**
	 * Test one basic object
	 */
	@Test
	public void testCollectionSimpleExcelV() throws Exception {
		
		XConfigCriteria configCriteria = new XConfigCriteria();
		configCriteria.setFileName("collection_object_simple_v");
		
		Engine en = new Engine();
		Collection<SimpleObjectPropV> collection = (Collection<SimpleObjectPropV>) en.unmarshalToCollection(configCriteria, new SimpleObjectPropV(), TestUtils.WORKING_DIR_UNMARSHALL);
		configCriteria.setFileName("unmarshal_3_1");
		en.marshalAsCollectionAndSave(configCriteria,collection,TestUtils.WORKING_DIR_UNMARSHALL_M);
	}
	
	/**
	 * Test one basic object
	 */
	@Test
	public void testCollectionMultiExcelH() throws Exception {
		
		XConfigCriteria configCriteria = new XConfigCriteria();
		configCriteria.setFileName("collection_object_multi_h");
		
		Engine en = new Engine();
		Collection<MultiTypeObjectPropH> collection = (Collection<MultiTypeObjectPropH>) en.unmarshalToCollection(configCriteria, new MultiTypeObjectPropH(), TestUtils.WORKING_DIR_UNMARSHALL);
		configCriteria.setFileName("unmarshal_2");
		en.marshalAsCollectionAndSave(configCriteria,collection,TestUtils.WORKING_DIR_UNMARSHALL_M);
	}
	
	/**
	 * Test one basic object
	 */
	@Test
	public void testCollectionMultiExcelV() throws Exception {
		
		XConfigCriteria configCriteria = new XConfigCriteria();
		configCriteria.setFileName("collection_object_multi_v");
		
		Engine en = new Engine();
		Collection<MultiTypeObjectPropV> collection = (Collection<MultiTypeObjectPropV>) en.unmarshalToCollection(configCriteria, new MultiTypeObjectPropV(), TestUtils.WORKING_DIR_UNMARSHALL);
		configCriteria.setFileName("unmarshal_4");
		en.marshalAsCollectionAndSave(configCriteria,collection,TestUtils.WORKING_DIR_UNMARSHALL_M);
	}
	
	/**
	 * Test one basic object
	 */
	@Test
	public void testObjectCollectionMultiExcelP() throws Exception {
		
		XConfigCriteria configCriteria = new XConfigCriteria();
		configCriteria.setFileName("collection_object_simple_with_collec_h");
		
		Engine en = new Engine();
		SimpleObjectWithCollectionH collection =  (SimpleObjectWithCollectionH) en.unmarshalFromPath( new SimpleObjectWithCollectionH(), TestUtils.WORKING_DIR_UNMARSHALL);
		configCriteria.setFileName("unmarshal_5");
		en.marshalAndSave(configCriteria,collection,TestUtils.WORKING_DIR_UNMARSHALL_M);
	}
	
	
}