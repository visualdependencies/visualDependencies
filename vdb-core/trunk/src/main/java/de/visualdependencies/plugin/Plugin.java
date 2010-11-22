package de.visualdependencies.plugin;

public interface Plugin {

	/**
	 * Checks if this plugin is compatible with the specified one. This check
	 * bases on the correct implementation of plugin's check.
	 * 
	 * @param otherPlugin
	 * @return
	 */
	boolean isCompatible(Plugin otherPlugin);
}
