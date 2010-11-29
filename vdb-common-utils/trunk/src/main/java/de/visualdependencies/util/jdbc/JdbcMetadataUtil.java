package de.visualdependencies.util.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.util.Assert;

import de.visualdependencies.data.entity.Schema;
import de.visualdependencies.data.entity.SchemaTable;
import de.visualdependencies.data.entity.SchemaView;
import de.visualdependencies.plugin.DataStore;
import de.visualdependencies.plugin.helper.MetadataWorkerParameters;
import edu.umd.cs.findbugs.annotations.NonNull;

final public class JdbcMetadataUtil {

	/**
	 * Creates a new jdbc metadata utility instance.
	 * 
	 *  Note: This constructor checks if the following objects are available: connection, data store and schema.
	 *  
	 * @param parameters
	 * @return
	 */
	public static JdbcMetadataUtil createInstance(@NonNull final MetadataWorkerParameters parameters) {

		Assert.notNull(parameters, "The metadata worker parameters must be set.");
		Assert.notNull(parameters.getConnection(), "The metadata worker parameters must have a connection.");
		Assert.notNull(parameters.getDataStore(), "The metadata worker parameters must have a datastore.");
		Assert.notNull(parameters.getSchema(), "The metadata worker parameters must have a schema.");

		return new JdbcMetadataUtil(parameters);
	}

	private final Logger logger = Logger.getLogger(getClass());

	private final MetadataWorkerParameters parameters;

	private JdbcMetadataUtil(final MetadataWorkerParameters parameters) {
		this.parameters = parameters;
	}

	protected SchemaTable buildSchemaTable(final ResultSet rs, final DataStore dataStore) throws SQLException {
		final SchemaTable table = dataStore.createSchemaTable();
		table.setName(rs.getString("TABLE_NAME"));

		if (logger.isInfoEnabled()) {
			logger.info("Table built: " + table.getName());
		}

		return table;
	}

	protected SchemaView buildSchemaView(final ResultSet rs, final DataStore dataStore) throws SQLException {
		final SchemaView view = dataStore.createSchemaView();
		view.setName(rs.getString("TABLE_NAME"));

		if (logger.isInfoEnabled()) {
			logger.info("View built: " + view.getName());
		}

		return view;
	}

	public void loadTables() {

		final Schema schema = parameters.getSchema();

		final Connection connection = parameters.getConnection();
		final DataStore dataStore = parameters.getDataStore();

		final List<SchemaTable> tables = new ArrayList<SchemaTable>();
		final List<SchemaView> views = new ArrayList<SchemaView>();

		ResultSet rs = null;
		try {
			final DatabaseMetaData metadata = connection.getMetaData();

			final String catalog = connection.getCatalog();
			final String schemaPattern = parameters.getSchema().getConnection().getData().get("schema");
			final String tableNamePattern = "%"; // all tables
			final String[] types = new String[] { "TABLE", "VIEW" }; // only tables

			rs = metadata.getTables(catalog, schemaPattern, tableNamePattern, types);

			while (rs.next()) {
				/**
				 * From the DatabaseMetadata#getTables() javadoc:
				 * 
				 * TABLE_CAT String => table catalog (may be null)
				 * TABLE_SCHEM String => table schema (may be null)
				 * TABLE_NAME String => table name
				 * TABLE_TYPE String => table type. Typical types are "TABLE", "VIEW", "SYSTEM TABLE",
				 * "GLOBAL TEMPORARY", "LOCAL TEMPORARY", "ALIAS", "SYNONYM".
				 * REMARKS String => explanatory comment on the table
				 * TYPE_CAT String => the types catalog (may be null)
				 * TYPE_SCHEM String => the types schema (may be null)
				 * TYPE_NAME String => type name (may be null)
				 * SELF_REFERENCING_COL_NAME String => name of the designated "identifier" column of a typed table (may
				 * be null)
				 * REF_GENERATION String => specifies how values in SELF_REFERENCING_COL_NAME are created. Values are
				 * "SYSTEM", "USER", "DERIVED". (may be null)
				 */
				final String tableName = rs.getString("TABLE_NAME");
				final String tableType = rs.getString("TABLE_TYPE");
				if ("TABLE".equals(tableType)) {
					tables.add(buildSchemaTable(rs, dataStore));
				} else if ("VIEW".equals(tableType)) {
					views.add(buildSchemaView(rs, dataStore));
				} else {
					if (logger.isDebugEnabled()) {
						logger.debug(String.format("Table object [name=\"%s\", type=\"%s\"] will be ignored.",
						        tableName, tableType));
					}
				}
			}

			schema.setTables(tables);
			schema.setViews(views);
			logger.info("SQL getTables finished.");
		} catch (final SQLException e) {
			logger.error("SQL getTables could not be finished.", e);
		} finally {
			JdbcUtils.closeResultSet(rs);
		}
	}
}
