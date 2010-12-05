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
