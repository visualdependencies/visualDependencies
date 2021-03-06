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

package de.visualdependencies.data.dao.impl;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import de.visualdependencies.data.dao.SchemaConnectionDao;
import de.visualdependencies.data.entity.Schema;
import de.visualdependencies.data.entity.SchemaConnection;

@Repository
public class SchemaConnectionDaoImpl extends AbstractGeneralDaoImpl<SchemaConnection> implements SchemaConnectionDao {

	@Override
	public SchemaConnection getBy(final Schema schema) {
		return (SchemaConnection) createCriteria().add(Restrictions.eq("schema.id", schema.getId())).uniqueResult();
	}
}
