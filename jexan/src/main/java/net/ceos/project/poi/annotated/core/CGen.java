package net.ceos.project.poi.annotated.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import net.ceos.project.poi.annotated.annotation.XlsConfiguration;
import net.ceos.project.poi.annotated.annotation.XlsElement;
import net.ceos.project.poi.annotated.annotation.XlsSheet;
import net.ceos.project.poi.annotated.definition.ExceptionMessage;
import net.ceos.project.poi.annotated.exception.ConfigurationException;
import net.ceos.project.poi.annotated.exception.ConverterException;
import net.ceos.project.poi.annotated.exception.ElementException;

public class CGen implements IGeneratorCSV {

	/**
	 * Get the runtime class of the object passed as parameter.
	 * 
	 * @param object
	 *            the object
	 * @return the runtime class
	 * @throws ElementException
	 */
	private Class<?> initializeRuntimeClass(Object object) throws ElementException {
		Class<?> oC = null;
		try {
			/* instance object class */
			oC = object.getClass();
		} catch (Exception e) {
			throw new ElementException(ExceptionMessage.ElementException_NullObject.getMessage(), e);
		}
		return oC;
	}

	/**
	 * Initialize the configuration to apply at the Excel.
	 * 
	 * @param oC
	 *            the {@link Class<?>}
	 * @return
	 * @throws ConfigurationException
	 */
	private void initializeConfigurationData(final CConfigCriteria configCriteria, final Class<?> oC)
			throws ConfigurationException {
		/* Process @XlsConfiguration */
		if (oC.isAnnotationPresent(XlsConfiguration.class)) {
			XlsConfiguration xlsAnnotation = (XlsConfiguration) oC.getAnnotation(XlsConfiguration.class);
			initializeXlsConfiguration(configCriteria, xlsAnnotation);
		} else {
			throw new ConfigurationException(
					ExceptionMessage.ConfigurationException_XlsConfigurationMissing.getMessage());
		}
		/* Process @XlsSheet */
		if (oC.isAnnotationPresent(XlsSheet.class)) {
			XlsSheet xlsAnnotation = (XlsSheet) oC.getAnnotation(XlsSheet.class);
			initializeXlsSheet(configCriteria, xlsAnnotation);
		} else {
			throw new ConfigurationException(ExceptionMessage.ConfigurationException_XlsSheetMissing.getMessage());
		}
	}

	/**
	 * Add the main xls configuration.
	 * 
	 * @param configCriteria
	 *            the {@link XConfigCriteria}
	 * @param annotation
	 *            the {@link XlsConfiguration}
	 * @return
	 */
	private void initializeXlsConfiguration(final CConfigCriteria configCriteria, final XlsConfiguration annotation) {
		configCriteria.setFileName(annotation.nameFile());
	}

	/**
	 * Add the sheet configuration.
	 * 
	 * @param configCriteria
	 *            the {@link XConfigCriteria}
	 * @param annotation
	 *            the {@link XlsSheet}
	 * @return
	 */
	private void initializeXlsSheet(final CConfigCriteria configCriteria, final XlsSheet annotation) {
		configCriteria.setTitleSheet(annotation.title());
		configCriteria.setCascadeLevel(annotation.cascadeLevel());
	}

	/**
	 * Add the content of one line stored at the Map into the file.
	 * 
	 * @param fW
	 *            the file to write
	 * @param values
	 *            the Map with the data to write at the file
	 * @throws IOException
	 */
	private void addLine(final FileWriter fW, final Map<Integer, String> values, final String separator)
			throws IOException {
		Set<Integer> keys = values.keySet();
		boolean isFirst = true;
		for (Integer integer : keys) {

			/* add separator */
			if (!isFirst) {
				fW.append(separator);
			}

			/* append value */
			fW.append(values.get(integer));
			isFirst = false;
		}
		fW.append(Constants.END_OF_LINE);
	}

