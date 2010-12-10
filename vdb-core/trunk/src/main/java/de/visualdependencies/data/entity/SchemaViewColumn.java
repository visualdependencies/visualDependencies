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

package de.visualdependencies.data.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("view")
public class SchemaViewColumn extends SchemaColumn {

	private static final long serialVersionUID = -21971115593825263L;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn
	private SchemaView view;

	public SchemaView getView() {
		return view;
	}

	public void setView(final SchemaView view) {
		this.view = view;
	}
}
