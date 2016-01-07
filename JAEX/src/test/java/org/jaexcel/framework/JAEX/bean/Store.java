package org.jaexcel.framework.JAEX.bean;

import java.util.List;

import org.jaexcel.framework.JAEX.annotation.XlsConfiguration;
import org.jaexcel.framework.JAEX.annotation.XlsElement;
import org.jaexcel.framework.JAEX.annotation.XlsSheet;

@XlsConfiguration(nameFile = "ManageStores")
@XlsSheet(title = "Store")
public class Store {

	@XlsElement(title = "Store name", position = 1)
	private String name;
	
	@XlsElement(title = "Location", position = 2)
	private String location;

	@XlsElement(title = "List of units", position = 3)
	private List<Unit> unitsAvaliable;

	@XlsElement(title = "List of units", position = 4)
	private List<Unit> unitsRequested;
	
}
