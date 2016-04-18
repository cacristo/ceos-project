package net.ceos.project.poi.annotated.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

	private static final String SLASH = "/";
	private static final String DOT = ".";
	private static final String COMMA = ",";
	private static final String END_OF_LINE = "\r\n";
	private static final String DEFAULT_DATE = "dd-MMM-yyyy HH:mm:ss";

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
	 * @param f
	 *            the file to write
	 * @param values
	 *            the Map with the data to write at the file
	 * @throws IOException
	 */
	private void addLine(final FileWriter f, final Map<Integer, String> values, final String separator)
			throws IOException {
		Set<Integer> keys = values.keySet();
		boolean isFirst = true;
		for (Integer integer : keys) {

			/* add separator */
			if (!isFirst) {
				f.append(separator);
			}

			/* append value */
			f.append(values.get(integer));
			isFirst = false;
		}
		f.append(END_OF_LINE);
	}

	/**
	 * Initialize an cell according the type of field and the PropagationType is
	 * PROPAGATION_HORIZONTAL.
	 * 
	 * @param o
	 *            the object
	 * @param f
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
	private int initializeField(final CConfigCriteria configCriteria, final Object o, final Field f,
			final XlsElement xlsAnnotation, final int idx) throws IllegalAccessException, NoSuchMethodException,
					InvocationTargetException, ConverterException, InstantiationException, IOException {

		/* make the field accessible to recover the value */
		f.setAccessible(true);

		int counter = 0;

		Class<?> fT = f.getType();

		boolean isAppliedToBaseObject = applyBaseObject(configCriteria, o, fT, f, xlsAnnotation, idx);

		if (!isAppliedToBaseObject && !fT.isPrimitive()) {
			Object nO = f.get(o);

			/* manage null objects */
			if (nO == null) {
				nO = fT.newInstance();
			}
			Class<?> oC = nO.getClass();

			counter = marshal(configCriteria, nO, oC, null, idx - 1);
		}

		return counter;
	}

	/**
	 * Convert the object to file.
	 * 
	 * @param o
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

		for (Field f : fL) {

			/* Process @XlsElement */
			if (f.isAnnotationPresent(XlsElement.class)) {
				XlsElement xlsAnnotation = (XlsElement) f.getAnnotation(XlsElement.class);

				/*
				 * increment of the counter related to the number of fields (if
				 * new object)
				 */
				counter++;

				/* content header */
				configCriteria.getHeader().put(index + xlsAnnotation.position(), xlsAnnotation.title());

				/* content values treatment */
				index += initializeField(configCriteria, o, f, xlsAnnotation, index + xlsAnnotation.position());
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
	 * @param o
	 *            the object
	 * @param oC
	 *            the object class
	 * @param v
	 *            the array with the content at one line
	 * @param idx
	 *            the index of the field
	 * @return the number of fields read
	 * @throws IllegalAccessException
	 * @throws ConverterException
	 * @throws InstantiationException
	 * @throws ParseException
	 */
	private int unmarshal(final CConfigCriteria configCriteria, final Object o, final Class<?> oC, final String[] v,
			final int idx) throws IllegalAccessException, ConverterException, InstantiationException, ParseException {
		/* counter related to the number of fields (if new object) */
		int counter = -1;
		int index = idx;

		/* get declared fields */
		List<Field> fL = Arrays.asList(oC.getDeclaredFields());

		for (Field f : fL) {
			/* process each field from the object */
			Class<?> fT = f.getType();

			/* Process @XlsElement */
			if (f.isAnnotationPresent(XlsElement.class)) {
				XlsElement xlsAnnotation = (XlsElement) f.getAnnotation(XlsElement.class);

				/*
				 * increment of the counter related to the number of fields (if
				 * new object)
				 */
				counter++;

				boolean isAppliedToBaseObject = applyBaseCsvObject(o, fT, f, xlsAnnotation, v,
						index + xlsAnnotation.position());

				if (!isAppliedToBaseObject && !fT.isPrimitive()) {

					Object subObjbect = fT.newInstance();
					Class<?> subObjbectClass = subObjbect.getClass();

					int internalCellCounter = unmarshal(configCriteria, subObjbect, subObjbectClass, v,
							index + xlsAnnotation.position() - 1);

					/* add the sub object to the parent object */
					f.set(o, subObjbect);

					/* update the index */
					index += internalCellCounter;
				}
			}
		}
		return counter;
	}

	/**
	 * Export the object to the CSV file.
	 * 
	 * @param o
	 *            the object
	 * @param fT
	 *            the field type
	 * @param f
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
	private boolean applyBaseObject(final CConfigCriteria configCriteria, final Object o, final Class<?> fT,
			final Field f, final XlsElement element, final int idx) throws IllegalAccessException, ConverterException,
					NoSuchMethodException, InvocationTargetException {
		/* flag which define if the cell was updated or not */
		boolean isUpdated = false;

		/* reading mask */
		String tM = element.transformMask();
		String fM = element.formatMask();

		if (CellHandler.OBJECT_DATE.equals(fT.getName())) {
			configCriteria.getContent().put(idx, CsvUtils.toDate((Date) f.get(o), fM, tM));
			isUpdated = true;

		} else if (CellHandler.OBJECT_STRING.equals(fT.getName())) {
			configCriteria.getContent().put(idx, (String) f.get(o) != null ? (String) f.get(o) : StringUtils.EMPTY);
			isUpdated = true;

		} else if (CellHandler.OBJECT_SHORT.equals(fT.getName())
				|| CellHandler.PRIMITIVE_SHORT.equals(fT.getName())) {
			configCriteria.getContent().put(idx,
					(Short) f.get(o) != null ? ((Short) f.get(o)).toString() : StringUtils.EMPTY);
			isUpdated = true;

		} else if (CellHandler.OBJECT_INTEGER.equals(fT.getName())
				|| CellHandler.PRIMITIVE_INTEGER.equals(fT.getName())) {
			configCriteria.getContent().put(idx,
					(Integer) f.get(o) != null ? ((Integer) f.get(o)).toString() : StringUtils.EMPTY);
			isUpdated = true;

		} else if (CellHandler.OBJECT_LONG.equals(fT.getName()) || CellHandler.PRIMITIVE_LONG.equals(fT.getName())) {
			configCriteria.getContent().put(idx,
					(Long) f.get(o) != null ? ((Long) f.get(o)).toString() : StringUtils.EMPTY);
			isUpdated = true;

		} else if (CellHandler.OBJECT_DOUBLE.equals(fT.getName())
				|| CellHandler.PRIMITIVE_DOUBLE.equals(fT.getName())) {
			configCriteria.getContent().put(idx, CsvUtils.toDouble((Double) f.get(o), fM, tM));
			isUpdated = true;

		} else if (CellHandler.OBJECT_BIGDECIMAL.equals(fT.getName())) {
			configCriteria.getContent().put(idx,
					(BigDecimal) f.get(o) != null ? ((BigDecimal) f.get(o)).toString() : StringUtils.EMPTY);
			isUpdated = true;

		} else if (CellHandler.OBJECT_FLOAT.equals(fT.getName()) || CellHandler.PRIMITIVE_FLOAT.equals(fT.getName())) {
			configCriteria.getContent().put(idx,
					(Float) f.get(o) != null ? ((Float) f.get(o)).toString() : StringUtils.EMPTY);
			isUpdated = true;

		} else if (CellHandler.OBJECT_BOOLEAN.equals(fT.getName())
				|| CellHandler.PRIMITIVE_BOOLEAN.equals(fT.getName())) {
			configCriteria.getContent().put(idx,
					(Boolean) f.get(o) != null ? ((Boolean) f.get(o)).toString() : StringUtils.EMPTY);
			isUpdated = true;

		} else if (fT.isEnum()) {
			configCriteria.getContent().put(idx, CsvUtils.toEnum(o, f));
			isUpdated = true;

		}
		return isUpdated;

	}

	/**
	 * Import the CSV file into the object.
	 * 
	 * @param o
	 *            the object
	 * @param fT
	 *            the field type
	 * @param f
	 *            the field
	 * @param xlsAnnotation
	 *            the {@link XlsElement} annotation
	 * @param v
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
	private boolean applyBaseCsvObject(final Object o, final Class<?> fT, final Field f, final XlsElement xlsAnnotation,
			final String[] v, final int idx) throws IllegalAccessException, ConverterException, ParseException {
		/* flag which define if the cell was updated or not */
		boolean isUpdated = false;

		f.setAccessible(true);

		if (CellHandler.OBJECT_DATE.equals(fT.getName())) {
			manageDate(o, f, xlsAnnotation, v, idx);
			isUpdated = true;

		} else if (CellHandler.OBJECT_STRING.equals(fT.getName())) {
			manageString(o, f, v, idx);
			isUpdated = true;

		} else if (CellHandler.OBJECT_SHORT.equals(fT.getName())
				|| CellHandler.PRIMITIVE_SHORT.equals(fT.getName())) {
			manageShort(o, f, v, idx);
			isUpdated = true;

		} else if (CellHandler.OBJECT_INTEGER.equals(fT.getName())
				|| CellHandler.PRIMITIVE_INTEGER.equals(fT.getName())) {
			manageInteger(o, f, v, idx);
			isUpdated = true;

		} else if (CellHandler.OBJECT_LONG.equals(fT.getName()) || CellHandler.PRIMITIVE_LONG.equals(fT.getName())) {
			manageLong(o, f, v, idx);
			isUpdated = true;

		} else if (CellHandler.OBJECT_DOUBLE.equals(fT.getName())
				|| CellHandler.PRIMITIVE_DOUBLE.equals(fT.getName())) {
			manageDouble(o, f, xlsAnnotation, v, idx);
			isUpdated = true;

		} else if (CellHandler.OBJECT_BIGDECIMAL.equals(fT.getName())) {
			manageBigDecimal(o, f, v, idx);
			isUpdated = true;

		} else if (CellHandler.OBJECT_FLOAT.equals(fT.getName()) || CellHandler.PRIMITIVE_FLOAT.equals(fT.getName())) {
			manageFloat(o, f, v, idx);
			isUpdated = true;

		} else if (CellHandler.OBJECT_BOOLEAN.equals(fT.getName())
				|| CellHandler.PRIMITIVE_BOOLEAN.equals(fT.getName())) {
			manageBoolean(o, f, xlsAnnotation, v, idx);
			isUpdated = true;

		} else if (fT.isEnum()) {
			f.set(o, Enum.valueOf((Class<Enum>) fT, v[idx]));
			isUpdated = true;

		}
		return isUpdated;
	}

	/**
	 * Apply a Date value to the object.
	 * 
	 * @param o
	 *            the object
	 * @param f
	 *            the field
	 * @param xlsAnnotation
	 *            the {@link XlsElement} annotation
	 * @param v
	 *            the array with the content at one line
	 * @param idx
	 *            the index of the field
	 * @throws IllegalAccessException
	 * @throws ConverterException
	 */
	private void manageDate(final Object o, final Field f, final XlsElement xlsAnnotation, final String[] v,
			final int idx) throws IllegalAccessException, ConverterException {
		String date = v[idx];
		if (StringUtils.isNotBlank(date)) {

			String tM = xlsAnnotation.transformMask();
			String fM = xlsAnnotation.formatMask();
			String decorator = StringUtils.isEmpty(tM) ? (StringUtils.isEmpty(fM) ? DEFAULT_DATE : fM) : tM;

			SimpleDateFormat dt = new SimpleDateFormat(decorator);
			try {
				Date dateConverted = dt.parse(date);
				f.set(o, dateConverted);
			} catch (ParseException e) {
				/*
				 * if date decorator do not match with a valid mask launch
				 * exception
				 */
				throw new ConverterException(ExceptionMessage.ConverterException_Date.getMessage(), e);
			}
		}
	}

	/**
	 * Apply a String value to the object.
	 * 
	 * @param o
	 *            the object
	 * @param f
	 *            the field
	 * @param v
	 *            the array with the content at one line
	 * @param idx
	 *            the index of the field
	 * @throws IllegalAccessException
	 */
	private void manageString(final Object o, final Field f, final String[] v, final int idx)
			throws IllegalAccessException {
		f.set(o, v[idx]);
	}

	/**
	 * Apply a Short value to the object.
	 * 
	 * @param o
	 *            the object
	 * @param f
	 *            the field
	 * @param v
	 *            the array with the content at one line
	 * @param idx
	 *            the index of the field
	 * @throws IllegalAccessException
	 */
	private void manageShort(final Object o, final Field f, final String[] v, final int idx)
			throws IllegalAccessException {
		String iValue = v[idx];
		if (StringUtils.isNotBlank(iValue)) {
			f.set(o, Short.valueOf(iValue));
		}
	}

	/**
	 * @param o
	 *            the object
	 * @param f
	 *            the field
	 * @param v
	 *            the array with the content at one line
	 * @param idx
	 *            the index of the field
	 * @throws IllegalAccessException
	 */
	private void manageInteger(final Object o, final Field f, final String[] v, final int idx)
			throws IllegalAccessException {
		String iValue = v[idx];
		if (StringUtils.isNotBlank(iValue)) {
			f.set(o, Integer.valueOf(iValue));
		}
	}

	/**
	 * Apply a Long value to the object.
	 * 
	 * @param o
	 *            the object
	 * @param f
	 *            the field
	 * @param v
	 *            the array with the content at one line
	 * @param idx
	 *            the index of the field
	 * @throws IllegalAccessException
	 */
	private void manageLong(final Object o, final Field f, final String[] v, final int idx)
			throws IllegalAccessException {
		String dValue = v[idx];
		if (StringUtils.isNotBlank(dValue)) {
			f.set(o, Double.valueOf(dValue).longValue());
		}
	}

	/**
	 * Apply a Double value to the object.
	 * 
	 * @param o
	 *            the object
	 * @param f
	 *            the field
	 * @param xlsAnnotation
	 *            the {@link XlsElement} annotation
	 * @param v
	 *            the array with the content at one line
	 * @param idx
	 *            the index of the field
	 * @throws IllegalAccessException
	 */
	private void manageDouble(final Object o, final Field f, final XlsElement xlsAnnotation, final String[] v,
			final int idx) throws IllegalAccessException {
		String dPValue = v[idx];
		if (StringUtils.isNotBlank(dPValue)) {
			if (StringUtils.isNotBlank(xlsAnnotation.transformMask())) {
				f.set(o, Double.parseDouble(dPValue.replace(COMMA, DOT)));
			} else if (StringUtils.isNotBlank(xlsAnnotation.formatMask())) {
				f.set(o, Double.parseDouble(dPValue.replace(COMMA, DOT)));
			} else {
				f.set(o, Double.parseDouble(dPValue));
			}
		}
	}

	/**
	 * Apply a BigDecimal value to the object.
	 * 
	 * @param o
	 *            the object
	 * @param f
	 *            the field
	 * @param v
	 *            the array with the content at one line
	 * @param idx
	 *            the index of the field
	 * @throws IllegalAccessException
	 */
	private void manageBigDecimal(final Object o, final Field f, final String[] v, final int idx)
			throws IllegalAccessException {
		String dBdValue = v[idx];
		if (dBdValue != null) {
			f.set(o, BigDecimal.valueOf(Double.valueOf(dBdValue)));
		}
	}

	/**
	 * Apply a Float value to the object.
	 * 
	 * @param o
	 *            the object
	 * @param f
	 *            the field
	 * @param v
	 *            the array with the content at one line
	 * @param idx
	 *            the index of the field
	 * @throws IllegalAccessException
	 */
	private void manageFloat(final Object o, final Field f, final String[] v, final int idx)
			throws IllegalAccessException {
		String fValue = v[idx];
		if (StringUtils.isNotBlank(fValue)) {
			f.set(o, Float.valueOf(fValue));
		}
	}

	/**
	 * Apply a Boolean value to the object.
	 * 
	 * @param o
	 *            the object
	 * @param f
	 *            the field
	 * @param xlsAnnotation
	 *            the {@link XlsElement} annotation
	 * @param v
	 *            the array with the content at one line
	 * @param idx
	 *            the index of the field
	 * @throws IllegalAccessException
	 */
	private void manageBoolean(final Object o, final Field f, final XlsElement xlsAnnotation, final String[] v,
			final int idx) throws IllegalAccessException {
		String bool = v[idx];

		if (StringUtils.isNotBlank(xlsAnnotation.transformMask())) {
			/* apply format mask defined at transform mask */
			String[] split = xlsAnnotation.transformMask().split(SLASH);
			f.set(o, StringUtils.isNotBlank(bool) ? (split[0].equals(bool) ? true : false) : null);

		} else {
			/* locale mode */
			f.set(o, StringUtils.isNotBlank(bool) ? Boolean.valueOf(bool) : null);
		}
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
