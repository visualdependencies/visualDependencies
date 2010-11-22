package de.visualdependencies.data.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;

import de.visualdependencies.data.dao.GeneralDao;
import de.visualdependencies.data.entity.AbstractEntity;

public abstract class AbstractGeneralDaoImpl<E extends AbstractEntity<? extends Serializable>> implements GeneralDao<E> {

	private final Class<E> persistentClass;

	@Autowired
	protected SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	protected AbstractGeneralDaoImpl() {
		// Retrieve the annotated class of generic type T.
		persistentClass = (Class<E>) ((ParameterizedType) this.getClass().getGenericSuperclass())
		        .getActualTypeArguments()[0];
	}

	@Override
	public E create() {
		try {
			final E instance = getPersistentClass().newInstance();
			return instance;
		} catch (final Exception e) {
			return null;
		}
	}

	protected Criteria createCriteria() {
		final Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(getPersistentClass());
	}

	@Override
	public boolean delete(final E entity) {
		sessionFactory.getCurrentSession().delete(entity);
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E findById(final Serializable id) {
		return (E) sessionFactory.getCurrentSession().load(getPersistentClass(), id);
	}

	public Class<E> getPersistentClass() {
		return persistentClass;
	}

	@Override
	public List<E> list() {
		final Criteria criteria = createCriteria();
		criteria.addOrder(Order.asc("name"));

		@SuppressWarnings("unchecked")
		final List<E> list = criteria.list();
		return list;
	}

	@Override
	public boolean save(final E entity) {
		sessionFactory.getCurrentSession().saveOrUpdate(entity);
		return true;
	}

}
