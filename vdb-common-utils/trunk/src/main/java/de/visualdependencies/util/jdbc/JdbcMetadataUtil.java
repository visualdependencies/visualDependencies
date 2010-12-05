package de.visualdependencies.util.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.util.Assert;

import de.visualdependencies.data.entity.Schema;
import de.visualdependencies.data.entity.SchemaTable;
import de.visualdependencies.data.entity.SchemaTableColumn;
import de.visualdependencies.data.entity.SchemaView;
import de.visualdependencies.data.entity.SchemaViewColumn;
import de.visualdependencies.plugin.DataStore;
import de.visualdependencies.plugin.helper.MetadataWorkerParameters;
import de.visualdependencies.util.translator.ColumnDataTranslator;
import de.visualdependencies.util.translator.ConnectionDataTranslator;
import edu.umd.cs.findbugs.annotations.NonNull;

final public class JdbcMetadataUtil {

	private static final String JDBC_TABLE_TYPE = "TABLE_TYPE";

	private static final String JDBC_DATA_TYPE = "DATA_TYPE";

	private static final String JDBC_COLUMN_NAME = "COLUMN_NAME";

	private static final String JDBC_TABLE_NAME = "TABLE_NAME";

	private static final String JDBC_WILDCARD = "%";

	private static final String JDBC_TABLE = "TABLE";

	private static final String JDBC_VIEW = "VIEW";

	/**
	 * Creates a new jdbc metadata utility instance.
	 * 
	 * Note: This constructor checks if the following objects are available: connection, data store and schema.
	 * 
	 * @param parameters
	 * @return
	 */
	public static JdbcMetadataUtil createInstance(@NonNull final MetadataWorkerParameters parameters) {

		Assert.notNull(parameters, "The metadata worker parameters must be set.");
		Assert.notNull(parameters.getConnection(), "The metadata worker parameters must have a connection.");
		Assert.notNull(parameters.getDataStore(), "The metadata worker parameters must have a datastore.");
		Assert.notNull(parameters.getSchema(), "The metadata worker parameters must have a schema.");
		Assert.notNull(parameters.getSchemaConnection(),
		        "The metadata worker parameters must have a valid schema connection.");

		return new JdbcMetadataUtil(parameters);
	}

	private final Logger logger = Logger.getLogger(getClass());

	private final MetadataWorkerParameters parameters;

	private final ConnectionDataTranslator connectionDataTranslator;

	private JdbcMetadataUtil(final MetadataWorkerParameters parameters) {
		this.parameters = parameters;
		connectionDataTranslator = ConnectionDataTranslator.create(parameters.getSchemaConnection());
	}

	protected SchemaTable buildSchemaTable(final ResultSet rs, final DataStore dataStore, final Schema schema)
	        throws SQLException {
		final SchemaTable table = dataStore.createSchemaTable(schema);
		table.setName(rs.getString(JDBC_TABLE_NAME));

		if (logger.isInfoEnabled()) {
			logger.info("Table has been built: " + table.getName());
		}

		return table;
	}

	protected SchemaTableColumn buildSchemaTableColumn(final ResultSet rs, final DataStore dataStore,
	        final SchemaTable table)
	        throws SQLException {
		final SchemaTableColumn column = dataStore.createSchemaTableColumn(table);
		column.setName(rs.getString(JDBC_COLUMN_NAME));

		final ColumnDataTranslator translator = ColumnDataTranslator.create(column);
		translator.setDataType(rs.getInt(JDBC_DATA_TYPE));

		if (logger.isInfoEnabled()) {
			logger.info("Column has been built: " + column.getName());
		}

		return column;
	}

	protected SchemaView buildSchemaView(final ResultSet rs, final DataStore dataStore, final Schema schema)
	        throws SQLException {
		final SchemaView view = dataStore.createSchemaView(schema);
		view.setName(rs.getString(JDBC_TABLE_NAME));

		if (logger.isInfoEnabled()) {
			logger.info("View has been built: " + view.getName());
		}

		return view;
	}

