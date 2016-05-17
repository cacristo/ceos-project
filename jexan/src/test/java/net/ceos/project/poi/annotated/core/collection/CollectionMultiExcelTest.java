package net.ceos.project.poi.annotated.core.collection;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.testng.annotations.Test;

import net.ceos.project.poi.annotated.annotation.XlsSheet;
import net.ceos.project.poi.annotated.bean.AddressInfo;
import net.ceos.project.poi.annotated.bean.Job;
import net.ceos.project.poi.annotated.bean.collection.MultiTypeObjectPropH;
import net.ceos.project.poi.annotated.bean.collection.MultiTypeObjectPropV;
import net.ceos.project.poi.annotated.bean.collection.ObjectSimpleObjectWithCollectionH;
import net.ceos.project.poi.annotated.bean.collection.ObjectSimpleObjectWithCollectionV;
import net.ceos.project.poi.annotated.bean.collection.SimpleObjectWithCollectionH;
import net.ceos.project.poi.annotated.bean.collection.SimpleObjectWithCollectionV;
import net.ceos.project.poi.annotated.core.Engine;
import net.ceos.project.poi.annotated.core.TestUtils;
import net.ceos.project.poi.annotated.core.XConfigCriteria;
import net.ceos.project.poi.annotated.definition.PropagationType;
import net.ceos.project.poi.annotated.exception.CustomizedRulesException;

/**
 * Unit test for simple App.
 */
public class CollectionMultiExcelTest {

	/**
	 * Test the annotation {@link XlsSheet} XlsSheet
	 */
	@Test
	public void testReadAnnotationXlsSheetHoriz() {
		Class<MultiTypeObjectPropH> oC = MultiTypeObjectPropH.class;

		// Process @XlsSheet
		if (oC.isAnnotationPresent(XlsSheet.class)) {
			XlsSheet xlsAnnotation = (XlsSheet) oC.getAnnotation(XlsSheet.class);

			assertEquals(xlsAnnotation.title(), "List multiple type obj horizont");
			assertEquals(xlsAnnotation.propagation(), PropagationType.PROPAGATION_HORIZONTAL);
		}
	}

	/**
	 * Test the annotation {@link XlsElement} XlsElement
	 */

	/*
	 * public void testReadAnnotationXlsElementHoriz() {
	 * Class<MultiTypeObjectListPropHorizontal> oC =
	 * MultiTypeObjectListPropHorizontal.class;
	 * 
	 * List<Field> fL = Arrays.asList(oC.getDeclaredFields()); for (Field f :
	 * fL) { // Process @XlsElement if (f.isAnnotationPresent(XlsElement.class))
	 * { XlsElement xlsAnnotation = (XlsElement) f
	 * .getAnnotation(XlsElement.class);
	 * 
	 * if (f.getName().equals("dateAttribute")) {
	 * assertEquals(xlsAnnotation.title(), "Date value"); } else if
	 * (f.getName().equals("stringAttribute")) {
	 * assertEquals(xlsAnnotation.title(), "String value"); } else if
	 * (f.getName().equals("integerAttribute")) {
	 * assertEquals(xlsAnnotation.title(), "Integer value"); } } } }
	 */

	/**
	 * Test one basic object
	 */
	@Test
	public void testCollectionMultiExcelHoriz() {
		List<MultiTypeObjectPropH> collectionSimpleObject = new ArrayList<MultiTypeObjectPropH>();
		for (int i = 0; i < 3; i++) {
			MultiTypeObjectPropH fastTest = buildMultiTypeObjectHoriz(i);
			collectionSimpleObject.add(fastTest);
		}
		

		Engine en = new Engine();
		try {
			en.marshalAsCollectionAndSave( collectionSimpleObject, TestUtils.WORKING_DIR_GENERATED_I);
		} catch (CustomizedRulesException e) {
			assertEquals(e.getCause().getMessage(), "Pim Pam Pum!!");
		} catch (Exception e) {
			;
		}
	}
	
	/**
	 * Test one basic object
	 */
	@Test
	public void testSimpleObjWithCollectionMultiPH() throws Exception {
		
		SimpleObjectWithCollectionH simpleObjectWithCollection = new SimpleObjectWithCollectionH();
		simpleObjectWithCollection.setDateAttribute(new Date());
		simpleObjectWithCollection.setIntegerAttribute(10);
		simpleObjectWithCollection.setStringAttribute("Test object simples with collection");
		List<MultiTypeObjectPropH> collectionMultiObject= new ArrayList<MultiTypeObjectPropH>();
		for (int i = 0; i < 3; i++) {
			MultiTypeObjectPropH fastTest = buildMultiTypeObjectHoriz(i);
			collectionMultiObject.add(fastTest);
		}
		simpleObjectWithCollection.setListMultiObjectPropH(collectionMultiObject);
		
		
		
		XConfigCriteria configCriteria = new XConfigCriteria();
		configCriteria.setFileName("collection_object_simple_with_collec_M_h");
		Engine en = new Engine();
		en.marshalAndSave(configCriteria,simpleObjectWithCollection, TestUtils.WORKING_DIR_GENERATED_I);
	}
	
