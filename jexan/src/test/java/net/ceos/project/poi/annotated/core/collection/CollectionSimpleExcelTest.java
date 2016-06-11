/**
 * Copyright 2016 Carlos CRISTO ABREU
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.ceos.project.poi.annotated.core.collection;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import net.ceos.project.poi.annotated.annotation.XlsElement;
import net.ceos.project.poi.annotated.annotation.XlsNestedHeader;
import net.ceos.project.poi.annotated.annotation.XlsSheet;
import net.ceos.project.poi.annotated.bean.collection.SimpleObjectPropV;
import net.ceos.project.poi.annotated.bean.collection.SimpleObjectWithCollectionH;
import net.ceos.project.poi.annotated.bean.collection.SimpleObjectWithCollectionV;
import net.ceos.project.poi.annotated.bean.collection.MultiTypeObjectPropH;
import net.ceos.project.poi.annotated.bean.collection.Object1;
import net.ceos.project.poi.annotated.bean.collection.Object2;
import net.ceos.project.poi.annotated.bean.collection.ObjectSimpleObjectWithCollectionH;
import net.ceos.project.poi.annotated.bean.collection.ObjectSimpleObjectWithCollectionV;
import net.ceos.project.poi.annotated.bean.collection.SimpleObjectPropH;
import net.ceos.project.poi.annotated.core.Engine;
import net.ceos.project.poi.annotated.core.TestUtils;
import net.ceos.project.poi.annotated.core.XConfigCriteria;
import net.ceos.project.poi.annotated.definition.ExtensionFileType;
import net.ceos.project.poi.annotated.definition.PropagationType;

/**
 * Unit test for simple App.
 */
public class CollectionSimpleExcelTest {
	
	/**
	 * Test the annotation {@link XlsSheet} XlsSheet
	 */
	@Test
	public void testReadAnnotationXlsSheetHoriz() {
		Class<SimpleObjectPropH> oC = SimpleObjectPropH.class;

		// Process @XlssssSheet
		if (oC.isAnnotationPresent(XlsSheet.class)) {
			XlsSheet xlsAnnotation = (XlsSheet) oC
					.getAnnotation(XlsSheet.class);

			assertEquals(xlsAnnotation.title(), "List Simple object horizontal");
			assertEquals(xlsAnnotation.propagation(),
					PropagationType.PROPAGATION_HORIZONTAL);
		}
	}

	/**
	 * Test the annotation {@link XlsElement} XlsElement
	 */
	@Test
	public void testReadAnnotationXlsElementHoriz() {
		Class<SimpleObjectPropH> oC = SimpleObjectPropH.class;

		List<Field> fL = Arrays.asList(oC.getDeclaredFields());
		for (Field f : fL) {
			// Process @XlsElement
			if (f.isAnnotationPresent(XlsElement.class)) {
				XlsElement xlsAnnotation = (XlsElement) f
						.getAnnotation(XlsElement.class);

				if (f.getName().equals("dateAttribute")) {
					assertEquals(xlsAnnotation.title(), "Date value");
				} else if (f.getName().equals("stringAttribute")) {
					assertEquals(xlsAnnotation.title(), "String value");
				} else if (f.getName().equals("integerAttribute")) {
					assertEquals(xlsAnnotation.title(), "Integer value");
				}
			}
		}
	}

	/**
	 * Test the annotation {@link XlsNestedHeader} XlsMasterHeader
	 */
	@Test
	public void testReadAnnotationXlsMasterHeaderHoriz() {
		Class<SimpleObjectPropH> oC = SimpleObjectPropH.class;

		List<Field> fL = Arrays.asList(oC.getDeclaredFields());
		for (Field f : fL) {
			// Process @XlsMasterHeader
			if (f.isAnnotationPresent(XlsNestedHeader.class)) {
				XlsNestedHeader xlsAnnotation = (XlsNestedHeader) f
						.getAnnotation(XlsNestedHeader.class);

				if (f.getName().equals("dateAttribute")) {
					assertEquals(xlsAnnotation.title(), "Main info");
					assertEquals(xlsAnnotation.startX(), 1);
					assertEquals(xlsAnnotation.endX(), 3);
				}
			}
		}
	}

