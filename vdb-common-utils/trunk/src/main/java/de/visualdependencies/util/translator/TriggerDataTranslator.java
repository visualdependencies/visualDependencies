package de.visualdependencies.util.translator;

import de.visualdependencies.data.entity.SchemaTrigger;
import edu.umd.cs.findbugs.annotations.NonNull;

/**
 * Trigger data map translator.
 * 
 * This utility class provides type-safe access to trigger properties.
 * 
 * @author Jan Philipp
 */
public class TriggerDataTranslator extends AbstractDataTranslator {

	private static final String SQL_DEFINITION = "sqlDefinition";

	public static TriggerDataTranslator create(@NonNull final SchemaTrigger trigger) {
		return new TriggerDataTranslator(trigger);
	}

	protected TriggerDataTranslator(@NonNull final SchemaTrigger trigger) {
		super(trigger.getData());
	}

	public String getSqlDefinition() {
		return getString(SQL_DEFINITION);
	}

	public void setSqlDefinition(final String value) {
		setString(SQL_DEFINITION, value);
	}

}
