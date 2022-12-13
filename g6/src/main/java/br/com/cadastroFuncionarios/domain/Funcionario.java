package br.com.cadastroFuncionarios.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Funcionario extends Pessoa implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name="cargo_id")
	private Cargo cargo;
	
	@JsonBackReference
	@OneToMany(mappedBy="id.funcionario")
	private Set<FuncionarioDepartamento> departamentos = new HashSet<>();

	public Cargo getCargo() {
		return cargo;
	}
	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	public List<Departamento> getDepartamentos() {
		List<Departamento> deptos = new ArrayList<>();
		for(FuncionarioDepartamento x : departamentos) {
			deptos.add(x.getDepartamento());
		}
		return deptos;
	}
	public void setDepartamentos(Set<FuncionarioDepartamento> departamentos) {
		this.departamentos = departamentos;
	}
	public Funcionario(Integer id, String name, int age, Date birthday, String document, Cargo cargo) {
		super();
		this.setId(id);
		this.setName(name);
		this.setAge(age);
		this.setBirthday(birthday);
		this.setDocument(document);
		this.cargo = cargo;
	}
	public Funcionario() {
		super();
		// TODO Auto-generated constructor stub
	}

}
