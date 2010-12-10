/*
 * visualDependencies (visualize your database)
 * Copyright (C) 2009-2010 Andre Kasper, Jan Philipp <knallisworld@googlemail.com>
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

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
