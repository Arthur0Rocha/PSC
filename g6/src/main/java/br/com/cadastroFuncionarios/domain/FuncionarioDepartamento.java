package br.com.cadastroFuncionarios.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class FuncionarioDepartamento implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private FuncionarioDepartamentoPK id =  new FuncionarioDepartamentoPK();

	public FuncionarioDepartamento() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FuncionarioDepartamento(Funcionario funcionario, Departamento departamento, Date startDate) {
		super();
		id.setFuncionario(funcionario);
		id.setDepartamento(departamento);
		id.setStartDate(startDate);
	}

	public FuncionarioDepartamentoPK getId() {
		return id;
	}

	public void setId(FuncionarioDepartamentoPK id) {
		this.id = id;
	}
	
	@JsonIgnore
	public Departamento getDepartamento() {
		return id.getDepartamento();
	}
	
	@JsonIgnore
	public Funcionario getFuncionario() {
		return id.getFuncionario();
	}
		
}
