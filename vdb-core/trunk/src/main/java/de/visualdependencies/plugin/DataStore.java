package de.visualdependencies.plugin;

import java.util.List;

import de.visualdependencies.data.entity.Schema;
import de.visualdependencies.data.entity.SchemaColumn;
import de.visualdependencies.data.entity.SchemaConnection;
import de.visualdependencies.data.entity.SchemaFunction;
import de.visualdependencies.data.entity.SchemaProcedure;
import de.visualdependencies.data.entity.SchemaTable;
import de.visualdependencies.data.entity.SchemaTrigger;
import de.visualdependencies.data.entity.SchemaView;
import edu.umd.cs.findbugs.annotations.NonNull;

public interface DataStore extends Plugin {

	Schema createSchema();

	SchemaColumn createSchemaColumn();

	SchemaConnection createSchemaConnection();

	SchemaFunction createSchemaFunction();

	SchemaProcedure createSchemaProcedure();

	SchemaTable createSchemaTable();

	SchemaTrigger createSchemaTrigger();

	SchemaView createSchemaView();

	/**
	 * Returns all available schemas.
	 * 
	 * @return
	 */
	List<Schema> getAllSchemas();

	/**
	 * Saves the specified schema.
	 * 
	 * @param schema
	 * @return
	 */
	boolean saveSchema(@NonNull Schema schema);

	/**
	 * Saves the specified schema connection.
	 * 
	 * @param schemaConnection
	 * @return
	 */
	boolean saveSchemaConnection(@NonNull SchemaConnection schemaConnection);

	/**
	 * Saves the specified schema table.
	 * 
	 * @param schemaTable
	 * @return
	 */
	boolean saveSchemaTable(@NonNull SchemaTable schemaTable);

	/**
	 * Notify the data store that it has to shutdown it service. For example a database provider should save it current
	 * state.
	 */
	void shutdown();

}
