package net.ceos.project.poi.annotated.functional.interfaces;

@FunctionalInterface
public interface PoiConstructor<I, O> {
	O newInstance(I input);
}
