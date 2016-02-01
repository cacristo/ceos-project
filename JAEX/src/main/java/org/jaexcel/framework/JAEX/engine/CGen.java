package org.jaexcel.framework.JAEX.engine;

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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.jaexcel.framework.JAEX.annotation.XlsConfiguration;
import org.jaexcel.framework.JAEX.annotation.XlsElement;
import org.jaexcel.framework.JAEX.annotation.XlsSheet;
import org.jaexcel.framework.JAEX.configuration.Configuration;
import org.jaexcel.framework.JAEX.definition.ExtensionFileType;
import org.jaexcel.framework.JAEX.definition.ExceptionMessage;
import org.jaexcel.framework.JAEX.exception.ConverterException;
import org.jaexcel.framework.JAEX.exception.ElementException;

public class CGen implements IGeneratorCSV {

	private static final String EMPTY = "";
	private static final char END_OF_LINE = '\n';
	private static final String DEFAULT_DATE = "dd-MMM-yyyy HH:mm:ss";

	private char separator = ',';
	private Boolean isHeaderPainted = Boolean.FALSE;
	private Map<Integer, String> header = new HashMap<>();
	private Map<Integer, String> content = new HashMap<>();

	Configuration configuration;

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
	 */
	private Configuration initializeConfigurationData(Class<?> oC) {
		Configuration config = null;

		/* Process @XlsConfiguration */
		if (oC.isAnnotationPresent(XlsConfiguration.class)) {
			XlsConfiguration xlsAnnotation = (XlsConfiguration) oC.getAnnotation(XlsConfiguration.class);
			config = initializeConfiguration(xlsAnnotation);
		}

		/* Process @XlsSheet */
		if (oC.isAnnotationPresent(XlsSheet.class)) {
			XlsSheet xlsAnnotation = (XlsSheet) oC.getAnnotation(XlsSheet.class);
			config = initializeSheetConfiguration(xlsAnnotation);
		}
		return config;
	}

	/**
	 * Add the main xls configuration.
	 * 
	 * @param config
	 * @return
	 */
	private Configuration initializeConfiguration(XlsConfiguration config) {
		if (configuration == null) {
			configuration = new Configuration();
		}
		configuration.setName(config.nameFile());
		configuration.setNameFile(config.nameFile() + config.extensionFile().getExtension());
		configuration.setExtensionFile(config.extensionFile());
		return configuration;
	}

