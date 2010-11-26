package de.visualdependencies.context;

import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * The utility class for Spring context management.
 * 
 * @author Jan Philipp
 */
final public class ContextUtil {

	/**
	 * Creates and return a new Spring context object.
	 * 
	 * This creates a complete new context including data abstraction layer and plugin management. All plugins defined
	 * in the application's package will recognized.
	 * 
	 * @return applicationContext
	 */
	public static GenericApplicationContext initialize() {

		// Creating the context for database configuration.
		final GenericXmlApplicationContext contextDatabase = new GenericXmlApplicationContext(
		        "classpath:context-database.xml");
		final GenericApplicationContext context = new GenericApplicationContext(contextDatabase);
		context.refresh();

		return context;
	}

}
