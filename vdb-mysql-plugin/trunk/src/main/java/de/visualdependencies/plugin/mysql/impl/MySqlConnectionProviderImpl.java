package de.visualdependencies.plugin.mysql.impl;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.Properties;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import de.visualdependencies.data.entity.SchemaConnection;
import de.visualdependencies.plugin.DefaultConnectionProviderImpl;
import de.visualdependencies.plugin.mysql.util.MySqlConnectionDataTranslator;

@Component("MySQL Connection Provider")
public class MySqlConnectionProviderImpl extends DefaultConnectionProviderImpl {

	protected com.mysql.jdbc.Driver buildDriver() throws SQLException {
		return new com.mysql.jdbc.Driver();
	}

	protected Connection buildConnection(SchemaConnection schemaConnection) throws SQLException {

		MySqlConnectionDataTranslator translator = MySqlConnectionDataTranslator.create(schemaConnection);

		String url = translator.getUrl();
		Assert.hasText(url, "The specified schema connection has no url set.");

		Driver driver = buildDriver();
		Connection connection = driver.connect(url, new Properties());

		Assert.notNull(connection, "The jdbc connection could not created.");

		return connection;
	}

	public void initializeDriver() throws IllegalStateException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			Assert.state(false, "No compatible MySQL driver found in classpath.");
		}
	}

}
