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
	private static final char END_OF_LINE = '\n';
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
	private void addLine(final FileWriter f, final Map<Integer, String> values, final String separator) throws IOException {
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
	private int initializeField(final CConfigCriteria configCriteria, final Object o, final Field f, final XlsElement xlsAnnotation, final int idx)
			throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, ConverterException,
			InstantiationException, IOException {

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
	private int marshal(final CConfigCriteria configCriteria, final Object o, final Class<?> oC, final FileWriter fW, final int idx)
			throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException,
			ConverterException, IOException {
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
	private int unmarshal(final CConfigCriteria configCriteria, final Object o, final Class<?> oC, final String[] v, final int idx)
			throws IllegalAccessException, ConverterException, InstantiationException, ParseException {
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
	private boolean applyBaseObject(final CConfigCriteria configCriteria, final Object o, final Class<?> fT, final Field f, final XlsElement element,
			final int idx) throws IllegalAccessException, ConverterException, NoSuchMethodException,
					InvocationTargetException {
		/* flag which define if the cell was updated or not */
		boolean isUpdated;

		/* reading mask */
		String tM = element.transformMask();
		String fM = element.formatMask();

		switch (fT.getName()) {
		case CellHandler.OBJECT_DATE:
			configCriteria.getContent().put(idx, CsvUtils.toDate((Date) f.get(o), fM, tM));
			isUpdated = true;
			break;

		case CellHandler.OBJECT_STRING:
			configCriteria.getContent().put(idx, (String) f.get(o) != null ? (String) f.get(o) : StringUtils.EMPTY);
			isUpdated = true;
			break;

		case CellHandler.OBJECT_INTEGER:
			/* falls through */
		case CellHandler.PRIMITIVE_INTEGER:
			configCriteria.getContent().put(idx, (Integer) f.get(o) != null ? ((Integer) f.get(o)).toString() : StringUtils.EMPTY);
			isUpdated = true;
			break;

		case CellHandler.OBJECT_LONG:
			/* falls through */
		case CellHandler.PRIMITIVE_LONG:
			configCriteria.getContent().put(idx, (Long) f.get(o) != null ? ((Long) f.get(o)).toString() : StringUtils.EMPTY);
			isUpdated = true;
			break;

		case CellHandler.OBJECT_DOUBLE:
			/* falls through */
		case CellHandler.PRIMITIVE_DOUBLE:
			configCriteria.getContent().put(idx, CsvUtils.toDouble((Double) f.get(o), fM, tM));
			isUpdated = true;
			break;

		case CellHandler.OBJECT_BIGDECIMAL:
			configCriteria.getContent().put(idx, (BigDecimal) f.get(o) != null ? ((BigDecimal) f.get(o)).toString() : StringUtils.EMPTY);
			isUpdated = true;
			break;

		case CellHandler.OBJECT_FLOAT:
			/* falls through */
		case CellHandler.PRIMITIVE_FLOAT:
			configCriteria.getContent().put(idx, (Float) f.get(o) != null ? ((Float) f.get(o)).toString() : StringUtils.EMPTY);
			isUpdated = true;
			break;

		case CellHandler.OBJECT_BOOLEAN:
			/* falls through */
		case CellHandler.PRIMITIVE_BOOLEAN:
			configCriteria.getContent().put(idx, (Boolean) f.get(o) != null ? ((Boolean) f.get(o)).toString() : StringUtils.EMPTY);
			isUpdated = true;
			break;

		default:
			isUpdated = false;
			break;
		}

		if (!isUpdated && fT.isEnum()) {
			configCriteria.getContent().put(idx, CsvUtils.toEnum(o, f));
			isUpdated = true;
		}
		return isUpdated;
	}

	/**
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
		boolean isUpdated;

		f.setAccessible(true);

		switch (fT.getName()) {
		case CellHandler.OBJECT_DATE:
			manageDate(o, f, xlsAnnotation, v, idx);
			isUpdated = true;
			break;

		case CellHandler.OBJECT_STRING:
			manageString(o, f, v, idx);
			isUpdated = true;
			break;

		case CellHandler.OBJECT_INTEGER:
			/* falls through */
		case CellHandler.PRIMITIVE_INTEGER:
			manageInteger(o, f, v, idx);
			isUpdated = true;
			break;

		case CellHandler.OBJECT_LONG:
			/* falls through */
		case CellHandler.PRIMITIVE_LONG:
			manageLong(o, f, v, idx);
			isUpdated = true;
			break;

		case CellHandler.OBJECT_DOUBLE:
			/* falls through */
		case CellHandler.PRIMITIVE_DOUBLE:
			manageDouble(o, f, xlsAnnotation, v, idx);
			isUpdated = true;
			break;

		case CellHandler.OBJECT_BIGDECIMAL:
			manageBigDecimal(o, f, v, idx);
			isUpdated = true;
			break;

		case CellHandler.OBJECT_FLOAT:
			/* falls through */
		case CellHandler.PRIMITIVE_FLOAT:
			manageFloat(o, f, v, idx);
			isUpdated = true;
			break;

		case CellHandler.OBJECT_BOOLEAN:
			/* falls through */
		case CellHandler.PRIMITIVE_BOOLEAN:
			manageBoolean(o, f, xlsAnnotation, v, idx);
			isUpdated = true;
			break;

		default:
			isUpdated = false;
			break;
		}

		if (!isUpdated && fT.isEnum()) {
			f.set(o, Enum.valueOf((Class<Enum>) fT, v[idx]));
			isUpdated = true;
		}

		return isUpdated;
	}

	/**
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
	 *@param o
	 *            the object
	 * @param f
	 *            the field
	 * @param xlsAnnotation
	 *            the {@link XlsElement} annotation
	 * @param v
	 *            the array with the content at one line
	 * @param idx
	 *            the index of the field
	 *  @throws IllegalAccessException
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

	@Override
	public void marshalAndSave(final Object object, final String pathFile) throws Exception {

		/* Initialize a basic ConfigCriteria */
		CConfigCriteria configCriteria = new CConfigCriteria();

		marshalAndSave(configCriteria, object, pathFile);
	}

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
	 * Marshal as collection.
	 * 
	 * @param listObject
	 * @param pathFile
	 * @param file
	 * @throws Exception
	 */
	@Override
	public void marshalAsCollectionAndSave(final Collection<?> listObject, final String pathFile) throws Exception {

		/* Initialize a basic ConfigCriteria */
		CConfigCriteria configCriteria = new CConfigCriteria();

		marshalAsCollectionAndSave(configCriteria, listObject, pathFile);
	}

	/**
	 * Marshal as collection.
	 * 
	 * @param listObject
	 * @param pathFile
	 * @param file
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
			/* marshal the content  list */
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
	 * @return the {@link Object} filled up
	 */
	@Override
	public Object unmarshalFromPath(final Object object, final String pathFile) throws Exception {
		/* initialize configuration data */
		CConfigCriteria configCriteria = new CConfigCriteria();
		
		unmarshalFromPath(configCriteria, object, pathFile);

		return object;
	}

	/**
	 * Generate the object from the path file passed as parameter.
	 * 
	 * @param object
	 *            the object to fill up.
	 * @param pathFile
	 *            the path where is found the file to read and pass the
	 *            information to the object
	 * @return the {@link Object} filled up
	 */
	@Override
	public Object unmarshalFromPath(final CConfigCriteria configCriteria, final Object object, final String pathFile) throws Exception {
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

		BufferedReader br = new BufferedReader(
				new FileReader(internalPathFile + configCriteria.getCompleteFileName()));
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

		return object;
	}

	/**
	 * 
	 */
	@Override
	public void unmarshalAsCollectionFromPath(final Class<?> oC, final Collection<?> listObject, final String pathFile) throws Exception {
		/* initialize configuration data */
		CConfigCriteria configCriteria = new CConfigCriteria();

		unmarshalAsCollectionFromPath(configCriteria, oC, listObject, pathFile);
	}

	/**
	 * 
	 */
	@Override
	public void unmarshalAsCollectionFromPath(final CConfigCriteria configCriteria, final Class<?> oC, final Collection<?> listObject, final String pathFile) throws Exception {

		if(listObject == null || listObject.isEmpty() || oC == null){
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

		BufferedReader br = new BufferedReader(
				new FileReader(internalPathFile + configCriteria.getCompleteFileName()));

		for (Object object : listObject) {
			String[] values = br.lines().skip(1).map(line -> line.split(configCriteria.getSeparator()))
					.collect(Collectors.toList()).get(0);

			/* unmarshal the content  list */
			unmarshal(configCriteria, object, oC, values, -1);
		}

		/* close the file */
		br.close();
	}

}
