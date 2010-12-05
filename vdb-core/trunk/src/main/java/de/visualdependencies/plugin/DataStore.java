package de.visualdependencies.plugin;

import java.util.List;

import de.visualdependencies.data.entity.Schema;
import de.visualdependencies.data.entity.SchemaConnection;
import de.visualdependencies.data.entity.SchemaFunction;
import de.visualdependencies.data.entity.SchemaProcedure;
import de.visualdependencies.data.entity.SchemaTable;
import de.visualdependencies.data.entity.SchemaTableColumn;
import de.visualdependencies.data.entity.SchemaTrigger;
import de.visualdependencies.data.entity.SchemaView;
import de.visualdependencies.data.entity.SchemaViewColumn;
import de.visualdependencies.data.entity.TableTriggerDependency;
import edu.umd.cs.findbugs.annotations.NonNull;

/**
 * Data storage interface.
 * 
 * @author Jan Philipp
 */
public interface DataStore extends Plugin {

	/**
	 * Returns a transient {@link Schema} entity.
	 * 
	 * @return
	 */
	Schema createSchema();

	/**
	 * Returns a transient {@link SchemaConnection} entity.
	 * 
	 * @return
	 */
	SchemaConnection createSchemaConnection();

	/**
	 * Returns a transient {@link SchemaFunction} entity.
	 * 
	 * @param schema
	 * @return
	 */
	SchemaFunction createSchemaFunction(Schema schema);

	/**
	 * Returns a transient {@link SchemaProcedure} entity.
	 * 
	 * @param schema
	 * @return
	 */
	SchemaProcedure createSchemaProcedure(Schema schema);

	/**
	 * Returns a transient {@link SchemaTable} entity.
	 * 
	 * @param schema
	 * @return
	 */
	SchemaTable createSchemaTable(Schema schema);

	/**
	 * Returns a transient {@link SchemaTableColumn} entity.
	 * 
	 * @param table
	 * @return
	 */
	SchemaTableColumn createSchemaTableColumn(SchemaTable table);

	/**
	 * Returns a transient {@link SchemaTrigger} entity.
	 * 
	 * @param schema
	 * @return
	 */
	SchemaTrigger createSchemaTrigger(Schema schema);

	/**
	 * Returns a transient {@link SchemaView} entity.
	 * 
	 * @param schema
	 * @return
	 */
	SchemaView createSchemaView(Schema schema);

	/**
	 * Returns a transient {@link SchemaViewColumn} entity.
	 * 
	 * @param view
	 * @return
	 */
	SchemaViewColumn createSchemaViewColumn(SchemaView view);

	/**
	 * Returns a transient {@link TableTriggerDependency} entity.
	 * 
	 * @param schema
	 * @return
	 */
	TableTriggerDependency createTableTriggerDependency(Schema schema);

	/**
	 * Returns all available {@link Schema} entities.
	 * 
	 * @return
	 */
	List<Schema> getAllSchemas();

	/**
	 * Loads and returns the {@link SchemaTableColumn}s of the specified {@link SchemaTable}.
	 * 
	 * @param table
	 * @return
	 */
	List<SchemaTableColumn> loadColumns(SchemaTable table);

	/**
	 * Loads and returns the {@link SchemaViewColumn}s of the specified {@link SchemaView}.
	 * 
	 * @param view
	 * @return
	 */
	List<SchemaViewColumn> loadColumns(SchemaView view);

	/**
	 * Loads and returns the {@link SchemaConnection} of the specified {@link Schema}.
	 * 
	 * @param schema
	 * @return
	 */
	SchemaConnection loadConnection(Schema schema);

	/**
	 * Loads and returns the {@link SchemaFunction}s of the specified {@link Schema}.
	 * 
	 * @param schema
	 * @return
	 */
	List<SchemaFunction> loadFunctions(Schema schema);

	/**
	 * Loads and returns the {@link SchemaProcedure}s of the specified {@link Schema}.
	 * 
	 * @param schema
	 * @return
	 */
	List<SchemaProcedure> loadProcedures(Schema schema);

	/**
	 * Loads and returns the {@link SchemaTable}s of the specified {@link Schema}.
	 * 
	 * @param schema
	 * @return
	 */
	List<SchemaTable> loadTables(Schema schema);

	/**
	 * Loads and returns the {@link TableTriggerDependency} of the specified {@link Schema}.
	 * 
	 * @param schema
	 * @return
	 */
	List<TableTriggerDependency> loadTableTriggerDependencies(Schema schema);

	/**
	 * Loads and returns the {@link SchemaTrigger}s of the specified {@link Schema}.
	 * 
	 * @param schema
	 * @return
	 */
	List<SchemaTrigger> loadTriggers(Schema schema);

	/**
	 * Loads and returns the {@link SchemaView}s of the specified {@link Schema}.
	 * 
	 * @param schema
	 * @return
	 */
	List<SchemaView> loadViews(Schema schema);

	/**
	 * Saves or updates the specified {@link Schema}.
	 * 
	 * @param schema
	 * @return
	 */
	boolean save(@NonNull Schema schema);

	/**
	 * Saves or updates the specified {@link SchemaConnection}.
	 * 
	 * @param schemaConnection
	 * @return
	 */
	boolean save(@NonNull SchemaConnection schemaConnection);

	/**
	 * Saves or updates the specified {@link SchemaTable}.
	 * 
	 * @param table
	 * @return
	 */
	boolean save(@NonNull SchemaTable table);

	/**
	 * Saves or updates the specified {@link SchemaTableColumn}.
	 * 
	 * @param column
	 * @return
	 */
	boolean save(@NonNull SchemaTableColumn column);

	/**
	 * Saves or updates the specified {@link SchemaTrigger}.
	 * 
	 * @param trigger
	 * @return
	 */
	void save(SchemaTrigger trigger);

	/**
	 * Saves or updates the specified {@link SchemaView}.
	 * 
	 * @param view
	 * @return
	 */
	boolean save(@NonNull SchemaView view);

	/**
	 * Saves or updates the specified {@link SchemaViewColumn}.
	 * 
	 * @param column
	 * @return
	 */
	boolean save(@NonNull SchemaViewColumn column);

	/**
	 * Saves or updates the specified {@link TableTriggerDependency}.
	 * 
	 * @param dependency
	 */
	boolean save(TableTriggerDependency dependency);

	/**
	 * Notifies the data store that it has to shutdown it service. For example a database provider could save it current
	 * state.
	 */
	void shutdown();

}
