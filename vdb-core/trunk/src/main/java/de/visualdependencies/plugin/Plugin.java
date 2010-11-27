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
