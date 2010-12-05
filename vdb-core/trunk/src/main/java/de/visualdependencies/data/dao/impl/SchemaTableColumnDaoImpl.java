package de.visualdependencies.data.dao.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import de.visualdependencies.data.dao.SchemaTableColumnDao;
import de.visualdependencies.data.entity.SchemaTable;
import de.visualdependencies.data.entity.SchemaTableColumn;

@Repository
public class SchemaTableColumnDaoImpl extends AbstractGeneralDaoImpl<SchemaTableColumn> implements SchemaTableColumnDao {

	@Override
	public List<SchemaTableColumn> list(final SchemaTable table) {
		@SuppressWarnings("unchecked")
		final List<SchemaTableColumn> list = createCriteria().add(Restrictions.eq("table.id", table.getId())).list();
		return list;
	}
}