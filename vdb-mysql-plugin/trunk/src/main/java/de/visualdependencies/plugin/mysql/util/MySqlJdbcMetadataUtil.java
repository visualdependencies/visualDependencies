package de.visualdependencies.plugin.mysql.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.util.Assert;

import de.visualdependencies.data.entity.Schema;
import de.visualdependencies.data.entity.SchemaTable;
import de.visualdependencies.data.entity.SchemaTrigger;
import de.visualdependencies.data.entity.SchemaView;
import de.visualdependencies.data.entity.TableTriggerDependency;
import de.visualdependencies.plugin.DataStore;
import de.visualdependencies.plugin.helper.MetadataWorkerParameters;
import de.visualdependencies.util.translator.ConnectionDataTranslator;
import de.visualdependencies.util.translator.TableTriggerDependencyDataTranslator;
import de.visualdependencies.util.translator.TriggerDataTranslator;
import edu.umd.cs.findbugs.annotations.NonNull;

public class MySqlJdbcMetadataUtil {

	private static final String SQL_STATEMENT = "action_statement";

	private static final String TRIGGER_SQL = "SELECT trigger_name, event_manipulation, definer, event_object_table, action_statement, action_timing FROM information_schema.triggers WHERE trigger_schema = ?";

	private static final String TABLE_TRIGGER_SQL = "SHOW TRIGGERS";

	private static final String JDBC_TRIGGER_NAME = "trigger_name";

	private final Logger logger = Logger.getLogger(getClass());

	private final MetadataWorkerParameters parameters;

	@SuppressWarnings("unused")
	private final ConnectionDataTranslator connectionDataTranslator;

	/**
	 * Creates a new MySql jdbc metadata utility instance.
	 * 
	 * Note: This constructor checks if the following objects are available: connection, data store and schema.
	 * 
	 * @param parameters
	 * @return
	 */
	public static MySqlJdbcMetadataUtil createInstance(@NonNull final MetadataWorkerParameters parameters) {

		Assert.notNull(parameters, "The metadata worker parameters must be set.");
		Assert.notNull(parameters.getConnection(), "The metadata worker parameters must have a connection.");
		Assert.notNull(parameters.getDataStore(), "The metadata worker parameters must have a datastore.");
		Assert.notNull(parameters.getSchema(), "The metadata worker parameters must have a schema.");
		Assert.notNull(parameters.getSchemaConnection(),
		        "The metadata worker parameters must have a valid schema connection.");

		return new MySqlJdbcMetadataUtil(parameters);
	}

	protected SchemaTrigger buildSchemaTrigger(final ResultSet rs, final DataStore dataStore, Schema schema)
	        throws SQLException {
		final SchemaTrigger trigger = dataStore.createSchemaTrigger(schema);
		trigger.setName(rs.getString(JDBC_TRIGGER_NAME));
		TriggerDataTranslator translator = TriggerDataTranslator.create(trigger);
		translator.setSqlDefinition(rs.getString(SQL_STATEMENT));

		if (logger.isInfoEnabled()) {
			logger.info("Trigger has been built: " + trigger.getName());
		}

		return trigger;
	}

	private MySqlJdbcMetadataUtil(final MetadataWorkerParameters parameters) {
		this.parameters = parameters;
		connectionDataTranslator = ConnectionDataTranslator.create(parameters.getSchemaConnection());
	}

	public void loadTriggers() {

		final Schema schema = parameters.getSchema();
		final Connection connection = parameters.getConnection();
		final DataStore dataStore = parameters.getDataStore();

		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			final String catalog = connection.getCatalog();

			if (logger.isDebugEnabled()) {
				logger.debug(String.format("Loading MySQL.Information_schema.triggers (%s)...", catalog));
			}

			pstm = connection.prepareStatement(TRIGGER_SQL);
			pstm.setString(1, catalog);
			rs = pstm.executeQuery();

			while (rs.next()) {
				/**
				 * From the TRIGGER_SQL javadoc:
				 * 
				 * 0 trigger_name,
				 * 1 event_manipulation,
				 * 2 definer,
				 * 3 event_object_table,
				 * 4 action_statement,
				 * 5 action_timing
				 */
				SchemaTrigger trigger = buildSchemaTrigger(rs, dataStore, schema);
				dataStore.save(trigger);
			}

			logger.info("#loadTriggers has finished.");

		} catch (final SQLException e) {
			logger.error("#loadTriggers could not be finished.", e);
		} finally {
			JdbcUtils.closeResultSet(rs);
			JdbcUtils.closeStatement(pstm);
		}
	}

	public void loadTableTriggerDependencies() {

		final Schema schema = parameters.getSchema();
		final Connection connection = parameters.getConnection();
		final DataStore dataStore = parameters.getDataStore();

		List<TableTriggerDependency> dependencies = dataStore.loadTableTriggerDependencies(schema);

		// Create shortcut cache
		Map<String, SchemaTable> tables = new HashMap<String, SchemaTable>();
		for (SchemaTable table : dataStore.loadTables(schema)) {
			tables.put(table.getName(), table);
		}

		Map<String, SchemaView> views = new HashMap<String, SchemaView>();
		for (SchemaView view : dataStore.loadViews(schema)) {
			views.put(view.getName(), view);
		}

		Map<String, SchemaTrigger> triggers = new HashMap<String, SchemaTrigger>();
		for (SchemaTrigger trigger : dataStore.loadTriggers(schema)) {
			triggers.put(trigger.getName(), trigger);
		}

		CallableStatement cstm = null;
		ResultSet rs = null;

		try {
			if (logger.isDebugEnabled()) {
				logger.debug(String.format("Loading MySQL.Show_triggers..."));
			}

			cstm = connection.prepareCall(TABLE_TRIGGER_SQL);
			rs = cstm.executeQuery();

			while (rs.next()) {
				/**
				 * From the SHOW_TRIGGERS javadoc:
				 * 
				 */
				TableTriggerDependency dependency = buildTableTriggerDependency(rs, dataStore, schema, tables, views,
				        triggers);
				dataStore.save(dependency);
				dependencies.add(dependency);
			}

			logger.info("#loadTriggers has finished.");

		} catch (final SQLException e) {
			logger.error("#loadTriggers could not be finished.", e);
		} finally {
			JdbcUtils.closeResultSet(rs);
			JdbcUtils.closeStatement(cstm);
		}
	}

	protected TableTriggerDependency buildTableTriggerDependency(ResultSet rs, DataStore dataStore, Schema schema,
	        Map<String, SchemaTable> tables, Map<String, SchemaView> views, Map<String, SchemaTrigger> triggers)
	        throws SQLException {
		TableTriggerDependency dependency = dataStore.createTableTriggerDependency(schema);
		String triggerName = rs.getString("Trigger");
		String tableName = rs.getString("Table");
		dependency.setName(triggerName);

		SchemaTable table = tables.get(tableName);
		SchemaTrigger trigger = triggers.get(triggerName);
		dependency.setTable(table);
		dependency.setTrigger(trigger);

		TableTriggerDependencyDataTranslator translator = TableTriggerDependencyDataTranslator.create(dependency);
		translator.setStatement(rs.getString("Statement"));
		translator.setEvent(rs.getString("Event"));

		if (logger.isInfoEnabled()) {
			logger.info("TableTriggerDependency has been built: " + dependency.getName());
		}

		return dependency;
	}
}
