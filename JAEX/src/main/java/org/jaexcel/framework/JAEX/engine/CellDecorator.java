package org.jaexcel.framework.JAEX.engine;

public class CellDecorator {

	private String decoratorName;
	
	private String fontName = "Arial";
	private short fontSize = 10;
	private boolean fontBold = false;
	private boolean fontItalic = false;
	// TODO private short fontUnderline = 0;

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
	}
	
	/**
	 * @return the decoratorName
	 */
	public String getDecoratorName() {
		return decoratorName;
	}

	/**
	 * @param decoratorName the decoratorName to set
	 */
	public void setDecoratorName(String decoratorName) {
		this.decoratorName = decoratorName;
	}

	/**
	 * @return the fontName
	 */
	public String getFontName() {
		return fontName;
	}

	/**
	 * @param fontName the fontName to set
	 */
	public void setFontName(String fontName) {
		this.fontName = fontName;
	}

	/**
	 * @return the fontSize
	 */
	public short getFontSize() {
		return fontSize;
	}

	/**
	 * @param fontSize the fontSize to set
	 */
	public void setFontSize(short fontSize) {
		this.fontSize = fontSize;
	}

	/**
	 * @return the fontBold
	 */
	public boolean isFontBold() {
		return fontBold;
	}

	/**
	 * @param fontBold
	 *            the fontBold to set
	 */
	public void setFontBold(boolean fontBold) {
		this.fontBold = fontBold;
	}

	/**
	 * @return the fontItalic
	 */
	public boolean isFontItalic() {
		return fontItalic;
	}

	/**
	 * @param fontItalic
	 *            the fontItalic to set
	 */
	public void setFontItalic(boolean fontItalic) {
		this.fontItalic = fontItalic;
	}

	/**
	 * @return the alignment
	 */
	public short getAlignment() {
		return alignment;
	}

	/**
	 * @param alignment
	 *            the alignment to set
	 */
	public void setAlignment(short alignment) {
		this.alignment = alignment;
	}

	/**
	 * @return the verticalAlignment
	 */
	public short getVerticalAlignment() {
		return verticalAlignment;
	}

	/**
	 * @param verticalAlignment
	 *            the verticalAlignment to set
	 */
	public void setVerticalAlignment(short verticalAlignment) {
		this.verticalAlignment = verticalAlignment;
	}

	/**
	 * @return the border
	 */
	public short getBorder() {
		return border;
	}

	/**
	 * @param border
	 *            the border to set
	 */
	public void setBorder(short border) {
		this.border = border;
	}

	/**
	 * @return the borderLeft
	 */
	public short getBorderLeft() {
		return borderLeft;
	}

	/**
	 * @param borderLeft
	 *            the borderLeft to set
	 */
	public void setBorderLeft(short borderLeft) {
		this.borderLeft = borderLeft;
	}

	/**
	 * @return the borderRight
	 */
	public short getBorderRight() {
		return borderRight;
	}

	/**
	 * @param borderRight
	 *            the borderRight to set
	 */
	public void setBorderRight(short borderRight) {
		this.borderRight = borderRight;
	}

	/**
	 * @return the borderTop
	 */
	public short getBorderTop() {
		return borderTop;
	}

	/**
	 * @param borderTop
	 *            the borderTop to set
	 */
	public void setBorderTop(short borderTop) {
		this.borderTop = borderTop;
	}

	/**
	 * @return the borderBottom
	 */
	public short getBorderBottom() {
		return borderBottom;
	}

	/**
	 * @param borderBottom
	 *            the borderBottom to set
	 */
	public void setBorderBottom(short borderBottom) {
		this.borderBottom = borderBottom;
	}

	/**
	 * @return the backgroundColor
	 */
	public short getBackgroundColor() {
		return backgroundColor;
	}

	/**
	 * @param backgroundColor
	 *            the backgroundColor to set
	 */
	public void setBackgroundColor(short backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	/**
	 * @return the foregroundColor
	 */
	public short getForegroundColor() {
		return foregroundColor;
	}

	/**
	 * @param foregroundColor
	 *            the foregroundColor to set
	 */
	public void setForegroundColor(short foregroundColor) {
		this.foregroundColor = foregroundColor;
	}

	/**
	 * @return the wrapText
	 */
	public boolean isWrapText() {
		return wrapText;
	}

	/**
	 * @param wrapText
	 *            the wrapText to set
	 */
	public void setWrapText(boolean wrapText) {
		this.wrapText = wrapText;
	}

}