	protected SchemaViewColumn buildSchemaViewColumn(final ResultSet rs, final DataStore dataStore,
	        final SchemaView view) throws SQLException {
		final SchemaViewColumn column = dataStore.createSchemaViewColumn(view);
		column.setName(rs.getString(JDBC_COLUMN_NAME));

		final ColumnDataTranslator translator = ColumnDataTranslator.create(column);
		translator.setDataType(rs.getInt(JDBC_DATA_TYPE));

		if (logger.isInfoEnabled()) {
			logger.info("Column has been built: " + column.getName());
		}

		return column;
	}

	/**
	 * Loads all columns of the specified connection. All objects are stored in the schema.
	 * 
	 * This implementation uses {@link DatabaseMetaData#getColumns(String, String, String, String)}.
	 */
	public void loadColumns() {

		final Schema schema = parameters.getSchema();
		final Connection connection = parameters.getConnection();
		final DataStore dataStore = parameters.getDataStore();

		// Build up internal shortcut cache.
		final Map<String, SchemaTable> tables = new HashMap<String, SchemaTable>();
		final Map<String, SchemaView> views = new HashMap<String, SchemaView>();
		for (final SchemaTable table : dataStore.loadTables(schema)) {
			tables.put(table.getName(), table);
		}
		for (final SchemaView view : dataStore.loadViews(schema)) {
			views.put(view.getName(), view);
		}

		final List<SchemaTableColumn> tableColumns = new ArrayList<SchemaTableColumn>();
		final List<SchemaViewColumn> viewColumns = new ArrayList<SchemaViewColumn>();

		ResultSet rs = null;
		try {
			final DatabaseMetaData metadata = connection.getMetaData();

			final String catalog = connection.getCatalog();
			final String schemaPattern = connectionDataTranslator.getSchema();
			final String tableNamePattern = JDBC_WILDCARD; // all tables
			final String columnNamePattern = JDBC_WILDCARD; // all tables

			if (logger.isDebugEnabled()) {
				logger.debug(String.format("Loading DatabaseMetadata#getColumns(%s, %s, %s, %s)...", catalog,
				        schemaPattern, tableNamePattern, columnNamePattern));
			}

			rs = metadata.getColumns(catalog, schemaPattern, tableNamePattern, columnNamePattern);

			while (rs.next()) {
				/**
				 * From the DatabaseMetadata#getColumns() javadoc:
				 * 
				 * TABLE_CAT String => table catalog (may be null)
				 * TABLE_SCHEM String => table schema (may be null)
				 * TABLE_NAME String => table name
				 * COLUMN_NAME String => column name
				 * DATA_TYPE int => SQL type from java.sql.Types
				 * TYPE_NAME String => Data source dependent type name, for a UDT the type name is fully qualified
				 * COLUMN_SIZE int => column size.
				 * BUFFER_LENGTH is not used.
				 * DECIMAL_DIGITS int => the number of fractional digits. Null is returned for data types where
				 * DECIMAL_DIGITS is not applicable.
				 * NUM_PREC_RADIX int => Radix (typically either 10 or 2)
				 * NULLABLE int => is NULL allowed.
				 * columnNoNulls - might not allow NULL values
				 * columnNullable - definitely allows NULL values
				 * columnNullableUnknown - nullability unknown
				 * REMARKS String => comment describing column (may be null)
				 * COLUMN_DEF String => default value for the column, which should be interpreted as a string when the
				 * value is enclosed in single quotes (may be null)
				 * SQL_DATA_TYPE int => unused
				 * SQL_DATETIME_SUB int => unused
				 * CHAR_OCTET_LENGTH int => for char types the maximum number of bytes in the column
				 * ORDINAL_POSITION int => index of column in table (starting at 1)
				 * IS_NULLABLE String => ISO rules are used to determine the nullability for a column.
				 * YES --- if the parameter can include NULLs
				 * NO --- if the parameter cannot include NULLs
				 * empty string --- if the nullability for the parameter is unknown
				 * SCOPE_CATLOG String => catalog of table that is the scope of a reference attribute (null if DATA_TYPE
				 * isn't REF)
				 * SCOPE_SCHEMA String => schema of table that is the scope of a reference attribute (null if the
				 * DATA_TYPE isn't REF)
				 * SCOPE_TABLE String => table name that this the scope of a reference attribure (null if the DATA_TYPE
				 * isn't REF)
				 * SOURCE_DATA_TYPE short => source type of a distinct type or user-generated Ref type, SQL type from
				 * java.sql.Types (null if DATA_TYPE isn't DISTINCT or user-generated REF)
				 * IS_AUTOINCREMENT String => Indicates whether this column is auto incremented
				 * YES --- if the column is auto incremented
				 * NO --- if the column is not auto incremented
				 * empty string --- if it cannot be determined whether the column is auto incremented parameter is
				 * unknown
				 */
				final String tableName = rs.getString(JDBC_TABLE_NAME);
				final String columnName = rs.getString(JDBC_COLUMN_NAME);
				final Integer columnType = rs.getInt(JDBC_DATA_TYPE);
				if (tables.containsKey(tableName)) {
					tableColumns.add(buildSchemaTableColumn(rs, dataStore, tables.get(tableName)));
				} else if (views.containsKey(tableName)) {
					viewColumns.add(buildSchemaViewColumn(rs, dataStore, views.get(tableName)));
				} else {
					if (logger.isDebugEnabled()) {
						logger.debug(String.format(
						        "Column object [table=\"%s\", name=\"%s\", type=\"%s\"] will be ignored.",
						        tableName, columnName, columnType));
					}
				}
			}

			logger.info("DatabaseMetadata#getColumns has finished.");

		} catch (final SQLException e) {
			logger.error("DatabaseMetadata#getColumns could not be finished.", e);
		} finally {
			JdbcUtils.closeResultSet(rs);
		}

		logger.info("Saving table columns...");
		for (final SchemaTableColumn column : tableColumns) {
			dataStore.save(column);
		}

		logger.info("Saving view columns...");
		for (final SchemaViewColumn column : viewColumns) {
			dataStore.save(column);
		}
	}

