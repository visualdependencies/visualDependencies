package de.visualdependencies.data.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "schema")
public class Schema extends AbstractEntity<Long> {

	private static final long serialVersionUID = -8769256751085624275L;

	@Id
	@GeneratedValue
	private long id;

	@Column
	private String name;

	@ElementCollection
	@JoinTable(name = "schema_data", joinColumns = @JoinColumn(name = "schemaId"))
	// @MapKeyEnumerated(EnumType.STRING)
	@Column(name = "value")
	private Map<String, String> data;

	@OneToOne
	private SchemaConnection connection;

	@OneToMany
	@Cascade(CascadeType.ALL)
	private List<SchemaTable> tables;

	@OneToMany
	@Cascade(CascadeType.ALL)
	private List<SchemaView> views;

	@OneToMany
	@Cascade(CascadeType.ALL)
	private List<SchemaTrigger> triggers;

	@OneToMany
	@Cascade(CascadeType.ALL)
	private List<SchemaFunction> functions;

	@OneToMany
	@Cascade(CascadeType.ALL)
	private List<SchemaProcedure> procedures;

	public SchemaConnection getConnection() {
		return connection;
	}

	public Map<String, String> getData() {
		if (data == null) {
			setData(new HashMap<String, String>());
		}
		return data;
	}

	public List<SchemaFunction> getFunctions() {
		if (functions == null) {
			setFunctions(new ArrayList<SchemaFunction>());
		}
		return functions;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<SchemaProcedure> getProcedures() {
		if (procedures == null) {
			setProcedures(new ArrayList<SchemaProcedure>());
		}
		return procedures;
	}

	public List<SchemaTable> getTables() {
		if (tables == null) {
			setTables(new ArrayList<SchemaTable>());
		}
		return tables;
	}

	public List<SchemaTrigger> getTriggers() {
		if (triggers == null) {
			setTriggers(new ArrayList<SchemaTrigger>());
		}
		return triggers;
	}

	public List<SchemaView> getViews() {
		if (views == null) {
			setViews(new ArrayList<SchemaView>());
		}
		return views;
	}

	public void setConnection(final SchemaConnection connection) {
		this.connection = connection;
	}

	public void setData(final Map<String, String> data) {
		this.data = data;
	}

	public void setFunctions(final List<SchemaFunction> functions) {
		this.functions = functions;
	}

	public void setId(final long id) {
		this.id = id;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public void setProcedures(final List<SchemaProcedure> procedures) {
		this.procedures = procedures;
	}

	public void setTables(final List<SchemaTable> tables) {
		this.tables = tables;
	}

	public void setTriggers(final List<SchemaTrigger> triggers) {
		this.triggers = triggers;
	}

	public void setViews(final List<SchemaView> views) {
		this.views = views;
	}

}
