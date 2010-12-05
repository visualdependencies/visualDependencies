package de.visualdependencies.data.entity;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.OptimisticLockType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name = "type",
        discriminatorType = DiscriminatorType.STRING)
@org.hibernate.annotations.Entity(optimisticLock = OptimisticLockType.VERSION)
@Table(name = "columns")
@DiscriminatorValue("abstract")
public class SchemaColumn extends AbstractEntity<Long> {

	private static final long serialVersionUID = -21971115593825263L;

	@Id
	@GeneratedValue
	private long id;

	@Column
	private String name;

	@ElementCollection(fetch = FetchType.EAGER)
	@BatchSize(size = 25)
	@JoinTable(name = "columns_data", joinColumns = @JoinColumn(name = "columnId"))
	// @MapKeyEnumerated(EnumType.STRING)
	@Column(name = "value")
	private Map<String, String> data;

	public Map<String, String> getData() {
		if (data == null) {
			setData(new HashMap<String, String>());
		}
		return data;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setData(final Map<String, String> data) {
		this.data = data;
	}

	public void setId(final long id) {
		this.id = id;
	}

	public void setName(final String name) {
		this.name = name;
	}

}
