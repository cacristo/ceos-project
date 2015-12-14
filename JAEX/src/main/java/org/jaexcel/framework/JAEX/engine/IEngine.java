package org.jaexcel.framework.JAEX.engine;

import java.util.Collection;

public interface IEngine {

	void marshal(Object object);
	void marshal(Object... objects);
	void marshalAsCollection(Collection<?> collection);


	//<T> Object unmarshal(T obecjt);
	//<T> Collection<T> unmarshalToCollection(T object);
	
	Object unmarshal(Object obecjt);
	Collection<?> unmarshalToCollection(Object object);
}
