package org.jaexcel.framework.JAEX.definition;

/**
 * This will define the title orientation type when writing into the Excel. <br>
 * 
 * {@value = TitleOrientationType.TOP } Will paint the title over the element. <br>
 * {@value = TitleOrientationType.LEFT } Will paint the title at the left of the element. <br>
 * {@value = TitleOrientationType.RIGHT } Will paint the title at the right of the element. <br>
 * {@value = TitleOrientationType.BOTTOM } Will paint the title under the element. <br>
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public enum TitleOrientationType {

	// right
	TOP(0), LEFT(1), RIGHT(2), BOTTOM(3);

	private int code;

	private TitleOrientationType(int s) {
		code = s;
	}

	public int getCode() {
		return code;
	}
}
