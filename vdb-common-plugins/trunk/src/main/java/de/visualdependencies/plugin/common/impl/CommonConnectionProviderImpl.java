package de.visualdependencies.plugin.common.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import de.visualdependencies.data.entity.SchemaConnection;
import de.visualdependencies.plugin.impl.DefaultConnectionProviderImpl;
import de.visualdependencies.util.translator.ConnectionDataTranslator;
import edu.umd.cs.findbugs.annotations.NonNull;

@Component("Common Connection Provider")
public class CommonConnectionProviderImpl extends DefaultConnectionProviderImpl {

	@NonNull
	protected Connection buildConnection(@NonNull SchemaConnection schemaConnection) throws SQLException {

		ConnectionDataTranslator translator = ConnectionDataTranslator.create(schemaConnection);
		Connection connection = null;

		String connectionType = translator.getConnectionBy();
		if ("ONLY_URL".equalsIgnoreCase(connectionType)) {
			String url = translator.getUrl();
			Assert.hasText(url, "The specified schema connection has no url set.");
			connection = DriverManager.getConnection(url);
		} else if ("URL_WITH_CREDENTIALS".equalsIgnoreCase(connectionType)) {
			String url = translator.getUrl();
			Assert.hasText(url, "The specified schema connection has no url set.");
			String username = translator.getUsername();
			Assert.hasText(username, "The specified schema connection has no username set.");
			String password = translator.getPassword();
			connection = DriverManager.getConnection(url, username, password);
		} else {
			String url = translator.getUrl();
			Assert.hasText(url, "The specified schema connection has no url set.");
			connection = DriverManager.getConnection(url);
		}

		Assert.notNull(connection, "The connection could not created.");

		return connection;

	}

}
