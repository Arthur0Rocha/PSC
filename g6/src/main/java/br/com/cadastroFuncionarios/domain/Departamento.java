package br.com.cadastroFuncionarios.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Departamento implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="departamento_id")
	private Integer id;
	@Column(name="departamento_name", length=50)
	private String name;
	
	@JsonIgnore
	@OneToMany(mappedBy="id.departamento")
	private Set<FuncionarioDepartamento> funcionarios = new HashSet<>();
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<FuncionarioDepartamento> getFuncionarios() {
		return funcionarios;
	}
	public void setFuncionarios(Set<FuncionarioDepartamento> funcionarios) {
		this.funcionarios = funcionarios;
	}
	public Departamento(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Departamento() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
