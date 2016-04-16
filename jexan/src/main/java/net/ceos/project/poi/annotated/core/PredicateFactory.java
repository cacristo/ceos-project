package net.ceos.project.poi.annotated.core;

import java.lang.reflect.Field;
import java.util.function.Predicate;

import org.apache.poi.ss.usermodel.Row;

import net.ceos.project.poi.annotated.annotation.XlsConfiguration;
import net.ceos.project.poi.annotated.annotation.XlsDecorator;
import net.ceos.project.poi.annotated.annotation.XlsDecorators;
import net.ceos.project.poi.annotated.annotation.XlsElement;
import net.ceos.project.poi.annotated.annotation.XlsFreeElement;
import net.ceos.project.poi.annotated.annotation.XlsFreezePane;
import net.ceos.project.poi.annotated.annotation.XlsGroupColumn;
import net.ceos.project.poi.annotated.annotation.XlsGroupRow;
import net.ceos.project.poi.annotated.annotation.XlsNestedHeader;
import net.ceos.project.poi.annotated.annotation.XlsSheet;
import net.ceos.project.poi.annotated.definition.ExtensionFileType;
import net.ceos.project.poi.annotated.definition.PropagationType;

public class PredicateFactory {

	/* Object annotations presents */
	protected static final Predicate<Class<?>> isAnnotationXlsSheetPresent = object -> object.isAnnotationPresent(XlsSheet.class);
	protected static final Predicate<Class<?>> isAnnotationXlsElementPresent = object -> object.isAnnotationPresent(XlsElement.class);
	protected static final Predicate<Class<?>> isAnnotationXlsDecoratorPresent = object -> object.isAnnotationPresent(XlsDecorator.class);
	protected static final Predicate<Class<?>> isAnnotationXlsDecoratorsPresent = object -> object.isAnnotationPresent(XlsDecorators.class);
	protected static final Predicate<Class<?>> isAnnotationXlsConfigurationPresent = object -> object.isAnnotationPresent(XlsConfiguration.class);

	/* Field annotations presents */
	protected static final Predicate<Field> isFieldAnnotationXlsElementPresent = object -> object.isAnnotationPresent(XlsElement.class);
	protected static final Predicate<Field> isFieldAnnotationXlsFreeElementPresent = object -> object.isAnnotationPresent(XlsFreeElement.class);
	protected static final Predicate<Field> isFieldAnnotationXlsNestedHeaderPresent = object -> object.isAnnotationPresent(XlsNestedHeader.class);
	
	/* Element validations */
	protected static final Predicate<XlsElement> isXlsElementInvalid = element -> element.position() < 1;
	protected static final Predicate<XlsFreeElement> isXlsFreeElementInvalid = freeElement -> freeElement.row() < 1
			|| freeElement.cell() < 1;
	
	/* Extension file validation */
	protected static final Predicate<ExtensionFileType> isExtensionFileDefault = extensionType -> extensionType != null 
			&& ExtensionFileType.XLS.getExtension().equals(extensionType.getExtension());

	/* Propagation file validation */
	protected static final Predicate<PropagationType> isPropagationHorizontal = PropagationType.PROPAGATION_HORIZONTAL::equals;
	
	/* XlsNestedHeader validations */
	protected static final Predicate<XlsNestedHeader> isNestedHeaderIdenticalHorizontalConfiguration = nestedHeader -> nestedHeader.startX() == nestedHeader.endX();
	protected static final Predicate<XlsNestedHeader> isNestedHeaderIdenticalVerticalConfiguration = nestedHeader -> nestedHeader.startY() == nestedHeader.endY();
	
	/* XlsFreezePane validations */
	protected static final Predicate<XlsFreezePane> isMandatoryFreezePaneValid = freezePane -> freezePane.colSplit() != -1 && freezePane.rowSplit() != -1;
	protected static final Predicate<XlsFreezePane> isOptionalFieldsFreezePaneIgnored = freezePane -> freezePane.leftMostColumn() == 0 && freezePane.topRow() == 0;
	protected static final Predicate<XlsFreezePane> isOptionalFieldsFreezePaneActived = freezePane -> freezePane.leftMostColumn() != 0 || freezePane.topRow() != 0;
	
	/* GroupElement validations */
	protected static final Predicate<XlsGroupColumn> isGroupColumnValid = column -> column.fromColumn() != 0 || column.toColumn() != 0;
	protected static final Predicate<XlsGroupRow> isGroupRowValid = row -> row.fromRow() != 0 || row.toRow() != 0;

	/* Row validations */
	protected static final Predicate<Row> isRowValid = row -> row != null;
	
	
	private PredicateFactory() {
		/* private constructor to hide the implicit public */
	}
}