	/**
	 * Test one basic object
	 */
	@Test
	public void testCollectionSimpleExcelHorizontal() throws Exception {
		
		List<SimpleObjectPropH> collectionSimpleObject= new ArrayList<SimpleObjectPropH>();
		for (int i = 0; i < 6; i++) {
			SimpleObjectPropH simpleObject = new SimpleObjectPropH();
			simpleObject.setDateAttribute(new Date());
			simpleObject.setStringAttribute("some string "+i);
			simpleObject.setIntegerAttribute(i);
			collectionSimpleObject.add(simpleObject);
		}
		
		Engine en = new Engine();
		en.marshalAsCollectionAndSave(collectionSimpleObject, TestUtils.WORKING_DIR_GENERATED_I);
	}
	
	
	/**
	 * Test the annotation {@link XlsSheet} XlsSheet
	 */
	@Test
	public void testReadAnnotationXlsSheetVert() {
		Class<SimpleObjectPropV> oC = SimpleObjectPropV.class;

		// Process @XlsSheet
		if (oC.isAnnotationPresent(XlsSheet.class)) {
			XlsSheet xlsAnnotation = (XlsSheet) oC
					.getAnnotation(XlsSheet.class);

			assertEquals(xlsAnnotation.title(), "collection object simple vertical");
			assertEquals(xlsAnnotation.propagation(),
					PropagationType.PROPAGATION_VERTICAL);
		}
	}

	/**
	 * Test the annotation {@link XlsElement} XlsElement
	 */
	@Test
	public void testReadAnnotationXlsElementVert() {
		Class<SimpleObjectPropV> oC = SimpleObjectPropV.class;

		List<Field> fL = Arrays.asList(oC.getDeclaredFields());
		for (Field f : fL) {
			// Process @XlsElement
			if (f.isAnnotationPresent(XlsElement.class)) {
				XlsElement xlsAnnotation = (XlsElement) f
						.getAnnotation(XlsElement.class);

				if (f.getName().equals("dateAttribute")) {
					assertEquals(xlsAnnotation.title(), "Date value");
				} else if (f.getName().equals("stringAttribute")) {
					assertEquals(xlsAnnotation.title(), "String value");
				} else if (f.getName().equals("integerAttribute")) {
					assertEquals(xlsAnnotation.title(), "Integer value");
				}
			}
		}
	}

	/**
	 * Test the annotation {@link XlsNestedHeader} XlsMasterHeader
	 */
	@Test
	public void testReadAnnotationXlsMasterHeaderVert() {
		Class<SimpleObjectPropV> oC = SimpleObjectPropV.class;

		List<Field> fL = Arrays.asList(oC.getDeclaredFields());
		for (Field f : fL) {
			// Process @XlsMasterHeader
			if (f.isAnnotationPresent(XlsNestedHeader.class)) {
				XlsNestedHeader xlsAnnotation = (XlsNestedHeader) f
						.getAnnotation(XlsNestedHeader.class);

				if (f.getName().equals("dateAttribute")) {
					assertEquals(xlsAnnotation.title(), "Main info");
					assertEquals(xlsAnnotation.startY(), 1);
					assertEquals(xlsAnnotation.endY(), 3);
				}
			}
		}
	}

	
	/**
	 * Test one basic object
	 */
	@Test
	public void testCollectionSimpleExcelVertical() throws Exception {
		
		List<SimpleObjectPropV> collectionSimpleObject= new ArrayList<SimpleObjectPropV>();
		for (int i = 0; i < 6; i++) {
			SimpleObjectPropV simpleObject = new SimpleObjectPropV();
			simpleObject.setDateAttribute(new Date());
			simpleObject.setStringAttribute("some string "+i);
			simpleObject.setIntegerAttribute(i);
			collectionSimpleObject.add(simpleObject);
		}
		
		
		
		Engine en = new Engine();
		en.marshalAsCollectionAndSave(collectionSimpleObject, TestUtils.WORKING_DIR_GENERATED_I);
	}
	
	
	/**
	 * Test one basic object
	 */
	@Test
	public void testSimpleObjWithCollectionSimplePH() throws Exception {
		
		SimpleObjectWithCollectionH simpleObjectWithCollection = new SimpleObjectWithCollectionH();
		simpleObjectWithCollection.setDateAttribute(new Date());
		simpleObjectWithCollection.setIntegerAttribute(10);
		simpleObjectWithCollection.setStringAttribute("Test");
		Set<SimpleObjectPropH> collectionSimpleObject= new HashSet<SimpleObjectPropH>();
		for (int i = 0; i < 6; i++) {
			SimpleObjectPropH simpleObject = new SimpleObjectPropH();
			simpleObject.setDateAttribute(new Date());
			simpleObject.setStringAttribute("some string "+i);
			simpleObject.setIntegerAttribute(i);
			collectionSimpleObject.add(simpleObject);
		}
	//	simpleObjectWithCollection.setSetSimpleObjectPropH(collectionSimpleObject);
		
		
		XConfigCriteria configCriteria = new XConfigCriteria();
		configCriteria.setFileName("collection_object_simple_with_collec_S_H_PS");
		
		Engine en = new Engine();
		en.marshalAndSave(configCriteria,simpleObjectWithCollection, TestUtils.WORKING_DIR_GENERATED_I);
	}
	
	
	
