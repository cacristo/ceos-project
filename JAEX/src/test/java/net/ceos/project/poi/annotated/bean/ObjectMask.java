package net.ceos.project.poi.annotated.bean;

import java.math.BigDecimal;
import java.util.Date;

import net.ceos.project.poi.annotated.annotation.XlsConfiguration;
import net.ceos.project.poi.annotated.annotation.XlsElement;
import net.ceos.project.poi.annotated.annotation.XlsSheet;

@XlsConfiguration(nameFile = "ObjectMask")
@XlsSheet(title = "Object Mask Sample")
public class ObjectMask {

	@XlsElement(title = "Date value default", position = 1)
	private Date dateAttribute1;

	@XlsElement(title = "Date value with format mask (1)", position = 2, formatMask = "dd-MM-yyyy")
	private Date dateAttribute2;

	@XlsElement(title = "Date value with format mask (2)", position = 3, formatMask = "yyMM")
	private Date dateAttribute3;

	@XlsElement(title = "Date value with format mask (3)", position = 4, formatMask = "yyyy")
	private Date dateAttribute4;

	@XlsElement(title = "Date value with transform mask (1)", position = 5, transformMask = "yyyy-MM-dd")
	private Date dateAttribute5;

	@XlsElement(title = "Date value with transform mask (2)", position = 6, transformMask = "yyMM")
	private Date dateAttribute6;

	@XlsElement(title = "Date value with transform mask (3)", position = 7, transformMask = "yyyy")
	private Date dateAttribute7;

	@XlsElement(title = "Double primitive value default", position = 8)
	private double doubleAttribute1;

	@XlsElement(title = "Double primitive with format mask (1)", position = 9, formatMask = "0.0")
	private double doubleAttribute2;

	@XlsElement(title = "Double with format mask (2)", position = 10, formatMask = "0.0000")
	private Double doubleAttribute3;

	@XlsElement(title = "Double with transform mask (1)", position = 11, transformMask = "0.0")
	private Double doubleAttribute4;

	@XlsElement(title = "Double primitive with transform mask (2)", position = 12, transformMask = "0.00000")
	private double doubleAttribute5;

	@XlsElement(title = "BigDecimal value default", position = 13)
	private BigDecimal bigDecimalAttribute1;

	@XlsElement(title = "BigDecimal with format mask (1)", position = 14, formatMask = "0.0")
	private BigDecimal bigDecimalAttribute2;

	@XlsElement(title = "BigDecimal with format mask (2)", position = 15, formatMask = "0.0000")
	private BigDecimal bigDecimalAttribute3;

	@XlsElement(title = "BigDecimal with transform mask (1)", position = 16, transformMask = "0.0")
	private BigDecimal bigDecimalAttribute4;

	@XlsElement(title = "BigDecimal with transform mask (2)", position = 17, transformMask = "0.00000")
	private BigDecimal bigDecimalAttribute5;

	@XlsElement(title = "BigDecimal with transform mask (3)", position = 18, transformMask = "0.00")
	private BigDecimal bigDecimalAttribute6;

	@XlsElement(title = "Float primitive value default", position = 19)
	private float floatAttribute1;

	@XlsElement(title = "Float with format mask (1)", position = 20, formatMask = "0.0")
	private Float floatAttribute2;

	@XlsElement(title = "Float with format mask (2)", position = 21, formatMask = "0.0000")
	private Float floatAttribute3;

	@XlsElement(title = "Float with transform mask (1)", position = 22, transformMask = "0.0")
	private Float floatAttribute4;

	@XlsElement(title = "Float primitive with transform mask (2)", position = 23, transformMask = "0.00")
	private float floatAttribute5;

	@XlsElement(title = "Boolean primitive with transform mask (1)", position = 24, transformMask = "Yes/No")
	private boolean booleanAttribute1;

	@XlsElement(title = "Boolean with transform mask (2)", position = 25)
	private Boolean booleanAttribute2;

	@XlsElement(title = "Boolean with transform mask (3)", position = 26, transformMask = "Oui/Non")
	private Boolean booleanAttribute3;

	@XlsElement(title = "Boolean primitive with format mask (2)", position = 27, formatMask = "Bah Oui/No No")
	private boolean booleanAttribute4;
	
	public ObjectMask() {
	}

	/**
	 * @return the dateAttribute1
	 */
	public Date getDateAttribute1() {
		return dateAttribute1;
	}

	/**
	 * @param dateAttribute1 the dateAttribute1 to set
	 */
	public void setDateAttribute1(Date dateAttribute1) {
		this.dateAttribute1 = dateAttribute1;
	}

	/**
	 * @return the dateAttribute2
	 */
	public Date getDateAttribute2() {
		return dateAttribute2;
	}

