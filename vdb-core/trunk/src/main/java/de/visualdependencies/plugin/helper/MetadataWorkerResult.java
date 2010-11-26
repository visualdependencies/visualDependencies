package de.visualdependencies.plugin.helper;

import java.util.Date;

final public class MetadataWorkerResult {

	public static MetadataWorkerResult create() {
		return new MetadataWorkerResult();
	}

	private long startedTime;

	private long usedTime = 0;

	private MetadataWorkerResult() {}

	/**
	 * Returns the current used time.
	 * 
	 * Note: Open marks which are not closed by {@link #markEnd()} will be ignored.
	 * 
	 * @return the used time in milliseconds.
	 */
	public long getUsedTime() {
		return usedTime;
	}

	/**
	 * Marks the current time as end for time usage calculation.
	 * 
	 * This computes the actual used time between the last call of {@link #markStart()} and now. The result is available
	 * under {@link #getUsedTime()}.
	 * 
	 * Multiple {@link #markStart()} and {@link #markEnd()} combinations are allowed, but not mixed.
	 */
	public void markEnd() {
		// If startedTime is still zero, markStart was never called before.
		if (startedTime > 0) {
			// Adds(!) the used time.
			usedTime += new Date().getTime() - startedTime;
			startedTime = 0;
		}
	}

	/**
	 * Marks the current time as start for time usage calculation.
	 * 
	 * This stores internally the current time. A later call of {@link #markEnd()} computes the difference and provides
	 * the result under {@link #getUsedTime()}.
	 * 
	 * Multiple {@link #markStart()} and {@link #markEnd()} combinations are allowed, but not mixed.
	 */
	public void markStart() {
		startedTime = new Date().getTime();
	}

}