	/**
	 * Loads all tables and views of the specified connection. All objects are stored in the schema.
	 * 
	 * This implementation uses {@link DatabaseMetaData#getTables(String, String, String, String[])}.
	 */
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
			final String schemaPattern = connectionDataTranslator.getSchema();
			final String tableNamePattern = JDBC_WILDCARD; // all tables
			final String[] types = new String[] { JDBC_TABLE, JDBC_VIEW }; // only tables

			if (logger.isDebugEnabled()) {
				logger.debug(String.format("Loading DatabaseMetadata#getTables(%s, %s, %s, %s)...", catalog,
				        schemaPattern, tableNamePattern, Arrays.toString(types)));
			}

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
				final String tableName = rs.getString(JDBC_TABLE_NAME);
				final String tableType = rs.getString(JDBC_TABLE_TYPE);
				if (JDBC_TABLE.equals(tableType)) {
					final SchemaTable table = buildSchemaTable(rs, dataStore, schema);
					dataStore.save(table);
					tables.add(table);
				} else if (JDBC_VIEW.equals(tableType)) {
					final SchemaView view = buildSchemaView(rs, dataStore, schema);
					dataStore.save(view);
					views.add(view);
				} else {
					if (logger.isDebugEnabled()) {
						logger.debug(String.format("Table object [name=\"%s\", type=\"%s\"] will be ignored.",
						        tableName, tableType));
					}
				}
			}

			logger.info("DatabaseMetadata#getTables has finished.");

		} catch (final SQLException e) {
			logger.error("DatabaseMetadata#getTables could not be finished.", e);
		} finally {
			JdbcUtils.closeResultSet(rs);
		}
	}
}
