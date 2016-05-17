package net.ceos.project.poi.annotated.bean.collection;

import java.util.List;

import net.ceos.project.poi.annotated.annotation.XlsConfiguration;
import net.ceos.project.poi.annotated.annotation.XlsElement;
import net.ceos.project.poi.annotated.annotation.XlsSheet;
import net.ceos.project.poi.annotated.definition.CascadeType;
import net.ceos.project.poi.annotated.definition.ExtensionFileType;
import net.ceos.project.poi.annotated.definition.PropagationType;

@XlsConfiguration(nameFile = "object1", extensionFile = ExtensionFileType.XLSX)
@XlsSheet(title = "object", startRow=2, propagation = PropagationType.PROPAGATION_HORIZONTAL,cascadeLevel=CascadeType.CASCADE_FULL)
public class Object1 {

	//@XlsElement(title = "string", position = 1)
	private String  string;
	
	@XlsElement(title = "list multi", position = 1)
	private List<Object2>  listObject2;

	public Object1 () {
	}

	public List<Object2> getListObject2() {
		return listObject2;
	}

	public void setListObject2(List<Object2> listObject2) {
		this.listObject2 = listObject2;
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}


	
	
	
	
}
