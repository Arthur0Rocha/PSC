package br.com.cadastroFuncionarios.services;

import java.util.List;
import java.util.Optional;

import br.com.cadastroFuncionarios.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cadastroFuncionarios.domain.Funcionario;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository repo;
	
	public List<Funcionario> findAll() {
		return repo.findAll();
	}
	
	public Funcionario find(Integer id) throws RuntimeException {
		Optional<Funcionario> func = repo.findById(id);
		return func.orElseThrow(() -> new RuntimeException("Objeto não encontrado:" + this.getClass().getName() + " ID: " + id));
	}
	
	public Funcionario insert(Funcionario obj) throws RuntimeException {
		obj.setId(null);
		return repo.save(obj);
	}

	public Funcionario update(Funcionario obj) {
		find(obj.getId());
		return repo.save(obj);
	}

	public void delete(Integer id) {
		find(id);
		repo.deleteById(id);
	}
}