	/**
	 * Test one basic object
	 */
	@Test
	public void testSimpleObjWithCollectionSimplePV() throws Exception {
		
		SimpleObjectWithCollectionV simpleObjectWithCollection = new SimpleObjectWithCollectionV();
		simpleObjectWithCollection.setDateAttribute(new Date());
		simpleObjectWithCollection.setIntegerAttribute(10);
		simpleObjectWithCollection.setStringAttribute("Test object simples with collection v");
		Set<SimpleObjectPropV> collectionSimpleObject= new HashSet<SimpleObjectPropV>();
		for (int i = 0; i < 6; i++) {
			SimpleObjectPropV simpleObject = new SimpleObjectPropV();
			simpleObject.setDateAttribute(new Date());
			simpleObject.setStringAttribute("some string "+i);
			simpleObject.setIntegerAttribute(i);
			collectionSimpleObject.add(simpleObject);
		}
		simpleObjectWithCollection.setSetSimpleObjectPropV(collectionSimpleObject);
		
		
		XConfigCriteria configCriteria = new XConfigCriteria();
		configCriteria.setFileName("collection_object_simple_with_collec_S_V");
		
		Engine en = new Engine();
		en.marshalAndSave(configCriteria,simpleObjectWithCollection, TestUtils.WORKING_DIR_GENERATED_I);
	}
	
	/**
	 * Test one basic object
	 */
	@Test
	public void testCollectionSimpleObjWithCollectionSimplePH() throws Exception {
		
		List<SimpleObjectWithCollectionH> collection = new ArrayList<SimpleObjectWithCollectionH>();
		for(int j=0;j<4;j++){
			SimpleObjectWithCollectionH simpleObjectWithCollection = new SimpleObjectWithCollectionH();
			simpleObjectWithCollection.setDateAttribute(new Date());
			simpleObjectWithCollection.setIntegerAttribute(10);
			simpleObjectWithCollection.setStringAttribute("L1 " + j);
			Set<SimpleObjectPropH> collectionSimpleObject= new HashSet<SimpleObjectPropH>();
			for (int i = 0; i < 6; i++) {
				SimpleObjectPropH simpleObject = new SimpleObjectPropH();
				simpleObject.setDateAttribute(new Date());
				simpleObject.setStringAttribute("some string l2 "+i);
				simpleObject.setIntegerAttribute(i);
				collectionSimpleObject.add(simpleObject);
			}
		//	simpleObjectWithCollection.setSetSimpleObjectPropH(collectionSimpleObject);
			collection.add(simpleObjectWithCollection);
		}
		
		
		
		XConfigCriteria configCriteria = new XConfigCriteria();
		configCriteria.setFileName("col_object_simple_with_collec_S_H_1");
		
		Engine en = new Engine();
		en.marshalAsCollectionAndSave(configCriteria,collection, TestUtils.WORKING_DIR_GENERATED_I);
	}
	
	/**
	 * Test one basic object
	 */
	@Test
	public void testCollectionSimpleObjWithCollectionSimplePV() throws Exception {
		List<SimpleObjectWithCollectionV> collection = new ArrayList<SimpleObjectWithCollectionV>();
		for(int j=0;j<4;j++){
			SimpleObjectWithCollectionV simpleObjectWithCollection = new SimpleObjectWithCollectionV();
			simpleObjectWithCollection.setDateAttribute(new Date());
			simpleObjectWithCollection.setIntegerAttribute(10);
			simpleObjectWithCollection.setStringAttribute("Test "+j);
			Set<SimpleObjectPropV> collectionSimpleObject= new HashSet<SimpleObjectPropV>();
			for (int i = 0; i < 4; i++) {
				SimpleObjectPropV simpleObject = new SimpleObjectPropV();
				simpleObject.setDateAttribute(new Date());
				simpleObject.setStringAttribute("some string "+i);
				simpleObject.setIntegerAttribute(i);
				collectionSimpleObject.add(simpleObject);
			}
			simpleObjectWithCollection.setSetSimpleObjectPropV(collectionSimpleObject);
			collection.add(simpleObjectWithCollection);
		}
		
		
		
		XConfigCriteria configCriteria = new XConfigCriteria();
		configCriteria.setFileName("col_object_simple_with_collec_S_V");
		
		Engine en = new Engine();
		en.marshalAsCollectionAndSave(configCriteria,collection, TestUtils.WORKING_DIR_GENERATED_I);
		
	}
	

