/**
 * Copyright 2016 Carlos CRISTO ABREU
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.ceos.project.poi.annotated.bean.retail;

import java.sql.Blob;

/**
 * The product line entity
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
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
