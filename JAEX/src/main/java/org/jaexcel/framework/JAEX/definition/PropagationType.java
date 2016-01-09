package org.jaexcel.framework.JAEX.definition;

/**
 * This will define the propagation type when writing to the Excel. <br>
 * 
 * {@value = PropagationType.PROPAGATION_HORIZONTAL } Will propagate horizontally all the elements. <br>
 * {@value = PropagationType.PROPAGATION_VERTICAL } Will propagate vertically all the elements. <br>
 * 
 * @version 1.0
 * @author Carlos CRISTO ABREU
 */
public enum PropagationType {

	PROPAGATION_HORIZONTAL(0), PROPAGATION_VERTICAL(1);

	private int code;

	private PropagationType(int s) {
		code = s;
	}

	public int getCode() {
		return code;
	}
}
