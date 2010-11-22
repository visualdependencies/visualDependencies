package de.visualdependencies.plugin.oracle.impl;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import de.visualdependencies.data.entity.SchemaConnection;
import de.visualdependencies.plugin.ConnectionProvider;

@Component("Oracle Connection Provider")
public class ConnectionProviderImpl extends AbstractOraclePluginImpl implements ConnectionProvider {

	public void closeConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	protected Connection buildConnection(SchemaConnection schemaConnection) {
		return null;
	}

	public Connection createConnection(SchemaConnection schemaConnection) {
		return buildConnection(schemaConnection);
	}

	public void initializeDriver() throws IllegalStateException {
		try {
			Class.forName("com.oracle.OJDBC");
		} catch (ClassNotFoundException e) {
			Assert.state(false, "No compatible driver found in classpath.");
		}
	}

	public boolean testConnection(SchemaConnection schemaConnection) {
		try {
			closeConnection(buildConnection(schemaConnection));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
