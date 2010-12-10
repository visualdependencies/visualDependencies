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

package de.visualdependencies.plugin;

import edu.umd.cs.findbugs.annotations.NonNull;

/**
 * A common plugin.
 * 
 * This object is a common plugin in the system. It is recommended to use a more concrete type, meaning a subtype of
 * Plugin.
 * 
 * @author Jan Philipp
 */
public interface Plugin {

	/**
	 * Checks if this plugin is compatible with the specified one. This check
	 * bases on the correct implementation of plugin's check.
	 * 
	 * @param otherPlugin
	 * @return
	 */
	boolean isCompatible(@NonNull Plugin otherPlugin);
}
