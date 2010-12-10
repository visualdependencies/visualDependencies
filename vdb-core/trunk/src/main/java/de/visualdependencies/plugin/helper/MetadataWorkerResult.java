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
