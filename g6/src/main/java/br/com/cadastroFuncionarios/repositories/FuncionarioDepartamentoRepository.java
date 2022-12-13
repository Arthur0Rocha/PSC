package br.com.cadastroFuncionarios.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cadastroFuncionarios.domain.FuncionarioDepartamento;

@Repository
public interface FuncionarioDepartamentoRepository extends JpaRepository<FuncionarioDepartamento, Integer>{

}