	/**
	 * Test one basic object
	 */
	@Test
	public void testObjectSimpleObjWithCollectionSimplePH() throws Exception {
		
		ObjectSimpleObjectWithCollectionH object = new ObjectSimpleObjectWithCollectionH();
		
		object.setDateAttribute(new Date());
		object.setIntegerAttribute(1);
		object.setStringAttribute("Objecto principal");
			SimpleObjectWithCollectionH simpleObjectWithCollection = new SimpleObjectWithCollectionH();
			simpleObjectWithCollection.setDateAttribute(new Date());
			simpleObjectWithCollection.setIntegerAttribute(10);
			simpleObjectWithCollection.setStringAttribute("objecto filho");
			Set<SimpleObjectPropH> collectionSimpleObject= new HashSet<SimpleObjectPropH>();
			for (int i = 0; i < 4; i++) {
				SimpleObjectPropH simpleObject = new SimpleObjectPropH();
				simpleObject.setDateAttribute(new Date());
				simpleObject.setStringAttribute("some string "+i);
				simpleObject.setIntegerAttribute(i);
				collectionSimpleObject.add(simpleObject);
			}
		//	simpleObjectWithCollection.setSetSimpleObjectPropH(collectionSimpleObject);
			object.setSimpleObjectCollH(simpleObjectWithCollection);
		
		
		
		XConfigCriteria configCriteria = new XConfigCriteria();
		configCriteria.setFileName("object_object_simple_with_collec_S_H");
		
		Engine en = new Engine();
		en.marshalAndSave(configCriteria,object, TestUtils.WORKING_DIR_GENERATED_I);
		
	}
	
	/**
	 * Test one basic object
	 */
	@Test
	public void testObjectSimpleObjWithCollectionSimplePV() throws Exception {
		ObjectSimpleObjectWithCollectionV object = new ObjectSimpleObjectWithCollectionV();
		
		object.setDateAttribute(new Date());
		object.setIntegerAttribute(1);
		object.setStringAttribute("Test pai");
			SimpleObjectWithCollectionV simpleObjectWithCollection = new SimpleObjectWithCollectionV();
			simpleObjectWithCollection.setDateAttribute(new Date());
			simpleObjectWithCollection.setIntegerAttribute(10);
			simpleObjectWithCollection.setStringAttribute("Test filho");
			Set<SimpleObjectPropV> collectionSimpleObject= new HashSet<SimpleObjectPropV>();
			for (int i = 0; i < 4; i++) {
				SimpleObjectPropV simpleObject = new SimpleObjectPropV();
				simpleObject.setDateAttribute(new Date());
				simpleObject.setStringAttribute("some string "+i);
				simpleObject.setIntegerAttribute(i);
				collectionSimpleObject.add(simpleObject);
			}
			simpleObjectWithCollection.setSetSimpleObjectPropV(collectionSimpleObject);
			object.setSimpleObjectCollV(simpleObjectWithCollection);
		
		
		
		
		XConfigCriteria configCriteria = new XConfigCriteria();
		configCriteria.setFileName("object_object_simple_with_collec_S_V");
		
		Engine en = new Engine();
		en.marshalAndSave(configCriteria,object, TestUtils.WORKING_DIR_GENERATED_I);
		
	}
	
	/**
	 * Test one basic object
	 */
	@Test
	public void testCascadePH() throws Exception {
		
		Object1 object = new Object1();
		object.setString("object 1");
		List<Object2> listObject2 = new ArrayList<Object2>();
			for (int i = 0; i < 5; i++) {
				Object2 simpleObject = new Object2();
				
			simpleObject.setString("object 2 "+i);
				List<SimpleObjectPropH> listObject3 = new ArrayList<SimpleObjectPropH>();
				for (int j = 0; j < 5; j++) {
					SimpleObjectPropH simpleObject3 = new SimpleObjectPropH();
					simpleObject3.setDateAttribute(new Date());
					simpleObject3.setIntegerAttribute(12);
					simpleObject3.setStringAttribute("Object "+i+" " +j);
					listObject3.add(simpleObject3);
					simpleObject.setSimpleObjectPropH(listObject3);
				}
				listObject2.add(simpleObject);
			}
			object.setListObject2(listObject2);
		
		
		
		XConfigCriteria configCriteria = new XConfigCriteria();
		configCriteria.setFileName("cascade_h");
		
		Engine en = new Engine();
		en.marshalAndSave(configCriteria,object, TestUtils.WORKING_DIR_GENERATED_I);
		
	}
	
	
}
