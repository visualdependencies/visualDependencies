package de.visualdependencies.data.dao;

import java.io.Serializable;
import java.util.List;

import de.visualdependencies.data.entity.AbstractEntity;

public interface GeneralDao<E extends AbstractEntity<? extends Serializable>> {

	E create();

	boolean delete(E entity);

	E findById(Serializable id);

	List<E> list();

	boolean save(E entity);
}
