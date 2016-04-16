package net.ceos.project.poi.annotated.functional.interfaces;

@FunctionalInterface
public interface CellStyle<C, CS> {
	void accept(C cell, CS cellstyle);
}
