package net.ceos.project.poi.annotated.core;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.testng.annotations.Test;

import net.ceos.project.poi.annotated.annotation.XlsSheet;
import net.ceos.project.poi.annotated.bean.AddressInfo;
import net.ceos.project.poi.annotated.bean.Job;
import net.ceos.project.poi.annotated.bean.MultiTypeObjectListPropHorizontal;
import net.ceos.project.poi.annotated.bean.MultiTypeObjectListPropVertical;
import net.ceos.project.poi.annotated.definition.ExtensionFileType;
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
		Class<MultiTypeObjectListPropHorizontal> oC = MultiTypeObjectListPropHorizontal.class;

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
		MultiTypeObjectListPropHorizontal fastTest1 = buildMultiTypeObjectHoriz(2);

		MultiTypeObjectListPropHorizontal fastTest2 = buildMultiTypeObjectHoriz(35);
		MultiTypeObjectListPropHorizontal fastTest3 = buildMultiTypeObjectHoriz(9);

		List<MultiTypeObjectListPropHorizontal> collectionSimpleObject = new ArrayList<MultiTypeObjectListPropHorizontal>();
		collectionSimpleObject.add(fastTest1);
		collectionSimpleObject.add(fastTest2);
		collectionSimpleObject.add(fastTest3);

		Engine en = new Engine();
		try {
			en.marshalAsCollection(collectionSimpleObject, "file_list_object_multi_horizontal", ExtensionFileType.XLSX);
		} catch (CustomizedRulesException e) {
			assertEquals(e.getCause().getMessage(), "Pim Pam Pum!!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	/**
	 * Test one basic object
	 */
	@Test
	public void testCollectionMultiExcelVert() throws Exception {
		MultiTypeObjectListPropVertical fastTest1 = buildMultiTypeObjectVert();

		MultiTypeObjectListPropVertical fastTest2 = buildMultiTypeObjectVert();

		List<MultiTypeObjectListPropVertical> collectionSimpleObject = new ArrayList<MultiTypeObjectListPropVertical>();
		collectionSimpleObject.add(fastTest1);
		collectionSimpleObject.add(fastTest2);
		// collectionSimpleObject.add(fastTest1);

		Engine en = new Engine();
		en.marshalAsCollection(collectionSimpleObject, "file_list_object_multi_vertical", ExtensionFileType.XLSX);
	}

	/**
	 * Create a multi type object for tests.
	 * 
	 * @return
	 */
	private MultiTypeObjectListPropHorizontal buildMultiTypeObjectHoriz(int xx) {
		MultiTypeObjectListPropHorizontal mto = new MultiTypeObjectListPropHorizontal();
		mto.setDateAttribute(new Date());
		if (xx < 10) {
			mto.setStringAttribute("some string");
		}
		mto.setIntegerAttribute(46);
		mto.setDoubleAttribute(Double.valueOf("25.3") * xx);
		mto.setLongAttribute(Long.valueOf("1234567890"));
		mto.setBooleanAttribute(Boolean.FALSE);

		Job job = new Job();
		job.setJobCode(0005);
		job.setJobFamily("Family Job Name");
		job.setJobName("Job Name");
		mto.setJob(job);

		mto.setIntegerPrimitiveAttribute(121 * xx);
		mto.setDoublePrimitiveAttribute(44.6);
		mto.setLongPrimitiveAttribute(987654321L);
		mto.setBooleanPrimitiveAttribute(true);

		AddressInfo ai = new AddressInfo();
		ai.setAddress("this is the street");
		ai.setNumber(99);
		ai.setCity("this is the city");
		ai.setCityCode(70065);
		ai.setCountry("This is a Country");
		mto.setAddressInfo(ai);
		return mto;
	}

	/**
	 * Create a multi type object for tests.
	 * 
	 * @return
	 */
	private MultiTypeObjectListPropVertical buildMultiTypeObjectVert() {
		MultiTypeObjectListPropVertical mto = new MultiTypeObjectListPropVertical();
		mto.setDateAttribute(new Date());
		mto.setStringAttribute("some string vert");
		mto.setIntegerAttribute(46);
		mto.setDoubleAttribute(Double.valueOf("26.9"));
		mto.setLongAttribute(Long.valueOf("643565"));
		mto.setBooleanAttribute(Boolean.FALSE);

		Job job = new Job();
		job.setJobCode(0005);
		job.setJobFamily("Family Job Name");
		job.setJobName("Job Name");
		mto.setJob(job);

		mto.setIntegerPrimitiveAttribute(121);
		mto.setDoublePrimitiveAttribute(44.6);
		mto.setLongPrimitiveAttribute(987654321L);
		mto.setBooleanPrimitiveAttribute(true);

		AddressInfo ai = new AddressInfo();
		ai.setAddress("this is the street");
		ai.setNumber(99);
		ai.setCity("this is the city");
		ai.setCityCode(70065);
		ai.setCountry("This is a Country");
		mto.setAddressInfo(ai);
		return mto;
	}
}
