package de.visualdependencies.plugin;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.util.Assert;

import de.visualdependencies.data.entity.SchemaConnection;
import de.visualdependencies.plugin.impl.AbstractPluginImpl;

public class DefaultConnectionProviderImpl extends AbstractPluginImpl implements ConnectionProvider {

	protected Connection buildConnection(final SchemaConnection schemaConnection) throws SQLException {
		Assert.state(false, "This method is not implemented.");
		return null;
	}

	@Override
	public void closeConnection(final Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (final SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Connection createConnection(final SchemaConnection schemaConnection) {
		try {
			final Connection connection = buildConnection(schemaConnection);
			Assert.notNull(connection, "The jdbc connection could not created.");
			return connection;
		} catch (final SQLException e) {
			logger.warn("Connection could not created.", e);
		}
		return null;
	}

	@Override
	public void initializeDriver() throws IllegalStateException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean testConnection(final SchemaConnection schemaConnection) {
		try {
			closeConnection(buildConnection(schemaConnection));
			return true;
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
