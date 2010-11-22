package de.visualdependencies.context;

import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class ContextUtil {

	public static GenericApplicationContext initialize() {

		final GenericXmlApplicationContext contextDatabase = new GenericXmlApplicationContext(
		        "classpath:context.xml");
		final GenericApplicationContext context = new GenericApplicationContext(contextDatabase);
		context.refresh();

		return context;
	}

}
