package de.visualdependencies.plugin.mysql.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.util.Assert;

import de.visualdependencies.data.entity.Schema;
import de.visualdependencies.data.entity.SchemaTrigger;
import de.visualdependencies.plugin.DataStore;
import de.visualdependencies.plugin.helper.MetadataWorkerParameters;
import de.visualdependencies.util.translator.ConnectionDataTranslator;
import de.visualdependencies.util.translator.TriggerDataTranslator;
import edu.umd.cs.findbugs.annotations.NonNull;

public class MySqlJdbcMetadataUtil {

	private static final String SQL_STATEMENT = "action_statement";

	private static final String TRIGGER_SQL = "SELECT trigger_name, event_manipulation, definer, event_object_table, action_statement, action_timing FROM information_schema.triggers WHERE trigger_schema = '%s'";

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
		Assert.notNull(parameters.getSchema().getConnection(),
		        "The metadata worker parameters must have a valid schema connection.");

		return new MySqlJdbcMetadataUtil(parameters);
	}

	protected SchemaTrigger buildSchemaTrigger(final ResultSet rs, final DataStore dataStore) throws SQLException {
		final SchemaTrigger trigger = dataStore.createSchemaTrigger();
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
		connectionDataTranslator = ConnectionDataTranslator.create(parameters.getSchema().getConnection());
	}

	public void loadTriggers() {

		final Schema schema = parameters.getSchema();
		final Connection connection = parameters.getConnection();
		final DataStore dataStore = parameters.getDataStore();

		List<SchemaTrigger> triggers = schema.getTriggers();

		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			final String catalog = connection.getCatalog();

			if (logger.isDebugEnabled()) {
				logger.debug(String.format("Loading MySQL.Information_schema.triggers (%s)...", catalog));
			}

			pstm = connection.prepareStatement(TRIGGER_SQL);
			pstm.setString(0, catalog);
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
				triggers.add(buildSchemaTrigger(rs, dataStore));
			}

			logger.info("#loadTriggers has finished.");

		} catch (final SQLException e) {
			logger.error("#loadTriggers could not be finished.", e);
		} finally {
			JdbcUtils.closeResultSet(rs);
		}
	}
}
