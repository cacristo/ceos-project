package net.ceos.project.poi.annotated.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import net.ceos.project.poi.annotated.annotation.XlsConfiguration;
import net.ceos.project.poi.annotated.annotation.XlsElement;
import net.ceos.project.poi.annotated.annotation.XlsSheet;
import net.ceos.project.poi.annotated.definition.ExceptionMessage;
import net.ceos.project.poi.annotated.exception.ConfigurationException;
import net.ceos.project.poi.annotated.exception.CustomizedRulesException;
import net.ceos.project.poi.annotated.exception.ElementException;
import net.ceos.project.poi.annotated.exception.WorkbookException;

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
			throw new ElementException(ExceptionMessage.ELEMENT_NULL_OBJECT.getMessage(), e);
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
		if (PredicateFactory.isAnnotationXlsConfigurationPresent.test(oC)) {
			XlsConfiguration xlsAnnotation = (XlsConfiguration) oC.getAnnotation(XlsConfiguration.class);
			initializeXlsConfiguration(configCriteria, xlsAnnotation);
		} else {
			throw new ConfigurationException(ExceptionMessage.CONFIGURATION_XLSCONFIGURATION_MISSING.getMessage());
		}
		/* Process @XlsSheet */
		if (PredicateFactory.isAnnotationXlsSheetPresent.test(oC)) {
			XlsSheet xlsAnnotation = (XlsSheet) oC.getAnnotation(XlsSheet.class);
			initializeXlsSheet(configCriteria, xlsAnnotation);
		} else {
			throw new ConfigurationException(ExceptionMessage.CONFIGURATION_XLSSHEET_MISSING.getMessage());
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
		/* append all values at the Map to the file */
		fW.append(values.values().stream().collect(Collectors.joining(separator)));
		/* add end of line */
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
	 * @throws WorkbookException
	 */
	private int initializeField(final CConfigCriteria configCriteria, final Object object, final Field field,
			final XlsElement xlsAnnotation, final int idx) throws WorkbookException {

		/* set enabled the accessible object */
		field.setAccessible(true);

		int counter = 0;

		Class<?> fT = field.getType();

		boolean isAppliedToBaseObject = toCsv(configCriteria, object, fT, field, xlsAnnotation, idx);

		if (!isAppliedToBaseObject && !fT.isPrimitive()) {
			try {
				Object nO = field.get(object);

				/* manage null objects */
				if (nO == null) {
					nO = fT.newInstance();
				}
				Class<?> oC = nO.getClass();

				counter = marshal(configCriteria, nO, oC, null, idx - 1);
			} catch (InstantiationException | IllegalAccessException e) {
				throw new CustomizedRulesException(ExceptionMessage.ELEMENT_NO_SUCH_METHOD.getMessage(), e);
			}
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
	 * @throws WorkbookException
	 */
	private boolean toCsv(final CConfigCriteria configCriteria, final Object object, final Class<?> fT,
			final Field field, final XlsElement element, final int idx) throws WorkbookException {
		/* flag which define if the cell was updated or not */
		boolean isUpdated;

		/* reading mask */
		String tM = element.transformMask();
		String fM = element.formatMask();

		switch (fT.getName()) {
		case CellHandler.OBJECT_DATE:
			isUpdated = CsvHandler.dateWriter(configCriteria, object, field, idx, tM, fM);
			break;

		case CellHandler.OBJECT_LOCALDATE:
			isUpdated = CsvHandler.localDateWriter(configCriteria, object, field, idx, tM, fM);
			break;

		case CellHandler.OBJECT_LOCALDATETIME:
			isUpdated = CsvHandler.localDateTimeWriter(configCriteria, object, field, idx, tM, fM);
			break;

		case CellHandler.OBJECT_STRING:
			isUpdated = CsvHandler.stringWriter(configCriteria, object, field, idx);
			break;

		case CellHandler.OBJECT_SHORT:
			/* falls through */
		case CellHandler.PRIMITIVE_SHORT:
			isUpdated = CsvHandler.shortWriter(configCriteria, object, field, idx);
			break;

		case CellHandler.OBJECT_INTEGER:
			/* falls through */
		case CellHandler.PRIMITIVE_INTEGER:
			isUpdated = CsvHandler.integerWriter(configCriteria, object, field, idx);
			break;

		case CellHandler.OBJECT_LONG:
			/* falls through */
		case CellHandler.PRIMITIVE_LONG:
			isUpdated = CsvHandler.longWriter(configCriteria, object, field, idx);
			break;

		case CellHandler.OBJECT_DOUBLE:
			/* falls through */
		case CellHandler.PRIMITIVE_DOUBLE:
			isUpdated = CsvHandler.doubleWriter(configCriteria, object, field, idx, tM, fM);
			break;

		case CellHandler.OBJECT_BIGDECIMAL:
			isUpdated = CsvHandler.bigDecimalWriter(configCriteria, object, field, idx);
			break;

		case CellHandler.OBJECT_FLOAT:
			/* falls through */
		case CellHandler.PRIMITIVE_FLOAT:
			isUpdated = CsvHandler.floatWriter(configCriteria, object, field, idx);
			break;

		case CellHandler.OBJECT_BOOLEAN:
			/* falls through */
		case CellHandler.PRIMITIVE_BOOLEAN:
			isUpdated = CsvHandler.booleanWriter(configCriteria, object, field, idx);
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
	 * @throws WorkbookException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private boolean toObject(final Object object, final Class<?> fT, final Field field, final XlsElement xlsAnnotation,
			final String[] values, final int idx) throws WorkbookException {
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
			try {
				field.set(object, Enum.valueOf((Class<Enum>) fT, values[idx]));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new WorkbookException(e.getMessage(), e);
			}
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
	 * @throws WorkbookException
	 */
	private int marshal(final CConfigCriteria configCriteria, final Object object, final Class<?> oC,
			final FileWriter fW, final int idx) throws WorkbookException {
		/* counter related to the number of fields (if new object) */
		int counter = -1;
		int index = idx;

		/* get declared fields */
		List<Field> fL = Arrays.asList(oC.getDeclaredFields());

		for (Field field : fL) {

			/* Process @XlsElement */
			if (PredicateFactory.isFieldAnnotationXlsElementPresent.test(field)) {
				XlsElement xlsAnnotation = (XlsElement) field.getAnnotation(XlsElement.class);

				/*
				 * increment of the counter related to the number of fields (if
				 * new object)
				 */
				counter++;

				/* content header */
				configCriteria.getHeader().put(index + xlsAnnotation.position(), xlsAnnotation.title());

				/* content values treatment */
				index += initializeField(configCriteria, object, field, xlsAnnotation,
						index + xlsAnnotation.position());
			}
		}

		if (fW != null) {
			try {
				/* paint the header line */
				if (!configCriteria.isHeaderPainted()) {
					addLine(fW, configCriteria.getHeader(), configCriteria.getSeparator());
					configCriteria.setIsHeaderPainted(Boolean.TRUE);
				}
				/* paint the content line */
				addLine(fW, configCriteria.getContent(), configCriteria.getSeparator());
			} catch (IOException e) {
				throw new WorkbookException(e.getMessage(), e);
			}
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
	 * @throws WorkbookException
	 */
	private int unmarshal(final CConfigCriteria configCriteria, final Object object, final Class<?> oC,
			final String[] values, final int idx) throws WorkbookException {
		/* counter related to the number of fields (if new object) */
		int counter = -1;
		int index = idx;

		/* get declared fields */
		List<Field> fL = Arrays.asList(oC.getDeclaredFields());

		for (Field field : fL) {
			/* process each field from the object */
			Class<?> fT = field.getType();

			/* Process @XlsElement */
			if (PredicateFactory.isFieldAnnotationXlsElementPresent.test(field)) {
				XlsElement xlsAnnotation = (XlsElement) field.getAnnotation(XlsElement.class);

				/*
				 * increment of the counter related to the number of fields (if
				 * new object)
				 */
				counter++;

				boolean isAppliedToBaseObject = toObject(object, fT, field, xlsAnnotation, values,
						index + xlsAnnotation.position());

				if (!isAppliedToBaseObject && !fT.isPrimitive()) {
					try {
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
					} catch (InstantiationException | IllegalAccessException e) {
						throw new CustomizedRulesException(ExceptionMessage.ELEMENT_NO_SUCH_METHOD.getMessage(), e);
					}
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
	public void marshalAndSave(final Object object, final String pathFile) throws WorkbookException {

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
	 * @throws WorkbookException
	 */
	@Override
	public void marshalAndSave(final CConfigCriteria configCriteria, final Object object, final String pathFile)
			throws WorkbookException {
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

		try {
			FileWriter fW = new FileWriter(internalPathFile + configCriteria.getCompleteFileName());

			if (object != null) {
				marshal(configCriteria, object, oC, fW, 0);
			}

			/* flush then close the file */
			fW.flush();
			fW.close();

		} catch (IOException e) {
			throw new WorkbookException(e.getMessage(), e);
		}
	}

	/**
	 * Generate the CSV file from the collection of objects.
	 * 
	 * @param listObject
	 *            the collection of objects to apply at the CSV file.
	 * @param pathFile
	 *            the path where is found the file to read and pass the
	 *            information to the object
	 * @throws WorkbookException
	 */
	@Override
	public void marshalAsCollectionAndSave(final Collection<?> listObject, final String pathFile)
			throws WorkbookException {

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
	 * @throws WorkbookException
	 */
	@Override
	public void marshalAsCollectionAndSave(final CConfigCriteria configCriteria, final Collection<?> listObject,
			final String pathFile) throws WorkbookException {

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

		try {
			FileWriter fW = new FileWriter(internalPathFile + configCriteria.getCompleteFileName());

			for (Object object : listObject) {
				/* marshal the content list */
				marshal(configCriteria, object, oC, fW, 0);
			}

			/* flush then close the file */
			fW.flush();
			fW.close();

		} catch (IOException e) {
			throw new WorkbookException(e.getMessage(), e);
		}

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
	 * @throws WorkbookException
	 */
	@Override
	public void unmarshalFromPath(final Object object, final String pathFile) throws WorkbookException {
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
			throws WorkbookException {
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

		try {
			BufferedReader br = Files
					.newBufferedReader(Paths.get(internalPathFile + configCriteria.getCompleteFileName()));

			String[] values = br.lines().skip(1).map(line -> line.split(configCriteria.getSeparator()))
					.collect(Collectors.toList()).get(0);

			unmarshal(configCriteria, object, oC, values, -1);

			/* close the file */
			br.close();

		} catch (IOException e) {
			throw new WorkbookException(e.getMessage(), e);
		}
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
	 * @throws WorkbookException
	 */
	@Override
	public void unmarshalAsCollectionFromPath(final Class<?> oC, final Collection<?> listObject, final String pathFile)
			throws WorkbookException {
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
	 * @throws WorkbookException
	 */
	@Override
	public void unmarshalAsCollectionFromPath(final CConfigCriteria configCriteria, final Class<?> oC,
			final Collection<?> listObject, final String pathFile) throws WorkbookException {

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

		try {
			BufferedReader br = Files
					.newBufferedReader(Paths.get(internalPathFile + configCriteria.getCompleteFileName()));

			for (Object object : listObject) {
				String[] values = br.lines().skip(1).map(line -> line.split(configCriteria.getSeparator()))
						.collect(Collectors.toList()).get(0);

				/* unmarshal the content list */
				unmarshal(configCriteria, object, oC, values, -1);
			}
			/* close the file */
			br.close();

		} catch (IOException e) {
			throw new WorkbookException(e.getMessage(), e);
		}
	}

	public static Object toCollection(Object o, Field f) {
		

		try {

			@SuppressWarnings("rawtypes")
			Class[] argTypes = {};

			String method = "get" + f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1);

			Method m = o.getClass().getDeclaredMethod(method, argTypes);

			return  m.invoke(o, (Object[]) null);

			
		} catch (Exception e) {
			
		}
		return null;
	}


}