	/**
	 * @param dateAttribute2 the dateAttribute2 to set
	 */
	public void setDateAttribute2(Date dateAttribute2) {
		this.dateAttribute2 = dateAttribute2;
	}

	/**
	 * @return the dateAttribute3
	 */
	public Date getDateAttribute3() {
		return dateAttribute3;
	}

	/**
	 * @param dateAttribute3 the dateAttribute3 to set
	 */
	public void setDateAttribute3(Date dateAttribute3) {
		this.dateAttribute3 = dateAttribute3;
	}

	/**
	 * @return the dateAttribute4
	 */
	public Date getDateAttribute4() {
		return dateAttribute4;
	}

	/**
	 * @param dateAttribute4 the dateAttribute4 to set
	 */
	public void setDateAttribute4(Date dateAttribute4) {
		this.dateAttribute4 = dateAttribute4;
	}

	/**
	 * @return the dateAttribute5
	 */
	public Date getDateAttribute5() {
		return dateAttribute5;
	}

	/**
	 * @param dateAttribute5 the dateAttribute5 to set
	 */
	public void setDateAttribute5(Date dateAttribute5) {
		this.dateAttribute5 = dateAttribute5;
	}

	/**
	 * @return the dateAttribute6
	 */
	public Date getDateAttribute6() {
		return dateAttribute6;
	}

	/**
	 * @param dateAttribute6 the dateAttribute6 to set
	 */
	public void setDateAttribute6(Date dateAttribute6) {
		this.dateAttribute6 = dateAttribute6;
	}

	/**
	 * @return the dateAttribute7
	 */
	public Date getDateAttribute7() {
		return dateAttribute7;
	}

	/**
	 * @param dateAttribute7 the dateAttribute7 to set
	 */
	public void setDateAttribute7(Date dateAttribute7) {
		this.dateAttribute7 = dateAttribute7;
	}

	/**
	 * @return the doubleAttribute1
	 */
	public double getDoubleAttribute1() {
		return doubleAttribute1;
	}

	/**
	 * @param doubleAttribute1 the doubleAttribute1 to set
	 */
	public void setDoubleAttribute1(double doubleAttribute1) {
		this.doubleAttribute1 = doubleAttribute1;
	}

	/**
	 * @return the doubleAttribute2
	 */
	public double getDoubleAttribute2() {
		return doubleAttribute2;
	}

	/**
	 * @param doubleAttribute2 the doubleAttribute2 to set
	 */
	public void setDoubleAttribute2(double doubleAttribute2) {
		this.doubleAttribute2 = doubleAttribute2;
	}

	/**
	 * @return the doubleAttribute3
	 */
	public Double getDoubleAttribute3() {
		return doubleAttribute3;
	}

	/**
	 * @param doubleAttribute3 the doubleAttribute3 to set
	 */
	public void setDoubleAttribute3(Double doubleAttribute3) {
		this.doubleAttribute3 = doubleAttribute3;
	}

	/**
	 * @return the doubleAttribute4
	 */
	public Double getDoubleAttribute4() {
		return doubleAttribute4;
	}

	/**
	 * @param doubleAttribute4 the doubleAttribute4 to set
	 */
	public void setDoubleAttribute4(Double doubleAttribute4) {
		this.doubleAttribute4 = doubleAttribute4;
	}

	/**
	 * @return the doubleAttribute5
	 */
	public double getDoubleAttribute5() {
		return doubleAttribute5;
	}

	/**
	 * @param doubleAttribute5 the doubleAttribute5 to set
	 */
	public void setDoubleAttribute5(double doubleAttribute5) {
		this.doubleAttribute5 = doubleAttribute5;
	}

	/**
	 * @return the bigDecimalAttribute1
	 */
	public BigDecimal getBigDecimalAttribute1() {
		return bigDecimalAttribute1;
	}

	/**
	 * @param bigDecimalAttribute1 the bigDecimalAttribute1 to set
	 */
	public void setBigDecimalAttribute1(BigDecimal bigDecimalAttribute1) {
		this.bigDecimalAttribute1 = bigDecimalAttribute1;
	}

	/**
	 * @return the bigDecimalAttribute2
	 */
	public BigDecimal getBigDecimalAttribute2() {
		return bigDecimalAttribute2;
	}

	/**
	 * @param bigDecimalAttribute2 the bigDecimalAttribute2 to set
	 */
	public void setBigDecimalAttribute2(BigDecimal bigDecimalAttribute2) {
		this.bigDecimalAttribute2 = bigDecimalAttribute2;
	}

	/**
	 * @return the bigDecimalAttribute3
	 */
	public BigDecimal getBigDecimalAttribute3() {
		return bigDecimalAttribute3;
	}

