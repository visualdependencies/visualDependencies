package de.visualdependencies.data.entity;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OptimisticLockType;

@Entity
@org.hibernate.annotations.Entity(optimisticLock = OptimisticLockType.VERSION)
@Table(name = "tables")
public class SchemaTable extends AbstractEntity<Long> {

	private static final long serialVersionUID = 358386476013342777L;

	@Id
	@GeneratedValue
	private long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn
	private Schema schema;

	@ElementCollection(fetch = FetchType.EAGER)
	@JoinTable(name = "tables_data", joinColumns = @JoinColumn(name = "tableId"))
	// @MapKeyEnumerated(EnumType.STRING)
	@Column(name = "value")
	private Map<String, String> data;

	private String name;

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

	public Schema getSchema() {
		return schema;
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

	public void setSchema(final Schema schema) {
		this.schema = schema;
	}

}