	/**
	 * Test one basic object
	 */
	@Test
	public void testSimpleObjWithCollectionMultiPV() throws Exception {
		
		SimpleObjectWithCollectionV simpleObjectWithCollection = new SimpleObjectWithCollectionV();
		simpleObjectWithCollection.setDateAttribute(new Date());
		simpleObjectWithCollection.setIntegerAttribute(10);
		simpleObjectWithCollection.setStringAttribute("Test object simples with collection");
		List<MultiTypeObjectPropV> collectionMultiObject= new ArrayList<MultiTypeObjectPropV>();
		for (int i = 0; i < 3; i++) {
			MultiTypeObjectPropV fastTest = buildMultiTypeObjectVert(i);
			collectionMultiObject.add(fastTest);
		}
		simpleObjectWithCollection.setListMultiObjectPropV(collectionMultiObject);
		
		
		
		XConfigCriteria configCriteria = new XConfigCriteria();
		configCriteria.setFileName("collection_object_simple_with_collec_M_V");
		Engine en = new Engine();
		en.marshalAndSave(configCriteria,simpleObjectWithCollection, TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Test one basic object
	 */
	@Test
	public void testCollectionMultiExcelVert() throws Exception {
		List<MultiTypeObjectPropV> collectionSimpleObject = new ArrayList<MultiTypeObjectPropV>();
		for (int i = 0; i < 3; i++) {
			MultiTypeObjectPropV fastTest = buildMultiTypeObjectVert(i);
			collectionSimpleObject.add(fastTest);
		}
		
		
		Engine en = new Engine();
		en.marshalAsCollectionAndSave(collectionSimpleObject, TestUtils.WORKING_DIR_GENERATED_I);
	}
	
	/**
	 * Test one basic object
	 */
	@Test
	public void testCollectionObjectWithMultiExcelH() throws Exception {

		List<SimpleObjectWithCollectionH> collection = new ArrayList<SimpleObjectWithCollectionH>();
		for (int i = 0; i < 3; i++) {
			SimpleObjectWithCollectionH simpleObjectWithCollection = new SimpleObjectWithCollectionH();
			simpleObjectWithCollection.setDateAttribute(new Date());
			simpleObjectWithCollection.setIntegerAttribute(10+i);
			simpleObjectWithCollection.setStringAttribute("Test "+i);
			List<MultiTypeObjectPropH> collectionMultiObject= new ArrayList<MultiTypeObjectPropH>();
			for (int j = 0;j < 3; j++) {
				MultiTypeObjectPropH fastTest = buildMultiTypeObjectHoriz(j);
				collectionMultiObject.add(fastTest);
			}
			simpleObjectWithCollection.setListMultiObjectPropH(collectionMultiObject);
			collection.add(simpleObjectWithCollection);
		}
		
		
		
		
		XConfigCriteria configCriteria = new XConfigCriteria();
		configCriteria.setFileName("Col_object_simple_with_collec_M_H");
		Engine en = new Engine();

		en.marshalAsCollectionAndSave(configCriteria,collection, TestUtils.WORKING_DIR_GENERATED_I);
	}
	
	/**
	 * Test one basic object
	 */
	@Test
	public void testObjectObjectWithMultiExcelH() throws Exception {

		ObjectSimpleObjectWithCollectionH object = new ObjectSimpleObjectWithCollectionH();
		
		object.setDateAttribute(new Date());
		object.setIntegerAttribute(10);
		object.setStringAttribute("Test pai");
		
			SimpleObjectWithCollectionH simpleObjectWithCollection = new SimpleObjectWithCollectionH();
			simpleObjectWithCollection.setDateAttribute(new Date());
			simpleObjectWithCollection.setIntegerAttribute(10);
			simpleObjectWithCollection.setStringAttribute("Test filho");
			List<MultiTypeObjectPropH> collectionMultiObject= new ArrayList<MultiTypeObjectPropH>();
			for (int j = 0;j < 3; j++) {
				MultiTypeObjectPropH fastTest = buildMultiTypeObjectHoriz(j);
				collectionMultiObject.add(fastTest);
			}
			simpleObjectWithCollection.setListMultiObjectPropH(collectionMultiObject);
			object.setSimpleObjectCollH(simpleObjectWithCollection);
		
		
		
		
		
		XConfigCriteria configCriteria = new XConfigCriteria();
		configCriteria.setFileName("object_object_simple_with_collec_M_H");
		Engine en = new Engine();

		en.marshalAndSave(configCriteria,object, TestUtils.WORKING_DIR_GENERATED_I);
	}

	
	/**
	 * Test one basic object
	 */
	@Test
	public void testCollectionObjectWithMultiExcelVert() throws Exception {

		List<SimpleObjectWithCollectionV> collection = new ArrayList<SimpleObjectWithCollectionV>();
		for (int i = 0; i < 3; i++) {
			SimpleObjectWithCollectionV simpleObjectWithCollection = new SimpleObjectWithCollectionV();
			simpleObjectWithCollection.setDateAttribute(new Date());
			simpleObjectWithCollection.setIntegerAttribute(10);
			simpleObjectWithCollection.setStringAttribute("Test");
			List<MultiTypeObjectPropV> collectionMultiObject= new ArrayList<MultiTypeObjectPropV>();
			for (int j = 0;j < 3; j++) {
				MultiTypeObjectPropV fastTest = buildMultiTypeObjectVert(j);
				collectionMultiObject.add(fastTest);
			}
			simpleObjectWithCollection.setListMultiObjectPropV(collectionMultiObject);
			collection.add(simpleObjectWithCollection);
		}
		
		
		
		
		XConfigCriteria configCriteria = new XConfigCriteria();
		configCriteria.setFileName("Col_object_simple_with_collec_M_V");
		Engine en = new Engine();

		en.marshalAsCollectionAndSave(configCriteria,collection, TestUtils.WORKING_DIR_GENERATED_I);
	}
	
	/**
	 * Test one basic object
	 */
	@Test
	public void testObjectObjectWithMultiExcelV() throws Exception {

		ObjectSimpleObjectWithCollectionV object = new ObjectSimpleObjectWithCollectionV();
		object.setDateAttribute(new Date());
		object.setIntegerAttribute(1);
		object.setStringAttribute("Test pai");
			SimpleObjectWithCollectionV simpleObjectWithCollection = new SimpleObjectWithCollectionV();
			simpleObjectWithCollection.setDateAttribute(new Date());
			simpleObjectWithCollection.setIntegerAttribute(10);
			simpleObjectWithCollection.setStringAttribute("Test filho");
			List<MultiTypeObjectPropV> collectionMultiObject= new ArrayList<MultiTypeObjectPropV>();
			for (int j = 0;j < 3; j++) {
				MultiTypeObjectPropV fastTest = buildMultiTypeObjectVert(j);
				collectionMultiObject.add(fastTest);
			}
			simpleObjectWithCollection.setListMultiObjectPropV(collectionMultiObject);
			object.setSimpleObjectCollV(simpleObjectWithCollection);
		
		
		
		
		
		XConfigCriteria configCriteria = new XConfigCriteria();
		configCriteria.setFileName("object_object_simple_with_collec_M_V");
		Engine en = new Engine();

		en.marshalAndSave(configCriteria,object, TestUtils.WORKING_DIR_GENERATED_I);
	}

	/**
	 * Create a multi type object for tests.
	 * 
	 * @return
	 */
	private MultiTypeObjectPropH buildMultiTypeObjectHoriz(int i) {
		MultiTypeObjectPropH mto = new MultiTypeObjectPropH();
		mto.setDateAttribute(new Date());
		
		mto.setStringAttribute("some string");
		
		mto.setIntegerAttribute(46+i);
		mto.setDoubleAttribute(Double.valueOf("25.3") * i);
		mto.setLongAttribute(Long.valueOf("1234567890"));
		mto.setBooleanAttribute(Boolean.FALSE);

		Job job = new Job();
		job.setJobCode(0005);
		job.setJobFamily("Family Job Name "+i);
		job.setJobName("Job Name "+i);
		mto.setJob(job);

		mto.setIntegerPrimitiveAttribute(121 * i);
		mto.setDoublePrimitiveAttribute(44.6);
		mto.setLongPrimitiveAttribute(987654321L);
		mto.setBooleanPrimitiveAttribute(true);

		AddressInfo ai = new AddressInfo();
		ai.setAddress("this is the street "+i);
		ai.setNumber(99);
		ai.setCity("this is the city "+i);
		ai.setCityCode(70065);
		ai.setCountry("This is a Country "+i);
		mto.setAddressInfo(ai);
		return mto;
	}

	/**
	 * Create a multi type object for tests.
	 * 
	 * @return
	 */
	private MultiTypeObjectPropV buildMultiTypeObjectVert(int i) {
		MultiTypeObjectPropV mto = new MultiTypeObjectPropV();
		mto.setDateAttribute(new Date());
		mto.setStringAttribute("some string vert "+i);
		mto.setIntegerAttribute(46);
		mto.setDoubleAttribute(Double.valueOf("26.9"));
		mto.setLongAttribute(Long.valueOf("643565"));
		mto.setBooleanAttribute(Boolean.FALSE);

		Job job = new Job();
		job.setJobCode(0005 + i);
		job.setJobFamily("Family Job Name "+i);
		job.setJobName("Job Name "+i);
		mto.setJob(job);

		mto.setIntegerPrimitiveAttribute(121);
		mto.setDoublePrimitiveAttribute(44D);
		mto.setLongPrimitiveAttribute(987654321L);
		mto.setBooleanPrimitiveAttribute(true);

		AddressInfo ai = new AddressInfo();
		ai.setAddress("this is the street "+i);
		ai.setNumber(99);
		ai.setCity("this is the city");
		ai.setCityCode(70065);
		ai.setCountry("This is a Country");
		mto.setAddressInfo(ai);
		return mto;
	}
}
