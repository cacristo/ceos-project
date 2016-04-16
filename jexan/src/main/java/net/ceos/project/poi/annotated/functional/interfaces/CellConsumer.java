package net.ceos.project.poi.annotated.functional.interfaces;

@FunctionalInterface
public interface CellConsumer<C, V> {
	void apply(C cell, V value);
}
