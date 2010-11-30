package de.visualdependencies.util.translator;

import de.visualdependencies.data.entity.TableTriggerDependency;
import edu.umd.cs.findbugs.annotations.NonNull;

/**
 * Column data map translator.
 * 
 * This utility class provides type-safe access to column properties.
 * 
 * @author Jan Philipp
 */
public class TableTriggerDependencyDataTranslator extends AbstractDataTranslator {

	private static final String EVENT = "statement";

	private static final String STATEMENT = "statement";

	public static TableTriggerDependencyDataTranslator create(@NonNull final TableTriggerDependency dependency) {
		return new TableTriggerDependencyDataTranslator(dependency);
	}

	protected TableTriggerDependencyDataTranslator(@NonNull final TableTriggerDependency dependency) {
		super(dependency.getData());
	}

	public String getEvent() {
		return getString(EVENT);
	}

	public String getStatement() {
		return getString(STATEMENT);
	}

	public void setEvent(final String value) {
		setString(EVENT, value);
	}

	public void setStatement(final String value) {
		setString(STATEMENT, value);
	}

}