	/**
	 * @param bigDecimalAttribute3 the bigDecimalAttribute3 to set
	 */
	public void setBigDecimalAttribute3(BigDecimal bigDecimalAttribute3) {
		this.bigDecimalAttribute3 = bigDecimalAttribute3;
	}

	/**
	 * @return the bigDecimalAttribute4
	 */
	public BigDecimal getBigDecimalAttribute4() {
		return bigDecimalAttribute4;
	}

	/**
	 * @param bigDecimalAttribute4 the bigDecimalAttribute4 to set
	 */
	public void setBigDecimalAttribute4(BigDecimal bigDecimalAttribute4) {
		this.bigDecimalAttribute4 = bigDecimalAttribute4;
	}

	/**
	 * @return the bigDecimalAttribute5
	 */
	public BigDecimal getBigDecimalAttribute5() {
		return bigDecimalAttribute5;
	}

	/**
	 * @param bigDecimalAttribute5 the bigDecimalAttribute5 to set
	 */
	public void setBigDecimalAttribute5(BigDecimal bigDecimalAttribute5) {
		this.bigDecimalAttribute5 = bigDecimalAttribute5;
	}

	/**
	 * @return the bigDecimalAttribute6
	 */
	public BigDecimal getBigDecimalAttribute6() {
		return bigDecimalAttribute6;
	}

	/**
	 * @param bigDecimalAttribute6 the bigDecimalAttribute6 to set
	 */
	public void setBigDecimalAttribute6(BigDecimal bigDecimalAttribute6) {
		this.bigDecimalAttribute6 = bigDecimalAttribute6;
	}

	/**
	 * @return the floatAttribute1
	 */
	public float getFloatAttribute1() {
		return floatAttribute1;
	}

	/**
	 * @param floatAttribute1 the floatAttribute1 to set
	 */
	public void setFloatAttribute1(float floatAttribute1) {
		this.floatAttribute1 = floatAttribute1;
	}

	/**
	 * @return the floatAttribute2
	 */
	public Float getFloatAttribute2() {
		return floatAttribute2;
	}

	/**
	 * @param floatAttribute2 the floatAttribute2 to set
	 */
	public void setFloatAttribute2(Float floatAttribute2) {
		this.floatAttribute2 = floatAttribute2;
	}

	/**
	 * @return the floatAttribute3
	 */
	public Float getFloatAttribute3() {
		return floatAttribute3;
	}

	/**
	 * @param floatAttribute3 the floatAttribute3 to set
	 */
	public void setFloatAttribute3(Float floatAttribute3) {
		this.floatAttribute3 = floatAttribute3;
	}

	/**
	 * @return the floatAttribute4
	 */
	public Float getFloatAttribute4() {
		return floatAttribute4;
	}

	/**
	 * @param floatAttribute4 the floatAttribute4 to set
	 */
	public void setFloatAttribute4(Float floatAttribute4) {
		this.floatAttribute4 = floatAttribute4;
	}

	/**
	 * @return the floatAttribute5
	 */
	public float getFloatAttribute5() {
		return floatAttribute5;
	}

	/**
	 * @param floatAttribute5 the floatAttribute5 to set
	 */
	public void setFloatAttribute5(float floatAttribute5) {
		this.floatAttribute5 = floatAttribute5;
	}

	/**
	 * @return the booleanAttribute1
	 */
	public boolean isBooleanAttribute1() {
		return booleanAttribute1;
	}

	/**
	 * @param booleanAttribute1 the booleanAttribute1 to set
	 */
	public void setBooleanAttribute1(boolean booleanAttribute1) {
		this.booleanAttribute1 = booleanAttribute1;
	}

	/**
	 * @return the booleanAttribute2
	 */
	public Boolean getBooleanAttribute2() {
		return booleanAttribute2;
	}

	/**
	 * @param booleanAttribute2 the booleanAttribute2 to set
	 */
	public void setBooleanAttribute2(Boolean booleanAttribute2) {
		this.booleanAttribute2 = booleanAttribute2;
	}

	/**
	 * @return the booleanAttribute3
	 */
	public Boolean getBooleanAttribute3() {
		return booleanAttribute3;
	}

	/**
	 * @param booleanAttribute3 the booleanAttribute3 to set
	 */
	public void setBooleanAttribute3(Boolean booleanAttribute3) {
		this.booleanAttribute3 = booleanAttribute3;
	}

	/**
	 * @return the booleanAttribute4
	 */
	public boolean isBooleanAttribute4() {
		return booleanAttribute4;
	}

	/**
	 * @param booleanAttribute4 the booleanAttribute4 to set
	 */
	public void setBooleanAttribute4(boolean booleanAttribute4) {
		this.booleanAttribute4 = booleanAttribute4;
	}
}
