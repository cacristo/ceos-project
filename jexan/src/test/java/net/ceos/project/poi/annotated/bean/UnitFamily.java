package net.ceos.project.poi.annotated.bean;

public enum UnitFamily {
	COMPUTER("Computer"),
	TABLETS("Tablets"),
	COMPONENTS("Components"),
	STORAGE("Storage"),
	NETWORK("Network"),
	SOFTWARE("Software");

	private String family;

	private UnitFamily(String s) {
		family = s;
	}

	public String getFamily() {
		return family;
	}
}