	/**
	 * Initialize an cell according the type of field and the PropagationType is
	 * PROPAGATION_HORIZONTAL.
	 * 
	 * @param object
	 *            the object
	 * @param field
	 *            the field
	 * @param xlsAnnotation
	 *            the XlsAnnotation
	 * @param values
	 *            the cascade level
	 * @param cL
	 *            the cascade level
	 * @return in case of the object return the number of cell created,
	 *         otherwise 0
	 * @throws ConverterException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws IOException
	 */
	private int initializeField(final CConfigCriteria configCriteria, final Object object, final Field field,
			final XlsElement xlsAnnotation, final int idx) throws IllegalAccessException, NoSuchMethodException,
					InvocationTargetException, ConverterException, InstantiationException, IOException {

		/* set enabled the accessible object */
		field.setAccessible(true);

		int counter = 0;

		Class<?> fT = field.getType();

		boolean isAppliedToBaseObject = toCsv(configCriteria, object, fT, field, xlsAnnotation, idx);

		if (!isAppliedToBaseObject && !fT.isPrimitive()) {
			Object nO = field.get(object);

			/* manage null objects */
			if (nO == null) {
				nO = fT.newInstance();
			}
			Class<?> oC = nO.getClass();

			counter = marshal(configCriteria, nO, oC, null, idx - 1);
		}
		/* set disabled the accessible object */
		field.setAccessible(false);

		return counter;
	}

	/**
	 * Export the object to the CSV file.
	 * 
	 * @param object
	 *            the object
	 * @param fT
	 *            the field type
	 * @param field
	 *            the field
	 * @param element
	 *            the {@link XlsElement} annotation
	 * @param idx
	 *            the index of the field
	 * @return true if the field has been updated, otherwise false
	 * @throws IllegalAccessException
	 * @throws ConverterException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 */
	private boolean toCsv(final CConfigCriteria configCriteria, final Object object, final Class<?> fT,
			final Field field, final XlsElement element, final int idx) throws IllegalAccessException,
					ConverterException, NoSuchMethodException, InvocationTargetException {
		/* flag which define if the cell was updated or not */
		boolean isUpdated;

		/* reading mask */
		String tM = element.transformMask();
		String fM = element.formatMask();

		switch (fT.getName()) {
		case CellHandler.OBJECT_DATE:
			configCriteria.getContent().put(idx, CsvHandler.toDate((Date) field.get(object), fM, tM));
			isUpdated = true;
			break;

		case CellHandler.OBJECT_LOCALDATE:
			configCriteria.getContent().put(idx, CsvHandler.toLocalDate((LocalDate) field.get(object), fM, tM));
			isUpdated = true;
			break;

		case CellHandler.OBJECT_LOCALDATETIME:
			configCriteria.getContent().put(idx, CsvHandler.toLocalDateTime((LocalDateTime) field.get(object), fM, tM));
			isUpdated = true;
			break;

		case CellHandler.OBJECT_STRING:
			configCriteria.getContent().put(idx,
					(String) field.get(object) != null ? (String) field.get(object) : StringUtils.EMPTY);
			isUpdated = true;
			break;

		case CellHandler.OBJECT_SHORT:
			/* falls through */
		case CellHandler.PRIMITIVE_SHORT:
			configCriteria.getContent().put(idx,
					(Short) field.get(object) != null ? ((Short) field.get(object)).toString() : StringUtils.EMPTY);
			isUpdated = true;
			break;

		case CellHandler.OBJECT_INTEGER:
			/* falls through */
		case CellHandler.PRIMITIVE_INTEGER:
			configCriteria.getContent().put(idx,
					(Integer) field.get(object) != null ? ((Integer) field.get(object)).toString() : StringUtils.EMPTY);
			isUpdated = true;
			break;

		case CellHandler.OBJECT_LONG:
			/* falls through */
		case CellHandler.PRIMITIVE_LONG:
			configCriteria.getContent().put(idx,
					(Long) field.get(object) != null ? ((Long) field.get(object)).toString() : StringUtils.EMPTY);
			isUpdated = true;
			break;

		case CellHandler.OBJECT_DOUBLE:
			/* falls through */
		case CellHandler.PRIMITIVE_DOUBLE:
			configCriteria.getContent().put(idx, CsvHandler.toDouble((Double) field.get(object), fM, tM));
			isUpdated = true;
			break;

		case CellHandler.OBJECT_BIGDECIMAL:
			configCriteria.getContent().put(idx, (BigDecimal) field.get(object) != null
					? ((BigDecimal) field.get(object)).toString() : StringUtils.EMPTY);
			isUpdated = true;
			break;

		case CellHandler.OBJECT_FLOAT:
			/* falls through */
		case CellHandler.PRIMITIVE_FLOAT:
			configCriteria.getContent().put(idx,
					(Float) field.get(object) != null ? ((Float) field.get(object)).toString() : StringUtils.EMPTY);
			isUpdated = true;
			break;

		case CellHandler.OBJECT_BOOLEAN:
			/* falls through */
		case CellHandler.PRIMITIVE_BOOLEAN:
			configCriteria.getContent().put(idx,
					(Boolean) field.get(object) != null ? ((Boolean) field.get(object)).toString() : StringUtils.EMPTY);
			isUpdated = true;
			break;

		default:
			isUpdated = false;
			break;
		}

		if (!isUpdated && fT.isEnum()) {
			configCriteria.getContent().put(idx, CsvHandler.toEnum(object, field));
			isUpdated = true;
		}
		return isUpdated;
	}

