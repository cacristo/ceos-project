package net.ceos.project.poi.annotated.core;

import java.util.List;

public interface IGeneratorCSV {

	
	void marshalAndSave(Object object, String pathFile, String file) throws Exception;
	
	void marshalAsCollectionAndSave(List<Object> listObject, String pathFile, String file) throws Exception;
	
	Object unmarshalFromPath(Object object, String pathFile) throws Exception;
	
	void unmarshalAsCollectionFromPath(List<Object> listObject, String pathFile, String file) throws Exception;
	
	void overrideSepratorChar(char newSeparator);
}
