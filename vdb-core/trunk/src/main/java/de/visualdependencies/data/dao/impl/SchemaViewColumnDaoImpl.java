package de.visualdependencies.data.dao.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import de.visualdependencies.data.dao.SchemaViewColumnDao;
import de.visualdependencies.data.entity.SchemaView;
import de.visualdependencies.data.entity.SchemaViewColumn;

@Repository
public class SchemaViewColumnDaoImpl extends AbstractGeneralDaoImpl<SchemaViewColumn> implements SchemaViewColumnDao {

	@Override
	public List<SchemaViewColumn> list(final SchemaView view) {
		@SuppressWarnings("unchecked")
		final List<SchemaViewColumn> list = createCriteria().add(Restrictions.eq("view.id", view.getId())).list();
		return list;
	}

}