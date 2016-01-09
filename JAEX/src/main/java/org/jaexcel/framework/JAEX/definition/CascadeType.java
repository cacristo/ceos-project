package org.jaexcel.framework.JAEX.definition;

/**
 * This will define the cascade type when reading one attribute of the object. <br>
 * 
 * {@value = CascadeType.CASCADE_BASE } Will propagate the writing only at the main objects. <br>
 * {@value = CascadeType.CASCADE_LEVEL_ONE } Will propagate the writing at level one of the objects at the any collection. <br>
 * {@value = CascadeType.CASCADE_LEVEL_TWO } Will propagate the writing at level two at any collection of the child objects. <br>
 * {@value = CascadeType.CASCADE_FULL } Will propagate the writing fully as objects exists at any collection. <br>
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public enum CascadeType {

	CASCADE_BASE(0), CASCADE_LEVEL_ONE(1), CASCADE_LEVEL_TWO(2), CASCADE_FULL(3);

	private int code;

	private CascadeType(int s) {
		code = s;
	}

	public int getCode() {
		return code;
	}
}
