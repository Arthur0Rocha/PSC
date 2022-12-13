package br.com.cadastroFuncionarios.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cadastroFuncionarios.domain.Cargo;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Integer>{
	
}
