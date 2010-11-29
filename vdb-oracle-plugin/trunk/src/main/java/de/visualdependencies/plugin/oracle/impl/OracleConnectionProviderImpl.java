package de.visualdependencies.plugin.oracle.impl;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import de.visualdependencies.data.entity.SchemaConnection;
import de.visualdependencies.plugin.impl.DefaultConnectionProviderImpl;

@Component("Oracle Connection Provider")
public class OracleConnectionProviderImpl extends DefaultConnectionProviderImpl {

	protected Connection buildConnection(SchemaConnection schemaConnection) throws SQLException {
		return null;
	}

	public void initializeDriver() throws IllegalStateException {
		try {
			Class.forName("com.oracle");
		} catch (ClassNotFoundException e) {
			Assert.state(false, "No compatible Oracle driver found in classpath.");
		}
	}

}
