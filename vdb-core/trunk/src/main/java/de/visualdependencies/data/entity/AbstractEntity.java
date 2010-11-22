package de.visualdependencies.data.entity;

import java.io.Serializable;

import javax.persistence.Version;

public abstract class AbstractEntity<T extends Serializable> implements Serializable {

	private static final long serialVersionUID = 7673954552151940556L;

	@Version
	protected Integer version;

	public Integer getVersion() {
		return version;
	}

	public void setVersion(final Integer version) {
		this.version = version;
	}

}
