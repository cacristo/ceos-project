package org.jaexcel.framework.JAEX.test;

public enum EnumTest {

	CASCADE_BASE(0), CASCADE_LEVEL_ONE(1), CASCADE_LEVEL_TWO(2), CASCADE_FULL(3);

	private int code;

	private EnumTest(int s) {
		code = s;
	}

	public int getCode() {
		return code;
	}
}
