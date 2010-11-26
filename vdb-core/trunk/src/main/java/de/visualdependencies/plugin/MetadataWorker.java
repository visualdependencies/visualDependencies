package de.visualdependencies.plugin;

import de.visualdependencies.plugin.helper.MetadataWorkerParameters;
import de.visualdependencies.plugin.helper.MetadataWorkerResult;
import edu.umd.cs.findbugs.annotations.NonNull;

public interface MetadataWorker extends Plugin {

	/**
	 * Loads the model from the specified parameters and persists all data.
	 * 
	 * This will create a JDBC connection, load all objects and store the data via the provided data store.
	 * 
	 * @param parameters
	 */
	@NonNull
	MetadataWorkerResult loadModel(@NonNull MetadataWorkerParameters parameters);

}
