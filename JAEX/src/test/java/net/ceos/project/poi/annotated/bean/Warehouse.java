package net.ceos.project.poi.annotated.bean;

import java.util.List;

import net.ceos.project.poi.annotated.annotation.XlsConfiguration;
import net.ceos.project.poi.annotated.annotation.XlsElement;
import net.ceos.project.poi.annotated.annotation.XlsSheet;

@XlsConfiguration(nameFile = "ManageWharehouse")
@XlsSheet(title = "Store")
public class Warehouse {

	@XlsElement(title = "Store name", position = 1)
	private String name;
	
	@XlsElement(title = "Location", position = 2)
	private String location;

	@XlsElement(title = "List of units", position = 3)
	private List<Unit> unitsAvaliable;
	
	@XlsElement(title = "List of units", position = 4)
	private List<Store> storeProvider;
	
}
