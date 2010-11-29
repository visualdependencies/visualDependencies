package de.visualdependencies.plugin.impl;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.util.Assert;

import de.visualdependencies.data.entity.SchemaConnection;
import de.visualdependencies.plugin.ConnectionProvider;

/**
 * Default implementation of a {@link ConnectionProvider}. Its propose is a lighter vendor-specific implementation.
 * 
 * @author Jan Philipp
 */
public class DefaultConnectionProviderImpl extends AbstractPluginImpl implements ConnectionProvider {

	protected Connection buildConnection(final SchemaConnection schemaConnection) throws SQLException {
		Assert.state(false, "This method is not implemented.");
		return null;
	}

	@Override
	public void closeConnection(final Connection connection) {
		JdbcUtils.closeConnection(connection);
	}

	@Override
	public Connection createConnection(final SchemaConnection schemaConnection) {
		try {
			final Connection connection = buildConnection(schemaConnection);
			Assert.notNull(connection, "The jdbc connection could not be created.");
			return connection;
		} catch (final SQLException e) {
			logger.warn("Connection could not be created.", e);
		}
		return null;
	}

	@Override
	public void initializeDriver() throws IllegalStateException {}

	@Override
	public boolean testConnection(final SchemaConnection schemaConnection) throws Exception {
		Connection connection = null;
		try {
			connection = buildConnection(schemaConnection);
			return true;
		} catch (final Exception e) {
			throw e;
		} finally {
			closeConnection(connection);
		}
	}

}
