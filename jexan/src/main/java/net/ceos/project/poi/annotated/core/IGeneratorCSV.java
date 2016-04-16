package net.ceos.project.poi.annotated.core;

import java.util.Collection;

public interface IGeneratorCSV {


	void marshalAndSave(Object object, String pathFile) throws Exception;

	void marshalAndSave(CConfigCriteria configCriteria, Object object, String pathFile) throws Exception;
	
	void marshalAsCollectionAndSave(Collection<?> listObject, String pathFile) throws Exception;
	
	void marshalAsCollectionAndSave(CConfigCriteria configCriteria, Collection<?> listObject, String pathFile) throws Exception;

	Object unmarshalFromPath(Object object, String pathFile) throws Exception;

	Object unmarshalFromPath(CConfigCriteria configCriteria, Object object, String pathFile) throws Exception;
	
	void unmarshalAsCollectionFromPath(Class<?> oC, Collection<?> listObject, String pathFile) throws Exception;
	
	void unmarshalAsCollectionFromPath(CConfigCriteria configCriteria, Class<?> oC, Collection<?> listObject, String pathFile) throws Exception;

}
