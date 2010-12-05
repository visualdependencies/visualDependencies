package de.visualdependencies.plugin.mysql.impl;

import java.sql.Connection;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import de.visualdependencies.data.entity.SchemaConnection;
import de.visualdependencies.plugin.ConnectionProvider;
import de.visualdependencies.plugin.MetadataWorker;
import de.visualdependencies.plugin.Plugin;
import de.visualdependencies.plugin.helper.MetadataWorkerParameters;
import de.visualdependencies.plugin.helper.MetadataWorkerResult;
import de.visualdependencies.plugin.mysql.util.MySqlJdbcMetadataUtil;
import de.visualdependencies.util.jdbc.JdbcMetadataUtil;

@Component("MySQL Metadata Worker")
@Transactional
public class MysqlMetadataWorkerImpl extends AbstractMysqlPluginImpl implements MetadataWorker {

	@Override
	public boolean isCompatible(Plugin otherPlugin) {
		// Accept the common connection provider plugin.
		if (otherPlugin instanceof de.visualdependencies.plugin.common.impl.CommonConnectionProviderImpl) { return true; }
		return super.isCompatible(otherPlugin);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public MetadataWorkerResult loadModel(MetadataWorkerParameters parameters) {

		// Create the result object. Both parameters and result live in this call.
		MetadataWorkerResult result = MetadataWorkerResult.create();
		result.markStart();

		ConnectionProvider connectionProvider = parameters.getConnectionProvider();
		SchemaConnection schemaConnection = parameters.getSchemaConnection();

		// Create a real JDBC connection.
		connectionProvider.initializeDriver();
		Connection connection = connectionProvider.createConnection(schemaConnection);
		parameters.setConnection(connection);

		JdbcMetadataUtil jdbcMetadataUtil = JdbcMetadataUtil.createInstance(parameters);
		MySqlJdbcMetadataUtil mysqlJdbcMetadataUtil = MySqlJdbcMetadataUtil.createInstance(parameters);

		jdbcMetadataUtil.loadTables();
		jdbcMetadataUtil.loadColumns();
		mysqlJdbcMetadataUtil.loadTriggers();
		mysqlJdbcMetadataUtil.loadTableTriggerDependencies();

		result.markEnd();

		return result;
	}

}