	/**
	 * Import the CSV file into the object.
	 * 
	 * @param object
	 *            the object
	 * @param fT
	 *            the field type
	 * @param field
	 *            the field
	 * @param xlsAnnotation
	 *            the {@link XlsElement} annotation
	 * @param values
	 *            the array with the content at one line
	 * @param idx
	 *            the index of the field
	 * @return true if the field has been updated, otherwise false
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws ConverterException
	 * @throws ParseException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private boolean toObject(final Object object, final Class<?> fT, final Field field, final XlsElement xlsAnnotation,
			final String[] values, final int idx) throws IllegalAccessException, ConverterException, ParseException {
		/* flag which define if the cell was updated or not */
		boolean isUpdated;
		/* set enabled the accessible object */
		field.setAccessible(true);

		switch (fT.getName()) {
		case CellHandler.OBJECT_DATE:
			CsvHandler.dateReader(object, field, xlsAnnotation, values, idx);
			isUpdated = true;
			break;

		case CellHandler.OBJECT_LOCALDATE:
			CsvHandler.localDateReader(object, field, xlsAnnotation, values, idx);
			isUpdated = true;
			break;

		case CellHandler.OBJECT_LOCALDATETIME:
			CsvHandler.localDateTimeReader(object, field, xlsAnnotation, values, idx);
			isUpdated = true;
			break;

		case CellHandler.OBJECT_STRING:
			CsvHandler.stringReader(object, field, values, idx);
			isUpdated = true;
			break;

		case CellHandler.OBJECT_SHORT:
			/* falls through */
		case CellHandler.PRIMITIVE_SHORT:
			CsvHandler.shortReader(object, field, values, idx);
			isUpdated = true;
			break;

		case CellHandler.OBJECT_INTEGER:
			/* falls through */
		case CellHandler.PRIMITIVE_INTEGER:
			CsvHandler.integerReader(object, field, values, idx);
			isUpdated = true;
			break;

		case CellHandler.OBJECT_LONG:
			/* falls through */
		case CellHandler.PRIMITIVE_LONG:
			CsvHandler.longReader(object, field, values, idx);
			isUpdated = true;
			break;

		case CellHandler.OBJECT_DOUBLE:
			/* falls through */
		case CellHandler.PRIMITIVE_DOUBLE:
			CsvHandler.doubleReader(object, field, xlsAnnotation, values, idx);
			isUpdated = true;
			break;

		case CellHandler.OBJECT_BIGDECIMAL:
			CsvHandler.bigDecimalReader(object, field, values, idx);
			isUpdated = true;
			break;

		case CellHandler.OBJECT_FLOAT:
			/* falls through */
		case CellHandler.PRIMITIVE_FLOAT:
			CsvHandler.floatReader(object, field, values, idx);
			isUpdated = true;
			break;

		case CellHandler.OBJECT_BOOLEAN:
			/* falls through */
		case CellHandler.PRIMITIVE_BOOLEAN:
			CsvHandler.booleanReader(object, field, xlsAnnotation, values, idx);
			isUpdated = true;
			break;

		default:
			isUpdated = false;
			break;
		}

