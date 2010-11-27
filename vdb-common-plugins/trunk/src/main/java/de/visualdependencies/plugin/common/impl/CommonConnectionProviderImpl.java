package de.visualdependencies.plugin.common.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import de.visualdependencies.data.entity.SchemaConnection;
import de.visualdependencies.plugin.DefaultConnectionProviderImpl;
import de.visualdependencies.plugin.common.util.CommonConnectionDataTranslator;
import edu.umd.cs.findbugs.annotations.NonNull;

@Component("Common Connection Provider")
public class CommonConnectionProviderImpl extends DefaultConnectionProviderImpl {

	@NonNull
	protected Connection buildConnection(@NonNull SchemaConnection schemaConnection) throws SQLException {

		CommonConnectionDataTranslator translator = CommonConnectionDataTranslator.create(schemaConnection);

		String url = translator.getUrl();
		Assert.hasText(url, "The specified schema connection has no url set.");

		Connection connection = DriverManager.getConnection(url);
		Assert.notNull(connection, "The connection could not created.");

		return connection;

	}

}
