package de.visualdependencies.util.translator;

import de.visualdependencies.data.entity.SchemaColumn;
import edu.umd.cs.findbugs.annotations.NonNull;

/**
 * Column data map translator.
 * 
 * This utility class provides type-safe access to column properties.
 * 
 * @author Jan Philipp
 */
public class ColumnDataTranslator extends AbstractDataTranslator {

	private static final String DATA_TYPE = "dataType";

	public static ColumnDataTranslator create(@NonNull final SchemaColumn column) {
		return new ColumnDataTranslator(column);
	}

	protected ColumnDataTranslator(@NonNull final SchemaColumn column) {
		super(column.getData());
	}

	public Integer getDataType() {
		return getInteger(DATA_TYPE);
	}

	public void setDataType(final Integer value) {
		setInteger(DATA_TYPE, value);
	}

}