		if (!isUpdated && fT.isEnum()) {
			field.set(object, Enum.valueOf((Class<Enum>) fT, values[idx]));
			isUpdated = true;
		}
		/* set disabled the accessible object */
		field.setAccessible(false);

		return isUpdated;
	}

	/**
	 * Convert the object to file.
	 * 
	 * @param object
	 *            the object
	 * @param oC
	 *            the object class
	 * @param fW
	 *            the {@link FileWriter}
	 * @param idx
	 *            the index of the field
	 * @return the number of fields read
	 * @throws ConverterException
	 * @throws InstantiationException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws IOException
	 */
	private int marshal(final CConfigCriteria configCriteria, final Object o, final Class<?> oC, final FileWriter fW,
			final int idx) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException,
					InstantiationException, ConverterException, IOException {
		/* counter related to the number of fields (if new object) */
		int counter = -1;
		int index = idx;

		/* get declared fields */
		List<Field> fL = Arrays.asList(oC.getDeclaredFields());

		for (Field field : fL) {

			/* Process @XlsElement */
			if (field.isAnnotationPresent(XlsElement.class)) {
				XlsElement xlsAnnotation = (XlsElement) field.getAnnotation(XlsElement.class);

				/*
				 * increment of the counter related to the number of fields (if
				 * new object)
				 */
				counter++;

				/* content header */
				configCriteria.getHeader().put(index + xlsAnnotation.position(), xlsAnnotation.title());

				/* content values treatment */
				index += initializeField(configCriteria, o, field, xlsAnnotation, index + xlsAnnotation.position());
			}
		}

		if (fW != null) {
			/* paint the header line */
			if (!configCriteria.isHeaderPainted()) {
				addLine(fW, configCriteria.getHeader(), configCriteria.getSeparator());
				configCriteria.setIsHeaderPainted(Boolean.TRUE);
			}
			/* paint the content line */
			addLine(fW, configCriteria.getContent(), configCriteria.getSeparator());
		}
		return counter;
	}

	/**
	 * Convert the file into object.
	 * 
	 * @param object
	 *            the object
	 * @param oC
	 *            the object class
	 * @param values
	 *            the array with the content at one line
	 * @param idx
	 *            the index of the field
	 * @return the number of fields read
	 * @throws IllegalAccessException
	 * @throws ConverterException
	 * @throws InstantiationException
	 * @throws ParseException
	 */
	private int unmarshal(final CConfigCriteria configCriteria, final Object object, final Class<?> oC,
			final String[] values, final int idx)
					throws IllegalAccessException, ConverterException, InstantiationException, ParseException {
		/* counter related to the number of fields (if new object) */
		int counter = -1;
		int index = idx;

		/* get declared fields */
		List<Field> fL = Arrays.asList(oC.getDeclaredFields());

		for (Field field : fL) {
			/* process each field from the object */
			Class<?> fT = field.getType();

			/* Process @XlsElement */
			if (field.isAnnotationPresent(XlsElement.class)) {
				XlsElement xlsAnnotation = (XlsElement) field.getAnnotation(XlsElement.class);

				/*
				 * increment of the counter related to the number of fields (if
				 * new object)
				 */
				counter++;

				boolean isAppliedToBaseObject = toObject(object, fT, field, xlsAnnotation, values,
						index + xlsAnnotation.position());

				if (!isAppliedToBaseObject && !fT.isPrimitive()) {

					Object subObjbect = fT.newInstance();
					Class<?> subObjbectClass = subObjbect.getClass();

					int internalCellCounter = unmarshal(configCriteria, subObjbect, subObjbectClass, values,
							index + xlsAnnotation.position() - 1);

					/* set enabled the accessible object */
					field.setAccessible(true);
					
					/* add the sub object to the parent object */
					field.set(object, subObjbect);

					/* set disabled the accessible object */
					field.setAccessible(false);

					/* update the index */
					index += internalCellCounter;
				}
			}
		}
		return counter;
	}

	/* ######################## Marshal methods ########################## */

	/**
	 * Generate the CSV file based at the object passed as parameters and save
	 * it at the path send as parameter.
	 * 
	 * @param object
	 *            the object to apply at the CSV file.
	 * @param pathFile
	 *            the path where is found the file to read and pass the
	 *            information to the object
	 */
	@Override
	public void marshalAndSave(final Object object, final String pathFile) throws Exception {

		/* Initialize a basic ConfigCriteria */
		CConfigCriteria configCriteria = new CConfigCriteria();

		marshalAndSave(configCriteria, object, pathFile);
	}

	/**
	 * Generate the CSV file based at {@link CConfigCriteria} and the object
	 * passed as parameters and save it at the path send as parameter.
	 * 
	 * @param configCriteria
	 *            the {@link CConfigCriteria} to use
	 * @param object
	 *            the object to apply at the CSV file.
	 * @param pathFile
	 *            the path where is found the file to read and pass the
	 *            information to the object
	 */
	@Override
	public void marshalAndSave(final CConfigCriteria configCriteria, final Object object, final String pathFile)
			throws Exception {
		/* initialize the runtime class of the object */
		Class<?> oC = initializeRuntimeClass(object);

		/* initialize configuration data */
		initializeConfigurationData(configCriteria, oC);

		/*
		 * check if the path terminate with the file separator, otherwise will
		 * be added to avoid any problem
		 */
		String internalPathFile = pathFile;
		if (!pathFile.endsWith(File.separator)) {
			internalPathFile = pathFile.concat(File.separator);
		}

		FileWriter fW = new FileWriter(internalPathFile + configCriteria.getCompleteFileName());

		if (object != null) {
			marshal(configCriteria, object, oC, fW, 0);
		}

		/* flush then close the file */
		fW.flush();
		fW.close();

	}

	/**
	 * Generate the CSV file from the collection of objects.
	 * 
	 * @param listObject
	 *            the collection of objects to apply at the CSV file.
	 * @param pathFile
	 *            the path where is found the file to read and pass the
	 *            information to the object
	 * @throws Exception
	 */
	@Override
	public void marshalAsCollectionAndSave(final Collection<?> listObject, final String pathFile) throws Exception {

		/* Initialize a basic ConfigCriteria */
		CConfigCriteria configCriteria = new CConfigCriteria();

		marshalAsCollectionAndSave(configCriteria, listObject, pathFile);
	}

	/**
	 * Generate the CSV file, based at {@link CConfigCriteria}, from the
	 * collection of objects.
	 * 
	 * @param configCriteria
	 *            the {@link CConfigCriteria} to use
	 * @param listObject
	 *            the collection of objects to apply at the CSV file.
	 * @param pathFile
	 *            the path where is found the file to read and pass the
	 *            information to the object
	 * @throws Exception
	 */
	@Override
	public void marshalAsCollectionAndSave(final CConfigCriteria configCriteria, final Collection<?> listObject,
			final String pathFile) throws Exception {

		if (listObject == null || listObject.isEmpty()) {
			return;
		}

		/* initialize the runtime class of the object */
		Class<?> oC = initializeRuntimeClass(listObject.stream().findFirst().get());

		/* initialize configuration data */
		initializeConfigurationData(configCriteria, oC);

		/*
		 * check if the path terminate with the file separator, otherwise will
		 * be added to avoid any problem
		 */
		String internalPathFile = pathFile;
		if (!pathFile.endsWith(File.separator)) {
			internalPathFile = pathFile.concat(File.separator);
		}

		FileWriter fW = new FileWriter(internalPathFile + configCriteria.getCompleteFileName());

		for (Object object : listObject) {
			/* marshal the content list */
			marshal(configCriteria, object, oC, fW, 0);
		}

		/* flush then close the file */
		fW.flush();
		fW.close();

	}

	/* ######################## Unmarshal methods ######################## */
	/**
	 * Generate the object from the path file passed as parameter.
	 * 
	 * @param object
	 *            the object to fill up.
	 * @param pathFile
	 *            the path where is found the file to read and pass the
	 *            information to the object
	 */
	@Override
	public void unmarshalFromPath(final Object object, final String pathFile) throws Exception {
		/* initialize configuration data */
		CConfigCriteria configCriteria = new CConfigCriteria();

		unmarshalFromPath(configCriteria, object, pathFile);
	}

	/**
	 * Generate the object from, based at {@link CConfigCriteria}, the path file
	 * passed as parameter.
	 * 
	 * @param configCriteria
	 *            the {@link CConfigCriteria} to use
	 * @param object
	 *            the object to fill up.
	 * @param pathFile
	 *            the path where is found the file to read and pass the
	 *            information to the object
	 */
	@Override
	public void unmarshalFromPath(final CConfigCriteria configCriteria, final Object object, final String pathFile)
			throws Exception {
		/* initialize the runtime class of the object */
		Class<?> oC = initializeRuntimeClass(object);

		/* initialize configuration data */
		initializeConfigurationData(configCriteria, oC);

		/*
		 * check if the path terminate with the file separator, otherwise will
		 * be added to avoid any problem
		 */
		String internalPathFile = pathFile;
		if (!pathFile.endsWith(File.separator)) {
			internalPathFile = pathFile.concat(File.separator);
		}

		BufferedReader br = new BufferedReader(new FileReader(internalPathFile + configCriteria.getCompleteFileName()));
		String[] values = null;
		String line = StringUtils.EMPTY;
		boolean isHeaderLine = true;
		while ((line = br.readLine()) != null) {
			if (isHeaderLine) {
				isHeaderLine = false;
				continue;
			}
			values = line.split(",");
		}
		unmarshal(configCriteria, object, oC, values, -1);

		/* close the file */
		br.close();
	}

	/**
	 * Charge the collection of object from the path file passed as parameter.
	 * 
	 * @param oC
	 *            the object class will read and inserted into the collection
	 * @param listObject
	 *            the collection to fill up.
	 * @param pathFile
	 *            the path where is found the file to read and pass the
	 *            information to the collection
	 */
	@Override
	public void unmarshalAsCollectionFromPath(final Class<?> oC, final Collection<?> listObject, final String pathFile)
			throws Exception {
		/* initialize configuration data */
		CConfigCriteria configCriteria = new CConfigCriteria();

		unmarshalAsCollectionFromPath(configCriteria, oC, listObject, pathFile);
	}

	/**
	 * Charge the collection of object, based at {@link CConfigCriteria}, from
	 * the path file passed as parameter.
	 * 
	 * @param configCriteria
	 *            the {@link CConfigCriteria} to use
	 * @param oC
	 *            the object class will read and inserted into the collection
	 * @param listObject
	 *            the collection to fill up.
	 * @param pathFile
	 *            the path where is found the file to read and pass the
	 *            information to the collection
	 */
	@Override
	public void unmarshalAsCollectionFromPath(final CConfigCriteria configCriteria, final Class<?> oC,
			final Collection<?> listObject, final String pathFile) throws Exception {

		if (listObject == null || listObject.isEmpty() || oC == null) {
			return;
		}

		/* initialize configuration data */
		initializeConfigurationData(configCriteria, oC);

		/*
		 * check if the path terminate with the file separator, otherwise will
		 * be added to avoid any problem
		 */
		String internalPathFile = pathFile;
		if (!pathFile.endsWith(File.separator)) {
			internalPathFile = pathFile.concat(File.separator);
		}

		BufferedReader br = new BufferedReader(new FileReader(internalPathFile + configCriteria.getCompleteFileName()));

		for (Object object : listObject) {
			String[] values = br.lines().skip(1).map(line -> line.split(configCriteria.getSeparator()))
					.collect(Collectors.toList()).get(0);

			/* unmarshal the content list */
			unmarshal(configCriteria, object, oC, values, -1);
		}

		/* close the file */
		br.close();
	}

}
