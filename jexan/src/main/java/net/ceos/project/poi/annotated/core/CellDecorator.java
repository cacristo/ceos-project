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
package net.ceos.project.poi.annotated.core;

/**
 * This class has all the properties available to apply to one cell decoration.
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public class CellDecorator {

	private String decoratorName;

	private String fontName = "Arial";
	private short fontSize = 10;
	private short fontColor = 0;
	private boolean fontBold = false;
	private boolean fontItalic = false;
	private byte fontUnderline = 0;

	private short alignment = 0;
	private short verticalAlignment = 0;

	private short border = 0;
	private short borderLeft = 0;
	private short borderRight = 0;
	private short borderTop = 0;
	private short borderBottom = 0;

	private short backgroundColor = 0;
	private short foregroundColor = 0;

	private boolean wrapText = false;

	public CellDecorator() {
		/* Default constructor */
	}

	/**
	 * @return the decoratorName
	 */
	public final String getDecoratorName() {
		return decoratorName;
	}

	/**
	 * @param decoratorName
	 *            the decoratorName to set
	 */
	public final void setDecoratorName(final String decoratorName) {
		this.decoratorName = decoratorName;
	}

	/**
	 * @return the fontName
	 */
	public final String getFontName() {
		return fontName;
	}

	/**
	 * @param fontName
	 *            the fontName to set
	 */
	public final void setFontName(final String fontName) {
		this.fontName = fontName;
	}

	/**
	 * @return the fontSize
	 */
	public final short getFontSize() {
		return fontSize;
	}

	/**
	 * @param fontSize
	 *            the fontSize to set
	 */
	public final void setFontSize(final short fontSize) {
		this.fontSize = fontSize;
	}

	/**
	 * @return the fontColor
	 */
	public final short getFontColor() {
		return fontColor;
	}

	/**
	 * @param fontColor
	 *            the fontColor to set
	 */
	public final void setFontColor(final short fontColor) {
		this.fontColor = fontColor;
	}

	/**
	 * @return the fontBold
	 */
	public final boolean isFontBold() {
		return fontBold;
	}

	/**
	 * @param fontBold
	 *            the fontBold to set
	 */
	public final void setFontBold(final boolean fontBold) {
		this.fontBold = fontBold;
	}

	/**
	 * @return the fontItalic
	 */
	public final boolean isFontItalic() {
		return fontItalic;
	}

	/**
	 * @param fontItalic
	 *            the fontItalic to set
	 */
	public final void setFontItalic(final boolean fontItalic) {
		this.fontItalic = fontItalic;
	}

	/**
	 * @return the fontUnderline
	 */
	public final byte getFontUnderline() {
		return fontUnderline;
	}

	/**
	 * @param fontUnderline
	 *            the fontUnderline to set
	 */
	public final void setFontUnderline(final byte fontUnderline) {
		this.fontUnderline = fontUnderline;
	}

	/**
	 * @return the alignment
	 */
	public final short getAlignment() {
		return alignment;
	}

	/**
	 * @param alignment
	 *            the alignment to set
	 */
	public final void setAlignment(final short alignment) {
		this.alignment = alignment;
	}

	/**
	 * @return the verticalAlignment
	 */
	public final short getVerticalAlignment() {
		return verticalAlignment;
	}

	/**
	 * @param verticalAlignment
	 *            the verticalAlignment to set
	 */
	public final void setVerticalAlignment(final short verticalAlignment) {
		this.verticalAlignment = verticalAlignment;
	}

	/**
	 * @return the border
	 */
	public final short getBorder() {
		return border;
	}

	/**
	 * @param border
	 *            the border to set
	 */
	public final void setBorder(final short border) {
		this.border = border;
	}

	/**
	 * @return the borderLeft
	 */
	public final short getBorderLeft() {
		return borderLeft;
	}

	/**
	 * @param borderLeft
	 *            the borderLeft to set
	 */
	public final void setBorderLeft(final short borderLeft) {
		this.borderLeft = borderLeft;
	}

	/**
	 * @return the borderRight
	 */
	public final short getBorderRight() {
		return borderRight;
	}

	/**
	 * @param borderRight
	 *            the borderRight to set
	 */
	public final void setBorderRight(final short borderRight) {
		this.borderRight = borderRight;
	}

	/**
	 * @return the borderTop
	 */
	public final short getBorderTop() {
		return borderTop;
	}

	/**
	 * @param borderTop
	 *            the borderTop to set
	 */
	public final void setBorderTop(final short borderTop) {
		this.borderTop = borderTop;
	}

	/**
	 * @return the borderBottom
	 */
	public final short getBorderBottom() {
		return borderBottom;
	}

	/**
	 * @param borderBottom
	 *            the borderBottom to set
	 */
	public final void setBorderBottom(final short borderBottom) {
		this.borderBottom = borderBottom;
	}

	/**
	 * @return the backgroundColor
	 */
	public final short getBackgroundColor() {
		return backgroundColor;
	}

	/**
	 * @param backgroundColor
	 *            the backgroundColor to set
	 */
	public final void setBackgroundColor(final short backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	/**
	 * @return the foregroundColor
	 */
	public final short getForegroundColor() {
		return foregroundColor;
	}

	/**
	 * @param foregroundColor
	 *            the foregroundColor to set
	 */
	public final void setForegroundColor(final short foregroundColor) {
		this.foregroundColor = foregroundColor;
	}

	/**
	 * @return the wrapText
	 */
	public final boolean isWrapText() {
		return wrapText;
	}

	/**
	 * @param wrapText
	 *            the wrapText to set
	 */
	public final void setWrapText(final boolean wrapText) {
		this.wrapText = wrapText;
	}

}
