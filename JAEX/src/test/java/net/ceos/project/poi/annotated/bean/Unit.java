package net.ceos.project.poi.annotated.bean;

import net.ceos.project.poi.annotated.annotation.XlsConfiguration;
import net.ceos.project.poi.annotated.annotation.XlsElement;
import net.ceos.project.poi.annotated.annotation.XlsSheet;
import net.ceos.project.poi.annotated.definition.PropagationType;

@XlsConfiguration(nameFile = "ManageUnits")
@XlsSheet(title = "Units History", propagation = PropagationType.PROPAGATION_VERTICAL)
public class Unit {
	@XlsElement(title = "Unit name", position = 1)
	private String name;

	@XlsElement(title = "Unit code", position = 2)
	private String code;
	
	@XlsElement(title = "Unit family", position = 3)
	private UnitFamily unitFamily;

	@XlsElement(title = "Purchase price", position = 4)
	private double purchasePrice;

	@XlsElement(title = "Sale price", position = 5)
	private double salePrice;

	@XlsElement(title = "Tax applied", position = 6)
	private int tax;

	@XlsElement(title = "Sale price (with Tax)", isFormula = true, formula = "Eidx * (Fidx% + 1)", position = 7)
	//@XlsElement(title = "Sale price (with Tax)", isFormula = true, formula = "idy5 * (idy6% + 1)", position = 7)
	private double salePriceTax;

	@XlsElement(title = "Number of units", position = 8)
	private int unitNumbers;

	@XlsElement(title = "Return of investment", isFormula = true, position = 9)
	private double unitROI;
	
}