	/**
	 * Add the sheet configuration.
	 * 
	 * @param config
	 * @return
	 */
	private Configuration initializeSheetConfiguration(XlsSheet config) {
		if (configuration == null) {
			configuration = new Configuration();
		}
		configuration.setTitleSheet(config.title());
		configuration.setPropagationType(config.propagation());
		configuration.setStartRow(config.startRow());
		configuration.setStartCell(config.startCell());
		return configuration;
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
	private void addLine(FileWriter f, Map<Integer, String> values) throws IOException {
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
	 * Force the the separator to be used.<br>
	 * By default is ','
	 * 
	 * @param newSeparator
	 *            the new separator to apply
	 */
	public void overrideSepratorChar(char newSeparator) {
		this.separator = newSeparator;
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
	 * @throws Exception
	 */
	private int initializeField(Object o, Field f, XlsElement xlsAnnotation, int idx) throws Exception {

		/* make the field accessible to recover the value */
		f.setAccessible(true);

		int counter = 0;

		Class<?> fT = f.getType();

		boolean isAppliedToBaseObject = applyBaseObject(o, fT, f, xlsAnnotation, idx);

		if (!isAppliedToBaseObject && !fT.isPrimitive()) {
			Object nO = f.get(o);

			/* manage null objects */
			if (nO == null) {
				nO = fT.newInstance();
			}
			Class<?> oC = nO.getClass();

			counter = marshal(nO, oC, null, idx - 1);
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
	 * @throws Exception
	 */
	private int marshal(Object o, Class<?> oC, FileWriter fW, int idx) throws Exception {
		/* counter related to the number of fields (if new object) */
		int counter = -1;

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
				header.put(idx + xlsAnnotation.position(), xlsAnnotation.title());

				/* content values treatment */
				idx += initializeField(o, f, xlsAnnotation, idx + xlsAnnotation.position());
			}
		}

		if (fW != null) {
			/* paint the header line */
			if (!isHeaderPainted) {
				addLine(fW, header);
				isHeaderPainted = Boolean.TRUE;
			}
			/* paint the content line */
			addLine(fW, content);
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
	 * @throws IllegalArgumentException
	 * @throws ParseException
	 */
	private int unmarshal(Object o, Class<?> oC, String[] v, int idx) throws IllegalAccessException,
			ConverterException, InstantiationException, IllegalArgumentException, ParseException {
		/* counter related to the number of fields (if new object) */
		int counter = -1;

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
						idx + xlsAnnotation.position());

				if (!isAppliedToBaseObject && !fT.isPrimitive()) {

					Object subObjbect = fT.newInstance();
					Class<?> subObjbectClass = subObjbect.getClass();

					int internalCellCounter = unmarshal(subObjbect, subObjbectClass, v,
							idx + xlsAnnotation.position() - 1);

					/* add the sub object to the parent object */
					f.set(o, subObjbect);

					/* update the index */
					idx += internalCellCounter;
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
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws ConverterException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InvocationTargetException
	 */
	private boolean applyBaseObject(Object o, Class<?> fT, Field f, XlsElement element, int idx)
			throws IllegalArgumentException, IllegalAccessException, ConverterException, NoSuchMethodException,
			SecurityException, InvocationTargetException {
		/* flag which define if the cell was updated or not */
		boolean isUpdated = false;

		/* reading mask */
		String tM = element.transformMask();
		String fM = element.formatMask();

		switch (fT.getName()) {
		case CellValueUtils.OBJECT_DATE:

			// OldCode
			// SimpleDateFormat formatDate = new SimpleDateFormat(DEFAULT_DATE);
			// content.put(idx, (Date) f.get(o) != null ?
			// formatDate.format((Date) f.get(o)) : EMPTY);
			content.put(idx, CsvUtils.toDate((Date) f.get(o), fM, tM));
			isUpdated = true;
			break;

		case CellValueUtils.OBJECT_STRING:

			content.put(idx, (String) f.get(o) != null ? (String) f.get(o) : EMPTY);
			isUpdated = true;
			break;

		case CellValueUtils.OBJECT_INTEGER:
			/* falls through */
		case CellValueUtils.PRIMITIVE_INTEGER:

			content.put(idx, (Integer) f.get(o) != null ? ((Integer) f.get(o)).toString() : EMPTY);
			isUpdated = true;
			break;

		case CellValueUtils.OBJECT_LONG:
			/* falls through */
		case CellValueUtils.PRIMITIVE_LONG:

			content.put(idx, (Long) f.get(o) != null ? ((Long) f.get(o)).toString() : EMPTY);
			isUpdated = true;
			break;

		case CellValueUtils.OBJECT_DOUBLE:
			/* falls through */
		case CellValueUtils.PRIMITIVE_DOUBLE:

			// content.put(idx, (Double) f.get(o) != null ? ((Double)
			// f.get(o)).toString() : EMPTY);
			content.put(idx, CsvUtils.toDouble((Double) f.get(o), fM, tM));
			isUpdated = true;
			break;

		case CellValueUtils.OBJECT_BIGDECIMAL:

			content.put(idx, (BigDecimal) f.get(o) != null ? ((BigDecimal) f.get(o)).toString() : EMPTY);
			// content.put(idx, CsvUtils.toBigDecimal((BigDecimal) f.get(o), fM,
			// tM));
			isUpdated = true;
			break;

		case CellValueUtils.OBJECT_FLOAT:
			/* falls through */
		case CellValueUtils.PRIMITIVE_FLOAT:

			content.put(idx, (Float) f.get(o) != null ? ((Float) f.get(o)).toString() : EMPTY);
			isUpdated = true;
			break;

		case CellValueUtils.OBJECT_BOOLEAN:
			/* falls through */
		case CellValueUtils.PRIMITIVE_BOOLEAN:

			content.put(idx, (Boolean) f.get(o) != null ? ((Boolean) f.get(o)).toString() : EMPTY);
			isUpdated = true;
			break;

		default:
			isUpdated = false;
			break;
		}

		if (!isUpdated && fT.isEnum()) {

			content.put(idx, CsvUtils.toEnum(o, f));
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
	private boolean applyBaseCsvObject(Object o, Class<?> fT, Field f, XlsElement xlsAnnotation, String[] v, int idx)
			throws IllegalArgumentException, IllegalAccessException, ConverterException, ParseException {
		/* flag which define if the cell was updated or not */
		boolean isUpdated = false;

		f.setAccessible(true);

		switch (fT.getName()) {
		case CellValueUtils.OBJECT_DATE:

			// OldCode
			// SimpleDateFormat formatDate = new SimpleDateFormat(DEFAULT_DATE);
			// f.set(o, formatDate.parse(v[idx]));

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
			isUpdated = true;

			break;

		case CellValueUtils.OBJECT_STRING:

			f.set(o, v[idx]);
			isUpdated = true;

			break;

		case CellValueUtils.OBJECT_INTEGER:
			/* falls through */
		case CellValueUtils.PRIMITIVE_INTEGER:

			String iValue = v[idx];
			if (StringUtils.isNotBlank(iValue)) {
				f.set(o, Integer.valueOf(iValue));
			}
			isUpdated = true;

			break;

		case CellValueUtils.OBJECT_LONG:
			/* falls through */
		case CellValueUtils.PRIMITIVE_LONG:

			String dValue = v[idx];
			if (StringUtils.isNotBlank(dValue)) {
				f.set(o, Double.valueOf(dValue).longValue());
			}
			isUpdated = true;

			break;

		case CellValueUtils.OBJECT_DOUBLE:
			/* falls through */
		case CellValueUtils.PRIMITIVE_DOUBLE:

			// OldCode
			// String dPValue = v[idx];
			// if (StringUtils.isNotBlank(dPValue)) {
			// f.set(o, Double.valueOf(dPValue));
			// }

			String dPValue = v[idx];
			if (StringUtils.isNotBlank(dPValue)) {
				if (StringUtils.isNotBlank(xlsAnnotation.transformMask())) {
					f.set(o, Double.parseDouble(dPValue.replace(",", ".")));
				} else if (StringUtils.isNotBlank(xlsAnnotation.formatMask())) {
					f.set(o, Double.parseDouble(dPValue.replace(",", ".")));
				} else {
					f.set(o, Double.parseDouble(dPValue));
				}
			}

			isUpdated = true;

			break;

		case CellValueUtils.OBJECT_BIGDECIMAL:

			String dBdValue = v[idx];
			if (dBdValue != null) {
				f.set(o, BigDecimal.valueOf(Double.valueOf(dBdValue)));
			}
			isUpdated = true;

			break;

		case CellValueUtils.OBJECT_FLOAT:
			/* falls through */
		case CellValueUtils.PRIMITIVE_FLOAT:

			String fValue = v[idx];
			if (StringUtils.isNotBlank(fValue)) {
				f.set(o, Float.valueOf(fValue));
			}
			isUpdated = true;

			break;

		case CellValueUtils.OBJECT_BOOLEAN:
			/* falls through */
		case CellValueUtils.PRIMITIVE_BOOLEAN:

			String bool = v[idx];

			if (StringUtils.isNotBlank(xlsAnnotation.transformMask())) {
				/* apply format mask defined at transform mask */
				String[] split = xlsAnnotation.transformMask().split("/");
				f.set(o, StringUtils.isNotBlank(bool) ? (split[0].equals(bool) ? true : false) : null);

			} else {
				/* locale mode */
				f.set(o, StringUtils.isNotBlank(bool) ? Boolean.valueOf(bool) : null);
			}
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

	/* ######################## Marshal methods ########################## */

	public void marshalAndSave(Object object, String pathFile, String file) throws Exception {
		/* initialize the runtime class of the object */
		Class<?> oC = initializeRuntimeClass(object);

		/* initialize configuration data */
		Configuration config = initializeConfigurationData(oC);

		/*
		 * check if the path terminate with the file separator, otherwise will
		 * be added to avoid any problem
		 */
		if (!pathFile.endsWith(File.separator)) {
			pathFile = pathFile.concat(File.separator);
		}

		// FileWriter fW = new FileWriter(pathFile +
		// configuration.getNameFile());
		FileWriter fW = new FileWriter(pathFile + config.getName() + ExtensionFileType.CSV.getExtension());

		marshal(object, oC, fW, 0);

		/* flush then close the file */
		fW.flush();
		fW.close();

	}

	public void marshalAsCollectionAndSave(List<Object> listObject, String pathFile, String file) throws Exception {

		if (listObject != null && !listObject.isEmpty()) {

			/*
			 * check if the path terminate with the file separator, otherwise
			 * will be added to avoid any problem
			 */
			if (!pathFile.endsWith(File.separator)) {
				pathFile = pathFile.concat(File.separator);
			}

			// FileWriter fW = new FileWriter(pathFile +
			// configuration.getNameFile());
			FileWriter fW = new FileWriter(pathFile + file + ExtensionFileType.CSV.getExtension());

			for (Object object : listObject) {
				/* initialize the runtime class of the object */
				Class<?> oC = initializeRuntimeClass(object);

				/* initialize configuration data */
				// Configuration config = initializeConfigurationData(oC);

				marshal(object, oC, fW, 0);

			}

			/* flush then close the file */
			fW.flush();
			fW.close();

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
	 * @return the {@link Object} filled up
	 */
	public Object unmarshalFromPath(Object object, String pathFile) throws Exception {
		/* initialize the runtime class of the object */
		Class<?> oC = initializeRuntimeClass(object);

		/* initialize configuration data */
		Configuration config = initializeConfigurationData(oC);

		/*
		 * check if the path terminate with the file separator, otherwise will
		 * be added to avoid any problem
		 */
		if (!pathFile.endsWith(File.separator)) {
			pathFile = pathFile.concat(File.separator);
		}

		BufferedReader br = new BufferedReader(
				new FileReader(pathFile + config.getName() + ExtensionFileType.CSV.getExtension()));
		String[] values = null;
		String line = "";
		boolean isHeaderLine = true;
		while ((line = br.readLine()) != null) {
			if (isHeaderLine) {
				isHeaderLine = false;
				continue;
			}
			values = line.split(",");
		}
		unmarshal(object, oC, values, -1);

		br.close();

		return object;
	}

	/**
	 * 
	 */
	public void unmarshalAsCollectionFromPath(List<Object> listObject, String pathFile, String file) throws Exception {

		/*
		 * check if the path terminate with the file separator, otherwise will
		 * be added to avoid any problem
		 */
		if (!pathFile.endsWith(File.separator)) {
			pathFile = pathFile.concat(File.separator);
		}

		BufferedReader br = new BufferedReader(new FileReader(pathFile + file + ExtensionFileType.CSV.getExtension()));

		for (Object object : listObject) {

			/* initialize the runtime class of the object */
			Class<?> oC = initializeRuntimeClass(object);

			/* initialize configuration data */
			// Configuration config = initializeConfigurationData(oC);

			String[] values = null;
			String line = "";
			boolean isHeaderLine = true;
			while ((line = br.readLine()) != null) {
				if (isHeaderLine) {
					isHeaderLine = false;
					continue;
				}
				values = line.split(",");
			}
			unmarshal(object, oC, values, -1);

		}

		br.close();
	}

}
