package net.ceos.project.poi.annotated.bean.retail;

import java.sql.Blob;

public class ProductLine {

	private String productLine;
	private String textDescription;
	private String htmlDescription;
	private Blob image;

	public ProductLine() {
		/* empty constructor */
	}

	/**
	 * @return the productLine
	 */
	public final String getProductLine() {
		return productLine;
	}

	/**
	 * @param productLine
	 *            the productLine to set
	 */
	public final void setProductLine(String productLine) {
		this.productLine = productLine;
	}

	/**
	 * @return the textDescription
	 */
	public final String getTextDescription() {
		return textDescription;
	}

	/**
	 * @param textDescription
	 *            the textDescription to set
	 */
	public final void setTextDescription(String textDescription) {
		this.textDescription = textDescription;
	}

	/**
	 * @return the htmlDescription
	 */
	public final String getHtmlDescription() {
		return htmlDescription;
	}

	/**
	 * @param htmlDescription
	 *            the htmlDescription to set
	 */
	public final void setHtmlDescription(String htmlDescription) {
		this.htmlDescription = htmlDescription;
	}

	/**
	 * @return the image
	 */
	public final Blob getImage() {
		return image;
	}

	/**
	 * @param image
	 *            the image to set
	 */
	public final void setImage(Blob image) {
		this.image = image;
	}

}
